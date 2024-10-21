package dao.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the rip_fournisseur database table.
 * 
 */
@Entity
@Table(name="rip_fournisseur")
@NamedQuery(name="RipFournisseur.findAll", query="SELECT r FROM RipFournisseur r")
public class RipFournisseur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String rip;
	
	private String code;

	/*//bi-directional many-to-one association to Reglement
	@OneToMany(mappedBy="ripFournisseur")
	private List<Reglement> reglements;*/

	//bi-directional many-to-one association to Fournisseur
	@ManyToOne
	@JoinColumn(name="id_four")
	private Fournisseur fournisseur;

	public RipFournisseur() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRip() {
		return this.rip;
	}

	public void setRip(String rip) {
		this.rip = rip;
	}

	/*public List<Reglement> getReglements() {
		return this.reglements;
	}

	public void setReglements(List<Reglement> reglements) {
		this.reglements = reglements;
	}

	public Reglement addReglement(Reglement reglement) {
		getReglements().add(reglement);
		reglement.setRipFournisseur(this);

		return reglement;
	}

	public Reglement removeReglement(Reglement reglement) {
		getReglements().remove(reglement);
		reglement.setRipFournisseur(null);

		return reglement;
	}
*/
	public Fournisseur getFournisseur() {
		return this.fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}