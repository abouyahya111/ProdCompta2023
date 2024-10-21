package main1;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.plaf.basic.BasicMenuBarUI;

import matierePremiere.AjoutMatierePremiere;
import matierePremiere.AjouterMPCompose;
import matierePremiere.ChercherMatierePremiere;
import matierePremiere.ListeMatierePremiere;
import matierePremiere.ModifierMatierePremierCompose;
import presentation.article.AjoutGrammageArticle;
import presentation.article.AjouterEstimationThe;
import presentation.article.CreerEstimationArticle;
import presentation.article.ModifierEstimationArticle;
import presentation.client.AjoutClient;
import presentation.client.ChercherClient;
import presentation.depot.AjoutDepot;
import presentation.depot.ChercherDepot;
import presentation.parametre.ActiverOffre;
import presentation.parametre.AjoutCharges;
import presentation.parametre.AjoutEmployeBloque;
import presentation.parametre.AjoutOffre;
import presentation.parametre.AjoutParametre;
import presentation.parametre.AjoutUtilisateur;
import presentation.parametre.AjouterPrixClientMP;
import presentation.parametre.GererAuthUtilisateur;
import presentation.stastiques.CoutDechetProduction;
import presentation.stockMP.AfficherDetailTransferMP;
import presentation.stockMP.AfficherStockMP;
import presentation.stockMP.AjoutChargeDepot;
import presentation.stockMP.AjoutInitialMP;
import presentation.stockMP.AjoutManqueDepot;
import presentation.stockMP.AjouterStockMP;
import presentation.stockMP.ConsulterCompteMagasinier;
import presentation.stockMP.ConsulterEtatCoutMoyen;
import presentation.stockMP.ConsulterEtatDeMargeStock;
import presentation.stockMP.ConsulterEtatDeValeurStock;
import presentation.stockMP.ConsulterEtatEmballage;
import presentation.stockMP.ConsulterEtatQuantiteMPTransforme;
import presentation.stockMP.ConsulterEtatQuantiteMPTransformeParSousFamille;
import presentation.stockMP.ConsulterEtatStock;
import presentation.stockMP.ConsulterEtatStockInitialMP;
import presentation.stockMP.ConsulterFactureVenteMP;
import presentation.stockMP.ConsulterMouvementStock;
import presentation.stockMP.EntrerStockMP;
import presentation.stockMP.EtatPrixMoyenStockInitialMPParMP;
import presentation.stockMP.ModifierInitialMP;
import presentation.stockMP.SortirStockMP;
import presentation.stockMP.TransfererMPProduitFini;
import presentation.stockMP.TransfererSockInitialMPVersAutreMP;
import presentation.stockMP.TransfererStockMP;
import presentation.stockPF.AfficherStockPF;
import presentation.stockPF.AjoutInitialProduitFinit;
import presentation.stockPF.ConsulterEtatAvoirFinance;
import presentation.stockPF.ConsulterEtatEstimationStockPF;
import presentation.stockPF.ConsulterEtatInitialStockPF;
import presentation.stockPF.ConsulterEtatMConsommablePF;
import presentation.stockPF.ConsulterEtatMargeStockPF;
import presentation.stockPF.ConsulterEtatStockPF;
import presentation.stockPF.ConsulterEtatStockPFComparaisonAvoir;
import presentation.stockPF.ConsulterEtatValeurSystem;
import presentation.stockPF.ConsulterMouvementStockPF;
import presentation.stockPF.ConsulterTransfererPFProduitFini;
import presentation.stockPF.EtatPrixMoyenInitialArticle;
import presentation.stockPF.ModifierInitialProduitFinit;
import presentation.stockPF.MoyenStockPF;
import presentation.stockPF.SortirStockPF;
import presentation.stockPF.TransfererPFProduitFini;
import util.Constantes;
import util.Utils;
import Equipe.AfficherFicheEmploye;
import Equipe.AfficherFicheGlobaleEmploye;
import Equipe.AjoutEmploye;
import Equipe.AjoutEquipe;
import Equipe.ChercherEmploye;
import Equipe.ChercherEquipe;
import FactureAchat.AjoutFactureAchat;
import FactureAchat.AjoutFactureAvoirPF;
import FactureAchat.AjoutFactureAvoirPFComparaison;
import FactureAchat.AjoutRegularisationFactureAchat;
import FactureAchat.AjoutReportMConsommable;
import FactureAchat.AjoutReportRemise;
import FactureAchat.ConsulterFactureAchat;
import FactureAchat.ConsulterFactureAvoirPF;
import FactureAchat.ConsulterFactureAvoirPFComparaison;
import FactureAchat.ConsulterFactureRegularisationAchat;
import FactureAchat.EtatFactureAchatArticles;
import FactureAchat.EtatFactureAchatOulmes;
import FactureAchat.EtatFactureAvoirPF;
import FactureAchat.EtatPrixMoyenAchatArticle;
import FactureAchat.EtatPrixMoyenAvoirArticle;
import FactureAchat.ImporterFcatureAchatPFExcel;
import FactureAchatMP.AjoutFactureAchatMP;
import FactureAchatMP.AjoutFactureAvoirMP;
import FactureAchatMP.ConsulterEtatFactureAchatMP;
import FactureAchatMP.ConsulterFactureAchatMP;
import FactureAchatMP.ConsulterFactureAvoirMP;
import FactureAchatMP.ImporterFcatureAchatMPExcel;
import FactureAchatMP.MoyenStockMP;
import FactureAchatMP.MoyenStockMPParSousFamille;
import FactureAchatMP.PrixMoyenDesAchatsMP;
import FactureAutresVente.AjoutFactureAutresVente;
import FactureAutresVente.ConsulterFactureAutresVente;
import FactureAutresVente.EtatBLAutresVentes;
import FactureAvoirClientPF.AjoutFactureAvoirClientPF;
import FactureAvoirClientPF.ConsulterFactureAvoirClientPF;
import FactureAvoirClientPF.HistoriqueDeTransfertFactureAvoirClientPFEnBL;
import FactureAvoirClientPF.TransfertFactureAvoirClientPFEnBL;
import FacturePF.AjoutFacturePF;
import FacturePF.AjoutHistoriqueVenteVendeurPF;
import FacturePF.ConsulterEtatHistoriqueVenteVendeurPF;
import FacturePF.ConsulterEtatVentePF;
import FacturePF.ConsulterFacturePF;
import FacturePF.ConsulterHistoriqueVenteVendeurPF;
import FacturePF.EtatBLPF;
import FacturePF.EtatChiffreAffaireAvecTimbre;
import FacturePF.EtatFactureArticles;
import FacturePF.EtatFactureAvecOuSansICE;
import FacturePF.EtatFacturePF;
import FacturePF.EtatFacturePFParArticle;

import FacturePF.EtatPrixMoyenArticle;
import FacturePF.EtatVenteClient;
import FacturePF.EtatVenteFamille;
import FacturePF.EtatVenteFamilleParFacture;
import FacturePF.HistoriqueDeTransfertFactureEnBL;
import FacturePF.ImporterFacturePFExcel;
import FacturePF.ImporterModifications;
import FacturePF.RemplacerArticleParArticleFacturePF;
import FacturePF.TransfertFactureEnBL;
import FactureServiceProduction.AjouterFactureServiceProduction;
import FactureServiceProduction.EtatFacturesService;
import FactureServiceProduction.EtatFacturesServiceDetaile;
import FactureServiceProduction.EtatFacturesServiceDetaileAvecSousTautaux;
import FactureServiceProduction.EtatFacturesServiceParOF;
//import FactureServiceProduction.ConsulterFactureServiceProduction;
import FactureServiceProduction.ImprimerFactureServiceProduction;
import FactureServiceTransport.AjoutFactureServiceTransport;
import FactureServiceTransport.ConsulterFactureServiceTransport;
import Journee.AjoutJournee;
import Production.AfficherCompteStockMP;
import Production.AjoutChargeFixeProd;
import Production.AjoutChargeVariableProd;
import Production.AnnulerLesOF;
import Production.BonProduction;
import Production.BonProductionParAnnee;
import Production.BonProductionParPourcentageEnVrac;
import Production.ConsulterDeMargeAvantProduction;
import Production.ConsulterEtatBoxEtCartonConsommerParPF;
import Production.ConsulterEtatMPConsommerParOF;
import Production.CoutProduction;
import Production.CreerOrdreFabrication;
import Production.DetailOF;
import Production.EtatTonnageProductionParMoisParMagasin;
import Production.ImporterProductionExcel;
import Production.LancerOrdreFabrication;
import Production.ModifierCoutProductionServiceParNumFacture;
import Production.ModifierCoutProductionserviceParProduction;
import Production.RegularisationPrixPF;
import Production.StatistiqueMPUtiliserLorsDeLaProductionPF;
import Production.TerminerOrdreFabrication;
import ProductionCarton.CreerOrdreFabricationMP;
import ProductionCarton.LancerOrdreFabricationMP;
import ProductionCarton.TerminerOrdreFabricationMP;
import Referentiel.AjoutArticlesClientPF;
import Referentiel.AjoutCategorie;
import Referentiel.AjoutClientPF;
import Referentiel.AjoutCompte;
import Referentiel.AjoutEnTete;
import Referentiel.AjoutFamilleArticlePF;
import Referentiel.AjoutSousFamilleArticlePF;
import Referentiel.AjoutSousFamilleEnVrac;
import Referentiel.AjoutTypeBL;
import Referentiel.AjoutTypeVente;
import Referentiel.AjoutVille;
import Referentiel.AjouterAdressesClientPF;
import Referentiel.ChercherClientPF;
import Referentiel.ConsulterArticlesClientPF;
import Referentiel.ConsulterCompte;
import Referentiel.ConsulterEtatChiffeAffaireClient;
import Referentiel.ConsulterListeClientPF;
import Referentiel.ConsulterMargeDeStockArticlePF;
import Referentiel.ConsulterMargeDeStockSousFamille;
import Referentiel.ConsulterValeurisationEtatStockParArticles;
import Referentiel.ConsulterValeurisationEtatStockParSousFamille;
import Referentiel.EtatAchatFamilleArticle;
import Referentiel.EtatMoyenValeurisationProduction;
import Referentiel.EtatMoyenValeurisationProductionParSousFamille;
import Referentiel.EtatPrixMoyenAvoirFamilleArticle;
import Referentiel.EtatPrixMoyenStockInitialMP;
import Referentiel.EtatPrixMoyenStockInitialPF;
import Referentiel.EtatServiceFamilleArticle;
import Referentiel.EtatVenteArticle;
import Referentiel.EtatVenteFamilleArticle;
import Reglement.AjoutReglement;
import Reglement.ImprimerFactureAutresVente;
import Reglement.ImprimerFactureMP;
import Reglement.ImprimerFacturePF;
import Reglement.ImprimerFactureServiceTransport;
import UniteFabrication.AjoutMachine;
import UniteFabrication.ChercherMachine;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.FournisseurDAOImpl;
import dao.daoImplManager.HabilitationDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.FournisseurDAO;
import dao.daoManager.HabilitationDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.StockMPDAO;
import dao.entity.Articles;
import dao.entity.Client;
import dao.entity.CompteMagasinier;
import dao.entity.Fournisseur;
import dao.entity.Habilitation;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Production;
import dao.entity.ProductionMP;
import dao.entity.Utilisateur;
import fournisseur.AjouterFournisseur;
import fournisseur.AjouterFournisseurOld;
import javax.swing.ScrollPaneConstants;


public class Main extends JFrame implements Constantes{
    
        public static JPanel mainPane;
        public static JLayeredPane contentPane;
        public static JLayeredPane precedentContentPane;
        public static JScrollPane generalScrollPane;
        public static JPanel titlePanel;
        public static JLabel title;
        public static JLabel bottomMessage;
        public static JPanel messagePanel; 
        
        public static JPanel userLoginPanel;
        public static JLabel  loginLabel;
        
        public static JPanel msgPanel;
        public static JLabel  messageLabel;
        
        private JButton cancelButto;
        private JButton btnStatistique;
        private JButton btnDeconnexion;
        
        private JButton btnGenerer;
        
   
       
        public JScrollPane listScrollPane;
        public static Color color;
        private  MatierePremiereDAO dao;
        private DepotDAO depotDAO;
        private StockMPDAO stockMPDAO;
        private ArticlesDAO artilesDAO;
    	private HabilitationDAO habilitationDAO;
    	private Utilisateur utilisateur =AuthentificationView.utilisateur;
    	private ClientDAO clientDAO;
    	private FournisseurDAO fournisseurDAO;
    	
    	/*Menu */
    	private JMenu depotMenu;
    	private JMenu fournisseurMenu;
    	private JMenu MatierePremierMenu;
    	private JMenu articleMenu;
    	private  JMenu stockMPMenu;
    	private JMenu uniteFabMenu;
    	private JMenu equipeFabMenu ;
    	private JMenu ordreFabMenu;
    	private JMenu prodCartMenu;
    	private JMenu stockPFMenu;
    	private JMenu parametreMenu;
    	
    	private  JMenu ReferentielMenu;
    	private JMenu FacturePFMenu;
    	private JMenu ReglementMenu;
    	
    	private JMenu FactureAchatMenu;
    	private JMenu FactureAchatMPMenu;
    	private JMenu FactureServiceProductionMenu;
    	private JMenu EtatsDeVerificationMenu;
    	private JMenu EtatsMenu;
    	private JMenu AdministrationMenu;
    	private JTextPane txtLabel = new JTextPane();
    	
