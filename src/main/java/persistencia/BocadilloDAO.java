package persistencia;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.Bocadillo;
import modelo.Pedido;
import utils.HibernateUtil;
import utils.JPAUtil;

public class BocadilloDAO {

	public void insertarBocadilloJPA(Bocadillo bocadillo) {
		 
		 EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
		 
		 try {
			em.getTransaction().begin();
			em.persist(bocadillo);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());   
		} finally {
			em.close();
		}
		 
	 }
	
	public void insertarBocadilloHibernate(Bocadillo bocadillo) {
		 
		 Transaction tr = null;
		 Session session = null;
		 
		 try {
			session = HibernateUtil.getSessionFactory().openSession();
			tr = session.beginTransaction();
			session.persist(bocadillo);
			tr.commit();
		} catch (PersistenceException e) {
			tr.rollback();
			System.err.println(e.getMessage());
		} finally {
			session.close();
		}
		 
	 }
	
	public void modificarBocadilloHibernate (Bocadillo bocadillo) {
		 
		 Transaction tr = null;
		 Session session = null;
		 
		 try {
				session = HibernateUtil.getSessionFactory().openSession();
				tr = session.beginTransaction();
				session.merge(bocadillo);
				tr.commit();
			} catch (PersistenceException e) {
				tr.rollback();
				System.err.println(e.getMessage());
			} finally {
				session.close();
			}
	 }
	 
	 public void modificarBocadilloJPA (Bocadillo bocadillo) {
		
		 EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
		 
		 try {
			em.getTransaction().begin();
			em.merge(bocadillo);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		} 
	 }

	 public void eliminarBocadilloJPA (Bocadillo bocadillo) {

		 EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
		 
		 try {
			Pedido p = em.find(Pedido.class, bocadillo.getId());
			em.getTransaction().begin();
			em.remove(p);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		} 
	 }
	 
	 public void eliminarPedidoHibernate (Bocadillo bocadillo) {
		 
		 Transaction tr = null;
		 Session session = null;
		 
		 try {
				session = HibernateUtil.getSessionFactory().openSession();
				tr = session.beginTransaction();
				session.delete(bocadillo);
				tr.commit();
			} catch (PersistenceException e) {
				tr.rollback();
				System.err.println(e.getMessage());
			} finally {
				session.close();
			}
	 }
	 
	 public ArrayList<Bocadillo> listarBocadillosJPA () {
		
		 EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
		 
		 try {
			em.getTransaction().begin();
			ArrayList<Bocadillo> listabocadillos = (ArrayList<Bocadillo>) em.createQuery("from Bocadillo").getResultList();
			em.getTransaction().commit();
			return listabocadillos;
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		} 
		 
		 return null;
	 }
	 
	 public ArrayList<Bocadillo> listarBocadillosHibernate () {
			
		 Transaction tr = null;
		 Session session = null;
		 
		 try {
				session = HibernateUtil.getSessionFactory().openSession();
				tr = session.beginTransaction();
				ArrayList<Bocadillo> listabocadillos = (ArrayList<Bocadillo>) session.createQuery("from Bocadillo").getResultList();
				tr.commit();
				return listabocadillos;
			} catch (PersistenceException e) {
				tr.rollback();
				System.err.println(e.getMessage());
			} finally {
				session.close();
			}
		 return null;
	 }
	 
	 public void imprimirBocadillos(ArrayList<Bocadillo> listabocadillos) {
		 
		 for (Bocadillo a: listabocadillos) {
			 a.imprimir();
		 }
	 }
	 
	 public Bocadillo readBocadillo(int id) {
		 
		 EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
		 
		 Bocadillo al = em.find(Bocadillo.class, id);
		 
		 return al;
	 }
	 
	 
	 public Bocadillo buscarIDJPA(int id) {
	        EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	        try {
	            entity.getTransaction().begin();
	            Bocadillo bocadillo = entity.find(Bocadillo.class, id);
	            entity.getTransaction().commit();
	            System.out.println("El bocadillo del id " +id+ " es " +bocadillo.getNombre());
	            return bocadillo;
	        } catch (PersistenceException exception) {
	            entity.getTransaction().rollback();
	            System.out.println(exception.getMessage());
	        } finally {
	            entity.close();
	        }
	        return null;    
	    }
	 
	 public Bocadillo buscarIDHibernate(int id) {
		 Transaction tr = null;
		 Session session = null;
		 Bocadillo resultado = null;
		 
		 try {
				session = HibernateUtil.getSessionFactory().openSession();
				tr = session.beginTransaction();
				resultado = session.find(Bocadillo.class, id);
				tr.commit();
				System.out.println("El bocadillo del id " +id+ " es " +resultado.toString());
				return resultado;
			} catch (PersistenceException e) {
				tr.rollback();
				System.err.println(e.getMessage());
			} finally {
				session.close();
			}
		 return resultado;
	    }
}
