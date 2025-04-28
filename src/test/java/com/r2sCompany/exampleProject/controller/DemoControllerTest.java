package com.r2sCompany.exampleProject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.r2sCompany.exampleProject.dto.DemoDTO;
import com.r2sCompany.exampleProject.service.DemoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class DemoControllerTest {

    private MockMvc mockMvc;
    private DemoService demoService;
    private ObjectMapper objectMapper;
    private DemoDTO demoDTO;

    @BeforeEach
    void setUp() {
        demoService = mock(DemoService.class);
        DemoController demoController = new DemoController(demoService);
        mockMvc = MockMvcBuilders.standaloneSetup(demoController).build();
        objectMapper = new ObjectMapper();

        demoDTO = new DemoDTO();
        demoDTO.setName("John");
        demoDTO.setAge(30);
        demoDTO.setAddress("123 Main St");
    }

    @Test
    void testHelloEndpoint() throws Exception {
        mockMvc.perform(get("/demo/hello"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("hello"));
    }

    @Test
    void testCreateItem() throws Exception {
        when(demoService.create(any())).thenReturn(demoDTO);

        mockMvc.perform(post("/demo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(demoDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.name").value("John"))
                .andExpect(jsonPath("$.msg").value("Demo created")); // 🔧 sửa tại đây
    }

    @Test
    void testCreateItem_Fail() throws Exception {
        when(demoService.create(any())).thenThrow(new RuntimeException("Something went wrong"));

        mockMvc.perform(post("/demo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(demoDTO)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.msg").value("Failed to create demo")); // 🔧 sửa tại đây
    }

    @Test
    void testGetAllItems() throws Exception {
        when(demoService.findAll()).thenReturn(List.of(demoDTO));

        mockMvc.perform(get("/demo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].name").value("John"))
                .andExpect(jsonPath("$.msg").value("All demos")); // 🔧 sửa tại đây
    }

    @Test
    void testGetItem() throws Exception {
        when(demoService.findById(1L)).thenReturn(demoDTO);

        mockMvc.perform(get("/demo/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.name").value("John"))
                .andExpect(jsonPath("$.msg").value("Demo found"));
    }

    @Test
    void testGetItem_NotFound() throws Exception {
        when(demoService.findById(anyLong()))
                .thenThrow(new RuntimeException("Demo not found with id 99"));

        mockMvc.perform(get("/demo/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.msg").value("Demo not found with id 99"));
    }

    @Test
    void testUpdateItem() throws Exception {
        when(demoService.update(anyLong(), any())).thenReturn(demoDTO);

        mockMvc.perform(put("/demo/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(demoDTO)))
                .andDo(result -> System.out.println("RESPONSE: " + result.getResponse().getContentAsString()))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.data.name").value("John"))
                .andExpect(jsonPath("$.msg").value("Demo updated"));
    }

    @Test
    void testUpdateItem_NotFound() throws Exception {
        when(demoService.update(anyLong(), any()))
                .thenThrow(new RuntimeException("Demo not found with id 2"));

        mockMvc.perform(put("/demo/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(demoDTO)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.msg").value("Demo not found with id 2"));
    }

    @Test
    void testDeleteItem() throws Exception {
        doNothing().when(demoService).delete(1L);

        mockMvc.perform(delete("/demo/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg").value("Demo deleted successfully")); // 🔧 sửa tại đây
    }

    @Test
    void testDeleteItem_NotFound() throws Exception {
        doThrow(new RuntimeException("Demo not found with id 999"))
                .when(demoService).delete(anyLong());

        mockMvc.perform(delete("/demo/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.msg").value("Demo not found with id 999"));
    }
}
