package br.com.letscode.livraria.model.dto;

import br.com.letscode.livraria.util.MessageUtils;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroDTO {

    private Long id;
    @NotNull
    private EditoraDTO editoraDTO;
    @NotNull
    private CategoriaDTO categoriaDTO;
    @NotNull(message = MessageUtils.NAME_IS_REQUIRED)
    private String nome;
    @NotNull
    private String isbn;
}
