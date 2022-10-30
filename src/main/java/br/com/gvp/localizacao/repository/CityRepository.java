package br.com.gvp.localizacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gvp.localizacao.entity.City;

public interface CityRepository extends JpaRepository<City, Long> {

}
