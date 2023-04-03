package br.com.letscode.livraria.service;

import br.com.letscode.livraria.model.entity.UsuarioEntity;
import br.com.letscode.livraria.repository.UsuarioRepository;
import br.com.letscode.livraria.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginUserService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioEntity> usuarioOp = repository.findByUsername(username);

        if(usuarioOp.isPresent()){
            return usuarioOp.get();
        }
        throw new SecurityException(MessageUtils.LOGIN_OR_PASSWORD_NOT_FOUND);
    }
}
