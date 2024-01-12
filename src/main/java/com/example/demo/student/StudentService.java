package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent(){
        return studentRepository.findAll();
    }

    public void addnewStudent(Student student){
        Optional<Student> studentByEmail=studentRepository.findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("Duplicate Email");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        boolean exist= studentRepository.existsById(id);
        if(!exist){
            throw new IllegalStateException("Student with id "+id+" does not exists.");
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(long id, String name, String email) {
        Student student =studentRepository.findById(id).orElseThrow(()-> new IllegalStateException("Student with id "+id+" does not exists."));
        if (name!=null && !name.isEmpty() && !Objects.equals(student.getName(),name)){
            student.setName(name);
        }
        if (email!=null && !email.isEmpty() && !Objects.equals(student.getEmail(),email)){
            Optional<Student> s = studentRepository.findStudentByEmail(email);
            if(s.isPresent()){
                throw  new IllegalStateException("Duplicate Email");
            }
            student.setEmail(email);
        }

    }
}
