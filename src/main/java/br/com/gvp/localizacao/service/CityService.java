package br.com.gvp.localizacao.service;

import static br.com.gvp.localizacao.repository.specifications.CitySpecification.*;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.gvp.localizacao.entity.City;
import br.com.gvp.localizacao.repository.CityRepository;

@Service
public class CityService {

	private CityRepository repository;

	public CityService(CityRepository repository) {
		this.repository = repository;
	}

	@Transactional
	public void saveCity() {
		var city = new City(5L, "Brasília", 10212666L);
		repository.save(city);
	}

	public void listCity() {
		repository.findAll().forEach(System.out::println);
	}

	public void listCityByNameSQL() {
		repository.findByNameSQLNative("São Paulo")
			.stream().map(cityProjection -> new City(cityProjection.getId(), cityProjection.getName(), null))
			.forEach(System.out::println);
	}
	
	public void listCityByName() {
		Pageable pageable = PageRequest.of(1, 2);
		repository.findByNameLike("%%%%", pageable).forEach(System.out::println);
	}

	public void listCityByInhabitants() {
		repository.findByInhabitants(10212666L).forEach(System.out::println);
	}

	public void listCitiesByNumberOfInhabitants() {
		repository.findByInhabitantsLessThanAndNameLike(10212666L, "Porto%").forEach(System.out::println);
	}
	
	public List<City> dinamicFilter(City city) {
		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withIgnoreCase()
				.withStringMatcher(ExampleMatcher.StringMatcher.STARTING)
				.withIncludeNullValues();
		Example<City> example = Example.of(city, matcher);
		
		return repository.findAll(example);
	}
	
	public void listCitiesSpecsByNameSpecs() {
		repository
			.findAll(nameEqual("Brasília").and(idEqual(1L)))
			.forEach(System.out::println);
	}
	
	public void listCitySpecsDinamicFilter(City filter) {
		Specification<City> specs = Specification.where((root, query, cb) -> cb.conjunction());
		
		if(filter.getId() != null) {
			specs = specs.and(idEqual(filter.getId()));
		}
		
		if(StringUtils.hasText(filter.getName())) {
			specs = specs.and(nameLike(filter.getName()));
		}
		
		if(filter.getInhabitants() != null) {
			specs = specs.and(inhabitantsGreaterThan(filter.getInhabitants()));
		}
		
		repository.findAll(specs).forEach(System.out::println);
	}

}
