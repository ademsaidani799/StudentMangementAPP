package com.example.demo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

 @Data
 @Document
 @NoArgsConstructor
public class Student{
     @Id
     private String id;
    private String firstName;
    private String lastName;
    @Indexed(unique = true)
    private String email;
    private Gender gender;
    private Address adress;
    private List<String> favouriteSubjects;

    private BigDecimal totalSpentInBooks;
    private LocalDateTime created;

     public Student(String firstName, String lastName, String email, Gender gender, Address adress, List<String> favouriteSubjects, BigDecimal totalSpentInBooks, LocalDateTime created) {
         this.firstName = firstName;
         this.lastName = lastName;
         this.email = email;
         this.gender = gender;
         this.adress = adress;
         this.favouriteSubjects = favouriteSubjects;
         this.totalSpentInBooks = totalSpentInBooks;
         this.created = created;
     }
 }
