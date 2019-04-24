package me.wcc.homenvi.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.choerodon.mybatis.domain.AuditDomain;
import io.choerodon.mybatis.annotation.ModifyAudit;
import io.choerodon.mybatis.annotation.VersionAudit;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 通知
 *
 * @author chuncheng.wang@hand-china.com 2019-04-08 17:47:59
 */
@ApiModel("通知")
@VersionAudit
@ModifyAudit
@Table(name = "notification")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Notification extends AuditDomain {

    public static final String FIELD_ID = "id";
    public static final String FIELD_USERID = "userid";
    public static final String FIELD_UNREAD = "unread";
    public static final String FIELD_TITLE = "title";
    public static final String FIELD_CONTENT = "content";

//
// 业务方法(按public protected private顺序排列)
// ------------------------------------------------------------------------------

    public Notification() {
    }

    public Notification(@NotNull Long userid) {
        this.userid = userid;
    }

    public Notification(@NotNull Long userid, Integer unread) {
        this.userid = userid;
        this.unread = unread;
    }
    //
// 数据库字段
// ------------------------------------------------------------------------------

    @Id
    @GeneratedValue
    @ApiModelProperty("主键ID")
    private Long id;
    @ApiModelProperty("所属用户ID")
    @NotNull
    private Long userid;
    @ApiModelProperty("是否未读")
    private Integer unread;
    @ApiModelProperty("标题")
    @NotEmpty
    private String title;
    @ApiModelProperty("内容")
    private String content;

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
     * @return 所属用户ID
     */
    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    /**
     * @return 是否未读
     */
    public Integer getUnread() {
        return unread;
    }

    public void setUnread(Integer unread) {
        this.unread = unread;
    }

    /**
     * @return 标题
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return 内容
     */
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
