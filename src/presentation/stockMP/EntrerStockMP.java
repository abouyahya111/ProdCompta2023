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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.JourneeDAOImpl;
import dao.daoImplManager.MouvementStockGlobalDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.DepotDAO;
import dao.daoManager.JourneeDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MouvementStockGlobalDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.CoutMP;
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

import javax.swing.SwingConstants;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import java.util.Locale;


public class EntrerStockMP extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleMP;

	private JXTable table;

	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgImprimer;
	private JButton btnIntialiserOF;
	private   JDateChooser dateTransfereChooser = new JDateChooser();
	
	
	
	private Map< String, String> mapQuantiteMP = new HashMap<>();
	private Map< Integer, MatierePremier> mapMatierePremier= new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremierTmp = new HashMap<>();
	
	private Map< String, Magasin> mapMagasinSource = new HashMap<>();
	private Map< String, Magasin> mapMagasinDestination = new HashMap<>();
	
	private Map< String, Integer> mapDepotSource = new HashMap<>();
	private Map< String, Depot> mapDepotSourceTmp = new HashMap<>();
	private Map< String, Depot> mapDepotDestionation = new HashMap<>();
	
	private List<Depot> listDepot =new ArrayList<Depot>();
	
	TransferStockMP transferStock = new TransferStockMP();
	
	
	
	private Map< Integer, MatierePremier> mapMatierePremiereDetailMouvement= new HashMap<>();
	private Map< Integer, BigDecimal> mapQuantiteDetailMouvement= new HashMap<>();
	private List<Journee> listJournee =new ArrayList<Journee>();
	private List<DetailMouvementStock> listDetailMouvementStockSource =new ArrayList<DetailMouvementStock>();
	private List<DetailMouvementStock> listDetailMouvementStockDestination =new ArrayList<DetailMouvementStock>();
	private JourneeDAO journeeDAO;
	private MouvementStockGlobalDAO mouvementStockGlobalDAO;
	
	
	
	private JComboBox<String> comboMagasinDestination=new JComboBox();
	private JComboBox<String> comboDepotSource=new JComboBox();
	private  JComboBox<String> comboMagasinSource=new JComboBox();
	private JComboBox<String> comboDepotDestination=new JComboBox();
	
	private JLabel lblMagasinSource;
	private JLabel lblDpotDestination;
	private JLabel lblMagasinDstination;
	
	private DepotDAO depotDAO;
	private StockMPDAO stockMPDAO;
	private TransferStockMPDAO transferStockMPDAO;
	private MatierePremiereDAO matierePremiereDAO;
	private Utilisateur utilisateur;
	private Depot depot = new Depot();
	private JTextField txtReftransfer;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public EntrerStockMP() {
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
        	imgImprimer = new ImageIcon(this.getClass().getResource("/img/imprimer.png"));

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        
       comboMagasinDestination=new JComboBox();
    	comboDepotSource=new JComboBox();
    	comboMagasinSource=new JComboBox();
    	comboDepotDestination=new JComboBox();
    	 comboDepotDestination.addItem("");
    		/* depot = depotDAO.findByCode(utilisateur.getCodeDepot());
    		
    		 comboDepotDestination.addItem(depot.getLibelle());*/
    		 
    		  if(!utilisateur.getCodeDepot().equals(CODE_DEPOT_SIEGE)	) {
    	    		 depot = depotDAO.findByCode(utilisateur.getCodeDepot());
    	    		 comboDepotDestination.addItem(depot.getLibelle());
    	    		 mapDepotDestionation.put(depot.getLibelle(), depot);
    	    	
    	    }else {
    	    	
    	    	listDepot = depotDAO.findAll();	
    		      int i=0;
    		      	while(i<listDepot.size())
    		      		{	
    		      			Depot depot=listDepot.get(i);
    		      			Magasin magasin=depotDAO.MagasinByTypeMagasinStkAttente(MAGASIN_CODE_TYPE_MP_ATT);
    		      			if(magasin!=null)
    		      			{
    		      				
    		      				if(depot.getId()!=magasin.getDepot().getId())
    		      				{
    		      					mapDepotDestionation.put(depot.getLibelle(), depot);
    	    		      			comboDepotDestination.addItem(depot.getLibelle());
    		      					
    		      				}
    		      			}else
    		      			{
    		      				
    		      				mapDepotDestionation.put(depot.getLibelle(), depot);
        		      			comboDepotDestination.addItem(depot.getLibelle());
    		      			}
    		      			
    		      			i++;
    		      		}
    	    	
    	    }
    		 comboDepotSource.addItem("");
    		
	    
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
				  		     	scrollPane.setBounds(9, 155, 892, 343);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		      
				  		      List<Magasin> 	listMagasin = depotDAO.listeMagasinByTypeMagasinDepotMachine(depot.getId(), MAGASIN_CODE_TYPE_MP,CODE_MACHINE_STOCKAGE);
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
					  		      
					  		    comboDepotDestination.addItemListener(new ItemListener() {
				  		     	 	public void itemStateChanged(ItemEvent e) {
				  		     	 	
				  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
				  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
					  		     	  	 // comboGroupe = new JComboBox();
				  		     	 	comboMagasinDestination.removeAllItems();
				  		     	 	//comboGroupe.addItem("");
				  		     	 Depot depot =new Depot();
				  		     	 	if(!comboDepotDestination.getSelectedItem().equals(""))
					  		     	  	   	 depot =mapDepotDestionation.get(comboDepotDestination.getSelectedItem());
							  		     
					  		     	 	//listMagasin = depotDAO.listeMagasinByTypeMagasinDepotMachine(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP,CODE_MACHINE_STOCKAGE);
				  		     	 
				  		     	 listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);
				  		   
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
					  		      
					  		      
				  		     
				  		     	listDepot = depotDAO.findDepotByCodeSaufEnParametre(utilisateur.getCodeDepot());	
				  		  	Magasin magasin=depotDAO.MagasinByTypeMagasinStkAttente(MAGASIN_CODE_TYPE_MP_ATT);
					  		      int i=0;
					  		      	while(i<listDepot.size())
					  		      	{
					  		      	Depot depot=listDepot.get(i);
					  		      			if(magasin!=null)
					  		      		{
					  		      				
					  		      			if(depot.getId()!=magasin.getDepot().getId())	
					  		      			{
					  		      			mapDepotSource.put(depot.getLibelle(), i);
						  		      		   mapDepotSourceTmp.put(depot.getLibelle(), depot);
						  		      			comboDepotSource.addItem(depot.getLibelle());
					  		      			}
					  		      				
					  		      			
					  		      		}else
					  		      		{
					  		      		mapDepotSource.put(depot.getLibelle(), i);
					  		      		   mapDepotSourceTmp.put(depot.getLibelle(), depot);
					  		      			comboDepotSource.addItem(depot.getLibelle());
					  		      		}
					  		      		
					  		      			
					  		      			i++;
					  		      		}
					  		      	
					  		      	
					  		      	
					  		    
					  		      
					  	    comboDepotSource.addItemListener(new ItemListener() {
				  		     	 	public void itemStateChanged(ItemEvent e) {
				  		     	 	
				  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
				  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
					  		     	  	 // comboGroupe = new JComboBox();
				  		     	 	comboMagasinSource.removeAllItems();
				  		     	 	//comboGroupe.addItem("");
				  		     	 Depot depot =new Depot();
				  		     	 	if(!comboDepotSource.getSelectedItem().equals(""))
					  		     	  	   	 depot =listDepot.get(mapDepotSource.get(comboDepotSource.getSelectedItem()));
							  		       
					  		     	  //	listMagasin = depotDAO.listeMagasinByTypeMagasinDepotMachine(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP,CODE_MACHINE_STOCKAGE);
				  		                listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);
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
				  		     	 	 }
				  		     	 	}
				  		     	 });
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(9, 126, 892, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 13, 892, 111);
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
				  		     	
				  		     	 comboMagasinSource.setBounds(103, 74, 161, 24);
				  		     	 layeredPane.add(comboMagasinSource);
				  		     	 
				  		 
				  		  comboMagasinDestination.setBounds(390, 74, 184, 24);
				  		  layeredPane.add(comboMagasinDestination);
				  		  
				  		  JLabel lblEquipe = new JLabel("Magasin Destination");
				  		  lblEquipe.setBounds(274, 73, 108, 26);
				  		  layeredPane.add(lblEquipe);
				  		  
				  		  lblMagasinSource = new JLabel("D\u00E9pot Source ");
				  		  lblMagasinSource.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
				  		  lblMagasinSource.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
				  		  lblMagasinSource.setBounds(10, 11, 237, 14);
				  		  layeredPane.add(lblMagasinSource);
				  		  
				  		  lblDpotDestination = new JLabel("D�pot Destination");
				  		  lblDpotDestination.setBounds(285, 33, 108, 26);
				  		  layeredPane.add(lblDpotDestination);
				  		  
				  		  
				  		  comboDepotDestination.setBounds(390, 34, 152, 24);
				  		  layeredPane.add(comboDepotDestination);
				  		  
				  		  lblMagasinDstination = new JLabel("D\u00E9pot D\u00E9stination");
				  		  lblMagasinDstination.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
				  		  lblMagasinDstination.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
				  		  lblMagasinDstination.setBounds(285, 11, 254, 14);
				  		  layeredPane.add(lblMagasinDstination);
				  		  
				  		  JLabel lblCodeTransfer = new JLabel("Code Transfer");
				  		  lblCodeTransfer.setBounds(610, 34, 81, 24);
				  		  layeredPane.add(lblCodeTransfer);
				  		  
				  		  txtReftransfer = new JTextField();
				  		util.Utils.copycoller(txtReftransfer);
				  		  txtReftransfer.setBounds(701, 34, 144, 24);
				  		  layeredPane.add(txtReftransfer);
				  		  txtReftransfer.setColumns(10);
				  		  
				  		   dateTransfereChooser = new JDateChooser();
				  		  dateTransfereChooser.setLocale(Locale.FRANCE);
				  		  dateTransfereChooser.setDateFormatString("dd/MM/yyyy");
				  		  dateTransfereChooser.setBounds(701, 71, 144, 26);
				  		  layeredPane.add(dateTransfereChooser);
				  		  
				  		  JLabel lblDateTransfre = new JLabel("Date Transf\u00E8re :");
				  		  lblDateTransfre.setBounds(610, 69, 87, 26);
				  		  layeredPane.add(lblDateTransfre);
		
		JButton btnValiderTransfer = new JButton("Valider Transfer MP");
		btnValiderTransfer.setIcon(imgValider);
		btnValiderTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//MouvementStockGlobal mouvementStockGlobalSource=null;
				MouvementStockGlobal mouvementStockGlobalDestination=null;
				
				
				String dateTransfere=((JTextField)dateTransfereChooser.getDateEditor().getUiComponent()).getText();
			if(!remplirMapChargeSupp())	{
				JOptionPane.showMessageDialog(null, "Il faut remplir la quantit�", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else {
				if(comboMagasinDestination.getSelectedItem().equals(comboMagasinSource.getSelectedItem())){
					JOptionPane.showMessageDialog(null, "Le Magasin source doit �tre diff�rent d�stination", "Erreur", JOptionPane.ERROR_MESSAGE);
				}else if(txtReftransfer.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Il faut saisir le code de transfer", "Erreur", JOptionPane.ERROR_MESSAGE);
					
					
				}else if(dateTransfere.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Il faut saisir la date de transfer", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				else{
				transferStock.setCodeTransfer(txtReftransfer.getText());
				transferStock.setCreerPar(AuthentificationView.utilisateur);
				transferStock.setDate(new Date());
				transferStock.setDateTransfer(dateTransfereChooser.getDate());
				transferStock.setStatut(Constantes.ETAT_TRANSFER_STOCK_ENTRE);
				transferStock.setListDetailTransferMP( remplirDetailTransfer());
				transferStock.setDepot(mapDepotDestionation.get(comboDepotDestination.getSelectedItem()));
				transferStockMPDAO.add(transferStock);
				

				 //////////////////////////////////////////////////////////Chercher le Journ�e et liste des mouvement de stock de cet journ�e /////////////////////////////////////////
				
				// Depot depotSource=mapDepotSourceTmp.get(comboDepotSource.getSelectedItem());
				/*
				  Depot depotDestination=mapDepotDestionation.get(comboDepotDestination.getSelectedItem());
				
              // Journee journeeSource=journeeDAO.findByDateEtatOuverte(dateTransfereChooser.getDate(), Constantes.ETAT_STATUT_OVERTE,depotSource.getId());
               Journee journeeDestination=journeeDAO.findByDateEtatOuverte(dateTransfereChooser.getDate(), Constantes.ETAT_STATUT_OVERTE,depotDestination.getId());
               
               
               if( journeeDestination!=null)
              {
              	 ///////// Mouvement Stock Magasin Source ////////////////////
              	 
              	/*
              	Magasin magasinSource=mapMagasinSource.get(comboMagasinSource.getSelectedItem());
              	 
              	if(depotSource!=null && magasinSource !=null)
              	{
              		
              		 mouvementStockGlobalSource=mouvementStockGlobalDAO.findMouvementStockGlobalByDetailMouvementStock(dateTransfereChooser.getDate(),depotSource.getId(), magasinSource.getId());
                       if(mouvementStockGlobalSource!=null)
                             {
                        listDetailMouvementStockSource=mouvementStockGlobalSource.getDetailMouvementStock();
                    }	
              		
              		
              	}   
              	*/        	 
                      ///////// Mouvement Stock Magasin Destination ////////////////////
                   /*   
                    
                 Magasin magasinDestination =mapMagasinDestination.get(comboMagasinDestination.getSelectedItem());
                      
                 	 
                 	 if(depotDestination!=null && magasinDestination !=null)
                 	 {
                 		 
                 		mouvementStockGlobalDestination=mouvementStockGlobalDAO.findMouvementStockGlobalByDetailMouvementStock(dateTransfereChooser.getDate(), depotDestination.getId(), magasinDestination.getId());
                      if(mouvementStockGlobalDestination!=null)
                            {
                       listDetailMouvementStockDestination=mouvementStockGlobalDestination.getDetailMouvementStock();
                   }
                       
                 		 
                 	 }

                      }



                     ////////////////////////////////////////////////////// chercher MP dans la liste detailMouvementStock Magasin Source et Modifier Quantite Transfere Sortie et leStock Finale  ////////////////////////////////
/*
                  for(int j=0;j<listDetailMouvementStockSource.size();j++)
                  {
                  MatierePremier matierepremiere=listDetailMouvementStockSource.get(j).getMatierePremier();
                  DetailMouvementStock detailMouvementStockSource=listDetailMouvementStockSource.get(j);
                   MatierePremier matierepremiereTmp=mapMatierePremiereDetailMouvement.get(matierepremiere.getId());
               if(matierepremiereTmp!=null)
                  {

              	 detailMouvementStockSource.setTransfertSorties (detailMouvementStockSource.getTransfertSorties().add(mapQuantiteDetailMouvement.get(matierepremiereTmp.getId())));	
              	 detailMouvementStockSource.setStockFinaldb((detailMouvementStockSource.getInitial().add(detailMouvementStockSource.getReception()).add(detailMouvementStockSource.getTransfertEntrees()).subtract((detailMouvementStockSource.getSorties().add(detailMouvementStockSource.getTransfertSorties()).add(detailMouvementStockSource.getCharge()).add(detailMouvementStockSource.getChargeSupplementaire())))).add(detailMouvementStockSource.getRetour()));

                   listDetailMouvementStockSource.set(j, detailMouvementStockSource);


                 }


                 }
  */
                  

                  ////////////////////////////////////////////////////// chercher MP dans la liste detailMouvementStock Magasin Destination et Modifier Transfere  entrer Charger et leStock Finale  ////////////////////////////////
/*
               for(int j=0;j<listDetailMouvementStockDestination.size();j++)
               {
               MatierePremier matierepremiere=listDetailMouvementStockDestination.get(j).getMatierePremier();
               DetailMouvementStock detailMouvementStockDestination=listDetailMouvementStockDestination.get(j);
                MatierePremier matierepremiereTmp=mapMatierePremiereDetailMouvement.get(matierepremiere.getId());
            if(matierepremiereTmp!=null)
               {

          	  detailMouvementStockDestination.setTransfertEntrees(detailMouvementStockDestination.getTransfertEntrees().add(mapQuantiteDetailMouvement.get(matierepremiereTmp.getId())));	
          	  detailMouvementStockDestination.setStockFinaldb((detailMouvementStockDestination.getInitial().add(detailMouvementStockDestination.getReception()).add(detailMouvementStockDestination.getTransfertEntrees()).subtract((detailMouvementStockDestination.getSorties().add(detailMouvementStockDestination.getTransfertSorties()).add(detailMouvementStockDestination.getCharge()).add(detailMouvementStockDestination.getChargeSupplementaire())))).add(detailMouvementStockDestination.getRetour()));

                listDetailMouvementStockDestination.set(j, detailMouvementStockDestination);


              }


              }
                 */
				/*
                  
                if( mouvementStockGlobalDestination!=null)
                {
                	/*
              	  mouvementStockGlobalSource.setDetailMouvementStock(listDetailMouvementStockSource);

                 mouvementStockGlobalDAO.edit(mouvementStockGlobalSource);
*/
              /*
              	  mouvementStockGlobalDestination.setDetailMouvementStock(listDetailMouvementStockDestination);

                 mouvementStockGlobalDAO.edit(mouvementStockGlobalDestination);

                   }
*/

                  ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
               
				
				
				
				
				JOptionPane.showMessageDialog(null, "Stock transf�r� avec succ�s", "Succ�s", JOptionPane.INFORMATION_MESSAGE);
				//intialiser();
				}
			}
		  }
		});
		btnValiderTransfer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnValiderTransfer.setBounds(247, 509, 142, 23);
		add(btnValiderTransfer);
		
		JButton btnAfficherStock = new JButton("Afficher Stock");
		btnAfficherStock.setBounds(964, 34, 102, 24);
		add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  				if(comboDepotSource.getSelectedItem().equals(""))	{
				  					JOptionPane.showMessageDialog(null, "Il faut choisir un magasin", "Erreur", JOptionPane.ERROR_MESSAGE);
				  				} else {
				  					List<StockMP> listStockMP=stockMPDAO.findAllByMagasin(mapMagasinSource.get(comboMagasinSource.getSelectedItem()).getId());
				  					
				  					afficher_tableMP(listStockMP);
				  				}
				  			  }
		});
		btnAfficherStock.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JButton btnBonEntrerMp = new JButton("Bon Entrer MP D\u00E9plac\u00E9e");
		btnBonEntrerMp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

	  		  	if(transferStock.getId()>0){
	  		  		
	  		  		if(!comboDepotDestination.getSelectedItem().equals("")){
	  		  	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	  		  	String date=dateFormat.format(transferStock.getDateTransfer());
				 List<DetailTransferStockMP> listDetailTransferStockMP=transferStock.getListDetailTransferMP();
				 if(listDetailTransferStockMP.size()!=0)
				 {
					 DetailTransferStockMP detailTransferStockMP=listDetailTransferStockMP.get(0);
						Map parameters = new HashMap();
						parameters.put("numTransfer", transferStock.getCodeTransfer());
						parameters.put("machineSource", detailTransferStockMP.getMagasinSouce().getLibelle());
						parameters.put("depSource", detailTransferStockMP.getMagasinSouce().getDepot().getLibelle());
						parameters.put("magasinDest", mapMagasinDestination.get(comboMagasinDestination.getSelectedItem()).getLibelle());
						parameters.put("depDest", comboDepotDestination.getSelectedItem());
						parameters.put("dateTransfer", date);
						JasperUtils.imprimerBonEntrerMP(listDetailTransferStockMP,parameters,transferStock.getCodeTransfer());
				 }else
				 {
					 JOptionPane.showMessageDialog(null, "Il faut saisir les mati�res premi�re", "Erreur", JOptionPane.INFORMATION_MESSAGE);
				 }
				
	  		  		}else 
	  		  		{
	  		  		JOptionPane.showMessageDialog(null, "Il faut choisir un megasin", "Erreur", JOptionPane.INFORMATION_MESSAGE);
	  		  		}
				
			//	JOptionPane.showMessageDialog(null, "PDF export� avec succ�s", "Succ�s", JOptionPane.INFORMATION_MESSAGE);
	  		  	}else {
	  		  	JOptionPane.showMessageDialog(null, "Il faut valider le transfer avant d'imprimer ", "Erreur Impression", JOptionPane.ERROR_MESSAGE);
	  		  	}
				
				
				
			}
		});
		btnBonEntrerMp.setBounds(539, 509, 178, 23);
		btnBonEntrerMp.setIcon(imgImprimer);
		add(btnBonEntrerMp);
		
				  		 
	}
	
	
	void intialiser()
	{
		comboDepotDestination.setSelectedIndex(-1);
		comboDepotSource.setSelectedIndex(-1);
		comboMagasinDestination.setSelectedIndex(-1);
		comboMagasinSource.setSelectedIndex(-1);
		txtReftransfer.setText("");
		dateTransfereChooser.setCalendar(null);
		
		for(int j=0;j<table.getRowCount();j++){
			table.setValueAt("", j, 3);
						
		}
				
		
	}
	
	void afficher_tableMP(List<StockMP> listStockMP)
	{

		
		intialiserTableau();
	
	        
		  int i=0;
			while(i<listStockMP.size())
			{	
				
				StockMP stockMP=listStockMP.get(i);
				mapMatierePremierTmp.put(stockMP.getMatierePremier().getCode(), stockMP.getMatierePremier());
				Object []ligne={stockMP.getMatierePremier().getCode(),stockMP.getMatierePremier().getNom(),stockMP.getStock(),""};

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
			mapMatierePremier.put(i, mapMatierePremierTmp.get(table.getValueAt(j, 0).toString()));
			i++;
			trouve=true;
		}
		
	}
	return trouve;
}


