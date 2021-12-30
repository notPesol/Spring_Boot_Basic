package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {

            Student student1 = new Student("Tyler", "tyler@ex.com",
                    LocalDate.of(2001, Month.FEBRUARY, 5));
            Student student2 = new Student("Oliver", "oliver@ex.com",
                    LocalDate.of(2010, Month.AUGUST, 25));
            Student student3 = new Student("Burger", "burger@ex.com",
                    LocalDate.of(1995, Month.DECEMBER, 19));
            Student student4 = new Student("Joker", "joker@ex.com",
                    LocalDate.of(1980, Month.MAY, 9));

            repository.saveAll(List.of(student1, student2, student3, student4));
        };
    }
}
