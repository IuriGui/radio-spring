package com.dominio.springradio1.controller;


import com.dominio.springradio1.model.Contrato;
import com.dominio.springradio1.service.ContratoService;
import com.dominio.springradio1.service.VendedorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/contratos")
public class ContratoController {

    private static final ContratoService contratoService = new ContratoService();


    @GetMapping({"", "/"})
    public String contratos(Model model) {

        model.addAttribute("contratos", contratoService.listarContratos());

        return "pages/contratos";
    }


    @GetMapping("/cadastrar")
    public String cadastrarGet(Model model) {
        model.addAttribute("model", "cadastrar");
        model.addAttribute("clientela", contratoService.listarClientes());
        model.addAttribute("vendedores", contratoService.listarVendedores());

        return "pages/cadastrarContrato";
    }

    @PostMapping("/cadastrar")
    public String cadastrarPost(@ModelAttribute("contrato") Contrato contrato, RedirectAttributes red) {

        System.out.println("POST Cadastrar...");
        System.out.println("Data fim " + contrato.getDataFim());
        System.out.println("Data Inicio " + contrato.getDataInicio());
        System.out.println("Valor " + contrato.getValor());
        System.out.println("Id cliente " + contrato.getId_cliente() );
        System.out.println("Id vendedor " + contrato.getId_vendedor());

        if(contratoService.criarContrato(contrato)){
            red.addFlashAttribute("msgSucesso", "Contrato cadastrado com sucesso!");
            new VendedorService().atualizarTotalVendido(contrato.getValor(), contrato.getId_vendedor());
        } else{
            red.addFlashAttribute("msgErro", "Erro ao cadastrar Contrato! Tente novamente mais tarde!");
        }

        return "redirect:/contratos";
    }


}
