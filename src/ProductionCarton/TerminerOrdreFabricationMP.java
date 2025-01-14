package ProductionCarton;

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
import util.Utils;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.ClientDAO;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.CompteurEmployeProdDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.CompteurResponsableProdDAO;
import dao.daoManager.DetailProdGenDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.EquipeDAO;
import dao.daoManager.FactureProductionDAO;
import dao.daoManager.FicheEmployeDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.ProductionMPDAO;
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
import dao.entity.CoutProdMP;
import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationMP;
import dao.entity.DetailFactureProduction;
import dao.entity.DetailProdGen;
import dao.entity.DetailProduction;
import dao.entity.DetailProductionMP;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.Equipe;
import dao.entity.FactureProduction;
import dao.entity.FicheEmploye;
import dao.entity.MatierePremier;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.ProductionMP;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.TransferStockMP;
import dao.entity.TransferStockPF;

import java.awt.Component;


public class TerminerOrdreFabricationMP extends JLayeredPane implements Constantes{

	public JLayeredPane contentPane;	
	
	private DefaultTableModel	 modeleMP;
	private DefaultTableModel	 modeleEmploye;
	private DefaultTableModel	 modeleEquipeEm;
	private DefaultTableModel	 modeleEquipeGen;
	private JXTable table= new JXTable();
	private JXTable tableEmploye= new JXTable();
	private ImageIcon imgModifier;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	
	
	private JButton btnChercherOF;
	private JButton btnTerminerOF;
	private JButton btnRechercher;
	private JTextField txtPrixServiceProd;
	
	private List<CoutProdMP> listCoutProdMP =new ArrayList<CoutProdMP>();
	private List<Employe> listEmploye=new ArrayList<Employe>();
	
	private List<DetailProductionMP> listDetailProductionMP =new ArrayList<DetailProductionMP>();
	private ProductionMP productionMP = new ProductionMP();
	
	private Map< Integer, String> mapDelaiEmploye = new HashMap<>();
	private Map< Integer, String> mapDelaiEmployeEmabalage = new HashMap<>();
	
	private Map< Integer, String> mapHeureSupp25EmployeProd = new HashMap<>();
	private Map< Integer, String> mapHeureSupp50EmployeProd = new HashMap<>();
	
	private Map< Integer, String> mapHeureSupp25EmployeEmbalage = new HashMap<>();
	private Map< Integer, String> mapHeureSupp50EmployeEmbalage = new HashMap<>();
	
	private Map< String, String> mapQuantiteConsomme = new HashMap<>();
	private Map< String, String> mapQuantiteDechet = new HashMap<>();
	private Map< String, String> mapQuantiteReste = new HashMap<>();
	public JTextField txtNumOF;
	private JTextField txtQuantiteRealise;
	private JLabel lblQuantitRalise;
	
	private BigDecimal coutTotalEmploye=BigDecimal.ZERO;
	private BigDecimal coutTotalEmployeEmbalage=BigDecimal.ZERO;
	private BigDecimal coutTotalAutreEmploye=BigDecimal.ZERO;
	private BigDecimal coutTotalMP=BigDecimal.ZERO;
	private BigDecimal coutTotalDechet=BigDecimal.ZERO;
	private BigDecimal delaiTotal=BigDecimal.ZERO;
	private BigDecimal delaiTotalEquipeEmbalage;
	

	private CompteurProductionDAO compteurProductionDAO;
	private StockMPDAO stockMPDAO;
	private StockPFDAO stockPFDAO;
	private ProductionMPDAO productionMPDAO;
	private TransferStockPFDAO transferStockPFDAO;
	private ParametreDAO parametreDAO;
	private FicheEmployeDAO ficheEmployeDAO;
	private CompteurResponsableProdDAO compteurResponsableProdDAO;
	private CompteurEmployeProdDAO compteurEmployeProdDAO;
	private  EquipeDAO equipeDAO;
	private FactureProductionDAO factureProductionDAO;
	private MatierePremiereDAO matierePremiereDAO;
	private CompteStockMPDAO compteStockMPDAO;
	private boolean validerSaisie=false;
	private String codeDepot;
	private DetailTransferMPDAO detailTransferStockMPDAO;
	private TransferStockMPDAO transferStockMPDAO;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	@SuppressWarnings("serial")
	public TerminerOrdreFabricationMP(ProductionMP productionMPP,String quantite, String nbreHeure) {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 648);
        try{
        	
        	
        	
        	
        	
        	delaiTotalEquipeEmbalage=BigDecimal.ZERO;
        	delaiTotal=BigDecimal.ZERO;
        	coutTotalEmployeEmbalage=BigDecimal.ZERO;
        	coutTotalDechet=BigDecimal.ZERO;
        	coutTotalMP=BigDecimal.ZERO;
        	
        	listCoutProdMP =new ArrayList<CoutProdMP>();
        	listEmploye=new ArrayList<Employe>();
        	listDetailProductionMP =new ArrayList<DetailProductionMP>();
        
        	detailTransferStockMPDAO=new DetailTransferMPDAOImpl();
        	transferStockMPDAO=new TransferStockMPDAOImpl();
        	mapDelaiEmploye = new HashMap<>();
        	mapDelaiEmployeEmabalage= new HashMap<>();
        	mapQuantiteConsomme = new HashMap<>();
        	mapQuantiteDechet = new HashMap<>();
        	mapQuantiteReste = new HashMap<>();
        	
        	mapHeureSupp25EmployeEmbalage= new HashMap<>();
        	mapHeureSupp50EmployeEmbalage= new HashMap<>();
        	mapHeureSupp25EmployeProd= new HashMap<>();
        	mapHeureSupp50EmployeProd= new HashMap<>();
        	productionMPDAO=ProdLauncher.productiompDAO;
        	
        	compteurProductionDAO=ProdLauncher.compteurProductionDAO;
        	transferStockPFDAO=ProdLauncher.transferStockPFDAO;
        	stockMPDAO=ProdLauncher.stockMPDAO;
        	stockPFDAO=ProdLauncher.stockPFDAO;
        	parametreDAO=ProdLauncher.parametreDAO;
        	ficheEmployeDAO=ProdLauncher.ficheEmployeDAO;
        	compteurResponsableProdDAO=ProdLauncher.compteurResponsableProdDAO;
        	compteurEmployeProdDAO=ProdLauncher.compteurEmployeProdDAO;
        	equipeDAO=ProdLauncher.equipeDAO;
        	factureProductionDAO=ProdLauncher.factureProductionDAO;
        	matierePremiereDAO=ProdLauncher.dao;
        	compteStockMPDAO=ProdLauncher.compteStockMPDAO;
        	txtQuantiteRealise=new JTextField();
        	 util.Utils.copycoller(txtQuantiteRealise);
			txtNumOF=new JTextField();
			  util.Utils.copycoller(txtNumOF);
			 txtPrixServiceProd = new JTextField();
			 util.Utils.copycoller(txtPrixServiceProd);
        	if(productionMPP.getNumOFMP()!=null)
        	{
        		
        		productionMP=productionMPP;
        		txtNumOF.setText(productionMP.getNumOFMP());
        		txtQuantiteRealise.setText(quantite);
        		txtPrixServiceProd.setText(nbreHeure);
        		
        		
        		AfficherMatierePremiere();
        		
        		
        	}
        	else {	
        		productionMP = new ProductionMP();
        	}
        	
        	
        	
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de donn�es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
        validerSaisie=false;
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
          } catch (Exception exp){exp.printStackTrace();
          }
        codeDepot= AuthentificationView.utilisateur.getCodeDepot();
				  		
