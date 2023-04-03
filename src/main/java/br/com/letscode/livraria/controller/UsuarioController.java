package br.com.letscode.livraria.controller;
import br.com.letscode.livraria.model.dto.UsuarioDTO;
import br.com.letscode.livraria.model.dto.UsuarioLoginDTO;
import br.com.letscode.livraria.model.dto.UsuarioSenhaDTO;
import br.com.letscode.livraria.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDTO> save(@Valid @RequestBody UsuarioSenhaDTO dto){
       return ResponseEntity.ok(service.save(dto));
    }

    @PostMapping(value = "/auth")
    public ResponseEntity<Object> logar(@RequestBody UsuarioLoginDTO dto){

        return ResponseEntity.ok(service.login(dto));


    }

}
