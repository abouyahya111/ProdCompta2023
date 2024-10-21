package FacturePF;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
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
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.CompteClientDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
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
import dao.entity.DetailTransferProduitFini;
import dao.entity.EtatStockPF;
import dao.entity.EtatVentePFParFamille;
import dao.entity.EtatVenteParArticle;
import dao.entity.EtatVenteParFamilleArticle;
import dao.entity.FacturePF;
import dao.entity.FamilleArticlePF;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.MouvementStockProduitsFini;
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
import javax.swing.JCheckBox;


public class EtatVenteFamille extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleCompte;
	private DefaultTableModel	 modeledetail;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgSupprimer;
	private ImageIcon imgRechercher;
	private ImageIcon imgImprimer;
	private ImageIcon imgExcel;
	
	
	private  DetailFacturePFDAO detailfacturedao;
	
	
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<FamilleArticlePF> listFamille =new ArrayList<FamilleArticlePF>();
	private List<String> listLibelleFamille =new ArrayList<String>();
	
	
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< String, FamilleArticlePF> mapFamillePF= new HashMap<>();
	private List<EtatVentePFParFamille> listVentePFParFamille =new ArrayList<EtatVentePFParFamille>();
	private List <Object[]> listeObject=new ArrayList<Object[]>();
	private JScrollPane scrollPane;
	private JXTable table;
	private Utilisateur utilisateur;
	private JDateChooser dateChooserdebut;
	private JLabel lblDateFin;
	private JButton btnImprimer;
	private JLabel lblEtatDeVente;
	private JButton button_1;
	String message="";
	private JLabel label;
	private JComboBox combodepot;
	private JLabel label_1;
	private JComboBox combomagasin;
	private DepotDAO depotdao;
	private FamilleArticlesPFDAO familleArticlesPFDAO;
	private DetailTransferProduitFiniDAO detailTransferStockPFDAO;
	  JCheckBox chaara = new JCheckBox("Chaara");
	 JCheckBox mkerkeb = new JCheckBox("Mkerkeb");
	JCheckBox eau = new JCheckBox("Eau");
	 JCheckBox eaugazeuse = new JCheckBox("Eau Gazeuse");
	 JCheckBox elnass = new JCheckBox("El Nass");
	 JCheckBox chckbxTous = new JCheckBox("Tous");
	 JCheckBox chckbxJus = new JCheckBox("Jus");
	  JCheckBox chckbxPoisson = new JCheckBox("Poisson");
	  JCheckBox chckbxTomates = new JCheckBox("Tomates");
		  JCheckBox chckbxCeramica = new JCheckBox("Ceramica");
		  JCheckBox chckbxAlmands = new JCheckBox("ALMANDS");
		  JCheckBox chckbxRiz = new JCheckBox("RIZ");
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public EtatVenteFamille() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

        setBounds(0, 0, 1513, 882);
       
        try{
        	   utilisateur=AuthentificationView.utilisateur;
        	   detailfacturedao=new DetailFacturePFDAOImpl();
        	   detailTransferStockPFDAO=new DetailTransferProduitFiniDAOImpl();
        	
        	
        	
      

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
            familleArticlesPFDAO=new FamilleArticlesPFDAOImpl();                        
            listFamille=familleArticlesPFDAO.findAll();
            
            
            
          } catch (Exception exp){exp.printStackTrace();}
        
        
        
	    
	      	
	     final Utilisateur utilCreation=AuthentificationView.utilisateur;
				  		   		
				  		   			
				  		   		
				  		   			
				  		   			scrollPane = new JScrollPane((Component) null);
				  		   			scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			scrollPane.setBounds(10, 269, 1470, 378);
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
				  		   					"Famille Article", "Montant HT", "Montant TVA","Montant TTC"
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
				/*
				 * 
				 * if(listVentePFParFamille.size()!=0) {
				 * 
				 * 
				 * DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy"); String
				 * datedebut=dateFormat.format(dateChooserdebut.getDate()); //String
				 * dateFin=dateFormat.format(dateChooserfin.getDate());
				 * 
				 * 
				 * 
				 * Map parameters = new HashMap(); parameters.put("datedebut", datedebut);
				 * parameters.put("dateFin", datedebut);
				 * 
				 * JasperUtils.imprimerEtatVenteParFamille(listVentePFParFamille, parameters);;
				 * 
				 * }else { JOptionPane.showMessageDialog(null,
				 * "Il n'existe auccun vente pour cette periode ", "Erreur",
				 * JOptionPane.ERROR_MESSAGE); }
				 * 
				 * 
				 * 
				 * 
				 */
				
				
				Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				
	    		if(magasin!=null)
	    		{
	    			
	    			String dateDebut=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
	    		
	    		String titre="Etat de Vente Par Famille "+magasin.getLibelle() +" Annee  "+dateDebut;
	    		String titrefeuilleexcel="Etat de Vente Par Famille ";
	    		ExporterTableVersExcel.tabletoexcelEtatVenteParFamille (table, titre,titrefeuilleexcel);
	    		}else
	    		{

	    			JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!","Attention",JOptionPane.ERROR_MESSAGE);
	    			return;
	    		
	    		}
				
				
			
			
			
			}
				  		   			});
				  		   			btnImprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			btnImprimer.setBounds(583, 674, 107, 24);
				  		   		btnImprimer.setIcon(imgExcel);
				  		   			add(btnImprimer);
				  		   			
				  		   			lblEtatDeVente = new JLabel("             Etat de Vente Par Famille Article");
				  		   			lblEtatDeVente.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
				  		   			lblEtatDeVente.setBackground(Color.WHITE);
				  		   			lblEtatDeVente.setBounds(143, 40, 926, 35);
				  		   			add(lblEtatDeVente);
				  		   			
				  		   			JLayeredPane layeredPane = new JLayeredPane();
				  		   			layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			layeredPane.setBounds(10, 111, 1470, 106);
				  		   			add(layeredPane);
				  		   			
				  		   			dateChooserdebut = new JDateChooser();
				  		   			dateChooserdebut.setBounds(591, 25, 139, 26);
				  		   			layeredPane.add(dateChooserdebut);
				  		   			dateChooserdebut.setLocale(Locale.FRANCE);
				  		   			dateChooserdebut.setDateFormatString("yyyy");
				  		   			
				  		   			lblDateFin = new JLabel("Annee  :");
				  		   			lblDateFin.setBounds(513, 27, 68, 24);
				  		   			layeredPane.add(lblDateFin);
				  		   			
				  		   			label = new JLabel("Depot :");
				  		   			label.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			label.setBounds(10, 27, 56, 24);
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
				  		   			combodepot.setBounds(60, 27, 183, 24);
				  		   			layeredPane.add(combodepot);
				  		   			
				  		   			label_1 = new JLabel("Magasin :");
				  		   			label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			label_1.setBounds(254, 26, 56, 24);
				  		   			layeredPane.add(label_1);
				  		   			
				  		   			combomagasin = new JComboBox();
				  		   			combomagasin.setSelectedIndex(-1);
				  		   			combomagasin.setBounds(320, 27, 183, 24);
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
				  				  
				  				   chaara = new JCheckBox("Chaara");
				  				  chaara.setBounds(876, 28, 75, 23);
				  				  layeredPane.add(chaara);
				  				  
				  				   mkerkeb = new JCheckBox("Mkerkeb");
				  				  mkerkeb.setBounds(953, 28, 75, 23);
				  				  layeredPane.add(mkerkeb);
				  				  
				  				   eau = new JCheckBox("Eau");
				  				  eau.setBounds(1107, 28, 56, 23);
				  				  layeredPane.add(eau);
				  				  
				  				   eaugazeuse = new JCheckBox("Eau Gazeuse");
				  				  eaugazeuse.setBounds(1166, 28, 107, 23);
				  				  layeredPane.add(eaugazeuse);
				  				  
				  				  button_1 = new JButton();
				  				  button_1.setBounds(1380, 20, 31, 31);
				  				  layeredPane.add(button_1);
				  				  button_1.addActionListener(new ActionListener() {
				  				  	public void actionPerformed(ActionEvent e) {
				  				  		Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				  				  		
				  				  		if(magasin!=null)
				  				  		{
				  				  			
				  				  	
					  		   				String dateDebut=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
					  						
					  					if(dateDebut.equals(""))	{
					  						JOptionPane.showMessageDialog(null, "Il faut choisir Date Début", "Erreur", JOptionPane.ERROR_MESSAGE);
					  						return;
					  					} else {
					  						
					  						
					  						 SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");

						/*
						 * Date datedu=null; Date dateau=null; try {
						 * 
						 * 
						 * 
						 * 
						 * datedu = dt.parse(dateDebut+"-01-01"); dateau=dt.parse(dateDebut+"-12-31");
						 * 
						 * 
						 * 
						 * 
						 * } catch (ParseException e1) { // TODO Auto-generated catch block
						 * e1.printStackTrace(); }
						 */
					  						 
					  						 
					  						
					  							if(chaara.isSelected()==false && mkerkeb.isSelected()==false && eau.isSelected()==false && eaugazeuse.isSelected()==false && elnass.isSelected()==false && chckbxJus.isSelected()==false && chckbxPoisson.isSelected()==false && chckbxCeramica.isSelected()==false && chckbxTomates.isSelected()==false && chckbxRiz.isSelected()==false)
					  							{
					  								
					  								JOptionPane.showMessageDialog(null, "Il faut selectionner chaara , mkerkeb , eau , eau gazeuse , Jus , Poisson , Ceramica ou Tomates", "Erreur", JOptionPane.ERROR_MESSAGE);
							  						return ;
					  								
					  							}else
					  							{
					  								listVentePFParFamille.clear();
					  								listLibelleFamille.clear();
					  								if(chaara.isSelected()==true)
					  								{
					  									listLibelleFamille.add(Constantes.LIBELLE_FAMILLE_CHAARA);
					  								}
					  								if(mkerkeb.isSelected()==true)
					  								{
					  									listLibelleFamille.add(Constantes.LIBELLE_FAMILLE_MKARKEB);
					  								} 
					  								if(eau.isSelected()==true)
					  								{
					  									listLibelleFamille.add(Constantes.LIBELLE_FAMILLE_EAU);
					  									
					  								} 
					  								if(eaugazeuse.isSelected()==true)
					  								{
					  									
					  									listLibelleFamille.add(Constantes.LIBELLE_FAMILLE_EAU_GAZEUSE);
					  								}
					  								
					  								if(elnass.isSelected()==true)
					  								{
					  									
					  									listLibelleFamille.add(Constantes.LIBELLE_FAMILLE_ELNASS);
					  								}
					  								
					  								if(chckbxJus.isSelected()==true)
					  								{
					  									
					  									listLibelleFamille.add(Constantes.LIBELLE_FAMILLE_JUS);
					  								}
					  								
					  								if(chckbxPoisson.isSelected()==true)
					  								{
					  									
					  									listLibelleFamille.add(Constantes.LIBELLE_FAMILLE_POISSON);
					  								}
					  								
					  								if(chckbxCeramica.isSelected()==true)
					  								{
					  									
					  									listLibelleFamille.add(Constantes.LIBELLE_FAMILLE_CERAMICA);
					  								}
					  								
					  								if(chckbxTomates.isSelected()==true)
					  								{
					  									
					  									listLibelleFamille.add(Constantes.LIBELLE_FAMILLE_TOMATES);
					  								}
					  								
					  								if(chckbxAlmands.isSelected()==true)
					  								{
					  									
					  									listLibelleFamille.add(Constantes.LIBELLE_FAMILLE_ALMANDS);
					  								}
					  								
					  								if(chckbxRiz.isSelected()==true)
					  								{
					  									
					  									listLibelleFamille.add(Constantes.LIBELLE_FAMILLE_RIZ);
					  								}
					  								
					  								for( int j=0;j<listLibelleFamille.size();j++)
					  								{
					  									
					  									
					  									
					  										
					  										 Date datedux=null;
					  										Date dateaux=null;
															try {
																
																
																	datedux = dt.parse(dateDebut+"-01-01");
																	 dateaux=dt.parse(dateDebut+"-12-31");
																	
																
																 
																 
															} catch (ParseException e1) {
																// TODO Auto-generated catch block
																e1.printStackTrace();
															}
					  										
					  										
					  										
					  										
															listeObject=detailfacturedao.listeEtatVentePFParFamilleParMois(datedux, dateaux, magasin);
					  									FamilleArticlePF familleArticlePF=familleArticlesPFDAO.findByLibelle(listLibelleFamille.get(j));
					  										
					  										for(int i=0;i<listeObject.size() ; i++)
							  								{
					  											 Object[] object=listeObject.get(i);
							  									
							  									if( ((String) object[0]).equals(listLibelleFamille.get(j)))
							  									{
							  										

							  										EtatVentePFParFamille etatVentePFParFamille=new EtatVentePFParFamille();
					  											
					  												
							  										etatVentePFParFamille.setFamilleArticlePF(familleArticlePF);
					  											
						  										etatVentePFParFamille.setMontantHTMois1((BigDecimal) object[1]);
						  										etatVentePFParFamille.setMontantTVAMois1(((BigDecimal) object[1]).multiply(new BigDecimal(0.2)));
						  										etatVentePFParFamille.setMontantTTCMois1(((BigDecimal) object[1]).multiply(new BigDecimal(1.2)));
					  												
					  										
					  												etatVentePFParFamille.setMontantHTMois2((BigDecimal) object[2]);
							  										etatVentePFParFamille.setMontantTVAMois2(((BigDecimal) object[2]).multiply(new BigDecimal(0.2)));
							  										etatVentePFParFamille.setMontantTTCMois2( ((BigDecimal) object[2]).multiply(new BigDecimal(1.2)));
							  										
					  										
					  												etatVentePFParFamille.setMontantHTMois3((BigDecimal) object[3]);
							  										etatVentePFParFamille.setMontantTVAMois3( ((BigDecimal) object[3]).multiply(new BigDecimal(0.2)));
							  										etatVentePFParFamille.setMontantTTCMois3( ((BigDecimal) object[3]).multiply(new BigDecimal(1.2)));
							  										
					  										
					  												etatVentePFParFamille.setMontantHTMois4((BigDecimal) object[4]);
							  										etatVentePFParFamille.setMontantTVAMois4( ((BigDecimal) object[4]).multiply(new BigDecimal(0.2)));
							  										etatVentePFParFamille.setMontantTTCMois4( ((BigDecimal) object[4]).multiply(new BigDecimal(1.2)));
							  										
					  										
					  												etatVentePFParFamille.setMontantHTMois5((BigDecimal) object[5]);
							  										etatVentePFParFamille.setMontantTVAMois5(((BigDecimal) object[5]).multiply(new BigDecimal(0.2)));
							  										etatVentePFParFamille.setMontantTTCMois5(((BigDecimal) object[5]).multiply(new BigDecimal(1.2)));
							  										
					  										
					  												etatVentePFParFamille.setMontantHTMois6((BigDecimal) object[6]);
							  										etatVentePFParFamille.setMontantTVAMois6( ((BigDecimal) object[6]).multiply(new BigDecimal(0.2)));
							  										etatVentePFParFamille.setMontantTTCMois6(( (BigDecimal) object[6]).multiply(new BigDecimal(1.2)));
							  										
					  											
					  												etatVentePFParFamille.setMontantHTMois7((BigDecimal) object[7]);
							  										etatVentePFParFamille.setMontantTVAMois7( ((BigDecimal) object[7]).multiply(new BigDecimal(0.2)));
							  										etatVentePFParFamille.setMontantTTCMois7(((BigDecimal) object[7]).multiply(new BigDecimal(1.2)));
							  										
					  										
					  												etatVentePFParFamille.setMontantHTMois8((BigDecimal) object[8]);
							  										etatVentePFParFamille.setMontantTVAMois8(( (BigDecimal) object[8]).multiply(new BigDecimal(0.2)));
							  										etatVentePFParFamille.setMontantTTCMois8( ((BigDecimal) object[8]).multiply(new BigDecimal(1.2)));
							  										
					  												
					  												
					  												etatVentePFParFamille.setMontantHTMois9((BigDecimal) object[9]);
							  										etatVentePFParFamille.setMontantTVAMois9( ((BigDecimal) object[9]).multiply(new BigDecimal(0.2)));
							  										etatVentePFParFamille.setMontantTTCMois9(((BigDecimal) object[9]).multiply(new BigDecimal(1.2)));
							  										
					  												etatVentePFParFamille.setMontantHTMois10((BigDecimal) object[10]);
							  										etatVentePFParFamille.setMontantTVAMois10( ((BigDecimal) object[10]).multiply(new BigDecimal(0.2)));
							  										etatVentePFParFamille.setMontantTTCMois10(((BigDecimal) object[10]).multiply(new BigDecimal(1.2)));
							  										
					  														
					  														etatVentePFParFamille.setMontantHTMois11((BigDecimal) object[11]);
									  										etatVentePFParFamille.setMontantTVAMois11(((BigDecimal) object[11]).multiply(new BigDecimal(0.2)));
									  										etatVentePFParFamille.setMontantTTCMois11( ((BigDecimal) object[11]).multiply(new BigDecimal(1.2)));
									  									
					  												
					  												etatVentePFParFamille.setMontantHTMois12((BigDecimal) object[12]);
							  										etatVentePFParFamille.setMontantTVAMois12(((BigDecimal) object[12]).multiply(new BigDecimal(0.2)));
							  										etatVentePFParFamille.setMontantTTCMois12(( (BigDecimal) object[12]).multiply(new BigDecimal(1.2)));
					  												
					  											listVentePFParFamille.add(etatVentePFParFamille);
					  													
					  												}
					  												
					  												
							  										
							  									}
							  										
							  							
							  											
							  									 	
							  								}
					  										
					  										
					  								
					  								afficher_tableEtatVenteArticle(listVentePFParFamille);
					  								
					  							
					  								
					  								
					  							}
					  							
					  							
					  						}
				  				  			
				  				  		}else
				  				  		{
				  				  			
				  				  		JOptionPane.showMessageDialog(null, "Il faut choisir le Magasin", "Erreur", JOptionPane.ERROR_MESSAGE);
				  						return ;
				  				  			
				  				  		}
				  				  		
				  		   		
				  				  	
				  				  		
				  				  	}
				  				  });
				  				  button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  				  button_1.setIcon(imgRechercher);
				  				  
				  				   elnass = new JCheckBox("El Nass");
				  				  elnass.setBounds(1030, 28, 75, 23);
				  				  layeredPane.add(elnass);
				  				  
				  				   chckbxJus = new JCheckBox("Jus");
				  				  chckbxJus.setBounds(1275, 28, 77, 23);
				  				  layeredPane.add(chckbxJus);
				  				  
				  				   chckbxTous = new JCheckBox("Tous");
				  				  chckbxTous.addActionListener(new ActionListener() {
				  				  	public void actionPerformed(ActionEvent arg0) {
				  				  		if(chckbxTous.isSelected()==true)
				  				  		{
				  				  			chckbxJus.setSelected(true);
				  				  			chaara.setSelected(true);
				  				  		mkerkeb.setSelected(true);
				  				  	eau.setSelected(true);
				  				  eaugazeuse.setSelected(true);	
				  				elnass.setSelected(true);	
				  				
				  				  chckbxPoisson.setSelected(true);			
				  				  	chckbxCeramica.setSelected(true);	
				  				  	chckbxTomates.setSelected(true);	
				  				  	chckbxAlmands.setSelected(true);
				  					chckbxRiz.setSelected(true);
				  				  			
				  				  			
				  				  		}else
				  				  		{
				  				  			

				  				  			chckbxJus.setSelected(false);
				  				  			chaara.setSelected(false);
				  				  		mkerkeb.setSelected(false);
				  				  	eau.setSelected(false);
				  				  eaugazeuse.setSelected(false);	
				  				elnass.setSelected(false);	
				  				  			
				  				chckbxPoisson.setSelected(false);			
			  				  	chckbxCeramica.setSelected(false);	
			  				  	chckbxTomates.setSelected(false);	
			  				  	chckbxAlmands.setSelected(false);		
			  				  chckbxRiz.setSelected(false);		
				  				  			
				  				  		
				  				  			
				  				  			
				  				  			
				  				  		}
				  				  		
				  				  		
				  				  		
				  				  	}
				  				  });
				  				  chckbxTous.setBounds(799, 28, 75, 23);
				  				  layeredPane.add(chckbxTous);
				  				  
				  				   chckbxPoisson = new JCheckBox("Poisson");
				  				  chckbxPoisson.setBounds(799, 62, 75, 23);
				  				  layeredPane.add(chckbxPoisson);
				  				 
				  				   chckbxCeramica = new JCheckBox("Ceramica");
				  				  chckbxCeramica.setBounds(876, 62, 110, 23);
				  				  layeredPane.add(chckbxCeramica);
				  				  
				  				   chckbxTomates = new JCheckBox("Tomates");
				  				  chckbxTomates.setBounds(988, 62, 100, 23);
				  				  layeredPane.add(chckbxTomates);
				  				  
				  				   chckbxAlmands = new JCheckBox("ALMANDS");
				  				  chckbxAlmands.setBounds(1107, 62, 100, 23);
				  				  layeredPane.add(chckbxAlmands);
				  				  
				  				    chckbxRiz = new JCheckBox("RIZ");
				  				  chckbxRiz.setBounds(1213, 62, 100, 23);
				  				  layeredPane.add(chckbxRiz);
				  				  
				  				  
				  				  
				  				  for(int i=0;i<listFamille.size();i++)
				  				  {
				  					  
				  					  FamilleArticlePF familleArticlePF= listFamille.get(i);
				  					
				  					  mapFamillePF.put(familleArticlePF.getLiblle(), familleArticlePF);
				  					  
				  				  }
				  		   			
				  		   		
				  		   			
	}
	
	

	


	
	
	
	
	
	
	void afficher_tableEtatVenteArticle(List<EtatVentePFParFamille> listEtatVenteParFamille)
	{
		
		

		modeleCompte =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Famille Article", "Montant HT Mois 1", "Montant TVA Mois 1","Montant TTC Mois 1", "Montant HT Mois 2", "Montant TVA Mois 2","Montant TTC Mois 2", "Montant HT Mois 3", "Montant TVA Mois 3","Montant TTC Mois 3", "Montant HT Mois 4", "Montant TVA Mois 4","Montant TTC Mois 4", "Montant HT Mois 5", "Montant TVA Mois 5","Montant TTC Mois 5", "Montant HT Mois 6", "Montant TVA Mois 6","Montant TTC Mois 6", "Montant HT Mois 7", "Montant TVA Mois 7","Montant TTC Mois 7", "Montant HT Mois 8", "Montant TVA Mois 8","Montant TTC Mois 8", "Montant HT Mois 9", "Montant TVA Mois 9","Montant TTC Mois 9", "Montant HT Mois 10", "Montant TVA Mois 10","Montant TTC Mois 10", "Montant HT Mois 11", "Montant TVA Mois 11","Montant TTC Mois 11", "Montant HT Mois 12", "Montant TVA Mois 12","Montant TTC Mois 12"
	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false
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
			while(i<listEtatVenteParFamille.size())
			{	
				
				EtatVentePFParFamille etatVentePFParFamille=listEtatVenteParFamille.get(i);
				Object []ligne={etatVentePFParFamille.getFamilleArticlePF().getLiblle() ,etatVentePFParFamille.getMontantHTMois1(),etatVentePFParFamille.getMontantTVAMois1(),etatVentePFParFamille.getMontantTTCMois1(),etatVentePFParFamille.getMontantHTMois2(),etatVentePFParFamille.getMontantTVAMois2(),etatVentePFParFamille.getMontantTTCMois2(),etatVentePFParFamille.getMontantHTMois3(),etatVentePFParFamille.getMontantTVAMois3(),etatVentePFParFamille.getMontantTTCMois3(),etatVentePFParFamille.getMontantHTMois4(),etatVentePFParFamille.getMontantTVAMois4(),etatVentePFParFamille.getMontantTTCMois4(),etatVentePFParFamille.getMontantHTMois5(),etatVentePFParFamille.getMontantTVAMois5(),etatVentePFParFamille.getMontantTTCMois5(),etatVentePFParFamille.getMontantHTMois6(),etatVentePFParFamille.getMontantTVAMois6(),etatVentePFParFamille.getMontantTTCMois6(),etatVentePFParFamille.getMontantHTMois7(),etatVentePFParFamille.getMontantTVAMois7(),etatVentePFParFamille.getMontantTTCMois7(),etatVentePFParFamille.getMontantHTMois8(),etatVentePFParFamille.getMontantTVAMois8(),etatVentePFParFamille.getMontantTTCMois8(),etatVentePFParFamille.getMontantHTMois9(),etatVentePFParFamille.getMontantTVAMois9(),etatVentePFParFamille.getMontantTTCMois9(),etatVentePFParFamille.getMontantHTMois10(),etatVentePFParFamille.getMontantTVAMois10(),etatVentePFParFamille.getMontantTTCMois10(),etatVentePFParFamille.getMontantHTMois11(),etatVentePFParFamille.getMontantTVAMois11(),etatVentePFParFamille.getMontantTTCMois11(),etatVentePFParFamille.getMontantHTMois12(),etatVentePFParFamille.getMontantTVAMois12(),etatVentePFParFamille.getMontantTTCMois12()};

				modeleCompte.addRow(ligne);
				i++;
			}
	}
	
	
	
	
	
	
	public void CalculerEtatPF( Date datedu , Date dateau , String famille)
	
	{
		/*
		 * 
		 * 
		 * 
		 * detailTransferStockPFDAO.ViderSession();
		 * 
		 * listEtatStockPFImprimer.clear(); listEtatStockPF.clear();
		 * List<DetailTransferProduitFini> listDetailTransferStockPFInitial =new
		 * ArrayList<DetailTransferProduitFini>(); List<DetailTransferProduitFini>
		 * listDetailTransferStockPFAchat =new ArrayList<DetailTransferProduitFini>();
		 * List<DetailTransferProduitFini> listDetailTransferStockPFAchatGroupebyPF =new
		 * ArrayList<DetailTransferProduitFini>();
		 * 
		 * List<DetailTransferProduitFini> listDetailTransferStockPFProduction =new
		 * ArrayList<DetailTransferProduitFini>(); List<DetailTransferProduitFini>
		 * listDetailTransferStockPFProductionGroupebyPF =new
		 * ArrayList<DetailTransferProduitFini>();
		 * 
		 * List<DetailTransferProduitFini> listDetailTransferStockPFSortie =new
		 * ArrayList<DetailTransferProduitFini>(); List<DetailTransferProduitFini>
		 * listDetailTransferStockPFSortieGroupebyPF =new
		 * ArrayList<DetailTransferProduitFini>();
		 * 
		 * List<DetailTransferProduitFini> listDetailTransferStockPFEntrer =new
		 * ArrayList<DetailTransferProduitFini>(); List<DetailTransferProduitFini>
		 * listDetailTransferStockPFEntrerGroupebyPF =new
		 * ArrayList<DetailTransferProduitFini>();
		 * 
		 * List<DetailTransferProduitFini> listDetailTransferStockPFEntrerProduction
		 * =new ArrayList<DetailTransferProduitFini>(); List<DetailTransferProduitFini>
		 * listDetailTransferStockPFEntrerProductionGroupebyPF =new
		 * ArrayList<DetailTransferProduitFini>();
		 * 
		 * List<DetailTransferProduitFini> listDetailTransferStockPFAvoirMarajne =new
		 * ArrayList<DetailTransferProduitFini>(); List<DetailTransferProduitFini>
		 * listDetailTransferStockPFAvoirMarajneGroupebyPF =new
		 * ArrayList<DetailTransferProduitFini>();
		 * 
		 * List<DetailTransferProduitFini> listDetailTransferStockPFAvoir =new
		 * ArrayList<DetailTransferProduitFini>(); List<DetailTransferProduitFini>
		 * listDetailTransferStockPFAvoirGroupebyPF =new
		 * ArrayList<DetailTransferProduitFini>(); List<DetailTransferProduitFini>
		 * listDetailTransferStockPFAllPFTransfer =new
		 * ArrayList<DetailTransferProduitFini>();
		 * 
		 * 
		 * SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy"); BigDecimal
		 * montantInitial=new BigDecimal(0); BigDecimal quantiteTotalInitial=new
		 * BigDecimal(0); BigDecimal PrixInitial=new BigDecimal(0); BigDecimal
		 * montantachat=new BigDecimal(0); BigDecimal quantiteTotalachat=new
		 * BigDecimal(0); BigDecimal quantiteTotalvente=new BigDecimal(0); BigDecimal
		 * montantvente=new BigDecimal(0); BigDecimal quantiteTotalavoir=new
		 * BigDecimal(0); BigDecimal montantavoir=new BigDecimal(0); BigDecimal
		 * quantiteTotalEntrer=new BigDecimal(0); BigDecimal montantEntrer=new
		 * BigDecimal(0); BigDecimal quantiteTotalGratuit=new BigDecimal(0); BigDecimal
		 * montantGratuit=new BigDecimal(0); BigDecimal quantiteTotalproduction=new
		 * BigDecimal(0); BigDecimal prixmoyenproduction=new BigDecimal(0); BigDecimal
		 * prixmoyenachat=new BigDecimal(0); BigDecimal prixmoyenvente=new
		 * BigDecimal(0); BigDecimal prixmoyentransferentrer=new BigDecimal(0);
		 * BigDecimal prixmoyenavoir=new BigDecimal(0); BigDecimal montantproduction=new
		 * BigDecimal(0);
		 * 
		 * BigDecimal quantiteTotalFinale=new BigDecimal(0); BigDecimal
		 * montantFinale=new BigDecimal(0); boolean trouve=false; FamilleArticlePF
		 * familleArticle=familleArticlesPFDAO.findByLibelle(famille); Articles
		 * article=null; Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
		 * if(magasin!=null) {
		 * 
		 * 
		 * 
		 * 
		 * 
		 * listDetailTransferStockPFSortie=detailTransferStockPFDAO.
		 * ListTransferStockPFEntreDeuxDatesBYPFStatutX(datedu, dateau, article,
		 * ETAT_TRANSFER_STOCK_VENTE,magasin, familleArticle);
		 * listDetailTransferStockPFSortieGroupebyPF=detailTransferStockPFDAO.
		 * ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(datedu, dateau,
		 * article, ETAT_TRANSFER_STOCK_VENTE,magasin, familleArticle);
		 * 
		 * 
		 * 
		 * listDetailTransferStockPFAllPFTransfer=detailTransferStockPFDAO.
		 * findAllTransferStockPFGroupeByByArticleIdSouFamille(magasin);
		 * 
		 * for(int d=0;d<listDetailTransferStockPFAllPFTransfer.size();d++) {
		 * DetailTransferProduitFini
		 * detailtransferstockpf=listDetailTransferStockPFAllPFTransfer.get(d);
		 * EtatStockPF etatstock=new EtatStockPF();
		 * etatstock.setArticle(detailtransferstockpf.getArticle());
		 * etatstock.setSousFamille(detailtransferstockpf.getSousFamille());
		 * etatstock.setFamilleArticle(detailtransferstockpf.getSousFamille().
		 * getFamileArticlePF()); etatstock.setQuantiteInitial(BigDecimal.ZERO);
		 * etatstock.setPrixInitial(BigDecimal.ZERO);
		 * etatstock.setMontantInitial(BigDecimal.ZERO);
		 * etatstock.setQuantiteAchat(BigDecimal.ZERO);
		 * etatstock.setPrixAchat(BigDecimal.ZERO);
		 * etatstock.setMontantAchat(BigDecimal.ZERO);
		 * etatstock.setQuantiteProduction(BigDecimal.ZERO);
		 * etatstock.setPrixProduction(BigDecimal.ZERO);
		 * etatstock.setMontantProduction(BigDecimal.ZERO);
		 * etatstock.setQuantiteSortie(BigDecimal.ZERO);
		 * etatstock.setPrixSortie(BigDecimal.ZERO);
		 * etatstock.setMontantSortie(BigDecimal.ZERO);
		 * etatstock.setQuantiteEntrer(BigDecimal.ZERO);
		 * etatstock.setPrixEntrer(BigDecimal.ZERO);
		 * etatstock.setMontantEntrer(BigDecimal.ZERO);
		 * etatstock.setQuantiteAvoir(BigDecimal.ZERO);
		 * etatstock.setPrixAvoir(BigDecimal.ZERO);
		 * etatstock.setMontantAvoir(BigDecimal.ZERO);
		 * etatstock.setMarge(BigDecimal.ZERO);
		 * etatstock.setQuantiteGratuit(BigDecimal.ZERO);
		 * etatstock.setPrixGratuit(BigDecimal.ZERO);
		 * etatstock.setMontantGratuit(BigDecimal.ZERO);
		 * etatstock.setQuantiteFinale(BigDecimal.ZERO);
		 * etatstock.setPrixFinale(BigDecimal.ZERO);
		 * etatstock.setMontantFinale(BigDecimal.ZERO); listEtatStockPF.add(etatstock);
		 * 
		 * }
		 * 
		 * 
		 * 
		 * // calculer Quantite Vente et le prix moyen
		 * 
		 * 
		 * 
		 * for(int i=0;i<listDetailTransferStockPFSortieGroupebyPF.size();i++) {
		 * quantiteTotalvente=BigDecimal.ZERO; quantiteTotalGratuit=BigDecimal.ZERO;
		 * montantvente=new BigDecimal(0); prixmoyenvente=new BigDecimal(0);
		 * 
		 * for(int j=0;j<listDetailTransferStockPFSortie.size();j++) {
		 * 
		 * if(listDetailTransferStockPFSortieGroupebyPF.get(i).getArticle().equals(
		 * listDetailTransferStockPFSortie.get(j).getArticle()) &&
		 * listDetailTransferStockPFSortieGroupebyPF.get(i).getSousFamille().equals(
		 * listDetailTransferStockPFSortie.get(j).getSousFamille())) {
		 * 
		 * 
		 * // la quantite Gratuité
		 * if(listDetailTransferStockPFSortie.get(j).getPrixUnitaire().setScale(6).
		 * equals(BigDecimal.ZERO.setScale(6))) {
		 * 
		 * quantiteTotalGratuit=quantiteTotalGratuit.add(listDetailTransferStockPFSortie
		 * .get(j).getQuantite());
		 * 
		 * 
		 * 
		 * }else {
		 * 
		 * // la quantite Vente
		 * 
		 * montantvente=montantvente.add(listDetailTransferStockPFSortie.get(j).
		 * getPrixUnitaire().multiply(listDetailTransferStockPFSortie.get(j).getQuantite
		 * ())); prixmoyenvente=((prixmoyenvente.multiply(quantiteTotalvente)).add(
		 * listDetailTransferStockPFSortie.get(j).getPrixUnitaire().setScale(6,
		 * RoundingMode.DOWN).multiply(listDetailTransferStockPFSortie.get(j).
		 * getQuantite()))).divide(quantiteTotalvente.add(
		 * listDetailTransferStockPFSortie.get(j).getQuantite()),6, RoundingMode.DOWN) ;
		 * 
		 * quantiteTotalvente=quantiteTotalvente.add(listDetailTransferStockPFSortie.get
		 * (j).getQuantite());
		 * 
		 * 
		 * }
		 * 
		 * 
		 * }
		 * 
		 * 
		 * }
		 * 
		 * if(!quantiteTotalvente.equals(BigDecimal.ZERO)) {
		 * 
		 * 
		 * for(int k=0;k<listEtatStockPF.size();k++) {
		 * 
		 * 
		 * if(listEtatStockPF.get(k).getArticle().equals(
		 * listDetailTransferStockPFSortieGroupebyPF.get(i).getArticle()) &&
		 * listEtatStockPF.get(k).getSousFamille().equals(
		 * listDetailTransferStockPFSortieGroupebyPF.get(i).getSousFamille())) {
		 * 
		 * EtatStockPF etatstockpf=listEtatStockPF.get(k);
		 * etatstockpf.setQuantiteSortie(quantiteTotalvente);
		 * etatstockpf.setPrixSortie(prixmoyenvente);
		 * etatstockpf.setMontantSortie(quantiteTotalvente.multiply(prixmoyenvente));
		 * 
		 * listEtatStockPF.set(k, etatstockpf); }
		 * 
		 * 
		 * }
		 * 
		 * 
		 * }
		 * 
		 * 
		 * if(!quantiteTotalGratuit.equals(BigDecimal.ZERO)) {
		 * 
		 * 
		 * for(int k=0;k<listEtatStockPF.size();k++) {
		 * 
		 * 
		 * if(listEtatStockPF.get(k).getArticle().equals(
		 * listDetailTransferStockPFSortieGroupebyPF.get(i).getArticle()) &&
		 * listEtatStockPF.get(k).getSousFamille().equals(
		 * listDetailTransferStockPFSortieGroupebyPF.get(i).getSousFamille())) {
		 * 
		 * EtatStockPF etatstockpf=listEtatStockPF.get(k);
		 * etatstockpf.setQuantiteGratuit(quantiteTotalGratuit.setScale(6));
		 * if(etatstockpf.getPrixAchat().setScale(6).equals(BigDecimal.ZERO.setScale(6))
		 * ) { etatstockpf.setPrixGratuit(etatstockpf.getPrixInitial().setScale(6));
		 * }else { etatstockpf.setPrixGratuit(etatstockpf.getPrixAchat().setScale(6)); }
		 * 
		 * etatstockpf.setMontantGratuit(etatstockpf.getQuantiteGratuit().setScale(6).
		 * multiply(etatstockpf.getPrixGratuit().setScale(6)));;
		 * 
		 * // ajouter la quantite gratuité et le prix gratuité(prix d'achat)
		 * 
		 * 
		 * listEtatStockPF.set(k, etatstockpf); }
		 * 
		 * }
		 * 
		 * 
		 * }
		 * 
		 * 
		 * }
		 * 
		 * }else {
		 * 
		 * 
		 * JOptionPane.showMessageDialog(null,
		 * "Veuillez selectionner un depot SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
		 * return; }
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */}
}
