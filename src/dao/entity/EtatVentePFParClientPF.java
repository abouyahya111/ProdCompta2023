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

public class EtatVentePFParClientPF implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="DATE_FACTURE")
	private Date dateFacture;
	
	@Column(name="NUM_FACTURE")
	private String numFacture;
	
	@Column(name="clientPF")
	private ClientPF clientPF;
	
	@Column(name="Montant HT")
	private BigDecimal montantHT ;

	@Column(name="Montant TVA")
	private BigDecimal montantTVA ;
	
	@Column(name="Montant TTC")
	private BigDecimal montantTTC ;


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Date getDateFacture() {
		return dateFacture;
	}


	public void setDateFacture(Date dateFacture) {
		this.dateFacture = dateFacture;
	}


	public String getNumFacture() {
		return numFacture;
	}


	public void setNumFacture(String numFactureTmp) {
		numFacture = numFactureTmp;
	}


	public ClientPF getClientPF() {
		return clientPF;
	}


	public void setClientPF(ClientPF clientPF) {
		this.clientPF = clientPF;
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