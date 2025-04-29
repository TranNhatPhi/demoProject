package com.r2sCompany.exampleProject.controller;

import com.r2sCompany.exampleProject.common.response.ResponseData;
import com.r2sCompany.exampleProject.common.response.ResponseError;
import com.r2sCompany.exampleProject.dto.DemoDTO;
import com.r2sCompany.exampleProject.service.DemoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class DemoController {

    private final DemoService demoService;

    @GetMapping("/hello")
    public ResponseEntity<ResponseData<String>> hello() {
        log.info("demoController hello");
        return ResponseEntity.ok(new ResponseData<>("demo controller", "hello"));
    }

    @PostMapping
    public ResponseEntity<?> createItem(@RequestBody DemoDTO demoDTO) {
        try {
            DemoDTO created = demoService.create(demoDTO);
            log.info("demoController createItem");
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(new ResponseData<>( "Demo created", created));
        } catch (Exception e) {
            log.error("Error creating demo", e);
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseError( "Failed to create demo"));
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllItems() {
        try {
            List<DemoDTO> demoList = demoService.findAll();
            log.info("demoController getAllItems");
            return ResponseEntity.ok(new ResponseData<>("All demos", demoList));
        } catch (Exception e) {
            log.error("Error fetching all demos", e);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseError( "Failed to retrieve demos"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getItem(@PathVariable Long id) {
        try {
            DemoDTO demo = demoService.findById(id);
            log.info("demoController getItem");
            return ResponseEntity.ok(new ResponseData<>("Demo found", demo));
        } catch (Exception e) {
            log.error("Error getting demo", e);
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseError( "Demo not found with id " + id));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateItem(@PathVariable Long id, @Valid @RequestBody DemoDTO demoDTO) {
        try {
            DemoDTO updatedDemo = demoService.update(id, demoDTO);
            log.info("demoController updateItem");
            return ResponseEntity.ok(new ResponseData<>( "Demo updated", updatedDemo));
        } catch (Exception e) {
            log.error("Error updating demo", e);
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseError( "Demo not found with id " + id));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable Long id) {
        try {
            demoService.delete(id);
            log.info("demoController deleteItem");
            return ResponseEntity
                    .ok(new ResponseData<>( "Demo deleted successfully", null));
        } catch (Exception e) {
            log.error("Error deleting demo", e);
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseError( "Demo not found with id " + id));
        }
    }
}
