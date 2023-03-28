package br.com.letscode.livraria.repository;

import br.com.letscode.livraria.model.entity.EditoraEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EditoraRepository extends JpaRepository<EditoraEntity, Long> {
    Optional<EditoraEntity> findByNome(String nome);

    @Query("SELECT entity FROM EditoraEntity entity WHERE entity.nome = :nome AND entity.id <> :id ")
    Optional<EditoraEntity> findByUpdate(String nome, Long id);
}
