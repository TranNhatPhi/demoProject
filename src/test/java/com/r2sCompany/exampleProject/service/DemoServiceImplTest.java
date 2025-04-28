package com.r2sCompany.exampleProject.service;

import com.r2sCompany.exampleProject.dto.DemoDTO;
import com.r2sCompany.exampleProject.entity.DemoEntity;
import com.r2sCompany.exampleProject.mapper.DemoMapper;
import com.r2sCompany.exampleProject.repository.DemoRepository;
import com.r2sCompany.exampleProject.service.impl.DemoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DemoServiceImplTest {

    @Mock
    private DemoRepository demoRepository;

    @Mock
    private DemoMapper demoMapper;

    @InjectMocks
    private DemoServiceImpl demoService;

    private DemoDTO demoDTO;
    private DemoEntity demoEntity;

    @BeforeEach
    void setUp() {
//        MockitoAnnotations.openMocks(this);

        demoDTO = new DemoDTO();
        demoDTO.setName("John");
        demoDTO.setAge(25);
        demoDTO.setAddress("123 Main St");

        demoEntity = new DemoEntity();
        demoEntity.setName("John");
        demoEntity.setAge(25);
        demoEntity.setAddress("123 Main St");
    }

    @Test
    void testCreate() {
        DemoEntity savedEntity = new DemoEntity();
        savedEntity.setId(1L);
        savedEntity.setName("John");
        savedEntity.setAge(25);
        savedEntity.setAddress("123 Main St");

        DemoDTO returnedDTO = new DemoDTO();
        returnedDTO.setName("John");
        returnedDTO.setAge(25);
        returnedDTO.setAddress("123 Main St");

        when(demoMapper.toEntity(demoDTO)).thenReturn(demoEntity);
        when(demoRepository.save(demoEntity)).thenReturn(savedEntity);
        when(demoMapper.toDTO(savedEntity)).thenReturn(returnedDTO);

        DemoDTO result = demoService.create(demoDTO);

        assertNotNull(result);
        assertEquals("John", result.getName());
        assertEquals(25, result.getAge());
        assertEquals("123 Main St", result.getAddress());
    }

    @Test
    void testUpdate_Success() {
        when(demoRepository.findById(1L)).thenReturn(Optional.of(demoEntity));
        when(demoRepository.save(demoEntity)).thenReturn(demoEntity);
        when(demoMapper.toDTO(demoEntity)).thenReturn(demoDTO);

        DemoDTO result = demoService.update(1L, demoDTO);

        assertNotNull(result);
        verify(demoRepository).save(demoEntity);
    }

    @Test
    void testUpdate_NotFound() {
        when(demoRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> demoService.update(1L, demoDTO));
        assertTrue(exception.getMessage().contains("Demo not found"));
    }

    @Test
    void testFindById_Success() {
        when(demoRepository.findById(1L)).thenReturn(Optional.of(demoEntity));
        when(demoMapper.toDTO(demoEntity)).thenReturn(demoDTO);

        DemoDTO result = demoService.findById(1L);

        assertEquals("John", result.getName());
    }

    @Test
    void testFindById_NotFound() {
        when(demoRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> demoService.findById(1L));
        assertTrue(exception.getMessage().contains("Demo not found"));
    }

    @Test
    void testFindAll() {
        when(demoRepository.findAll()).thenReturn(Arrays.asList(demoEntity));
        when(demoMapper.toDTO(demoEntity)).thenReturn(demoDTO);

        List<DemoDTO> result = demoService.findAll();

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getName());
    }

    @Test
    void testDelete_Success() {
        when(demoRepository.existsById(1L)).thenReturn(true);
        doNothing().when(demoRepository).deleteById(1L);

        assertDoesNotThrow(() -> demoService.delete(1L));
        verify(demoRepository).deleteById(1L);
    }

    @Test
    void testDelete_NotFound() {
        when(demoRepository.existsById(1L)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> demoService.delete(1L));
        assertTrue(exception.getMessage().contains("Demo not found"));
    }
}
