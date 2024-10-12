package com.tin.springcoursera.controller;

import com.tin.springcoursera.dto.request.CreateMaterialRequest;
import com.tin.springcoursera.dto.request.UpdateMaterialRequest;
import com.tin.springcoursera.dto.response.ListResponse;
import com.tin.springcoursera.entity.Material;
import com.tin.springcoursera.service.MaterialService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api")
@AllArgsConstructor
public class MaterialController {
    private final MaterialService materialService;

    @GetMapping("modules/{moduleId}/materials")
    public ListResponse<Material> getMaterials(@PathVariable int moduleId) {
        return materialService.getMaterialsByModuleId(moduleId);
    }

    @PostMapping("materials")
    @ResponseStatus(HttpStatus.CREATED)
    public Material createMaterial(@Valid @ModelAttribute CreateMaterialRequest request) throws IOException {
        return materialService.creteMaterial(request);
    }

    @PutMapping("materials/{materialId}")
    public Material updateMaterial(@PathVariable int materialId, @Valid @ModelAttribute UpdateMaterialRequest request) throws IOException {
        return materialService.updateMaterial(materialId, request);
    }

    @DeleteMapping("materials/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMaterial(@PathVariable int id) {
        materialService.deleteMaterial(id);
    }

    @GetMapping("materials/{id}")
    public Material getMaterial(@PathVariable int id) {
        return materialService.getMaterialById(id);
    }
}
