package br.com.letscode.livraria.service;

import br.com.letscode.livraria.exceptions.BusinessException;
import br.com.letscode.livraria.exceptions.NotFoundException;
import br.com.letscode.livraria.model.dto.CategoriaDTO;
import br.com.letscode.livraria.model.entity.CategoriaEntity;
import br.com.letscode.livraria.model.mapper.CategoriaMapper;
import br.com.letscode.livraria.repository.CategoriaRepository;
import br.com.letscode.livraria.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;
    @Autowired
    private CategoriaMapper mapper;
    @Transactional
    public CategoriaDTO save(CategoriaDTO dto) {

        Optional<CategoriaEntity> optionalEntity = repository.findByNome(dto.getNome());
        if(optionalEntity.isPresent()){
            throw new BusinessException(MessageUtils.EDITORA_ALREADY_EXISTS);
        }
        CategoriaEntity entity = mapper.toEntity(dto);
        repository.save(entity);
        return mapper.toDto(entity);

    }
    @Transactional
    public CategoriaDTO update(CategoriaDTO dto) {
        this.findById(dto.getId());
        Optional<CategoriaEntity> optionalEntity = repository.findByUpdate(dto.getNome(), dto.getId());

        if(optionalEntity.isPresent()){
            throw new BusinessException(MessageUtils.CATEGORIA_ALREADY_EXISTS);
        }
        CategoriaEntity entity = mapper.toEntity(dto);
        repository.save(entity);
        return mapper.toDto(entity);
    }
    @Transactional(readOnly = true)
    public List<CategoriaDTO> findAll() {
        return mapper.toDto(repository.findAll());
    }
    @Transactional(readOnly = true)
    public CategoriaDTO findById(Long id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(NotFoundException::new);
    }
    @Transactional
    public CategoriaDTO delete(Long id) {
        CategoriaDTO dto = findById(id);
        repository.deleteById(id);
        return dto;
    }
}
