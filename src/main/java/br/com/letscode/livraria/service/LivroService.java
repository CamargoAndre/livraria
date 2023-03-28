package br.com.letscode.livraria.service;

import br.com.letscode.livraria.exceptions.BusinessException;
import br.com.letscode.livraria.exceptions.NotFoundException;
import br.com.letscode.livraria.model.dto.LivroDTO;
import br.com.letscode.livraria.model.entity.LivroEntity;
import br.com.letscode.livraria.model.mapper.LivroMapper;
import br.com.letscode.livraria.repository.LivroRepository;
import br.com.letscode.livraria.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    @Autowired
    private LivroRepository repository;
    @Autowired
    private LivroMapper mapper;

    @Transactional
    public LivroDTO save(LivroDTO dto) {
        Optional<LivroEntity> optionalEntity = repository.findByIsbn(dto.getIsbn());
        if(optionalEntity.isPresent()){
            throw new BusinessException(MessageUtils.LIVRO_ALREADY_EXISTIS);
        }
        LivroEntity entity = mapper.toEntity(dto);
        repository.save(entity);
        return mapper.toDto(entity);
    }
    @Transactional
    public LivroDTO update(LivroDTO dto) {

        this.findById(dto.getId());
        Optional<LivroEntity> optionalEntity = repository.findUpdate(dto.getId(), dto.getIsbn());
        if(optionalEntity.isPresent()){
            throw new BusinessException(MessageUtils.LIVRO_ALREADY_EXISTIS);
        }
        LivroEntity entity = mapper.toEntity(dto);
        repository.save(entity);
        return mapper.toDto(entity);

    }
    @Transactional(readOnly = true)
    public List<LivroDTO> findAll() {
        return mapper.toDto(repository.findAll());
    }

    @Transactional(readOnly = true)
    public LivroDTO findById(Long id) {
        return repository.findById(id).map(mapper::toDto).orElseThrow(NotFoundException::new);
    }

    @Transactional
    public LivroDTO delete(Long id) {
        LivroDTO dto = this.findById(id);
        repository.deleteById(id);
        return dto;
    }
    @Transactional(readOnly = true)
    public List<LivroDTO> findByCategoria(Long id) {
        return mapper.toDto(repository.findByCategoria(id));
    }
    @Transactional(readOnly = true)
    public List<LivroDTO> findByEditora(Long id) {
        return mapper.toDto(repository.findByEditora(id));
    }
    @Transactional(readOnly = true)
    public List<LivroDTO>  findByNomeOrIsbn(String nome, String isbn) {

        if(nome.equals("")){
            return mapper.toDto(repository.findLikeIsbn(isbn));
        }
        if(isbn.equals("")){
            return mapper.toDto(repository.findLikeNome(nome));
        }

        return mapper.toDto(repository.findByNomeOrIsbn(nome, isbn));
    }
}
