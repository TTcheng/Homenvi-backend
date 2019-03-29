package me.wcc.auth.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 菜单
 *
 * @author chuncheng.wang@hand-china.com 19-3-10 下午2:10
 */
@Table(name = "menu")
public class Menu implements Serializable {
    private static final long serialVersionUID = 1114479575720904849L;
    @Id
    @GeneratedValue
    private Long id;
    @ApiModelProperty("父菜单ID")
    private Long parentId;
    @ApiModelProperty("菜单编码")
    private String menuCode;
    @ApiModelProperty("备注")
    private String remark;

    @Transient
    private Set<BackendApi> backendApis = new HashSet<>();

    // getter and setter


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Set<BackendApi> getBackendApis() {
        return backendApis;
    }

    public void setBackendApis(Set<BackendApi> backendApis) {
        this.backendApis = backendApis;
    }
}
