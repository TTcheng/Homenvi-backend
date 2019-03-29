package me.wcc.auth.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 接口对象
 *
 * @author chuncheng.wang@hand-china.com 19-3-10 下午2:10
 */
@Table(name = "backendApi")
public class BackendApi implements Serializable {

    private static final long serialVersionUID = -6951678670479871431L;

    @Id
    @GeneratedValue
    private Integer id;
    private String tag;
    private String path;
    private String method;
    private String remark;

    //getter and setter


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
