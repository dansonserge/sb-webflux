package com.sergedanson.spwebflux.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.annotation.Generated;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="user_entity")
public class UserEntity {
    @Id
    private Long id;
    private String firstName;
    private String lastName;

    private Boolean status;

    private String email;

    private String password;
}
