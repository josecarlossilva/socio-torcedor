package br.com.sociotorcedor.controller;

import br.com.sociotorcedor.domain.CampanhaResource;
import br.com.sociotorcedor.domain.ErrorInfo;
import br.com.sociotorcedor.domain.SocioTorcedor;
import br.com.sociotorcedor.domain.SocioTorcedorResource;
import br.com.sociotorcedor.exception.SocioTorcedorJaCadastradoException;
import br.com.sociotorcedor.service.CampanhaService;
import br.com.sociotorcedor.service.SocioTorcedorService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/socios")
@Api(value = "Sócio Torcedor", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE,
        tags = {"Endpoints do Sócio Torcedor"}, description = "Lida com todas as requisções da API Rest de sócio torcedor", basePath = "/api/v1/socios")
public class SocioTorcedorController {

    private static final Logger logger = LoggerFactory.getLogger(SocioTorcedorController.class);

    @Autowired
    private SocioTorcedorService socioTorcedorService;

    @Autowired
    private CampanhaService campanhaService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE ,produces = MediaType.APPLICATION_JSON_VALUE)
    @HystrixCommand(fallbackMethod = "retornaStatusCreated")
    @ApiOperation(value = "Cria um novo sócio torcedor com base nos parametros passados",
            notes = "Cria um novo sócio torcedor e retorna a lista de campanhas associadas ao time do torcedor" +
                    ",caso o serviço de campanhas esteja indisponível o serviço cria o sócio torcedor e retorna o status " +
                    "201 criado",
            response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400 , message = "Conflict"),
            @ApiResponse(code = 409 , message = "Bad Request"),
            @ApiResponse(code = 500 , message = "Internal Server Error")})
    public ResponseEntity<List<CampanhaResource>> cadastrarSocioTorcedor(@Valid @RequestBody SocioTorcedorResource socioTorcedorResource){

        try {

            final List<CampanhaResource> campanhasByTimeCoracao =
                    campanhaService.getCampanhasByTimeCoracao(socioTorcedorResource.getTimeCoracao());

            final SocioTorcedor socioTorcedor =
                    socioTorcedorService.cadastrarSocioTorcedor(socioTorcedorResource.getNomeCompleto(), socioTorcedorResource.getEmail(),
                            socioTorcedorResource.getDataNascimento(), socioTorcedorResource.getTimeCoracao());
            if(logger.isDebugEnabled()){
                logger.debug("Sócio Torcedor : {} cadastrado com sucesso", socioTorcedor);
            }
            return new ResponseEntity<>(campanhasByTimeCoracao,
                    HttpStatus.CREATED);

        }catch (DuplicateKeyException ex){
            if(logger.isDebugEnabled()){
                logger.debug("Sócio Torcedor com e-mail: {} já cadastrado", socioTorcedorResource.getEmail());
            }
            throw new SocioTorcedorJaCadastradoException();
        }
    }

    /**
     * Método de fallback que será chamado pelo Hystrix quando o serviço de campanhas estiver indisponível
     * @param socioTorcedorResource
     * @return
     */
    private ResponseEntity<List<CampanhaResource>> retornaStatusCreated(SocioTorcedorResource socioTorcedorResource) {

        try {

            final SocioTorcedor socioTorcedor = socioTorcedorService.cadastrarSocioTorcedor(socioTorcedorResource.getNomeCompleto(), socioTorcedorResource.getEmail(),
                    socioTorcedorResource.getDataNascimento(), socioTorcedorResource.getTimeCoracao());
            if(logger.isDebugEnabled()){
                logger.debug("Sócio torcedor : {} cadastrado com sucesso", socioTorcedor);
                logger.debug("Nao foi possível conectar-se com o servidor de campanhas", socioTorcedor);
            }

            return new ResponseEntity<>(HttpStatus.CREATED);

        }catch (DuplicateKeyException ex){
            if(logger.isDebugEnabled()){
                logger.debug("Sócio torcedor com e-amil: {} já cadastrado", socioTorcedorResource.getEmail());
            }
            throw new SocioTorcedorJaCadastradoException();
        }
    }

    /*
     * Os métodos abaixo capturam a exceção e retornam o tatus e menssagem de erro de acordo com o tipo da exceção
     */

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(SocioTorcedorJaCadastradoException.class)
    @ResponseBody
    public ErrorInfo handleSocioTorcedorJaCadastradoException( SocioTorcedorJaCadastradoException ex) {
        return new ErrorInfo(ServletUriComponentsBuilder.fromCurrentRequest().path("").toUriString() ,ex);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorInfo handleInternalServerError(Exception ex) {
        return new ErrorInfo(ServletUriComponentsBuilder.fromCurrentRequest().path("").toUriString() , ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ErrorInfo handleHttpMessageNotReadableException( HttpMessageNotReadableException ex) {
        return new ErrorInfo(ServletUriComponentsBuilder.fromCurrentRequest().path("").toUriString() ,ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorInfo handleValidationException( MethodArgumentNotValidException ex) {
        return new ErrorInfo(ServletUriComponentsBuilder.fromCurrentRequest().path("").toUriString() ,ex);
    }
}
