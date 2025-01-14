package presentation.stockPF;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;
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
import javax.swing.border.MatteBorder;
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
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
import dao.daoManager.DepotDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.TransferStockPFDAO;
import dao.entity.Articles;
import dao.entity.Depot;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Magasin;
import dao.entity.StockPF;
import dao.entity.TransferStockPF;
import dao.entity.Utilisateur;


public class SortirStockPF extends JLayeredPane implements Constantes {
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleMP;

	private JXTable table;

	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private JButton btnIntialiserOF;
	
	
	
	
	private Map< String, String> mapQuantiteMP = new HashMap<>();
	private Map< Integer, Articles> mapArticle= new HashMap<>();
	private Map< String, Articles> mapArticleTmp = new HashMap<>();
	
	private Map< String, Magasin> mapMagasinSource = new HashMap<>();
	private Map< String, Magasin> mapMagasinDestination = new HashMap<>();
	
	private Map< String, Depot> mapDepotSource = new HashMap<>();
	private Map< String, Integer> mapDepotDestionation = new HashMap<>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	
	TransferStockPF transferStock = new TransferStockPF();
	
	private JComboBox<String> comboMagasinDestination=new JComboBox();
	private JComboBox<String> comboDepotSource=new JComboBox();;
	private  JComboBox<String> comboMagasinSource=new JComboBox();;
	private JComboBox<String> comboDepotDestination=new JComboBox();;
	
	private JLabel lblMagasinSource;
	private JLabel lblDpotDestination;
	private JLabel lblMagasinDstination;
	
