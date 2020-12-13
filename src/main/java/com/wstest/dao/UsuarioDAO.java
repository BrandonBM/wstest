package com.wstest.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wstest.entity.UsuarioEntity;

@Repository
public interface UsuarioDAO extends CrudRepository<UsuarioEntity, Integer> {

	@Query(nativeQuery = true, value = " SELECT id FROM usuario ORDER BY id DESC LIMIT 1 ")
	public Integer lastID();
	
	/*@Query(nativeQuery = true, value = "SELECT id, nombres, apellidos, cedula, correo, telefono FROM usuario ")
	public List<UsuarioEntity> findAll();*/

	/*@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "INSERT INTO usuario" + 
			" (nombres, apellidos, cedula, correo, telefono) " + 
			"VALUES(:p1, :p2, :p3, :p4, :p5)")
	public void insert(
			@Param("p1") String nombres, 
			@Param("p2") String apellidos,
			@Param("p3") Integer cedula,
			@Param("p4") String correo,
			@Param("p5") Integer telefono
			);*/
	
	@Query(nativeQuery = true, value = "select case when cedula != null then false else true end as existe"
			+ " from usuario where cedula=:p1 ")
	public Integer existeCedula(@Param("p1") Integer cedula);
	
	@Query(nativeQuery = true, value = "select case when correo != null then false else true end as existe"
			+ " from usuario where correo=:p1 ")
	public Integer existeCorreo(@Param("p1") String correo);
	

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE usuario " + 
			" SET nombres=:p1, apellidos=:p2, cedula=:p3, correo=:p4, telefono=:p5 " + 
			" WHERE id=:p0 ")
	public void update(
			@Param("p0") Integer id,
			@Param("p1") String nombres, 
			@Param("p2") String apellidos,
			@Param("p3") Integer cedula,
			@Param("p4") String correo,
			@Param("p5") Long telefono
			);

	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "DELETE FROM usuario WHERE id=:p1")
	public void delete(@Param("p1") Integer id);
}
