package com.trabajodegrado.freshfruit.negocio;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.trabajodegrado.freshfruit.configuracion.ContextoSesion;
import com.trabajodegrado.freshfruit.excepciones.ConflictException;
import com.trabajodegrado.freshfruit.excepciones.DatosInvalidosExcepcion;
import com.trabajodegrado.freshfruit.modelos.Inventarios;
import com.trabajodegrado.freshfruit.modelos.Movimientosinventario;
import com.trabajodegrado.freshfruit.modelos.dto.InventariosDTO;
import com.trabajodegrado.freshfruit.modelos.dto.PaginacionDTO;
import com.trabajodegrado.freshfruit.modelos.dto.RespuestaPaginada;
import com.trabajodegrado.freshfruit.repositorio.InventariosRepositorio;
import com.trabajodegrado.freshfruit.repositorio.MovimientosinventarioRepositorio;
import com.trabajodegrado.freshfruit.utilidades.Constantes;

	@Service
	public class InventariosNegocio {
		
		@Autowired
		private InventariosRepositorio inventarioRepositorio;
		
		@Autowired
		private MovimientosinventarioRepositorio movimientosinventarioRepositorio;
		
		
		public List<Inventarios> obtenerLista() {
			return inventarioRepositorio.findAll();
		}
		
		public RespuestaPaginada<Inventarios> obtenerListaPaginada(PaginacionDTO paginacion) {
			
			RespuestaPaginada<Inventarios> respuestaPaginada = new RespuestaPaginada<>();
			Page<Inventarios> consultaProductos = inventarioRepositorio.findAll(PageRequest.of(paginacion.getPaginaActual(), paginacion.getPaginacion()));
			
	        respuestaPaginada.setLista(consultaProductos.getContent());
	        respuestaPaginada.setTotal(consultaProductos.getTotalElements());
	        respuestaPaginada.setTotalPaginas(consultaProductos.getTotalPages());
	        
	        return respuestaPaginada;
			    	
		}
		
		public Inventarios obtener(Integer id) {
			Optional<Inventarios> inventario = inventarioRepositorio.findById(id);	    
			
			if(inventario.isPresent()) {
				return inventario.get();
			}else {
				throw new ConflictException("Inventario no encontrado");
			}

		}
		
		@Transactional
		public String insertarInventario(InventariosDTO inventario) {
			
			if(!validarInventario(inventario)) {
				throw new DatosInvalidosExcepcion("Diligencie todos los datos correctamente");
			}
			
			//Valido si ya existe un registro de inventario para este producto
			Optional<Inventarios> inventarioExistente = inventarioRepositorio.findByIdproducto(inventario.getIdproducto());
			if(inventarioExistente.isPresent()) {
				//Se lo adiciono y creo el movimiento
				inventarioExistente.get().setExistencias(inventarioExistente.get().getExistencias() + inventario.getExistencias());
				
				inventarioRepositorio.save(inventarioExistente.get());
				
				insertarMovimiento(inventarioExistente.get().getId(), inventario.getExistencias(), Constantes.TIPOS_MOVIMIENTO_INVENTARIO.ADICION, inventario.getIdMotivo());
				
				return "Se adiciona al registro de inventario ya existe número " + inventarioExistente.get().getId() 
						+ ". Existencias actuales : " + inventarioExistente.get().getExistencias();
				
			}else{
				//Creo un registro de inventario nuevo
				Inventarios nuevoInventario = new Inventarios();
				nuevoInventario.setExistencias(inventario.getExistencias());
				nuevoInventario.setIdproducto(inventario.getIdproducto());
				Inventarios inventarioNuevo = inventarioRepositorio.save(nuevoInventario);
				
				insertarMovimiento(inventarioNuevo.getId(), inventario.getExistencias(), Constantes.TIPOS_MOVIMIENTO_INVENTARIO.CREACION, inventario.getIdMotivo());
				
				return "Se crea nuevo registro de inventario. ";
				
			}
			
		}
		
		@Transactional
		public String disminuirInventario(InventariosDTO inventario) {
			Optional<Inventarios> inventarioExistente = inventarioRepositorio.findById(inventario.getId());
			if(!inventarioExistente.isPresent()) {
				throw new ConflictException("Inventario no encontrado");
			}
			
			if(inventario.getExistencias()>inventarioExistente.get().getExistencias()) {
				throw new ConflictException("La cantidad requerida no se encuentra disponible.");
			}
			inventarioExistente.get().setExistencias(inventarioExistente.get().getExistencias()-inventario.getExistencias());
			inventarioRepositorio.save(inventarioExistente.get());
			insertarMovimiento(inventarioExistente.get().getId(), inventario.getExistencias(), Constantes.TIPOS_MOVIMIENTO_INVENTARIO.DISMINUCION, inventario.getIdMotivo());

			
			return "Inventario actualizado correctamente. Nuevas existencias: " + inventarioExistente.get().getExistencias();
		}
		
		
		
		
		public void insertarMovimiento(Integer idInventario, Integer cantidad, String tipoMovimiento, Integer idMotivo) {
			Movimientosinventario movimiento = new Movimientosinventario();
			movimiento.setCantidadmovimiento(cantidad);
			movimiento.setFecha(new Date());
			movimiento.setIdinventario(idInventario);
			movimiento.setIdmotivo(idMotivo);
			movimiento.setTipomovimiento(tipoMovimiento);
			movimiento.setIdusuario(ContextoSesion.getUsuarioSesion()); //Sacarlo del token 
			
			movimientosinventarioRepositorio.save(movimiento);
			
			
		}
		
		public RespuestaPaginada<Movimientosinventario> obenterListaMovimientos(PaginacionDTO paginacion){
			
			RespuestaPaginada<Movimientosinventario> respuestaPaginada = new RespuestaPaginada<>();
			Page<Movimientosinventario> consultaProductos = movimientosinventarioRepositorio.findAll(PageRequest.of(paginacion.getPaginaActual(), paginacion.getPaginacion()));
			
	        respuestaPaginada.setLista(consultaProductos.getContent());
	        respuestaPaginada.setTotal(consultaProductos.getTotalElements());
	        respuestaPaginada.setTotalPaginas(consultaProductos.getTotalPages());
	        
	        return respuestaPaginada;
		}
		
		
		public List<Movimientosinventario> obtenerListaMovimientosInventario(Integer idInventario){
			return movimientosinventarioRepositorio.findByIdinventario(idInventario);
		}
		
		private boolean validarInventario(InventariosDTO inventario) {
			if(inventario.getExistencias() == null ||
					inventario.getExistencias().equals(0) ||
					inventario.getIdproducto() == null ||
					inventario.getIdproducto().equals(0)  
					) {
				return false;
			}
			return true;
		}
		
}
