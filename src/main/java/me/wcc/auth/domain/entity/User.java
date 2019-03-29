package me.wcc.auth.domain.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.choerodon.mybatis.domain.AuditDomain;
import io.choerodon.mybatis.annotation.ModifyAudit;
import io.choerodon.mybatis.annotation.VersionAudit;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import me.wcc.auth.domain.Role;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户信息表 oauth认证主体
 *
 * @author ttchengwang@gmail.com 2019-03-25 17:32:27
 */
@ApiModel("")
@VersionAudit
@ModifyAudit
@Table(name = "iam_user")
public class User extends AuditDomain implements Serializable {

    private static final long serialVersionUID = 5707454509247323466L;

    public static final String FIELD_ID = "id";
    public static final String FIELD_LOGIN_NAME = "loginName";
    public static final String FIELD_EMAIL = "email";
    public static final String FIELD_PASSWORD = "password";
    public static final String FIELD_REAL_NAME = "realName";
    public static final String FIELD_PHONE = "phone";
    public static final String FIELD_IMAGE_URL = "imageUrl";
    public static final String FIELD_PROFILE_PHOTO = "profilePhoto";
    public static final String FIELD_LANGUAGE = "language";
    public static final String FIELD_TIME_ZONE = "timeZone";
    public static final String FIELD_LAST_PASSWORD_UPDATED_AT = "lastPasswordUpdatedAt";
    public static final String FIELD_LAST_LOGIN_AT = "lastLoginAt";
    public static final String FIELD_ENABLED = "enabled";
    public static final String FIELD_LOCKED = "locked";
    public static final String FIELD_IS_ADMIN = "isAdmin";
    public static final String FIELD_LOCKED_UNTIL_AT = "lockedUntilAt";
    public static final String FIELD_PASSWORD_ATTEMPT = "passwordAttempt";

    //
    // 业务方法(按public protected private顺序排列)
    // ------------------------------------------------------------------------------


    public User() {
    }

    public User(@NotBlank String loginName) {
        this.loginName = loginName;
    }

    //
    // 数据库字段
    // ------------------------------------------------------------------------------


    @ApiModelProperty("")
    @Id
    @GeneratedValue
    private Long id;
    @ApiModelProperty(value = "用户名", required = true)
    @NotBlank
    private String loginName;
    @ApiModelProperty(value = "电子邮箱地址", required = true)
    @NotBlank
    private String email;
    @ApiModelProperty(value = "加密的用户密码", required = true)
    @NotBlank
    private String password;
    @ApiModelProperty(value = "用户真实姓名")
    private String realName;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "用户头像地址")
    private String imageUrl;
    @ApiModelProperty(value = "用户二进制头像")
    private String profilePhoto;
    @ApiModelProperty(value = "语言", required = true)
    @NotBlank
    private String language;
    @ApiModelProperty(value = "时区", required = true)
    @NotBlank
    private String timeZone;
    @ApiModelProperty(value = "上一次密码更新时间", required = true)
    @NotNull
    private Date lastPasswordUpdatedAt;
    @ApiModelProperty(value = "上一次登陆时间")
    private Date lastLoginAt;
    @ApiModelProperty(value = "用户是否启用。1启用，0未启用", required = true)
    @NotNull
    private Integer enabled;
    @ApiModelProperty(value = "是否锁定账户", required = true)
    @NotNull
    private Integer locked;
    @ApiModelProperty(value = "是否为管理员用户。1表示是，0表示不是")
    private Integer isAdmin;
    @ApiModelProperty(value = "锁定账户截止时间")
    private Date lockedUntilAt;
    @ApiModelProperty(value = "密码输错累积次数")
    private Integer passwordAttempt;

    //
    // 非数据库字段
    // ------------------------------------------------------------------------------
    @Transient
    private List<Role> roles;
    //
    // getter/setter
    // ------------------------------------------------------------------------------

    /**
     * @return
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return 用户名
     */
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * @return 电子邮箱地址
     */
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return 加密的用户密码
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return 用户真实姓名
     */
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    /**
     * @return 手机号
     */
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return 用户头像地址
     */
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    /**
     * @return 用户二进制头像
     */
    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    /**
     * @return 语言
     */
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return 时区
     */
    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    /**
     * @return 上一次密码更新时间
     */
    public Date getLastPasswordUpdatedAt() {
        return lastPasswordUpdatedAt;
    }

    public void setLastPasswordUpdatedAt(Date lastPasswordUpdatedAt) {
        this.lastPasswordUpdatedAt = lastPasswordUpdatedAt;
    }

    /**
     * @return 上一次登陆时间
     */
    public Date getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(Date lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    /**
     * @return 用户是否启用。1启用，0未启用
     */
    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    /**
     * @return 是否锁定账户
     */
    public Integer getLocked() {
        return locked;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    /**
     * @return 是否为管理员用户。1表示是，0表示不是
     */
    public Integer getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * @return 锁定账户截止时间
     */
    public Date getLockedUntilAt() {
        return lockedUntilAt;
    }

    public void setLockedUntilAt(Date lockedUntilAt) {
        this.lockedUntilAt = lockedUntilAt;
    }

    /**
     * @return 密码输错累积次数
     */
    public Integer getPasswordAttempt() {
        return passwordAttempt;
    }

    public void setPasswordAttempt(Integer passwordAttempt) {
        this.passwordAttempt = passwordAttempt;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
