package me.wcc.auth.mapper;


import me.wcc.auth.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;

import io.choerodon.mybatis.common.BaseMapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
