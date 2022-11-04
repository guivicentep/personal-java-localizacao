package br.com.gvp.localizacao.repository.specifications;

import org.springframework.data.jpa.domain.Specification;

import br.com.gvp.localizacao.entity.City;

public abstract class CitySpecification {
	
	public static Specification<City> idEqual(Long id) {
		return (root, query, cb) -> cb.equal(root.get("id"), id);
	}
	
	public static Specification<City> nameEqual(String name) {
		return (root, query, cb) -> cb.equal(root.get("name"), name);
	}
	
	public static Specification<City> inhabitantsGreaterThan(Integer value) {
		return (root, query, cb) -> cb.greaterThan(root.get("inhabitants"), value);
	}
}
