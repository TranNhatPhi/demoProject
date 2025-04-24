package com.r2sCompany.exampleProject.dto.response;

public class ResponseError extends ResponseData {

    public ResponseError(int status, String msg) {
        super(status, msg);
    }
}