List<DetailTransferStockMP> remplirDetailTransfer(){
	BigDecimal quantite=BigDecimal.ZERO;
	BigDecimal prixStockDestination=BigDecimal.ZERO;
	BigDecimal prixStockSource=BigDecimal.ZERO;
	BigDecimal prixMoyen=BigDecimal.ZERO;
	BigDecimal stockSource=BigDecimal.ZERO;
	BigDecimal stockDestination=BigDecimal.ZERO;
	BigDecimal sommeStock=BigDecimal.ZERO;
	
	List<DetailTransferStockMP> listDetailTransferStockMP= new ArrayList<DetailTransferStockMP>();
	
	for(int i=0;i<mapMatierePremier.size();i++){
		
	
		MatierePremier matierePremier =mapMatierePremier.get(i);
		quantite=new BigDecimal(mapQuantiteMP.get(matierePremier.getCode()));
		
		
		if(quantite.compareTo(BigDecimal.ZERO) >0){
			
			mapMatierePremiereDetailMouvement.put(matierePremier.getId(),matierePremier);
			mapQuantiteDetailMouvement.put(matierePremier.getId(), quantite);
			
			
		DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
		Magasin magasinSource =mapMagasinSource.get(comboMagasinSource.getSelectedItem());
		Magasin magasinDestination=mapMagasinDestination.get(comboMagasinDestination.getSelectedItem());
		StockMP stockMPSource=stockMPDAO.findStockByMagasinMP(matierePremier.getId(), magasinSource.getId());
		StockMP stockMPDestination=stockMPDAO.findStockByMagasinMP(matierePremier.getId(), magasinDestination.getId());
		
		if(stockMPDestination!=null)
		{
			
			sommeStock=quantite.add(stockMPDestination.getStock())  ;
			//stockSource=stockMPSource.getStock().subtract(quantite);
			stockDestination=stockMPDestination.getStock().add(quantite);
			
			
			
			prixStockDestination=stockMPDestination.getPrixUnitaire();
			prixStockSource=stockMPSource.getPrixUnitaire();
			
			prixMoyen=prixStockDestination.multiply(stockMPDestination.getStock()).add(prixStockSource) .multiply(quantite) ;
			
			prixMoyen=prixMoyen.divide(sommeStock, 6, BigDecimal.ROUND_HALF_UP);
			stockMPDestination.setPrixUnitaire(prixMoyen);
			
			stockMPDestination.setStock(stockDestination);
			//stockMPSource.setStock(stockSource);
			stockMPDAO.edit(stockMPDestination);
		}else
		{
			StockMP stockMPDestinationTMP=new StockMP();
			sommeStock=quantite ;
			//stockSource=stockMPSource.getStock().subtract(quantite);
			stockDestination=quantite;
			
			
			
			prixStockDestination=BigDecimal.ZERO;
			prixStockSource=stockMPSource.getPrixUnitaire();
			
			prixMoyen=prixStockSource.multiply(quantite) ;
			
			prixMoyen=prixMoyen.divide(sommeStock, 6, BigDecimal.ROUND_HALF_UP);
			stockMPDestinationTMP.setPrixUnitaire(prixMoyen);
			stockMPDestinationTMP.setMagasin(magasinDestination);
			stockMPDestinationTMP.setMatierePremier(matierePremier);
			stockMPDestinationTMP.setQuantiteCommande(BigDecimal.ZERO);
			stockMPDestinationTMP.setStockMin(BigDecimal.ZERO);
			stockMPDestinationTMP.setStock(stockDestination);
			//stockMPSource.setStock(stockSource);
			stockMPDAO.add(stockMPDestinationTMP);
			
		}
		
	
		
		
		//stockMPDAO.edit(stockMPSource);
		
		detailTransferStockMP.setMagasinDestination(magasinDestination);
		detailTransferStockMP.setMagasinSouce(magasinSource);
		detailTransferStockMP.setMatierePremier(matierePremier);
		detailTransferStockMP.setQuantite(quantite);
		detailTransferStockMP.setPrixUnitaire(prixStockSource);
		detailTransferStockMP.setTransferStockMP(transferStock);
		listDetailTransferStockMP.add(detailTransferStockMP);
	}
		/*else {
		JOptionPane.showMessageDialog(null, "Stock de : �"+matierePremier.getNom()+"� ne peut Transf�re! Quantit� en stock et inf�rireure � la quantit� � transf�rer", "Erreur", JOptionPane.ERROR_MESSAGE);
		
	}*/
		
		
	}
	
	return listDetailTransferStockMP;
	
}


void intialiserTableau(){
	
	
	modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code","Nom MP","Quantit� En Stock","Quantit� a Tranf�rer"
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
