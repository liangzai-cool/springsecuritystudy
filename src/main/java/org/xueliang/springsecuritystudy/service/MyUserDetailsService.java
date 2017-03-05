package org.xueliang.springsecuritystudy.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.xueliang.springsecuritystudy.Constants;
import org.xueliang.springsecuritystudy.model.User;

@Service
public class MyUserDetailsService implements UserDetailsService {
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> userList = Constants.userList;
        for (int i = 0, len = userList.size(); i < len; i++) {
            User user = userList.get(i);
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        throw new UsernameNotFoundException("用户不存在！");
    }
}
