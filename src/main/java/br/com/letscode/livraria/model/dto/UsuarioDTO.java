package br.com.letscode.livraria.model.dto;


import lombok.Data;

@Data
public class UsuarioDTO {


    private Long id;

    private String email;

    private String nome;

    private String username;

    private String cpf;

}
