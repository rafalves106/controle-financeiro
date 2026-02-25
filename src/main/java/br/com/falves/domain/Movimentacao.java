/**
 * @author falvesmac
 */

package br.com.falves.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jdk.jfr.Description;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name="tb_movimentacao")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movimentacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Description("Identificador único da movimentação financeira")
    private Long id;

    @Column(nullable = false)
    @Description("Descrição da movimentação financeira")
    @NotNull(message = "A descrição da movimentação não pode ser nula.")
    private String descricao;

    @Column(nullable = false)
    @Description("Valor da movimentação financeira")
    @NotNull(message = "O valor da movimentação não pode ser nulo.")
    private BigDecimal valor;

    @Column(nullable = false)
    @Description("Tipo da movimentação financeira: RECEITA ou DESPESA")
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O tipo da movimentação não pode ser nulo.")
    private TipoMovimentacao tipo;
}