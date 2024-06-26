package com.example.demo.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "student")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity {
    // Getters and Setters for all fields including ID
    @Getter
    @Id
    @SequenceGenerator(
            name = "student_sequence",
            sequenceName = "student_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy =  GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    private Long id;

    @Getter
    @Column(unique = true, nullable = false)
    private String name;

    @Getter
    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Getter
    private LocalDate dob;

    @Getter
    @Column(name = "student_group", nullable = false)
    private String studentGroup;

    @Getter
    private String subGroup;

    @Transient
    private Integer age;

    // Constructor without id parameter
    public StudentEntity(String name,
                         String email,
                         LocalDate dob,
                         String studentGroup,
                         String subGroup) {
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.studentGroup = studentGroup;
        this.subGroup = subGroup;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public void setStudentGroup(String studentGroup) {
        this.studentGroup = studentGroup;
    }

    public void setSubGroup(String subGroup) {
        this.subGroup = subGroup;
    }

    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
