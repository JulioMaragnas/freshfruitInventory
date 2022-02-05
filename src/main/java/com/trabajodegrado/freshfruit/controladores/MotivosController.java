package com.trabajodegrado.freshfruit.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trabajodegrado.freshfruit.modelos.Motivos;
import com.trabajodegrado.freshfruit.negocio.MotivosNegocio;


@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RequestMapping("/motivos")
public class MotivosController {

    @Autowired
    private MotivosNegocio motivosNegocio;

    @GetMapping("/")
    public ResponseEntity<List<Motivos>> obtenerLista() {
	return new ResponseEntity<>(motivosNegocio.obtenerLista(), HttpStatus.OK);
    }
    
    @GetMapping("/obtenerListaSelect")
    public ResponseEntity<List<Motivos>> obtenerListaSelect() {
	return new ResponseEntity<>(motivosNegocio.obtenerListaSelect(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Motivos> obtenerMotivo(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(motivosNegocio.obtenerMotivo(id), HttpStatus.OK);
    }
    
    @PutMapping("/")
    public ResponseEntity<String> actualizarMotivo(@RequestBody Motivos motivo) {
        return new ResponseEntity<>(motivosNegocio.actualizarMotivo(motivo), HttpStatus.OK);
    }
    
    @PostMapping("/")
    public ResponseEntity<String> crearMotivo(@RequestBody Motivos motivo) {
        return new ResponseEntity<>(motivosNegocio.crearMotivo(motivo), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> inactivarMotivo(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(motivosNegocio.inactivarMotivo(id), HttpStatus.OK);
    }
    
}
