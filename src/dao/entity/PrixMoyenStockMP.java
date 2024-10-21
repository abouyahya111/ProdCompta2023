package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;





/**
 * The persistent class for the artiles database table.
 * 
 */
@Entity
@NamedQuery(name="PrixMoyenStockMP.findAll", query="SELECT f FROM PrixMoyenStockMP f")
public class PrixMoyenStockMP implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@ManyToOne
	@JoinColumn(name="id_mp")
	private MatierePremier mp;
	
	
	@Column(name="famille")
	private String famille;
	
	@Column(name="sousfamille")
	private String sousfamille;
	
	
	// Stock Initial
	
	@Column(name="quantite_initial")
	private BigDecimal quantiteInitial;

	@Column(name="prix_initial")
	private BigDecimal prixInitial;
	
	@Column(name="montant_initial")
	private BigDecimal montantInitial;
	
	// Achat
	
	@Column(name="quantite_achat")
	private BigDecimal quantiteAchat;

	@Column(name="prix_achat")
	private BigDecimal prixAchat;
		
	@Column(name="montant_achat")
	private BigDecimal montantAchat;
	
	
	// Fabriquer
	
		@Column(name="quantite_fabriquer")
		private BigDecimal quantiteFabriquer;

		@Column(name="prix_fabriquer")
		private BigDecimal prixFabriquer;
			
		@Column(name="montant_fabriquer")
		private BigDecimal montantFabriquer;
	
	/// Prix Moyen
	
	@Column(name="QUANTITE_FINALE")
	private BigDecimal quantiteFinale ;

	@Column(name="PRIX")
	private BigDecimal prixMoyen ;
	
	@Column(name="MONTANT_HT")
	private BigDecimal montantHTFinale ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MatierePremier getMp() {
		return mp;
	}

	public void setMp(MatierePremier mp) {
		this.mp = mp;
	}

	public BigDecimal getQuantiteInitial() {
		return quantiteInitial;
	}

	public void setQuantiteInitial(BigDecimal quantiteInitial) {
		this.quantiteInitial = quantiteInitial;
	}

	public BigDecimal getPrixInitial() {
		return prixInitial;
	}

	public void setPrixInitial(BigDecimal prixInitial) {
		this.prixInitial = prixInitial;
	}

	public BigDecimal getMontantInitial() {
		return montantInitial;
	}

	public void setMontantInitial(BigDecimal montantInitial) {
		this.montantInitial = montantInitial;
	}

	public BigDecimal getQuantiteAchat() {
		return quantiteAchat;
	}

	public void setQuantiteAchat(BigDecimal quantiteAchat) {
		this.quantiteAchat = quantiteAchat;
	}

	public BigDecimal getPrixAchat() {
		return prixAchat;
	}

	public void setPrixAchat(BigDecimal prixAchat) {
		this.prixAchat = prixAchat;
	}

	public BigDecimal getMontantAchat() {
		return montantAchat;
	}

	public void setMontantAchat(BigDecimal montantAchat) {
		this.montantAchat = montantAchat;
	}

	public BigDecimal getQuantiteFinale() {
		return quantiteFinale;
	}

	public void setQuantiteFinale(BigDecimal quantiteFinale) {
		this.quantiteFinale = quantiteFinale;
	}

	public BigDecimal getPrixMoyen() {
		return prixMoyen;
	}

	public void setPrixMoyen(BigDecimal prixMoyen) {
		this.prixMoyen = prixMoyen;
	}

	public BigDecimal getMontantHTFinale() {
		return montantHTFinale;
	}

	public void setMontantHTFinale(BigDecimal montantHTFinale) {
		this.montantHTFinale = montantHTFinale;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFamille() {
		return famille;
	}

	public void setFamille(String famille) {
		this.famille = famille;
	}

	public String getSousfamille() {
		return sousfamille;
	}

	public void setSousfamille(String sousfamille) {
		this.sousfamille = sousfamille;
	}

	public BigDecimal getQuantiteFabriquer() {
		return quantiteFabriquer;
	}

	public void setQuantiteFabriquer(BigDecimal quantiteFabriquer) {
		this.quantiteFabriquer = quantiteFabriquer;
	}

	public BigDecimal getPrixFabriquer() {
		return prixFabriquer;
	}

	public void setPrixFabriquer(BigDecimal prixFabriquer) {
		this.prixFabriquer = prixFabriquer;
	}

	public BigDecimal getMontantFabriquer() {
		return montantFabriquer;
	}

	public void setMontantFabriquer(BigDecimal montantFabriquer) {
		this.montantFabriquer = montantFabriquer;
	}
	

	
	
	
}