package com.ues21.checklist.service;

import com.ues21.checklist.model.Usuario;
import com.ues21.checklist.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario create(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario read(Integer id) {
        return usuarioRepository.findById(id).get();
    }

    public Usuario update(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void delete(Integer id) {
        Usuario usuario = read(id);
        usuarioRepository.delete(usuario);
    }

}
