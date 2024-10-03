package com.tin.springcoursera.service;

import com.tin.springcoursera.dto.request.CreateMaterialRequest;
import com.tin.springcoursera.dto.request.UpdateMaterialRequest;
import com.tin.springcoursera.dto.response.ListResponse;
import com.tin.springcoursera.entity.Material;
import com.tin.springcoursera.entity.Module;
import com.tin.springcoursera.exception.ResourceNotFoundException;
import com.tin.springcoursera.repository.MaterialRepository;
import com.tin.springcoursera.repository.ModuleRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@AllArgsConstructor
public class MaterialService {
    private final MaterialRepository materialRepository;
    private final FileService fileService;
    private final ModuleService moduleService;

    private final String MATERIAL_NOT_FOUND = "Không tìm thấy tài liệu";
    private final ModuleRepository moduleRepository;

    public ListResponse<Material> getMaterialsByModuleId(int moduleId) {
        return new ListResponse<>(materialRepository.getMaterialsByModuleId(moduleId));
    }

    @Transactional
    public Material creteMaterial(CreateMaterialRequest request) throws IOException {
        Module module = moduleRepository.findById(request.getModuleId()).orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy học phần"));
        Material material = Material.builder()
                .name(request.getName())
                .fileType(request.getFileType())
                .moduleId(module.getId())
                .build();

        String fileUrl = fileService.storeFile(request.getFile());
        material.setFileUrl(fileUrl);

        return materialRepository.save(material);
    }

    @Transactional
    public Material updateMaterial(Integer materialId, UpdateMaterialRequest request) throws IOException {
        Material material = materialRepository.findById(materialId).orElseThrow(() -> new ResourceNotFoundException(MATERIAL_NOT_FOUND));

        if (request.getFile() != null) {
            String fileUrl = fileService.storeFile(request.getFile());
            material.setFileUrl(fileUrl);
        }

        material.setName(request.getName());
        material.setFileType(request.getFileType());

        return materialRepository.save(material);
    }

    @Transactional
    public void deleteMaterial(Integer id) {
        materialRepository.deleteById(id);
    }

    public Material getMaterialById(int id) {
        return materialRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MATERIAL_NOT_FOUND));
    }
}
