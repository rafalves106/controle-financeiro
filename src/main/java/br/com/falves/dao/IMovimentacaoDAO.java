package br.com.falves.dao;

import br.com.falves.domain.Movimentacao;

import java.util.Collection;

public interface IMovimentacaoDAO {
    public Boolean cadastrar(Movimentacao m);

    public void excluir(Long id);

    public void alterar(Movimentacao m);

    public Movimentacao consultar(Long id);

    public Collection<Movimentacao> buscarTodas();
}
