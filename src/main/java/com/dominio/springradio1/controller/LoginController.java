package com.dominio.springradio1.controller;

import com.dominio.springradio1.model.User;
import com.dominio.springradio1.service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String loginForm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @PostMapping
    public String loginSubmit(HttpSession session, Model model, @ModelAttribute("user") User user) {
        User usuarioAutenticado = new LoginService().autenticar(user.getEmail(), user.getSenha());

        if (usuarioAutenticado != null) {
            session.setAttribute("user", usuarioAutenticado);
            return "redirect:/dashboard/";
        } else {
            model.addAttribute("msg", "Login ou senha incorreto!");
            return "login";
        }
    }


}

