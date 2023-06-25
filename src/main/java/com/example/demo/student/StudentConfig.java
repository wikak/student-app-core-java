package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student wikakStudent = new Student("Wilfried", "wk@icloud.com", LocalDate.of(2000, Month.FEBRUARY, 11));
            Student stephaneStudent = new Student("Stephane", "steph@icloud.com", LocalDate.of(2002, Month.FEBRUARY, 11));
            Student billStudent = new Student("Bill", "bill@icloud.com", LocalDate.of(1997, Month.FEBRUARY, 11));
            List<Student> students = new ArrayList<>();
            students.add(wikakStudent);
            students.add(stephaneStudent);
            students.add(billStudent);
            repository.saveAll(students);
        };
    }
}
