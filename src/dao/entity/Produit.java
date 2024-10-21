package dao.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the produit database table.
 * 
 */
@Entity
@NamedQuery(name="Produit.findAll", query="SELECT p FROM Produit p")
public class Produit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String code;

	private String nom;

	//bi-directional many-to-one association to Production
	@OneToMany(mappedBy="produit")
	private List<Production> productions;

	public Produit() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Production> getProductions() {
		return this.productions;
	}

	public void setProductions(List<Production> productions) {
		this.productions = productions;
	}

/*	public Production addProduction(Production production) {
		getProductions().add(production);
		production.setProduit(this);

		return production;
	}

	public Production removeProduction(Production production) {
		getProductions().remove(production);
		production.setProduit(null);

		return production;
	}*/

}