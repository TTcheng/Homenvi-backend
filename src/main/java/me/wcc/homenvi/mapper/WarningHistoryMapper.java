package me.wcc.homenvi.mapper;

import me.wcc.homenvi.entity.WarningHistory;

import io.choerodon.mybatis.common.BaseMapper;

/**
 * 采集警告Mapper
 *
 * @author ttchengwang@foxmail.com 2019-05-12 10:39:52
 */
public interface WarningHistoryMapper extends BaseMapper<WarningHistory> {
    Integer selectCountFromTime(WarningHistory warningHistory);
}

