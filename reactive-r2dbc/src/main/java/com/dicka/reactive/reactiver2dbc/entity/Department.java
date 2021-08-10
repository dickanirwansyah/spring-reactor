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
@Table("department")
public class Department {

    @Id
    @Column("id")
    private Integer id;

    @Column("name")
    private String name;

    @Column("user_id")
    private Integer userId;

    @Column("loc")
    private String loc;
}
