package com.r2sCompany.exampleProject.service;

import com.r2sCompany.exampleProject.dto.DemoDTO;
import java.util.List;

public interface DemoService {
    DemoDTO create(DemoDTO demoDTO);

    DemoDTO update(Long id, DemoDTO demoDTO);

    DemoDTO findById(Long id);

    List<DemoDTO> findAll();

    void delete(Long id);
}
