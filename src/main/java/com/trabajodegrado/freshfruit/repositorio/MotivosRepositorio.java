package com.trabajodegrado.freshfruit.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trabajodegrado.freshfruit.modelos.Motivos;


@Repository
public interface MotivosRepositorio extends JpaRepository<Motivos, Integer>{
    public List<Motivos> findByDescripcion(String descripcion);
    public List<Motivos> findByEstado(Integer estado);
 }