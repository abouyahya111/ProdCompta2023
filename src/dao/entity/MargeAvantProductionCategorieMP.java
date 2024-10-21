package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


/**
 * The persistent class for the detail_commande database table.
 * 
 */
@Entity
@Table(name="MARGE_AVANT_PRODUCTION_CATEGORIE")
@NamedQuery(name="MargeAvantProductionCategorie.findAll", query="SELECT d FROM MargeAvantProductionCategorie d")
public class MargeAvantProductionCategorieMP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="PRIX_MOYEN")
	private BigDecimal prixMoyen;

	@Column(name="STOCK")
	private BigDecimal stock;

	@Column(name="COUT_SERVICE")
	private BigDecimal coutService;
	
	@Column(name="TOTAL_PRIX")
	private BigDecimal totalPrix;
	
	@Column(name="MONTANT")
	private BigDecimal montant;
	
	@ManyToOne
	@JoinColumn(name="id_Sous_Famille")
	private SousFamilleArticlePF sousFamille;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getPrixMoyen() {
		return prixMoyen;
	}

	public void setPrixMoyen(BigDecimal prixMoyen) {
		this.prixMoyen = prixMoyen;
	}

	public BigDecimal getStock() {
		return stock;
	}

	public void setStock(BigDecimal stock) {
		this.stock = stock;
	}

	public BigDecimal getCoutService() {
		return coutService;
	}

	public void setCoutService(BigDecimal coutService) {
		this.coutService = coutService;
	}

	public BigDecimal getTotalPrix() {
		return totalPrix;
	}

	public void setTotalPrix(BigDecimal totalPrix) {
		this.totalPrix = totalPrix;
	}

	public SousFamilleArticlePF getSousFamille() {
		return sousFamille;
	}

	public void setSousFamille(SousFamilleArticlePF sousFamille) {
		this.sousFamille = sousFamille;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getMontant() {
		return montant;
	}

	public void setMontant(BigDecimal montant) {
		this.montant = montant;
	}
	
	
	

}