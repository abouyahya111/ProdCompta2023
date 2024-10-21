package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * The persistent class for the detail_facture database table.
 * 
 */
@Entity
@Table(name = "detail_facture_PFParFamille")
@NamedQuery(name = "DetailFacturePFParFamille.findAll", query = "SELECT d FROM DetailFacturePFParFamille d")
public class DetailFacturePFParFamille implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	// bi-directional many-to-one association to Facture

	@Column(name = "quantite")
	private BigDecimal quantite;
	
	@Column(name = "MONTANT_HT")
	private BigDecimal montantHT;

	@Column(name = "MONTANT_TVA")
	private BigDecimal montantTVA;

	@Column(name = "MONTANT_TTC")
	private BigDecimal montantTTC;

	@ManyToOne
	@JoinColumn(name = "FAMILLE")
	private FamilleArticlePF famillearticle;

	public DetailFacturePFParFamille() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getMontantHT() {
		return montantHT;
	}

	public void setMontantHT(BigDecimal montantHT) {
		this.montantHT = montantHT;
	}

	public BigDecimal getMontantTVA() {
		return montantTVA;
	}

	public void setMontantTVA(BigDecimal montantTVA) {
		this.montantTVA = montantTVA;
	}

	public BigDecimal getMontantTTC() {
		return montantTTC;
	}

	public void setMontantTTC(BigDecimal montantTTC) {
		this.montantTTC = montantTTC;
	}





	public FamilleArticlePF getFamillearticle() {
		return famillearticle;
	}

	public void setFamillearticle(FamilleArticlePF famillearticle) {
		this.famillearticle = famillearticle;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getQuantite() {
		return quantite;
	}

	public void setQuantite(BigDecimal quantite) {
		this.quantite = quantite;
	}

}