package com.academy.service.impl;

import java.util.List;

import com.academy.repository.IGenericRepository;
import com.academy.service.IGenericService;

public abstract class GenericServiceImpl<T, ID> implements IGenericService<T, ID> {

	protected abstract IGenericRepository<T, ID> getRepository();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T save(T t) throws Exception {
		return getRepository().save(t);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T update(T t) throws Exception {
		return getRepository().save(t);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<T> readAll() throws Exception {
		return getRepository().findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T readById(ID id) throws Exception {
		return getRepository().findById(id).orElse(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delete(ID id) throws Exception {
		getRepository().deleteById(id);
	}
}
