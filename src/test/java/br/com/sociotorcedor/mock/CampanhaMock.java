package br.com.sociotorcedor.mock;

import br.com.sociotorcedor.domain.CampanhaResource;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CampanhaMock {

    public static List<CampanhaResource> getCampanhasResource(){

        List<CampanhaResource> campanhas = new ArrayList<>();
        campanhas.add(new CampanhaResource("Campanha 2",  "TimeDoCoracao",
                LocalDate.of(2017,10,05),LocalDate.of(2017,10,9)));

        campanhas.add(new CampanhaResource("Campanha 4",  "TIME-1004",
                LocalDate.of(2017,10,10),LocalDate.of(2017,10,20)));

        return campanhas;
    }
}