				  		  		intialiserTableMP();
				  		  		initialiserTableauEmploye();
				  		  	
				  		  btnChercherOF = new JButton("Chercher OF");
				  		  btnChercherOF.setHorizontalAlignment(SwingConstants.LEADING);
				  		  btnChercherOF.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		productionMP=productionMPDAO.findByNumOFMP(txtNumOF.getText(),codeDepot);
				  				if(productionMP!=null){
				  				    
				  			  		 if(txtQuantiteRealise.getText().equals("")){
				  			  			JOptionPane.showMessageDialog(null, "Il faut saisir la quantit� r�alis�e", "Erreur", JOptionPane.ERROR_MESSAGE);
				  					  }	else {
				  			  	List<CoutProdMP>listCoutProdMPTmp=productionMPDAO.listeCoutProdMP(productionMP.getId());
				  			  	afficherDetailPorduction(productionMP.getArticlesMP().getDetailEstimationMP(),listCoutProdMPTmp);
				  			//	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				  			//	String dateProduction=dateFormat.format(productionMP.getDateProduction());
				  				
				  				List<DetailProductionMP> listDetailProductionMP=productionMPDAO.listeDetailProduction(productionMP.getId());
				  				
				  				afficher_tableMP(listCoutProdMPTmp);
				  				afficher_tableEmploye(listDetailProductionMP);
				  			
				  				
				  					  }
				  				}else{
				  				  JOptionPane.showMessageDialog(null, "OF n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
				  					
				  				}
				  		
				  		  	}
				  		  });
				  		  
				  		  
				  	
				  		btnChercherOF.setIcon(new ImageIcon(CreerOrdreFabricationMP.class.getResource("/img/chercher.png")));
				  		 btnChercherOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnChercherOF.setBounds(871, 8, 120, 23);
				  		 add(btnChercherOF);
				  		    
				  		    btnTerminerOF = new JButton("Terminer OF");
				  		    btnTerminerOF.setBounds(514, 606, 112, 24);
				  		    add(btnTerminerOF);
				  		    btnTerminerOF.setIcon(imgAjouter);
				  		    btnTerminerOF.addActionListener(new ActionListener() {
		  		     	public void actionPerformed(ActionEvent e) {
			  		     	
				  		     	  int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment Terminer cet Ordre de Fabrication?", 
										"Satisfaction", JOptionPane.YES_NO_OPTION);
									 
									if(reponse == JOptionPane.YES_OPTION )
										{
				  		     		if(txtQuantiteRealise.getText().equals("")){
				  		     			JOptionPane.showMessageDialog(null, "Il faut saisir la quantit� r�alis�e!", "Erreur", JOptionPane.ERROR_MESSAGE);
				  		     		}else if(txtPrixServiceProd.getText().equals("")){
				  		     			JOptionPane.showMessageDialog(null, "Il faut saisir le Prix Unitaire :Service Production!", "Erreur", JOptionPane.ERROR_MESSAGE);
				  		     	
			  		     		}
				  		     		else {
				  		     		if(productionMP.getStatut().equals(Constantes.ETAT_OF_LANCER)) {
				  		     			
				  		     			if(validerSaisie=false){
				  		     				JOptionPane.showMessageDialog(null, "Il faut valider la saisie!", "Erreur", JOptionPane.ERROR_MESSAGE);
				  		     			}else {
			  		     			BigDecimal coutTotal=coutTotalAutreEmploye.add(coutTotalEmploye).add(coutTotalDechet).add(coutTotalMP);
				  		     			
			  		     			productionMP.setNbreHeure(new BigDecimal(txtPrixServiceProd.getText()));
			  		     			productionMP.setQuantiteReel(new BigDecimal(txtQuantiteRealise.getText()));
			  		     		//	productionMP.setDateProduction(new Date());
			  		     			productionMP.setUtilisateurMAJ(AuthentificationView.utilisateur);
				  		     		
				  		     		 /*d�lai des employ�s Production*/
				  		     		
				  		     		for(int j=0;j<tableEmploye.getRowCount();j++){
			  		     			
				  		     			if(!tableEmploye.getValueAt(j, 3).toString().equals("")){
				  		     			mapDelaiEmploye.put(Integer.parseInt(tableEmploye.getValueAt(j, 0).toString()), tableEmploye.getValueAt(j, 3).toString());
				  		     			delaiTotal=delaiTotal.add(new BigDecimal(tableEmploye.getValueAt(j, 3).toString())) ;
				  		     			}else 
			  		     				mapDelaiEmploye.put(Integer.parseInt(tableEmploye.getValueAt(j, 0).toString()), String.valueOf(0));
				  		     			
				  		     			if(!tableEmploye.getValueAt(j, 4).toString().equals("")){
					  		     				mapHeureSupp25EmployeProd.put(Integer.parseInt(tableEmploye.getValueAt(j, 0).toString()), tableEmploye.getValueAt(j, 4).toString());
					  		     			}else 
					  		     				mapHeureSupp25EmployeProd.put(Integer.parseInt(tableEmploye.getValueAt(j, 0).toString()), String.valueOf(0));
				  		     			
				  		     			if(!tableEmploye.getValueAt(j, 5).toString().equals("")){
				  		     				mapHeureSupp50EmployeProd.put(Integer.parseInt(tableEmploye.getValueAt(j, 0).toString()), tableEmploye.getValueAt(j, 5).toString());
				  		     			}else 
				  		     				mapHeureSupp50EmployeProd.put(Integer.parseInt(tableEmploye.getValueAt(j, 0).toString()), String.valueOf(0));
			  		     		}
			  		     		
			  		     		/* d�lai des employ�s Emabalege*/
			  		     	
			  		     		
			  		     	
				  		     		listDetailProductionMP=productionMP.getDetailProductionsMP();
				  		     		
				  		     		productionMP.setDetailProductionsMP(calculeCoutEmploye(listDetailProductionMP,mapDelaiEmploye));
				  		     		remplirQuantite();
				  		     		 
				  		     		productionMP.setListCoutProdMP(calculeCoutMatierePremiere(productionMP.getListCoutProdMP()));
				  		     		
				  		     		
				  		     		productionMP.setCoutTotalMP(coutTotalMP);
				  		     		productionMP.setCoutTotalEmploye(coutTotalEmploye);
				  		     		productionMP.setCoutDechet(coutTotalDechet);
				  		     		coutTotal=coutTotalAutreEmploye.add(coutTotalEmploye).add(coutTotalEmployeEmbalage).add(coutTotalMP) ;
				  		     		productionMP.setCoutTotal(coutTotal);
				  		     		productionMP.setStatut(Constantes.ETAT_OF_TERMINER);
				  		     		productionMPDAO.edit(productionMP);
				  		     		calculerStockCoutProduitFini(coutTotal);
				  		     		
			  		     		JOptionPane.showMessageDialog(null, "Ordre de Fabrication Termin� avec succ�s!", "Succ�s", JOptionPane.INFORMATION_MESSAGE);
				  		     		
				  		     		
			  		     			}
			  		     		
			  		     		}else{
			  		     			JOptionPane.showMessageDialog(null, "Ordre de Fabrication n'est pas encore lanc� ou termin�!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		     		}
			  		     	  }
			  		     	 }
				  		     
				  		     	}
				  		     });
				  		    	btnTerminerOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     table.setSortable(false);
				  		  
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
				  		     	scrollPane.setBounds(8, 80, 1213, 186);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(7, 59, 1213, 23);
				  		     	add(titledSeparator);
				  		  
		txtNumOF.addKeyListener(new KeyAdapter() {
		  	@Override
		  	public void keyReleased(KeyEvent e)
		  	{
		  		if (e.getKeyCode() == e.VK_ENTER)
		  		{
		  				
		  		
		  			productionMP=productionMPDAO.findByNumOFMP(txtNumOF.getText(),codeDepot);
		  			if(productionMP!=null){
				  		
				  		  if(txtQuantiteRealise.getText().equals("")){
				  			JOptionPane.showMessageDialog(null, "Il faut saisir la quantit� r�alis�e", "Erreur", JOptionPane.ERROR_MESSAGE);
						  }	else {
				  	List<CoutProdMP>listCoutProdMPTmp=productionMPP.getListCoutProdMP();
				  	afficherDetailPorduction(productionMP.getArticlesMP().getDetailEstimationMP(),listCoutProdMPTmp);
		  			//listEmploye=productionMP.getEquipe().getListEmploye();
		  			//txtDescription.setText(production.getDescription());
		  			//txtNbreHeure.setText(""+production.getQuantiteEstime());
		  			
		  			
		  			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	  				String dateProduction=dateFormat.format(productionMP.getDateProduction());
	  			
		  			
		  			afficher_tableMP(listCoutProdMPTmp);
		  		//	afficher_tableEmploye(listEmploye);
						  }
		  			}else{
		  			  JOptionPane.showMessageDialog(null, "OF n'existe pas", "Erreur", JOptionPane.ERROR_MESSAGE);
		  				
		  			}
		  			
		  		

		  		}}});
		
		txtNumOF.setBounds(79, 6, 153, 26);
		add(txtNumOF);
		txtNumOF.setColumns(10);
		
		JLabel lblNumOF = new JLabel("Num\u00E9ro OF");
		lblNumOF.setBounds(9, 7, 89, 24);
		add(lblNumOF);
		tableEmploye.setSortable(false);
		
		JScrollPane scrollPane_1 = new JScrollPane(tableEmploye);
		scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane_1.setBounds(9, 281, 1212, 314);
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
	//	scrollPane_1.setViewportView(tableEmploye);
		  DefaultCellEditor ce1 = (DefaultCellEditor) tableEmploye.getDefaultEditor(Object.class);
	        JTextComponent textField1 = (JTextComponent) ce1.getComponent();
	        util.Utils.copycollercell(textField1);
		JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
		GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
		gridBagLayout.rowWeights = new double[]{0.0};
		gridBagLayout.rowHeights = new int[]{0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		titledSeparator_1.setTitle("Saisir D\u00E9lai Equipe Production");
		titledSeparator_1.setBounds(9, 265, 594, 17);
		add(titledSeparator_1);
				  		    
				  		    JButton btnValiderSaisie = new JButton("Valider Saisie");
				  		    btnValiderSaisie.addActionListener(new ActionListener() {
				  		    	public void actionPerformed(ActionEvent e) {
				  		    		
				  		    		remplirQuantite();
				  		    		List<CoutProdMP>listCoutProdMPTmp=productionMPDAO.listeCoutProdMP(productionMP.getId());
								  	afficherDetailPorduction(productionMP.getArticlesMP().getDetailEstimationMP(),listCoutProdMPTmp);
				  		    		validerSaisiQuantiteDechetReste(listCoutProdMPTmp);
				  		    	}
				  		    });
				  		    btnValiderSaisie.setBounds(789, 606, 112, 24);
				  		    add(btnValiderSaisie);
				  		    
				  		  
				  		    txtQuantiteRealise.setBounds(345, 6, 153, 26);
				  		    add(txtQuantiteRealise);
				  		    txtQuantiteRealise.setColumns(10);
				  		    
				  		    lblQuantitRalise = new JLabel("Quantit\u00E9 r\u00E9alis\u00E9e:");
				  		    lblQuantitRalise.setBounds(242, 6, 102, 26);
				  		    add(lblQuantitRalise);
				  		    lblQuantitRalise.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		    
				  		   
				  		    txtPrixServiceProd.setBounds(692, 6, 153, 26);
				  		    add(txtPrixServiceProd);
				  		    txtPrixServiceProd.setColumns(10);
				  		    
				  		    JLabel lblQuantite = new JLabel("Delai Service Production :");
				  		    lblQuantite.setBounds(539, 6, 143, 26);
				  		    add(lblQuantite);
				  		    lblQuantite.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		    
				  		    JButton btnAnnuler = new JButton("Annuler");
				  		    btnAnnuler.addActionListener(new ActionListener() {
				  		    	public void actionPerformed(ActionEvent e) {
				  		    		
				  		    		if(productionMP.getId()<0){
			  		    			 JOptionPane.showMessageDialog(null, "Il faut Cherercher l'OF � Annuler", "Message", JOptionPane.ERROR_MESSAGE);
				  		    			
				  		    		}else {
				  		    			
				  		    			
				  		    			if(!productionMP.getStatut().equals(ETAT_OF_ANNULER)){
				  		    				 if(productionMP.getStatut().equals(ETAT_OF_TERMINER)){
				  		    					 annulerStockMatierePremiere(productionMP.getListCoutProdMP(),productionMP.getMagasinProd().getId(),productionMP.getMagasinStockage().getId());
				  		    			
				  		    					 deleteListeObject(ficheEmployeDAO.findByNumOf(productionMP.getNumOFMP()));
				  		    					 productionMP.setStatut(ETAT_OF_ANNULER);
				  		    					 productionMP.setUtilisateurAnnulation(AuthentificationView.utilisateur);
				  		    					 annulerStockProduitFini();
				  		    					 
				  		    					 productionMPDAO.edit(productionMP);
				  		    			
				  		    			JOptionPane.showMessageDialog(null, "OF Annul� avec succ�s", "Message", JOptionPane.ERROR_MESSAGE); 
				  		    				 }else{
				  		    					JOptionPane.showMessageDialog(null, "OF doit �tre Termin�", "Message", JOptionPane.ERROR_MESSAGE); 
				  		    				 }
				  		    			
				  		    			}else{
				  		    				JOptionPane.showMessageDialog(null, "OF est d�j� Annul�", "Message", JOptionPane.ERROR_MESSAGE);
				  		    			}
				  		    		}
				  		    		
				  		    		
				  		    	}
				  		    });
				  		    btnAnnuler.setBounds(636, 607, 143, 23);
				  		    add(btnAnnuler);
				  		    
				  		    JButton btnSaisirDelaiEmploy_1 = new JButton("Saisir Delai Employ\u00E9 Production");
				  		    btnSaisirDelaiEmploy_1.addActionListener(new ActionListener() {
				  		    	public void actionPerformed(ActionEvent arg0) {
				  		    		if(productionMP.getId()>0)
				  		    		{
				  		    			JFrame popupJFrame = new ListeEmployeProdCarton(productionMP,txtQuantiteRealise.getText(),txtPrixServiceProd.getText());
						  		    	  popupJFrame.setVisible(true);
				  		    		}else
				  		    		{
				  		    			JOptionPane.showMessageDialog(null, "Ordre de fabrication introuvable !!", "Erreur", JOptionPane.ERROR_MESSAGE);
				  		    		}
				  		    		
				  		    		
				  		    		
				  		    	}
				  		    });
				  		    btnSaisirDelaiEmploy_1.setBounds(319, 607, 185, 23);
				  		    add(btnSaisirDelaiEmploy_1);
				  		    
				  		  afficher_tableEmploye(productionMP.getDetailProductionsMP());
				  		  afficher_tableMP(productionMP.getListCoutProdMP());
				  		
				  		
				 	  		
	}
	
	
	  void AfficherMatierePremiere()
	  {
			
		 
			  
		  	List<CoutProdMP>	listCoutProdMPTmp=productionMPDAO.listeCoutProdMP(productionMP.getId());
		  	afficherDetailPorduction(productionMP.getArticlesMP().getDetailEstimationMP(),listCoutProdMPTmp);
			
			
		
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String dateproduction=dateFormat.format(productionMP.getDateProduction());
		
			
			
			List<DetailProductionMP> listDetailProductionMP=productionMPDAO.listeDetailProduction(productionMP.getId());
		
			
			afficher_tableMP(listCoutProdMPTmp);
			afficher_tableEmploye(listDetailProductionMP);
			
			
	  }
	
void	intialiserTableMP(){
		 modeleMP =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Code","Nom MP","Quantit� Calcul�e","Quantit� Existante","Quantit� Charg�e","Charge Supp", "Quantit� Consomm�e", "Quantit� D�chet", "Quantit� Rest�e", "Ecart"
	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false,false,false,false,false, true, true, true,false
	  		     	};
	  		     	public boolean isCellEditable(int row, int column) {
	  		     		return columnEditables[column];
	  		     	}
	  		     };
	  		     
	  		   table.setModel(modeleMP); 
	  		   table.getColumnModel().getColumn(0).setPreferredWidth(40);
	  		   table.getColumnModel().getColumn(1).setPreferredWidth(160);
	  		   table.getColumnModel().getColumn(2).setPreferredWidth(60);
	  		   table.getColumnModel().getColumn(3).setPreferredWidth(60);
	  		   table.getColumnModel().getColumn(4).setPreferredWidth(60);
	  		   table.getColumnModel().getColumn(5).setPreferredWidth(60);
	  		   table.getColumnModel().getColumn(6).setPreferredWidth(60);
	  		   table.getColumnModel().getColumn(7).setPreferredWidth(60);
	  		   table.getColumnModel().getColumn(8).setPreferredWidth(60);
	  		   table.getColumnModel().getColumn(9).setPreferredWidth(60);
	}
	
