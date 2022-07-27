package org.yhsu.wstgwproxy.bean;

import lombok.Data;

/**
 * 服务返回值
 *
 * @author: yhsu
 * @createDate: 2022/7/27 11:35
 */
@Data
public class ServiceResult<T> {

    private String errCode;

    private String errMsg;

    private T data;

    private boolean flag;

    public static final ServiceResult buildError(String errCode, String errMsg) {
        ServiceResult result = new ServiceResult();
        result.setErrCode(errCode);
        result.setErrMsg(errMsg);
        return result;
    }
}
