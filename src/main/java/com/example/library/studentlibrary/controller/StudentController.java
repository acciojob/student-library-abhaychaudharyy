package com.example.library.studentlibrary.controller;

import com.example.library.studentlibrary.models.Student;
import com.example.library.studentlibrary.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//Add required annotations
@RestController
@RequestMapping("student")
public class StudentController {

    @Autowired
    StudentService ss;
    //Add required annotations
    @GetMapping("/studentByEmail")
    public ResponseEntity<Student> getStudentByEmail(@RequestParam("email") String email){
        Student s = ss.getDetailsByEmail(email);
        return new ResponseEntity<>(s, HttpStatus.OK);
        //return new ResponseEntity<>("Student details printed successfully ", HttpStatus.OK);
    }

    //Add required annotations
    @GetMapping("/studentById")
    public ResponseEntity<Student> getStudentById(@RequestParam("id") int id){
        Student s = ss.getDetailsById(id);
        // return new ResponseEntity<>("Student details printed successfully ", HttpStatus.OK);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }

    //Add required annotations
    @PostMapping("/createStudent")
    public ResponseEntity<String> createStudent(@RequestBody Student student){
        ss.createStudent(student);
        return new ResponseEntity<>("the student is successfully added to the system", HttpStatus.CREATED);
    }

    //Add required annotations
    @PutMapping("/updateStudent")
    public ResponseEntity<String> updateStudent(@RequestBody Student student){
        ss.updateStudent(student);
        return new ResponseEntity<>("student is updated", HttpStatus.ACCEPTED);
    }

    //Add required annotations
    @DeleteMapping("/deleteStudent")
    public ResponseEntity<String> deleteStudent(@RequestParam("id") int id){
        ss.deleteStudent(id);
        return new ResponseEntity<>("student is deleted", HttpStatus.ACCEPTED);
    }

}