void afficher_tableMP(List<CoutProdMP> listCoutMP)
	{
	
	intialiserTableMP();
		  int i=0;
		  NumberFormat nf = new DecimalFormat("0.###");
		
			while(i<listCoutMP.size())
			{	
				CoutProdMP coutMP=listCoutMP.get(i);
			
				BigDecimal quantiteTotal=coutMP.getQuantite();
				BigDecimal quantiteExistante=coutMP.getQuantExistante();
				BigDecimal quantiteCharge=coutMP.getQuantCharge();
				BigDecimal quantitechargeSupp=coutMP.getQuantChargeSupp();
				BigDecimal quantiteConsomme=coutMP.getQuantConsomme();
				BigDecimal quantiteDechet=coutMP.getQuantDechet();
				BigDecimal quantiteReste=coutMP.getQuantReste();
				BigDecimal ecart=(quantiteCharge.add(quantitechargeSupp).add(quantiteExistante) ).subtract(quantiteConsomme.add(quantiteDechet).add(quantiteReste));
				
				//ecart=NumberUtils.roundHalfDown(ecart,2 );
				//  String strEcart = nf.format(ecart);
				
				Object []ligne={coutMP.getMatierePremier().getCode(),coutMP.getMatierePremier().getNom(),quantiteTotal.setScale(6)+" "+coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getUnite(),quantiteExistante.setScale(6),quantiteCharge.setScale(6),quantitechargeSupp.setScale(6),quantiteConsomme.setScale(6),quantiteDechet.setScale(6),quantiteReste.setScale(6),ecart};

				modeleMP.addRow(ligne);
				i++;
			}
			  table.setModel(modeleMP); 
	}
	

