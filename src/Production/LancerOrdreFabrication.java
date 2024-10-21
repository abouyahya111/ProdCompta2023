package Production;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
import dao.daoImplManager.CompteurProductionDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.JourneeDAOImpl;
import dao.daoImplManager.MouvementStockGlobalDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.JourneeDAO;
import dao.daoManager.MouvementStockGlobalDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.CompteurProduction;
import dao.entity.CoutMP;
import dao.entity.DetailMouvementStock;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.Journee;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.MouvementStockGlobal;
import dao.entity.Production;
import dao.entity.StockMP;
import dao.entity.TransferStockMP;
import dao.entity.Utilisateur;


public class LancerOrdreFabrication extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleMP;

	private JXTable  table = new JXTable();
	private ImageIcon imgImprimer;
	private ImageIcon imgValider;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	
	private JButton btnChercherOF;
	private JButton btnImprimer;
	private JButton btnAnnulerOF;
	private JButton btnLancerOF;
	private JButton btnRechercher;
	
	private JTextField quantite;
	private JTextField codeArticle;
	private Utilisateur utilisateur;
	TransferStockMP transferStock = new TransferStockMP();
	private	List<DetailTransferStockMP> listDetailTransferStockMPChargeSupp= new ArrayList<DetailTransferStockMP>();
	private	List<DetailTransferStockMP> listDetailTransferStockMPCharge= new ArrayList<DetailTransferStockMP>();
	private TransferStockMPDAO transferStockMPDAO;
	private DetailTransferMPDAO detailTransfertMPDAO ;
	
	private DepotDAO depotDAO;
	private ProductionDAO productionDAO;
	private StockMPDAO stockMPDAO;
	private CompteurProductionDAO compteurProductionDAO;
	
	private JComboBox<String> categorie;
	private JComboBox<String> comboMachine;
	private  JComboBox<String> comboLigneMachine;
	private List<CoutMP> listCoutMP =new ArrayList<CoutMP>();
	
	private Map< String, String> mapChargeSupp = new HashMap<>();
	
	private Map< Integer, MatierePremier> mapMatierePremiereDetailMouvement= new HashMap<>();
	private Map< Integer, BigDecimal> mapQuantiteDetailMouvement= new HashMap<>();
	private List<Journee> listJournee =new ArrayList<Journee>();
	private List<DetailMouvementStock> listDetailMouvementStock =new ArrayList<DetailMouvementStock>();
	private JourneeDAO journeeDAO;
	private MouvementStockGlobalDAO mouvementStockGlobalDAO;
	
	private static Production production = new Production();
	
	private JComboBox<String> comboEquipe;
	private JTextField txtNumOF;
	private JLabel lblDescriptionOf;
	private JTextField txtDescription;
	private JTextField txtDateDebutPrev;
	private JTextField txtDateFinPrev;
	boolean QuantiteInsuffisant=false;
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public LancerOrdreFabrication() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 565);
        try{
        	
        	mouvementStockGlobalDAO=new MouvementStockGlobalDAOImpl();
        	journeeDAO=new JourneeDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	productionDAO=new ProductionDAOImpl();
        	stockMPDAO=new StockMPDAOImpl();
        	compteurProductionDAO=new CompteurProductionDAOImpl();
        	transferStockMPDAO=new TransferStockMPDAOImpl();
        	depotDAO=new DepotDAOImpl();
        	detailTransfertMPDAO=new DetailTransferMPDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
		 	
	String codeDepot=AuthentificationView.utilisateur.getCodeDepot();
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgValider = new ImageIcon(this.getClass().getResource("/img/valider.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgImprimer = new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
          } catch (Exception exp){exp.printStackTrace();}
    
		
		
				  		  btnImprimer = new JButton("Fiche Calcule MP");
				  		 
				  		  btnImprimer.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {

					  		  	if(production.getId()>0 && production.getStatut().equals(Constantes.ETAT_OF_LANCER)){
					  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					  		  	String date=dateFormat.format(production.getDate());
								 List<CoutMP> listCoutMP=production.getListCoutMP();
								 
								Map parameters = new HashMap();
								parameters.put("numOF", production.getNumOF());
								parameters.put("machine", production.getLigneMachine().getMachine().getNom());
								parameters.put("equipe", production.getEquipe().getNomEquipe());
								parameters.put("magasin", production.getMagasinProd().getLibelle());
								parameters.put("dateProd", date);
								JasperUtils.imprimerFicheCalculeMatierePremiere(listCoutMP,parameters,production.getNumOF());
								
								//JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
					  		  	}else {
					  		  	JOptionPane.showMessageDialog(null, "Il faut Lancer OF avant d'imprimer ", "Erreur Impression", JOptionPane.ERROR_MESSAGE);
					  		  	}
					  		  	
				  		  	}
				  		  });
				  		  
				  		modeleMP =new DefaultTableModel(
				  		     	new Object[][] {
				  		     	},
				  		     	new String[] {
				  		     			"Code","Nom Matière Première   ", "Quantité", "Charge Supp"
				  		     	}
				  		     ) {
				  		     	boolean[] columnEditables = new boolean[] {
				  		     			false,false,false, true
				  		     	};
				  		     	public boolean isCellEditable(int row, int column) {
				  		     		return columnEditables[column];
				  		     	}
				  		     };
				  		   table.setModel(modeleMP); 
				  		   table.getColumnModel().getColumn(0).setPreferredWidth(10);
				  		   table.getColumnModel().getColumn(1).setPreferredWidth(300);
				  		   table.getColumnModel().getColumn(2).setPreferredWidth(60);
				  		   table.getColumnModel().getColumn(3).setPreferredWidth(60);
				  		
				  		   txtNumOF = new JTextField();
				  		 util.Utils.copycoller(txtNumOF);
				  		   btnImprimer.setIcon(imgImprimer);
				  		   btnImprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   btnImprimer.setBounds(388, 457, 131, 24);
				  		   add(btnImprimer);
				  		 
				  		   btnChercherOF = new JButton("Chercher OF");
				  		   btnChercherOF.setHorizontalAlignment(SwingConstants.LEADING);
				  		   btnChercherOF.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  		

					  			
					  			production=productionDAO.findByNumOF(txtNumOF.getText(),codeDepot);
					  			if(production!=null){
					  				
					  			listCoutMP=productionDAO.listeCoutMP(production.getId());
					  			txtDescription.setText(production.getDescription());
					  			quantite.setText(""+production.getQuantiteEstime());
					  			codeArticle.setText(production.getArticles().getCodeArticle());
					  			categorie.addItem(production.getArticles().getLiblle());
					  			categorie.setSelectedItem(production.getArticles().getLiblle());
					  			
					  		//	comboEquipe.addItem(production.getEquipe().getNomEquipe());
					  		//	comboEquipe.setSelectedItem(production.getEquipe().getNomEquipe());
					  			
					  			comboLigneMachine.addItem(production.getLigneMachine().getNom());
					  			comboLigneMachine.setSelectedItem(production.getLigneMachine().getNom());
					  			
					  			comboMachine.addItem(production.getLigneMachine().getMachine().getNom());
					  			comboMachine.setSelectedItem(production.getLigneMachine().getMachine().getNom());
					  			
					  			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				  				String dateDebutPrev=dateFormat.format(production.getDate_debFabPre());
				  				String dateFinPrev=dateFormat.format(production.getDateFinFabPre());
					  			txtDateDebutPrev.setText(dateDebutPrev);
					  			txtDateFinPrev.setText(dateFinPrev);
					  			afficher_tableMP(listCoutMP);
					  			}else{
					  			  JOptionPane.showMessageDialog(null, "OF n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
					  				
					  			}

				  		  		
				  		  	}
				  		  });
				  		btnChercherOF.setIcon(new ImageIcon(CreerOrdreFabrication.class.getResource("/img/chercher.png")));
				  		 btnChercherOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnChercherOF.setBounds(765, 13, 118, 23);
				  		 add(btnChercherOF);
				  		    
				  		    btnLancerOF = new JButton("Lancer OF");
				  		    btnLancerOF.setBounds(10, 457, 107, 24);
				  		    add(btnLancerOF);
				  		    btnLancerOF.setIcon(imgAjouter);
				  		    btnLancerOF.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     		
				  		     		if(production.getStatut().equals(Constantes.ETAT_OF_CREER)){
				  		     			
				  		     		production.setDateDebFabRee(new Date());
				  		     		
				  		     		
				  		     		production.setUtilisateurMAJ(AuthentificationView.utilisateur);
				  		     		List<CoutMP> listCoutMP=reglerStockMatierePremiere(production.getListCoutMP(),production,production);
				  		     		
				  		     		List<DetailResponsableProd> listDetailResponsableProd=production.getListDetailResponsableProd();
				  		     		production.setStatut(Constantes.ETAT_OF_LANCER);
				  		     		
				  		  		// ajouter transfer stock MP type charge 
			  		     			String codeTransfert=Utils.genererCodeTransfer(production.getCodeDepot(),ETAT_TRANSFER_STOCK_CHARGE);
									if(listDetailTransferStockMPCharge.size()!=0)
									{
										transferStock.setCodeTransfer(production.getNumOF());
										transferStock.setCreerPar(utilisateur);
										transferStock.setDate(new Date());
										transferStock.setDateTransfer(production.getDate_debFabPre());
										transferStock.setDepot(production.getMagasinProd().getDepot());
										//transferStock.setListDetailTransferMP(listDetailTransferStockMPCharge);
										if(production.getService().equals(Constantes.PRODUCTION_SERVICE_NON))
										{
											transferStock.setStatut(ETAT_TRANSFER_STOCK_CHARGE);
										}else if(production.getService().equals(Constantes.PRODUCTION_SERVICE_OUI))
										{
											transferStock.setStatut(Constantes.ETAT_TRANSFER_STOCK_SERVICE);	
										}
										
										transferStockMPDAO.add(transferStock);
									}
								
				  		     		for(int i=0;i<listDetailTransferStockMPCharge.size();i++)
				  		     		{
				  		     			detailTransfertMPDAO.add(listDetailTransferStockMPCharge.get(i));
				  		     		}
				  		     		productionDAO.edit(production);
				  		     		JOptionPane.showMessageDialog(null, "Ordre de Fabrication lancé avec succès!", "Succès", JOptionPane.INFORMATION_MESSAGE);
				  		     		transferStock=new TransferStockMP();
				  		     		/*try {
										EmailUtil.sendEmailSSL("systeme.production2016@gmail.com",
											"OF Lancé avec succès",
											registerMailBody());
									} catch (AddressException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									} catch (MessagingException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}*/
				  		     		}else{
				  		     			JOptionPane.showMessageDialog(null, "Ordre de Fabrication est déjà lancé ou terminé!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		}
				  		     	}
				  		     });
				  		    btnLancerOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		    comboEquipe = new JComboBox<String>();
				  		     btnAnnulerOF = new JButton("Initialiser");
				  		     btnAnnulerOF.setBounds(120, 457, 106, 23);
				  		     add(btnAnnulerOF);
				  		     btnAnnulerOF.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     	intialiser();
				  		     		
				  		     	}
				  		     });
				  		     btnAnnulerOF.setIcon(imgInit);
				  		     btnAnnulerOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		    
				  		     table.setShowVerticalLines(false);
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
				  		     	scrollPane.setBounds(9, 57, 889, 229);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	 
				  		     	comboMachine = new JComboBox();		
			  		    
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(9, 32, 782, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(10, 297, 888, 149);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("Machine");
				  		     	lblMachine.setBounds(10, 86, 58, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	
				  		     	 
				  		     	 comboMachine.setBounds(119, 87, 136, 24);
				  		     	 layeredPane.add(comboMachine);
				  		     	 comboMachine.addItem("");
				  		     	 
				  		     	 JLabel lblGroupe = new JLabel("Ligne Machine");
				  		     	 lblGroupe.setBounds(283, 86, 77, 24);
				  		     	 layeredPane.add(lblGroupe);
				  		     	 lblGroupe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	 comboLigneMachine = new JComboBox();
				  		     	 comboLigneMachine.setBounds(367, 87, 144, 24);
				  		     	 layeredPane.add(comboLigneMachine);
				  		     	 
				  		 
				  		  comboEquipe.setBounds(619, 87, 152, 24);
				  		  layeredPane.add(comboEquipe);
				  		  comboEquipe.addItem("");
				  		  
				  		  JLabel lblEquipe = new JLabel("Equipe");
				  		  lblEquipe.setBounds(550, 86, 51, 26);
				  		  layeredPane.add(lblEquipe);
				  		  
				  		  JLabel lblDatePrevue = new JLabel("Date D\u00E9but Pr\u00E9vue");
				  		  lblDatePrevue.setBounds(10, 48, 102, 26);
				  		  layeredPane.add(lblDatePrevue);
				  		  
				  		  JLabel lblDateFin = new JLabel("Date Fin pr\u00E9vue");
				  		  lblDateFin.setBounds(283, 48, 77, 26);
				  		  layeredPane.add(lblDateFin);
				  		  codeArticle = new JTextField();
				  		util.Utils.copycoller(codeArticle);
				  		  codeArticle.setBounds(119, 10, 136, 26);
				  		  layeredPane.add(codeArticle);
				  		  categorie = new JComboBox();
				  		  categorie.setEnabled(false);
				  		  categorie.setEditable(true);
				  		  categorie.setBackground(Color.WHITE);
				  		  categorie.addItem("");
				  		  
		txtNumOF.addKeyListener(new KeyAdapter() {
		  	@Override
		  	public void keyReleased(KeyEvent e)
		  	{
		  		if (e.getKeyCode() == e.VK_ENTER)
		  		{
		  			
		  			production=productionDAO.findByNumOF(txtNumOF.getText(),codeDepot);
		  			if(production!=null){
		  			listCoutMP=productionDAO.listeCoutMP(production.getId());
		  			txtDescription.setText(production.getDescription());
		  			quantite.setText(""+production.getQuantiteEstime());
		  			codeArticle.setText(production.getArticles().getCodeArticle());
		  			categorie.addItem(production.getArticles().getLiblle());
		  			categorie.setSelectedItem(production.getArticles().getLiblle());
		  			
		  			//comboEquipe.addItem(production.getEquipe().getNomEquipe());
		  		//	comboEquipe.setSelectedItem(production.getEquipe().getNomEquipe());
		  			
		  			comboLigneMachine.addItem(production.getLigneMachine().getNom());
		  			comboLigneMachine.setSelectedItem(production.getLigneMachine().getNom());
		  			
		  			comboMachine.addItem(production.getLigneMachine().getMachine().getNom());
		  			comboMachine.setSelectedItem(production.getLigneMachine().getMachine().getNom());
		  			
		  			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	  				String dateDebutPrev=dateFormat.format(production.getDate_debFabPre());
	  				String dateFinPrev=dateFormat.format(production.getDateFinFabPre());
		  			txtDateDebutPrev.setText(dateDebutPrev);
		  			txtDateFinPrev.setText(dateFinPrev);
		  			afficher_tableMP(listCoutMP);
		  			}else{
		  			  JOptionPane.showMessageDialog(null, "OF n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
		  				
		  			}

		  		}}});
		codeArticle.setColumns(10);
		
		  JLabel lblCodeArticle = new JLabel("Code Article");
		  lblCodeArticle.setBounds(8, 10, 82, 26);
		  layeredPane.add(lblCodeArticle);
		  lblCodeArticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		  categorie.setBounds(367, 13, 144, 26);
		  layeredPane.add(categorie);
		  categorie.addItem(""); 
		  
		  quantite = new JTextField();
		  util.Utils.copycoller(quantite);
		  quantite.setBounds(614, 11, 157, 26);
		  layeredPane.add(quantite);
		  quantite.setColumns(10);
		  
		  JLabel lblQuantite = new JLabel("Quantit\u00E9 :");
		  lblQuantite.setBounds(533, 12, 68, 26);
		  layeredPane.add(lblQuantite);
		  lblQuantite.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  
		    
		    JLabel lblArticle = new JLabel("Article:");
		    lblArticle.setBounds(283, 11, 102, 26);
		    layeredPane.add(lblArticle);
		    lblArticle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		    
		    txtDateDebutPrev = new JTextField();
		    util.Utils.copycoller(txtDateDebutPrev);
		    txtDateDebutPrev.setColumns(10);
		    txtDateDebutPrev.setBounds(119, 51, 136, 26);
		    layeredPane.add(txtDateDebutPrev);
		    
		    txtDateFinPrev = new JTextField();
		    util.Utils.copycoller(txtDateFinPrev);
		    txtDateFinPrev.setColumns(10);
		    txtDateFinPrev.setBounds(367, 51, 144, 26);
		    layeredPane.add(txtDateFinPrev);
		
		txtNumOF.setBounds(91, 11, 144, 26);
		add(txtNumOF);
		txtNumOF.setColumns(10);
		
		JLabel lblNumOF = new JLabel("Num\u00E9ro OF");
		lblNumOF.setBounds(9, 12, 89, 24);
		add(lblNumOF);
		
		txtDescription = new JTextField();
		util.Utils.copycoller(txtDescription);
		txtDescription.setBounds(347, 11, 408, 26);
		add(txtDescription);
		txtDescription.setColumns(10);
		
		lblDescriptionOf = new JLabel("Description OF");
		lblDescriptionOf.setBounds(263, 12, 89, 24);
		add(lblDescriptionOf);
		
		JButton btnValiderChargeSupp = new JButton("Valider Charge Supp");
		btnValiderChargeSupp.setIcon(imgValider);
		
		btnValiderChargeSupp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MouvementStockGlobal mouvementStockGlobal=null;
				
				if(production.getStatut().equals(Constantes.ETAT_OF_LANCER)){	
			if(!remplirMapChargeSupp())	{
				JOptionPane.showMessageDialog(null, "Il faut remplir la quantité à charger ", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else {
				
				
				  int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Valider cette charge ?", 
							"Satisfaction", JOptionPane.YES_NO_OPTION);
					 
					if(reponse == JOptionPane.YES_OPTION )
						{

				production.setListCoutMP(ajouetrChargeSupp(listCoutMP,mapChargeSupp,production,production));
				if(QuantiteInsuffisant==true)
				{
					return;
				}
				productionDAO.edit(production);
				
				
				/*////////////////////////////////////////////////////////// Chercher le Journée et liste des mouvement de stock de cet journée /////////////////////////////////////////
				
				Journee journee=journeeDAO.findByDateEtatOuverte(production.getDate(), Constantes.ETAT_STATUT_OVERTE,production.getMagasinStockage().getDepot().getId());
				if(journee!=null)
				{
					 mouvementStockGlobal=mouvementStockGlobalDAO.findMouvementStockGlobalByDetailMouvementStock(production.getDate(), production.getMagasinStockage().getDepot().getId(), production.getMagasinStockage().getId());
					if(mouvementStockGlobal!=null)
					{
						listDetailMouvementStock=mouvementStockGlobal.getDetailMouvementStock();
					}
				
				
				}
				
				
				
				
				////////////////////////////////////////////////////// chercher MP dans la liste detailMouvementStock et Modifier Quantite Charger et leStock Finale ////////////////////////////////
				
	     			for(int j=0;j<listDetailMouvementStock.size();j++)
	     			{
	     				MatierePremier matierepremiere=listDetailMouvementStock.get(j).getMatierePremier();
	     				DetailMouvementStock detailMouvementStock=listDetailMouvementStock.get(j);
	     				MatierePremier matierepremiereTmp=mapMatierePremiereDetailMouvement.get(matierepremiere.getId());
	     				if(matierepremiereTmp!=null)
	     				{
	     					
	     					detailMouvementStock.setChargeSupplementaire (detailMouvementStock.getChargeSupplementaire().add(mapQuantiteDetailMouvement.get(matierepremiereTmp.getId())));	
	     					detailMouvementStock.setStockFinaldb((detailMouvementStock.getInitial().add(detailMouvementStock.getReception()).add(detailMouvementStock.getTransfertEntrees()).subtract((detailMouvementStock.getSorties().add(detailMouvementStock.getTransfertSorties()).add(detailMouvementStock.getCharge()).add(detailMouvementStock.getChargeSupplementaire())))).add(detailMouvementStock.getRetour()));
	     					
	     					listDetailMouvementStock.set(j, detailMouvementStock);
	     					
	     					
	     				}
	     				
	     				
	     			}
	     			if(mouvementStockGlobal!=null)
	     			{
	     				mouvementStockGlobal.setDetailMouvementStock(listDetailMouvementStock);
	     				
	     				mouvementStockGlobalDAO.edit(mouvementStockGlobal);
	     				
	     			}
	     			
				
				
				
				
				///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
				*/
				
				
				// ajouter transfer stock MP type charge 
	     	   String codeTransfert=Utils.genererCodeTransfer(production.getMagasinProd().getDepot().getCode(),ETAT_TRANSFER_STOCK_CHARGE);
	     		
				transferStock.setCodeTransfer(production.getNumOF());
				transferStock.setCreerPar(production.getUtilisateurCreation());
				transferStock.setDate(new Date());
				transferStock.setDateTransfer(production.getDate_debFabPre());
				transferStock.setDepot(production.getMagasinProd().getDepot());
				//transferStock.setListDetailTransferMP(listDetailTransferStockMPChargeSupp);
				transferStock.setStatut(ETAT_TRANSFER_STOCK_CHARGE_SUPP);
				transferStockMPDAO.add(transferStock);
				
				for(int i=0;i<listDetailTransferStockMPChargeSupp.size();i++)
		     		{
		     			detailTransfertMPDAO.add(listDetailTransferStockMPChargeSupp.get(i));
		     		}
				
				
				JOptionPane.showMessageDialog(null, "La charge a été ajouté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
						}
				
			/*	try {
					EmailUtil.sendEmailSSL("systeme.production2016@gmail.com",
						"Charge Supplémetaire",
						registerMailBody2());
				} catch (AddressException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}*/
			}
				}else {
					JOptionPane.showMessageDialog(null, "Il faut Lancer l'OF avant de valider la charge supplemntaire ", "Erreur", JOptionPane.ERROR_MESSAGE);	
				}
			
		  }
		});
		btnValiderChargeSupp.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnValiderChargeSupp.setBounds(228, 457, 158, 23);
		add(btnValiderChargeSupp);
		
		JButton btnImprimerSortieChargeSupp = new JButton("Bon Sortie Charge Supp");
		
		btnImprimerSortieChargeSupp.setIcon(imgImprimer);
		btnImprimerSortieChargeSupp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

	  		  	if(production.getStatut().equals(Constantes.ETAT_OF_LANCER)){
	  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	  		  	String date=dateFormat.format(production.getDate());
				 List<CoutMP> listCoutMP=production.getListCoutMP();
				 
				Map parameters = new HashMap();
				parameters.put("numOF", production.getNumOF());
				parameters.put("machine", production.getLigneMachine().getMachine().getNom());
				parameters.put("equipe", production.getEquipe().getNomEquipe());
				parameters.put("magasin", production.getMagasinProd().getLibelle());
				parameters.put("dateProd", date);
				JasperUtils.imprimerBonSortieMPChargeSupp(listCoutMP,parameters,production.getNumOF());
				
			//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  		  	}else {
	  		  	JOptionPane.showMessageDialog(null, "Il faut Lancer OF avant d'imprimer ", "Erreur Impression", JOptionPane.ERROR_MESSAGE);
	  		  	}
	  		  	
  		  	
				
			}
		});
		btnImprimerSortieChargeSupp.setBounds(521, 457, 167, 23);
		add(btnImprimerSortieChargeSupp);
		
		JButton btnAnnulerOf = new JButton("Annuler OF");
		btnAnnulerOf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MouvementStockGlobal mouvementStockGlobal=null;
		    		if(production.getId()<=0){
		    			 JOptionPane.showMessageDialog(null, "Il faut Cherercher l'OF à Annuler", "Message", JOptionPane.ERROR_MESSAGE);
		    			
		    		}else {
		    			
		    			if(!production.getStatut().equals(ETAT_OF_ANNULER)){
		    				 if(production.getStatut().equals(ETAT_OF_LANCER)){
		    					 
		    					 CompteurProduction compteurProduction=compteurProductionDAO.findByDateProdPeriode(production.getDate(),production.getPeriode());
				    			 int compteurProd=compteurProduction.getCompteur();
				    			 compteurProd=compteurProd-1;
				    			 compteurProduction.setCompteur(compteurProd);
		    					 
		    			annulerOF(production.getListCoutMP(),production.getMagasinProd().getId(),production.getMagasinStockage().getId());
		    			
		    			production.setStatut(ETAT_OF_ANNULER);
		    			production.setUtilisateurAnnulation(AuthentificationView.utilisateur);
		    			
		    			productionDAO.edit(production);
		    			compteurProductionDAO.edit(compteurProduction);
		    			
		    			
		    			
						/*////////////////////////////////////////////////////////// stocker charge supp dans detailMouvementStock et calculer le stock finale /////////////////////////////////////////
						
						Journee journee=journeeDAO.findByDateEtatOuverte(production.getDate(), Constantes.ETAT_STATUT_OVERTE,production.getMagasinStockage().getDepot().getId());
						if(journee!=null)
						{
							 mouvementStockGlobal=mouvementStockGlobalDAO.findMouvementStockGlobalByDetailMouvementStock(production.getDate(), production.getMagasinStockage().getDepot().getId(), production.getMagasinStockage().getId());
							if(mouvementStockGlobal!=null)
							{
								listDetailMouvementStock=mouvementStockGlobal.getDetailMouvementStock();
							}
						
						
						}
						
						
						
						
						////////////////////////////////////////////////////// chercher MP dans la liste detailMouvementStock et Modifier Quantite Charger et leStock Finale ////////////////////////////////
						
			     			for(int j=0;j<listDetailMouvementStock.size();j++)
			     			{
			     				MatierePremier matierepremiere=listDetailMouvementStock.get(j).getMatierePremier();
			     				DetailMouvementStock detailMouvementStock=listDetailMouvementStock.get(j);
			     				MatierePremier matierepremiereTmp=mapMatierePremiereDetailMouvement.get(matierepremiere.getId());
			     				if(matierepremiereTmp!=null)
			     				{
			     					
			     					detailMouvementStock.setChargeSupplementaire (detailMouvementStock.getChargeSupplementaire().subtract(mapQuantiteDetailMouvement.get(matierepremiereTmp.getId())));	
			     					detailMouvementStock.setStockFinaldb((detailMouvementStock.getInitial().add(detailMouvementStock.getReception()).add(detailMouvementStock.getTransfertEntrees()).subtract((detailMouvementStock.getSorties().add(detailMouvementStock.getTransfertSorties()).add(detailMouvementStock.getCharge()).add(detailMouvementStock.getChargeSupplementaire())))).add(detailMouvementStock.getRetour()));
			     					
			     					listDetailMouvementStock.set(j, detailMouvementStock);
			     					
			     					
			     				}
			     				
			     				
			     			}
			     			if(mouvementStockGlobal!=null)
			     			{
			     				mouvementStockGlobal.setDetailMouvementStock(listDetailMouvementStock);
			     				
			     				mouvementStockGlobalDAO.edit(mouvementStockGlobal);
			     				
			     			}
			     			
						
						
						
						
						///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					
		    			
		    			
		    			
		    			
		    			
		    			*/
		    			
		    			
		    			
		    			
		    			
		    			
		    			
		    			
		    			JOptionPane.showMessageDialog(null, "OF Annulé avec succès", "Message", JOptionPane.ERROR_MESSAGE); 
		    				 }else{
		    					JOptionPane.showMessageDialog(null, "OF n'est pas encore lancé", "Message", JOptionPane.ERROR_MESSAGE); 
		    				 }
		    			
		    			}else{
		    				JOptionPane.showMessageDialog(null, "OF est déjà Annulé", "Message", JOptionPane.ERROR_MESSAGE);
		    			}
		    		}
		    		
		    		
		    	}
		});
		btnAnnulerOf.setBounds(697, 457, 89, 23);
		add(btnAnnulerOf);
				  		     
				  		 
	}
	
	
	void intialiser()
	{
		quantite.setText("");
		codeArticle.setText("");
		categorie.setSelectedItem("");
		
	}
	
