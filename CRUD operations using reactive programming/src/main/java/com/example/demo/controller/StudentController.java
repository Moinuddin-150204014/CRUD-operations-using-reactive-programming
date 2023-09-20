package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class StudentController {
    StudentService studentService;
    StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    /*@GetMapping(value = "/students")
    List<Student> students(){
        return studentService.students();
    }

    @PostMapping("/student")
    Student addStudent(@RequestBody Student student){
        return studentService.addStudent(student.getName(), student.getEmail());
    }*/

    @GetMapping(value = "/rstudents", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Student> studentFlux(){
        return studentService.studentFlux();
    }

    @PostMapping("/rstudent")
    Mono<Student> addStudentMono(@RequestBody Student student){
        return studentService.addStudentMono(student.getName(), student.getEmail());
    }

    @DeleteMapping("/deleteStudent")
    Mono<Void> deleteStudentMono(@RequestBody Student student){
        return studentService.deleteStudentMono(student.getId());
    }

    @PutMapping("/updateStudent")
    Mono<Void> updateStudentMono(@RequestBody Student student){
        return studentService.updateStudentMono(student.getId(),student);
    }
}
