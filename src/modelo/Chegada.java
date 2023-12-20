package modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "chegada")
public class Chegada {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_prova", nullable = false)
	private Prova prova;

	@Column(name = "colocacao")
	private Integer colocacao;

	@ManyToOne
	@JoinColumn(name = "id_piloto", nullable = false)
	private Piloto piloto;

	public Chegada() {
	}

	public Chegada(Prova prova, int colocacao, Piloto piloto) {
		this.prova = prova;
		this.colocacao = colocacao;
		this.piloto = piloto;
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

	public Piloto getPiloto() {
		return piloto;
	}

	public void setPiloto(Piloto piloto) {
		this.piloto = piloto;
	}

	@Override
	public String toString() {
		return "Chegada [id=" + id + ", prova=" + prova.getId() + ", colocacao=" + colocacao + ", piloto=" + piloto.getNome() + "]";
	}
}
