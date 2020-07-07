package com.web.jdbc.usermanagement.utils;
import java.util.HashMap;
import java.util.Map;

import com.web.jdbc.usermanagement.bean.UserAccount;
import com.web.jdbc.usermanagement.config.SecurityConfig;


public class DataDAO {
	 
    private static final Map<String, UserAccount> mapUsers = new HashMap<String, UserAccount>();
 
    static {
        initUsers();
    }
 
    private static void initUsers() {
 
        // User này có 1 vai trò là EMPLOYEE.
        UserAccount emp = new UserAccount("student", "123", UserAccount.GENDER_MALE, //
                SecurityConfig.ROLE_STUDENT);
 
        // User này có 2 vai trò MANAGER.
        UserAccount mng = new UserAccount("admin", "admin", UserAccount.GENDER_MALE, //
                 SecurityConfig.ROLE_ADMIN);
        
        UserAccount lec = new UserAccount("lecturer", "123", UserAccount.GENDER_FEMALE, //
                SecurityConfig.ROLE_LECTURER);
 
        mapUsers.put(emp.getUserName(), emp);
        mapUsers.put(mng.getUserName(), mng);
        mapUsers.put(lec.getUserName(), lec);
    }
 
    // Tìm kiếm người dùng theo userName và password.
    public static UserAccount findUser(String userName, String password) {
        UserAccount u = mapUsers.get(userName);
        if (u != null && u.getPassword().equals(password)) {
            return u;
        }
        return null;
    }
 
}