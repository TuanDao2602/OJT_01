package com.example.oct_student.model.serviceImp;

import com.example.oct_student.model.entity.Student;
import com.example.oct_student.model.repository.StudentRepository;
import com.example.oct_student.model.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.sql.SQLException;
import java.util.List;
@Service
@Transactional(rollbackFor = SQLException.class )
public class StudentServiceImp implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(int studentId) {
        return studentRepository.findById(studentId).get();
    }

    @Override
    public Student saveOrUpdate(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void delete(int studentId) {
        studentRepository.deleteById(studentId);

    }

    @Override
    public List<Student> searchByNameOrId(String studentName,int studentId) {
        return studentRepository.findByStudentNameOrStudentId(studentName,studentId);
    }

    @Override
    public List<Student> getStudentsByAgeBetween(int ageFrom, int ageTo) {
        return studentRepository.getStudentsByAgeBetween(ageFrom,ageTo);
    }

    @Override
    public List<Student> sortStudentByStudentName(String direction) {
        if (direction.equals("asc")){
            return studentRepository.findAll(Sort.by("studentName").ascending());
        }else {
            return studentRepository.findAll(Sort.by("studentName").descending());
        }

    }

    @Override
    public List<Student> sortByNameAndAge(String directionName, String directionAge) {

        if (directionName.equals("asc")){
            if (directionAge.equals("asc")){
                return studentRepository.findAll(Sort.by("studentName").and(Sort.by("age")));
            }else {
                return studentRepository.findAll(Sort.by("studentName").and(Sort.by("age").descending()));
            }

        }else {
            if (directionAge.equals("asc")){
                return studentRepository.findAll(Sort.by("studentName").descending().and(Sort.by("age")));
            }else {
                return studentRepository.findAll(Sort.by("studentName").descending().and(Sort.by("age").descending()));
            }

        }

    }

    @Override
    public Page<Student> getPagging(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }
}
