package me.wcc.base.app.service;

import me.wcc.base.domain.CustomUserDetails;
import me.wcc.base.domain.User;
import me.wcc.base.infra.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chuncheng.wang@hand-china.com 19-3-10 下午9:58
 */
@Service
public class CustomUserDetailsService implements UserDetailsService{


    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.selectOne(new User(s));
        if (user == null) {
            throw new UsernameNotFoundException("用户名不对");
        }
        return new CustomUserDetails(user);
    }
}
