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


public class BonProductionParAnnee extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleProd;
	private DefaultTableModel	 modeleMP;
	private DefaultTableModel	 modeleEmployeGen;
	private DefaultTableModel	 modeleEmployeProd;
	private DefaultTableModel	 modeleEmployeEmballage;
	private JXTable table;
	

	private Map< String, Boolean> mapBonProduction = new HashMap<>();
	 List<CoutMP> listCoutMP=new ArrayList<CoutMP>();
	 List<CoutMP> listCoutMPTmp=new ArrayList<CoutMP>();
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
	public BonProductionParAnnee() {
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
				  		     	 lblDateFin.setBounds(200, 10, 51, 24);
				  		     	 layeredPane.add(lblDateFin);
				  		     	 lblDateFin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnAfficherStock = new JButton();
		btnAfficherStock.setIcon(imgRechercher);
		btnAfficherStock.setBounds(958, 11, 31, 31);
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
		
		 
		dateDebutChooser.setBounds(37, 11, 153, 24);
		layeredPane.add(dateDebutChooser);
		
		
		dateFinChooser.setBounds(233, 11, 167, 24);
		layeredPane.add(dateFinChooser);
		
		JLabel lblDepot = new JLabel("Depot :");
		lblDepot.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDepot.setBounds(423, 11, 51, 24);
		layeredPane.add(lblDepot);
		
		 combodepot = new JComboBox();
		combodepot.setBounds(471, 12, 149, 24);
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
		  button.setBounds(1011, 12, 106, 30);
		  layeredPane.add(button);
		  
		  JLabel lblMagasinMp = new JLabel("Magasin MP :");
		  lblMagasinMp.setFont(new Font("Tahoma", Font.PLAIN, 12));
		  lblMagasinMp.setBounds(630, 11, 72, 24);
		  layeredPane.add(lblMagasinMp);
		  
		    MagasinMP = new JComboBox();
		  MagasinMP.setSelectedIndex(-1);
		  MagasinMP.setBounds(710, 12, 199, 24);
		  layeredPane.add(MagasinMP);
		  
		  JButton btnImprimerDetailOf = new JButton("Imprimer Bon OF");
		  btnImprimerDetailOf.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		try {
		  			
		  		Magasin magasin=mapMagasin.get(MagasinMP.getSelectedItem())	;
		  		BigDecimal quantiteFabrique=BigDecimal.ZERO;
		  		if(magasin==null)
		  		{
		  			JOptionPane.showMessageDialog(null, "Veuillez Selectionner Le Magasin SVP !!!");
		  			
		  		return;
		  		}
		  		Client client=clientDAO.findClientByCodeClient(magasin.getCodeMachine());
		  			mapBonProduction.clear();
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
								quantiteFabrique=new BigDecimal(object[2].toString());
								
								coutMPDAO.ViderSession();
								
								listCoutMPTmp.clear();
								listCoutMP.clear();
								
								listCoutMP=coutMPDAO.listeCoutMPByArticleByMagasin(article, magasin);
								
								boolean existe=false;
								for(int t=0;t<listCoutMP.size();t++)
								{
									existe=false;
									for(int r=0;r<listCoutMPTmp.size();r++)
									{
										
										CoutMP coutMP=listCoutMPTmp.get(r);
										if(listCoutMP.get(t).getMatierePremier().getId()==coutMP.getMatierePremier().getId())
										{
											
											
											existe=true;
											
											coutMP.setQuantConsomme(coutMP.getQuantConsomme().add(listCoutMP.get(t).getQuantConsomme()));
											coutMP.setQuantiteOffre(coutMP.getQuantiteOffre().add(listCoutMP.get(t).getQuantiteOffre()));
											
											coutMP.setQuantDechet(coutMP.getQuantDechet().add(listCoutMP.get(t).getQuantDechet()));
											listCoutMPTmp.set(r, coutMP);
											
										}
									}
									
									if(existe==false)
									{
										listCoutMPTmp.add(listCoutMP.get(t));
									}
									
									
									
									
									
								}
								
								
								
								
								if(article.getCentreCout5()!=null)
								{
									
									if(article.getCentreCout5().compareTo(BigDecimal.ONE)==0)
									{
										
										for(int k=0;k<listCoutMPTmp.size();k++)
										{
											
											if(listCoutMPTmp.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
											{
												
												quantiteFabrique=quantiteFabrique.add(listCoutMPTmp.get(k).getQuantiteOffre());
												 
												
												
											}
											
											
											
										}
										
										
										
										
									}
									
									
								}
								
								
								
								
								
								parameters.put("quantiteFabriquee", quantiteFabrique);
								parameters.put("DateProd", dateDebutChooser.getDate());
								parameters.put("listcoutMP", listCoutMPTmp);
								
								if(client!=null)
								{
									parameters.put("societe", client.getNom());
								}else
								{
									parameters.put("societe", "");
								}
								
								for(int j=0;j<listCoutMPTmp.size();j++)
								{
									
									if(listCoutMPTmp.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
									{
										//listCoutMPTmp.get(j).setQuantConsomme(listCoutMPTmp.get(j).getQuantiteOffre());	
										
									}else if(listCoutMPTmp.get(j).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(SOUS_CATEGORIE_MATIERE_PREMIERE_CARTON))
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
												 
												 
										listCoutMPTmp.get(j).setQuantConsomme(listCoutMPTmp.get(j).getQuantConsomme().setScale(0, BigDecimal.ROUND_DOWN));
												 
							        	/*	 }
											 
											 
											 
										 }*/
										
										
									}
									
									
								}
								
							 if(checkSansDechet.isSelected()==true)
							 {
								 JasperUtils.imprimerBonProductionParAnneeSansDechet(listCoutMPTmp, parameters);	
							 }else
							 {
								 JasperUtils.imprimerBonProductionParAnnee(listCoutMPTmp, parameters);	
							 }
								
								
								
								
								if(article.getCentreCout5()!=null)
								{
									
									if(article.getCentreCout5().compareTo(BigDecimal.ONE)==0)
									{
										
										for(int k=0;k<listCoutMPTmp.size();k++)
										{
											
											if(listCoutMPTmp.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
											{
												
												
												listCoutMPTmp.get(k).setQuantConsomme(listCoutMPTmp.get(k).getQuantConsomme().subtract (listCoutMPTmp.get(k).getQuantiteOffre()));
												
												
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
