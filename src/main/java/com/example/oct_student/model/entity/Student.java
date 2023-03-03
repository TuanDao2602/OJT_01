package com.example.oct_student.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "StudentId")
    private int studentId;
    @Column(name = "StudentName", columnDefinition = "nvarchar(50)", nullable = false, unique = true)
    private String studentName;
    @Column(name = "Age")
    private int age;
    @Column(name = "StudentStatus")
    private boolean studentStatus;
}
