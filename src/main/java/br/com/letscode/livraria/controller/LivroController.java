package br.com.letscode.livraria.controller;

import br.com.letscode.livraria.model.dto.LivroDTO;
import br.com.letscode.livraria.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/livro")
public class LivroController {
    @Autowired
    private LivroService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LivroDTO> save(@Valid @RequestBody LivroDTO dto){
        return ResponseEntity.ok(service.save(dto));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LivroDTO> update(@Valid @RequestBody LivroDTO dto){
        return ResponseEntity.ok(service.update(dto));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LivroDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LivroDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LivroDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }

    //Listar livro por categoria
    @GetMapping(value = "/categoria/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LivroDTO>> findByCategoria (@PathVariable Long id){
        return ResponseEntity.ok(service.findByCategoria(id));
    }

    //Listar livro por Editora
    @GetMapping(value = "/editora/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LivroDTO>> findByEditora (@PathVariable Long id) {
        return ResponseEntity.ok(service.findByEditora(id));
    }

    //Buscar Livro pelo Nome ou Pelo Isbn ou pelos dois
    @GetMapping(value= "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LivroDTO>> findByNomeOrIsbn(@RequestParam(name = "nome", defaultValue = "") String nome,
                                                           @RequestParam(name = "isbn", defaultValue = "") String isbn){
        return ResponseEntity.ok(service.findByNomeOrIsbn(nome, isbn));
    }

}
