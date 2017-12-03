package br.com.sociotorcedor.repository;

import br.com.sociotorcedor.domain.SocioTorcedor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SocioTorcedorRepository extends MongoRepository<SocioTorcedor, String> {

    Optional<SocioTorcedor> findByNomeCompletoIgnoreCase(String nomeCompleto);
}
