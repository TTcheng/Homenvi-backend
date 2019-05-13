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
 * 采集项等级
 *
 * @author ttchengwang@foxmail.com 2019-05-11 14:49:53
 */
@ApiModel("采集项等级")
@VersionAudit
@ModifyAudit
@Table(name = "collection_level")
public class CollectionLevel extends AuditDomain {

    public static final String FIELD_ID = "id";
    public static final String FIELD_COLLECTION_SPEC_ID = "collectionSpecId";
    public static final String FIELD_LEVEL_NAME = "levelName";
    public static final String FIELD_UPPER_BOUND = "upperBound";
    public static final String FIELD_LOWER_BOUND = "lowerBound";
    public static final String FIELD_COLLECTION_NAME = "collectionName";
    public static final String FIELD_COLLECTION_FIELD = "collectionField";
    public static final String FIELD_REMARK = "remark";

    public CollectionLevel() {
    }

    public CollectionLevel(@NotEmpty String collectionField) {
        this.collectionField = collectionField;
    }

    //
// 业务方法(按public protected private顺序排列)
// ------------------------------------------------------------------------------

//
// 数据库字段
// ------------------------------------------------------------------------------

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private Long collectionSpecId;
    @NotEmpty
    private String levelName;
    @NotEmpty
    private String collectionName;
    @NotEmpty
    private String collectionField;
    @NotNull
    private BigDecimal upperBound;
    @NotNull
    private BigDecimal lowerBound;
    private String remark;

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
     * @return 等级名
     */
    public String getLevelName() {
            return levelName;
    }

    public void setLevelName(String levelName) {
            this.levelName = levelName;
    }

    /**
     * @return 采集名称
     */
    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    /**
     * @return 采集字段
     */
    public String getCollectionField() {
        return collectionField;
    }

    public void setCollectionField(String collectionField) {
        this.collectionField = collectionField;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

