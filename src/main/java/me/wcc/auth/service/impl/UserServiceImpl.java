package me.wcc.auth.service.impl;

import me.wcc.auth.domain.entity.User;
import me.wcc.auth.service.UserService;
import org.springframework.stereotype.Service;

/**
 *
 * @author chuncheng.wang@hand-china.com 19-3-29 下午4:02
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public User current(String tokenAndType) {
        return null;
    }
}
