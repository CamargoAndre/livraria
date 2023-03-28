package br.com.letscode.livraria.service;

import br.com.letscode.livraria.exceptions.BusinessException;
import br.com.letscode.livraria.exceptions.NotFoundException;
import br.com.letscode.livraria.model.dto.EditoraDTO;
import br.com.letscode.livraria.model.entity.EditoraEntity;
import br.com.letscode.livraria.model.mapper.EditoraMapper;
import br.com.letscode.livraria.repository.EditoraRepository;
import br.com.letscode.livraria.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EditoraService {

    @Autowired
    private EditoraRepository repository;
    @Autowired
    private EditoraMapper mapper;

    @Transactional
    public EditoraDTO save(EditoraDTO dto) {
        Optional<EditoraEntity> optionalEntity = repository.findByNome(dto.getNome());
        if(optionalEntity.isPresent()){
            throw new BusinessException(MessageUtils.EDITORA_ALREADY_EXISTS);
        }
        EditoraEntity entity = mapper.toEntity(dto);
        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Transactional
    public EditoraDTO update(EditoraDTO dto) {

        this.findById(dto.getId());
        Optional<EditoraEntity> optionalEntity = repository.findByUpdate(dto.getNome(), dto.getId());

       if(optionalEntity.isPresent()){
            throw new BusinessException(MessageUtils.EDITORA_ALREADY_EXISTS);
        }
        EditoraEntity entity = mapper.toEntity(dto);
        repository.save(entity);
        return mapper.toDto(entity);
    }

    @Transactional(readOnly = true)
    public List<EditoraDTO> findAll() {
        return mapper.toDto(repository.findAll());
    }

    @Transactional(readOnly = true)
    public EditoraDTO findById(Long id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public EditoraDTO delete(Long id) {

        EditoraDTO dto = this.findById(id);
        repository.deleteById(id);
        return dto;
    }
}
