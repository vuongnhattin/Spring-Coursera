package com.tin.springcoursera.service;

import com.tin.springcoursera.dto.request.CreateModuleRequest;
import com.tin.springcoursera.dto.request.UpdateModuleRequest;
import com.tin.springcoursera.dto.response.ListResponse;
import com.tin.springcoursera.dto.response.ModuleMaterialResponse;
import com.tin.springcoursera.entity.Material;
import com.tin.springcoursera.entity.Module;
import com.tin.springcoursera.exception.ResourceNotFoundException;
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

    private final String MODULE_NOT_FOUND = "Không tìm thấy học phần";

    public ListResponse<Module> getModulesByCourseId(int courseId) {
        return new ListResponse<>(moduleRepository.getModulesByCourseId(courseId));
    }

    @Transactional
    public Module createModule(CreateModuleRequest request) {
        Module module = Module.builder()
                .name(request.getName())
                .moduleNo(request.getModuleNo())
                .courseId(request.getCourseId())
                .build();
        return moduleRepository.save(module);
    }

    @Transactional
    public Module updateModule(Integer moduleId, UpdateModuleRequest request) {
        Module module = moduleRepository.findById(moduleId).orElseThrow(() -> new ResourceNotFoundException(MODULE_NOT_FOUND));

        module.setName(request.getName());
        module.setModuleNo(request.getModuleNo());

        return moduleRepository.save(module);
    }

    @Transactional
    public void deleteModule(int moduleId) {
        Module module = moduleRepository.findById(moduleId).orElseThrow(() -> new ResourceNotFoundException(MODULE_NOT_FOUND));
        moduleRepository.delete(module);
    }

    public ListResponse<ModuleMaterialResponse> getModuleMaterialsByCourseId(int courseId) {
        List<Module> modules = moduleRepository.getModulesByCourseId(courseId);

        List<ModuleMaterialResponse> result = new ArrayList<>();
        modules.forEach(module -> {
            List<Material> materials = materialRepository.getMaterialsByModuleId(module.getId());
            result.add(new ModuleMaterialResponse(module, materials));
        });

        return new ListResponse<>(result);
    }
}
