package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


/**
 * The persistent class for the detail_commande database table.
 * 
 */
@Entity
@Table(name="detail_Prix_Article")
@NamedQuery(name="DetailPrixArticle.findAll", query="SELECT d FROM DetailPrixArticle d")
public class DetailPrixArticle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	

	private BigDecimal prix;


	//bi-directional many-to-one association to Commande
	@ManyToOne
	@JoinColumn(name="id_article")
	private Articles articles;
	
	@ManyToOne
	@JoinColumn(name="MODIFIER_PAR")
	private Utilisateur modifierPar;
	
	@ManyToOne
	@JoinColumn(name="ID_DEPOT")
	private Depot depot;
	
	@ManyToOne
	@JoinColumn(name="ID_FAMILLE")
	private FamilleArticlePF familleArticlePF;
	
	@ManyToOne
	@JoinColumn(name="ID_SOUSFAMILLE")
	private SousFamilleArticlePF sousFamilleArticlePF;
	
	
	@ManyToOne
	@JoinColumn(name="ID_MAGASIN")
	private Magasin magasin;
	
	
	
	@ManyToOne
	@JoinColumn(name="ID_CLIENT")
	private ClientPF clientPF;
	

	public DetailPrixArticle() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getPrix() {
		return prix;
	}

	public void setPrix(BigDecimal prix) {
		this.prix = prix;
	}

	public Articles getArticles() {
		return articles;
	}

	public void setArticles(Articles articles) {
		this.articles = articles;
	}

	public Utilisateur getModifierPar() {
		return modifierPar;
	}

	public void setModifierPar(Utilisateur modifierPar) {
		this.modifierPar = modifierPar;
	}

	public Depot getDepot() {
		return depot;
	}

	public void setDepot(Depot depot) {
		this.depot = depot;
	}

	public Magasin getMagasin() {
		return magasin;
	}

	public void setMagasin(Magasin magasin) {
		this.magasin = magasin;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public FamilleArticlePF getFamilleArticlePF() {
		return familleArticlePF;
	}

	public void setFamilleArticlePF(FamilleArticlePF familleArticlePF) {
		this.familleArticlePF = familleArticlePF;
	}

	public SousFamilleArticlePF getSousFamilleArticlePF() {
		return sousFamilleArticlePF;
	}

	public void setSousFamilleArticlePF(SousFamilleArticlePF sousFamilleArticlePF) {
		this.sousFamilleArticlePF = sousFamilleArticlePF;
	}

	public ClientPF getClientPF() {
		return clientPF;
	}

	public void setClientPF(ClientPF clientPF) {
		this.clientPF = clientPF;
	}


	

}