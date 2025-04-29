package com.r2sCompany.exampleProject.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "demo")
@Getter
@Setter
@Entity
public class DemoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID tự động tăng
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Integer age;
    @Column(nullable = false)
    private String address;

}
