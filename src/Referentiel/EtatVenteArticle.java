package Referentiel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
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
import dao.entity.ClientPF;
import dao.entity.CompteClient;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.DetailFacturePF;
import dao.entity.EtatVenteParArticle;
import dao.entity.FacturePF;
import dao.entity.FamilleArticlePF;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
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


public class EtatVenteArticle extends JLayeredPane implements Constantes{
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
	
	private DepotDAO depotdao;
	private List<DetailFacturePF> listDetailFacture =new ArrayList<DetailFacturePF>();
	private List<DetailFacturePF> listDetailFactureTmp =new ArrayList<DetailFacturePF>();
	private List<EtatVenteParArticle> listEtatVente =new ArrayList<EtatVenteParArticle>();
	private JScrollPane scrollPane;
	private JXTable table = new JXTable();
	private Utilisateur utilisateur;
	private JLabel lblDateDebut;
	private JDateChooser dateChooserdebut;
	private JLabel lblDateFin;
	private JDateChooser dateChooserfin;
	private JButton btnImprimer;
	private JLabel lblEtatDeVente;
	private JButton button_1;
	String message="";
	JComboBox combodepot = new JComboBox();
	JComboBox combomagasin = new JComboBox();
	private JButton button;
	 private ImageIcon imgExcel;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public EtatVenteArticle() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

        setBounds(0, 0, 1284, 882);
       
        try{
        	   utilisateur=AuthentificationView.utilisateur;
        	   detailfacturedao=new DetailFacturePFDAOImpl();
        	
        	
        	
        	
      

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp.png"));
            imgRechercher= new ImageIcon(this.getClass().getResource("/img/rechercher.png"));
            imgImprimer=new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
            imgExcel=new ImageIcon(this.getClass().getResource("/img/excel.png"));
        	depotdao=ProdLauncher.depotDAO;
        	utilisateur=AuthentificationView.utilisateur;
        	 
          } catch (Exception exp){exp.printStackTrace();}
        
        
        
	    
	      	
