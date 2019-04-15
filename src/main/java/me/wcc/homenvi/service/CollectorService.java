package me.wcc.homenvi.service;

import me.wcc.homenvi.entity.Collector;

import io.choerodon.mybatis.service.BaseService;

/**
 * 终端采集节点服务接口
 *
 * @author ttchengwang@foxmail.com
 * @date 2019-04-12 19:17:00
 */
public interface CollectorService extends BaseService<Collector> {
    /***
     * 采集节点每次启动时刷新节点信息
     *
     * @param collector 采集节点
     */
    void refresh(Collector collector);
}

