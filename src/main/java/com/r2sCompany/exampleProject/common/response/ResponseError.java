package com.r2sCompany.exampleProject.common.response;


public class ResponseError extends ResponseData<Object> {

    public ResponseError(int status, String msg) {
        super(status, msg);
    }
}
