package com.trabajodegrado.freshfruit.modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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

@Table(name = "inventarios", schema="trabajodegrado")
@Entity
public class Inventarios {
	

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;
	    private Integer idproducto;
	    private Integer existencias;

	    @OneToOne
		@JoinColumn(name = "idproducto", insertable = false, updatable = false)
		private Productos productos;
	    
	    
}


