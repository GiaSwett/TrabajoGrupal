/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import Logica.ListaTareas;
import Persistencia.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Itati
 */
public class ListaTareasJpaController implements Serializable {

    public ListaTareasJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

      public ListaTareasJpaController() {
        emf = Persistence.createEntityManagerFactory("PU_Lista");
    }
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ListaTareas listaTareas) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(listaTareas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ListaTareas listaTareas) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            listaTareas = em.merge(listaTareas);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = listaTareas.getId();
                if (findListaTareas(id) == null) {
                    throw new NonexistentEntityException("The listaTareas with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ListaTareas listaTareas;
            try {
                listaTareas = em.getReference(ListaTareas.class, id);
                listaTareas.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The listaTareas with id " + id + " no longer exists.", enfe);
            }
            em.remove(listaTareas);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ListaTareas> findListaTareasEntities() {
        return findListaTareasEntities(true, -1, -1);
    }

    public List<ListaTareas> findListaTareasEntities(int maxResults, int firstResult) {
        return findListaTareasEntities(false, maxResults, firstResult);
    }

    private List<ListaTareas> findListaTareasEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ListaTareas.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ListaTareas findListaTareas(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ListaTareas.class, id);
        } finally {
            em.close();
        }
    }

    public int getListaTareasCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ListaTareas> rt = cq.from(ListaTareas.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
