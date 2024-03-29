package com.ues21.checklist.service;

import com.ues21.checklist.controller.api.CreateTareaRequest;
import com.ues21.checklist.model.Lista;
import com.ues21.checklist.model.Tarea;
import com.ues21.checklist.repository.TareaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TareaService {

    private TareaRepository tareaRepository;
    private ListaService listaService;

    public TareaService(TareaRepository tareaRepository, ListaService listaService) {
        this.tareaRepository = tareaRepository;
        this.listaService = listaService;
    }

    public Tarea create(CreateTareaRequest request) {
        Tarea tarea = toTarea(request);
        Tarea tareaPersistida = tareaRepository.save(tarea);
        Lista lista = listaService.read(request.getIdLista());

        if (lista.getTareas() != null) {
            lista.getTareas().add(tareaPersistida);
        } else {
            lista.setTareas(new ArrayList<>());
            lista.getTareas().add(tareaPersistida);
        }

        listaService.update(lista);
        return tarea;
    }

    public Tarea read(Integer id) {
        return tareaRepository.findById(id).get();
    }

    public Tarea update(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public void delete(Integer id) {
        Tarea tarea = read(id);
        tareaRepository.delete(tarea);
    }


    private Tarea toTarea(CreateTareaRequest request) {
        Tarea tarea = new Tarea();
        tarea.setDescripcion(request.getDescripcion());
        return tarea;
    }
}
