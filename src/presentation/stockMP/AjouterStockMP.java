package presentation.stockMP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
import java.time.DayOfWeek;
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
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import main1.AuthentificationView;
import main1.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.Utils;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.JourneeDAOImpl;
import dao.daoImplManager.MouvementStockGlobalDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.DepotDAO;
import dao.daoManager.JourneeDAO;
import dao.daoManager.MouvementStockGlobalDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.Depot;
import dao.entity.DetailMouvementStock;
import dao.entity.DetailTransferStockMP;
import dao.entity.Journee;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.MouvementStockGlobal;
import dao.entity.StockMP;
import dao.entity.TransferStockMP;
import dao.entity.Utilisateur;

import com.toedter.calendar.JDateChooser;

import java.util.Locale;


public class AjouterStockMP extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;
	private ImageIcon imgModifier;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgRechercher;
	
	
	private JComboBox<String> comboDepot=new JComboBox();
	private  JComboBox<String> comboMagasin=new JComboBox();;
	

	private Map< String, Integer> mapMagasin = new HashMap<>();
	private Map< String, Depot> mapDepot = new HashMap<>();
	private Map< Integer, String> mapQuantite= new HashMap<>();
	private Map< Integer, String> mapPrixUnitaire= new HashMap<>();
	private Map< Integer, MatierePremier> mapMatierePremiere= new HashMap<>();
	private Map< Integer, BigDecimal> mapQuantiteDetailMouvement= new HashMap<>();
	
	
	
	private List<Depot> listDepot =new ArrayList<Depot>();
	List<StockMP> listStockMP =new ArrayList<StockMP>();
	private List<Journee> listJournee =new ArrayList<Journee>();
	private List<DetailMouvementStock> listDetailMouvementStock =new ArrayList<DetailMouvementStock>();
	
	
	
	
	
	private Utilisateur utilisateur;
	private Depot depot=new Depot();
	private  JDateChooser dateTransfereChooser = new JDateChooser();
	private DepotDAO depotDAO;
	private StockMPDAO stockMPDAO;
	private TransferStockMPDAO transferStockMPDAO;
	private JourneeDAO journeeDAO;
	private MouvementStockGlobalDAO mouvementStockGlobalDAO;
	JButton btnAfficherStock ;
	private JTextField txtcode;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjouterStockMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 565);
        try{
        	mouvementStockGlobalDAO=new MouvementStockGlobalDAOImpl();
        	journeeDAO=new JourneeDAOImpl();
        	depotDAO=new DepotDAOImpl();
        	stockMPDAO=new StockMPDAOImpl();
        	transferStockMPDAO=new TransferStockMPDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        
    	if(utilisateur.getNom().equals("admin"))
      	{
		  listDepot=depotDAO.findAll();
		  int k=0;
		  comboDepot.addItem("");
	     	while (k<listDepot.size())
	     	{
	     		Depot depot=listDepot.get(k);
	     		Magasin magasin=depotDAO.MagasinByTypeMagasinStkAttente(MAGASIN_CODE_TYPE_MP_ATT);
	     		if(magasin!=null)
	     		{
	     			if(depot.getId()!=magasin.getDepot().getId())
		     		{
	     				comboDepot.addItem(depot.getLibelle());
			     		
			     		mapDepot.put(depot.getLibelle(), depot);
			     	
			     		
		     			
		     		}
	     		}else
	     		{
	     			comboDepot.addItem(depot.getLibelle());
		     		
		     		mapDepot.put(depot.getLibelle(), depot);
		     	
		     		
	     		}
	     		k++;
	     		
	     	}
	      
	  }else
      	{
      		
      		Depot depot= depotDAO.findByCode(utilisateur.getCodeDepot());
      		
      		if(depot!=null)
      		{
      			comboDepot.addItem(depot.getLibelle());
      	    	mapDepot.put(depot.getLibelle(), depot);
      		}
      	}
		     	
		     	
        
        
        
        
        
        /*
        if(!utilisateur.getCodeDepot().equals(CODE_DEPOT_SIEGE)	) {
	    		 depot = depotDAO.findByCode(utilisateur.getCodeDepot());
	    		 mapDepot.put(depot.getLibelle(), depot);
	    		comboDepot.addItem(depot.getLibelle());
	    }else {
	    	
	  	listDepot = depotDAO.findAll();	
	      int i=0;
	      	while(i<listDepot.size())
	      		{	
	      			 depot=listDepot.get(i);
	      			mapDepot.put(depot.getLibelle(), depot);
	      			comboDepot.addItem(depot.getLibelle());
	      			i++;
	      		}
	    }
	    
	    */
	    
		btnAfficherStock = new JButton("");
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp.png"));
             imgRechercher= new ImageIcon(this.getClass().getResource("/img/rechercher.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		     table = new JXTable();
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
				  		     intialiserTabeleau();
				  		   DefaultCellEditor ce = (DefaultCellEditor) table.getDefaultEditor(Object.class);
					        JTextComponent textField = (JTextComponent) ce.getComponent();
					        util.Utils.copycollercell(textField);
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 130, 1035, 367);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		      List<Magasin> 	listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);
				  		      if(listMagasin!=null){
				  		    	  
				  		    	  int j=0;
					  		      	while(j<listMagasin.size())
					  		      		{	
					  		      			Magasin magasin=listMagasin.get(j);
					  		      		comboMagasin.addItem(magasin.getLibelle());
					  		      		mapMagasin.put(magasin.getLibelle(), magasin.getId());
					  		      			j++;
					  		      		}
				  		      }
				  		     	
				  		     
					  		      	
					  		      comboDepot.addItemListener(new ItemListener() {
					  		     	 	public void itemStateChanged(ItemEvent e) {
					  		     	 	btnAfficherStock.setVisible(true);
					  		     	 	
					  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
					  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
						  		     	  	 // comboGroupe = new JComboBox();
					  		     	 	comboMagasin.removeAllItems();
					  		     	 
					  		     	 	//comboGroupe.addItem("");
					  		     	 	if(!comboDepot.getSelectedItem().equals(""))
						  		     	  	   	 depot =mapDepot.get(comboDepot.getSelectedItem());
								  		       
						  		     	   listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);
								  		      if(listMagasin!=null && listMagasin.size()> 0){
								  		    	  
								  		    	  int j=0;
									  		      	while(j<listMagasin.size())
									  		      		{	
									  		      			Magasin magasin=listMagasin.get(j);
									  		      			comboMagasin.addItem(magasin.getLibelle());
									  		      			mapMagasin.put(magasin.getLibelle(), magasin.getId());
									  		      			j++;
									  		      		}
								  		      }else {
								  		    	//JOptionPane.showMessageDialog(null, "Ce d�pot ne contient aucun magasin", "Erreur", JOptionPane.ERROR_MESSAGE);
								  		    	btnAfficherStock.setVisible(false);
								  		      }
					  		     	 	 }
					  		     	 	}
					  		     	 });
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(9, 101, 1035, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 36, 1035, 54);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("D\u00E9pot :");
				  		     	lblMachine.setBounds(10, 11, 96, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	
				  		     	 
				  		     	 comboDepot.setBounds(51, 11, 144, 24);
				  		     	 layeredPane.add(comboDepot);
				  		     	
				  		     	 
				  		     	 JLabel lblGroupe = new JLabel("Magasin :");
				  		     	 lblGroupe.setBounds(225, 10, 102, 24);
				  		     	 layeredPane.add(lblGroupe);
				  		     	 lblGroupe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	
				  		     	 comboMagasin.setBounds(281, 11, 192, 24);
				  		     	 layeredPane.add(comboMagasin);
				  		     	 
				  		     	 txtcode = new JTextField();
				  		       util.Utils.copycoller(txtcode);
				  		     	 txtcode.setBackground(Color.WHITE);
				  		     	 txtcode.setEditable(false);
				  		     	 txtcode.setBounds(582, 10, 189, 24);
				  		     	 layeredPane.add(txtcode);
				  		     	 txtcode.setColumns(10);
				  		     	 
				  		     	 JLabel lblCodeTransfert = new JLabel("Code Transfert:");
				  		     	 lblCodeTransfert.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	 lblCodeTransfert.setBounds(483, 11, 102, 24);
				  		     	 layeredPane.add(lblCodeTransfert);
				  		     	 
				  		     	  dateTransfereChooser = new JDateChooser();
				  		     	 dateTransfereChooser.setLocale(Locale.FRANCE);
				  		     	 dateTransfereChooser.setDateFormatString("dd/MM/yyyy");
				  		     	 dateTransfereChooser.setBounds(868, 10, 155, 26);
				  		     	 layeredPane.add(dateTransfereChooser);
				  		     	 
				  		     	 JLabel label = new JLabel("Date Transf\u00E8re :");
				  		     	 label.setBounds(777, 10, 87, 26);
				  		     	 layeredPane.add(label);
				  		   	comboMagasin.setSelectedIndex(-1);
				  		   	comboDepot.setSelectedIndex(-1);
		JButton btnValiderStock = new JButton("Valider Stock");
		btnValiderStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String dateTransfere=((JTextField)dateTransfereChooser.getDateEditor().getUiComponent()).getText();
				
				if(!remplirMapStock())	{
					JOptionPane.showMessageDialog(null, "Il faut Saisir le stock avant de valider", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else if(dateTransfere.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Il faut saisir la date d'ajout", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				else{
					txtcode.setText(Utils.genererCodeTransfer(mapDepot.get(comboDepot.getSelectedItem()).getCode(),ETAT_TRANSFER_STOCK_ENTRE));
					validerStock(listStockMP);
					
					JOptionPane.showMessageDialog(null, "le stock a �t� valid�e avec succ�s", "Succ�s", JOptionPane.INFORMATION_MESSAGE);
					
					
					
					
					
					
					
					
					
					
					
					
					initialierMapStock();
					
				}
			
			}
		});
		btnValiderStock.setBounds(291, 506, 100, 23);
		add(btnValiderStock);
		
		JButton btnInitialiser = new JButton("Initialiser");
		btnInitialiser.setIcon(imgInit);
		btnInitialiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				initialiser();
				
			}
		});
		
		btnAfficherStock.setIcon(imgRechercher);
		btnInitialiser.setBounds(412, 506, 100, 23);
		add(btnInitialiser);
		btnAfficherStock.setBounds(1064, 41, 36, 36);
		add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(comboDepot.getSelectedItem().equals("") || comboMagasin.getSelectedItem().equals(""))	{
				JOptionPane.showMessageDialog(null, "Il faut choisir un magasin", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else {
				txtcode.setText("");
				//txtcode.setText(Utils.genererCodeTransfer(AuthentificationView.utilisateur.getCodeDepot(),ETAT_TRANSFER_STOCK_ENTRE));
				listStockMP=stockMPDAO.listeStockNouveauMP(mapMagasin.get(comboMagasin.getSelectedItem()));
				
				remplirMapStock();
				afficher_tableMP(listStockMP);
				
				
			}
		  }
		});
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
	
				  		     
				  		 
	}
	
