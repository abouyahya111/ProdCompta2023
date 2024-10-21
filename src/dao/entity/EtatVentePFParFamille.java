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

public class EtatVentePFParFamille implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@Column(name="famileArticle")
	private FamilleArticlePF familleArticlePF;
	
	@Column(name="Montant HTMois1")
	private BigDecimal montantHTMois1 ;

	@Column(name="Montant TVAMois1")
	private BigDecimal montantTVAMois1 ;
	
	@Column(name="Montant TTCMois1")
	private BigDecimal montantTTCMois1 ;
	
	@Column(name="Montant HTMois2")
	private BigDecimal montantHTMois2 ;

	@Column(name="Montant TVAMois2")
	private BigDecimal montantTVAMois2 ;
	
	@Column(name="Montant TTCMois2")
	private BigDecimal montantTTCMois2 ;

	@Column(name="Montant HTMois3")
	private BigDecimal montantHTMois3 ;

	@Column(name="Montant TVAMois3")
	private BigDecimal montantTVAMois3 ;
	
	@Column(name="Montant TTCMois3")
	private BigDecimal montantTTCMois3 ;
	
	@Column(name="Montant HTMois4")
	private BigDecimal montantHTMois4 ;

	@Column(name="Montant TVAMois4")
	private BigDecimal montantTVAMois4 ;
	
	@Column(name="Montant TTCMois4")
	private BigDecimal montantTTCMois4 ;	
	
	@Column(name="Montant HTMois5")
	private BigDecimal montantHTMois5 ;

	@Column(name="Montant TVAMois5")
	private BigDecimal montantTVAMois5 ;
	
	@Column(name="Montant TTCMois5")
	private BigDecimal montantTTCMois5 ;
	
	@Column(name="Montant HTMois6")
	private BigDecimal montantHTMois6 ;

	@Column(name="Montant TVAMois6")
	private BigDecimal montantTVAMois6 ;
	
	@Column(name="Montant TTCMois6")
	private BigDecimal montantTTCMois6 ;
	
	@Column(name="Montant HTMois7")
	private BigDecimal montantHTMois7 ;

	@Column(name="Montant TVAMois7")
	private BigDecimal montantTVAMois7 ;
	
	@Column(name="Montant TTCMois7")
	private BigDecimal montantTTCMois7 ;
	
	@Column(name="Montant HTMois8")
	private BigDecimal montantHTMois8 ;

	@Column(name="Montant TVAMois8")
	private BigDecimal montantTVAMois8 ;
	
	@Column(name="Montant TTCMois8")
	private BigDecimal montantTTCMois8 ;
	
	@Column(name="Montant HTMois9")
	private BigDecimal montantHTMois9 ;

	@Column(name="Montant TVAMois9")
	private BigDecimal montantTVAMois9 ;
	
	@Column(name="Montant TTCMois9")
	private BigDecimal montantTTCMois9 ;
	
	
	@Column(name="Montant HTMois10")
	private BigDecimal montantHTMois10 ;

	@Column(name="Montant TVAMois10")
	private BigDecimal montantTVAMois10 ;
	
	@Column(name="Montant TTCMois10")
	private BigDecimal montantTTCMois10 ;
	
	
	@Column(name="Montant HTMois11")
	private BigDecimal montantHTMois11 ;

	@Column(name="Montant TVAMois11")
	private BigDecimal montantTVAMois11 ;
	
	@Column(name="Montant TTCMois11")
	private BigDecimal montantTTCMois11 ;
	
	@Column(name="Montant HTMois12")
	private BigDecimal montantHTMois12 ;

	@Column(name="Montant TVAMois12")
	private BigDecimal montantTVAMois12 ;
	
	@Column(name="Montant TTCMois12")
	private BigDecimal montantTTCMois12 ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public FamilleArticlePF getFamilleArticlePF() {
		return familleArticlePF;
	}

	public void setFamilleArticlePF(FamilleArticlePF familleArticlePF) {
		this.familleArticlePF = familleArticlePF;
	}

	public BigDecimal getMontantHTMois1() {
		return montantHTMois1;
	}

	public void setMontantHTMois1(BigDecimal montantHTMois1) {
		this.montantHTMois1 = montantHTMois1;
	}

	public BigDecimal getMontantTVAMois1() {
		return montantTVAMois1;
	}

	public void setMontantTVAMois1(BigDecimal montantTVAMois1) {
		this.montantTVAMois1 = montantTVAMois1;
	}

	public BigDecimal getMontantTTCMois1() {
		return montantTTCMois1;
	}

	public void setMontantTTCMois1(BigDecimal montantTTCMois1) {
		this.montantTTCMois1 = montantTTCMois1;
	}

	public BigDecimal getMontantHTMois2() {
		return montantHTMois2;
	}

	public void setMontantHTMois2(BigDecimal montantHTMois2) {
		this.montantHTMois2 = montantHTMois2;
	}

	public BigDecimal getMontantTVAMois2() {
		return montantTVAMois2;
	}

	public void setMontantTVAMois2(BigDecimal montantTVAMois2) {
		this.montantTVAMois2 = montantTVAMois2;
	}

	public BigDecimal getMontantTTCMois2() {
		return montantTTCMois2;
	}

	public void setMontantTTCMois2(BigDecimal montantTTCMois2) {
		this.montantTTCMois2 = montantTTCMois2;
	}

	public BigDecimal getMontantHTMois3() {
		return montantHTMois3;
	}

	public void setMontantHTMois3(BigDecimal montantHTMois3) {
		this.montantHTMois3 = montantHTMois3;
	}

	public BigDecimal getMontantTVAMois3() {
		return montantTVAMois3;
	}

	public void setMontantTVAMois3(BigDecimal montantTVAMois3) {
		this.montantTVAMois3 = montantTVAMois3;
	}

	public BigDecimal getMontantTTCMois3() {
		return montantTTCMois3;
	}

	public void setMontantTTCMois3(BigDecimal montantTTCMois3) {
		this.montantTTCMois3 = montantTTCMois3;
	}

	public BigDecimal getMontantHTMois4() {
		return montantHTMois4;
	}

	public void setMontantHTMois4(BigDecimal montantHTMois4) {
		this.montantHTMois4 = montantHTMois4;
	}

	public BigDecimal getMontantTVAMois4() {
		return montantTVAMois4;
	}

	public void setMontantTVAMois4(BigDecimal montantTVAMois4) {
		this.montantTVAMois4 = montantTVAMois4;
	}

	public BigDecimal getMontantTTCMois4() {
		return montantTTCMois4;
	}

	public void setMontantTTCMois4(BigDecimal montantTTCMois4) {
		this.montantTTCMois4 = montantTTCMois4;
	}

	public BigDecimal getMontantHTMois5() {
		return montantHTMois5;
	}

	public void setMontantHTMois5(BigDecimal montantHTMois5) {
		this.montantHTMois5 = montantHTMois5;
	}

	public BigDecimal getMontantTVAMois5() {
		return montantTVAMois5;
	}

	public void setMontantTVAMois5(BigDecimal montantTVAMois5) {
		this.montantTVAMois5 = montantTVAMois5;
	}

	public BigDecimal getMontantTTCMois5() {
		return montantTTCMois5;
	}

	public void setMontantTTCMois5(BigDecimal montantTTCMois5) {
		this.montantTTCMois5 = montantTTCMois5;
	}

	public BigDecimal getMontantHTMois6() {
		return montantHTMois6;
	}

	public void setMontantHTMois6(BigDecimal montantHTMois6) {
		this.montantHTMois6 = montantHTMois6;
	}

	public BigDecimal getMontantTVAMois6() {
		return montantTVAMois6;
	}

	public void setMontantTVAMois6(BigDecimal montantTVAMois6) {
		this.montantTVAMois6 = montantTVAMois6;
	}

	public BigDecimal getMontantTTCMois6() {
		return montantTTCMois6;
	}

	public void setMontantTTCMois6(BigDecimal montantTTCMois6) {
		this.montantTTCMois6 = montantTTCMois6;
	}

	public BigDecimal getMontantHTMois7() {
		return montantHTMois7;
	}

	public void setMontantHTMois7(BigDecimal montantHTMois7) {
		this.montantHTMois7 = montantHTMois7;
	}

	public BigDecimal getMontantTVAMois7() {
		return montantTVAMois7;
	}

	public void setMontantTVAMois7(BigDecimal montantTVAMois7) {
		this.montantTVAMois7 = montantTVAMois7;
	}

	public BigDecimal getMontantTTCMois7() {
		return montantTTCMois7;
	}

	public void setMontantTTCMois7(BigDecimal montantTTCMois7) {
		this.montantTTCMois7 = montantTTCMois7;
	}

	public BigDecimal getMontantHTMois8() {
		return montantHTMois8;
	}

	public void setMontantHTMois8(BigDecimal montantHTMois8) {
		this.montantHTMois8 = montantHTMois8;
	}

	public BigDecimal getMontantTVAMois8() {
		return montantTVAMois8;
	}

	public void setMontantTVAMois8(BigDecimal montantTVAMois8) {
		this.montantTVAMois8 = montantTVAMois8;
	}

	public BigDecimal getMontantTTCMois8() {
		return montantTTCMois8;
	}

	public void setMontantTTCMois8(BigDecimal montantTTCMois8) {
		this.montantTTCMois8 = montantTTCMois8;
	}

	public BigDecimal getMontantHTMois9() {
		return montantHTMois9;
	}

	public void setMontantHTMois9(BigDecimal montantHTMois9) {
		this.montantHTMois9 = montantHTMois9;
	}

	public BigDecimal getMontantTVAMois9() {
		return montantTVAMois9;
	}

	public void setMontantTVAMois9(BigDecimal montantTVAMois9) {
		this.montantTVAMois9 = montantTVAMois9;
	}

	public BigDecimal getMontantTTCMois9() {
		return montantTTCMois9;
	}

	public void setMontantTTCMois9(BigDecimal montantTTCMois9) {
		this.montantTTCMois9 = montantTTCMois9;
	}

	public BigDecimal getMontantHTMois10() {
		return montantHTMois10;
	}

	public void setMontantHTMois10(BigDecimal montantHTMois10) {
		this.montantHTMois10 = montantHTMois10;
	}

	public BigDecimal getMontantTVAMois10() {
		return montantTVAMois10;
	}

	public void setMontantTVAMois10(BigDecimal montantTVAMois10) {
		this.montantTVAMois10 = montantTVAMois10;
	}

	public BigDecimal getMontantTTCMois10() {
		return montantTTCMois10;
	}

	public void setMontantTTCMois10(BigDecimal montantTTCMois10) {
		this.montantTTCMois10 = montantTTCMois10;
	}

	public BigDecimal getMontantHTMois11() {
		return montantHTMois11;
	}

	public void setMontantHTMois11(BigDecimal montantHTMois11) {
		this.montantHTMois11 = montantHTMois11;
	}

	public BigDecimal getMontantTVAMois11() {
		return montantTVAMois11;
	}

	public void setMontantTVAMois11(BigDecimal montantTVAMois11) {
		this.montantTVAMois11 = montantTVAMois11;
	}

	public BigDecimal getMontantTTCMois11() {
		return montantTTCMois11;
	}

	public void setMontantTTCMois11(BigDecimal montantTTCMois11) {
		this.montantTTCMois11 = montantTTCMois11;
	}

	public BigDecimal getMontantHTMois12() {
		return montantHTMois12;
	}

	public void setMontantHTMois12(BigDecimal montantHTMois12) {
		this.montantHTMois12 = montantHTMois12;
	}

	public BigDecimal getMontantTVAMois12() {
		return montantTVAMois12;
	}

	public void setMontantTVAMois12(BigDecimal montantTVAMois12) {
		this.montantTVAMois12 = montantTVAMois12;
	}

	public BigDecimal getMontantTTCMois12() {
		return montantTTCMois12;
	}

	public void setMontantTTCMois12(BigDecimal montantTTCMois12) {
		this.montantTTCMois12 = montantTTCMois12;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	



}