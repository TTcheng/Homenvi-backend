package me.wcc.base.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 菜单
 *
 * @author chuncheng.wang@hand-china.com 19-3-10 下午2:10
 */
@Table(name = "munu")
public class Menu implements Serializable {
    private static final long serialVersionUID = 1114479575720904849L;
    @Id
    @GeneratedValue
    private Long id;
    private Integer parentId;
    private String link;

    @Transient
    private Set<BackendApi> backendApis = new HashSet<>();

    // getter and setter


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Set<BackendApi> getBackendApis() {
        return backendApis;
    }

    public void setBackendApis(Set<BackendApi> backendApis) {
        this.backendApis = backendApis;
    }
}
