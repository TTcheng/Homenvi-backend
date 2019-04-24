package me.wcc.auth.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.wcc.auth.entity.User;
import me.wcc.base.controller.BaseController;
import me.wcc.base.exception.CommonException;
import me.wcc.base.infra.constant.BaseConstants;
import me.wcc.base.infra.utils.Results;
import me.wcc.homenvi.entity.Notification;
import me.wcc.homenvi.service.NotificationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author chuncheng.wang@hand-china.com 19-3-27 上午10:22
 */
@RestController
@Api(tags = "认证接口")
@RequestMapping("/auth")
@FrameworkEndpoint
public class AuthController extends BaseController {
    @Autowired
    private TokenEndpoint tokenEndpoint;

    @Autowired
    @Qualifier("consumerTokenServices")
    ConsumerTokenServices consumerTokenServices;

    @Autowired
    NotificationService notificationService;

    @GetMapping("/check")
    @ApiOperation("检查是否登录")
    public ResponseEntity check() {
        return Results.success();
    }

    @GetMapping("/current")
    @ApiOperation("获取当前用户信息")
    public ResponseEntity<User> current() {
        return Results.success(super.getCurrentUser());
    }

    @ApiOperation(value = "当前用户关联明细")
    @GetMapping("/currentDetail")
    public ResponseEntity<User> currentDetail() {
        User user = super.getCurrentUser();
        Notification conditions = new Notification(user.getId(), BaseConstants.FLAG_YES);
        user.setNotifyCount(notificationService.selectCount(conditions));
        return Results.success(user);
    }

    private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_SECRET = "client_secret";

    @PostMapping("/login")
    @ApiOperation("登录")
    public ResponseEntity<OAuth2AccessToken> login(@RequestParam Map<String, String> parameters)
            throws HttpRequestMethodNotSupportedException {
        String clientId = parameters.get(CLIENT_ID);
        if (null == clientId) {
            throw new CommonException(BaseConstants.ErrorCode.FIELD_NOT_NULL, CLIENT_ID);
        }
        String clientSecret = parameters.get(CLIENT_SECRET);
        if (null == clientSecret) {
            throw new CommonException(BaseConstants.ErrorCode.FIELD_NOT_NULL, CLIENT_SECRET);
        }
        List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("USER");
        org.springframework.security.core.userdetails.User client =
                new org.springframework.security.core.userdetails.User(clientId, clientSecret, authorities);
        UsernamePasswordAuthenticationToken principal = new UsernamePasswordAuthenticationToken(client,
                null, null);
        return tokenEndpoint.postAccessToken(principal, parameters);
    }

    @DeleteMapping("/logout")
    @ApiOperation("登出")
    public ResponseEntity<String> logout() {
        String accessToken = request.getHeader("Authorization");
        if (StringUtils.isNoneBlank(accessToken) && accessToken.startsWith(OAuth2AccessToken.BEARER_TYPE)) {
            accessToken = accessToken.substring(OAuth2AccessToken.BEARER_TYPE.length() + 1);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\":\"unauthorized\",\"error_description\":\"Full authentication is required to access this resource\"}");
        }
        if (consumerTokenServices.revokeToken(accessToken)) {
            return Results.success("注销成功!");
        } else {
            return Results.error("注销失败!");
        }
    }

}