void afficher_tableEmploye(List<DetailProductionMP> listDetailProductionMP)
	{
	initialiserTableauEmploye();
	BigDecimal delai; 
	BigDecimal heureSupp25; 
	BigDecimal heureSupp50; 
	boolean absent=false;
		  int i=0;
			while(i<listDetailProductionMP.size())
			{	
				DetailProductionMP detailProductionMP=listDetailProductionMP.get(i);
				delai=detailProductionMP.getDelaiEmploye();
				heureSupp25=detailProductionMP.getHeureSupp25();
				heureSupp50=detailProductionMP.getHeureSupp50();
				absent=detailProductionMP.isAbsent();
				Object []ligne={detailProductionMP.getEmploye().getId(),detailProductionMP.getEmploye().getNumDossier(),detailProductionMP.getEmploye().getNom(),delai,heureSupp25,heureSupp50,absent};

				modeleEmploye.addRow(ligne);
				i++;
			}
			tableEmploye.setModel(modeleEmploye);
	}





	
List<DetailProductionMP> calculeCoutEmploye(List<DetailProductionMP> listDetailProductionMP,Map< Integer, String> mapDelaiEmploye){
		BigDecimal delai=BigDecimal.ZERO;
		
		BigDecimal remise=BigDecimal.ZERO;
		BigDecimal coutHoraire=BigDecimal.ZERO;
		BigDecimal heureSupp25; 
		BigDecimal heureSupp50; 
		
		BigDecimal coutSupp25=BigDecimal.ZERO;
		BigDecimal coutSupp50=BigDecimal.ZERO;
		
		List<DetailProductionMP> listDetailProductionMPTmp= new ArrayList<DetailProductionMP>();
		for(int i=0;i<listDetailProductionMP.size();i++){
			
			DetailProductionMP detailProductionMP =listDetailProductionMP.get(i);
			
			if(!detailProductionMP.getEmploye().isSalarie()){
			
			if(detailProductionMP.isAbsent()==true){
	    		
		   		 String code=Utils.genereCodeDateMoisAnnee(productionMP.getDateProduction());
					 
		   		 Utils.compterAbsenceEmploye(code, detailProductionMP.getEmploye(), productionMP.getDateProduction());
		   		}
			
			delai=new BigDecimal(mapDelaiEmploye.get(detailProductionMP.getEmploye().getId()));
			heureSupp25=new BigDecimal(mapHeureSupp25EmployeProd.get(detailProductionMP.getEmploye().getId()));
			heureSupp50=new BigDecimal(mapHeureSupp50EmployeProd.get(detailProductionMP.getEmploye().getId()));
			
				
			coutHoraire=detailProductionMP.getEmploye().getCoutHoraire().multiply(delai);
			coutSupp25=heureSupp25.multiply(COUT_HEURE_SUPPLEMENTAIRE_25) ;
			coutSupp50=heureSupp50.multiply(COUT_HEURE_SUPPLEMENTAIRE_50) ;
			
			coutTotalEmploye=coutTotalEmploye.add(coutHoraire).add(coutSupp25).add(coutSupp50) ;
			detailProductionMP.setCoutTotal(coutHoraire);
			detailProductionMP.setDelaiEmploye(delai);
			detailProductionMP.setHeureSupp25(heureSupp25);
			detailProductionMP.setHeureSupp50(heureSupp50);
			detailProductionMP.setCoutSupp25(coutSupp25);
			detailProductionMP.setCoutSupp50(coutSupp50);
			detailProductionMP.setRemise(remise);
			
			
			if(!detailProductionMP.getEmploye().isSalarie()){
			FicheEmploye ficheEmploye =ficheEmployeDAO.findByPeriodeDateSitutation(productionMP.getDateProduction(), detailProductionMP.getEmploye().getId());
			if(ficheEmploye!=null){
				/*Remplir fiche programme*/
				coutHoraire=coutHoraire.add(ficheEmploye.getCoutTotal()) ;
				delai=delai.add(ficheEmploye.getDelaiEmploye());
				String numOF=ficheEmploye.getNumOF()+"-"+productionMP.getNumOFMP();
				BigDecimal delaiProd=ficheEmploye.getDelaiProd().add(productionMP.getNbreHeure()) ;
		/*	ficheEmploye.setDateSituation(production.getDate());
			
			ficheEmploye.setEmploye(detailProdGen.getEmploye());;
			
			ficheEmploye.setHeureSupp25(heureSupp25);
			ficheEmploye.setHeureSupp50(heureSupp50);
			ficheEmploye.setCoutSupp25(coutSupp25);
			ficheEmploye.setCoutSupp50(coutSupp50);*/
			
			ficheEmploye.setNumOF(numOF);
			ficheEmploye.setCoutTotal(coutHoraire);
			ficheEmploye.setDelaiProd(delaiProd);
			
			ficheEmploye.setDelaiEmploye(delai);
			
			 if(detailProductionMP.isAbsent()==false && ficheEmploye.getDelaiEmploye().compareTo(ficheEmploye.getDelaiProd())  >=0){
		   			
		   		 Parametre parametre_remise_ouvrier=parametreDAO.findByCode(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
				 Parametre parametre_remise_ouvrier_vrac=parametreDAO.findByCode(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);
					
					if(detailProductionMP.getEmploye().getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
						remise=parametre_remise_ouvrier.getValeur();
					if(detailProductionMP.getEmploye().getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
						remise=parametre_remise_ouvrier_vrac.getValeur();
		   			
		   		}else {
		   			remise=BigDecimal.ZERO;
		   		}
			 ficheEmploye.setRemise(remise);
			ficheEmployeDAO.edit(ficheEmploye);
			} else{
				ficheEmploye =new FicheEmploye();
				ficheEmploye.setCoutTotal(coutHoraire);
				ficheEmploye.setNumOF(productionMP.getNumOFMP());
				ficheEmploye.setDateSituation(productionMP.getDateProduction());
				ficheEmploye.setDelaiEmploye(delai);
				ficheEmploye.setEmploye(detailProductionMP.getEmploye());;
				
				ficheEmploye.setHeureSupp25(heureSupp25);
				ficheEmploye.setHeureSupp50(heureSupp50);
				ficheEmploye.setCoutSupp25(coutSupp25);
				ficheEmploye.setCoutSupp50(coutSupp50);
				
				
				 if(detailProductionMP.isAbsent()==false && delai.compareTo(productionMP.getNbreHeure()) >=0){
			   			
			   		 Parametre parametre_remise_ouvrier=parametreDAO.findByCode(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION);
					 Parametre parametre_remise_ouvrier_vrac=parametreDAO.findByCode(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE);
						
						if(detailProductionMP.getEmploye().getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_PRODUCTION))
							remise=parametre_remise_ouvrier.getValeur();
						if(detailProductionMP.getEmploye().getTypeResEmploye().getCode().equals(TYPE_EMPLOYE_MAIN_OUVRE_EN_VRAC))
							remise=parametre_remise_ouvrier_vrac.getValeur();
			   			
			   		}else {
			   			remise=BigDecimal.ZERO;
			   		}
				 
				 ficheEmploye.setRemise(remise);
				 ficheEmploye.setDelaiProd(productionMP.getNbreHeure());
				ficheEmployeDAO.add(ficheEmploye);
				
			}
			}
			
			listDetailProductionMPTmp.add(detailProductionMP);
		}
		}	
		return listDetailProductionMPTmp;
		
	}




boolean remplirQuantite(){
	boolean trouve=false;
	for(int j=0;j<table.getRowCount();j++){
		
		if(!table.getValueAt(j, 7).toString().equals("")){
			mapQuantiteDechet.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 7).toString()); 
			trouve=true;
		}else {
			mapQuantiteDechet.put(table.getValueAt(j, 0).toString(), String.valueOf(0));
		}
		if(!table.getValueAt(j, 8).toString().equals("")){
			mapQuantiteReste.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 8).toString());
			trouve=true;
		}else {
			mapQuantiteReste.put(table.getValueAt(j, 0).toString(),  String.valueOf(0));
		}
		
	}
	return trouve;
}
List<CoutProdMP> validerSaisiQuantiteDechetReste(List<CoutProdMP> listCoutProdMP) {
	
	BigDecimal quantiteDechet=BigDecimal.ZERO;
	BigDecimal quantiteReste=BigDecimal.ZERO;
	
	List<CoutProdMP> listCoutProdMPTmp=new ArrayList<CoutProdMP>();
	
for(int i=0;i<listCoutProdMP.size();i++){ 
		
		CoutProdMP coutMP=listCoutProdMP.get(i);
		
		quantiteDechet=new BigDecimal(mapQuantiteDechet.get(coutMP.getMatierePremier().getCode()));
		quantiteReste=new BigDecimal(mapQuantiteReste.get(coutMP.getMatierePremier().getCode()));
		
		coutMP.setQuantDechet(quantiteDechet);
		coutMP.setQuantReste(quantiteReste);
		//listCoutMP.set(i,coutMP);
		listCoutProdMPTmp.add(coutMP);
}
afficher_tableMP(listCoutProdMPTmp);

validerSaisie=true;
return listCoutProdMPTmp;
	
}

