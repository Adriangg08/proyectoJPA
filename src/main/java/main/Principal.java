package main;

import java.util.Set;

import modelo.Alumno;
import modelo.Bocadillo;
import modelo.Ingrediente;
import modelo.Pedido;
import persistencia.AlumnoDAO;
import persistencia.BocadilloDAO;
import persistencia.IngredienteDAO;
import persistencia.PedidoDAO;

public class Principal {

	public static void main(String[] args) {
		Alumno a = new Alumno("Adrian");
		AlumnoDAO aDAO = new AlumnoDAO();
//		Alumno a = aDAO.buscarIDJPA(1);
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
		
		Pedido p = new Pedido(a);
		PedidoDAO pDAO = new PedidoDAO();
//		Pedido p = pDAO.buscarIDJPA(1);
		
//		p.setAlumno(a);
//		a.getPedidos().add(p);
		
		p.setAlumno(a);
		a.getPedidos().add(p);
		
		Bocadillo b = new Bocadillo("Jamon");
		BocadilloDAO bDAO = new BocadilloDAO();
//		Bocadillo b = bDAO.buscarIDJPA(1);
		
//		p.getBocadillos().add(b);
//		b.getPedidos().add(p);
		
		p.getBocadillos().add(b);
		b.getPedidos().add(p);
		
		Ingrediente i1 = new Ingrediente("Pan",true);
		Ingrediente i2 = new Ingrediente("Tomate",true);
		Ingrediente i3 = new Ingrediente("Jamon",false);
		IngredienteDAO iDAO = new IngredienteDAO();
		
		b.getIngredientes().add(i1);
		b.getIngredientes().add(i2);
		b.getIngredientes().add(i3);
		
		i1.getBocadillos().add(b);
		i2.getBocadillos().add(b);
		i3.getBocadillos().add(b);
		
		b.getIngredientes().add(i1);
		b.getIngredientes().add(i2);
		b.getIngredientes().add(i3);
		
		aDAO.insertarAlumnoJPA(a);
		
		pDAO.insertarPedidoJPA(p);
		
		bDAO.insertarBocadilloJPA(b);
		
		iDAO.insertarIngredienteJPA(i1);
		iDAO.insertarIngredienteJPA(i2);
		iDAO.insertarIngredienteJPA(i3);
		
		aDAO.imprimirAlumnos(aDAO.listarAlumnosJPA());
	}

}