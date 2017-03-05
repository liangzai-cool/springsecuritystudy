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
            user.setPassword("$2a$10$r6V8qEOWr.4TZxdMCNRweOfeD3uxhEMd3Ey7obEhJsh7F53S3c8l6");   // myuserpass
            user.setAuthorities(new ArrayList<Authority>(){
                private static final long serialVersionUID = 1L;
                {
                    add(new Authority("USER"));
                }
            });
            
            User admin = new User();
            admin.setUsername("myadmin");
            admin.setPassword("$2a$10$UoD6b1MX7cTKwPjia34n4eJ1Q7eD4c.rBFbM826tPdqGWs/bh5Xfm");  // myadminpass
            admin.setAuthorities(new ArrayList<Authority>(){
                private static final long serialVersionUID = 1L;
                {
                    add(new Authority("ADMIN"));
                }
            });
            
            User dba = new User();
            dba.setUsername("mydba");
            dba.setPassword("$2a$10$31Vufh14KaO40xqRKlJvkeBIboOtPNjqJdKXJuEuxAjMwCqhECL8a");    // mydbapass
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
