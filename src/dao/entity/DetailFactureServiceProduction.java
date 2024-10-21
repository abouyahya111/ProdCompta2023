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
@Table(name="detail_facture_Service_Production")
@NamedQuery(name="DetailFactureServiceProduction.findAll", query="SELECT d FROM DetailFactureServiceProduction d")
public class DetailFactureServiceProduction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	//bi-directional many-to-one association to Facture
	
	@JoinColumn(name="article")
	private String article;
	
	@Column(name="prix")
	private BigDecimal prix;
	
	@Column(name="quantite")
	private BigDecimal quantite;

	@Column(name="MONTANT_HT")
	private BigDecimal montantHT;
	
	//bi-directional many-to-one association to Facture
	//@NotFound(action=NotFoundAction.IGNORE)

	
	@ManyToOne
	@JoinColumn(name="id_factureService")
	private FactureServiceProduction factureService;
	
	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="creer_par")
	private Utilisateur utilisateur;
	
	
	public DetailFactureServiceProduction() {
	}

	public int getId() {
		return id;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public BigDecimal getPrix() {
		return prix;
	}

	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}

	public BigDecimal getQuantite() {
		return quantite;
	}

	public void setQuantite(BigDecimal quantite) {
		this.quantite = quantite;
	}

	public FactureServiceProduction getFactureService() {
		return factureService;
	}

	public void setFactureService(FactureServiceProduction factureService) {
		this.factureService = factureService;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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



}