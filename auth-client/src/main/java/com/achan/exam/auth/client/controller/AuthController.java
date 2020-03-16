package com.achan.exam.auth.client.controller;

import cn.hutool.http.HttpUtil;
import com.achan.exam.auth.client.bean.param.UserParam;
import com.achan.exam.common.annotation.BaseResponse;
import com.achan.exam.util.redis.SimpleStringRedisUtil;
import com.sun.istack.NotNull;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
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
    @Autowired
    private SimpleStringRedisUtil redisUtil;

    @PostMapping("/login")
    @ApiOperation("登录")
    public OAuth2AccessToken login(@RequestBody @Validated UserParam param) throws IOException {
        Map<String, Object> params = new HashMap<>(5);
        params.put("username", param.getUsername());
        params.put("password", param.getPassword());
        params.put("grant_type", "password");
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        return getOAuth2Token(params);
    }

    private OAuth2AccessToken getOAuth2Token(Map<String, Object> params) throws IOException {
        String result = HttpUtil.post(tokenUri, params, 1000 * 30);
        ObjectMapper objectMapper = new ObjectMapper();
        OAuth2AccessToken resultObject = objectMapper.readValue(result, OAuth2AccessToken.class);
        if (resultObject.getValue() == null) {
            throw objectMapper.readValue(result, InvalidGrantException.class);
        }
        return resultObject;
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
        return getOAuth2Token(params);
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.DELETE, RequestMethod.POST})
    @ApiOperation("退出登录")
    public void logout(@RequestParam(name = "access-token") @Validated @NotBlank String accessToken,
                       @RequestParam(name = "refresh-token") @Validated @NotBlank String refreshToken) {
        // TODO: 这里只是把能看明白是干啥的删掉了，没有删全
        redisUtil.delete("access:" + accessToken);
        redisUtil.delete("auth:" + accessToken);
        redisUtil.delete("access_to_refresh:" + accessToken);
        redisUtil.delete("refresh:" + refreshToken);
        redisUtil.delete("refresh_auth:" + refreshToken);
        redisUtil.delete("refresh_to_access:" + refreshToken);
    }
}
