package com.dominio.springradio1.controller;


import com.dominio.springradio1.model.Vendedor;
import com.dominio.springradio1.service.ClienteService;
import com.dominio.springradio1.service.VendedorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vendedor")
public class VendedorController{

    private static final VendedorService vendedor = new VendedorService();


    @GetMapping({"", "/"})
    public String vendedores(Model model){

        model.addAttribute("vendedores", vendedor.listarVendedores());

        return "pages/vendedores";

    }


}
