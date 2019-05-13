package me.wcc.homenvi.service.impl;

import me.wcc.homenvi.entity.CollectionWarning;
import me.wcc.homenvi.mapper.WarningHistoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import me.wcc.homenvi.entity.WarningHistory;
import me.wcc.homenvi.service.WarningHistoryService;

import io.choerodon.mybatis.service.BaseServiceImpl;

/**
 * 采集警告服务实现类
 *
 * @author ttchengwang@foxmail.com
 * @date 2019-05-12 10:39:52
 */
@Service
public class WarningHistoryServiceImpl extends BaseServiceImpl<WarningHistory> implements WarningHistoryService {

    private WarningHistoryMapper warningHistoryMapper;

    @Autowired
    public void setWarningHistoryMapper(WarningHistoryMapper warningHistoryMapper) {
        this.warningHistoryMapper = warningHistoryMapper;
    }

    @Override
    public Integer selectCountFromTime(WarningHistory warningHistory) {
        return warningHistoryMapper.selectCountFromTime(warningHistory);
    }

    @Override
    public void newHistory(CollectionWarning collectionWarning, String content) {
        insert(new WarningHistory(
                collectionWarning.getId(),
                collectionWarning.getCollectionField(),
                collectionWarning.getCollectionName(),
                collectionWarning.getReason(), content));
    }
}
