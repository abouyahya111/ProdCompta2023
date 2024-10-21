package presentation.stockPF;

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
import util.JasperUtils;
import util.NumberUtils;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.MouvementStockGlobalDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.MouvementStockGlobalDAO;
import dao.daoManager.StockPFDAO;
import dao.entity.Articles;
import dao.entity.Depot;
import dao.entity.DetailTransferProduitFini;
import dao.entity.EtatStockPF;
import dao.entity.FamilleArticlePF;
import dao.entity.Magasin;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.Utilisateur;

import java.awt.Component;
import com.toedter.calendar.JDateChooser;
import java.util.Locale;


public class AfficherStockPF extends JLayeredPane {
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;
	private ImageIcon imgModifier;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private JButton btnRechercher;
	
	
	private JComboBox<String> comboDepot=new JComboBox();
	private  JComboBox<String> comboMagasin=new JComboBox();

	private Map< String, Integer> mapMagasin = new HashMap<>();
	private Map< String, Magasin> mapMagasinTmp= new HashMap<>();
	private Utilisateur utilisateur;
	
	private Map< String, Depot> mapDepot = new HashMap<>();
	private List<Depot> listDepot =new ArrayList<Depot>();
	private List<StockPF> listStockPF=new ArrayList<StockPF>();
	private List<StockPF> listStockPFTmp=new ArrayList<StockPF>();
	private List<StockPF> listStockPFAfficher=new ArrayList<StockPF>();
	
