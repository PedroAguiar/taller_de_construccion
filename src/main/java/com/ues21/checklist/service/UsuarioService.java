package com.ues21.checklist.service;

import com.ues21.checklist.controller.api.CreateUsuarioRequest;
import com.ues21.checklist.controller.api.UpdateUsuarioRequest;
import com.ues21.checklist.model.Usuario;
import com.ues21.checklist.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Usuario create(CreateUsuarioRequest request) {
        Usuario usuario = toUsuario(request);
        return usuarioRepository.save(usuario);
    }

    public Usuario read(Integer id) {
        return usuarioRepository.getOne(id);
    }

    @Transactional
    public Usuario update(UpdateUsuarioRequest request) {
        Usuario usuario = read(request.getIdUsuario());
        usuario.setUserName(request.getUserName());
        usuario.setPassword(request.getPassword());
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public void delete(Integer id) {
        Usuario usuario = read(id);
        usuarioRepository.delete(usuario);
    }

    private Usuario toUsuario(CreateUsuarioRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setApellido(request.getApellido());
        usuario.setUserName(request.getUserName());
        usuario.setPassword(request.getPassword());
        return usuario;
    }
}
