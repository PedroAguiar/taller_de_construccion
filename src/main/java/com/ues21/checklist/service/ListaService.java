package com.ues21.checklist.service;

import com.ues21.checklist.controller.api.CreateListaRequest;
import com.ues21.checklist.controller.api.UpdateListaRequest;
import com.ues21.checklist.model.Lista;
import com.ues21.checklist.model.Usuario;
import com.ues21.checklist.repository.ListaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class ListaService {

    private ListaRepository listaRepository;
    private UsuarioService usuarioService;

    public ListaService(ListaRepository listaRepository, UsuarioService usuarioService) {

        this.listaRepository = listaRepository;
        this.usuarioService = usuarioService;
    }

    @Transactional
    public Lista create(CreateListaRequest request) {
        Lista lista = toLista(request);

        Usuario usuario = usuarioService.read(request.getIdUsuario());
        lista.setUsuario(usuario);

        return listaRepository.save(lista);
    }

    public Lista read(Integer id) {
        return listaRepository.getOne(id);
    }

    @Transactional
    public Lista update(UpdateListaRequest request) {
        Lista lista = read(request.getListaId());

        lista.setTitulo(request.getTitulo());
        lista.setEstado(request.getEstado());

        return listaRepository.save(lista);
    }

    @Transactional
    public void delete(Integer id) {
        Lista lista = read(id);
        listaRepository.delete(lista);
    }

    private Lista toLista(CreateListaRequest request) {
        Lista lista = new Lista();
        lista.setTitulo(request.getTitulo());
        lista.setTareas(new ArrayList<>());
        return lista;
    }
}
