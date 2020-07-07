package com.web.jdbc.usermanagement.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
 
public class SecurityConfig {
 
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_STUDENT = "STUDENT";
    public static final String ROLE_LECTURER = "LECTURER";
 
    // String: Role
    // List<String>: urlPatterns.
    private static final Map<String, List<String>> mapConfig = new HashMap<String, List<String>>();
 
    static {
        init();
    }
 
    private static void init() {
 
        // Cấu hình cho vai trò "EMPLOYEE".
        List<String> urlPatterns1 = new ArrayList<String>();
 
        urlPatterns1.add("/userInfo");
        urlPatterns1.add("/employeeTask");

 
        mapConfig.put(ROLE_STUDENT, urlPatterns1);
 
        // Cấu hình cho vai trò "MANAGER".
        List<String> urlPatterns2 = new ArrayList<String>();
 
        urlPatterns2.add("/userInfo");
        urlPatterns2.add("/managerTask");
        urlPatterns2.add("/ClassControllerServlet");
        urlPatterns2.add("/SubjectControllerServlet");
        urlPatterns2.add("/FaculityControllerServlet");
        urlPatterns2.add("/ScoreControllerServlet");
        urlPatterns2.add("/ScoreTableControllerServlet");
        urlPatterns2.add("/ScoreTableControllerServletReport");
 
        mapConfig.put(ROLE_ADMIN, urlPatterns2);
        
        
        List<String> urlPatterns3 = new ArrayList<String>();
        
        urlPatterns3.add("/userInfo");
        urlPatterns3.add("/xemlichday");
        mapConfig.put(ROLE_LECTURER, urlPatterns3);
        
        
    }
 
    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }
 
    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }
 
}