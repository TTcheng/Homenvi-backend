package me.wcc.homenvi.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.choerodon.mybatis.domain.AuditDomain;

import java.math.BigDecimal;

import io.choerodon.mybatis.annotation.ModifyAudit;
import io.choerodon.mybatis.annotation.VersionAudit;

import io.swagger.annotations.ApiModel;

/**
 * 采集警告
 *
 * @author ttchengwang@foxmail.com 2019-05-11 15:20:57
 */
@ApiModel("采集警告")
@VersionAudit
@ModifyAudit
@Table(name = "collection_warning")
public class CollectionWarning extends AuditDomain {

    public static final String FIELD_ID = "id";
    public static final String FIELD_COLLECTION_SPEC_ID = "collectionSpecId";
    public static final String FIELD_COLLECTION_FIELD = "collectionField";
    public static final String FIELD_COLLECTION_NAME = "collectionName";
    public static final String FIELD_REASON = "reason";
    public static final String FIELD_ADVICE = "advice";
    public static final String FIELD_VALUE_TYPE = "valueType";
    public static final String FIELD_UPPER_BOUND = "upperBound";
    public static final String FIELD_LOWER_BOUND = "lowerBound";
    public static final String FIELD_REMARK = "remark";

    public CollectionWarning() {
    }

    public CollectionWarning(@NotEmpty String collectionField) {
        this.collectionField = collectionField;
    }

    //
// 业务方法(按public protected private顺序排列)
// ------------------------------------------------------------------------------
    public Boolean satisfy(Double value) {
        BigDecimal target = BigDecimal.valueOf(value);
        return lowerBound.compareTo(target) < 0 && upperBound.compareTo(target) > 0;
    }

//
// 数据库字段
// ------------------------------------------------------------------------------

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private Long collectionSpecId;
    @NotEmpty
    private String collectionField;
    @NotEmpty
    private String collectionName;
    @NotEmpty
    private String reason;
    @NotEmpty
    private String valueType;
    @NotNull
    private BigDecimal upperBound;
    @NotNull
    private BigDecimal lowerBound;
    @NotEmpty
    private String remark;
    @NotEmpty
    private String advice;

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
     * @return 采集项id
     */
    public Long getCollectionSpecId() {
        return collectionSpecId;
    }

    public void setCollectionSpecId(Long collectionSpecId) {
        this.collectionSpecId = collectionSpecId;
    }

    /**
     * @return 字段
     */
    public String getCollectionField() {
        return collectionField;
    }

    public void setCollectionField(String collectionField) {
        this.collectionField = collectionField;
    }

    /**
     * @return 名称
     */
    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    /**
     * @return 警告原因
     */
    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * @return 取值类型
     */
    public String getValueType() {
        return valueType;
    }

    public void setValueType(String valueType) {
        this.valueType = valueType;
    }

    /**
     * @return 上届值
     */
    public BigDecimal getUpperBound() {
        return upperBound;
    }

    public void setUpperBound(BigDecimal upperBound) {
        this.upperBound = upperBound;
    }

    /**
     * @return 下界值
     */
    public BigDecimal getLowerBound() {
        return lowerBound;
    }

    public void setLowerBound(BigDecimal lowerBound) {
        this.lowerBound = lowerBound;
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

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }
}
