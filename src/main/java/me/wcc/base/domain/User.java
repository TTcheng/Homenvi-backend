package me.wcc.base.domain;

import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**
 * 用户
 *
 * @author chuncheng.wang@hand-china.com 19-3-10 下午10:01
 */
public class User implements Serializable {
    private static final long serialVersionUID = 3255509625071009422L;
    private Long id;
    private String loginName;
    private String phone;
    private String email;
    private String address;
    private boolean enabled;
    private String realName;
    private String password;
    private String remark;
    @Transient
    private List<Role> roles;

    public User(String loginName) {
        this.loginName = loginName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
