package com.trabajodegrado.freshfruit.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trabajodegrado.freshfruit.modelos.Estados;
import com.trabajodegrado.freshfruit.negocio.EstadosNegocio;


	

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RequestMapping("/estados")
public class EstadosController {

    @Autowired
    private EstadosNegocio estadosNegocio;

    @GetMapping("/")
    public ResponseEntity<List<Estados>> obtenerLista() {
	return new ResponseEntity<>(estadosNegocio.obtenerLista(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Estados> obtenerEstado(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(estadosNegocio.obtenerEstado(id), HttpStatus.OK);
    }
    
    @GetMapping("/obtenerporcodigo/{codigo}")
    public ResponseEntity<Estados> obtenerEstadoPorCodigo(@PathVariable("codigo") String codigo) {
        return new ResponseEntity<>(estadosNegocio.obtenerEstadoPorCodigo(codigo), HttpStatus.OK);
    }
}
