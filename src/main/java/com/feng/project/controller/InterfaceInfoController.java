package com.feng.project.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.feng.fengapiclientsdk.client.FengApiClient;
import com.feng.fengapicommon.model.entity.InterfaceInfo;
import com.feng.fengapicommon.model.entity.User;
import com.feng.project.annotation.AuthCheck;
import com.feng.project.common.*;
import com.feng.project.constant.CommonConstant;
import com.feng.project.exception.BusinessException;
import com.feng.project.model.dto.interfaceInfo.InterfaceInfoAddRequest;
import com.feng.project.model.dto.interfaceInfo.InterfaceInfoInvokeRequest;
import com.feng.project.model.dto.interfaceInfo.InterfaceInfoQueryRequest;
import com.feng.project.model.dto.interfaceInfo.InterfaceInfoUpdateRequest;
import com.feng.project.model.enums.InterfaceInfoStatusEnum;
import com.feng.project.service.InterfaceInfoService;
import com.feng.project.service.UserService;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 接口管理
 *
 * @author feng
 */
@RestController
@RequestMapping("/interfaceInfo")
@Slf4j
public class InterfaceInfoController {

    @Resource
    private InterfaceInfoService interfaceInfoService;

    @Resource
    private UserService userService;

    @Resource
    private FengApiClient fengApiClient;

    // region 增删改查

