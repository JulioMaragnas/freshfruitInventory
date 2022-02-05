package com.trabajodegrado.freshfruit.negocio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.trabajodegrado.freshfruit.excepciones.ConflictException;
import com.trabajodegrado.freshfruit.excepciones.DatosInvalidosExcepcion;
import com.trabajodegrado.freshfruit.modelos.Motivos;
import com.trabajodegrado.freshfruit.repositorio.MotivosRepositorio;



@Service
public class MotivosNegocio {
	
	@Autowired
	private MotivosRepositorio motivosRepositorio;
	
	public List<Motivos> obtenerLista() {
		return motivosRepositorio.findAll();	    	
	}
	
	public List<Motivos> obtenerListaSelect() {
		return motivosRepositorio.findByEstado(1);	    	
	}
	
	public Motivos obtenerMotivo(Integer id) {
		Optional<Motivos> motivo = motivosRepositorio.findById(id);	
		if(motivo.isPresent()) {
			return motivo.get();
		}else {
			throw new ConflictException("Motivo no encontrado");
		}

	}
	
	//Si el motivo no está activo que no lo pueda editar
	public String actualizarMotivo(Motivos motivo){
		
		if(!validarMotivo(motivo)) {
			throw new DatosInvalidosExcepcion("Diligencie todos los datos correctamente");
		}
		
		List<Motivos> motivoExistente = motivosRepositorio.findByDescripcion(motivo.getDescripcion());
		if(!motivoExistente.isEmpty()) {
			if(motivoExistente.get(0).getId() != motivo.getId()) {
				throw new ConflictException("Ya existe un motivo con la misma descripción");
			}
		}
		
		motivo.setEstado(1);
		Motivos mot = motivosRepositorio.save(motivo);
		if(mot.getId() == null) {
            throw new ConflictException("No se encontró el motivo a actualizar");

		}else{
			return "Motivo actualizado correctamente";
		}
		
	}
	@Modifying
	public String crearMotivo(Motivos motivo){
		
		if(!validarMotivo(motivo)) {
			throw new DatosInvalidosExcepcion("Diligencie todos los datos correctamente");
		}
		List<Motivos> motivoExistente = motivosRepositorio.findByDescripcion(motivo.getDescripcion());
		if(!motivoExistente.isEmpty()){
			 throw new ConflictException("Ya existe un motivo con la misma descripción");
		}
		
		motivo.setEstado(1);
		motivosRepositorio.save(motivo);
		
		return "Motivo creado correctamente";
		
	}
	
	private boolean validarMotivo(Motivos motivo) {
		if(motivo.getDescripcion().isEmpty()) {
					return false;
				}
		return true;
		}
	
	
	public String inactivarMotivo(Integer id) {
		Optional<Motivos> motivo = motivosRepositorio.findById(id);
		if(!motivo.isPresent()){
			 throw new ConflictException("No se encontró el motivo a inactivar");

		}else {
			motivo.get().setEstado(0);
			motivosRepositorio.save(motivo.get());
			return "Motivo inactivado correctamente";
		}
	}
	
	

}
