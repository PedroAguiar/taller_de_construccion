package com.ues21.checklist.service;

import com.ues21.checklist.controller.api.CreateTareaRequest;
import com.ues21.checklist.controller.api.UpdateTareaRequest;
import com.ues21.checklist.model.Tarea;
import com.ues21.checklist.repository.TareaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TareaService {

    private TareaRepository tareaRepository;

    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    @Transactional
    public Tarea create(CreateTareaRequest request) {
        Tarea tarea = toTarea(request);
        return tareaRepository.save(tarea);
    }

    public Tarea read(Integer id) {
        return tareaRepository.getOne(id);
    }

    public List<Tarea> readAll(List<Integer> ids) {
        return tareaRepository.findAllById(ids);
    }

    @Transactional
    public Tarea update(UpdateTareaRequest request) {
        Tarea tarea = read(request.getIdTarea());

        tarea.setDescripcion(request.getDescripcion());
        tarea.setEstado(request.getEstado());

        return tareaRepository.save(tarea);
    }

    @Transactional
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
