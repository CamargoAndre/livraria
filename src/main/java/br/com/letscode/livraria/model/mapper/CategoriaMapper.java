package br.com.letscode.livraria.model.mapper;

import br.com.letscode.livraria.model.dto.CategoriaDTO;
import br.com.letscode.livraria.model.entity.CategoriaEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoriaMapper {
    public CategoriaEntity toEntity(CategoriaDTO dto) {

        CategoriaEntity entity = new CategoriaEntity();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());

        return entity;
    }

    public CategoriaDTO toDto(CategoriaEntity entity) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        return dto;

    }

    public List<CategoriaDTO> toDto(List<CategoriaEntity> entities){
        return entities.stream().map(this::toDto).collect(Collectors.toList());
    }
}