List<CoutProdMP>  calculeCoutMatierePremiere(List<CoutProdMP> listCoutProdMP){
	BigDecimal quantiteDechet=BigDecimal.ZERO;
	BigDecimal quantiteConsomme=BigDecimal.ZERO;
	BigDecimal quantiteReste=BigDecimal.ZERO;
	BigDecimal quantiteMP=BigDecimal.ZERO;
	
	BigDecimal prixMP=BigDecimal.ZERO;
	BigDecimal coutDechet=BigDecimal.ZERO;
	List<CoutProdMP> listCoutProdMPTmp=new ArrayList<CoutProdMP>();
	for(int i=0;i<listCoutProdMP.size();i++){ 
		
		CoutProdMP coutProdMP=listCoutProdMP.get(i);

		StockMP stockmp=stockMPDAO.findStockByMagasinMP(coutProdMP.getMatierePremier().getId(), productionMP.getMagasinProd().getId());
		
		//quantiteConsomme=Integer.parseInt(mapQuantiteConsomme.get(coutMP.getMatierePremier().getCode()));
		quantiteConsomme=coutProdMP.getQuantConsomme();
		if((quantiteConsomme.setScale(6, RoundingMode.HALF_UP).subtract(quantiteConsomme.setScale(0,RoundingMode.FLOOR ))).compareTo(new BigDecimal(0.5))>=0)
		{
			quantiteConsomme=quantiteConsomme.add(BigDecimal.ONE).setScale(0,RoundingMode.FLOOR);
		}else
		{
			quantiteConsomme=quantiteConsomme.setScale(0,RoundingMode.FLOOR );	
		}
		
		
		quantiteDechet=new BigDecimal(mapQuantiteDechet.get(coutProdMP.getMatierePremier().getCode()));
		quantiteReste=new BigDecimal(mapQuantiteReste.get(coutProdMP.getMatierePremier().getCode()));
		//quantiteReste=stockmp.getStock()-(quantiteConsomme+quantiteDechet);
		coutProdMP.setQuantConsomme(quantiteConsomme);
		coutProdMP.setQuantDechet(quantiteDechet);
		//quantiteMP=quantiteConsomme+coutMP.getQuantChargeSupp();
		prixMP=quantiteConsomme.multiply(coutProdMP.getPrixUnitaire()) ;
		coutDechet=quantiteDechet.multiply(coutProdMP.getPrixUnitaire()) ;
		coutProdMP.setPrixTotal(prixMP);
		coutProdMP.setCoutDechet(coutDechet);
		coutTotalMP=coutTotalMP.add(prixMP) ;
		coutTotalDechet=coutTotalDechet.add(coutDechet)  ;
		//quantiteReste=stockmp.getStock()-quantiteConsomme;
		
	 if(stockmp!=null)
	 {
		 quantiteMP=stockmp.getStock().subtract(quantiteConsomme.add(quantiteDechet).add(quantiteReste)) ;
	 }
		
		
		if(quantiteMP.compareTo(BigDecimal.ZERO) !=0){
			
			 Calendar cal = Calendar.getInstance();
		     cal.setTime(productionMP.getDateProduction());
		     int  annee = cal.get(Calendar.YEAR);
		     int mois = cal.get(Calendar.MONTH)+1;
		     
		
		CompteStockMP compteStockMP =compteStockMPDAO.findByCodeMPAnneeMois(coutProdMP.getMatierePremier().getCode(),mois,annee);
		if(stockmp!=null)
		{
			stockmp.setStock(BigDecimal.ZERO);
		}
		
		if(compteStockMP==null) {
			compteStockMP=new CompteStockMP();
			compteStockMP.setMatierePremier(coutProdMP.getMatierePremier());
			compteStockMP.setPrixUnitaire(coutProdMP.getPrixUnitaire());
			compteStockMP.setQuantite(quantiteMP);
			compteStockMP.setMois(mois);
			compteStockMP.setAnnee(annee);
			compteStockMPDAO.add(compteStockMP);
			
		}else {
			
			BigDecimal quantite =compteStockMP.getQuantite().add(quantiteMP) ;
			BigDecimal coutTotal =(compteStockMP.getQuantite().multiply(compteStockMP.getPrixUnitaire())).add(quantiteMP.multiply(coutProdMP.getPrixUnitaire()));
			
			BigDecimal prixUnitaire=coutTotal.divide(quantite, 6, BigDecimal.ROUND_HALF_UP) ;
			
			
			compteStockMP.setQuantite(quantite);
			compteStockMP.setPrixUnitaire(prixUnitaire);
			
			compteStockMPDAO.edit(compteStockMP);
			
			
		}
	}
		if(stockmp!=null)
		{
			stockmp.setStock(quantiteReste);
			stockMPDAO.edit(stockmp);
			
		}
		
	}
	return listCoutProdMPTmp;
  }
