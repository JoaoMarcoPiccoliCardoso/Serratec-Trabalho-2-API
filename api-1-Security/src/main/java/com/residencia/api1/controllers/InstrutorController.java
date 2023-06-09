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

import com.residencia.api1.entities.Instrutor;
import com.residencia.api1.services.InstrutorService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/instrutores")
public class InstrutorController {
	@Autowired
	InstrutorService  instrutorService;
	
	@GetMapping("/user")
	public ResponseEntity<List<Instrutor>> getAllInstrutors() {
		return new ResponseEntity<>(instrutorService.getAllInstrutores(), HttpStatus.OK);
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<Instrutor> getInstrutorById(@Valid @PathVariable Integer id) {
		Instrutor instrutorResponse = instrutorService.getInstrutorById(id);
		if (null == instrutorResponse)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(instrutorResponse, HttpStatus.OK);

	}

	@PostMapping("/adm")
	public ResponseEntity<Instrutor> saveInstrutor(@Valid @RequestBody Instrutor instrutor) {
		return new ResponseEntity<>(instrutorService.saveInstrutor(instrutor), HttpStatus.CREATED);
	}


	@PutMapping("/adm/{id}")
	public ResponseEntity<Instrutor> updateInstrutor(@Valid @RequestBody Instrutor instrutor, @Valid @PathVariable Integer id) {
		Instrutor instrutorAtualizado = instrutorService.getInstrutorById(id);
		if (instrutorAtualizado != null) {
			return new ResponseEntity<>(instrutorService.updateInstrutor(instrutor, id), HttpStatus.OK);	
		} else {
			return new ResponseEntity<>(instrutorService.updateInstrutor(instrutor, id), HttpStatus.BAD_REQUEST);	
		}
	}

	@DeleteMapping("/adm/{id}")
	public ResponseEntity<Boolean> delInstrutor(@Valid @PathVariable Integer id) {
		if (instrutorService.getInstrutorById(id) != null) {
			Boolean resp = instrutorService.deleteInstrutor(id);
			if (resp)
				return new ResponseEntity<>(resp, HttpStatus.OK);
			else
				return new ResponseEntity<>(resp, HttpStatus.NOT_MODIFIED);
		} else {
			return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
		}
	}
}