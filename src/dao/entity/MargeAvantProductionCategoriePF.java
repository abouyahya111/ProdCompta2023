package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


/**
 * The persistent class for the detail_commande database table.
 * 
 */
@Entity
@Table(name="MARGE_AVANT_PRODUCTION_CATEGORIEPF")
@NamedQuery(name="MargeAvantProductionCategoriePF.findAll", query="SELECT d FROM MargeAvantProductionCategoriePF d")
public class MargeAvantProductionCategoriePF implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@ManyToOne
	@JoinColumn(name="id_Sous_Famille")
	private SousFamilleArticlePF sousFamille;
	
	@ManyToOne
	@JoinColumn(name="id_Article")
	private Articles articles;
	
	@Column(name="PRIX_VENTE")
	private BigDecimal prixVente;
	
	
	@Column(name="PRIX_ENVRAC")
	private BigDecimal prixEnvrac;

	@Column(name="STOCK")
	private BigDecimal stock;

	@Column(name="MARGE")
	private BigDecimal marge;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public SousFamilleArticlePF getSousFamille() {
		return sousFamille;
	}

	public void setSousFamille(SousFamilleArticlePF sousFamille) {
		this.sousFamille = sousFamille;
	}

	public Articles getArticles() {
		return articles;
	}

	public void setArticles(Articles articles) {
		this.articles = articles;
	}

	public BigDecimal getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(BigDecimal prixVente) {
		this.prixVente = prixVente;
	}

	public BigDecimal getStock() {
		return stock;
	}

	public void setStock(BigDecimal stock) {
		this.stock = stock;
	}

	public BigDecimal getMarge() {
		return marge;
	}

	public void setMarge(BigDecimal marge) {
		this.marge = marge;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getPrixEnvrac() {
		return prixEnvrac;
	}

	public void setPrixEnvrac(BigDecimal prixEnvrac) {
		this.prixEnvrac = prixEnvrac;
	}
	
	
	
	
	

}