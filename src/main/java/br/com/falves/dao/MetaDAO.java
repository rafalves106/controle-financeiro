/**
 * @author falvesmac
 */

package br.com.falves.dao;

import br.com.falves.domain.Meta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Collection;

public class MetaDAO implements IMetaDAO{
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("controle-financeiro");

    @Override
    public Boolean cadastrar(Meta m) {
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

    public void excluir(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Meta m = em.find(Meta.class, id);
            if (m != null) {
                em.remove(m);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao excluir meta");
        } finally {
            em.close();
        }
    }


    @Override
    public Collection<Meta> buscarTodas() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("FROM Meta", Meta.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Meta consultar(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Meta.class, id);
        } finally {
            em.close();
        }
    }
}