package me.wcc.homenvi.service.impl;

import org.springframework.stereotype.Service;
import me.wcc.homenvi.entity.CollectionSpecification;
import me.wcc.homenvi.service.CollectionSpecificationService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.choerodon.mybatis.service.BaseServiceImpl;

/**
 * 采集项参数服务实现类
 *
 * @author ttchengwang@foxmail.com
 * @date 2019-05-11 14:49:53
 */
@Service
public class CollectionSpecificationServiceImpl extends BaseServiceImpl<CollectionSpecification> implements CollectionSpecificationService {


    @Override
    public Map<String, CollectionSpecification> selectMapByFields(List<String> fields) {
        Map<String, CollectionSpecification> fieldName = new HashMap<>(fields.size());
        fields.forEach(field -> {
            CollectionSpecification one = selectOne(new CollectionSpecification(field));
            if (one != null) {
                fieldName.put(field, one);
            }
        });
        return fieldName;
    }
}
