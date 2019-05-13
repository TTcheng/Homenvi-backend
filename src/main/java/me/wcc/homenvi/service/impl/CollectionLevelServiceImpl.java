package me.wcc.homenvi.service.impl;

import org.springframework.stereotype.Service;
import me.wcc.homenvi.entity.CollectionLevel;
import me.wcc.homenvi.service.CollectionLevelService;

import java.math.BigDecimal;
import java.util.List;

import io.choerodon.mybatis.service.BaseServiceImpl;

/**
 * 采集项等级服务实现类
 *
 * @author ttchengwang@foxmail.com
 * @date 2019-05-11 14:49:53
 */
@Service
public class CollectionLevelServiceImpl extends BaseServiceImpl<CollectionLevel> implements CollectionLevelService {


    @Override
    public CollectionLevel satisfiedLevel(String field, Double value) {
        List<CollectionLevel> levels = select(new CollectionLevel(field));
        for (CollectionLevel level : levels) {
            if (null == level.getUpperBound() || null == level.getLowerBound()) {
                continue;
            }
            if (level.getLowerBound().compareTo(BigDecimal.valueOf(value)) <= 0
                    && level.getUpperBound().compareTo(BigDecimal.valueOf(value)) >= 0) {
                return level;
            }
        }
        return null;
    }
}
