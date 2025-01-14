package presentation.stockMP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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

import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.CompteMagasinierDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoManager.CompteMagasinierDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.StockMPDAO;
import dao.entity.CompteMagasinier;
import dao.entity.Depot;
import dao.entity.DetailCompteMagasinier;
import dao.entity.DetailEstimationPromo;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.Utilisateur;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class AjoutManqueDepot extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;
	private ImageIcon imgModifier;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgRechercher;;
	
	private List<MatierePremier> listMatierePremiere =new ArrayList<MatierePremier>();
	private List<DetailCompteMagasinier> listDetailCompteMagasinier =new ArrayList<DetailCompteMagasinier>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private Map< String, MatierePremier> mapMatierPremier = new HashMap<>();
	
	private Map< Integer, String> mapQuantite= new HashMap<>();
	private Map< Integer, String> mapPrixUnitaire= new HashMap<>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	
	private MatierePremiereDAO matierePremierDAO;
	private CompteMagasinierDAO compteMagasinierDAO;
	private StockMPDAO stockmpDAO;
	private Utilisateur utilisateur;
	private DepotDAO depotdao;
	
	private CompteMagasinier compteMagasinier=new CompteMagasinier();

	
	
	private JTextField textNom;
	private JTextField textCodeMagasinier;
	private JTextField textcodemp;
	private JTextField textdesignation;
	private JTextField textquantite;
	private JComboBox combomp = new JComboBox();
    private	JButton btnAjouter = new JButton("Ajouter");
    private JButton modifbutton = new JButton();
    private JButton suppbutton = new JButton();
   private JComboBox comboMagasin = new JComboBox();
   private JComboBox comboDepot = new JComboBox();
   
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	@SuppressWarnings("unchecked")
	public AjoutManqueDepot() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 837);
        try{
        	
        	matierePremierDAO=new MatierePremierDAOImpl();
        	compteMagasinierDAO=new CompteMagasinierDAOImpl();
        	stockmpDAO=new StockMPDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	depotdao=new DepotDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        
      
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
            imgSupprimer= new ImageIcon(this.getClass().getResource("/img/supp1.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		     table = new JXTable();
				  		     table.addMouseListener(new MouseAdapter() {
				  		     	@Override
				  		     	public void mouseClicked(MouseEvent arg0) {
				  		     		textdesignation.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				  		     		MatierePremier mp=mapMatierPremier.get(table.getValueAt(table.getSelectedRow(), 1).toString());
				  		     		textcodemp.setText(mp.getCode());
				  		     		combomp.setSelectedItem(mp.getNom());
				  		     		textquantite.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
				  		     		//textprixunitaire.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
				  		     		//textmontant.setText(table.getValueAt(table.getSelectedRow(), 4).toString());
				  		     		comboDepot.setSelectedItem(listDetailCompteMagasinier.get(table.getSelectedRow()).getDepot().getLibelle());
				  		     		comboMagasin.setSelectedItem(listDetailCompteMagasinier.get(table.getSelectedRow()).getMagasin().getLibelle());
				  		     		btnAjouter.setEnabled(false);
				  	       			modifbutton.setEnabled(true);
				  	       			suppbutton.setEnabled(true);
				  		     		
				  		     	}
				  		     });
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
				  		     	scrollPane.setBounds(10, 416, 1136, 367);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		   
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(10, 387, 1136, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
				  		     	GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
				  		     	gridBagLayout.rowWeights = new double[]{0.0};
				  		     	gridBagLayout.rowHeights = new int[]{0};
				  		     	gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
				  		     	gridBagLayout.columnWidths = new int[]{0, 0, 0};
				  		     	titledSeparator_1.setTitle("Magasinier");
				  		     	titledSeparator_1.setBounds(10, 11, 1136, 30);
				  		     	add(titledSeparator_1);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(10, 47, 1136, 54);
				  		     	add(layeredPane);
				  		     	
				  		     	textNom = new JTextField();
				  		     	textNom.setColumns(10);
				  		     	textNom.setBackground(Color.WHITE);
				  		     	textNom.setBounds(358, 13, 189, 24);
				  		     	layeredPane.add(textNom);
				  		     	
				  		     	JLabel lblNom = new JLabel("Nom  :");
				  		     	lblNom.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	lblNom.setBounds(296, 12, 77, 24);
				  		     	layeredPane.add(lblNom);
				  		     	
				  		     	JDateChooser dateChooserOperation = new JDateChooser();
				  		     	dateChooserOperation.setLocale(Locale.FRANCE);
				  		     	dateChooserOperation.setDateFormatString("dd/MM/yyyy");
				  		     	dateChooserOperation.setBounds(721, 13, 155, 26);
				  		     	layeredPane.add(dateChooserOperation);
				  		     	
				  		     	JLabel lblDateOpration = new JLabel("Date Op\u00E9ration :");
				  		     	lblDateOpration.setBounds(590, 11, 121, 26);
				  		     	layeredPane.add(lblDateOpration);
				  		     	
				  		     	JLabel lblCode = new JLabel("Code :");
				  		     	lblCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	lblCode.setBounds(10, 11, 67, 24);
				  		     	layeredPane.add(lblCode);
				  		     	
				  		     	textCodeMagasinier = new JTextField();
				  		     	textCodeMagasinier.addKeyListener(new KeyAdapter() {
				  		     		@Override
				  		     		public void keyPressed(KeyEvent e) {
				  		     			if(e.getKeyCode()==e.VK_ENTER)
				  			      		{
				  			      			if(!textCodeMagasinier.getText().equals(""))
				  			      			{
				  			      			CompteMagasinier compteMagasinier=compteMagasinierDAO.findByCode(textCodeMagasinier.getText());
				  					    		
				  					    		if(compteMagasinier!=null)
				  					    		{	
				  					    			textNom.setText(compteMagasinier.getNom());
				  					    			
				  					    		}else
				  					    		{
				  					    			 JOptionPane.showMessageDialog(null, "Code Magasinier Introuvable !!!!", "Erreur", JOptionPane.ERROR_MESSAGE);
				  					    			textNom.setText("");
				  					    			textCodeMagasinier.requestFocus();
				  					    		}
				  			      				
				  			      				
				  			      		}else
				  			      		{
				  			      			 JOptionPane.showMessageDialog(null, "Veuillez  entrer code magasinier SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
				  			      			textNom.setText("");
		  					    			textCodeMagasinier.requestFocus();
				  			      		}
				  			      		}
				  		     			
				  		     			
				  		     			
				  		     			
				  		     			
				  		     			
				  		     		}
				  		     	});
				  		     	textCodeMagasinier.setColumns(10);
				  		     	textCodeMagasinier.setBackground(Color.WHITE);
				  		     	textCodeMagasinier.setBounds(66, 10, 189, 24);
				  		     	layeredPane.add(textCodeMagasinier);
				  		     	
				  		     	JXTitledSeparator titledSeparator_2 = new JXTitledSeparator();
				  		     	GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator_2.getLayout();
				  		     	gridBagLayout_1.rowWeights = new double[]{0.0};
				  		     	gridBagLayout_1.rowHeights = new int[]{0};
				  		     	gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
				  		     	gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
				  		     	titledSeparator_2.setTitle("Detail Compte Magasinier");
				  		     	titledSeparator_2.setBounds(10, 112, 1136, 30);
				  		     	add(titledSeparator_2);
				  		     	
				  		     	JLayeredPane layeredPane_1 = new JLayeredPane();
				  		     	layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane_1.setBounds(10, 148, 1136, 191);
				  		     	add(layeredPane_1);
				  		     	
				  		     	textcodemp = new JTextField();
				  		     	textcodemp.addKeyListener(new KeyAdapter() {
				  		     		@Override
				  		     		public void keyPressed(KeyEvent e) {
				  		     			if(e.getKeyCode()==e.VK_ENTER)
				  			      		{
				  			      			if(!textcodemp.getText().equals(""))
				  			      			{
				  			      				MatierePremier matierepremiere=matierePremierDAO.findByCode(textcodemp.getText().toUpperCase());
				  					    		
				  					    		if(matierepremiere!=null)
				  					    		{	
				  					    			combomp.setSelectedItem(matierepremiere.getNom());
				  					    			
				  					    		}else
				  					    		{
				  					    			 JOptionPane.showMessageDialog(null, "Code matiere premiere Introuvable !!!!", "Erreur", JOptionPane.ERROR_MESSAGE);
				  					    			combomp.setSelectedIndex(-1);
				  					    			
				  					    		}
				  			      				
				  			      				
				  			      		}else
				  			      		{
				  			      			 JOptionPane.showMessageDialog(null, "Veuillez  entrer code matiere premiere SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
				  			      			combomp.setSelectedIndex(-1);
				  			      			
				  			      		}
				  			      		}
				  		     			
				  		     			
				  		     			
				  		     			
				  		     			
				  		     		}
				  		     	});
				  		     	textcodemp.setColumns(10);
				  		     	textcodemp.setBackground(Color.WHITE);
				  		     	textcodemp.setBounds(94, 47, 108, 24);
				  		     	layeredPane_1.add(textcodemp);
				  		     	
				  		     	JLabel lblCodeMp = new JLabel("Code MP  :");
				  		     	lblCodeMp.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	lblCodeMp.setBounds(10, 46, 77, 24);
				  		     	layeredPane_1.add(lblCodeMp);
				  		     	
				  		     	JLabel lblDesignation = new JLabel("Designation :");
				  		     	lblDesignation.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	lblDesignation.setBounds(10, 11, 77, 24);
				  		     	layeredPane_1.add(lblDesignation);
				  		     	
				  		     	textdesignation = new JTextField();
				  		     	textdesignation.setColumns(10);
				  		     	textdesignation.setBackground(Color.WHITE);
				  		     	textdesignation.setBounds(94, 12, 508, 24);
				  		     	layeredPane_1.add(textdesignation);
				  		     	
				  		     	 combomp = new JComboBox();
				  		     	
				  		     	 combomp.addActionListener(new ActionListener() {
				  		     	 	public void actionPerformed(ActionEvent arg0) {
				  		     	 		
				  		     	 	if(combomp.getSelectedIndex()!=-1)
				  			 		{
				  			 			if(!combomp.getSelectedItem().equals(""))
				  				 		{
				  				 			
				  				 		MatierePremier matierepremiere=mapMatierPremier.get(combomp.getSelectedItem());
				  				 		textcodemp.setText(matierepremiere.getCode());
				  				 		comboDepot.setSelectedIndex(-1);
				  				 		comboMagasin.setSelectedIndex(-1);
				  				 		
				  				 			
				  				 		}else
				  				 		{
				  				 			comboDepot.setSelectedItem("");;
					  				 		comboMagasin.setSelectedItem("");;
					  				 		
				  				 		}
				  				 	
				  			 		}
				  			 		
				  			 		
				  		     	 		
				  		     	 	}
				  		     	 });
				  		     	combomp.setSelectedIndex(-1);
				  		     	combomp.setBounds(324, 47, 265, 24);
				  		     	layeredPane_1.add(combomp);
				  		     	combomp.addItem("");
				  		     	listMatierePremiere=matierePremierDAO.findAll();
				  		     	int i=0;
				  		     	while(i<listMatierePremiere.size())
				  		     	{
				  		     		MatierePremier mp=listMatierePremiere.get(i);
				  		     		combomp.addItem(mp.getNom());
				  		     		mapMatierPremier.put(mp.getNom(), mp);
				  		     		i++;
				  		     	}
				  		     	
				  		     	
				  		     	JLabel lblMatierePremiere = new JLabel("Matiere Premiere :");
				  		     	lblMatierePremiere.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	lblMatierePremiere.setBounds(212, 46, 102, 24);
				  		     	layeredPane_1.add(lblMatierePremiere);
				  		     	
				  		     	JLabel lblQuantit = new JLabel("Quantit\u00E9  :");
				  		     	lblQuantit.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	lblQuantit.setBounds(10, 81, 77, 24);
				  		     	layeredPane_1.add(lblQuantit);
				  		     	
				  		     	textquantite = new JTextField();
				  		     	textquantite.addFocusListener(new FocusAdapter() {
				  		     		@Override
				  		     		public void focusLost(FocusEvent arg0) {}
				  		     	});
				  		     	textquantite.addKeyListener(new KeyAdapter() {
				  		     		@Override
				  		     		public void keyPressed(KeyEvent e) {}
				  		     	});
				  		     	textquantite.setColumns(10);
				  		     	textquantite.setBackground(Color.WHITE);
				  		     	textquantite.setBounds(94, 82, 108, 24);
				  		     	layeredPane_1.add(textquantite);
				  		     	
				  		     	JLabel lblDepot = new JLabel("Depot :");
				  		     	lblDepot.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	lblDepot.setBounds(619, 47, 56, 24);
				  		     	layeredPane_1.add(lblDepot);
				  		     	
				  		     	 comboDepot = new JComboBox();
				  		     	comboDepot.addItem("");
				  		     
				  		     	comboDepot.addItemListener(new ItemListener() {
				  		     		public void itemStateChanged(ItemEvent e) {
				  		     			
				  		     		 if(e.getStateChange() == ItemEvent.SELECTED)
				  		     		 {
				  		     			int i=0;
				  		     			if(!combomp.getSelectedItem().equals(""))
				  		     			{
				  		     				if(!comboDepot.getSelectedItem().equals(""))
					  		     			{
					  		     				Depot depot=mapDepot.get(comboDepot.getSelectedItem());
					  		     				listMagasin=depotdao.listeMagasinByCategorieinDepot(depot.getId());
					  		     				if(listMagasin.size()!=0)
					  		     				{
					  		     					comboMagasin.removeAllItems();
					  		     					comboMagasin.addItem("");
					  		     					while(i<listMagasin.size())
						  		     				{
						  		     					Magasin magasin=listMagasin.get(i);
						  		     					comboMagasin.addItem(magasin.getLibelle());
						  		     					mapMagasin.put(magasin.getLibelle(), magasin);
						  		     					i++;
						  		     				}
					  		     				}else
					  		     				{
					  		     					comboMagasin.removeAllItems();
					  		     					
					  		     				}
					  		     				
					  		     				
					  		     			}else
					  		     			{
					  		     				comboMagasin.removeAllItems();
					  		     				
					  		     			}
				  		     				
				  		     			}else
				  		     			{
				  		     				//JOptionPane.showMessageDialog(null, "Veuillez choisir la Mati�re Premiere SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
				  		     				comboMagasin.removeAllItems();
				  		     				
				  		     			}
				  		     			 
				  		     			 
				  		     			 
				  		     			 
				  		     		 }
				  		     			
				  		     			
				  		     		}
				  		     	});
				  		     	
				  		     
				  		     	comboDepot.setSelectedIndex(-1);
				  		     	comboDepot.setBounds(669, 47, 177, 24);
				  		     	layeredPane_1.add(comboDepot);
				  		     	
				  		     	JLabel lblMagasin = new JLabel("Magasin :");
				  		     	lblMagasin.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	lblMagasin.setBounds(856, 46, 56, 24);
				  		     	layeredPane_1.add(lblMagasin);
				  		  
				  		     	 comboMagasin = new JComboBox();
				  		   	comboMagasin.addItem("");
				  		     	 comboMagasin.addItemListener(new ItemListener() {
				  		     	 	public void itemStateChanged(ItemEvent e) {}
				  		     	 });
				  		     
				  		     
				  		     	comboMagasin.setSelectedIndex(-1);
				  		     	comboMagasin.setBounds(910, 47, 216, 24);
				  		     	layeredPane_1.add(comboMagasin);
				  		     	
				  		  	if(utilisateur.getNom().equals("admin"))
					      	{
							  listDepot=depotdao.findAll();
							  int k=0;
							  comboDepot.addItem("");
						     	while (k<listDepot.size())
						     	{
						     		Depot depot=listDepot.get(k);
						     		Magasin magasin=depotdao.MagasinByTypeMagasinStkAttente(MAGASIN_CODE_TYPE_MP_ATT);
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
					      		
					      		Depot depot= depotdao.findByCode(utilisateur.getCodeDepot());
					      		
					      		if(depot!=null)
					      		{
					      			comboDepot.addItem(depot.getLibelle());
					      	    	mapDepot.put(depot.getLibelle(), depot);
					      		}
					      	}
				  		     	
				  		     	
				  		     	
				  		     	/*
				  		     	listDepot=depotdao.findAll();
				  		     	int k=0;
				  		     	while (k<listDepot.size())
				  		     	{
				  		     		Depot depot=listDepot.get(k);
				  		     		comboDepot.addItem(depot.getLibelle());
				  		     		mapDepot.put(depot.getLibelle(), depot);
				  		     	
				  		     		k++;
				  		     	}
				  		     	*/
				  		     	 btnAjouter = new JButton("Ajouter");
				  		     	btnAjouter.addActionListener(new ActionListener() {
				  		     		public void actionPerformed(ActionEvent arg0) {
				  		     			BigDecimal stock=BigDecimal.ZERO;
				  		     			
				  		     			try {
				  		     				
				  		     				
				  		     				if(textCodeMagasinier.getText().equals(""))
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer le code magasinier SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				return;
					  		     			}else if(textNom.getText().equals(""))
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer le Nom magasinier SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				return;
					  		     			}
					  		     			else if(dateChooserOperation.getDate()==null)
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer la date d'op�ration SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				return;
					  		     			}
					  		     			
					  		     			else if(textdesignation.getText().equals(""))
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer la designation SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				return;
					  		     			}
					  		     			else if(textcodemp.getText().equals("") || combomp.getSelectedIndex()==-1)
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer la Matiere premiere SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				return;
					  		     			}
					  		     			else if(comboDepot.getSelectedItem().equals(""))
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer la Depot SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				return;
					  		     			}
					  		     			else if(comboMagasin.getSelectedItem().equals(""))
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer le Magasin de Stock SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				return;
					  		     			}
					  		     			else if( textquantite.getText().equals(""))
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer la Quantit� SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				return;
					  		     			}
					  		     			else if(new BigDecimal(textquantite.getText()).compareTo(BigDecimal.ZERO)<=0)
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "la Quantit� doit etre sup�rieur � 0 SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				return;
					  		     			}
					  		     			
					  		     			
					  		     			else
					  		     			{
					  		     				CompteMagasinier compteMagasiniertmp=compteMagasinierDAO.findByCode(textCodeMagasinier.getText());
					  		     				MatierePremier matierePremier=mapMatierPremier.get(combomp.getSelectedItem());
					  		     				Magasin magasin=mapMagasin.get(comboMagasin.getSelectedItem());
					  		     				Depot depot=mapDepot.get(comboDepot.getSelectedItem());
					  		     				StockMP stockmp=stockmpDAO.findStockByMagasinMP(matierePremier.getId(), magasin.getId());
					  		     				if(stockmp.getStock().compareTo(new BigDecimal(textquantite.getText()))>=0)
					  		     				{
					  		     					stock=stockmp.getStock().subtract(new BigDecimal(textquantite.getText())    );
					  		     					stockmp.setStock(stock);
					  		     					stockmpDAO.edit(stockmp);
					  		     					DetailCompteMagasinier detailCompteMagasinier=new DetailCompteMagasinier();
						  		     				detailCompteMagasinier.setDateoperation(dateChooserOperation.getDate());
						  		     				detailCompteMagasinier.setDesignation(textdesignation.getText());
						  		     				detailCompteMagasinier.setMatierePremier(matierePremier);
						  		     				detailCompteMagasinier.setQuantite(new BigDecimal(textquantite.getText()));
						  		     				detailCompteMagasinier.setPrix(stockmp.getPrixUnitaire());
						  		     				detailCompteMagasinier.setMontant(stockmp.getPrixUnitaire().multiply( new BigDecimal(textquantite.getText()))    );
						  		     				if(compteMagasiniertmp!=null)
						  		     				{
						  		     					detailCompteMagasinier.setCompteMagasinier(compteMagasiniertmp);
						  		     				}else
						  		     				{
						  		     					detailCompteMagasinier.setCompteMagasinier(compteMagasinier);
						  		     				}
						  		     			
						  		     				
						  		     				detailCompteMagasinier.setMagasin(magasin);
						  		     				detailCompteMagasinier.setDepot(depot);
						  		     				listDetailCompteMagasinier.add(detailCompteMagasinier);
						  		     				
						  		     				
						  		     				afficher_tableDetailCompteMagasinier(listDetailCompteMagasinier);
						  		     				initialiser();
					  		     				}else
					  		     				{
					  		     					JOptionPane.showMessageDialog(null, "Stock de "+combomp.getSelectedItem() + " insuffisant","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				}
					  		     				
					  		     				
					  		     				
					  		     			}
					  		     			
				  		     				
				  		     				
											
										} catch (NumberFormatException e) {JOptionPane.showMessageDialog(null, "la Quantit� , le Prix Unitaire et le Montant doit etre num�rique  ","Erreur",JOptionPane.ERROR_MESSAGE);
											
										}
				  		     			
				  		     			
				  		     		}
				  		     	});
				  		     	btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	btnAjouter.setBounds(391, 352, 107, 24);
				  		     	btnAjouter.setIcon(imgAjouter);
				  		     	add(btnAjouter);
				  		     	
				  		     	JButton btnValider = new JButton("Valider");
				  		     	btnValider.addActionListener(new ActionListener() {
				  		     		public void actionPerformed(ActionEvent e) {
				  		     			BigDecimal MontantTotal=BigDecimal.ZERO;
				  		     			
				  		     			if(textCodeMagasinier.getText().equals(""))
				  		     			{
				  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer le code magasinier SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  		     				return;
				  		     			}else if(textNom.getText().equals(""))
				  		     			{
				  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer le Nom magasinier SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  		     				return;
				  		     			}
				  		     			else if(dateChooserOperation.getDate()==null)
				  		     			{
				  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer la date d'op�ration SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  		     				return;
				  		     			}else if(listDetailCompteMagasinier.size()==0)
				  		     			{
				  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer le detail de compte magasinier SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  		     				return;
				  		     			}
				  		     			else
				  		     			{
				  		     				int i=0;
				  		     				while(i<listDetailCompteMagasinier.size())
				  		     				{
				  		     					MontantTotal=MontantTotal.add(listDetailCompteMagasinier.get(i).getMontant())    ;
				  		     					
				  		     					i++;
				  		     				}
				  		     				
				  		     				CompteMagasinier compteMagasinierTmp=compteMagasinierDAO.findByCode(textCodeMagasinier.getText().toUpperCase());
				  		     				if(compteMagasinierTmp!=null)
				  		     				{
				  		     					MontantTotal=MontantTotal.add(compteMagasinierTmp.getMontant())   ;
				  		     					compteMagasinierTmp.setMontant(MontantTotal);
				  		     					compteMagasinierTmp.setDetailCompteMagasinier(listDetailCompteMagasinier);
				  		     					compteMagasinierDAO.edit(compteMagasinierTmp);
				  		     					JOptionPane.showMessageDialog(null, "l'ajout effectu� avec succ�e ","Succ�e",JOptionPane.INFORMATION_MESSAGE);
				  		     					initialiser();
				  		     					intialiserTabeleau();
				  		     					textCodeMagasinier.setText("");
				  		     					textNom.setText("");
				  		     					dateChooserOperation.setCalendar(null);
				  		     					textCodeMagasinier.requestDefaultFocus();
				  		     					listDetailCompteMagasinier.clear();
				  		     					
				  		     				}else
				  		     				{
				  		     					
				  		     					//compteMagasinier=new CompteMagasinier();
				  		     					compteMagasinier.setCode(textCodeMagasinier.getText().toUpperCase());
				  		     					compteMagasinier.setNom(textNom.getText());
				  		     					compteMagasinier.setMontant(MontantTotal);
				  		     					compteMagasinier.setDetailCompteMagasinier(listDetailCompteMagasinier);
				  		     					compteMagasinierDAO.add(compteMagasinier);
				  		     					JOptionPane.showMessageDialog(null, "l'ajout effectu� avec succ�e ","Succ�e",JOptionPane.INFORMATION_MESSAGE);
				  		     					initialiser();
				  		     					intialiserTabeleau();
				  		     					textCodeMagasinier.setText("");
				  		     					textNom.setText("");
				  		     					dateChooserOperation.setCalendar(null);
				  		     					textCodeMagasinier.requestDefaultFocus();
				  		     					listDetailCompteMagasinier.clear();
				  		     					
				  		     				}
				  		     				
				  		     				
				  		     			}
				  		     			
				  		     			
				  		     		}
				  		     	});
				  		     	btnValider.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	btnValider.setBounds(407, 794, 107, 24);
				  		     	btnValider.setIcon(imgAjouter);
				  		     	add(btnValider);
				  		     	
				  		     	JButton btnInitialiser = new JButton("Initialiser");
				  		     	btnInitialiser.addActionListener(new ActionListener() {
				  		     		public void actionPerformed(ActionEvent arg0) {
				  		     			
				  		     			
				  		     			initialiser();
				  		     			
				  		     		}
				  		     	});
				  		     	btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	btnInitialiser.setBounds(522, 352, 107, 24);
				  		     	btnInitialiser.setIcon(imgInit);
				  		     	add(btnInitialiser);
				  		     	
				  		     	 modifbutton = new JButton();
				  		     	 modifbutton.addActionListener(new ActionListener() {
				  		     	 	public void actionPerformed(ActionEvent e) {
				  		     	 		
                                    BigDecimal stock=BigDecimal.ZERO;

				  		     			try {
				  		     				
					  		     			if(textCodeMagasinier.getText().equals(""))
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer le code magasinier SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				return;
					  		     			}else if(textNom.getText().equals(""))
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer le Nom magasinier SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				return;
					  		     			}
					  		     			else if(dateChooserOperation.getDate()==null)
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer la date d'op�ration SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				return;
					  		     			}
					  		     			else if(textdesignation.getText().equals(""))
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer la designation SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				return;
					  		     			}
					  		     			else if(textcodemp.getText().equals("") || combomp.getSelectedIndex()==-1)
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer la Matiere premiere SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				return;
					  		     			}
					  		     			else if( textquantite.getText().equals(""))
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer la Quantit� SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				return;
					  		     			}
					  		     			else if(new BigDecimal(textquantite.getText()).compareTo(BigDecimal.ZERO) <=0)
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "la Quantit� doit etre sup�rieur � 0 SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				return;
					  		     			}
					  		     			else
					  		     			{
					  		     				MatierePremier matierePremier=mapMatierPremier.get(listDetailCompteMagasinier.get(table.getSelectedRow()).getMatierePremier().getNom());
					  		     				Magasin magasin=mapMagasin.get(listDetailCompteMagasinier.get(table.getSelectedRow()).getMagasin().getLibelle());
					  		     				StockMP stockmp=stockmpDAO.findStockByMagasinMP(matierePremier.getId(), magasin.getId());					  		     				
					  		     				DetailCompteMagasinier detailCompteMagasinier=listDetailCompteMagasinier.get(table.getSelectedRow());
					  		     				if((stockmp.getStock().add(detailCompteMagasinier.getQuantite())).compareTo(new BigDecimal(textquantite.getText())) >=0)
					  		     				{
					  		     					Depot depot=mapDepot.get(comboDepot.getSelectedItem());
					  		     					stock=stockmp.getStock().add(detailCompteMagasinier.getQuantite());
					  		     					stockmp.setStock(stock);
					  		     					stockmpDAO.edit(stockmp);
					  		     					stock=BigDecimal.ZERO;
					  		     					MatierePremier matierePremiertmp=mapMatierPremier.get(combomp.getSelectedItem());
						  		     				Magasin magasintmp=mapMagasin.get(comboMagasin.getSelectedItem());
					  		     					StockMP stockmpTmp=stockmpDAO.findStockByMagasinMP(matierePremiertmp.getId(),magasintmp.getId());
					  		     					stock=stockmpTmp.getStock().subtract(new BigDecimal(textquantite.getText()))   ;
					  		     					stockmpTmp.setStock(stock);
					  		     					stockmpDAO.edit(stockmpTmp);
					  		     					detailCompteMagasinier.setDateoperation(dateChooserOperation.getDate());
						  		     				detailCompteMagasinier.setDesignation(textdesignation.getText());
						  		     				detailCompteMagasinier.setMatierePremier(matierePremiertmp);
						  		     				detailCompteMagasinier.setMagasin(magasintmp);
						  		     				detailCompteMagasinier.setDepot(depot);
						  		     				detailCompteMagasinier.setQuantite(new BigDecimal(textquantite.getText()));
						  		     				detailCompteMagasinier.setPrix(stockmpTmp.getPrixUnitaire());
						  		     				detailCompteMagasinier.setMontant(stockmpTmp.getPrixUnitaire().multiply(new BigDecimal(textquantite.getText())));
						  		     				listDetailCompteMagasinier.set(table.getSelectedRow(), detailCompteMagasinier);
						  		     				afficher_tableDetailCompteMagasinier(listDetailCompteMagasinier);
						  		     				initialiser();
					  		     				}else
					  		     				{
					  		     					JOptionPane.showMessageDialog(null, "Stock de "+combomp.getSelectedItem() + " insuffisant","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				}
					  		     				
					  		     				
					  		     				
					  		     			}
					  		     			
				  		     				
				  		     				
											
										} catch (NumberFormatException r) {JOptionPane.showMessageDialog(null, "la Quantit� , le Prix Unitaire et le Montant doit etre num�rique  ","Erreur",JOptionPane.ERROR_MESSAGE);
											
										}
				  		     			
				  		     	 		
				  		     	 		
				  		     	 		
				  		     	 		
				  		     	 	}
				  		     	 });
				  		     	modifbutton.setEnabled(false);
				  		     	modifbutton.setBounds(1201, 490, 73, 24);
				  		     	modifbutton.setIcon(imgModifier);
				  		     	add(modifbutton);
				  		     	
				  		     	 suppbutton = new JButton();
				  		     	 suppbutton.addActionListener(new ActionListener() {
				  		     	 	public void actionPerformed(ActionEvent e) {
				  		     	 		BigDecimal stock=BigDecimal.ZERO;
				  		     	 		Magasin magasin=mapMagasin.get(listDetailCompteMagasinier.get(table.getSelectedRow()).getMagasin().getLibelle());
				  		     	 	MatierePremier matierepremiere=mapMatierPremier.get(listDetailCompteMagasinier.get(table.getSelectedRow()).getMatierePremier().getNom());
				  		     	 	StockMP stockmp=stockmpDAO.findStockByMagasinMP(matierepremiere.getId(), magasin.getId()) ; 	 
				  		     	 stock=stockmp.getStock().add(listDetailCompteMagasinier.get(table.getSelectedRow()).getQuantite());
				  		     	stockmp.setStock(stock);
				  		      stockmpDAO.edit(stockmp);
				  		  	listDetailCompteMagasinier.remove(table.getSelectedRow());
				  		     	
				  		     	 	afficher_tableDetailCompteMagasinier(listDetailCompteMagasinier);
				  		     	 	initialiser();
				  		     	 	}
				  		     	 });
				  		     	suppbutton.setEnabled(false);
				  		     	suppbutton.setBounds(1201, 537, 73, 24);
				  		     	suppbutton.setIcon(imgSupprimer);
				  		     	add(suppbutton);
	
				  		     
				  		 
	}

	
	 void initialiser()
	 {
		 textdesignation.setText("");
		 textcodemp.setText("");
		 combomp.setSelectedIndex(-1);
		 comboMagasin.setSelectedIndex(-1);
		 comboDepot.setSelectedIndex(-1);
		 textquantite.setText("");
		
		 textdesignation.requestDefaultFocus();
		 
		 btnAjouter.setEnabled(true);
		    
	     modifbutton.setEnabled(false);
		 suppbutton.setEnabled(false);
		 
	 }

