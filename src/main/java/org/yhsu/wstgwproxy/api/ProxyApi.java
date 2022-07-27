package org.yhsu.wstgwproxy.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.yhsu.wstgwproxy.bean.GetUserInfoParams;
import org.yhsu.wstgwproxy.service.ProxyService;

/**
 * 代理Api接口
 *
 * @author: yhsu
 * @createDate: 2022/7/27 11:32
 */
@RestController
@RequestMapping("/api/v1/")
public class ProxyApi {

    @Autowired
    private ProxyService proxyService;

    /**
     * POST请求根据Token获取用户信息
     *
     * @param token
     * @return
     */
    @GetMapping(value = "getUserInfoByToken", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getUserInfoByToken(@RequestParam(value = "token") String token, @RequestParam(value = "serviceId", required = false) String serviceId, @RequestParam(value = "roleCode", required = false) String roleCode) {
        return proxyService.getUserInfoByToken(token, serviceId, roleCode);
    }

    /**
     * POST请求根据Token获取用户信息
     *
     * @param userInfoParams
     * @return
     */
    @PostMapping(value = "getUserInfoByToken", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getUserInfoByTokenPost(@RequestBody GetUserInfoParams userInfoParams) {
        return proxyService.getUserInfoByToken(userInfoParams.getToken(), userInfoParams.getServiceId(), userInfoParams.getRoleCode());
    }
}
