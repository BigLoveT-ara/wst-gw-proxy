package org.yhsu.wstgwproxy.bean;

import lombok.Data;

/**
 * 根据Token获取用户信息的借口参数
 *
 * @author: yhsu
 * @createDate: 2022/7/27 12:42
 */
@Data
public class GetUserInfoParams {

    private String token;

    private String serviceId;

    private String roleCode;
}
