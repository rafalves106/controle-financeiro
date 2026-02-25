/**
 * @author falvesmac
 */

package br.com.falves.dao;

import br.com.falves.domain.Movimentacao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Collection;

public class MovimentacaoDAO implements IMovimentacaoDAO {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("controle-financeiro");

    @Override
    public Boolean cadastrar(Movimentacao m) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(m);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println(e.getMessage());
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public Movimentacao consultar(Long id) {
        EntityManager em = emf.createEntityManager();

        try {
            return em.find(Movimentacao.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public Collection<Movimentacao> buscarTodas() {
        EntityManager em = emf.createEntityManager();

        try {
            return em.createQuery("FROM Movimentacao", Movimentacao.class).getResultList();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    @Override
    public void alterar(Movimentacao m) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(m);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }

    @Override
    public void excluir(Long id) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Movimentacao m = em.find(Movimentacao.class, id);
            if (m != null) {
                em.remove(m);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }
}