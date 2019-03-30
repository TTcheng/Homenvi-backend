package me.wcc.auth.domain;

import me.wcc.auth.domain.entity.User;
import me.wcc.base.infra.constant.BaseConstants;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author chuncheng.wang@hand-china.com 19-3-10 下午9:59
 */
public class CustomUserDetails implements Serializable, UserDetails {

    private static final long serialVersionUID = -5283831892608417510L;
    public static final String CLASS_NAME = "CustomUserDetails";
    private User user;

    public CustomUserDetails() {
    }

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        /*
        * List<GrantedAuthority> authorities = new ArrayList<>();
        * for (Role role : user.getRoles()) {
        *    authorities.add(new SimpleGrantedAuthority(role.getName()));
        * }
        * return authorities;
        */
        return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_SUPER");
    }

    public User getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLoginName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return BaseConstants.FLAG_NO.equals(user.getLocked());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return BaseConstants.FLAG_YES.equals(user.getEnabled());
    }

    public String getTimeZone() {
        // TODO timeZone
        return null;
    }

    public String getLanguage() {
        // TODO language
        return null;
    }

    public Long getUserId() {
        // TODO userID
        return null;
    }
}
