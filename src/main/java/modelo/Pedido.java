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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="pedidos")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="id_alumno", nullable = false)
	private Alumno alumno;
	
	@ManyToMany(mappedBy = "pedidos",cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER) //Asociado al atributo Set
	private Set<Bocadillo> bocadillos;

	public Pedido(Alumno a) {
		alumno = a;
		bocadillos = new HashSet<Bocadillo>();
	}
	
	public Pedido() {
		bocadillos = new HashSet<Bocadillo>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Set<Bocadillo> getBocadillos() {
		return bocadillos;
	}

	public void setBocadillos(Set<Bocadillo> bocadillos) {
		this.bocadillos = bocadillos;
	}

	public void imprimir() {
		System.out.println("Pedido id=" + id);
		for(Bocadillo b: bocadillos) {
			b.imprimir();
		}
		
		System.out.println("El precio total es: " + calcularPrecio());
	}

	private String calcularPrecio() {
		
		double resul = 0;
		
		for(Bocadillo b: bocadillos) {
			resul += b.getPrecio();
		}
		
		return String.valueOf(resul);
	}
	
	
}
