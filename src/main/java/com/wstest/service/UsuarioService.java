package com.wstest.service;

import java.util.List;

import com.wstest.entity.UsuarioEntity;

public interface UsuarioService {
	
	public Integer lastID();
	public UsuarioEntity getBy(Integer id);
	public Integer existeCedula(Integer cedula);
	public Integer existeCorreo(String correo);
	public List<UsuarioEntity> getAll();
	public void save(UsuarioEntity obj);
	public void update(UsuarioEntity obj);
	public void delete(Integer id);

}
