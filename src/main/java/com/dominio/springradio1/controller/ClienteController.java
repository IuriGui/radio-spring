package com.dominio.springradio1.controller;


import com.dominio.springradio1.model.Cliente;
import com.dominio.springradio1.model.Contato;
import com.dominio.springradio1.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    private static final ClienteService clienteService = new ClienteService();

    @GetMapping({"", "/"})
    public String clientes(Model model) throws SQLException, ClassNotFoundException {
            model.addAttribute("clientes", clienteService.listarClientesComContato());
            return "pages/clientes";
    }


    @GetMapping("/cadastrar")
    public String cadastrar(Model model) {
        model.addAttribute("modo", "cadastrar");
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("contato", new Contato());
        return "pages/cadastrarCliente";
    }

    @PostMapping("/cadastrar")
    public String cadastrarCliente(@ModelAttribute("cliente") Cliente cliente,  @ModelAttribute("contato") Contato contato, RedirectAttributes red) throws SQLException, ClassNotFoundException {

        System.out.println("Cadastrando Cliente...");
        System.out.println("Nome do cliente: " + cliente.getNomeCliente());
        System.out.println("Nome do contato " + contato.getNomeContato());

        if(clienteService.cadastrarClienteComContato(cliente, contato)){
            red.addFlashAttribute("msgSucesso", "Cliente cadastrado com sucesso!");
        } else{
            red.addFlashAttribute("msg", "Erro ao cadastrar cliente!");
        }
        return "redirect:/cliente";
    }


    @GetMapping("/apagar/{clienteId}")
    public String clientes(@PathVariable int clienteId, Model model, RedirectAttributes redirectAttributes) throws SQLException, ClassNotFoundException {

        model.addAttribute("cliente", clienteService.listarClientePeloId(clienteId));
        model.addAttribute("modo", "apagar");


        return "pages/cadastrarCliente";
    }

    @PostMapping("/apagar/{id}")
    public String ApagarCliente(@ModelAttribute("cliente") Cliente cliente, RedirectAttributes red) throws SQLException, ClassNotFoundException {

        String ret = clienteService.deleteCliente(cliente.getId());
        System.out.println(cliente.getId());
        red.addFlashAttribute("msgSucesso", ret);
        return "redirect:/cliente";
    }

    @GetMapping("/editar/{clienteId}")
    public String editarCliente(@PathVariable int clienteId, Model model, RedirectAttributes redirectAttributes) throws SQLException, ClassNotFoundException {

        model.addAttribute("cliente", clienteService.listarClientePeloId(clienteId));
        model.addAttribute("modo", "editar");

        System.out.println("get com cliente id tem que mandar pro cadastrar cliente");

        return "pages/cadastrarCliente";
    }

    @PostMapping("/editar/{id}")
    public String editarCliente(@ModelAttribute("cliente") Cliente cliente, RedirectAttributes red) throws SQLException, ClassNotFoundException {

        boolean ret = clienteService.updateCliente(cliente);
        if(ret){
            red.addFlashAttribute("msgSucesso", "Cliente editado com sucesso!");
        } else {
            red.addFlashAttribute("msg", "Erro ao editar cliente!");
        }
        return "redirect:/cliente";
    }


}
