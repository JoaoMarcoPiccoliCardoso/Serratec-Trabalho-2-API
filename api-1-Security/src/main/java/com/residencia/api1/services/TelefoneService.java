package com.residencia.api1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.api1.entities.Instrutor;
import com.residencia.api1.entities.Telefone;
import com.residencia.api1.exceptions.NoSuchElementException;
import com.residencia.api1.exceptions.UniqueElementException;
import com.residencia.api1.exceptions.UnmatchingIdsException;
import com.residencia.api1.repositories.InstrutorRepository;
import com.residencia.api1.repositories.TelefoneRepository;
import com.residencia.api1.security.services.EmailService;

@Service
public class TelefoneService {
	@Autowired
	TelefoneRepository telefoneRepository;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	InstrutorRepository instrutorRepository;
	
	public List<Telefone> getAllTelefonees() {
		return telefoneRepository.findAll();
	}
	
	public Telefone getTelefoneById(Integer id) {
		return telefoneRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Telefone", id));
	}
	
	public Telefone saveTelefone(Telefone telefone) {
			Instrutor instrutor = instrutorRepository.findById(telefone.getInstrutor().getId()).orElse(null);
			if (instrutor != null && instrutor.getTelefone() == null) {
				Telefone telefoneEmail = telefoneRepository.save(telefone);
				emailService.enviarEmail("email@gmail.com", "Telefone Criado", telefoneEmail.toString());
				return telefoneRepository.save(telefone);					
			} else {
				throw new UniqueElementException();
			}
	}
	
	public Telefone updateTelefone(Telefone telefone, Integer id) {
		if (telefone.getId() != id) {
			throw new UnmatchingIdsException(telefone.getId(), id);
		} else {
			return telefoneRepository.save(telefone);
		}
	}
	
	public boolean deleteTelefone(Integer id) {
		telefoneRepository.deleteById(id);
		Telefone telefoneDeletado = telefoneRepository.findById(id).orElse(null);
		if (null == telefoneDeletado) {
			return true;
		} else {
			return false;
		}
	}

}
