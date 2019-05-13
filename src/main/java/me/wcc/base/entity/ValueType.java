package me.wcc.base.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import io.choerodon.mybatis.domain.AuditDomain;
import io.choerodon.mybatis.annotation.ModifyAudit;
import io.choerodon.mybatis.annotation.VersionAudit;
import io.swagger.annotations.ApiModel;

/**
 * 取值类型
 *
 * @author ttchengwang@foxmail.com 2019-05-11 15:38:17
 */
@ApiModel("取值类型")
@VersionAudit
@ModifyAudit
@Table(name = "base_value_type")
public class ValueType extends AuditDomain {

    public static final String FIELD_ID = "id";
    public static final String FIELD_TYPE = "type";
    public static final String FIELD_NAME = "name";

//
// 业务方法(按public protected private顺序排列)
// ------------------------------------------------------------------------------

//
// 数据库字段
// ------------------------------------------------------------------------------

    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty
    private String type;
    @NotEmpty
    private String name;

//
// 非数据库字段
// ------------------------------------------------------------------------------

//
// getter/setter
// ------------------------------------------------------------------------------


    /**
     * @return 主键ID
     */
    public Long getId() {
            return id;
    }

    public void setId(Long id) {
            this.id = id;
    }
    /**
     * @return 类型
     */
    public String getType() {
            return type;
    }

    public void setType(String type) {
            this.type = type;
    }
    /**
     * @return 名称
     */
    public String getName() {
            return name;
    }

    public void setName(String name) {
            this.name = name;
    }
}