void intialiserTabeleau(){
	
	modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Designation", "Matiere Premiere", "Quantit\u00E9", "Prix Unitaire", "Montant"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		   table.setModel(new DefaultTableModel(
		   	new Object[][] {
		   	},
		   	new String[] {
		   		"Designation", "Matiere Premiere", "Quantit\u00E9", "Prix Unitaire", "Montant"
		   	}
		   ));
		   table.getColumnModel().getColumn(0).setPreferredWidth(289);
		   table.getColumnModel().getColumn(1).setPreferredWidth(123);
		   table.getColumnModel().getColumn(2).setPreferredWidth(84);
		   table.getColumnModel().getColumn(3).setPreferredWidth(88);
}


void afficher_tableDetailCompteMagasinier(List<DetailCompteMagasinier> listDetailCompteMagasinier)
{
	modeleMP =new DefaultTableModel(
	     	new Object[][] {
	     	},
	     	new String[] {
	     			"Designation", "Matiere Premiere", "Quantit\u00E9", "Prix Unitaire", "Montant"
	     	}
	     ) {
	     	boolean[] columnEditables = new boolean[] {
	     			false,false,false,false,false
	     	};
	     	public boolean isCellEditable(int row, int column) {
	     		return columnEditables[column];
	     	}
	     };
	     table.setModel(modeleMP);
	int i=0;
	 
	while(i<listDetailCompteMagasinier.size())
	{	
		DetailCompteMagasinier detailCompteMagasinier=listDetailCompteMagasinier.get(i);
		MatierePremier matierepremiere=detailCompteMagasinier.getMatierePremier();
		
		String designation=detailCompteMagasinier.getDesignation();
		String mp=matierepremiere.getNom();
		BigDecimal quantite =detailCompteMagasinier.getQuantite();
		BigDecimal prixtmp=detailCompteMagasinier.getPrix();
		BigDecimal montanttmp=detailCompteMagasinier.getMontant();
		Object []ligne={designation,mp,quantite,prixtmp,montanttmp};

		modeleMP.addRow(ligne);
		i++;
	}
}
}
