package me.wcc.auth.mapper;


import me.wcc.auth.entity.User;

import java.util.List;

import io.choerodon.mybatis.common.BaseMapper;

/**
 * Mapper
 *
 * @author chuncheng.wang@hand-china.com 2019-04-08 18:35:24
 */
public interface UserMapper extends BaseMapper<User> {

    List selectInsensitiveUsers(User condition);

    User selectInsensitiveUser(Long id);
}
