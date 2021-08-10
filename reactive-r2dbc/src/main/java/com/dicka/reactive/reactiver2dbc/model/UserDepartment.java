package com.dicka.reactive.reactiver2dbc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDepartment {

    private Integer userId;
    private String username;
    private int age;
    private double salary;
    private Integer departmentId;
    private String departmentName;
    private String loc;
}
