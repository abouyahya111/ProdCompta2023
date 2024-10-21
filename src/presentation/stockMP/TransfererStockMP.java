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
import java.math.RoundingMode;
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
import util.Utils;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailFactureVenteMPDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.FacturePFDAOImpl;
import dao.daoImplManager.FactureVenteMPDAOImpl;
import dao.daoImplManager.JourneeDAOImpl;
import dao.daoImplManager.MouvementStockGlobalDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.ClientDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailFactureAchatMPDAO;
import dao.daoManager.DetailFactureVenteMPDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.FactureAchatMPDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FactureVenteMPDAO;
import dao.daoManager.JourneeDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MouvementStockGlobalDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.SequenceurDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.Client;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailFactureAchatMP;
import dao.entity.DetailFacturePF;
import dao.entity.DetailFactureVenteMP;
import dao.entity.DetailMouvementStock;
import dao.entity.DetailTransferStockMP;
import dao.entity.FactureAchatMP;
import dao.entity.FacturePF;
import dao.entity.FactureVenteMP;
import dao.entity.Fournisseur;
import dao.entity.Journee;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.MouvementStockGlobal;
import dao.entity.Parametre;
import dao.entity.Sequenceur;
import dao.entity.StockMP;
import dao.entity.TransferStockMP;
import dao.entity.Utilisateur;

import javax.swing.SwingConstants;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import java.util.Locale;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import javax.swing.JCheckBox;


