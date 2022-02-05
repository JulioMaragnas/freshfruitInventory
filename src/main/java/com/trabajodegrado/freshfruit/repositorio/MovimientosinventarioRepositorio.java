package com.trabajodegrado.freshfruit.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.trabajodegrado.freshfruit.modelos.Movimientosinventario;
import com.trabajodegrado.freshfruit.modelos.Productos;


@Repository
public interface MovimientosinventarioRepositorio extends JpaRepository<Movimientosinventario, Integer>, PagingAndSortingRepository<Movimientosinventario, Integer>{
      public List<Movimientosinventario> findByIdinventario(Integer idinventario);
 }