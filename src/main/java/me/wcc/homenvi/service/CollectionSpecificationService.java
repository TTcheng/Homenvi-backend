package me.wcc.homenvi.service;

import me.wcc.homenvi.entity.CollectionSpecification;

import java.util.List;
import java.util.Map;

import io.choerodon.mybatis.service.BaseService;

/**
 * 采集项参数服务接口
 *
 * @author ttchengwang@foxmail.com
 * @date 2019-05-11 14:49:53
 */
public interface CollectionSpecificationService extends BaseService<CollectionSpecification> {

    Map<String, CollectionSpecification> selectMapByFields(List<String> fields);
}

