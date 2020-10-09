package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.entities.Role;
import com.geekbrains.geek.market.entities.User;
import com.geekbrains.geek.market.repositories.RoleRepository;
import com.geekbrains.geek.market.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/registration")
@AllArgsConstructor
public class RegistrationController {
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @GetMapping
    public String registration(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("display_error", false);
        return "registration";
    }

    @PostMapping
    public String addUser(@ModelAttribute("user") User newUser, Model model) {
        if(userService.findByUsername(newUser.getName()) != null) {
            model.addAttribute("display_error", true);

            return "registration"; // такой пользователь уже зарегистрирован
        }
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        List<Role> user_roles = new ArrayList<>();
        user_roles.add(roleRepository.findByName("ROLE_USER"));
        newUser.setRoles(user_roles);
        userService.save(newUser);
        return "redirect:/login";
    }
}