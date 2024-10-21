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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the commande database table.
 * 
 */
@Entity
@Table(name="Transfer_StockInitialMP_VersStockInitial_AutreMP")
@NamedQuery(name="TransferStockInitialMPVersStockInitialAutreMP.findAll", query="SELECT c FROM TransferStockInitialMPVersStockInitialAutreMP c")
public class TransferStockInitialMPVersStockInitialAutreMP implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreer;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_Modifier")
	private Date dateModifier; 
	
	@Temporal(TemporalType.DATE)
	@Column(name="date_transfer")
	private Date dateTransfer;
	
	
	
	@Column(name="Code_Transfer")
	private String CodeTransfer;

	 

	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="modifier_par")
	private Utilisateur modifierPar;


	//bi-directional many-to-one association to Utilisateur
	@ManyToOne
	@JoinColumn(name="creer_par")
	private Utilisateur creerPar;

	@ManyToOne
	@JoinColumn(name="id_depot_Source")
	private Depot depotSource;
	
	@ManyToOne
	@JoinColumn(name="id_depot_Destination")
	private Depot depotDestination;

	@ManyToOne
	@JoinColumn(name="id_MP_Source")
	private MatierePremier mpSource;
	
	
	@ManyToOne
	@JoinColumn(name="id_MP_Destination")
	private MatierePremier mpDestination;
	
	@ManyToOne
	@JoinColumn(name="id_Magasin_source")
	private Magasin magasinSouce;
	
	@ManyToOne
	@JoinColumn(name="id_Magasin_destination")
	private Magasin magasinDestination;
	
	@Column(name="prix_unitaire")
	private BigDecimal prixUnitaire;

	private BigDecimal quantite;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateCreer() {
		return dateCreer;
	}

	public void setDateCreer(Date dateCreer) {
		this.dateCreer = dateCreer;
	}

	public Date getDateModifier() {
		return dateModifier;
	}

	public void setDateModifier(Date dateModifier) {
		this.dateModifier = dateModifier;
	}

	public Date getDateTransfer() {
		return dateTransfer;
	}

	public void setDateTransfer(Date dateTransfer) {
		this.dateTransfer = dateTransfer;
	}

	public String getCodeTransfer() {
		return CodeTransfer;
	}

	public void setCodeTransfer(String codeTransfer) {
		CodeTransfer = codeTransfer;
	}

	public Utilisateur getModifierPar() {
		return modifierPar;
	}

	public void setModifierPar(Utilisateur modifierPar) {
		this.modifierPar = modifierPar;
	}

	public Utilisateur getCreerPar() {
		return creerPar;
	}

	public void setCreerPar(Utilisateur creerPar) {
		this.creerPar = creerPar;
	}

	public Depot getDepotSource() {
		return depotSource;
	}

	public void setDepotSource(Depot depotSource) {
		this.depotSource = depotSource;
	}

	public Depot getDepotDestination() {
		return depotDestination;
	}

	public void setDepotDestination(Depot depotDestination) {
		this.depotDestination = depotDestination;
	}

	public MatierePremier getMpSource() {
		return mpSource;
	}

	public void setMpSource(MatierePremier mpSource) {
		this.mpSource = mpSource;
	}

	public MatierePremier getMpDestination() {
		return mpDestination;
	}

	public void setMpDestination(MatierePremier mpDestination) {
		this.mpDestination = mpDestination;
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

	public BigDecimal getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(BigDecimal prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}

	public BigDecimal getQuantite() {
		return quantite;
	}

	public void setQuantite(BigDecimal quantite) {
		this.quantite = quantite;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	


}