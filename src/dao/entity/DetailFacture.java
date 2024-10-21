package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


/**
 * The persistent class for the detail_facture database table.
 * 
 */
@Entity
@Table(name="detail_facture")
@NamedQuery(name="DetailFacture.findAll", query="SELECT d FROM DetailFacture d")
public class DetailFacture implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="prix_unitaire")
	private BigDecimal prixUnitaire;

	@Column(name="quantite_recu")
	private BigDecimal quantiteRecu;

	//bi-directional many-to-one association to MatierePremier
	@ManyToOne
	@JoinColumn(name="id_mat_pre")
	private MatierePremier matierePremier;

	//bi-directional many-to-one association to Facture
	@ManyToOne
	@JoinColumn(name="id_facture")
	private Facture facture;

	public DetailFacture() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getPrixUnitaire() {
		return this.prixUnitaire;
	}

	public void setPrixUnitaire(BigDecimal prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public BigDecimal getQuantiteRecu() {
		return this.quantiteRecu;
	}

	public void setQuantiteRecu(BigDecimal quantiteRecu) {
		this.quantiteRecu = quantiteRecu;
	}

	public MatierePremier getMatierePremier() {
		return this.matierePremier;
	}

	public void setMatierePremier(MatierePremier matierePremier) {
		this.matierePremier = matierePremier;
	}

	public Facture getFacture() {
		return this.facture;
	}

	public void setFacture(Facture facture) {
		this.facture = facture;
	}

}