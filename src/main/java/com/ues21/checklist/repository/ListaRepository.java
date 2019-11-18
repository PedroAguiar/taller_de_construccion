package com.ues21.checklist.repository;

import com.ues21.checklist.model.Lista;
import com.ues21.checklist.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface ListaRepository extends JpaRepository<Lista, Integer>, QueryByExampleExecutor<Lista> {

    List<Lista> findAllByUsuario(Usuario usuario);

}
