package com.example.oct_student.model.repository;

import com.example.oct_student.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    List<Student> findByStudentNameOrStudentId(String studentName,int studentId);
    List<Student>findByStudentNameContaining(String studentName);//search Gần đúng
    List<Student>getStudentsByAgeBetween(int ageFrom,int ageTo);
}

