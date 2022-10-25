package persistencia;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.Ingrediente;
import modelo.Pedido;
import utils.HibernateUtil;
import utils.JPAUtil;

public class IngredienteDAO {

	public void insertarIngredienteJPA(Ingrediente ingrediente) {
		 
		 EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
		 
		 try {
			em.getTransaction().begin();
			em.persist(ingrediente);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());   
		} finally {
			em.close();
		}
		 
	 }
	
	public void insertarIngredienteHibernate(Ingrediente ingrediente) {
		 
		 Transaction tr = null;
		 Session session = null;
		 
		 try {
			session = HibernateUtil.getSessionFactory().openSession();
			tr = session.beginTransaction();
			session.persist(ingrediente);
			tr.commit();
		} catch (PersistenceException e) {
			tr.rollback();
			System.err.println(e.getMessage());
		} finally {
			session.close();
		}
		 
	 }
	
	public void modificarIngredienteHibernate (Ingrediente ingrediente) {
		 
		 Transaction tr = null;
		 Session session = null;
		 
		 try {
				session = HibernateUtil.getSessionFactory().openSession();
				tr = session.beginTransaction();
				session.merge(ingrediente);
				tr.commit();
			} catch (PersistenceException e) {
				tr.rollback();
				System.err.println(e.getMessage());
			} finally {
				session.close();
			}
	 }
	 
	 public void modificarIngredienteJPA (Ingrediente ingrediente) {
		
		 EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
		 
		 try {
			em.getTransaction().begin();
			em.merge(ingrediente);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		} 
	 }

	 public void eliminarIngredienteJPA (Ingrediente ingrediente) {

		 EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
		 
		 try {
			Ingrediente i = em.find(Ingrediente.class, ingrediente.getId());
			em.getTransaction().begin();
			em.remove(i);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		} 
	 }
	 
	 public void eliminarIngredienteHibernate (Ingrediente ingrediente) {
		 
		 Transaction tr = null;
		 Session session = null;
		 
		 try {
				session = HibernateUtil.getSessionFactory().openSession();
				tr = session.beginTransaction();
				session.delete(ingrediente);
				tr.commit();
			} catch (PersistenceException e) {
				tr.rollback();
				System.err.println(e.getMessage());
			} finally {
				session.close();
			}
	 }
	 
	 public ArrayList<Ingrediente> listarIngredientesJPA () {
		
		 EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
		 
		 try {
			em.getTransaction().begin();
			ArrayList<Ingrediente> listaingredientes = (ArrayList<Ingrediente>) em.createQuery("from Ingrediente").getResultList();
			em.getTransaction().commit();
			return listaingredientes;
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		} 
		 
		 return null;
	 }
	 
	 public ArrayList<Ingrediente> listarIngredientesHibernate () {
			
		 Transaction tr = null;
		 Session session = null;
		 
		 try {
				session = HibernateUtil.getSessionFactory().openSession();
				tr = session.beginTransaction();
				ArrayList<Ingrediente> listaingredientes = (ArrayList<Ingrediente>) session.createQuery("from Ingrediente").getResultList();
				tr.commit();
				return listaingredientes;
			} catch (PersistenceException e) {
				tr.rollback();
				System.err.println(e.getMessage());
			} finally {
				session.close();
			}
		 return null;
	 }
	 
	 public void imprimirIngredientes(ArrayList<Ingrediente> listaingredientes) {
		 
		 for (Ingrediente a: listaingredientes) {
			 a.imprimir();
		 }
	 }
	 
	 public Ingrediente readIngrediente(int id) {
		 
		 EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
		 
		 Ingrediente al = em.find(Ingrediente.class, id);
		 
		 return al;
	 }
	 
	 
	 public Ingrediente buscarIDJPA(int id) {
	        EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	        try {
	            entity.getTransaction().begin();
	            Ingrediente ingrediente = entity.find(Ingrediente.class, id);
	            entity.getTransaction().commit();
	            System.out.println("El ingrediente de id " +id+ " es " +ingrediente.getNombre());
	            return ingrediente;
	        } catch (PersistenceException exception) {
	            entity.getTransaction().rollback();
	            System.out.println(exception.getMessage());
	        } finally {
	            entity.close();
	        }
	        return null;    
	    }
	 
	 public Ingrediente buscarIDHibernate(int id) {
		 Transaction tr = null;
		 Session session = null;
		 Ingrediente resultado = null;
		 
		 try {
				session = HibernateUtil.getSessionFactory().openSession();
				tr = session.beginTransaction();
				resultado = session.find(Ingrediente.class, id);
				tr.commit();
				System.out.println("El ingrediente del id " +id+ " es " +resultado.toString());
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