	private DepotDAO depotDAO;
	private StockPFDAO stockPFDAO;
	private List<EtatStockPF> listEtatStockPF =new ArrayList<EtatStockPF>();
	private MouvementStockGlobalDAO mouvementStockGlobaleDAO;
	private DetailTransferProduitFiniDAO detailTransferStockPFDAO;
	JDateChooser datedebut = new JDateChooser();
	JDateChooser datefin = new JDateChooser();
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AfficherStockPF() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1487, 825);
        try{
        	
        	
        	depotDAO=new DepotDAOImpl();
        	stockPFDAO=new StockPFDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;
        	mouvementStockGlobaleDAO=new MouvementStockGlobalDAOImpl();
         	detailTransferStockPFDAO=new DetailTransferProduitFiniDAOImpl();

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion √† la base de donn√©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
        comboDepot.addItem("");
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp.png"));
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
				  		     intialiserTableau();
				  		  
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 130, 1140, 396);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		  /*   	listDepot = depotDAO.findAll();	
				  		   	Magasin magasin=depotDAO.MagasinByTypeMagasinStkAttente(Constantes.MAGASIN_CODE_TYPE_MP_ATT);
					  		      int i=0;
					  		      	while(i<listDepot.size())
					  		      		{	
					  		      			Depot depot=listDepot.get(i);
					  		      			if(magasin!=null)
					  		      			{
					  		      				if(depot.getId()!=magasin.getDepot().getId())
					  		      				{
					  		      				mapDepot.put(depot.getLibelle(), i);
						  		      			comboDepot.addItem(depot.getLibelle());
					  		      				}
					  		      				
					  		      			}else
					  		      			{
					  		      			mapDepot.put(depot.getLibelle(), i);
					  		      			comboDepot.addItem(depot.getLibelle());
					  		      			}
					  		      		
					  		      			i++;
					  		      		}*/
				  		     	
				  		     //	comboDepot.addItem("");
				  		     	
				  		     	
				  		     	
				  		  
				  	        
				  		     	
				  		     	
					  		      if(!utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_SIEGE)	) {
					  		    	Depot depot=  depotDAO.findByCode(utilisateur.getCodeDepot());
					  	    		comboDepot.addItem(depot.getLibelle());
					  	    		mapDepot.put(depot.getLibelle(), depot);
					  	    		
					  	    	/*
					  	    		List<Magasin> 	listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_MP);
						  		      if(listMagasin!=null){
						  		    	  
						  		    	  int j=0;
							  		      	while(j<listMagasin.size())
							  		      		{	
							  		      			Magasin magasin=listMagasin.get(j);
							  		      			comboMagasin.addItem(magasin.getLibelle());
							  		      			mapMagasin.put(magasin.getLibelle(), magasin.getId());
							  		      			j++;
							  		     
							  		      		
							  		      		}
						  		      }
						  		      
						  		      */
					  	    		
					  	    }else {
					  	    	
					  	    	listDepot = depotDAO.findAll();	
					  		      int i=0;
					  		      	while(i<listDepot.size())
					  		      		{	
					  		      			Depot depot=listDepot.get(i);
					  		      			mapDepot.put(depot.getLibelle(), depot);
					  		      			comboDepot.addItem(depot.getLibelle());
					  		      			i++;
					  		      		}
					  	    	
					  	    }
					  		      	
					  		      comboDepot.addItemListener(new ItemListener() {
					  		     	 	public void itemStateChanged(ItemEvent e) {
					  		     	 	
					  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
					  		     	 	 List<Magasin> listMagasin=new ArrayList<Magasin>();
						  		     	  	 // comboGroupe = new JComboBox();
					  		     	 	comboMagasin.removeAllItems();
					  		     	 Depot depot=new Depot();
					  		     	 	//comboGroupe.addItem("");
					  		     	 	if(!comboDepot.getSelectedItem().equals(""))
						  		     	  	   	 depot =mapDepot.get(comboDepot.getSelectedItem());
								  		       
						  		     	  		listMagasin = depotDAO.listeMagasinByTypeMagasinDepot(depot.getId(), Constantes.MAGASIN_CODE_TYPE_PF);
								  		      if(listMagasin!=null){
								  		    	  
								  		    	  int j=0;
									  		      	while(j<listMagasin.size())
									  		      		{	
									  		      			Magasin magasin=listMagasin.get(j);
									  		      			comboMagasin.addItem(magasin.getLibelle());
									  		      			mapMagasin.put(magasin.getLibelle(), magasin.getId());
									  		      		   mapMagasinTmp.put(magasin.getLibelle(), magasin);
									  		      			j++;
									  		      		}
								  		      }
					  		     	 	 }
					  		     	 	}
					  		     	 });
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Produits Fini  ");
				  		     	titledSeparator.setBounds(9, 101, 1140, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 36, 1140, 54);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("D\u00E9pot :");
				  		     	lblMachine.setBounds(543, 11, 48, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	
				  		     	 
				  		     	 comboDepot.setBounds(586, 11, 144, 24);
				  		     	 layeredPane.add(comboDepot);
				  		     	 
				  		     	 
				  		     	 JLabel lblGroupe = new JLabel("Magasin :");
				  		     	 lblGroupe.setBounds(756, 11, 102, 24);
				  		     	 layeredPane.add(lblGroupe);
				  		     	 lblGroupe.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		     	
				  		     	 comboMagasin.setBounds(823, 12, 144, 24);
				  		     	 layeredPane.add(comboMagasin);
		
		JButton btnAfficherStock = new JButton("Afficher Stock");
		btnAfficherStock.setBounds(1017, 12, 113, 23);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(comboDepot.getSelectedItem().equals(""))	{
				JOptionPane.showMessageDialog(null, "Il faut choisir un magasin", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else {
				
				/* ******************************************** regler Stock  ********************************************** */
				
	    		/*listEtatStockPF.clear();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFInitial =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFAchat =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFAchatGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    		 
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFProduction =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFProductionGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    		 
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFSortie =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFSortieGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    		 
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFAvoir =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFAvoirGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFAllPFTransfer =new ArrayList<DetailTransferProduitFini>();
	    		 
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFEntrer =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFEntrerGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    		
	    		
	    		 SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/YYYY");
		    		BigDecimal montantInitial=new BigDecimal(0);
		    		BigDecimal quantiteTotalInitial=new BigDecimal(0);
		    		BigDecimal PrixInitial=new BigDecimal(0);
		    		BigDecimal montantachat=new BigDecimal(0);
		    		BigDecimal quantiteTotalachat=new BigDecimal(0);
		    		BigDecimal quantiteTotalvente=new BigDecimal(0);
		    		BigDecimal montantvente=new BigDecimal(0);
		    		BigDecimal quantiteTotalavoir=new BigDecimal(0);
		    		BigDecimal montantavoir=new BigDecimal(0);
		    		BigDecimal quantiteTotalEntrer=new BigDecimal(0);
		    		BigDecimal montantEntrer=new BigDecimal(0);
		    		
		    		BigDecimal quantiteTotalproduction=new BigDecimal(0);
		    		BigDecimal montantproduction=new BigDecimal(0);
		    		
		    		BigDecimal quantiteTotalFinale=new BigDecimal(0);
		    		BigDecimal montantFinale=new BigDecimal(0);
	    		boolean trouve=false;
	    		Articles article=null;
	    		FamilleArticlePF familleArticle=null;
	    		try {
	    			Date dateDebut = new SimpleDateFormat("yyyy-MM-dd").parse("2018"+"-01-01");
	    			Magasin magasin=mapMagasinTmp.get(comboMagasin.getSelectedItem());
	    		
		    		if(magasin!=null)
		    		{
		    			
			    		
		    			listDetailTransferStockPFInitial=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateDebut, new Date(), article, Constantes.ETAT_TRANSFER_STOCK_INITIAL,magasin, familleArticle);
			    		
			    		listDetailTransferStockPFAchat=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateDebut, new Date(), article,  Constantes.ETAT_TRANSFER_STOCK_ACHAT,magasin, familleArticle);
			    		listDetailTransferStockPFAchatGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateDebut, new Date(), article,  Constantes.ETAT_TRANSFER_STOCK_ACHAT,magasin, familleArticle);
			    		
			    		listDetailTransferStockPFProduction=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateDebut, new Date(), article,  Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE,magasin, familleArticle);
			    		listDetailTransferStockPFProductionGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateDebut, new Date(), article,  Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE,magasin, familleArticle);
			    		
			    		
			    		listDetailTransferStockPFSortie=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateDebut, new Date(), article,  Constantes.ETAT_TRANSFER_STOCK_VENTE,magasin, familleArticle);
			    		listDetailTransferStockPFSortieGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateDebut, new Date(), article,  Constantes.ETAT_TRANSFER_STOCK_VENTE,magasin, familleArticle);
			    		
			    		

			    		listDetailTransferStockPFEntrer=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateDebut, new Date(), article,  Constantes.ETAT_TRANSFER_STOCK_ENTRER_MP,magasin, familleArticle);
			    		listDetailTransferStockPFEntrerGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateDebut, new Date(), article,  Constantes.ETAT_TRANSFER_STOCK_ENTRER_MP,magasin, familleArticle);
			    		
			    		
			    		
			    		listDetailTransferStockPFAvoir=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(dateDebut, new Date(), article,  Constantes.ETAT_TRANSFER_STOCK_AVOIR,magasin, familleArticle);
			    		listDetailTransferStockPFAvoirGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(dateDebut, new Date(), article,  Constantes.ETAT_TRANSFER_STOCK_AVOIR,magasin, familleArticle);
			    		listDetailTransferStockPFAllPFTransfer=detailTransferStockPFDAO.findAllTransferStockPFGroupeByByArticleIdSouFamille(magasin);
			    		
			    		
			    		for(int d=0;d<listDetailTransferStockPFAllPFTransfer.size();d++)
			    		{
			    			DetailTransferProduitFini detailtransferstockpf=listDetailTransferStockPFAllPFTransfer.get(d);
			    			EtatStockPF etatstock=new EtatStockPF();
			    			etatstock.setArticle(detailtransferstockpf.getArticle());
			    			etatstock.setSousFamille(detailtransferstockpf.getSousFamille());
			    			etatstock.setFamilleArticle(detailtransferstockpf.getSousFamille().getFamileArticlePF());
			    			etatstock.setQuantiteInitial(BigDecimal.ZERO);
			    			etatstock.setPrixInitial(BigDecimal.ZERO);
			    			etatstock.setMontantInitial(BigDecimal.ZERO);
			    			etatstock.setQuantiteAchat(BigDecimal.ZERO);
			    			etatstock.setPrixAchat(BigDecimal.ZERO);
			    			etatstock.setMontantAchat(BigDecimal.ZERO);
			    			etatstock.setQuantiteProduction(BigDecimal.ZERO);
			    			etatstock.setPrixProduction(BigDecimal.ZERO);
			    			etatstock.setMontantProduction(BigDecimal.ZERO);
			    			etatstock.setQuantiteSortie(BigDecimal.ZERO);
			    			etatstock.setPrixSortie(BigDecimal.ZERO);
			    			etatstock.setMontantSortie(BigDecimal.ZERO);
			    			etatstock.setQuantiteEntrer(BigDecimal.ZERO);
			    			etatstock.setPrixEntrer(BigDecimal.ZERO);
			    			etatstock.setMontantEntrer(BigDecimal.ZERO);
			    			etatstock.setQuantiteAvoir(BigDecimal.ZERO);
			    			etatstock.setPrixAvoir(BigDecimal.ZERO);
			    			etatstock.setMontantAvoir(BigDecimal.ZERO);
			    			etatstock.setMarge(BigDecimal.ZERO);
			    			listEtatStockPF.add(etatstock);
			    			
			    		}
			    		
			    		
			    		
			    		
			    		
			    		// charger list Etat stock Articles initialiser les enregistrement des achats et ventes par zero
			    		for(int i=0;i<listDetailTransferStockPFInitial.size();i++)
			    		{
			    			
			    			
			    			DetailTransferProduitFini detailtransferstockpf=listDetailTransferStockPFInitial.get(i);
			    			
			    			EtatStockPF etatstock=new EtatStockPF();
			    			etatstock.setArticle(detailtransferstockpf.getArticle());
			    			etatstock.setSousFamille(detailtransferstockpf.getSousFamille());
			    			etatstock.setQuantiteInitial(detailtransferstockpf.getQuantite());
			    			
			    		
			    				
			    			
			    				PrixInitial=detailtransferstockpf.getPrixUnitaire();
			    			
			    				montantInitial=detailtransferstockpf.getQuantite().multiply(detailtransferstockpf.getPrixUnitaire());
			    			
			    			   quantiteTotalInitial=detailtransferstockpf.getQuantite();
			    			
			    			
			    			etatstock.setQuantiteAchat(BigDecimal.ZERO);
			    			etatstock.setPrixAchat(BigDecimal.ZERO);
			    			etatstock.setMontantAchat(BigDecimal.ZERO);
			    			etatstock.setQuantiteProduction(BigDecimal.ZERO);
			    			etatstock.setPrixProduction(BigDecimal.ZERO);
			    			etatstock.setMontantProduction(BigDecimal.ZERO);
			    			etatstock.setQuantiteSortie(BigDecimal.ZERO);
			    			etatstock.setPrixSortie(BigDecimal.ZERO);
			    			etatstock.setMontantSortie(BigDecimal.ZERO);
			    			etatstock.setQuantiteAvoir(BigDecimal.ZERO);
			    			etatstock.setPrixAvoir(BigDecimal.ZERO);
			    			etatstock.setMontantAvoir(BigDecimal.ZERO);
			    			
			    			listEtatStockPF.add(etatstock);
			    			
			    			if(!montantInitial.equals(BigDecimal.ZERO) && !quantiteTotalInitial.equals(BigDecimal.ZERO))
				    		{
				    			
				    			for(int p=0;p<listEtatStockPF.size();p++)
				    	    	{
				    				if(listEtatStockPF.get(p).getArticle().equals(listDetailTransferStockPFInitial.get(i).getArticle()) && listEtatStockPF.get(p).getSousFamille().equals(listDetailTransferStockPFInitial.get(i).getSousFamille()) )
					    			{
					    				EtatStockPF etatstockpf=listEtatStockPF.get(p);
					    				etatstockpf.setQuantiteInitial(quantiteTotalInitial);
					    				etatstockpf.setPrixInitial(PrixInitial);
					    				etatstockpf.setMontantInitial(montantInitial);
					    				listEtatStockPF.set(p, etatstockpf);
					    			
					    				
					    			}
				    	    	}
				    			
				    			
				    			
				    		}
			    			
			    			
			    			
			    		}
			    		
			    		
			    		// calculer le prix moyen et quantite achat
			    		
			    		
			    	for(int j=0;j<listDetailTransferStockPFAchatGroupebyPF.size();j++)
			    	{
			    		montantachat=new BigDecimal(0);
			    		quantiteTotalachat=new BigDecimal(0);
			    		boolean existe=false;
			    			
			    	for(int k=0;k<listDetailTransferStockPFAchat.size();k++)
			    	{
			    		
			    		if(listDetailTransferStockPFAchatGroupebyPF.get(j).getArticle().equals(listDetailTransferStockPFAchat.get(k).getArticle()) && listDetailTransferStockPFAchatGroupebyPF.get(j).getSousFamille().equals(listDetailTransferStockPFAchat.get(k).getSousFamille()))
			    		{
			    			montantachat=montantachat.add(listDetailTransferStockPFAchat.get(k).getPrixUnitaire().multiply(listDetailTransferStockPFAchat.get(k).getQuantite()));
			    			quantiteTotalachat=quantiteTotalachat.add(listDetailTransferStockPFAchat.get(k).getQuantite());
			    			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
			    			
			    		}
			    		
			    		
			    	}
			    		if(!montantachat.equals(BigDecimal.ZERO) && !quantiteTotalachat.equals(BigDecimal.ZERO))
			    		{
			    			
			    			for(int i=0;i<listEtatStockPF.size();i++)
			    	    	{
			    				if(listEtatStockPF.get(i).getArticle().equals(listDetailTransferStockPFAchatGroupebyPF.get(j).getArticle()) && listEtatStockPF.get(i).getSousFamille().equals(listDetailTransferStockPFAchatGroupebyPF.get(j).getSousFamille()) )
				    			{
				    				EtatStockPF etatstockpf=listEtatStockPF.get(i);
				    				etatstockpf.setQuantiteAchat(quantiteTotalachat);
				    				etatstockpf.setPrixAchat((montantachat.divide(quantiteTotalachat,6,RoundingMode.DOWN)));
				    				etatstockpf.setMontantAchat(etatstockpf.getQuantiteAchat().multiply(etatstockpf.getPrixAchat()));
				    				listEtatStockPF.set(i, etatstockpf);
				    			
				    				
				    			}
			    	    	}
			    			
			    			
			    			
			    		}
			    		
			    	}
			    	
			    	
		    	// calculer Quantite Production et le prix moyen
			    	
			    
			    	
			    	for(int i=0;i<listDetailTransferStockPFProductionGroupebyPF.size();i++)
			    	{
			    		quantiteTotalproduction=BigDecimal.ZERO;
			    		montantproduction=new BigDecimal(0);
			    		
			    		for(int j=0;j<listDetailTransferStockPFProduction.size();j++)
			    		{
			    			
			    			if(listDetailTransferStockPFProductionGroupebyPF.get(i).getArticle().equals(listDetailTransferStockPFProduction.get(j).getArticle()) && listDetailTransferStockPFProductionGroupebyPF.get(i).getSousFamille().equals(listDetailTransferStockPFProduction.get(j).getSousFamille()))
			    			{
			    				
			    				montantproduction=montantproduction.add(listDetailTransferStockPFProduction.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFProduction.get(j).getQuantite()));
			    				quantiteTotalproduction=quantiteTotalproduction.add(listDetailTransferStockPFProduction.get(j).getQuantite());
			    				
			    				
			    			}
			    			
			    			
			    		}
			    		
			    		if(!montantproduction.equals(BigDecimal.ZERO) && !quantiteTotalproduction.equals(BigDecimal.ZERO))
			    		{
			    			
			    			
			    		   	for(int k=0;k<listEtatStockPF.size();k++)
			    	    	{
			    	    		
			    	    	
			    	    		if(listEtatStockPF.get(k).getArticle().equals(listDetailTransferStockPFProductionGroupebyPF.get(i).getArticle()) && listEtatStockPF.get(k).getSousFamille().equals(listDetailTransferStockPFProductionGroupebyPF.get(i).getSousFamille()))
			    	    		{
			    	    			
			    	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
			    	    			etatstockpf.setQuantiteProduction(quantiteTotalproduction);
			    	    			etatstockpf.setPrixProduction(montantproduction.divide(quantiteTotalproduction,6,RoundingMode.DOWN));
			    	    			etatstockpf.setMontantProduction(quantiteTotalproduction.multiply(montantproduction.divide(quantiteTotalproduction,6,RoundingMode.DOWN)));
			    	    			
			    	    			listEtatStockPF.set(k, etatstockpf);
			    	    		}
			    	    		
			    	    		
			    	    	}
			    			
			    		}
			    		
			    		
			    		
			    	}
			    	
			    	
			    	
			    	
			    	// calculer Quantite Vente et le prix moyen
			    	
			    	
			    	
			    	for(int i=0;i<listDetailTransferStockPFSortieGroupebyPF.size();i++)
			    	{
			    		quantiteTotalvente=BigDecimal.ZERO;
			    		montantvente=new BigDecimal(0);
			    		
			    		for(int j=0;j<listDetailTransferStockPFSortie.size();j++)
			    		{
			    			
			    			if(listDetailTransferStockPFSortieGroupebyPF.get(i).getArticle().equals(listDetailTransferStockPFSortie.get(j).getArticle()) && listDetailTransferStockPFSortieGroupebyPF.get(i).getSousFamille().equals(listDetailTransferStockPFSortie.get(j).getSousFamille()))
			    			{
			    				
			    				montantvente=montantvente.add(listDetailTransferStockPFSortie.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFSortie.get(j).getQuantite()));
			    				quantiteTotalvente=quantiteTotalvente.add(listDetailTransferStockPFSortie.get(j).getQuantite());
			    				
			    				
			    			}
			    			
			    			
			    		}
			    		
			    		if(!quantiteTotalvente.equals(BigDecimal.ZERO))
			    		{
			    			
			    			
			    		   	for(int k=0;k<listEtatStockPF.size();k++)
			    	    	{
			    	    		
			    	    	
			    	    		if(listEtatStockPF.get(k).getArticle().equals(listDetailTransferStockPFSortieGroupebyPF.get(i).getArticle()) && listEtatStockPF.get(k).getSousFamille().equals(listDetailTransferStockPFSortieGroupebyPF.get(i).getSousFamille()))
			    	    		{
			    	    			
			    	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
			    	    			etatstockpf.setQuantiteSortie(quantiteTotalvente);
			    	    			etatstockpf.setPrixSortie(montantvente.divide(quantiteTotalvente,6,RoundingMode.DOWN));
			    	    			etatstockpf.setMontantSortie(quantiteTotalvente.multiply(montantvente.divide(quantiteTotalvente,6,RoundingMode.DOWN)));
			    	    			
			    	    			listEtatStockPF.set(k, etatstockpf);
			    	    		}
			    	    		
			    	    		
			    	    	}
			    			
			    			
			    		}
			    		
			    		
			    		
			    	}
			    	
	    	// calculer Quantite Transfer Entrer et le prix moyen
			    	
			    	
			    	
			    	for(int i=0;i<listDetailTransferStockPFEntrerGroupebyPF.size();i++)
			    	{
			    		quantiteTotalEntrer=BigDecimal.ZERO;
			    		montantEntrer=new BigDecimal(0);
			    		
			    		for(int j=0;j<listDetailTransferStockPFEntrer.size();j++)
			    		{
			    			
			    			if(listDetailTransferStockPFEntrerGroupebyPF.get(i).getArticle().equals(listDetailTransferStockPFEntrer.get(j).getArticle()) && listDetailTransferStockPFEntrerGroupebyPF.get(i).getSousFamille().equals(listDetailTransferStockPFEntrer.get(j).getSousFamille()))
			    			{
			    				
			    				montantEntrer=montantEntrer.add(listDetailTransferStockPFEntrer.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFEntrer.get(j).getQuantite()));
			    				quantiteTotalEntrer=quantiteTotalEntrer.add(listDetailTransferStockPFEntrer.get(j).getQuantite());
			    				
			    				
			    			}
			    			
			    			
			    		}
			    		
			    		if(!quantiteTotalEntrer.equals(BigDecimal.ZERO))
			    		{
			    			
			    			
			    		   	for(int k=0;k<listEtatStockPF.size();k++)
			    	    	{
			    	    		
			    	    	
			    	    		if(listEtatStockPF.get(k).getArticle().equals(listDetailTransferStockPFEntrerGroupebyPF.get(i).getArticle()) && listEtatStockPF.get(k).getSousFamille().equals(listDetailTransferStockPFEntrerGroupebyPF.get(i).getSousFamille()))
			    	    		{
			    	    			
			    	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
			    	    			etatstockpf.setQuantiteEntrer(quantiteTotalEntrer);
			    	    			etatstockpf.setPrixEntrer(montantEntrer.divide(quantiteTotalEntrer,6,RoundingMode.DOWN));
			    	    			etatstockpf.setMontantEntrer(quantiteTotalEntrer.multiply(montantEntrer.divide(quantiteTotalEntrer,6,RoundingMode.DOWN)));
			    	    			
			    	    			listEtatStockPF.set(k, etatstockpf);
			    	    		}
			    	    		
			    	    		
			    	    	}
			    			
			    			
			    		}
			    		
			    	}
			    	
			    	
		// calculer Quantite avoir et le prix moyen
			    	
			    	
			    	
			    	for(int i=0;i<listDetailTransferStockPFAvoirGroupebyPF.size();i++)
			    	{
			    		quantiteTotalavoir=BigDecimal.ZERO;
			    		montantavoir=new BigDecimal(0);
			    		
			    		for(int j=0;j<listDetailTransferStockPFAvoir.size();j++)
			    		{
			    			
			    			if(listDetailTransferStockPFAvoirGroupebyPF.get(i).getArticle().equals(listDetailTransferStockPFAvoir.get(j).getArticle()) && listDetailTransferStockPFAvoirGroupebyPF.get(i).getSousFamille().equals(listDetailTransferStockPFAvoir.get(j).getSousFamille()))
			    			{
			    				
			    				montantavoir=montantavoir.add(listDetailTransferStockPFAvoir.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFAvoir.get(j).getQuantite()));
			    				quantiteTotalavoir=quantiteTotalavoir.add(listDetailTransferStockPFAvoir.get(j).getQuantite());
			    				
			    				
			    			}
			    			
			    			
			    		}
			    		
			    		if(!quantiteTotalavoir.equals(BigDecimal.ZERO))
			    		{
			    			
			    			
			    		   	for(int k=0;k<listEtatStockPF.size();k++)
			    	    	{
			    	    		
			    	    	
			    	    		if(listEtatStockPF.get(k).getArticle().equals(listDetailTransferStockPFAvoirGroupebyPF.get(i).getArticle()) && listEtatStockPF.get(k).getSousFamille().equals(listDetailTransferStockPFAvoirGroupebyPF.get(i).getSousFamille()))
			    	    		{
			    	    			
			    	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
			    	    			etatstockpf.setQuantiteAvoir(quantiteTotalavoir);
			    	    			etatstockpf.setPrixAvoir(montantavoir.divide(quantiteTotalavoir,6,RoundingMode.DOWN));
			    	    			etatstockpf.setMontantAvoir(quantiteTotalavoir.multiply(montantavoir.divide(quantiteTotalavoir,6,RoundingMode.DOWN)));
			    	    			
			    	    			listEtatStockPF.set(k, etatstockpf);
			    	    		}
			    	    		
			    	    		
			    	    	}
			    			
			    			
			    		}
			    		
			    		
			    		
			    	}
			    	
			    	
			    
			    	
			    	// Calculer Stock Finale
			    	
			    	
				   	for(int i=0;i<listEtatStockPF.size();i++)
			    	{
				   	 BigDecimal prixMoyen=BigDecimal.ZERO;
				   	 BigDecimal QuantiteTotal=BigDecimal.ZERO;
				   		   quantiteTotalFinale=BigDecimal.ZERO;
				   		    montantFinale=BigDecimal.ZERO;
			    			EtatStockPF etatstockpf=listEtatStockPF.get(i);
			    			etatstockpf.setQuantiteFinale((etatstockpf.getQuantiteInitial().add(etatstockpf.getQuantiteAchat().add(etatstockpf.getQuantiteProduction()).add(etatstockpf.getQuantiteEntrer()))).subtract(etatstockpf.getQuantiteSortie().add(etatstockpf.getQuantiteAvoir())));
			    			quantiteTotalFinale=quantiteTotalFinale.add(etatstockpf.getQuantiteInitial().add(etatstockpf.getQuantiteAchat().add(etatstockpf.getQuantiteProduction()).add(etatstockpf.getQuantiteEntrer())).subtract(etatstockpf.getQuantiteAvoir().add(etatstockpf.getQuantiteSortie())));
			    			montantFinale=etatstockpf.getMontantInitial().add(etatstockpf.getMontantAchat().add(etatstockpf.getMontantProduction().add(etatstockpf.getMontantEntrer())));
			    			QuantiteTotal=etatstockpf.getQuantiteInitial().add(etatstockpf.getQuantiteAchat().add(etatstockpf.getQuantiteProduction().add(etatstockpf.getQuantiteEntrer())));
	if(!QuantiteTotal.setScale(2, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP)))
	{
		prixMoyen=montantFinale.divide(QuantiteTotal, 6, RoundingMode.DOWN);
		etatstockpf.setPrixFinale(prixMoyen.setScale(6,  RoundingMode.DOWN));
		etatstockpf.setMontantFinale(etatstockpf.getPrixFinale().multiply(etatstockpf.getQuantiteFinale()));
	}else
	{
		etatstockpf.setPrixFinale(prixMoyen.setScale(6,  RoundingMode.DOWN));
		
	}
			    			
			    			
			    				
				    			
			    		
			    			
			    			if(etatstockpf.getPrixSortie().setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6,  RoundingMode.DOWN)))
			    			{
			    				etatstockpf.setMarge(BigDecimal.ZERO);
			    				
			    			}else
			    			{
			    				etatstockpf.setMarge((etatstockpf.getPrixSortie().subtract(etatstockpf.getPrixInitial())).divide(etatstockpf.getPrixSortie(), RoundingMode.DOWN));
			    			}
			    			
			    			
			    			listEtatStockPF.set(i, etatstockpf);
			    		
			    		
			    	}
				   	*/
				   
			    	

	    		
	    		detailTransferStockPFDAO.ViderSession();
	    		
	    		
	    		listEtatStockPF.clear();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFInitial =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFAchat =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFAchatGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    		 
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFProduction =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFProductionGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    		 
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFSortie =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFSortieGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    		 
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFEntrer =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFEntrerGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    		 
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFAvoir =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFAvoirGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    		 
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFAvoirClientPF =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFAvoirClientPFGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    		
	    		 
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFAllPFTransfer =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFAvoirMarajne =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFAvoirMarajneGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    		 
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFEntrerProduction =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFEntrerProductionGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    		 
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFPFEntrer =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFPFEntrerGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    	
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFPFSortie =new ArrayList<DetailTransferProduitFini>();
	    		 List<DetailTransferProduitFini> listDetailTransferStockPFPFSortieGroupebyPF =new ArrayList<DetailTransferProduitFini>();
	    	
	    		 
	    		 
	    	
	    		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
	    		BigDecimal montantInitial=new BigDecimal(0);
	    		BigDecimal quantiteTotalInitial=new BigDecimal(0);
	    		BigDecimal PrixInitial=new BigDecimal(0);
	    		BigDecimal montantachat=new BigDecimal(0);
	    		BigDecimal quantiteTotalachat=new BigDecimal(0);
	    		BigDecimal quantiteTotalvente=new BigDecimal(0);
	    		BigDecimal montantvente=new BigDecimal(0);
	    		BigDecimal quantiteTotalavoir=new BigDecimal(0);
	    		BigDecimal montantavoir=new BigDecimal(0);
	    		BigDecimal quantiteTotalavoirClientPF=new BigDecimal(0);
	    		BigDecimal montantavoirClientPF=new BigDecimal(0);
	    		BigDecimal quantiteTotalEntrer=new BigDecimal(0);
	    		BigDecimal montantEntrer=new BigDecimal(0);
	    		BigDecimal quantiteTotalGratuit=new BigDecimal(0);
	    		BigDecimal montantGratuit=new BigDecimal(0);
	    		BigDecimal quantiteTotalproduction=new BigDecimal(0);
	    		BigDecimal montantproduction=new BigDecimal(0);
	    		BigDecimal prixmoyentransferentrer=new BigDecimal(0);
	    		BigDecimal prixmoyenavoir=new BigDecimal(0);
	    		BigDecimal prixmoyenavoirClientPF=new BigDecimal(0);
	    		BigDecimal quantiteTotalFinale=new BigDecimal(0);
	    		BigDecimal montantFinale=new BigDecimal(0);
	    		boolean trouve=false;
	    		Articles article=null;
	    		FamilleArticlePF familleArticle=null;
	    		try {
	    			
	    			Magasin magasin=mapMagasinTmp.get(comboMagasin.getSelectedItem());
	    		if(magasin!=null)
	    		{
	    			
		    		if(datedebut.getDate()==null || datefin.getDate()==null)
		    		{
		    			JOptionPane.showMessageDialog(null, "Veuillez entrer la date SVP");
		    		} 
		    		
		    		listDetailTransferStockPFInitial=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(datedebut.getDate(), datefin.getDate(), article, Constantes.ETAT_TRANSFER_STOCK_INITIAL,magasin, familleArticle);
		    		
		    		listDetailTransferStockPFAchat=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(datedebut.getDate(), datefin.getDate(), article, Constantes.ETAT_TRANSFER_STOCK_ACHAT,magasin, familleArticle);
		    		listDetailTransferStockPFAchatGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(datedebut.getDate(), datefin.getDate(), article, Constantes.ETAT_TRANSFER_STOCK_ACHAT,magasin, familleArticle);
		    		
		    		listDetailTransferStockPFProduction=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(datedebut.getDate(), datefin.getDate(), article, Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE,magasin, familleArticle);
		    		listDetailTransferStockPFProductionGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(datedebut.getDate(), datefin.getDate(), article, Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE,magasin, familleArticle);
		    		
		    		
		    		listDetailTransferStockPFSortie=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(datedebut.getDate(), datefin.getDate(), article, Constantes.ETAT_TRANSFER_STOCK_VENTE,magasin, familleArticle);
		    		listDetailTransferStockPFSortieGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(datedebut.getDate(), datefin.getDate(), article, Constantes.ETAT_TRANSFER_STOCK_VENTE,magasin, familleArticle);
		    		
		    		

		    		listDetailTransferStockPFEntrerProduction=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(datedebut.getDate(), datefin.getDate(), article, Constantes.ETAT_TRANSFER_STOCK_ENTRER_MP,magasin, familleArticle);
		    		listDetailTransferStockPFEntrerProductionGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(datedebut.getDate(), datefin.getDate(), article, Constantes.ETAT_TRANSFER_STOCK_ENTRER_MP,magasin, familleArticle);
		    		
		    		listDetailTransferStockPFEntrer=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(datedebut.getDate(), datefin.getDate(), article, Constantes.ETAT_TRANSFER_STOCK_ENTRER_PF_MP,magasin, familleArticle);
		    		listDetailTransferStockPFEntrerGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(datedebut.getDate(), datefin.getDate(), article, Constantes.ETAT_TRANSFER_STOCK_ENTRER_PF_MP,magasin, familleArticle);
		    
		    		
		    		listDetailTransferStockPFAvoir=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(datedebut.getDate(), datefin.getDate(), article, Constantes.ETAT_TRANSFER_STOCK_AVOIR,magasin, familleArticle);
		    		listDetailTransferStockPFAvoirGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(datedebut.getDate(), datefin.getDate(), article, Constantes.ETAT_TRANSFER_STOCK_AVOIR,magasin, familleArticle);
		    		
		    		
		    		listDetailTransferStockPFAvoirClientPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(datedebut.getDate(), datefin.getDate(), article, Constantes.ETAT_TRANSFER_STOCK_AVOIR_CLIENT,magasin, familleArticle);
		    		listDetailTransferStockPFAvoirClientPFGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(datedebut.getDate(), datefin.getDate(), article, Constantes.ETAT_TRANSFER_STOCK_AVOIR_CLIENT,magasin, familleArticle);
		    	
		    		
		    		
		    		listDetailTransferStockPFAvoirMarajne=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(datedebut.getDate(), datefin.getDate(), article, Constantes.ETAT_TRANSFER_STOCK_AVOIR_R,magasin, familleArticle);
		    		listDetailTransferStockPFAvoirMarajneGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(datedebut.getDate(), datefin.getDate(), article, Constantes.ETAT_TRANSFER_STOCK_AVOIR_R,magasin, familleArticle);
		    
		    		listDetailTransferStockPFPFEntrer=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(datedebut.getDate(), datefin.getDate(), article, Constantes.ETAT_TRANSFER_STOCK_ENTRER_PF_PF,magasin, familleArticle);
		    		listDetailTransferStockPFPFEntrerGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(datedebut.getDate(), datefin.getDate(), article, Constantes.ETAT_TRANSFER_STOCK_ENTRER_PF_PF,magasin, familleArticle);
		    	
		    		listDetailTransferStockPFPFSortie=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFStatutX(datedebut.getDate(), datefin.getDate(), article, Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF_PF,magasin, familleArticle);
		    		listDetailTransferStockPFPFSortieGroupebyPF=detailTransferStockPFDAO.ListTransferStockPFEntreDeuxDatesBYPFDistinctByStatutX(datedebut.getDate(), datefin.getDate(), article, Constantes.ETAT_TRANSFER_STOCK_SORTIE_PF_PF,magasin, familleArticle);
		    	
		    		
		    		listDetailTransferStockPFAllPFTransfer=detailTransferStockPFDAO.findAllTransferStockPFGroupeByByArticleIdSouFamille(magasin);
		    		
		    		for(int d=0;d<listDetailTransferStockPFAllPFTransfer.size();d++)
		    		{
		    			DetailTransferProduitFini detailtransferstockpf=listDetailTransferStockPFAllPFTransfer.get(d);
		    			EtatStockPF etatstock=new EtatStockPF();
		    			etatstock.setArticle(detailtransferstockpf.getArticle());
		    			etatstock.setSousFamille(detailtransferstockpf.getSousFamille());
		    			etatstock.setFamilleArticle(detailtransferstockpf.getSousFamille().getFamileArticlePF());
		    			etatstock.setQuantiteInitial(BigDecimal.ZERO);
		    			etatstock.setPrixInitial(BigDecimal.ZERO);
		    			etatstock.setMontantInitial(BigDecimal.ZERO);
		    			etatstock.setQuantiteAchat(BigDecimal.ZERO);
		    			etatstock.setPrixAchat(BigDecimal.ZERO);
		    			etatstock.setMontantAchat(BigDecimal.ZERO);
		    			etatstock.setQuantiteProduction(BigDecimal.ZERO);
		    			etatstock.setPrixProduction(BigDecimal.ZERO);
		    			etatstock.setMontantProduction(BigDecimal.ZERO);
		    			etatstock.setQuantiteSortie(BigDecimal.ZERO);
		    			etatstock.setPrixSortie(BigDecimal.ZERO);
		    			etatstock.setMontantSortie(BigDecimal.ZERO);
		    			etatstock.setQuantiteEntrer(BigDecimal.ZERO);
		    			etatstock.setPrixEntrer(BigDecimal.ZERO);
		    			etatstock.setMontantEntrer(BigDecimal.ZERO);
		    			etatstock.setQuantiteAvoir(BigDecimal.ZERO);
		    			etatstock.setPrixAvoir(BigDecimal.ZERO);
		    			etatstock.setMontantAvoir(BigDecimal.ZERO);
		    			etatstock.setQuantiteAvoirClient(BigDecimal.ZERO);
		    			etatstock.setPrixAvoirClient(BigDecimal.ZERO);
		    			etatstock.setMontantAvoirClient(BigDecimal.ZERO);
		    			etatstock.setMarge(BigDecimal.ZERO);
		    			etatstock.setQuantiteGratuit(BigDecimal.ZERO);
		    			etatstock.setPrixGratuit(BigDecimal.ZERO);
		    			etatstock.setMontantGratuit(BigDecimal.ZERO);
		    			etatstock.setQuantiteFinale(BigDecimal.ZERO);
		    			etatstock.setPrixFinale(BigDecimal.ZERO);
		    			etatstock.setMontantFinale(BigDecimal.ZERO);
		    			listEtatStockPF.add(etatstock);
		    			
		    		}
		    		
		    		// charger list Etat stock Articles initialiser les enregistrement des achats et ventes par zero
		    		for(int i=0;i<listDetailTransferStockPFInitial.size();i++)
		    		{
		    			
		    			
		    			DetailTransferProduitFini detailtransferstockpf=listDetailTransferStockPFInitial.get(i);
		    			/*
		    			EtatStockPF etatstock=new EtatStockPF();
		    			etatstock.setArticle(detailtransferstockpf.getArticle());
		    			etatstock.setSousFamille(detailtransferstockpf.getSousFamille());
		    			etatstock.setQuantiteInitial(detailtransferstockpf.getQuantite());
		    			*/
		    		
		    				
		    			
		    				PrixInitial=detailtransferstockpf.getPrixUnitaire();
		    			
		    				montantInitial=detailtransferstockpf.getQuantite().multiply(detailtransferstockpf.getPrixUnitaire());
		    			
		    			   quantiteTotalInitial=detailtransferstockpf.getQuantite();
		    			
		    			/*
		    			etatstock.setQuantiteAchat(BigDecimal.ZERO);
		    			etatstock.setPrixAchat(BigDecimal.ZERO);
		    			etatstock.setMontantAchat(BigDecimal.ZERO);
		    			etatstock.setQuantiteProduction(BigDecimal.ZERO);
		    			etatstock.setPrixProduction(BigDecimal.ZERO);
		    			etatstock.setMontantProduction(BigDecimal.ZERO);
		    			etatstock.setQuantiteSortie(BigDecimal.ZERO);
		    			etatstock.setPrixSortie(BigDecimal.ZERO);
		    			etatstock.setMontantSortie(BigDecimal.ZERO);
		    			etatstock.setQuantiteAvoir(BigDecimal.ZERO);
		    			etatstock.setPrixAvoir(BigDecimal.ZERO);
		    			etatstock.setMontantAvoir(BigDecimal.ZERO);
		    			
		    			listEtatStockPF.add(etatstock);
		    			*/
		    			if(!montantInitial.equals(BigDecimal.ZERO) && !quantiteTotalInitial.equals(BigDecimal.ZERO))
			    		{
			    			
			    			for(int p=0;p<listEtatStockPF.size();p++)
			    	    	{
			    				if(listEtatStockPF.get(p).getArticle().equals(listDetailTransferStockPFInitial.get(i).getArticle()) && listEtatStockPF.get(p).getSousFamille().equals(listDetailTransferStockPFInitial.get(i).getSousFamille()) )
				    			{
				    				EtatStockPF etatstockpf=listEtatStockPF.get(p);
				    				etatstockpf.setQuantiteInitial(quantiteTotalInitial);
				    				etatstockpf.setPrixInitial(PrixInitial);
				    				etatstockpf.setMontantInitial(montantInitial);
				    				listEtatStockPF.set(p, etatstockpf);
				    			
				    				
				    			}
			    	    	}
			    			
			    			
			    			
			    		}
		    			
		    			
		    			
		    		}
		    		
		    		
		    		// calculer le prix moyen et quantite achat
		    		
		    		
		    	for(int j=0;j<listDetailTransferStockPFAchatGroupebyPF.size();j++)
		    	{
		    		montantachat=new BigDecimal(0);
		    		quantiteTotalachat=new BigDecimal(0);
		    		boolean existe=false;
		    			
		    	for(int k=0;k<listDetailTransferStockPFAchat.size();k++)
		    	{
		    		
		    		if(listDetailTransferStockPFAchatGroupebyPF.get(j).getArticle().equals(listDetailTransferStockPFAchat.get(k).getArticle()) && listDetailTransferStockPFAchatGroupebyPF.get(j).getSousFamille().equals(listDetailTransferStockPFAchat.get(k).getSousFamille()))
		    		{
		    			montantachat=montantachat.add(listDetailTransferStockPFAchat.get(k).getPrixUnitaire().multiply(listDetailTransferStockPFAchat.get(k).getQuantite()));
		    			quantiteTotalachat=quantiteTotalachat.add(listDetailTransferStockPFAchat.get(k).getQuantite());
		    			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
		    			
		    		}
		    		
		    		
		    	}
		    		if(!montantachat.equals(BigDecimal.ZERO) && !quantiteTotalachat.equals(BigDecimal.ZERO))
		    		{
		    			
		    			for(int i=0;i<listEtatStockPF.size();i++)
		    	    	{
		    				if(listEtatStockPF.get(i).getArticle().equals(listDetailTransferStockPFAchatGroupebyPF.get(j).getArticle()) && listEtatStockPF.get(i).getSousFamille().equals(listDetailTransferStockPFAchatGroupebyPF.get(j).getSousFamille()) )
			    			{
			    				EtatStockPF etatstockpf=listEtatStockPF.get(i);
			    				etatstockpf.setQuantiteAchat(quantiteTotalachat);
			    				etatstockpf.setPrixAchat((montantachat.divide(quantiteTotalachat,6,RoundingMode.DOWN)));
			    				etatstockpf.setMontantAchat(etatstockpf.getQuantiteAchat().multiply(etatstockpf.getPrixAchat()));
			    				listEtatStockPF.set(i, etatstockpf);
			    			
			    				
			    			}
		    	    	}
		    			
		    			
		    			
		    		}
		    		
		    	}
		    	
		    	
	    	// calculer Quantite Production et le prix moyen
		    	
		    
		    	
		    	for(int i=0;i<listDetailTransferStockPFProductionGroupebyPF.size();i++)
		    	{
		    		quantiteTotalproduction=BigDecimal.ZERO;
		    		montantproduction=new BigDecimal(0);
		    		
		    		for(int j=0;j<listDetailTransferStockPFProduction.size();j++)
		    		{
		    			
		    			if(listDetailTransferStockPFProductionGroupebyPF.get(i).getArticle().equals(listDetailTransferStockPFProduction.get(j).getArticle()) && listDetailTransferStockPFProductionGroupebyPF.get(i).getSousFamille().equals(listDetailTransferStockPFProduction.get(j).getSousFamille()))
		    			{
		    				
		    				montantproduction=montantproduction.add(listDetailTransferStockPFProduction.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFProduction.get(j).getQuantite()));
		    				quantiteTotalproduction=quantiteTotalproduction.add(listDetailTransferStockPFProduction.get(j).getQuantite());
		    				
		    				
		    			}
		    			
		    			
		    		}
		    		
		    		if(!montantproduction.equals(BigDecimal.ZERO) && !quantiteTotalproduction.equals(BigDecimal.ZERO))
		    		{
		    			
		    			
		    		   	for(int k=0;k<listEtatStockPF.size();k++)
		    	    	{
		    	    		
		    	    	
		    	    		if(listEtatStockPF.get(k).getArticle().equals(listDetailTransferStockPFProductionGroupebyPF.get(i).getArticle()) && listEtatStockPF.get(k).getSousFamille().equals(listDetailTransferStockPFProductionGroupebyPF.get(i).getSousFamille()))
		    	    		{
		    	    			
		    	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
		    	    			etatstockpf.setQuantiteProduction(quantiteTotalproduction);
		    	    			etatstockpf.setPrixProduction(montantproduction.divide(quantiteTotalproduction,6,RoundingMode.DOWN));
		    	    			etatstockpf.setMontantProduction(quantiteTotalproduction.multiply(montantproduction.divide(quantiteTotalproduction,6,RoundingMode.DOWN)));
		    	    			
		    	    			listEtatStockPF.set(k, etatstockpf);
		    	    		}
		    	    		
		    	    		
		    	    	}
		    			
		    		}
		    		
		    		
		    		
		    	}
		    	
		    	
		    	
		    	
		    	// calculer Quantite Vente et le prix moyen
		    	
		    	
		    	
		    	for(int i=0;i<listDetailTransferStockPFSortieGroupebyPF.size();i++)
		    	{
		    		quantiteTotalvente=BigDecimal.ZERO;
		    		quantiteTotalGratuit=BigDecimal.ZERO;
		    		montantvente=new BigDecimal(0);
		    		
		    		for(int j=0;j<listDetailTransferStockPFSortie.size();j++)
		    		{
		    			
		    			if(listDetailTransferStockPFSortieGroupebyPF.get(i).getArticle().equals(listDetailTransferStockPFSortie.get(j).getArticle()) && listDetailTransferStockPFSortieGroupebyPF.get(i).getSousFamille().equals(listDetailTransferStockPFSortie.get(j).getSousFamille()))
		    			{
		    				
		    				
		    				// la quantite GratuitÈ
		    				if(listDetailTransferStockPFSortie.get(j).getPrixUnitaire().setScale(6).equals(BigDecimal.ZERO.setScale(6)))
		    				{
		    					
		    					quantiteTotalGratuit=quantiteTotalGratuit.add(listDetailTransferStockPFSortie.get(j).getQuantite());
		    					
		    					
		    					
		    				}else
		    				{
		    					
		    					// la quantite Vente
		    					
		    					montantvente=montantvente.add(listDetailTransferStockPFSortie.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFSortie.get(j).getQuantite()));
		    					quantiteTotalvente=quantiteTotalvente.add(listDetailTransferStockPFSortie.get(j).getQuantite());
		    				}
		    				
		    				
		    			}
		    			
		    			
		    		}
		    		
		    		if(!quantiteTotalvente.equals(BigDecimal.ZERO))
		    		{
		    			
		    			
		    		   	for(int k=0;k<listEtatStockPF.size();k++)
		    	    	{
		    	    		
		    	    	
		    	    		if(listEtatStockPF.get(k).getArticle().equals(listDetailTransferStockPFSortieGroupebyPF.get(i).getArticle()) && listEtatStockPF.get(k).getSousFamille().equals(listDetailTransferStockPFSortieGroupebyPF.get(i).getSousFamille()))
		    	    		{
		    	    			
		    	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
		    	    			etatstockpf.setQuantiteSortie(quantiteTotalvente);
		    	    			etatstockpf.setPrixSortie(montantvente.divide(quantiteTotalvente,6,RoundingMode.DOWN));
		    	    			etatstockpf.setMontantSortie(quantiteTotalvente.multiply(montantvente.divide(quantiteTotalvente,6,RoundingMode.DOWN)));
		    	    		
		    	    			listEtatStockPF.set(k, etatstockpf);
		    	    		}
		    	    		
		    	    		
		    	    	}
		    			
		    			
		    		}
		    		
		    		
		    		if(!quantiteTotalGratuit.equals(BigDecimal.ZERO))
		    		{
		    			
		    			
		    		   	for(int k=0;k<listEtatStockPF.size();k++)
		    	    	{
		    	    		
		    	    	
		    	    		if(listEtatStockPF.get(k).getArticle().equals(listDetailTransferStockPFSortieGroupebyPF.get(i).getArticle()) && listEtatStockPF.get(k).getSousFamille().equals(listDetailTransferStockPFSortieGroupebyPF.get(i).getSousFamille()))
		    	    		{
		    	    			
		    	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
		    	    			etatstockpf.setQuantiteGratuit(quantiteTotalGratuit.setScale(6));
		    	    			if(etatstockpf.getPrixAchat().setScale(6).equals(BigDecimal.ZERO.setScale(6)))
		    	    			{
		    	    				etatstockpf.setPrixGratuit(etatstockpf.getPrixInitial().setScale(6));
		    	    			}else
		    	    			{
		    	    				etatstockpf.setPrixGratuit(etatstockpf.getPrixAchat().setScale(6));
		    	    			}
		    	    			
		    	    			etatstockpf.setMontantGratuit(etatstockpf.getQuantiteGratuit().setScale(6).multiply(etatstockpf.getPrixGratuit().setScale(6)));;
		    	    			
		    	    			 // ajouter la quantite gratuitÈ et le prix gratuitÈ(prix d'achat)
		    	    			
		    	    			
		    	    			listEtatStockPF.set(k, etatstockpf);
		    	    		}
		    	    		
		    	    	}
		    			
		    			
		    		}
		    		
		    		
		    	}
		    	
    	//     	// calculer Quantite Transfer Entrer et le prix moyen les MP Transfert EN PF vien de Production
		    	
		    	
		    	
		    	for(int i=0;i<listDetailTransferStockPFEntrerProductionGroupebyPF.size();i++)
		    	{
		    		quantiteTotalEntrer=BigDecimal.ZERO;
		    		montantEntrer=new BigDecimal(0);
		    		prixmoyentransferentrer=new BigDecimal(0);
		    		
		    		for(int j=0;j<listDetailTransferStockPFEntrerProduction.size();j++)
		    		{
		    			
		    			if(listDetailTransferStockPFEntrerProductionGroupebyPF.get(i).getArticle().getId()== listDetailTransferStockPFEntrerProduction.get(j).getArticle().getId() && listDetailTransferStockPFEntrerProductionGroupebyPF.get(i).getSousFamille().getId()==listDetailTransferStockPFEntrerProduction.get(j).getSousFamille().getId())
		    			{
		    				
		    				montantEntrer=montantEntrer.add(listDetailTransferStockPFEntrerProduction.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFEntrerProduction.get(j).getQuantite()));
		    				prixmoyentransferentrer=((prixmoyentransferentrer.multiply(quantiteTotalEntrer)).add(listDetailTransferStockPFEntrerProduction.get(j).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFEntrerProduction.get(j).getQuantite()))).divide(quantiteTotalEntrer.add(listDetailTransferStockPFEntrerProduction.get(j).getQuantite()),6, RoundingMode.DOWN) ;

		    				quantiteTotalEntrer=quantiteTotalEntrer.add(listDetailTransferStockPFEntrerProduction.get(j).getQuantite());
		    				
		    				
		    			}
		    			
		    			
		    		}
		    		
		    		if(!quantiteTotalEntrer.equals(BigDecimal.ZERO))
		    		{
		    			
		    			
		    		   	for(int k=0;k<listEtatStockPF.size();k++)
		    	    	{
		    	    		
		    	    	
		    	    		if(listEtatStockPF.get(k).getArticle().getId()== listDetailTransferStockPFEntrerProductionGroupebyPF.get(i).getArticle().getId() && listEtatStockPF.get(k).getSousFamille().getId()== listDetailTransferStockPFEntrerProductionGroupebyPF.get(i).getSousFamille().getId())
		    	    		{
		    	    			
		    	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
		    	    			
		    	    			etatstockpf.setPrixEntrer(((prixmoyentransferentrer.multiply(quantiteTotalEntrer)).add(etatstockpf.getPrixEntrer().setScale(6, RoundingMode.DOWN).multiply(etatstockpf.getQuantiteEntrer()))).divide(quantiteTotalEntrer.add(etatstockpf.getQuantiteEntrer()),6, RoundingMode.DOWN));

		    	    			etatstockpf.setQuantiteEntrer(etatstockpf.getQuantiteEntrer().add(quantiteTotalEntrer) );
		    	    			
		    	    			etatstockpf.setMontantEntrer(etatstockpf.getQuantiteEntrer().multiply(etatstockpf.getPrixEntrer()));
		    	    			
		    	    			listEtatStockPF.set(k, etatstockpf);
		    	    		}
		    	    		
		    	    		
		    	    	}
		    			
		    			
		    		}
		    		
		    	}
		    	
		    	
    	//     	// calculer Quantite Transfer PF En PF Entrer et le prix moyen  
		    	
		    	
		    	
		    	for(int i=0;i<listDetailTransferStockPFPFEntrerGroupebyPF.size();i++)
		    	{
		    		quantiteTotalEntrer=BigDecimal.ZERO;
		    		montantEntrer=new BigDecimal(0);
		    		prixmoyentransferentrer=new BigDecimal(0);
		    		
		    		for(int j=0;j<listDetailTransferStockPFPFEntrer.size();j++)
		    		{
		    			
		    			if(listDetailTransferStockPFPFEntrerGroupebyPF.get(i).getArticle().getId()== listDetailTransferStockPFPFEntrer.get(j).getArticle().getId() && listDetailTransferStockPFPFEntrerGroupebyPF.get(i).getSousFamille().getId()==listDetailTransferStockPFPFEntrer.get(j).getSousFamille().getId())
		    			{
		    				
		    				montantEntrer=montantEntrer.add(listDetailTransferStockPFPFEntrer.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFPFEntrer.get(j).getQuantite()));
		    				prixmoyentransferentrer=((prixmoyentransferentrer.multiply(quantiteTotalEntrer)).add(listDetailTransferStockPFPFEntrer.get(j).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFPFEntrer.get(j).getQuantite()))).divide(quantiteTotalEntrer.add(listDetailTransferStockPFPFEntrer.get(j).getQuantite()),6, RoundingMode.DOWN) ;

		    				quantiteTotalEntrer=quantiteTotalEntrer.add(listDetailTransferStockPFPFEntrer.get(j).getQuantite());
		    				
		    				
		    			}
		    			
		    			
		    		}
		    		
		    		if(!quantiteTotalEntrer.equals(BigDecimal.ZERO))
		    		{
		    			
		    			
		    		   	for(int k=0;k<listEtatStockPF.size();k++)
		    	    	{
		    	    		
		    	    	
		    	    		if(listEtatStockPF.get(k).getArticle().getId()== listDetailTransferStockPFPFEntrerGroupebyPF.get(i).getArticle().getId() && listEtatStockPF.get(k).getSousFamille().getId()== listDetailTransferStockPFPFEntrerGroupebyPF.get(i).getSousFamille().getId())
		    	    		{
		    	    			
		    	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
		    	    			
		    	    			etatstockpf.setPrixEntrer(((prixmoyentransferentrer.multiply(quantiteTotalEntrer)).add(etatstockpf.getPrixEntrer().setScale(6, RoundingMode.DOWN).multiply(etatstockpf.getQuantiteEntrer()))).divide(quantiteTotalEntrer.add(etatstockpf.getQuantiteEntrer()),6, RoundingMode.DOWN));

		    	    			etatstockpf.setQuantiteEntrer(etatstockpf.getQuantiteEntrer().add(quantiteTotalEntrer) );
		    	    			
		    	    			etatstockpf.setMontantEntrer(etatstockpf.getQuantiteEntrer().multiply(etatstockpf.getPrixEntrer()));
		    	    			
		    	    			listEtatStockPF.set(k, etatstockpf);
		    	    		}
		    	    		
		    	    		
		    	    	}
		    			
		    			
		    		}
		    		
		    	}   	
		    	
		    	
		    	// calculer Quantite Transfer PF En PF Sortie et le prix moyen  
		    	
		    	
		    	
		    	for(int i=0;i<listDetailTransferStockPFPFSortieGroupebyPF.size();i++)
		    	{
		    		quantiteTotalvente=BigDecimal.ZERO;
		    		
		    		montantvente=new BigDecimal(0);
		    		
		    		for(int j=0;j<listDetailTransferStockPFPFSortie.size();j++)
		    		{
		    			
		    			if(listDetailTransferStockPFPFSortieGroupebyPF.get(i).getArticle().equals(listDetailTransferStockPFPFSortie.get(j).getArticle()) && listDetailTransferStockPFPFSortieGroupebyPF.get(i).getSousFamille().equals(listDetailTransferStockPFPFSortie.get(j).getSousFamille()))
		    			{
		    				
		    					
		    					montantvente=montantvente.add(listDetailTransferStockPFPFSortie.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFPFSortie.get(j).getQuantite()));
		    					quantiteTotalvente=quantiteTotalvente.add(listDetailTransferStockPFPFSortie.get(j).getQuantite());
		    				 
		    				
		    				
		    			}
		    			
		    			
		    		}
		    		
		    		if(!quantiteTotalvente.equals(BigDecimal.ZERO))
		    		{
		    			
		    			
		    		   	for(int k=0;k<listEtatStockPF.size();k++)
		    	    	{
		    	    		
		    	    	
		    	    		if(listEtatStockPF.get(k).getArticle().equals(listDetailTransferStockPFPFSortieGroupebyPF.get(i).getArticle()) && listEtatStockPF.get(k).getSousFamille().equals(listDetailTransferStockPFPFSortieGroupebyPF.get(i).getSousFamille()))
		    	    		{
		    	    			
		    	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
		    	    			etatstockpf.setQuantiteSortie(quantiteTotalvente);
		    	    			etatstockpf.setPrixSortie(montantvente.divide(quantiteTotalvente,6,RoundingMode.DOWN));
		    	    			etatstockpf.setMontantSortie(quantiteTotalvente.multiply(montantvente.divide(quantiteTotalvente,6,RoundingMode.DOWN)));
		    	    		
		    	    			listEtatStockPF.set(k, etatstockpf);
		    	    		}
		    	    		
		    	    		
		    	    	}
		    			
		    			
		    		}
		    		
		    		
		    		
		    		
		    	}	
		    	
		    	
		    	
		    	
    	// calculer Quantite Transfer Entrer et le prix moyen (Les MP transfer En PF )
		    	
		    	
		    	
		    	for(int i=0;i<listDetailTransferStockPFEntrerGroupebyPF.size();i++)
		    	{
		    		quantiteTotalEntrer=BigDecimal.ZERO;
		    		montantEntrer=new BigDecimal(0);
		    		prixmoyentransferentrer=new BigDecimal(0);
		    		
		    		for(int j=0;j<listDetailTransferStockPFEntrer.size();j++)
		    		{
		    			
		    			if(listDetailTransferStockPFEntrerGroupebyPF.get(i).getArticle().getId()== listDetailTransferStockPFEntrer.get(j).getArticle().getId() && listDetailTransferStockPFEntrerGroupebyPF.get(i).getSousFamille().getId()== listDetailTransferStockPFEntrer.get(j).getSousFamille().getId())
		    			{
		    				
		    				montantEntrer=montantEntrer.add(listDetailTransferStockPFEntrer.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFEntrer.get(j).getQuantite()));
		    				prixmoyentransferentrer=((prixmoyentransferentrer.multiply(quantiteTotalEntrer)).add(listDetailTransferStockPFEntrer.get(j).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFEntrer.get(j).getQuantite()))).divide(quantiteTotalEntrer.add(listDetailTransferStockPFEntrer.get(j).getQuantite()),6, RoundingMode.DOWN) ;

		    				quantiteTotalEntrer=quantiteTotalEntrer.add(listDetailTransferStockPFEntrer.get(j).getQuantite());
		    				
		    				
		    			}
		    			
		    			
		    		}
		    		
		    		if(!quantiteTotalEntrer.equals(BigDecimal.ZERO))
		    		{
		    			
		    			
		    		   	for(int k=0;k<listEtatStockPF.size();k++)
		    	    	{
		    	    		
		    	    	
		    	    		if(listEtatStockPF.get(k).getArticle().getId()== listDetailTransferStockPFEntrerGroupebyPF.get(i).getArticle().getId() && listEtatStockPF.get(k).getSousFamille().getId()== listDetailTransferStockPFEntrerGroupebyPF.get(i).getSousFamille().getId())
		    	    		{
		    	    			
		    	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
		    	    			
		    	    			etatstockpf.setPrixEntrer(((prixmoyentransferentrer.multiply(quantiteTotalEntrer)).add(etatstockpf.getPrixEntrer().setScale(6, RoundingMode.DOWN).multiply(etatstockpf.getQuantiteEntrer()))).divide(quantiteTotalEntrer.add(etatstockpf.getQuantiteEntrer()),6, RoundingMode.DOWN));

		    	    			etatstockpf.setQuantiteEntrer(etatstockpf.getQuantiteEntrer().add(quantiteTotalEntrer) );
		    	    			
		    	    			etatstockpf.setMontantEntrer(etatstockpf.getQuantiteEntrer().multiply(etatstockpf.getPrixEntrer()));
		    	    	
		    	    			
		    	    			listEtatStockPF.set(k, etatstockpf);
		    	    		}
		    	    		
		    	    		
		    	    	}
		    			
		    			
		    		}
		    		
		    	} 	
		    	
		    	
		    	
	// calculer Quantite avoir et le prix moyen
		    	
		    	
		    	
		    	for(int i=0;i<listDetailTransferStockPFAvoirGroupebyPF.size();i++)
		    	{
		    		quantiteTotalavoir=BigDecimal.ZERO;
		    		montantavoir=new BigDecimal(0);
		    		
		    		for(int j=0;j<listDetailTransferStockPFAvoir.size();j++)
		    		{
		    			
		    			if(listDetailTransferStockPFAvoirGroupebyPF.get(i).getArticle().equals(listDetailTransferStockPFAvoir.get(j).getArticle()) && listDetailTransferStockPFAvoirGroupebyPF.get(i).getSousFamille().equals(listDetailTransferStockPFAvoir.get(j).getSousFamille()))
		    			{
		    				
		    				montantavoir=montantavoir.add(listDetailTransferStockPFAvoir.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFAvoir.get(j).getQuantite()));
		    				quantiteTotalavoir=quantiteTotalavoir.add(listDetailTransferStockPFAvoir.get(j).getQuantite());
		    				
		    				
		    			}
		    			
		    			
		    		}
		    		
		    		if(!quantiteTotalavoir.equals(BigDecimal.ZERO))
		    		{
		    			
		    			
		    		   	for(int k=0;k<listEtatStockPF.size();k++)
		    	    	{
		    	    		
		    	    	
		    	    		if(listEtatStockPF.get(k).getArticle().equals(listDetailTransferStockPFAvoirGroupebyPF.get(i).getArticle()) && listEtatStockPF.get(k).getSousFamille().equals(listDetailTransferStockPFAvoirGroupebyPF.get(i).getSousFamille() ) && listEtatStockPF.get(k).getSousFamille().getFamileArticlePF().getLiblle().equals(Constantes.FAMILLE_EMBALLAGE))
		    	    		{
		    	    			
		    	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
		    	    			etatstockpf.setQuantiteAvoir(quantiteTotalavoir);
		    	    			etatstockpf.setPrixAvoir(montantavoir.divide(quantiteTotalavoir,6,RoundingMode.DOWN));
		    	    			etatstockpf.setMontantAvoir(quantiteTotalavoir.multiply(montantavoir.divide(quantiteTotalavoir,6,RoundingMode.DOWN)));
		    	    			
		    	    			listEtatStockPF.set(k, etatstockpf);
		    	    		}
		    	    		
		    	    		
		    	    	}
		    			
		    			
		    		}
		    		
		    		
		    		
		    	}
		    	
		    	
	// calculer Quantite avoir Client PF et le prix moyen
		    	
		    	
		    	
		    	for(int i=0;i<listDetailTransferStockPFAvoirClientPFGroupebyPF.size();i++)
		    	{
		    		quantiteTotalavoirClientPF=BigDecimal.ZERO;
		    		montantavoirClientPF=new BigDecimal(0);
		    		
		    		for(int j=0;j<listDetailTransferStockPFAvoirClientPF.size();j++)
		    		{
		    			
		    			if(listDetailTransferStockPFAvoirClientPFGroupebyPF.get(i).getArticle().equals(listDetailTransferStockPFAvoirClientPF.get(j).getArticle()) && listDetailTransferStockPFAvoirClientPFGroupebyPF.get(i).getSousFamille().equals(listDetailTransferStockPFAvoirClientPF.get(j).getSousFamille()))
		    			{
		    				
		    				montantavoirClientPF=montantavoirClientPF.add(listDetailTransferStockPFAvoirClientPF.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFAvoirClientPF.get(j).getQuantite()));
		    				quantiteTotalavoirClientPF=quantiteTotalavoirClientPF.add(listDetailTransferStockPFAvoirClientPF.get(j).getQuantite());
		    				
		    				
		    			}
		    			
		    			
		    		}
		    		
		    		if(!quantiteTotalavoirClientPF.equals(BigDecimal.ZERO))
		    		{
		    			
		    			
		    		   	for(int k=0;k<listEtatStockPF.size();k++)
		    	    	{
		    	    		
		    	    	
		    	    		if(listEtatStockPF.get(k).getArticle().equals(listDetailTransferStockPFAvoirClientPFGroupebyPF.get(i).getArticle()) && listEtatStockPF.get(k).getSousFamille().equals(listDetailTransferStockPFAvoirClientPFGroupebyPF.get(i).getSousFamille() ))
		    	    		{
		    	    			
		    	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
		    	    			etatstockpf.setQuantiteAvoirClient(quantiteTotalavoirClientPF);
		    	    			etatstockpf.setPrixAvoirClient(montantavoirClientPF.divide(quantiteTotalavoirClientPF,6,RoundingMode.DOWN));
		    	    			etatstockpf.setMontantAvoirClient(quantiteTotalavoirClientPF.multiply(montantavoirClientPF.divide(quantiteTotalavoirClientPF,6,RoundingMode.DOWN)));
		    	    			
		    	    			listEtatStockPF.set(k, etatstockpf);
		    	    		}
		    	    		
		    	    		
		    	    	}
		    			
		    			
		    		}
		    		
		    		
		    		
		    	} 	
		    	
		    	
		    	
