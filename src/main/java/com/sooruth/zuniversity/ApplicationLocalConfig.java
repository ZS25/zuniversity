package com.sooruth.zuniversity;

import com.github.javafaker.Faker;
import com.sooruth.zuniversity.entity.Student;
import com.sooruth.zuniversity.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@Profile("local")
@Configuration
@PropertySource("messages.properties")
@Import({SecurityConfig.class})
public class ApplicationLocalConfig {

    private final Logger LOG = LoggerFactory.getLogger(ApplicationLocalConfig.class);

    @Bean
    /**
     * @apiNote This method will execute just after the context is created to initialise the database with some records.
     */
    public CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            fillStudentTablesAtStartup(studentRepository);
            //retrievePagedSortedStudents(studentRepository);
        };
    }

    private void fillStudentTablesAtStartup(StudentRepository studentRepository) {
        Faker faker = new Faker();
        List<Student> studentList = new ArrayList<>(20);
        for (int i = 0; i < 20; i++) {
            final String firstName = faker.name().firstName();
            final String lastName = faker.name().lastName();
            final String email = String.format("%s.%s@zuniversity.edu",firstName,lastName);
            final int age = faker.number().numberBetween(18,99);
            final Student student = new Student(firstName,lastName,email,age);

           studentList.add(student);
        }
        studentRepository.saveAll(studentList);
        LOG.info(String.format("Number of records in T_STUDENT at startup: %d", studentRepository.count()));
    }

    private void retrievePagedSortedStudents(StudentRepository studentRepository) {
        LOG.info(String.format("Number of students at beginning of application is:%d",
                studentRepository.count()));
        final Sort SORT = Sort.by("lastName").ascending()
                .and(Sort.by("age").ascending());

        PageRequest pageRequest = PageRequest.of(0,5,SORT); //0 means first page, 5 means 5 students per page
        Page<Student> page = studentRepository.findAll(pageRequest);

        while(!page.isEmpty()){
            page.get().forEach(p -> LOG.info(String.format("Student: %s",p)));

            pageRequest = pageRequest.next();
            page = studentRepository.findAll(pageRequest);
        }
    }
}