void afficher_tableMP(List<StockMP> listStockMP)
	{
	intialiserTabeleau();
		  int i=0;
			while(i<listStockMP.size())
			{	
				
				StockMP stockMP=listStockMP.get(i);
				
				
				Object []ligne={stockMP.getId(),stockMP.getMatierePremier().getCode(),stockMP.getMatierePremier().getNom(),"",""};

				modeleMP.addRow(ligne);
				i++;
			}
	}

void intialiserTabeleau(){
	
	modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code Stock", "Code Mati�re Premi�re","Mati�re Premi�re", "Quantit�", "Prix Unitaire"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,true,true
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		   table.setModel(modeleMP); 
		   table.getColumnModel().getColumn(0).setPreferredWidth(20);
		   table.getColumnModel().getColumn(1).setPreferredWidth(160);
		   table.getColumnModel().getColumn(2).setPreferredWidth(260);
		   table.getColumnModel().getColumn(3).setPreferredWidth(60);
		   table.getColumnModel().getColumn(4).setPreferredWidth(60);
}


boolean remplirMapStock(){
	boolean trouve=false;
	for(int j=0;j<table.getRowCount();j++){
		
		if(!table.getValueAt(j, 3).toString().equals("") && !table.getValueAt(j, 4).toString().equals("")){
			
			
			mapQuantite.put(Integer.parseInt(table.getValueAt(j, 0).toString()), table.getValueAt(j, 3).toString());
			mapPrixUnitaire.put(Integer.parseInt(table.getValueAt(j, 0).toString()), table.getValueAt(j, 4).toString());
			trouve=true;
		}else {
			mapQuantite.put(Integer.parseInt(table.getValueAt(j, 0).toString()), "0");
			mapPrixUnitaire.put(Integer.parseInt(table.getValueAt(j, 0).toString()), "0");
		}
		
	}
	return trouve;
}


