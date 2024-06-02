package com.kr.demo.controllers;


import ch.qos.logback.core.model.Model;
import com.kr.demo.models.User;
import com.kr.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class UserController {

@Autowired
    private  UserRepository userRepository;

    @GetMapping("/inf")
    public String inf(Model model) {
      return "inf";
    }

        @GetMapping("/")
    public String logIn(Model model) {
//        model.addAttribute("name", name);
        return "login";
    }
    @PostMapping("/")
  public String logIn(@RequestParam String email, @RequestParam String password, Model model) {
        User user = userRepository.findByEmailAndPassword(email, password);
        if (user != null) {
            // Пользователь найден, редирект на home.html
            return "redirect:/home";
        } else {
            // Пользователь не найден, возвращаемся на login.html с сообщением об ошибке

            return "login";
        }
    }

    @GetMapping("/registr")
    public String registr(Model model) {
        return "registr";
    }

    @PostMapping("/registr")
    public String newUser(@RequestParam String email,@RequestParam String name, @RequestParam String password,Model model) {
        User user=new User(email, name, password);
        userRepository.save(user);
        return "redirect: ";
    }

}
