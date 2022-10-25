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
@Table(name="bocadillos")
public class Bocadillo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="nombre")
	private String nombre;
	
	@ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(
			name = "pedidos_bocadillos", 
			joinColumns = {@JoinColumn(name = "id_bocadillo")}, 
			inverseJoinColumns = {@JoinColumn(name = "id_pedido")}
		)
	private Set<Pedido> pedidos;
	
	public Bocadillo () {
		pedidos = new HashSet<Pedido>();
	}

	
	public Bocadillo(String nombre) {
		this.nombre = nombre;
		pedidos = new HashSet<Pedido>();
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(Set<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	public void imprimir() {
		System.out.println("Bocadillo id=" + id + ", nombre=" + getNombre());
	}
	
	
}