void 	intialiserTableau() {
		
		modeleMP =new DefaultTableModel(
  		     	new Object[][] {
  		     	},
  		     	new String[] {
  		     			"Code","Nom Matière Première   ", "Quantité Calculée", "Quantité Existante", "Quantité Chargée", "Charge Supplémetaire",
  		     	}
  		     ) {
  		     	boolean[] columnEditables = new boolean[] {
  		     			false,false,false,false,false, true
  		     	};
  		     	public boolean isCellEditable(int row, int column) {
  		     		return columnEditables[column];
  		     	}
  		     };
  		   table.setModel(modeleMP); 
  		   table.getColumnModel().getColumn(0).setPreferredWidth(30);
  		   table.getColumnModel().getColumn(1).setPreferredWidth(300);
  		   table.getColumnModel().getColumn(2).setPreferredWidth(60);
  		   table.getColumnModel().getColumn(3).setPreferredWidth(60);
	}
	
	void afficher_tableMP(List<CoutMP> listCoutMP)
	{
	        BigDecimal chargeSupp;
		  int i=0;
		  intialiserTableau();
			while(i<listCoutMP.size())
			{	
				
				CoutMP coutMP=listCoutMP.get(i);
			
				BigDecimal quantiteTotal=coutMP.getQuantite();
				BigDecimal quantiteExistante=coutMP.getQuantExistante();
				BigDecimal quantiteACharge=coutMP.getQuantCharge();
				chargeSupp=coutMP.getQuantChargeSupp();
				
					if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_BOX))
					{
						
						Object []ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(), NumberUtils.GroupingFormatBigDecimal( quantiteTotal.setScale(0,RoundingMode.DOWN))+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),NumberUtils.GroupingFormatBigDecimal(quantiteExistante.setScale(6,RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(quantiteACharge.setScale(6,RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(chargeSupp.setScale(6,RoundingMode.HALF_UP))};

						modeleMP.addRow(ligne);
						
					}else
					{
						Object []ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(), NumberUtils.GroupingFormatBigDecimal( quantiteTotal.setScale(6,RoundingMode.HALF_UP))+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),NumberUtils.GroupingFormatBigDecimal(quantiteExistante.setScale(6,RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(quantiteACharge.setScale(6,RoundingMode.HALF_UP)),NumberUtils.GroupingFormatBigDecimal(chargeSupp.setScale(6,RoundingMode.HALF_UP))};

						modeleMP.addRow(ligne);
					}
				
			
				
				i++;
			}
	}
	
	List<CoutMP>  reglerStockMatierePremiere(List<CoutMP> listCoutMP,Production productionMagasinProd,Production productionMagasinStockage){
	BigDecimal quantiteStockage=BigDecimal.ZERO;
	BigDecimal quantiteAConsomme=BigDecimal.ZERO;
	BigDecimal quantiteProd=BigDecimal.ZERO;
	BigDecimal prixUnitaire=BigDecimal.ZERO;
	BigDecimal prixTotal=BigDecimal.ZERO;
	Magasin  magasinElnasTeaPacking=null;
	
	List<CoutMP> listCoutMPTmp=new ArrayList<CoutMP>();
	for(int i=0;i<listCoutMP.size();i++){ 
		quantiteStockage=BigDecimal.ZERO;
		CoutMP coutMP=listCoutMP.get(i);
	if(coutMP.getTransfer().equals(COUT_MP_TRANSFER_NON))
	{
		if(coutMP.getProdcutionCM().getService().equals(Constantes.PRODUCTION_SERVICE_OUI))
		{
			if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes. SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
					{
			
				quantiteAConsomme=coutMP.getQuantCharge();
				StockMP stockMPProd=stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),productionMagasinProd.getMagasinProd().getId() );
				StockMP stockMPStockage=stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),productionMagasinStockage.getMagasinStockage().getId() );
				
				quantiteProd=stockMPProd.getStock().add(quantiteAConsomme);
				quantiteStockage=stockMPStockage.getStock().subtract(quantiteAConsomme) ;
				
				DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
				if(coutMP.getMagasin()!=null)
				{
					detailTransferStockMP.setMagasinSouce(coutMP.getMagasin());
					StockMP stockMPStockageTmp=stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),coutMP.getMagasin().getId() );
					detailTransferStockMP.setPrixUnitaire(stockMPStockageTmp.getPrixUnitaire());
					
				}else
				{
					detailTransferStockMP.setMagasinSouce(productionMagasinStockage.getMagasinStockage());
					detailTransferStockMP.setPrixUnitaire(stockMPStockage.getPrixUnitaire());
				}
				
				detailTransferStockMP.setMagasinDestination(productionMagasinStockage.getMagasinProd());
				detailTransferStockMP.setMatierePremier(coutMP.getMatierePremier());
				
				
				
				if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX)  || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES))
				{
					detailTransferStockMP.setQuantite(coutMP.getQuantCharge().setScale(0, RoundingMode.CEILING));
					
				}else if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON))
				{
					detailTransferStockMP.setQuantite(coutMP.getQuantCharge().setScale(0, BigDecimal.ROUND_DOWN));
				}
				
				else
				{
					detailTransferStockMP.setQuantite(coutMP.getQuantCharge());
				}
				
				detailTransferStockMP.setTransferStockMP(transferStock);
				listDetailTransferStockMPCharge.add(detailTransferStockMP);
				
				
				
				
				/*prixUnitaire=coutMP.getQuantite()*coutMP.getPrixUnitaire()+coutMP.getQuantChargeSupp()*stockMPStockage.getPrixUnitaire();
				prixUnitaire=prixUnitaire/quantiteProd;
				prixTotal=prixUnitaire*quantiteConsomme;
				
				coutMP.setPrixUnitaire(prixUnitaire);
				coutMP.setPrixTotal(prixTotal);
				listCoutMPTmp.add(coutMP);*/
				stockMPStockage.setStock(quantiteStockage);
				stockMPProd.setStock(quantiteProd);
				stockMPDAO.edit(stockMPStockage);
				stockMPDAO.edit(stockMPProd);
				
				
					}else
					{
						
						
						
						if(!coutMP.getProdcutionCM().getMagasinStockage().getLibelle().equals(depotDAO.magasinByCode(CODE_MAGASIN_ELNASS_TEA_PACKING).getLibelle()))
						{
							 magasinElnasTeaPacking=depotDAO.magasinByCode(CODE_MAGASIN_ELNASS_TEA_PACKING);	
						}else
						{
							magasinElnasTeaPacking=coutMP.getProdcutionCM().getMagasinStockage();
						}
						
						quantiteAConsomme=coutMP.getQuantCharge();
						
					if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU) || !coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes. SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
						{
						if(coutMP.getMagasin()!=null)
						{
							if(!coutMP.getMagasin().getLibelle().equals(depotDAO.magasinByCode(CODE_MAGASIN_ELNASS_TEA_PACKING).getLibelle()))
							{
								magasinElnasTeaPacking=coutMP.getMagasin();
							}
						}
						
						}
					
						
						StockMP stockMPProd=stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),productionMagasinProd.getMagasinProd().getId());
						StockMP stockMPStockage=stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),magasinElnasTeaPacking.getId());
						
						quantiteProd=stockMPProd.getStock().add(quantiteAConsomme);
						quantiteStockage=stockMPStockage.getStock().subtract(quantiteAConsomme) ;
						
						DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
						if(coutMP.getMagasin()!=null)
						{
							detailTransferStockMP.setMagasinSouce(coutMP.getMagasin());
							StockMP stockMPStockageTmp=stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),coutMP.getMagasin().getId());
							detailTransferStockMP.setPrixUnitaire(stockMPStockageTmp.getPrixUnitaire());
						}else
						{
							detailTransferStockMP.setMagasinSouce(magasinElnasTeaPacking);
							detailTransferStockMP.setPrixUnitaire(stockMPStockage.getPrixUnitaire());
						}
						
						detailTransferStockMP.setMagasinDestination(productionMagasinStockage.getMagasinProd());
						detailTransferStockMP.setMatierePremier(coutMP.getMatierePremier());
						
						
						if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX)  || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES))
						{
							detailTransferStockMP.setQuantite(coutMP.getQuantCharge().setScale(0, RoundingMode.CEILING));
							
						}else if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON))
						{
							
							detailTransferStockMP.setQuantite(coutMP.getQuantCharge().setScale(0, BigDecimal.ROUND_DOWN));
							
						}
						
						else
						{
							detailTransferStockMP.setQuantite(coutMP.getQuantCharge());
						}
						
						detailTransferStockMP.setTransferStockMP(transferStock);
						listDetailTransferStockMPCharge.add(detailTransferStockMP);
						
						
						
						
						/*prixUnitaire=coutMP.getQuantite()*coutMP.getPrixUnitaire()+coutMP.getQuantChargeSupp()*stockMPStockage.getPrixUnitaire();
						prixUnitaire=prixUnitaire/quantiteProd;
						prixTotal=prixUnitaire*quantiteConsomme;
						
						coutMP.setPrixUnitaire(prixUnitaire);
						coutMP.setPrixTotal(prixTotal);
						listCoutMPTmp.add(coutMP);*/
						stockMPStockage.setStock(quantiteStockage);
						stockMPProd.setStock(quantiteProd);
						stockMPDAO.edit(stockMPStockage);
						stockMPDAO.edit(stockMPProd);
					}
			
			
			
		}else
		{
			quantiteAConsomme=coutMP.getQuantCharge();
			StockMP stockMPProd=stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),productionMagasinProd.getMagasinProd().getId() );
			
			StockMP stockMPStockage=stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),productionMagasinStockage.getMagasinStockage().getId() );
			
			quantiteProd=stockMPProd.getStock().add(quantiteAConsomme);
			quantiteStockage=stockMPStockage.getStock().subtract(quantiteAConsomme) ;
			
			DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
			detailTransferStockMP.setMagasinSouce(productionMagasinStockage.getMagasinStockage());
			detailTransferStockMP.setMagasinDestination(productionMagasinStockage.getMagasinProd());
			detailTransferStockMP.setMatierePremier(coutMP.getMatierePremier());
			detailTransferStockMP.setPrixUnitaire(coutMP.getPrixUnitaire());
			if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX)  || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES))
			{
				
				detailTransferStockMP.setQuantite(coutMP.getQuantCharge().setScale(0, RoundingMode.CEILING));
				
			}else if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON))
			{
				detailTransferStockMP.setQuantite(coutMP.getQuantCharge().setScale(0, BigDecimal.ROUND_DOWN));
				
			}
			
			
			else
			{
				detailTransferStockMP.setQuantite(coutMP.getQuantCharge());
			}
			
			detailTransferStockMP.setTransferStockMP(transferStock);
			listDetailTransferStockMPCharge.add(detailTransferStockMP);
			
			
			
			
			/*prixUnitaire=coutMP.getQuantite()*coutMP.getPrixUnitaire()+coutMP.getQuantChargeSupp()*stockMPStockage.getPrixUnitaire();
			prixUnitaire=prixUnitaire/quantiteProd;
			prixTotal=prixUnitaire*quantiteConsomme;
			
			coutMP.setPrixUnitaire(prixUnitaire);
			coutMP.setPrixTotal(prixTotal);
			listCoutMPTmp.add(coutMP);*/
			stockMPStockage.setStock(quantiteStockage);
			stockMPProd.setStock(quantiteProd);
			stockMPDAO.edit(stockMPStockage);
			stockMPDAO.edit(stockMPProd);
		}
		
	}else
	{
		StockMP stockMPStockage=null;	
		quantiteAConsomme=coutMP.getQuantCharge();
	
		StockMP stockMPProd=stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),productionMagasinProd.getMagasinProd().getId() );
		
		if(coutMP.getProdcutionCM().getService().equals(Constantes.PRODUCTION_SERVICE_OUI))
		{
			if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes. SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
			{
				 stockMPStockage=stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),productionMagasinStockage.getMagasinStockage().getId() );	
			}else
			{
				magasinElnasTeaPacking=depotDAO.magasinByCode(CODE_MAGASIN_ELNASS_TEA_PACKING);
				stockMPStockage=stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),magasinElnasTeaPacking.getId() );	
				
			}
			
			
		}else
		{
			 stockMPStockage=stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),productionMagasinStockage.getMagasinStockage().getId() );	
		}
		
		
		quantiteProd=stockMPProd.getStock().add(quantiteAConsomme);
		quantiteStockage=stockMPStockage.getStock().subtract(quantiteAConsomme) ;
		if(coutMP.getProdcutionCM().getService().equals(Constantes.PRODUCTION_SERVICE_OUI))
		{
			if(!coutMP.getProdcutionCM().getMagasinStockage().getLibelle().equals(depotDAO.magasinByCode(CODE_MAGASIN_ELNASS_TEA_PACKING).getLibelle()))
			{
				 magasinElnasTeaPacking=depotDAO.magasinByCode(CODE_MAGASIN_ELNASS_TEA_PACKING);	
			}
			DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
			if(coutMP.getMagasin()!=null)
			{
				detailTransferStockMP.setMagasinSouce(coutMP.getMagasin());
			}else
			{
				detailTransferStockMP.setMagasinSouce(magasinElnasTeaPacking);
			}
			
			detailTransferStockMP.setMagasinDestination(productionMagasinStockage.getMagasinProd());
			detailTransferStockMP.setMatierePremier(coutMP.getMatierePremier());
			detailTransferStockMP.setPrixUnitaire(stockMPStockage.getPrixUnitaire());
			detailTransferStockMP.setQuantite(coutMP.getQuantCharge());
			detailTransferStockMP.setTransferStockMP(transferStock);
			listDetailTransferStockMPCharge.add(detailTransferStockMP);
			
		}
		
		
		
		
		
		/*prixUnitaire=coutMP.getQuantite()*coutMP.getPrixUnitaire()+coutMP.getQuantChargeSupp()*stockMPStockage.getPrixUnitaire();
		prixUnitaire=prixUnitaire/quantiteProd;
		prixTotal=prixUnitaire*quantiteConsomme;
		
		coutMP.setPrixUnitaire(prixUnitaire);
		coutMP.setPrixTotal(prixTotal);
		listCoutMPTmp.add(coutMP);*/
		stockMPStockage.setStock(quantiteStockage);
		stockMPProd.setStock(quantiteProd);
		stockMPDAO.edit(stockMPStockage);
		stockMPDAO.edit(stockMPProd);
	
	}
		
		
	}
	return listCoutMPTmp;
  }

