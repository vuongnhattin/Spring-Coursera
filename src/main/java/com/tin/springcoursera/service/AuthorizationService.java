package com.tin.springcoursera.service;

import com.tin.springcoursera.entity.Material;
import com.tin.springcoursera.entity.Module;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("auth")
@RequiredArgsConstructor
@Getter
@Slf4j
public class AuthorizationService {
    private final MemberService memberService;
    private final ModuleService moduleService;
    private final MaterialService materialService;

    private String getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    public boolean isMemberOfCourse(int courseId) {
        return memberService.isMember(getUserId(), courseId);
    }

    public boolean isAdminOfCourse(int courseId) {
        return memberService.isAdmin(getUserId(), courseId);
    }

    public boolean canViewModule(int moduleId) {
        Module module = moduleService.getModuleById(moduleId);
        int courseId = module.getCourseId();
        return isMemberOfCourse(courseId);
    }

    public boolean canEditModule(int moduleId) {
        Module module = moduleService.getModuleById(moduleId);
        int courseId = module.getCourseId();
        return isAdminOfCourse(courseId);
    }

    public boolean canViewMaterial(int materialId) {
        Material material = materialService.getMaterialById(materialId);
        Module module = moduleService.getModuleById(material.getModuleId());
        int courseId = module.getCourseId();
        return isMemberOfCourse(courseId);
    }

    public boolean canEditMaterial(int materialId) {
        Material material = materialService.getMaterialById(materialId);
        Module module = moduleService.getModuleById(material.getModuleId());
        int courseId = module.getCourseId();
        return isAdminOfCourse(courseId);
    }
}
