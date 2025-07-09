package com.dominio.springradio1.controller;


import com.dominio.springradio1.model.BlocoComercial;
import com.dominio.springradio1.model.Programa;
import com.dominio.springradio1.service.ProgramaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/programa")
public class ProgramaController {

    private static final ProgramaService programaService = new ProgramaService();




    @GetMapping({"", "/"})
    public String programa(Model model) throws SQLException, ClassNotFoundException {
        System.out.println("GET programa...");
        model.addAttribute("programas", programaService.getAllProgramas());
        return "pages/programas";
    }


    @GetMapping("/adicionar")
    public String adicionar(Model model) throws SQLException, ClassNotFoundException {
        System.out.println("GET adicionar...");
        model.addAttribute("programa", new Programa());
        return "pages/cadastrarPrograma";
    }


    @GetMapping("/detalhes/{id}")
    public String detalhes(Model model, @PathVariable int id) throws SQLException, ClassNotFoundException {
        System.out.println("GET Detalhes...");
        model.addAttribute("programa", programaService.getPrograma(id));
        return "pages/programasDetalhes";
    }


    @PostMapping("/adicionar")
    public String adicionar(Programa programa) {
        if(programaService.addPrograma(programa)){
            return "redirect:/dashboard";
        } else{
            return "redirect:/error";
        }
    }








}
