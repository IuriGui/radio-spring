package com.dominio.springradio1.controller;

import com.dominio.springradio1.model.BlocoComercial;
import com.dominio.springradio1.service.BlocoComercialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
public class BlocoComercialController {



    @GetMapping( "/blocoComercial/add/{id}")
    public String addBlocoGet(Model model, @PathVariable int id) throws SQLException, ClassNotFoundException {

        BlocoComercial b = new BlocoComercial();
        b.setIdPrograma(id);

        model.addAttribute("bloco", b);
        return "pages/cadastrarBlocoComercial";

    }

    @PostMapping("/blocoComercial/add/{id}")
    public String addBloco(@ModelAttribute BlocoComercial bloco, @PathVariable int id) throws SQLException, ClassNotFoundException {
        BlocoComercialService service = new BlocoComercialService();
        boolean sucesso = service.addBlocoComercial(bloco, id);
        if (sucesso) {
            return "redirect:/programa/detalhes/" + id;
        } else {
            return "pages/cadastrarBlocoComercial";
        }
    }

}
