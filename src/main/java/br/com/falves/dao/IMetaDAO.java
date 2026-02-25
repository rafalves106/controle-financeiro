package br.com.falves.dao;

import br.com.falves.domain.Meta;

import java.util.Collection;

public interface IMetaDAO {
    public Boolean cadastrar(Meta m);

    public void excluir(Long id);

    public Collection<Meta> buscarTodas();

    public Meta consultar(Long id);
}
