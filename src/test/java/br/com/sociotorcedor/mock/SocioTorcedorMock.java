package br.com.sociotorcedor.mock;

import br.com.sociotorcedor.domain.SocioTorcedor;
import br.com.sociotorcedor.domain.SocioTorcedorResource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SocioTorcedorMock {

    public static List<SocioTorcedor> getSocioTorcedores(){
        List<SocioTorcedor> socios = new ArrayList();
        socios.add(new SocioTorcedor("José Carlos", "josecarlossill@gmail.com",
                LocalDate.of(1981, 05 , 14), "Corinthians"));

        socios.add(new SocioTorcedor("Joao Silva", "jjsilva@gmaiil.com",
                LocalDate.of(1955, 02 , 22), "Vasco"));

        return socios;
    }

    public static SocioTorcedorResource criaSocioTorcedorResource(){

        return new SocioTorcedorResource("José Carlos", "josecarlossill@gmail.com",
                LocalDate.of(1981, 05 , 14), "TimeDoCoracao");
    }
}