	private DepotDAO depotDAO;
	private StockPFDAO stockPFDAO;
	private TransferStockPFDAO transferStockPFDAO;
	private Utilisateur utilisateur;
	private Depot depot = new Depot();
	private JTextField txtRefTransfere;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public SortirStockPF() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 565);
        try{
        	
        	
        	depotDAO=new DepotDAOImpl();
        	stockPFDAO=new StockPFDAOImpl();
        	transferStockPFDAO=new TransferStockPFDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        
        if(!utilisateur.getCodeDepot().equals(CODE_DEPOT_SIEGE)	) {
   		 	depot = depotDAO.findByCode(utilisateur.getCodeDepot());
	     		comboDepotSource.addItem(depot.getLibelle());
   }else {
   	
   	listDepot = depotDAO.findAll();	
	      int i=0;
	      	while(i<listDepot.size())
	      		{	
	      			Depot depot=listDepot.get(i);
	      			mapDepotSource.put(depot.getLibelle(), depot);
	      			comboDepotSource.addItem(depot.getLibelle());
	      			i++;
	      		}
   	
   }
    		
	    
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgValider=new ImageIcon(this.getClass().getResource("/img/valider.png"));
          
          } catch (Exception exp){exp.printStackTrace();}
				  		     btnIntialiserOF = new JButton("Initialiser");
				  		     btnIntialiserOF.setBounds(415, 509, 112, 23);
				  		     add(btnIntialiserOF);
				  		     btnIntialiserOF.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     	intialiser();
				  		     		
				  		     	}
				  		     });
				  		     btnIntialiserOF.setIcon(imgInit);
				  		     btnIntialiserOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     table = new JXTable();
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
				  		     	scrollPane.setBounds(9, 155, 795, 343);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		      
				  		      List<Magasin> 	listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_PF);
					  		      if(listMagasin!=null){
					  		    	  
					  		    	  int j=0;
						  		      	while(j<listMagasin.size())
						  		      		{	
						  		      			Magasin magasin=listMagasin.get(j);
						  		      			comboMagasinSource.addItem(magasin.getLibelle());
						  		      			mapMagasinSource.put(magasin.getLibelle(), magasin);
						  		      			j++;
						  		      		}
					  		      }
					  		      
					  		      
				  		     
				  		     //	listDepot = depotDAO.findDepotByCodeSaufEnParametre(utilisateur.getCodeDepot());	
					  		    listDepot = depotDAO.findAll();
					  		    comboDepotDestination.addItem("");
					  			Magasin magasin=depotDAO.MagasinByTypeMagasinStkAttente(MAGASIN_CODE_TYPE_MP_ATT);
					  		      int i=0;
					  		      	while(i<listDepot.size())
					  		      		{	
					  		      			Depot depot=listDepot.get(i);
					  		      			if(magasin!=null)
					  		      			{
					  		      				if(depot.getId()!=magasin.getDepot().getId())
					  		      				{
					  		      				mapDepotDestionation.put(depot.getLibelle(), i);
						  		      			comboDepotDestination.addItem(depot.getLibelle());
					  		      					
					  		      				}
					  		      				
					  		      			}else
					  		      			{
					  		      			mapDepotDestionation.put(depot.getLibelle(), i);
					  		      			comboDepotDestination.addItem(depot.getLibelle());
					  		      				
					  		      			}
					  		      			
					  		      			i++;
					  		      		}
					  		      	
					  		      
					  	    comboDepotDestination.addItemListener(new ItemListener() {
				  		     	 	public void itemStateChanged(ItemEvent e) {
				  		     	 	
				  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
				  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
					  		     	  	 // comboGroupe = new JComboBox();
				  		     	 	comboMagasinDestination.removeAllItems();
				  		     	 	//comboGroupe.addItem("");
				  		     	 Depot depot =new Depot();
				  		     	 	if(!comboDepotDestination.getSelectedItem().equals(""))
					  		     	  	   	 depot =listDepot.get(mapDepotDestionation.get(comboDepotDestination.getSelectedItem()));
							  		       
					  		     	  	listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_PF);
							  		      if(listMagasin!=null){
							  		    	  
							  		    	  int j=0;
								  		      	while(j<listMagasin.size())
								  		      		{	
								  		      			Magasin magasin=listMagasin.get(j);
								  		      			comboMagasinDestination.addItem(magasin.getLibelle());
								  		      			mapMagasinDestination.put(magasin.getLibelle(), magasin);
								  		      			j++;
								  		      		}
							  		      }
				  		     	 	 }
				  		     	 	}
				  		     	 });
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(9, 126, 795, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 13, 795, 111);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("D\u00E9pot Soure");
				  		     	lblMachine.setBounds(10, 34, 114, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	
				  		     	 
				  		     	 comboDepotSource.setBounds(103, 34, 144, 24);
				  		     	 layeredPane.add(comboDepotSource);
				  		     	
				  		     	 
				  		     	 JLabel lblGroupe = new JLabel("Magasin Source");
				  		     	 lblGroupe.setBounds(10, 73, 102, 24);
				  		     	 layeredPane.add(lblGroupe);
				  		     	 lblGroupe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	
				  		     	 comboMagasinSource.setBounds(103, 74, 144, 24);
				  		     	 layeredPane.add(comboMagasinSource);
				  		     	 
				  		 
				  		  comboMagasinDestination.setBounds(409, 74, 152, 24);
				  		  layeredPane.add(comboMagasinDestination);
				  		  
				  		  JLabel lblEquipe = new JLabel("Magasin Destination");
				  		  lblEquipe.setBounds(308, 73, 108, 26);
				  		  layeredPane.add(lblEquipe);
				  		  
				  		  lblMagasinSource = new JLabel("Magasin Source ");
				  		  lblMagasinSource.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
				  		  lblMagasinSource.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
				  		  lblMagasinSource.setBounds(10, 11, 237, 14);
				  		  layeredPane.add(lblMagasinSource);
				  		  
				  		  lblDpotDestination = new JLabel("D�pot Destination");
				  		  lblDpotDestination.setBounds(308, 33, 108, 26);
				  		  layeredPane.add(lblDpotDestination);
				  		  
				  		  
				  		  comboDepotDestination.setBounds(409, 34, 152, 24);
				  		  layeredPane.add(comboDepotDestination);
				  		  
				  		  lblMagasinDstination = new JLabel("Magasin D\u00E9stination");
				  		  lblMagasinDstination.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
				  		  lblMagasinDstination.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
				  		  lblMagasinDstination.setBounds(308, 11, 254, 14);
				  		  layeredPane.add(lblMagasinDstination);
				  		  
				  		  JLabel lblCodeTrnsafer = new JLabel("Code Transafer ");
				  		  lblCodeTrnsafer.setBounds(578, 34, 94, 24);
				  		  layeredPane.add(lblCodeTrnsafer);
				  		  
				  		  txtRefTransfere = new JTextField();
				  		util.Utils.copycoller(txtRefTransfere);
				  		  txtRefTransfere.setBounds(654, 34, 131, 24);
				  		  layeredPane.add(txtRefTransfere);
				  		  txtRefTransfere.setColumns(10);
		
		JButton btnValiderTransfer = new JButton("Valider Transfer MP");
		btnValiderTransfer.setIcon(imgValider);
		btnValiderTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(!remplirMapChargeSupp())	{
				JOptionPane.showMessageDialog(null, "Il faut remplir la quantit�", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else {
				if(comboMagasinDestination.getSelectedItem()==null && comboMagasinDestination.getSelectedItem().equals("")){
					JOptionPane.showMessageDialog(null, "Il faut choisir un magasin", "Erreur", JOptionPane.ERROR_MESSAGE);
				}else/* if(txtRefTransfere.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Il faut saisir le code de transfer", "Erreur", JOptionPane.ERROR_MESSAGE);
				}else*/ {
				String codeTransfert=Utils.genererCodeTransfer(depot.getCode(),ETAT_TRANSFER_STOCK_SORTIE);
				txtRefTransfere.setText(codeTransfert);
				transferStock.setCodeTransfer(txtRefTransfere.getText());
				transferStock.setCreerPar(AuthentificationView.utilisateur);
				transferStock.setDate(new Date());
				transferStock.setDateTransfer(new Date());
				transferStock.setStatut(Constantes.STATUT_TRANSFER_PRODUIT_FINI_SORTIE);
				transferStock.setListDetailTransferProduitFini(remplirDetailTransfer());
				transferStockPFDAO.add(transferStock);
				JOptionPane.showMessageDialog(null, "Stock transf�r� avec succ�s", "Succ�s", JOptionPane.INFORMATION_MESSAGE);
				intialiser();
				}
			}
		  }
		});
		btnValiderTransfer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnValiderTransfer.setBounds(247, 509, 158, 23);
		add(btnValiderTransfer);
		
		JButton btnAfficherStock = new JButton("Afficher Stock");
		btnAfficherStock.setBounds(814, 13, 102, 24);
		add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  				if(comboDepotSource.getSelectedItem().equals(""))	{
				  					JOptionPane.showMessageDialog(null, "Il faut choisir un magasin", "Erreur", JOptionPane.ERROR_MESSAGE);
				  				} else {
				  					List<StockPF> listStockPF=new ArrayList<StockPF>();
				  					listStockPF=stockPFDAO.findSockNonVideByMagasin(mapMagasinSource.get(comboMagasinSource.getSelectedItem()).getId());
				  					afficher_tableMP(listStockPF);
				  				}
				  			  }
		});
		btnAfficherStock.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JButton btnImprimer = new JButton("Imprimer");
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(transferStock.getId()>0){
		  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		  		  	String date=dateFormat.format(transferStock.getDate());
					 List<DetailTransferProduitFini> listDetailTransferStockPF=transferStock.getListDetailTransferProduitFini();
					 DetailTransferProduitFini detailTransferStockPF=listDetailTransferStockPF.get(0);
					Map parameters = new HashMap();
					parameters.put("numTransfer", transferStock.getCodeTransfer());
					parameters.put("machineSource", detailTransferStockPF.getMagasinSouce().getLibelle());
					parameters.put("depSource", detailTransferStockPF.getMagasinSouce().getDepot().getLibelle());
					parameters.put("magasinDest", detailTransferStockPF.getMagasinDestination().getLibelle());
					parameters.put("depDest", detailTransferStockPF.getMagasinDestination().getDepot().getLibelle());
					parameters.put("dateTransfer", date);
					JasperUtils.imprimerBonSortiePF(listDetailTransferStockPF,parameters,transferStock.getCodeTransfer());
					
				//	JOptionPane.showMessageDialog(null, "PDF export� avec succ�s", "Succ�s", JOptionPane.INFORMATION_MESSAGE);
		  		  	}else {
		  		  	JOptionPane.showMessageDialog(null, "Il faut valider le transfer avant d'imprimer ", "Erreur Impression", JOptionPane.ERROR_MESSAGE);
		  		  	}
			}
		});
		btnImprimer.setBounds(543, 509, 89, 23);
		add(btnImprimer);
		
				  		     
				  		 
	}
	
	
	void intialiser()
	{
		comboDepotDestination.setSelectedItem("");
		comboDepotSource.setSelectedItem("");
		comboMagasinDestination.setSelectedItem("");
		comboMagasinSource.setSelectedItem("");
		
		for(int j=0;j<table.getRowCount();j++){
			table.setValueAt("", j, 2);
						
		}
				
		
	}
	
	void afficher_tableMP(List<StockPF> listStockPF)
	{

		
		intialiserTableau();
	
	        
		  int i=0;
			while(i<listStockPF.size())
			{	
				
				StockPF stockPF=listStockPF.get(i);
				mapArticleTmp.put(stockPF.getArticles().getCodeArticle(), stockPF.getArticles());
				Object []ligne={stockPF.getArticles().getCodeArticle(),stockPF.getArticles().getLiblle(),stockPF.getStock(),""};

				modeleMP.addRow(ligne);
				
				i++;
			}
			table.setModel(modeleMP); 
	}
	


