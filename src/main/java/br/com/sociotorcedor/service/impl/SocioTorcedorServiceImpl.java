package br.com.sociotorcedor.service.impl;

import br.com.sociotorcedor.domain.SocioTorcedor;
import br.com.sociotorcedor.repository.SocioTorcedorRepository;
import br.com.sociotorcedor.service.SocioTorcedorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Service
@Validated
public class SocioTorcedorServiceImpl implements SocioTorcedorService {

    private static final Logger logger = LoggerFactory.getLogger(SocioTorcedorServiceImpl.class);

    @Autowired
    private SocioTorcedorRepository socioTorcedorRepository;

    public SocioTorcedor cadastrarSocioTorcedor(String nome, String email, LocalDate dataNascimento, String timeCoracao)
            throws DuplicateKeyException {

        final SocioTorcedor socioTorcedor = new SocioTorcedor(nome, email, dataNascimento, timeCoracao);

        if(logger.isDebugEnabled()){
            logger.debug("Cadastrando Sócio Torcedor : {}", socioTorcedor);

        }
        return socioTorcedorRepository.save(socioTorcedor);

    }
}
