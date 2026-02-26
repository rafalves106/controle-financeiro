/**
 * @author falvesmac
 */

package br.com.falves.service;

import br.com.falves.dao.MovimentacaoDAO;
import br.com.falves.domain.Movimentacao;

import java.math.BigDecimal;
import java.util.Collection;

public class MovimentacaoService {
    private final MovimentacaoDAO dao;

    public MovimentacaoService() {
        this.dao = new MovimentacaoDAO();
    }

    public void cadastrarMovimentacao(Movimentacao m) {
        try {
            Boolean sucesso = dao.cadastrar(m);
            if (!sucesso) {
                throw new RuntimeException("Erro interno ao persistir no banco. Verifique os logs do servidor.");
            }
        } catch (Exception e) {
            System.err.println("Erro no Service: " + e.getMessage());
            throw e;
        }
    }

    public void excluirMovimentacao(Long id) {
        try {
            Long idValido = dao.consultar(id).getId();

            dao.excluir(idValido);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void alterarMovimentacao(Long idOriginal ,Movimentacao alterada) {
        try {
            if(alterada.getDescricao() == null){
                throw new IllegalArgumentException("Descrição inválida.");
            } else if (alterada.getTipo() == null) {
                throw new IllegalArgumentException("Tipo inválido.");
            } else if (alterada.getValor() == null || alterada.getValor().compareTo(BigDecimal.ZERO) <= 0) {
                throw new IllegalArgumentException("Valor inválido.");
            }

            alterada.setId(idOriginal);

            dao.alterar(alterada);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Movimentacao consultarMovimentacao(Long id) {

        if (id == null || id <= 0){
            throw new IllegalArgumentException("ID inválido.");
        }

        try {
            Movimentacao m = dao.consultar(id);
            if (m == null){
                throw new IllegalArgumentException("Movimentação com ID: " + id + " não encontrada.");
            }
            return m;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Collection<Movimentacao> buscarTodasMovimentacoes() {
        Collection<Movimentacao> movimentacoes = dao.buscarTodas();

        if (movimentacoes.isEmpty()){
            throw new IllegalArgumentException("Nenhuma movimentação cadastrada.");
        }

        return movimentacoes;
    }
}