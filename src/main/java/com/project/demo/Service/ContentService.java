package com.project.demo.Service;

import org.springframework.stereotype.Service;

@Service
public class ContentService {
    public String getPublicContent() {
        return "Public content Accessible by everyone";
    }

    public String getUserContent() {
        return "Welcome User . This is user-level content";
    }

    public String getAdminContent() {
        return "Welcome Admin . This is admin-level content";
    }


}
