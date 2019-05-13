package me.wcc.homenvi.service;

import me.wcc.homenvi.entity.CollectionLevel;

import io.choerodon.mybatis.service.BaseService;

/**
 * 采集项等级服务接口
 *
 * @author ttchengwang@foxmail.com
 * @date 2019-05-11 14:49:53
 */
public interface CollectionLevelService extends BaseService<CollectionLevel> {

    CollectionLevel satisfiedLevel(String field, Double value);
}

