package me.wcc.auth.service;

import me.wcc.auth.entity.User;
import me.wcc.base.domain.Page;

import io.choerodon.mybatis.pagehelper.domain.PageRequest;
import io.choerodon.mybatis.service.BaseService;

/**
 * 用户服务接口
 *
 * @author chuncheng.wang@hand-china.com
 * @date 2019-04-08 18:35:24
 */
public interface UserService extends BaseService<User> {
    /**
     * 分页查询非敏感用户信息，
     *
     * @param pageRequest 分页排序条件
     * @param condition 过滤条件
     * @return 分页的用户
     */
    Page<User> pageInsensitiveUsers(PageRequest pageRequest, User condition);

    /**
     * 查询指定id的非敏感用户信息，
     *
     * @param id id
     * @return 指定用户
     */
    User selectInsensitiveUser(Long id);
}

