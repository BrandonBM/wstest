package com.wstest.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wstest.dao.UsuarioDAO;
import com.wstest.entity.UsuarioEntity;
import com.wstest.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Override
	public List<UsuarioEntity> getAll() {
		return (List<UsuarioEntity>) this.usuarioDAO.findAll();
	}

	@Override
	public void save(UsuarioEntity obj) {
		if (obj != null) {
			/** Utiliza CrudRepository */
			this.usuarioDAO.save(obj);

			/*usuarioDAO.insert(
					obj.getNombres(),
					obj.getApellidos(),
					obj.getCedula(),
					obj.getCorreo(),
					obj.getTelefono());*/
		}
	}

	@Override
	public void update(UsuarioEntity obj) {
		if (obj != null) {
			this.usuarioDAO.update(
					obj.getId(), 
					obj.getNombres(),
					obj.getApellidos(),
					obj.getCedula(),
					obj.getCorreo(),
					obj.getTelefono());
		}

	}

	@Override
	public void delete(Integer id) {
		if (id != null) {
			this.usuarioDAO.delete(id);
		}
	}

	@Override
	public Integer lastID() {
		return this.usuarioDAO.lastID();
	}

	@Override
	public UsuarioEntity getBy(Integer id) {
		return this.usuarioDAO.findById(id).orElse(null);
	}

	@Override
	public Integer existeCedula(Integer cedula) {
		return this.usuarioDAO.existeCedula(cedula);
	}

	@Override
	public Integer existeCorreo(String correo) {
		return this.usuarioDAO.existeCorreo(correo);
	}

}
