package com.sooruth.zuniversity.service;

import com.sooruth.zuniversity.entity.User;
import com.sooruth.zuniversity.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Overwriting this method in Spring Security UserDetailsService interface in order not to reinvent the wheel.
     * DaoAuthenticationProvider from Spring Security will continue from here...
     * @param username is email
     * @return UserDetails
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String email, password;
        List<GrantedAuthority> authorities = null; // later multiple authorities (permissions) can be given to a user
        User user = userRepository.findByEmail(username);
        if (null == user) {
            String errorMessage = String.format("User details not found for the user: %s", username);
            LOG.error(errorMessage);
            throw new UsernameNotFoundException(errorMessage);
        } else{
            email = user.getEmail();
            password = user.getPassword();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getAuthority()));
        }
        return new org.springframework.security.core.userdetails.User(email,password,authorities);
    }

    @Override
    public Long create(User user) {
        user.setDateCreated(LocalDateTime.now());
        return (userRepository.save(user)).getId();
    }

    @Override
    public User read(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        return userOptional.orElseThrow(() -> new IllegalArgumentException(
                String.format("User with ID:%d not found!", id)));
    }

    @Override
    public User findUserByEmail(String email) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(email));

        return userOptional.orElseThrow(() -> new IllegalArgumentException(
                String.format("User with email:%s not found!", email)));
    }

    @Override
    public Page<User> readAll(int page, int size) {
        final Sort SORT_BY_EMAIL = Sort.by("email").ascending();
        PageRequest pageRequest = PageRequest.of(page, size, SORT_BY_EMAIL);

        return userRepository.findAll(pageRequest);
    }

    @Override
    public User update(User user) {
        User userFromDatabase = read(user.getId());

        userFromDatabase.setEmail(user.getEmail());
        userFromDatabase.setPassword(user.getPassword());
        userFromDatabase.setAuthority(user.getAuthority());

        return userRepository.save(userFromDatabase);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
