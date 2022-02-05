package com.trabajodegrado.freshfruit.negocio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabajodegrado.freshfruit.excepciones.ConflictException;
import com.trabajodegrado.freshfruit.modelos.Estados;
import com.trabajodegrado.freshfruit.repositorio.EstadosRepositorio;

	@Service
	public class EstadosNegocio {
		
		@Autowired
		private EstadosRepositorio estadosRepositorio;
		
		public List<Estados> obtenerLista() {
			return estadosRepositorio.findAll();	    	
		}
		
		
		public Estados obtenerEstado(Integer id) {
			Optional<Estados> estado = estadosRepositorio.findById(id);	
			if(estado.isPresent()) {
				return estado.get();
			}else {
				throw new ConflictException("Estado no encontrado");
			}

		}
		
		public Estados obtenerEstadoPorCodigo(String codigo) {
			Optional<Estados> estado = estadosRepositorio.findByCodigo(codigo);	
			if(estado.isPresent()) {
				return estado.get();
			}else {
				throw new ConflictException("Estado no encontrado");
			}

		}
}
