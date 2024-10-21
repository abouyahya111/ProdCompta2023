package Production;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import main1.AuthentificationView;
import main1.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.JasperUtils;
import util.NumberUtils;
import util.Utils;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.CompteStockMPDAOImpl;
import dao.daoImplManager.CompteurEmployeProdDAOImpl;
import dao.daoImplManager.CompteurProductionDAOImpl;
import dao.daoImplManager.CompteurResponsableProdDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailFactureServiceProductionDAOImpl;
import dao.daoImplManager.DetailProdGenDAOImpl;
import dao.daoImplManager.DetailProductionDAOImpl;
import dao.daoImplManager.DetailResponsableProdDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.EquipeDAOImpl;
import dao.daoImplManager.FactureAutresVenteDAOImpl;
import dao.daoImplManager.FacturePFDAOImpl;
import dao.daoImplManager.FactureProductionDAOImpl;
import dao.daoImplManager.FactureServiceProductionDAOImpl;
import dao.daoImplManager.FactureServiceTransportDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.FicheEmployeDAOImpl;
import dao.daoImplManager.JourneeDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.MouvementStockGlobalDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.PromotionDAOImpl;
import dao.daoImplManager.SousFamilleArticlesPFDAOImpl;
import dao.daoImplManager.SousFamilleEnVracDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.CompteurEmployeProdDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.CompteurResponsableProdDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailFactureServiceProductionDAO;
import dao.daoManager.DetailProdGenDAO;
import dao.daoManager.DetailProductionDAO;
import dao.daoManager.DetailResponsableProdDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.EquipeDAO;
import dao.daoManager.FactureAutresVenteDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureProductionDAO;
import dao.daoManager.FactureServiceProductionDAO;
import dao.daoManager.FactureServiceTransportDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.FicheEmployeDAO;
import dao.daoManager.JourneeDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MouvementStockGlobalDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.PromotionDAO;
import dao.daoManager.SequenceurDAO;
import dao.daoManager.SousFamilleArticlesPFDAO;
import dao.daoManager.SousFamilleEnVracDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.daoManager.TransferStockPFDAO;
import dao.entity.Articles;
import dao.entity.Client;
import dao.entity.CompteStockMP;
import dao.entity.CompteurEmployeProd;
import dao.entity.CompteurProduction;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.DetailFacturePF;
import dao.entity.DetailFactureProduction;
import dao.entity.DetailFactureServiceProduction;
import dao.entity.DetailMouvementStock;
import dao.entity.DetailProdGen;
import dao.entity.DetailProduction;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.Equipe;
import dao.entity.FactureAutresVente;
import dao.entity.FacturePF;
import dao.entity.FactureProduction;
import dao.entity.FactureServiceProduction;
import dao.entity.FactureServiceTransport;
import dao.entity.FamilleArticlePF;
import dao.entity.FicheEmploye;
import dao.entity.Journee;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.MouvementStockGlobal;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.Promotion;
import dao.entity.Sequenceur;
import dao.entity.SousFamilleArticlePF;
import dao.entity.SousFamilleEnVrac;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.TransferStockMP;
import dao.entity.TransferStockPF;
import dao.entity.Utilisateur;

import javax.swing.JCheckBox;

public class TerminerOrdreFabrication extends JLayeredPane implements Constantes {
	public JLayeredPane contentPane;

	private DefaultTableModel modeleMP;
	private DefaultTableModel modeleEmploye;
	private DefaultTableModel modeleEquipeEm;
	private DefaultTableModel modeleEquipeGen;
	private JXTable table = new JXTable();
	private JXTable table_1 = new JXTable();
	private JXTable tableEmploye = new JXTable();
	private JXTable tableEmployeGen = new JXTable();
	private ImageIcon imgModifier;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgSupprimer;

	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnTerminerOF;
	private JButton btnRechercher;
	private JTextField txtPrixServiceProd;
	private JTextField codeArticle;

	private JComboBox<String> comboMachine;
	private JComboBox<String> comboLigneMachine;
	private JComboBox categorie;
	private List<SousFamilleEnVrac> listSousFamille = new ArrayList<SousFamilleEnVrac>();
	private List<CoutMP> listCoutMP = new ArrayList<CoutMP>();
	private List<CoutMP> listCoutMPTmp = new ArrayList<CoutMP>();
	private List<Employe> listEmploye = new ArrayList<Employe>();
	private List<DetailTransferStockMP> listDetailTransferMP = new ArrayList<DetailTransferStockMP>();
	private List<DetailProduction> listDetailProduction = new ArrayList<DetailProduction>();
	private List<DetailProduction> listDetailProductionTMP = new ArrayList<DetailProduction>();
	List<DetailProdGen> listDetailProdGenTmp = new ArrayList<DetailProdGen>();
	private List<DetailProdGen> listeDetailProdGen = new ArrayList<DetailProdGen>();
	List<DetailResponsableProd> listDetailResponsableProdTmp = new ArrayList<DetailResponsableProd>();
	private Production production = new Production();

	private Map<Integer, BigDecimal> mapQuantiteDetailMouvement = new HashMap<>();
	private Map<Integer, MatierePremier> mapMatierePremiere = new HashMap<>();
	private List<Journee> listJournee = new ArrayList<Journee>();
	private List<DetailMouvementStock> listDetailMouvementStock = new ArrayList<DetailMouvementStock>();
	private JourneeDAO journeeDAO;
	private MouvementStockGlobalDAO mouvementStockGlobalDAO;

	private Map<Integer, String> mapDelaiEmploye = new HashMap<>();
	private Map<Integer, String> mapDelaiEmployeEmabalage = new HashMap<>();

	private Map<Integer, String> mapHeureSupp25EmployeProd = new HashMap<>();
	private Map<Integer, String> mapHeureSupp50EmployeProd = new HashMap<>();

	private Map<Integer, String> mapHeureSupp25EmployeEmbalage = new HashMap<>();
	private Map<Integer, String> mapHeureSupp50EmployeEmbalage = new HashMap<>();

	private Map<String, String> mapQuantiteDechet = new HashMap<>();
	private Map<String, String> mapQuantiteReste = new HashMap<>();
	private Map<String, String> mapQuantiteDechetFour = new HashMap<>();
	private Map<String, String> mapQuantiteManquante = new HashMap<>();
	private Map<String, String> mapQuantiteOffre = new HashMap<>();

	private JComboBox<String> comboEquipe;
	public JTextField txtNumOF;
	private JTextField txtQuantiteRealise;
	private JLabel lblQuantitRalise;

	private BigDecimal coutTotalEmploye = BigDecimal.ZERO;
	private BigDecimal coutTotalEmployeEmbalage = BigDecimal.ZERO;
	private BigDecimal coutTotalAutreEmploye = BigDecimal.ZERO;
	private BigDecimal coutTotalMP = BigDecimal.ZERO;
	private BigDecimal coutTotalDechet = BigDecimal.ZERO;
	private JButton btnSaisieDelaiEquipeGen;
	private BigDecimal delaiTotal = BigDecimal.ZERO;
	private BigDecimal delaiTotalEquipeEmbalage;

