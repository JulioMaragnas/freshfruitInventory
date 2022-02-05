package com.trabajodegrado.freshfruit.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.trabajodegrado.freshfruit.seguridad.JWTToken;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RequestMapping("/usuarios")
public class LogueoTemporalController {
	
	@Autowired
	private JWTToken jwt;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(/*@RequestBody Usuarios usuario*/) throws Exception {
		return new ResponseEntity<>(jwt.getJWTToken(), HttpStatus.OK);
	}

}
