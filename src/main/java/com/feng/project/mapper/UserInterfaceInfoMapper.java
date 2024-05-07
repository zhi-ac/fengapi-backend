package com.feng.project.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feng.fengapicommon.model.entity.UserInterfaceInfo;

import java.util.List;

/**
 * @Entity generator.domain.UserInterfaceInfo
 */
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {

    //select interfaceInfoId, sum(totalNum) as totalNum from user_interface_info
    //group by interfaceInfoId
    //order by totalNum desc
    //limit 3
    List<UserInterfaceInfo> listTopInvokeInterfaceInfo(int limit);

}




