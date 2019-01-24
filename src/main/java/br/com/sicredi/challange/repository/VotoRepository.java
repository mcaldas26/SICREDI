package br.com.sicredi.challange.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.sicredi.challange.model.Voto;
import br.com.sicredi.challange.model.VotoId;

@Repository
public interface VotoRepository extends CrudRepository<Voto, VotoId> {
}
