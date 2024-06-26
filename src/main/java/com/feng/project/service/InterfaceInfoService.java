package com.feng.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.feng.fengapicommon.model.entity.InterfaceInfo;

/**
 *
 */
public interface InterfaceInfoService extends IService<InterfaceInfo> {

    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
