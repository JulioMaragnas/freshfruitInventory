package com.trabajodegrado.freshfruit.negocio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import com.trabajodegrado.freshfruit.excepciones.ConflictException;
import com.trabajodegrado.freshfruit.excepciones.DatosInvalidosExcepcion;
import com.trabajodegrado.freshfruit.modelos.Productos;
import com.trabajodegrado.freshfruit.modelos.dto.PaginacionDTO;
import com.trabajodegrado.freshfruit.modelos.dto.RespuestaPaginada;
import com.trabajodegrado.freshfruit.repositorio.ProductosRepositorio;

@Service
public class ProductosNegocio {
	
	@Autowired
	private ProductosRepositorio productosRepositorio;
	
//	public List<Productos> obtenerLista() {
//		return productosRepositorio.findAll();	    	
//	}
	
	public RespuestaPaginada<Productos> obtenerLista(PaginacionDTO paginacion) {
		
		RespuestaPaginada<Productos> respuestaPaginada = new RespuestaPaginada<>();
		
		Page<Productos> consultaProductos = productosRepositorio.findAll(PageRequest.of(paginacion.getPaginaActual(), paginacion.getPaginacion()));
		
        respuestaPaginada.setLista(consultaProductos.getContent());
        respuestaPaginada.setTotal(consultaProductos.getTotalElements());
        respuestaPaginada.setTotalPaginas(consultaProductos.getTotalPages());
        
        return respuestaPaginada;
		    	
	}
	
	public Productos obtenerProducto(Integer id) {
		Optional<Productos> producto = productosRepositorio.findById(id);	
		if(producto.isPresent()) {
			return producto.get();
		}else {
			throw new ConflictException("Producto no encontrado");
		}

	}
	
	public Productos obtenerProductoPorNombre(String nombre) {
		Optional<Productos> producto = productosRepositorio.findByNombre(nombre);	
		if(producto.isPresent()) {
			return producto.get();
		}else {
			throw new ConflictException("Producto no encontrado");
		}

	}
	
	public String actualizarProducto(Productos producto){
		
		if(!validarProducto(producto)) {
			throw new DatosInvalidosExcepcion("Diligencie todos los datos correctamente");
		}
		
		Optional<Productos> productoExistente = productosRepositorio.findByNombre(producto.getNombre());
		if(productoExistente.isPresent()) {
			if(productoExistente.get().getId() != producto.getId()) {
				throw new ConflictException("Ya existe un producto con el mismo nombre");
			}
		}
		
		Productos prod = productosRepositorio.save(producto);
		if(prod.getId() == null) {
            throw new ConflictException("No se encontró el producto a actualizar");

		}else{
			return "Producto actualizado correctamente";
		}
		
	}
	@Modifying
	public String crearProducto(Productos producto){
		
		if(!validarProducto(producto)) {
			throw new DatosInvalidosExcepcion("Diligencie todos los datos correctamente");
		}
		Optional<Productos> productoExistente = productosRepositorio.findByNombre(producto.getNombre());
		if(productoExistente.isPresent()){
			 throw new ConflictException("Ya existe un producto con el mismo nombre");
		}
		
		productosRepositorio.save(producto);
		
		return "Producto creado correctamente";
		
	}
	
	private boolean validarProducto(Productos producto) {
		if(producto.getNombre().isEmpty() || 
				producto.getDescripcion().isEmpty() || 
				producto.getImagen().isEmpty() || 
				producto.getPrecio().equals(0) ||
				producto.getPrecio() == null   ||
				producto.getValorproduccionunitario().equals(0) ||
				producto.getValorproduccionunitario() == null
				) {
					return false;
				}
		return true;
		}
	
	
//	public String inactivarProducto(Integer id) {
//		Optional<Productos> producto = productosRepositorio.findById(id);
//		if(!producto.isPresent()){
//			 throw new ConflictException("No se encontró el producto a actualizar");
//
//		}else {
//			producto.get().set
//		}
//	}
	
	

}
