package io.choerodon.mybatis.helper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

import io.choerodon.mybatis.domain.Audit;

/**
 * @author chuncheng.wang@hand-china.com 2019-01-29 16:29:28
 */
public class AuditHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuditHelper.class);
    private static ThreadLocal<Audit> audits = new ThreadLocal<>();

    private AuditHelper() {
    }

    public static void setAudit(Long userid) {
        Audit audit = new Audit();
        audit.setNow(new Date());
        audit.setUser(userid);
        audits.set(audit);
    }

    public static Audit audit() {
        Audit audit = audits.get();
        if (audit == null) {
            LOGGER.warn("No audit info in memory!, use default admin");
            audit = new Audit();
            audit.setUser(0L);
            audits.set(audit);
        }
        audit.setNow(new Date());
        return audit;
    }
}
