package br.com.letscode.livraria.model.mapper;

import br.com.letscode.livraria.model.dto.EditoraDTO;
import br.com.letscode.livraria.model.dto.LivroDTO;
import br.com.letscode.livraria.model.entity.EditoraEntity;
import br.com.letscode.livraria.model.entity.LivroEntity;
import br.com.letscode.livraria.service.CategoriaService;
import br.com.letscode.livraria.service.EditoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LivroMapper {
    @Autowired
    private EditoraMapper editoraMapper;
    @Autowired
    private EditoraService editoraService;
    @Autowired
    private CategoriaMapper categoriaMapper;
    @Autowired
    private CategoriaService categoriaService;


    public LivroEntity toEntity(LivroDTO dto){

        LivroEntity entity = new LivroEntity();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setIsbn(dto.getIsbn());
        entity.setEditora(editoraMapper.toEntity(editoraService.findById(dto.getEditoraDTO().getId())));
        entity.setCategoria(categoriaMapper.toEntity(categoriaService.findById(dto.getCategoriaDTO().getId())));

        return  entity;
    }


    public LivroDTO toDto(LivroEntity entity){
        LivroDTO dto = new LivroDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setIsbn(entity.getIsbn());
        dto.setEditoraDTO(editoraService.findById(entity.getEditora().getId()));
        dto.setCategoriaDTO(categoriaService.findById(entity.getCategoria().getId()));

        return dto;
    }

    public List<LivroDTO> toDto(List<LivroEntity> entities) {
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }
}