void afficherDetailPorduction(List<DetailEstimationMP> lisDetailEstimationMP,List<CoutProdMP> listCoutProdMP){
	DetailEstimationMP detailEstimationMP=new DetailEstimationMP();
	CoutProdMP coutProdMP=new CoutProdMP();
	CoutProdMP coutProdMPTmp=new CoutProdMP();
	int position=-1;
	BigDecimal quantiteConsommme=BigDecimal.ZERO;
	BigDecimal quantiteRealise=new BigDecimal(txtQuantiteRealise.getText());
	boolean trouve =false;
	int priorite=0;
	BigDecimal quantiteTotalCharge=BigDecimal.ZERO;
	
	for(int i=0;i<lisDetailEstimationMP.size();i++){
		trouve =false;
		detailEstimationMP=lisDetailEstimationMP.get(i);
		for(int j=0;j<listCoutProdMP.size();j++){
			coutProdMP=listCoutProdMP.get(j);
			
			if(detailEstimationMP.getMatierePremier().getId()==coutProdMP.getMatierePremier().getId()){
				
					
				quantiteConsommme=detailEstimationMP.getQuantite().multiply(quantiteRealise) ;
				
				coutProdMP.setQuantConsomme(quantiteConsommme);
				listCoutProdMP.set(j,coutProdMP);
				
				}
				
			
			}
			
		}
	

		
	}













