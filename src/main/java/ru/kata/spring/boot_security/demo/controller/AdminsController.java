package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;

@Controller
@RequestMapping()
public class AdminsController {
    private final UserServiceImpl userService;
    private final RoleServiceImpl roleService;

    public AdminsController(UserServiceImpl userService, RoleServiceImpl roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping("/admin")
    public String getAllUsers(Principal principal, @ModelAttribute("user") User user, Model model) {
        model.addAttribute("principal", userService.getUserByLogin(principal.getName()));
        model.addAttribute("user", userService.getAllUsers());
        model.addAttribute("roles", roleService.getRoles());
        model.addAttribute("newUser", new User());
        return "admin";
    }



    @PostMapping("/admin")
    public String create(@ModelAttribute("user") User user) {
        userService.create(user);
        return "redirect:/admin";
    }


    @PatchMapping("/admin/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.update(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/admin/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
