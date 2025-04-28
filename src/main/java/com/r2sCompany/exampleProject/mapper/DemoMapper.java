package com.r2sCompany.exampleProject.mapper;

import com.r2sCompany.exampleProject.dto.DemoDTO;
import com.r2sCompany.exampleProject.entity.DemoEntity;
import org.springframework.stereotype.Component;

@Component
public class DemoMapper {

    // Chuyển đổi từ DemoDTO sang DemoEntity
    public DemoEntity toEntity(DemoDTO demoDTO) {
        if (demoDTO == null) {
            return null;
        }
        DemoEntity demoEntity = new DemoEntity();
        demoEntity.setName(demoDTO.getName());
        demoEntity.setAge(demoDTO.getAge());
        demoEntity.setAddress(demoDTO.getAddress());
        return demoEntity;
    }

    // Chuyển đổi từ DemoEntity sang DemoDTO
    public DemoDTO toDTO(DemoEntity demoEntity) {
        if (demoEntity == null) {
            return null;
        }
        DemoDTO demoDTO = new DemoDTO();
        demoDTO.setName(demoEntity.getName());
        demoDTO.setAge(demoEntity.getAge());
        demoDTO.setAddress(demoEntity.getAddress());
        return demoDTO;
    }
}