	private ArticlesDAO articlesDAO;
	private DetailProdGenDAO detailProdGenDAO;
	private CompteurProductionDAO compteurProductionDAO;
	private StockMPDAO stockMPDAO;
	private StockPFDAO stockPFDAO;
	private ProductionDAO productionDAO;
	private TransferStockPFDAO transferStockPFDAO;
	private ParametreDAO parametreDAO;
	private FicheEmployeDAO ficheEmployeDAO;
	private CompteurResponsableProdDAO compteurResponsableProdDAO;
	private CompteurEmployeProdDAO compteurEmployeProdDAO;
	private EquipeDAO equipeDAO;
	private FactureProductionDAO factureProductionDAO;
	private MatierePremiereDAO matierePremiereDAO;
	private CompteStockMPDAO compteStockMPDAO;
	private DetailProductionDAO detailProductionDAO;
	private DetailResponsableProdDAO detailResponsableDAO;
	private SousFamilleEnVracDAO sousFamilleEnvracDAo;
	private SousFamilleArticlesPFDAO sousFamilleArticleDAo;
	private boolean validerSaisie = false;
	private String codeDepot;
	private JCheckBox chckbxRetourDepot = new JCheckBox();
	private TransferStockMPDAO transferStockMPDAO;
	private DetailTransferMPDAO detailtransferMPDAO;
	private DetailTransferProduitFiniDAO detailTransferProduitFiniDAO;
	private PromotionDAO promotiondao = new PromotionDAOImpl();
	private FamilleArticlesPFDAO famillePFDAO;
	private TransferStockMPDAO transferstockmpDAO;
	SousFamilleArticlePF sousfamilleEnvrac = null;
	SousFamilleArticlePF sousfamilleTmp = null;
	private Utilisateur utilisateur;
	private DepotDAO depotDAO;
	BigDecimal coutPF = BigDecimal.ZERO;
	private static SequenceurDAO sequenceurDAO=ProdLauncher.sequenceurDAO;
	private FactureServiceProductionDAO factureserviceproductionDAO;
	private DetailFactureServiceProductionDAO detailfactureserviceproductionDAO;
	private List<DetailFactureServiceProduction> listdetailFactureServiceProduction =new ArrayList<DetailFactureServiceProduction>();
	 private ClientDAO clientdao;
	 
/////////////////////////////////////////////////////////////// Liste Des Bl Non Facture ////////////////////////////////////////////////////////////////////////////////////////////////	  
	  
private List<FacturePF> listFacturePFBLNonFacturer =new ArrayList<FacturePF>();  
private List<FactureAutresVente> listFactureAutresVenteBLNonFacturer =new ArrayList<FactureAutresVente>(); 
private List<FactureServiceTransport> listFactureServiceTransportBLNonFacturer =new ArrayList<FactureServiceTransport>();
private List<Production> listProductionServiceLancerBLNonFacturer =new ArrayList<Production>();
FactureAutresVenteDAO factureAutresVenteDAO;
FactureServiceTransportDAO factureServiceTransportDAO;
private FacturePFDAO facturepfdao;

String MessageBlNonFacturer="";


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		  

	 
	 
	
	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	@SuppressWarnings("serial")
	public TerminerOrdreFabrication(Production productionP, String quantite, String nbreHeure) {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, 1408, 630);
		try {
			delaiTotalEquipeEmbalage = BigDecimal.ZERO;
			delaiTotal = BigDecimal.ZERO;
			coutTotalEmployeEmbalage = BigDecimal.ZERO;
			coutTotalDechet = BigDecimal.ZERO;
			coutTotalMP = BigDecimal.ZERO;

			listCoutMP = new ArrayList<CoutMP>();
			listEmploye = new ArrayList<Employe>();
			listDetailProduction = new ArrayList<DetailProduction>();
			listeDetailProdGen = new ArrayList<DetailProdGen>();

			depotDAO = new DepotDAOImpl();
			mapDelaiEmploye = new HashMap<>();
			mapDelaiEmployeEmabalage = new HashMap<>();
			// mapQuantiteConsomme = new HashMap<>();
			mapQuantiteDechet = new HashMap<>();
			mapQuantiteReste = new HashMap<>();
			mapQuantiteDechetFour = new HashMap<>();
			mapQuantiteManquante = new HashMap<>();
			mapQuantiteOffre = new HashMap<>();
			transferStockMPDAO = new TransferStockMPDAOImpl();
			mouvementStockGlobalDAO = new MouvementStockGlobalDAOImpl();
			journeeDAO = new JourneeDAOImpl();
			sousFamilleEnvracDAo = new SousFamilleEnVracDAOImpl();
			detailtransferMPDAO = new DetailTransferMPDAOImpl();
			mapHeureSupp25EmployeEmbalage = new HashMap<>();
			mapHeureSupp50EmployeEmbalage = new HashMap<>();
			mapHeureSupp25EmployeProd = new HashMap<>();
			mapHeureSupp50EmployeProd = new HashMap<>();
			productionDAO = new ProductionDAOImpl();
			detailProdGenDAO = new DetailProdGenDAOImpl();
			compteurProductionDAO = new CompteurProductionDAOImpl();
			transferStockPFDAO = new TransferStockPFDAOImpl();
			detailTransferProduitFiniDAO = new DetailTransferProduitFiniDAOImpl();
			stockMPDAO = new StockMPDAOImpl();
			stockPFDAO = new StockPFDAOImpl();
			parametreDAO = new ParametreDAOImpl();
			ficheEmployeDAO = new FicheEmployeDAOImpl();
			compteurResponsableProdDAO = new CompteurResponsableProdDAOImpl();
			compteurEmployeProdDAO = new CompteurEmployeProdDAOImpl();
			equipeDAO = new EquipeDAOImpl();
			factureProductionDAO = new FactureProductionDAOImpl();
			matierePremiereDAO = new MatierePremierDAOImpl();
			compteStockMPDAO = new CompteStockMPDAOImpl();
			detailProductionDAO = new DetailProductionDAOImpl();
			detailResponsableDAO = new DetailResponsableProdDAOImpl();
			txtQuantiteRealise = new JTextField();
			util.Utils.copycoller(txtQuantiteRealise);
			txtNumOF = new JTextField();
			util.Utils.copycoller(txtNumOF);
			txtPrixServiceProd = new JTextField();
			util.Utils.copycoller(txtPrixServiceProd);
			codeArticle = new JTextField();
			util.Utils.copycoller(codeArticle);
			categorie = new JComboBox();
			comboEquipe = new JComboBox<String>();
			comboLigneMachine = new JComboBox<String>();
			transferstockmpDAO = new TransferStockMPDAOImpl();
			articlesDAO = new ArticlesDAOImpl();
			famillePFDAO = new FamilleArticlesPFDAOImpl();
			utilisateur = AuthentificationView.utilisateur;
			comboMachine = new JComboBox<String>();
			factureserviceproductionDAO=new FactureServiceProductionDAOImpl();
         	detailfactureserviceproductionDAO=new DetailFactureServiceProductionDAOImpl();
         	clientdao=new ClientDAOImpl();
         	sousFamilleArticleDAo=new SousFamilleArticlesPFDAOImpl();
         	listSousFamille=sousFamilleEnvracDAo.findAll();
         	
         	facturepfdao=new FacturePFDAOImpl();
        	factureAutresVenteDAO=new FactureAutresVenteDAOImpl();
        	factureServiceTransportDAO=new FactureServiceTransportDAOImpl();
         	
         	
			if (productionP.getNumOF() != null) {

				production = productionP;
				txtNumOF.setText(production.getNumOF());
				txtQuantiteRealise.setText(quantite);
				txtPrixServiceProd.setText(nbreHeure);

				AfficherMatierePremiere();

			} else {
				production = new Production();
			}

		} catch (Exception exp) {
			exp.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de données", "Erreur",
					JOptionPane.ERROR_MESSAGE);
		}

		validerSaisie = false;

		try {
			imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
			imgInit = new ImageIcon(this.getClass().getResource("/img/init.png"));
			imgModifier = new ImageIcon(this.getClass().getResource("/img/modifier.png"));
			imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp1.png"));
		} catch (Exception exp) {
			exp.printStackTrace();
		}
		codeDepot = AuthentificationView.utilisateur.getCodeDepot();

		btnImprimer = new JButton("Imprimer D\u00E9tail OF");
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				List<CoutMP> listCoutMP = productionDAO.listeCoutMP(production.getId());

				Map parameters = new HashMap();
				parameters.put("numOF", production.getNumOF());
				parameters.put("ligneMachine", production.getLigneMachine().getNom());
				parameters.put("machine", production.getLigneMachine().getMachine().getNom());
				parameters.put("equipe", production.getEquipe().getNomEquipe());
				parameters.put("magasin", production.getMagasinProd().getLibelle());
				parameters.put("depot", production.getMagasinProd().getDepot().getLibelle());

				parameters.put("article", production.getArticles().getLiblle());
				parameters.put("CodeArticle", production.getArticles().getCodeArticle());
				parameters.put("quantiteDemande", production.getQuantiteEstime() + "");
				parameters.put("equipeGen", production.getEquipeGen().getNomEquipe());
				parameters.put("periode", production.getPeriode());

				parameters.put("quantiteRealise", production.getQuantiteReel() + "");
				parameters.put("heureTravaile", production.getNbreHeure() + "");

				JasperUtils.imprimerDetailOrdreFabrication(listCoutMP, parameters, production.getNumOF());

				// JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès",
				// JOptionPane.INFORMATION_MESSAGE);
			}
		});

		intialiserTableMP();
		initialiserTableauEmploye();
		initialiserTableauEquipeEmbalage();
		initialiserTableauEmployeGen();
		btnImprimer.setIcon(imgModifier);
		btnImprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnImprimer.setBounds(612, 599, 143, 24);
		add(btnImprimer);

		btnChercherOF = new JButton("Chercher OF");
		btnChercherOF.setHorizontalAlignment(SwingConstants.LEADING);
		btnChercherOF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				production = productionDAO.findByNumOF(txtNumOF.getText(), codeDepot);
				if (production != null) {
					listDetailProdGenTmp.clear();
					listDetailProductionTMP.clear();
					listDetailResponsableProdTmp.clear();
					if (txtQuantiteRealise.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Il faut saisir la quantité réalisée", "Erreur",
								JOptionPane.ERROR_MESSAGE);
					} else {
						List<CoutMP> listCoutMPTmp = productionDAO.listeCoutMP(production.getId());

						afficherDetailPorduction(production.getArticles().getDetailEstimation(), listCoutMPTmp);

						codeArticle.setText(production.getArticles().getCodeArticle());
						categorie.addItem(production.getArticles().getLiblle());
						categorie.setSelectedItem(production.getArticles().getLiblle());

						// comboEquipe.addItem(production.getEquipe().getNomEquipe());
						// comboEquipe.setSelectedItem(production.getEquipe().getNomEquipe());

						comboLigneMachine.addItem(production.getLigneMachine().getNom());
						comboLigneMachine.setSelectedItem(production.getLigneMachine().getNom());

						comboMachine.addItem(production.getLigneMachine().getMachine().getNom());
						comboMachine.setSelectedItem(production.getLigneMachine().getMachine().getNom());

						DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
						String dateDebutPrev = dateFormat.format(production.getDate_debFabPre());
						String dateFinPrev = dateFormat.format(production.getDateFinFabPre());

						listDetailProdGenTmp = productionDAO.listeDetailProdGen(production.getId());
						listDetailProductionTMP = productionDAO.listeDetailProduction(production.getId());
						listDetailResponsableProdTmp = productionDAO.listeDetailResponsableProd(production.getId());
						afficher_tableMP(listCoutMPTmp);
						afficher_tableEmploye(listDetailProductionTMP);
						afficher_tableEmployeEmabalage(listDetailProdGenTmp);
						afficher_tableEmployeGen(listDetailResponsableProdTmp);

					}
				} else {
					JOptionPane.showMessageDialog(null, "OF n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);

				}

			}
		});

		btnChercherOF.setIcon(new ImageIcon(CreerOrdreFabrication.class.getResource("/img/chercher.png")));
		btnChercherOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnChercherOF.setBounds(871, 8, 120, 23);
		add(btnChercherOF);

		btnTerminerOF = new JButton("Terminer OF");
		btnTerminerOF.setBounds(204, 599, 112, 24);
		add(btnTerminerOF);
		btnTerminerOF.setIcon(imgAjouter);
		btnTerminerOF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				MouvementStockGlobal mouvementStockGlobal = null;

				int reponse = JOptionPane.showConfirmDialog(null,
						"Vous voulez vraiment Terminer cet Ordre de Fabrication?", "Satisfaction",
						JOptionPane.YES_NO_OPTION);

				if (reponse == JOptionPane.YES_OPTION) {
					if (txtQuantiteRealise.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Il faut saisir la quantité réalisée!", "Erreur",
								JOptionPane.ERROR_MESSAGE);
					} else if (txtPrixServiceProd.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Il faut saisir le Prix Unitaire :Service Production!",
								"Erreur", JOptionPane.ERROR_MESSAGE);
					} else if (production.getListDetailProdGen() == null
							|| production.getListDetailProdGen().size() < 0) {
						JOptionPane.showMessageDialog(null, "Il faut valider les équipes avant de terminer OF!",
								"Erreur", JOptionPane.ERROR_MESSAGE);
					} else {
						if (production.getStatut().equals(Constantes.ETAT_OF_LANCER)) {

							if (validerSaisie = false) {
								JOptionPane.showMessageDialog(null, "Il faut valider la saisie!", "Erreur",
										JOptionPane.ERROR_MESSAGE);
							} else {
								
								if(production.getService().equals(Constantes.PRODUCTION_SERVICE_OUI))
								{
									
									ChercherBLNonFacture(production.getDate_debFabPre(),production);
									 if(!MessageBlNonFacturer.equals(""))
                             		 {
                             			 
                             			JOptionPane.showMessageDialog(null, MessageBlNonFacturer); 
                             			 return;
                             			 
                             		 }
									
								}
								
							
								
								BigDecimal coutTotal = coutTotalMP;

								production.setNbreHeure(new BigDecimal(txtPrixServiceProd.getText()));
								production.setQuantiteReel(new BigDecimal(txtQuantiteRealise.getText()));
								production.setDateDebFabRee(new Date());
								production.setUtilisateurMAJ(AuthentificationView.utilisateur);

								/* délai des employés Production */
								for (int j = 0; j < tableEmploye.getRowCount(); j++) {

									if (!tableEmploye.getValueAt(j, 3).toString().equals("")) {
										mapDelaiEmploye.put(Integer.parseInt(tableEmploye.getValueAt(j, 0).toString()),
												tableEmploye.getValueAt(j, 3).toString());
										delaiTotal = delaiTotal
												.add(new BigDecimal(tableEmploye.getValueAt(j, 3).toString()));
									} else
										mapDelaiEmploye.put(Integer.parseInt(tableEmploye.getValueAt(j, 0).toString()),
												String.valueOf(0));

									if (!tableEmploye.getValueAt(j, 4).toString().equals("")) {
										mapHeureSupp25EmployeProd.put(
												Integer.parseInt(tableEmploye.getValueAt(j, 0).toString()),
												tableEmploye.getValueAt(j, 4).toString());
									} else
										mapHeureSupp25EmployeProd.put(
												Integer.parseInt(tableEmploye.getValueAt(j, 0).toString()),
												String.valueOf(0));

									if (!tableEmploye.getValueAt(j, 5).toString().equals("")) {
										mapHeureSupp50EmployeProd.put(
												Integer.parseInt(tableEmploye.getValueAt(j, 0).toString()),
												tableEmploye.getValueAt(j, 5).toString());
									} else
										mapHeureSupp50EmployeProd.put(
												Integer.parseInt(tableEmploye.getValueAt(j, 0).toString()),
												String.valueOf(0));
								}

								/* délai des employés Emabalege */
								for (int j = 0; j < table_1.getRowCount(); j++) {
									if (!table_1.getValueAt(j, 3).toString().equals("")) {
										mapDelaiEmployeEmabalage.put(
												Integer.parseInt(table_1.getValueAt(j, 0).toString()),
												table_1.getValueAt(j, 3).toString());
										delaiTotalEquipeEmbalage = delaiTotalEquipeEmbalage
												.add(new BigDecimal(table_1.getValueAt(j, 3).toString()));
									} else
										mapDelaiEmployeEmabalage.put(
												Integer.parseInt(table_1.getValueAt(j, 0).toString()),
												String.valueOf(0));

									if (!table_1.getValueAt(j, 4).toString().equals("")) {
										mapHeureSupp25EmployeEmbalage.put(
												Integer.parseInt(table_1.getValueAt(j, 0).toString()),
												table_1.getValueAt(j, 4).toString());
									} else
										mapHeureSupp25EmployeEmbalage.put(
												Integer.parseInt(table_1.getValueAt(j, 0).toString()),
												String.valueOf(0));

									if (!table_1.getValueAt(j, 5).toString().equals("")) {
										mapHeureSupp50EmployeEmbalage.put(
												Integer.parseInt(table_1.getValueAt(j, 0).toString()),
												table_1.getValueAt(j, 5).toString());
									} else
										mapHeureSupp50EmployeEmbalage.put(
												Integer.parseInt(table_1.getValueAt(j, 0).toString()),
												String.valueOf(0));
								}

								//////////////////////////////////////////////////////////////////////////////
								/*
								 * listeDetailProdGen=production.getListDetailProdGen();
								 * listDetailProduction=production.getDetailProductions();
								 * 
								 * production.setDetailProductions(calculeCoutEmploye(listDetailProduction,
								 * mapDelaiEmploye));
								 * production.setListDetailProdGen(calculeCoutEmployeEmbalage(listeDetailProdGen
								 * ,mapDelaiEmployeEmabalage)); remplirQuantite();
								 * 
								 * 
								 * listCoutMPTmp=calculeCoutMatierePremiere(production.getListCoutMP());
								 * production.setListCoutMP(listCoutMPTmp);
								 * 
								 * production.setDateDebFabRee(new Date());
								 * 
								 * //production.setListDetailResponsableProd(listDetailResponsableProd);
								 * 
								 * production.setCoutTotalMP(coutTotalMP);
								 * production.setCoutTotalEmployeGen(coutTotalAutreEmploye);
								 * production.setCoutTotalEmployeEmbalage( coutTotalEmployeEmbalage);
								 * production.setCoutTotalEmploye(coutTotalEmploye);
								 * production.setCoutDechet(coutTotalDechet); coutTotal=coutTotalMP;
								 * 
								 * calculerStockCoutProduitFini(coutTotal); production.setNbreHeure(new
								 * BigDecimal(txtPrixServiceProd.getText()));
								 * production.setCoutTotal(coutPF.multiply(production.getQuantiteReel()));
								 * production.setStatut(Constantes.ETAT_OF_TERMINER);
								 * production.setQuantiteReel(new BigDecimal(txtQuantiteRealise.getText()));
								 * productionDAO.edit(production);
								 */

								///////////////////////////////////////////////////////////////////////////////////////////////////////////
								
								
								
								

								listeDetailProdGen = production.getListDetailProdGen();
								listDetailProduction = production.getDetailProductions();

								production.setDetailProductions(
										calculeCoutEmploye(listDetailProduction, mapDelaiEmploye));
								production.setListDetailProdGen(
										calculeCoutEmployeEmbalage(listeDetailProdGen, mapDelaiEmployeEmabalage));
								remplirQuantite();

								// production.setListCoutMP(calculeCoutMatierePremiere(production.getListCoutMP()));

								production.setDateDebFabRee(new Date());

								// production.setListDetailResponsableProd(listDetailResponsableProd);
								CalculeCoutMP();
								production.setCoutTotalMP(coutTotalMP);
								/*
								 * production.setCoutTotalEmployeGen(coutTotalAutreEmploye);
								 * production.setCoutTotalEmployeEmbalage( coutTotalEmployeEmbalage);
								 * production.setCoutTotalEmploye(coutTotalEmploye);
								 */
								production.setCoutDechet(coutTotalDechet);

								coutTotal = coutTotalMP;
								calculerStockCoutProduitFini(coutTotal);
								//////////////////////////////////////////////////////////////
								production.setListCoutMP(calculeCoutMatierePremiere(production.getListCoutMP()));
								production.setNbreHeure(new BigDecimal(txtPrixServiceProd.getText()));
								production.setQuantiteReel(new BigDecimal(txtQuantiteRealise.getText()));
								production.setDateDebFabRee(new Date());
								production.setUtilisateurMAJ(AuthentificationView.utilisateur);

								//////////////////////////////////////////////////////////////////////

								production.setCoutTotal(coutPF.multiply(production.getQuantiteReel()));
								production.setStatut(Constantes.ETAT_OF_TERMINER);
								productionDAO.edit(production);

								// calculRemiseResponsableProduction(production.getDate(),
								// production.getPeriode());

								transfertMPEnProduitFini(production.getListCoutMP());

								/////////////////////////////// ajouter la quantite dechet au transfer stock mp
								/////////////////////////////// ////////////////////////////////////////
								/*
								 * listDetailTransferMP=detailtransferMPDAO.findDetailTransferMPByNumOFStatut(
								 * production.getNumOF(), production.getMagasinStockage(),
								 * Constantes.ETAT_TRANSFER_STOCK_CHARGE);
								 * 
								 * for(int i=0;i<production.getListCoutMP().size();i++) { CoutMP
								 * coutmp=production.getListCoutMP().get(i); for(int j=0;
								 * j<listDetailTransferMP.size();j++) {
								 * if(listDetailTransferMP.get(j).getMatierePremier().equals(coutmp.
								 * getMatierePremier())) { DetailTransferStockMP
								 * detailtransferstockmp=listDetailTransferMP.get(j); StockMP
								 * stockmp=stockMPDAO.findStockByMagasinMP(coutmp.getMatierePremier().getId(),
								 * production.getMagasinStockage().getId());
								 * detailtransferstockmp.setQuantiteDechet(coutmp.getQuantDechet());
								 * if(stockmp.getStockDechet()!=null) {
								 * stockmp.setStockDechet(stockmp.getStockDechet().add(coutmp.getQuantDechet()))
								 * ; }else { stockmp.setStockDechet(coutmp.getQuantDechet()); }
								 * 
								 * if(coutmp.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().
								 * equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU)) {
								 * detailtransferstockmp.setQuantiteOffre(coutmp.getQuantiteOffre());
								 * if(stockmp.getStockOffre()!=null) {
								 * stockmp.setStockOffre(stockmp.getStockOffre().add(coutmp.getQuantiteOffre()))
								 * ; }else { stockmp.setStockOffre(coutmp.getQuantiteOffre()); }
								 * 
								 * }else { detailtransferstockmp.setQuantite(coutmp.getQuantConsomme());
								 * 
								 * } stockMPDAO.edit(stockmp);
								 * 
								 * detailtransferMPDAO.edit(detailtransferstockmp);
								 * 
								 * } }
								 * 
								 * 
								 * }
								 */

								/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
								/*
								 * if(chckbxRetourDepot.isSelected()==true) {
								 * 
								 * 
								 * 
								 * Journee journee=journeeDAO.findByDateEtatOuverte(production.getDate(),
								 * Constantes.ETAT_STATUT_OVERTE,production.getMagasinStockage().getDepot().
								 * getId()); if(journee!=null) {
								 * 
								 * 
								 * mouvementStockGlobal=mouvementStockGlobalDAO.
								 * findMouvementStockGlobalByDetailMouvementStock(production.getDate(),
								 * production.getMagasinStockage().getDepot().getId(),production.
								 * getMagasinStockage().getId()); if(mouvementStockGlobal!=null) {
								 * listDetailMouvementStock=mouvementStockGlobal.getDetailMouvementStock();
								 * 
								 * 
								 * } }
								 * 
								 * 
								 * 
								 * for(int j=0;j<listDetailMouvementStock.size();j++) { MatierePremier
								 * matierepremiere=listDetailMouvementStock.get(j).getMatierePremier();
								 * DetailMouvementStock detailMouvementStock=listDetailMouvementStock.get(j);
								 * MatierePremier
								 * matierepremiereTmp=mapMatierePremiere.get(matierepremiere.getId());
								 * if(matierepremiereTmp!=null) { detailMouvementStock.setRetour
								 * (detailMouvementStock.getRetour().add(mapQuantiteDetailMouvement.get(
								 * matierepremiere.getId())));
								 * detailMouvementStock.setStockFinaldb((detailMouvementStock.getInitial().add(
								 * detailMouvementStock.getReception()).add(detailMouvementStock.
								 * getTransfertEntrees()).subtract((detailMouvementStock.getSorties().add(
								 * detailMouvementStock.getTransfertSorties()).add(detailMouvementStock.
								 * getCharge()).add(detailMouvementStock.getChargeSupplementaire())))).add(
								 * detailMouvementStock.getRetour()));
								 * 
								 * listDetailMouvementStock.set(j, detailMouvementStock);
								 * 
								 * 
								 * }
								 * 
								 * 
								 * } if(mouvementStockGlobal!=null) {
								 * mouvementStockGlobal.setDetailMouvementStock(listDetailMouvementStock);
								 * 
								 * mouvementStockGlobalDAO.edit(mouvementStockGlobal);
								 * 
								 * }
								 * 
								 * }
								 */

								// JOptionPane.showMessageDialog(null, "Prix : "+coutPF +" Cout Total : "+
								// production.getCoutTotal());

								JOptionPane.showMessageDialog(null, "Ordre de Fabrication Terminé avec succès!","Succès", JOptionPane.INFORMATION_MESSAGE);

								
								if(production.getService().equals(Constantes.PRODUCTION_SERVICE_OUI))
								{
									
									BigDecimal MonatantHT=BigDecimal.ZERO;
									Parametre coutService=parametreDAO.findByCode(Constantes.COUT_SERVICE);
									 Parametre nombre_facture_par_jour=parametreDAO.findByCode(Constantes.NOMBRE_FACTURE_PAR_JOUR);
									
										boolean cadeau =false;
										Parametre PercentagePrixCadeau=parametreDAO.findByCode(Constantes.PERCENTAGE_PRIX_OFFRE);
										
										  Magasin  magasinElnasTeaPacking=null;
											
										  magasinElnasTeaPacking=depotDAO.magasinByCode(CODE_MAGASIN_ELNASS_TEA_PACKING);
										
										FactureServiceProduction factureServiceProduction=new FactureServiceProduction();
										
										BigDecimal QuantiteOffre=BigDecimal.ZERO;
										
										//MonatantHT=MonatantHT.add(production.getCoutTotal());
										DetailFactureServiceProduction detailfactureserviceproduction=new DetailFactureServiceProduction();
										detailfactureserviceproduction.setArticle(production.getArticles().getLiblle());
										for(int j=0;j<production.getListCoutMP().size();j++)
										{
											CoutMP coutmp=production.getListCoutMP().get(j);
											
											if(coutmp.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
											{
												
												QuantiteOffre=QuantiteOffre.add(coutmp.getQuantiteOffre());
												
											}
											if(coutmp.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
											{
												
													if(coutmp.getMagasin()!=null)
													{
														if(coutmp.getMagasin().getLibelle().equals(magasinElnasTeaPacking.getLibelle()))
														{
															
															cadeau=true;
															
														}
														
													}
													
													
	    	                                   
												
																								
											}
											
											
										}
										  BigDecimal PrixOffre=BigDecimal.ZERO;
										 
										 if(production.getOffre()!=null)
										 {
											
											Promotion  promotion=promotiondao.findByCode(production.getOffre());
										
											  if(promotion!=null)
											  {
												  for(int j=0;j<promotion.getDetailEstimationPromo().size();j++)
												  {
													/* if(promotion.getDetailEstimationPromo().get(j).getMatierePremiere() .getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
													 {
													  QuantiteEnVrac=QuantiteEnVrac.add(promotion.getDetailEstimationPromo().get(j).getQuantite()); 
													 }*/
													 
													 if(production.getService().equals(Constantes.PRODUCTION_SERVICE_OUI))
													 {
														 if(promotion.getDetailEstimationPromo().get(j).getMatierePremiere() .getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
														 {
															 StockMP stockMP=stockMPDAO.findStockByMagasinMP(promotion.getDetailEstimationPromo().get(j).getMatierePremiere().getId(), magasinElnasTeaPacking.getId());
															 
															
															 if(cadeau==true)
															 {
																 PrixOffre=PrixOffre.add(stockMP.getPrixUnitaire().multiply(promotion.getDetailEstimationPromo().get(j).getQuantite()).multiply((PercentagePrixCadeau.getValeur().divide(new BigDecimal(100), 6, RoundingMode.UP).add(BigDecimal.ONE)))); 
 
															 }
															 
															 
														 }else
														 {
															/* StockMP stockMP=stockMPDAO.findStockByMagasinMP(promotion.getDetailEstimationPromo().get(i).getMatierePremiere().getId(), magasinElnasTeaPacking.getId());
															 PrixOffre=PrixOffre.add(stockMP.getPrixUnitaire().multiply(promotion.getDetailEstimationPromo().get(i).getQuantite()).multiply(new BigDecimal(1.2))); */
														 }
														 
													 }else
													 {
														 StockMP stockMP=stockMPDAO.findStockByMagasinMP(promotion.getDetailEstimationPromo().get(j).getMatierePremiere().getId(), production.getMagasinStockage().getId());
														 PrixOffre=PrixOffre.add(stockMP.getPrixUnitaire().multiply(promotion.getDetailEstimationPromo().get(j).getQuantite()).multiply(new BigDecimal(1.2))); 
													 }
													
												  }
												  
												  PrixOffre=PrixOffre.divide(production.getArticles().getCentreCout3(), RoundingMode.HALF_UP); // centercout3 est le poids de carton 
											  }
											  
										 }
										
										
										 Parametre parametre=parametreDAO.findByCode(COUT_SERVICE);
										 BigDecimal quantite=production.getQuantiteReel().add(QuantiteOffre);
										 
										 PrixOffre=PrixOffre.add(parametre.getValeur());
										 
										 MonatantHT=quantite.multiply(PrixOffre);
										detailfactureserviceproduction.setQuantite(production.getQuantiteReel().add(QuantiteOffre));
										detailfactureserviceproduction.setPrix(PrixOffre);
										detailfactureserviceproduction.setMontantHT(MonatantHT);
										detailfactureserviceproduction.setUtilisateur(utilisateur);
										detailfactureserviceproduction.setFactureService(factureServiceProduction);
										
										listdetailFactureServiceProduction.add(detailfactureserviceproduction);
										production.setService(Constantes.TRANSFERE_BL_FACTURE);
										
										productionDAO.edit(production);
									
										
											
										//Production productionTmp=ListProductionServiceAfficher.get(table.getSelectedRow());
										
											for(int t=0;t<production.getListCoutMP().size();t++)
											{
												
												CoutMP coutMP=production.getListCoutMP().get(t);
												if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
												{
													
													
													DetailFactureServiceProduction detailfactureserviceproductionTmp=new DetailFactureServiceProduction();
													detailfactureserviceproductionTmp.setArticle(coutMP.getMatierePremier().getNom());									
													detailfactureserviceproductionTmp.setQuantite(coutMP.getQuantiteOffre());
													detailfactureserviceproductionTmp.setPrix(BigDecimal.ZERO);
													detailfactureserviceproductionTmp.setMontantHT(BigDecimal.ZERO);
													detailfactureserviceproductionTmp.setUtilisateur(utilisateur);
													detailfactureserviceproductionTmp.setFactureService(factureServiceProduction);							
													listdetailFactureServiceProduction.add(detailfactureserviceproductionTmp);
													
												}
														
											}
											
											//Date d=production.getDate_debFabPre();
										
											/// Numerotation 2019
											SimpleDateFormat dcn = new SimpleDateFormat("yyyy");
											
											///SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
										String date = dcn.format(production.getDate_debFabPre());
										String NumFacture=Utils.genererCodeFactureVente(date);
										
				/////////////////////////////////////////////////// New Numerotation 2019  ////////////////////////////////////////////////////////////////////////////////						 
									/*
									 * if(NumFacture.equals("")) {
									 * 
									 * d =util.DateUtils.ajoutNbJours(production.getDate_debFabPre(), 1); Calendar
									 * cal=Calendar.getInstance(); cal.setTime(d);
									 * 
									 * if(cal.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
									 * d=util.DateUtils.ajoutNbJours(d, 1); } while(NumFacture.equals("")) {
									 * 
									 * NumFacture=Utils.genererCodeFactureVente(d);
									 * 
									 * if(!NumFacture.equals("")) { date = dcn.format(d);
									 * 
									 * break; }else {
									 * 
									 * d= util.DateUtils.ajoutNbJours(d, 1); Calendar calTmp=Calendar.getInstance();
									 * calTmp.setTime(d);
									 * 
									 * if(calTmp.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
									 * d=util.DateUtils.ajoutNbJours(d, 1); }
									 * 
									 * }
									 * 
									 * 
									 * };
									 * 
									 * }
									 */
									//////////////////////////////////////////////////////////////////////////////////////////////////////	
										
										if(listdetailFactureServiceProduction.size()!=0)
										{
											//factureServiceProduction.setNumFacture(Utils.genererCodeFactureVente(production.getDate_debFabPre())); //New Numerotation 2019
											Client client=clientdao.findClientByCodeClient(production.getMagasinPF().getCodeMachine());
											Depot depot=depotDAO.findByCode(production.getCodeDepot());
											factureServiceProduction.setNumFacture(NumFacture);
											factureServiceProduction.setClient(client);
											factureServiceProduction.setCreerPar(utilisateur);
											factureServiceProduction.setDateFacture(production.getDate_debFabPre());
											factureServiceProduction.setDateSaisi(new Date());
											factureServiceProduction.setDepot(depot);
											factureServiceProduction.setNumOF(production.getNumOF());
											factureServiceProduction.setMontantHT(MonatantHT);
											if(client.isTva()==true)
											{
												factureServiceProduction.setMontantTVA(MonatantHT.multiply(new BigDecimal(0.2)));
												
											}else
											{
												factureServiceProduction.setMontantTVA(MonatantHT.multiply(new BigDecimal(0)));
												
											}
											
											factureServiceProduction.setMontantTTC(factureServiceProduction.getMontantHT().add(factureServiceProduction.getMontantTVA()));
											
										//	factureServiceProduction.setDetailFactureServiceProduction(listdetailFactureServiceProduction);
											factureServiceProduction.setEtat(Constantes.ETAT_REGLE);
											factureServiceProduction.setType(Constantes.TRANSFERE_BL_FACTURE);
											factureserviceproductionDAO.add(factureServiceProduction);
					
											for(int j=0;j<listdetailFactureServiceProduction.size();j++)
											{
												detailfactureserviceproductionDAO.add(listdetailFactureServiceProduction.get(j));
											}
											
											production.setNumFacture(NumFacture);
											productionDAO.edit(production);
											
						//////////////////////////////////////////////////////////	New Numerotation 2019 ////////////////////////////////////////////////////////////				
										//New Numerotation 2019
										/*
										 * Calendar c = Calendar.getInstance();
										 * 
										 * c.setTime(d); int nbrjrs=c.get(Calendar.DAY_OF_YEAR); int nbrsunday = nbrjrs
										 * / 7; int nbrjrssansweekend=nbrjrs-nbrsunday;
										 * 
										 * 
										 * 
										 * if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
										 * 
										 * { Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
										 * Constantes.CODE_FACTURE_VENTE_ETP); if(sequenceur!=null) { int
										 * valeur=sequenceur.getValeur()+1; sequenceur.setValeur(valeur);
										 * sequenceurDAO.edit(sequenceur); }else {
										 * 
										 * int valeur=((nbrjrssansweekend-1) *
										 * nombre_facture_par_jour.getValeur().intValue())+1; Sequenceur
										 * sequenceurTmp=new Sequenceur(); sequenceurTmp.setCode(date);
										 * sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_ETP);
										 * sequenceurTmp.setValeur(valeur); sequenceurDAO.add(sequenceurTmp); }
										 * 
										 * }else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR)) {
										 * Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date,
										 * Constantes.CODE_FACTURE_VENTE_AHB); if(sequenceur!=null) { int
										 * valeur=sequenceur.getValeur()+1; sequenceur.setValeur(valeur);
										 * sequenceurDAO.edit(sequenceur); }else { int valeur=((nbrjrssansweekend-1) *
										 * nombre_facture_par_jour.getValeur().intValue())+1; Sequenceur
										 * sequenceurTmp=new Sequenceur(); sequenceurTmp.setCode(date);
										 * sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_AHB);
										 * sequenceurTmp.setValeur(valeur); sequenceurDAO.add(sequenceurTmp); }
										 * 
										 * }
										 */
						//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////				 
											
									
                      ///////////////////////////////////////////////////////  Numerotation 2019   ///////////////////////////////////////////////////////////////			
											
											//Numerotation 2019
										  if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
										  
										  { Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date, Constantes.CODE_FACTURE_VENTE_ETP);
										  if(sequenceur!=null) {
											  int valeur=sequenceur.getValeur()+1; 
											  sequenceur.setValeur(valeur);
										  sequenceurDAO.edit(sequenceur); 
										  }else { 
											  Sequenceur sequenceurTmp=new Sequenceur();
											  sequenceurTmp.setCode(date);
										  sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_ETP);
										  sequenceurTmp.setValeur(1); 
										  sequenceurDAO.add(sequenceurTmp);
										  }
										  
										  }else if(
										 utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR))
										  {
										  Sequenceur sequenceur=sequenceurDAO.findByCodeLibelle(date, Constantes.CODE_FACTURE_VENTE_AHB);
										  if(sequenceur!=null)
										  { int  valeur=sequenceur.getValeur()+1; 
										  sequenceur.setValeur(valeur);
										  sequenceurDAO.edit(sequenceur);
										  }else {
											  
											  Sequenceur sequenceurTmp=new Sequenceur(); 
											  sequenceurTmp.setCode(date);
										  sequenceurTmp.setLibelle(Constantes.CODE_FACTURE_VENTE_AHB);
										  sequenceurTmp.setValeur(1); 
										  sequenceurDAO.add(sequenceurTmp); }
										  
										  }
										 
											
										}
									
										JOptionPane.showMessageDialog(null, "Facture a été crée avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE); 
												 
									
									
								}
								
								/*
								 * int rep = JOptionPane.showConfirmDialog(null,
								 * "Vous voulez créer une facture de service ?", "Satisfaction",
								 * JOptionPane.YES_NO_OPTION);
								 * 
								 * if(rep == JOptionPane.YES_OPTION ) { List<DetailFactureProduction>
								 * listDetailFactureProduction= new ArrayList<DetailFactureProduction>();
								 * DetailFactureProduction detailFactureProduction= new
								 * DetailFactureProduction(); FactureProduction factureProduction = new
								 * FactureProduction();
								 * 
								 * ClientDAO clientDAO =ProdLauncher.clientDAO;
								 * 
								 * 
								 * Client clientFournisseur=new Client(); Client client=new Client();
								 * 
								 * if(production.getNumOF().substring(0,
								 * 1).equals(DEBUT_NUM_OF_PRODUCTION_TANTAN))
								 * clientFournisseur=clientDAO.findClientByCodeClient(
								 * CODE_CLIENT_FOURNISSEUR_SERVICE_PRODUCTION_TANTAN);
								 * 
								 * else if (production.getNumOF().substring(0,
								 * 1).equals(DEBUT_NUM_OF_PRODUCTION_LAAYOUNE))
								 * clientFournisseur=clientDAO.findClientByCodeClient(
								 * CODE_CLIENT_FOURNISSEUR_SERVICE_PRODUCTION_LAAYOUNE);
								 * 
								 * client=clientDAO.findClientByCodeClient(production.getMagasinStockage().
								 * getCodeMachine());
								 * 
								 * Creation service production MatierePremier
								 * matierePremier=matierePremiereDAO.findByCode(
								 * MATIERE_PREMIERE_SERVICE_PRODUCTION);
								 * 
								 * 
								 * 
								 * 
								 * creation de la facture BigDecimal montantGlobalFacture=BigDecimal.ZERO;
								 * BigDecimal prixFacture=new BigDecimal(txtPrixServiceProd.getText());
								 * BigDecimal quantite=new BigDecimal(txtQuantiteRealise.getText()); BigDecimal
								 * montantTotal =prixFacture.multiply(quantite);
								 * detailFactureProduction.setMatierePremier(matierePremier);
								 * detailFactureProduction.setPrixUnitaire(prixFacture);
								 * detailFactureProduction.setQuantite(quantite);
								 * detailFactureProduction.setMontantTotal(montantTotal);
								 * detailFactureProduction.setFactureProduction(factureProduction);
								 * listDetailFactureProduction.add(detailFactureProduction);
								 * montantGlobalFacture=montantGlobalFacture.add(montantTotal);
								 * 
								 * factureProduction.setClientFournisseurMP(clientFournisseur);
								 * factureProduction.setClientMP(client);
								 * factureProduction.setCreerPar(AuthentificationView.utilisateur);
								 * factureProduction.setDateFacture(new Date());
								 * factureProduction.setDetailFactureProduction(listDetailFactureProduction);
								 * factureProduction.setMontantTotal(montantGlobalFacture);
								 * factureProduction.setNumFacture(Utils.genererNumFactureProduction(
								 * FACTURE_PRODUCTION_LIBELLE));
								 * factureProduction.setNumOF(production.getNumOF());
								 * 
								 * 
								 * factureProductionDAO.add(factureProduction);
								 * JOptionPane.showMessageDialog(null, "Facture a été crée avec succès!",
								 * "Succès", JOptionPane.INFORMATION_MESSAGE); }
								 */
							}

						} else {
							JOptionPane.showMessageDialog(null,
									"Ordre de Fabrication n'est pas encore lancé ou terminé!", "Attention",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}
		});
		btnTerminerOF.setFont(new Font("Tahoma", Font.PLAIN, 11));

		table_1.setShowVerticalLines(true);
		table_1.setSelectionBackground(new Color(51, 204, 255));
		table_1.setRowHeightEnabled(true);
		table_1.setBackground(new Color(255, 255, 255));
		table_1.setHighlighters(HighlighterFactory.createSimpleStriping());
		table_1.setColumnControlVisible(true);
		table_1.setForeground(Color.BLACK);
		table_1.setGridColor(new Color(0, 0, 255));
		table_1.setAutoCreateRowSorter(true);
		table_1.setBounds(2, 27, 411, 198);
		table_1.setRowHeight(20);
		DefaultCellEditor ce1 = (DefaultCellEditor) table_1.getDefaultEditor(Object.class);
		JTextComponent textField1 = (JTextComponent) ce1.getComponent();
		util.Utils.copycollercell(textField1);
		table.setShowVerticalLines(true);
		table.setSelectionBackground(new Color(51, 204, 255));
		table.setRowHeightEnabled(true);
		table.setBackground(new Color(255, 255, 255));
		table.setHighlighters(HighlighterFactory.createSimpleStriping());
		table.setColumnControlVisible(true);
		table.setForeground(Color.BLACK);
		table.setGridColor(new Color(0, 0, 255));
		table.setAutoCreateRowSorter(true);
		table.setBounds(2, 27, 411, 198);
		table.setRowHeight(20);
		DefaultCellEditor ce = (DefaultCellEditor) table.getDefaultEditor(Object.class);
		JTextComponent textField = (JTextComponent) ce.getComponent();
		util.Utils.copycollercell(textField);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(8, 80, 1323, 186);
		add(scrollPane);
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		JXTitledSeparator titledSeparator = new JXTitledSeparator();
		titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
		titledSeparator.setBounds(7, 59, 1323, 23);
		add(titledSeparator);

		txtNumOF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == e.VK_ENTER) {

					production = productionDAO.findByNumOF(txtNumOF.getText(), codeDepot);
					if (production != null) {

						if (txtQuantiteRealise.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Il faut saisir la quantité réalisée", "Erreur",
									JOptionPane.ERROR_MESSAGE);
						} else {
							List<CoutMP> listCoutMPTmp = production.getListCoutMP();
							afficherDetailPorduction(production.getArticles().getDetailEstimation(), listCoutMPTmp);
							listEmploye = production.getEquipe().getListEmploye();
							// txtDescription.setText(production.getDescription());
							// txtNbreHeure.setText(""+production.getQuantiteEstime());
							codeArticle.setText(production.getArticles().getCodeArticle());
							categorie.addItem(production.getArticles().getLiblle());
							categorie.setSelectedItem(production.getArticles().getLiblle());

							comboEquipe.addItem(production.getEquipe().getNomEquipe());
							comboEquipe.setSelectedItem(production.getEquipe().getNomEquipe());

							comboLigneMachine.addItem(production.getLigneMachine().getNom());
							comboLigneMachine.setSelectedItem(production.getLigneMachine().getNom());

							comboMachine.addItem(production.getLigneMachine().getMachine().getNom());
							comboMachine.setSelectedItem(production.getLigneMachine().getMachine().getNom());

							DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
							String dateDebutPrev = dateFormat.format(production.getDate_debFabPre());
							String dateFinPrev = dateFormat.format(production.getDateFinFabPre());

							afficher_tableMP(listCoutMPTmp);
							// afficher_tableEmploye(listEmploye);
						}
					} else {
						JOptionPane.showMessageDialog(null, "OF n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);

					}

				}
			}
		});

		txtNumOF.setBounds(79, 6, 153, 26);
		add(txtNumOF);
		txtNumOF.setColumns(10);

		JLabel lblNumOF = new JLabel("Num\u00E9ro OF");
		lblNumOF.setBounds(9, 7, 89, 24);
		add(lblNumOF);

		JScrollPane scrollPane_1 = new JScrollPane(tableEmploye);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(9, 281, 594, 314);
		add(scrollPane_1);
		tableEmploye.setHighlighters(HighlighterFactory.createSimpleStriping());
		tableEmploye.setShowVerticalLines(true);
		tableEmploye.setSelectionBackground(new Color(51, 204, 255));
		tableEmploye.setRowHeightEnabled(true);
		tableEmploye.setRowHeight(20);
		tableEmploye.setGridColor(new Color(0, 0, 255));
		tableEmploye.setForeground(Color.BLACK);
		tableEmploye.setColumnControlVisible(true);
		tableEmploye.setBackground(new Color(255, 255, 255));
		tableEmploye.setAutoCreateRowSorter(true);
		// scrollPane_1.setViewportView(tableEmploye);
		DefaultCellEditor ce2 = (DefaultCellEditor) tableEmploye.getDefaultEditor(Object.class);
		JTextComponent textField2 = (JTextComponent) ce2.getComponent();
		util.Utils.copycollercell(textField2);
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[] { 0.0 };
		gridBagLayout.rowHeights = new int[] { 0 };
		gridBagLayout.columnWeights = new double[] { 0.0, 0.0, 0.0 };
		gridBagLayout.columnWidths = new int[] { 0, 0, 0 };
		titledSeparator_1.setTitle("Saisir D\u00E9lai Equipe Production");
		titledSeparator_1.setBounds(9, 265, 594, 17);
		add(titledSeparator_1);

		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(9, 34, 1322, 29);
		add(layeredPane);
		layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		btnSaisieDelaiEquipeGen = new JButton("Saisir Delai Equipe");
		btnSaisieDelaiEquipeGen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (production.getId() > 0) {
					JFrame popupJFrame = new SaisirListeEmployeGen(production, txtQuantiteRealise.getText(),
							txtPrixServiceProd.getText());
					popupJFrame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "OF introuvable !!!!!", "Erreur", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnSaisieDelaiEquipeGen.setBounds(765, 599, 127, 24);
		add(btnSaisieDelaiEquipeGen);

		JButton btnValiderSaisie = new JButton("Valider Saisie");
		btnValiderSaisie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				remplirQuantite();
				List<CoutMP> listCoutMPTmp = productionDAO.listeCoutMP(production.getId());
				afficherDetailPorduction(production.getArticles().getDetailEstimation(), listCoutMPTmp);
				validerSaisiQuantiteDechetReste(listCoutMPTmp);
			}
		});
		btnValiderSaisie.setBounds(479, 599, 112, 24);
		add(btnValiderSaisie);

		txtQuantiteRealise.setBounds(345, 6, 153, 26);
		add(txtQuantiteRealise);
		txtQuantiteRealise.setColumns(10);

		lblQuantitRalise = new JLabel("Quantit\u00E9 r\u00E9alis\u00E9e:");
		lblQuantitRalise.setBounds(242, 6, 102, 26);
		add(lblQuantitRalise);
		lblQuantitRalise.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JScrollPane scrollPane_2 = new JScrollPane(table_1);
		scrollPane_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_2.setBounds(683, 284, 646, 145);
		add(scrollPane_2);

		JXTitledSeparator titledSeparator_2 = new JXTitledSeparator();
		GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator_2.getLayout();
		gridBagLayout_1.rowWeights = new double[] { 0.0 };
		gridBagLayout_1.rowHeights = new int[] { 0 };
		gridBagLayout_1.columnWeights = new double[] { 0.0, 0.0, 0.0 };
		gridBagLayout_1.columnWidths = new int[] { 0, 0, 0 };
		titledSeparator_2.setTitle("Saisir D\u00E9lai Equipe Embalage");
		titledSeparator_2.setBounds(683, 267, 646, 17);
		add(titledSeparator_2);

		txtPrixServiceProd.setBounds(692, 6, 153, 26);
		add(txtPrixServiceProd);
		txtPrixServiceProd.setColumns(10);

		JLabel lblQuantite = new JLabel("Delai Service Production :");
		lblQuantite.setBounds(527, 6, 175, 26);
		add(lblQuantite);
		lblQuantite.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (production.getId() < 0) {
					JOptionPane.showMessageDialog(null, "Il faut Cherercher l'OF à Annuler", "Message",
							JOptionPane.ERROR_MESSAGE);

				} else {

					if (!production.getStatut().equals(ETAT_OF_ANNULER)) {
						if (production.getStatut().equals(ETAT_OF_TERMINER)) {
							
							
							if(JOptionPane.showConfirmDialog(null, "Voullez vous vraiment Annuler L'OF ?","Confirmation",JOptionPane.YES_OPTION)==JOptionPane.YES_OPTION)
	  		   				{
								
								CompteurProduction compteurProduction = compteurProductionDAO
										.findByDateProdPeriode(production.getDate(), production.getPeriode());
								int compteurProd = compteurProduction.getCompteur();
								compteurProd = compteurProd - 1;
								compteurProduction.setCompteur(compteurProd);

								annulerStockMatierePremiere(production.getListCoutMP(), production.getMagasinProd().getId(),
										production.getMagasinStockage().getId());

								annulerStockProduitFini();
								
								
							FactureServiceProduction factureServiceProduction=factureserviceproductionDAO.findByNumOF(production.getNumOF());
								if(factureServiceProduction!=null)
								{
									
								   listdetailFactureServiceProduction=detailfactureserviceproductionDAO.listeDetailFactureServiceProductionByFacture(factureServiceProduction.getId())	;
									for(int i=0;i<listdetailFactureServiceProduction.size();i++)
									{
										
									DetailFactureServiceProduction detailFactureServiceProduction=	listdetailFactureServiceProduction.get(i);
									detailfactureserviceproductionDAO.delete(detailFactureServiceProduction.getId());
										
										
										
									}
									
									factureserviceproductionDAO.delete(factureServiceProduction.getId());
									
								}
								
								

								deleteListeObject(ficheEmployeDAO.findByNumOf(production.getNumOF()));
								production.setStatut(ETAT_OF_ANNULER);
								production.setUtilisateurAnnulation(AuthentificationView.utilisateur);
								productionDAO.edit(production);
								compteurProductionDAO.edit(compteurProduction);
								JOptionPane.showMessageDialog(null, "OF Annulé avec succès", "Message",
										JOptionPane.ERROR_MESSAGE);
								
								
								
	  		   				}

					
							
							
							
							
						} else {
							JOptionPane.showMessageDialog(null, "OF doit étre Terminé", "Message",
									JOptionPane.ERROR_MESSAGE);
						}

					} else {
						JOptionPane.showMessageDialog(null, "OF est déjà Annulé", "Message", JOptionPane.ERROR_MESSAGE);
					}
				}

			}
		});
		btnAnnuler.setBounds(326, 600, 143, 23);
		add(btnAnnuler);

		JButton btnFactureService = new JButton("Facture Service ");
		btnFactureService.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FactureProduction factureProduction = factureProductionDAO
						.findFactureProductionByNumOF(production.getNumOF());

				if (factureProduction.getId() > 0) {
					List<DetailFactureProduction> listDetailFactureProduction = new ArrayList<DetailFactureProduction>();
					DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					String date = dateFormat.format(factureProduction.getDateFacture());
					listDetailFactureProduction = factureProduction.getDetailFactureProduction();

					BigDecimal tva20 = (BigDecimal) (factureProduction.getMontantTotal().multiply(new BigDecimal(0.2)));
					BigDecimal totalTTC = tva20.add(factureProduction.getMontantTotal());

					Map parameters = new HashMap();
					parameters.put("nomClientFour", factureProduction.getClientFournisseurMP().getNom());
					parameters.put("adresseClientFour", factureProduction.getClientFournisseurMP().getAdresse());
					parameters.put("telClienFour", factureProduction.getClientFournisseurMP().getNumTel());
					parameters.put("numFacture", factureProduction.getNumFacture());
					parameters.put("dateFacture", date);
					parameters.put("nomClient", factureProduction.getClientMP().getNom());
					parameters.put("adresseClient", factureProduction.getClientMP().getAdresse());
					parameters.put("telClient", factureProduction.getClientMP().getNumTel());
					parameters.put("totalHorsTaxe", String.valueOf(factureProduction.getMontantTotal()));
					parameters.put("tva20", String.valueOf(tva20));
					parameters.put("totalTTC", String.valueOf(totalTTC));

					JasperUtils.imprimerFacutreProduction(listDetailFactureProduction, parameters,
							factureProduction.getNumFacture());

					// JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès",
					// JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Facture n'existe pas !!", "Erreur Impression",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnFactureService.setBounds(902, 600, 120, 23);
		add(btnFactureService);

		JButton btnSaisirDelaiEmploy_1 = new JButton("Saisir Delai Employ\u00E9 Production");
		btnSaisirDelaiEmploy_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (production.getId() > 0) {
					JFrame popupJFrame = new ListeEmploye(production, txtQuantiteRealise.getText(),
							txtPrixServiceProd.getText());
					popupJFrame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Ordre de fabrication introuvable !!", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnSaisirDelaiEmploy_1.setBounds(9, 600, 185, 23);
		add(btnSaisirDelaiEmploy_1);

		JButton btnSaisirDelaiEmploy = new JButton("Saisir Delai Employ\u00E9 Emballage");
		btnSaisirDelaiEmploy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (production.getId() > 0) {
					JFrame popupJFrame = new ListeEmployeEmballage(production, txtQuantiteRealise.getText(),
							txtPrixServiceProd.getText());
					popupJFrame.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Ordre de fabrication introuvable !!", "Erreur",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnSaisirDelaiEmploy.setBounds(1037, 600, 185, 23);
		add(btnSaisirDelaiEmploy);

		comboEquipe.setBounds(1065, 36, 157, 24);
		add(comboEquipe);

		JLabel lblEquipe = new JLabel("Equipe");
		lblEquipe.setBounds(1020, 35, 51, 26);
		add(lblEquipe);

		comboLigneMachine.setBounds(853, 36, 157, 24);
		add(comboLigneMachine);

		JLabel lblGroupe = new JLabel("Ligne Machine");
		lblGroupe.setBounds(764, 35, 77, 24);
		add(lblGroupe);
		lblGroupe.setFont(new Font("Tahoma", Font.PLAIN, 12));

		comboMachine.setBounds(597, 36, 157, 24);
		add(comboMachine);

		JLabel lblMachine = new JLabel("Machine");
		lblMachine.setBounds(538, 35, 58, 24);
		add(lblMachine);
		lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 12));

		categorie.setBounds(319, 35, 194, 26);
		add(categorie);

		categorie.setForeground(Color.BLACK);
		categorie.setBackground(Color.WHITE);

		JLabel label = new JLabel("Article:");
		label.setBounds(262, 34, 102, 26);
		add(label);
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));

		codeArticle.setBounds(83, 35, 153, 26);
		add(codeArticle);

		codeArticle.setDisabledTextColor(Color.BLACK);
		codeArticle.setBackground(Color.WHITE);
		codeArticle.setEnabled(false);
		codeArticle.setColumns(10);

		JLabel lblCodeArticle = new JLabel("Code Article");
		lblCodeArticle.setBounds(9, 35, 82, 26);
		add(lblCodeArticle);
		lblCodeArticle.setFont(new Font("Tahoma", Font.PLAIN, 11));

		JScrollPane scrollPane_3 = new JScrollPane(tableEmployeGen);

		scrollPane_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_3.setBounds(680, 450, 649, 145);
		add(scrollPane_3);

		tableEmployeGen.setHighlighters(HighlighterFactory.createSimpleStriping());
		tableEmployeGen.setShowVerticalLines(true);
		tableEmployeGen.setSelectionBackground(new Color(51, 204, 255));
		tableEmployeGen.setRowHeightEnabled(true);
		tableEmployeGen.setRowHeight(20);
		tableEmployeGen.setGridColor(new Color(0, 0, 255));
		tableEmployeGen.setForeground(Color.BLACK);
		tableEmployeGen.setColumnControlVisible(true);
		tableEmployeGen.setBackground(new Color(255, 255, 255));
		tableEmployeGen.setAutoCreateRowSorter(true);

		JButton supprimerEquipeProduction = new JButton("");
		supprimerEquipeProduction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (listDetailProductionTMP.size() != 0) {
					if (tableEmploye.getSelectedRow() != -1) {
						DetailProduction detailProduction = production.getDetailProductions()
								.get(tableEmploye.getSelectedRow());

						Production productionTmp = productionDAO.findByNumOFStatut(
								detailProduction.getProduction().getNumOF(), Constantes.ETAT_OF_LANCER);
						if (productionTmp != null) {
							int reponse = JOptionPane.showConfirmDialog(null,
									"Vous voulez vraiment Supprimer l'employer ?", "Satisfaction",
									JOptionPane.YES_NO_OPTION);

							if (reponse == JOptionPane.YES_OPTION) {

								detailProductionDAO.delete(detailProduction.getId());

								JOptionPane.showMessageDialog(null, "Employé supprimer avec succée ", "Satisfaction",
										JOptionPane.INFORMATION_MESSAGE);

								listDetailProductionTMP.remove(tableEmploye.getSelectedRow());
								productionTmp.setDetailProductions(listDetailProductionTMP);
								productionDAO.edit(productionTmp);

								afficher_tableEmploye(listDetailProductionTMP);

							}

						} else {
							JOptionPane.showMessageDialog(null,
									"Impossible de supprimer employé dont le OF n'est pas lancé !!!!", "Erreur",
									JOptionPane.ERROR_MESSAGE);
							return;
						}

					}
				} else {
					JOptionPane.showMessageDialog(null, "la liste des employés est vide !!!!", "Erreur",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

			}
		});
		supprimerEquipeProduction.setBounds(612, 315, 58, 23);
		supprimerEquipeProduction.setIcon(imgSupprimer);
		add(supprimerEquipeProduction);

		JButton supprimeremployeempllage = new JButton("");
		supprimeremployeempllage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (listDetailProdGenTmp.size() != 0) {
					if (table_1.getSelectedRow() != -1) {
						DetailProdGen detailProdGen = production.getListDetailProdGen().get(table_1.getSelectedRow());

						Production productionTmp = productionDAO.findByNumOFStatut(
								detailProdGen.getProductionGen().getNumOF(), Constantes.ETAT_OF_LANCER);
						if (productionTmp != null) {
							int reponse = JOptionPane.showConfirmDialog(null,
									"Vous voulez vraiment Supprimer l'employer ?", "Satisfaction",
									JOptionPane.YES_NO_OPTION);

							if (reponse == JOptionPane.YES_OPTION) {

								detailProdGenDAO.delete(detailProdGen.getId());

								JOptionPane.showMessageDialog(null, "Employé supprimer avec succée ", "Satisfaction",
										JOptionPane.INFORMATION_MESSAGE);

								listDetailProdGenTmp.remove(table_1.getSelectedRow());
								productionTmp.setListDetailProdGen(listDetailProdGenTmp);
								productionDAO.edit(productionTmp);

								afficher_tableEmployeEmabalage(listDetailProdGenTmp);

							}

						} else {
							JOptionPane.showMessageDialog(null,
									"Impossible de supprimer employé dont le OF n'est pas lancé !!!!", "Erreur",
									JOptionPane.ERROR_MESSAGE);
							return;
						}

					}
				} else {
					JOptionPane.showMessageDialog(null, "la liste des employés est vide !!!!", "Erreur",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

			}
		});
		supprimeremployeempllage.setBounds(1339, 315, 58, 23);
		supprimeremployeempllage.setIcon(imgSupprimer);
		add(supprimeremployeempllage);

		JButton supprimeremployergen = new JButton("");
		supprimeremployergen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (listDetailResponsableProdTmp.size() != 0) {
					if (tableEmployeGen.getSelectedRow() != -1) {
						DetailResponsableProd detailResponsableProd = production.getListDetailResponsableProd()
								.get(tableEmployeGen.getSelectedRow());

						Production productionTmp = productionDAO.findByNumOFStatut(
								detailResponsableProd.getProduction().getNumOF(), Constantes.ETAT_OF_LANCER);
						if (productionTmp != null) {
							int reponse = JOptionPane.showConfirmDialog(null,
									"Vous voulez vraiment Supprimer l'employer ?", "Satisfaction",
									JOptionPane.YES_NO_OPTION);

							if (reponse == JOptionPane.YES_OPTION) {

								detailResponsableDAO.delete(detailResponsableProd.getId());

								JOptionPane.showMessageDialog(null, "Employé supprimer avec succée ", "Satisfaction",
										JOptionPane.INFORMATION_MESSAGE);

								listDetailResponsableProdTmp.remove(tableEmployeGen.getSelectedRow());
								productionTmp.setListDetailResponsableProd(listDetailResponsableProdTmp);
								productionDAO.edit(productionTmp);

								afficher_tableEmployeGen(listDetailResponsableProdTmp);

							}

						} else {
							JOptionPane.showMessageDialog(null,
									"Impossible de supprimer employé dont le OF est terminé !!!!", "Erreur",
									JOptionPane.ERROR_MESSAGE);
							return;
						}

					}
				} else {
					JOptionPane.showMessageDialog(null, "la liste des employés est vide !!!!", "Erreur",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

			}
		});
		supprimeremployergen.setBounds(1339, 488, 58, 23);
		supprimeremployergen.setIcon(imgSupprimer);
		add(supprimeremployergen);

		chckbxRetourDepot = new JCheckBox("Retour Depot");
		chckbxRetourDepot.setOpaque(false);
		chckbxRetourDepot.setBounds(1020, 8, 143, 23);
		add(chckbxRetourDepot);
		comboMachine.addItem("");
		comboEquipe.addItem("");

		afficher_tableEmploye(production.getDetailProductions());
		afficher_tableMP(production.getListCoutMP());
		afficher_tableEmployeEmabalage(production.getListDetailProdGen());
		afficher_tableEmployeGen(production.getListDetailResponsableProd());

	}

	void AfficherMatierePremiere() {

		List<CoutMP> listCoutMPTmp = productionDAO.listeCoutMP(production.getId());
		afficherDetailPorduction(production.getArticles().getDetailEstimation(), listCoutMPTmp);

		codeArticle.setText(production.getArticles().getCodeArticle());
		categorie.addItem(production.getArticles().getLiblle());
		categorie.setSelectedItem(production.getArticles().getLiblle());

		comboLigneMachine.addItem(production.getLigneMachine().getNom());
		comboLigneMachine.setSelectedItem(production.getLigneMachine().getNom());

		comboMachine.addItem(production.getLigneMachine().getMachine().getNom());
		comboMachine.setSelectedItem(production.getLigneMachine().getMachine().getNom());

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String dateDebutPrev = dateFormat.format(production.getDate_debFabPre());
		String dateFinPrev = dateFormat.format(production.getDateFinFabPre());

		List<DetailProdGen> listDetailProdGen = productionDAO.listeDetailProdGen(production.getId());
		List<DetailProduction> listDetailProduction = productionDAO.listeDetailProduction(production.getId());
		List<DetailResponsableProd> listDetailResponsableProd = productionDAO
				.listeDetailResponsableProd(production.getId());
		afficher_tableEmployeGen(listDetailResponsableProd);
		afficher_tableMP(listCoutMPTmp);
		afficher_tableEmploye(listDetailProduction);
		afficher_tableEmployeEmabalage(listDetailProdGen);

	}

	void intialiserTableMP() {
		modeleMP = new DefaultTableModel(new Object[][] {},
				new String[] { "CODE", "NOM MP", "QTE CALCULE", "QTE EXISTANTE", "QTE CHARGE", "CHARGE SUPP",
						"QTE CONSOMME", "QTE DECHET", "QTE DECHET FOUR", "QTE MANQUANTE", "QTE OFFRE",
						"QTE RESTE USINE", "ECART" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, true, true,
					true, true, true, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		table.setModel(modeleMP);
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(160);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.getColumnModel().getColumn(3).setPreferredWidth(40);
		table.getColumnModel().getColumn(4).setPreferredWidth(40);
		table.getColumnModel().getColumn(5).setPreferredWidth(40);
		table.getColumnModel().getColumn(6).setPreferredWidth(40);
		table.getColumnModel().getColumn(7).setPreferredWidth(60);
		table.getColumnModel().getColumn(8).setPreferredWidth(60);
		table.getColumnModel().getColumn(9).setPreferredWidth(60);
		table.getColumnModel().getColumn(10).setPreferredWidth(60);
		table.getColumnModel().getColumn(11).setPreferredWidth(60);
		table.getColumnModel().getColumn(12).setPreferredWidth(40);
	}

	void afficher_tableMP(List<CoutMP> listCoutMP) {

		intialiserTableMP();

		int i = 0;
		NumberFormat nf = new DecimalFormat("0.###");
		NumberFormat nfimp = new DecimalFormat("0.######");

		while (i < listCoutMP.size()) {
			BigDecimal ecart = BigDecimal.ZERO;
			CoutMP coutMP = listCoutMP.get(i);

			BigDecimal quantiteTotal = coutMP.getQuantite();
			BigDecimal quantiteExistante = coutMP.getQuantExistante();
			BigDecimal quantiteCharge = coutMP.getQuantCharge();
			BigDecimal quantitechargeSupp = coutMP.getQuantChargeSupp();
			BigDecimal quantiteConsomme = coutMP.getQuantConsomme();
			BigDecimal quantiteDechet = coutMP.getQuantDechet();
			BigDecimal quantiteDechetFour = coutMP.getQuantDechetFournisseur();
			BigDecimal quantiteManquante = coutMP.getQuantiteManquante();
			BigDecimal quantiteOffre = coutMP.getQuantiteOffre();
			BigDecimal quantiteReste = coutMP.getQuantReste();

			if ( coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
							.equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX)) {
				ecart = (quantiteCharge.setScale(0, RoundingMode.DOWN)
						.add(quantitechargeSupp.setScale(0, RoundingMode.DOWN))
						.add(quantiteExistante.setScale(0, RoundingMode.DOWN)))
								.subtract(quantiteConsomme.setScale(0, BigDecimal.ROUND_UP)
										.add(quantiteDechet.setScale(0, RoundingMode.DOWN))
										.add(quantiteDechetFour.setScale(0, RoundingMode.DOWN))
										.add(quantiteManquante.setScale(0, RoundingMode.DOWN))
										.add(quantiteOffre.setScale(0, RoundingMode.DOWN))
										.add(quantiteReste.setScale(0, RoundingMode.DOWN)));

				ecart.setScale(2, BigDecimal.ROUND_HALF_DOWN);
			} else if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
					.equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON))
			{
				

				ecart = (quantiteCharge.setScale(0, RoundingMode.DOWN)
						.add(quantitechargeSupp.setScale(0, RoundingMode.DOWN))
						.add(quantiteExistante.setScale(0, RoundingMode.DOWN)))
								.subtract(quantiteConsomme.setScale(0, BigDecimal.ROUND_DOWN)
										.add(quantiteDechet.setScale(0, RoundingMode.DOWN))
										.add(quantiteDechetFour.setScale(0, RoundingMode.DOWN))
										.add(quantiteManquante.setScale(0, RoundingMode.DOWN))
										.add(quantiteOffre.setScale(0, RoundingMode.DOWN))
										.add(quantiteReste.setScale(0, RoundingMode.DOWN)));

				ecart.setScale(2, BigDecimal.ROUND_HALF_DOWN);
			
			}
			
			
			
			else {
				ecart = (quantiteCharge.add(quantitechargeSupp).add(quantiteExistante))
						.subtract(quantiteConsomme.add(quantiteDechet).add(quantiteDechetFour).add(quantiteManquante)
								.add(quantiteOffre).add(quantiteReste));

				ecart.setScale(6, BigDecimal.ROUND_HALF_DOWN);
			}

			// ecart=NumberUtils.roundHalfDown(ecart,2 );

			String strEcart = "";

			if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
					.equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CENTURE)) {
				strEcart = nfimp.format(ecart);
			} else {
				strEcart = nf.format(ecart);
			}

			if ( coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
							.equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX)) {
				Object[] ligne = { coutMP.getMatierePremier().getCode(), coutMP.getMatierePremier().getNom(),
						NumberUtils.GroupingFormatBigDecimal(quantiteTotal) + " "
								+ coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),
						NumberUtils.GroupingFormatBigDecimal(quantiteExistante.setScale(0, RoundingMode.DOWN)),
						NumberUtils.GroupingFormatBigDecimal(quantiteCharge.setScale(0, RoundingMode.DOWN)),
						NumberUtils.GroupingFormatBigDecimal(quantitechargeSupp.setScale(0, RoundingMode.DOWN)),
						NumberUtils.GroupingFormatBigDecimal(quantiteConsomme.setScale(0, BigDecimal.ROUND_UP)),
						NumberUtils.GroupingFormatBigDecimal(quantiteDechet.setScale(0, RoundingMode.DOWN)),
						NumberUtils.GroupingFormatBigDecimal(quantiteDechetFour.setScale(0, RoundingMode.DOWN)),
						NumberUtils.GroupingFormatBigDecimal(quantiteManquante.setScale(0, RoundingMode.DOWN)),
						NumberUtils.GroupingFormatBigDecimal(quantiteOffre.setScale(0, RoundingMode.DOWN)),
						NumberUtils.GroupingFormatBigDecimal(quantiteReste.setScale(0, RoundingMode.DOWN)), strEcart };

				modeleMP.addRow(ligne);

			} else if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
					.equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON))
			{
				

				Object[] ligne = { coutMP.getMatierePremier().getCode(), coutMP.getMatierePremier().getNom(),
						NumberUtils.GroupingFormatBigDecimal(quantiteTotal) + " "
								+ coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),
						NumberUtils.GroupingFormatBigDecimal(quantiteExistante.setScale(0, RoundingMode.DOWN)),
						NumberUtils.GroupingFormatBigDecimal(quantiteCharge.setScale(0, RoundingMode.DOWN)),
						NumberUtils.GroupingFormatBigDecimal(quantitechargeSupp.setScale(0, RoundingMode.DOWN)),
						NumberUtils.GroupingFormatBigDecimal(quantiteConsomme.setScale(0, BigDecimal.ROUND_DOWN)),
						NumberUtils.GroupingFormatBigDecimal(quantiteDechet.setScale(0, RoundingMode.DOWN)),
						NumberUtils.GroupingFormatBigDecimal(quantiteDechetFour.setScale(0, RoundingMode.DOWN)),
						NumberUtils.GroupingFormatBigDecimal(quantiteManquante.setScale(0, RoundingMode.DOWN)),
						NumberUtils.GroupingFormatBigDecimal(quantiteOffre.setScale(0, RoundingMode.DOWN)),
						NumberUtils.GroupingFormatBigDecimal(quantiteReste.setScale(0, RoundingMode.DOWN)), strEcart };

				modeleMP.addRow(ligne);

			
				
			}else {

				Object[] ligne = { coutMP.getMatierePremier().getCode(), coutMP.getMatierePremier().getNom(),
						NumberUtils.GroupingFormatBigDecimal(quantiteTotal.setScale(6, RoundingMode.HALF_UP)) + " "
								+ coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),
						NumberUtils.GroupingFormatBigDecimal(quantiteExistante.setScale(6, RoundingMode.HALF_UP)),
						NumberUtils.GroupingFormatBigDecimal(quantiteCharge.setScale(6, RoundingMode.HALF_UP)),
						NumberUtils.GroupingFormatBigDecimal(quantitechargeSupp.setScale(6, RoundingMode.HALF_UP)),
						NumberUtils.GroupingFormatBigDecimal(quantiteConsomme.setScale(6, RoundingMode.HALF_UP)),
						quantiteDechet.setScale(6, RoundingMode.HALF_UP),
						quantiteDechetFour.setScale(6, RoundingMode.HALF_UP),
						quantiteManquante.setScale(6, RoundingMode.HALF_UP),
						quantiteOffre.setScale(6, RoundingMode.HALF_UP),
						quantiteReste.setScale(6, RoundingMode.HALF_UP),
						strEcart };

				modeleMP.addRow(ligne);

			}

			i++;
		}
		table.setModel(modeleMP);
	}

	void afficher_tableEmploye(List<DetailProduction> listDetailProduction) {
		initialiserTableauEmploye();
		BigDecimal delai;
		BigDecimal heureSupp25;
		BigDecimal heureSupp50;
		boolean absent = false;
		int i = 0;
		while (i < listDetailProduction.size()) {
			DetailProduction detailProduction = listDetailProduction.get(i);
			delai = detailProduction.getDelaiEmploye();
			heureSupp25 = detailProduction.getHeureSupp25();
			heureSupp50 = detailProduction.getHeureSupp50();
			absent = detailProduction.isAbsent();
			Object[] ligne = { detailProduction.getEmploye().getId(), detailProduction.getEmploye().getNumDossier(),
					detailProduction.getEmploye().getNom(), delai, heureSupp25, heureSupp50, absent };

			modeleEmploye.addRow(ligne);
			i++;
		}
		tableEmploye.setModel(modeleEmploye);
	}

	void afficher_tableEmployeEmabalage(List<DetailProdGen> listDetailProdGen) {
		initialiserTableauEquipeEmbalage();
		BigDecimal delai;
		BigDecimal heureSupp25;
		BigDecimal heureSupp50;
		boolean absent = false;
		int i = 0;
		while (i < listDetailProdGen.size()) {
			DetailProdGen detailProdGen = listDetailProdGen.get(i);

			delai = detailProdGen.getDelaiEmploye();
			heureSupp25 = detailProdGen.getHeureSupp25();
			heureSupp50 = detailProdGen.getHeureSupp50();
			absent = detailProdGen.isAbsent();
			Object[] ligne = { detailProdGen.getEmploye().getId(), detailProdGen.getEmploye().getNumDossier(),
					detailProdGen.getEmploye().getNom(), delai, heureSupp25, heureSupp50, absent };

			modeleEquipeEm.addRow(ligne);
			i++;
		}
		table_1.setModel(modeleEquipeEm);
	}

	void afficher_tableEmployeGen(List<DetailResponsableProd> listDetailResponsableProd) {
		initialiserTableauEmployeGen();
		BigDecimal delai;
		BigDecimal heureSupp25;
		BigDecimal heureSupp50;
		boolean absent = false;
		int i = 0;
		while (i < listDetailResponsableProd.size()) {
			DetailResponsableProd detailResponsableProd = listDetailResponsableProd.get(i);
			delai = detailResponsableProd.getDelaiEmploye();
			heureSupp25 = detailResponsableProd.getHeureSupp25();
			heureSupp50 = detailResponsableProd.getHeureSupp50();
			absent = detailResponsableProd.isAbsent();
			Object[] ligne = { detailResponsableProd.getEmploye().getId(),
					detailResponsableProd.getEmploye().getNumDossier(), detailResponsableProd.getEmploye().getNom(),
					delai, heureSupp25, heureSupp50, absent };

			modeleEquipeGen.addRow(ligne);
			i++;
		}
		tableEmployeGen.setModel(modeleEquipeGen);
	}

	List<DetailProduction> calculeCoutEmploye(List<DetailProduction> listDetailProduction,
			Map<Integer, String> mapDelaiEmploye) {
		BigDecimal delai = BigDecimal.ZERO;

		BigDecimal remise = BigDecimal.ZERO;
		BigDecimal coutHoraire = BigDecimal.ZERO;
		BigDecimal heureSupp25;
		BigDecimal heureSupp50;

		BigDecimal coutSupp25 = BigDecimal.ZERO;
		BigDecimal coutSupp50 = BigDecimal.ZERO;

		List<DetailProduction> listDetailProductionTmp = new ArrayList<DetailProduction>();
		for (int i = 0; i < listDetailProduction.size(); i++) {

			remise = BigDecimal.ZERO;
			delai = BigDecimal.ZERO;
			coutHoraire = BigDecimal.ZERO;
			heureSupp25 = BigDecimal.ZERO;
			heureSupp50 = BigDecimal.ZERO;
			coutSupp25 = BigDecimal.ZERO;
			coutSupp50 = BigDecimal.ZERO;
			DetailProduction detailProduction = listDetailProduction.get(i);

			if (!detailProduction.getEmploye().isSalarie()) {

				delai = new BigDecimal(mapDelaiEmploye.get(detailProduction.getEmploye().getId()));
				heureSupp25 = new BigDecimal(mapHeureSupp25EmployeProd.get(detailProduction.getEmploye().getId()));
				heureSupp50 = new BigDecimal(mapHeureSupp50EmployeProd.get(detailProduction.getEmploye().getId()));

				if (detailProduction.isAbsent() == true) {

					String code = Utils.genereCodeDateMoisAnnee(production.getDate());

					Utils.compterAbsenceEmploye(code, detailProduction.getEmploye(), production.getDate());

					delai = BigDecimal.ZERO;
					heureSupp25 = BigDecimal.ZERO;
					heureSupp50 = BigDecimal.ZERO;

				}

				coutHoraire = detailProduction.getEmploye().getCoutHoraire().multiply(delai);
				coutSupp25 = heureSupp25.multiply(COUT_HEURE_SUPPLEMENTAIRE_25);
				coutSupp50 = heureSupp50.multiply(COUT_HEURE_SUPPLEMENTAIRE_50);

				coutTotalEmploye = coutTotalEmploye.add(coutHoraire).add(coutSupp25).add(coutSupp50);
				detailProduction.setCoutTotal(coutHoraire);
				detailProduction.setDelaiEmploye(delai);
				detailProduction.setHeureSupp25(heureSupp25);
				detailProduction.setHeureSupp50(heureSupp50);
				detailProduction.setCoutSupp25(coutSupp25);
				detailProduction.setCoutSupp50(coutSupp50);
				detailProduction.setRemise(remise);

				if (!detailProduction.getEmploye().isSalarie()) {
					FicheEmploye ficheEmploye = ficheEmployeDAO.findByPeriodeDateSitutation(production.getDate(),
							detailProduction.getEmploye().getId());
					if (ficheEmploye != null) {
						/* Remplir fiche programme */
						coutHoraire = coutHoraire.add(ficheEmploye.getCoutTotal());
						delai = delai.add(ficheEmploye.getDelaiEmploye());
						String numOF = ficheEmploye.getNumOF() + "-" + production.getNumOF();
						BigDecimal delaiProd = ficheEmploye.getDelaiProd().add(production.getNbreHeure());

						/*
						 * ficheEmploye.setDateSituation(production.getDate());
						 * 
						 * ficheEmploye.setEmploye(detailProdGen.getEmploye());;
						 * 
						 * ficheEmploye.setHeureSupp25(heureSupp25);
						 * ficheEmploye.setHeureSupp50(heureSupp50);
						 * ficheEmploye.setCoutSupp25(coutSupp25);
						 * ficheEmploye.setCoutSupp50(coutSupp50);
						 */

						ficheEmploye.setNumOF(numOF);
						ficheEmploye.setCoutTotal(coutHoraire);
						ficheEmploye.setDelaiProd(delaiProd);

						ficheEmploye.setDelaiEmploye(delai);

						if (detailProduction.isAbsent() == false
								&& ficheEmploye.getDelaiEmploye().compareTo(ficheEmploye.getDelaiProd()) >= 0) {

							Parametre parametre_remise_ouvrier = parametreDAO
									.findByCode(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
							Parametre parametre_remise_ouvrier_vrac = parametreDAO
									.findByCode(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);

							if (detailProduction.getEmploye().getTypeResEmploye().getCode()
									.equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
								remise = parametre_remise_ouvrier.getValeur();
							if (detailProduction.getEmploye().getTypeResEmploye().getCode()
									.equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
								remise = parametre_remise_ouvrier_vrac.getValeur();

						} else {
							remise = BigDecimal.ZERO;
						}
						ficheEmploye.setRemise(remise);
						ficheEmployeDAO.edit(ficheEmploye);
					} else {
						ficheEmploye = new FicheEmploye();
						ficheEmploye.setCoutTotal(coutHoraire);
						ficheEmploye.setNumOF(production.getNumOF());
						ficheEmploye.setDateSituation(production.getDate());
						ficheEmploye.setDelaiEmploye(delai);
						ficheEmploye.setEmploye(detailProduction.getEmploye());
						;

						ficheEmploye.setHeureSupp25(heureSupp25);
						ficheEmploye.setHeureSupp50(heureSupp50);
						ficheEmploye.setCoutSupp25(coutSupp25);
						ficheEmploye.setCoutSupp50(coutSupp50);

						if (detailProduction.isAbsent() == false && delai.compareTo(production.getNbreHeure()) >= 0) {

							Parametre parametre_remise_ouvrier = parametreDAO
									.findByCode(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
							Parametre parametre_remise_ouvrier_vrac = parametreDAO
									.findByCode(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);

							if (detailProduction.getEmploye().getTypeResEmploye().getCode()
									.equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
								remise = parametre_remise_ouvrier.getValeur();
							if (detailProduction.getEmploye().getTypeResEmploye().getCode()
									.equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
								remise = parametre_remise_ouvrier_vrac.getValeur();

						} else {
							remise = BigDecimal.ZERO;
						}

						ficheEmploye.setRemise(remise);
						ficheEmploye.setDelaiProd(production.getNbreHeure());
						ficheEmployeDAO.add(ficheEmploye);

					}
				}

				listDetailProductionTmp.add(detailProduction);
			}
		}
		return listDetailProductionTmp;

	}

	List<DetailProdGen> calculeCoutEmployeEmbalage(List<DetailProdGen> listDetailProdGen,
			Map<Integer, String> mapDelaiEmployeEmabalage) {
		BigDecimal delai = BigDecimal.ZERO;
		BigDecimal remise = BigDecimal.ZERO;
		BigDecimal coutHoraire = BigDecimal.ZERO;
		BigDecimal heureSupp25;
		BigDecimal heureSupp50;

		BigDecimal coutSupp25 = BigDecimal.ZERO;
		BigDecimal coutSupp50 = BigDecimal.ZERO;
		List<DetailProdGen> listDetailDetailProdGenTmp = new ArrayList<DetailProdGen>();
		for (int i = 0; i < listDetailProdGen.size(); i++) {

			DetailProdGen detailProdGen = listDetailProdGen.get(i);

			remise = BigDecimal.ZERO;
			delai = BigDecimal.ZERO;
			coutHoraire = BigDecimal.ZERO;
			heureSupp25 = BigDecimal.ZERO;
			heureSupp50 = BigDecimal.ZERO;
			coutSupp25 = BigDecimal.ZERO;
			coutSupp50 = BigDecimal.ZERO;

			if (!detailProdGen.getEmploye().isSalarie()) {

				delai = new BigDecimal(mapDelaiEmployeEmabalage.get(detailProdGen.getEmploye().getId()));
				heureSupp25 = new BigDecimal(mapHeureSupp25EmployeEmbalage.get(detailProdGen.getEmploye().getId()));
				heureSupp50 = new BigDecimal(mapHeureSupp50EmployeEmbalage.get(detailProdGen.getEmploye().getId()));

				if (detailProdGen.isAbsent() == true) {

					String code = Utils.genereCodeDateMoisAnnee(production.getDate());
					Utils.compterAbsenceEmploye(code, detailProdGen.getEmploye(), production.getDate());
					delai = BigDecimal.ZERO;
					heureSupp25 = BigDecimal.ZERO;
					heureSupp50 = BigDecimal.ZERO;
				}

				coutHoraire = detailProdGen.getEmploye().getCoutHoraire().multiply(delai);
				coutSupp25 = heureSupp25.multiply(COUT_HEURE_SUPPLEMENTAIRE_25);
				coutSupp50 = heureSupp50.multiply(COUT_HEURE_SUPPLEMENTAIRE_50);

				coutTotalEmployeEmbalage = coutTotalEmployeEmbalage.add(coutHoraire).add(coutSupp25).add(coutSupp50);
				detailProdGen.setCoutTotal(coutHoraire);
				detailProdGen.setDelaiEmploye(delai);
				detailProdGen.setRemise(remise);
				detailProdGen.setHeureSupp25(heureSupp25);
				detailProdGen.setHeureSupp50(heureSupp50);
				detailProdGen.setCoutSupp25(coutSupp25);
				detailProdGen.setCoutSupp50(coutSupp50);

				if (!detailProdGen.getEmploye().isSalarie()) {
					FicheEmploye ficheEmploye = ficheEmployeDAO.findByPeriodeDateSitutation(production.getDate(),
							detailProdGen.getEmploye().getId());
					if (ficheEmploye != null) {
						/* Remplir fiche programme */
						coutHoraire = coutHoraire.add(ficheEmploye.getCoutTotal());
						delai = delai.add(ficheEmploye.getDelaiEmploye());
						String numOF = ficheEmploye.getNumOF() + "-" + production.getNumOF();
						BigDecimal delaiProd = ficheEmploye.getDelaiProd().add(production.getNbreHeure());
						/*
						 * ficheEmploye.setDateSituation(production.getDate());
						 * 
						 * ficheEmploye.setEmploye(detailProdGen.getEmploye());;
						 * 
						 * ficheEmploye.setHeureSupp25(heureSupp25);
						 * ficheEmploye.setHeureSupp50(heureSupp50);
						 * ficheEmploye.setCoutSupp25(coutSupp25);
						 * ficheEmploye.setCoutSupp50(coutSupp50);
						 */

						ficheEmploye.setNumOF(numOF);
						ficheEmploye.setCoutTotal(coutHoraire);
						ficheEmploye.setDelaiProd(delaiProd);

						ficheEmploye.setDelaiEmploye(delai);

						if (detailProdGen.isAbsent() == false
								&& ficheEmploye.getDelaiEmploye().compareTo(ficheEmploye.getDelaiProd()) >= 0) {

							Parametre parametre_remise_ouvrier = parametreDAO
									.findByCode(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
							Parametre parametre_remise_ouvrier_vrac = parametreDAO
									.findByCode(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);

							if (detailProdGen.getEmploye().getTypeResEmploye().getCode()
									.equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
								remise = parametre_remise_ouvrier.getValeur();
							if (detailProdGen.getEmploye().getTypeResEmploye().getCode()
									.equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
								remise = parametre_remise_ouvrier_vrac.getValeur();

						} else {
							remise = BigDecimal.ZERO;
						}
						ficheEmploye.setRemise(remise);
						ficheEmployeDAO.edit(ficheEmploye);
					} else {
						ficheEmploye = new FicheEmploye();
						ficheEmploye.setCoutTotal(coutHoraire);
						ficheEmploye.setNumOF(production.getNumOF());
						ficheEmploye.setDateSituation(production.getDate());
						ficheEmploye.setDelaiEmploye(delai);
						ficheEmploye.setEmploye(detailProdGen.getEmploye());
						;

						ficheEmploye.setHeureSupp25(heureSupp25);
						ficheEmploye.setHeureSupp50(heureSupp50);
						ficheEmploye.setCoutSupp25(coutSupp25);
						ficheEmploye.setCoutSupp50(coutSupp50);

						if (detailProdGen.isAbsent() == false && delai.compareTo(production.getNbreHeure()) >= 0) {

							Parametre parametre_remise_ouvrier = parametreDAO
									.findByCode(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
							Parametre parametre_remise_ouvrier_vrac = parametreDAO
									.findByCode(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);

							if (detailProdGen.getEmploye().getTypeResEmploye().getCode()
									.equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
								remise = parametre_remise_ouvrier.getValeur();
							if (detailProdGen.getEmploye().getTypeResEmploye().getCode()
									.equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
								remise = parametre_remise_ouvrier_vrac.getValeur();

						} else {
							remise = BigDecimal.ZERO;
						}

						ficheEmploye.setRemise(remise);
						ficheEmploye.setDelaiProd(production.getNbreHeure());
						ficheEmployeDAO.add(ficheEmploye);

					}

				}
				listDetailDetailProdGenTmp.add(detailProdGen);
			}
		}
		return listDetailDetailProdGenTmp;

	}

	boolean remplirQuantite() {
		boolean trouve = false;

		for (int j = 0; j < table.getRowCount(); j++) {

			if (!table.getValueAt(j, 7).toString().equals("")) {
				mapQuantiteDechet.put(table.getValueAt(j, 0).toString(),
						table.getValueAt(j, 7).toString().replace(",", "."));
				trouve = true;
			} else {
				mapQuantiteDechet.put(table.getValueAt(j, 0).toString(), String.valueOf(0).replace(",", "."));
			}
			if (!table.getValueAt(j, 8).toString().equals("")) {

				mapQuantiteDechetFour.put(table.getValueAt(j, 0).toString(),
						table.getValueAt(j, 8).toString().replace(",", "."));

				trouve = true;
			} else {
				mapQuantiteDechetFour.put(table.getValueAt(j, 0).toString(), String.valueOf(0).replace(",", "."));
			}
			if (!table.getValueAt(j, 9).toString().equals("")) {

				mapQuantiteManquante.put(table.getValueAt(j, 0).toString(),
						table.getValueAt(j, 9).toString().replace(",", "."));
				trouve = true;
			} else {
				mapQuantiteManquante.put(table.getValueAt(j, 0).toString(), String.valueOf(0).replace(",", "."));
			}

			if (!table.getValueAt(j, 10).toString().equals("")) {

				mapQuantiteOffre.put(table.getValueAt(j, 0).toString(),
						table.getValueAt(j, 10).toString().replace(",", "."));
				trouve = true;
			} else {
				mapQuantiteOffre.put(table.getValueAt(j, 0).toString(), String.valueOf(0).replace(",", "."));
			}

			if (!table.getValueAt(j, 11).toString().equals("")) {

				mapQuantiteReste.put(table.getValueAt(j, 0).toString(),
						table.getValueAt(j, 11).toString().replace(",", "."));
				trouve = true;
			} else {
				mapQuantiteReste.put(table.getValueAt(j, 0).toString(), String.valueOf(0).replace(",", "."));
			}

		}
		return trouve;
	}

	List<CoutMP> validerSaisiQuantiteDechetReste(List<CoutMP> listCoutMP) {

		BigDecimal quantiteDechet = BigDecimal.ZERO;
		BigDecimal quantiteReste = BigDecimal.ZERO;
		BigDecimal quantiteDechetFour = BigDecimal.ZERO;
		BigDecimal quantiteManquante = BigDecimal.ZERO;
		BigDecimal quantiteOffre = BigDecimal.ZERO;

		List<CoutMP> listCoutMPTmp = new ArrayList<CoutMP>();

		for (int i = 0; i < listCoutMP.size(); i++) {

			CoutMP coutMP = listCoutMP.get(i);

			quantiteDechet = new BigDecimal(mapQuantiteDechet.get(coutMP.getMatierePremier().getCode()));
			quantiteReste = new BigDecimal(mapQuantiteReste.get(coutMP.getMatierePremier().getCode()));
			quantiteDechetFour = new BigDecimal(mapQuantiteDechetFour.get(coutMP.getMatierePremier().getCode()));
			quantiteManquante = new BigDecimal(mapQuantiteManquante.get(coutMP.getMatierePremier().getCode()));
			quantiteOffre = new BigDecimal(mapQuantiteOffre.get(coutMP.getMatierePremier().getCode()));

			coutMP.setQuantDechet(quantiteDechet);
			coutMP.setQuantReste(quantiteReste);
			coutMP.setQuantDechetFournisseur(quantiteDechetFour);
			coutMP.setQuantiteManquante(quantiteManquante);
			coutMP.setQuantiteOffre(quantiteOffre);

			// listCoutMP.set(i,coutMP);
			listCoutMPTmp.add(coutMP);
		}
		afficher_tableMP(listCoutMPTmp);

		validerSaisie = true;
		return listCoutMPTmp;

	}

	List<CoutMP> calculeCoutMatierePremiere(List<CoutMP> listCoutMP) {

		detailtransferMPDAO.ViderSession();
		BigDecimal quantiteDechet = BigDecimal.ZERO;
		BigDecimal quantiteConsomme = BigDecimal.ZERO;
		BigDecimal quantiteReste = BigDecimal.ZERO;
		BigDecimal quantiteMP = BigDecimal.ZERO;

		BigDecimal quantiteDechetFour = BigDecimal.ZERO;
		BigDecimal quantiteManquante = BigDecimal.ZERO;
		BigDecimal quantiteOffre = BigDecimal.ZERO;
		Magasin magasinElnasTeaPacking = null;
		BigDecimal prixUnitaire = BigDecimal.ZERO;
		BigDecimal prixMP = BigDecimal.ZERO;
		BigDecimal coutDechet = BigDecimal.ZERO;
		BigDecimal coutDechetFour = BigDecimal.ZERO;
		BigDecimal coutManquante = BigDecimal.ZERO;
		BigDecimal coutQuantiteOffre = BigDecimal.ZERO;

		BigDecimal quantiteStock = BigDecimal.ZERO;

		coutTotalMP = BigDecimal.ZERO;

		List<CoutMP> listCoutMPTmp = new ArrayList<CoutMP>();
		for (int i = 0; i < listCoutMP.size(); i++) {

			CoutMP coutMP = listCoutMP.get(i);
			prixUnitaire = coutMP.getPrixUnitaire();
			StockMP stockmp = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
					production.getMagasinProd().getId());

			StockMP stockmpStockage = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
					production.getMagasinStockage().getId());
			StockMP stockmpTmp = null;
			// quantiteConsomme=Integer.parseInt(mapQuantiteConsomme.get(coutMP.getMatierePremier().getCode()));

			if ( coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
							.equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX)) {
				quantiteConsomme = coutMP.getQuantConsomme().setScale(0, BigDecimal.ROUND_UP);
			} else if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
					.equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON)
					) {
				
				quantiteConsomme = coutMP.getQuantConsomme().setScale(0, BigDecimal.ROUND_DOWN);
				
			}
			
			else {
				quantiteConsomme = coutMP.getQuantConsomme();
			}

			quantiteDechet = new BigDecimal(mapQuantiteDechet.get(coutMP.getMatierePremier().getCode()));

			quantiteReste = new BigDecimal(mapQuantiteReste.get(coutMP.getMatierePremier().getCode()));

			quantiteDechetFour = new BigDecimal(mapQuantiteDechetFour.get(coutMP.getMatierePremier().getCode()));

			quantiteManquante = new BigDecimal(mapQuantiteManquante.get(coutMP.getMatierePremier().getCode()));

			quantiteOffre = new BigDecimal(mapQuantiteOffre.get(coutMP.getMatierePremier().getCode()));

			// Modifier quantite dechet de transfer stock MP
			// Modifier quantite dechet de transfer stock MP
			TransferStockMP transferstockmp = null;
			if (coutMP.getProdcutionCM().getService().equals(Constantes.PRODUCTION_SERVICE_NON)) {
				transferstockmp = transferstockmpDAO.findTransferByCodeStatut(production.getNumOF(),
						Constantes.ETAT_TRANSFER_STOCK_CHARGE);
				stockmpTmp = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
						production.getMagasinStockage().getId());

			} else if ((coutMP.getProdcutionCM().getService().equals(Constantes.PRODUCTION_SERVICE_OUI))) {
				transferstockmp = transferstockmpDAO.findTransferByCodeStatut(production.getNumOF(),
						Constantes.ETAT_TRANSFER_STOCK_SERVICE);
				if (!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
						.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
					if (!coutMP.getProdcutionCM().getMagasinStockage().getLibelle()
							.equals(depotDAO.magasinByCode(CODE_MAGASIN_ELNASS_TEA_PACKING).getLibelle())) {
						magasinElnasTeaPacking = depotDAO.magasinByCode(CODE_MAGASIN_ELNASS_TEA_PACKING);
						stockmpTmp = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
								magasinElnasTeaPacking.getId());

					}
				} else {
					stockmpTmp = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
							production.getMagasinStockage().getId());

				}

			}

			DetailTransferStockMP detailtransferstockmp = detailtransferMPDAO
					.findDetailTransferStockMPByMPByTransferMP(coutMP.getMatierePremier(), transferstockmp);

			if (detailtransferstockmp != null) {
				if (detailtransferstockmp.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
						.equals(Constantes.CODE_BOX)
						
						|| detailtransferstockmp.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
								.equals(Constantes.CODE_TAMPON)
						|| detailtransferstockmp.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
								.equals(Constantes.CODE_STICKERS)
						|| detailtransferstockmp.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
								.equals(Constantes.CODE_THERRES_VERRES)) {
					
					detailtransferstockmp.setQuantiteDechet(quantiteDechet.setScale(0, RoundingMode.CEILING));
					detailtransferstockmp.setQuantite(quantiteConsomme);
					if (stockmpTmp.getStockDechet() != null) {
						stockmpTmp.setStockDechet(stockmpTmp.getStockDechet().setScale(0, RoundingMode.CEILING)
								.add(quantiteDechet.setScale(0, RoundingMode.CEILING)));
					} else {
						stockmpTmp.setStockDechet(quantiteDechet.setScale(0, RoundingMode.CEILING));
					}

				}
				
				
				
				else {
					detailtransferstockmp.setQuantiteDechet(quantiteDechet);
					detailtransferstockmp.setQuantite(quantiteConsomme);
					if (stockmpTmp.getStockDechet() != null) {
						stockmpTmp.setStockDechet(stockmpTmp.getStockDechet().add(quantiteDechet));
					} else {
						stockmpTmp.setStockDechet(quantiteDechet);
					}
				}

				detailtransferstockmp.setQuantiteOffre(quantiteOffre);
				if (stockmpTmp.getStockOffre() != null) {
					stockmpTmp.setStockOffre(stockmpTmp.getStockOffre().add(quantiteOffre));
				} else {
					stockmpTmp.setStockOffre(quantiteOffre);
				}

				// stockmpTmp.setStock(stockmpTmp.getStock().subtract(quantiteOffre));
				stockMPDAO.edit(stockmpTmp);

				detailtransferMPDAO.edit(detailtransferstockmp);

			}

			// quantiteReste=stockmp.getStock()-(quantiteConsomme+quantiteDechet);
			coutMP.setQuantConsomme(quantiteConsomme);
			coutMP.setQuantDechet(quantiteDechet);
			coutMP.setQuantiteOffre(quantiteOffre);
			// quantiteMP=quantiteConsomme+coutMP.getQuantChargeSupp();
			if (coutMP.getProdcutionCM().getService().equals(Constantes.PRODUCTION_SERVICE_OUI)
					&& coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
							.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
				prixMP = (coutMP.getQuantConsomme()).multiply(coutMP.getPrixUnitaire());
			} else {
				prixMP = quantiteConsomme.multiply(prixUnitaire);
			}

			coutDechet = quantiteDechet.multiply(prixUnitaire);
			coutDechetFour = quantiteDechetFour.multiply(prixUnitaire);
			coutManquante = quantiteManquante.multiply(prixUnitaire);
			coutQuantiteOffre = quantiteOffre.multiply(prixUnitaire);
			coutMP.setPrixTotal(prixMP);

			coutMP.setPrixTotal(prixMP);
			coutMP.setCoutDechet(coutDechet);
			coutMP.setCoutDechetFournisseur(coutDechetFour);
			coutMP.setCoutManquante(coutManquante);
			coutMP.setCoutOffre(coutQuantiteOffre);

			coutTotalMP = coutTotalMP.add(prixMP);
			coutTotalDechet = coutTotalDechet.add(coutDechet).add(coutDechetFour).add(coutManquante);
			// quantiteReste=stockmp.getStock()-quantiteConsomme;

			quantiteMP = stockmp.getStock().subtract(quantiteConsomme.add(quantiteDechet).add(quantiteReste)
					.add(quantiteDechetFour).add(quantiteManquante).add(quantiteOffre));

			if (quantiteMP.compareTo(BigDecimal.ZERO) != 0) {

				Calendar cal = Calendar.getInstance();
				cal.setTime(production.getDate());
				int annee = cal.get(Calendar.YEAR);
				int mois = cal.get(Calendar.MONTH) + 1;

				CompteStockMP compteStockMP = compteStockMPDAO
						.findByCodeMPAnneeMois(coutMP.getMatierePremier().getCode(), mois, annee);
				stockmp.setStock(BigDecimal.ZERO);
				if (compteStockMP == null) {
					compteStockMP = new CompteStockMP();
					compteStockMP.setMatierePremier(coutMP.getMatierePremier());
					compteStockMP.setPrixUnitaire(coutMP.getPrixUnitaire());
					compteStockMP.setQuantite(quantiteMP);
					compteStockMP.setMois(mois);
					compteStockMP.setAnnee(annee);
					compteStockMPDAO.add(compteStockMP);

				} else {

					BigDecimal quantite = compteStockMP.getQuantite().add(quantiteMP);
					// BigDecimal coutTotal
					// =(compteStockMP.getQuantite()*compteStockMP.getPrixUnitaire())+(quantiteMP*coutMP.getPrixUnitaire());

					// BigDecimal prixUnitaire=coutTotal/Math.abs(quantite);

					compteStockMP.setQuantite(quantite);
					// compteStockMP.setPrixUnitaire(prixUnitaire);

					compteStockMPDAO.edit(compteStockMP);

				}
			}

			if (chckbxRetourDepot.isSelected() == true) {

				quantiteStock = stockmpStockage.getStock().add(quantiteReste);
				stockmpStockage.setStock(quantiteStock);
				stockMPDAO.edit(stockmpStockage);
				stockmp.setStock(BigDecimal.ZERO);
				stockMPDAO.edit(stockmp);
				mapQuantiteDetailMouvement.put(coutMP.getMatierePremier().getId(), quantiteReste);
				mapMatierePremiere.put(coutMP.getMatierePremier().getId(), coutMP.getMatierePremier());

			} else {
				stockmp.setStock(quantiteReste);
				stockMPDAO.edit(stockmp);

			}

			listCoutMPTmp.add(coutMP);

		}
		return listCoutMPTmp;
	}

	void afficherDetailPorduction(List<DetailEstimation> lisDetailEstimation, List<CoutMP> listCoutMP) {
		DetailEstimation detailEstimation = new DetailEstimation();
		CoutMP coutMP = new CoutMP();
		CoutMP coutMPTmp = new CoutMP();
		int position = -1;
		boolean trouve = false;
		BigDecimal quantiteConsommme = BigDecimal.ZERO;
		BigDecimal quantiteRealise = new BigDecimal(txtQuantiteRealise.getText());
		boolean articleMixte = production.isArticleMixte();
		int priorite = 0;
		BigDecimal quantiteTotalCharge = BigDecimal.ZERO;
		int j;
		for (j = 0; j < listCoutMP.size(); j++) {
			coutMP = listCoutMP.get(j);
			trouve = false;
			for (int i = 0; i < lisDetailEstimation.size(); i++) {

				detailEstimation = lisDetailEstimation.get(i);

				if (detailEstimation.getMatierePremier().getId() == coutMP.getMatierePremier().getId()) {
					// if(detailEstimation.getPriorite()==0){

					trouve = true;
					if (detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
							.equals(SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {

						if (articleMixte == true) {
							if (detailEstimation.getPriorite() == PRIORITE_ESTIMATION_2) {
								quantiteConsommme = detailEstimation.getQuantite()
										.multiply(new BigDecimal(txtQuantiteRealise.getText()));
								coutMP.setQuantConsomme(quantiteConsommme);
								listCoutMP.set(j, coutMP);
							}
						} else {

							if (detailEstimation.getPriorite() == PRIORITE_ESTIMATION_1) {
								quantiteConsommme = detailEstimation.getQuantite()
										.multiply(new BigDecimal(txtQuantiteRealise.getText()));
								coutMP.setQuantConsomme(quantiteConsommme);
								listCoutMP.set(j, coutMP);
							}
						}

					} else {
						quantiteConsommme = detailEstimation.getQuantite()
								.multiply(new BigDecimal(txtQuantiteRealise.getText()));

						if (detailEstimation.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
								.equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON)) {

							quantiteConsommme = (BigDecimal) (quantiteConsommme);

						}
						coutMP.setQuantConsomme(quantiteConsommme);
						listCoutMP.set(j, coutMP);
					}

				} /*
					 * else if(detailEstimation.getPriorite()>0){
					 * 
					 * for(int k=0;k<listCoutMP.size();k++){ if(detailEstimation.getPriorite()==k){
					 * quantiteTotalCharge=coutMP.getQuantCharge()+coutMP.getQuantExistante();
					 * if(quantiteRealise<=quantiteTotalCharge){
					 * coutMP.setQuantConsomme(quantiteRealise); listCoutMP.set(j,coutMP);
					 * quantiteRealise=0;
					 * 
					 * }else {
					 * 
					 * if(priorite<detailEstimation.getPriorite()){
					 * priorite=detailEstimation.getPriorite(); position=j; }
					 * quantiteTotalCharge=coutMP.getQuantCharge()+coutMP.getQuantExistante();
					 * coutMP.setQuantConsomme(quantiteTotalCharge); listCoutMP.set(j,coutMP);
					 * quantiteRealise=quantiteRealise-quantiteTotalCharge;
					 * 
					 * } } } }
					 */

			}
			if (trouve == false) {

				coutMP.setQuantConsomme(BigDecimal.ZERO);
				listCoutMP.set(j, coutMP);

			}

		}

		/*
		 * if(quantiteRealise>0){
		 * 
		 * coutMP=listCoutMP.get(position); BigDecimal
		 * quantite=coutMP.getQuantConsomme()+quantiteRealise;
		 * coutMP.setQuantConsomme(quantite); listCoutMP.set(position, coutMP);
		 * 
		 * }
		 */

	}

	void calculerStockCoutProduitFini(BigDecimal coutTotal) {
		Parametre PercentagePrixCadeau = parametreDAO.findByCode(Constantes.PERCENTAGE_PRIX_OFFRE);

		coutPF = BigDecimal.ZERO;
		BigDecimal nouveauCout = BigDecimal.ZERO;
		BigDecimal quantiteTotal = BigDecimal.ZERO;
		BigDecimal coutStock = BigDecimal.ZERO;
		BigDecimal NombreCarton = BigDecimal.ZERO;
		BigDecimal QuantiteEnVrac = BigDecimal.ZERO;
		BigDecimal PrixOffre = BigDecimal.ZERO;

		// coutTotal=production.getCoutTotalEmploye()+production.getCoutTotalEmployeGen()+production.getCoutTotalMP()+production.getCoutTotalEmployeEmbalage();
		Promotion promotion = null;
		Parametre parametre = parametreDAO.findByCode(COUT_SERVICE);
		coutPF = coutTotal.divide(production.getQuantiteReel(), 6, BigDecimal.ROUND_HALF_UP);
		sousfamilleTmp = null;
		// production=productionDAO.findByNumOF(txtNumOF.getText(),codeDepot);
		listCoutMPTmp = productionDAO.listeCoutMP(production.getId());
		List<CoutMP> listCoutMP = listCoutMPTmp;

		for (int i = 0; i < listCoutMP.size(); i++) {

			if (listCoutMP.get(i).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
					.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
				
				for(int j=0;j<listSousFamille.size();j++)
				{
					
					SousFamilleEnVrac sousFamilleArticlePF =listSousFamille.get(j);
					
					if(sousFamilleArticlePF.getMatierePremier() !=null)
					{
						
						if(sousFamilleArticlePF.getMatierePremier().getId()==listCoutMP.get(i).getMatierePremier().getId())
						{
							
							sousfamilleTmp=sousFamilleArticlePF.getSousfamile();
							
						}
						
						
						
					}
					
					
					
					
					
				}
				
				
				
				/*

				if (listCoutMP.get(i).getMatierePremier().getCode().equals(Constantes.CODE_SOUS_FAMILLE_3505)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505);

				} else if (listCoutMP.get(i).getMatierePremier().getCode()
						.equals(Constantes.CODE_SOUS_FAMILLE_3505_A)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_A);

				}else if (listCoutMP.get(i).getMatierePremier().getCode()
						.equals(Constantes.CODE_SOUS_FAMILLE_3505_A_5KG)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_A);

				}
				else if (listCoutMP.get(i).getMatierePremier().getCode()
						.equals(Constantes.CODE_SOUS_FAMILLE_3505_AA)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_AA);

				}
				else if (listCoutMP.get(i).getMatierePremier().getCode()
						.equals(Constantes.CODE_SOUS_FAMILLE_3505_AA_5KG)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_AA);

				} 
				else if (listCoutMP.get(i).getMatierePremier().getCode()
						.equals(Constantes.CODE_SOUS_FAMILLE_3505_AAA_5KG)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_AAA);

				} 
				
				
				else if (listCoutMP.get(i).getMatierePremier().getCode()
						.equals(Constantes.CODE_SOUS_FAMILLE_3505_B)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_B);

				} else if (listCoutMP.get(i).getMatierePremier().getCode()
						.equals(Constantes.CODE_SOUS_FAMILLE_3505_C)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_C);

				} else if (listCoutMP.get(i).getMatierePremier().getCode()
						.equals(Constantes.CODE_SOUS_FAMILLE_3505_D)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_D);

				} else if (listCoutMP.get(i).getMatierePremier().getCode()
						.equals(Constantes.CODE_SOUS_FAMILLE_401022)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_401022);

				} else if (listCoutMP.get(i).getMatierePremier().getCode().equals(Constantes.CODE_SOUS_FAMILLE_4011)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_4011);

				} else if (listCoutMP.get(i).getMatierePremier().getCode().equals(Constantes.CODE_SOUS_FAMILLE_9366)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9366);

				} else if (listCoutMP.get(i).getMatierePremier().getCode().equals(Constantes.CODE_SOUS_FAMILLE_9367)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9367);

				} else if (listCoutMP.get(i).getMatierePremier().getCode().equals(Constantes.CODE_SOUS_FAMILLE_9368)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9368);

				} else if (listCoutMP.get(i).getMatierePremier().getCode().equals(Constantes.CODE_SOUS_FAMILLE_9369)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9369);

				} else if (listCoutMP.get(i).getMatierePremier().getCode().equals(Constantes.CODE_SOUS_FAMILLE_9371)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9371);

				} else if (listCoutMP.get(i).getMatierePremier().getCode().equals(Constantes.CODE_SOUS_FAMILLE_9475)) {

					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9475);

				} else if (listCoutMP.get(i).getMatierePremier().getCode().equals(Constantes.CODE_SOUS_FAMILLE_9575)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9575);

				} else if (listCoutMP.get(i).getMatierePremier().getCode()
						.equals(Constantes.CODE_SOUS_FAMILLE_4011_NAZHA)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_4011);

				} else if (listCoutMP.get(i).getMatierePremier().getCode()
						.equals(Constantes.CODE_SOUS_FAMILLE_4011_MAHA)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_4011);

				}
				*/

			}

			if (listCoutMP.get(i).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
					.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON)) {
				NombreCarton = NombreCarton.add(listCoutMP.get(i).getQuantConsomme());
			}

		}

		if (production.getOffre() != null) {

			promotion = promotiondao.findByCode(production.getOffre());
			Magasin magasinElnasTeaPacking = null;
			magasinElnasTeaPacking = depotDAO.magasinByCode(CODE_MAGASIN_ELNASS_TEA_PACKING);
			if (promotion != null) {
				for (int i = 0; i < promotion.getDetailEstimationPromo().size(); i++) {
					if (promotion.getDetailEstimationPromo().get(i).getMatierePremiere().getCategorieMp()
							.getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
						QuantiteEnVrac = QuantiteEnVrac.add(promotion.getDetailEstimationPromo().get(i).getQuantite());
					}

					if (production.getService().equals(Constantes.PRODUCTION_SERVICE_OUI)) {
						if (promotion.getDetailEstimationPromo().get(i).getMatierePremiere().getCategorieMp()
								.getSubCategorieMp().getCode()
								.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU)) {

							for (int k = 0; k < production.getListCoutMP().size(); k++) {

								if (production.getListCoutMP().get(k).getMatierePremier().getCategorieMp()
										.getSubCategorieMp().getCode()
										.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU)
										&& production.getListCoutMP().get(k).getMatierePremier().getId() == promotion
												.getDetailEstimationPromo().get(i).getMatierePremiere().getId()) {
									if (production.getListCoutMP().get(k).getMagasin() != null) {
										if (production.getListCoutMP().get(k).getMagasin()
												.equals(magasinElnasTeaPacking)) {

											StockMP stockMP = stockMPDAO
													.findStockByMagasinMP(
															promotion.getDetailEstimationPromo().get(i)
																	.getMatierePremiere().getId(),
															magasinElnasTeaPacking.getId());

											PrixOffre = PrixOffre.add(stockMP.getPrixUnitaire()
													.multiply(promotion.getDetailEstimationPromo().get(i).getQuantite())
													.multiply((PercentagePrixCadeau.getValeur()
															.divide(new BigDecimal(100), 6, RoundingMode.UP).add(BigDecimal.ONE))));

										}

									}

								}

							}

						} else {
							/*
							 * StockMP
							 * stockMP=stockMPDAO.findStockByMagasinMP(promotion.getDetailEstimationPromo().
							 * get(i).getMatierePremiere().getId(), magasinElnasTeaPacking.getId());
							 * PrixOffre=PrixOffre.add(stockMP.getPrixUnitaire().multiply(promotion.
							 * getDetailEstimationPromo().get(i).getQuantite()).multiply(new
							 * BigDecimal(1.2)));
							 */
						}

					} else {
						StockMP stockMP = stockMPDAO.findStockByMagasinMP(
								promotion.getDetailEstimationPromo().get(i).getMatierePremiere().getId(),
								production.getMagasinStockage().getId());
						PrixOffre = PrixOffre.add(stockMP.getPrixUnitaire()
								.multiply(promotion.getDetailEstimationPromo().get(i).getQuantite())
								.multiply(new BigDecimal(1.2)));
					}

				}

				PrixOffre = PrixOffre.divide(production.getArticles().getCentreCout3(), RoundingMode.HALF_UP); // centercout3
																												// est
																												// le
																												// poids
																												// de
																												// carton
			}

		}

		if (production.getService().equals(Constantes.PRODUCTION_SERVICE_OUI)) {
			/*
			 * List <CoutMP> listeCoutMPTmp=production.getListCoutMP();
			 * 
			 * for(CoutMP coutMP : listeCoutMPTmp){
			 * 
			 * if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().
			 * equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)){
			 * 
			 * coutPF= coutMP.getPrixUnitaire(); break; } }
			 */

			coutPF = coutPF.add(PrixOffre).add(parametre.getValeur());
		} else {
			coutPF = coutPF;
		}

		StockPF stockPF = stockPFDAO.findStockByMagasinPFBySousFamille(production.getArticles().getId(),
				production.getMagasinPF().getId(), sousfamilleTmp.getId());

		if (stockPF != null) {
			quantiteTotal = stockPF.getStock().add(production.getQuantiteReel());
			// +cout service prod +
			coutStock = stockPF.getStock().multiply(stockPF.getPrixUnitaire());

			if (coutStock.compareTo(BigDecimal.ZERO) > 0)
				nouveauCout = ((coutPF.multiply(production.getQuantiteReel())).add(coutStock)).divide(quantiteTotal, 6,
						BigDecimal.ROUND_HALF_UP);
			else {
				nouveauCout = coutPF;
			}

			stockPF.setArticles(production.getArticles());
			stockPF.setPrixUnitaire(nouveauCout);
			if (promotion != null) {
				if (stockPF.getStockOffre() != null) {
					stockPF.setStockOffre(stockPF.getStockOffre().add(QuantiteEnVrac.multiply(NombreCarton)));
				} else {
					stockPF.setStockOffre(QuantiteEnVrac.multiply(NombreCarton));
				}

			} else {

				stockPF.setStockOffre(BigDecimal.ZERO);
			}

			stockPF.setStock(quantiteTotal.subtract(stockPF.getStockOffre()));

			production = productionDAO.findByNumOF(txtNumOF.getText(), codeDepot);
			List<CoutMP> listCoutMPTmp = productionDAO.listeCoutMP(production.getId());
			SousFamilleArticlePF sousfamille = null;

			for (int i = 0; i < listCoutMPTmp.size(); i++) {

				if (listCoutMPTmp.get(i).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
						.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
					
					for(int j=0;j<listSousFamille.size();j++)
					{
						
						SousFamilleEnVrac sousFamilleArticlePF =listSousFamille.get(j);
						
						if(sousFamilleArticlePF.getMatierePremier() !=null)
						{
							
							if(sousFamilleArticlePF.getMatierePremier().getId()==listCoutMPTmp.get(i).getMatierePremier().getId())
							{
								
								sousfamille=sousFamilleArticlePF.getSousfamile();
								stockPF.setSousFamille(sousfamille);
								
							}
							
							
							
						}
						
						
						
						
						
					}
					
					
					
					
					/*

					if (listCoutMPTmp.get(i).getMatierePremier().getCode().equals(Constantes.CODE_SOUS_FAMILLE_3505)) {
						sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505);

						stockPF.setSousFamille(sousfamille);

					} else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_3505_A)) {
						sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_A);

						stockPF.setSousFamille(sousfamille);

					} else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_3505_A_5KG)) {
						sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_A);

						stockPF.setSousFamille(sousfamille);

					} 
					else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_3505_AA)) {
						sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_AA);

						stockPF.setSousFamille(sousfamille);

					}
					else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_3505_AA_5KG)) {
						sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_AA);

						stockPF.setSousFamille(sousfamille);

					}
					else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_3505_AAA_5KG)) {
						sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_AAA);

						stockPF.setSousFamille(sousfamille);

					}
					
					
					else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_3505_B)) {
						sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_B);

						stockPF.setSousFamille(sousfamille);

					} else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_3505_C)) {
						sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_C);

						stockPF.setSousFamille(sousfamille);

					} else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_3505_D)) {
						sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_D);

						stockPF.setSousFamille(sousfamille);

					} else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_401022)) {
						sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_401022);

						stockPF.setSousFamille(sousfamille);

					} else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_9366)) {
						sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9366);

						stockPF.setSousFamille(sousfamille);

					} else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_9367)) {
						sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9367);

						stockPF.setSousFamille(sousfamille);

					} else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_9368)) {
						sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9368);

						stockPF.setSousFamille(sousfamille);

					} else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_9369)) {
						sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9369);

						stockPF.setSousFamille(sousfamille);

					} else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_9371)) {
						sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9371);

						stockPF.setSousFamille(sousfamille);

					} else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_9475)) {

						sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9475);

						stockPF.setSousFamille(sousfamille);

					} else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_9575)) {
						sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9575);

						stockPF.setSousFamille(sousfamille);

					} else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_4011_NAZHA)) {
						sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_4011);

						stockPF.setSousFamille(sousfamille);

					} else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_4011)) {
						sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_4011);

						stockPF.setSousFamille(sousfamille);

					} else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_4011_MAHA)) {
						sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_4011);

						stockPF.setSousFamille(sousfamille);

					}
					
					
					*/

				}
			}

			DetailTransferProduitFini detailTransferProduitFini = new DetailTransferProduitFini();
			TransferStockPF transferStockPF = new TransferStockPF();
			List<DetailTransferProduitFini> listeDetailTransferProduitFini = new ArrayList<DetailTransferProduitFini>();

			detailTransferProduitFini.setArticle(production.getArticles());
			detailTransferProduitFini.setSousFamille(sousfamille);
			detailTransferProduitFini.setDateTransfer(production.getDate_debFabPre());
			detailTransferProduitFini.setMagasinDestination(production.getMagasinPF());
			detailTransferProduitFini.setMagasinSouce(production.getMagasinProd());
			detailTransferProduitFini.setQuantite(new BigDecimal(txtQuantiteRealise.getText()));
			detailTransferProduitFini.setPrixUnitaire(coutPF);
			detailTransferProduitFini.setTypeTransfer(Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE);
			detailTransferProduitFini.setTransferStockPF(transferStockPF);

			listeDetailTransferProduitFini.add(detailTransferProduitFini);

			transferStockPF.setCodeTransfer(production.getNumOF());
			transferStockPF.setCreerPar(AuthentificationView.utilisateur);
			transferStockPF.setDate(new Date());
			transferStockPF.setDateTransfer(production.getDate_debFabPre());
			// transferStockPF.setListDetailTransferProduitFini(listeDetailTransferProduitFini);
			transferStockPF.setStatut(TYPE_TRANSFER_PRODUIT_FINI_ENTRE);

			transferStockPFDAO.add(transferStockPF);
			detailTransferProduitFiniDAO.add(detailTransferProduitFini);

			stockPFDAO.edit(stockPF);
			sousfamilleEnvrac = sousfamille;

		} else if (stockPF == null) {
			StockPF stockPFmp = new StockPF();

			quantiteTotal = production.getQuantiteReel();

			if (coutStock.compareTo(BigDecimal.ZERO) > 0)
				nouveauCout = (coutPF.multiply(production.getQuantiteReel())).divide(quantiteTotal, 6,
						BigDecimal.ROUND_HALF_UP);
			else {
				nouveauCout = coutPF;
			}

			stockPFmp.setArticles(production.getArticles());
			stockPFmp.setPrixUnitaire(nouveauCout);
			if (promotion != null) {

				stockPFmp.setStockOffre(QuantiteEnVrac.multiply(NombreCarton));

			} else {
				stockPFmp.setStockOffre(BigDecimal.ZERO);
			}

			stockPFmp.setStock(quantiteTotal.subtract(stockPFmp.getStockOffre()));

			stockPFmp.setMagasin(production.getMagasinPF());

			production = productionDAO.findByNumOF(txtNumOF.getText(), codeDepot);
			List<CoutMP> listCoutMPTmp = productionDAO.listeCoutMP(production.getId());

			for (int i = 0; i < listCoutMPTmp.size(); i++) {

				if (listCoutMPTmp.get(i).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
						.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
					
					for(int j=0;j<listSousFamille.size();j++)
					{
						
						SousFamilleEnVrac sousFamilleArticlePF =listSousFamille.get(j);
						
						if(sousFamilleArticlePF.getMatierePremier() !=null)
						{
							
							if(sousFamilleArticlePF.getMatierePremier().getId()==listCoutMPTmp.get(i).getMatierePremier().getId())
							{
								
								SousFamilleArticlePF sousfamille=sousFamilleArticlePF.getSousfamile();
								stockPFmp.setSousFamille(sousfamille);
								
							}
							
							
							
						}
						
						
					}
					
					
					
					
					
					/*

					if (listCoutMPTmp.get(i).getMatierePremier().getCode().equals(Constantes.CODE_SOUS_FAMILLE_3505)) {
						SousFamilleArticlePF sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505);

						stockPFmp.setSousFamille(sousfamille);

					} else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_3505_A)) {
						SousFamilleArticlePF sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_A);

						stockPFmp.setSousFamille(sousfamille);

					} else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_3505_A_5KG)) {
						SousFamilleArticlePF sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_A);

						stockPFmp.setSousFamille(sousfamille);

					}
					
					
					else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_3505_AA)) {
						SousFamilleArticlePF sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_AA);

						stockPFmp.setSousFamille(sousfamille);

					}
					else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_3505_AA_5KG)) {
						SousFamilleArticlePF sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_AA);

						stockPFmp.setSousFamille(sousfamille);

					}
					else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_3505_AAA_5KG)) {
						SousFamilleArticlePF sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_AAA);

						stockPFmp.setSousFamille(sousfamille);

					}
					
					
					
					else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_3505_B)) {
						SousFamilleArticlePF sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_B);

						stockPFmp.setSousFamille(sousfamille);

					} else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_3505_C)) {
						SousFamilleArticlePF sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_C);

						stockPFmp.setSousFamille(sousfamille);

					} else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_3505_D)) {
						SousFamilleArticlePF sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_D);

						stockPFmp.setSousFamille(sousfamille);

					} else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_401022)) {
						SousFamilleArticlePF sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_401022);

						stockPFmp.setSousFamille(sousfamille);

					} else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_4011)) {
						SousFamilleArticlePF sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_4011);

						stockPFmp.setSousFamille(sousfamille);

					} else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_9366)) {
						SousFamilleArticlePF sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9366);

						stockPFmp.setSousFamille(sousfamille);

					} else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_9367)) {
						SousFamilleArticlePF sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9367);

						stockPFmp.setSousFamille(sousfamille);

					} else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_9368)) {
						SousFamilleArticlePF sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9368);

						stockPFmp.setSousFamille(sousfamille);

					} else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_9369)) {
						SousFamilleArticlePF sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9369);

						stockPFmp.setSousFamille(sousfamille);

					} else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_9371)) {
						SousFamilleArticlePF sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9371);

						stockPFmp.setSousFamille(sousfamille);

					} else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_9475)) {

						SousFamilleArticlePF sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9475);

						stockPFmp.setSousFamille(sousfamille);

					} else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_9575)) {
						SousFamilleArticlePF sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9575);

						stockPFmp.setSousFamille(sousfamille);

					} else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_4011_NAZHA)) {
						SousFamilleArticlePF sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_4011);

						stockPFmp.setSousFamille(sousfamille);

					} else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_4011)) {
						SousFamilleArticlePF sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_4011);

						stockPFmp.setSousFamille(sousfamille);

					} else if (listCoutMPTmp.get(i).getMatierePremier().getCode()
							.equals(Constantes.CODE_SOUS_FAMILLE_4011_MAHA)) {
						SousFamilleArticlePF sousfamille = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_4011);

						stockPFmp.setSousFamille(sousfamille);

					}
					
					
					
					*/

				}
			}

			DetailTransferProduitFini detailTransferProduitFini = new DetailTransferProduitFini();
			TransferStockPF transferStockPF = new TransferStockPF();
			List<DetailTransferProduitFini> listeDetailTransferProduitFini = new ArrayList<DetailTransferProduitFini>();

			detailTransferProduitFini.setArticle(production.getArticles());
			detailTransferProduitFini.setDateTransfer(production.getDate_debFabPre());
			detailTransferProduitFini.setMagasinDestination(production.getMagasinPF());
			detailTransferProduitFini.setMagasinSouce(production.getMagasinProd());
			detailTransferProduitFini.setQuantite(production.getQuantiteReel());
			detailTransferProduitFini.setPrixUnitaire(coutPF);
			detailTransferProduitFini.setTypeTransfer(Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE);
			detailTransferProduitFini.setTransferStockPF(transferStockPF);
			detailTransferProduitFini.setSousFamille(stockPFmp.getSousFamille());
			listeDetailTransferProduitFini.add(detailTransferProduitFini);

			transferStockPF.setCodeTransfer(production.getNumOF());
			transferStockPF.setCreerPar(AuthentificationView.utilisateur);
			transferStockPF.setDate(new Date());
			transferStockPF.setDateTransfer(production.getDate_debFabPre());
			// transferStockPF.setListDetailTransferProduitFini(listeDetailTransferProduitFini);
			transferStockPF.setStatut(TYPE_TRANSFER_PRODUIT_FINI_ENTRE);
			transferStockPFDAO.add(transferStockPF);
			detailTransferProduitFiniDAO.add(detailTransferProduitFini);
			stockPFDAO.add(stockPFmp);
			sousfamilleEnvrac = stockPFmp.getSousFamille();
		}

	}

	void initialiserTableauEmploye() {
		modeleEmploye = new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Matricule", "Nom", "Délai Travaillé", "H Supp 25%", "H Supp 50%", "Absent" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, true, true, true, true };

			Class[] columnTypes = new Class[] { String.class, String.class, String.class, BigDecimal.class,
					BigDecimal.class, BigDecimal.class, Boolean.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tableEmploye.setModel(modeleEmploye);
		tableEmploye.getColumnModel().getColumn(0).setPreferredWidth(1);
		tableEmploye.getColumnModel().getColumn(1).setPreferredWidth(60);
		tableEmploye.getColumnModel().getColumn(2).setPreferredWidth(160);
		tableEmploye.getColumnModel().getColumn(3).setPreferredWidth(60);
		tableEmploye.getColumnModel().getColumn(4).setPreferredWidth(60);
		tableEmploye.getColumnModel().getColumn(5).setPreferredWidth(60);
		tableEmploye.getColumnModel().getColumn(5).setPreferredWidth(60);
	}

	void initialiserTableauEmployeGen() {
		modeleEquipeGen = new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Matricule", "Nom", "Délai Travaillé", "H Supp 25%", "H Supp 50%", "Absent" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, true, true, true, true };

			Class[] columnTypes = new Class[] { String.class, String.class, String.class, BigDecimal.class,
					BigDecimal.class, BigDecimal.class, Boolean.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tableEmployeGen.setModel(modeleEquipeGen);
		tableEmployeGen.getColumnModel().getColumn(0).setPreferredWidth(1);
		tableEmployeGen.getColumnModel().getColumn(1).setPreferredWidth(60);
		tableEmployeGen.getColumnModel().getColumn(2).setPreferredWidth(160);
		tableEmployeGen.getColumnModel().getColumn(3).setPreferredWidth(60);
		tableEmployeGen.getColumnModel().getColumn(4).setPreferredWidth(60);
		tableEmployeGen.getColumnModel().getColumn(5).setPreferredWidth(60);
		tableEmployeGen.getColumnModel().getColumn(5).setPreferredWidth(60);
	}

	void initialiserTableauEquipeEmbalage() {

		modeleEquipeEm = new DefaultTableModel(new Object[][] {},
				new String[] { "ID", "Matricule", "Nom", "Délai Travaillé", "H Supp 25%", "H Supp 50%", "Absent" }) {
			boolean[] columnEditables = new boolean[] { false, false, false, true, true, true, true };

			Class[] columnTypes = new Class[] { String.class, String.class, String.class, BigDecimal.class,
					BigDecimal.class, BigDecimal.class, Boolean.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};

		table_1.setModel(modeleEquipeEm);
		table_1.getColumnModel().getColumn(0).setPreferredWidth(1);
		table_1.getColumnModel().getColumn(1).setPreferredWidth(60);
		table_1.getColumnModel().getColumn(2).setPreferredWidth(160);
		table_1.getColumnModel().getColumn(3).setPreferredWidth(60);
		table_1.getColumnModel().getColumn(4).setPreferredWidth(60);
		table_1.getColumnModel().getColumn(5).setPreferredWidth(60);
		table_1.getColumnModel().getColumn(5).setPreferredWidth(60);

	}

	List<DetailProdGen> remplieDetailProdGen(List<Employe> listEmploye) {
		List<DetailProdGen> listDetailProdGen = new ArrayList<DetailProdGen>();
		for (int i = 0; i < listEmploye.size(); i++) {
			DetailProdGen detailProdGen = new DetailProdGen();
			Employe employe = listEmploye.get(i);
			detailProdGen.setCoutTotal(BigDecimal.ZERO);
			detailProdGen.setRemise(employe.getRemise());
			detailProdGen.setEmploye(employe);
			detailProdGen.setProductionGen(production);

			listDetailProdGen.add(detailProdGen);
		}

		production.setListDetailProdGen(listDetailProdGen);
		productionDAO.edit(production);
		return listDetailProdGen;
	}

	List<DetailProduction> remplieDetailProdcution(List<Employe> listEmploye) {
		List<DetailProduction> listDetailProdcution = new ArrayList<DetailProduction>();

		for (int i = 0; i < listEmploye.size(); i++) {
			DetailProduction detailProd = new DetailProduction();
			Employe employe = listEmploye.get(i);
			detailProd.setCoutTotal(BigDecimal.ZERO);
			detailProd.setRemise(employe.getRemise());
			detailProd.setEmploye(employe);
			detailProd.setProduction(production);

			// listDetailProdcution.add(detailProd);
			production.getDetailProductions().add(detailProd);
		}
//	production.setDetailProductions(listDetailProdcution);

		/*
		 * List<DetailProduction>
		 * listDetailProdcutionTmp=production.getDetailProductions(); DetailProduction
		 * detailProdDeleted=new DetailProduction(); if(listDetailProdcutionTmp!=null &&
		 * listDetailProdcutionTmp.size()>0){ for(int
		 * j=0;j<listDetailProdcutionTmp.size();j++){ DetailProduction detailProd=
		 * listDetailProdcutionTmp.get(j);
		 * if(!listEmploye.contains(detailProd.getEmploye())){
		 * detailProdDeleted=production.removeDetailProduction(detailProd);
		 * System.out.println("######"+detailProdDeleted.getId()); } }
		 * 
		 * }
		 */
		productionDAO.edit(production);

		return production.getDetailProductions();
	}

	void annulerStockMatierePremiere(List<CoutMP> listCoutMP, int idMagasinProd, int idMagasinStockage) {
		BigDecimal quantiteStockage = BigDecimal.ZERO;
		BigDecimal quantiteCharge = BigDecimal.ZERO;
		BigDecimal quantiteStockmp = BigDecimal.ZERO;
		BigDecimal quantiteARetournerCompteMP = BigDecimal.ZERO;
		boolean annuler = false;
		for (int i = 0; i < listCoutMP.size(); i++) {
			quantiteStockage = BigDecimal.ZERO;
			CoutMP coutMP = listCoutMP.get(i);

			quantiteCharge = coutMP.getQuantCharge();
			BigDecimal quantiteConsomme = coutMP.getQuantConsomme();
			BigDecimal quantitechargeSupp = coutMP.getQuantChargeSupp();
			BigDecimal quantiteExistante = coutMP.getQuantExistante();
			BigDecimal quantiteDechet = coutMP.getQuantDechet();
			BigDecimal quantiteDechetFour = coutMP.getQuantDechetFournisseur();
			BigDecimal quantiteManquante = coutMP.getQuantiteManquante();
			BigDecimal quantiteOffre = coutMP.getQuantiteOffre();
			BigDecimal quantiteReste = coutMP.getQuantReste();

			StockMP stockMPProd = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(), idMagasinProd);
			StockMP stockMPStockage = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
					idMagasinStockage);
			quantiteStockmp = quantiteExistante.add(stockMPProd.getStock());

			quantiteStockage = stockMPStockage.getStock().add(quantiteCharge);

			BigDecimal ecart = (quantiteCharge.add(quantitechargeSupp).add(quantiteExistante))
					.subtract(quantiteConsomme.add(quantiteDechet).add(quantiteDechetFour).add(quantiteManquante)
							.add(quantiteOffre).add(quantiteReste));

			Calendar cal = Calendar.getInstance();
			cal.setTime(production.getDate());
			int annee = cal.get(Calendar.YEAR);
			int mois = cal.get(Calendar.MONTH) + 1;

			CompteStockMP compteStockMP = compteStockMPDAO.findByCodeMPAnneeMois(coutMP.getMatierePremier().getCode(),
					mois, annee);

			if (compteStockMP != null) {
				quantiteARetournerCompteMP = compteStockMP.getQuantite().subtract(ecart);
				compteStockMP.setQuantite(quantiteARetournerCompteMP);
				compteStockMPDAO.edit(compteStockMP);
			}

			stockMPProd.setStock(quantiteStockmp);
			stockMPStockage.setStock(quantiteStockage);

			/*
			 * coutMP.setCoutDechet(0); coutMP.setQuantCharge(0);
			 * coutMP.setQuantChargeSupp(0); coutMP.setQuantConsomme(0);
			 * coutMP.setQuantDechet(0); coutMP.setQuantExistante(0); coutMP.setQuantite(0);
			 * coutMP.setQuantReste(0); coutMP.setQuantDechetFournisseur(0);
			 * coutMP.setQuantiteManquante(0); cou
			 */
			listCoutMP.set(i, coutMP);
			// listCoutMP.remove(i);

			stockMPDAO.edit(stockMPStockage);
			stockMPDAO.edit(stockMPProd);
			annuler = true;
		}
		if (annuler == true) {
			// Supprimer le transfer stock MP
			TransferStockMP transferStockMP = transferStockMPDAO.findTransferByCode(production.getNumOF());
			
		int i=	transferStockMP.getId();
			i=i+1;
			boolean existe=true;
			
			while(existe==true)
			{
				
				TransferStockMP transferStockMPTmp = transferStockMPDAO.findById(i);
				
				if(transferStockMPTmp!=null)
				{
					if(transferStockMPTmp.getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF_SERVICE) || transferStockMPTmp.getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF))
					{
						transferStockMPDAO.delete(transferStockMPTmp.getId());
						
						existe=true;
						
					}else
					{
						existe=false;
					}
					
				}else
				{
					existe=false;
				}
				
				i=i+1;
				
			}
			
			
			
			
			
			
			if (transferStockMP != null) {
				transferStockMPDAO.delete(transferStockMP.getId());
			}

		}

		// production.setListCoutMP(listCoutMP);
	}

	void annulerStockProduitFini() {
		BigDecimal quantiteAannuler = BigDecimal.ZERO;
		BigDecimal quantite = BigDecimal.ZERO;

		SousFamilleArticlePF sousfamilleTmp = null;
		production = productionDAO.findByNumOF(txtNumOF.getText(), codeDepot);
		List<CoutMP> listCoutMP = productionDAO.listeCoutMP(production.getId());
		for (int i = 0; i < listCoutMP.size(); i++) {

			if (listCoutMP.get(i).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
					.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
				
				for(int j=0;j<listSousFamille.size();j++)
				{
					
					SousFamilleEnVrac sousFamilleArticlePF =listSousFamille.get(j);
					
					if(sousFamilleArticlePF.getMatierePremier() !=null)
					{
						
						if(sousFamilleArticlePF.getMatierePremier().getId()==listCoutMP.get(i).getMatierePremier().getId())
						{
							
							sousfamilleTmp=sousFamilleArticlePF.getSousfamile();
							
							
						}
						
						
						
					}
					
					
				}
				
				
				
				
				/*

				if (listCoutMP.get(i).getMatierePremier().getCode().equals(Constantes.CODE_SOUS_FAMILLE_3505)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505);

				} else if (listCoutMP.get(i).getMatierePremier().getCode()
						.equals(Constantes.CODE_SOUS_FAMILLE_3505_A)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_A);

				}
				else if (listCoutMP.get(i).getMatierePremier().getCode()
						.equals(Constantes.CODE_SOUS_FAMILLE_3505_A_5KG)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_A);

				} 
				
				else if (listCoutMP.get(i).getMatierePremier().getCode()
						.equals(Constantes.CODE_SOUS_FAMILLE_3505_AA)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_AA);

				} 
				else if (listCoutMP.get(i).getMatierePremier().getCode()
						.equals(Constantes.CODE_SOUS_FAMILLE_3505_AA_5KG)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_AA);

				}
				else if (listCoutMP.get(i).getMatierePremier().getCode()
						.equals(Constantes.CODE_SOUS_FAMILLE_3505_AAA_5KG)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_AAA);

				}
				
				else if (listCoutMP.get(i).getMatierePremier().getCode()
						.equals(Constantes.CODE_SOUS_FAMILLE_3505_B)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_B);

				} else if (listCoutMP.get(i).getMatierePremier().getCode()
						.equals(Constantes.CODE_SOUS_FAMILLE_3505_C)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_C);

				} else if (listCoutMP.get(i).getMatierePremier().getCode()
						.equals(Constantes.CODE_SOUS_FAMILLE_3505_D)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_3505_D);

				} else if (listCoutMP.get(i).getMatierePremier().getCode()
						.equals(Constantes.CODE_SOUS_FAMILLE_401022)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_401022);

				} else if (listCoutMP.get(i).getMatierePremier().getCode().equals(Constantes.CODE_SOUS_FAMILLE_4011)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_4011);

				} else if (listCoutMP.get(i).getMatierePremier().getCode().equals(Constantes.CODE_SOUS_FAMILLE_9366)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9366);

				} else if (listCoutMP.get(i).getMatierePremier().getCode().equals(Constantes.CODE_SOUS_FAMILLE_9367)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9367);

				} else if (listCoutMP.get(i).getMatierePremier().getCode().equals(Constantes.CODE_SOUS_FAMILLE_9368)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9368);

				} else if (listCoutMP.get(i).getMatierePremier().getCode().equals(Constantes.CODE_SOUS_FAMILLE_9369)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9369);

				} else if (listCoutMP.get(i).getMatierePremier().getCode().equals(Constantes.CODE_SOUS_FAMILLE_9371)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9371);

				} else if (listCoutMP.get(i).getMatierePremier().getCode().equals(Constantes.CODE_SOUS_FAMILLE_9475)) {

					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9475);

				} else if (listCoutMP.get(i).getMatierePremier().getCode().equals(Constantes.CODE_SOUS_FAMILLE_9575)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_9575);

				} else if (listCoutMP.get(i).getMatierePremier().getCode()
						.equals(Constantes.CODE_SOUS_FAMILLE_4011_NAZHA)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_4011);

				} else if (listCoutMP.get(i).getMatierePremier().getCode().equals(Constantes.CODE_SOUS_FAMILLE_4011)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_4011);

				} else if (listCoutMP.get(i).getMatierePremier().getCode()
						.equals(Constantes.CODE_SOUS_FAMILLE_4011_MAHA)) {
					sousfamilleTmp = sousFamilleArticleDAo.findByCode(Constantes.CODE_MP_4011);

				}
				
				
				*/

			}
		}
		StockPF stockPF = stockPFDAO.findStockByMagasinPFBySousFamille(production.getArticles().getId(),
				production.getMagasinPF().getId(), sousfamilleTmp.getId());
		TransferStockPF transferStockPF = transferStockPFDAO.findByCodeTransfert(production.getNumOF());
		quantiteAannuler = production.getQuantiteReel();
		quantite = stockPF.getStock().subtract(quantiteAannuler);

		stockPF.setStock(quantite);

		if (transferStockPF != null) {
			
			
		int j=	transferStockPF.getId();
		j=j+1;
		boolean existe=true;	
			
			while(existe==true)
			{
				
				TransferStockPF transferStockPFTmp = transferStockPFDAO.findById(j);	
				
				if(transferStockPFTmp!=null)
					
				{
					
					if(transferStockPFTmp.getStatut().equals(Constantes.ETAT_TRANSFER_STOCK_ENTRER_MP))
					{
						
						
						List<DetailTransferProduitFini> listDetailTransferStockPF = detailTransferProduitFiniDAO.findByTransferStockPF(transferStockPFTmp.getId());
						
						for (int i = 0; i < listDetailTransferStockPF.size(); i++) {
							DetailTransferProduitFini detailtransferproduitfini = listDetailTransferStockPF.get(i);
							detailTransferProduitFiniDAO.delete(detailtransferproduitfini.getId());

						}
						transferStockPFDAO.deleteObject(transferStockPFTmp);
						
						existe=true;
						
						
						
					}else
					{
						
						existe=false;
						
					}
					
			
					
					
				}else
				{
					existe=false;
					
				}
				
				
				
				j=j+1;
				
			}
			
			
			
			
			
			
			
			
			
			

			List<DetailTransferProduitFini> listDetailTransferStockPF = detailTransferProduitFiniDAO
					.findByTransferStockPF(transferStockPF.getId());
			for (int i = 0; i < listDetailTransferStockPF.size(); i++) {
				DetailTransferProduitFini detailtransferproduitfini = listDetailTransferStockPF.get(i);
				detailTransferProduitFiniDAO.delete(detailtransferproduitfini.getId());

			}
			transferStockPFDAO.deleteObject(transferStockPF);
		}

		stockPFDAO.edit(stockPF);

	}

	void deleteListeObject(List<FicheEmploye> listFicheEmploye) {

		for (int i = 0; i < listFicheEmploye.size(); i++) {
			FicheEmploye ficheEmploye = listFicheEmploye.get(i);
			ficheEmployeDAO.deleteObject(ficheEmploye);
		}
	}

// transfert MP en Produit Fini (offre et dechet)

	void transfertMPEnProduitFini(List<CoutMP> listCoutMP) {
		BigDecimal CoutMPOffre = BigDecimal.ZERO;
		Magasin magasinElnasTeaPacking = null;
		Magasin magasinElnassTeaPackingPF = null;
		Magasin magasinElnassTeaPackingPFTmp = depotDAO.magasinByCode(CODE_MAGASIN_ELNASS_TEA_PACKING);
		BigDecimal PrixOffre = BigDecimal.ZERO;
		BigDecimal QuantiteEnVrac = BigDecimal.ZERO;
		// calculer le cout des mp offre
		for (int j = 0; j < listCoutMP.size(); j++) {
			CoutMP coutMP = listCoutMP.get(j);
			if (!coutMP.getQuantiteOffre().setScale(6).equals(BigDecimal.ZERO.setScale(6))) {
				if (!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
						.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)
						&& !coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
								.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU)) {
					if (coutMP.getMagasin() != null) {
						if (coutMP.getMagasin().getId() != magasinElnassTeaPackingPFTmp.getId()) {

							CoutMPOffre = CoutMPOffre.add(coutMP.getQuantiteOffre().multiply(coutMP.getPrixUnitaire()));

						}

					} else {

						CoutMPOffre = CoutMPOffre.add(coutMP.getQuantiteOffre().multiply(coutMP.getPrixUnitaire()));

					}

				}

			}

		}

		if (production.getOffre() != null) {
			Parametre PercentagePrixCadeau = parametreDAO.findByCode(Constantes.PERCENTAGE_PRIX_OFFRE);
			Promotion promotion = promotiondao.findByCode(production.getOffre());
			Magasin magasinElnasTeaPackingTmp = null;
			magasinElnasTeaPackingTmp = depotDAO.magasinByCode(CODE_MAGASIN_ELNASS_TEA_PACKING);
			if (promotion != null) {
				for (int i = 0; i < promotion.getDetailEstimationPromo().size(); i++) {
					if (promotion.getDetailEstimationPromo().get(i).getMatierePremiere().getCategorieMp()
							.getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
						QuantiteEnVrac = QuantiteEnVrac.add(promotion.getDetailEstimationPromo().get(i).getQuantite());
					}

					if (production.getService().equals(Constantes.PRODUCTION_SERVICE_OUI)) {
						if (promotion.getDetailEstimationPromo().get(i).getMatierePremiere().getCategorieMp()
								.getSubCategorieMp().getCode()
								.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU)) {

							for (int k = 0; k < production.getListCoutMP().size(); k++) {

								if (production.getListCoutMP().get(k).getMatierePremier().getCategorieMp()
										.getSubCategorieMp().getCode()
										.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU)
										&& production.getListCoutMP().get(k).getMatierePremier().getId() == promotion
												.getDetailEstimationPromo().get(i).getMatierePremiere().getId()) {
									if (production.getListCoutMP().get(k).getMagasin() != null) {
										if (production.getListCoutMP().get(k).getMagasin()
												.getId() == magasinElnasTeaPackingTmp.getId()) {

											StockMP stockMP = stockMPDAO
													.findStockByMagasinMP(
															promotion.getDetailEstimationPromo().get(i)
																	.getMatierePremiere().getId(),
															magasinElnasTeaPackingTmp.getId());

											PrixOffre = PrixOffre.add(stockMP.getPrixUnitaire()
													.multiply(promotion.getDetailEstimationPromo().get(i).getQuantite())
													.multiply((PercentagePrixCadeau.getValeur()
															.divide(new BigDecimal(100), 6, RoundingMode.UP).add(BigDecimal.ONE))));

										}

									}

								}

							}

						} else {
							/*
							 * StockMP
							 * stockMP=stockMPDAO.findStockByMagasinMP(promotion.getDetailEstimationPromo().
							 * get(i).getMatierePremiere().getId(), magasinElnasTeaPacking.getId());
							 * PrixOffre=PrixOffre.add(stockMP.getPrixUnitaire().multiply(promotion.
							 * getDetailEstimationPromo().get(i).getQuantite()).multiply(new
							 * BigDecimal(1.2)));
							 */
						}

					} else {
						StockMP stockMP = stockMPDAO.findStockByMagasinMP(
								promotion.getDetailEstimationPromo().get(i).getMatierePremiere().getId(),
								production.getMagasinStockage().getId());
						PrixOffre = PrixOffre.add(stockMP.getPrixUnitaire()
								.multiply(promotion.getDetailEstimationPromo().get(i).getQuantite())
								.multiply((PercentagePrixCadeau.getValeur().divide(new BigDecimal(100), 6,
										RoundingMode.UP).add(BigDecimal.ONE))));
					}

				}

				PrixOffre = PrixOffre.divide(production.getArticles().getCentreCout3(), RoundingMode.HALF_UP); // centercout3
																												// est
																												// le
																												// poids
																												// de
																												// carton
			}

		}

		for (int i = 0; i < listCoutMP.size(); i++) {
			CoutMP coutMP = listCoutMP.get(i);

			// Quantité Offre
			if (!coutMP.getQuantiteOffre().setScale(6).equals(BigDecimal.ZERO.setScale(6))) {

				if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
						.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)
						|| coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
								.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU)) {

					// Inserer les Qtt offre dans la table transfert stock MP

					TransferStockMP transferStockMP = new TransferStockMP();

					transferStockMP.setCodeTransfer(Utils.genererCodeTransfer(
							AuthentificationView.utilisateur.getCodeDepot(), ETAT_TRANSFER_STOCK_SORTIE));
					transferStockMP.setCreerPar(AuthentificationView.utilisateur);
					transferStockMP.setDate(new Date());
					transferStockMP.setDateTransfer(production.getDate_debFabPre());

					transferStockMP.setDepot(production.getMagasinStockage().getDepot());

					DetailTransferStockMP detailTransferStockMP = new DetailTransferStockMP();

					if (production.getService().equals(Constantes.PRODUCTION_SERVICE_OUI)) {
						transferStockMP.setStatut(ETAT_TRANSFER_STOCK_SORTIE_PF_SERVICE);
						transferStockMPDAO.add(transferStockMP);
						magasinElnasTeaPacking = depotDAO.magasinByCode(CODE_MAGASIN_ELNASS_TEA_PACKING);

						if (!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
								.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {

							detailTransferStockMP.setMagasinDestination(production.getMagasinPF());
							if (coutMP.getMagasin() != null) {

								detailTransferStockMP.setMagasinSouce(coutMP.getMagasin());

							} else {
								detailTransferStockMP.setMagasinSouce(magasinElnasTeaPacking);
							}

						}

					} else {
						transferStockMP.setStatut(ETAT_TRANSFER_STOCK_SORTIE_PF);
						transferStockMPDAO.add(transferStockMP);
						if (!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
								.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
							detailTransferStockMP.setMagasinDestination(production.getMagasinPF());
							detailTransferStockMP.setMagasinSouce(production.getMagasinStockage());
						}
					}

					if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
							.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
						detailTransferStockMP.setMagasinDestination(production.getMagasinPF());
						detailTransferStockMP.setMagasinSouce(production.getMagasinStockage());
					}

					detailTransferStockMP.setMatierePremier(coutMP.getMatierePremier());
					detailTransferStockMP.setQuantiteOffre(BigDecimal.ZERO);
					detailTransferStockMP.setQuantite(coutMP.getQuantiteOffre());
					detailTransferStockMP.setQuantiteDechet(BigDecimal.ZERO);
					detailTransferStockMP.setPrixUnitaire(coutMP.getPrixUnitaire());
					detailTransferStockMP.setStockSource(Constantes.MP_STOCK_OFFRE);
					detailTransferStockMP.setTransferStockMP(transferStockMP);
					detailtransferMPDAO.add(detailTransferStockMP);

					// Inserer les Qtt offre dans la table transfert stock PF

					TransferStockPF transferStockPF = new TransferStockPF();
					transferStockPF.setCodeTransfer(Utils.genererCodeTransfer(
							AuthentificationView.utilisateur.getCodeDepot(), ETAT_TRANSFER_STOCK_SORTIE));
					transferStockPF.setCreerPar(AuthentificationView.utilisateur);
					transferStockPF.setDate(new Date());
					transferStockPF.setDateTransfer(production.getDate_debFabPre());
					transferStockPF.setStatut(ETAT_TRANSFER_STOCK_ENTRER_MP);
					transferStockPFDAO.add(transferStockPF);

					DetailTransferProduitFini detailTransferProduitFini = new DetailTransferProduitFini();

					if (production.getService().equals(Constantes.PRODUCTION_SERVICE_OUI)) {
						magasinElnasTeaPacking = depotDAO.magasinByCode(CODE_MAGASIN_ELNASS_TEA_PACKING);
						detailTransferProduitFini.setMagasinDestination(production.getMagasinPF());
						detailTransferProduitFini.setMagasinSouce(magasinElnasTeaPacking);

					} else {
						detailTransferProduitFini.setMagasinDestination(production.getMagasinPF());
						detailTransferProduitFini.setMagasinSouce(production.getMagasinStockage());
					}

					if (production.getService().equals(Constantes.PRODUCTION_SERVICE_OUI)) {
						if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
								.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
							BigDecimal coutEnvrac = BigDecimal.ZERO;

							coutEnvrac = (CoutMPOffre
									.add((coutMP.getQuantiteOffre().multiply(coutMP.getPrixUnitaire()))))
											.divide(coutMP.getQuantiteOffre(), 6, RoundingMode.HALF_UP);
							Parametre parametre = parametreDAO.findByCode(COUT_SERVICE);
							coutEnvrac = coutEnvrac.add(parametre.getValeur()).add(PrixOffre);
							Articles article = articlesDAO
									.findByCode(coutMP.getProdcutionCM().getArticles().getCodeArticle() + CODE_PRODUIT_FINI_OFFRE);
							if (article != null) {
								detailTransferProduitFini.setArticle(article);

								StockPF stockPF = stockPFDAO.findStockByMagasinPFBySousFamille(article.getId(),
										production.getMagasinPF().getId(), sousfamilleEnvrac.getId());
								if (stockPF != null) {
									stockPF.setStockOffre(stockPF.getStockOffre().add(coutMP.getQuantiteOffre()));
									stockPF.setPrixUnitaire(stockPF.getPrixUnitaire().multiply(stockPF.getStockOffre())
											.add(coutEnvrac.multiply(coutMP.getQuantiteOffre()))
											.divide(coutMP.getQuantiteOffre().add(stockPF.getStockOffre()),
													RoundingMode.HALF_UP));
									stockPFDAO.edit(stockPF);
								} else {
									StockPF stockPFTmp = new StockPF();
									stockPFTmp.setArticles(article);
									stockPFTmp.setMagasin(production.getMagasinPF());
									stockPFTmp.setPrixUnitaire(coutEnvrac);
									stockPFTmp.setSousFamille(sousfamilleEnvrac);
									stockPFTmp.setStock(BigDecimal.ZERO);
									stockPFTmp.setStockOffre(coutMP.getQuantiteOffre());
									stockPFDAO.add(stockPFTmp);
								}

							} else {
								Articles articleTmp = new Articles();
								articleTmp.setCodeArticle(
										coutMP.getProdcutionCM().getArticles().getCodeArticle() + CODE_PRODUIT_FINI_OFFRE);
								articleTmp.setConditionnement(
										coutMP.getProdcutionCM().getArticles().getConditionnement());
								articleTmp.setLiblle(coutMP.getProdcutionCM().getArticles().getLiblle() + " (OFFRE)");
								articleTmp.setCentreCout1(coutMP.getProdcutionCM().getArticles().getCentreCout1());
								articleTmp.setCentreCout3(coutMP.getProdcutionCM().getArticles().getCentreCout3());
								articleTmp.setCentreCout2(Constantes.ARTICLE_PIECE);
								articleTmp.setDateCreation(new Date());
								articlesDAO.add(articleTmp);
								detailTransferProduitFini.setArticle(articleTmp);

								StockPF stcokPfTmp = new StockPF();
								stcokPfTmp.setArticles(articleTmp);
								stcokPfTmp.setSousFamille(sousfamilleEnvrac);
								stcokPfTmp.setMagasin(production.getMagasinPF());
								stcokPfTmp.setPrixUnitaire(coutEnvrac);
								stcokPfTmp.setStock(BigDecimal.ZERO);
								stcokPfTmp.setStockOffre(coutMP.getQuantiteOffre());
								stockPFDAO.add(stcokPfTmp);
							}

							detailTransferProduitFini.setSousFamille(sousfamilleEnvrac);
							detailTransferProduitFini.setPrixUnitaire(coutEnvrac);

							detailTransferProduitFini.setQuantite(coutMP.getQuantiteOffre());

							detailTransferProduitFini.setDateTransfer(production.getDate_debFabPre());
							detailTransferProduitFini.setTransferStockPF(transferStockPF);
							detailTransferProduitFini.setTypeTransfer(ETAT_TRANSFER_STOCK_ENTRER_MP);
							detailTransferProduitFiniDAO.add(detailTransferProduitFini);

						} else {

							if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
									.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU)) {

								if (coutMP.getMagasin() != null) {

									if (!coutMP.getMagasin().getLibelle()
											.equals(depotDAO.magasinByCode(CODE_MAGASIN_ELNASS_TEA_PACKING).getLibelle())) {

										SousFamilleArticlePF sousfamille = sousFamilleArticleDAo
												.findByCode(coutMP.getMatierePremier().getCode() + "_O");
										Articles article = articlesDAO
												.findByCode(coutMP.getMatierePremier().getCode() + CODE_PRODUIT_FINI_OFFRE);
										if (article != null) {
											detailTransferProduitFini.setArticle(article);

											StockPF stockPF = stockPFDAO.findStockByMagasinPFBySousFamille(
													article.getId(), production.getMagasinPF().getId(),
													sousfamille.getId());
											if (stockPF != null) {
												stockPF.setStockOffre(
														stockPF.getStockOffre().add(coutMP.getQuantiteOffre()));
												stockPFDAO.edit(stockPF);
											} else {
												StockPF stockPFTmp = new StockPF();
												stockPFTmp.setArticles(article);
												stockPFTmp.setMagasin(production.getMagasinPF());
												stockPFTmp.setPrixUnitaire(coutMP.getPrixUnitaire());
												stockPFTmp.setSousFamille(sousfamille);
												stockPFTmp.setStock(BigDecimal.ZERO);
												stockPFTmp.setStockOffre(coutMP.getQuantiteOffre());
												stockPFDAO.add(stockPFTmp);
											}

										} else {

											Articles articleTmp = new Articles();
											articleTmp.setCodeArticle(coutMP.getMatierePremier().getCode() + CODE_PRODUIT_FINI_OFFRE);
											articleTmp.setConditionnement(BigDecimal.ONE);
											articleTmp.setLiblle(coutMP.getMatierePremier().getNom() + " (OFFRE)");
											
											articleTmp.setCentreCout2(Constantes.ARTICLE_PIECE);
											articleTmp.setDateCreation(new Date());
											articlesDAO.add(articleTmp);
											detailTransferProduitFini.setArticle(articleTmp);

											StockPF stcokPfTmp = new StockPF();
											stcokPfTmp.setArticles(articleTmp);
											stcokPfTmp.setSousFamille(sousfamille);
											stcokPfTmp.setMagasin(production.getMagasinPF());
											stcokPfTmp.setPrixUnitaire(coutMP.getPrixUnitaire());
											stcokPfTmp.setStock(BigDecimal.ZERO);
											stcokPfTmp.setStockOffre(coutMP.getQuantiteOffre());
											stockPFDAO.add(stcokPfTmp);
										}

										detailTransferProduitFini.setSousFamille(sousfamille);
										detailTransferProduitFini.setPrixUnitaire(coutMP.getPrixUnitaire());
										detailTransferProduitFini.setQuantite(coutMP.getQuantiteOffre());
										detailTransferProduitFini.setDateTransfer(production.getDate_debFabPre());
										detailTransferProduitFini.setTransferStockPF(transferStockPF);
										detailTransferProduitFini.setTypeTransfer(ETAT_TRANSFER_STOCK_ENTRER_MP);
										detailTransferProduitFiniDAO.add(detailTransferProduitFini);

									}

								}

							}

						}
					} else {
						if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
								.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
							BigDecimal coutEnvrac = BigDecimal.ZERO;
							coutEnvrac = (CoutMPOffre
									.add((coutMP.getQuantiteOffre().multiply(coutMP.getPrixUnitaire()))))
											.divide(coutMP.getQuantiteOffre(), 6, RoundingMode.HALF_UP);

							Articles article = articlesDAO
									.findByCode(coutMP.getProdcutionCM().getArticles().getCodeArticle() + CODE_PRODUIT_FINI_OFFRE);
							if (article != null) {
								detailTransferProduitFini.setArticle(article);

								StockPF stockPF = stockPFDAO.findStockByMagasinPFBySousFamille(article.getId(),
										production.getMagasinPF().getId(), sousfamilleEnvrac.getId());
								if (stockPF != null) {
									stockPF.setStockOffre(stockPF.getStockOffre().add(coutMP.getQuantiteOffre()));
									stockPF.setPrixUnitaire(stockPF.getPrixUnitaire().multiply(stockPF.getStockOffre())
											.add(coutEnvrac.multiply(coutMP.getQuantiteOffre()))
											.divide(coutMP.getQuantiteOffre().add(stockPF.getStockOffre()),
													RoundingMode.HALF_UP));
									stockPFDAO.edit(stockPF);
								} else {
									StockPF stockPFTmp = new StockPF();
									stockPFTmp.setArticles(article);
									stockPFTmp.setMagasin(production.getMagasinPF());
									stockPFTmp.setPrixUnitaire(coutEnvrac);
									stockPFTmp.setSousFamille(sousfamilleEnvrac);
									stockPFTmp.setStock(BigDecimal.ZERO);
									stockPFTmp.setStockOffre(coutMP.getQuantiteOffre());
									stockPFDAO.add(stockPFTmp);
								}

							} else {
								Articles articleTmp = new Articles();
								articleTmp.setCodeArticle(
										coutMP.getProdcutionCM().getArticles().getCodeArticle() + CODE_PRODUIT_FINI_OFFRE);
								articleTmp.setConditionnement(
										coutMP.getProdcutionCM().getArticles().getConditionnement());
								articleTmp.setLiblle(coutMP.getProdcutionCM().getArticles().getLiblle() + " (OFFRE)");
								articleTmp.setCentreCout2(Constantes.ARTICLE_PIECE);
								articleTmp.setCentreCout1(coutMP.getProdcutionCM().getArticles().getCentreCout1());
								articleTmp.setCentreCout3(coutMP.getProdcutionCM().getArticles().getCentreCout3());
								articleTmp.setDateCreation(new Date());
								articlesDAO.add(articleTmp);
								detailTransferProduitFini.setArticle(articleTmp);

								StockPF stcokPfTmp = new StockPF();
								stcokPfTmp.setArticles(articleTmp);
								stcokPfTmp.setSousFamille(sousfamilleEnvrac);
								stcokPfTmp.setMagasin(production.getMagasinPF());
								stcokPfTmp.setPrixUnitaire(coutEnvrac);
								stcokPfTmp.setStock(BigDecimal.ZERO);
								stcokPfTmp.setStockOffre(coutMP.getQuantiteOffre());
								stockPFDAO.add(stcokPfTmp);
							}

							detailTransferProduitFini.setSousFamille(sousfamilleEnvrac);
							detailTransferProduitFini.setPrixUnitaire(coutEnvrac);

							detailTransferProduitFini.setQuantite(coutMP.getQuantiteOffre());

							detailTransferProduitFini.setDateTransfer(production.getDate_debFabPre());
							detailTransferProduitFini.setTransferStockPF(transferStockPF);
							detailTransferProduitFini.setTypeTransfer(ETAT_TRANSFER_STOCK_ENTRER_MP);
							detailTransferProduitFiniDAO.add(detailTransferProduitFini);

						} else {
							SousFamilleArticlePF sousfamille = sousFamilleArticleDAo
									.findByCode(coutMP.getMatierePremier().getCode() + "_O");
							Articles article = articlesDAO.findByCode(coutMP.getMatierePremier().getCode() + CODE_PRODUIT_FINI_OFFRE);
							if (article != null) {
								detailTransferProduitFini.setArticle(article);

								StockPF stockPF = stockPFDAO.findStockByMagasinPFBySousFamille(article.getId(),
										production.getMagasinPF().getId(), sousfamille.getId());
								if (stockPF != null) {
									stockPF.setStockOffre(stockPF.getStockOffre().add(coutMP.getQuantiteOffre()));
									stockPFDAO.edit(stockPF);
								} else {
									StockPF stockPFTmp = new StockPF();
									stockPFTmp.setArticles(article);
									stockPFTmp.setMagasin(production.getMagasinPF());
									stockPFTmp.setPrixUnitaire(coutMP.getPrixUnitaire());
									stockPFTmp.setSousFamille(sousfamille);
									stockPFTmp.setStock(BigDecimal.ZERO);
									stockPFTmp.setStockOffre(coutMP.getQuantiteOffre());
									stockPFDAO.add(stockPFTmp);
								}

							} else {

								Articles articleTmp = new Articles();
								articleTmp.setCodeArticle(coutMP.getMatierePremier().getCode() + CODE_PRODUIT_FINI_OFFRE);
								articleTmp.setConditionnement(BigDecimal.ONE);
								articleTmp.setLiblle(coutMP.getMatierePremier().getNom() + " (OFFRE)");
								articleTmp.setCentreCout2(Constantes.ARTICLE_PIECE);
								articleTmp.setDateCreation(new Date());
								articlesDAO.add(articleTmp);
								detailTransferProduitFini.setArticle(articleTmp);

								StockPF stcokPfTmp = new StockPF();
								stcokPfTmp.setArticles(articleTmp);
								stcokPfTmp.setSousFamille(sousfamille);
								stcokPfTmp.setMagasin(production.getMagasinPF());
								stcokPfTmp.setPrixUnitaire(coutMP.getPrixUnitaire());
								stcokPfTmp.setStock(BigDecimal.ZERO);
								stcokPfTmp.setStockOffre(coutMP.getQuantiteOffre());
								stockPFDAO.add(stcokPfTmp);
							}

							detailTransferProduitFini.setSousFamille(sousfamille);
							detailTransferProduitFini.setPrixUnitaire(coutMP.getPrixUnitaire());

							detailTransferProduitFini.setQuantite(coutMP.getQuantiteOffre());

							detailTransferProduitFini.setDateTransfer(production.getDate_debFabPre());
							detailTransferProduitFini.setTransferStockPF(transferStockPF);
							detailTransferProduitFini.setTypeTransfer(ETAT_TRANSFER_STOCK_ENTRER_MP);
							detailTransferProduitFiniDAO.add(detailTransferProduitFini);
						}
					}

				} else {

					// Inserer les Qtt offre dans la table transfert stock MP

					TransferStockMP transferStockMP = new TransferStockMP();

					transferStockMP.setCodeTransfer(Utils.genererCodeTransfer(
							AuthentificationView.utilisateur.getCodeDepot(), ETAT_TRANSFER_STOCK_SORTIE));
					transferStockMP.setCreerPar(AuthentificationView.utilisateur);
					transferStockMP.setDate(new Date());
					transferStockMP.setDateTransfer(production.getDate_debFabPre());

					transferStockMP.setDepot(production.getMagasinStockage().getDepot());

					DetailTransferStockMP detailTransferStockMP = new DetailTransferStockMP();

					if (production.getService().equals(Constantes.PRODUCTION_SERVICE_OUI)) {
						transferStockMP.setStatut(ETAT_TRANSFER_STOCK_SORTIE_PF_SERVICE);
						transferStockMPDAO.add(transferStockMP);
						magasinElnasTeaPacking = depotDAO.magasinByCode(CODE_MAGASIN_ELNASS_TEA_PACKING);

						if (!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
								.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
							detailTransferStockMP.setMagasinDestination(production.getMagasinPF());
							if (coutMP.getMagasin() != null) {
								detailTransferStockMP.setMagasinSouce(coutMP.getMagasin());
							} else {
								detailTransferStockMP.setMagasinSouce(magasinElnasTeaPacking);
							}

						}

					} else {
						transferStockMP.setStatut(ETAT_TRANSFER_STOCK_SORTIE_PF);
						transferStockMPDAO.add(transferStockMP);
						if (!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
								.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
							detailTransferStockMP.setMagasinDestination(production.getMagasinPF());
							detailTransferStockMP.setMagasinSouce(production.getMagasinStockage());
						}

					}

					if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
							.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
						detailTransferStockMP.setMagasinDestination(production.getMagasinPF());
						detailTransferStockMP.setMagasinSouce(production.getMagasinStockage());
					}

					detailTransferStockMP.setMatierePremier(coutMP.getMatierePremier());
					detailTransferStockMP.setQuantiteOffre(BigDecimal.ZERO);
					detailTransferStockMP.setQuantite(coutMP.getQuantiteOffre());
					detailTransferStockMP.setQuantiteDechet(BigDecimal.ZERO);
					detailTransferStockMP.setPrixUnitaire(coutMP.getPrixUnitaire());
					detailTransferStockMP.setStockSource(Constantes.MP_STOCK_OFFRE);
					detailTransferStockMP.setTransferStockMP(transferStockMP);
					detailtransferMPDAO.add(detailTransferStockMP);

				}

			}

			// Quantite Dechet
			if (!coutMP.getQuantDechet().setScale(6).equals(BigDecimal.ZERO.setScale(6))) {

				/* Inserer les Qtt dechet dans la table transfert stock MP */
				SousFamilleArticlePF sousFamilleArticlePF = null;
				sousFamilleArticlePF = sousFamilleArticleDAo.findByCode(coutMP.getMatierePremier().getCode());
				TransferStockMP transferStockMP = new TransferStockMP();

				transferStockMP.setCodeTransfer(Utils.genererCodeTransfer(
						AuthentificationView.utilisateur.getCodeDepot(), ETAT_TRANSFER_STOCK_SORTIE));
				transferStockMP.setCreerPar(AuthentificationView.utilisateur);
				transferStockMP.setDate(new Date());
				transferStockMP.setDateTransfer(production.getDate_debFabPre());

				transferStockMP.setDepot(production.getMagasinStockage().getDepot());

				if (!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
						.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
					sousFamilleArticlePF = sousFamilleArticleDAo.findByCode(coutMP.getMatierePremier().getCode());
				}

				DetailTransferStockMP detailTransferStockMP = new DetailTransferStockMP();

				if (production.getService().equals(Constantes.PRODUCTION_SERVICE_OUI)) {
					transferStockMP.setStatut(ETAT_TRANSFER_STOCK_SORTIE_PF_SERVICE);
					transferStockMPDAO.add(transferStockMP);
					magasinElnasTeaPacking = depotDAO.magasinByCode(CODE_MAGASIN_ELNASS_TEA_PACKING);
					magasinElnassTeaPackingPF = depotDAO.magasinByCode(CODE_MAGASIN_ELNASS_TEA_PACKING_PF);
					if (!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
							.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
						if (coutMP.getMagasin() != null) {
							detailTransferStockMP.setMagasinSouce(coutMP.getMagasin());
						} else {
							detailTransferStockMP.setMagasinSouce(magasinElnasTeaPacking);
						}
						detailTransferStockMP.setMagasinDestination(magasinElnassTeaPackingPF);

					}

				} else {
					transferStockMP.setStatut(ETAT_TRANSFER_STOCK_SORTIE_PF);
					transferStockMPDAO.add(transferStockMP);
					if (!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
							.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
						detailTransferStockMP.setMagasinDestination(production.getMagasinPF());
						detailTransferStockMP.setMagasinSouce(production.getMagasinStockage());
					}
				}

				if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
						.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
					detailTransferStockMP.setMagasinDestination(production.getMagasinPF());
					detailTransferStockMP.setMagasinSouce(production.getMagasinStockage());
				}

				detailTransferStockMP.setMatierePremier(coutMP.getMatierePremier());
				detailTransferStockMP.setQuantiteOffre(BigDecimal.ZERO);
				detailTransferStockMP.setQuantite(coutMP.getQuantDechet());
				detailTransferStockMP.setQuantiteDechet(BigDecimal.ZERO);
				detailTransferStockMP.setPrixUnitaire(coutMP.getPrixUnitaire());
				detailTransferStockMP.setStockSource(Constantes.MP_STOCK_DECHET);
				detailTransferStockMP.setTransferStockMP(transferStockMP);
				detailtransferMPDAO.add(detailTransferStockMP);

				/*
				 * if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().
				 * equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
				 */

				/* Inserer les Qtt dechet dans la table transfert stock PF */

				TransferStockPF transferStockPF = new TransferStockPF();
				transferStockPF.setCodeTransfer(Utils.genererCodeTransfer(
						AuthentificationView.utilisateur.getCodeDepot(), ETAT_TRANSFER_STOCK_SORTIE));
				transferStockPF.setCreerPar(AuthentificationView.utilisateur);
				transferStockPF.setDate(new Date());
				transferStockPF.setDateTransfer(production.getDate_debFabPre());
				transferStockPF.setStatut(ETAT_TRANSFER_STOCK_ENTRER_MP);
				transferStockPFDAO.add(transferStockPF);

				DetailTransferProduitFini detailTransferProduitFini = new DetailTransferProduitFini();
				if (production.getService().equals(Constantes.PRODUCTION_SERVICE_OUI)) {
					/*
					 * if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().
					 * equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
					 * detailTransferProduitFini.setMagasinDestination(production.getMagasinPF());
					 * detailTransferProduitFini.setMagasinSouce(production.getMagasinStockage());
					 * 
					 * }else {
					 * detailTransferProduitFini.setMagasinDestination(magasinElnassTeaPackingPF);
					 * detailTransferProduitFini.setMagasinSouce(magasinElnasTeaPacking); }
					 */
					if (coutMP.getMagasin() != null) {

						detailTransferProduitFini.setMagasinSouce(coutMP.getMagasin());

						if (coutMP.getMagasin().getId() == magasinElnasTeaPacking.getId()) {
							detailTransferProduitFini.setMagasinDestination(magasinElnassTeaPackingPF);
						} else {
							detailTransferProduitFini.setMagasinDestination(production.getMagasinPF());
						}

					} else {
						detailTransferProduitFini.setMagasinDestination(production.getMagasinPF());
						detailTransferProduitFini.setMagasinSouce(production.getMagasinStockage());

					}

				} else {
					detailTransferProduitFini.setMagasinDestination(production.getMagasinPF());
					detailTransferProduitFini.setMagasinSouce(production.getMagasinStockage());
				}

				Articles article = articlesDAO.findByCode(coutMP.getMatierePremier().getCode() + "_PFD");
				if (article != null) {
					detailTransferProduitFini.setArticle(article);
					StockPF stockPF = null;
					if (production.getService().equals(Constantes.PRODUCTION_SERVICE_OUI)) {

						if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
								.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
							stockPF = stockPFDAO.findStockByMagasinPFBySousFamille(article.getId(),
									production.getMagasinPF().getId(), sousfamilleEnvrac.getId());

						} else {
							if (coutMP.getMagasin() != null) {
								if (coutMP.getMagasin().getId() == magasinElnasTeaPacking.getId()) {
									stockPF = stockPFDAO.findStockByMagasinPFBySousFamille(article.getId(),
											magasinElnassTeaPackingPF.getId(), sousFamilleArticlePF.getId());

								} else {
									stockPF = stockPFDAO.findStockByMagasinPFBySousFamille(article.getId(),
											production.getMagasinPF().getId(), sousfamilleEnvrac.getId());

								}

							} else {

								stockPF = stockPFDAO.findStockByMagasinPFBySousFamille(article.getId(),
										magasinElnassTeaPackingPF.getId(), sousFamilleArticlePF.getId());

							}

						}

					} else {
						if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
								.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
							stockPF = stockPFDAO.findStockByMagasinPFBySousFamille(article.getId(),
									production.getMagasinPF().getId(), sousfamilleEnvrac.getId());
						} else {
							stockPF = stockPFDAO.findStockByMagasinPFBySousFamille(article.getId(),
									production.getMagasinPF().getId(), sousFamilleArticlePF.getId());

						}

					}

					if (stockPF != null) {
						stockPF.setStock(stockPF.getStock().add(coutMP.getQuantDechet()));
						stockPFDAO.edit(stockPF);
					} else {
						StockPF stockPFTmp = new StockPF();
						stockPFTmp.setArticles(article);
						if (production.getService().equals(Constantes.PRODUCTION_SERVICE_OUI)) {
							if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
									.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {

								stockPFTmp.setMagasin(production.getMagasinPF());

							} else

							{
								if (coutMP.getMagasin() != null) {

									if (coutMP.getMagasin().getId() == magasinElnasTeaPacking.getId()) {
										stockPFTmp.setMagasin(magasinElnassTeaPackingPF);

									} else {

										stockPFTmp.setMagasin(production.getMagasinPF());
									}

								} else {
									stockPFTmp.setMagasin(magasinElnassTeaPackingPF);
								}

							}

						} else {
							stockPFTmp.setMagasin(production.getMagasinPF());
						}

						stockPFTmp.setPrixUnitaire(coutMP.getPrixUnitaire());
						if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
								.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
							stockPFTmp.setSousFamille(sousfamilleEnvrac);
						} else {
							stockPFTmp.setSousFamille(sousFamilleArticlePF);
						}

						stockPFTmp.setStockOffre(BigDecimal.ZERO);
						stockPFTmp.setStock(coutMP.getQuantDechet());
						stockPFDAO.add(stockPFTmp);
					}

				} else {
					Articles articleTmp = new Articles();
					articleTmp.setCodeArticle(coutMP.getMatierePremier().getCode() + "_PFD");
					articleTmp.setConditionnement(coutMP.getProdcutionCM().getArticles().getConditionnement());
					articleTmp.setLiblle(coutMP.getMatierePremier().getNom() + " (DECHET)");
					articleTmp.setCentreCout2(Constantes.ARTICLE_PIECE);
					articleTmp.setDateCreation(new Date());
					articlesDAO.add(articleTmp);
					detailTransferProduitFini.setArticle(articleTmp);

					StockPF stcokPfTmp = new StockPF();
					stcokPfTmp.setArticles(articleTmp);
					if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
							.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
						stcokPfTmp.setSousFamille(sousfamilleEnvrac);
					} else {
						stcokPfTmp.setSousFamille(sousFamilleArticlePF);
					}

					if (production.getService().equals(Constantes.PRODUCTION_SERVICE_OUI)) {

						if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
								.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
							stcokPfTmp.setMagasin(production.getMagasinPF());
						} else {

							if (coutMP.getMagasin() != null) {

								if (coutMP.getMagasin().getId() == magasinElnasTeaPacking.getId()) {

									stcokPfTmp.setMagasin(magasinElnassTeaPackingPF);

								} else {

									stcokPfTmp.setMagasin(production.getMagasinPF());

								}

							} else {
								stcokPfTmp.setMagasin(magasinElnassTeaPackingPF);

							}

						}

					} else {
						stcokPfTmp.setMagasin(production.getMagasinPF());
					}
					stcokPfTmp.setPrixUnitaire(coutMP.getPrixUnitaire());
					stcokPfTmp.setStock(coutMP.getQuantDechet());
					stcokPfTmp.setStockOffre(BigDecimal.ZERO);
					stockPFDAO.add(stcokPfTmp);
				}

				detailTransferProduitFini.setQuantite(coutMP.getQuantDechet());
				detailTransferProduitFini.setPrixUnitaire(coutMP.getPrixUnitaire());
				if (coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
						.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
					detailTransferProduitFini.setSousFamille(sousfamilleEnvrac);
				} else {
					detailTransferProduitFini.setSousFamille(sousFamilleArticlePF);

				}

				detailTransferProduitFini.setDateTransfer(production.getDate_debFabPre());
				detailTransferProduitFini.setTransferStockPF(transferStockPF);
				detailTransferProduitFini.setTypeTransfer(ETAT_TRANSFER_STOCK_ENTRER_MP);
				detailTransferProduitFiniDAO.add(detailTransferProduitFini);

				/*
				 * }else {
				 * 
				 * 
				 * Inserer les Qtt dechet dans la table transfert stock PF
				 * 
				 * TransferStockPF transferStockPF=new TransferStockPF();
				 * transferStockPF.setCodeTransfer(Utils.genererCodeTransfer(
				 * AuthentificationView.utilisateur.getCodeDepot(),ETAT_TRANSFER_STOCK_SORTIE));
				 * transferStockPF.setCreerPar(AuthentificationView.utilisateur);
				 * transferStockPF.setDate(new Date());
				 * transferStockPF.setDateTransfer(production.getDateDebFabRee());
				 * transferStockPF.setStatut(ETAT_TRANSFER_STOCK_ENTRER_MP);
				 * transferStockPFDAO.add(transferStockPF);
				 * 
				 * magasinElnasTeaPacking=depotDAO.magasinByCode(CODE_MAGASIN_ELNASS_TEA_PACKING
				 * ); magasinElnassTeaPackingPF=depotDAO.magasinByCode(
				 * CODE_MAGASIN_ELNASS_TEA_PACKING_PF);
				 * 
				 * DetailTransferProduitFini detailTransferProduitFini=new
				 * DetailTransferProduitFini();
				 * detailTransferProduitFini.setMagasinDestination(magasinElnassTeaPackingPF);
				 * detailTransferProduitFini.setMagasinSouce(magasinElnasTeaPacking);
				 * 
				 * SousFamilleArticlePF
				 * sousfamille=sousFamilleArticleDAo.findByCode(coutMP.getMatierePremier().
				 * getCode()); // sous famille dechet Articles article=
				 * articlesDAO.findByCode(coutMP.getMatierePremier().getCode()+"_PFD");
				 * if(article!=null) { detailTransferProduitFini.setArticle(article);
				 * 
				 * 
				 * StockPF stockPF=stockPFDAO.findStockByMagasinPFBySousFamille(article.getId(),
				 * magasinElnassTeaPackingPF.getId(),sousfamille.getId()); if(stockPF!=null) {
				 * stockPF.setStock(stockPF.getStock().add(coutMP.getQuantDechet()));
				 * stockPFDAO.edit(stockPF); }else { StockPF stockPFTmp=new StockPF();
				 * stockPFTmp.setArticles(article);
				 * stockPFTmp.setMagasin(magasinElnassTeaPackingPF);
				 * stockPFTmp.setPrixUnitaire(coutMP.getPrixUnitaire());
				 * stockPFTmp.setSousFamille(sousfamille); stockPFTmp.setStock(BigDecimal.ZERO);
				 * stockPFTmp.setStock(coutMP.getQuantDechet()); stockPFDAO.add(stockPFTmp); }
				 * 
				 * 
				 * }else {
				 * 
				 * Articles articleTmp=new Articles();
				 * articleTmp.setCodeArticle(coutMP.getMatierePremier().getCode()+"_PFD");
				 * articleTmp.setConditionnement(BigDecimal.ONE);
				 * articleTmp.setLiblle(coutMP.getMatierePremier().getNom()+" (DECHET)");
				 * articleTmp.setCentreCout2(Constantes.ARTICLE_PIECE);
				 * articleTmp.setDateCreation(new Date()); articlesDAO.add(articleTmp);
				 * detailTransferProduitFini.setArticle(articleTmp);
				 * 
				 * StockPF stcokPfTmp=new StockPF(); stcokPfTmp.setArticles(articleTmp);
				 * stcokPfTmp.setSousFamille(sousfamille);
				 * stcokPfTmp.setMagasin(magasinElnassTeaPackingPF);
				 * stcokPfTmp.setPrixUnitaire(coutMP.getPrixUnitaire());
				 * stcokPfTmp.setStock(coutMP.getQuantDechet()); stcokPfTmp.setStockOffre
				 * (BigDecimal.ZERO); stockPFDAO.add(stcokPfTmp); }
				 * 
				 * detailTransferProduitFini.setQuantite(coutMP.getQuantDechet());
				 * detailTransferProduitFini.setPrixUnitaire(coutMP.getPrixUnitaire());
				 * detailTransferProduitFini.setSousFamille(sousfamille);
				 * detailTransferProduitFini.setDateTransfer(production.getDateDebFabRee());
				 * detailTransferProduitFini.setTransferStockPF(transferStockPF);
				 * detailTransferProduitFini.setTypeTransfer(ETAT_TRANSFER_STOCK_ENTRER_MP);
				 * detailTransferProduitFiniDAO.add(detailTransferProduitFini);
				 * 
				 * }
				 */

			}

		}

	}

	void majDelaiEmployeGenerique(List<Production> listeProduction) {

		// List<Production> listeProduction
		// =productionDAO.listeProductionByDateByPeriode(dateProd,periode);
		BigDecimal coutResponsableProd = BigDecimal.ZERO;
		BigDecimal delaiEmploye = BigDecimal.ZERO;
		BigDecimal coutHeure25 = BigDecimal.ZERO;
		BigDecimal delaiHeure25 = BigDecimal.ZERO;
		BigDecimal coutHeure50 = BigDecimal.ZERO;
		BigDecimal delaiHeure50 = BigDecimal.ZERO;
		BigDecimal coutTotalEmployeGen = BigDecimal.ZERO;
		BigDecimal remise = BigDecimal.ZERO;
		BigDecimal coutTotal = BigDecimal.ZERO;

		int compteur = 1;
		for (int i = 0; i < listeProduction.size(); i++) {
			coutTotalEmployeGen = BigDecimal.ZERO;
			Production production = listeProduction.get(i);
			if (production.getStatut().equals(ETAT_OF_TERMINER)) {
				List<DetailResponsableProd> listeDetailResponsableProd = production.getListDetailResponsableProd();

				for (int j = 0; j < listeDetailResponsableProd.size(); j++) {

					DetailResponsableProd detailResponsableProd = listeDetailResponsableProd.get(j);

					if (!detailResponsableProd.getEmploye().isSalarie()) {

						Employe employe = detailResponsableProd.getEmploye();
						FicheEmploye ficheEmploye = ficheEmployeDAO.findByPeriodeDateSitutation(production.getDate(),
								detailResponsableProd.getEmploye().getId());

						CompteurEmployeProd compteurEmployeProd = compteurEmployeProdDAO
								.findByDateProdPeriode(production.getDate(), production.getPeriode(), employe.getId());

						if (compteurEmployeProd != null)
							compteur = compteurEmployeProd.getCompteur();
						else
							compteur = 1;
						if (ficheEmploye != null) {

							coutResponsableProd = ficheEmploye.getCoutTotal().divide(new BigDecimal(compteur), 6,
									BigDecimal.ROUND_HALF_UP);
							delaiEmploye = ficheEmploye.getDelaiEmploye().divide(new BigDecimal(compteur), 6,
									BigDecimal.ROUND_HALF_UP);
							coutHeure25 = ficheEmploye.getCoutSupp25().divide(new BigDecimal(compteur), 6,
									BigDecimal.ROUND_HALF_UP);
							delaiHeure25 = ficheEmploye.getHeureSupp25().divide(new BigDecimal(compteur), 6,
									BigDecimal.ROUND_HALF_UP);
							coutHeure50 = ficheEmploye.getCoutSupp50().divide(new BigDecimal(compteur), 6,
									BigDecimal.ROUND_HALF_UP);
							delaiHeure50 = ficheEmploye.getHeureSupp50().divide(new BigDecimal(compteur), 6,
									BigDecimal.ROUND_HALF_UP);
							remise = ficheEmploye.getRemise().divide(new BigDecimal(compteur), 6,
									BigDecimal.ROUND_HALF_UP);

							detailResponsableProd.setCoutTotal(coutResponsableProd);
							detailResponsableProd.setDelaiEmploye(delaiEmploye);
							detailResponsableProd.setCoutSupp25(coutHeure25);
							detailResponsableProd.setHeureSupp25(delaiHeure25);
							detailResponsableProd.setHeureSupp50(delaiHeure50);
							detailResponsableProd.setCoutSupp50(coutHeure50);
							detailResponsableProd.setRemise(remise);

							coutTotalEmployeGen = coutTotalEmployeGen.add(coutResponsableProd).add(coutHeure25)
									.add(coutHeure50);
						}

						listeDetailResponsableProd.set(j, detailResponsableProd);
					}

				}

				coutTotal = production.getCoutTotalMP().add(production.getCoutDechet())
						.add(production.getCoutTotalEmploye()).add(production.getCoutTotalEmployeEmbalage())
						.add(coutTotalEmployeGen);
				// calculerStockCoutProduitFini(coutTotal);
				production.setListDetailResponsableProd(listeDetailResponsableProd);
				production.setCoutTotalEmployeGen(coutTotalEmployeGen);

				production.setCoutTotal(coutPF.multiply(production.getQuantiteReel()));
				productionDAO.edit(production);

			}

		}
	}

	public void CalculeCoutMP() {

		Magasin magasinElnasTeaPackingTmp = null;
		magasinElnasTeaPackingTmp = depotDAO.magasinByCode(CODE_MAGASIN_ELNASS_TEA_PACKING);
		BigDecimal quantiteDechet = BigDecimal.ZERO;
		BigDecimal quantiteConsomme = BigDecimal.ZERO;
		BigDecimal quantiteReste = BigDecimal.ZERO;
		BigDecimal quantiteMP = BigDecimal.ZERO;

		BigDecimal quantiteDechetFour = BigDecimal.ZERO;
		BigDecimal quantiteManquante = BigDecimal.ZERO;
		BigDecimal quantiteOffre = BigDecimal.ZERO;
		Magasin magasinElnasTeaPacking = null;
		BigDecimal prixUnitaire = BigDecimal.ZERO;
		BigDecimal prixMP = BigDecimal.ZERO;
		BigDecimal coutDechet = BigDecimal.ZERO;
		BigDecimal coutDechetFour = BigDecimal.ZERO;
		BigDecimal coutManquante = BigDecimal.ZERO;
		BigDecimal coutQuantiteOffre = BigDecimal.ZERO;

		BigDecimal quantiteStock = BigDecimal.ZERO;

		List<CoutMP> listCoutMPTmp = new ArrayList<CoutMP>();
		for (int i = 0; i < production.getListCoutMP().size(); i++) {
			prixMP = BigDecimal.ZERO;
			CoutMP coutMP = production.getListCoutMP().get(i);
			prixUnitaire = coutMP.getPrixUnitaire();
			StockMP stockmp = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
					production.getMagasinProd().getId());

			StockMP stockmpStockage = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
					production.getMagasinStockage().getId());
			StockMP stockmpTmp = null;
			// quantiteConsomme=Integer.parseInt(mapQuantiteConsomme.get(coutMP.getMatierePremier().getCode()));

			if ( coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
							.equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX)) {
				quantiteConsomme = coutMP.getQuantConsomme().setScale(0, BigDecimal.ROUND_UP);
			
			} else if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
					.equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON)
					)
			{
				quantiteConsomme = coutMP.getQuantConsomme().setScale(0, BigDecimal.ROUND_DOWN);
				
			}
			
			
			
			else {
				quantiteConsomme = coutMP.getQuantConsomme();
			}

			quantiteDechet = new BigDecimal(mapQuantiteDechet.get(coutMP.getMatierePremier().getCode()));

			quantiteReste = new BigDecimal(mapQuantiteReste.get(coutMP.getMatierePremier().getCode()));

			quantiteDechetFour = new BigDecimal(mapQuantiteDechetFour.get(coutMP.getMatierePremier().getCode()));

			quantiteManquante = new BigDecimal(mapQuantiteManquante.get(coutMP.getMatierePremier().getCode()));

			quantiteOffre = new BigDecimal(mapQuantiteOffre.get(coutMP.getMatierePremier().getCode()));

			// Modifier quantite dechet de transfer stock MP
			// Modifier quantite dechet de transfer stock MP
			TransferStockMP transferstockmp = null;
			if (coutMP.getProdcutionCM().getService().equals(Constantes.PRODUCTION_SERVICE_NON)) {
				transferstockmp = transferstockmpDAO.findTransferByCodeStatut(production.getNumOF(),
						Constantes.ETAT_TRANSFER_STOCK_CHARGE);
				stockmpTmp = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
						production.getMagasinStockage().getId());

			} else if ((coutMP.getProdcutionCM().getService().equals(Constantes.PRODUCTION_SERVICE_OUI))) {
				transferstockmp = transferstockmpDAO.findTransferByCodeStatut(production.getNumOF(),
						Constantes.ETAT_TRANSFER_STOCK_SERVICE);
				if (!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
						.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
					if (!coutMP.getProdcutionCM().getMagasinStockage().getLibelle()
							.equals(depotDAO.magasinByCode(CODE_MAGASIN_ELNASS_TEA_PACKING).getLibelle())) {
						magasinElnasTeaPacking = depotDAO.magasinByCode(CODE_MAGASIN_ELNASS_TEA_PACKING);
						stockmpTmp = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
								magasinElnasTeaPacking.getId());

					}
				} else {
					stockmpTmp = stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),
							production.getMagasinStockage().getId());

				}

			}

			// quantiteReste=stockmp.getStock()-(quantiteConsomme+quantiteDechet);
			coutMP.setQuantConsomme(quantiteConsomme);
			coutMP.setQuantDechet(quantiteDechet);
			coutMP.setQuantiteOffre(quantiteOffre);
			// quantiteMP=quantiteConsomme+coutMP.getQuantChargeSupp();
			if (coutMP.getProdcutionCM().getService().equals(Constantes.PRODUCTION_SERVICE_OUI)
					&& coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
							.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
				if (coutMP.getMagasin() != null) {
					if (coutMP.getMagasin().getId() != magasinElnasTeaPackingTmp.getId()) {

						prixMP = (coutMP.getQuantConsomme()).multiply(coutMP.getPrixUnitaire());

					}

				}

			} else {
				if (coutMP.getProdcutionCM().getService().equals(Constantes.PRODUCTION_SERVICE_OUI)) {

					if (coutMP.getMagasin() != null) {
						if (coutMP.getMagasin().getId() != magasinElnasTeaPackingTmp.getId()) {

							prixMP = quantiteConsomme.multiply(prixUnitaire);

						}

					}

				} else {

					prixMP = quantiteConsomme.multiply(prixUnitaire);

				}

			}

			coutDechet = quantiteDechet.multiply(prixUnitaire);
			coutDechetFour = quantiteDechetFour.multiply(prixUnitaire);
			coutManquante = quantiteManquante.multiply(prixUnitaire);
			coutQuantiteOffre = quantiteOffre.multiply(prixUnitaire);

			coutTotalMP = coutTotalMP.add(prixMP);
			coutTotalDechet = coutTotalDechet.add(coutDechet).add(coutDechetFour).add(coutManquante);
			// quantiteReste=stockmp.getStock()-quantiteConsomme;

		}

	}
	
	
	public void ChercherBLNonFacture(Date date , Production productionTmp)
	{
		MessageBlNonFacturer="";
		Depot depot=depotDAO.findByCode(productionTmp.getCodeDepot());
		
		listFacturePFBLNonFacturer=facturepfdao.listeFacturePFNonFacturer(date, depot , Constantes.TYPE_BON_LIVRAISON);
		
		listFactureAutresVenteBLNonFacturer=factureAutresVenteDAO.listFactureAutresVenteNonFacturer(date, depot , Constantes.TYPE_BON_LIVRAISON);
		
		listFactureServiceTransportBLNonFacturer=factureServiceTransportDAO.listeFactureNonFacturer(date, depot , Constantes.TYPE_BON_LIVRAISON);
		
		listProductionServiceLancerBLNonFacturer=productionDAO.listeProductionServiceNonFacturer(Constantes.ETAT_OF_CREER, Constantes.ETAT_OF_LANCER, depot.getCode(), Constantes.PRODUCTION_SERVICE_OUI, date);
		
		if(listFacturePFBLNonFacturer.size()!=0)
		{
			MessageBlNonFacturer=MessageBlNonFacturer+"Il y a des Factures PF dont la date Facture est inférieur à "+date +" pas encore facturé"+"\n";
			
		}
		
		if(listFactureAutresVenteBLNonFacturer.size()!=0)
		{
			MessageBlNonFacturer=MessageBlNonFacturer+"Il y a des Factures Autres vente dont la date Facture est inférieur à "+date +" pas encore facturé"+"\n";

		}
		
		
		if(listFactureServiceTransportBLNonFacturer.size()!=0)
		{
			
			MessageBlNonFacturer=MessageBlNonFacturer+"Il y a des Factures Service Transport dont la date Facture est inférieur à "+date +" pas encore facturé"+"\n";

			
		}
		
		if(listProductionServiceLancerBLNonFacturer.size()!=0)
		{
			
			MessageBlNonFacturer=MessageBlNonFacturer+"Il y a des Productions Service Creer ou Lancer dont la date Production est inférieur à "+date +"  pas encore facturé  , Veuillez le terminer ou Bien Annulé"+"\n";

			
		}
		
		
	}
	
	
	
	
	

}
