package com.fisherman.controller;

import com.fisherman.model.User;
import com.fisherman.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/addUser")
    public String addUser(User user) {
        if (userService.addUser(user) == 1) {
            return "registrationSuccess";
        } else {
            return "registrationFail";
        }
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam(name = "userName") String userName,
                        @RequestParam(name = "password") String password, Model model) {
        List<User> users = userService.selectUserByName(userName);
        if (users != null && users.size() != 0) {
            for (User user : users) {
                if (user.getPassword().equals(password)) {
                    model.addAttribute("name", userName);
                    return "loginSuccess";
                }
            }
        }
        return "index";
    }

    @GetMapping(value = "/registration")
    public String registry() {
        return "registry";
    }
}
