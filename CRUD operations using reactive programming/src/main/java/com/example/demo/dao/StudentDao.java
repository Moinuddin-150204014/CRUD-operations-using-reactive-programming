package com.example.demo.dao;

import com.example.demo.model.Student;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class StudentDao {
    //    private List<Student> students = new ArrayList<>();
    private Flux<Student> studentFlux = Flux.empty();


//    public List<Student> getStudents(){
//        return students;
//    }

    public Flux<Student> getStudentFlux() {
        return studentFlux;
    }

//    public Student addStudent(String name, String email){
//        UUID id = UUID.randomUUID();
//        Student student = new Student(id, name, email);
//        students.add(student);
//        return student;
//    }

    public Mono<Student> addStudentMono(String name, String email) {
        UUID id = UUID.randomUUID();
        Student student = new Student(id, name, email);
        studentFlux = studentFlux.concatWithValues(student);
        return Mono.just(student);
    }


    public Mono<Void> deleteStudent(UUID studentId) {
        studentFlux = studentFlux.filter(student -> !student.getId().equals(studentId));
        return Mono.empty();
    }

    public Mono<Void> updateStudent(UUID studentId, Student updateSt) {
        studentFlux = studentFlux
                .map(student ->
                {
                    if (student.getId().equals(studentId)) {
                        student.setName(updateSt.getName());
                        student.setEmail(updateSt.getEmail());
                    }
                    return student;
                });

        return Mono.empty();
    }
}
