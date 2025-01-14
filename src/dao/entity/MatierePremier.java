package dao.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the matiere_premier database table.
 * 
 */
@Entity
@Table(name="matiere_premier")
@NamedQuery(name="MatierePremier.findAll", query="SELECT m FROM MatierePremier m")
public class MatierePremier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	private String code;

	private String nom;
	
	private boolean deleted ;

	/*//bi-directional many-to-one association to DetailCommande
	@OneToMany(mappedBy="matierePremier")
	private List<DetailCommande> detailCommandes;

	//bi-directional many-to-one association to DetailFacture
	@OneToMany(mappedBy="matierePremier")
	private List<DetailFacture> detailFactures;

	//bi-directional many-to-one association to DetailProduit
	@OneToMany(mappedBy="matierePremier")
	private List<DetailProduit> detailProduits;*/

	//bi-directional many-to-one association to CategorieMp
	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name="id_cat")
	private CategorieMp categorieMp;

	/*//bi-directional many-to-one association to Stock
	@OneToMany(mappedBy="matierePremier")
	private List<Stock> stocks;*/

	public MatierePremier() {
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

/*	public List<DetailCommande> getDetailCommandes() {
		return this.detailCommandes;
	}

	public void setDetailCommandes(List<DetailCommande> detailCommandes) {
		this.detailCommandes = detailCommandes;
	}

	public DetailCommande addDetailCommande(DetailCommande detailCommande) {
		getDetailCommandes().add(detailCommande);
		detailCommande.setMatierePremier(this);

		return detailCommande;
	}

	public DetailCommande removeDetailCommande(DetailCommande detailCommande) {
		getDetailCommandes().remove(detailCommande);
		detailCommande.setMatierePremier(null);

		return detailCommande;
	}

	public List<DetailFacture> getDetailFactures() {
		return this.detailFactures;
	}

	public void setDetailFactures(List<DetailFacture> detailFactures) {
		this.detailFactures = detailFactures;
	}

	public DetailFacture addDetailFacture(DetailFacture detailFacture) {
		getDetailFactures().add(detailFacture);
		detailFacture.setMatierePremier(this);

		return detailFacture;
	}

	public DetailFacture removeDetailFacture(DetailFacture detailFacture) {
		getDetailFactures().remove(detailFacture);
		detailFacture.setMatierePremier(null);

		return detailFacture;
	}

	public List<DetailProduit> getDetailProduits() {
		return this.detailProduits;
	}

	public void setDetailProduits(List<DetailProduit> detailProduits) {
		this.detailProduits = detailProduits;
	}

	public DetailProduit addDetailProduit(DetailProduit detailProduit) {
		getDetailProduits().add(detailProduit);
		detailProduit.setMatierePremier(this);

		return detailProduit;
	}

	public DetailProduit removeDetailProduit(DetailProduit detailProduit) {
		getDetailProduits().remove(detailProduit);
		detailProduit.setMatierePremier(null);

		return detailProduit;
	}
*/
	public CategorieMp getCategorieMp() {
		return this.categorieMp;
	}

	public void setCategorieMp(CategorieMp categorieMp) {
		this.categorieMp = categorieMp;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	/*public List<Stock> getStocks() {
		return this.stocks;
	}

	public void setStocks(List<Stock> stocks) {
		this.stocks = stocks;
	}

	public Stock addStock(Stock stock) {
		getStocks().add(stock);
		stock.setMatierePremier(this);

		return stock;
	}

	public Stock removeStock(Stock stock) {
		getStocks().remove(stock);
		stock.setMatierePremier(null);

		return stock;
	}*/

}