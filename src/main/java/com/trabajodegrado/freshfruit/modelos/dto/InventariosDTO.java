package com.trabajodegrado.freshfruit.modelos.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor


public class InventariosDTO {
	
	    private Integer id;
	    private Integer idproducto;
	    private Integer existencias;
	    private Integer idMotivo;

}


