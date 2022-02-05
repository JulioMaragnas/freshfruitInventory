package com.trabajodegrado.freshfruit.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.trabajodegrado.freshfruit.modelos.Productos;

@Repository
public interface ProductosRepositorio extends JpaRepository<Productos, Integer>, PagingAndSortingRepository<Productos, Integer>{
      public Optional<Productos> findByNombre(String nombre);
 }