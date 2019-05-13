package me.wcc.homenvi.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.choerodon.mybatis.domain.AuditDomain;
import io.choerodon.mybatis.annotation.ModifyAudit;
import io.choerodon.mybatis.annotation.VersionAudit;

import io.swagger.annotations.ApiModel;

/**
 * 采集警告历史
 *
 * @author ttchengwang@foxmail.com 2019-05-12 10:39:52
 */
@ApiModel("采集警告历史")
@VersionAudit
@ModifyAudit
@Table(name = "warning_history")
public class WarningHistory extends AuditDomain {

    public static final String FIELD_ID = "id";
    public static final String FIELD_COLLECTION_WARN_ID = "collectionWarnId";
    public static final String FIELD_COLLECTION_FIELD = "collectionField";
    public static final String FIELD_COLLECTION_NAME = "collectionName";
    public static final String FIELD_WARN_REASON = "warnReason";
    public static final String FIELD_WARN_CONTENT = "warnContent";

    public WarningHistory() {
    }

    public WarningHistory(@NotNull Long collectionWarnId) {
        this.collectionWarnId = collectionWarnId;
    }

    public WarningHistory(@NotNull Long collectionWarnId, @NotEmpty String collectionField,
                          @NotEmpty String collectionName, @NotEmpty String warnReason,
                          @NotEmpty String warnContent) {
        this.collectionWarnId = collectionWarnId;
        this.collectionField = collectionField;
        this.collectionName = collectionName;
        this.warnReason = warnReason;
        this.warnContent = warnContent;
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
    private Long collectionWarnId;
    @NotEmpty
    private String collectionField;
    @NotEmpty
    private String collectionName;
    @NotEmpty
    private String warnReason;
    @NotEmpty
    private String warnContent;

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
     * @return 采集项警告id
     */
    public Long getCollectionWarnId() {
        return collectionWarnId;
    }

    public void setCollectionWarnId(Long collectionWarnId) {
        this.collectionWarnId = collectionWarnId;
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
     * @return 采集名称
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
    public String getWarnReason() {
        return warnReason;
    }

    public void setWarnReason(String warnReason) {
        this.warnReason = warnReason;
    }

    /**
     * @return 内容
     */
    public String getWarnContent() {
        return warnContent;
    }

    public void setWarnContent(String warnContent) {
        this.warnContent = warnContent;
    }
}
