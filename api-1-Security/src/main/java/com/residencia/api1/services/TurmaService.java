package com.residencia.api1.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.api1.entities.Turma;
import com.residencia.api1.exceptions.NoSuchElementException;
import com.residencia.api1.repositories.TurmaRepository;
import com.residencia.api1.security.services.EmailService;

@Service
public class TurmaService {
	@Autowired
	TurmaRepository turmaRepository;
	
	@Autowired
	EmailService emailService;
	
	public List<Turma> getAllTurmaes() {
		return turmaRepository.findAll();
	}
	
	public Turma getTurmaById(Integer id) {
		return turmaRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Turma", id));
	}
	
	public Turma saveTurma(Turma turma) {
		Turma turmaEmail = turmaRepository.save(turma);
		emailService.enviarEmail("email@gmail.com", "Telefone Criado", turmaEmail.toString());
		return turmaRepository.save(turma);
	}
	
	public Turma updateTurma(Turma turma, Integer id) {
		Turma turmaAtualizado = turmaRepository.findById(id).orElse(null);
		if (turmaAtualizado != null) {
			return turmaRepository.save(turma);
		} else {
			return null;
		}
	}
	
	public boolean deleteTurma(Integer id) {
		turmaRepository.deleteById(id);
		Turma turmaDeletado = turmaRepository.findById(id).orElse(null);
		if (null == turmaDeletado) {
			return true;
		} else {
			return false;
		}
	}

}
