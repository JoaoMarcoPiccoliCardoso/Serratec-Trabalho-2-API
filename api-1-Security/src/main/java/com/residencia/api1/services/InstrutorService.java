package com.residencia.api1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.api1.entities.Instrutor;
import com.residencia.api1.exceptions.NoSuchElementException;
import com.residencia.api1.exceptions.UnmatchingIdsException;
import com.residencia.api1.repositories.InstrutorRepository;
import com.residencia.api1.security.services.EmailService;

@Service
public class InstrutorService {
	@Autowired
	InstrutorRepository instrutorRepository;
	
	@Autowired
	EmailService emailService;

	public List<Instrutor> getAllInstrutores() {
		return instrutorRepository.findAll();
	}

	public Instrutor getInstrutorById(Integer id) {
		return instrutorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Instrutor", id));
	}

	public Instrutor saveInstrutor(Instrutor instrutor) {
		Instrutor instrutorEmail = instrutorRepository.save(instrutor);
		emailService.enviarEmail("emaildestino@gmail.com", "Instrutor Criado", instrutorEmail.toString());
		return instrutorRepository.save(instrutor);
	}

	public Instrutor updateInstrutor(Instrutor instrutor, Integer id) {
		if (instrutor.getId() != id) {
			throw new UnmatchingIdsException(instrutor.getId(), id);
		} else {
			return instrutorRepository.save(instrutor);
		}
	}

	public boolean deleteInstrutor(Integer id) {
		instrutorRepository.deleteById(id);
		Instrutor instrutorDeletado = instrutorRepository.findById(id).orElse(null);
		if (null == instrutorDeletado) {
			return true;
		} else {
			return false;
		}
	}

}
