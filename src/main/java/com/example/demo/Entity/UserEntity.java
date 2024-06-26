package com.example.demo.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "app_user")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @JsonIgnore // This annotation will exclude 'password' from serialization
    @Column(nullable = false)
    private String password;

    @Column(name = "date_created", nullable = false)
    private LocalDate dateCreated;

    @Column(name = "time_created", nullable = false)
    private LocalTime timeCreated;

    // Constructor without id parameter
    public UserEntity(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.dateCreated = LocalDate.now();
        this.timeCreated = LocalTime.now();
    }
}
