package Journee;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
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
import org.jdesktop.swingx.JXTitledSeparator;

import Production.MatierePremiere;
import util.Constantes;
import util.Utils;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailMouvementStockDAOImpl;
import dao.daoImplManager.JourneeDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.MouvementStockGlobalDAOImpl;
import dao.daoImplManager.StockMPDAOImpl;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailMouvementStockDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.JourneeDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.MouvementStockGlobalDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.daoManager.UtilisateurDAO;
import dao.entity.CategorieMp;
import dao.entity.Client;
import dao.entity.Depot;
import dao.entity.DetailEstimation;
import dao.entity.DetailMouvementStock;
import dao.entity.FamilleArticlePF;
import dao.entity.Journee;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.MouvementStockGlobal;
import dao.entity.StockMP;
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

import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.Box;


public class AjoutJournee extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleCat;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgSupprimer;
	
	private  FamilleArticlesPFDAO famillearticleDAO;
	
	
 	
	private List<MouvementStockGlobal> listMouvementStockGlobal =new ArrayList<MouvementStockGlobal>();
	private List<DetailMouvementStock> listDetailMouvementStock =new ArrayList<DetailMouvementStock>();
	private List<DetailMouvementStock> listDetailMouvementStockTmp =new ArrayList<DetailMouvementStock>();
	private List<Journee> listJournee =new ArrayList<Journee>();
	
	private List<Magasin> listMagasin =new ArrayList<Magasin>();
	private List<StockMP> listStock =new ArrayList<StockMP>();
	private DepotDAO depotDAO;
	private MouvementStockGlobalDAO mouvementStockGlobaleDAO;
	private DetailMouvementStockDAO detailMouvementStockDAO;
	private StockMPDAO stockMPDAO;
	
	public static Utilisateur utilisateur;
	public UtilisateurDAO utiliseurDAO;
	public JourneeDAO journeeDAO;
	public static MatierePremiereDAO dao= new MatierePremierDAOImpl();
 
 	
	
	private JButton button_1;
	private JButton btnAjouter;
	private JScrollPane scrollPane;
	private JTable table;
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjoutJournee() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

        setBounds(0, 0, 1284, 882);
       
        try{
        	
        	
        

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp.png"));
          } catch (Exception exp){exp.printStackTrace();}
        
        
        depotDAO=new DepotDAOImpl();
		detailMouvementStockDAO=new DetailMouvementStockDAOImpl();
		mouvementStockGlobaleDAO =new MouvementStockGlobalDAOImpl();
		stockMPDAO=new StockMPDAOImpl();
		journeeDAO=new JourneeDAOImpl();
	   
		utilisateur=AuthentificationView.utilisateur;
	   
				  		 
				  		 JLayeredPane layeredPane = new JLayeredPane();
				  		   
				  		  
				  		   layeredPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(192, 192, 192)));
				  		   layeredPane.setBounds(31, 177, 685, 71);
				  		   add(layeredPane);
				  		   
				  		   JDateChooser dateChoosercreation = new JDateChooser();
				  		   dateChoosercreation.setBounds(260, 21, 183, 24);
				  		   layeredPane.add(dateChoosercreation);
				  		   
				  		   JLabel lblDateJourne = new JLabel("Date Journ\u00E9e  :");
				  		   lblDateJourne.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   lblDateJourne.setBounds(170, 21, 96, 24);
				  		   layeredPane.add(lblDateJourne);
				  		   			
				  		   		
				  		   			
				  		   			scrollPane = new JScrollPane((Component) null);
				  		   			scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			scrollPane.setBounds(30, 340, 686, 365);
				  		   			add(scrollPane);
				  		   			
				  		   			table = new JTable();
				  		   			table.addMouseListener(new MouseAdapter() {
				  		   				@Override
				  		   				public void mouseClicked(MouseEvent arg0) {}
				  		   			});
				  		   			scrollPane.setViewportView(table);
				  		   			table.setModel(new DefaultTableModel(
				  		   				new Object[][] {
				  		   				},
				  		   				new String[] {
				  		   					"Date Journ\u00E9e", "Statut","Depot"
				  		   				}
				  		   			));
				  		   			table.getColumnModel().getColumn(0).setPreferredWidth(191);
				  		   			table.getColumnModel().getColumn(1).setPreferredWidth(127);
				  		   			table.setFillsViewportHeight(true);
				  		   	
				  		   			
				  		   			button_1 = new JButton("Initialiser");
				  		   			button_1.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   					
				  		   					initialiser();
				  		   					
				  		   				}
				  		   			});
				  		   			button_1.setBounds(309, 262, 106, 23);
				  		   			add(button_1);
				  		   			button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			
				  		   			btnAjouter = new JButton("Ajouter");
				  		   			btnAjouter.setBounds(192, 261, 107, 24);
				  		   			add(btnAjouter);
				  		   			btnAjouter.addActionListener(new ActionListener() {
				  		   				
				  		   				public void actionPerformed(ActionEvent arg0) {/*
				  		   					

				  		   					/////////// charger Mouvement de Stock /////////
				  		   					
				  		   					
				  		   					if(dateChoosercreation.getDate()!=null)
				  		   					{
				  		   						
				  		   						listJournee =journeeDAO.findAll();
				  		   						if(listJournee.size()==0)
				  		   						{
				  		   						Depot depot=depotDAO.findByCode(utilisateur.getCodeDepot());
						  		   					
							  		   				  Journee journee = new Journee();
								  		               
									  		   	        journee.setUtilisateurCreation(utilisateur);
									  		   	        journee.setDateCreation(new Date());
									  		   	        journee.setDateJournee(dateChoosercreation.getDate());
									  		   	        journee.setDateOvertute(dateChoosercreation.getDate());
									  		   	        journee.setDepot(depot);
									  		   	        journee.setStatue(Constantes.ETAT_STATUT_OVERTE);

									  		   	        journeeDAO.add(journee);
									  		   				
						  		   					
						  		   						listMagasin =depotDAO.findMagasinByCategorieTypeMagasinDepot(Constantes.MAGASIN_CODE_CATEGORIE_STOCKAGE, Constantes.MAGASIN_CODE_TYPE_MP, depot.getId());
						  		   						for(int j=0;j<listMagasin.size();j++)
						  		   						{
						  		   							listDetailMouvementStock.clear();
						  		   							Magasin magasin=listMagasin.get(j);
						  		   							listStock=stockMPDAO.findAllByMagasin(magasin.getId());
						  		   							MouvementStockGlobal mouvementStockGlobal=new MouvementStockGlobal();
						  		   							for(int k=0;k<listStock.size();k++)
						  		   							{
						  		   								StockMP stockmp=listStock.get(k);
						  		   								
						  		   								DetailMouvementStock detailMouvementStock=new DetailMouvementStock();
						  		   								
						  		   								detailMouvementStock.setDateCreation(dateChoosercreation.getDate());
						  		   								detailMouvementStock.setMatierePremier(stockmp.getMatierePremier());
						  		   								detailMouvementStock.setInitial(stockmp.getStock());
						  		   								detailMouvementStock.setUtilisateurCreation(utilisateur);
						  		   								detailMouvementStock.setCharge(BigDecimal.ZERO);
						  		   								detailMouvementStock.setChargeSupplementaire(BigDecimal.ZERO);
						  		   								detailMouvementStock.setReception(BigDecimal.ZERO);
						  		   								detailMouvementStock.setSorties(BigDecimal.ZERO);
						  		   								detailMouvementStock.setStockFinaldb(BigDecimal.ZERO);
						  		   								detailMouvementStock.setTransfertEntrees(BigDecimal.ZERO);
						  		   								detailMouvementStock.setTransfertSorties(BigDecimal.ZERO);
						  		   						    	detailMouvementStock.setRetour(BigDecimal.ZERO);
						  		   								detailMouvementStock.setMouvementStockGlobal(mouvementStockGlobal);
						  		   								listDetailMouvementStock.add(detailMouvementStock);
						  		   									
						  		   								
						  		   								
						  		   							}
						  		   							mouvementStockGlobal.setDateCreation(dateChoosercreation.getDate());
						  		   							mouvementStockGlobal.setDateMouvement(dateChoosercreation.getDate());
						  		   							mouvementStockGlobal.setDepot(depot);
						  		   							mouvementStockGlobal.setMagasin(magasin);
						  		   							mouvementStockGlobal.setUtilisateurCreation(utilisateur);
						  		   							mouvementStockGlobal.setDetailMouvementStock(listDetailMouvementStock);
						  		   							mouvementStockGlobaleDAO.add(mouvementStockGlobal);
						  		   						
						  		   							
						  		   							}
						  		   						
						  		   						
						  		   						charger();

				  		   						}else
				  		   						{
				  		   						 Depot depot=depotDAO.findByCode(utilisateur.getCodeDepot());
				  		   						Journee journeeTmp=journeeDAO.findJourneeByDetailOverte(Constantes.ETAT_STATUT_OVERTE, depot.getId());
				  		   							if(journeeTmp!=null)
				  		   							{
				  		   								
				  		   								
				  		   								
				  		   								
				  		   								
				  		   								
				  		   							 BigDecimal valeur = BigDecimal.ZERO;
				  		   				             boolean exist = false;       
				  		   				                      
				  		   				          
				  		   				               if (dateChoosercreation.getDate().compareTo(journeeTmp.getDateJournee())>0){
				  		   				               
				  		   				               listMouvementStockGlobal = mouvementStockGlobaleDAO.findMouvementStockGlobalByDetailMouvementStock(journeeTmp.getDateJournee());
				  		   				                 
				  		   				          
				  		   				               Journee journee = new Journee();
				  		   				               
				  		   				        journee.setUtilisateurCreation(utilisateur);
				  		   				        journee.setDateCreation(new Date());
				  		   				        journee.setDateJournee(dateChoosercreation.getDate());
				  		   				        journee.setDateOvertute(dateChoosercreation.getDate());
				  		   				        journee.setDateOvertute(dateChoosercreation.getDate());
				  		   				        journee.setDepot(depot);
				  		   				        journee.setStatue(Constantes.ETAT_STATUT_OVERTE);

				  		   				        journeeDAO.add(journee);

				  		   				        
				  		   				                   
				  		   				        journeeTmp.setStatue(Constantes.ETAT_STATUT_FERMER);    
				  		   				        journeeTmp.setDateFermeture(new Date());        
				  		   				        journeeDAO.edit(journeeTmp);

				  		   				        
				  		   				   
			  		   						listMagasin =depotDAO.findMagasinByCategorieTypeMagasinDepot(Constantes.MAGASIN_CODE_CATEGORIE_STOCKAGE, Constantes.MAGASIN_CODE_TYPE_MP, depot.getId());
			  		   					
				  		   				        
				  		   				        for (int i=0 ; i <listMagasin.size(); i++){
				  		   				        Magasin magasin = listMagasin.get(i);
				  		   				       listStock=stockMPDAO.findAllByMagasin(magasin.getId());
				  		   				        MouvementStockGlobal mouvementStockGlobal = new MouvementStockGlobal();
				  		   				             
				  		   				        mouvementStockGlobal.setDateMouvement(dateChoosercreation.getDate());
				  		   				        mouvementStockGlobal.setDepot(depot);
				  		   				        mouvementStockGlobal.setMagasin(magasin);
				  		   				        mouvementStockGlobal.setDateCreation(new Date());
				  		   				        mouvementStockGlobal.setUtilisateurCreation(utilisateur);
				  		   				        
				  		   				        for (int j=0 ; j <listStock.size(); j++){
				  		   				        StockMP stockmp = listStock.get(j);
				  		   				                 exist = false;
				  		   				            DetailMouvementStock detailMouvementStock = new DetailMouvementStock();
				  		   				            
				  		   				                  for (int k=0 ;k < listMouvementStockGlobal.size(); k++)
				  		   				       {
				  		   				           
				  		   				       MouvementStockGlobal mouvementStockGlobalTmp = listMouvementStockGlobal.get(k);
				  		   				       
				  		   				       
				  		   				  listDetailMouvementStockTmp=mouvementStockGlobalTmp.getDetailMouvementStock();

				  		   				       for (int f= 0 ;f< listDetailMouvementStockTmp.size();f++){

				  		   				      if(mouvementStockGlobalTmp.getDepot().getId() == depot.getId()&& 
				  		   				              mouvementStockGlobalTmp.getMagasin().getId()== magasin.getId() &&
				  		   				              mouvementStockGlobalTmp.getDetailMouvementStock().get(f).getMatierePremier().getId() == stockmp.getMatierePremier().getId() ){
				  		   				          
				  		   				        // valeur =  mouvementStockGlobalTmp.getDetailMouvementStock().get(f).getStockFinaldb();
				  		   				         
				  		   				 valeur= (listDetailMouvementStockTmp.get(f).getInitial().add(listDetailMouvementStockTmp.get(f).getReception()).add(listDetailMouvementStockTmp.get(f).getTransfertEntrees()).subtract((listDetailMouvementStockTmp.get(f).getSorties().add(listDetailMouvementStockTmp.get(f).getTransfertSorties()).add(listDetailMouvementStockTmp.get(f).getCharge()).add(listDetailMouvementStockTmp.get(f).getChargeSupplementaire())))).add(listDetailMouvementStockTmp.get(f).getRetour());
				  		   				listDetailMouvementStockTmp.get(f).setStockFinaldb(valeur);
				  		   			
				  		   				    
				  		   				    exist = true;
				  		   				    
				  		   				      }
				  		   				  
				  		   				       }
				  		   				                    if (exist == true){
				  		   				             mouvementStockGlobalTmp.setDetailMouvementStock(listDetailMouvementStockTmp);
				  		   				              mouvementStockGlobaleDAO.edit(mouvementStockGlobalTmp);
				  		   				             
				  		   				              detailMouvementStock.setMatierePremier(stockmp.getMatierePremier());
				  		   				              detailMouvementStock.setInitial(valeur);
				  		   				              detailMouvementStock.setCharge(BigDecimal.ZERO);
				  		   				              detailMouvementStock.setChargeSupplementaire(BigDecimal.ZERO);
				  		   				              detailMouvementStock.setReception(BigDecimal.ZERO);
				  		   				              detailMouvementStock.setTransfertEntrees(BigDecimal.ZERO);
				  		   				              detailMouvementStock.setTransfertSorties(BigDecimal.ZERO);
				  		   				              detailMouvementStock.setSorties(BigDecimal.ZERO);
				  		   				              detailMouvementStock.setStockFinaldb(BigDecimal.ZERO);
				  		   				              detailMouvementStock.setRetour(BigDecimal.ZERO);
				  		   				              detailMouvementStock.setMouvementStockGlobal(mouvementStockGlobal);
				  		   				              detailMouvementStock.setDateCreation(new Date());
				  		   				     	
				  		   				              detailMouvementStock.setUtilisateurCreation(utilisateur);
				  		   				        listDetailMouvementStock.add(detailMouvementStock);
				  		   				
				  		   				        
				  		   				       }else{
				  		   				               detailMouvementStock.setInitial(stockmp.getStock());
				  		   				              detailMouvementStock.setMatierePremier(stockmp.getMatierePremier());
				  		   				              detailMouvementStock.setCharge(BigDecimal.ZERO);
				  		   				              detailMouvementStock.setChargeSupplementaire(BigDecimal.ZERO);
				  		   				              detailMouvementStock.setReception(BigDecimal.ZERO);
				  		   				              detailMouvementStock.setTransfertEntrees(BigDecimal.ZERO);
				  		   				              detailMouvementStock.setTransfertSorties(BigDecimal.ZERO);
				  		   				              detailMouvementStock.setSorties(BigDecimal.ZERO);
				  		   				              detailMouvementStock.setRetour(BigDecimal.ZERO);
				  		   				              detailMouvementStock.setStockFinaldb(BigDecimal.ZERO);
				  		   				              detailMouvementStock.setMouvementStockGlobal(mouvementStockGlobal);
				  		   				              detailMouvementStock.setDateCreation(new Date());
				  		   				              
				  		   				              detailMouvementStock.setUtilisateurCreation(utilisateur);
				  		   				       
				  		   				              listDetailMouvementStock.add(detailMouvementStock);
				  		   				       }
				  		   				      
				  		   				      }
				  		   				         
				  		   				            
				  		   				        }
				  		   				           mouvementStockGlobal.setDetailMouvementStock(listDetailMouvementStock);
				  		   				              mouvementStockGlobaleDAO.add(mouvementStockGlobal);
				  		   				        }

				  		   				         
				  		   				        charger();
				  		   				         
				  		   				      
				  		   				       
				  		   				             }else {
				  		   				            	 
				  		   				            	 JOptionPane.showMessageDialog(null, "La Date que vous avez saisie et inferireur a la date de fermeture !!","Attention",JOptionPane.ERROR_MESSAGE);
				  		   				            	 
				  		   				                
				  		   				                return;
				  		   				               } 
				  		   								
				  		   								
				  		   								
				  		   							}else
				  		   							{
				  		   								

						  		   					
								  		   					 
									  		   				  Journee journee = new Journee();
										  		               
											  		   	        journee.setUtilisateurCreation(utilisateur);
											  		   	        journee.setDateCreation(new Date());
											  		   	        journee.setDateJournee(dateChoosercreation.getDate());
											  		   	        journee.setDateOvertute(dateChoosercreation.getDate());
											  		   	        journee.setDepot(depot);
											  		   	        journee.setStatue(Constantes.ETAT_STATUT_OVERTE);

											  		   	        journeeDAO.add(journee);
											  		   				
								  		   					
								  		   						listMagasin =depotDAO.findMagasinByCategorieTypeMagasinDepot(Constantes.MAGASIN_CODE_CATEGORIE_STOCKAGE, Constantes.MAGASIN_CODE_TYPE_MP, depot.getId());
								  		   						for(int j=0;j<listMagasin.size();j++)
								  		   						{
								  		   							listDetailMouvementStock.clear();
								  		   							Magasin magasin=listMagasin.get(j);
								  		   							listStock=stockMPDAO.findAllByMagasin(magasin.getId());
								  		   							MouvementStockGlobal mouvementStockGlobal=new MouvementStockGlobal();
								  		   							for(int k=0;k<listStock.size();k++)
								  		   							{
								  		   								StockMP stockmp=listStock.get(k);
								  		   								
								  		   								DetailMouvementStock detailMouvementStock=new DetailMouvementStock();
								  		   								
								  		   								detailMouvementStock.setDateCreation(dateChoosercreation.getDate());
								  		   								detailMouvementStock.setMatierePremier(stockmp.getMatierePremier());
								  		   								detailMouvementStock.setInitial(stockmp.getStock());
								  		   								detailMouvementStock.setUtilisateurCreation(utilisateur);
								  		   								detailMouvementStock.setCharge(BigDecimal.ZERO);
								  		   								detailMouvementStock.setChargeSupplementaire(BigDecimal.ZERO);
								  		   								detailMouvementStock.setReception(BigDecimal.ZERO);
								  		   								detailMouvementStock.setSorties(BigDecimal.ZERO);
								  		   								detailMouvementStock.setStockFinaldb(BigDecimal.ZERO);
								  		   								detailMouvementStock.setTransfertEntrees(BigDecimal.ZERO);
								  		   								detailMouvementStock.setTransfertSorties(BigDecimal.ZERO);
								  		   						    	detailMouvementStock.setRetour(BigDecimal.ZERO);
								  		   								detailMouvementStock.setMouvementStockGlobal(mouvementStockGlobal);
								  		   								listDetailMouvementStock.add(detailMouvementStock);
								  		   									
								  		   								
								  		   								
								  		   							}
								  		   							mouvementStockGlobal.setDateCreation(dateChoosercreation.getDate());
								  		   							mouvementStockGlobal.setDateMouvement(dateChoosercreation.getDate());
								  		   							mouvementStockGlobal.setDepot(depot);
								  		   							mouvementStockGlobal.setMagasin(magasin);
								  		   							mouvementStockGlobal.setUtilisateurCreation(utilisateur);
								  		   							mouvementStockGlobal.setDetailMouvementStock(listDetailMouvementStock);
								  		   							mouvementStockGlobaleDAO.add(mouvementStockGlobal);
								  		   						
								  		   							
								  		   							}
								  		   						
								  		   						
								  		   						charger();

						  		   						
				  		   								
				  		   								
				  		   								
				  		   								
				  		   								
				  		   								
				  		   								
				  		   							}
				  		   							
				  		   							
				  		   						}
				  		   						
				  		   						
				  		   						
				  		   					}else
				  		   					{
				  		   					 
		  		   				            	 JOptionPane.showMessageDialog(null, "Veuillez saisir une date SVP !!","Attention",JOptionPane.ERROR_MESSAGE);
		  		   				            	 
		  		   				                
		  		   				                return;
				  		   						
				  		   					}
				  		   					
				  		   					
				  		   					
				  		   				*/}
				  		   					
				  		   				
				  		   				
				  		   			});
				  		   			btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			
				  		   			JLabel lblCrationJourne = new JLabel("             Cr\u00E9ation Journ\u00E9e");
				  		   			lblCrationJourne.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
				  		   			lblCrationJourne.setBackground(Color.WHITE);
				  		   			lblCrationJourne.setBounds(31, 38, 686, 35);
				  		   			add(lblCrationJourne);
				  		   			
				  		   			Component glue = Box.createGlue();
				  		   			glue.setBounds(950, 164, -42, -47);
				  		   			add(glue);
				  		   			
				  		   			
				  		   		 listJournee=journeeDAO.findAll();
				  			    afficher_tableJournee(listJournee);
				  		   			
	}
	
	
	
	void charger()
	{
		listJournee.clear();
		listJournee=journeeDAO.findAll();
		afficher_tableJournee(listJournee);
		
	}
	
	void initialiser()
	{
		
		
	}
	
	void afficher_tableJournee(List<Journee> listJournee)
	{
		
		

		modeleCat =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Date Journ\u00E9e", "Statut","Depot"
	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false,false
	  		     	};
	  		     	public boolean isCellEditable(int row, int column) {
	  		     		return columnEditables[column];
	  		     	}
	  		     };
	  		   table.setModel(modeleCat);
	  		  // table.getColumnModel().getColumn(2).setPreferredWidth(160);
	  		   //table.getColumnModel().getColumn(3).setPreferredWidth(60);
	        //q table.getColumnModel().getColumn(4).setPreferredWidth(60);
	        
		  int i=0;
			while(i<listJournee.size())
			{	
				
				
				Object []ligne={listJournee.get(i).getDateOvertute() ,listJournee.get(i).getStatue(),listJournee.get(i).getDepot().getLibelle()};

				modeleCat.addRow(ligne);
				i++;
			}
	}
}
