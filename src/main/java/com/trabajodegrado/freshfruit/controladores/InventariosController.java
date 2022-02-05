package com.trabajodegrado.freshfruit.controladores;

import java.util.List;

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

import com.trabajodegrado.freshfruit.modelos.Inventarios;
import com.trabajodegrado.freshfruit.modelos.Movimientosinventario;
import com.trabajodegrado.freshfruit.modelos.dto.InventariosDTO;
import com.trabajodegrado.freshfruit.modelos.dto.PaginacionDTO;
import com.trabajodegrado.freshfruit.modelos.dto.RespuestaPaginada;
import com.trabajodegrado.freshfruit.negocio.InventariosNegocio;


	@RestController
	@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
	@RequestMapping("/inventarios")
	public class InventariosController {

	    @Autowired
	    private InventariosNegocio inventariosNegocio;

	    @GetMapping("/")
	    public ResponseEntity<List<Inventarios>> obtenerLista() {
		return new ResponseEntity<>(inventariosNegocio.obtenerLista(), HttpStatus.OK);
	    }
	    
	    @GetMapping("/paginada")
	    public ResponseEntity<RespuestaPaginada<Inventarios>> obtenerListaPaginada(@PathParam("paginacion") PaginacionDTO paginacion) {
		return new ResponseEntity<>(inventariosNegocio.obtenerListaPaginada(paginacion), HttpStatus.OK);
	    }
	    
	    @GetMapping("/{id}")
	    public ResponseEntity<Inventarios> obtenerInventario(@PathVariable("id") Integer id) {
	        return new ResponseEntity<>(inventariosNegocio.obtener(id), HttpStatus.OK);
	    }
	    
	    @GetMapping("/obtenerListaMovimientos/")
	    public ResponseEntity<RespuestaPaginada<Movimientosinventario>> obenterListaMovimientos(@PathParam("paginacion") PaginacionDTO paginacion) {
		return new ResponseEntity<>(inventariosNegocio.obenterListaMovimientos(paginacion), HttpStatus.OK);
	    }
	    
	    @GetMapping("/obtenerListaMovimientosInventario/{id}")
	    public ResponseEntity<List<Movimientosinventario>> obtenerListaMovimientosInventario(@PathVariable("id") Integer id) {
		return new ResponseEntity<>(inventariosNegocio.obtenerListaMovimientosInventario(id), HttpStatus.OK);
	    }
	    
	    @PostMapping("/")
	    public ResponseEntity<String> insertarInventario(@RequestBody InventariosDTO inventario) {
	        return new ResponseEntity<>(inventariosNegocio.insertarInventario(inventario), HttpStatus.OK);
	    }
	    
	    @PostMapping("/disminuirInventario/")
	    public ResponseEntity<String> disminuirInventario(@RequestBody InventariosDTO inventario) {
	        return new ResponseEntity<>(inventariosNegocio.disminuirInventario(inventario), HttpStatus.OK);
	    }
}
