package br.com.gvp.localizacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.gvp.localizacao.entity.City;

public interface CityRepository extends JpaRepository<City, Long> {
	
	
	/* Busca pelo nome correto */
	List<City> findByName(String name);
	
	/* Utilizando a query para conseguir pegar o valor sem case sensitive */
	/* Busca pelo nome like */
	@Query("select c from City c where lower(c.name) like lower(:name)")
	List<City> findByNameLike(String name);
	
	/* Busca pelo nome que começa por */
	List<City> findByNameStartingWith(String name);
	
	/* Busca pelo nome que termina por */
	List<City> findByNameEndingWith(String name);
	
	/* Busca pelo nome que contém */
	List<City> findByNameContaining(String name);
	
	List<City> findByInhabitants(Long inhabitants);
	
	List<City> findByInhabitantsLessThan(Long innhabitants);
	
	List<City> findByInhabitantsGreaterThan(Long innhabitants);
	
	List<City> findByInhabitantsLessThanEqual(Long innhabitants);
	
	List<City> findByInhabitantsLessThanAndNameLike(Long innhabitants, String name);
}

