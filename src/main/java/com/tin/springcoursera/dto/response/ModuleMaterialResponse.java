package com.tin.springcoursera.dto.response;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.tin.springcoursera.entity.Material;
import com.tin.springcoursera.entity.Module;
import com.tin.springcoursera.repository.MaterialRepository;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ModuleMaterialResponse {
    @JsonUnwrapped
    private Module module;
    private List<Material> materials;
}
