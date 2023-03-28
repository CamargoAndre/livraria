package br.com.letscode.livraria.model.dto;

import br.com.letscode.livraria.util.MessageUtils;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {
    private Long id;
    @NotBlank(message = MessageUtils.NAME_IS_REQUIRED)
    @Size(max = 100)
    private String nome;
}
