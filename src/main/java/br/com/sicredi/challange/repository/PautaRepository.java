package br.com.sicredi.challange.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.sicredi.challange.model.Pauta;

@Repository
public interface PautaRepository extends CrudRepository<Pauta, Long> {

	@Query("select p from Pauta p where p.id = ?1 and  p.dtInicioVotacao <= ?2 and p.dtFimVotacao >= ?2")
	Optional<Pauta> getPautaByIdAndActive(Long id, LocalDateTime dateTime);
}
