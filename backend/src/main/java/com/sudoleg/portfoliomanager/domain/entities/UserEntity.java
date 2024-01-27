package com.sudoleg.portfoliomanager.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Database entity for users.
 * The username and the email are unique.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    private Integer userId;

    @Column(unique = true)
    private String username;

    private String name;

    private String surname;

    @Column(unique = true)
    private String email;

}
