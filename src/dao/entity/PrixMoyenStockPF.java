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
@NamedQuery(name="PrixMoyenStockPF.findAll", query="SELECT f FROM PrixMoyenStockPF f")
public class PrixMoyenStockPF implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@ManyToOne
	@JoinColumn(name="id_mp")
	private MatierePremier mp;
	
	
	/// Prix Moyen MP
	
	@Column(name="QUANTITE_MP")
	private BigDecimal quantiteMP ;

	@Column(name="PRIX")
	private BigDecimal prixMoyenMP ;
	
	@Column(name="MONTANT_HT")
	private BigDecimal montantHTMP ;
	
	
	/// Prix Moyen PF
	
	@ManyToOne
	@JoinColumn(name="id_Article")
	private Articles articles;
	
	@Column(name="SOUS_FAMILLE")
	private SousFamilleArticlePF sousFamille ;
	
		@Column(name="QUANTITE_SERVICE")
		private BigDecimal quantiteService ;

		@Column(name="PRIX_Service")
		private BigDecimal prixMoyenService ;
		
		@Column(name="MONTANT_HT_Service")
		private BigDecimal montantHTService ;

		/// Prix Moyen Finale
		
	
		
			@Column(name="QUANTITE_FINALE")
			private BigDecimal quantiteFinale ;

			@Column(name="PRIX_MOYEN_FINALE")
			private BigDecimal prixMoyenFinale ;
			
			@Column(name="MONTANT_HT_FINALE")
			private BigDecimal montantHTFinale ;

			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public MatierePremier getMp() {
				return mp;
			}

			public void setMp(MatierePremier mp) {
				this.mp = mp;
			}

			public BigDecimal getQuantiteMP() {
				return quantiteMP;
			}

			public void setQuantiteMP(BigDecimal quantiteMP) {
				this.quantiteMP = quantiteMP;
			}

			public BigDecimal getPrixMoyenMP() {
				return prixMoyenMP;
			}

			public void setPrixMoyenMP(BigDecimal prixMoyenMP) {
				this.prixMoyenMP = prixMoyenMP;
			}

			public BigDecimal getMontantHTMP() {
				return montantHTMP;
			}

			public void setMontantHTMP(BigDecimal montantHTMP) {
				this.montantHTMP = montantHTMP;
			}

			public Articles getArticles() {
				return articles;
			}

			public void setArticles(Articles articles) {
				this.articles = articles;
			}

			public BigDecimal getQuantiteService() {
				return quantiteService;
			}

			public void setQuantiteService(BigDecimal quantiteService) {
				this.quantiteService = quantiteService;
			}

			public BigDecimal getPrixMoyenService() {
				return prixMoyenService;
			}

			public void setPrixMoyenService(BigDecimal prixMoyenService) {
				this.prixMoyenService = prixMoyenService;
			}

			public BigDecimal getMontantHTService() {
				return montantHTService;
			}

			public void setMontantHTService(BigDecimal montantHTService) {
				this.montantHTService = montantHTService;
			}

			public BigDecimal getQuantiteFinale() {
				return quantiteFinale;
			}

			public void setQuantiteFinale(BigDecimal quantiteFinale) {
				this.quantiteFinale = quantiteFinale;
			}

			public BigDecimal getPrixMoyenFinale() {
				return prixMoyenFinale;
			}

			public void setPrixMoyenFinale(BigDecimal prixMoyenFinale) {
				this.prixMoyenFinale = prixMoyenFinale;
			}

			public BigDecimal getMontantHTFinale() {
				return montantHTFinale;
			}

			public void setMontantHTFinale(BigDecimal montantHTFinale) {
				this.montantHTFinale = montantHTFinale;
			}

			public static long getSerialversionuid() {
				return serialVersionUID;
			}

			public SousFamilleArticlePF getSousFamille() {
				return sousFamille;
			}

			public void setSousFamille(SousFamilleArticlePF sousFamille) {
				this.sousFamille = sousFamille;
			}

			
		
	
	


}