package com.tin.springcoursera.controller;

import com.tin.springcoursera.dto.request.CreateModuleRequest;
import com.tin.springcoursera.dto.request.UpdateModuleRequest;
import com.tin.springcoursera.dto.response.ListResponse;
import com.tin.springcoursera.dto.response.ModuleMaterialResponse;
import com.tin.springcoursera.entity.Module;
import com.tin.springcoursera.service.ModuleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api")
public class ModuleController {
    private final ModuleService moduleService;

    @GetMapping("courses/{id}/modules")
    @PreAuthorize("@auth.isMemberOfCourse(#id)")
    public ListResponse<Module> getModules(@PathVariable int id) {
        return moduleService.getModulesByCourseId(id);
    }

    @PostMapping("modules")
    @PreAuthorize("@auth.isAdminOfCourse(#request.courseId)")
    @ResponseStatus(HttpStatus.CREATED)
    public Module createModule(@Valid @RequestBody CreateModuleRequest request) {
        return moduleService.createModule(request);
    }

    @PutMapping("modules/{moduleId}")
    @PreAuthorize("@auth.canEditModule(#moduleId)")
    public Module updateModule(@PathVariable int moduleId, @Valid @RequestBody UpdateModuleRequest request) {
        return moduleService.updateModule(moduleId, request);
    }

    @DeleteMapping("modules/{moduleId}")
    @PreAuthorize("@auth.canEditModule(#moduleId)")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteModule(@PathVariable int moduleId) {
        moduleService.deleteModule(moduleId);
    }

    @GetMapping("courses/{courseId}/modules-detail")
    @PreAuthorize("@auth.isMemberOfCourse(#courseId)")
    public ListResponse<ModuleMaterialResponse> getModulesDetail(@PathVariable int courseId) {
        return moduleService.getModulesDetailByCourseId(courseId);
    }

    @GetMapping("modules/{moduleId}")
    @PreAuthorize("@auth.canViewModule(#moduleId)")
    public Module getModule(@PathVariable int moduleId) {
        return moduleService.getModuleById(moduleId);
    }
}