boolean remplirMapChargeSupp(){
	boolean trouve=false;
	BigDecimal quantite=BigDecimal.ZERO;
	for(int j=0;j<table.getRowCount();j++){
		quantite =new BigDecimal(table.getValueAt(j, 5).toString());
		
		if(!table.getValueAt(j, 5).toString().equals("") || quantite.compareTo(BigDecimal.ZERO) !=0){
			mapChargeSupp.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 5).toString());
			trouve=true;
		}else {
			mapChargeSupp.put(table.getValueAt(j, 0).toString(), "0");
		}
		
	}
	return trouve;
}

List<CoutMP> ajouetrChargeSupp(List<CoutMP> listCoutMP,Map< String, String> mapChargeSupp,Production productionMagasinProd,Production productionMagasinStockage){
	BigDecimal QuantiteCharge=BigDecimal.ZERO;
	BigDecimal QuantiteTotal=BigDecimal.ZERO;
	BigDecimal coutTotal=BigDecimal.ZERO;
	BigDecimal quantiteStockage=BigDecimal.ZERO;
	BigDecimal quantiteConsomme=BigDecimal.ZERO;
	BigDecimal quantiteProd=BigDecimal.ZERO;
	BigDecimal StockFinale=BigDecimal.ZERO;
	Magasin magasinElnasTeaPacking=null;
	SimpleDateFormat sdf=new SimpleDateFormat("YYYY");
	String date="01/01/"+sdf.format(productionMagasinStockage.getDate_debFabPre())+"";
		Date dateDebut= new Date(date);
	QuantiteInsuffisant=false;
	boolean existe=false;
	List<CoutMP> listCoutMPTmp= new ArrayList<CoutMP>();
	for(int i=0;i<listCoutMP.size();i++){
		StockFinale=BigDecimal.ZERO;
		CoutMP coutMP =listCoutMP.get(i);
		if(coutMP.getTransfer()!=null && coutMP.getTransfer().equals(COUT_MP_TRANSFER_NON))
		{
			QuantiteCharge=new BigDecimal(mapChargeSupp.get(coutMP.getMatierePremier().getCode()));
			
			StockMP stockMPStockage=stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),coutMP.getMagasin().getId() );
			
			
			StockMP stockMPProd=stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),productionMagasinProd.getMagasinProd().getId() );
			
			// Stock Finale de ce jour
			List<Object[]> listestockfinale=detailTransfertMPDAO.StockFinaleMPByMagasin(dateDebut, productionMagasinStockage.getDate_debFabPre(), coutMP.getMagasin(), coutMP.getMatierePremier());
			if(!listestockfinale.isEmpty())
			{
				for(int t=0;t<listestockfinale.size();t++)
	 			{
	 				
	 			 Object[] object=listestockfinale.get(t);
				
	 			StockFinale= (BigDecimal)object[1];
				
	 			}
			}
			
			
			
			quantiteProd=stockMPProd.getStock().add(QuantiteCharge)  ;
			if(stockMPStockage.getStock().compareTo(QuantiteCharge)>=0 && StockFinale.compareTo(QuantiteCharge)>=0)
			{
				existe=false;
				for(int j=0;j<listDetailTransferStockMPChargeSupp.size();j++)
				{
					if(listDetailTransferStockMPChargeSupp.get(j).getMatierePremier().equals(coutMP.getMatierePremier()))
					{
						existe=true;
					}
				}
				if(existe==false)
				{
					quantiteStockage=stockMPStockage.getStock().subtract(QuantiteCharge);
					if(QuantiteCharge.compareTo(BigDecimal.ZERO)>0)
					{
						mapMatierePremiereDetailMouvement.put(coutMP.getMatierePremier().getId(), coutMP.getMatierePremier());
						mapQuantiteDetailMouvement.put(coutMP.getMatierePremier().getId(), QuantiteCharge);
											
					}
					
					
					QuantiteTotal=coutMP.getQuantChargeSupp().add(QuantiteCharge);
					stockMPProd.setStock(quantiteProd);
					stockMPStockage.setStock(quantiteStockage);
					coutMP.setQuantChargeSupp(QuantiteTotal);
					
					
					DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
					detailTransferStockMP.setMagasinDestination(productionMagasinProd.getMagasinProd());
					detailTransferStockMP.setMagasinSouce(productionMagasinStockage.getMagasinStockage());
					detailTransferStockMP.setMatierePremier(coutMP.getMatierePremier());
					detailTransferStockMP.setPrixUnitaire(coutMP.getPrixUnitaire());
if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) ||coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES))
{
	detailTransferStockMP.setQuantite(coutMP.getQuantChargeSupp().setScale(0, RoundingMode.CEILING));
}else
{
	detailTransferStockMP.setQuantite(coutMP.getQuantChargeSupp());
}
					
					detailTransferStockMP.setTransferStockMP(transferStock);
					listDetailTransferStockMPChargeSupp.add(detailTransferStockMP);
					
					stockMPDAO.edit(stockMPStockage);
					stockMPDAO.edit(stockMPProd);
					
					listCoutMPTmp.add(coutMP);
				}
			
			}else
			{
				JOptionPane.showMessageDialog(null, "Quantité de "+coutMP.getMatierePremier().getNom() +"insuffisant au stock : "+productionMagasinStockage.getMagasinStockage().getLibelle());
				QuantiteInsuffisant=true;
				break;
			}
			
			
			/*prixUnitaire = (QuantiteCharge*stockMPStockage.getPrixUnitaire())+(stockMPProd.getPrixUnitaire()*stockMPProd.getStock());
			
			if(quantiteProd> 0)
			prixUnitaire=prixUnitaire/quantiteProd;*/
			
			
			
			
		}else if(coutMP.getTransfer()!=null && coutMP.getTransfer().equals(COUT_MP_TRANSFER_OUI)) {
			

			QuantiteCharge=new BigDecimal(mapChargeSupp.get(coutMP.getMatierePremier().getCode()));
			
				 magasinElnasTeaPacking=depotDAO.magasinByCode(CODE_MAGASIN_ELNASS_TEA_PACKING);	
			
			StockMP stockMPStockage=stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),coutMP.getMagasin().getId() );
			
			
			StockMP stockMPProd=stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),productionMagasinProd.getMagasinProd().getId() );
			
			
			// Stock Finale de ce jour
			List<Object[]> listestockfinale=detailTransfertMPDAO.StockFinaleMPByMagasin(dateDebut, productionMagasinStockage.getDate_debFabPre(), coutMP.getMagasin(), coutMP.getMatierePremier());
			if(!listestockfinale.isEmpty())
			{
				for(int t=0;t<listestockfinale.size();t++)
	 			{
	 				
	 			 Object[] object=listestockfinale.get(t);
				
	 			StockFinale= (BigDecimal)object[1];
				
	 			}
			}
			
			
			
			quantiteProd=stockMPProd.getStock().add(QuantiteCharge)  ;
			if(stockMPStockage.getStock().compareTo(QuantiteCharge)>=0 && StockFinale.compareTo(QuantiteCharge)>=0 )
			{
				existe=false;
				for(int j=0;j<listDetailTransferStockMPChargeSupp.size();j++)
				{
					if(listDetailTransferStockMPChargeSupp.get(j).getMatierePremier().equals(coutMP.getMatierePremier()))
					{
						existe=true;
					}
				}
				if(existe==false)
				{
					quantiteStockage=stockMPStockage.getStock().subtract(QuantiteCharge);
					if(QuantiteCharge.compareTo(BigDecimal.ZERO)>0)
					{
						mapMatierePremiereDetailMouvement.put(coutMP.getMatierePremier().getId(), coutMP.getMatierePremier());
						mapQuantiteDetailMouvement.put(coutMP.getMatierePremier().getId(), QuantiteCharge);
						
						
					}
					
					
					
					QuantiteTotal=coutMP.getQuantChargeSupp().add(QuantiteCharge);
					stockMPProd.setStock(quantiteProd);
					stockMPStockage.setStock(quantiteStockage);
					coutMP.setQuantChargeSupp(QuantiteTotal);
					
					
					DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
					detailTransferStockMP.setMagasinDestination(productionMagasinProd.getMagasinProd());
					detailTransferStockMP.setMagasinSouce(productionMagasinStockage.getMagasinStockage());
					detailTransferStockMP.setMatierePremier(coutMP.getMatierePremier());
					detailTransferStockMP.setPrixUnitaire(coutMP.getPrixUnitaire());
					detailTransferStockMP.setQuantite(coutMP.getQuantChargeSupp());
					detailTransferStockMP.setTransferStockMP(transferStock);
					listDetailTransferStockMPChargeSupp.add(detailTransferStockMP);
					
					stockMPDAO.edit(stockMPStockage);
					stockMPDAO.edit(stockMPProd);
					
					listCoutMPTmp.add(coutMP);
				}
				
			
			}else
			{

				JOptionPane.showMessageDialog(null, "Quantité de "+coutMP.getMatierePremier().getNom() +"insuffisant au stock : "+magasinElnasTeaPacking.getLibelle());
				QuantiteInsuffisant=true;
				break;
			}
			
			
			/*prixUnitaire = (QuantiteCharge*stockMPStockage.getPrixUnitaire())+(stockMPProd.getPrixUnitaire()*stockMPProd.getStock());
			
			if(quantiteProd> 0)
			prixUnitaire=prixUnitaire/quantiteProd;*/
			
			
			
			
		
		}

	}
	return listCoutMPTmp;
	
}