void initialiserTableauEmploye(){
	modeleEmploye =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"ID","Matricule","Nom", "D�lai Travaill�", "H Supp 25%", "H Supp 50%", "Absent"
		     	}
		     ) {
     	boolean[] columnEditables = new boolean[] {
     			false,false,false,true,true,true,true
     	};
    
     	Class[] columnTypes = new Class[] {
     			String.class,String.class,String.class,BigDecimal.class,BigDecimal.class,BigDecimal.class, Boolean.class
			};
      	
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

void initialiserTableauEmployeGen(){
	modeleEquipeGen =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"ID","Matricule","Nom", "D�lai Travaill�", "H Supp 25%", "H Supp 50%", "Absent"
		     	}
		     ) {
     	boolean[] columnEditables = new boolean[] {
     			false,false,false,true,true,true,true
     	};
    
     	Class[] columnTypes = new Class[] {
     			String.class,String.class,String.class,BigDecimal.class,BigDecimal.class,BigDecimal.class, Boolean.class
			};
      	
	       public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
     	public boolean isCellEditable(int row, int column) {
     		return columnEditables[column];
     	}
     };
}





List<DetailProductionMP>  remplieDetailProdcution(List<Employe> listEmploye){
	List<DetailProductionMP> listDetailProdcutionMP=new ArrayList<DetailProductionMP>();

	
	for(int i=0;i<listEmploye.size();i++){
		DetailProductionMP detailProdMP= new DetailProductionMP();
		Employe employe =listEmploye.get(i);
		detailProdMP.setCoutTotal(BigDecimal.ZERO);
		detailProdMP.setRemise(employe.getRemise());
		detailProdMP.setEmploye(employe);
		detailProdMP.setProductionMP(productionMP);
		
		//listDetailProdcution.add(detailProd);
		productionMP.getDetailProductionsMP().add(detailProdMP);
	}
//	production.setDetailProductions(listDetailProdcution);
	
	
/*	List<DetailProduction> listDetailProdcutionTmp=production.getDetailProductions();
	DetailProduction detailProdDeleted=new DetailProduction();
	if(listDetailProdcutionTmp!=null && listDetailProdcutionTmp.size()>0){
	for(int j=0;j<listDetailProdcutionTmp.size();j++){
		DetailProduction detailProd= listDetailProdcutionTmp.get(j);
		if(!listEmploye.contains(detailProd.getEmploye())){
			detailProdDeleted=production.removeDetailProduction(detailProd);
			System.out.println("######"+detailProdDeleted.getId());
		}
	}
	
	}*/
	productionMPDAO.edit(productionMP);
	
	return productionMP.getDetailProductionsMP();
  }


void  annulerStockMatierePremiere(List<CoutProdMP> listCoutProdMP,int idMagasinProd,int idMagasinStockage){
	BigDecimal quantiteStockage=BigDecimal.ZERO;
	BigDecimal quantiteCharge=BigDecimal.ZERO;
	BigDecimal quantiteExistante=BigDecimal.ZERO;
	for(int i=0;i<listCoutProdMP.size();i++){ 
		quantiteStockage=BigDecimal.ZERO;
		CoutProdMP coutProdMP=listCoutProdMP.get(i);
	
		
		 quantiteCharge=coutProdMP.getQuantCharge();
		StockMP stockMPProd=stockMPDAO.findStockByMagasinMP(coutProdMP.getMatierePremier().getId(),idMagasinProd );
		StockMP stockMPStockage=stockMPDAO.findStockByMagasinMP(coutProdMP.getMatierePremier().getId(),idMagasinStockage );
		quantiteExistante=coutProdMP.getQuantExistante().add(stockMPProd.getStock()) ;
		
		quantiteStockage=stockMPStockage.getStock().add(quantiteCharge) ;
		
		
		stockMPProd.setStock(quantiteExistante);
		stockMPStockage.setStock(quantiteStockage);
		
		coutProdMP.setCoutDechet(BigDecimal.ZERO);
		coutProdMP.setQuantCharge(BigDecimal.ZERO);
		coutProdMP.setQuantChargeSupp(BigDecimal.ZERO);
		coutProdMP.setQuantConsomme(BigDecimal.ZERO);
		coutProdMP.setQuantDechet(BigDecimal.ZERO);
		coutProdMP.setQuantExistante(BigDecimal.ZERO);
		coutProdMP.setQuantite(BigDecimal.ZERO);
		coutProdMP.setQuantReste(BigDecimal.ZERO);
		listCoutProdMP.set(i, coutProdMP);
	//	listCoutMP.remove(i);

		stockMPDAO.edit(stockMPStockage);
		stockMPDAO.edit(stockMPProd);
		
		
	}
	
	productionMP.setListCoutProdMP(listCoutProdMP);
  }
