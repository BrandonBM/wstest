package com.wstest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wstest.entity.UsuarioEntity;
import com.wstest.service.UsuarioService;
import com.wstest.util.exception.CustomServiceException;
import com.wstest.util.exception.ResourceNotFoundException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	
	@RequestMapping( 
			value = "/existscedula/{id}",
			method = RequestMethod.GET,
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
			)
	public ResponseEntity<Boolean> validCedula(@PathVariable("id") int cedula) {
		try {
			Integer existe = usuarioService.existeCedula(cedula);
			boolean result = (existe != null )? existe.intValue() == 1 : false;
			return new ResponseEntity<Boolean>(result, HttpStatus.OK);
		} catch(CustomServiceException e) {
			throw new CustomServiceException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
		}
	}
	
	
	@RequestMapping( 
			value = "/existscorreo/{id}",
			method = RequestMethod.GET,
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
			)
	public ResponseEntity<Boolean> validCedula(@PathVariable("id") String correo) {
		try {
			Integer existe = usuarioService.existeCorreo(correo);
			boolean result = (existe != null )? existe.intValue() == 1 : false;
			return new ResponseEntity<Boolean>(result, HttpStatus.OK);
		} catch(CustomServiceException e) {
			throw new CustomServiceException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
		}
	}

	@RequestMapping( 
			value = "/getBy/{id}",
			method = RequestMethod.GET,
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
			)
	public ResponseEntity<UsuarioEntity> getBy(@PathVariable("id") int id) {
		try {
			UsuarioEntity item = usuarioService.getBy(id);
			return new ResponseEntity<UsuarioEntity>(item, HttpStatus.OK);
		} catch(CustomServiceException e) {
			throw new CustomServiceException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
		}
	}

	
	
	@RequestMapping( 
			value = "/getAll",
			method = RequestMethod.GET,
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
			)
	public ResponseEntity<List<UsuarioEntity>> getAll() {
		try {
			List<UsuarioEntity> items = usuarioService.getAll();
			return new ResponseEntity<List<UsuarioEntity>>(items, HttpStatus.OK);
		} catch(CustomServiceException e) {
			throw new CustomServiceException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
		}
	}

	
	@RequestMapping( 
			value = "/create",
			method = RequestMethod.POST,
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
			)
	public ResponseEntity<UsuarioEntity> save(@RequestBody UsuarioEntity obj) {
		try {
			this.usuarioService.save(obj);
			return  new ResponseEntity<UsuarioEntity>(obj,HttpStatus.OK);
		} catch(CustomServiceException e) {
			throw new CustomServiceException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
		}
	}

	
	@RequestMapping( 
			value = "/update",
			method = RequestMethod.PUT,
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
			)
	public ResponseEntity<UsuarioEntity> update(@RequestBody UsuarioEntity obj) {
		try {
			if (this.usuarioService.getBy(obj.getId()) == null){
				throw new ResourceNotFoundException("El Usuario no existe");
			}
			this.usuarioService.update(obj);
			return new ResponseEntity<UsuarioEntity>(obj,HttpStatus.OK);
		}
		catch(CustomServiceException e) {
			throw new CustomServiceException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
		}
	}

	
	@RequestMapping( 
			value = "/delete/{id}",
			method = RequestMethod.DELETE,
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }
			)
	public ResponseEntity<Void> delete(@PathVariable("id") int id) {
		try {
			if (usuarioService.getBy(id) == null){
				throw new ResourceNotFoundException("El Usuario no existe");
			}
			usuarioService.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		catch(CustomServiceException e) {
			throw new CustomServiceException(HttpStatus.BAD_REQUEST, e.getLocalizedMessage());
		}
	}

}