public class TransfererStockMP extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleMP;

	private JXTable table;

	private ImageIcon imgInit;
	private ImageIcon imgValider;
	private ImageIcon imgRechercher;
	private ImageIcon imgImprimer;
	private JButton btnIntialiserOF;
	private  JDateChooser dateTransfereChooser = new JDateChooser();
	private List<DetailFactureVenteMP> listDetailFactureVenteMP =new ArrayList<DetailFactureVenteMP>();
	private List<DetailTransferStockMP> listDetailTransfertStockMP =new ArrayList<DetailTransferStockMP>();
	private Map< Integer, MatierePremier> mapMatierePremiereDetailMouvement= new HashMap<>();
	private Map< Integer, BigDecimal> mapQuantiteDetailMouvement= new HashMap<>();
	private List<Journee> listJournee =new ArrayList<Journee>();
	private List<DetailMouvementStock> listDetailMouvementStockSource =new ArrayList<DetailMouvementStock>();
	private List<DetailMouvementStock> listDetailMouvementStockDestination =new ArrayList<DetailMouvementStock>();
	private JourneeDAO journeeDAO;
	private MouvementStockGlobalDAO mouvementStockGlobalDAO;
	private static SequenceurDAO sequenceurDAO=ProdLauncher.sequenceurDAO;
	
	
	private Map< String, String> mapQuantiteMP = new HashMap<>();
	private Map< String, String> mapPrixMP = new HashMap<>();
	private Map< String, String> mapreduction = new HashMap<>();
	private Map< Integer, MatierePremier> mapMatierePremier= new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremierTmp = new HashMap<>();
	
	private Map< String, Magasin> mapMagasinSource = new HashMap<>();
	private Map< String, Magasin> mapMagasinDestination = new HashMap<>();
	
	private Map< String, Integer> mapDepotSource = new HashMap<>();
	private Map< String, Depot> mapDepotSourcetmp = new HashMap<>();
	private Map< String, Depot> mapDepotDestionationTmp = new HashMap<>();
	private Map< String, Integer> mapDepotDestionation = new HashMap<>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	
	TransferStockMP transferStock = new TransferStockMP();
	private ParametreDAO parametredao;
	private JComboBox<String> comboMagasinDestination=new JComboBox();
	private JComboBox<String> comboDepotSource=new JComboBox();;
	private  JComboBox<String> comboMagasinSource=new JComboBox();;
	private JComboBox<String> comboDepotDestination=new JComboBox();;
	private Utilisateur utilisateur;
	private JLabel lblMagasinSource;
	private JLabel lblDpotDestination;
	private JLabel lblMagasinDstination;
	private ClientDAO clientDAO;
	private DetailFactureVenteMPDAO detailFactureVenteMPdao;
	private DetailTransferMPDAO detailTransferMPDAO;
	private FactureVenteMPDAO factureVenteMPdao;
	private FacturePFDAO factureVentePFdao;
	private FactureVenteMP factureVenteMP=new FactureVenteMP();
	private DepotDAO depotDAO;
	private StockMPDAO stockMPDAO;
	private TransferStockMPDAO transferStockMPDAO;
	private MatierePremiereDAO matierePremiereDAO;
	private Depot depot = new Depot();
	private JTextField txtCodeTransfer;
	private JTextField txtnumbl;
	boolean Erreurmarge=false;
	private JCheckBox chckbxVente = new JCheckBox();
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public TransfererStockMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1385, 565);
        try{
        	
        	mouvementStockGlobalDAO=new MouvementStockGlobalDAOImpl();
        	journeeDAO=new JourneeDAOImpl();
        	factureVenteMPdao=new FactureVenteMPDAOImpl();
        	depotDAO=new DepotDAOImpl();
        	stockMPDAO=new StockMPDAOImpl();
        	transferStockMPDAO=new TransferStockMPDAOImpl();
        	detailFactureVenteMPdao=new DetailFactureVenteMPDAOImpl();
        	 utilisateur=AuthentificationView.utilisateur;
        	 clientDAO=new ClientDAOImpl();
        		parametredao=new ParametreDAOImpl();
        		factureVentePFdao=new FacturePFDAOImpl();
        		detailTransferMPDAO=new DetailTransferMPDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		String Codedepot= AuthentificationView.utilisateur.getCodeDepot();
		listDepot =new ArrayList<Depot>(); 
		if(Codedepot.equals(CODE_DEPOT_SIEGE)){
				listDepot = depotDAO.findDepotByCodeSaufEnParametre(Codedepot);
		   	} else {
		   		depot = depotDAO.findByCode(Codedepot);
		   		listDepot.add(depot);
		   	}
    		 
    		
	     //	comboDepotDestination.addItem(depot.getLibelle());
	     //	comboDepotSource.addItem(depot.getLibelle());
	    
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgValider=new ImageIcon(this.getClass().getResource("/img/valider.png"));
            imgRechercher= new ImageIcon(this.getClass().getResource("/img/rechercher.png"));
            imgImprimer = new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
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
				  		   
					  		  txtCodeTransfer = new JTextField();
					  		util.Utils.copycoller(txtCodeTransfer);
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
				  		     	scrollPane.setBounds(9, 155, 1247, 343);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		      
				  		   /*   List<Magasin> 	listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);
					  		      if(listMagasin!=null){
					  		    	  
					  		    	  int j=0;
						  		      	while(j<listMagasin.size())
						  		      		{	
						  		      			Magasin magasin=listMagasin.get(j);
						  		      			comboMagasinSource.addItem(magasin.getLibelle());
						  		      			mapMagasinSource.put(magasin.getLibelle(), magasin);
						  		      			comboMagasinDestination.addItem(magasin.getLibelle());
						  		      			mapMagasinDestination.put(magasin.getLibelle(), magasin);
					  		      			
						  		      			j++;
						  		      		}
					  		      }*/
					  		      
				  		      if(utilisateur.getLogin().equals("admin"))
				  			  {
				  		    	listDepot = depotDAO.findAll();	
					  		  	Magasin magasin=depotDAO.MagasinByTypeMagasinStkAttente(MAGASIN_CODE_TYPE_MP_ATT);
					  		     	comboDepotSource.addItem("");
					  		     	comboDepotDestination.addItem("");
						  		      int i=0;
						  		      	while(i<listDepot.size())
						  		      		{	
						  		      		
						  		      		
						  		      			Depot depot=listDepot.get(i);
						  		      			if(magasin!=null)
						  		      			{
						  		      				if(depot.getId()!=magasin.getDepot().getId())
						  		      				{
						  		      					
						  		      				mapDepotSource.put(depot.getLibelle(), i);
							  		      		    mapDepotSourcetmp.put(depot.getLibelle(), depot);
							  		      		    mapDepotDestionationTmp.put(depot.getLibelle(), depot);
							  		      			mapDepotDestionation.put(depot.getLibelle(), i);
							  		      			comboDepotSource.addItem(depot.getLibelle());
							  		      			comboDepotDestination.addItem(depot.getLibelle());
						  		      				}
						  		      			}else
						  		      			{
						  		      			mapDepotSource.put(depot.getLibelle(), i);
						  		      		    mapDepotSourcetmp.put(depot.getLibelle(), depot);
						  		      		    mapDepotDestionationTmp.put(depot.getLibelle(), depot);
						  		      			mapDepotDestionation.put(depot.getLibelle(), i);
						  		      			comboDepotSource.addItem(depot.getLibelle());
						  		      			comboDepotDestination.addItem(depot.getLibelle());
						  		      			}
						  		      			i++;
						  		      		}
						  		      	
				  			  }else
				  			  {
				  				  
				  				 Depot depot=depotDAO.findByCode(utilisateur.getCodeDepot());
				  				  if(depot!=null)
				  				  {
				  					comboDepotSource.addItem("");
				  					comboDepotSource.addItem(depot.getLibelle());
				  					mapDepotSourcetmp.put(depot.getLibelle(), depot);
				  					listDepot=depotDAO.findDepotByCodeSaufEnParametre(utilisateur.getCodeDepot());
				  					for(int i=0;i<listDepot.size();i++)
				  					{
				  						comboDepotDestination.addItem(listDepot.get(i).getLibelle());	
				  						 mapDepotDestionationTmp.put(listDepot.get(i).getLibelle(), listDepot.get(i));
				  						
				  					}
				  					
				  					
				  			     	
				  				  }  
				  				  
				  				  
				  				  
				  				  
				  				  
				  			  }
				  		    comboDepotSource.setSelectedIndex(-1);
				  		  comboDepotDestination.setSelectedIndex(-1);
				  		     	
					  		      comboDepotSource.addItemListener(new ItemListener() {
					  		     	 	public void itemStateChanged(ItemEvent e) {
					  		     	 	
					  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
					  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
						  		     	  	 // comboGroupe = new JComboBox();
					  		     	 	comboMagasinSource.removeAllItems();
					  		     	 Depot depot =new Depot();
					  		     	 	//comboGroupe.addItem("");
					  		     	if(!comboDepotSource.getSelectedItem().equals(""))
						  		     	  	   	 depot =mapDepotSourcetmp.get(comboDepotSource.getSelectedItem());
								  		       
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
					  		      
					  		    comboDepotDestination.addItemListener(new ItemListener() {
				  		     	 	public void itemStateChanged(ItemEvent e) {
				  		     	 	
				  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
				  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
					  		     	  	 // comboGroupe = new JComboBox();
				  		     	 	comboMagasinDestination.removeAllItems();
				  		     	 	//comboGroupe.addItem("");
				  		     	 Depot depot =new Depot();
				  		     	 	if(!comboDepotDestination.getSelectedItem().equals(""))
					  		     	  	   	 depot =mapDepotDestionationTmp.get(comboDepotDestination.getSelectedItem());
							  		       
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
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(9, 126, 1247, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 13, 1247, 111);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("D\u00E9pot Soure");
				  		     	lblMachine.setBounds(10, 34, 114, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	
				  		     	 
				  		     	 comboDepotSource.setBounds(103, 34, 176, 24);
				  		     	 layeredPane.add(comboDepotSource);
				  		     	
				  		     	 
				  		     	 JLabel lblGroupe = new JLabel("Magasin Source");
				  		     	 lblGroupe.setBounds(10, 73, 102, 24);
				  		     	 layeredPane.add(lblGroupe);
				  		     	 lblGroupe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	
				  		     	 comboMagasinSource.setBounds(103, 74, 193, 24);
				  		     	 layeredPane.add(comboMagasinSource);
				  		     	 
				  		 
				  		  comboMagasinDestination.setBounds(409, 74, 228, 24);
				  		  layeredPane.add(comboMagasinDestination);
				  		  
				  		  JLabel lblEquipe = new JLabel("Magasin Destination");
				  		  lblEquipe.setBounds(299, 73, 108, 26);
				  		  layeredPane.add(lblEquipe);
				  		  
				  		  lblMagasinSource = new JLabel("Magasin Source ");
				  		  lblMagasinSource.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
				  		  lblMagasinSource.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
				  		  lblMagasinSource.setBounds(10, 11, 237, 14);
				  		  layeredPane.add(lblMagasinSource);
				  		  
				  		  lblDpotDestination = new JLabel("Dépot Destination");
				  		  lblDpotDestination.setBounds(299, 33, 108, 26);
				  		  layeredPane.add(lblDpotDestination);
				  		  
				  		  
				  		  comboDepotDestination.setBounds(409, 34, 200, 24);
				  		  layeredPane.add(comboDepotDestination);
				  		  
				  		  lblMagasinDstination = new JLabel("Magasin D\u00E9stination");
				  		  lblMagasinDstination.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
				  		  lblMagasinDstination.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
				  		  lblMagasinDstination.setBounds(289, 11, 254, 14);
				  		  layeredPane.add(lblMagasinDstination);
				  		  
				  		  JButton btnAfficherStock = new JButton("");
				  		btnAfficherStock.setIcon(imgRechercher);
				  		  btnAfficherStock.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  				if(comboDepotSource.getSelectedItem().equals(""))	{
				  					JOptionPane.showMessageDialog(null, "Il faut choisir un magasin", "Erreur", JOptionPane.ERROR_MESSAGE);
				  				} else {
				  					txtCodeTransfer.setText("");	
				  					List<StockMP> listStockMP=stockMPDAO.findSockNonVideByMagasin(mapMagasinSource.get(comboMagasinSource.getSelectedItem()).getId());
				  					
				  					afficher_tableMP(listStockMP);
				  				}
				  			  }
				  		  });
				  		  btnAfficherStock.setBounds(1155, 34, 36, 36);
				  		  layeredPane.add(btnAfficherStock);
				  		
				  		  txtCodeTransfer.setBounds(728, 74, 155, 24);
				  		  layeredPane.add(txtCodeTransfer);
				  		  txtCodeTransfer.setColumns(10);
				  		  
				  		   dateTransfereChooser = new JDateChooser();
				  		   dateTransfereChooser.addPropertyChangeListener(new PropertyChangeListener() {
				  		   	public void propertyChange(PropertyChangeEvent arg0) {/*
				  		   		

								if(dateTransfereChooser.getDate()!=null)
								{
								SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
								 String date = dcn.format(dateTransfereChooser.getDate());
								
								 
								txtnumfacture.setText(Utils.genererCodeFactureVente(date));
								}
							
				  		   	*/}
				  		   });
				  		  dateTransfereChooser.setLocale(Locale.FRANCE);
				  		  dateTransfereChooser.setDateFormatString("dd/MM/yyyy");
				  		  dateTransfereChooser.setBounds(728, 32, 155, 26);
				  		  layeredPane.add(dateTransfereChooser);
				  		  
				  		  JLabel lblDateTransfre = new JLabel("Date Transf\u00E8re :");
				  		  lblDateTransfre.setBounds(635, 31, 87, 26);
				  		  layeredPane.add(lblDateTransfre);
				  		  
				  		  JLabel label = new JLabel("Code Transafert ");
				  		  label.setBounds(642, 73, 94, 24);
				  		  layeredPane.add(label);
				  		  
				  		  txtnumbl = new JTextField();
				  		  txtnumbl.setEnabled(false);
				  		  txtnumbl.setColumns(10);
				  		  txtnumbl.setBounds(964, 34, 155, 24);
				  		  layeredPane.add(txtnumbl);
				  		  
				  		  JLabel lblNFacture = new JLabel("N\u00B0 BL :");
				  		  lblNFacture.setBounds(893, 34, 87, 26);
				  		  layeredPane.add(lblNFacture);
		
		JButton btnValiderTransfer = new JButton("Valider Transfer MP");
		btnValiderTransfer.setIcon(imgValider);
		btnValiderTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					listDetailFactureVenteMP.clear();
					SimpleDateFormat dcn = new SimpleDateFormat("yyMMdd");
				String date = dcn.format(dateTransfereChooser.getDate());
				String dateTransfere=((JTextField)dateTransfereChooser.getDateEditor().getUiComponent()).getText();
				if(!remplirMapChargeSupp())	{
					JOptionPane.showMessageDialog(null, "Il faut remplir la quantité", "Erreur", JOptionPane.ERROR_MESSAGE);
				} else {
					Magasin magasinSource =mapMagasinSource.get(comboMagasinSource.getSelectedItem());
					Magasin magasinDestination =mapMagasinDestination.get(comboMagasinDestination.getSelectedItem());
					
					if(magasinSource.getCode().equals(magasinDestination.getCode())){
						JOptionPane.showMessageDialog(null, "Le Magasin source doit ètre différent déstination", "Erreur", JOptionPane.ERROR_MESSAGE);
					}else if(dateTransfere.equals(""))
					{
						JOptionPane.showMessageDialog(null, "Il faut saisir la date de transfer", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
					else
					
					{
						
						FactureVenteMP factureventetmp=factureVenteMPdao.findFactureVenteMPByNumBL(txtnumbl.getText());
						FacturePF factureventetPF=factureVentePFdao.findFactureVentePFByNumBL(txtnumbl.getText());
						if(factureventetmp!=null || factureventetPF!=null)
						{
							JOptionPane.showMessageDialog(null, "Num BL existe déja ","Erreur",JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						for(int i=0;i<mapMatierePremier.size();i++)
						{
							MatierePremier matierepremiere=mapMatierePremier.get(i);
							
							
							 DetailFactureVenteMP detailFacture=new DetailFactureVenteMP();
			        	 	 if(mapreduction.get(matierepremiere.getCode())!=null)
			        	 	 {
			        	 		 detailFacture.setReduction(new BigDecimal(mapreduction.get(matierepremiere.getCode())));
			        	 	 }else
			        	 	 {
			        	 		 detailFacture.setReduction(BigDecimal.ZERO); 
			        	 	 }
					        
					         
					          detailFacture.setMatierePremiere(matierepremiere);
					          detailFacture.setQuantite(new BigDecimal(mapQuantiteMP.get(matierepremiere.getCode())));
					          detailFacture.setPrixUnitaire(new BigDecimal(mapPrixMP.get(matierepremiere.getCode())));
					          detailFacture.setMontantHT(new BigDecimal(mapPrixMP.get(matierepremiere.getCode())).multiply(new BigDecimal(mapQuantiteMP.get(matierepremiere.getCode()))).setScale(6, RoundingMode.HALF_UP));
					          detailFacture.setMontantTVA(((new BigDecimal(mapPrixMP.get(matierepremiere.getCode())).multiply(new BigDecimal(mapQuantiteMP.get(matierepremiere.getCode())))).multiply(Constantes.TVA)).setScale(6, RoundingMode.HALF_UP));
					          detailFacture.setTva(Constantes.TVA.multiply(new BigDecimal(100)));
					       
					         
					        detailFacture.setMontantTTC(((new BigDecimal(mapPrixMP.get(matierepremiere.getCode())).multiply(new BigDecimal(mapQuantiteMP.get(matierepremiere.getCode())))).add((new BigDecimal(mapPrixMP.get(matierepremiere.getCode())).multiply(new BigDecimal(mapQuantiteMP.get(matierepremiere.getCode())))).multiply(Constantes.TVA))).setScale(6, RoundingMode.HALF_UP));
					          
					         
					         //  detailFacture.setTypeVente(typevente);
					        
					           detailFacture.setFactureVenteMP(factureVenteMP);
					       //  detailFacture.setDateCreation(new Date());
					           
					           detailFacture.setUtilisateur(utilisateur);
					         
					           listDetailFactureVenteMP.add(detailFacture);
							
						}
						
						  int i=0;
					        BigDecimal montanttotal=BigDecimal.ZERO;
					        BigDecimal montanttotalHT=BigDecimal.ZERO;
					        BigDecimal montanttotalTVA=BigDecimal.ZERO;
					        BigDecimal sommequantite=BigDecimal.ZERO;
					        while(i<listDetailFactureVenteMP.size())
					        {
					          DetailFactureVenteMP detailFactureVenteMP=listDetailFactureVenteMP.get(i);
					          montanttotal=  montanttotal.add(detailFactureVenteMP.getMontantTTC());
					          sommequantite= sommequantite.add(detailFactureVenteMP.getQuantite());
					          montanttotalHT=montanttotalHT.add(detailFactureVenteMP.getMontantHT());
					          montanttotalTVA=montanttotalTVA.add(detailFactureVenteMP.getMontantTVA());
					            
					            i++;
					        }
						
					Depot depotdestination=mapDepotDestionationTmp.get(comboDepotDestination.getSelectedItem());
					Depot depotsource=mapDepotSourcetmp.get(comboDepotSource.getSelectedItem());
						
					Client client=clientDAO.findClientByCodeClient(magasinDestination.getCodeMachine());
						
					String codeTransfert=Utils.genererCodeTransfer(depot.getCode(),ETAT_TRANSFER_STOCK_VENTE);
						
					factureVenteMP.setNumBl(txtnumbl.getText());
					factureVenteMP.setClient(client);
					factureVenteMP.setDepotDestination(depotdestination);
					factureVenteMP.setDepotSource(depotsource);
					factureVenteMP.setMagasinSource(magasinSource);
					factureVenteMP.setMagasinDestination(magasinDestination);
					factureVenteMP.setDateFacture(dateTransfereChooser.getDate());
					factureVenteMP.setEtat(Constantes.ETAT_NON_REGLE);
					factureVenteMP.setType(Constantes.TYPE_BON_LIVRAISON);
					factureVenteMP.setMontantHT(montanttotalHT);
					factureVenteMP.setMontantTVA(montanttotalTVA);
					factureVenteMP.setMontantTTC(montanttotal);	
					factureVenteMP.setDetailFactureVenteMP(listDetailFactureVenteMP);
					factureVenteMP.setCodeTransfer(codeTransfert);
					factureVenteMP.setCreerPar(utilisateur);
				  
						
					txtCodeTransfer.setText(codeTransfert);
					transferStock.setCodeTransfer(txtCodeTransfer.getText());
					transferStock.setCreerPar(AuthentificationView.utilisateur);
					transferStock.setDate(new Date());
					transferStock.setDateTransfer(dateTransfereChooser.getDate());
					transferStock.setStatut(ETAT_TRANSFER_STOCK_VENTE);
					transferStock.setDepot(mapDepotSourcetmp.get(comboDepotSource.getSelectedItem()));
					listDetailTransfertStockMP=remplirDetailTransfer();
					transferStock.setListDetailTransferMP(listDetailTransfertStockMP);
					
							/*
							 * for(int k=0;k< listDetailTransfertStockMP.size();k++) {
							 * 
							 * 
							 * detailTransferMPDAO.add(listDetailTransfertStockMP.get(k));
							 * 
							 * 
							 * 
							 * }
							 */
					if(Erreurmarge==true)
					{
						 int reponse = JOptionPane.showConfirmDialog(null, " Erreur de la marge Vous voulez vraiment valider le prix  ?", 
									"Satisfaction", JOptionPane.YES_NO_OPTION);
							 
							if(reponse == JOptionPane.YES_OPTION )
								
								
							{
								
								
							}else
							{
								 return;
							}
					}
					
					 factureVenteMPdao.add(factureVenteMP);
					 transferStockMPDAO.add(transferStock);
					 intialiserTableau();
					 factureVenteMP=new FactureVenteMP();
					 transferStock=new TransferStockMP();
					    
					/*
					  Sequenceur  sequenceur=sequenceurDAO.findByCodeLibelle(date, Constantes.CODE_FACTURE_VENTE);
					    if(sequenceur!=null)
					    {
					    	int valeur=sequenceur.getValeur()+1;
						    sequenceur.setValeur(valeur);
						    sequenceurDAO.edit(sequenceur);
					    }else
					    {
					    	Sequenceur sequenseur=new Sequenceur();
							sequenseur.setLibelle( Constantes.CODE_FACTURE_VENTE);
							sequenseur.setCode(date);
							sequenseur.setValeur(1);
							sequenceurDAO.add(sequenseur);
					    }
					   
					    
					    
					 //////////////////////////////////////////////////////////Chercher le Journée et liste des mouvement de stock de cet journée /////////////////////////////////////////
					
					 Depot depotSource=mapDepotSourcetmp.get(comboDepotSource.getSelectedItem());
					 Depot depotDestination=mapDepotDestionationTmp.get(comboDepotDestination.getSelectedItem());
					 
					 
	                Journee journeeSource=journeeDAO.findByDateEtatOuverte(dateTransfereChooser.getDate(), Constantes.ETAT_STATUT_OVERTE,depotSource.getId());
	                Journee journeeDestination=journeeDAO.findByDateEtatOuverte(dateTransfereChooser.getDate(), Constantes.ETAT_STATUT_OVERTE,depotDestination.getId());
	                
	                
	                if(journeeSource!=null && journeeDestination!=null)
	               {
	               	 ///////// Mouvement Stock Magasin Source ////////////////////
	               	 
	               
	               	
	               	 
	               	if(depotSource!=null && magasinSource !=null)
	               	{
	               		
	               		 mouvementStockGlobalSource=mouvementStockGlobalDAO.findMouvementStockGlobalByDetailMouvementStock(dateTransfereChooser.getDate(),depotSource.getId(), magasinSource.getId());
	                     if(mouvementStockGlobalSource!=null)
	                      {
	                     listDetailMouvementStockSource=mouvementStockGlobalSource.getDetailMouvementStock();
	                     }	
	               		
	               		
	               	}
	               	 
	                       ///////// Mouvement Stock Magasin Destination ////////////////////
	                       
	                       
	                  
	                       
	                  	 
	                  	 if(depotDestination!=null && magasinDestination!=null)
	                  	 {
	                  		 
	                  		mouvementStockGlobalDestination=mouvementStockGlobalDAO.findMouvementStockGlobalByDetailMouvementStock(dateTransfereChooser.getDate(), depotDestination.getId(), magasinDestination.getId());
	                       if(mouvementStockGlobalDestination!=null)
	                             {
	                        listDetailMouvementStockDestination=mouvementStockGlobalDestination.getDetailMouvementStock();
	                        }
	                        
	                  		 
	                  	 }

	                       }



	                      ////////////////////////////////////////////////////// chercher MP dans la liste detailMouvementStock Magasin Source et Modifier Quantite Transfere Sortie et leStock Finale  ////////////////////////////////

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
	                   
	                   ////////////////////////////////////////////////////// chercher MP dans la liste detailMouvementStock Magasin Destination et Modifier Transfere  entrer Charger et leStock Finale  ////////////////////////////////

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
	                   
	                   
	                 if(mouvementStockGlobalSource!=null && mouvementStockGlobalDestination!=null)
	                 {
	               	  mouvementStockGlobalSource.setDetailMouvementStock(listDetailMouvementStockSource);

	                  mouvementStockGlobalDAO.edit(mouvementStockGlobalSource);

	                
	               	  mouvementStockGlobalDestination.setDetailMouvementStock(listDetailMouvementStockDestination);

	                  mouvementStockGlobalDAO.edit(mouvementStockGlobalDestination);

	                   }
	*/

	                   ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					
					JOptionPane.showMessageDialog(null, "Stock transféré avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
					
					
				initialierMapStock();
					}
				}
			 
					
					
					
					
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "La Quantité , le Prix et la Remise doit etre en chiffre SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				}
				
				
				 }
		});
		btnValiderTransfer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnValiderTransfer.setBounds(247, 509, 142, 23);
		add(btnValiderTransfer);
		
		JButton btnimprimer = new JButton("Bon Transfere MP D\u00E9plac\u00E9e");
		btnimprimer.addActionListener(new ActionListener() {
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
						JasperUtils.imprimerBonTransfereMP(listDetailTransferStockMP,parameters,transferStock.getCodeTransfer());
				 }else
				 {
					 JOptionPane.showMessageDialog(null, "Il faut saisir les matières premiere à transferer", "Erreur", JOptionPane.INFORMATION_MESSAGE);
				 }
				
	  		  		}else {
	  		  		JOptionPane.showMessageDialog(null, "Il faut choisir un megasin", "Erreur", JOptionPane.INFORMATION_MESSAGE);
	  		  		}
				
			//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  		  	}else {
	  		  	JOptionPane.showMessageDialog(null, "Il faut valider le transfer avant d'imprimer ", "Erreur Impression", JOptionPane.ERROR_MESSAGE);
	  		  	}
				
			}
		});
		btnimprimer.setBounds(558, 509, 200, 23);
		btnimprimer.setIcon(imgImprimer);
		add(btnimprimer);
		
		 chckbxVente = new JCheckBox("Vente");
		chckbxVente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxVente.isSelected()==true)
				{
					txtnumbl.setText("");
					txtnumbl.setEnabled(true);
				}else
				{
					txtnumbl.setText("");
					txtnumbl.setEnabled(false);
				}
					
			}
		});
		chckbxVente.setBounds(1262, 44, 73, 24);
		add(chckbxVente);
		
				  		     
				  		 
	}
	
	
	void intialiser()
	{
		comboDepotDestination.setSelectedItem("");
		comboDepotSource.setSelectedItem("");
		comboMagasinDestination.setSelectedItem("");
		comboMagasinSource.setSelectedItem("");
		txtCodeTransfer.setText("");
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
	mapMatierePremier.clear();
	mapQuantiteMP.clear();
	mapPrixMP.clear();
	mapreduction.clear();
	for(int j=0;j<table.getRowCount();j++){
		
		if(!table.getValueAt(j, 3).toString().equals("") &&  !table.getValueAt(j, 4).toString().equals("")){
			
			mapQuantiteMP.put(table.getValueAt(j, 0).toString(),table.getValueAt(j, 3).toString());
			mapMatierePremier.put(i, mapMatierePremierTmp.get(table.getValueAt(j, 0).toString()));
			mapPrixMP.put(table.getValueAt(j, 0).toString(),table.getValueAt(j, 4).toString());
			
			if(table.getValueAt(j,5)!=null)
			{
				
				mapreduction.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 5).toString());
				
			}else
			{
				mapreduction.put(table.getValueAt(j, 0).toString(), "0");
				
			}
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
	BigDecimal prixVente=BigDecimal.ZERO;
	BigDecimal stockSource=BigDecimal.ZERO;
	BigDecimal stockDestination=BigDecimal.ZERO;
	BigDecimal sommeStock=BigDecimal.ZERO;
	BigDecimal marge=BigDecimal.ZERO;
	boolean trouvemarge=false;
	StockMP stockMPSource=null;
	List<DetailTransferStockMP> listDetailTransferStockMP= new ArrayList<DetailTransferStockMP>();
	Erreurmarge=false;
	for(int i=0;i<mapMatierePremier.size();i++){
		
		DetailTransferStockMP detailTransferStockMP=new DetailTransferStockMP();
		Magasin magasinSource =mapMagasinSource.get(comboMagasinSource.getSelectedItem());
		Magasin magasinDestination=mapMagasinDestination.get(comboMagasinDestination.getSelectedItem());
		MatierePremier matierePremier =mapMatierePremier.get(i);
		quantite=new BigDecimal(mapQuantiteMP.get(matierePremier.getCode()));
		prixVente=new BigDecimal(mapPrixMP.get(matierePremier.getCode()));
		 stockMPSource=stockMPDAO.findStockByMagasinMP(matierePremier.getId(), magasinSource.getId());
		//StockMP stockMPDestination=stockMPDAO.findStockByMagasinMP(matierePremier.getId(), magasinDestination.getId());
		if(stockMPSource!=null)
		{
			if(stockMPSource.getStock().compareTo(quantite)>=0){
				/*sommeStock=quantite+stockMPDestination.getStock();*/
				/*
				mapMatierePremiereDetailMouvement.put(matierePremier.getId(),matierePremier);
				mapQuantiteDetailMouvement.put(matierePremier.getId(), quantite);
				*/
				
				 marge=((prixVente).subtract(stockMPSource.getPrixUnitaire())).divide(prixVente, 6, RoundingMode.HALF_UP);
        		 marge=marge.multiply(new BigDecimal(100));
	        	 Parametre margemax=parametredao.findByCode(Constantes.MARGE_MAX);
	        	 Parametre margemin=parametredao.findByCode(Constantes.MARGE_MIN);
					/*
					 * if(marge.compareTo(margemax.getValeur())>0 ||
					 * marge.compareTo(margemin.getValeur())<0) {
					 * 
					 * 
					 * Erreurmarge=true; break; }
					 */
			stockSource=stockMPSource.getStock().subtract(quantite);
			//stockDestination=stockMPDestination.getStock().add(quantite);
			
			
			
			//prixStockDestination=stockMPDestination.getPrixUnitaire();
			prixStockSource=stockMPSource.getPrixUnitaire();
			
			//prixMoyen=prixStockDestination*stockMPDestination.getStock()+ prixStockSource *quantite;
			
			//=prixMoyen/sommeStock;
		//	stockMPDestination.setPrixUnitaire(prixMoyen);
			
			//stockMPDestination.setStock(stockDestination);
			stockMPSource.setStock(stockSource);
			
			//stockMPDAO.edit(stockMPDestination);
			
			
			detailTransferStockMP.setMagasinDestination(magasinDestination);
			detailTransferStockMP.setMagasinSouce(magasinSource);
			detailTransferStockMP.setMatierePremier(matierePremier);
			detailTransferStockMP.setQuantite(quantite);
			detailTransferStockMP.setPrixUnitaire(prixStockSource);
			detailTransferStockMP.setTransferStockMP(transferStock);
			listDetailTransferStockMP.add(detailTransferStockMP);
			
			
		}else {
			JOptionPane.showMessageDialog(null, "Stock de : «"+matierePremier.getNom()+"» ne peut Transfére! Quantité en stock et inférireure à la quantité à transférer", "Erreur", JOptionPane.ERROR_MESSAGE);
			
		}
		}

	}
	if(stockMPSource!=null)
	{
		if(Erreurmarge==false)
		{
			stockMPDAO.edit(stockMPSource);
		}

	}
	
	return listDetailTransferStockMP;
	
}


void intialiserTableau(){
	
	
	modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code","Nom MP","Quantité En Stock","Quantité a Tranférer","Prix Vente","Reduction"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,true,true,true
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		 table.setModel(modeleMP); 
		 table.getColumnModel().getColumn(0).setPreferredWidth(10);
     table.getColumnModel().getColumn(1).setPreferredWidth(260);
     table.getColumnModel().getColumn(2).setPreferredWidth(160);
   table.getColumnModel().getColumn(3).setPreferredWidth(160);
   table.getColumnModel().getColumn(4).setPreferredWidth(60);
   table.getColumnModel().getColumn(5).setPreferredWidth(60);
}



void initialierMapStock(){
	
	for(int j=0;j<table.getRowCount();j++){
		table.setValueAt("", j, 3);
		table.setValueAt("", j,4);
		table.setValueAt("", j,5);
	}
}
}
