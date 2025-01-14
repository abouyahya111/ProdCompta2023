package presentation.stockMP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
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
import util.JasperUtils;

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


public class ConsulterCompteMagasinier extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;
	private ImageIcon imgModifier;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgRechercher;
	private ImageIcon imgImprimer;
	
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

	
	private JDateChooser dateChooserOperation = new JDateChooser();
	private JTextField textNom;
	private JTextField textCodeMagasinier;
	private JComboBox combomp = new JComboBox();
    private	JButton btnAjouter = new JButton("Ajouter");
    private JButton modifbutton = new JButton();
    private JButton suppbutton = new JButton();
   private JComboBox comboMagasin = new JComboBox();
   private JComboBox comboDepot = new JComboBox();
   private JTextField textTotal;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	@SuppressWarnings("unchecked")
	public ConsulterCompteMagasinier() {
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
            imgImprimer = new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		     table = new JXTable();
				  		     table.addMouseListener(new MouseAdapter() {
				  		     	@Override
				  		     	public void mouseClicked(MouseEvent arg0) {
				  		     	
				  		     		
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
				  		     	scrollPane.setBounds(10, 141, 1136, 367);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		   
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(10, 112, 1136, 30);
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
				  		     	
				  		     	 dateChooserOperation = new JDateChooser();
				  		     	dateChooserOperation.setLocale(Locale.FRANCE);
				  		     	dateChooserOperation.setDateFormatString("dd/MM/yyyy");
				  		     	dateChooserOperation.setBounds(681, 11, 155, 26);
				  		     	layeredPane.add(dateChooserOperation);
				  		     	
				  		     	JLabel lblDateOpration = new JLabel("Date Op\u00E9ration :");
				  		     	lblDateOpration.setBounds(590, 11, 87, 26);
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
				  		     	
				  		     	JButton btnConsulter = new JButton("Consulter");
				  		     	btnConsulter.addActionListener(new ActionListener() {
				  		     		public void actionPerformed(ActionEvent e) {
				  		     		  DecimalFormat format = new DecimalFormat("#0.00");
				  		     		BigDecimal MontantTotaltmp ;
				  		     		BigDecimal number ; 
									BigDecimal MontantTotal = new BigDecimal("0");
				  		     			
				  		     			if(textCodeMagasinier.getText().equals(""))
				  		     			{
				  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer le code magasinier SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  		     				vidertotalettableau();
				  		     				return;
				  		     			}else if(textNom.getText().equals(""))
				  		     			{
				  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer le Nom magasinier SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  		     				vidertotalettableau();
				  		     				return;
				  		     			}
				  		     			else if(dateChooserOperation.getDate().equals(""))
				  		     			{
				  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer la date d'op�ration SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				  		     				vidertotalettableau();
				  		     				return;
				  		     			}
				  		     			else
				  		     			{
				  		     				CompteMagasinier compteMagasinier=compteMagasinierDAO.findByCode(textCodeMagasinier.getText());
				  		     				if(compteMagasinier!=null)
				  		     				{
                                                Date date=dateChooserOperation.getDate();
				  		     					listDetailCompteMagasinier =compteMagasinierDAO.findDetailCompteMagasinierByCompteMagasinierAndDate(compteMagasinier.getId(), date);
				  		     					if(listDetailCompteMagasinier.size()!=0)
				  		     					{
				  		     						int i=0;
				  		     						while(i<listDetailCompteMagasinier.size())
				  		     						{
				  		     							DetailCompteMagasinier detailcomptemagasinier=listDetailCompteMagasinier.get(i);
				  		     							number =new BigDecimal(listDetailCompteMagasinier.get(i).getMontant()+"");
				  		     							number=number.setScale(2,BigDecimal.ROUND_DOWN);
				  		     							MontantTotaltmp=number;
				  		     							detailcomptemagasinier.setMontant(MontantTotaltmp);
				  		     							listDetailCompteMagasinier.set(i, detailcomptemagasinier);
				  		     							MontantTotal=MontantTotal.add(number);
				  		     							i++;
				  		     						}
				  		     						afficher_tableDetailCompteMagasinier(listDetailCompteMagasinier);
				  		     						textTotal.setText(format.format(MontantTotal)+" "+ Constantes.POURCENTAGEDH);
				  		     						
				  		     					}
				  		     					else
				  		     					{
				  		     						JOptionPane.showMessageDialog(null, "Compte Magasinier n'a aucun details au date sp�cifie","Erreur",JOptionPane.ERROR_MESSAGE);
				  		     						vidertotalettableau();
				  		     					}

				  		     				}else
				  		     				{
				  		     					JOptionPane.showMessageDialog(null, "Compte Magasinier Introuvable ","Erreur",JOptionPane.ERROR_MESSAGE);
				  		     					vidertotalettableau();
				  		     				}
				  		     			}
				  		     			
				  		     			
				  		     		}
				  		     	});
				  		     	btnConsulter.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	btnConsulter.setBounds(884, 13, 107, 24);
				  		     	layeredPane.add(btnConsulter);
				  		     	
				  		     	JButton btnInitialiser = new JButton("Initialiser");
				  		     	btnInitialiser.addActionListener(new ActionListener() {
				  		     		public void actionPerformed(ActionEvent arg0) {
				  		     			
				  		     			initialiser();
				  		     		}
				  		     	});
				  		     	btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	btnInitialiser.setBounds(1004, 14, 107, 24);
				  		     	btnInitialiser.setIcon(imgInit);
				  		     	layeredPane.add(btnInitialiser);
				  		     	listMatierePremiere=matierePremierDAO.findAll();
				  		     	int i=0;
				  		     	while(i<listMatierePremiere.size())
				  		     	{
				  		     		MatierePremier mp=listMatierePremiere.get(i);
				  		     		combomp.addItem(mp.getNom());
				  		     		mapMatierPremier.put(mp.getNom(), mp);
				  		     		i++;
				  		     	}
				  		     	
				  		     	listDepot=depotdao.findAll();
				  		     	int k=0;
				  		     	while (k<listDepot.size())
				  		     	{
				  		     		Depot depot=listDepot.get(k);
				  		     		comboDepot.addItem(depot.getLibelle());
				  		     		mapDepot.put(depot.getLibelle(), depot);
				  		     	
				  		     		k++;
				  		     	}
				  		     	
				  		     	textTotal = new JTextField();
				  		     	textTotal.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
				  		     	textTotal.setEditable(false);
				  		     	textTotal.setColumns(10);
				  		     	textTotal.setBackground(Color.WHITE);
				  		     	textTotal.setBounds(963, 519, 183, 24);
				  		     	add(textTotal);
				  		     	
				  		     	JLabel lblMontantTotal = new JLabel("Montant Total :");
				  		     	lblMontantTotal.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	lblMontantTotal.setBounds(845, 519, 108, 24);
				  		     	add(lblMontantTotal);
				  		     	
				  		     	JButton btnConsulterCompteMagasinier = new JButton("Consulter Compte Magasinier");
				  		     	btnConsulterCompteMagasinier.addActionListener(new ActionListener() {
				  		     		public void actionPerformed(ActionEvent arg0) {
				  		     			if(listDetailCompteMagasinier.size()!=0)
				  		     			{
				  		     				if(textCodeMagasinier.getText().equals(""))
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer le code magasinier SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				vidertotalettableau();
					  		     				return;
					  		     			}else if(textNom.getText().equals(""))
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer le Nom magasinier SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				vidertotalettableau();
					  		     				return;
					  		     			}
					  		     			else if(dateChooserOperation.getDate().equals(""))
					  		     			{
					  		     				JOptionPane.showMessageDialog(null, "Veuillez entrer la date d'op�ration SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
					  		     				vidertotalettableau();
					  		     				return;
					  		     			}
					  		     			else
					  		     			{
					  		     				Map parameters = new HashMap();
					  		     				parameters.put("code", textCodeMagasinier.getText());
					  		     				parameters.put("nom", textNom.getText());
					  		     				parameters.put("date", dateChooserOperation.getDate());
					  		     				parameters.put("total", textTotal.getText());
					  		     				JasperUtils.imprimerCompteMagasinier(listDetailCompteMagasinier,parameters);
					  		     				
					  		     			}
				  		     			
				  		     				
				  		     				
				  		     			}else
				  		     			{
				  		     			 JOptionPane.showMessageDialog(null, "Il n'existe pas aucun detail pour cet magasinier dans cette p�riode!! ", "Erreur", JOptionPane.ERROR_MESSAGE); 
				  		     			}
				  		     		
				  		     			
				  		     			
				  		     		}
				  		     	});
				  		     	btnConsulterCompteMagasinier.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	btnConsulterCompteMagasinier.setBounds(468, 519, 206, 23);
				  		     	btnConsulterCompteMagasinier.setIcon(imgImprimer);
				  		     	add(btnConsulterCompteMagasinier);
	
				  		     
				  		 
	}

	
	 void initialiser()
	 {
		
		textCodeMagasinier.setText("");
		textNom.setText("");
		dateChooserOperation.setCalendar(null);
		
		textCodeMagasinier.requestDefaultFocus();
		 
	 }
	 
	 
	 void vidertotalettableau()
	 {
		 textTotal.setText("");
		 intialiserTabeleau();
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
	BigDecimal montant ; 
	  DecimalFormat format = new DecimalFormat("#0.00");
	while(i<listDetailCompteMagasinier.size())
	{	
		DetailCompteMagasinier detailCompteMagasinier=listDetailCompteMagasinier.get(i);
		MatierePremier matierepremiere=detailCompteMagasinier.getMatierePremier();
		
		String designation=detailCompteMagasinier.getDesignation();
		String mp=matierepremiere.getNom();
		BigDecimal quantite =detailCompteMagasinier.getQuantite();
		BigDecimal prix=detailCompteMagasinier.getPrix();
		 montant=new BigDecimal(detailCompteMagasinier.getMontant()+"");
		 montant=montant.setScale(2,BigDecimal.ROUND_DOWN);
		Object []ligne={designation,mp,quantite,format.format(prix),montant};

		modeleMP.addRow(ligne);
		i++;
	}
}
}
