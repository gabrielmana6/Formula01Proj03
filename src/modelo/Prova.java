package modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "prova")
public class Prova {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_prova", nullable = false)
	private Prova prova;

	@Column(name = "colocacao")
	private Integer colocacao;

	@OneToMany(mappedBy = "prova", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Chegada> chegadas = new ArrayList<>();

	public Prova() {
		this.chegadas = new ArrayList<Chegada>();
	}

	// Metodos
	public void addChegada(Chegada chegada) {
		this.chegadas.add(chegada);
	}

	public void rmvChegada(Chegada chegada) {
		this.chegadas.remove(chegada);
	}

	// Getters and setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Prova getProva() {
		return prova;
	}

	public void setProva(Prova prova) {
		this.prova = prova;
	}

	public Integer getColocacao() {
		return colocacao;
	}

	public void setColocacao(Integer colocacao) {
		this.colocacao = colocacao;
	}

	public List<Chegada> getChegadas() {
		return chegadas;
	}

	public void setChegadas(List<Chegada> chegadas) {
		this.chegadas = chegadas;
	}

	@Override
	public String toString() {
		return "Prova [id=" + id + ", prova=" + prova + ", colocacao=" + colocacao + ", chegadas=" + chegadas + "]";
	}
}
