/**
 * @author falvesmac
 */

package br.com.falves.service;

import br.com.falves.dao.MetaDAO;
import br.com.falves.domain.Meta;

import java.util.Collection;

public class MetaService {
    private final MetaDAO dao;

    public MetaService() {
        this.dao = new MetaDAO();
    }

    public void cadastrarMeta(Meta meta) {
        try {
            Boolean estaCadastrada = dao.cadastrar(meta);

            if (!estaCadastrada){
                throw new IllegalArgumentException("Meta com ID: " + meta.getId() + " já está cadastrada.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void excluirMeta(Long id) {
        try {
            Long idValido = dao.consultar(id).getId();

            dao.excluir(idValido);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Collection<Meta> buscarTodasMetas() {
        return dao.buscarTodas();
    }

    public Meta consultarMeta(Long id) {
        return dao.consultar(id);
    }
}