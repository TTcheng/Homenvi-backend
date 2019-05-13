package me.wcc.homenvi.service.impl;

import me.wcc.base.infra.constant.BaseConstants;
import org.springframework.stereotype.Service;
import me.wcc.homenvi.entity.Notification;
import me.wcc.homenvi.service.NotificationService;

import io.choerodon.mybatis.service.BaseServiceImpl;

/**
 * 服务实现类
 *
 * @author chuncheng.wang@hand-china.com
 * @date 2019-04-08 17:38:52
 */
@Service
public class NotificationServiceImpl extends BaseServiceImpl<Notification> implements NotificationService {


    @Override
    public void newNotification(Long userid, String title, String content) {
        Notification notification = new Notification(userid, title, content);
        notification.setUnread(BaseConstants.FLAG_YES);
        insert(notification);
    }
}
