package com.sooruth.zuniversity;

import com.github.javafaker.Faker;
import com.sooruth.zuniversity.entity.Student;
import com.sooruth.zuniversity.entity.User;
import com.sooruth.zuniversity.repository.StudentRepository;
import com.sooruth.zuniversity.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
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
    public CommandLineRunner commandLineRunner(StudentRepository studentRepository, UserRepository userRepository, PasswordEncoder passwordEncoder){
        return args -> {
            fillUserTableAtStartup(userRepository, passwordEncoder);
            fillStudentTableAtStartup(studentRepository);
        };
    }

    private void fillUserTableAtStartup(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        List<User> userList = new ArrayList<>();

        final String email1 = "john.connor@zuniversity.edu";
        final String password1 = passwordEncoder.encode("katherineBrewster");
        final String authority1 = "read";
        final User user1 = new User(email1, password1, authority1, LocalDateTime.now(), null);
        userList.add(user1);

        final String email2 = "kyle.reez@zuniversity.edu";
        final String password2 = passwordEncoder.encode("sarahConnor");
        final String authority2 = "read";
        final User user2 = new User(email2, password2, authority2, LocalDateTime.now(), null);
        userList.add(user2);

        final String email3 = "t.800@zuniversity.edu";
        final String password3 = passwordEncoder.encode("T-X");
        final String authority3 = "write";
        final User user3 = new User(email3, password3, authority3, LocalDateTime.now(), null);
        userList.add(user3);

        userRepository.saveAll(userList);

        LOG.info(String.format("Number of records in T_USER at startup: %d", userRepository.count()));
    }

    private void fillStudentTableAtStartup(StudentRepository studentRepository) {
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
}
