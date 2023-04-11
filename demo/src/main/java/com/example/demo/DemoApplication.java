package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository repository, MongoTemplate mongoTemplate){
		return args ->{
			Address adrs = new Address(
					"England",
					"London",
					"NE9");
			Student st = new Student(
					"jamila",
					"ahmed",
					"jahmed@gmail.com",
					Gender.FEMALE,
					adrs,
					List.of("Computer Science"),
					BigDecimal.TEN,
					LocalDateTime.now()


			);
			//executeThis(repository, mongoTemplate, st);
			repository.findByEmail(st.getEmail()).ifPresentOrElse(student -> {
				System.out.println("student exists");
			},()->{
				System.out.println("inseting student");
				repository.insert(st);
			});


		};
	}

	private static void executeThis(StudentRepository repository, MongoTemplate mongoTemplate, Student st) {
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is("jahmed@gmail.com"));

		List<Student> lst = mongoTemplate.find(query, Student.class);

		if(lst.size()>1){
			throw new IllegalStateException("found many students with "+ st.getEmail());
		}else if(lst.isEmpty()){
			System.out.println("inserting student "+ st.getEmail());
			repository.insert(st);

		}else {

			System.out.println("User already exists "+ st.getEmail());

		}
	}
}
