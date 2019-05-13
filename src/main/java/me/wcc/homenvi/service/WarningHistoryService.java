package me.wcc.homenvi.service;

import me.wcc.homenvi.entity.CollectionWarning;
import me.wcc.homenvi.entity.WarningHistory;

import io.choerodon.mybatis.service.BaseService;

/**
 * 采集警告服务接口
 *
 * @author ttchengwang@foxmail.com
 * @date 2019-05-12 10:39:52
 */
public interface WarningHistoryService extends BaseService<WarningHistory> {
    Integer selectCountFromTime(WarningHistory warningHistory);

    void newHistory(CollectionWarning collectionWarning, String content);
}

