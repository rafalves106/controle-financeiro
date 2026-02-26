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

    public void cadastrarMeta(Meta m) {
        try {
            Boolean sucesso = dao.cadastrar(m);
            if (!sucesso) {
                // Se o DAO retornou false, é porque caiu no catch do DAO
                throw new RuntimeException("Erro interno ao persistir no banco. Verifique os logs do servidor.");
            }
        } catch (Exception e) {
            System.err.println("Erro no Service: " + e.getMessage());
            throw e;
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