void validerStock(List<StockMP> listStockMP){
	MouvementStockGlobal mouvementStockGlobal=null;
	
	BigDecimal quantite=BigDecimal.ZERO;
	BigDecimal prixUnitaire=BigDecimal.ZERO;
	BigDecimal prixQuantiteTransfer=BigDecimal.ZERO;
	
	BigDecimal prixStock=BigDecimal.ZERO;
	
	BigDecimal quatiteTotal=BigDecimal.ZERO;
	BigDecimal prixTotal=BigDecimal.ZERO;
	String dateTransfere=((JTextField)dateTransfereChooser.getDateEditor().getUiComponent()).getText();
	TransferStockMP transferStock = new TransferStockMP();
	Magasin magasin=depotDAO.magasinByCode(CODE_MAGASIN_FOURNISSEUR);
	List<DetailTransferStockMP> listDetailTransferStockMP= new ArrayList<DetailTransferStockMP>();
	/*
	Journee journee=journeeDAO.findByDateEtatOuverte(dateTransfereChooser.getDate(), Constantes.ETAT_STATUT_OVERTE,mapDepot.get(comboDepot.getSelectedItem()).getId());
	if(journee!=null)
	{
		 mouvementStockGlobal=mouvementStockGlobalDAO.findMouvementStockGlobalByDetailMouvementStock(dateTransfereChooser.getDate(), mapDepot.get(comboDepot.getSelectedItem()).getId(), mapMagasin.get(comboMagasin.getSelectedItem()));
		if(mouvementStockGlobal!=null)
		{
			listDetailMouvementStock=mouvementStockGlobal.getDetailMouvementStock();
		}
	
	
	}
	
	*/
	
	
	
	for(int i=0;i<listStockMP.size();i++){	
		
		
		StockMP stockMP=listStockMP.get(i);
		DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
		
		prixStock=stockMP.getStock().multiply(stockMP.getPrixUnitaire()) ;
		if(mapQuantite.get(stockMP.getId())!=null)
		{
			quantite=new BigDecimal(mapQuantite.get(stockMP.getId()));
		}
		
		if(mapPrixUnitaire.get(stockMP.getId())!=null)
		{
			prixUnitaire=new BigDecimal(mapPrixUnitaire.get(stockMP.getId()));
		}
		
		
		prixQuantiteTransfer=quantite.multiply(prixUnitaire)   ;
		
		quatiteTotal=quantite.add(stockMP.getStock())   ;
		
		
		if(quantite.compareTo(BigDecimal.ZERO)>0){
			mapMatierePremiere.put(stockMP.getMatierePremier().getId(), stockMP.getMatierePremier());
			mapQuantiteDetailMouvement.put(stockMP.getMatierePremier().getId(), quantite);
			prixTotal=(prixStock.add(prixQuantiteTransfer)).divide(quatiteTotal, 6, BigDecimal.ROUND_HALF_UP);
			stockMP.setStock(quatiteTotal);
			stockMP.setPrixUnitaire(prixTotal);
		detailTransferStockMP.setMagasinDestination(stockMP.getMagasin());
		detailTransferStockMP.setMagasinSouce(magasin);
		detailTransferStockMP.setMatierePremier(stockMP.getMatierePremier());
		detailTransferStockMP.setQuantite(quantite);
		detailTransferStockMP.setPrixUnitaire(prixUnitaire);
		detailTransferStockMP.setTransferStockMP(transferStock);
		listDetailTransferStockMP.add(detailTransferStockMP);
		stockMPDAO.edit(stockMP);
		}
		
	}
	
	/*
	
	
	for(int j=0;j<listDetailMouvementStock.size();j++)
	{
		MatierePremier matierepremiere=listDetailMouvementStock.get(j).getMatierePremier();
		DetailMouvementStock detailMouvementStock=listDetailMouvementStock.get(j);
		MatierePremier matierepremiereTmp=mapMatierePremiere.get(matierepremiere.getId());
		if(matierepremiereTmp!=null)
		{
			detailMouvementStock.setReception(detailMouvementStock.getReception().add(mapQuantiteDetailMouvement.get(matierepremiere.getId())));	
			detailMouvementStock.setStockFinaldb((detailMouvementStock.getInitial().add(detailMouvementStock.getReception()).add(detailMouvementStock.getTransfertEntrees()).subtract((detailMouvementStock.getSorties().add(detailMouvementStock.getTransfertSorties()).add(detailMouvementStock.getCharge()).add(detailMouvementStock.getChargeSupplementaire())))).add(detailMouvementStock.getRetour()));
			
			listDetailMouvementStock.set(j, detailMouvementStock);
			
			
		}
		
		
	}
	if(mouvementStockGlobal!=null)
	{
		mouvementStockGlobal.setDetailMouvementStock(listDetailMouvementStock);
		
		mouvementStockGlobalDAO.edit(mouvementStockGlobal);
		
	}
	
	*/
	
	
	
	
	
	
		
	transferStock.setCodeTransfer(txtcode.getText());
	transferStock.setCreerPar(AuthentificationView.utilisateur);
	transferStock.setDate(new Date());
	transferStock.setDateTransfer(new Date());
	transferStock.setStatut(ETAT_TRANSFER_STOCK_AJOUT);
	transferStock.setDepot(mapDepot.get(comboDepot.getSelectedItem()));
	transferStock.setDateTransfer(dateTransfereChooser.getDate());
	transferStock.setListDetailTransferMP(listDetailTransferStockMP);
	transferStockMPDAO.add(transferStock);
}

void initialiser(){
	intialiserTabeleau();
	comboDepot.setSelectedItem("");
	comboMagasin.setSelectedItem("");
	listStockMP=new ArrayList<StockMP>();
	txtcode.setText("");
	intialiserTabeleau();
}

void initialierMapStock(){
	
	for(int j=0;j<table.getRowCount();j++){
		table.setValueAt("", j, 3);
		table.setValueAt("", j,4);
		
	}
}
}
