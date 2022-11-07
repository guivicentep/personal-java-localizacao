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
	
	public static Specification<City> inhabitantsGreaterThan(Long value) {
		return (root, query, cb) -> cb.greaterThan(root.get("inhabitants"), value);
	}
	
	public static Specification<City> inhabitantsBetween(Long min, Long max) {
		return (root, query, cb) -> cb.between(root.get("inhabitants"), min, max);
	}
	
	public static Specification<City> nameLike(String name) {
		return (root, query, cb) -> cb.like(cb.upper(root.get("name")), "%" + name + "%".toUpperCase());
	}
	
}
