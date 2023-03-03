package com.example.oct_student.controller;

import com.example.oct_student.model.entity.Student;
import com.example.oct_student.model.service.StudentService;
import com.example.oct_student.model.serviceImp.StudentServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("api/v1/student")
public class StudentController {
    @Autowired
    private StudentServiceImp studentServiceImp;
    @Autowired
    private StudentService studentService;
    @GetMapping
    public List<Student>getAllStudent(){
        return studentService.findAll();
    }
    @GetMapping("/{studentId}")
    public Student getStudentById(@PathVariable("studentId")int studentId){
        return studentService.findById(studentId);
    }
    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return  studentService.saveOrUpdate(student);
    }
    @PutMapping("/{studentId}")
    public  Student updateStudent(@PathVariable("studentId")int studentId,@RequestBody Student student){
        Student studentUpdate = studentService.findById(studentId);
        studentUpdate.setStudentName(student.getStudentName());
        studentUpdate.setAge(student.getAge());
        studentUpdate.setStudentStatus(student.isStudentStatus());
        //cập nhật
        return studentService.saveOrUpdate(studentUpdate);
    }
    @DeleteMapping("/{studentId}")
    public void deleteStudent(@PathVariable("studentId")int studentId){
        studentService.delete(studentId);
    }
    @GetMapping("/search")
    public List<Student> searchNameOrId(@RequestParam("studentName")String studentName,@RequestParam("studentId")int studentId){
        return studentService.searchByNameOrId(studentName,studentId);
    }
    @GetMapping("/searchByAge")
    public List<Student> getStudentFromTo(@RequestParam("ageFrom")int ageFrom,@RequestParam("ageTo")int ageTo){
        return  studentService.getStudentsByAgeBetween(ageFrom,ageTo);
    }
    @GetMapping("/sortByName")
    public ResponseEntity<List<Student>>sortStudentByStudentName(@RequestParam("direction")String direction){
        List<Student> listStudent = studentService.sortStudentByStudentName(direction);
        return new ResponseEntity<>(listStudent, HttpStatus.OK);

    }
    @GetMapping("/sortByNameAndAge")
    public ResponseEntity<List<Student>>sortStudentByNameAndAge(@RequestParam("directionName")String directionName,@RequestParam("directionAge")String directionAge){
        List<Student>listStudent =studentService.sortByNameAndAge(directionName,directionAge);
        return new ResponseEntity<>(listStudent,HttpStatus.OK);
    }
    @GetMapping("/getPagging")
    public ResponseEntity<Map<String,Object>>getPagging(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "3")int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<Student> pageStudent= studentService.getPagging(pageable);
        Map<String,Object> data = new HashMap<>();
        data.put("student",pageStudent.getContent());
        data.put("total",pageStudent.getSize());
        data.put("totalItems",pageStudent.getTotalElements());
        data.put("totalPages",pageStudent.getTotalPages());
        return new ResponseEntity<>(data,HttpStatus.OK);


    }
    @GetMapping("/getPaggingAndSortByName")
    public ResponseEntity<Map<String,Object>>getPaggingAndSortByName(
            @RequestParam(defaultValue = "0")int page,
            @RequestParam(defaultValue = "3")int size,
            @RequestParam String direction){
        Sort.Order order;
        if(direction.equals("asc")){
            order=new Sort.Order(Sort.Direction.ASC,"studentName");

        }else{
            order=new Sort.Order(Sort.Direction.DESC,"studentName");
        }
        Pageable pageable = PageRequest.of(page,size,Sort.by(order));
        Page<Student> pageStudent= studentService.getPagging(pageable);
        Map<String,Object> data = new HashMap<>();
        data.put("student",pageStudent.getContent());
        data.put("total",pageStudent.getSize());
        data.put("totalItems",pageStudent.getTotalElements());
        data.put("totalPages",pageStudent.getTotalPages());
        return new ResponseEntity<>(data,HttpStatus.OK);


    }

}
