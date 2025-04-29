package com.r2sCompany.exampleProject.dto;


import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DemoDTO {

    @NotBlank(message = "Tên không được để trống")
    @Size(min = 2, max = 100, message = "Tên phải từ 2 đến 100 ký tự")
    private String name;

    @NotNull(message = "Tuổi không được để trống")
    @Min(value = 0, message = "Tuổi phải lớn hơn hoặc bằng 0")
    @Max(value = 150, message = "Tuổi phải nhỏ hơn hoặc bằng 150")
    private Integer age;

    @NotBlank(message = "Địa chỉ không được để trống")
    @Size(max = 255, message = "Địa chỉ tối đa 255 ký tự")
    private String address;
}

