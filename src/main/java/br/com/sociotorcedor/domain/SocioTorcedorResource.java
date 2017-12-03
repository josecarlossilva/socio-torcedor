package br.com.sociotorcedor.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.google.common.base.MoreObjects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@ApiModel(value="SocioTorcedorResource", description="Representa os dados do sócio torcedor que devem ser recebidos e retornados pela API Rest de sócio torcedor")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SocioTorcedorResource {

    @Size(min=5, max=100, message="O nome completo tem capacidade de 5 a 100 caracteres.")
    @NotNull(message="O nome completo do sócio torcedor é obrigatório!")
    @ApiModelProperty(value = "Nome completo do Sócio Torcedor", dataType = "string", required = true)
    private String nomeCompleto;

    @Size(min=5, max=150, message="E-mail tem capacidade de 5 a 150 caracteres.")
    @NotNull(message="E-mail é obrigatório!")
    @Email(message = "O e-mail esta com formato inválido")
    @ApiModelProperty(value = "E-mail do Sócio Torcedor", dataType = "string", required = true)
    private String email;

    @NotNull(message="Data de nascimento é obrigatório!")
    @JsonSerialize(using = ToStringSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @ApiModelProperty(value = "Data de nascimento do Sócio Torcedor", dataType = "date", required = true)
    private LocalDate dataNascimento;

    @Size(min=5, max=100, message="Time do coração tem a capacidade de 5 a 100 caracteres.")
    @NotNull(message="A identifição do time do coração é obrigatório!")
    @ApiModelProperty(value = "Identificação do time do coração do Sócio Torcedor", dataType = "string", required = true)
    private String timeCoracao;

    SocioTorcedorResource() {
    }

    public SocioTorcedorResource(String nomeCompleto, String email, LocalDate dataNascimento, String timeCoracao) {
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.timeCoracao = timeCoracao;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTimeCoracao() {
        return timeCoracao;
    }

    public void setTimeCoracao(String timeCoracao) {
        this.timeCoracao = timeCoracao;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("nomeCompleto", nomeCompleto)
                .add("email", email)
                .add("dataNascimento", dataNascimento)
                .add("timeCoracao", timeCoracao)
                .toString();
    }
}
