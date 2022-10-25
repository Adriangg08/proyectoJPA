package persistencia;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import modelo.Alumno;
import utils.HibernateUtil;
import utils.JPAUtil;

public class AlumnoDAO {


	 public void insertarAlumnoJPA(Alumno alumno) {
		 
		 EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
		 
		 try {
			em.getTransaction().begin();
			em.persist(alumno);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		}
		 
	 }
	 
	 public void insertarAlumnoHibernate(Alumno alumno) {
		 
		 Transaction tr = null;
		 Session session = null;
		 
		 try {
			session = HibernateUtil.getSessionFactory().openSession();
			tr = session.beginTransaction();
			session.persist(alumno);
			tr.commit();
		} catch (PersistenceException e) {
			tr.rollback();
			System.err.println(e.getMessage());
		} finally {
			session.close();
		}
		 
	 }
	 
	 public void modificarAlumnoHibernate (Alumno alumno) {
		 
		 Transaction tr = null;
		 Session session = null;
		 
		 try {
				session = HibernateUtil.getSessionFactory().openSession();
				tr = session.beginTransaction();
				session.merge(alumno);
				tr.commit();
			} catch (PersistenceException e) {
				tr.rollback();
				System.err.println(e.getMessage());
			} finally {
				session.close();
			}
	 }
	 
	 public void modificarAlumnoJPA (Alumno alumno) {
		
		 EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
		 
		 try {
			em.getTransaction().begin();
			em.merge(alumno);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		} 
	 }

	 public void eliminarAlumnoJPA (Alumno alumno) {

		 EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
		 
		 try {
			Alumno a = em.find(Alumno.class, alumno.getId());
			em.getTransaction().begin();
			em.remove(a);
			em.getTransaction().commit();
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		} 
	 }
	 
	 public void eliminarAlumnoHibernate (Alumno alumno) {
		 
		 Transaction tr = null;
		 Session session = null;
		 
		 try {
				session = HibernateUtil.getSessionFactory().openSession();
				tr = session.beginTransaction();
				session.delete(alumno);
				tr.commit();
			} catch (PersistenceException e) {
				tr.rollback();
				System.err.println(e.getMessage());
			} finally {
				session.close();
			}
	 }
	 
	 public ArrayList<Alumno> listarAlumnosJPA () {
		
		 EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
		 
		 try {
			em.getTransaction().begin();
			ArrayList<Alumno> listaAlumnos = (ArrayList<Alumno>) em.createQuery("from Alumno").getResultList();
			em.getTransaction().commit();
			return listaAlumnos;
		} catch (PersistenceException e) {
			em.getTransaction().rollback();
			System.err.println(e.getMessage());
		} finally {
			em.close();
		} 
		 
		 return null;
	 }
	 
	 public ArrayList<Alumno> listarAlumnosHibernate () {
			
		 Transaction tr = null;
		 Session session = null;
		 
		 try {
				session = HibernateUtil.getSessionFactory().openSession();
				tr = session.beginTransaction();
				ArrayList<Alumno> listaAlumnos = (ArrayList<Alumno>) session.createQuery("from Alumno").getResultList();
				tr.commit();
				return listaAlumnos;
			} catch (PersistenceException e) {
				tr.rollback();
				System.err.println(e.getMessage());
			} finally {
				session.close();
			}
		 return null;
	 }
	 
	 public void imprimirAlumnos(ArrayList<Alumno> listaAlumnos) {
		 
		 for (Alumno a: listaAlumnos) {
			 a.imprimir();
		 }
	 }
	 
	 public Alumno readAlumno(int id) {
		 
		 EntityManager em =  JPAUtil.getEntityManagerFactory().createEntityManager();
		 
		 Alumno al = em.find(Alumno.class, id);
		 
		 return al;
	 }
	 
	 
	 public Alumno buscarIDJPA(int id) {
	        EntityManager entity = JPAUtil.getEntityManagerFactory().createEntityManager();
	        try {
	            entity.getTransaction().begin();
	            Alumno alumno = entity.find(Alumno.class, id);
	            entity.getTransaction().commit();
	            System.out.println("El alumno del id " +id+ " es " +alumno.getNombre());
	            return alumno;
	        } catch (PersistenceException exception) {
	            entity.getTransaction().rollback();
	            System.out.println(exception.getMessage());
	        } finally {
	            entity.close();
	        }
	        return null;    
	    }
	 
	 public Alumno buscarIDHibernate(int id) {
		 Transaction tr = null;
		 Session session = null;
		 Alumno resultado = null;
		 
		 try {
				session = HibernateUtil.getSessionFactory().openSession();
				tr = session.beginTransaction();
				resultado = session.find(Alumno.class, id);
				tr.commit();
				System.out.println("El alumno del id " +id+ " es " +resultado.toString());
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
