package com.fernando.curso.springboot.app.springboot_crud.services;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fernando.curso.springboot.app.springboot_crud.entities.Role;
import com.fernando.curso.springboot.app.springboot_crud.entities.User;
import com.fernando.curso.springboot.app.springboot_crud.repositories.RoleRepository;
import com.fernando.curso.springboot.app.springboot_crud.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional
    public User save(User user) {
        Optional<Role> optionalRoleUser = roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();

        // optionalRoleUser.ifPresent(role -> roles.add(role));
        optionalRoleUser.ifPresent(roles::add);

        if(user.isAdmin()){
            Optional<Role> optionalRoleAdmin = roleRepository.findByName("ROLE_ADMIN");

            // optionalRoleAdmin.ifPresent(role -> roles.add(role));
            optionalRoleAdmin.ifPresent(roles::add);
        }

        user.setRoles(roles);

        // String passwordEncoded = passwordEncoder.encode(user.getPassword());
        // user.setPassword(passwordEncoded);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
        
    }

}
