package me.wcc.auth.service.impl;

import me.wcc.auth.entity.User;
import me.wcc.auth.mapper.UserMapper;
import me.wcc.base.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import me.wcc.auth.service.UserService;

import io.choerodon.mybatis.pagehelper.PageHelper;
import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.mybatis.service.BaseServiceImpl;

/**
 * 用户服务实现类
 *
 * @author chuncheng.wang@hand-china.com
 * @date 2019-04-08 18:35:24
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public Page<User> pageInsensitiveUsers(PageRequest pageRequest, User condition) {
        return PageHelper.doPageAndSort(pageRequest, () -> userMapper.selectInsensitiveUsers(condition));
    }

    @Override
    public User selectInsensitiveUser(Long id) {
        return userMapper.selectInsensitiveUser(id);
    }
}
