package com.ues21.checklist.controller;

import com.ues21.checklist.model.Lista;
import com.ues21.checklist.model.Tarea;
import com.ues21.checklist.model.Usuario;
import com.ues21.checklist.repository.ListaRepository;
import com.ues21.checklist.repository.UsuarioRepository;
import com.ues21.checklist.service.ListaService;
import com.ues21.checklist.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class TasksListController {

    private UsuarioService usuarioService;
    private ListaService listaService;

    TasksListController(UsuarioService usuarioService, ListaService listaService) {
        this.usuarioService = usuarioService;
        this.listaService = listaService;
    }

    @GetMapping("/taskslist")
    public String greeting(@RequestParam(name="userId", required=false, defaultValue="1") Integer userId, Model model) {
        Usuario usuario = usuarioService.read(userId);
        System.out.println("Usuario: " + usuario.getId());
        List<Lista> listas = listaService.getAllForUser(usuario);
        model.addAttribute("usuario", usuario);
        model.addAttribute("listas", listas);
        return "taskslist";
    }

    @PostMapping("/taskslist")
    public String save(@ModelAttribute Lista lista, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "taskslist";
        }

        return "taskslist";
    }
}
