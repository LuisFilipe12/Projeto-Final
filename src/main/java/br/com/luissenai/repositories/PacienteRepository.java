package br.com.luissenai.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.luissenai.model.Paciente;

public interface PacienteRepository extends PagingAndSortingRepository<Paciente, Integer>{

	public Paciente findById(String id);
	
}
