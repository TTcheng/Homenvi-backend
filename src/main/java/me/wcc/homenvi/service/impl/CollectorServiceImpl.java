package me.wcc.homenvi.service.impl;

import me.wcc.homenvi.exception.UnauthorizedCollectorException;
import me.wcc.homenvi.mapper.CollectorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import me.wcc.homenvi.entity.Collector;
import me.wcc.homenvi.service.CollectorService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import io.choerodon.mybatis.service.BaseServiceImpl;

/**
 * 终端采集节点服务实现类
 *
 * @author ttchengwang@foxmail.com
 * @date 2019-04-12 19:17:00
 */
@Service
public class CollectorServiceImpl extends BaseServiceImpl<Collector> implements CollectorService {

    @Autowired
    private CollectorMapper collectorMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void refresh(Collector collector) {
        Collector select = collectorMapper.selectByIdentifier(collector.getIdentifier());
        if (!select.getPassword().equals(collector.getPassword())) {
            throw new UnauthorizedCollectorException();
        }
        collector.setId(select.getId());
        collector.setLastOnlineTime(new Date());
        super.updateOptional(collector, Collector.FIELD_LAST_ONLINE_TIME, Collector.FIELD_IP_ADDRESS,
                Collector.FIELD_GATEWAY_ADDRESS, Collector.FIELD_DNS_ADDRESS, Collector.FIELD_MAC_ADDRESS,
                Collector.FIELD_SUBNET_MASK_ADDRESS);
    }
}
