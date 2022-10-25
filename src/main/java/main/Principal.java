package main;

import java.util.Set;

import modelo.Alumno;
import modelo.Bocadillo;
import modelo.Pedido;
import persistencia.AlumnoDAO;
import persistencia.BocadilloDAO;
import persistencia.PedidoDAO;

public class Principal {

	public static void main(String[] args) {
//		Alumno a = new Alumno("Adrian");
		AlumnoDAO aDAO = new AlumnoDAO();
		Alumno a = aDAO.buscarIDJPA(1);
//		aDAO.insertarAlumnoJPA(a);
//		a.setNombre("Paco");
//		aDAO.modificarAlumno(a);
//		aDAO.eliminarAlumno(a);
//		aDAO.imprimirAlumnos(aDAO.listarAlumnos());
//		System.out.println(aDAO.readAlumno(7).toString());

//		aDAO.insertarAlumnoHibernate(a);
//		aDAO.insertarAlumnoHibernate(a);
//		aDAO.imprimirAlumnos(aDAO.listarAlumnosHibernate());
//		aDAO.buscarIDHibernate(3);
		
//		Pedido p = new Pedido(a);
		PedidoDAO pDAO = new PedidoDAO();
		Pedido p = pDAO.buscarIDJPA(1);
		
//		p.setAlumno(a);
//		a.getPedidos().add(p);
		
//		pDAO.insertarPedidoJPA(p);
		
//		Bocadillo b = new Bocadillo("Jamon");
		BocadilloDAO bDAO = new BocadilloDAO();
		Bocadillo b = bDAO.buscarIDJPA(1);
		
//		p.getBocadillos().add(b);
//		b.getPedidos().add(p);
		
//		bDAO.insertarBocadilloJPA(b);
		
		p.setAlumno(a);
		a.getPedidos().add(p);
		
		p.getBocadillos().add(b);
		b.getPedidos().add(p);
		
		aDAO.imprimirAlumnos(aDAO.listarAlumnosJPA());
	}

}