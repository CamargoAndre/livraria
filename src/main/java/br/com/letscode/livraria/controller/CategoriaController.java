package br.com.letscode.livraria.controller;

import br.com.letscode.livraria.model.dto.CategoriaDTO;
import br.com.letscode.livraria.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService service;
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriaDTO> save(@Valid @RequestBody CategoriaDTO dto){
        return ResponseEntity.ok(service.save(dto));
    }
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriaDTO> update(@Valid @RequestBody CategoriaDTO dto){
        return ResponseEntity.ok(service.update(dto));
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoriaDTO>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriaDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }
    @DeleteMapping(value = "{id}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoriaDTO> delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }






}
