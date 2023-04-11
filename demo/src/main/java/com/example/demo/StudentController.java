package com.example.demo;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;


    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    record NewRequestBody(String firstName, String lastName, String email, Gender gender, Address adress, List<String> favouriteSubjects,BigDecimal totalSpentInBooks, LocalDateTime created){}
    @PostMapping
    public void addStudent(@RequestBody NewRequestBody newRequestBody){
        Student st = new Student();
        st.setFirstName(newRequestBody.firstName());
        st.setLastName(newRequestBody.lastName());
        st.setEmail(newRequestBody.email());
        st.setAdress(newRequestBody.adress());
        st.setGender(newRequestBody.gender());
        st.setFavouriteSubjects(newRequestBody.favouriteSubjects());
        st.setTotalSpentInBooks(newRequestBody.totalSpentInBooks());
        st.setCreated(newRequestBody.created());
        studentService.addStudent(st);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") String id){

        studentService.deleteStudent(id);
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable("id") String id, @RequestBody NewRequestBody newRequestBody){

        studentService.findById(id).map(st->{
            st.setFirstName(newRequestBody.firstName());
            st.setLastName(newRequestBody.lastName());
            st.setEmail(newRequestBody.email());
            st.setAdress(newRequestBody.adress());
            st.setGender(newRequestBody.gender());
            st.setFavouriteSubjects(newRequestBody.favouriteSubjects());
            st.setTotalSpentInBooks(newRequestBody.totalSpentInBooks());
            st.setCreated(newRequestBody.created());

            return studentService.save(st);

        });
    }
}

