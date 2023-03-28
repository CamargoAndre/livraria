package br.com.letscode.livraria.repository;

import br.com.letscode.livraria.model.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {
    Optional<CategoriaEntity> findByNome(String nome);

    @Query("SELECT entity FROM CategoriaEntity  entity WHERE entity.nome = :nome AND entity.id <> :id ")
    Optional<CategoriaEntity> findByUpdate(String nome, Long id);
}
