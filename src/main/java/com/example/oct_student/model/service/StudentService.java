package com.example.oct_student.model.service;

import com.example.oct_student.model.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    List<Student> findAll();
    Student findById(int studentId);
    Student saveOrUpdate(Student student);
    void delete(int studentId);
    List<Student> searchByNameOrId(String studentName,int studentId);
    List<Student>getStudentsByAgeBetween(int ageFrom,int ageTo);
    List<Student>sortStudentByStudentName(String direction);
    List<Student>sortByNameAndAge(String directionName,String directionAge);
    Page<Student>getPagging(Pageable pageable);
}
