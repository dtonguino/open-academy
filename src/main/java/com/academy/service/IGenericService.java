package com.academy.service;

import java.util.List;

public interface IGenericService<T, ID> {
	/**
	 * Inserta la informacion de la entidad.
	 * 
	 * @param t la informacion de la entidad
	 * @return la informacion de la entidad insertada
	 * @throws Exception cuando se presenta un error al insertar
	 */
	T save(T t) throws Exception;

	/**
	 * Actualiza la informacion de la entidad.
	 * 
	 * @param t la informacion de la entidad
	 * @return la informacion de la entidad actualizada
	 * @throws Exception cuando se presenta un error al actualizar
	 */
	T update(T t) throws Exception;

	/**
	 * Consulta la informacion de todos los registros.
	 * 
	 * @return lista de los registros consultados
	 * @throws Exception cuando se presenta un error al consular
	 */
	List<T> readAll() throws Exception;

	/**
	 * Consulta la informacion de la entidad en base a su clave primaria.
	 * 
	 * @param id la clave primaria de la entidad
	 * @return la informacion de la entidad
	 * @throws Exception cuando se presenta un error al consultar
	 */
	T readById(ID id) throws Exception;

	/**
	 * Elimina la informacion de la entidad en base a su clave primaria.
	 * 
	 * @param id la clave primaria de la entidad
	 * @throws Exception cuando se presenta un error al eliminar
	 */
	void delete(ID id) throws Exception;
}