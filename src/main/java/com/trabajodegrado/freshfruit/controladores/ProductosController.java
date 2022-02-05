package com.trabajodegrado.freshfruit.controladores;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trabajodegrado.freshfruit.modelos.Productos;
import com.trabajodegrado.freshfruit.modelos.dto.PaginacionDTO;
import com.trabajodegrado.freshfruit.modelos.dto.RespuestaPaginada;
import com.trabajodegrado.freshfruit.negocio.ProductosNegocio;


	@RestController
	@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
	@RequestMapping("/productos")
	public class ProductosController {

	    @Autowired
	    private ProductosNegocio productosNegocio;

//	    @GetMapping("/")
//	    public ResponseEntity<List<Productos>> obtenerLista() {
//		return new ResponseEntity<>(productosNegocio.obtenerLista(), HttpStatus.OK);
//	    }
	    
	    @GetMapping("/")
	    public ResponseEntity<RespuestaPaginada<Productos>> obtenerLista(@PathParam("paginacion") PaginacionDTO paginacion) {
		return new ResponseEntity<>(productosNegocio.obtenerLista(paginacion), HttpStatus.OK);
	    }
	    
	    @GetMapping("/{id}")
	    public ResponseEntity<Productos> obtenerProducto(@PathVariable("id") Integer id) {
	        return new ResponseEntity<>(productosNegocio.obtenerProducto(id), HttpStatus.OK);
	    }
	    
	    @GetMapping("/obtenerpornombre/{nombre}")
	    public ResponseEntity<Productos> obtenerProductoPorNombre(@PathVariable("nombre") String nombre) {
	        return new ResponseEntity<>(productosNegocio.obtenerProductoPorNombre(nombre), HttpStatus.OK);
	    }
	    
	    @PutMapping("/")
	    public ResponseEntity<String> actualizarProducto(@RequestBody Productos producto) {
	        return new ResponseEntity<>(productosNegocio.actualizarProducto(producto), HttpStatus.OK);
	    }

	    @PostMapping("/")
	    public ResponseEntity<String> crearProducto(@RequestBody Productos producto) {
	        return new ResponseEntity<>(productosNegocio.crearProducto(producto), HttpStatus.OK);
	    }
	    
	}
