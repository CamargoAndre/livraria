package br.com.letscode.livraria.model.mapper;

import br.com.letscode.livraria.model.dto.EditoraDTO;
import br.com.letscode.livraria.model.entity.EditoraEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EditoraMapper {

    public EditoraEntity toEntity(EditoraDTO dto) {

        EditoraEntity entity = new EditoraEntity();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());

        return entity;
    }

    public EditoraDTO toDto(EditoraEntity entity) {

        EditoraDTO dto = new EditoraDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setDescricao(entity.getDescricao());

        return dto;
    }

    public List<EditoraDTO> toDto(List<EditoraEntity> entities){
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }
}
