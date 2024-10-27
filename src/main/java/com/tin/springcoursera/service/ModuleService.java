package com.tin.springcoursera.service;

import com.tin.springcoursera.dto.request.CreateModuleRequest;
import com.tin.springcoursera.dto.request.UpdateModuleRequest;
import com.tin.springcoursera.dto.response.ListResponse;
import com.tin.springcoursera.dto.response.ModuleMaterialResponse;
import com.tin.springcoursera.entity.Material;
import com.tin.springcoursera.entity.Module;
import com.tin.springcoursera.exception.AppException;
import com.tin.springcoursera.exception.DuplicateResourceException;
import com.tin.springcoursera.repository.MaterialRepository;
import com.tin.springcoursera.repository.ModuleRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ModuleService {
    private final ModuleRepository moduleRepository;
    private final MaterialRepository materialRepository;

    public static final String MODULE_DUPLICATED = "Tên học phần này đã tồn tại";
    private final String MODULE_NOT_FOUND = "Không tìm thấy học phần";

    public ListResponse<Module> getModulesByCourseId(int courseId) {
        return new ListResponse<>(moduleRepository.getModulesByCourseId(courseId));
    }

    @Transactional
    public Module createModule(CreateModuleRequest request) {
        if (this.isDuplicateModule(request.getName(), request.getCourseId())) {
            throw new DuplicateResourceException(MODULE_DUPLICATED);
        }

        Module module = Module.builder()
                .name(request.getName())
                .courseId(request.getCourseId())
                .build();
        return moduleRepository.save(module);
    }

    @Transactional
    public Module updateModule(int moduleId, UpdateModuleRequest request) {
        Module module = moduleRepository.findById(moduleId).orElseThrow(() -> new AppException(404, MODULE_NOT_FOUND));

        if (this.isDuplicateModuleUpdate(request.getName(), moduleId)) {
            throw new DuplicateResourceException(MODULE_DUPLICATED);
        }

        module.setName(request.getName());

        return moduleRepository.save(module);
    }

    @Transactional
    public void deleteModule(int moduleId) {
        Module module = moduleRepository.findById(moduleId).orElseThrow(() -> new AppException(404, MODULE_NOT_FOUND));
        moduleRepository.delete(module);
    }

    public ListResponse<ModuleMaterialResponse> getModulesDetailByCourseId(int courseId) {
        List<Module> modules = moduleRepository.getModulesByCourseId(courseId);

        List<ModuleMaterialResponse> result = new ArrayList<>();
        modules.forEach(module -> {
            List<Material> materials = materialRepository.getMaterialsByModuleId(module.getId());
            result.add(new ModuleMaterialResponse(module, materials));
        });

        return new ListResponse<>(result);
    }

    public Module getModuleById(int moduleId) {
        return moduleRepository.findById(moduleId).orElseThrow(() -> new AppException(404, MODULE_NOT_FOUND));
    }

    boolean isDuplicateModule(String name, int courseId) {
        return moduleRepository.findModuleByNameAndCourseId(name, courseId).isPresent();
    }

    boolean isDuplicateModuleUpdate(String name, int moduleId) {
        List<Module> modules = moduleRepository.getModulesInSameCourse(moduleId);
        return modules.stream().anyMatch(module -> module.getName().equals(name));
    }
}