boolean remplirMapChargeSupp(){
	boolean trouve=false;
	int i=0;
			
	for(int j=0;j<table.getRowCount();j++){
		
		if(!table.getValueAt(j, 3).toString().equals("") ){
			mapQuantiteMP.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 3).toString());
			mapArticle.put(i, mapArticleTmp.get(table.getValueAt(j, 0).toString()));
			i++;
			trouve=true;
		}
		
	}
	return trouve;
}


List<DetailTransferProduitFini> remplirDetailTransfer(){
	BigDecimal quantite=BigDecimal.ZERO;
	BigDecimal prixStockDestination=BigDecimal.ZERO;
	BigDecimal prixStockSource=BigDecimal.ZERO;
	BigDecimal prixMoyen=BigDecimal.ZERO;
	BigDecimal stockSource=BigDecimal.ZERO;
	BigDecimal stockDestination=BigDecimal.ZERO;
	BigDecimal sommeStock=BigDecimal.ZERO;
	
	List<DetailTransferProduitFini> listDetailTransferProduitFini= new ArrayList<DetailTransferProduitFini>();
	
	for(int i=0;i<mapArticle.size();i++){/*
		
		DetailTransferProduitFini detailTransferStockPF=new DetailTransferProduitFini();
		Magasin magasinSource =mapMagasinSource.get(comboMagasinSource.getSelectedItem());
		Magasin magasinDestination=mapMagasinDestination.get(comboMagasinDestination.getSelectedItem());
		Articles article =mapArticle.get(i);
		quantite=new BigDecimal(mapQuantiteMP.get(article.getCodeArticle()));
		StockPF stockPFSource=stockPFDAO.findStockByMagasinPFBySousFamille (article.getId(), magasinSource.getId(),article.getSousFamilleArticle().getId());
		StockPF stockPFDestination=stockPFDAO.findStockByMagasinPFBySousFamille(article.getId(), magasinDestination.getId(),article.getSousFamilleArticle().getId());
		
		if(stockPFSource.getStock().compareTo(quantite)>=0){
		sommeStock=quantite.add(stockPFSource.getStock());
		stockSource=stockPFSource.getStock().subtract(quantite);
		prixStockSource=stockPFSource.getPrixUnitaire();
		
		 if(stockPFDestination==null){
			 stockPFDestination=new StockPF();
			 stockPFDestination.setArticles(article);
			 stockPFDestination.setMagasin(magasinDestination);
			 stockPFDestination.setPrixUnitaire(prixStockSource);
			 stockPFDestination.setStock(quantite);
			 stockPFDAO.add(stockPFDestination);
		 }else  {
			 
			 stockDestination=stockPFDestination.getStock().add(quantite);
			 prixStockDestination=stockPFDestination.getPrixUnitaire();
			 prixMoyen=prixStockDestination.multiply(stockPFDestination.getStock()).add(prixStockSource).multiply(quantite);
			 prixMoyen=prixMoyen.divide(sommeStock, 6, BigDecimal.ROUND_HALF_UP);
			 stockPFDestination.setPrixUnitaire(prixMoyen);
			 stockPFDestination.setStock(stockDestination);
			stockPFDAO.edit(stockPFDestination);
			 
		 }
		 
		
		
		
		
		stockPFSource.setStock(stockSource);
		stockPFDAO.edit(stockPFSource);
		
		detailTransferStockPF.setMagasinDestination(magasinDestination);
		detailTransferStockPF.setMagasinSouce(magasinSource);
		detailTransferStockPF.setArticle(article);
		detailTransferStockPF.setQuantite(quantite);
		detailTransferStockPF.setPrixUnitaire(prixStockSource);
		detailTransferStockPF.setTransferStockPF(transferStock);
		listDetailTransferProduitFini.add(detailTransferStockPF);
	}else {
		JOptionPane.showMessageDialog(null, "Stock de : �"+article.getLiblle()+"� ne peut Transf�re! Quantit� en stock et inf�rireure � la quantit� � transf�rer", "Erreur", JOptionPane.ERROR_MESSAGE);
		
	}
		
		
	*/}
	
	return listDetailTransferProduitFini;
	
}


void intialiserTableau(){
	
	
	modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code Article","Article","Quantit� En Stock","Quantit� a Tranf�rer"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,true
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		 table.setModel(modeleMP); 
		 table.getColumnModel().getColumn(0).setPreferredWidth(10);
     table.getColumnModel().getColumn(1).setPreferredWidth(260);
     table.getColumnModel().getColumn(2).setPreferredWidth(160);
  //  table.getColumnModel().getColumn(3).setPreferredWidth(160);
    //q table.getColumnModel().getColumn(4).setPreferredWidth(60);
}
}
