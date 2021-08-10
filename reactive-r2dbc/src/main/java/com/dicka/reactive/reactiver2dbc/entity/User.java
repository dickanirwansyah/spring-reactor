package com.dicka.reactive.reactiver2dbc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table("users")
public class User {

    @Id
    @Column("id")
    private Integer id;

    @Column("name")
    private String name;

    @Column("age")
    private int age;

    @Column("salary")
    private double salary;
}
