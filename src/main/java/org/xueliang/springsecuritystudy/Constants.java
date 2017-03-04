package org.xueliang.springsecuritystudy;

import java.util.ArrayList;
import java.util.List;

import org.xueliang.springsecuritystudy.model.Authority;
import org.xueliang.springsecuritystudy.model.User;

public class Constants {

    public static final List<User> userList = new ArrayList<User>(){
        
        private static final long serialVersionUID = 1L;
        {
            User user = new User();
            user.setUsername("myuser");
            user.setPassword("myuserpass");
            user.setAuthorities(new ArrayList<Authority>(){
                private static final long serialVersionUID = 1L;
                {
                    add(new Authority("USER"));
                }
            });
            
            User admin = new User();
            admin.setUsername("myadmin");
            admin.setPassword("myadminpass");
            admin.setAuthorities(new ArrayList<Authority>(){
                private static final long serialVersionUID = 1L;
                {
                    add(new Authority("ADMIN"));
                }
            });
            
            User dba = new User();
            dba.setUsername("mydba");
            dba.setPassword("mydba");
            dba.setAuthorities(new ArrayList<Authority>(){
                private static final long serialVersionUID = 1L;
                {
                    add(new Authority("DBA"));
                }
            });
            
            add(user);
            add(admin);
            add(dba);
        }
    };
}
