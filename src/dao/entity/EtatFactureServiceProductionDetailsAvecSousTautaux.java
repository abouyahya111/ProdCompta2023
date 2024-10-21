package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the commande database table.
 * 
 */
@Entity
@NamedQuery(name="FactureServiceProductionDetailAvecSousTautaux.findAll", query="SELECT c FROM FactureServiceProductionDetailAvecSousTautaux c")
public class EtatFactureServiceProductionDetailsAvecSousTautaux implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
 
	
	@Column(name="NUM_FACTURE")
	private String numFacture;
	
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATE_FACTURE")
	private Date dateFacture;
 
	@ManyToOne
	@JoinColumn(name = "article")
	private Articles article;
	
	@ManyToOne
	@JoinColumn(name = "sousfamille")
	private SousFamilleArticlePF sousfamille;
	
	@ManyToOne
	@JoinColumn(name = "client")
	private Client client;
	 
	@Column(name="QUANTITE")
	private BigDecimal quantite;
	
	@Column(name="PRIX")
	private BigDecimal prix;
	
	@Column(name="MONTANT_HT")
	private BigDecimal montantHT;
	
	
	@Column(name="MONTANT_TVA")
	private BigDecimal montantTVA;
	
	@Column(name="MONTANT_TTC")
	private BigDecimal montantTTC;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumFacture() {
		return numFacture;
	}

	public void setNumFacture(String numFacture) {
		this.numFacture = numFacture;
	}

	public Date getDateFacture() {
		return dateFacture;
	}

	public void setDateFacture(Date dateFacture) {
		this.dateFacture = dateFacture;
	}

	public Articles getArticle() {
		return article;
	}

	public void setArticle(Articles article) {
		this.article = article;
	}

	public SousFamilleArticlePF getSousfamille() {
		return sousfamille;
	}

	public void setSousfamille(SousFamilleArticlePF sousfamille) {
		this.sousfamille = sousfamille;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public BigDecimal getQuantite() {
		return quantite;
	}

	public void setQuantite(BigDecimal quantite) {
		this.quantite = quantite;
	}

	public BigDecimal getPrix() {
		return prix;
	}

	public void setPrix(BigDecimal prix) {
		this.prix = prix;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	  
	

	
	
}