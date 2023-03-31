package br.com.letscode.livraria.service;

import br.com.letscode.livraria.model.dto.UsuarioDTO;
import br.com.letscode.livraria.model.dto.UsuarioSenhaDTO;
import br.com.letscode.livraria.model.entity.UsuarioEntity;
import br.com.letscode.livraria.model.mapper.UsuarioMapper;
import br.com.letscode.livraria.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private UsuarioMapper mapper;

    @Transactional
    public UsuarioDTO save(UsuarioSenhaDTO dto){
        UsuarioEntity entity = mapper.toEntity(dto);
        repository.save(entity);
        return mapper.toDto(entity);
    }
}
