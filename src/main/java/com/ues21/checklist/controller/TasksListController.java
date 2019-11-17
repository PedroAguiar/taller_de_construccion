package com.ues21.checklist.controller;

import com.ues21.checklist.model.Lista;
import com.ues21.checklist.model.Tarea;
import com.ues21.checklist.model.Usuario;
import com.ues21.checklist.repository.ListaRepository;
import com.ues21.checklist.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class TasksListController {

    @Autowired
    public UsuarioRepository usuarioRepository;
    @Autowired
    public ListaRepository listaRepository;

    @GetMapping("/taskslist")
    public String greeting(@RequestParam(name="userId", required=false, defaultValue="1") Integer userId, Model model) {
        Optional<Usuario> usuario = usuarioRepository.findById(userId);
        Lista listaExample = new Lista();
        listaExample.setUsuario(usuario.get());
        System.out.println("Usuario: " + listaExample.getUsuario());
        List<Lista> listLista = listaRepository.findByUsuario(usuario.get());
        model.addAttribute("usuario", usuario);
        model.addAttribute("lista", listLista);
        return "taskslist";
    }
}
