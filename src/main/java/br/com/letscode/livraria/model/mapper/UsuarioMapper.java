package br.com.letscode.livraria.model.mapper;

import br.com.letscode.livraria.model.dto.UsuarioDTO;
import br.com.letscode.livraria.model.dto.UsuarioSenhaDTO;
import br.com.letscode.livraria.model.entity.UsuarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    @Autowired
    private PasswordEncoder encoder;

    public UsuarioEntity toEntity(UsuarioSenhaDTO dto){
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setCpf(dto.getCpf());
        entity.setUsername(dto.getUsername());

        entity.setPassword(encoder.encode(dto.getPassword()));

        return entity;
    }

    public UsuarioDTO toDto(UsuarioEntity entity){
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setCpf(entity.getCpf());
        dto.setUsername(entity.getUsername());

        return dto;
    }
}
