package Referentiel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import main1.AuthentificationView;
import main1.ProdLauncher;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;

import Production.MatierePremiere;
import util.Constantes;
import util.ConverterNumberToWords;
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.DetailFacturePFDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.CompteClientDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.entity.Articles;
import dao.entity.CategorieMp;
import dao.entity.Client;
import dao.entity.CompteClient;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.DetailFacturePF;
import dao.entity.EtatVenteParArticle;
import dao.entity.EtatVenteParFamilleArticle;
import dao.entity.FacturePF;
import dao.entity.FamilleArticlePF;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.SousFamilleArticlePF;
import dao.entity.SubCategorieMp;
import dao.entity.Utilisateur;

import javax.swing.border.EtchedBorder;

import java.awt.GridBagLayout;

import javax.swing.JScrollPane;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.toedter.calendar.JDateChooser;

import java.util.Locale;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class EtatVenteFamilleArticle extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleCompte;
	private DefaultTableModel	 modeledetail;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgSupprimer;
	private ImageIcon imgRechercher;
	private ImageIcon imgImprimer;
	
	
	private  DetailFacturePFDAO detailfacturedao;
	
	
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private List<DetailFacturePF> listDetailFacture =new ArrayList<DetailFacturePF>();
	private List<DetailFacturePF> listDetailFactureTmp =new ArrayList<DetailFacturePF>();
	private List<EtatVenteParFamilleArticle> listEtatVente =new ArrayList<EtatVenteParFamilleArticle>();
	private JScrollPane scrollPane;
	private JXTable table;
	private Utilisateur utilisateur;
	private JLabel lblDateDebut;
	private JDateChooser dateChooserdebut;
	private JLabel lblDateFin;
	private JDateChooser dateChooserfin;
	private JButton btnImprimer;
	private JLabel lblEtatDeVente;
	private JButton button_1;
	String message="";
	private JLabel label;
	private JComboBox combodepot;
	private JLabel label_1;
	private JComboBox combomagasin;
	private DepotDAO depotdao;
	private FamilleArticlesPFDAO familleArticlesDAO;
	 FamilleArticlePF FamilleOffre;
	 private JButton btnExporterExcel;
	 private ImageIcon imgExcel;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public EtatVenteFamilleArticle() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

        setBounds(0, 0, 1284, 882);
       
        try{
        	   utilisateur=AuthentificationView.utilisateur;
        	   detailfacturedao=new DetailFacturePFDAOImpl();
        	
        	
        	
        	
      

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion � la base de donn�es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
	
        try{
        	 imgExcel=new ImageIcon(this.getClass().getResource("/img/excel.png"));
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp.png"));
            imgRechercher= new ImageIcon(this.getClass().getResource("/img/rechercher.png"));
            imgImprimer=new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
            depotdao=ProdLauncher.depotDAO;
        	familleArticlesDAO=new FamilleArticlesPFDAOImpl();
            utilisateur=AuthentificationView.utilisateur;
        	FamilleOffre=familleArticlesDAO.findByLibelle(FAMILLE_CADEAU);
          } catch (Exception exp){exp.printStackTrace();}
        
        
        
	    
	      	
	     final Utilisateur utilCreation=AuthentificationView.utilisateur;
				  		   		
				  		   			
				  		   		
				  		   			
				  		   			scrollPane = new JScrollPane((Component) null);
				  		   			scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			scrollPane.setBounds(36, 269, 1152, 378);
				  		   			add(scrollPane);
				  		   			
				  		   			table = new JXTable();
				  		   			table.addMouseListener(new MouseAdapter() {
				  		   				@Override
				  		   				public void mouseClicked(MouseEvent arg0) {}
				  		   			});
				  		   			scrollPane.setViewportView(table);
				  		   			table.setModel(new DefaultTableModel(
				  		   				new Object[][] {
				  		   				},
				  		   				new String[] {
				  		   					"Famille Article","Sous Famille Article", "Total Vente", "Prix Moyen Vente", "Montant HT"
				  		   				}
				  		   			));
				  		   			table.getColumnModel().getColumn(0).setPreferredWidth(221);
				  		   			table.getColumnModel().getColumn(1).setPreferredWidth(120);
				  		   			table.getColumnModel().getColumn(2).setPreferredWidth(123);
				  		   		table.getColumnModel().getColumn(3).setPreferredWidth(123);
				  		   			table.setFillsViewportHeight(true);
				  		   			
				  		   			btnImprimer = new JButton("Imprimer");
				  		   			btnImprimer.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent arg0) {
				  		   					
				  		   					if(listEtatVente.size()!=0)
				  		   					{
				  		   						
				  		   				
				  		   				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
							  		  	String datedebut=dateFormat.format(dateChooserdebut.getDate());
							  		 	String dateFin=dateFormat.format(dateChooserfin.getDate());
							  		  
										
							  		    
										Map parameters = new HashMap();
										parameters.put("datedebut", datedebut);
										parameters.put("dateFin", dateFin);
										parameters.put("titre", "Etat de Vente Par Sous Famille Article");
										 
										JasperUtils.imprimerEtatVenteFamilleArticle(listEtatVente,parameters);
										
								 }else
								 {
									 JOptionPane.showMessageDialog(null, "Il n'existe auccun vente pour cette periode ", "Erreur", JOptionPane.ERROR_MESSAGE); 
								 }
				  		   					
				  		   					
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   			btnImprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			btnImprimer.setBounds(439, 671, 107, 24);
				  		   		btnImprimer.setIcon(imgImprimer);
				  		   			add(btnImprimer);
				  		   			
				  		   			lblEtatDeVente = new JLabel("             Etat de Vente Par Sous Famille Article");
				  		   			lblEtatDeVente.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
				  		   			lblEtatDeVente.setBackground(Color.WHITE);
				  		   			lblEtatDeVente.setBounds(143, 40, 926, 35);
				  		   			add(lblEtatDeVente);
				  		   			
				  		   			JLayeredPane layeredPane = new JLayeredPane();
				  		   			layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			layeredPane.setBounds(36, 167, 1152, 54);
				  		   			add(layeredPane);
				  		   			
				  		   			lblDateDebut = new JLabel("Date Debut  :");
				  		   			lblDateDebut.setBounds(582, 13, 84, 24);
				  		   			layeredPane.add(lblDateDebut);
				  		   			
				  		   			dateChooserdebut = new JDateChooser();
				  		   			dateChooserdebut.setBounds(665, 11, 181, 26);
				  		   			layeredPane.add(dateChooserdebut);
				  		   			dateChooserdebut.setLocale(Locale.FRANCE);
				  		   			dateChooserdebut.setDateFormatString("dd/MM/yyyy");
				  		   			
				  		   			lblDateFin = new JLabel("Date Fin  :");
				  		   			lblDateFin.setBounds(856, 13, 62, 24);
				  		   			layeredPane.add(lblDateFin);
				  		   			
				  		   			dateChooserfin = new JDateChooser();
				  		   			dateChooserfin.setBounds(920, 13, 181, 26);
				  		   			layeredPane.add(dateChooserfin);
				  		   			dateChooserfin.setLocale(Locale.FRANCE);
				  		   			dateChooserfin.setDateFormatString("dd/MM/yyyy");
				  		   			
				  		   			button_1 = new JButton();
				  		   			button_1.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   					Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				  		   					
				  		   					if(magasin!=null)
				  		   					{
				  		   						
				  		   					BigDecimal quantite=BigDecimal.ZERO;
				  		   					BigDecimal quantitefoiprix=BigDecimal.ZERO;
				  		   				BigDecimal quantiteOffre=BigDecimal.ZERO;
			  		   					BigDecimal quantiteOffrefoiprix=BigDecimal.ZERO;
				  		   					
				  		   					
				  		   					
					  		   					boolean trouve=false;
					  		   					
					  		   				String dateDebut=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
					  						String dateFin=((JTextField)dateChooserfin.getDateEditor().getUiComponent()).getText();
					  					if(dateDebut.equals(""))	{
					  						JOptionPane.showMessageDialog(null, "Il faut choisir Date D�but", "Erreur", JOptionPane.ERROR_MESSAGE);
					  					} else if(dateFin.equals("")){
					  						JOptionPane.showMessageDialog(null, "Il faut choisir Date Fin", "Erreur", JOptionPane.ERROR_MESSAGE);
					  						
					  					
					  						}else {
					  							
					  							listEtatVente.clear();
					  							listDetailFactureTmp=detailfacturedao.listeDetailFacturePFByDate(dateChooserdebut.getDate(), dateChooserfin.getDate(), magasin);
					  						
					  							if(listDetailFactureTmp.size()!=0)
					  							{
					  								
					  								
						  							 for(int k=0;k<listDetailFactureTmp.size();k++)
						  							 {
						  								 DetailFacturePF detailfacture=listDetailFactureTmp.get(k);
						  								 trouve=false;
						  								
						  								 if(listDetailFacture.size()!=0)
						  								 {
						  									
						  									 for(int i=0;i<listDetailFacture.size();i++)
						  									 {
						  										 if(listDetailFacture.get(i).getSousFamille().equals(detailfacture.getSousFamille()))
						  										 {
						  											 trouve=true;
						  										 }
						  										 
						  									 }
						  									 
						  								 }else
						  								 {
						  									listDetailFacture.add(detailfacture);
						  									trouve=true;
						  								 }
						  								 
						  								 if(trouve==false)
						  								 {
						  									listDetailFacture.add(detailfacture);
						  								 }
						  							 }
						  							
						  							
						  							
						  						boolean existe=false;
						  						boolean exist=false;
						  							
						  							if(listDetailFacture.size()!=0)
						  							{
						  								for(int i=0;i<listDetailFacture.size();i++)
							  							{
							  								SousFamilleArticlePF sousfamilleArticle =listDetailFacture.get(i).getSousFamille();
							  								quantite=BigDecimal.ZERO;
							  								quantitefoiprix=BigDecimal.ZERO;
							  								quantiteOffre=BigDecimal.ZERO;
							  								quantiteOffrefoiprix=BigDecimal.ZERO;
							  								
							  								existe=false;
							  								exist=false;
							  							for(int j=0;j<listDetailFactureTmp.size();j++)	
							  							{
							  								
							  								
							  								
							  								if(listDetailFactureTmp.get(j).getSousFamille().equals(sousfamilleArticle))
							  								{
							  								   	if(listDetailFactureTmp.get(j).getPrixUnitaire().compareTo(BigDecimal.ZERO)==0)
							  								   	{
							  								   	quantiteOffrefoiprix=quantiteOffrefoiprix.add(listDetailFactureTmp.get(j).getQuantite().multiply(listDetailFactureTmp.get(j).getPrixUnitaire()));
							  								  quantiteOffre=quantiteOffre.add(listDetailFactureTmp.get(j).getQuantite());
							  								   		
							  								existe=true;
							  								   	}else
							  								   	{
							  								   		
							  								  	quantitefoiprix=quantitefoiprix.add(listDetailFactureTmp.get(j).getQuantite().multiply(listDetailFactureTmp.get(j).getPrixUnitaire()));
							  									quantite=quantite.add(listDetailFactureTmp.get(j).getQuantite());
							  									exist=true;
							  								   	
							  								   	}
							  									
							  									
							  									
							  								}
							  								
							  								
							  							}
							  							
							  							if(existe==true)
							  							{

								  							
								  		////////////////////////////////////////////////////////////// Offre ///////////////////////////////////////////////////////////////////////////////////////					
								  							
								  							
								  							EtatVenteParFamilleArticle etatventeFamilleArticleTmp=new EtatVenteParFamilleArticle();
								  							etatventeFamilleArticleTmp.setSousFamilleArticlePF(sousfamilleArticle);
								  							etatventeFamilleArticleTmp.setFamilleArticlePF(FamilleOffre);
								  							etatventeFamilleArticleTmp.setTotalVente(quantiteOffre);
								  							etatventeFamilleArticleTmp.setPrixMoyen(quantiteOffrefoiprix.divide(quantiteOffre, 6, RoundingMode.HALF_UP));
								  							etatventeFamilleArticleTmp.setMontant(etatventeFamilleArticleTmp.getPrixMoyen().multiply(etatventeFamilleArticleTmp.getTotalVente()).setScale(6, RoundingMode.FLOOR));
								  							listEtatVente.add(etatventeFamilleArticleTmp);
								  							
							  								
							  								
							  							}
							  							if(exist==true)
							  							{
							  								
							  							
							  								EtatVenteParFamilleArticle etatventeFamilleArticle=new EtatVenteParFamilleArticle();
								  							etatventeFamilleArticle.setSousFamilleArticlePF(sousfamilleArticle);
								  							etatventeFamilleArticle.setFamilleArticlePF(sousfamilleArticle.getFamileArticlePF());
								  							etatventeFamilleArticle.setTotalVente(quantite);
								  							etatventeFamilleArticle.setPrixMoyen(quantitefoiprix.divide(quantite, 6, RoundingMode.HALF_UP));
								  							etatventeFamilleArticle.setMontant(etatventeFamilleArticle.getPrixMoyen().multiply(etatventeFamilleArticle.getTotalVente()).setScale(6, RoundingMode.FLOOR));
								  							listEtatVente.add(etatventeFamilleArticle);
							  							}
							  							
							  							
							  						
							  							
							  								
							  							}
						  								
						  								afficher_tableEtatVenteArticle(listEtatVente);
						  								
						  								
						  							}else
						  							{
						  								JOptionPane.showMessageDialog(null, "Accun Vente � la periode saisi","Erreur",JOptionPane.ERROR_MESSAGE);
						  								listEtatVente.clear();
						  								afficher_tableEtatVenteArticle(listEtatVente);
						  								return;
						  							}
					  								
					  							}
					  							
					  							
					  						}
				  		   						
				  		   					}
				  		   					
				  		   		
				  		   				
				  		   					
				  		   				}
				  		   			});
				  		   			button_1.setBounds(1111, 11, 31, 31);
				  		   			layeredPane.add(button_1);
				  		   			button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			button_1.setIcon(imgRechercher);
				  		   			
				  		   			label = new JLabel("Depot :");
				  		   			label.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			label.setBounds(55, 13, 56, 24);
				  		   			layeredPane.add(label);
				  		   			
				  		   			combodepot = new JComboBox();
				  		   			combodepot.addItemListener(new ItemListener() {
				  		   				public void itemStateChanged(ItemEvent e) {
				  		   					


					  		  		  		

					  		   	 			
					  		     	 		 if(e.getStateChange() == ItemEvent.SELECTED)
					  		     	 		 {
					  		     	 			int i=0;
					  		     	 		
					  		     	 				if(!combodepot.getSelectedItem().equals(""))
					  		      			{
					  		      				Depot depot=mapDepot.get(combodepot.getSelectedItem());
					  		      				if(depot!=null)
					  		      				{
					  		      					listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(),Constantes.MAGASIN_CODE_TYPE_PF);
					  		 		     				if(listMagasin.size()!=0)
					  		 		     				{
					  		 		     					combomagasin.removeAllItems();
					  		 		     					combomagasin.addItem("");
					  		 		     					while(i<listMagasin.size())
					  		   		     				{
					  		   		     					Magasin magasin=listMagasin.get(i);
					  		   		     					combomagasin.addItem(magasin.getLibelle());
					  		   		     					mapMagasin.put(magasin.getLibelle(), magasin);
					  		   		     					i++;
					  		   		     				}
					  		 		     				}else
					  		 		     				{
					  		 		     					combomagasin.removeAllItems();
					  		 		     					
					  		 		     				}
					  		 		     				
					  		 		     				
					  		 		     				
					  		 		     				
					  		 		     			}else
					  		 		     			{
					  		 		     				combomagasin.removeAllItems();
					  		 		     				
					  		 		     			}
					  		 		     				
					  		      					
					  		      				}
					  		      				
					  		      				
					  		     	 		 }
					  		     	 	
					  		  	
					  		   			 		
					  		   			 		
					  		   			 		
					  		   			 		
					  		   			 		
					  		   			 	
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   			combodepot.setSelectedIndex(-1);
				  		   			combodepot.setBounds(105, 13, 183, 24);
				  		   			layeredPane.add(combodepot);
				  		   			
				  		   			label_1 = new JLabel("Magasin :");
				  		   			label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			label_1.setBounds(299, 12, 56, 24);
				  		   			layeredPane.add(label_1);
				  		   			
				  		   			combomagasin = new JComboBox();
				  		   			combomagasin.setSelectedIndex(-1);
				  		   			combomagasin.setBounds(365, 13, 183, 24);
				  		   			layeredPane.add(combomagasin);
				  		   			
				  		   			
				  		   		 if(utilisateur.getLogin().equals("admin"))
				  				  {
				  					  listDepot=depotdao.findAll();
				  					  int k=0;
				  				     	 combodepot.addItem("");
				  				     	while (k<listDepot.size())
				  				     	{
				  				     		Depot depot=listDepot.get(k);
				  				     		Magasin magasin=depotdao.MagasinByTypeMagasinStkAttente(MAGASIN_CODE_TYPE_MP_ATT);
				  				     		if(magasin!=null)
				  				     		{
				  				     			if(depot.getId()!=magasin.getDepot().getId())
				  					     		{
				  					     			combodepot.addItem(depot.getLibelle());
				  						     		
				  						     		mapDepot.put(depot.getLibelle(), depot);
				  						     	
				  						     		
				  					     			
				  					     		}
				  				     		}else
				  				     		{
				  				     			combodepot.addItem(depot.getLibelle());
				  					     		
				  					     		mapDepot.put(depot.getLibelle(), depot);
				  					     	
				  					     		
				  				     		}
				  				     		k++;
				  				     		
				  				     	}
				  				      
				  				  }else
				  				  {
				  					  Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
				  					  if(depot!=null)
				  					  {
				  						  combodepot.addItem(depot.getLibelle());
				  						
				  				     		mapDepot.put(depot.getLibelle(), depot);
				  					  }
				  				  }
				  				 
				  				  
				  				  combodepot.setSelectedIndex(-1);  			
				  				  
				  				  btnExporterExcel = new JButton("Exporter Excel");
				  				  btnExporterExcel.addActionListener(new ActionListener() {
				  				  	public void actionPerformed(ActionEvent arg0) {
				  				  		

				  			      		

				  						
				  					  Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				  					  if(magasin!=null) {
				  						  
				  						if(listEtatVente.size()!=0)
			  		   					{
				  							
				  						  String
					  					  titre="Etat de Vente Par Sous Famille Article "+mapMagasin.get(combomagasin.getSelectedItem()).getLibelle(); 
					  					  String titrefeuille="Etat de Vente Par Sous Famille Article PF ";
					  					  
					  					  
					  					  ExporterTableVersExcel.tabletoexcelEtatVenteParSousFamilleArticle (table, titre,titrefeuille);
					  					  
			  		   					}
				  					  
				  				
				  					  }else {
				  					  
				  					  
				  					  JOptionPane.showMessageDialog(null,
				  					  "Veuillez selectionner le magasin SVP !!!","Attention",JOptionPane.
				  					  ERROR_MESSAGE); return;
				  					  
				  					  
				  					  }
				  					 
				  		    	
				  		      		
				  		      		
				  		      		
				  		      		
				  		      	
				  				  		
				  				  		
				  				  		
				  				  		
				  				  		
				  				  	}
				  				  });
				  				  btnExporterExcel.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  				  btnExporterExcel.setBounds(584, 672, 128, 24);
				  				  btnExporterExcel.setIcon(imgExcel);
				  				  add(btnExporterExcel);
				  		   			
				  		   		
				  		   			
	}
	
	

	


	
	
	
	
	
	
	void afficher_tableEtatVenteArticle(List<EtatVenteParFamilleArticle> listEtatVenteFamilleArticle)
	{
		
		

		modeleCompte =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Famille Article","Sous Famille Article", "Total Vente", "Prix Moyen Vente", "Montant HT"
	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false,false,false,false
	  		     	};
	  		     	public boolean isCellEditable(int row, int column) {
	  		     		return columnEditables[column];
	  		     	}
	  		     };
	  		   table.setModel(modeleCompte);
	  		  // table.getColumnModel().getColumn(2).setPreferredWidth(160);
	  		   //table.getColumnModel().getColumn(3).setPreferredWidth(60);
	        //q table.getColumnModel().getColumn(4).setPreferredWidth(60);
	        
		  int i=0;
			while(i<listEtatVenteFamilleArticle.size())
			{	
				
				DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
				DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();

				symbols.setGroupingSeparator(' ');
				formatter.setDecimalFormatSymbols(symbols);
				
				
				Object []ligne={listEtatVenteFamilleArticle.get(i).getFamilleArticlePF().getLiblle(), listEtatVenteFamilleArticle.get(i).getSousFamilleArticlePF().getLiblle() ,listEtatVenteFamilleArticle.get(i).getTotalVente(),listEtatVenteFamilleArticle.get(i).getPrixMoyen(), listEtatVenteFamilleArticle.get(i).getMontant() };

				modeleCompte.addRow(ligne);
				i++;
			}
	}
	
	
	
	
	
	
	
	
}