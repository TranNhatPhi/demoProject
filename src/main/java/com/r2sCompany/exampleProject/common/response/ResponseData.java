package com.r2sCompany.exampleProject.common.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Data
public class ResponseData<T> {
    private boolean success;
    private String message;
    private T data;

    public ResponseData(String message, T data) {
        this.success = true;
        this.message = message;
        this.data = data;
    }

    // getter + setter (hoặc dùng @Data của Lombok)
}


