package modelo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "piloto")
public class Piloto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nome", length = 45)
	private String nome;

	@Column(name = "escuderia", length = 30)
	private String escuderia;

	@OneToMany(mappedBy = "piloto", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<Chegada> chegadas;

	public Piloto() {
	}

	public Piloto(String nome, String escuderia) {
		this.nome = nome;
		this.escuderia = escuderia;
	}

	// Metodos
	public void addChegada(Chegada chegada) {
		this.chegadas.add(chegada);
	}

	public void rmvChegada(Chegada chegada) {
		this.chegadas.remove(chegada);
	}

	// Getters and setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEscuderia() {
		return escuderia;
	}

	public void setEscuderia(String escuderia) {
		this.escuderia = escuderia;
	}
	
	public List<Chegada> getChegadas() {
		return chegadas;
	}

	public void setChegadas(List<Chegada> chegadas) {
		this.chegadas = chegadas;
	}

	@Override
	public String toString() {
		return "Piloto [nome=" + nome + ", escuderia=" + escuderia + "]";
	}

}