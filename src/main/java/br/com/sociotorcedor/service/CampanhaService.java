package br.com.sociotorcedor.service;

import br.com.sociotorcedor.domain.CampanhaResource;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(name = "campanhaService", url = "http://localhost:8080/api")
public interface CampanhaService {

    @RequestMapping("/v1/campanhas/time-coracao/{timeCoracao}")
    List<CampanhaResource> getCampanhasByTimeCoracao(@PathVariable("timeCoracao") String cep);
}
