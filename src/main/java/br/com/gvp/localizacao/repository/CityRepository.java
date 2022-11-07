package br.com.gvp.localizacao.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.gvp.localizacao.entity.City;
import br.com.gvp.localizacao.repository.projections.CityProjection;

public interface CityRepository extends JpaRepository<City, Long>, JpaSpecificationExecutor<City> {
	
	@Query(nativeQuery = true, value = "select c.city_id as id, c.name from tb_city as c where c.name =:name ")
	List<CityProjection> findByNameSQLNative(@Param("name") String name);
	
	/* Busca pelo nome correto */
	List<City> findByName(String name);
	
	/* Utilizando a query para conseguir pegar o valor sem case sensitive */
	/* Busca pelo nome like ordenado */
	@Query("select c from City c where lower(c.name) like lower(:name)")
	List<City> findByNameLike(String name, Sort sort);
	
	/* Utilizando a query para conseguir pegar o valor sem case sensitive */
	/* Busca pelo nome like paginado*/
	@Query("select c from City c where lower(c.name) like lower(:name)")
	Page<City> findByNameLike(String name, Pageable pageable);
	
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

