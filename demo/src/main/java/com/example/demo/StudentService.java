package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StudentService {

    private final StudentRepository repository;
    public List<Student> getAllStudents(){
        return repository.findAll();
    }

    public void addStudent(Student st){
        repository.insert(st);

    }

    public void deleteStudent(String id){
        repository.deleteById(id);

    }

    public Optional<Student> findById(String id){
        return repository.findById(id);
    }

    public Student save(Student st){
        return repository.save(st);
    }
}
