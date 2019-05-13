package me.wcc.homenvi.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import io.choerodon.mybatis.domain.AuditDomain;

import java.math.BigDecimal;

import io.choerodon.mybatis.annotation.ModifyAudit;
import io.choerodon.mybatis.annotation.VersionAudit;

import io.swagger.annotations.ApiModel;

/**
 * 采集项参数
 *
 * @author ttchengwang@foxmail.com 2019-05-11 14:49:53
 */
@ApiModel("采集项参数")
@VersionAudit
@ModifyAudit
@Table(name = "collection_specification")
public class CollectionSpecification extends AuditDomain {

    public static final String FIELD_ID = "id";
    public static final String FIELD_FIELD = "field";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_UNIT = "unit";
    public static final String FIELD_REMARK = "remark";
    public static final String FIELD_MIN = "min";
    public static final String FIELD_MAX = "max";

    public CollectionSpecification() {
    }

    public CollectionSpecification(@NotEmpty String field) {
        this.field = field;
    }

    //
// 业务方法(按public protected private顺序排列)
// ------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "CollectionSpecification{" + "id=" + id +
                ", field='" + field + '\'' +
                ", name='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", remark='" + remark + '\'' +
                ", min=" + min +
                ", max=" + max +
                '}';
    }


//
// 数据库字段
// ------------------------------------------------------------------------------

    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty
    private String field;
    @NotEmpty
    private String name;
    @NotEmpty
    private String unit;
    private String remark;
    private BigDecimal min;
    private BigDecimal max;

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
     * @return 字段
     */
    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
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

    /**
     * @return 单位
     */
    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @return 备注
     */
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return 最小值
     */
    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    /**
     * @return 最大值
     */
    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }
}
