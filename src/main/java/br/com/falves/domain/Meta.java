/**
 * @author falvesmac
 */

package br.com.falves.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_meta")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Meta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "A descrição da meta não pode ser nula.")
    private String descricao;

    @Column(nullable = false)
    @NotNull(message = "O valor da meta não pode ser nulo.")
    private BigDecimal valor;
}