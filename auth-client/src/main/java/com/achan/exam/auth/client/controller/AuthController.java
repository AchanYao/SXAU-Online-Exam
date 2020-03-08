package com.achan.exam.auth.client.controller;

import cn.hutool.http.HttpUtil;
import com.achan.exam.auth.client.bean.param.UserParam;
import com.achan.exam.common.annotation.BaseResponse;
import com.sun.istack.NotNull;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Achan
 * @date 2020/3/7
 */
@RestController
@RequestMapping("/auth")
@Api(tags = "认证操作")
@BaseResponse
public class AuthController {

    @Value("${security.oauth2.client.client-id}")
    private String clientId;
    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;
    @Value("${security.oauth2.client.access-token-uri}")
    private String tokenUri;

    @PostMapping("/login")
    @ApiOperation("登录")
    public OAuth2AccessToken login(@RequestBody @Validated UserParam param) throws IOException {
        Map<String, Object> params = new HashMap<>(5);
        params.put("username", param.getUsername());
        params.put("password", param.getPassword());
        params.put("grant_type", "password");
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        String result = HttpUtil.post(tokenUri, params, 1000 * 30);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(result, OAuth2AccessToken.class);
    }

    @GetMapping("/me")
    @ApiOperation("获取当前登录的用户信息")
    @PreAuthorize("isAuthenticated()")
    public UserDetails authMe(Authentication authentication) {
        return (UserDetails) authentication.getPrincipal();
    }

    @PostMapping("/refresh")
    @ApiOperation("刷新token")
    public OAuth2AccessToken refreshToken(@RequestParam @Validated @NotNull @NotBlank String token) throws IOException {
        Map<String, Object> params = new HashMap<>(4);
        params.put("grant_type", "refresh_token");
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        params.put("refresh_token", token);
        String result = HttpUtil.post(tokenUri, params, 1000 * 30);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(result, OAuth2AccessToken.class);
    }
}