void  annulerStockProduitFini(){

	MatierePremier matierePremier=matierePremiereDAO.findByCode(productionMP.getArticlesMP().getCodeArticle());
	
	 StockMP stockMP = stockMPDAO.findStockByMagasinMP(matierePremier.getId(),productionMP.getMagasinStockage().getId());
	 
	 BigDecimal p1=productionMP.getCoutTotal().divide(productionMP.getQuantiteReel(), 6, BigDecimal.ROUND_HALF_UP)  ;
	 BigDecimal q1=productionMP.getQuantiteReel();
	 
	 BigDecimal montantQ1P1=q1.multiply(p1) ;
	 
	 BigDecimal q2=stockMP.getStock();
	 BigDecimal p2=stockMP.getPrixUnitaire();
	 
	 BigDecimal montantQ2P2=q2.multiply(p2) ;
	 
	 BigDecimal q=q2.subtract(q1) ;
	
	 BigDecimal p = (montantQ2P2 .subtract(montantQ1P1) ).divide(q, 6, BigDecimal.ROUND_HALF_UP)  ;
	 
	 stockMP.setStock(q);
	 stockMP.setPrixUnitaire(p);
	 stockMPDAO.edit(stockMP);
}

void deleteListeObject(List<FicheEmploye> listFicheEmploye){
	
	for(int i=0;i<listFicheEmploye.size();i++){
		FicheEmploye ficheEmploye=listFicheEmploye.get(i);
		ficheEmployeDAO.deleteObject(ficheEmploye);
	}
}


void calculerStockCoutProduitFini(BigDecimal prixTotal){
	MatierePremier matierePremier=matierePremiereDAO.findByCode(productionMP.getArticlesMP().getCodeArticle());
	BigDecimal prixPF=BigDecimal.ZERO;
	BigDecimal nouveauprix=BigDecimal.ZERO;
	BigDecimal quantiteTotal=BigDecimal.ZERO ;
	BigDecimal prixStock=BigDecimal.ZERO;
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy");
	String date="01/01/"+sdf.format(productionMP.getDateProduction())+"";
		Date dateDebut= new Date(date);
		BigDecimal StockFinale=BigDecimal.ZERO;
		
		StockFinale=BigDecimal.ZERO;
		// Stock Finale de ce jour
		List<Object[]> listestockfinale=detailTransferStockMPDAO.StockFinaleMPByMagasin(dateDebut, productionMP.getDateProduction(), productionMP.getMagasinStockage(), matierePremier);
		if(!listestockfinale.isEmpty())
		{
			for(int t=0;t<listestockfinale.size();t++)
 			{
 				
 			 Object[] object=listestockfinale.get(t);
			
 			StockFinale= (BigDecimal)object[1];
			
 			}
		}
		
	//prixTotal=productionMP.getCoutTotalEmploye()+productionMP.getCoutTotalMP()+productionMP.getCoutDechet();
	
	prixPF=prixTotal.divide(productionMP.getQuantiteReel(), 6, BigDecimal.ROUND_HALF_UP)  ;
	
	
	
	 StockMP stockMP = stockMPDAO.findStockByMagasinMP(matierePremier.getId(),productionMP.getMagasinStockage().getId());
	 
	 if(stockMP!=null)
	 {
		 
		 quantiteTotal=StockFinale.add(productionMP.getQuantiteReel());
		 prixStock=StockFinale.multiply(stockMP.getPrixUnitaire());
		 
		 	if(prixStock.compareTo(BigDecimal.ZERO)>0)
		 		nouveauprix=(prixTotal.add(prixStock) ).divide(quantiteTotal, 6, BigDecimal.ROUND_HALF_UP) ;
		 	else 
		 		nouveauprix= prixPF;
		 	
		 	stockMP.setStock(quantiteTotal);
		 	stockMP.setPrixUnitaire(nouveauprix);
		 	stockMPDAO.edit(stockMP);
		 
	 }else
	 {
		 
		 StockMP stockMPTmp=new StockMP(); 
		 
		 stockMPTmp.setMagasin(productionMP.getMagasinStockage());
		 stockMPTmp.setMatierePremier(matierePremier);
		 stockMPTmp.setStock(productionMP.getQuantiteReel());
		 stockMPTmp.setPrixUnitaire(prixPF);
		 stockMPDAO.add(stockMPTmp);
	 }
	 
	 
	TransferStockMP transferStockMP=new TransferStockMP();
	 	
	 	transferStockMP.setCodeTransfer(productionMP.getNumOFMP());
	 	transferStockMP.setCreerPar(productionMP.getUtilisateurCreation());
	 	transferStockMP.setDate(new Date());
	 	transferStockMP.setDateTransfer(productionMP.getDateProduction());
	 	transferStockMP.setDepot(productionMP.getMagasinStockage().getDepot());
	 	transferStockMP.setStatut(Constantes.ETAT_TRANSFER_STOCK_FABRIQUE);
	 	transferStockMPDAO.add(transferStockMP);
	 	
	 	DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
	 	detailTransferStockMP.setMagasinDestination(productionMP.getMagasinStockage());
	 	detailTransferStockMP.setMatierePremier(matierePremier);
	 	detailTransferStockMP.setQuantite(productionMP.getQuantiteReel());
	 	detailTransferStockMP.setTransferStockMP(transferStockMP);	
	 	detailTransferStockMP.setPrixUnitaire(productionMP.getCoutTotal().divide(productionMP.getQuantiteReel(), 6, RoundingMode.HALF_UP));
       detailTransferStockMPDAO.add(detailTransferStockMP);
	 	
	 	
	 	
	 	
}




}
