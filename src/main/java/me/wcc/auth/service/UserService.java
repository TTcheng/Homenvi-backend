package me.wcc.auth.service;

import me.wcc.auth.domain.entity.User;

/**
 * @author chuncheng.wang@hand-china.com 19-3-29 下午3:59
 */
public interface UserService{
    /**
     *
     *
     * @return 当前用户
     */
    User current(String tokenAndType);
}
