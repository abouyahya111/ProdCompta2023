package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the devise database table.
 * 
 */
@Entity
@NamedQuery(name="Devise.findAll", query="SELECT d FROM Devise d")
public class Devise implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String nom;

	@Column(name="taux_esitime")
	private BigDecimal tauxEsitime;

	//bi-directional many-to-one association to Reglement
	@OneToMany(mappedBy="devise")
	private List<Reglement> reglements;

	public Devise() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public BigDecimal getTauxEsitime() {
		return this.tauxEsitime;
	}

	public void setTauxEsitime(BigDecimal tauxEsitime) {
		this.tauxEsitime = tauxEsitime;
	}

	public List<Reglement> getReglements() {
		return this.reglements;
	}

	public void setReglements(List<Reglement> reglements) {
		this.reglements = reglements;
	}

	public Reglement addReglement(Reglement reglement) {
		getReglements().add(reglement);
		reglement.setDevise(this);

		return reglement;
	}

	public Reglement removeReglement(Reglement reglement) {
		getReglements().remove(reglement);
		reglement.setDevise(null);

		return reglement;
	}

}