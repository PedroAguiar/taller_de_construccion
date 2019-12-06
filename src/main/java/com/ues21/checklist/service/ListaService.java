package com.ues21.checklist.service;

import com.ues21.checklist.model.Lista;
import com.ues21.checklist.model.Usuario;
import com.ues21.checklist.repository.ListaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ListaService {

    private ListaRepository listaRepository;
    private UsuarioService usuarioService;

    public ListaService(ListaRepository listaRepository, UsuarioService usuarioService) {

        this.listaRepository = listaRepository;
        this.usuarioService = usuarioService;
    }

    public Lista create(Lista lista) {
        Usuario usuario = usuarioService.read(1);
        lista.setUsuario(usuario);
        return listaRepository.save(lista);
    }

    public Lista read(Integer id) {
        return listaRepository.findById(id).get();
    }

    public Lista update(Lista lista) {
        return listaRepository.save(lista);
    }

    public void delete(Integer id) {
        Lista lista = read(id);
        listaRepository.delete(lista);
    }

    public List<Lista> getAllForUser(Usuario usuario) {
        return listaRepository.findAllByUsuario(usuario);
    }
}
