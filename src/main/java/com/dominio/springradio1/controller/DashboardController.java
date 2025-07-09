package com.dominio.springradio1.controller;


import com.dominio.springradio1.model.User;
import com.dominio.springradio1.service.ClienteService;
import com.dominio.springradio1.service.ContratoService;
import com.dominio.springradio1.service.VendedorService;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeFeature;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private static final ClienteService c = new ClienteService();
    private static final VendedorService vs = new VendedorService();
    public static final ContratoService cs = new ContratoService();

    @GetMapping({"", "/"})
    public String dashboard(HttpSession session, Model model) {


        LocalDate hoje = LocalDate.now();
        String nomeMes = hoje.getMonth().getDisplayName(TextStyle.FULL, new Locale("pt", "BR"));

        model.addAttribute("mes", nomeMes);
        model.addAttribute("totalVendido", cs.somaDosContratos(hoje.getMonth()));
        model.addAttribute("qtdContratos", cs.countContratos());
        model.addAttribute("clienteNovosMes", c.countClientesNovosMes());
        model.addAttribute("clientOrdenado",  c.listarClienteOrdenado());
        System.out.println("GET Dashboard...");

        return "pages/dashboard";
    }



}
