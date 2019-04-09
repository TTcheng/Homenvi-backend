package me.wcc.auth.entity;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户角色
 *
 * @author chuncheng.wang@hand-china.com 19-3-10 下午1:34
 */
@Table(name = "role")
public class Role implements Serializable {
    private static final long serialVersionUID = -228463851192502799L;

    public Role() {
    }

    public Role(@NotNull String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty("父角色ID")
    private Long parentId;

    @NotNull
    @ApiModelProperty("角色名称")
    private String name;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty(hidden = true)
    @Transient
    private Set<Menu> menus = new HashSet<>();
    @ApiModelProperty(hidden = true)
    @Transient
    private Set<BackendApi> backendApis = new HashSet<>();

    // getter and setter

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    public Set<BackendApi> getBackendApis() {
        return backendApis;
    }

    public void setBackendApis(Set<BackendApi> backendApis) {
        this.backendApis = backendApis;
    }
}
