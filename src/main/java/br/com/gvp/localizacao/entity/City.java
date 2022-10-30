package br.com.gvp.localizacao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_city")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City {
	
	@Id
	@Column(name = "city_id")
	private Long id;
	
	@Column(name = "name", length = 50)
	private String name;
	
	@Column(name = "qtt_inhabitants")
	private Long inhabitants;
}
