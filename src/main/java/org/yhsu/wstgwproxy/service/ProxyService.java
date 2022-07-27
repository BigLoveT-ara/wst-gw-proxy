package org.yhsu.wstgwproxy.service;

import com.alibaba.fastjson.JSON;
import com.iflytek.wst.gateway.sdk.constant.SdkConstant;
import com.iflytek.wst.gateway.sdk.enums.HttpMethod;
import com.iflytek.wst.gateway.sdk.enums.ParamPosition;
import com.iflytek.wst.gateway.sdk.model.ApiRequest;
import com.iflytek.wst.gateway.sdk.model.ApiResponse;
import com.iflytek.wst.gateway.sdk.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yhsu.wstgwproxy.bean.ServiceResult;
import org.yhsu.wstgwproxy.core.WstRestClient;

/**
 * 接口代理的业务层接口
 *
 * @author: yhsu
 * @createDate: 2022/7/27 11:33
 */
@Service
@Slf4j
public class ProxyService {

    @Autowired
    private WstRestClient wstRestClient;

    public static final String STR_EMPTY = "";

    public static final String STR_DEFAULT = "default";

    /**
     * 根据Token获取用户信息
     *
     * @param token
     * @return
     */
    public String getUserInfoByToken(String token, String serviceId, String roleCode) {
        log.debug("使用Token获取用户信息,token:{};serviceId:{},roleCode:{}", token, serviceId, roleCode);
        if (StringUtils.isBlank(token)) {
            log.warn("token为空");
            return JSON.toJSONString(ServiceResult.buildError("-1", "token为空"));
        }
        if (null == serviceId) {
            serviceId = STR_DEFAULT;
        }
        if (null == roleCode) {
            roleCode = STR_DEFAULT;
        }
        try {
            ApiRequest request = new ApiRequest(HttpMethod.GET, "/user/getUserInfoByToken");
            request.addParam("token", token, ParamPosition.QUERY, true);
            request.addParam("serviceId", serviceId, ParamPosition.QUERY, true);
            request.addParam("roleCode", roleCode, ParamPosition.QUERY, true);
            ApiResponse apiResponse = wstRestClient.sendSyncRequest(request);
            if (null == apiResponse) {
                return JSON.toJSONString(ServiceResult.buildError("-1", "接口调用异常"));
            }
            return new String(apiResponse.getBody(), SdkConstant.CLOUDAPI_ENCODING);
        } catch (Exception e) {
            log.error("调用皖事通网关接口，根据Token获取用户信息异常,token:{}", token);
            log.error("调用皖事通网关接口，根据Token获取用户信息异常", e);
            return JSON.toJSONString(ServiceResult.buildError("-1", "接口调用异常"));
        }
    }
}