List<CoutMP>  annulerOF(List<CoutMP> listCoutMP,int idMagasinProd,int idMagasinStockage){
	BigDecimal quantiteStockage=BigDecimal.ZERO;
	BigDecimal quantiteAConsomme=BigDecimal.ZERO;
	BigDecimal quantiteProd=BigDecimal.ZERO;
	BigDecimal quantiteChargeSupp=BigDecimal.ZERO;

	List<CoutMP> listCoutMPTmp=new ArrayList<CoutMP>();
	for(int i=0;i<listCoutMP.size();i++){ 
		quantiteStockage=BigDecimal.ZERO;
		CoutMP coutMP=listCoutMP.get(i);
	/*	if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CELLOPHANE) ){
			quantiteAConsomme=coutMP.getQuantCharge();
		}else {
			quantiteAConsomme=coutMP.getQuantite()-coutMP.getQuantExistante();
		}*/
		quantiteAConsomme=coutMP.getQuantCharge();
		quantiteChargeSupp=coutMP.getQuantChargeSupp();
		
		StockMP stockMPProd=stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),idMagasinProd );
		StockMP stockMPStockage=stockMPDAO.findStockByMagasinMP(coutMP.getMatierePremier().getId(),idMagasinStockage );
		
		quantiteProd=stockMPProd.getStock().subtract(quantiteAConsomme.add(quantiteChargeSupp)) ;
		quantiteStockage=stockMPStockage.getStock().add((quantiteAConsomme.add(quantiteChargeSupp)));
		
		
		/*prixUnitaire=coutMP.getQuantite()*coutMP.getPrixUnitaire()+coutMP.getQuantChargeSupp()*stockMPStockage.getPrixUnitaire();
		prixUnitaire=prixUnitaire/quantiteProd;
		prixTotal=prixUnitaire*quantiteConsomme;
		
		coutMP.setPrixUnitaire(prixUnitaire);
		coutMP.setPrixTotal(prixTotal);
		listCoutMPTmp.add(coutMP);*/
		stockMPStockage.setStock(quantiteStockage);
		stockMPProd.setStock(quantiteProd);
		stockMPDAO.edit(stockMPStockage);
		stockMPDAO.edit(stockMPProd);
		
	}
	return listCoutMPTmp;
  }


private static String registerMailBody() {
	return "<HTML><b>OF N°  :</b>"+production.getNumOF()+" a été lance <br><br>"
			
			+ "Merci pour votre confiance<br>"
			+ "Service Informatique<br>"
			+"Système Production</HTML>";
}


private static String registerMailBody2() {
	List<CoutMP> listCoutMP =new ArrayList<CoutMP>(); 
	
	listCoutMP =production.getListCoutMP();
	String mail;
	String mail1;
	String mail2 = "";
	String mail3;
	String MP[]= null;
	BigDecimal quantite[] = null;
	for (int i=0;i< listCoutMP.size();i++){
		CoutMP coutMP=listCoutMP.get(i);
		if(coutMP.getQuantChargeSupp().compareTo(BigDecimal.ZERO)>0)
		mail2=mail2+coutMP.getMatierePremier().getNom()+":"+coutMP.getQuantChargeSupp()+"<br><br>";
		
	}
		
	
	mail1= "<HTML><b>Charge Supplémentaire OF N°  :</b>"+production.getNumOF()+"  <br><br>";
			
	mail3		= "Merci pour votre confiance<br>"
			+ "Service Informatique<br>"
			+"Système Production</HTML>";
		
	
	return mail1+mail2+mail3;
}
}
