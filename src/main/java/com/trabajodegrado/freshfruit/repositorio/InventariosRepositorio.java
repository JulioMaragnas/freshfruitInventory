package com.trabajodegrado.freshfruit.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.trabajodegrado.freshfruit.modelos.Inventarios;
import com.trabajodegrado.freshfruit.modelos.Productos;


@Repository
public interface InventariosRepositorio extends JpaRepository<Inventarios, Integer>, PagingAndSortingRepository<Inventarios, Integer>{
	public Optional<Inventarios> findByIdproducto(Integer idproducto);
	
	
	
//	@Query("FROM trabajodegrado.Inventarios WHERE existencias = :existencias")
//	List<Inventarios> findByExistencias(Integer existencias);
	
 }

