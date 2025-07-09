package com.dominio.springradio1.controller;


import com.dominio.springradio1.model.User;
import com.dominio.springradio1.model.Vendedor;
import com.dominio.springradio1.service.CadastroUserService;
import com.dominio.springradio1.service.VendedorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/signup")
public class CadastrarUsuarioController {


//    linkarUser&Vendedor

    private final CadastroUserService cadastroUserService = new CadastroUserService();
    private final VendedorService vendedorService = new VendedorService();

    @GetMapping({"", "/"})
    public String cadastrarUsuario(Model model) {
        model.addAttribute("modo", "criarUsuario");
        return "signup";
    }


    @PostMapping("/cadastroUsuario")
    public String cadastrarUsuario(Model model, @ModelAttribute("user") User user) {

        int userId = cadastroUserService.criarUser(user.getEmail(), user.getSenha(), false);

        if(userId > 0){
            Vendedor v = new Vendedor();
            v.setIdUser(userId);
            System.out.println("Cadastro usuario...");
            System.out.println(v);
            model.addAttribute("vendedor", v);
            model.addAttribute("modo", "linkarUserVendedor");
            model.addAttribute("msg", "Usuário criado, agora cadastre os dados do vendedor");
        } else {
            model.addAttribute("msg", "Erro ao criar usuário");
        }
        return "signup";
    }

    @PostMapping("/linkarUserVendedor")
    public String linkarUserVendedor(@ModelAttribute("vendedor") Vendedor vendedor, Model model, RedirectAttributes red) {

        System.out.println("LinkarUserVendededor...");
        System.out.println(vendedor);

        boolean vend = new VendedorService().criarVendedor(vendedor);
        int id = ((int) vendedor.getIdUser());
        System.out.println(id);


        if(vend){
            boolean usuarioAtiv = cadastroUserService.ativarUsuario(id);
            if(usuarioAtiv){
                red.addFlashAttribute("msg", "Cadastro realizado com sucesso! Realize seu login!");
            } else{
                red.addFlashAttribute("msg", "Erro! ");
            }
        }
        return "redirect:/login";
    }







}
