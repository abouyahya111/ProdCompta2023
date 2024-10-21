package Production;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import main1.AuthentificationView;
import main1.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.DateUtils;
import util.JasperUtils;
import util.Utils;

import com.sun.org.apache.bcel.internal.generic.ATHROW;
import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.CoutMPDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.CoutMPDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.ProductionDAO;
import dao.entity.Articles;
import dao.entity.Client;
import dao.entity.CompteStockMP;
import dao.entity.CoutMP;
import dao.entity.CoutMPEnVracConsommePourBonProductionComptaPourcentageEnvrac;
import dao.entity.Depot;
import dao.entity.DetailProdGen;
import dao.entity.DetailProduction;
import dao.entity.DetailResponsableProd;
import dao.entity.Employe;
import dao.entity.FicheEmploye;
import dao.entity.Magasin;
import dao.entity.Production;
import dao.entity.Utilisateur;

import java.awt.Component;

import javax.swing.JComboBox;

import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.ScrollPane;
import javax.swing.JCheckBox;


public class BonProductionParPourcentageEnVrac extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleProd;
	private DefaultTableModel	 modeleMP;
	private DefaultTableModel	 modeleEmployeGen;
	private DefaultTableModel	 modeleEmployeProd;
	private DefaultTableModel	 modeleEmployeEmballage;
	private JXTable table;
	

	private Map< String, Boolean> mapBonProduction = new HashMap<>();
	 List<CoutMP> listCoutMP=new ArrayList<CoutMP>();
	 List<CoutMPEnVracConsommePourBonProductionComptaPourcentageEnvrac> listCoutEnVracConsomme=new ArrayList<CoutMPEnVracConsommePourBonProductionComptaPourcentageEnvrac>();
	 List<CoutMP> listCoutMPTmpAfficher=new ArrayList<CoutMP>();
	 List<CoutMP> listCoutMPCarton=new ArrayList<CoutMP>();
	 List<DetailResponsableProd> listEmployeGesnerique=new ArrayList<DetailResponsableProd>();
	 List<DetailProdGen> listEmployeEmballage=new ArrayList<DetailProdGen>();
	 List<DetailProduction> listEmployeProduction=new ArrayList<DetailProduction>();
	 List<Magasin> listMagasinMP=new ArrayList<Magasin>();
	private ImageIcon imgValider;
	private ImageIcon imgInit;
	private ImageIcon imgImprimer;
	private ImageIcon imgRechercher;
	private JDateChooser dateDebutChooser = new JDateChooser();
	private JDateChooser dateFinChooser = new JDateChooser();
	JComboBox combodepot = new JComboBox();
	private Map< Integer, String> mapAvance= new HashMap<>();
	private Map< String, BigDecimal> mapParametre = new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private List<Depot> listDepot=new ArrayList<Depot>();
	private List<Object[]> listProduction= new ArrayList<Object[]>();
	private List<Production> listProductionTmp= new ArrayList<Production>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Utilisateur utilisateur;
	private ProductionDAO productionDAO;
	private DepotDAO depotdao;
	JCheckBox checkSansDechet = new JCheckBox("Imprimer Sans Dechet");
	JComboBox MagasinMP = new JComboBox();
	CoutMPDAO coutMPDAO;
	private ArticlesDAO ArticleDAO;
	ClientDAO clientDAO;
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public BonProductionParPourcentageEnVrac() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1579, 1062);
        try{
        	
        	 utilisateur=AuthentificationView.utilisateur;
        	productionDAO=new ProductionDAOImpl();
        	depotdao=new DepotDAOImpl();
        	listMagasinMP=depotdao.listeMagasinByTypeMagasin(Constantes.MAGASIN_CODE_TYPE_MP);
        	coutMPDAO=new CoutMPDAOImpl();
        	ArticleDAO= new ArticlesDAOImpl();
        	clientDAO=new ClientDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion √† la base de donn√©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        
        try{
        	
        	imgRechercher= new ImageIcon(this.getClass().getResource("/img/rechercher.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgImprimer=new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
            imgValider=new ImageIcon(this.getClass().getResource("/img/valider.png"));
          } catch (Exception exp){exp.printStackTrace();}
		
        mapParametre=Utils.listeParametre();	 	
	
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
          } catch (Exception exp){exp.printStackTrace();}
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
				  		     
				  		   modeleProd =new DefaultTableModel(
					  		     	new Object[][] {
					  		     	},
					  		     	new String[] {
					  		     			"Code Articlet","Article","Total Production","Imprimer"
					  		     	}
					  		     ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     			false,false,false,false 
					  		     	};
					  		     	public boolean isCellEditable(int row, int column) {
					  		     		return columnEditables[column];
					  		     	}
					  		     };
					  		     
					  		     
					  		     
					  		 table.setModel(modeleProd); 
					  		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
					         table.getColumnModel().getColumn(1).setPreferredWidth(160);
					         table.getColumnModel().getColumn(2).setPreferredWidth(60);
					      //   intialiserTableau2();
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 65, 1286, 338);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	modeleProd =new DefaultTableModel(
				  			     	new Object[][] {
				  			     	},
				  			     	new String[] {
				  			     			"Num OF", "Date","Depot","Article","Statut", "Type production","Imprimer"
				  			     	}
				  			     ) {
				  		  		boolean[] columnEditables = new boolean[] {
				  						false,false,false,false,false,false,true
				  				};
				  				Class[] columnTypes = new Class[] {
				  						String.class,Date.class,String.class,String.class,String.class,String.class, Boolean.class
				  					};
				  				  public Class getColumnClass(int columnIndex) {
				  						return columnTypes[columnIndex];
				  					}
				  				public boolean isCellEditable(int row, int column) {
				  					return columnEditables[column];
				  				}
				  			};
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("");
				  		     	titledSeparator.setBounds(9, 49, 569, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 11, 1271, 54);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblDateDebut = new JLabel("Du :");
				  		     	lblDateDebut.setBounds(10, 11, 31, 24);
				  		     	layeredPane.add(lblDateDebut);
				  		     	lblDateDebut.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	 
				  		     	 JLabel lblDateFin = new JLabel("Au :");
				  		     	 lblDateFin.setBounds(197, 10, 51, 24);
				  		     	 layeredPane.add(lblDateFin);
				  		     	 lblDateFin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnAfficherStock = new JButton();
		btnAfficherStock.setIcon(imgRechercher);
		btnAfficherStock.setBounds(937, 11, 31, 31);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dateDebut=((JTextField)dateDebutChooser.getDateEditor().getUiComponent()).getText();
				String dateFin=((JTextField)dateFinChooser.getDateEditor().getUiComponent()).getText();
				Magasin magasin=mapMagasin.get(MagasinMP.getSelectedItem());
				if(magasin==null)
				{
					JOptionPane.showMessageDialog(null, "Il faut choisir le Magasin SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}
			 if(dateDebut.equals(""))	{
				JOptionPane.showMessageDialog(null, "Il faut choisir Date DÈbut", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else if(dateFin.equals("")){
				JOptionPane.showMessageDialog(null, "Il faut choisir Date Fin", "Erreur", JOptionPane.ERROR_MESSAGE);
				
			}else if(combodepot.getSelectedIndex()==-1)
			{
				JOptionPane.showMessageDialog(null, "Il faut choisir le Depot SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
			}else
			
			{
				
				Depot depot=mapDepot.get(combodepot.getSelectedItem());
          listProduction.clear();
     listProduction=productionDAO.listeArticleFabriqueParDepotParMagasin(dateDebutChooser.getDate(), dateFinChooser.getDate(),Constantes.ETAT_OF_TERMINER,depot,magasin );
afficher_tableProd(listProduction);
				
				
				
			}
		  }
		});
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		 
		dateDebutChooser.setBounds(37, 11, 150, 24);
		layeredPane.add(dateDebutChooser);
		
		
		dateFinChooser.setBounds(230, 11, 170, 24);
		layeredPane.add(dateFinChooser);
		
		JLabel lblDepot = new JLabel("Depot :");
		lblDepot.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDepot.setBounds(424, 11, 51, 24);
		layeredPane.add(lblDepot);
		
		 combodepot = new JComboBox();
		combodepot.setBounds(472, 12, 149, 24);
		layeredPane.add(combodepot);
		
		
		  if(utilisateur.getLogin().equals("admin"))
		  {
			  listDepot=depotdao.findAll();
			  int k=0;
		     	 combodepot.addItem("");
		     	while (k<listDepot.size())
		     	{
		     		Depot depot=listDepot.get(k);
		     		combodepot.addItem(depot.getLibelle());
		     		mapDepot.put(depot.getLibelle(), depot);
		     		k++;
		     		
		     	}
		      
		  }else{
			  Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
			  if(depot!=null)
			  {
				  combodepot.addItem(depot.getLibelle());
				
		     		mapDepot.put(depot.getLibelle(), depot);
			  }
		  }
		 
		 
		  
		  combodepot.setSelectedIndex(-1);
		  
		  JButton button = new JButton("Initialiser");
		  button.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		
		  		dateDebutChooser.setCalendar(null);
		  		dateFinChooser.setCalendar(null);
		  		 
		  	
		  		
		  		
		  	}
		  });
		  button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  button.setBounds(990, 12, 106, 30);
		  layeredPane.add(button);
		  
		  JLabel lblMagasinMp = new JLabel("Magasin MP :");
		  lblMagasinMp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  lblMagasinMp.setBounds(631, 11, 72, 24);
		  layeredPane.add(lblMagasinMp);
		  
		    MagasinMP = new JComboBox();
		  MagasinMP.setSelectedIndex(-1);
		  MagasinMP.setBounds(711, 12, 199, 24);
		  layeredPane.add(MagasinMP);
		  
		  JButton btnImprimerDetailOf = new JButton("Imprimer Bon OF");
		  btnImprimerDetailOf.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		try {
		  			
		  		Magasin magasin=mapMagasin.get(MagasinMP.getSelectedItem())	;
		  		Depot depot=mapDepot.get(combodepot.getSelectedItem());
		  		BigDecimal quantiteFabrique=BigDecimal.ZERO;
		  		if(magasin==null)
		  		{
		  			JOptionPane.showMessageDialog(null, "Veuillez Selectionner Le Magasin SVP !!!");
		  			
		  		return;
		  		}
		  			
		  		Client client=clientDAO.findClientByCodeClient(magasin.getCodeMachine());
		  			mapBonProduction.clear();
		  			listCoutMPCarton.clear();
		    		if(!remplirmapBonProduction())	{
						JOptionPane.showMessageDialog(null, "Il faut remplir les Productions ‡ imprimer", "Erreur", JOptionPane.ERROR_MESSAGE);
					} else {
						
						
				 		Map parameters = new HashMap();
						for(int i=0;i<listProduction.size() ;i++)
						{
							 Object[] object=listProduction.get(i);	
								if(object[0]!=null)
								{
									Articles article=ArticleDAO.findByCode(object[0].toString())	;
									 
									
								
									
								 
							 
							if(mapBonProduction.containsKey(article.getCodeArticle()))
							{
								quantiteFabrique=BigDecimal.ZERO;
								
								
								coutMPDAO.ViderSession();
								listCoutMPTmpAfficher.clear();
								listCoutEnVracConsomme.clear();
								listCoutMP.clear();
								
								listCoutMP=coutMPDAO.listeCoutMPEnVracByArticleByMagasin (article, magasin);
								
								boolean existe=false;
								for(int t=0;t<listCoutMP.size();t++)
								{
									existe=false;
									for(int r=0;r<listCoutEnVracConsomme.size();r++)
									{
										
										CoutMPEnVracConsommePourBonProductionComptaPourcentageEnvrac coutMP=listCoutEnVracConsomme.get(r);
										if(listCoutMP.get(t).getMatierePremier().getId()==coutMP.getMatierePremier().getId())
										{
											
											
											existe=true;
											
											coutMP.setQuantConsomme(coutMP.getQuantConsomme().add(listCoutMP.get(t).getQuantConsomme()));
											coutMP.setQuantiteOffre(coutMP.getQuantiteOffre().add(listCoutMP.get(t).getQuantiteOffre()));
											
											coutMP.setQuantDechet(coutMP.getQuantDechet().add(listCoutMP.get(t).getQuantDechet()));
											listCoutEnVracConsomme.set(r, coutMP);
											
										}
									}
									
									if(existe==false)
									{
										CoutMPEnVracConsommePourBonProductionComptaPourcentageEnvrac coutMP=new CoutMPEnVracConsommePourBonProductionComptaPourcentageEnvrac();
										coutMP.setMatierePremier(listCoutMP.get(t).getMatierePremier());
										coutMP.setQuantConsomme(listCoutMP.get(t).getQuantConsomme());
										coutMP.setQuantiteOffre(listCoutMP.get(t).getQuantiteOffre());
										coutMP.setQuantDechet(listCoutMP.get(t).getQuantDechet());
										listCoutEnVracConsomme.add(coutMP);
										
										
										
										
									}
									
									
									
									
									
								}
								
								
								
						listProductionTmp=productionDAO.listeProductionParArticleParDepotParMagasin(dateDebutChooser.getDate(), dateFinChooser.getDate(),Constantes.ETAT_OF_TERMINER,depot,magasin , article);
								
							for(int p=0;p<listProductionTmp.size();p++)
							{
								listCoutMPCarton.clear();
								
								
								
								quantiteFabrique=BigDecimal.ZERO;
								Production production=listProductionTmp.get(p);
								quantiteFabrique=production.getQuantiteReel();
								
								listCoutMPCarton=coutMPDAO.listeCoutMPByProduction(production);
								
								if(production.getNumOF().equals("T_1308_14_3275"))
								{
									System.out.println("T_1308_14_3275");
								}
								
								BigDecimal quantiteCarton=BigDecimal.ZERO;
								for(int j=0;j<listCoutMPCarton.size();j++)
								{
									
									if(listCoutMPCarton.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON))
									{
										
										quantiteCarton=listCoutMPCarton.get(j).getQuantConsomme();
									}
								}
								
								listCoutMPTmpAfficher.clear();
								
								listCoutMPTmpAfficher=coutMPDAO.listeCoutMPEmballageByProductionByMagasin(production, magasin);
								
								
								for(int y=0;y<listCoutEnVracConsomme.size();y++)
								{
									
									CoutMPEnVracConsommePourBonProductionComptaPourcentageEnvrac coutMP=listCoutEnVracConsomme.get(y);
									
									CoutMP coutMPTmp=new CoutMP();
									coutMPTmp.setMatierePremier(coutMP.getMatierePremier());
									coutMPTmp.setQuantConsomme(coutMP.getQuantConsomme());
									coutMPTmp.setQuantiteOffre(coutMP.getQuantiteOffre());
									coutMPTmp.setQuantDechet(coutMP.getQuantDechet());
									coutMPTmp.setProdcutionCM(production);
									listCoutMPTmpAfficher.add(coutMPTmp);
								}	
								BigDecimal quantitetTotalEnvracConsomme=BigDecimal.ZERO;
								BigDecimal quantitetTotalEnvracDechet=BigDecimal.ZERO;
								BigDecimal quantitetTotalEnvracOffre=BigDecimal.ZERO;
								int position=-1;
							for(int y=0;y<listCoutMPTmpAfficher.size();y++)
							{
								
								CoutMP coutMP=listCoutMPTmpAfficher.get(y);
								
								if(listCoutMPTmpAfficher.get(y).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
								{
									
									coutMP.setQuantConsomme(coutMP.getQuantConsomme().divide(new BigDecimal(object[2].toString()), 6, RoundingMode.HALF_UP).multiply(production.getQuantiteReel()));
									coutMP.setQuantDechet(coutMP.getQuantDechet().divide(new BigDecimal(object[2].toString()), 6, RoundingMode.HALF_UP).multiply(production.getQuantiteReel()));
									coutMP.setQuantiteOffre(coutMP.getQuantiteOffre().divide(new BigDecimal(object[2].toString()), 6, RoundingMode.HALF_UP).multiply(production.getQuantiteReel()));
									
									BigDecimal result=coutMP.getQuantConsomme().multiply(new BigDecimal(1000)) .divide(article.getCentreCout1(), 6, RoundingMode.FLOOR);
									coutMP.setQuantConsomme(result.setScale(0, RoundingMode.FLOOR).multiply (article.getCentreCout1()).divide(new BigDecimal(1000), 6, RoundingMode.FLOOR));
									quantitetTotalEnvracConsomme=quantitetTotalEnvracConsomme.add(coutMP.getQuantConsomme());
									
									
									BigDecimal resultOffre=coutMP.getQuantiteOffre().multiply(new BigDecimal(1000)) .divide(article.getCentreCout1(), 6, RoundingMode.FLOOR);
									coutMP.setQuantiteOffre(resultOffre.setScale(0, RoundingMode.FLOOR).multiply (article.getCentreCout1()).divide(new BigDecimal(1000), 6, RoundingMode.FLOOR));
									quantitetTotalEnvracOffre= quantitetTotalEnvracOffre.add(coutMP.getQuantiteOffre());
									
									
									position=y;
								}
								
								listCoutMPTmpAfficher.set(y, coutMP);
								
								
							}
							
							
							if(quantitetTotalEnvracConsomme.compareTo(production.getQuantiteReel())!=0)
							{
								BigDecimal restQuantiteConsommerEnvrac=BigDecimal.ZERO;
								
								restQuantiteConsommerEnvrac=production.getQuantiteReel().subtract(quantitetTotalEnvracConsomme);
								
								for(int y=0;y<listCoutMPTmpAfficher.size();y++)
								{
									
									CoutMP coutMP=listCoutMPTmpAfficher.get(y);
									
									if(listCoutMPTmpAfficher.get(y).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
									{
										if(position==y)
										{
											coutMP.setQuantConsomme(coutMP.getQuantConsomme().add(restQuantiteConsommerEnvrac));
											 
											 
										}
									
									}
									
									listCoutMPTmpAfficher.set(y, coutMP);
									
									
								}	
								
								
								
							}
							 
								
								
								BigDecimal quantiteOffre=quantiteCarton.multiply(article.getCentreCout1().divide(new BigDecimal(1000), 6, RoundingMode.HALF_UP));
							
							 
							
							if(quantitetTotalEnvracOffre.compareTo(quantiteOffre)!=0)
							{
								BigDecimal restQuantiteOffreEnvrac=BigDecimal.ZERO;
								
								restQuantiteOffreEnvrac=quantiteOffre.subtract(quantitetTotalEnvracOffre);
								
								for(int y=0;y<listCoutMPTmpAfficher.size();y++)
								{
									
									CoutMP coutMP=listCoutMPTmpAfficher.get(y);
									
									if(listCoutMPTmpAfficher.get(y).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
									{
										if(position==y)
										{
											coutMP.setQuantiteOffre (coutMP.getQuantiteOffre().add(restQuantiteOffreEnvrac));
											 
											 
										}
									
									}
									
									listCoutMPTmpAfficher.set(y, coutMP);
									
									
								}	
								
								
								
							}
							

							if(article.getCentreCout5()!=null)
							{
								
								if(article.getCentreCout5().compareTo(BigDecimal.ONE)==0)
								{
									
									for(int k=0;k<listCoutMPTmpAfficher.size();k++)
									{
										
										if(listCoutMPTmpAfficher.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
										{
											quantiteFabrique=quantiteFabrique.add(listCoutMPTmpAfficher.get(k).getQuantiteOffre());
											
											//listCoutMPTmpAfficher.get(k).setQuantConsomme(listCoutMPTmpAfficher.get(k).getQuantConsomme().add(listCoutMPTmpAfficher.get(k).getQuantiteOffre()));
											
											listCoutMPTmpAfficher.get(k).setQuantConsomme(listCoutMPTmpAfficher.get(k).getQuantConsomme());
											
											
										}
										 	
									}
									 	
								}
								
								
							}
							 
							parameters.put("quantiteFabriquee", quantiteFabrique);
							parameters.put("DateProd", production.getDate());
							parameters.put("listcoutMP", listCoutMPTmpAfficher);
							if(client!=null)
							{
								parameters.put("societe", client.getNom());
							}else
							{
								parameters.put("societe", "");
							}
							
							for(int j=0;j<listCoutMPTmpAfficher.size();j++)
							{
								
								if(listCoutMPTmpAfficher.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
								{
									//listCoutMPTmpAfficher.get(j).setQuantConsomme(listCoutMPTmpAfficher.get(j).getQuantiteOffre());	
									
								}else if(listCoutMPTmpAfficher.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON))
								{
									/*
									 * BigDecimal
									 * rest=production.getListCoutMP().get(j).getQuantConsomme().subtract(production
									 * .getListCoutMP().get(j).getQuantConsomme().setScale(0,
									 * RoundingMode.FLOOR)).movePointRight(production.getListCoutMP().get(j).
									 * getQuantConsomme().scale()); if(!rest.equals(BigDecimal.ZERO)) {
									 * 
									 * if(!production.getListCoutMP().get(j).getQuantConsomme().remainder(BigDecimal
									 * .ONE).setScale(6, RoundingMode.FLOOR).equals(BigDecimal.ZERO.setScale(6,
									 * RoundingMode.FLOOR))) {
									 */
											 
											 
									listCoutMPTmpAfficher.get(j).setQuantConsomme(listCoutMPTmpAfficher.get(j).getQuantConsomme().setScale(0, BigDecimal.ROUND_DOWN));
											 
						        	/*	 }
										 
										 
										 
									 }*/
									
									
								}
								
								
							}
							
							
								
							 if(checkSansDechet.isSelected()==true)
							 {
								 JasperUtils.imprimerBonProductionPourcentageEnVracConsommeSansDechet(listCoutMPTmpAfficher, parameters);	
							 }else
							 {
								 JasperUtils.imprimerBonProductionPourcentageEnVracConsomme(listCoutMPTmpAfficher, parameters);	
							 }
								
								
								
								
								if(article.getCentreCout5()!=null)
								{
									
									if(article.getCentreCout5().compareTo(BigDecimal.ONE)==0)
									{
										
										for(int k=0;k<listCoutMPTmpAfficher.size();k++)
										{
											
											if(listCoutMPTmpAfficher.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
											{
												
												
												listCoutMPTmpAfficher.get(k).setQuantConsomme(listCoutMPTmpAfficher.get(k).getQuantConsomme().subtract (listCoutMPTmpAfficher.get(k).getQuantiteOffre()));
												
												
											}
											
											
										}
										
										
										
										
									}
									
									
								}
								
							}
								
								
								
								
								
								
								
								
								
						
								
				
							}
						}
						}
						
						
					}
		  			
		  			

			 
					
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
					
				}
		  		
		  			
		  	}
		  });
		  btnImprimerDetailOf.setBounds(411, 437, 174, 24);
		  add(btnImprimerDetailOf);
		  
		    checkSansDechet = new JCheckBox("Imprimer Sans Dechet");
		  checkSansDechet.setFont(new Font("Tahoma", Font.BOLD, 11));
		  checkSansDechet.setBounds(617, 438, 174, 23);
		  add(checkSansDechet);
		
		MagasinMP.addItem("");
	
	for(int i=0;i<listMagasinMP.size();i++)
	{
		MagasinMP.addItem(listMagasinMP.get(i).getLibelle());
		mapMagasin.put(listMagasinMP.get(i).getLibelle(), listMagasinMP.get(i));
		
	}
				  		 
	}
	
	boolean remplirmapBonProduction(){
		boolean trouve=false;
		int i=0;
				
		for(int j=0;j<table.getRowCount();j++){
			
			boolean regle=(boolean) table.getValueAt(j, 3);
			if(regle==true ){
				
				mapBonProduction.put(String.valueOf(table.getValueAt(j, 0).toString()), Boolean.valueOf(table.getValueAt(j, 3).toString()));
				i++;
				trouve=true;
			}
			
		}
		return trouve;
	}
	
	
	
void afficher_tableProd(List<Object[]> listProduction)
	{
		intialiserTableau();
		 
		
			for (int i=0;i<listProduction.size();i++)
			{	
				
				 Object[] object=listProduction.get(i);	
				if(object[0]!=null)
				{
					Articles article=ArticleDAO.findByCode(object[0].toString())	;
					if(article!=null)
					{
						Object []ligne={article.getCodeArticle(),article.getLiblle(),new BigDecimal(object[2].toString()),false};

						modeleProd.addRow( ligne);
					}
					
				
					
				}
				 
				
				
				
			
			}
			
		
	}



void intialiserTableau(){
	modeleProd =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code Article","Article","Total Production","Imprimer"
		     	}
		     ) {
		boolean[] columnEditables = new boolean[] {
				false,false,false,true
		};
		Class[] columnTypes = new Class[] {
				String.class,String.class,BigDecimal.class, Boolean.class
			};
		  public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}
	};
		     
		 table.setModel(modeleProd); 
		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
      table.getColumnModel().getColumn(1).setPreferredWidth(160);
      table.getColumnModel().getColumn(2).setPreferredWidth(60);
      table.getColumnModel().getColumn(3).setPreferredWidth(60);
 
}
}
