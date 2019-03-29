package me.wcc.auth.endpoint;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.wcc.base.infra.utils.Results;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登出接口
 *
 * @author chuncheng.wang@hand-china.com 19-3-28 下午2:41
 **/
@FrameworkEndpoint
@Api(tags = "登出接口")
public class TokenRevokeEndpoint {

    @Autowired
    @Qualifier("consumerTokenServices")
    ConsumerTokenServices consumerTokenServices;

    @PostMapping("/oauth/remove")
    @ApiOperation("退出登录")
    @ResponseBody
    public ResponseEntity<String> removeToken(@RequestHeader("Authorization") String access_token) {
        if (StringUtils.isNoneBlank(access_token)) {
            access_token = access_token.substring(OAuth2AccessToken.BEARER_TYPE.length() + 1);
        } else {
            return Results.error("认证失败");
        }
        if (consumerTokenServices.revokeToken(access_token)) {
            return Results.success("注销成功");
        } else {
            return Results.error("注销失败");
        }
    }
}
