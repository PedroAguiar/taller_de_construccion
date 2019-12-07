package com.ues21.checklist.controller;

import com.ues21.checklist.controller.api.CreateListaRequest;
import com.ues21.checklist.controller.api.CreateTareaRequest;
import com.ues21.checklist.model.Lista;
import com.ues21.checklist.model.Tarea;
import com.ues21.checklist.model.Usuario;
import com.ues21.checklist.service.ListaService;
import com.ues21.checklist.service.TareaService;
import com.ues21.checklist.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TasksListController {

    private UsuarioService usuarioService;
    private ListaService listaService;
    private TareaService tareaService;

    TasksListController(UsuarioService usuarioService, ListaService listaService, TareaService tareaService) {
        this.usuarioService = usuarioService;
        this.listaService = listaService;
        this.tareaService = tareaService;
    }

    @GetMapping("/taskslist")
    public String tasksLists(@RequestParam(name="userId", required=false, defaultValue="1") Integer userId, Model model) {
        Usuario usuario = usuarioService.read(userId);
        List<Lista> listas = listaService.getAllForUser(usuario);
        model.addAttribute("usuario", usuario);
        model.addAttribute("listas", listas);
        if (listas.isEmpty()) {
            Lista lista = new Lista();
            lista.setTitulo("");
            List<Tarea> tareas = new ArrayList<>();
            lista.setTareas(tareas);
            listas.add(lista);
            model.addAttribute("listas", listas);
            model.addAttribute("usuario", usuario);
            model.addAttribute("lista", lista);
            model.addAttribute("crearLista", new CreateListaRequest());
            model.addAttribute("crearTask", new CreateTareaRequest());
            listaService.create(lista);
            return "taskslist";
        }
        return "redirect:/taskslist/" + listas.get(0).getId();
    }

    @GetMapping("/taskslist/{id}")
    public String taskList(@RequestParam(name="userId", required=false, defaultValue="1") Integer userId, @PathVariable(name = "id") Integer id, Model model) {
        Usuario usuario = usuarioService.read(userId);
        List<Lista> listas = listaService.getAllForUser(usuario);
        model.addAttribute("usuario", usuario);
        model.addAttribute("listas", listas);

        Lista lista = listaService.read(id);
        model.addAttribute("lista", lista);
        model.addAttribute("crearLista", new Lista());
        model.addAttribute("crearTask", new Tarea());
        return "taskslist";
    }

    @PostMapping("/taskslist/{id}/update")
    public String update(@PathVariable(name = "id") Integer id, @ModelAttribute Lista lista, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "taskslist";
        }
        Lista listaVieja = listaService.read(lista.getId());

        if (listaVieja.getTareas() != null && lista.getTareas() != null) {
            for (Tarea tarea : listaVieja.getTareas()) {
                lista.getTareas().forEach(t -> {
                   if (t.getId() == tarea.getId()) {
                       t.setEstado(tarea.getEstado());
                       t.setDescripcion(tarea.getDescripcion());
                   }
                });
            }
        }

        listaVieja.setTitulo(lista.getTitulo());
        listaService.update(listaVieja);
        return "redirect:/taskslist/" + id;
    }

    @PostMapping("/taskslist/new")
    public String saveChecklist(@ModelAttribute Lista lista, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "taskslist";
        }
        Lista listaPersistida = listaService.create(lista);
        return "redirect:/taskslist/" + listaPersistida.getId();
    }

    @GetMapping("/taskslist/{id}/delete")
    public String deleteChecklist(@PathVariable(name = "id") Integer id) {
        listaService.delete(id);
        return "redirect:/taskslist";
    }

    @PostMapping("/taskslist/{id}/tasks/new")
    public String saveTask(@ModelAttribute CreateTareaRequest request, @PathVariable(value = "id") Integer idLista,
                           BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "taskslist";
        }
        request.setIdLista(idLista);
        tareaService.create(request);
        return "redirect:/taskslist/" + request.getIdLista();
    }


    @GetMapping("/taskslist/{id}/completar")
    public String completeChecklist(@PathVariable(name = "id") Integer id) {
        Lista lista = listaService.read(id);

        if (lista.getTareas() != null) {
            for (Tarea tarea : lista.getTareas()) {
                tarea.setEstado(true);
            }
        }

        lista.setEstado(true);
        listaService.update(lista);
        return "redirect:/taskslist/" + id;
    }
}
