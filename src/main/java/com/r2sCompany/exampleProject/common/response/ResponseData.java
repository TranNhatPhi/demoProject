package com.r2sCompany.exampleProject.common.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Data
public class ResponseData<T> implements Serializable {
    private final int status;
    private final String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    //PUT,PATCH,DELETE
    public ResponseData(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
    //get,post
    public ResponseData(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

}