	     final Utilisateur utilCreation=AuthentificationView.utilisateur;
				  		   		
				  		   			
				  		   		
				  		   			
				  		   			scrollPane = new JScrollPane((Component) null);
				  		   			scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			scrollPane.setBounds(57, 269, 1107, 378);
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
				  		   					"Article", "Total Vente", "Prix Moyen Vente"
				  		   				}
				  		   			));
				  		   			table.getColumnModel().getColumn(0).setPreferredWidth(221);
				  		   			table.getColumnModel().getColumn(1).setPreferredWidth(120);
				  		   			table.getColumnModel().getColumn(2).setPreferredWidth(123);
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
										
									
										
										
										JasperUtils.imprimerEtatVenteArticle(listEtatVente,parameters);
										
								 }else
								 {
									 JOptionPane.showMessageDialog(null, "Il n'existe auccun vente pour cette periode ", "Erreur", JOptionPane.ERROR_MESSAGE); 
								 }
				  		   					
				  		   					
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   			btnImprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			btnImprimer.setBounds(583, 674, 107, 24);
				  		   		btnImprimer.setIcon(imgImprimer);
				  		   			add(btnImprimer);
				  		   			
				  		   			lblEtatDeVente = new JLabel("             Etat de Vente Par Article");
				  		   			lblEtatDeVente.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
				  		   			lblEtatDeVente.setBackground(Color.WHITE);
				  		   			lblEtatDeVente.setBounds(260, 41, 791, 35);
				  		   			add(lblEtatDeVente);
				  		   			
				  		   			JLayeredPane layeredPane = new JLayeredPane();
				  		   			layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			layeredPane.setBounds(57, 167, 1107, 54);
				  		   			add(layeredPane);
				  		   			
				  		   			lblDateDebut = new JLabel("Date Debut  :");
				  		   			lblDateDebut.setBounds(538, 12, 84, 24);
				  		   			layeredPane.add(lblDateDebut);
				  		   			
				  		   			dateChooserdebut = new JDateChooser();
				  		   			dateChooserdebut.setBounds(610, 12, 181, 26);
				  		   			layeredPane.add(dateChooserdebut);
				  		   			dateChooserdebut.setLocale(Locale.FRANCE);
				  		   			dateChooserdebut.setDateFormatString("dd/MM/yyyy");
				  		   			
				  		   			lblDateFin = new JLabel("Date Fin  :");
				  		   			lblDateFin.setBounds(801, 14, 62, 24);
				  		   			layeredPane.add(lblDateFin);
				  		   			
				  		   			dateChooserfin = new JDateChooser();
				  		   			dateChooserfin.setBounds(865, 14, 181, 26);
				  		   			layeredPane.add(dateChooserfin);
				  		   			dateChooserfin.setLocale(Locale.FRANCE);
				  		   			dateChooserfin.setDateFormatString("dd/MM/yyyy");
				  		   			
				  		   			button_1 = new JButton();
				  		   			button_1.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   					Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				  		   					if(magasin !=null)
				  		   					{
				  		   						
				  		   	 				BigDecimal quantite=BigDecimal.ZERO;
				  		   					BigDecimal quantitefoiprix=BigDecimal.ZERO;
					  		   					boolean trouve=false;
					  		   					
					  		   				String dateDebut=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
					  						String dateFin=((JTextField)dateChooserfin.getDateEditor().getUiComponent()).getText();
					  					if(dateDebut.equals(""))	{
					  						JOptionPane.showMessageDialog(null, "Il faut choisir Date Début", "Erreur", JOptionPane.ERROR_MESSAGE);
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
						  										 if(listDetailFacture.get(i).getArticle().equals(detailfacture.getArticle()))
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
						  							
						  							
						  							
						  						
						  							
						  							if(listDetailFacture.size()!=0)
						  							{
						  								for(int i=0;i<listDetailFacture.size();i++)
							  							{
							  								Articles article =listDetailFacture.get(i).getArticle();
							  								quantite=BigDecimal.ZERO;
							  								quantitefoiprix=BigDecimal.ZERO;
							  								
							  							for(int j=0;j<listDetailFactureTmp.size();j++)	
							  							{
							  								
							  								
							  								
							  								if(listDetailFactureTmp.get(j).getArticle().equals(article))
							  								{
							  									
							  									quantitefoiprix=quantitefoiprix.add(listDetailFactureTmp.get(j).getQuantite().multiply(listDetailFactureTmp.get(j).getPrixUnitaire()));
							  									quantite=quantite.add(listDetailFactureTmp.get(j).getQuantite());
							  									
							  									
							  								}
							  								
							  								
							  							}
							  							
							  							EtatVenteParArticle etatventearticle=new EtatVenteParArticle();
							  							etatventearticle.setArticle(article);
							  							etatventearticle.setTotalVente(quantite);
							  							etatventearticle.setPrixMoyen(quantitefoiprix.divide(quantite, 6, RoundingMode.HALF_UP));
							  							listEtatVente.add(etatventearticle);
							  							
							  								
							  							}
						  								
						  								afficher_tableEtatVenteArticle(listEtatVente);
						  								
						  								
						  							}else
						  							{
						  								JOptionPane.showMessageDialog(null, "Accun Vente à la periode saisi","Erreur",JOptionPane.ERROR_MESSAGE);
						  								listEtatVente.clear();
						  								afficher_tableEtatVenteArticle(listEtatVente);
						  								return;
						  							}
					  								
					  							}
					  							
					  						}
				  		   						
				  		   					}else
				  		   					{
				  		   					JOptionPane.showMessageDialog(null, "Veuillez selectionner un Magasin SVP","Erreur",JOptionPane.ERROR_MESSAGE);
				  		   					return;
				  		   					}
				  		   					
				  		  
				  		   				
				  		   					
				  		   				}
				  		   			});
				  		   			button_1.setBounds(1066, 12, 31, 31);
				  		   			layeredPane.add(button_1);
				  		   			button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			button_1.setIcon(imgRechercher);
				  		   			
				  		   			JLabel label = new JLabel("Depot :");
				  		   			label.setBounds(10, 12, 56, 24);
				  		   			layeredPane.add(label);
				  		   			label.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			
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
				  		   			combodepot.setBounds(60, 12, 183, 24);
				  		   			layeredPane.add(combodepot);
				  		   			combodepot.setSelectedIndex(-1);
				  		   			
				  		   			JLabel label_1 = new JLabel("Magasin :");
				  		   			label_1.setBounds(254, 11, 56, 24);
				  		   			layeredPane.add(label_1);
				  		   			label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			
				  		   			 combomagasin = new JComboBox();
				  		   			combomagasin.setBounds(320, 12, 183, 24);
				  		   			layeredPane.add(combomagasin);
				  		   			combomagasin.setSelectedIndex(-1);
				  		   			
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
				  				  
				  				  button = new JButton("Exporter Excel");
				  				  button.addActionListener(new ActionListener() {
				  				  	public void actionPerformed(ActionEvent e) {
				  				  		

				  			      		

				  				  		

				  			      		

				  						
				  					  Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				  					  if(magasin!=null) {
				  						  
				  						if(listEtatVente.size()!=0)
				  	   					{
				  							
				  						  String
				  						  titre="Etat Vente Article "+mapMagasin.get(combomagasin.getSelectedItem()).getLibelle(); 
				  						  String titrefeuille="Etat Vente Article  ";
				  						  
				  						  
				  						  ExporterTableVersExcel.tabletoexcelEtatVenteParArticle (table, titre,titrefeuille);
				  						  
				  	   					}
				  					  
				  				
				  					  }else {
				  					  
				  					  
				  					  JOptionPane.showMessageDialog(null,
				  					  "Veuillez selectionner le magasin SVP !!!","Attention",JOptionPane.
				  					  ERROR_MESSAGE); return;
				  					  
				  					  
				  					  }
				  					 
				  		    	
				  		      		
				  		      		
				  		      		
				  		      		
				  		      	
				  				  		
				  				  		
				  				  		
				  				  		
				  				  		
				  				  	
				  		      		
				  		      		
				  		      		
				  		      		
				  		      		
				  		      	
				  				  		
				  				  		
				  				  		
				  				  		
				  				  		
				  				  	}
				  				  });
				  				  button.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  				  button.setBounds(734, 675, 142, 24);
				  				button.setIcon(imgExcel);
				  				  add(button);
				  		   			
				  		   			
				  		   			
				  		   			
				  		   			
				  		   			
				  		   		
				  		   			
	}
	
	

	


	
	
	
	
	
	
	void afficher_tableEtatVenteArticle(List<EtatVenteParArticle> listEtatVenteArticle)
	{
		
		

		modeleCompte =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Article", "Total Vente", "Prix Moyen Vente"
	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false,false
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
			while(i<listEtatVenteArticle.size())
			{	
				
				
				Object []ligne={listEtatVenteArticle.get(i).getArticle().getLiblle() ,listEtatVenteArticle.get(i).getTotalVente(),listEtatVenteArticle.get(i).getPrixMoyen()};

				modeleCompte.addRow(ligne);
				i++;
			}
	}
}
