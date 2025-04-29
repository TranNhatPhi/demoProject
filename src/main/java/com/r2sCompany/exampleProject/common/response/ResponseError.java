package com.r2sCompany.exampleProject.common.response;



public class ResponseError {
    private boolean success = false;
    private String message;

    public ResponseError(String message) {
        this.message = message;
    }

    // getter + setter (hoặc dùng @Data của Lombok)
}
