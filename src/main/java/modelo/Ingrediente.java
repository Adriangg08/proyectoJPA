package modelo;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="ingredientes")
public class Ingrediente {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="esVegano")
	private boolean esVegano = true;
	
	@ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(
			name = "bocadillos_ingredientes",
			joinColumns = {@JoinColumn(name="id_ingrediente")},
			inverseJoinColumns = {@JoinColumn(name="id_bocadillo")}
			)
	private Set<Bocadillo> bocadillos;

	public Ingrediente(String nombre, boolean esVegano) {
		this.nombre = nombre;
		this.esVegano = esVegano;
		bocadillos = new HashSet<Bocadillo>();
	}
	
	public Ingrediente () {
		bocadillos = new HashSet<Bocadillo>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Bocadillo> getBocadillos() {
		return bocadillos;
	}

	public void setBocadillos(Set<Bocadillo> bocadillos) {
		this.bocadillos = bocadillos;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getEsVegano() {
		return esVegano;
	}

	public void setEsVegano(boolean esVegano) {
		this.esVegano = esVegano;
	}

	public void imprimir() {
		System.out.println("Ingrediente id=" + id + ", nombre=" + nombre);
	}
}
