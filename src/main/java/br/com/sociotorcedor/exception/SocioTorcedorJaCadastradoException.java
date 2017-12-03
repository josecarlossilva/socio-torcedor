package br.com.sociotorcedor.exception;

public class SocioTorcedorJaCadastradoException extends RuntimeException {

    public SocioTorcedorJaCadastradoException() {
        super("Usuário já cadastrado");
    }
}
