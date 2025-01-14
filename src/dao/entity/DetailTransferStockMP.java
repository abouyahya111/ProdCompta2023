package dao.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;


/**
 * The persistent class for the detail_commande database table.
 * 
 */
@Entity
@Table(name="Detail_Transfer_StockMP")
@NamedQuery(name="DetailTransferStockMP.findAll", query="SELECT d FROM DetailTransferStockMP d")
public class DetailTransferStockMP implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="prix_unitaire")
	private BigDecimal prixUnitaire;

	private BigDecimal quantite;

	//bi-directional many-to-one association to MatierePremier
	@ManyToOne
	@JoinColumn(name="id_mat_pre")
	private MatierePremier matierePremier;
	
	@ManyToOne
	@JoinColumn(name="id_Magasin_source")
	private Magasin magasinSouce;
	
	@ManyToOne
	@JoinColumn(name="id_Magasin_destination")
	private Magasin magasinDestination;

	//bi-directional many-to-one association to Commande
	@ManyToOne
	@JoinColumn(name="id_transfer")
	private TransferStockMP transferStockMP;
	
	@Column(name="Quantite_Dechet")
	private BigDecimal QuantiteDechet;
	
	@Column(name="Quantite_Offre")
	private BigDecimal QuantiteOffre;
	
	@Column(name="Stock_Source")
	private String StockSource;
	
	public DetailTransferStockMP() {
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

	public BigDecimal getQuantite() {
		return this.quantite;
	}

	public void setQuantite(BigDecimal quantite) {
		this.quantite = quantite;
	}

	public MatierePremier getMatierePremier() {
		return this.matierePremier;
	}

	public void setMatierePremier(MatierePremier matierePremier) {
		this.matierePremier = matierePremier;
	}

	public Magasin getMagasinSouce() {
		return magasinSouce;
	}

	public void setMagasinSouce(Magasin magasinSouce) {
		this.magasinSouce = magasinSouce;
	}

	public Magasin getMagasinDestination() {
		return magasinDestination;
	}

	public void setMagasinDestination(Magasin magasinDestination) {
		this.magasinDestination = magasinDestination;
	}

	public TransferStockMP getTransferStockMP() {
		return transferStockMP;
	}

	public void setTransferStockMP(TransferStockMP transferStockMP) {
		this.transferStockMP = transferStockMP;
	}

	public BigDecimal getQuantiteDechet() {
		return QuantiteDechet;
	}

	public void setQuantiteDechet(BigDecimal quantiteDechet) {
		QuantiteDechet = quantiteDechet;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BigDecimal getQuantiteOffre() {
		return QuantiteOffre;
	}

	public void setQuantiteOffre(BigDecimal quantiteOffre) {
		QuantiteOffre = quantiteOffre;
	}

	public String getStockSource() {
		return StockSource;
	}

	public void setStockSource(String stockSource) {
		StockSource = stockSource;
	}



	

}