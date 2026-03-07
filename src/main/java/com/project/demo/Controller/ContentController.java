package com.project.demo.Controller;

import com.project.demo.Service.ContentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
@Tag(name="Content API", description = "APIs for accessing role-based content")
@RestController
@RequiredArgsConstructor
public class ContentController {

    private final ContentService contentService;

    @Operation(summary = "Public content", description = "Accessible to anyone without authentication")
    @GetMapping("/public")
    public String getPublicContent(){
        return contentService.getPublicContent();
    }

    @Operation(summary = "User_content", description = "Accessible only to authenticated users")
    @GetMapping("/user")
    public String getUserContent(){
        return contentService.getUserContent();
    }

    @Operation(summary = "Admin_content", description = "Accessible only to admin users ")
    @GetMapping("/admin")
    public String getAdminContent(){
        return contentService.getAdminContent();
    }
}
