package me.wcc.homenvi.mapper;

import me.wcc.homenvi.entity.Collector;
import io.choerodon.mybatis.common.BaseMapper;

/**
 * 终端采集节点Mapper
 *
 * @author ttchengwang@foxmail.com 2019-04-12 19:17:00
 */
public interface CollectorMapper extends BaseMapper<Collector> {

    Collector selectByIdentifier(String identifier);
}

