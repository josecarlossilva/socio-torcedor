package br.com.sociotorcedor.controller;

import br.com.sociotorcedor.domain.CampanhaResource;
import br.com.sociotorcedor.exception.SocioTorcedorJaCadastradoException;
import br.com.sociotorcedor.mock.CampanhaMock;
import br.com.sociotorcedor.mock.SocioTorcedorMock;
import br.com.sociotorcedor.repository.SocioTorcedorRepository;
import br.com.sociotorcedor.service.CampanhaService;
import feign.RetryableException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SocioTorcedorControllerTest {

    @Autowired
    private SocioTorcedorController socioTorcedorController;

    @Autowired
    private SocioTorcedorRepository socioTorcedorRepository;

    @MockBean
    private CampanhaService campanhaService;

    @Before
    public void setUp() throws Exception {
        HttpServletRequest mockRequest = new MockHttpServletRequest();
        ServletRequestAttributes servletRequestAttributes = new ServletRequestAttributes(mockRequest);
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);
        socioTorcedorRepository.deleteAll();
        given(this.campanhaService.getCampanhasByTimeCoracao("TimeDoCoracao"))
                .willReturn(CampanhaMock.getCampanhasResource());
    }

    @Test
    public void quandoSocioTorcedorCadastradoDeveRetornarListaDeCampanhas() throws Exception {
        final ResponseEntity<List<CampanhaResource>> responseEntity =
                socioTorcedorController.cadastrarSocioTorcedor(SocioTorcedorMock.criaSocioTorcedorResource());

        assertThat(responseEntity).as("O Sócio torcedor deve ser criado com sucesso").isNotNull();
        assertThat(responseEntity.getStatusCode()).as("O Status code deve ser created").isEqualTo(HttpStatus.CREATED);
        assertThat(responseEntity.getBody()).as("Deve retornar a lista de campanhas").hasSize(2);
    }

    @Test
    public void quandoServicoDeCampanhasEstiverForaHystrixDeveChamarCallbackECadastrarSocioERetornarCreated() throws Exception {

        given(this.campanhaService.getCampanhasByTimeCoracao("TimeDoCoracao"))
                .willThrow(RetryableException.class);

        final ResponseEntity<List<CampanhaResource>> responseEntity =
                socioTorcedorController.cadastrarSocioTorcedor(SocioTorcedorMock.criaSocioTorcedorResource());

        assertThat(responseEntity).as("O Sócio torcedor deve ser criado com sucesso").isNotNull();
        assertThat(responseEntity.getStatusCode()).as("O Status code deve ser created").isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void naoDeveCadastrarDoisUsuariosComMesmoEmail() throws Exception{
        socioTorcedorRepository.save(SocioTorcedorMock.getSocioTorcedores());
        assertThatExceptionOfType(SocioTorcedorJaCadastradoException.class)
                .isThrownBy(() ->  socioTorcedorController.cadastrarSocioTorcedor(SocioTorcedorMock.criaSocioTorcedorResource()))
                .withMessageContaining("Usuário já cadastrado");

    }

}