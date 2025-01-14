package main1;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;

import org.hibernate.Session;

import util.Constantes;
import util.HibernateUtil;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ArticlesMPDAOImpl;
import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.ChargeFixeDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargeVariableDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.ClientPFDAOImpl;
import dao.daoImplManager.CompteClientDAOImpl;
import dao.daoImplManager.CompteMagasinierDAOImpl;
import dao.daoImplManager.CompteStockMPDAOImpl;
import dao.daoImplManager.CompteurAbsenceEmployeDAOImpl;
import dao.daoImplManager.CompteurEmployeProdDAOImpl;
import dao.daoImplManager.CompteurNumDossierDAOImpl;
import dao.daoImplManager.CompteurProductionDAOImpl;
import dao.daoImplManager.CompteurResponsableProdDAOImpl;
import dao.daoImplManager.CompteurTransferMPDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailChargeFixeDAOImpl;
import dao.daoImplManager.DetailChargeVariableDAOImpl;
import dao.daoImplManager.DetailCommandeDAOImpl;
import dao.daoImplManager.DetailCompteClientDAOImpl;
import dao.daoImplManager.DetailCoutProductionDAOImpl;
import dao.daoImplManager.DetailEstimationDAOImpl;
import dao.daoImplManager.DetailEstimationMPDAOImpl;
import dao.daoImplManager.DetailEstimationPromoDAOImpl;
import dao.daoImplManager.DetailFactureAchatDAOImpl;
import dao.daoImplManager.DetailFactureAchatMPDAOImpl;
import dao.daoImplManager.DetailFactureAvoirMPDAOImpl;
import dao.daoImplManager.DetailFactureAvoirPFDAOImpl;
import dao.daoImplManager.DetailFacturePFDAOImpl;
import dao.daoImplManager.DetailFactureServiceProductionDAOImpl;
import dao.daoImplManager.DetailFactureVenteMPDAOImpl;
import dao.daoImplManager.DetailMouvementStockDAOImpl;
import dao.daoImplManager.DetailPrixArticleDAOImpl;
import dao.daoImplManager.DetailProdGenDAOImpl;
import dao.daoImplManager.DetailProductionDAOImpl;
import dao.daoImplManager.DetailResponsableProdDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.EmployeBloqueDAOImpl;
import dao.daoImplManager.EmployeDAOImpl;
import dao.daoImplManager.EquipeDAOImpl;
import dao.daoImplManager.FactureAchatDAOImpl;
import dao.daoImplManager.FactureAchatMPDAOImpl;
import dao.daoImplManager.FactureAvoirMPDAOImpl;
import dao.daoImplManager.FactureAvoirPFDAOImpl;
import dao.daoImplManager.FacturePFDAOImpl;
import dao.daoImplManager.FactureProductionDAOImpl;
import dao.daoImplManager.FactureServiceProductionDAOImpl;
import dao.daoImplManager.FactureVenteMPDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.FicheEmployeDAOImpl;
import dao.daoImplManager.FicheEmployeGlobaleDAOImpl;
import dao.daoImplManager.FournisseurDAOImpl;
import dao.daoImplManager.FraisDepotDAOImpl;
import dao.daoImplManager.GrammageBoxDAOImpl;
import dao.daoImplManager.GrammageCartonDAOImpl;
import dao.daoImplManager.HabilitationDAOImpl;
import dao.daoImplManager.JourneeDAOImpl;
import dao.daoImplManager.ListTvaDAOImpl;
import dao.daoImplManager.MachineDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.MenuDAOImpl;
import dao.daoImplManager.MouvementStockGlobalDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.PrixClientMPDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.ProductionMPDAOImpl;
import dao.daoImplManager.SequenceurDAOImpl;
import dao.daoImplManager.SousFamilleArticlesPFDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.SubCategorieMPAOImpl;
//import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
import dao.daoImplManager.TypeEquipeDAOImpl;
import dao.daoImplManager.TypeResEmployeDAOImpl;
import dao.daoImplManager.TypeVenteDAOImpl;
import dao.daoImplManager.UtilisateurDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ArticlesMPDAO;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.ChargeFixeDAO;
import dao.daoManager.ChargeProductionDAO;
import dao.daoManager.ChargeVariableDAO;
import dao.daoManager.ChargesDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.ClientPFDAO;
import dao.daoManager.CompteClientDAO;
import dao.daoManager.CompteMagasinierDAO;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.CompteurAbsenceEmployeDAO;
import dao.daoManager.CompteurEmployeProdDAO;
import dao.daoManager.CompteurNumDossierDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.CompteurResponsableProdDAO;
import dao.daoManager.CompteurTransferMPDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailChargeFixeDAO;
import dao.daoManager.DetailChargeVariableDAO;
import dao.daoManager.DetailCommandeDAO;
import dao.daoManager.DetailCompteClientDAO;
import dao.daoManager.DetailCoutProductionDAO;
import dao.daoManager.DetailEstimationDAO;
import dao.daoManager.DetailEstimationMPDAO;
import dao.daoManager.DetailEstimationPromoDAO;
import dao.daoManager.DetailFactureAchatMPDAO;
import dao.daoManager.DetailFactureAvoirMPDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailFactureServiceProductionDAO;
import dao.daoManager.DetailMouvementStockDAO;
import dao.daoManager.DetailProdGenDAO;
import dao.daoManager.DetailProductionDAO;
import dao.daoManager.DetailResponsableProdDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.EmployeBloqueDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.EquipeDAO;
import dao.daoManager.FactureAchatDAO;
import dao.daoManager.FactureAchatMPDAO;
import dao.daoManager.FactureAvoirMPDAO;
import dao.daoManager.FactureAvoirPFDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureProductionDAO;
import dao.daoManager.FactureServiceProductionDAO;
import dao.daoManager.FactureVenteMPDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.FicheEmployeDAO;
import dao.daoManager.FicheEmployeGlobaleDAO;
import dao.daoManager.FournisseurDAO;
import dao.daoManager.FraisDepotDAO;
import dao.daoManager.HabilitationDAO;
import dao.daoManager.JourneeDAO;
import dao.daoManager.ListTvaDAO;
import dao.daoManager.MachineDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MenuDAO;
import dao.daoManager.MouvementStockGlobalDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.PrixClientMPDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.ProductionMPDAO;
import dao.daoManager.SequenceurDAO;
import dao.daoManager.SousFamilleArticlesPFDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.daoManager.TransferStockPFDAO;
import dao.daoManager.TypeEquipeDAO;
import dao.daoManager.TypeResEmployeDAO;
import dao.daoManager.TypeVenteDAO;
import dao.daoManager.UtilisateurDAO;
import dao.entity.Depot;
import dao.entity.DetailCoutProduction;
import dao.entity.DetailFacturePF;
import dao.entity.DetailFactureServiceProduction;
import dao.entity.DetailMouvementStock;
import dao.entity.DetailProduction;
import dao.entity.FactureAchatMP;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.MouvementStockGlobal;
import dao.entity.StockMP;
import dao.entity.Utilisateur;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class ProdLauncher extends javax.swing.JFrame {
	
	public static MatierePremiereDAO dao;
	public static CategorieMpDAO daoCategorie;
	public static FournisseurDAO fournisseurDAO;
	public static MachineDAO machineDAO;
	public static ArticlesDAO articlesDAO ;
	public static EquipeDAO equipeDAO ;
	public static DetailCommandeDAO detailCommandeDAO;
	public static ProductionDAO productionDAO;
	public static UtilisateurDAO utilisaeurDAO;
	public static StockMPDAO stockMPDAO;
	public static DepotDAO depotDAO;
	public static TransferStockMPDAO transferStockMPDAO;
	public static TypeResEmployeDAO typeResEmployeDAO;
	public static EmployeDAO employeDAO;
	public static TypeEquipeDAO typeEquipeDAO;
	public static DetailProdGenDAO detailProdGenDAO;
	public static CompteurProductionDAO compteurProductionDAO;
	public static StockPFDAO stockPFDAO;
	public static DetailTransferProduitFiniDAO detailTransferProduitFiniDAO;
	public static EmployeBloqueDAO employeBloqueDAO;
	public static ParametreDAO parametreDAO;
	public static CategorieMpDAO categorieMpDAO;
	public static FicheEmployeDAO ficheEmployeDAO;
	public static SequenceurDAO sequenceurDAO;
	public static TransferStockPFDAO transferStockPFDAO;
	public static CompteurNumDossierDAO compteurNumDossierDAO;
	public static MenuDAO  menuDAO;
	public static HabilitationDAO habilitationDAO;
	public static CompteurResponsableProdDAO compteurResponsableProdDAO;
	public static CompteurTransferMPDAO compteurTransferMPDAO;
	public static CompteurEmployeProdDAO compteurEmployeProdDAO;
	public static ClientDAO clientDAO;
	public static FactureProductionDAO factureProductionDAO;
	public static PrixClientMPDAO prixClientMPDAO;
	public static CompteStockMPDAO compteStockMPDAO;
	public static CompteurAbsenceEmployeDAO compteurAbsenceEmployeDAO;
	public static DetailEstimationPromoDAO detailestimationpromodao;
	public static FicheEmployeGlobaleDAO ficheEmployeGlobaledao;
	public static ChargeProductionDAO chargeproductiondao;
	public static DetailChargeFixeDAO detailchargefixdao;
	public static DetailChargeVariableDAO detailchargevariabledao;
	public static DetailEstimationMPDAO detailestimationmpdao;
	public static DetailEstimationDAO detailestimationdao;
	public static ChargesDAO chargedao;
	public static SubCategorieMPDAO subcategoriempdao;
	public static CompteMagasinierDAO comptemagasinierdao;
	public static FraisDepotDAO fraisdepotdao;
	public static ChargeFixeDAO chargefixedao;
	public static ChargeVariableDAO chargevariabledao;
	public static DetailCoutProductionDAO detailcoutproductiondao;
	public static DetailProductionDAO detailproductiondao;
	public static DetailResponsableProdDAO detailResponsabledao;
	public static FamilleArticlesPFDAO familleArticlePFDAO;
	public static SousFamilleArticlesPFDAO  sousFamilleArticlePFDAO;
	public static ClientPFDAO  clientPFDAO;
	public static TypeVenteDAO typeVenteDAO;
	public static FacturePFDAO facturePFDAO;
	public static DetailFacturePFDAO detailFacturePfDAO;
	public static ClientPFDAO clientPfDAO;
	public static CompteClientDAO compteClientDAO;
	public static DetailCompteClientDAO detailCompteClientDAO;
	public static MouvementStockGlobalDAO mouvementStockGlobaleDAO;
	public static DetailMouvementStockDAO detailMouvementStockDAO;
	public static JourneeDAO journeeDAO;
	public static FactureAchatDAO factureAchatDAO;
	public static dao.daoManager.DetailFactureAchatDAO DetailFactureAchatDAO;
	//public static FournisseurDAO fournisseurAchatDAO;
	//public static DetailTransferMPDAO detailTransferMPDAO;
	private ImageIcon imgBest;
	public static FactureServiceProductionDAO factureServiceProductiondao;
	public static DetailFactureServiceProductionDAO detailfactureServiceProductiondao;
	public static ProductionMPDAO productiompDAO;
	public static ArticlesMPDAO articlempDAO;
	public static FactureAchatMPDAO factureAchatMPDAO;
	public static DetailFactureAchatMPDAO DetailfactureAchatMPDAO;
	public static FactureAvoirMPDAO factureAvoirMPDAO;
	public static DetailFactureAvoirMPDAO DetailfactureAvoirMPDAO;
	public static FactureAvoirPFDAO factureAvoirPFDAO;
	public static dao.daoManager.DetailFactureAvoirPFDAO DetailFactureAvoirPFDAO;
	public static dao.daoManager.DetailPrixArticleDAO DetailPrixArticleDAO;
	public static  FactureVenteMPDAO FactureVenteMPDAO;
	public static dao.daoManager.GrammageBoxDAO GrammageBoxDAO;
	public static dao.daoManager.GrammageCartonDAO GrammageCartonDAO;
	public static  dao.daoManager.DetailFactureVenteMPDAO DetailFactureVenteMPDAO;
	public static  ListTvaDAO ListTvaDAO;
	
	
	public static  Session session=HibernateUtil.openSession();
	
	
	/**
	 * Creates new form 
	 */
	public ProdLauncher() {
		// ce code affiche les looks install�s, leurs noms et leurs classes
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			System.out.println("Le look and feel de l'OS est : "
					+ UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println("Probl�me dans l'installation L&F syst�me.");
		}
		initComponents();
		
		setLocationRelativeTo(null);
		new Thread(new thread1()).start();
		new Thread(new threadLoadSession()).start();
	}

	public void moveToTheNewWindow() {
		
		this.dispose();
		//authentification authentification = new authentification();
		AuthentificationView authentification = new AuthentificationView();
		authentification.setVisible(true);
		authentification.setLocationRelativeTo(null);
		}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		dinningRommProgressBar = new javax.swing.JProgressBar();
		labelLoad = new javax.swing.JLabel();
		labelChangeLoader = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Bienvenue dans Application Prod Compta 2023 V1.0");
		setResizable(false);
		jPanel1.setBackground(new java.awt.Color(255, 255, 255));
		 imgBest= new ImageIcon(this.getClass().getResource("/img/best.png"));
		//jLabel1.setIcon(new ImageIcon(this.getClass().getResource("/img/best.png"))); // NOI18N
		 ///********************///
		jLabel1.setIcon(imgBest);
		dinningRommProgressBar.setForeground(new java.awt.Color(204, 0, 0));
		dinningRommProgressBar.setOpaque(true);
		dinningRommProgressBar.setStringPainted(true);

		labelLoad.setText("Chargement");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1Layout.setHorizontalGroup(
			jPanel1Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup()
							.addGap(208)
							.addComponent(labelLoad, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(labelChangeLoader, GroupLayout.PREFERRED_SIZE, 326, GroupLayout.PREFERRED_SIZE))
						.addGroup(jPanel1Layout.createSequentialGroup()
							.addGap(175)
							.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(dinningRommProgressBar, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
					.addContainerGap(118, Short.MAX_VALUE))
		);
		jPanel1Layout.setVerticalGroup(
			jPanel1Layout.createParallelGroup(Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
					.addContainerGap()
					.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 165, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(dinningRommProgressBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
						.addComponent(labelLoad)
						.addComponent(labelChangeLoader))
					.addContainerGap(94, Short.MAX_VALUE))
		);
		jPanel1.setLayout(jPanel1Layout);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.PREFERRED_SIZE));
		layout.setVerticalGroup(layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
	}// </editor-fold>

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				File dir = new File ("C:\\Edition\\EquipeProducion");
				File dir1 = new File ("C:\\Edition\\EquipeGenerique");
				File dir2 = new File ("C:\\Edition\\OrdreFabrication");
				File dir3 = new File ("C:\\Edition\\BonSortieMatierePremiere");
				File dir4 = new File ("C:\\Edition\\BonSortieChargeSupp");
				File dir5 = new File ("C:\\Edition\\FicheDechetMatierePremiere");
				File dir6 = new File ("C:\\Edition\\FicheEmploye");
				File dir7 = new File ("C:\\Edition\\BonSortieMPDeplace");
				File dir8 = new File ("C:\\log");
				
				dir.mkdirs();
				dir1.mkdirs();
				dir2.mkdirs();
				dir3.mkdirs();
				dir4.mkdirs();
				dir5.mkdirs();
				dir6.mkdirs();
				dir7.mkdirs();
				dir8.mkdirs();
			
				dinningRommProgressBar = new JProgressBar(0, 100);
				new ProdLauncher().setVisible(true);
			}
		});
	}

	class thread1 implements Runnable {
		protected volatile boolean running = true;

		public void run() {

			while (running) {
				for (int i = 0; i <= 100; i++) { // Progressively increment
													// variable i
					dinningRommProgressBar.setValue(i); // Set value
					dinningRommProgressBar.repaint(); // Refresh graphics
					
					try {
							Thread.sleep(40);
							
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (dinningRommProgressBar.getValue() == 0)  {
						labelChangeLoader
								.setText("modules Graphiques Production...");
					}
					if (dinningRommProgressBar.getValue() == 20){
						labelChangeLoader
								.setText("d�pendances m�tiers Production...");
					}
					if (dinningRommProgressBar.getValue() == 40){
						labelChangeLoader.setText("module de s�curit�...");
						}
					if (dinningRommProgressBar.getValue() == 60){
						labelChangeLoader
								.setText("modules simulation et reporting...");
					}
					if (dinningRommProgressBar.getValue() == 80){
						labelChangeLoader
								.setText("d�pendances base de donn�es...");
					}
					if (dinningRommProgressBar.getValue() == 99) {
						machineDAO=new MachineDAOImpl();
						dao= new MatierePremierDAOImpl();
						detailCommandeDAO=new DetailCommandeDAOImpl();
						productionDAO=new ProductionDAOImpl();
						utilisaeurDAO=new UtilisateurDAOImpl();
						equipeDAO =new EquipeDAOImpl();
						daoCategorie= new  CategorieMpDAOImpl();
						fournisseurDAO=new FournisseurDAOImpl();
						articlesDAO = new ArticlesDAOImpl();
						stockMPDAO=new StockMPDAOImpl();
						depotDAO=new DepotDAOImpl();
						//transferStockMPDAO=new TransferStockMPDAOImpl();
						typeResEmployeDAO=new TypeResEmployeDAOImpl();
						employeDAO=new EmployeDAOImpl();
						typeEquipeDAO=new TypeEquipeDAOImpl();
						detailProdGenDAO=new DetailProdGenDAOImpl();
						compteurProductionDAO=new CompteurProductionDAOImpl();
						stockPFDAO=new StockPFDAOImpl();
						detailTransferProduitFiniDAO=new DetailTransferProduitFiniDAOImpl();
						employeBloqueDAO= new EmployeBloqueDAOImpl();
						parametreDAO=new ParametreDAOImpl();
						categorieMpDAO=new CategorieMpDAOImpl();
						ficheEmployeDAO=new FicheEmployeDAOImpl();
						sequenceurDAO=new SequenceurDAOImpl();
						transferStockPFDAO=new TransferStockPFDAOImpl();
						compteurNumDossierDAO=new CompteurNumDossierDAOImpl();
						menuDAO=new MenuDAOImpl();
						habilitationDAO = new HabilitationDAOImpl();
						compteurResponsableProdDAO=new CompteurResponsableProdDAOImpl();
						compteurTransferMPDAO=new CompteurTransferMPDAOImpl();
						compteurEmployeProdDAO=new CompteurEmployeProdDAOImpl();
						clientDAO=new ClientDAOImpl();
						factureProductionDAO=new FactureProductionDAOImpl();
						prixClientMPDAO=new PrixClientMPDAOImpl();
						compteStockMPDAO=new CompteStockMPDAOImpl();
						compteurAbsenceEmployeDAO=new CompteurAbsenceEmployeDAOImpl();
						detailestimationpromodao=new DetailEstimationPromoDAOImpl();
						productiompDAO=new ProductionMPDAOImpl();
						articlempDAO=new ArticlesMPDAOImpl();
						ficheEmployeGlobaledao=new FicheEmployeGlobaleDAOImpl();
						chargeproductiondao=new ChargeProductionDAOImpl();
						detailchargefixdao=new DetailChargeFixeDAOImpl();
						detailchargevariabledao=new DetailChargeVariableDAOImpl();
						detailestimationmpdao=new DetailEstimationMPDAOImpl();
						detailestimationdao=new DetailEstimationDAOImpl();
						chargedao=new ChargesDAOImpl();
						subcategoriempdao=new SubCategorieMPAOImpl();
						comptemagasinierdao=new CompteMagasinierDAOImpl();
						fraisdepotdao=new FraisDepotDAOImpl();
						chargefixedao=new ChargeFixeDAOImpl();
						chargevariabledao=new ChargeVariableDAOImpl();
						detailcoutproductiondao=new DetailCoutProductionDAOImpl();
						detailproductiondao=new DetailProductionDAOImpl();
						detailResponsabledao=new DetailResponsableProdDAOImpl();
						familleArticlePFDAO=new FamilleArticlesPFDAOImpl();
						sousFamilleArticlePFDAO=new SousFamilleArticlesPFDAOImpl();
						clientPFDAO=new ClientPFDAOImpl();
						typeVenteDAO=new TypeVenteDAOImpl();
						facturePFDAO =new FacturePFDAOImpl();
						detailFacturePfDAO=new DetailFacturePFDAOImpl();
						clientPfDAO=new ClientPFDAOImpl();
						compteClientDAO=new CompteClientDAOImpl();
						detailCompteClientDAO=new DetailCompteClientDAOImpl();
						mouvementStockGlobaleDAO=new MouvementStockGlobalDAOImpl();
						detailMouvementStockDAO=new DetailMouvementStockDAOImpl();
						journeeDAO=new JourneeDAOImpl();
						factureAchatDAO=new FactureAchatDAOImpl();
						DetailFactureAchatDAO=new DetailFactureAchatDAOImpl();
						//fournisseurAchatDAO=new FournisseurDAOImpl();
						//detailTransferMPDAO=new DetailTransferMPDAOImpl();
						factureAchatMPDAO=new FactureAchatMPDAOImpl();
						DetailfactureAchatMPDAO=new DetailFactureAchatMPDAOImpl();
						factureAvoirMPDAO=new FactureAvoirMPDAOImpl();
						DetailfactureAvoirMPDAO=new DetailFactureAvoirMPDAOImpl();
						factureAvoirPFDAO=new FactureAvoirPFDAOImpl();
						DetailFactureAvoirPFDAO=new DetailFactureAvoirPFDAOImpl();
						DetailPrixArticleDAO=new DetailPrixArticleDAOImpl();
						FactureVenteMPDAO=new FactureVenteMPDAOImpl();
						DetailFactureVenteMPDAO=new DetailFactureVenteMPDAOImpl();
						factureServiceProductiondao=new FactureServiceProductionDAOImpl();
						detailfactureServiceProductiondao=new DetailFactureServiceProductionDAOImpl();
						GrammageBoxDAO=new GrammageBoxDAOImpl();
						GrammageCartonDAO=new GrammageCartonDAOImpl();
						ListTvaDAO=new ListTvaDAOImpl();
						Utilisateur utilisateur =utilisaeurDAO.findUtilisateurByLoginMotPasse(Constantes.ADMIN_CONNEXION, Constantes.ADMIN_CONNEXION);
						
						if(utilisateur==null){
							utilisateur=new Utilisateur();
							utilisateur.setCodeDepot(Constantes.CODE_DEPOT_SIEGE);
							utilisateur.setDateCreation(new Date());
							utilisateur.setLogin(Constantes.ADMIN_CONNEXION);
							utilisateur.setNom(Constantes.ADMIN_CONNEXION);
							utilisateur.setPassword(Constantes.ADMIN_CONNEXION);
							utilisaeurDAO.add(utilisateur);
						}
						
					
						
						
						
						
						running = false;
						
						moveToTheNewWindow();
					}

				}
			}
		}
	}

	class threadLoadSession implements Runnable {
		//@Override
		public void run() {
			// TODO Auto-generated method stub
		//	Session session=HibernateUtil.openSession();
		}
	}

	// Variables declaration - do not modify
	private javax.swing.JLabel jLabel1;
	private javax.swing.JPanel jPanel1;
	private static javax.swing.JProgressBar dinningRommProgressBar;
	private javax.swing.JLabel labelChangeLoader;
	private javax.swing.JLabel labelLoad;
	// End of variables declaration
}
