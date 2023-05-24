package com.residencia.api1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.residencia.api1.entities.Telefone;
import com.residencia.api1.services.TelefoneService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/telefones")
public class TelefoneController {
	@Autowired
	TelefoneService telefoneService;

	@GetMapping("/user")
	public ResponseEntity<List<Telefone>> getAllTelefones() {
		return new ResponseEntity<>(telefoneService.getAllTelefonees(), HttpStatus.OK);
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<Telefone> getTelefoneById(@Valid @PathVariable Integer id) {
		Telefone telefoneResponse = telefoneService.getTelefoneById(id);
		if (null == telefoneResponse)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(telefoneResponse, HttpStatus.OK);

	}

	@PostMapping("/inst")
	public ResponseEntity<Telefone> saveTelefone(@Valid @RequestBody Telefone telefone) {
		return new ResponseEntity<>(telefoneService.saveTelefone(telefone), HttpStatus.CREATED);
	}

	@PutMapping("/inst/{id}")
	public ResponseEntity<Telefone> updateTelefone(@Valid @RequestBody Telefone telefone, @Valid @PathVariable Integer id) {
		Telefone telefoneAtualizado = telefoneService.getTelefoneById(id);
		if (telefoneAtualizado == null) {
			return new ResponseEntity<>(telefoneService.updateTelefone(telefone, id), HttpStatus.BAD_REQUEST);	
		} else {
			return new ResponseEntity<>(telefoneService.updateTelefone(telefone, id), HttpStatus.OK);	
		}

	}

	@DeleteMapping("/adm/{id}")
	public ResponseEntity<Boolean> delTelefone(@Valid @PathVariable Integer id) {
		if (telefoneService.getTelefoneById(id) != null) {
			Boolean resp = telefoneService.deleteTelefone(id);
			if (resp)
				return new ResponseEntity<>(resp, HttpStatus.OK);
			else
				return new ResponseEntity<>(resp, HttpStatus.NOT_MODIFIED);
		} else {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}
	}
}