    /**
     * 发布接口
     *
     * @param idRequest
     * @param request
     * @return
     */
    @PostMapping("/online")
    @AuthCheck(mustRole = "admin")
    public BaseResponse<Boolean> onlineInterfaceInfo(@RequestBody IdRequest idRequest,
                                                     HttpServletRequest request) {
        // 如果id为null或者id小于等于0
        if (idRequest == null || idRequest.getId() <= 0) {
            // 抛出业务异常，表示请求参数错误
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        // 1.校验该接口是否存在
        // 获取idRequest对象的id属性值
        long id = idRequest.getId();
        // 根据id查询接口信息数据
        InterfaceInfo oldInterfaceInfo = interfaceInfoService.getById(id);
        // 如果查询结果为空
        if (oldInterfaceInfo == null) {
            // 抛出业务异常，表示未找到数据
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 2.判断该接口是否可以调用
        // 创建一个User对象(这里先模拟一下，搞个假数据)
        com.feng.fengapiclientsdk.model.User user = new com.feng.fengapiclientsdk.model.User();
        user.setUsername("test");
        String username = fengApiClient.getUserNameByPost(user);
        if (StringUtils.isBlank(username)) {
            // 抛出系统错误的业务异常，表示系统内部异常，并附带错误信息"接口验证失败"
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "接口验证失败");
        }
        // 创建一个InterfaceInfo对象
        InterfaceInfo interfaceInfo = new InterfaceInfo();
        // 设置interfaceInfo的id属性为id
        interfaceInfo.setId(id);
        // 3.修改接口数据库中的状态字段为 1
        interfaceInfo.setStatus(InterfaceInfoStatusEnum.ONLINE.getValue());
        boolean result = interfaceInfoService.updateById(interfaceInfo);
        return ResultUtils.success(result);
    }
    /**
     * 下线接口
     *
     * @param idRequest
     * @param request
     * @return
     */
    @PostMapping("/offline")
    @AuthCheck(mustRole = "admin")
    public BaseResponse<Boolean> offlineInterfaceInfo(@RequestBody IdRequest idRequest,
                                                     HttpServletRequest request) {
        // 如果id为null或者id小于等于0
        if (idRequest == null || idRequest.getId() <= 0) {
            // 抛出业务异常，表示请求参数错误
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        // 1.校验该接口是否存在
        // 获取idRequest对象的id属性值
        long id = idRequest.getId();
        // 根据id查询接口信息数据
        InterfaceInfo oldInterfaceInfo = interfaceInfoService.getById(id);
        // 如果查询结果为空
        if (oldInterfaceInfo == null) {
            // 抛出业务异常，表示未找到数据
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }

        // 创建一个InterfaceInfo对象
        InterfaceInfo interfaceInfo = new InterfaceInfo();
        // 设置interfaceInfo的id属性为id
        interfaceInfo.setId(id);
        // 2.修改接口数据库中的状态字段为 0
        interfaceInfo.setStatus(InterfaceInfoStatusEnum.OFFLINE.getValue());
        boolean result = interfaceInfoService.updateById(interfaceInfo);
        return ResultUtils.success(result);
    }

    /**
     * 调试接口
     * @param interfaceInfoInvokeRequest
     * @param request
     * @return
     */
    @PostMapping("/invoke")
    @AuthCheck(mustRole = "admin")
    public BaseResponse<Object> invokeInterfaceInfo(@RequestBody InterfaceInfoInvokeRequest interfaceInfoInvokeRequest,
                                                      HttpServletRequest request) {
        // 检查请求对象是否为空或者接口id是否小于等于0
        if (interfaceInfoInvokeRequest == null || interfaceInfoInvokeRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        // 获取接口id
        long id = interfaceInfoInvokeRequest.getId();

        // 获取用户请求参数
        String userRequestParams = interfaceInfoInvokeRequest.getUserRequestParams();

        // 判断是否存在
        InterfaceInfo oldInterfaceInfo = interfaceInfoService.getById(id);
        if (oldInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }

        // 检查接口状态是否为下线状态
        if (oldInterfaceInfo.getStatus() == InterfaceInfoStatusEnum.OFFLINE.getValue()) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口已关闭");
        }

        // 获取当前登录用户的ak和sk，这样相当于用户自己的这个身份去调用，
        // 也不会担心它刷接口，因为知道是谁刷了这个接口，会比较安全
        User loginUser = userService.getLoginUser(request);
        String accessKey = loginUser.getAccessKey();
        String secretKey = loginUser.getSecretKey();
        FengApiClient tempclient = new FengApiClient(accessKey, secretKey);


        // 我们只需要进行测试调用，所以我们需要解析传递过来的参数。
        Gson gson = new Gson();
        // 将用户请求参数转换为com.feng.fengapiclientsdk.model.User对象
        com.feng.fengapiclientsdk.model.User user = gson.fromJson(userRequestParams, com.feng.fengapiclientsdk.model.User.class);
        // 调用YuApiClient的getUsernameByPost方法，传入用户对象，获取用户名
        String usernameByPost = tempclient.getUserNameByPost(user);
        // 返回成功响应，并包含调用结果
        return ResultUtils.success(usernameByPost);
    }


    /**
     * 创建
     *
     * @param interfaceInfoAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addInterfaceInfo(@RequestBody InterfaceInfoAddRequest interfaceInfoAddRequest, HttpServletRequest request) {
        if (interfaceInfoAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        InterfaceInfo interfaceInfo = new InterfaceInfo();
        BeanUtils.copyProperties(interfaceInfoAddRequest, interfaceInfo);
        // 校验
        interfaceInfoService.validInterfaceInfo(interfaceInfo, true);
        User loginUser = userService.getLoginUser(request);
        interfaceInfo.setUserId(loginUser.getId());
        boolean result = interfaceInfoService.save(interfaceInfo);
        if (!result) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        long newInterfaceInfoId = interfaceInfo.getId();
        return ResultUtils.success(newInterfaceInfoId);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteInterfaceInfo(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        // 判断是否存在
        InterfaceInfo oldInterfaceInfo = interfaceInfoService.getById(id);
        if (oldInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 仅本人或管理员可删除
        if (!oldInterfaceInfo.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = interfaceInfoService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新
     *
     * @param interfaceInfoUpdateRequest
     * @param request
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateInterfaceInfo(@RequestBody InterfaceInfoUpdateRequest interfaceInfoUpdateRequest,
                                            HttpServletRequest request) {
        if (interfaceInfoUpdateRequest == null || interfaceInfoUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        InterfaceInfo interfaceInfo = new InterfaceInfo();
        BeanUtils.copyProperties(interfaceInfoUpdateRequest, interfaceInfo);
        // 参数校验
        interfaceInfoService.validInterfaceInfo(interfaceInfo, false);
        User user = userService.getLoginUser(request);
        long id = interfaceInfoUpdateRequest.getId();
        // 判断是否存在
        InterfaceInfo oldInterfaceInfo = interfaceInfoService.getById(id);
        if (oldInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        // 仅本人或管理员可修改
        if (!oldInterfaceInfo.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean result = interfaceInfoService.updateById(interfaceInfo);
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public BaseResponse<InterfaceInfo> getInterfaceInfoById(long id) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        InterfaceInfo interfaceInfo = interfaceInfoService.getById(id);
        return ResultUtils.success(interfaceInfo);
    }

    /**
     * 获取列表（仅管理员可使用）
     *
     * @param interfaceInfoQueryRequest
     * @return
     */
    @AuthCheck(mustRole = "admin")
    @GetMapping("/list")
    public BaseResponse<List<InterfaceInfo>> listInterfaceInfo(InterfaceInfoQueryRequest interfaceInfoQueryRequest) {
        InterfaceInfo interfaceInfoQuery = new InterfaceInfo();
        if (interfaceInfoQueryRequest != null) {
            BeanUtils.copyProperties(interfaceInfoQueryRequest, interfaceInfoQuery);
        }
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>(interfaceInfoQuery);
        List<InterfaceInfo> interfaceInfoList = interfaceInfoService.list(queryWrapper);
        return ResultUtils.success(interfaceInfoList);
    }

    /**
     * 分页获取列表
     *
     * @param interfaceInfoQueryRequest
     * @param request
     * @return
     */
    @GetMapping("/list/page")
    public BaseResponse<Page<InterfaceInfo>> listInterfaceInfoByPage(InterfaceInfoQueryRequest interfaceInfoQueryRequest, HttpServletRequest request) {
        if (interfaceInfoQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        InterfaceInfo interfaceInfoQuery = new InterfaceInfo();
        BeanUtils.copyProperties(interfaceInfoQueryRequest, interfaceInfoQuery);
        long current = interfaceInfoQueryRequest.getCurrent();
        long size = interfaceInfoQueryRequest.getPageSize();
        String sortField = interfaceInfoQueryRequest.getSortField();
        String sortOrder = interfaceInfoQueryRequest.getSortOrder();
        String description = interfaceInfoQuery.getDescription();
        // description 需支持模糊搜索
        interfaceInfoQuery.setDescription(null);
        // 限制爬虫
        if (size > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>(interfaceInfoQuery);
        queryWrapper.like(StringUtils.isNotBlank(description), "description", description);
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField),
                sortOrder.equals(CommonConstant.SORT_ORDER_ASC), sortField);
        Page<InterfaceInfo> interfaceInfoPage = interfaceInfoService.page(new Page<>(current, size), queryWrapper);
        return ResultUtils.success(interfaceInfoPage);
    }

    // endregion

}
