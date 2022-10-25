package persistencia;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.Pedido;
import utils.HibernateUtil;
import utils.JPAUtil;

public class PedidoDAO {


	 public void insertarPedidoJPA(Pedido pedido) {
		 
		 EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
		 
		 try {
			em.getTransaction().begin();
			em.persist(pedido);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());   
		} finally {
			em.close();
		}
		 
	 }
	 
	 public void insertarPedidoHibernate(Pedido pedido) {
		 
		 Transaction tr = null;
		 Session session = null;
		 
		 try {
			session = HibernateUtil.getSessionFactory().openSession();
			tr = session.beginTransaction();
			session.persist(pedido);
			tr.commit();
		} catch (PersistenceException e) {
			tr.rollback();
			System.err.println(e.getMessage());
		} finally {
			session.close();
		}
		 
	 }
	 
	 public void modificarPedidoHibernate (Pedido pedido) {
		 
		 Transaction tr = null;
		 Session session = null;
		 
		 try {
				session = HibernateUtil.getSessionFactory().openSession();
				tr = session.beginTransaction();
				session.merge(pedido);
				tr.commit();
			} catch (PersistenceException e) {
				tr.rollback();
				System.err.println(e.getMessage());
			} finally {
				session.close();
			}
	 }
	 
	 public void modificarPedidoJPA (Pedido pedido) {
		
		 EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
		 
		 try {
			em.getTransaction().begin();
			em.merge(pedido);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		} 
	 }

	 public void eliminarPedidoJPA (Pedido pedido) {

		 EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
		 
		 try {
			Pedido p = em.find(Pedido.class, pedido.getId());
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
	 
	 public void eliminarPedidoHibernate (Pedido pedido) {
		 
		 Transaction tr = null;
		 Session session = null;
		 
		 try {
				session = HibernateUtil.getSessionFactory().openSession();
				tr = session.beginTransaction();
				session.delete(pedido);
				tr.commit();
			} catch (PersistenceException e) {
				tr.rollback();
				System.err.println(e.getMessage());
			} finally {
				session.close();
			}
	 }
	 
	 public ArrayList<Pedido> listarPedidosJPA () {
		
		 EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
		 
		 try {
			em.getTransaction().begin();
			ArrayList<Pedido> listapedidos = (ArrayList<Pedido>) em.createQuery("from Pedido").getResultList();
			em.getTransaction().commit();
			return listapedidos;
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		} 
		 
		 return null;
	 }
	 
	 public ArrayList<Pedido> listarPedidosHibernate () {
			
		 Transaction tr = null;
		 Session session = null;
		 
		 try {
				session = HibernateUtil.getSessionFactory().openSession();
				tr = session.beginTransaction();
				ArrayList<Pedido> listapedidos = (ArrayList<Pedido>) session.createQuery("from Pedido").getResultList();
				tr.commit();
				return listapedidos;
			} catch (PersistenceException e) {
				tr.rollback();
				System.err.println(e.getMessage());
			} finally {
				session.close();
			}
		 return null;
	 }
	 
	 public void imprimirPedidos(ArrayList<Pedido> listapedidos) {
		 
		 for (Pedido a: listapedidos) {
			 a.imprimir();
		 }
	 }
	 
	 public Pedido readPedido(int id) {
		 
		 EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
		 
		 Pedido al = em.find(Pedido.class, id);
		 
		 return al;
	 }
	 
	 
	 public Pedido buscarIDJPA(int id) {
	        EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	        try {
	            entity.getTransaction().begin();
	            Pedido pedido = entity.find(Pedido.class, id);
	            entity.getTransaction().commit();
	            System.out.println("El pedido de id " +id);
	            return pedido;
	        } catch (PersistenceException exception) {
	            entity.getTransaction().rollback();
	            System.out.println(exception.getMessage());
	        } finally {
	            entity.close();
	        }
	        return null;    
	    }
	 
	 public Pedido buscarIDHibernate(int id) {
		 Transaction tr = null;
		 Session session = null;
		 Pedido resultado = null;
		 
		 try {
				session = HibernateUtil.getSessionFactory().openSession();
				tr = session.beginTransaction();
				resultado = session.find(Pedido.class, id);
				tr.commit();
				System.out.println("El pedido del id " +id+ " es " +resultado.toString());
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
