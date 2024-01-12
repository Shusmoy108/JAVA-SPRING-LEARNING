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
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student shusmoy= new Student(
                    "Shusmoy",
                    "Shusmoy@gmail.com",
                    LocalDate.of(1996, Month.MAY,13)
            );
            Student nisha=new Student(
                    "Nisha",
                    "nisha@gmail.com",
                    LocalDate.of(1999,Month.APRIL,25)
            );
            //studentRepository.saveAll(List.of(shusmoy,nisha));
        };
    }
}