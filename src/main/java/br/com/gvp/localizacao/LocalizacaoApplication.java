package br.com.gvp.localizacao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.gvp.localizacao.entity.City;
import br.com.gvp.localizacao.repository.CityRepository;

@SpringBootApplication
public class LocalizacaoApplication implements CommandLineRunner {
	
	@Autowired
	private CityRepository cityRepository;

	@Override
	public void run(String... args) throws Exception {
		listCitiesByNumberOfInhabitants();
	}
	
	void listCityByName() {
		cityRepository.findByNameLike("porto%").forEach(System.out::println);
	}
	
	void listCityByInhabitants() {
		cityRepository.findByInhabitants(10212666L).forEach(System.out::println);
	}
	
	void listCitiesByNumberOfInhabitants() {
		cityRepository.findByInhabitantsLessThanAndNameLike(10212666L, "Porto%").forEach(System.out::println);
	}
	
	@Transactional
	void saveCity() {
		var city = new City(5L, "Brasília", 10212666L);
		cityRepository.save(city);
	}
	
	void listCity() {
		cityRepository.findAll().forEach(System.out::println);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(LocalizacaoApplication.class, args);
	}



}