        public Main(){
        	 
        	dao=new MatierePremierDAOImpl();
        	depotDAO=new DepotDAOImpl();
        	stockMPDAO=new StockMPDAOImpl();
        	artilesDAO=new ArticlesDAOImpl();
        	habilitationDAO=new HabilitationDAOImpl();
        	clientDAO=new ClientDAOImpl();
        	fournisseurDAO=new FournisseurDAOImpl();
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setTitle("Bienvenue dans Application Prod Compta V1.0 2023");
            final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
            setBounds(0, 0, (int) dim.getWidth(), (int) dim.getHeight()-10);
            setExtendedState(JFrame.MAXIMIZED_BOTH);
           
            
           mainPane=new JPanel();
           // mainPane   = new MainEntityFlatMenu();
           // mainPane.setBackground(new Color(204, 204, 255));
            mainPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
            setContentPane(mainPane);
            mainPane.setLayout(null);
            contentPane  = new JLayeredPane();
            
            titlePanel = new JPanel();
            titlePanel.setBounds(0, 0, (int) dim.getWidth(), 33);
            titlePanel.setBackground(Color.white);
            titlePanel.setBorder(new SoftBevelBorder(0, null, null, null, null));
            mainPane.add(titlePanel);
            title = new JLabel("Accueil Bienvenue " + AuthentificationView.login);
            title.setForeground(new Color(255, 140, 0));
            title.setFont(new Font("New Times", 1, 16));
            //btnDeconnexion=new ImageIcon(this.getClass().getResource("/img/deconnect.png"))
           btnDeconnexion = new JButton(new ImageIcon(this.getClass().getResource("/img/deconnect.png")));
            btnDeconnexion.setBounds((int)dim.getWidth() - 40, 0, 35, 32);
            btnDeconnexion.setToolTipText("Déconnecter");
           
            cancelButto = new JButton(new ImageIcon(this.getClass().getResource("/img/Setting.png")));
            cancelButto.setBounds((int)dim.getWidth() - 80, 0, 35, 32);
            cancelButto.setToolTipText("Paramètre");
            
            btnStatistique = new JButton(new ImageIcon(this.getClass().getResource("/img/statistik.png")));
            btnStatistique.setBounds((int)dim.getWidth() - 120, 0, 35, 32);
            btnStatistique.setToolTipText("Statistiques");
            
            btnGenerer = new JButton(new ImageIcon(this.getClass().getResource("/img/generer.png")));
            btnGenerer.setBounds((int)dim.getWidth() - 160, 0, 35, 32);
            btnGenerer.setToolTipText("Générer Stock");
            
            
          
            titlePanel.add(this.btnDeconnexion);
            titlePanel.add(this.cancelButto);
            titlePanel.add(this.btnStatistique);
            titlePanel.add(this.btnGenerer);
            
            GroupLayout gl_titlePanel = new GroupLayout(titlePanel);
            gl_titlePanel.setHorizontalGroup(
            	gl_titlePanel.createParallelGroup(Alignment.LEADING)
            		.addGroup(gl_titlePanel.createSequentialGroup()
            			.addComponent(title, GroupLayout.PREFERRED_SIZE, 837, GroupLayout.PREFERRED_SIZE)
            			.addContainerGap(1077, Short.MAX_VALUE))
            );
            gl_titlePanel.setVerticalGroup(
            	gl_titlePanel.createParallelGroup(Alignment.LEADING)
            		.addGroup(gl_titlePanel.createSequentialGroup()
            			.addComponent(title, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
            			.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            titlePanel.setLayout(gl_titlePanel);
            
            messagePanel = new JPanel(new FlowLayout(0));
            messagePanel.setBounds(5, dim.height - 120 + 5, dim.width - 450, 20);
            messagePanel.setLayout(new BoxLayout(messagePanel, 1));
            messagePanel.setBorder(BorderFactory.createEtchedBorder());
            mainPane.add(messagePanel);
            bottomMessage = new JLabel();
            bottomMessage.setText("");
            messagePanel.add(bottomMessage);
            
            userLoginPanel = new JPanel();
            userLoginPanel.setBounds(messagePanel.getWidth() + 5, dim.height - 120 + 5, dim.width - messagePanel.getWidth() - 350, 20);
            userLoginPanel.setLayout(new BoxLayout(this.userLoginPanel, 1));
            userLoginPanel.setBorder(BorderFactory.createEtchedBorder());
            mainPane.add(userLoginPanel);
            color = userLoginPanel.getBackground();
            loginLabel = new JLabel();
            loginLabel.setText(utilisateur.getLogin());
            userLoginPanel.add(loginLabel);
            
            msgPanel = new JPanel();
            msgPanel.setBounds(messagePanel.getWidth() + 105, dim.height - 120 + 5, dim.width - messagePanel.getWidth() - 125, 20);
            msgPanel.setLayout(new BoxLayout(msgPanel, 1));
            msgPanel.setBorder(BorderFactory.createEtchedBorder());
            mainPane.add(msgPanel);
            messageLabel = new JLabel();
            msgPanel.add(messageLabel);
            
           
            generalScrollPane = new JScrollPane();
            
            generalScrollPane.setViewportView(contentPane);
            
            generalScrollPane.setBounds(0,30,(int)dim.getWidth(),(int)dim.getHeight()-10-140+5);
            
            mainPane.add(generalScrollPane);
            contentPane.setOpaque(true);
            contentPane.setBackground(Color.WHITE);
            contentPane.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
            contentPane.setLayout(null);
            txtLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
            txtLabel.setBackground(Color.PINK);
            
            
            txtLabel.setBounds(10, 52, 609, 417);
          //  contentPane.add(txtLabel);
            
            /*TRAITEMENTS DES ALERTES DU STOCK MINIMAL*/
            String nomMP="";
            int i=0;
           // List<StockMP> listeStockMP =stockMPDAO.findStockMin(utilisateur.getId());
            
            // ALERTE DU STOCK MINIMAL
            
           /* Map< Integer, Float> mapStockTotalByMp= new HashMap<>();
            mapStockTotalByMp=stockMPDAO.findStockTotalByMagasin(utilisateur.getId());
            List<MatierePremier>	listMP =dao.findAll();
            for(i=0;i<listMP.size();i++){
            	MatierePremier matierePremier=listMP.get(i);
            	float quantiteStock =mapStockTotalByMp.get(matierePremier.getId());	
            	nomMP+="-"+matierePremier.getNom()+" : "+quantiteStock+"\n";
            }
            txtLabel.setText(nomMP);*/
        	getContentPane().setLayout(null);
        	  btnDeconnexion.addActionListener(new ActionListener() {
  	  		  	public void actionPerformed(ActionEvent e) {
  	  		  		moveToTheNewWindow();
  	  		  	}
        	  });
        	  
        	  btnGenerer.addActionListener(new ActionListener() {
    	  		  	public void actionPerformed(ActionEvent e) {
    	  		   	List<MatierePremier> listMP =new ArrayList<MatierePremier>();
    	  		   	List<Articles> listArticles =new ArrayList<Articles>();
    	  		   	List<Client> listClient =new ArrayList<Client>();
    	  			List<Fournisseur> listFournisseur =new ArrayList<Fournisseur>();
    	  		  
		  		   	List<Magasin> listMagasin= new ArrayList<Magasin>();
		  		   	List<Magasin> listMagasinProduitFini= new ArrayList<Magasin>();
		  		   
		  		   	listMP =dao.findAll();
		  		   	listArticles=artilesDAO.findAll();
		  		   	listClient=clientDAO.findAll();
		  		   	listFournisseur=fournisseurDAO.findAll();
		  		   	
		  		  		listMagasin=depotDAO.listeMagasinByTypeMagasin(Constantes.MAGASIN_CODE_TYPE_MP);
		  		  		listMagasinProduitFini=depotDAO.listeMagasinByTypeMagasin(Constantes.MAGASIN_CODE_TYPE_PF);
		  		  		
		  		   	//	Utils.genererStockByMagasinMP(listMP,listMagasin);
		  		   	//	Utils.genererStockProduitFiniByMagasin(listArticles, listMagasinProduitFini);
		  		   		Utils.genererPrixCientByMP(listMP,listClient,listFournisseur);
		  		   		
		  		   	JOptionPane.showMessageDialog(null, "Le stock est généré avec succès", "Attention", JOptionPane.INFORMATION_MESSAGE);
    	  		  	}
          	  });
                   
            createMenuBar();
            
        }
        
       
        
        
        public Main(int id_user,String user,Connection conn){
        	
        	
        }
        

        
        
 private void createMenuBar() {

        JMenuBar menubar = new JMenuBar();
        menubar.setBackground(new Color(248, 248, 255));
        menubar.setOpaque(true);
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        menubar.add(Box.createRigidArea(new Dimension(0,35)));
        menubar.setFont(new Font("New Times",Font.BOLD,10));
        menubar.setUI ( new BasicMenuBarUI (){
       public void paint ( Graphics g, JComponent c ){
       g.setColor ( new Color(248, 248, 255) );
       
       g.fillRect ( 0, 0, c.getWidth (), c.getHeight () );
    }
} );
         menubar.setForeground(Color.white);
         
         

         
        
  /* Menu Accueil */////       
        JMenu acceuilMenu = new JMenu("Accueil");
        menubar.add(acceuilMenu);
        
        JMenuItem acceuilItem = new JMenuItem("Accueil");
        acceuilItem.setMnemonic(KeyEvent.VK_A);
        acceuilMenu.add(acceuilItem);
        acceuilItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText(AuthentificationView.login);
                                                            	titlePanel.add(title);
                                                            	Main window = new Main();
                                            	 				
                                            	 				window.setVisible(true);
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        JMenuItem acceuilFermerMenuItem = new JMenuItem("Fermer");
        acceuilFermerMenuItem.setMnemonic(70);
        acceuilMenu.add(acceuilFermerMenuItem);
        /*
         clientMenu = new JMenu("Client");
        menubar.add(clientMenu);
        
        JMenuItem ajoutClientItem = new JMenuItem("Ajout Client");
        ajoutClientItem.setMnemonic(KeyEvent.VK_A);
        clientMenu.add(ajoutClientItem);
        ajoutClientItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Ajouter Client");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new AjoutClient();
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                            }
                                                                 });
        
        JMenuItem chercherClientItem = new JMenuItem("Chercher Client");
        chercherClientItem.setMnemonic(KeyEvent.VK_A);
        clientMenu.add(chercherClientItem);
        chercherClientItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Chercher Client");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new ChercherClient();
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });

        
        */
        /* Menu Fournisseur 
         * 
         */
         depotMenu = new JMenu("Dépot");
        menubar.add(depotMenu);
        JMenuItem depotItem = new JMenuItem("Ajouter Dépot");
        depotItem.setMnemonic(KeyEvent.VK_A);
        depotMenu.add(depotItem);
        depotItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Ajouter Dépot");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new AjoutDepot();
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);  
                                                                generalScrollPane.setAutoscrolls(true);
                                                                contentPane.setOpaque(true);
                                                            }
                                                                 });
        
        
        JMenuItem chercherDepotItem = new JMenuItem("Chercher Dépot");
        chercherDepotItem.setMnemonic(KeyEvent.VK_A);
        depotMenu.add(chercherDepotItem);
        chercherDepotItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Chercher Dépot");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new ChercherDepot();
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                            }
                                                                 });
        
        // Menu Fournisseur 
        
        
          fournisseurMenu = new JMenu("Fournisseur");
        menubar.add(fournisseurMenu);
        JMenuItem fournisseurItem = new JMenuItem("Ajouter Fournisseur");
        fournisseurItem.setMnemonic(KeyEvent.VK_A);
        fournisseurMenu.add(fournisseurItem);
        fournisseurItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Ajouter Fournisseur");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new AjouterFournisseur();
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                            }
                                                                 });
        
         /* Menu Fournisseur 
         * 
         */
        /*
        JMenuItem fournisseur2Item = new JMenuItem("Chercher Fournisseur");
        fournisseurItem.setMnemonic(KeyEvent.VK_A);
        fournisseurMenu.add(fournisseur2Item);
        fournisseur2Item.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Ajouter Fournisseur");
                                                            	titlePanel.add(title);
                                                            	//contentPane  = new ajouterClient();
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                            }
                                                                 });
        
      
      Menu commande 
        JMenu commandeMenu = new JMenu("Commande");
        menubar.add(commandeMenu);
        JMenuItem commandeItem = new JMenuItem("Ajouter Commande");
        commandeItem.setMnemonic(KeyEvent.VK_A);
        commandeMenu.add(commandeItem);
        commandeItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Ajouter Commande");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new AjouterClient();
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                            }
                                                                 });*/
        
       
        
 /*Menu Matière Première */
        MatierePremierMenu = new JMenu("Matière Première");
        menubar.add(MatierePremierMenu);
        
        JMenuItem saisirMatierePremiereItem = new JMenuItem("Ajouter Matière Première");
        saisirMatierePremiereItem.setMnemonic(KeyEvent.VK_A);
        MatierePremierMenu.add(saisirMatierePremiereItem);
        saisirMatierePremiereItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Ajouter Matière Première");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new AjoutMatierePremiere();
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
       
        JMenuItem chercherMatierePremiereItem = new JMenuItem("Chercher Matière Première");
        chercherMatierePremiereItem.setMnemonic(KeyEvent.VK_A);
        MatierePremierMenu.add(chercherMatierePremiereItem);
        chercherMatierePremiereItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Chercher Matière Première");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new ChercherMatierePremiere();
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        
        JMenuItem ajouterMpComposeItem = new JMenuItem("Ajouter Matière Première Composé");
        ajouterMpComposeItem.setMnemonic(KeyEvent.VK_A);
        MatierePremierMenu.add(ajouterMpComposeItem);
        ajouterMpComposeItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Ajouter Matière Première Composé");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new AjouterMPCompose();
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        
        JMenuItem modifierMpComposeItem = new JMenuItem("Modifier Matière Première Composé");
        modifierMpComposeItem.setMnemonic(KeyEvent.VK_A);
        MatierePremierMenu.add(modifierMpComposeItem);
        modifierMpComposeItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Modifier Matière Première Composé");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new ModifierMatierePremierCompose();
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        
        
        JMenuItem listeMpItem = new JMenuItem("Liste Matiere Premiere");
        listeMpItem.setMnemonic(KeyEvent.VK_A);
        MatierePremierMenu.add(listeMpItem);
        listeMpItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Liste Matiere Premiere");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new ListeMatierePremiere();
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        
      
        
       
        
        
        
        /*Menu Estimation article 
         * 
         */
         articleMenu = new JMenu("Article");
        menubar.add(articleMenu);
         JMenuItem CreerEstimationMenuItem = new JMenuItem("Ajouter Article");
         CreerEstimationMenuItem.setMnemonic(KeyEvent.VK_A);
         articleMenu.add(CreerEstimationMenuItem);
         CreerEstimationMenuItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Créer Estimation article");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new CreerEstimationArticle();
                                                             	
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  });
         JMenuItem modifierEstimationMenuItem = new JMenuItem("Chercher Article");
         modifierEstimationMenuItem.setMnemonic(KeyEvent.VK_A);
         articleMenu.add(modifierEstimationMenuItem);
         modifierEstimationMenuItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Modifier Estimation article");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new ModifierEstimationArticle();
                                                             	
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  });
         
         JMenuItem ajouterEstimationMenuItem = new JMenuItem("Ajouter Estimation catégorie thé ");
         ajouterEstimationMenuItem.setMnemonic(KeyEvent.VK_A);
         articleMenu.add(ajouterEstimationMenuItem);
         ajouterEstimationMenuItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Ajouter Estimation catégorie thé ");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new AjouterEstimationThe();
                                                             	
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  });
         
         
         JMenuItem ajouterGrammageArticleMenuItem = new JMenuItem("Ajouter Grammage Article ");
         ajouterGrammageArticleMenuItem.setMnemonic(KeyEvent.VK_A);
         articleMenu.add(ajouterGrammageArticleMenuItem);
         ajouterGrammageArticleMenuItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Ajouter Grammage Article ");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new AjoutGrammageArticle();
                                                             	
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  });
        
        
        /*Menu Stock matière première
         * 
         */
        
         stockMPMenu = new JMenu("Stock Matière Première");
        menubar.add(stockMPMenu);
        
        
        JMenuItem ajouterInitialStockMenuItem = new JMenuItem("Ajouter Initial Matière Première");
        ajouterInitialStockMenuItem.setMnemonic(KeyEvent.VK_A);
        stockMPMenu.add(ajouterInitialStockMenuItem);
        ajouterInitialStockMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Ajouter Initial Matière Première");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new AjoutInitialMP();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        
        
        
        JMenuItem modifierInitialStockMenuItem = new JMenuItem("Modifier Initial Matière Première");
        modifierInitialStockMenuItem.setMnemonic(KeyEvent.VK_A);
        stockMPMenu.add(modifierInitialStockMenuItem);
        modifierInitialStockMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Modifier Initial Matière Première");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new ModifierInitialMP();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        
        
        
        
        
        
        JMenuItem ConsulterEtatStockInitialItem = new JMenuItem("Consulter Etat Stock Initial MP");
        ConsulterEtatStockInitialItem.setMnemonic(KeyEvent.VK_A);
        stockMPMenu.add(ConsulterEtatStockInitialItem);
        ConsulterEtatStockInitialItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Consulter Etat Stock Initial MP");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new ConsulterEtatStockInitialMP();
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        
       
        
        
        /*
        
        JMenuItem ajouterStockMenuItem = new JMenuItem("Ajouter Stock Matière Première");
        ajouterStockMenuItem.setMnemonic(KeyEvent.VK_A);
        stockMPMenu.add(ajouterStockMenuItem);
        ajouterStockMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Ajouter Stock Matière Première");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new AjouterStockMP();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        */
        
        JMenuItem afficherMPMenuItem = new JMenuItem("Afficher Stock Matière Première");
        afficherMPMenuItem.setMnemonic(KeyEvent.VK_A);
        stockMPMenu.add(afficherMPMenuItem);
        afficherMPMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Afficher Stock Matière Première");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new AfficherStockMP();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        
        
        
     
        
        
        
		/*
		 * JMenuItem transfererMPMenuItem = new
		 * JMenuItem("Transférer Stock Matière Première");
		 * transfererMPMenuItem.setMnemonic(KeyEvent.VK_A);
		 * stockMPMenu.add(transfererMPMenuItem);
		 * transfererMPMenuItem.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent event) {
		 * title.setText("Transférer Stock Matière Première"); titlePanel.add(title);
		 * contentPane = new TransfererStockMP();
		 * 
		 * contentPane.setPreferredSize(new
		 * Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
		 * generalScrollPane.setViewportView(contentPane); contentPane.setOpaque(true);
		 * 
		 * 
		 * } });
		 * 
		 */         
		/*
		 * JMenuItem consulterFactureVenteMPMenuItem = new
		 * JMenuItem("Consulter BL Vente MP");
		 * consulterFactureVenteMPMenuItem.setMnemonic(KeyEvent.VK_A);
		 * stockMPMenu.add(consulterFactureVenteMPMenuItem);
		 * consulterFactureVenteMPMenuItem.addActionListener(new ActionListener() {
		 * public void actionPerformed(ActionEvent event) {
		 * title.setText("Consulter BL Vente MP"); titlePanel.add(title); contentPane =
		 * new ConsulterFactureVenteMP();
		 * 
		 * contentPane.setPreferredSize(new
		 * Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
		 * generalScrollPane.setViewportView(contentPane); contentPane.setOpaque(true);
		 * 
		 * } });
		 */
         
         
         
         /*
         JMenuItem entrerMPMenuItem = new JMenuItem("Recevoir Stock Matière Première");
         entrerMPMenuItem.setMnemonic(KeyEvent.VK_A);
         stockMPMenu.add(entrerMPMenuItem);
         entrerMPMenuItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Recevoir Stock Matière Première");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new EntrerStockMP();
                                                             	
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  });
         
         JMenuItem sortirMPMenuItem = new JMenuItem("Sortir Stock Matière Première");
         sortirMPMenuItem.setMnemonic(KeyEvent.VK_A);
         stockMPMenu.add(sortirMPMenuItem);
         sortirMPMenuItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Sortir Stock Matière Première");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new SortirStockMP();
                                                             	
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  });
         
         JMenuItem detailTransferMenuItem = new JMenuItem("Afficher Détail Transfert");
         detailTransferMenuItem.setMnemonic(KeyEvent.VK_A);
         stockMPMenu.add(detailTransferMenuItem);
         detailTransferMenuItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Afficher Détail Transfert");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new AfficherDetailTransferMP();
                                                             	
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  });
         */
         JMenuItem TransfereMpProduitFiniItem = new JMenuItem("Transfere Matiere Premiere Produit Fini");
         TransfereMpProduitFiniItem.setMnemonic(KeyEvent.VK_A);
         stockMPMenu.add(TransfereMpProduitFiniItem);
         TransfereMpProduitFiniItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Transfere Matiere Premiere Produit Fini");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new TransfererMPProduitFini();
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  });
         
         
         JMenuItem TransfereStockInitialMpVersAutreMP = new JMenuItem("Transfere Stock Initiale MP Vers Autre MP");
         TransfereStockInitialMpVersAutreMP.setMnemonic(KeyEvent.VK_A);
         stockMPMenu.add(TransfereStockInitialMpVersAutreMP);
         TransfereStockInitialMpVersAutreMP.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Transfere Stock Initiale MP Vers Autre MP");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new TransfererSockInitialMPVersAutreMP();
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  });
         
         
         JMenuItem MonqueDepotItem = new JMenuItem("Manque Depot");
         MonqueDepotItem.setMnemonic(KeyEvent.VK_A);
         stockMPMenu.add(MonqueDepotItem);
         MonqueDepotItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Manque Depot");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new AjoutManqueDepot();
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  });
         
         
         
         JMenuItem ConsulterCompteMagasinierItem = new JMenuItem("Consulter Compte Magasinier");
         ConsulterCompteMagasinierItem.setMnemonic(KeyEvent.VK_A);
         stockMPMenu.add(ConsulterCompteMagasinierItem);
         ConsulterCompteMagasinierItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Consulter Compte Magasinier");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new ConsulterCompteMagasinier();
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  });
         
         JMenuItem ChargeDepotItem = new JMenuItem("Charge Depot");
         ChargeDepotItem.setMnemonic(KeyEvent.VK_A);
         stockMPMenu.add(ChargeDepotItem);
         ChargeDepotItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Charge Depot");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new AjoutChargeDepot();
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  });
         
         
         
         JMenuItem ConsulterMouvementStockItem = new JMenuItem("Consulter Mouvement Stock");
         ConsulterMouvementStockItem.setMnemonic(KeyEvent.VK_A);
         stockMPMenu.add(ConsulterMouvementStockItem);
         ConsulterMouvementStockItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Consulter Mouvement Stock");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new ConsulterMouvementStock();
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  });
         
         

         JMenuItem ConsulterEtatStockItem = new JMenuItem("Consulter Etat Stock");
         ConsulterEtatStockItem.setMnemonic(KeyEvent.VK_A);
         stockMPMenu.add(ConsulterEtatStockItem);
         ConsulterEtatStockItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Consulter Etat Stock");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new ConsulterEtatStock();
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  });
         
         JMenuItem ConsulterDetailEtatMPItem = new JMenuItem("Consulter Detail Etat MP");
         ConsulterDetailEtatMPItem.setMnemonic(KeyEvent.VK_A);
         stockMPMenu.add(ConsulterDetailEtatMPItem);
         ConsulterDetailEtatMPItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Consulter Detail Etat MP");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new ConsulterEtatEmballage();
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  });
        
        
         JMenuItem ConsulterStatistiqueMPConsommeLorsProductionPFMenuItem = new JMenuItem("Consulter MP Consommer Lors Production PF");
         ConsulterStatistiqueMPConsommeLorsProductionPFMenuItem.setMnemonic(KeyEvent.VK_A);
         stockMPMenu.add(ConsulterStatistiqueMPConsommeLorsProductionPFMenuItem);
         ConsulterStatistiqueMPConsommeLorsProductionPFMenuItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Consulter MP Consommer Lors Production PF");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new StatistiqueMPUtiliserLorsDeLaProductionPF();
                                                             	
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  }); 
        
        /*Menu Unité de fabrication
         * 
         */
        
         uniteFabMenu = new JMenu("Unité de Fabrication");
        menubar.add(uniteFabMenu);
         JMenuItem AjouterClientMenuItem = new JMenuItem("Ajouter Unité de Fabrication");
         AjouterClientMenuItem.setMnemonic(KeyEvent.VK_A);
         uniteFabMenu.add(AjouterClientMenuItem);
         AjouterClientMenuItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Ajouter Machine");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new AjoutMachine();
                                                             	
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  });
         
         JMenuItem SoldeClientMenuItem = new JMenuItem("Chercher Unité de Fabrication");
         SoldeClientMenuItem.setMnemonic(KeyEvent.VK_A);
         uniteFabMenu.add(SoldeClientMenuItem);
         SoldeClientMenuItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	contentPane  = new ChercherMachine();
                                                             	title.setText("Chercher Unité de Fabrication");
                                                             	titlePanel.add(title);
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  });
         
         /*Menu Equipe
          * 
          */
         
           equipeFabMenu = new JMenu("Equipe");
           menubar.add(equipeFabMenu);
           
         JMenuItem creerEquipeMenuItem = new JMenuItem("Créer Equipe");
         AjouterClientMenuItem.setMnemonic(KeyEvent.VK_A);
         equipeFabMenu.add(creerEquipeMenuItem);
         
         creerEquipeMenuItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Créer Equipe");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new AjoutEquipe();
                                                             	
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  });
        
         JMenuItem chercherEquipeMenuItem = new JMenuItem("Chercher Equipe");
         chercherEquipeMenuItem.setMnemonic(KeyEvent.VK_A);
         equipeFabMenu.add(chercherEquipeMenuItem);
         chercherEquipeMenuItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Chercher Equipe");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new ChercherEquipe();
                                                             	
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  });
         JMenuItem ajoutEmployeMenuItem = new JMenuItem("Ajouter Employé");
         ajoutEmployeMenuItem.setMnemonic(KeyEvent.VK_A);
         equipeFabMenu.add(ajoutEmployeMenuItem);
         ajoutEmployeMenuItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Ajouter Employé");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new AjoutEmploye();
                                                             	
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  });
         JMenuItem creerEmployeMenuItem = new JMenuItem("Chercher Employé");
         creerEmployeMenuItem.setMnemonic(KeyEvent.VK_A);
         equipeFabMenu.add(creerEmployeMenuItem);
         creerEmployeMenuItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Chercher Employé");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new ChercherEmploye();
                                                             	
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  });
         JMenuItem ficheEmployeMenuItem = new JMenuItem("Fiche Employé");
         ficheEmployeMenuItem.setMnemonic(KeyEvent.VK_A);
         equipeFabMenu.add(ficheEmployeMenuItem);
         ficheEmployeMenuItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Fiche Employé");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new AfficherFicheEmploye();
                                                             	
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  });
         JMenuItem ficheEmployeGlobaleMenuItem = new JMenuItem("Fiche Employé Globale");
         ficheEmployeGlobaleMenuItem.setMnemonic(KeyEvent.VK_A);
         equipeFabMenu.add(ficheEmployeGlobaleMenuItem);
         ficheEmployeGlobaleMenuItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Fiche Employé Globale");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new AfficherFicheGlobaleEmploye();
                                                             	
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  });
     
        /*Menu Ordre de fabrication 
         * 
         */
        
         
          ordreFabMenu = new JMenu("Ordre de Fabrication");
          menubar.add(ordreFabMenu);
        JMenuItem creerOrdreMenuItem = new JMenuItem("Créer Order de Fabrication");
        AjouterClientMenuItem.setMnemonic(KeyEvent.VK_A);
        ordreFabMenu.add(creerOrdreMenuItem);
        creerOrdreMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Créer Order de fabrication");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new CreerOrdreFabrication();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
     
      
        JMenuItem lancerOrdreMenuItem = new JMenuItem("Lancer Order de Fabrication");
        lancerOrdreMenuItem.setMnemonic(KeyEvent.VK_A);
        ordreFabMenu.add(lancerOrdreMenuItem);
        lancerOrdreMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Lancer Order de fabrication");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new LancerOrdreFabrication();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        
        JMenuItem terminerOrdreMenuItem = new JMenuItem("Terminer Order de Fabrication");
        terminerOrdreMenuItem.setMnemonic(KeyEvent.VK_A);
        ordreFabMenu.add(terminerOrdreMenuItem);
        terminerOrdreMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Terminer Order de Fabrication");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new TerminerOrdreFabrication(new Production(),"","");
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        
        JMenuItem importerProductionExcelMenuItem = new JMenuItem("Importer Production Excel");
        importerProductionExcelMenuItem.setMnemonic(KeyEvent.VK_A);
        ordreFabMenu.add(importerProductionExcelMenuItem);
        importerProductionExcelMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Importer Production Excel");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new ImporterProductionExcel();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        
        
        JMenuItem afficherCoutDechetMenuItem = new JMenuItem("Afficher Cout total des déchets");
        afficherCoutDechetMenuItem.setMnemonic(KeyEvent.VK_A);
     //   ordreFabMenu.add(afficherCoutDechetMenuItem);
        afficherCoutDechetMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Afficher Cout total des déchets");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new CoutDechetProduction();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        
        JMenuItem afficherCompteStockMPMenuItem = new JMenuItem("Afficher Compte Stock Matière Première");
        afficherCompteStockMPMenuItem.setMnemonic(KeyEvent.VK_A);
        ordreFabMenu.add(afficherCompteStockMPMenuItem);
        afficherCompteStockMPMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Afficher Compte Stock Matière Première");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new AfficherCompteStockMP();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        
        JMenuItem ChargeVariableProductionMenuItem = new JMenuItem("Saisir Charge Production Variable");
        ChargeVariableProductionMenuItem.setMnemonic(KeyEvent.VK_A);
        ordreFabMenu.add(ChargeVariableProductionMenuItem);
        ChargeVariableProductionMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Saisir Charge Production Variable");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new AjoutChargeVariableProd();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        
        
        JMenuItem ChargeFixeProductionMenuItem = new JMenuItem("Saisir Charge Production Fixe");
        ChargeFixeProductionMenuItem.setMnemonic(KeyEvent.VK_A);
        ordreFabMenu.add(ChargeFixeProductionMenuItem);
        ChargeFixeProductionMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Saisir Charge Production Fixe");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new AjoutChargeFixeProd();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        
        
        JMenuItem CoutProductionMenuItem = new JMenuItem("Cout Production");
        CoutProductionMenuItem.setMnemonic(KeyEvent.VK_A);
        ordreFabMenu.add(CoutProductionMenuItem);
        CoutProductionMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Cout Production");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new CoutProduction();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        
        
        JMenuItem detailproductionMenuItem = new JMenuItem("Detail OF");
        detailproductionMenuItem.setMnemonic(KeyEvent.VK_A);
        ordreFabMenu.add(detailproductionMenuItem);
        detailproductionMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Detail OF");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new DetailOF();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        JMenu BonsProductions = new JMenu("Bons Productions");
        BonsProductions.setMnemonic(KeyEvent.VK_A);
        ordreFabMenu.add(BonsProductions);
        
        JMenuItem bonproductioncomptaMenuItem = new JMenuItem("Bon Production Compta");
        bonproductioncomptaMenuItem.setMnemonic(KeyEvent.VK_A);
        BonsProductions.add(bonproductioncomptaMenuItem);
        bonproductioncomptaMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Bon Production Compta");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new BonProduction();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        
        JMenuItem bonproductioncomptaParAnnneMenuItem = new JMenuItem("Bon Production Compta Par Annee");
        bonproductioncomptaParAnnneMenuItem.setMnemonic(KeyEvent.VK_A);
        BonsProductions.add(bonproductioncomptaParAnnneMenuItem);
        bonproductioncomptaParAnnneMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Bon Production Compta Par Annee");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new BonProductionParAnnee();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        
        
        JMenuItem bonproductioncomptaParPourcentageEnVracConsommeMenuItem = new JMenuItem("Bon Production Compta Par % En Vrac Consomme");
        bonproductioncomptaParPourcentageEnVracConsommeMenuItem.setMnemonic(KeyEvent.VK_A);
        BonsProductions.add(bonproductioncomptaParPourcentageEnVracConsommeMenuItem);
        bonproductioncomptaParPourcentageEnVracConsommeMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Bon Production Compta Par % En Vrac Consomme");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new BonProductionParPourcentageEnVrac();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        
        
        JMenuItem regularisationprixpfMenuItem = new JMenuItem("Régularisation de Prix de PF");
        regularisationprixpfMenuItem.setMnemonic(KeyEvent.VK_A);
        ordreFabMenu.add(regularisationprixpfMenuItem);
        regularisationprixpfMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Régularisation de Prix de PF");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new RegularisationPrixPF();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        
        
       
        
        JMenuItem modifierCoutProductionserviceMenuItem = new JMenuItem("Modifier Cout Production Service Par Production");
        modifierCoutProductionserviceMenuItem.setMnemonic(KeyEvent.VK_A);
        ordreFabMenu.add(modifierCoutProductionserviceMenuItem);
        modifierCoutProductionserviceMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Modifier Cout Production Service Par Production");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new ModifierCoutProductionserviceParProduction();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 }); 
        
        
        
        JMenuItem modifierCoutProductionserviceParFactureServiceMenuItem = new JMenuItem("Modifier Cout Production Service Par Facture Service");
        modifierCoutProductionserviceParFactureServiceMenuItem.setMnemonic(KeyEvent.VK_A);
        ordreFabMenu.add(modifierCoutProductionserviceParFactureServiceMenuItem);
        modifierCoutProductionserviceParFactureServiceMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Modifier Cout Production Service Par Facture Service");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new ModifierCoutProductionServiceParNumFacture();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        
        
        JMenuItem annulerProductionMenuItem = new JMenuItem("Annulation Des OF");
        annulerProductionMenuItem.setMnemonic(KeyEvent.VK_A);
        ordreFabMenu.add(annulerProductionMenuItem);
        annulerProductionMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Annulation Des OF");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new AnnulerLesOF();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        
        JMenuItem ConsulterEtatMPConsommerparOFMenuItem = new JMenuItem("Consulter Quantites Consommer Par OF");
        ConsulterEtatMPConsommerparOFMenuItem.setMnemonic(KeyEvent.VK_A);
        ordreFabMenu.add(ConsulterEtatMPConsommerparOFMenuItem);
        ConsulterEtatMPConsommerparOFMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Consulter Quantites Consommer Par OF");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new ConsulterEtatMPConsommerParOF();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 }); 
        
       
        JMenuItem ConsulterEtatBoxEtCartonConsommeParPFMenuItem = new JMenuItem("Consulter Etat Box Et Carton Consomme Par PF");
        ConsulterEtatBoxEtCartonConsommeParPFMenuItem.setMnemonic(KeyEvent.VK_A);
        ordreFabMenu.add(ConsulterEtatBoxEtCartonConsommeParPFMenuItem);
        ConsulterEtatBoxEtCartonConsommeParPFMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Consulter Etat Box Et Carton Consomme Par PF");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new ConsulterEtatBoxEtCartonConsommerParPF();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });  
        
        
        
        
        
        /*Menu Production Carton 
         * 
         */
        
         
        prodCartMenu = new JMenu("Production Carton");
        menubar.add(prodCartMenu);
       
        JMenuItem creerordrefabricationmpMenuItem = new JMenuItem("Créer Ordre de Fabrication MP");
        creerordrefabricationmpMenuItem.setMnemonic(KeyEvent.VK_A);
        prodCartMenu.add(creerordrefabricationmpMenuItem);
        creerordrefabricationmpMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Créer Ordre de Fabrication MP");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new CreerOrdreFabricationMP();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        
        
        
        
        JMenuItem lancerorderfabricationmpMenuItem = new JMenuItem("Lancer Ordre de Fabrication MP");
        lancerorderfabricationmpMenuItem.setMnemonic(KeyEvent.VK_A);
        prodCartMenu.add(lancerorderfabricationmpMenuItem);
        lancerorderfabricationmpMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Lancer Ordre de Fabrication MP");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new LancerOrdreFabricationMP();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
       
        
    
         
        
         JMenuItem terminerorderfabricationmpMenuItem = new JMenuItem("Terminer Ordre de Fabrication MP");
         terminerorderfabricationmpMenuItem.setMnemonic(KeyEvent.VK_A);
         prodCartMenu.add(terminerorderfabricationmpMenuItem);
         terminerorderfabricationmpMenuItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Terminer Ordre de Fabrication MP");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new TerminerOrdreFabricationMP(new ProductionMP(),"","");
                                                             	
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  });
        
        
        
        
        
        
        
        /*Menu Stock produit fini
         * 
         */
       
         stockPFMenu = new JMenu("Stock Produit Fini");
        menubar.add(stockPFMenu);
        
        
        JMenuItem ajoutInitialPFMenuItem = new JMenuItem("Ajouter Initial Produit Fini");
        ajoutInitialPFMenuItem.setMnemonic(KeyEvent.VK_A);
        stockPFMenu.add(ajoutInitialPFMenuItem);
        ajoutInitialPFMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Ajouter Initial Produit Fini");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new AjoutInitialProduitFinit();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        
        
        JMenuItem ModifierInitialPFMenuItem = new JMenuItem("Modifier Initial Produit Fini");
        ModifierInitialPFMenuItem.setMnemonic(KeyEvent.VK_A);
        stockPFMenu.add(ModifierInitialPFMenuItem);
        ModifierInitialPFMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Modifier Initial Produit Fini");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new ModifierInitialProduitFinit();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        
        
        
        JMenuItem ConsulterEtatStockInitialPFItem = new JMenuItem("Consulter Etat Stock Initial Produit Fini");
        ConsulterEtatStockInitialPFItem.setMnemonic(KeyEvent.VK_A);
        stockPFMenu.add(ConsulterEtatStockInitialPFItem);
        ConsulterEtatStockInitialPFItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Consulter Etat Stock Initial Produit Fini");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new ConsulterEtatInitialStockPF();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        
        
       
        JMenuItem afficherPFMenuItem = new JMenuItem("Afficher Stock Produit Fini");
        afficherPFMenuItem.setMnemonic(KeyEvent.VK_A);
        stockPFMenu.add(afficherPFMenuItem);
        afficherPFMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Afficher Stock Produit Fini");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new AfficherStockPF();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
    
         
       /* 
         JMenuItem sortirPFMenuItem = new JMenuItem("Sortir Stock Produit Fini");
         sortirPFMenuItem.setMnemonic(KeyEvent.VK_A);
         stockPFMenu.add(sortirPFMenuItem);
         sortirPFMenuItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Sortir Stock Produit Fini");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new SortirStockPF();
                                                             	
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  });*/
         
         
         
         JMenuItem ConsulterMouvementStockPFItem = new JMenuItem("Consulter Mouvement Stock Produit Fini");
         ConsulterMouvementStockPFItem.setMnemonic(KeyEvent.VK_A);
         stockPFMenu.add(ConsulterMouvementStockPFItem);
         ConsulterMouvementStockPFItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Consulter Mouvement Stock Produit Fini");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new ConsulterMouvementStockPF();
                                                             	
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  });
         
         
         JMenuItem ConsulterEtatStockPFItem = new JMenuItem("Consulter Etat Stock Produit Fini");
         ConsulterEtatStockPFItem.setMnemonic(KeyEvent.VK_A);
         stockPFMenu.add(ConsulterEtatStockPFItem);
         ConsulterEtatStockPFItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Consulter Etat Stock Produit Fini");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new ConsulterEtatStockPF();
                                                             	
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  });
         
         
         JMenuItem ConsulterEtatComparaisonAvoirPFItem = new JMenuItem("Consulter Etat De Comparaison Avoir PF");
         ConsulterEtatComparaisonAvoirPFItem.setMnemonic(KeyEvent.VK_A);
         stockPFMenu.add(ConsulterEtatComparaisonAvoirPFItem);
         ConsulterEtatComparaisonAvoirPFItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Consulter Etat De Comparaison Avoir PF");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new ConsulterEtatStockPFComparaisonAvoir();
                                                             	
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  });
         
         
         
         
         
         JMenuItem ConsulterEtatEstimationStockPFItem = new JMenuItem("Consulter Etat Estimation Stock Produit Fini");
         ConsulterEtatEstimationStockPFItem.setMnemonic(KeyEvent.VK_A);
         stockPFMenu.add(ConsulterEtatEstimationStockPFItem);
         ConsulterEtatEstimationStockPFItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Consulter Etat Estimation Stock Produit Fini");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new ConsulterEtatEstimationStockPF();
                                                             	
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  });
         
         
         JMenuItem ConsulterEtatAvoirFinancePFItem = new JMenuItem("Consulter Etat Avoir Finance Produit Fini");
         ConsulterEtatAvoirFinancePFItem.setMnemonic(KeyEvent.VK_A);
         stockPFMenu.add(ConsulterEtatAvoirFinancePFItem);
         ConsulterEtatAvoirFinancePFItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Consulter Etat Avoir Finance Produit Fini");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new ConsulterEtatAvoirFinance();
                                                             	
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  });
         
         
         
         
         JMenuItem ConsulterEtatMConsommablePFItem = new JMenuItem("Consulter Etat MConsommable");
         ConsulterEtatMConsommablePFItem.setMnemonic(KeyEvent.VK_A);
         stockPFMenu.add(ConsulterEtatMConsommablePFItem);
         ConsulterEtatMConsommablePFItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Consulter Etat MConsommable");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new ConsulterEtatMConsommablePF();
                                                             	
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  });
         
         
         JMenuItem TransferePFProduitFiniItem = new JMenuItem("Transfere Produit Fini En Produit Fini");
         TransferePFProduitFiniItem.setMnemonic(KeyEvent.VK_A);
         stockPFMenu.add(TransferePFProduitFiniItem);
         TransferePFProduitFiniItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Transfere Produit Fini En Produit Fini");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new TransfererPFProduitFini();
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  });
         
         
         JMenuItem ConsulterTransferePFProduitFiniItem = new JMenuItem("Consulter Transfere Produit Fini En Produit Fini");
         ConsulterTransferePFProduitFiniItem.setMnemonic(KeyEvent.VK_A);
         stockPFMenu.add(ConsulterTransferePFProduitFiniItem);
         ConsulterTransferePFProduitFiniItem.addActionListener(new ActionListener() {
                                                             public void actionPerformed(ActionEvent event) {
                                                             	title.setText("Consulter Transfere Produit Fini En Produit Fini");
                                                             	titlePanel.add(title);
                                                             	contentPane  = new ConsulterTransfererPFProduitFini();
                                                                 contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                 generalScrollPane.setViewportView(contentPane);                
                                                                 contentPane.setOpaque(true);
                                                                 

                                                                      }
                                                                  });
         
         
        /*Menu Paramètres
         * 
         */
        
          parametreMenu = new JMenu("Paramètres");
          menubar.add(parametreMenu);
          
          JMenuItem ajouterPrixClientMenuItem = new JMenuItem("Ajouter Prix Client ");
          ajouterPrixClientMenuItem.setMnemonic(KeyEvent.VK_A);
          parametreMenu.add(ajouterPrixClientMenuItem);
          ajouterPrixClientMenuItem.addActionListener(new ActionListener() {
                                                              public void actionPerformed(ActionEvent event) {
                                                              	title.setText("Ajouter Prix Client");
                                                              	titlePanel.add(title);
                                                              	contentPane  = new AjouterPrixClientMP();
                                                              	
                                                                  contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                  generalScrollPane.setViewportView(contentPane);                
                                                                  contentPane.setOpaque(true);
                                                                  

                                                                       }
                                                                   });
        JMenuItem BloquerEmployeMenuItem = new JMenuItem("Bloquer Employé");
        BloquerEmployeMenuItem.setMnemonic(KeyEvent.VK_A);
        parametreMenu.add(BloquerEmployeMenuItem);
        BloquerEmployeMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Bloquer Employé");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new AjoutEmployeBloque();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });

        JMenuItem ajoutParametreMenuItem = new JMenuItem("Ajouter Paramètre");
        ajoutParametreMenuItem.setMnemonic(KeyEvent.VK_A);
        parametreMenu.add(ajoutParametreMenuItem);
        ajoutParametreMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Ajouter Paramètre");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new AjoutParametre();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        
        JMenuItem ajoutUtilisateurMenuItem = new JMenuItem("Ajouter Utilisateur");
        ajoutUtilisateurMenuItem.setMnemonic(KeyEvent.VK_A);
        parametreMenu.add(ajoutUtilisateurMenuItem);
        ajoutUtilisateurMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Ajout Utilisateur");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new AjoutUtilisateur();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                                 });
        
        JMenuItem gererUtilisateurMenuItem = new JMenuItem("Gérer Authorisation");
        gererUtilisateurMenuItem.setMnemonic(KeyEvent.VK_A);
        parametreMenu.add(gererUtilisateurMenuItem);
        gererUtilisateurMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Gérer Authorisation");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new GererAuthUtilisateur();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                

                                                                     }
                                                            
                                                                 });
        
        
        JMenuItem ChargesMenuItem = new JMenuItem("Ajouter Charges");
        ChargesMenuItem.setMnemonic(KeyEvent.VK_A);
        parametreMenu.add(ChargesMenuItem);
        ChargesMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Ajout Charges");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new AjoutCharges();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                
                                                                     }
                                                            
                                                                 });
																 
																 
																  JMenuItem OffreMenuItem = new JMenuItem("Ajouter Offre");
        OffreMenuItem.setMnemonic(KeyEvent.VK_A);
        parametreMenu.add(OffreMenuItem);
        OffreMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Ajout Offre");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new AjoutOffre();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                
                                                                     }
                                                            
                                                                 });
        
        JMenuItem ActiverOffreMenuItem = new JMenuItem("Activer Offre");
        ActiverOffreMenuItem.setMnemonic(KeyEvent.VK_A);
        parametreMenu.add(ActiverOffreMenuItem);
        ActiverOffreMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Activer Offre");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new ActiverOffre();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                
                                                                     }
                                                            
                                                                 });
        
        
        
        JMenuItem AjouterEnteteMenuItem = new JMenuItem("Ajouter En Tete");
        AjouterEnteteMenuItem.setMnemonic(KeyEvent.VK_A);
        parametreMenu.add(AjouterEnteteMenuItem);
        AjouterEnteteMenuItem.addActionListener(new ActionListener() {
                                                            public void actionPerformed(ActionEvent event) {
                                                            	title.setText("Ajouter En Tete");
                                                            	titlePanel.add(title);
                                                            	contentPane  = new AjoutEnTete();
                                                            	
                                                                contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                generalScrollPane.setViewportView(contentPane);                
                                                                contentPane.setOpaque(true);
                                                                
                                                                     }
                                                            
                                                                 });
        
        /*Menu Referentiel
         * 
         */
        
        ReferentielMenu = new JMenu("Referentiel");
          menubar.add(ReferentielMenu);
          
          JMenuItem ajouterCategorietMenuItem = new JMenuItem("Ajouter Sous Categorie");
          ajouterCategorietMenuItem.setMnemonic(KeyEvent.VK_A);
          ReferentielMenu.add(ajouterCategorietMenuItem);
          ajouterCategorietMenuItem.addActionListener(new ActionListener() {
                                                              public void actionPerformed(ActionEvent event) {
                                                              	title.setText("Ajouter Sous Categorie");
                                                              	titlePanel.add(title);
                                                              	contentPane  = new AjoutCategorie();
                                                              	
                                                                  contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                  generalScrollPane.setViewportView(contentPane);                
                                                                  contentPane.setOpaque(true);
                                                                  

                                                                       }
                                                                   });
          
          JMenuItem ajouterFamilleArticlePFMenuItem = new JMenuItem("Ajouter Famille Article PF");
          ajouterFamilleArticlePFMenuItem.setMnemonic(KeyEvent.VK_A);
          ReferentielMenu.add(ajouterFamilleArticlePFMenuItem);
          ajouterFamilleArticlePFMenuItem.addActionListener(new ActionListener() {
                                                              public void actionPerformed(ActionEvent event) {
                                                              	title.setText("Ajouter Famille Article PF");
                                                              	titlePanel.add(title);
                                                              	contentPane  = new AjoutFamilleArticlePF();
                                                              	
                                                                  contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                  generalScrollPane.setViewportView(contentPane);                
                                                                  contentPane.setOpaque(true);
                                                                  

                                                                       }
                                                                   });
          
          
          JMenuItem ajouterSousFamilleArticlePFMenuItem = new JMenuItem("Ajouter Sous Famille Article PF");
          ajouterSousFamilleArticlePFMenuItem.setMnemonic(KeyEvent.VK_A);
          ReferentielMenu.add(ajouterSousFamilleArticlePFMenuItem);
          ajouterSousFamilleArticlePFMenuItem.addActionListener(new ActionListener() {
                                                              public void actionPerformed(ActionEvent event) {
                                                              	title.setText("Ajouter Sous Famille Article PF");
                                                              	titlePanel.add(title);
                                                              	contentPane  = new AjoutSousFamilleArticlePF();
                                                              	
                                                                  contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                  generalScrollPane.setViewportView(contentPane);                
                                                                  contentPane.setOpaque(true);
                                                                  

                                                                       }
                                                                   });
          
          
          JMenuItem ajouterSousFamilleEnVracMenuItem = new JMenuItem("Ajouter Sous Famille En Vrac");
          ajouterSousFamilleEnVracMenuItem.setMnemonic(KeyEvent.VK_A);
          ReferentielMenu.add(ajouterSousFamilleEnVracMenuItem);
          ajouterSousFamilleEnVracMenuItem.addActionListener(new ActionListener() {
                                                              public void actionPerformed(ActionEvent event) {
                                                              	title.setText("Ajouter Sous Famille En Vrac");
                                                              	titlePanel.add(title);
                                                              	contentPane  = new AjoutSousFamilleEnVrac();
                                                              	
                                                                  contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                  generalScrollPane.setViewportView(contentPane);                
                                                                  contentPane.setOpaque(true);
                                                                  

                                                                       }
                                                                   });
          
          
          /*
          JMenuItem ajouterTypeVenteMenuItem = new JMenuItem("Ajouter Type de Vente");
          ajouterTypeVenteMenuItem.setMnemonic(KeyEvent.VK_A);
          ReferentielMenu.add(ajouterTypeVenteMenuItem);
          ajouterTypeVenteMenuItem.addActionListener(new ActionListener() {
                                                              public void actionPerformed(ActionEvent event) {
                                                              	title.setText("Ajouter Type de Vente");
                                                              	titlePanel.add(title);
                                                              	contentPane  = new AjoutTypeVente();
                                                              	
                                                                  contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                  generalScrollPane.setViewportView(contentPane);                
                                                                  contentPane.setOpaque(true);
                                                                  

                                                                       }
                                                                   });
          
          
          */
          
          JMenuItem ajouterVilleMenuItem = new JMenuItem("Ajouter Ville");
          ajouterVilleMenuItem.setMnemonic(KeyEvent.VK_A);
          ReferentielMenu.add(ajouterVilleMenuItem);
          ajouterVilleMenuItem.addActionListener(new ActionListener() {
                                                              public void actionPerformed(ActionEvent event) {
                                                              	title.setText("Ajouter Ville");
                                                              	titlePanel.add(title);
                                                              	contentPane  = new AjoutVille();
                                                              	
                                                                  contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                  generalScrollPane.setViewportView(contentPane);                
                                                                  contentPane.setOpaque(true);
                                                                  

                                                                       }
                                                                   });
          
          JMenuItem ajouterClientPFMenuItem = new JMenuItem("Ajouter Client PF");
          ajouterClientPFMenuItem.setMnemonic(KeyEvent.VK_A);
          ReferentielMenu.add(ajouterClientPFMenuItem);
          ajouterClientPFMenuItem.addActionListener(new ActionListener() {
                                                              public void actionPerformed(ActionEvent event) {
                                                              	title.setText("Ajouter Client PF");
                                                              	titlePanel.add(title);
                                                              	contentPane  = new AjoutClientPF();
                                                              	
                                                                  contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                  generalScrollPane.setViewportView(contentPane);                
                                                                  contentPane.setOpaque(true);
                                                                  

                                                                       }
                                                                   });
          
          
          JMenuItem rechercheClientPFMenuItem = new JMenuItem("Chercher Client PF");
          rechercheClientPFMenuItem.setMnemonic(KeyEvent.VK_A);
          ReferentielMenu.add(rechercheClientPFMenuItem);
          rechercheClientPFMenuItem.addActionListener(new ActionListener() {
                                                              public void actionPerformed(ActionEvent event) {
                                                              	title.setText("Chercher Client PF");
                                                              	titlePanel.add(title);
                                                              	contentPane  = new ChercherClientPF();
                                                              	
                                                                  contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                  generalScrollPane.setViewportView(contentPane);                
                                                                  contentPane.setOpaque(true);
                                                                  

                                                                       }
                                                                   });
          
          
          JMenuItem ajouterAdresseClientPFMenuItem = new JMenuItem("Ajouter Adresses Client PF");
          ajouterAdresseClientPFMenuItem.setMnemonic(KeyEvent.VK_A);
          ReferentielMenu.add(ajouterAdresseClientPFMenuItem);
          ajouterAdresseClientPFMenuItem.addActionListener(new ActionListener() {
                                                              public void actionPerformed(ActionEvent event) {
                                                              	title.setText("Ajouter Adresses Client PF");
                                                              	titlePanel.add(title);
                                                              	contentPane  = new AjouterAdressesClientPF();
                                                              	
                                                                  contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                  generalScrollPane.setViewportView(contentPane);                
                                                                  contentPane.setOpaque(true);
                                                                  

                                                                       }
                                                                   });
          
          
          JMenuItem ImprimerClientPFMenuItem = new JMenuItem("Imprimer Liste Client PF");
          ImprimerClientPFMenuItem.setMnemonic(KeyEvent.VK_A);
          ReferentielMenu.add(ImprimerClientPFMenuItem);
          ImprimerClientPFMenuItem.addActionListener(new ActionListener() {
                                                              public void actionPerformed(ActionEvent event) {
                                                              	title.setText("Imprimer Liste Client PF");
                                                              	titlePanel.add(title);
                                                              	contentPane  = new ConsulterListeClientPF();
                                                              	
                                                                  contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                  generalScrollPane.setViewportView(contentPane);                
                                                                  contentPane.setOpaque(true);
                                                                  

                                                                       }
                                                                   });
          
          
          JMenuItem AjouterArticlesClientPFMenuItem = new JMenuItem("Ajouter Articles Client PF");
          AjouterArticlesClientPFMenuItem.setMnemonic(KeyEvent.VK_A);
          ReferentielMenu.add(AjouterArticlesClientPFMenuItem);
          AjouterArticlesClientPFMenuItem.addActionListener(new ActionListener() {
                                                              public void actionPerformed(ActionEvent event) {
                                                              	title.setText("Ajouter Articles Client PF");
                                                              	titlePanel.add(title);
                                                              	contentPane  = new AjoutArticlesClientPF();
                                                              	
                                                                  contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                  generalScrollPane.setViewportView(contentPane);                
                                                                  contentPane.setOpaque(true);
                                                                  

                                                                       }
                                                                   });
          
          
          
          JMenuItem ConsulterArticlesClientPFMenuItem = new JMenuItem("Consulter Articles Client PF");
          ConsulterArticlesClientPFMenuItem.setMnemonic(KeyEvent.VK_A);
          ReferentielMenu.add(ConsulterArticlesClientPFMenuItem);
          ConsulterArticlesClientPFMenuItem.addActionListener(new ActionListener() {
                                                              public void actionPerformed(ActionEvent event) {
                                                              	title.setText("Consulter Articles Client PF");
                                                              	titlePanel.add(title);
                                                              	contentPane  = new ConsulterArticlesClientPF();
                                                              	
                                                                  contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                  generalScrollPane.setViewportView(contentPane);                
                                                                  contentPane.setOpaque(true);
                                                                  

                                                                       }
                                                                   });
          
          
   /////////////////////////////////////////////////////////////////////////////////////////////// Client Service //////////////////////////////////////////////////////////////////////////
          
          JMenuItem ajouterClientServiceMenuItem = new JMenuItem("Ajouter Client Service");
          ajouterClientServiceMenuItem.setMnemonic(KeyEvent.VK_A);
          ReferentielMenu.add(ajouterClientServiceMenuItem);
          ajouterClientServiceMenuItem.addActionListener(new ActionListener() {
                                                              public void actionPerformed(ActionEvent event) {
                                                              	title.setText("Ajouter Client Service");
                                                              	titlePanel.add(title);
                                                              	contentPane  = new AjoutClient();
                                                              	
                                                                  contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                  generalScrollPane.setViewportView(contentPane);                
                                                                  contentPane.setOpaque(true);
                                                                  

                                                                       }
                                                                   });
          
          
          JMenuItem rechercheClientServiceMenuItem = new JMenuItem("Chercher Client Service");
          rechercheClientServiceMenuItem.setMnemonic(KeyEvent.VK_A);
          ReferentielMenu.add(rechercheClientServiceMenuItem);
          rechercheClientServiceMenuItem.addActionListener(new ActionListener() {
                                                              public void actionPerformed(ActionEvent event) {
                                                              	title.setText("Chercher Client Service");
                                                              	titlePanel.add(title);
                                                              	contentPane  = new ChercherClient();
                                                              	
                                                                  contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                  generalScrollPane.setViewportView(contentPane);                
                                                                  contentPane.setOpaque(true);
                                                                  

                                                                       }
                                                                   });
          
          
  ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////        
          
          JMenuItem ajouterCompteMenuItem = new JMenuItem("Ajouter Compte");
          ajouterCompteMenuItem.setMnemonic(KeyEvent.VK_A);
          ReferentielMenu.add(ajouterCompteMenuItem);
          ajouterCompteMenuItem.addActionListener(new ActionListener() {
                                                              public void actionPerformed(ActionEvent event) {
                                                              	title.setText("Ajouter Compte");
                                                              	titlePanel.add(title);
                                                              	contentPane  = new AjoutCompte();
                                                              	
                                                                  contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                  generalScrollPane.setViewportView(contentPane);                
                                                                  contentPane.setOpaque(true);
                                                                  

                                                                       }
                                                                   });
          
          
          JMenuItem ConsulterCompteMenuItem = new JMenuItem("Consulter Compte");
          ConsulterCompteMenuItem.setMnemonic(KeyEvent.VK_A);
          ReferentielMenu.add(ConsulterCompteMenuItem);
          ConsulterCompteMenuItem.addActionListener(new ActionListener() {
                                                              public void actionPerformed(ActionEvent event) {
                                                              	title.setText("Consulter Compte");
                                                              	titlePanel.add(title);
                                                              	contentPane  = new ConsulterCompte();
                                                              	
                                                                  contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                  generalScrollPane.setViewportView(contentPane);                
                                                                  contentPane.setOpaque(true);
                                                                  

                                                                       }
                                                                   });
          
          
          
          
          JMenuItem AtouterTypeBLMenuItem = new JMenuItem("Ajouter Type BL");
          AtouterTypeBLMenuItem.setMnemonic(KeyEvent.VK_A);
          ReferentielMenu.add(AtouterTypeBLMenuItem);
          AtouterTypeBLMenuItem.addActionListener(new ActionListener() {
                                                              public void actionPerformed(ActionEvent event) {
                                                              	title.setText("Ajouter Type BL");
                                                              	titlePanel.add(title);
                                                              	contentPane  = new AjoutTypeBL();
                                                              	
                                                                  contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                  generalScrollPane.setViewportView(contentPane);                
                                                                  contentPane.setOpaque(true);
                                                                  

                                                                       }
                                                                   });   
          
          
          
          /*Menu Facture Vente
           * 
           */
          
          FacturePFMenu = new JMenu("Vente Produit Fini ");
            menubar.add(FacturePFMenu);
            
            JMenuItem AjoutFacturePFMenuItem = new JMenuItem("Saisir BL");
            AjoutFacturePFMenuItem.setMnemonic(KeyEvent.VK_A);
            FacturePFMenu.add(AjoutFacturePFMenuItem);
            AjoutFacturePFMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Saisir BL");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new AjoutFacturePF();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
           
            
            JMenuItem ConsulterFacturePFMenuItem = new JMenuItem("Consulter BL");
            ConsulterFacturePFMenuItem.setMnemonic(KeyEvent.VK_A);
            FacturePFMenu.add(ConsulterFacturePFMenuItem);
            ConsulterFacturePFMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Consulter BL");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new ConsulterFacturePF();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            
            JMenuItem AjoutFactureAutresVenteMenuItem = new JMenuItem("Saisir BL Autres Vente");
            AjoutFactureAutresVenteMenuItem.setMnemonic(KeyEvent.VK_A);
            FacturePFMenu.add(AjoutFactureAutresVenteMenuItem);
            AjoutFactureAutresVenteMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Saisir BL Autres Vente");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new AjoutFactureAutresVente();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
           
            
            JMenuItem ConsulterFactureAutresVenteMenuItem = new JMenuItem("Consulter BL Autres Vente");
            ConsulterFactureAutresVenteMenuItem.setMnemonic(KeyEvent.VK_A);
            FacturePFMenu.add(ConsulterFactureAutresVenteMenuItem);
            ConsulterFactureAutresVenteMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Consulter BL Autres Vente");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new ConsulterFactureAutresVente();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            JMenuItem ConsulterEtatFactureAutresVenteMenuItem = new JMenuItem("Consulter Etat Factures Autres ventes");
            ConsulterEtatFactureAutresVenteMenuItem.setMnemonic(KeyEvent.VK_A);
            FacturePFMenu.add(ConsulterEtatFactureAutresVenteMenuItem);
            ConsulterEtatFactureAutresVenteMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Consulter Etat Factures Autres ventes");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new EtatBLAutresVentes();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            JMenuItem EtatFacturePFMenuItem = new JMenuItem("Etat des Factures PF Par Mode Réglement");
            EtatFacturePFMenuItem.setMnemonic(KeyEvent.VK_A);
            FacturePFMenu.add(EtatFacturePFMenuItem);
            EtatFacturePFMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Etat des Factures PF Par Mode Réglement");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new EtatFacturePF();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            JMenuItem EtatFacturePFParArticleMenuItem = new JMenuItem("Etat des Factures Par Article");
            EtatFacturePFParArticleMenuItem.setMnemonic(KeyEvent.VK_A);
            FacturePFMenu.add(EtatFacturePFParArticleMenuItem);
            EtatFacturePFParArticleMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Etat des Factures Par Article");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new EtatFacturePFParArticle();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            JMenuItem EtatBLPFMenuItem = new JMenuItem("Etat des BL/Facture");
            EtatBLPFMenuItem.setMnemonic(KeyEvent.VK_A);
            FacturePFMenu.add(EtatBLPFMenuItem);
            EtatBLPFMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Etat des BL/Facture");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new EtatBLPF();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            
            JMenuItem EtatVenteParArticleMenuItem = new JMenuItem("Etat de Vente Article");
            EtatVenteParArticleMenuItem.setMnemonic(KeyEvent.VK_A);
            FacturePFMenu.add(EtatVenteParArticleMenuItem);
            EtatVenteParArticleMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Etat de Vente Article");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new EtatVenteArticle();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
       
            
            JMenuItem EtatChiffreAffaireAvecTimbreMenuItem = new JMenuItem("Etat Chiffre d'Affaire Avec Timbre");
            EtatChiffreAffaireAvecTimbreMenuItem.setMnemonic(KeyEvent.VK_A);
            FacturePFMenu.add(EtatChiffreAffaireAvecTimbreMenuItem);
            EtatChiffreAffaireAvecTimbreMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Etat Chiffre d'Affaire Avec Timbre");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new EtatChiffreAffaireAvecTimbre();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            
            
            
            
            JMenuItem EtatVentePFParFamille = new JMenuItem("Etat de Vente PF Par Famille");
            EtatVentePFParFamille.setMnemonic(KeyEvent.VK_A);
            FacturePFMenu.add(EtatVentePFParFamille);
            EtatVentePFParFamille.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Etat de Vente PF Par Famille");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new EtatVenteFamille();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            JMenuItem EtatVentePFParFamilleParFacture = new JMenuItem("Etat de Vente PF Par Famille Par Facture");
            EtatVentePFParFamilleParFacture.setMnemonic(KeyEvent.VK_A);
            FacturePFMenu.add(EtatVentePFParFamilleParFacture);
            EtatVentePFParFamilleParFacture.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Etat de Vente PF Par Famille Par Facture");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new EtatVenteFamilleParFacture();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            JMenuItem EtatVentePFParClientPF = new JMenuItem("Etat de Vente PF Par Client ");
            EtatVentePFParClientPF.setMnemonic(KeyEvent.VK_A);
            FacturePFMenu.add(EtatVentePFParClientPF);
            EtatVentePFParClientPF.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Etat de Vente PF ParClient");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new EtatVenteClient();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            JMenuItem EtatFactureArticleMenuItem = new JMenuItem("Etat Facture Article");
            EtatFactureArticleMenuItem.setMnemonic(KeyEvent.VK_A);
            FacturePFMenu.add(EtatFactureArticleMenuItem);
            EtatFactureArticleMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Etat Facture Article");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new EtatFactureArticles();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            JMenuItem AjoutHistoriqueVenteVendeurPFMenuItem = new JMenuItem("Ajouter Historique Vente Vendeur PF");
            AjoutHistoriqueVenteVendeurPFMenuItem.setMnemonic(KeyEvent.VK_A);
            FacturePFMenu.add(AjoutHistoriqueVenteVendeurPFMenuItem);
            AjoutHistoriqueVenteVendeurPFMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Ajouter Historique Vente Vendeur PF");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new AjoutHistoriqueVenteVendeurPF();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            JMenuItem ConsulterHistoriqueVenteVendeurPFMenuItem = new JMenuItem("Consulter Historique Vente Vendeur PF");
            ConsulterHistoriqueVenteVendeurPFMenuItem.setMnemonic(KeyEvent.VK_A);
            FacturePFMenu.add(ConsulterHistoriqueVenteVendeurPFMenuItem);
            ConsulterHistoriqueVenteVendeurPFMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Consulter Historique Vente Vendeur PF");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new ConsulterHistoriqueVenteVendeurPF();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            JMenuItem ImporterFactureVenteExcelMenuItem = new JMenuItem("Importer Facture Vente Excel");
            ImporterFactureVenteExcelMenuItem.setMnemonic(KeyEvent.VK_A);
            FacturePFMenu.add(ImporterFactureVenteExcelMenuItem);
            ImporterFactureVenteExcelMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Importer Facture Vente Excel");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new ImporterFacturePFExcel();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            
            JMenuItem ImporterModificationsExcelMenuItem = new JMenuItem("Importer Modification Excel");
            ImporterModificationsExcelMenuItem.setMnemonic(KeyEvent.VK_A);
            FacturePFMenu.add(ImporterModificationsExcelMenuItem);
            ImporterModificationsExcelMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Importer Modification Excel");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new ImporterModifications();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            
            
            
            JMenuItem RmplacerArticleParArticleFacturePFMenuItem = new JMenuItem("Remplacer Article Par Article");
            RmplacerArticleParArticleFacturePFMenuItem.setMnemonic(KeyEvent.VK_A);
            FacturePFMenu.add(RmplacerArticleParArticleFacturePFMenuItem);
            RmplacerArticleParArticleFacturePFMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Remplacer Article Par Article");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new RemplacerArticleParArticleFacturePF();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            
		/*
		 * JMenuItem EtatPrixMoyenMenuItem = new JMenuItem("Etat Prix Moyen");
		 * EtatPrixMoyenMenuItem.setMnemonic(KeyEvent.VK_A);
		 * FacturePFMenu.add(EtatPrixMoyenMenuItem);
		 * EtatPrixMoyenMenuItem.addActionListener(new ActionListener() { public void
		 * actionPerformed(ActionEvent event) { title.setText("Etat Prix Moyen");
		 * titlePanel.add(title); contentPane = new EtatPrixMoyenArticle();
		 * 
		 * contentPane.setPreferredSize(new
		 * Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
		 * generalScrollPane.setViewportView(contentPane); contentPane.setOpaque(true);
		 * 
		 * 
		 * } });
		 */
            
            
            //Menu Facture d'Achat Produit Fini
            
             FactureAchatMenu=new JMenu("Achat Produit Fini ");
             menubar.add(FactureAchatMenu);
              JMenuItem AjoutFactureAchatMenuItem = new JMenuItem("Saisir Facture d'Achat");
              AjoutFactureAchatMenuItem.setMnemonic(KeyEvent.VK_A);
              FactureAchatMenu.add(AjoutFactureAchatMenuItem);
              AjoutFactureAchatMenuItem.addActionListener(new ActionListener() {
                                                                  public void actionPerformed(ActionEvent event) {
                                                                  	title.setText("Saisir Facture d'Achat");
                                                                  	titlePanel.add(title);
                                                                  	contentPane  = new AjoutFactureAchat();
                                                                  	
                                                                      contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                      generalScrollPane.setViewportView(contentPane);                
                                                                      contentPane.setOpaque(true);
                                                                      

                                                                           }
                                                                       });
              
              
             
              
              JMenuItem ConsulterFactureAchatMenuItem = new JMenuItem("Consulter Facture d'Achat");
              ConsulterFactureAchatMenuItem.setMnemonic(KeyEvent.VK_A);
              FactureAchatMenu.add(ConsulterFactureAchatMenuItem);
              ConsulterFactureAchatMenuItem.addActionListener(new ActionListener() {
                                                                  public void actionPerformed(ActionEvent event) {
                                                                  	title.setText("Consulter Facture d'Achat");
                                                                  	titlePanel.add(title);
                                                                  	contentPane  = new ConsulterFactureAchat();
                                                                  	
                                                                      contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                      generalScrollPane.setViewportView(contentPane);                
                                                                      contentPane.setOpaque(true);
                                                                      

                                                                           }
                                                                       });
              
              JMenuItem ImporterFactureAchatMenuItem = new JMenuItem("Importer Facture d'Achat PF Excel");
              ImporterFactureAchatMenuItem.setMnemonic(KeyEvent.VK_A);
              FactureAchatMenu.add(ImporterFactureAchatMenuItem);
              ImporterFactureAchatMenuItem.addActionListener(new ActionListener() {
                                                                  public void actionPerformed(ActionEvent event) {
                                                                  	title.setText("Importer Facture d'Achat PF Excel");
                                                                  	titlePanel.add(title);
                                                                  	contentPane  = new ImporterFcatureAchatPFExcel();
                                                                  	
                                                                      contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                      generalScrollPane.setViewportView(contentPane);                
                                                                      contentPane.setOpaque(true);
                                                                      

                                                                           }
                                                                       });
              
              
              JMenuItem AjoutFactureRegularisationAchatMenuItem = new JMenuItem("Saisir Facture Régularisation d'Achat");
              AjoutFactureRegularisationAchatMenuItem.setMnemonic(KeyEvent.VK_A);
              FactureAchatMenu.add(AjoutFactureRegularisationAchatMenuItem);
              AjoutFactureRegularisationAchatMenuItem.addActionListener(new ActionListener() {
                                                                  public void actionPerformed(ActionEvent event) {
                                                                  	title.setText("Saisir Facture Régularisation d'Achat");
                                                                  	titlePanel.add(title);
                                                                  	contentPane  = new AjoutRegularisationFactureAchat();
                                                                  	
                                                                      contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                      generalScrollPane.setViewportView(contentPane);                
                                                                      contentPane.setOpaque(true);
                                                                      

                                                                           }
                                                                       });
              
              
             
              
              JMenuItem ConsulterFactureRegularisationAchatMenuItem = new JMenuItem("Consulter Facture Régularisation d'Achat");
              ConsulterFactureRegularisationAchatMenuItem.setMnemonic(KeyEvent.VK_A);
              FactureAchatMenu.add(ConsulterFactureRegularisationAchatMenuItem);
              ConsulterFactureRegularisationAchatMenuItem.addActionListener(new ActionListener() {
                                                                  public void actionPerformed(ActionEvent event) {
                                                                  	title.setText("Consulter Facture Régularisation d'Achat");
                                                                  	titlePanel.add(title);
                                                                  	contentPane  = new ConsulterFactureRegularisationAchat();
                                                                  	
                                                                      contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                      generalScrollPane.setViewportView(contentPane);                
                                                                      contentPane.setOpaque(true);
                                                                      

                                                                           }
                                                                       });
              
              JMenuItem AjoutFactureAvoirMenuItem = new JMenuItem("Saisir Facture d'Avoir");
              AjoutFactureAvoirMenuItem.setMnemonic(KeyEvent.VK_A);
              FactureAchatMenu.add(AjoutFactureAvoirMenuItem);
              AjoutFactureAvoirMenuItem.addActionListener(new ActionListener() {
                                                                  public void actionPerformed(ActionEvent event) {
                                                                  	title.setText("Saisir Facture d'Avoir");
                                                                  	titlePanel.add(title);
                                                                  	contentPane  = new AjoutFactureAvoirPF();
                                                                  	
                                                                      contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                      generalScrollPane.setViewportView(contentPane);                
                                                                      contentPane.setOpaque(true);
                                                                      

                                                                           }
                                                                       });
              
              
             
              
              JMenuItem ConsulterFactureAvoirMenuItem = new JMenuItem("Consulter Facture d'Avoir");
              ConsulterFactureAvoirMenuItem.setMnemonic(KeyEvent.VK_A);
              FactureAchatMenu.add(ConsulterFactureAvoirMenuItem);
              ConsulterFactureAvoirMenuItem.addActionListener(new ActionListener() {
                                                                  public void actionPerformed(ActionEvent event) {
                                                                  	title.setText("Consulter Facture d'Avoir");
                                                                  	titlePanel.add(title);
                                                                  	contentPane  = new ConsulterFactureAvoirPF();
                                                                  	
                                                                      contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                      generalScrollPane.setViewportView(contentPane);                
                                                                      contentPane.setOpaque(true);
                                                                      

                                                                           }
                                                                       });
              
              
              JMenuItem AjoutFactureAvoirClientPFMenuItem = new JMenuItem("Saisir Facture d'Avoir Client PF");
              AjoutFactureAvoirClientPFMenuItem.setMnemonic(KeyEvent.VK_A);
              FactureAchatMenu.add(AjoutFactureAvoirClientPFMenuItem);
              AjoutFactureAvoirClientPFMenuItem.addActionListener(new ActionListener() {
                                                                  public void actionPerformed(ActionEvent event) {
                                                                  	title.setText("Saisir Facture d'Avoir Client PF");
                                                                  	titlePanel.add(title);
                                                                  	contentPane  = new AjoutFactureAvoirClientPF();
                                                                  	
                                                                      contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                      generalScrollPane.setViewportView(contentPane);                
                                                                      contentPane.setOpaque(true);
                                                                      

                                                                           }
                                                                       });
              
              
             
              
              JMenuItem ConsulterFactureAvoirClientPFMenuItem = new JMenuItem("Consulter Facture d'Avoir Client PF");
              ConsulterFactureAvoirClientPFMenuItem.setMnemonic(KeyEvent.VK_A);
              FactureAchatMenu.add(ConsulterFactureAvoirClientPFMenuItem);
              ConsulterFactureAvoirClientPFMenuItem.addActionListener(new ActionListener() {
                                                                  public void actionPerformed(ActionEvent event) {
                                                                  	title.setText("Consulter Facture d'Avoir Client PF");
                                                                  	titlePanel.add(title);
                                                                  	contentPane  = new ConsulterFactureAvoirClientPF();
                                                                  	
                                                                      contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                      generalScrollPane.setViewportView(contentPane);                
                                                                      contentPane.setOpaque(true);
                                                                      

                                                                           }
                                                                       });
              
              
              
              
              
              /*
              
              JMenuItem AjoutFactureAvoirComparaisonMenuItem = new JMenuItem("Saisir Facture d'Avoir Comparaison");
              AjoutFactureAvoirComparaisonMenuItem.setMnemonic(KeyEvent.VK_A);
              FactureAchatMenu.add(AjoutFactureAvoirComparaisonMenuItem);
              AjoutFactureAvoirComparaisonMenuItem.addActionListener(new ActionListener() {
                                                                  public void actionPerformed(ActionEvent event) {
                                                                  	title.setText("Saisir Facture d'Avoir Comparaison");
                                                                  	titlePanel.add(title);
                                                                  	contentPane  = new AjoutFactureAvoirPFComparaison();
                                                                  	
                                                                      contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                      generalScrollPane.setViewportView(contentPane);                
                                                                      contentPane.setOpaque(true);
                                                                      

                                                                           }
                                                                       });
              
              
             
              
              JMenuItem ConsulterFactureAvoirComparaisonMenuItem = new JMenuItem("Consulter Facture d'Avoir Comparaison");
              ConsulterFactureAvoirComparaisonMenuItem.setMnemonic(KeyEvent.VK_A);
              FactureAchatMenu.add(ConsulterFactureAvoirComparaisonMenuItem);
              ConsulterFactureAvoirComparaisonMenuItem.addActionListener(new ActionListener() {
                                                                  public void actionPerformed(ActionEvent event) {
                                                                  	title.setText("Consulter Facture d'Avoir Comparaison");
                                                                  	titlePanel.add(title);
                                                                  	contentPane  = new ConsulterFactureAvoirPFComparaison();
                                                                  	
                                                                      contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                      generalScrollPane.setViewportView(contentPane);                
                                                                      contentPane.setOpaque(true);
                                                                      

                                                                           }
                                                                       });
         
              
              */
              
              JMenuItem AjoutReportRemiseMenuItem = new JMenuItem("Ajout Report Remise");
              AjoutReportRemiseMenuItem.setMnemonic(KeyEvent.VK_A);
              FactureAchatMenu.add(AjoutReportRemiseMenuItem);
              AjoutReportRemiseMenuItem.addActionListener(new ActionListener() {
                                                                  public void actionPerformed(ActionEvent event) {
                                                                  	title.setText("Ajout Report Remise");
                                                                  	titlePanel.add(title);
                                                                  	contentPane  = new AjoutReportRemise();
                                                                  	
                                                                      contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                      generalScrollPane.setViewportView(contentPane);                
                                                                      contentPane.setOpaque(true);
                                                                      

                                                                           }
                                                                       });
              
              
              
              JMenuItem AjoutReportMConsommableMenuItem = new JMenuItem("Ajout Report MC");
              AjoutReportMConsommableMenuItem.setMnemonic(KeyEvent.VK_A);
              FactureAchatMenu.add(AjoutReportMConsommableMenuItem);
              AjoutReportMConsommableMenuItem.addActionListener(new ActionListener() {
                                                                  public void actionPerformed(ActionEvent event) {
                                                                  	title.setText("Ajout Report MC");
                                                                  	titlePanel.add(title);
                                                                  	contentPane  = new AjoutReportMConsommable();
                                                                  	
                                                                      contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                      generalScrollPane.setViewportView(contentPane);                
                                                                      contentPane.setOpaque(true);
                                                                      

                                                                           }
                                                                       });  
              
             
              
              
              JMenuItem EtatFactureAchatPFMenuItem = new JMenuItem("Etat Facture Achat PF");
              EtatFactureAchatPFMenuItem.setMnemonic(KeyEvent.VK_A);
              FactureAchatMenu.add(EtatFactureAchatPFMenuItem);
              EtatFactureAchatPFMenuItem.addActionListener(new ActionListener() {
                                                                  public void actionPerformed(ActionEvent event) {
                                                                  	title.setText("Etat Facture Achat PF");
                                                                  	titlePanel.add(title);
                                                                  	contentPane  = new EtatFactureAchatOulmes();
                                                                  	
                                                                      contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                      generalScrollPane.setViewportView(contentPane);                
                                                                      contentPane.setOpaque(true);
                                                                      

                                                                           }
                                                                       });  
              
              
              //Menu Facture Reglement
            ReglementMenu = new JMenu("Reglement");
            menubar.add(ReglementMenu);
            
            JMenuItem ReglementMenuItem = new JMenuItem("Ajouter Reglement");
            ReglementMenuItem.setMnemonic(KeyEvent.VK_A);
            ReglementMenu.add(ReglementMenuItem);
            ReglementMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Ajouter Reglement");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new AjoutReglement();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            JMenuItem ImprimerFactureMenuItem = new JMenuItem("Imprimer Facture PF");
            ImprimerFactureMenuItem.setMnemonic(KeyEvent.VK_A);
            ReglementMenu.add(ImprimerFactureMenuItem);
            ImprimerFactureMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Imprimer Facture PF");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new ImprimerFacturePF();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            JMenuItem ImprimerFactureAutresventeMenuItem = new JMenuItem("Imprimer Facture Autres Vente");
            ImprimerFactureAutresventeMenuItem.setMnemonic(KeyEvent.VK_A);
            ReglementMenu.add(ImprimerFactureAutresventeMenuItem);
            ImprimerFactureAutresventeMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Imprimer Facture Autres Vente");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new ImprimerFactureAutresVente();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            JMenuItem ImprimerFactureMPMenuItem = new JMenuItem("Imprimer Facture MP");
            ImprimerFactureMPMenuItem.setMnemonic(KeyEvent.VK_A);
            ReglementMenu.add(ImprimerFactureMPMenuItem);
            ImprimerFactureMPMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Imprimer Facture MP");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new ImprimerFactureMP();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
            
            
            
            
            JMenuItem ImprimerFactureServiceTransportMenuItem = new JMenuItem("Imprimer Facture Service Transport");
            ImprimerFactureServiceTransportMenuItem.setMnemonic(KeyEvent.VK_A);
            ReglementMenu.add(ImprimerFactureServiceTransportMenuItem);
            ImprimerFactureServiceTransportMenuItem.addActionListener(new ActionListener() {
                                                                public void actionPerformed(ActionEvent event) {
                                                                	title.setText("Imprimer Facture Service Transport");
                                                                	titlePanel.add(title);
                                                                	contentPane  = new ImprimerFactureServiceTransport();
                                                                	
                                                                    contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                    generalScrollPane.setViewportView(contentPane);                
                                                                    contentPane.setOpaque(true);
                                                                    

                                                                         }
                                                                     });
            
     
            
            /*Menu Journee
             * 
            
            
            JourneeMenu = new JMenu("Journee");
              menubar.add(JourneeMenu);
              
              JMenuItem AjoutJourneeMenuItem = new JMenuItem("Ajouter Journee");
              AjoutJourneeMenuItem.setMnemonic(KeyEvent.VK_A);
              JourneeMenu.add(AjoutJourneeMenuItem);
              AjoutJourneeMenuItem.addActionListener(new ActionListener() {
                                                                  public void actionPerformed(ActionEvent event) {
                                                                  	title.setText("Ajouter Journee");
                                                                  	titlePanel.add(title);
                                                                  	contentPane  = new AjoutJournee();
                                                                  	
                                                                      contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                      generalScrollPane.setViewportView(contentPane);                
                                                                      contentPane.setOpaque(true);
                                                                      

                                                                           }
                                                                       });
            
        
        
          */
            
           // Menu Facture Achat MP
            
            FactureAchatMPMenu  = new JMenu("Facture Achat MP");
              menubar.add(FactureAchatMPMenu);
              
              JMenuItem AjoutFactureAchatMPMenuItem = new JMenuItem("Ajouter Facture Achat MP");
              AjoutFactureAchatMPMenuItem.setMnemonic(KeyEvent.VK_A);
              FactureAchatMPMenu.add(AjoutFactureAchatMPMenuItem);
              AjoutFactureAchatMPMenuItem.addActionListener(new ActionListener() {
                                                                  public void actionPerformed(ActionEvent event) {
                                                                  	title.setText("Ajouter Facture Achat MP");
                                                                  	titlePanel.add(title);
                                                                  	contentPane  = new AjoutFactureAchatMP();
                                                                  	
                                                                      contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                      generalScrollPane.setViewportView(contentPane);                
                                                                      contentPane.setOpaque(true);
                                                                      
                                                                  }
                                                                       });
              
              
              
              JMenuItem ConsulterFactureAchatMPMenuItem = new JMenuItem("Consulter Facture Achat MP");
              ConsulterFactureAchatMPMenuItem.setMnemonic(KeyEvent.VK_A);
              FactureAchatMPMenu.add(ConsulterFactureAchatMPMenuItem);
              ConsulterFactureAchatMPMenuItem.addActionListener(new ActionListener() {
                                                                  public void actionPerformed(ActionEvent event) {
                                                                  	title.setText("Consulter Facture Achat MP");
                                                                  	titlePanel.add(title);
                                                                  	contentPane  = new ConsulterFactureAchatMP();
                                                                  	
                                                                      contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                      generalScrollPane.setViewportView(contentPane);                
                                                                      contentPane.setOpaque(true);
                                                                      

                                                                           }
                                                                       });
              
              
              JMenuItem ImporterFactureAchatMPMenuItem = new JMenuItem("Importer Facture Achat MP Excel");
              ImporterFactureAchatMPMenuItem.setMnemonic(KeyEvent.VK_A);
              FactureAchatMPMenu.add(ImporterFactureAchatMPMenuItem);
              ImporterFactureAchatMPMenuItem.addActionListener(new ActionListener() {
                                                                  public void actionPerformed(ActionEvent event) {
                                                                  	title.setText("Importer Facture Achat MP Excel");
                                                                  	titlePanel.add(title);
                                                                  	contentPane  = new ImporterFcatureAchatMPExcel();
                                                                  	
                                                                      contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                      generalScrollPane.setViewportView(contentPane);                
                                                                      contentPane.setOpaque(true);
                                                                      

                                                                           }
                                                                       });
            
              JMenuItem AjoutFactureAvoirMPMenuItem = new JMenuItem("Ajouter Facture Avoir MP");
              AjoutFactureAvoirMPMenuItem.setMnemonic(KeyEvent.VK_A);
              FactureAchatMPMenu.add(AjoutFactureAvoirMPMenuItem);
              AjoutFactureAvoirMPMenuItem.addActionListener(new ActionListener() {
                                                                  public void actionPerformed(ActionEvent event) {
                                                                  	title.setText("Ajouter Facture Avoir MP");
                                                                  	titlePanel.add(title);
                                                                  	contentPane  = new AjoutFactureAvoirMP();
                                                                  	
                                                                      contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                      generalScrollPane.setViewportView(contentPane);                
                                                                      contentPane.setOpaque(true);
                                                                      

                                                                           }
                                                                       });
              
              
              
              JMenuItem ConsulterFactureAvoirMPMenuItem = new JMenuItem("Consulter Facture Avoir MP");
              ConsulterFactureAvoirMPMenuItem.setMnemonic(KeyEvent.VK_A);
              FactureAchatMPMenu.add(ConsulterFactureAvoirMPMenuItem);
              ConsulterFactureAvoirMPMenuItem.addActionListener(new ActionListener() {
                                                                  public void actionPerformed(ActionEvent event) {
                                                                  	title.setText("Consulter Facture Avoir MP");
                                                                  	titlePanel.add(title);
                                                                  	contentPane  = new ConsulterFactureAvoirMP();
                                                                  	
                                                                      contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                      generalScrollPane.setViewportView(contentPane);     
                                                                   
                                                                      contentPane.setOpaque(true);
                                                                      

                                                                           }
                                                                       });
              
              
              // Menu Facture Service Production
              
              FactureServiceProductionMenu  = new JMenu("Facture Service Production");
                menubar.add(FactureServiceProductionMenu);
                
                
		/*
		 * JMenuItem AjouterFactureServiceProductionMenuItem = new
		 * JMenuItem("Ajouter Facture Service Production");
		 * AjouterFactureServiceProductionMenuItem.setMnemonic(KeyEvent.VK_A);
		 * FactureServiceProductionMenu.add(AjouterFactureServiceProductionMenuItem);
		 * AjouterFactureServiceProductionMenuItem.addActionListener(new
		 * ActionListener() { public void actionPerformed(ActionEvent event) {
		 * title.setText("Ajouter Facture Service Production"); titlePanel.add(title);
		 * contentPane = new AjouterFactureServiceProduction();
		 * 
		 * contentPane.setPreferredSize(new
		 * Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
		 * generalScrollPane.setViewportView(contentPane); contentPane.setOpaque(true);
		 * 
		 * 
		 * } });
		 * 
		 */                
                
                /*
                JMenuItem ConsulterFactureServiceProductionMenuItem = new JMenuItem("Consulter Facture Service Production");
                ConsulterFactureServiceProductionMenuItem.setMnemonic(KeyEvent.VK_A);
                FactureServiceProductionMenu.add(ConsulterFactureServiceProductionMenuItem);
                ConsulterFactureServiceProductionMenuItem.addActionListener(new ActionListener() {
                                                                    public void actionPerformed(ActionEvent event) {
                                                                    	title.setText("Consulter Facture Service Production");
                                                                    	titlePanel.add(title);
                                                                    	contentPane  = new ConsulterFactureServiceProduction();
                                                                    	
                                                                        contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                        generalScrollPane.setViewportView(contentPane);                
                                                                        contentPane.setOpaque(true);
                                                                        
                                                                    }
                                                                         });*/
                
                
                JMenuItem ImprimerFactureServiceProductionMenuItem = new JMenuItem("Imprimer Facture Service Production");
                ImprimerFactureServiceProductionMenuItem.setMnemonic(KeyEvent.VK_A);
                FactureServiceProductionMenu.add(ImprimerFactureServiceProductionMenuItem);
                ImprimerFactureServiceProductionMenuItem.addActionListener(new ActionListener() {
                                                                    public void actionPerformed(ActionEvent event) {
                                                                    	title.setText("Imprimer Facture Service Production");
                                                                    	titlePanel.add(title);
                                                                    	contentPane  = new ImprimerFactureServiceProduction();
                                                                    	
                                                                        contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                        generalScrollPane.setViewportView(contentPane);   
                                                                        
                                                                        contentPane.setOpaque(true);
                                                                        
                                                                    }
                                                                         });
                
                JMenuItem AjouterFactureServiceTransportMenuItem = new JMenuItem("Ajouter Facture Service Transport");
                AjouterFactureServiceTransportMenuItem.setMnemonic(KeyEvent.VK_A);
                FactureServiceProductionMenu.add(AjouterFactureServiceTransportMenuItem);
                AjouterFactureServiceTransportMenuItem.addActionListener(new ActionListener() {
                                                                    public void actionPerformed(ActionEvent event) {
                                                                    	title.setText("Ajouter Facture Service Transport");
                                                                    	titlePanel.add(title);
                                                                    	contentPane  = new AjoutFactureServiceTransport();
                                                                    	
                                                                        contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                        generalScrollPane.setViewportView(contentPane);   
                                                                        
                                                                        contentPane.setOpaque(true);
                                                                        
                                                                    }
                                                                         });
                
                JMenuItem ConsulterFactureServiceTransportMenuItem = new JMenuItem("Consulter Facture Service Transport");
                ConsulterFactureServiceTransportMenuItem.setMnemonic(KeyEvent.VK_A);
                FactureServiceProductionMenu.add(ConsulterFactureServiceTransportMenuItem);
                ConsulterFactureServiceTransportMenuItem.addActionListener(new ActionListener() {
                                                                    public void actionPerformed(ActionEvent event) {
                                                                    	title.setText("Consulter Facture Service Transport");
                                                                    	titlePanel.add(title);
                                                                    	contentPane  = new ConsulterFactureServiceTransport();
                                                                    	
                                                                        contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                        generalScrollPane.setViewportView(contentPane);   
                                                                        
                                                                        contentPane.setOpaque(true);
                                                                        
                                                                    }
                                                                         });
                
                
                
                
                
                
              
                // Menu Facture Etats De Verification
                
                EtatsDeVerificationMenu  = new JMenu("Etats De Vérification");
                  menubar.add(EtatsDeVerificationMenu);
                  
                  
                  
                  JMenu EtatPrixMoyenStockInitial = new JMenu("Etats Prix Moyen Stock Initial");
                  EtatPrixMoyenStockInitial.setMnemonic(KeyEvent.VK_A);
                  EtatsDeVerificationMenu.add(EtatPrixMoyenStockInitial);
                  
                  
                  
                  JMenuItem EtatPrixMoyenStockInitialPFMenuItem = new JMenuItem("Etats Prix Moyen Stock Initial PF / Sous Famille");
                  EtatPrixMoyenStockInitialPFMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatPrixMoyenStockInitial.add(EtatPrixMoyenStockInitialPFMenuItem);
                  EtatPrixMoyenStockInitialPFMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Etats Prix Moyen Stock Initial PF");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new EtatPrixMoyenStockInitialPF();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);   
                                                                          
                                                                          contentPane.setOpaque(true);
                                                                          
                                                                      }
                                                                           }); 
                  
                  JMenuItem EtatPrixMoyenStockInitialPFArticleMenuItem = new JMenuItem("Etats Prix Moyen Stock Initial PF / Article");
                  EtatPrixMoyenStockInitialPFArticleMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatPrixMoyenStockInitial.add(EtatPrixMoyenStockInitialPFArticleMenuItem);
                  EtatPrixMoyenStockInitialPFArticleMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Etats Prix Moyen Stock Initial PF / Article");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new EtatPrixMoyenInitialArticle();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);   
                                                                          
                                                                          contentPane.setOpaque(true);
                                                                          
                                                                      }
                                                                           }); 
                  
                  JMenuItem EtatPrixMoyenStockInitialMPMenuItem = new JMenuItem("Etats Prix Moyen Stock Initial MP");
                  EtatPrixMoyenStockInitialMPMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatPrixMoyenStockInitial.add(EtatPrixMoyenStockInitialMPMenuItem);
                  EtatPrixMoyenStockInitialMPMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Etats Prix Moyen Stock Initial MP");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new EtatPrixMoyenStockInitialMP();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);   
                                                                          
                                                                          contentPane.setOpaque(true);
                                                                          
                                                                      }
                                                                           }); 
                  JMenuItem EtatPrixMoyenStockInitialMPParMPMenuItem = new JMenuItem("Etats Prix Moyen Stock Initial MP / Article");
                  EtatPrixMoyenStockInitialMPParMPMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatPrixMoyenStockInitial.add(EtatPrixMoyenStockInitialMPParMPMenuItem);
                  EtatPrixMoyenStockInitialMPParMPMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Etats Prix Moyen Stock Initial MP / Article");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new EtatPrixMoyenStockInitialMPParMP();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);   
                                                                          
                                                                          contentPane.setOpaque(true);
                                                                          
                                                                      }
                                                                           }); 
                  
                  
                  
                  
                 
                  JMenu EtatPrixMoyenVentePF = new JMenu("Etats Prix Moyen Vente PF");
                  EtatPrixMoyenVentePF.setMnemonic(KeyEvent.VK_A);
                  EtatsDeVerificationMenu.add(EtatPrixMoyenVentePF);
                  
                  
                  
                  JMenuItem EtatPrixMoyenneArticleMenuItem = new JMenuItem("Etat Prix Moyenne Vente Article PF");
                  EtatPrixMoyenneArticleMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatPrixMoyenVentePF.add(EtatPrixMoyenneArticleMenuItem);
                  EtatPrixMoyenneArticleMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Etat Prix Moyenne Vente Article PF");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new EtatPrixMoyenArticle();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);   
                                                                          
                                                                          contentPane.setOpaque(true);
                                                                          
                                                                      }
                                                                           });   
                  
                  
                  
                  JMenuItem EtatVenteParFamilleArticleMenuItem = new JMenuItem("Etat Moyen de Vente Sous Famille Article PF");
                  EtatVenteParFamilleArticleMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatPrixMoyenVentePF.add(EtatVenteParFamilleArticleMenuItem);
                  EtatVenteParFamilleArticleMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Etat Moyen de Vente Sous Famille Article PF");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new EtatVenteFamilleArticle();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);                
                                                                          contentPane.setOpaque(true);
                                                                          

                                                                               }
                                                                           });
                  
                  
                  
                  
                  JMenu EtatPrixMoyenAchatPF = new JMenu("Etats Prix Moyen Achat PF");
                  EtatPrixMoyenAchatPF.setMnemonic(KeyEvent.VK_A);
                  EtatsDeVerificationMenu.add(EtatPrixMoyenAchatPF);
                  
                  JMenuItem EtatPrixMoyenneAchatArticleMenuItem = new JMenuItem("Etat Prix Moyenne Achat Article PF");
                  EtatPrixMoyenneAchatArticleMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatPrixMoyenAchatPF.add(EtatPrixMoyenneAchatArticleMenuItem);
                  EtatPrixMoyenneAchatArticleMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Etat Prix Moyenne Achat Article PF");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new EtatPrixMoyenAchatArticle();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);   
                                                                          
                                                                          contentPane.setOpaque(true);
                                                                          
                                                                      }
                                                                           });    
                  
                  
                  JMenuItem EtatAchatParFamilleArticleMenuItem = new JMenuItem("Etat Moyen d'Achat Famille Article PF " );
                  EtatAchatParFamilleArticleMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatPrixMoyenAchatPF.add(EtatAchatParFamilleArticleMenuItem);
                  EtatAchatParFamilleArticleMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Etat d'Achat Famille Article PF");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new EtatAchatFamilleArticle();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);                
                                                                          contentPane.setOpaque(true);
                                                                          

                                                                               }
                                                                           });
                  
                  
                  JMenu EtatPrixMoyenAchatMP = new JMenu("Etats Prix Moyen Achat MP");
                  EtatPrixMoyenAchatMP.setMnemonic(KeyEvent.VK_A);
                  EtatsDeVerificationMenu.add(EtatPrixMoyenAchatMP);
                  
                  
                  JMenuItem EtatPrixMoyenneAchatMPMenuItem = new JMenuItem("Prix Moyen Des Achats MP ");
                  EtatPrixMoyenneAchatMPMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatPrixMoyenAchatMP.add(EtatPrixMoyenneAchatMPMenuItem);
                  EtatPrixMoyenneAchatMPMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Prix Moyen Des Achats MP");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new PrixMoyenDesAchatsMP();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);   
                                                                          
                                                                          contentPane.setOpaque(true);
                                                                          
                                                                      }
                                                                           });  
                  
                  
      
                  
                  JMenuItem EtatMoyenStockMpMenuItem = new JMenuItem("Etat Prix Moyen (Stock Initial + Achats ) / MP");
                  EtatMoyenStockMpMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatPrixMoyenAchatMP.add(EtatMoyenStockMpMenuItem);
                  EtatMoyenStockMpMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Etat Prix Moyen (Stock Initial + Achats ) / MP");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new MoyenStockMP();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);                
                                                                          contentPane.setOpaque(true);
                                                                          

                                                                               }
                                                                           }); 
                  
                  
                  JMenuItem EtatMoyenStockMpSousFamilleMenuItem = new JMenuItem("Etat Prix Moyen (Stock Initial + Achats ) / Sous Famille");
                  EtatMoyenStockMpSousFamilleMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatPrixMoyenAchatMP.add(EtatMoyenStockMpSousFamilleMenuItem);
                  EtatMoyenStockMpSousFamilleMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Etat Prix Moyen (Stock Initial + Achats ) / Sous Famille");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new MoyenStockMPParSousFamille();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);                
                                                                          contentPane.setOpaque(true);
                                                                          

                                                                               }
                                                                           }); 
                  
                  
                  JMenu EtatQuantiteMPTransforme = new JMenu("ETAT Quantite MP Transforme");
                  EtatQuantiteMPTransforme.setMnemonic(KeyEvent.VK_A);
                  EtatsDeVerificationMenu.add(EtatQuantiteMPTransforme);
                  
                  JMenuItem EtatQuantiteMPTransformerParMPMenuItem = new JMenuItem("ETAT Quantite MP Transforme/MP");
                  EtatQuantiteMPTransformerParMPMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatQuantiteMPTransforme.add(EtatQuantiteMPTransformerParMPMenuItem);
                  EtatQuantiteMPTransformerParMPMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("ETAT Quantite MP Transforme/MP");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new ConsulterEtatQuantiteMPTransforme();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);                
                                                                          contentPane.setOpaque(true);
                                                                          

                                                                               }
                                                                           }); 
              
                  JMenuItem EtatQuantiteMPTransformerParSousFamilleMenuItem = new JMenuItem("ETAT Quantite MP Transforme/Sous Famille");
                  EtatQuantiteMPTransformerParSousFamilleMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatQuantiteMPTransforme.add(EtatQuantiteMPTransformerParSousFamilleMenuItem);
                  EtatQuantiteMPTransformerParSousFamilleMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("ETAT Quantite MP Transforme/Sous Famille");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new ConsulterEtatQuantiteMPTransformeParSousFamille();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);                
                                                                          contentPane.setOpaque(true);
                                                                          

                                                                               }
                                                                           }); 
                
                  
                  JMenu EtatPrixMoyenProductionPF = new JMenu("Etats Prix Moyen Production PF");
                  EtatPrixMoyenProductionPF.setMnemonic(KeyEvent.VK_A);
                  EtatsDeVerificationMenu.add(EtatPrixMoyenProductionPF);
                 
                 
                  
                  JMenuItem EtatServiceParFamilleArticleMenuItem = new JMenuItem("Etat Prix Moyen de Service Famille Article");
                  EtatServiceParFamilleArticleMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatPrixMoyenProductionPF.add(EtatServiceParFamilleArticleMenuItem);
                  EtatServiceParFamilleArticleMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Etat Prix Moyen de Service Famille Article");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new EtatServiceFamilleArticle();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);                
                                                                          contentPane.setOpaque(true);
                                                                          

                                                                               }
                                                                           }); 
                  
                  
                  
                  
                 
                    
                 
                  
                  JMenuItem EtatMoyenStockPFProductionMenuItem = new JMenuItem("Etat Prix Moyen Cout Production");
                  EtatMoyenStockPFProductionMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatPrixMoyenProductionPF.add(EtatMoyenStockPFProductionMenuItem);
                  EtatMoyenStockPFProductionMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Etat Prix Moyen Cout Production");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new MoyenStockPF();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);                
                                                                          contentPane.setOpaque(true);
                                                                          

                                                                               }
                                                                           }); 
                  
                  
                  
                  JMenuItem EtatPrixMoyenValeurisationProductionParSousFamilleMenuItem = new JMenuItem("Etat Prix Moyen Valeurisation Production Par Sous Famille");
                  EtatPrixMoyenValeurisationProductionParSousFamilleMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatPrixMoyenProductionPF.add(EtatPrixMoyenValeurisationProductionParSousFamilleMenuItem);
                  EtatPrixMoyenValeurisationProductionParSousFamilleMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Etat Prix Moyen Valeurisation Production Par Sous Famille");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new EtatMoyenValeurisationProductionParSousFamille();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);                
                                                                          contentPane.setOpaque(true);
                                                                          

                                                                               }
                                                                           }); 
                  
                  
                  
                  
                  
                  
                  
                  JMenuItem EtatMoyenValeurisationProductionMenuItem = new JMenuItem("Etat Prix Moyen Valeurisation Production Par Article");
                  EtatMoyenValeurisationProductionMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatPrixMoyenProductionPF.add(EtatMoyenValeurisationProductionMenuItem);
                  EtatMoyenValeurisationProductionMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Etat Prix Moyen Valeurisation Production Par Article");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new EtatMoyenValeurisationProduction();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);                
                                                                          contentPane.setOpaque(true);
                                                                          

                                                                               }
                                                                           }); 
                  
                  
                  
                
                  
                  JMenu EtatPrixMoyenAvoirPF = new JMenu("Etats Prix Moyen Avoir PF");
                  EtatPrixMoyenAvoirPF.setMnemonic(KeyEvent.VK_A);
                  EtatsDeVerificationMenu.add(EtatPrixMoyenAvoirPF);
                  
                  
                  
                  JMenuItem EtatPrixMoyenneAvoirMenuItem = new JMenuItem("Etat Prix Moyenne Avoir Article PF");
                  EtatPrixMoyenneAvoirMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatPrixMoyenAvoirPF.add(EtatPrixMoyenneAvoirMenuItem);
                  EtatPrixMoyenneAvoirMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Etat Prix Moyenne Avoir Article PF");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new EtatPrixMoyenAvoirArticle();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);   
                                                                          
                                                                          contentPane.setOpaque(true);
                                                                          
                                                                      }
                                                                           }); 
                  
                  JMenuItem EtatPrixMoyenneAvoirSousFamilleMenuItem = new JMenuItem("Etat Prix Moyenne Avoir Sous Famille");
                  EtatPrixMoyenneAvoirSousFamilleMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatPrixMoyenAvoirPF.add(EtatPrixMoyenneAvoirSousFamilleMenuItem);
                  EtatPrixMoyenneAvoirSousFamilleMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Etat Prix Moyenne Avoir Sous Famille");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new EtatPrixMoyenAvoirFamilleArticle();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);   
                                                                          
                                                                          contentPane.setOpaque(true);
                                                                          
                                                                      }
                                                                           }); 
                  
                  
                  
                  
                  
                  JMenu EtatValeurDeStockSystemMenu = new JMenu("Consulter Etat Valeur Stock System");
                  EtatValeurDeStockSystemMenu.setMnemonic(KeyEvent.VK_A);
                  EtatsDeVerificationMenu.add(EtatValeurDeStockSystemMenu);  
                  
                  
                  JMenuItem EtatValeurSystemkMenuItem = new JMenuItem("Consulter Etat Valeur System");
                  EtatValeurSystemkMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatValeurDeStockSystemMenu.add(EtatValeurSystemkMenuItem);
                  EtatValeurSystemkMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Consulter Etat Valeur System");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new ConsulterEtatValeurSystem();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);   
                                                                          
                                                                          contentPane.setOpaque(true);
                                                                          
                                                                      }
                                                                           });        
                  
                  JMenuItem EtatmargeparArticleMenuItem = new JMenuItem("Consulter Etat Marge Par Article Et Global");
                  EtatmargeparArticleMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatValeurDeStockSystemMenu.add(EtatmargeparArticleMenuItem);
                  EtatmargeparArticleMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Consulter Etat Marge Par Article Et Global");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new ConsulterEtatMargeStockPF();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);   
                                                                          
                                                                          contentPane.setOpaque(true);
                                                                          
                                                                      }
                                                                           });  
                  
                  
                  
                  
             
                  
                  
                  
                  JMenu EtatValeurisationDeStockMenu = new JMenu("Valeurisation Etat Stock");
                  EtatValeurisationDeStockMenu.setMnemonic(KeyEvent.VK_A);
                  EtatsDeVerificationMenu.add(EtatValeurisationDeStockMenu);  
                  
                  JMenuItem EtatValeurisationEtatStockParSousFamilleMenuItem = new JMenuItem("Valeurisation Etat Stock / Sous Famille");
                  EtatValeurisationEtatStockParSousFamilleMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatValeurisationDeStockMenu.add(EtatValeurisationEtatStockParSousFamilleMenuItem);
                  EtatValeurisationEtatStockParSousFamilleMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Valeurisation Etat Stock / Sous Famille");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new ConsulterValeurisationEtatStockParSousFamille();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);   
                                                                          
                                                                          contentPane.setOpaque(true);
                                                                          
                                                                      }
                                                                           });  
                  
                   
                  
                  JMenuItem EtatValeurisationEtatStockParArticleMenuItem = new JMenuItem("Valeurisation Etat Stock / Articles");
                  EtatValeurisationEtatStockParArticleMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatValeurisationDeStockMenu.add(EtatValeurisationEtatStockParArticleMenuItem);
                  EtatValeurisationEtatStockParArticleMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Valeurisation Etat Stock / Articles");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new ConsulterValeurisationEtatStockParArticles();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);   
                                                                          
                                                                          contentPane.setOpaque(true);
                                                                          
                                                                      }
                                                                           });  
                  
                  
                  JMenuItem EtatMargeStockStockParArticleMenuItem = new JMenuItem("La Marge De Stock / Articles");
                  EtatMargeStockStockParArticleMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatValeurisationDeStockMenu.add(EtatMargeStockStockParArticleMenuItem);
                  EtatMargeStockStockParArticleMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("La Marge De Stock / Articles");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new ConsulterMargeDeStockArticlePF();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);   
                                                                          
                                                                          contentPane.setOpaque(true);
                                                                          
                                                                      }
                                                                           }); 
                  
                  
                  JMenuItem EtatMargeStockStockParSousFamilleMenuItem = new JMenuItem("La Marge De Stock / Sous Famille");
                  EtatMargeStockStockParSousFamilleMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatValeurisationDeStockMenu.add(EtatMargeStockStockParSousFamilleMenuItem);
                  EtatMargeStockStockParSousFamilleMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("La Marge De Stock / Sous Famille");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new ConsulterMargeDeStockSousFamille();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);   
                                                                          
                                                                          contentPane.setOpaque(true);
                                                                          
                                                                      }
                                                                           }); 
                  
                  
                  
                  
                  // Menu Des Etats
                  
                
                  
                  EtatsMenu  = new JMenu("Etats Factures");
                    menubar.add(EtatsMenu);    
                  
                  JMenuItem ConsulterEtatFacturePFMenuItem = new JMenuItem("Consulter Etat Facture De Vente PF");
                  ConsulterEtatFacturePFMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatsMenu.add(ConsulterEtatFacturePFMenuItem);
                  ConsulterEtatFacturePFMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Consulter Etat De Vente PF");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new ConsulterEtatVentePF();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);                
                                                                          contentPane.setOpaque(true);
                                                                          

                                                                               }
                                                                           });   
                  
                  
                
                
                JMenuItem ConsulterEtatHistoriqueVenteVendeurPFMenuItem = new JMenuItem("Consulter Etat Historique Vente Vendeur PF");
                ConsulterEtatHistoriqueVenteVendeurPFMenuItem.setMnemonic(KeyEvent.VK_A);
                EtatsMenu.add(ConsulterEtatHistoriqueVenteVendeurPFMenuItem);
                ConsulterEtatHistoriqueVenteVendeurPFMenuItem.addActionListener(new ActionListener() {
                                                                    public void actionPerformed(ActionEvent event) {
                                                                    	title.setText("Consulter Etat Historique Vente Vendeur PF");
                                                                    	titlePanel.add(title);
                                                                    	contentPane  = new ConsulterEtatHistoriqueVenteVendeurPF();
                                                                    	
                                                                        contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                        generalScrollPane.setViewportView(contentPane);                
                                                                        contentPane.setOpaque(true);
                                                                        

                                                                             }
                                                                         });      
                  
                  
                  
                  JMenuItem EtatFactureAchatarticleMenuItem = new JMenuItem("Etat Facture Achat Article");
                  EtatFactureAchatarticleMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatsMenu.add(EtatFactureAchatarticleMenuItem);
                  EtatFactureAchatarticleMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Etat Facture Achat Article");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new EtatFactureAchatArticles();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);                
                                                                          contentPane.setOpaque(true);
                                                                          

                                                                               }
                                                                           });  
                  
                  JMenuItem EtatFactureServiceProductionDetailMenuItem = new JMenuItem("Etat Facture Service Production / Details");
                  EtatFactureServiceProductionDetailMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatsMenu.add(EtatFactureServiceProductionDetailMenuItem);
                  EtatFactureServiceProductionDetailMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Etat Facture Service Production / Details");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new EtatFacturesServiceDetaile();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);   
                                                                          
                                                                          contentPane.setOpaque(true);
                                                                          
                                                                      }
                                                                           });
                  
                  
                  JMenuItem EtatFactureServiceProductionDetailAvecSousTautauxMenuItem = new JMenuItem("Etat Facture Service Production / Details Avec Sous Tataux");
                  EtatFactureServiceProductionDetailAvecSousTautauxMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatsMenu.add(EtatFactureServiceProductionDetailAvecSousTautauxMenuItem);
                  EtatFactureServiceProductionDetailAvecSousTautauxMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Etat Facture Service Production / Details Avec Sous Tataux");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new EtatFacturesServiceDetaileAvecSousTautaux();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);   
                                                                          
                                                                          contentPane.setOpaque(true);
                                                                          
                                                                      }
                                                                           });
                  
                  JMenuItem EtatTonnageTotalProductionMenuItem = new JMenuItem("Etat De Tonnage Production El Nass Tea Packing ");
                  EtatTonnageTotalProductionMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatsMenu.add(EtatTonnageTotalProductionMenuItem);
                  EtatTonnageTotalProductionMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Etat De Tonnage Production El Nass Tea Packing ");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new EtatTonnageProductionParMoisParMagasin();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);   
                                                                          
                                                                          contentPane.setOpaque(true);
                                                                          
                                                                      }
                                                                           });
                  
                  JMenuItem EtatFactureServiceProductionMenuItem = new JMenuItem("Etat Facture Service Production");
                  EtatFactureServiceProductionMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatsMenu.add(EtatFactureServiceProductionMenuItem);
                  EtatFactureServiceProductionMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Etat Facture Service Production");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new EtatFacturesService();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);   
                                                                          
                                                                          contentPane.setOpaque(true);
                                                                          
                                                                      }
                                                                           });
                  
                  
                  JMenuItem EtatFactureServiceProductionParOFMenuItem = new JMenuItem("Etat Facture Service Production Par OF");
                  EtatFactureServiceProductionParOFMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatsMenu.add(EtatFactureServiceProductionParOFMenuItem);
                  EtatFactureServiceProductionParOFMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Etat Facture Service Production Par OF");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new EtatFacturesServiceParOF();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);   
                                                                          
                                                                          contentPane.setOpaque(true);
                                                                          
                                                                      }
                                                                           });
                  
                  
                  
                  JMenuItem EtatFactureAchatMPMenuItem = new JMenuItem("Consulter Etat Facture Achat MP");
                  EtatFactureAchatMPMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatsMenu.add(EtatFactureAchatMPMenuItem);
                  EtatFactureAchatMPMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Consulter Etat Facture Achat MP");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new ConsulterEtatFactureAchatMP();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);   
                                                                          
                                                                          contentPane.setOpaque(true);
                                                                          
                                                                      }
                                                                           });
                  
                  JMenu EtatValeurDeStockMenu = new JMenu("Consulter Etat Valeur Stock");
                  EtatValeurDeStockMenu.setMnemonic(KeyEvent.VK_A);
                  EtatsMenu.add(EtatValeurDeStockMenu);       
                  
                  
                  
                  JMenuItem consultermargeavantproductionMenuItem = new JMenuItem("Consulter Marge Avant Production");
                  consultermargeavantproductionMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatValeurDeStockMenu.add(consultermargeavantproductionMenuItem);
                  consultermargeavantproductionMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Consulter Marge Avant Production");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new ConsulterDeMargeAvantProduction();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);                
                                                                          contentPane.setOpaque(true);
                                                                          

                                                                               }
                                                                           }); 
                  
                  
                  
                  
                  JMenuItem EtatValeurStockMenuItem = new JMenuItem("Consulter Valeurisation");
                  EtatValeurStockMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatValeurDeStockMenu.add(EtatValeurStockMenuItem);
                  EtatValeurStockMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Consulter Valeurisation");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new ConsulterEtatDeValeurStock();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);   
                                                                          
                                                                          contentPane.setOpaque(true);
                                                                          
                                                                      }
                                                                           });              
                  
                  
                  JMenuItem EtatMargeStockMenuItem = new JMenuItem("Consulter La Marge");
                  EtatMargeStockMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatValeurDeStockMenu.add(EtatMargeStockMenuItem);
                  EtatMargeStockMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Consulter La Marge");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new ConsulterEtatDeMargeStock();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);   
                                                                          
                                                                          contentPane.setOpaque(true);
                                                                          
                                                                      }
                                                                           });   
                  
                  
                  JMenuItem EtatCoutMoyenStockMenuItem = new JMenuItem("Consulter Le Cout Moyen");
                  EtatCoutMoyenStockMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatValeurDeStockMenu.add(EtatCoutMoyenStockMenuItem);
                  EtatCoutMoyenStockMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Consulter Le Cout Moyen");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new ConsulterEtatCoutMoyen();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);   
                                                                          
                                                                          contentPane.setOpaque(true);
                                                                          
                                                                      }
                                                                           }); 
                  
                  
                  
                      
                  
                  
                  JMenu EtatStockInitial = new JMenu("Etats Stock Initial");
                  EtatStockInitial.setMnemonic(KeyEvent.VK_A);
                  EtatsMenu.add(EtatStockInitial);
                  
                  JMenuItem EtatStockInitialPFMenuItem = new JMenuItem("Etat Initial PF");
                  EtatStockInitialPFMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatStockInitial.add(EtatStockInitialPFMenuItem);
                  EtatStockInitialPFMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Etat Initial PF");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new ConsulterEtatInitialStockPF();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);                
                                                                          contentPane.setOpaque(true);
                                                                          

                                                                               }
                                                                           }); 
                  
                  
                  JMenuItem EtatStockInitialMPMenuItem = new JMenuItem("Etat Initial MP");
                  EtatStockInitialMPMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatStockInitial.add(EtatStockInitialMPMenuItem);
                  EtatStockInitialMPMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Etat Initial MP");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new ConsulterEtatStockInitialMP();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);                
                                                                          contentPane.setOpaque(true);
                                                                          

                                                                               }
                                                                           }); 
                  
                  
    
                  JMenuItem EtatFactureAvoirPFMenuItem = new JMenuItem("Etat Facture Avoir PF");
                  EtatFactureAvoirPFMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatsMenu.add(EtatFactureAvoirPFMenuItem);
                  EtatFactureAvoirPFMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Etat Facture Avoir PF");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new EtatFactureAvoirPF();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);                
                                                                          contentPane.setOpaque(true);
                                                                          

                                                                               }
                                                                           }); 
                  
                  JMenuItem EtatChiffreAffaireClientMenuItem = new JMenuItem("Etat Chiffre D'Affaire Client PF Avec Ou Sans ICE");
                  EtatChiffreAffaireClientMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatsMenu.add(EtatChiffreAffaireClientMenuItem);
                  EtatChiffreAffaireClientMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Etat Chiffre D'Affaire Client PF Avec Ou Sans ICE");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new ConsulterEtatChiffeAffaireClient();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);                
                                                                          contentPane.setOpaque(true);
                                                                          

                                                                               }
                                                                           }); 
                  
                  
                  
                  JMenu EtatCompta = new JMenu("Etats Comptabilité");
                  EtatCompta.setMnemonic(KeyEvent.VK_A);
                  EtatsMenu.add(EtatCompta);
                  
                  JMenuItem EtatFactureClientICEMenuItem = new JMenuItem("Etat Facture Client Avec ou Sans ICE");
                  EtatFactureClientICEMenuItem.setMnemonic(KeyEvent.VK_A);
                  EtatCompta.add(EtatFactureClientICEMenuItem);
                  EtatFactureClientICEMenuItem.addActionListener(new ActionListener() {
                                                                      public void actionPerformed(ActionEvent event) {
                                                                      	title.setText("Etat Facture Client Avec ou Sans ICE");
                                                                      	titlePanel.add(title);
                                                                      	contentPane  = new EtatFactureAvecOuSansICE();
                                                                      	
                                                                          contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                          generalScrollPane.setViewportView(contentPane);                
                                                                          contentPane.setOpaque(true);
                                                                          

                                                                               }
                                                                           }); 
                  
            
                  
   // Menu Administration
                 
                  AdministrationMenu  = new JMenu("Administration");
                    menubar.add(AdministrationMenu);           
                   
                    JMenuItem TransfertFactureEnBLMenuItem = new JMenuItem("Transfert Les factures En BL");
                    TransfertFactureEnBLMenuItem.setMnemonic(KeyEvent.VK_A);
                    AdministrationMenu.add(TransfertFactureEnBLMenuItem);
                    TransfertFactureEnBLMenuItem.addActionListener(new ActionListener() {
                                                                        public void actionPerformed(ActionEvent event) {
                                                                        	title.setText("Transfert Les factures En BL");
                                                                        	titlePanel.add(title);
                                                                        	contentPane  = new TransfertFactureEnBL();
                                                                        	
                                                                            contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                            generalScrollPane.setViewportView(contentPane);   
                                                                            
                                                                            contentPane.setOpaque(true);
                                                                            
                                                                        }
                                                                             });
                    
                    JMenuItem HistoriqueTransfertFactureEnBLMenuItem = new JMenuItem("Historique De Transfert Les factures En BL");
                    HistoriqueTransfertFactureEnBLMenuItem.setMnemonic(KeyEvent.VK_A);
                    AdministrationMenu.add(HistoriqueTransfertFactureEnBLMenuItem);
                    HistoriqueTransfertFactureEnBLMenuItem.addActionListener(new ActionListener() {
                                                                        public void actionPerformed(ActionEvent event) {
                                                                        	title.setText("Historique De Transfert Les factures En BL");
                                                                        	titlePanel.add(title);
                                                                        	contentPane  = new HistoriqueDeTransfertFactureEnBL();
                                                                        	
                                                                            contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                            generalScrollPane.setViewportView(contentPane);   
                                                                            
                                                                            contentPane.setOpaque(true);
                                                                            
                                                                        }
                                                                             });
                    
                  
                    JMenuItem TransfertFactureAvoirClientPFEnBLMenuItem = new JMenuItem("Transfert Les factures Avoir Client PF En BL");
                    TransfertFactureAvoirClientPFEnBLMenuItem.setMnemonic(KeyEvent.VK_A);
                    AdministrationMenu.add(TransfertFactureAvoirClientPFEnBLMenuItem);
                    TransfertFactureAvoirClientPFEnBLMenuItem.addActionListener(new ActionListener() {
                                                                        public void actionPerformed(ActionEvent event) {
                                                                        	title.setText("Transfert Les factures Avoir Client PF En BL");
                                                                        	titlePanel.add(title);
                                                                        	contentPane  = new TransfertFactureAvoirClientPFEnBL();
                                                                        	
                                                                            contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                            generalScrollPane.setViewportView(contentPane);   
                                                                            
                                                                            contentPane.setOpaque(true);
                                                                            
                                                                        }
                                                                             });
                    
                    JMenuItem HistoriqueTransfertFactureAvoirClientPFEnBLMenuItem = new JMenuItem("Historique De Transfert Les factures Avoir Client PF En BL");
                    HistoriqueTransfertFactureAvoirClientPFEnBLMenuItem.setMnemonic(KeyEvent.VK_A);
                    AdministrationMenu.add(HistoriqueTransfertFactureAvoirClientPFEnBLMenuItem);
                    HistoriqueTransfertFactureAvoirClientPFEnBLMenuItem.addActionListener(new ActionListener() {
                                                                        public void actionPerformed(ActionEvent event) {
                                                                        	title.setText("Historique De Transfert Les factures Avoir Client PF En BL");
                                                                        	titlePanel.add(title);
                                                                        	contentPane  = new HistoriqueDeTransfertFactureAvoirClientPFEnBL();
                                                                        	
                                                                            contentPane.setPreferredSize(new Dimension((int)dim.getWidth()+100,(int)dim.getHeight()-70));
                                                                            generalScrollPane.setViewportView(contentPane);   
                                                                            
                                                                            contentPane.setOpaque(true);
                                                                            
                                                                        }
                                                                             });     
                    
                
                  
            
        setJMenuBar(menubar);
        autoriseMenuUtilisateur();
       
            
      //  depotMenu.setVisible(false);
    }
 
 public void moveToTheNewWindow() {
		this.dispose();
		AuthentificationView authentification = new AuthentificationView();
		authentification.setVisible(true);
		authentification.setLocationRelativeTo(null);
	}
 
 void autoriseMenuUtilisateur(){
	 
	 List<Habilitation> listHabilitation=habilitationDAO.findHabilitationByUtilisateur(utilisateur.getId());
	 
	 for(int i=0;i<listHabilitation.size();i++){
		 Habilitation habilitation =listHabilitation.get(i);
		 
		 if(habilitation.getMenu().getCode().equals(MENU_DEPOT)){
			 depotMenu.setVisible(habilitation.isAutorise());
		 }
		 
		 if(habilitation.getMenu().getCode().equals(MENU_MATIERE_PREMIERE)){
			 MatierePremierMenu.setVisible(habilitation.isAutorise());
		 }
		 if(habilitation.getMenu().getCode().equals(MENU_ARTICLE)){
			 articleMenu.setVisible(habilitation.isAutorise());
		 }
		 if(habilitation.getMenu().getCode().equals(MENU_STOCK_MATIERE_PREMIERE)){
			 stockMPMenu.setVisible(habilitation.isAutorise());
		 }
		 if(habilitation.getMenu().getCode().equals(MENU_MACHINE)){
			 uniteFabMenu.setVisible(habilitation.isAutorise());
		 }
		 if(habilitation.getMenu().getCode().equals(MENU_EQUIPE)){
			 equipeFabMenu.setVisible(habilitation.isAutorise());
		 }
		 if(habilitation.getMenu().getCode().equals(MENU_ORDRE_FABRICATION)){
			 ordreFabMenu.setVisible(habilitation.isAutorise());
		 }
		 if(habilitation.getMenu().getCode().equals(MENU_STOCK_PRODUIT_FINI)){
			 stockPFMenu.setVisible(habilitation.isAutorise());
		 }
		 if(habilitation.getMenu().getCode().equals(MENU_PARAMETRE)){
			 parametreMenu.setVisible(habilitation.isAutorise());
		 }
		
		 
		 if(habilitation.getMenu().getCode().equals(MENU_fournisseur)){
			 fournisseurMenu.setVisible(habilitation.isAutorise());
		 }
		 
		 if(habilitation.getMenu().getCode().equals(MENU_prodCart)){
			 prodCartMenu.setVisible(habilitation.isAutorise());
		 }
		 
		 if(habilitation.getMenu().getCode().equals(MENU_Referentiel)){
			 ReferentielMenu.setVisible(habilitation.isAutorise());
		 }
		 if(habilitation.getMenu().getCode().equals(MENU_FacturePF)){
			 FacturePFMenu.setVisible(habilitation.isAutorise());
		 }
		 
		 if(habilitation.getMenu().getCode().equals(MENU_Reglement)){
			 ReglementMenu.setVisible(habilitation.isAutorise());
		 }
		 if(habilitation.getMenu().getCode().equals(MENU_FactureAchat)){
			 FactureAchatMenu.setVisible(habilitation.isAutorise());
		 }
		 
		 if(habilitation.getMenu().getCode().equals(MENU_FactureAchatMP)){
			 FactureAchatMPMenu.setVisible(habilitation.isAutorise());
		 }
		 
		 if(habilitation.getMenu().getCode().equals(MENU_FactureServiceProduction)){
			 FactureServiceProductionMenu.setVisible(habilitation.isAutorise());
		 }
		 
		 if(habilitation.getMenu().getCode().equals(MENU_EtatsDeVerification)){
			 EtatsDeVerificationMenu.setVisible(habilitation.isAutorise());
		 }
		 if(habilitation.getMenu().getCode().equals(MENU_Etats)){
			 EtatsMenu.setVisible(habilitation.isAutorise());
		 }
		 
		 if(habilitation.getMenu().getCode().equals(MENU_Administration)){
			 AdministrationMenu.setVisible(habilitation.isAutorise());
		 }
		 
		 
		 
    	 
	 }
	 
 }
}


