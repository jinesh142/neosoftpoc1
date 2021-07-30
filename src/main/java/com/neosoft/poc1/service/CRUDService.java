package com.neosoft.poc1.service;

import java.util.List;
import java.util.Optional;

public interface CRUDService<E> {
	E save(E entity);

	Optional<E> getById(Long id);

	List<E> getAll();

	void delete(Long id);
}
