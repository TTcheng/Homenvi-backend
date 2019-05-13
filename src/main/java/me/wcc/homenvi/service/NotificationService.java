package me.wcc.homenvi.service;

import me.wcc.homenvi.entity.Notification;

import io.choerodon.mybatis.service.BaseService;

/**
 * 服务接口
 *
 * @author chuncheng.wang@hand-china.com
 * @date 2019-04-08 17:38:52
 */
public interface NotificationService extends BaseService<Notification> {
    void newNotification(Long userid, String title, String content);
}

