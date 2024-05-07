package com.feng.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feng.fengapicommon.model.entity.UserInterfaceInfo;

public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {

    public void validInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) ;

    public boolean invokeCount(long interfaceInfoId, long userId);
}