// calculer Quantite avoir Marjane et le prix moyen
		    	
		    	
		    	
		    	for(int i=0;i<listDetailTransferStockPFAvoirMarajneGroupebyPF.size();i++)
		    	{
		    		quantiteTotalavoir=BigDecimal.ZERO;
		    		montantavoir=new BigDecimal(0);
		    		prixmoyenavoir=BigDecimal.ZERO;
		    		for(int j=0;j<listDetailTransferStockPFAvoirMarajne.size();j++)
		    		{
		    			
		    			if(listDetailTransferStockPFAvoirMarajneGroupebyPF.get(i).getArticle().equals(listDetailTransferStockPFAvoirMarajne.get(j).getArticle()) && listDetailTransferStockPFAvoirMarajneGroupebyPF.get(i).getSousFamille().equals(listDetailTransferStockPFAvoirMarajne.get(j).getSousFamille()) && listDetailTransferStockPFAvoirMarajne.get(j).getTransferStockPF().getCodeTransfer().contains("_R"))
		    			{
		    				
		    				montantavoir=montantavoir.add(listDetailTransferStockPFAvoirMarajne.get(j).getPrixUnitaire().multiply(listDetailTransferStockPFAvoirMarajne.get(j).getQuantite()));
		    				prixmoyenavoir=((prixmoyenavoir.multiply(quantiteTotalavoir)).add(listDetailTransferStockPFAvoirMarajne.get(j).getPrixUnitaire().setScale(6, RoundingMode.DOWN).multiply(listDetailTransferStockPFAvoirMarajne.get(j).getQuantite()))).divide(quantiteTotalavoir.add(listDetailTransferStockPFAvoirMarajne.get(j).getQuantite()),6, RoundingMode.DOWN) ;

		    				quantiteTotalavoir=quantiteTotalavoir.add(listDetailTransferStockPFAvoirMarajne.get(j).getQuantite());
		    				
		    				
		    			}
		    			
		    			
		    		}
		    		
		    		if(!quantiteTotalavoir.equals(BigDecimal.ZERO))
		    		{
		    			
		    			
		    		   	for(int k=0;k<listEtatStockPF.size();k++)
		    	    	{
		    	    		
		    	    	
		    	    		if(listEtatStockPF.get(k).getArticle().equals(listDetailTransferStockPFAvoirMarajneGroupebyPF.get(i).getArticle()) && listEtatStockPF.get(k).getSousFamille().equals(listDetailTransferStockPFAvoirMarajneGroupebyPF.get(i).getSousFamille()) )
		    	    		{
		    	    			
		    	    			EtatStockPF etatstockpf=listEtatStockPF.get(k);
		    	    			etatstockpf.setQuantiteAvoir(quantiteTotalavoir);
		    	    			etatstockpf.setPrixAvoir(prixmoyenavoir);
		    	    			etatstockpf.setMontantAvoir(quantiteTotalavoir.multiply(prixmoyenavoir));
		    	    			
		    	    			listEtatStockPF.set(k, etatstockpf);
		    	    		}
		    	    		
		    	    		
		    	    	}
		    			
		    			
		    		}
		    		
		    		
		    		
		    	} 
		    	
		    	
		    	
		    	
		    	// Calculer Stock Finale
		    	
		    	
			   	for(int i=0;i<listEtatStockPF.size();i++)
		    	{
			   	 BigDecimal prixMoyen=BigDecimal.ZERO;
			   	 BigDecimal QuantiteTotal=BigDecimal.ZERO;
			   		   quantiteTotalFinale=BigDecimal.ZERO;
			   		    montantFinale=BigDecimal.ZERO;
		    			EtatStockPF etatstockpf=listEtatStockPF.get(i);
		    			etatstockpf.setQuantiteFinale((etatstockpf.getQuantiteInitial().add(etatstockpf.getQuantiteAchat().add(etatstockpf.getQuantiteProduction()).add(etatstockpf.getQuantiteEntrer()).add(etatstockpf.getQuantiteAvoirClient()))).subtract(etatstockpf.getQuantiteSortie().add(etatstockpf.getQuantiteAvoir()).add(etatstockpf.getQuantiteGratuit())));
		    			quantiteTotalFinale=quantiteTotalFinale.add(etatstockpf.getQuantiteInitial().add(etatstockpf.getQuantiteAchat().add(etatstockpf.getQuantiteProduction()).add(etatstockpf.getQuantiteEntrer()).add(etatstockpf.getQuantiteAvoirClient())).subtract(etatstockpf.getQuantiteAvoir().add(etatstockpf.getQuantiteSortie()).add(etatstockpf.getQuantiteGratuit())));
		    			montantFinale=etatstockpf.getMontantInitial().add(etatstockpf.getMontantAchat().add(etatstockpf.getMontantProduction().add(etatstockpf.getMontantEntrer()).add(etatstockpf.getMontantAvoirClient())));
		    			QuantiteTotal=etatstockpf.getQuantiteInitial().add(etatstockpf.getQuantiteAchat().add(etatstockpf.getQuantiteProduction().add(etatstockpf.getQuantiteEntrer()).add(etatstockpf.getQuantiteAvoirClient())));
if(!QuantiteTotal.setScale(2, RoundingMode.HALF_UP).equals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP)))
{
	prixMoyen=montantFinale.divide(QuantiteTotal, 6, RoundingMode.DOWN);
	etatstockpf.setPrixFinale(prixMoyen.setScale(6,  RoundingMode.DOWN));
	etatstockpf.setMontantFinale(etatstockpf.getPrixFinale().multiply(etatstockpf.getQuantiteFinale()));
}else
{
	etatstockpf.setPrixFinale(prixMoyen.setScale(6,  RoundingMode.DOWN));
	
}
		    			
		    			
		    			
		    			if(etatstockpf.getPrixSortie().setScale(6, RoundingMode.DOWN).equals(BigDecimal.ZERO.setScale(6,  RoundingMode.DOWN)))
		    			{
		    				etatstockpf.setMarge(BigDecimal.ZERO);
		    				
		    			}else
		    			{
		    				etatstockpf.setMarge((etatstockpf.getPrixSortie().subtract(etatstockpf.getPrixInitial())).divide(etatstockpf.getPrixSortie(), RoundingMode.DOWN));
		    			}
		    			
		    			
		    			listEtatStockPF.set(i, etatstockpf);
		    		
		    		
		    	}
			   	
			   	  
			    	
			    	
			    /*	******* regler stock ***************************** */
				   	
					
	    				 
	    			for(int t=0;t<listEtatStockPF.size();t++)	
	    			{
	    				Magasin magasinTmp=mapMagasinTmp.get(comboMagasin.getSelectedItem());
	    				 StockPF stockpf=stockPFDAO.findStockByMagasinPFBySousFamille(listEtatStockPF.get(t).getArticle().getId(), magasinTmp.getId(), listEtatStockPF.get(t).getSousFamille().getId());
	    				
	    					stockpf.setStock(listEtatStockPF.get(t).getQuantiteFinale());
	    					stockpf.setPrixUnitaire(listEtatStockPF.get(t).getPrixFinale());
		    				stockPFDAO.edit(stockpf);
	    				
	    			}
	    			
	    			
		    			
		    		}else
		    		{
		    			

		    			JOptionPane.showMessageDialog(null, "Veuillez selectionner un depot SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
		    			return;
		    		}
		    		
	    			
				} catch (Exception e2) {
					// TODO: handle exception
				}
	    		
	    
				
				/* *********************************************************************************************************** */
	    		listStockPF.clear();
	    		
	    		listStockPFTmp.clear();
	    		listStockPFTmp=stockPFDAO.findStockProduitFiniByMagasin(mapMagasin.get(comboMagasin.getSelectedItem()));
	    		 listStockPF=stockPFDAO.findStockProduitFiniByMagasinGroupByArticle(mapMagasin.get(comboMagasin.getSelectedItem()));
	    		
	    		
	    		 
	    		 BigDecimal StockFinale=BigDecimal.ZERO;
    			 for(int y=0;y<listStockPF.size();y++)
    			 {
    				 StockPF stockpf=listStockPF.get(y);
    				 StockFinale=BigDecimal.ZERO;
    			for(int t=0;t<listStockPFTmp.size();t++)	
    			{
    				
    				if(stockpf.getArticles().getId()==listStockPFTmp.get(t).getArticles().getId())
    				{
    					
    					
    					StockFinale=StockFinale.add(listStockPFTmp.get(t).getStock());
    					
    					
    				}
    				
    			}
    			
    				stockpf.setStock(StockFinale);
    				listStockPF.set(y, stockpf);
    			
    			
    			
    				 
    			 }
    			 afficher_tableMP(listStockPF);
				
				
			}
		  }
		});
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel label = new JLabel("Date Debut :");
		label.setBounds(10, 13, 136, 24);
		layeredPane.add(label);
		label.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		
		 datedebut = new JDateChooser();
		datedebut.setBounds(101, 13, 163, 26);
		layeredPane.add(datedebut);
		datedebut.setLocale(Locale.FRANCE);
		datedebut.setDateFormatString("dd/MM/yyyy");
		
		JLabel label_1 = new JLabel("Date Fin :");
		label_1.setBounds(274, 11, 106, 24);
		layeredPane.add(label_1);
		label_1.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		
		 datefin = new JDateChooser();
		datefin.setBounds(348, 11, 169, 26);
		layeredPane.add(datefin);
		datefin.setLocale(Locale.FRANCE);
		datefin.setDateFormatString("dd/MM/yyyy");
		
	   	Date date;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse((String)util.DateUtils.getCurrentYear()+"-01-01");
			  datedebut.setDate(date);
  	          datefin.setDate(new Date());
  	          
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		JButton button = new JButton("Imprimer ");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				

  		  		
		  		  
				 
				 
				Map parameters = new HashMap();
				parameters.put("depot", comboDepot.getSelectedItem());
				
				parameters.put("magasin",comboMagasin.getSelectedItem());
				
				
				
				JasperUtils.imprimerSitutationStockPF(listStockPF, parameters);
				
				//JOptionPane.showMessageDialog(null, "PDF exportÈ avec succËs", "SuccËs", JOptionPane.INFORMATION_MESSAGE);
	  		  	
				
				
				
			
				
				
				
			}
		});
		button.setBounds(295, 526, 89, 23);
		add(button);
	
				  		     
				  		 
	}
	
void afficher_tableMP(List<StockPF> listStockPF)
	{
	intialiserTableau();
		  int i=0;
			while(i<listStockPF.size())
			{	
				
				StockPF stockPF=listStockPF.get(i);
				
				
				Object []ligne={stockPF.getArticles().getCodeArticle(),stockPF.getArticles().getLiblle(),NumberUtils.GroupingFormatBigDecimal(stockPF.getStock()),NumberUtils.GroupingFormatBigDecimal(stockPF.getPrixUnitaire())};

				modeleMP.addRow(ligne);
				i++;
			}
	}
	
void intialiserTableau(){
	
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code Article","Article", "QuantitÈ","Prix Unitaire"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		   table.setModel(modeleMP); 
		   table.getColumnModel().getColumn(0).setPreferredWidth(160);
		   table.getColumnModel().getColumn(1).setPreferredWidth(260);
		   table.getColumnModel().getColumn(2).setPreferredWidth(160);
}
}
