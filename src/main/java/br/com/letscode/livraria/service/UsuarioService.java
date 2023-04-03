package br.com.letscode.livraria.service;

import br.com.letscode.livraria.model.dto.TokenDTO;
import br.com.letscode.livraria.model.dto.UsuarioDTO;
import br.com.letscode.livraria.model.dto.UsuarioLoginDTO;
import br.com.letscode.livraria.model.dto.UsuarioSenhaDTO;
import br.com.letscode.livraria.model.entity.UsuarioEntity;
import br.com.letscode.livraria.model.mapper.UsuarioMapper;
import br.com.letscode.livraria.repository.UsuarioRepository;
import br.com.letscode.livraria.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UsuarioMapper mapper;

    @Transactional
    public UsuarioDTO save(UsuarioSenhaDTO dto){
        UsuarioEntity entity = mapper.toEntity(dto);
        repository.save(entity);
        return mapper.toDto(entity);
    }

    public TokenDTO login(UsuarioLoginDTO dto) {
        UsernamePasswordAuthenticationToken authentication =
                UsernamePasswordAuthenticationToken.authenticated(dto.getUsername(), dto.getPassword(), null);
        if(!authentication.isAuthenticated()){
            throw new SecurityException(MessageUtils.LOGIN_OR_PASSWORD_NOT_FOUND);
        }
        Authentication auth = authManager.authenticate(authentication);
        if (auth.isAuthenticated()) {
            UsuarioEntity entity = (UsuarioEntity) auth.getPrincipal();
            String token = jwtService.gerarToken(mapper.toDto(entity));
            return new TokenDTO("Bearer",token);
        }

        throw new SecurityException(MessageUtils.LOGIN_OR_PASSWORD_NOT_FOUND);
    }

}
