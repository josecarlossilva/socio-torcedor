package br.com.sociotorcedor.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.google.common.base.MoreObjects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

@ApiModel(value="CampanhaResource", description="Representa os dados da campanha que devem ser recebidos pela API Rest de campanha")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CampanhaResource {

    @ApiModelProperty(value = "Nome da campanha", dataType = "string", required = true)
    private String nome;

    @ApiModelProperty(value = "Id do time do coração", dataType = "string", required = true)
    private String timeCoracaoId;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @ApiModelProperty(value = "Data de inicio de vigência", dataType = "date", required = true)
    private LocalDate inicioVigencia;

    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @ApiModelProperty(value = "Data de fim de vigência", dataType = "date", required = true)
    private LocalDate fimVigencia;

    @JsonIgnore
    private String chave;

    public CampanhaResource() {
    }

    public CampanhaResource(String nome, String timeCoracaoId, LocalDate inicioVigencia, LocalDate fimVigencia) {
        this.nome = nome;
        this.timeCoracaoId = timeCoracaoId;
        this.inicioVigencia = inicioVigencia;
        this.fimVigencia = fimVigencia;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTimeCoracaoId() {
        return timeCoracaoId;
    }

    public void setTimeCoracaoId(String timeCoracaoId) {
        this.timeCoracaoId = timeCoracaoId;
    }

    public LocalDate getInicioVigencia() {
        return inicioVigencia;
    }

    public void setInicioVigencia(LocalDate inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
    }

    public LocalDate getFimVigencia() {
        return fimVigencia;
    }

    public void setFimVigencia(LocalDate fimVigencia) {
        this.fimVigencia = fimVigencia;
    }

    public String getChave() {
        return chave;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("nome", nome)
                .add("timeCoracaoId", timeCoracaoId)
                .add("inicioVigencia", inicioVigencia)
                .add("fimVigencia", fimVigencia)
                .toString();
    }
}
