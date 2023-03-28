package br.com.letscode.livraria.repository;


import br.com.letscode.livraria.model.entity.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<LivroEntity, Long> {

    Optional<LivroEntity> findByIsbn(String isbn);

    @Query("SELECT entity FROM LivroEntity entity WHERE entity.id = :id AND entity.isbn <> :isbn")
    Optional<LivroEntity> findUpdate(Long id, String isbn);

    @Query("SELECT entity FROM LivroEntity entity WHERE entity.categoria.id = :id")
   List<LivroEntity> findByCategoria(Long id);

    @Query("SELECT entity FROM LivroEntity entity WHERE entity.editora.id = :id")
    List<LivroEntity> findByEditora(Long id);

    @Query("SELECT entity FROM LivroEntity entity WHERE UPPER(entity.nome) LIKE CONCAT('%',UPPER(:nome),'%') AND entity.isbn LIKE CONCAT('%',:isbn,'%')")
    List<LivroEntity> findByNomeOrIsbn(String nome, String isbn);

    @Query("SELECT entity FROM LivroEntity entity WHERE entity.isbn LIKE CONCAT('%',:isbn,'%')")
    List<LivroEntity> findLikeIsbn(String isbn);
    @Query("SELECT entity FROM LivroEntity entity WHERE UPPER(entity.nome) LIKE CONCAT('%',UPPER(:nome),'%')")
    List<LivroEntity> findLikeNome(String nome);
}