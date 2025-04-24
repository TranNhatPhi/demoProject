package com.r2sCompany.exampleProject.controller;

import com.r2sCompany.exampleProject.common.response.ResponseData;
import com.r2sCompany.exampleProject.common.response.ResponseError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@RestController
@RequestMapping("/demo")
public class demoController {

    private final Map<Long, String> demoStore = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    @GetMapping("/hello")
    public ResponseData<String> hello() {

        log.info("demoController hello");
        return new ResponseData<>(HttpStatus.OK.value(), "demo controller", "hello");

    }

    @PostMapping
    public ResponseData<?> createItem(@RequestBody String content) {
        try {
            Long id = idCounter.incrementAndGet();
            demoStore.put(id, content);
            Map<String, Object> data = new HashMap<>();
            data.put("id", id);
            data.put("content", content);
            log.info("demoController createItem");
            return new ResponseData<>(HttpStatus.CREATED.value(), "Item created", data);
        } catch (Exception e) {
            log.error("Error creating item", e);
            return new ResponseError(HttpStatus.BAD_REQUEST.value(), "Failed to create item");
        }
    }

    @GetMapping
    public ResponseData<?> getAllItems() {
        try {
            List<Map<String, Object>> items = new ArrayList<>();
            demoStore.forEach((id, content) -> {
                Map<String, Object> item = new HashMap<>();
                item.put("id", id);
                item.put("content", content);
                items.add(item);
            });
            log.info("demoController getAllItems");
            return new ResponseData<>(HttpStatus.OK.value(), "All items", items);
        } catch (Exception e) {
            log.error("Error fetching all items", e);
            return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to retrieve items");
        }
    }

    @GetMapping("/{id}")
    public ResponseData<?> getItem(@PathVariable Long id) {
        try {
            String content = demoStore.get(id);
            if (content == null) {
                return new ResponseError(HttpStatus.NOT_FOUND.value(), "Item not found");
            }
            Map<String, Object> item = new HashMap<>();
            item.put("id", id);
            item.put("content", content);
            log.info("demoController getItem");
            return new ResponseData<>(HttpStatus.OK.value(), "Item found", item);
        } catch (Exception e) {
            log.error("Error getting item", e);
            return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to get item");
        }
    }

    @PutMapping("/{id}")
    public ResponseData<?> updateItem(@PathVariable Long id, @RequestBody String newContent) {
        try {
            if (!demoStore.containsKey(id)) {
                return new ResponseError(HttpStatus.NOT_FOUND.value(), "Item not found");
            }
            demoStore.put(id, newContent);
            Map<String, Object> item = new HashMap<>();
            item.put("id", id);
            item.put("content", newContent);
            log.info("demoController updateItem");
            return new ResponseData<>(HttpStatus.ACCEPTED.value(), "Item updated", item);
        } catch (Exception e) {
            log.error("Error updating item", e);
            return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to update item");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseData<?> deleteItem(@PathVariable Long id) {
        try {
            if (!demoStore.containsKey(id)) {
                return new ResponseError(HttpStatus.NOT_FOUND.value(), "Item not found");
            }
            demoStore.remove(id);
            log.info("demoController deleteItem");
            return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "Item deleted", "Deleted item with id: " + id);
        } catch (Exception e) {
            log.error("Error deleting item", e);
            return new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to delete item");
        }
    }
}