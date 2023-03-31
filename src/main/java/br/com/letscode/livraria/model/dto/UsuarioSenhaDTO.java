package br.com.letscode.livraria.model.dto;

import lombok.Data;

@Data
public class UsuarioSenhaDTO extends UsuarioDTO {

    private String password;
}
