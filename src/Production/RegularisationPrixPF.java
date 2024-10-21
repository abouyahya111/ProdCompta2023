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

import util.CalculePrixEtatStockMPAHB;
import util.CalculePrixEtatStockMPETP;
import util.Constantes;
import util.DateUtils;
import util.JasperUtils;
import util.Utils;

import com.sun.org.apache.bcel.internal.generic.ATHROW;
import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.CoutMPDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.PromotionDAOImpl;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.CoutMPDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.PromotionDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.daoManager.TransferStockPFDAO;
import dao.entity.CompteStockMP;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailMouvementStock;
import dao.entity.DetailProdGen;
import dao.entity.DetailProduction;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.EtatStockMP;
import dao.entity.EtatStockMPAHB;
import dao.entity.EtatStockMPETP;
import dao.entity.FicheEmploye;
import dao.entity.Magasin;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.Promotion;
import dao.entity.StockMP;
import dao.entity.TransferStockMP;
import dao.entity.TransferStockPF;
import dao.entity.Utilisateur;

import java.awt.Component;

import javax.swing.JComboBox;

import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.ScrollPane;


public class RegularisationPrixPF extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleProd;
	private DefaultTableModel	 modeleMP;
	private DefaultTableModel	 modeleEmployeGen;
	private DefaultTableModel	 modeleEmployeProd;
	private DefaultTableModel	 modeleEmployeEmballage;
	private JXTable table;	
////////////////////////////////////////////////////////////// Ahl Brahim ///////////////////////////////////////////////////////	
	
	private  List<EtatStockMPAHB> listEtatStockMPAHB = new ArrayList<EtatStockMPAHB>();
	
	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
//////////////////////////////////////////////////////////////El Nass Tea Packing ///////////////////////////////////////////////////////
	
