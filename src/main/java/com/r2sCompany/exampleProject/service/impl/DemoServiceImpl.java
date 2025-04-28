package com.r2sCompany.exampleProject.service.impl;

import com.r2sCompany.exampleProject.dto.DemoDTO;
import com.r2sCompany.exampleProject.entity.DemoEntity;
import com.r2sCompany.exampleProject.mapper.DemoMapper;
import com.r2sCompany.exampleProject.repository.DemoRepository;
import com.r2sCompany.exampleProject.service.DemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DemoServiceImpl implements DemoService {

    private final DemoRepository demoRepository;
    private final DemoMapper demoMapper;

    @Override
    public DemoDTO create(DemoDTO demoDTO) {
        DemoEntity entity = demoMapper.toEntity(demoDTO);
        DemoEntity savedEntity = demoRepository.save(entity);
        return demoMapper.toDTO(savedEntity);
    }

    @Override
    public DemoDTO update(Long id, DemoDTO demoDTO) {
        DemoEntity existingEntity = demoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Demo not found with id " + id));
        existingEntity.setName(demoDTO.getName());
        existingEntity.setAge(demoDTO.getAge());
        DemoEntity updatedEntity = demoRepository.save(existingEntity);
        return demoMapper.toDTO(updatedEntity);
    }

    @Override
    public DemoDTO findById(Long id) {
        DemoEntity entity = demoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Demo not found with id " + id));
        return demoMapper.toDTO(entity);
    }

    @Override
    public List<DemoDTO> findAll() {
        List<DemoEntity> entities = demoRepository.findAll();
        return entities.stream()
                .map(demoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (!demoRepository.existsById(id)) {
            throw new RuntimeException("Demo not found with id " + id);
        }
        demoRepository.deleteById(id);
    }
}
