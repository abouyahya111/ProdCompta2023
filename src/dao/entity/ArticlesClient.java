package dao.entity;

import java.io.Serializable;
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
@NamedQuery(name="ArticlesClient.findAll", query="SELECT f FROM ArticlesClient f")
public class ArticlesClient implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private int id;

	@ManyToOne
	@JoinColumn(name="ID_CLIENT")
	private ClientPF clientPF;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_SAISI")
	private Date dateSaisi;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE_MODIFIER")
	private Date dateModifier;
	
	
	@ManyToOne
	@JoinColumn(name="MODIFIER_PAR")
	private Utilisateur modifierPar;
	
	
	@ManyToOne
	@JoinColumn(name="CREER_PAR")
	private Utilisateur creerPar;
	
	@ManyToOne
	@JoinColumn(name="ID_DEPOT")
	private Depot depot;
	
	@ManyToOne
	@JoinColumn(name="ID_MAGASIN")
	private Magasin magasin;
	
	@OneToMany(mappedBy="articlesclient")
	private List<DetailArticlesClient> detailArticlesClient;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ClientPF getClientPF() {
		return clientPF;
	}

	public void setClientPF(ClientPF clientPF) {
		this.clientPF = clientPF;
	}

	public Date getDateSaisi() {
		return dateSaisi;
	}

	public void setDateSaisi(Date dateSaisi) {
		this.dateSaisi = dateSaisi;
	}

	public Date getDateModifier() {
		return dateModifier;
	}

	public void setDateModifier(Date dateModifier) {
		this.dateModifier = dateModifier;
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

	public List<DetailArticlesClient> getDetailArticlesClient() {
		return detailArticlesClient;
	}

	public void setDetailArticlesClient(List<DetailArticlesClient> detailArticlesClient) {
		this.detailArticlesClient = detailArticlesClient;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	} 
	
	
	
	
}