private  List<EtatStockMPETP> listEtatStockMPETP = new ArrayList<EtatStockMPETP>();


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	private Map< String, Boolean> mapBonProduction = new HashMap<>();
	 List<CoutMP> listCoutMP=new ArrayList<CoutMP>();
	 
	 List<DetailResponsableProd> listEmployeGesnerique=new ArrayList<DetailResponsableProd>();
	 List<DetailProdGen> listEmployeEmballage=new ArrayList<DetailProdGen>();
	 List<DetailProduction> listEmployeProduction=new ArrayList<DetailProduction>();
	 List<DetailTransferStockMP> listDetailTransferStockMP=new ArrayList<DetailTransferStockMP>();
	 List<TransferStockPF> listTransferStockPF=new ArrayList<TransferStockPF>();
	 List<TransferStockMP> listTransferStockMP=new ArrayList<TransferStockMP>();
	 List<DetailTransferProduitFini> listDetailTransferStockPF=new ArrayList<DetailTransferProduitFini>();
	private ImageIcon imgValider;
	private ImageIcon imgInit;
	private ImageIcon imgImprimer;
	private ImageIcon imgRechercher;
	private JDateChooser dateDebutChooser = new JDateChooser();
	private JDateChooser dateFinChooser = new JDateChooser();
	JComboBox combodepot = new JComboBox();
	private Map< Integer, String> mapAvance= new HashMap<>();
	private Map< String, BigDecimal> mapParametre = new HashMap<>();
	private List<Depot> listDepot=new ArrayList<Depot>();
	private List<Production> listProduction=new ArrayList<Production>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Utilisateur utilisateur;
	private ProductionDAO productionDAO;
	private DepotDAO depotdao;
	private CoutMPDAO coutMPDAO;
	private TransferStockMPDAO transferStockMPDAO ;
	private DetailTransferMPDAO detailTransferMPDAO ;
	private TransferStockPFDAO transferStockPFDAO;
	DetailTransferProduitFiniDAO detailTransferProduitFiniDAO;
	private ParametreDAO parametreDAO;
	private PromotionDAO promotiondao = new PromotionDAOImpl();
	private DepotDAO depotDAO;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public RegularisationPrixPF() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1579, 1062);
        try{
        	
        	 utilisateur=AuthentificationView.utilisateur;
        	productionDAO=new ProductionDAOImpl();
        	depotdao=new DepotDAOImpl();
        	coutMPDAO=new CoutMPDAOImpl();
        	transferStockMPDAO=new TransferStockMPDAOImpl();
        	detailTransferMPDAO=new DetailTransferMPDAOImpl();
        	transferStockPFDAO=new TransferStockPFDAOImpl();
        	detailTransferProduitFiniDAO=new DetailTransferProduitFiniDAOImpl();
        	parametreDAO = new ParametreDAOImpl();
        	depotDAO = new DepotDAOImpl();
        	
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
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
					  		     			"Num OF","Date", "Depot","Article","Statut", "Type production"
					  		     	}
					  		     ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     			false,false,false,false,false,false
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
				  		     	scrollPane.setBounds(10, 169, 1030, 338);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	modeleProd =new DefaultTableModel(
				  			     	new Object[][] {
				  			     	},
				  			     	new String[] {
				  			     			"Num OF", "Date","Depot","Article","Statut", "Type production"
				  			     	}
				  			     ) {
				  		  		boolean[] columnEditables = new boolean[] {
				  						false,false,false,false,false,false,true
				  				};
				  				Class[] columnTypes = new Class[] {
				  						String.class,Date.class,String.class,String.class,String.class,String.class
				  					};
				  				  public Class getColumnClass(int columnIndex) {
				  						return columnTypes[columnIndex];
				  					}
				  				public boolean isCellEditable(int row, int column) {
				  					return columnEditables[column];
				  				}
				  			};
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(10, 115, 1030, 54);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblDateDebut = new JLabel("Du :");
				  		     	lblDateDebut.setBounds(192, 12, 31, 24);
				  		     	layeredPane.add(lblDateDebut);
				  		     	lblDateDebut.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	 
				  		     	 JLabel lblDateFin = new JLabel("Au :");
				  		     	 lblDateFin.setBounds(340, 11, 51, 24);
				  		     	 layeredPane.add(lblDateFin);
				  		     	 lblDateFin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnAfficherStock = new JButton();
		btnAfficherStock.setIcon(imgRechercher);
		btnAfficherStock.setBounds(829, 11, 31, 31);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 
			     listProduction=productionDAO.listeProductionTerminerEntreDeuxDate(dateDebutChooser.getDate(), dateFinChooser.getDate());
			afficher_tableProd(listProduction);

			
		  }
		});
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		 
		dateDebutChooser.setBounds(219, 12, 111, 24);
		layeredPane.add(dateDebutChooser);
		
		
		dateFinChooser.setBounds(373, 12, 124, 24);
		layeredPane.add(dateFinChooser);
		  
		  JButton button = new JButton("Initialiser");
		  button.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		
		  		dateDebutChooser.setCalendar(null);
		  		dateFinChooser.setCalendar(null);
		  		
		  	
		  		
		  		
		  	}
		  });
		  button.setFont(new Font("Tahoma", Font.PLAIN, 11));
		  button.setBounds(882, 12, 106, 30);
		  layeredPane.add(button);
		  
		  JButton btnImprimerDetailOf = new JButton("Regler le Prix PF");
		  btnImprimerDetailOf.addActionListener(new ActionListener() {
		  	public void actionPerformed(ActionEvent arg0) {
		  		
				
				listEtatStockMPAHB.clear();
				listEtatStockMPETP.clear();
				
				///////////////////////////////////////////////////// Calcule Prix moyen MP Actuel de magasin AHB et ETP ///////////////////////////////////////////////////////////
				
				
				
				/*
				  for(int i=0;i<listEtatStockMPAHB.size() ; i++) {
				  
					  
				  JOptionPane.showMessageDialog(null, listEtatStockMPAHB.get(i).getMp().getNom()
				  +" ---- "+listEtatStockMPAHB.get(i).getPrixFinale().setScale(6, RoundingMode.HALF_UP));
				  
				  
				  
				  }
				 */
			for(int i=0;i<table.getRowCount();i++)
			{
				
				BigDecimal coutTotalMP=BigDecimal.ZERO;
				BigDecimal coutPF=BigDecimal.ZERO;
				BigDecimal coutTotalDechet=BigDecimal.ZERO;
				BigDecimal coutDechet = BigDecimal.ZERO;
				BigDecimal coutDechetFour = BigDecimal.ZERO;
				BigDecimal coutManquante = BigDecimal.ZERO;
				Parametre PercentagePrixCadeau = parametreDAO.findByCode(Constantes.PERCENTAGE_PRIX_OFFRE);
				BigDecimal coutQuantiteOffre = BigDecimal.ZERO;
				BigDecimal prixMP = BigDecimal.ZERO;
				BigDecimal prixMPPromo = BigDecimal.ZERO;
				BigDecimal PrixOffre = BigDecimal.ZERO;
				Promotion promotion = null;
				Parametre parametre = parametreDAO.findByCode(COUT_SERVICE);
				
				Magasin magasinAhb =depotdao.magasinByCode(MAGASIN_AHB_MP);
				Magasin magasinETP =depotdao.magasinByCode(CODE_MAGASIN_ELNASS_TEA_PACKING);
				
				listEtatStockMPAHB.clear();
				listEtatStockMPETP.clear();
				
			
				
				
		
		//////////////////////////////////////////////////////////////////////Modifier Les prix cout MP Selon le Magasin des MP /////////////////////////////////////////////////////////////////////////////////////////		
				Production production =productionDAO.findByNumOF(table.getValueAt(i, 0).toString(), utilisateur.getCodeDepot());
			    
				if(production!=null)
				{
					
					listEtatStockMPAHB=CalculePrixEtatStockMPAHB.CalculerPrixMP(magasinAhb, production.getDate() ,table.getValueAt(i, 0).toString());
					listEtatStockMPETP=CalculePrixEtatStockMPETP.CalculerPrixMP(magasinETP, production.getDate() , table.getValueAt(i, 0).toString());	

					
					
						/*
						 * for(int i=0;i<listProduction.size();i++) {
						 */
													
		
																
										
										
										
					promotion = null;
					prixMP = BigDecimal.ZERO;
					PrixOffre = BigDecimal.ZERO;
					
					
					
	/////////////////////////////////////////////////// Modifier prix Cout MP ////////////////////////////////////////////////
					
				for(int j=0;j<production.getListCoutMP().size();j++)	
				{
					
					CoutMP coutMP =production.getListCoutMP().get(j);
					
					if(coutMP.getMagasin().getId()==magasinAhb.getId())
					{
					for(int k=0;k<listEtatStockMPAHB.size();k++)
					{
						
						EtatStockMPAHB etatStockMP =listEtatStockMPAHB.get(k);
						if(coutMP.getMatierePremier().getId()==etatStockMP.getMp().getId())
						{
							coutMP.setPrixUnitaire(etatStockMP.getPrixFinale());
							coutMP.setPrixTotal(coutMP.getPrixUnitaire().multiply(coutMP.getQuantConsomme()));
							coutMP.setCoutDechet(coutMP.getPrixUnitaire().multiply(coutMP.getQuantDechet()));
							coutMP.setCoutOffre(coutMP.getPrixUnitaire().multiply(coutMP.getQuantiteOffre()));
							
						}
						
					}
						
					}else if (coutMP.getMagasin().getId()==magasinETP.getId())
					{
						
						for(int k=0;k<listEtatStockMPETP.size();k++)
						{
							
							EtatStockMPETP etatStockMP =listEtatStockMPETP.get(k);
							if(coutMP.getMatierePremier().getId()==etatStockMP.getMp().getId())
							{
								
								coutMP.setPrixUnitaire(etatStockMP.getPrixFinale());
								coutMP.setPrixTotal(coutMP.getPrixUnitaire().multiply(coutMP.getQuantConsomme()));
								coutMP.setCoutDechet(coutMP.getPrixUnitaire().multiply(coutMP.getQuantDechet()));
								coutMP.setCoutOffre(coutMP.getPrixUnitaire().multiply(coutMP.getQuantiteOffre()));
								
								
							}
							
						}	
						
						
					}
					
					coutMPDAO.edit(coutMP);
					
					
				}
				
				
//////////////////////////////////////// Modifier Prix Transfer MP ///////////////////////////////////////////////////
				
			TransferStockMP transferStockMP =transferStockMPDAO.findTransferByCode(production.getNumOF())	;
			
			listDetailTransferStockMP=	transferStockMP.getListDetailTransferMP();
			
			 List<CoutMP> listCoutMPTmp=new ArrayList<CoutMP>();
				
			 listCoutMPTmp= productionDAO.listeCoutMP(production.getId()) ;
			 
			for(int s=0;s<listCoutMPTmp.size();s++)
			{
				prixMP = BigDecimal.ZERO;
				CoutMP coutMPTmp=listCoutMPTmp.get(s);
				
				for(int d=0;d<listDetailTransferStockMP.size();d++)
				{
					DetailTransferStockMP detailTransferStockMP =listDetailTransferStockMP.get(d);
					
					if(coutMPTmp.getMatierePremier().getId()==detailTransferStockMP.getMatierePremier().getId())
					{
						
						detailTransferStockMP.setPrixUnitaire(coutMPTmp.getPrixUnitaire());
						
						detailTransferMPDAO.edit(detailTransferStockMP);
						
						
					}
					
			}
				
//////////////////////////////////////// Calculer coutTotalMP  ///////////////////////////////////////////////////			
				
				if (coutMPTmp.getProdcutionCM().getService().equals(Constantes.TRANSFERE_BL_FACTURE)
						&& coutMPTmp.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)) {
					if (coutMPTmp.getMagasin() != null) {
						if (coutMPTmp.getMagasin().getId() != magasinETP.getId()) {

							prixMP = (coutMPTmp.getQuantConsomme()).multiply(coutMPTmp.getPrixUnitaire());

						}

					}

				} else {
					if (coutMPTmp.getProdcutionCM().getService().equals(Constantes.TRANSFERE_BL_FACTURE)) {

						if (coutMPTmp.getMagasin() != null) {
							if (coutMPTmp.getMagasin().getId() != magasinETP.getId()) {

								prixMP = coutMPTmp.getQuantConsomme().multiply(coutMPTmp.getPrixUnitaire());

							}

						}

					} else {

						prixMP = coutMPTmp.getQuantConsomme().multiply(coutMPTmp.getPrixUnitaire());

					}

				}

				coutDechet = coutMPTmp.getQuantDechet().multiply(coutMPTmp.getPrixUnitaire());
				coutDechetFour = coutMPTmp.getQuantDechetFournisseur().multiply(coutMPTmp.getPrixUnitaire());
				coutManquante = coutMPTmp.getQuantiteManquante().multiply(coutMPTmp.getPrixUnitaire());
				coutQuantiteOffre = coutMPTmp.getQuantiteOffre().multiply(coutMPTmp.getPrixUnitaire());

				coutTotalMP = coutTotalMP.add(prixMP);
				coutTotalDechet = coutTotalDechet.add(coutDechet).add(coutDechetFour).add(coutManquante);
				
				
			}
			
			
			
	/////////////////////////////////// Modifier CoutTotalMP et CoutTotalDechet de production  /////////////////////////////////////////////
			
			production.setCoutTotalMP(coutTotalMP);
			production.setCoutDechet(coutTotalDechet);
			
////////////////////////////////////////Modifier Prix Dechet / Offre Transfer MP ///////////////////////////////////////////////////		
			
			listTransferStockMP.clear();
	int m = 1 , x=1;
	TransferStockMP transferStockMPTMP =transferStockMPDAO.findById(transferStockMP.getId()+m);
	
	if(transferStockMPTMP!=null)
	{
		x=transferStockMPTMP.getId();
		while( transferStockMPTMP.getStatut().contains("SORTIE_PF"))
		{
			
			listTransferStockMP.add(transferStockMPTMP)	;
			
		x=x+1;
		transferStockMPTMP =transferStockMPDAO.findById(x);	
		
		if(transferStockMPTMP==null)
		{
			break;
		}
			
		}
		
	}

			
		for(int l=0;l<listTransferStockMP.size();l++)	
		{
			for(int t=0;t<production.getListCoutMP().size();t++)
			{
				CoutMP coutMP=production.getListCoutMP().get(t);
				
				for(int c=0;c<listTransferStockMP.get(l).getListDetailTransferMP().size();c++)	
				{
					
					DetailTransferStockMP detailTransferStockMP=listTransferStockMP.get(l).getListDetailTransferMP().get(c);
					
					if(coutMP.getMatierePremier().getId()==detailTransferStockMP.getMatierePremier().getId())
					{
						
						detailTransferStockMP.setPrixUnitaire(coutMP.getPrixUnitaire());	
						
						detailTransferMPDAO.edit(detailTransferStockMP);
						
					}
					
					
				}
				
			}
			
		
			
			
		}
		
//////////////////////////////////////// Calculer prix PF ///////////////////////////////////////////////////		
		
		coutPF = coutTotalMP.divide(production.getQuantiteReel(), 6, BigDecimal.ROUND_HALF_UP);	
		
		if (production.getOffre() != null) {

			promotion = promotiondao.findByCode(production.getOffre());
			
			
			if (promotion != null) {
				for (int f = 0; f < promotion.getDetailEstimationPromo().size(); f++) {
				
					prixMPPromo = BigDecimal.ZERO;
					if (production.getService().equals(Constantes.TRANSFERE_BL_FACTURE)) {
						if (promotion.getDetailEstimationPromo().get(f).getMatierePremiere().getCategorieMp()
								.getSubCategorieMp().getCode()
								.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU)) {
							
							
							 List<CoutMP> listCoutMP=new ArrayList<CoutMP>();
							 
							 listCoutMP=productionDAO.listeCoutMP(production.getId());

							for (int k = 0; k < listCoutMP.size(); k++) {

								if (listCoutMP.get(k).getMatierePremier().getCategorieMp()
										.getSubCategorieMp().getCode()
										.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU)
										&& listCoutMP.get(k).getMatierePremier().getId() == promotion
												.getDetailEstimationPromo().get(f).getMatierePremiere().getId()) {
									if (listCoutMP.get(k).getMagasin() != null) {
										if (listCoutMP.get(k).getMagasin().getId()==
												magasinETP.getId()) {
											
											for(int p=0;p<listEtatStockMPETP.size() ; p++)
											{
												if(promotion.getDetailEstimationPromo().get(f).getMatierePremiere().getId()==listEtatStockMPETP.get(p).getMp().getId())
												{
													
												prixMPPromo=listEtatStockMPETP.get(p).getPrixFinale();	
													
												}
												
												
											}

											PrixOffre = PrixOffre.add(prixMPPromo
													.multiply(promotion.getDetailEstimationPromo().get(f).getQuantite())
													.multiply((PercentagePrixCadeau.getValeur()
															.divide(new BigDecimal(100), 6, RoundingMode.UP).add(BigDecimal.ONE))));

										}

									}

								}

							}

						} else {
							/*
							 * StockMP
							 * stockMP=stockMPDAO.findStockByMagasinMP(promotion.getDetailEstimationPromo().
							 * get(i).getMatierePremiere().getId(), magasinElnasTeaPacking.getId());
							 * PrixOffre=PrixOffre.add(stockMP.getPrixUnitaire().multiply(promotion.
							 * getDetailEstimationPromo().get(i).getQuantite()).multiply(new
							 * BigDecimal(1.2)));
							 */
						}

					} else {
						
						
						if(production.getMagasinStockage().getId()== magasinETP.getId())
						{
							
							for(int p=0;p<listEtatStockMPETP.size() ; p++)
							{
								if(promotion.getDetailEstimationPromo().get(f).getMatierePremiere().getId()==listEtatStockMPETP.get(p).getMp().getId())
								{
									
								prixMPPromo=listEtatStockMPETP.get(p).getPrixFinale();	
									
								}
								
								
							}
						}else if(production.getMagasinStockage().getId()== magasinAhb.getId())
						{
							
							
							for(int p=0;p<listEtatStockMPAHB.size() ; p++)
							{
								if(promotion.getDetailEstimationPromo().get(f).getMatierePremiere().getId()==listEtatStockMPAHB.get(p).getMp().getId())
								{
									
								prixMPPromo=listEtatStockMPAHB.get(p).getPrixFinale();	
									
								}
								
								
							}
							
							
							
						}
						
					
						
						
						
						PrixOffre = PrixOffre.add(prixMPPromo
								.multiply(promotion.getDetailEstimationPromo().get(f).getQuantite())
								.multiply(new BigDecimal(1.2)));
					}

				}

				PrixOffre = PrixOffre.divide(production.getArticles().getCentreCout3(), RoundingMode.HALF_UP); // centercout3
																												// est
																												// le
																												// poids
																												// de
																												// carton
			}

		}

		if (production.getService().equals(Constantes.TRANSFERE_BL_FACTURE)) {
			/*
			 * List <CoutMP> listeCoutMPTmp=production.getListCoutMP();
			 * 
			 * for(CoutMP coutMP : listeCoutMPTmp){
			 * 
			 * if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().
			 * equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)){
			 * 
			 * coutPF= coutMP.getPrixUnitaire(); break; } }
			 */

			coutPF = coutPF.add(PrixOffre).add(parametre.getValeur());
		} else {
			coutPF = coutPF;
		}

		
/////////////////////////////////// Modifier CoutTotal de production  /////////////////////////////////////////////
		
		production.setCoutTotal(coutPF.multiply(production.getQuantiteReel()));
		
		
///////////////////////////////////// Modifier Prix PF Transfer PF ///////////////////////////////////////////////////////////////		
		
		TransferStockPF transferStockPF =transferStockPFDAO.findByCodeTransfert(production.getNumOF());
		for(int r =0;r<transferStockPF.getListDetailTransferProduitFini().size();r++)
		{
			
			DetailTransferProduitFini detailTransferProduitFini =transferStockPF.getListDetailTransferProduitFini().get(r);
			detailTransferProduitFini.setPrixUnitaire(coutPF);
			detailTransferProduitFiniDAO.edit(detailTransferProduitFini);
		}
		
		
///////////////////////////////////// Modifier Prix PF Transfer PF (Les MP Transfer en PF Dechet et Offre) ///////////////////////////////////////////////////////////////			
		
		
		int z=1 , l=1;
		
		listTransferStockPF.clear();

		TransferStockPF transferStockPFTMP =transferStockPFDAO.findById(transferStockPF.getId()+z);
		
		if(transferStockPFTMP != null)
		{
			l=transferStockPFTMP.getId();
			while(transferStockPFTMP.getStatut().contains(ETAT_TRANSFER_STOCK_ENTRER_MP))
			{
				
				
				
				
				listTransferStockPF.add(transferStockPFTMP)	;
				
			l=l+1;
			transferStockPFTMP =transferStockPFDAO.findById(l);	
				if(transferStockPFTMP==null)
				{
					break;
				}
			}
		}
		
		
		BigDecimal CoutMPOffre = BigDecimal.ZERO;
		Magasin magasinElnassTeaPackingPFTmp = depotDAO.magasinByCode(CODE_MAGASIN_ELNASS_TEA_PACKING);
		
		// calculer le cout des mp offre
		
		 List<CoutMP> listCoutMP=new ArrayList<CoutMP>();
		 
		 listCoutMP=productionDAO.listeCoutMP(production.getId());
		
		for (int j = 0; j < listCoutMP.size(); j++) {
			CoutMP coutMP = listCoutMP.get(j);
			if (!coutMP.getQuantiteOffre().setScale(6).equals(BigDecimal.ZERO.setScale(6))) {
				if (!coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
						.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)
						&& !coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
								.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU)) {
					if (coutMP.getMagasin() != null) {
						if (coutMP.getMagasin().getId() != magasinElnassTeaPackingPFTmp.getId()) {

							CoutMPOffre = CoutMPOffre.add(coutMP.getQuantiteOffre().multiply(coutMP.getPrixUnitaire()));

						}

					} else {

						CoutMPOffre = CoutMPOffre.add(coutMP.getQuantiteOffre().multiply(coutMP.getPrixUnitaire()));

					}

				}

			}

		}
				

	for(int p=0;p<listCoutMP.size();p++)
	{
		
		
		CoutMP coutMP =listCoutMP.get(p);
		
		
	//	System.out.println(coutMP.getMatierePremier().getNom() +" : "+coutMP.getPrixUnitaire());
		
		
		for(int y=0;y<listTransferStockPF.size();y++)
		{
			
		listDetailTransferStockPF=	listTransferStockPF.get(y).getListDetailTransferProduitFini();
			
			for(int g=0;g<listDetailTransferStockPF.size();g++)
			{
				DetailTransferProduitFini detailTransferProduitFini=listDetailTransferStockPF.get(g);
				
				
				if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
										.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
				{
					
				if((coutMP.getProdcutionCM().getArticles().getCodeArticle() + "_PFO").equals(detailTransferProduitFini.getArticle().getCodeArticle()))	
				{
					
					if(production.getService().equals(Constantes.TRANSFERE_BL_FACTURE))
					{
						BigDecimal coutEnvrac = BigDecimal.ZERO;

						coutEnvrac = (CoutMPOffre
								.add((coutMP.getQuantiteOffre().multiply(coutMP.getPrixUnitaire()))))
										.divide(coutMP.getQuantiteOffre(), 6, RoundingMode.HALF_UP);
						
						coutEnvrac = coutEnvrac.add(parametre.getValeur()).add(PrixOffre);	
						
						detailTransferProduitFini.setPrixUnitaire(coutEnvrac);
						detailTransferProduitFiniDAO.edit(detailTransferProduitFini);
						
						
					}else
					{
						
						
						BigDecimal coutEnvrac = BigDecimal.ZERO;
						coutEnvrac = (CoutMPOffre
								.add((coutMP.getQuantiteOffre().multiply(coutMP.getPrixUnitaire()))))
										.divide(coutMP.getQuantiteOffre(), 6, RoundingMode.HALF_UP);
						
						
						detailTransferProduitFini.setPrixUnitaire(coutEnvrac);
						detailTransferProduitFiniDAO.edit(detailTransferProduitFini);
						
						
					}
					
					
					
					
					
					
					
					
				}
					
					
					
					
					
					
				}
				
				
				
				
				
				
				
			if((coutMP.getMatierePremier().getCode() + "_PFO").equals(detailTransferProduitFini.getArticle().getCodeArticle()))	
			{
				
				if(production.getService().equals(Constantes.TRANSFERE_BL_FACTURE))
				{
					
					if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
							.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE)
							|| coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
									.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
					{
						
						if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
										.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
						{
							
							BigDecimal coutEnvrac = BigDecimal.ZERO;

							coutEnvrac = (CoutMPOffre
									.add((coutMP.getQuantiteOffre().multiply(coutMP.getPrixUnitaire()))))
											.divide(coutMP.getQuantiteOffre(), 6, RoundingMode.HALF_UP);
							
							coutEnvrac = coutEnvrac.add(parametre.getValeur()).add(PrixOffre);	
							
							detailTransferProduitFini.setPrixUnitaire(coutEnvrac);
							detailTransferProduitFiniDAO.edit(detailTransferProduitFini);
							
							
							
							
							
						}else if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
											.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
						{
							if (coutMP.getMagasin() != null) {
								
								if (coutMP.getMagasin().getLibelle()
										.equals(depotDAO.magasinByCode(MAGASIN_AHB_MP).getLibelle())) {
									
									detailTransferProduitFini.setPrixUnitaire(coutMP.getPrixUnitaire());
									detailTransferProduitFiniDAO.edit(detailTransferProduitFini);
									
									
								}
								
								
							}
							
						
							
						}
						
					
						
						
					}else
					{
						
						detailTransferProduitFini.setPrixUnitaire(coutMP.getPrixUnitaire());
						detailTransferProduitFiniDAO.edit(detailTransferProduitFini);
						
						
					}
					
					
				}else
				{
					
					if(coutMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getCode()
							.equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
					{
						
						BigDecimal coutEnvrac = BigDecimal.ZERO;
						coutEnvrac = (CoutMPOffre
								.add((coutMP.getQuantiteOffre().multiply(coutMP.getPrixUnitaire()))))
										.divide(coutMP.getQuantiteOffre(), 6, RoundingMode.HALF_UP);
						
						
						detailTransferProduitFini.setPrixUnitaire(coutEnvrac);
						detailTransferProduitFiniDAO.edit(detailTransferProduitFini);
						
						
						
						
					}else
					{
						
						

						detailTransferProduitFini.setPrixUnitaire(coutMP.getPrixUnitaire());
						detailTransferProduitFiniDAO.edit(detailTransferProduitFini);
						
						
					}
					
					
					
					
					
				}
				
			}else
			{
				
			if((coutMP.getMatierePremier().getCode() + "_PFD").equals(detailTransferProduitFini.getArticle().getCodeArticle()))	
			{
				
				detailTransferProduitFini.setPrixUnitaire(coutMP.getPrixUnitaire());
				detailTransferProduitFiniDAO.edit(detailTransferProduitFini);
				
			}
				
			}
			
			}
			
				
		}
		
		
	}

		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
		
		productionDAO.edit(production);
				
				
	/*}*/
				
				
				
				
		 		
		  		
		  		
				}

			}
			
			JOptionPane.showMessageDialog(null, "Les prix production Modifi�");	
			
			
		  	}
		  });
		  btnImprimerDetailOf.setBounds(393, 518, 174, 24);
		  add(btnImprimerDetailOf);
		  
		  JLabel label = new JLabel("     La R\u00E9gularisation Des Prix De PF ");
		  label.setFont(new Font("Tahoma", Font.BOLD, 45));
		  label.setBounds(137, 25, 844, 47);
		  add(label);
		
		
	
				  		     
				  		 
	}
	

	
	
	
void afficher_tableProd(List<Production> listProduction)
	{
		intialiserTableau();
		 
			for (int i=0;i<listProduction.size();i++)
			{	
				
				//Object [] ficheEmploye=(Object[]) listFicheEmploye.get(i);
				Production production=listProduction.get(i);
				
				
				Object []ligne={production.getNumOF(),production.getDate_debFabPre(), production.getMagasinPF().getDepot().getLibelle(),production.getArticles().getLiblle(),production.getStatut(),production.getService()};

				modeleProd.addRow( ligne);
			
			}
			
		
	}



void intialiserTableau(){
	modeleProd =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Num OF","Date", "Depot","Article","Statut", "Type production"
		     	}
		     ) {
		boolean[] columnEditables = new boolean[] {
				false,false,false,false,false,false,true
		};
		Class[] columnTypes = new Class[] {
				String.class,Date.class,String.class,String.class,String.class,String.class
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

 
}
}
