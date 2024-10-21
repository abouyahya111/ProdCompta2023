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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;





/**
 * The persistent class for the artiles database table.
 * 
 */
@Entity
@NamedQuery(name="listeCordonneFacturePourImportationExcel.findAll", query="SELECT f FROM listeCordonneFacturePourImportationExcel f")
public class listeCordonneFacturePourImportationExcel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@Column(name="NUM_BL")
	private String numBL;
	
	@Column(name="TYPE_bl")
	private String typeBL ;
	
	@Temporal(TemporalType.DATE)
	@Column(name="DATE_FACTURE")
	private Date dateFacture;

	@ManyToOne
	@JoinColumn(name="ID_CLIENT")
	private ClientPF clientPF;
	
	@ManyToOne
	@JoinColumn(name="ID_FOURNISSEUR")
	private Client Fournisseur;
	
	@ManyToOne
	@JoinColumn(name="ID_DEPOT")
	private Depot depot;
	
	@ManyToOne
	@JoinColumn(name="ID_MAGASIN")
	private Magasin magasin;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumBL() {
		return numBL;
	}

	public void setNumBL(String numBL) {
		this.numBL = numBL;
	}

	public String getTypeBL() {
		return typeBL;
	}

	public void setTypeBL(String typeBL) {
		this.typeBL = typeBL;
	}

	public Date getDateFacture() {
		return dateFacture;
	}

	public void setDateFacture(Date dateFacture) {
		this.dateFacture = dateFacture;
	}

	public ClientPF getClientPF() {
		return clientPF;
	}

	public void setClientPF(ClientPF clientPF) {
		this.clientPF = clientPF;
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

	public Client getFournisseur() {
		return Fournisseur;
	}

	public void setFournisseur(Client fournisseur) {
		Fournisseur = fournisseur;
	}
	
	
	

}