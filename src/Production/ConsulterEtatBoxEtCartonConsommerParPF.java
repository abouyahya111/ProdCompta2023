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
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.DateUtils;
import util.ExporterTableVersExcel;
import util.JasperUtils;
import util.Utils;

import com.sun.org.apache.bcel.internal.generic.ATHROW;
import com.sun.org.apache.bcel.internal.generic.InstructionConstants.Clinit;
import com.toedter.calendar.JDateChooser;

import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailFactureAchatMPDAOImpl;
import dao.daoImplManager.DetailTransferMPDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FactureServiceProductionDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoImplManager.ProductionDAOImpl;
import dao.daoImplManager.SousFamilleEnVracDAOImpl;
import dao.daoManager.ClientDAO;
import dao.daoManager.CompteStockMPDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailFactureAchatMPDAO;
import dao.daoManager.DetailTransferMPDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.EmployeDAO;
import dao.daoManager.FactureServiceProductionDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.SousFamilleEnVracDAO;
import dao.entity.Articles;
import dao.entity.CategorieMp;
import dao.entity.Client;
import dao.entity.CompteStockMP;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailProdGen;
import dao.entity.DetailProduction;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.EtatBoxEtCartonConsommeParPF;
import dao.entity.FactureServiceProduction;
import dao.entity.FicheEmploye;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.PrixMoyenStockMP;
import dao.entity.Production;
import dao.entity.SousFamilleArticlePF;
import dao.entity.SousFamilleEnVrac;
import dao.entity.SubCategorieMp;
import dao.entity.Utilisateur;

import java.awt.Component;

import javax.swing.JComboBox;

import java.awt.GridBagLayout;

import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.ScrollPane;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;


public class ConsulterEtatBoxEtCartonConsommerParPF extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleProd;
	private DefaultTableModel	 modeleMP;
	private DefaultTableModel	 modeleEmployeGen;
	private DefaultTableModel	 modeleEmployeProd;
	private DefaultTableModel	 modeleEmployeEmballage;
	private JXTable table;
	

	List<Object[]> listObjectQuantiteProduction=new ArrayList<Object[]>();
	 List<Object[]> listObject=new ArrayList<Object[]>();
	 List<EtatBoxEtCartonConsommeParPF> listEtatBoxEtCartonConsommeParPF=new ArrayList<EtatBoxEtCartonConsommeParPF>();
	 List<DetailProdGen> listEmployeEmballage=new ArrayList<DetailProdGen>();
	 List<DetailProduction> listEmployeProduction=new ArrayList<DetailProduction>();
	private ImageIcon imgValider;
	private ImageIcon imgInit;
	private ImageIcon imgImprimer;
	private ImageIcon imgExcel;
	private ImageIcon imgRechercher;
	private JDateChooser dateDebutChooser = new JDateChooser();
	private JDateChooser dateFinChooser = new JDateChooser();
	JComboBox combodepot = new JComboBox();
	private Map< Integer, String> mapAvance= new HashMap<>();
	private Map< String, BigDecimal> mapParametre = new HashMap<>();
	private List<Depot> listDepot=new ArrayList<Depot>();
	private List<Magasin> listMagasin=new ArrayList<Magasin>();
	private List<Production> listProduction=new ArrayList<Production>();
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Utilisateur utilisateur;
	private ProductionDAO productionDAO;
	private DepotDAO depotdao;
	  JComboBox comboMagasin = new JComboBox();
	  JCheckBox checkTerminer = new JCheckBox("Termin\u00E9");
	  JCheckBox checkAnnule = new JCheckBox("Annul\u00E9");
	  
	  private  DetailTransferMPDAO detailTransferMPDAO;
	  JComboBox comboclient = new JComboBox(); 
	  private List<Client> listClient =new ArrayList<Client>();
	  private ClientDAO clientdao;
	  private Map< String, Client> mapClient= new HashMap<>();
	  private DetailTransferProduitFiniDAO detailTransferProduitFiniDAO;
	  private Map< String, BigDecimal> mapPrixMoyenneMatierePremiere = new HashMap<>();
	  private List<DetailTransferStockMP> listDetailTransferStockMPGroupebyMP =new ArrayList<DetailTransferStockMP>();
	  private List<PrixMoyenStockMP> listPrixMoyenStockMP =new ArrayList<PrixMoyenStockMP>();
	  private List<DetailTransferStockMP> listDetailTransfertStockMP =new ArrayList<DetailTransferStockMP>();
	  SousFamilleEnVracDAO sousFamilleEnVracDAO;
	  private List <Object[]> listeObject=new ArrayList<Object[]>();
		private DetailFactureAchatMPDAO detailFactureAchatMPdao;
		private List<DetailTransferStockMP> listDetailTransferStockMPFabrique=new ArrayList<DetailTransferStockMP>();
		private MatierePremiereDAO matierePremiereDAO;
		
		
		
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ConsulterEtatBoxEtCartonConsommerParPF() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1579, 1062);
        try{
        	
        	 utilisateur=AuthentificationView.utilisateur;
        	productionDAO=new ProductionDAOImpl();
        	depotdao=new DepotDAOImpl();
        	detailTransferMPDAO=new DetailTransferMPDAOImpl();
        	 clientdao=new ClientDAOImpl();
        	 detailTransferProduitFiniDAO=new DetailTransferProduitFiniDAOImpl();
        	 sousFamilleEnVracDAO=new SousFamilleEnVracDAOImpl();
        	  detailFactureAchatMPdao=new DetailFactureAchatMPDAOImpl();
        	  matierePremiereDAO=new MatierePremierDAOImpl();
        	  
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion √† la base de donn√©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
        
        try{
        	
        	imgRechercher= new ImageIcon(this.getClass().getResource("/img/rechercher.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgImprimer=new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
            imgValider=new ImageIcon(this.getClass().getResource("/img/valider.png"));
            imgExcel=new ImageIcon(this.getClass().getResource("/img/excel.png"));
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
					  		     			"Code Article","Article", "Sous Famille", "Quantite Realiser","Box","Quantite Box Consomme","Prix Box","Montant Box","Carton","Quantite Carton Consomme","Prix Carton","Montant Carton"
					  		     	}
					  		     ) {
					  		     	boolean[] columnEditables = new boolean[] {
					  		     			false,false,false,false,false,false,false,false,false,false,false,false
					  		     	};
					  		     	public boolean isCellEditable(int row, int column) {
					  		     		return columnEditables[column];
					  		     	}
					  		     };
					  		     
					  		     
					  		     
					  		 table.setModel(modeleProd); 
					  		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
					         table.getColumnModel().getColumn(1).setPreferredWidth(60);
					         table.getColumnModel().getColumn(2).setPreferredWidth(60);
					         table.getColumnModel().getColumn(3).setPreferredWidth(60);
					         table.getColumnModel().getColumn(4).setPreferredWidth(60);
					         table.getColumnModel().getColumn(5).setPreferredWidth(160);
					         table.getColumnModel().getColumn(6).setPreferredWidth(60);
					         table.getColumnModel().getColumn(7).setPreferredWidth(160);
					         table.getColumnModel().getColumn(8).setPreferredWidth(60);
					         table.getColumnModel().getColumn(9).setPreferredWidth(60);
					         table.getColumnModel().getColumn(10).setPreferredWidth(60);
					         table.getColumnModel().getColumn(11).setPreferredWidth(60);
					      //   intialiserTableau2();
				  		     	
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 65, 1325, 529);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	modeleProd =new DefaultTableModel(
				  			     	new Object[][] {
				  			     	},
				  			     	new String[] {
				  	  		     			"Code Article","Article", "Sous Famille", "Quantite Realiser","Carton","Quantite carton Consomme","Prix Carton","Montant Carton","Box","Quantite Box Consomme","Prix Box","Montant Box"
				  			     	}
				  			     ) {
				  			     	boolean[] columnEditables = new boolean[] {
				  			     			false,false,false,false,false,false,false,false,false,false,false,false
				  			     	};
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
				  		     	layeredPane.setBounds(9, 11, 1325, 54);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblDateDebut = new JLabel("Du :");
				  		     	lblDateDebut.setBounds(65, 11, 31, 24);
				  		     	layeredPane.add(lblDateDebut);
				  		     	lblDateDebut.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		     	 
				  		     	 JLabel lblDateFin = new JLabel("Au :");
				  		     	 lblDateFin.setBounds(298, 11, 51, 24);
				  		     	 layeredPane.add(lblDateFin);
				  		     	 lblDateFin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		JButton btnAfficherStock = new JButton();
		btnAfficherStock.setIcon(imgRechercher);
		btnAfficherStock.setBounds(1264, 11, 31, 31);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String req="";
			 
				String dateDebut=((JTextField)dateDebutChooser.getDateEditor().getUiComponent()).getText();
				String dateFin=((JTextField)dateFinChooser.getDateEditor().getUiComponent()).getText();
			if(dateDebut.equals(""))	{
				JOptionPane.showMessageDialog(null, "Il faut choisir Date DÈbut", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else if(dateFin.equals("")){
				JOptionPane.showMessageDialog(null, "Il faut choisir Date Fin", "Erreur", JOptionPane.ERROR_MESSAGE);
				
			}
			else if(comboclient.getSelectedItem().equals("")){
				JOptionPane.showMessageDialog(null, "Il faut Selectionner le Client", "Erreur", JOptionPane.ERROR_MESSAGE);
				
			}
			
			else
			
			{
			 
				
				
				
				 
				Client client=mapClient.get(comboclient.getSelectedItem().toString());
				
				if(client==null)
				{
					JOptionPane.showMessageDialog(null, "Veuillez Selectionner le client SVP SVP !!!", "Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Magasin magasinMP=depotdao.magasinByCodeMachineByTypeMagasin(Constantes.MAGASIN_CODE_TYPE_MP, client.getCode());
				Magasin magasinPF=depotdao.magasinByCodeMachineByTypeMagasin(Constantes.MAGASIN_CODE_TYPE_PF, client.getCode());
			
				
				
				
				
				
	/////////////////////////////////////////////////////////////////////  carton  /////////////////////////////////////////////////////////////////////////////////////			
				req=req+" and c.transferStockMP.dateTransfer between '"+dateDebut+"' and '"+dateFin+"' and c.magasinSouce.id='"+magasinMP.getId()+"' and c.matierePremier.categorieMp.subCategorieMp.id in (2) group by d.article,d.sousFamille,c.matierePremier"	;		
				
				
				listEtatBoxEtCartonConsommeParPF.clear();	
				
				
          listObject.clear();
          listObject=detailTransferMPDAO.listeMPBoxEtCartonConsommeParPF(req);
          
          for(int i=0;i<listObject.size();i++)
          {
        	  
        	  Object[] object=listObject.get(i);  
        	  
        	  if(object[0]!=null)
				{
        		  if(object[1]!=null)
  				{
        			  
        			  if(object[2]!=null)
        				{
        				  if(object[3]!=null)
        	  				{
        					 
        							EtatBoxEtCartonConsommeParPF etatBoxEtCartonConsommeParPF=new EtatBoxEtCartonConsommeParPF();  
              					  
              					  Articles articles=(Articles)object[0];
              					  SousFamilleArticlePF sousFamilleArticlePF=(SousFamilleArticlePF)object[1];
              					  MatierePremier matierePremier=(MatierePremier)object[2];
              					  etatBoxEtCartonConsommeParPF.setArticle(articles);
              					  etatBoxEtCartonConsommeParPF.setSousFamille(sousFamilleArticlePF.getLiblle());
              					  etatBoxEtCartonConsommeParPF.setMpCarton(matierePremier);
              					 
              					  etatBoxEtCartonConsommeParPF.setQuantiteCartonConsomme((BigDecimal)object[3]);
              					  listEtatBoxEtCartonConsommeParPF.add(etatBoxEtCartonConsommeParPF);  
        						  
          	  			 
        					  
        					  
        				
        					  
        					  
        	  				}
        				  
        				}
        			  
        			  
  				}
        		  
        		  
        		  
        		  
				}
        	  
        	  
        	  
          }
          
          
/////////////////////////////////////////////////////////////////////  Box  /////////////////////////////////////////////////////////////////////////////////////			
          req="";
          
          req=req+" and c.transferStockMP.dateTransfer between '"+dateDebut+"' and '"+dateFin+"' and c.magasinSouce.id='"+magasinMP.getId()+"' and c.matierePremier.categorieMp.subCategorieMp.id in (13) group by d.article,d.sousFamille,c.matierePremier"	;		





listObject.clear();
listObject=detailTransferMPDAO.listeMPBoxEtCartonConsommeParPF(req);
boolean existe=false;
BigDecimal quantiteTotalBoxConsomme=BigDecimal.ZERO;
for(int i=0;i<listObject.size();i++)
{

Object[] object=listObject.get(i);  

if(object[0]!=null)
{
if(object[1]!=null)
{

if(object[2]!=null)
{
if(object[3]!=null)
{
	
	
	
	Articles articles=(Articles)object[0];
	SousFamilleArticlePF sousFamilleArticlePF=(SousFamilleArticlePF)object[1];
	MatierePremier matierePremier=(MatierePremier)object[2];
	existe=false;
	System.out.println(articles.getCodeArticle() +" -- "+sousFamilleArticlePF.getLiblle());
	
for(int j=0;j<listEtatBoxEtCartonConsommeParPF.size();j++)
{
	EtatBoxEtCartonConsommeParPF etatBoxEtCartonConsommeParPF=listEtatBoxEtCartonConsommeParPF.get(j);
	
if(etatBoxEtCartonConsommeParPF.getArticle().getCodeArticle().equals(articles.getCodeArticle())) 
{
	if(etatBoxEtCartonConsommeParPF.getSousFamille().equals(sousFamilleArticlePF.getLiblle())) 
	{
		
		if(etatBoxEtCartonConsommeParPF.getMpBox()==null)
		{
			quantiteTotalBoxConsomme=quantiteTotalBoxConsomme.add((BigDecimal)object[3]);
			existe=true;
			etatBoxEtCartonConsommeParPF.setMpBox(matierePremier);	
			etatBoxEtCartonConsommeParPF.setQuantiteBoxConsomme((BigDecimal)object[3]);
			
			listEtatBoxEtCartonConsommeParPF.set(j, etatBoxEtCartonConsommeParPF);
		}
		
	}
		
		
 
	
}
	
}


if(existe==false)
{
	
	EtatBoxEtCartonConsommeParPF etatBoxEtCartonConsommeParPF=new EtatBoxEtCartonConsommeParPF();  


	 
	etatBoxEtCartonConsommeParPF.setArticle(articles);
	etatBoxEtCartonConsommeParPF.setSousFamille(sousFamilleArticlePF.getLiblle());
	etatBoxEtCartonConsommeParPF.setMpBox (matierePremier);
	etatBoxEtCartonConsommeParPF.setQuantiteBoxConsomme((BigDecimal)object[3]);
	
	listEtatBoxEtCartonConsommeParPF.add(etatBoxEtCartonConsommeParPF); 
	quantiteTotalBoxConsomme=quantiteTotalBoxConsomme.add((BigDecimal)object[3]);
}
 







}

}


}




}



}


req="";
req=req+" typeTransfer='"+Constantes.TYPE_TRANSFER_PRODUIT_FINI_ENTRE+"' and d.magasinDestination.id='"+magasinPF.getId()+"' and d.transferStockPF.dateTransfer between '"+dateDebut+"' and '"+dateFin+"' ";

listObjectQuantiteProduction=detailTransferProduitFiniDAO.QuantiteFabriqueParArticleParSousFamille(req);

CalculerPrixMoyen(magasinMP);
         
         
for(int t=0;t<listEtatBoxEtCartonConsommeParPF.size();t++)
{

	EtatBoxEtCartonConsommeParPF etatBoxEtCartonConsommeParPF=listEtatBoxEtCartonConsommeParPF.get(t);
	
	for (int d=0;d<listObjectQuantiteProduction.size();d++)
	{
		
		Object[] object=listObjectQuantiteProduction.get(d);  

		if(object[0]!=null)
		{
			
		if(object[1]!=null)
		{

		if(object[2]!=null)
		{
			
			Articles articles=(Articles)object[0];
			SousFamilleArticlePF sousFamilleArticlePF=(SousFamilleArticlePF)object[1];	
			
			if(etatBoxEtCartonConsommeParPF.getArticle().getId()==articles.getId())
			{
				
				if(etatBoxEtCartonConsommeParPF.getSousFamille().equals(sousFamilleArticlePF.getLiblle()))
				{
					
					etatBoxEtCartonConsommeParPF.setQuantiteRealiser((BigDecimal)object[2]);
					
				}
				
				
				
				
			}
			
		}
		
		}
		
		}
		
		
		
		
	}
	if(etatBoxEtCartonConsommeParPF.getMpBox()!=null)
	{
		if(mapPrixMoyenneMatierePremiere.get(etatBoxEtCartonConsommeParPF.getMpBox().getNom())!=null)
		{
			etatBoxEtCartonConsommeParPF.setPrixBox(mapPrixMoyenneMatierePremiere.get(etatBoxEtCartonConsommeParPF.getMpBox().getNom()));
			etatBoxEtCartonConsommeParPF.setMontantBox(etatBoxEtCartonConsommeParPF.getPrixBox().multiply(etatBoxEtCartonConsommeParPF.getQuantiteBoxConsomme()));
			
			
		}else
		{
			etatBoxEtCartonConsommeParPF.setPrixBox(BigDecimal.ZERO);
			etatBoxEtCartonConsommeParPF.setMontantBox(BigDecimal.ZERO);

		}
	}else
	{
		etatBoxEtCartonConsommeParPF.setPrixBox(BigDecimal.ZERO);
		etatBoxEtCartonConsommeParPF.setMontantBox(BigDecimal.ZERO);
	}
	
	
	 
	if(etatBoxEtCartonConsommeParPF.getMpCarton()!=null)
	{
		if(mapPrixMoyenneMatierePremiere.get(etatBoxEtCartonConsommeParPF.getMpCarton().getNom())!=null)
		{
			etatBoxEtCartonConsommeParPF.setPrixCarton (mapPrixMoyenneMatierePremiere.get(etatBoxEtCartonConsommeParPF.getMpCarton().getNom()));
			etatBoxEtCartonConsommeParPF.setMontantCarton(etatBoxEtCartonConsommeParPF.getPrixCarton().multiply(etatBoxEtCartonConsommeParPF.getQuantiteCartonConsomme()));
			
			
		}else
		{
			etatBoxEtCartonConsommeParPF.setPrixCarton(BigDecimal.ZERO);
			etatBoxEtCartonConsommeParPF.setMontantCarton(BigDecimal.ZERO);

		}
	}else
	{
		etatBoxEtCartonConsommeParPF.setPrixCarton(BigDecimal.ZERO);
		etatBoxEtCartonConsommeParPF.setMontantCarton(BigDecimal.ZERO);
	}
	
	
	
	
	
	
}




       
          
   System.out.println(quantiteTotalBoxConsomme);       
   
afficher_tableProd(listEtatBoxEtCartonConsommeParPF);
				
				
				
				
				
				
				
				
			}
		  }
		});
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		dateDebutChooser.setDateFormatString("yyyy-MM-dd");
		
		 
		dateDebutChooser.setBounds(92, 11, 174, 24);
		layeredPane.add(dateDebutChooser);
		dateFinChooser.setDateFormatString("yyyy-MM-dd");
		
		
		dateFinChooser.setBounds(331, 12, 210, 24);
		layeredPane.add(dateFinChooser);
		
		JButton btnAfficherDetailOF = new JButton("Exporter Excel");
		btnAfficherDetailOF.setIcon(imgExcel);
		btnAfficherDetailOF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			

			  		

		      		

					
				Client client=mapClient.get(comboclient.getSelectedItem().toString());
				
				if(client==null)
				{
					JOptionPane.showMessageDialog(null, "Veuillez Selectionner le client SVP SVP !!!", "Erreur", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				Magasin magasinMP=depotdao.magasinByCodeMachineByTypeMagasin(Constantes.MAGASIN_CODE_TYPE_MP, client.getCode());
				  
				  if(magasinMP!=null) {
					  
					if(table.getRowCount()!=0)
   					{
					
						
					  String
					  titre="Etat Quantite Box Et Carton Consommer Par Les PF "+ magasinMP.getLibelle(); 
					  String titrefeuille="Etat Quantite Box Et Carton Consommer Par Les PF";
					  
					  
					  ExporterTableVersExcel.tabletoexcelEtatBoxEtCartonConsommerParPF (table, titre,titrefeuille);
					  
   					}
				  
			
				  }else {
				  
				  
				  JOptionPane.showMessageDialog(null,
				  "Veuillez selectionner le Client SVP !!!","Attention",JOptionPane.
				  ERROR_MESSAGE); return;
				  
				  
				  }
				 
	    	
	      		
	      		
	      		
	      		
	      	
			  		
			  		
			  		
			  		
			  		
			  	
			
			}
		});
		btnAfficherDetailOF.setBounds(464, 630, 174, 24);
		add(btnAfficherDetailOF);
		
		
		  if(utilisateur.getLogin().equals("admin"))
		  {
			  listClient=clientdao.findAll();
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
				  listClient=clientdao.findListClientByCodeDepot(depot.getCode());
				  combodepot.addItem("");
				  combodepot.addItem(depot.getLibelle());
				
		     		mapDepot.put(depot.getLibelle(), depot);
			  }
		  };
		  
		    comboclient = new JComboBox();
		  comboclient.setBounds(665, 18, 365, 24);
		  layeredPane.add(comboclient);
		  AutoCompleteDecorator.decorate(comboclient);
		  
		  JLabel label = new JLabel("Client  :");
		  label.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
		  label.setBounds(585, 17, 70, 24);
		  layeredPane.add(label);
		
		
	      Depot depotTmp=depotdao.findByCode(utilisateur.getCodeDepot());
	      List<Magasin>  listmagasin =depotdao.listeMagasinByTypeMagasinDepot(depotTmp.getId(),  Constantes.MAGASIN_CODE_TYPE_PF);
	      if(depotTmp.getCode().equals(CODE_DEPOT_TANTAN))
	    	{
	    	 comboclient.addItem("");
	    	 
	    	} 
	      boolean exist=false;
	     int i=0;
	     while(i<listClient.size())
	     {
	    	   exist=false;
	    	Client client=listClient.get(i);
	    	
	    	if(depotTmp.getCode().equals(CODE_DEPOT_TANTAN))
	    	{
	    		comboclient.addItem(client.getNom());
		    	 mapClient.put(client.getNom(), client);
	    	}else
	    	{
	    		
	    		for(int t=0;t<listmagasin.size();t++)
	    		{
	    			if(listmagasin.get(t).getCodeMachine().equals(client.getCode()))
	    			{
	    				exist=true;
	    			}
	    			
	    		}
	    		
	    		 if(exist==true)
	    		 {
	    			 comboclient.addItem(client.getNom());
			    	 mapClient.put(client.getNom(), client); 
	    		 }
	    		
	    		
	    	}
	    	
	    	
	    	 
	    	 i++;
	     }
	    
	     if(depotTmp.getCode().equals(CODE_DEPOT_TANTAN))
	    	{
	    	 
	    	 comboclient.setSelectedItem("");
	    	 
	    	 
	    	} 
				  		     
				  		 
	}
	
void afficher_tableProd(List<EtatBoxEtCartonConsommeParPF> listEtatBoxEtCartonConsommeParPF)
	{
		intialiserTableau();
		
		 String Box="";
		 String carton="";
		 BigDecimal quantitebox=BigDecimal.ZERO;
		 BigDecimal prixbox=BigDecimal.ZERO;
		 BigDecimal montantbox=BigDecimal.ZERO;
		 BigDecimal quantiteCarton=BigDecimal.ZERO;
		 BigDecimal prixCarton=BigDecimal.ZERO;
		 BigDecimal montantCarton=BigDecimal.ZERO;
		 
			for (int i=0;i<listEtatBoxEtCartonConsommeParPF.size();i++)
			{
				
				   quantitebox=BigDecimal.ZERO;
				   prixbox=BigDecimal.ZERO;
				   montantbox=BigDecimal.ZERO;
				   quantiteCarton=BigDecimal.ZERO;
				   prixCarton=BigDecimal.ZERO;
				   montantCarton=BigDecimal.ZERO;
				
				if(listEtatBoxEtCartonConsommeParPF.get(i).getMpBox()!=null)
				{
					Box=listEtatBoxEtCartonConsommeParPF.get(i).getMpBox().getNom();
					quantitebox=listEtatBoxEtCartonConsommeParPF.get(i).getQuantiteBoxConsomme();
					 prixbox=listEtatBoxEtCartonConsommeParPF.get(i).getPrixBox();
					 montantbox=listEtatBoxEtCartonConsommeParPF.get(i).getMontantBox();
					
				}else
				{
					Box="-";
					  quantitebox=BigDecimal.ZERO;
					  prixbox=BigDecimal.ZERO;
					  montantbox=BigDecimal.ZERO;
					  
				}
				
				if(listEtatBoxEtCartonConsommeParPF.get(i).getMpCarton()!=null)
				{
					carton=listEtatBoxEtCartonConsommeParPF.get(i).getMpCarton().getNom();
					quantiteCarton=listEtatBoxEtCartonConsommeParPF.get(i).getQuantiteCartonConsomme();
					 prixCarton=listEtatBoxEtCartonConsommeParPF.get(i).getPrixCarton();
					   montantCarton=listEtatBoxEtCartonConsommeParPF.get(i).getMontantCarton();
				}else
				{
					carton="-";
					 quantiteCarton=BigDecimal.ZERO;
					 prixCarton=BigDecimal.ZERO;
					   montantCarton=BigDecimal.ZERO;
				}
				
				
					Object []ligne={listEtatBoxEtCartonConsommeParPF.get(i).getArticle().getCodeArticle(),listEtatBoxEtCartonConsommeParPF.get(i).getArticle().getLiblle(),listEtatBoxEtCartonConsommeParPF.get(i).getSousFamille(),listEtatBoxEtCartonConsommeParPF.get(i).getQuantiteRealiser(),carton,quantiteCarton,prixCarton,montantCarton,Box,quantitebox,prixbox,montantbox};
					modeleProd.addRow( ligne);
				 
				

				
			
			}
			
		
	}








void intialiserTableau(){
	modeleProd =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
  		     			"Code Article","Article", "Sous Famille", "Quantite Realiser","Carton","Quantite carton Consomme","Prix Carton","Montant Carton","Box","Quantite Box Consomme","Prix Box","Montant Box"


		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false,false,false,false,false,false,false,false,false,false
		     	};
		     	public boolean isCellEditable(int row, int column) {
		     		return columnEditables[column];
		     	}
		     };
		     
		 table.setModel(modeleProd); 
		 table.getColumnModel().getColumn(0).setPreferredWidth(60);
      table.getColumnModel().getColumn(1).setPreferredWidth(60);
      table.getColumnModel().getColumn(2).setPreferredWidth(60);
      table.getColumnModel().getColumn(3).setPreferredWidth(60);
      table.getColumnModel().getColumn(4).setPreferredWidth(60);
      table.getColumnModel().getColumn(5).setPreferredWidth(160);
      table.getColumnModel().getColumn(6).setPreferredWidth(60);
      table.getColumnModel().getColumn(7).setPreferredWidth(160);
      table.getColumnModel().getColumn(8).setPreferredWidth(60);
      table.getColumnModel().getColumn(9).setPreferredWidth(60);
      table.getColumnModel().getColumn(10).setPreferredWidth(60);
     
 
}


public void CalculerPrixMoyen(Magasin magasin)
{
	mapPrixMoyenneMatierePremiere.clear();
	

	listDetailTransferStockMPGroupebyMP=detailTransferMPDAO.findAllTransferStockMPGroupeByMP(magasin);
	BigDecimal montantfabriquer=new BigDecimal(0);
		BigDecimal quantiteTotalfabriquer=new BigDecimal(0);
	for(int g=0;g<listDetailTransferStockMPGroupebyMP.size();g++)
	{
		
		
		  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		listPrixMoyenStockMP.clear();
		boolean existe=false;
			String TousSubCategorie="";
			String Touscategorie="";
			String TousArticle="";
			String requete="";
			if(magasin!=null)
			{
				
				requete=" d.transferStockMP.statut='"+Constantes.ETAT_TRANSFER_STOCK_INITIAL+"' ";
				
				requete=requete+"and  d.magasinDestination.id = '"+magasin.getId()+"' ";
				
				if(dateDebutChooser.getDate()!=null && dateFinChooser.getDate()!=null)
	  		{
					
	  		if(	DateUtils.nbJoursEntre(dateDebutChooser.getDate(), dateFinChooser.getDate())>=0)
	  		{
	  			
	  			String dateDu=formatter.format(dateDebutChooser.getDate());
	  			String dateAu=formatter.format(dateFinChooser.getDate());
	  			
	  			
	  			requete=requete+"and  d.transferStockMP.dateTransfer between '"+dateDu +"' and '"+dateAu+"' ";
	  			
	  			
	  			CategorieMp categorieMP=null;
	  			SubCategorieMp souscategorieMP=null;
	  			
	  			  
	  			
	  			MatierePremier mp=listDetailTransferStockMPGroupebyMP.get(g).getMatierePremier();
	  			categorieMP=mp.getCategorieMp();
	  			souscategorieMP=mp.getCategorieMp().getSubCategorieMp();
	  			if(souscategorieMP!=null)
	  			{
	  				requete=requete+"and  d.matierePremier.categorieMp.subCategorieMp.id = '"+souscategorieMP.getId()+"' ";
	  				
	  			}
	  			
	  			if(categorieMP!=null)
	  			{
	  				requete=requete+"and  d.matierePremier.categorieMp.id = '"+categorieMP.getId()+"' ";
	  				
	  			}
	  			
	  		
	  			
	  			
	  	
	  			
	  			if(mp!=null)
	  			{
	  				
	  				requete=requete+"and  d.matierePremier.id = '"+mp.getId()+"' ";	
	  				
	  				
	  			}
	  			
	  			
	  			
	  			
		  	       	listDetailTransfertStockMP=detailTransferMPDAO.listeDetailTransfertMP(requete);
		  			 for(int i=0;i<listDetailTransfertStockMP.size();i++)
		  			 {
		  				 
		  				DetailTransferStockMP detailTransferStockMP= listDetailTransfertStockMP.get(i);
		  				
		  			PrixMoyenStockMP prixMoyenStockMP=new PrixMoyenStockMP();
		  			prixMoyenStockMP.setMp(detailTransferStockMP.getMatierePremier());
		  			prixMoyenStockMP.setPrixInitial(detailTransferStockMP.getPrixUnitaire());
		  			prixMoyenStockMP.setQuantiteInitial(detailTransferStockMP.getQuantite());
		  			prixMoyenStockMP.setMontantInitial(detailTransferStockMP.getPrixUnitaire().multiply(detailTransferStockMP.getQuantite()));
		  			prixMoyenStockMP.setQuantiteAchat(BigDecimal.ZERO);	
		  			prixMoyenStockMP.setPrixAchat(BigDecimal.ZERO);
		  			prixMoyenStockMP.setMontantAchat(BigDecimal.ZERO);
		  			prixMoyenStockMP.setQuantiteFabriquer (BigDecimal.ZERO);	
		  			prixMoyenStockMP.setPrixFabriquer(BigDecimal.ZERO);
		  			prixMoyenStockMP.setMontantFabriquer(BigDecimal.ZERO);
		  			prixMoyenStockMP.setQuantiteFinale(detailTransferStockMP.getQuantite());
		  			prixMoyenStockMP.setPrixMoyen(detailTransferStockMP.getPrixUnitaire());
		  			prixMoyenStockMP.setMontantHTFinale(detailTransferStockMP.getPrixUnitaire().multiply(detailTransferStockMP.getQuantite()));
		  			
		  			
		  			SousFamilleEnVrac sousFamilleEnVrac=sousFamilleEnVracDAO.findByMP(detailTransferStockMP.getMatierePremier());
		  			if(sousFamilleEnVrac!=null)
		  			{
		  				
		  				prixMoyenStockMP.setFamille(sousFamilleEnVrac.getSousfamile().getFamileArticlePF().getLiblle());
		  				prixMoyenStockMP.setSousfamille(sousFamilleEnVrac.getSousfamile().getLiblle());
		  				
		  			}else
		  			{
		  				
		  				
		  				prixMoyenStockMP.setFamille(detailTransferStockMP.getMatierePremier().getCategorieMp().getSubCategorieMp().getNom());
		  				prixMoyenStockMP.setSousfamille(detailTransferStockMP.getMatierePremier().getCategorieMp().getNom());
		  				
		  				
		  			}
		  			
		  			listPrixMoyenStockMP.add(prixMoyenStockMP);
		  				 
		  				 
		  			 }
		  			 
	
		  			 
		  			 
		  			 
		  			 
		  				
		  				listeObject=detailFactureAchatMPdao.listeEtatPrixMoyenMP (dateDebutChooser.getDate(), dateFinChooser.getDate(),magasin,souscategorieMP,mp, TousSubCategorie,TousArticle, categorieMP , Touscategorie);
		  				
		  				
		  				
		  				for(int j=0;j<listeObject.size();j++)
		  				{
		  					existe=false;
		  					 Object[] object=listeObject.get(j);
		  					 
		  					 for(int k=0;k<listPrixMoyenStockMP.size();k++)
		  					 {
		  						 
		  						PrixMoyenStockMP prixMoyenStockMP=listPrixMoyenStockMP.get(k); 
		  						 
		  						 
		  						 
		  						if(object[0].toString().equals(prixMoyenStockMP.getMp().getCode()))
		  						{
		  							
		  							existe=true;
		  							
		  							prixMoyenStockMP.setQuantiteAchat(new BigDecimal(object[4].toString()));	
						  			prixMoyenStockMP.setPrixAchat(new BigDecimal(object[5].toString()));
						  			prixMoyenStockMP.setMontantAchat(new BigDecimal(object[4].toString()).multiply(new BigDecimal(object[5].toString())));
						  			prixMoyenStockMP.setQuantiteFinale(prixMoyenStockMP.getQuantiteAchat().add(prixMoyenStockMP.getQuantiteInitial()));
						  			prixMoyenStockMP.setPrixMoyen((prixMoyenStockMP.getMontantInitial().add(prixMoyenStockMP.getMontantAchat())).divide(prixMoyenStockMP.getQuantiteAchat().add(prixMoyenStockMP.getQuantiteInitial()), 6, RoundingMode.HALF_UP));
						  			prixMoyenStockMP.setMontantHTFinale(prixMoyenStockMP.getQuantiteFinale().multiply(prixMoyenStockMP.getPrixMoyen()));
						  			 
						  			
						  			listPrixMoyenStockMP.set(k, prixMoyenStockMP);
		  							
		  						}
		  						
		  						 
		  					 }
		  					
		  					 if(existe==false)
		  					 {
		  						
		  						MatierePremier matierePremier=matierePremiereDAO.findByCode(object[0].toString()) ;
		  						 
		  						SousFamilleEnVrac sousFamilleEnVrac=sousFamilleEnVracDAO.findByMP(matierePremier);
		  						
		  						
		  						
		  						PrixMoyenStockMP prixMoyenStockMP=new PrixMoyenStockMP();
					  			prixMoyenStockMP.setMp(matierePremier);
					  			prixMoyenStockMP.setPrixInitial(BigDecimal.ZERO);
					  			prixMoyenStockMP.setQuantiteInitial(BigDecimal.ZERO);
					  			prixMoyenStockMP.setMontantInitial(BigDecimal.ZERO);
					  			prixMoyenStockMP.setQuantiteAchat(new BigDecimal(object[4].toString()));	
					  			prixMoyenStockMP.setPrixAchat(new BigDecimal(object[5].toString()));
					  			prixMoyenStockMP.setMontantAchat(new BigDecimal(object[4].toString()).multiply(new BigDecimal(object[5].toString())));
					  			prixMoyenStockMP.setQuantiteFinale(new BigDecimal(object[4].toString()));
					  			prixMoyenStockMP.setPrixMoyen(new BigDecimal(object[5].toString()));
					  			prixMoyenStockMP.setQuantiteFabriquer (BigDecimal.ZERO);	
					  			prixMoyenStockMP.setPrixFabriquer(BigDecimal.ZERO);
					  			prixMoyenStockMP.setMontantFabriquer(BigDecimal.ZERO);
					  			prixMoyenStockMP.setMontantHTFinale(new BigDecimal(object[4].toString()).multiply(new BigDecimal(object[5].toString())));
					  			
					  			if(sousFamilleEnVrac!=null)
					  			{
					  				
					  				prixMoyenStockMP.setFamille(sousFamilleEnVrac.getSousfamile().getFamileArticlePF().getLiblle());
					  				prixMoyenStockMP.setSousfamille(sousFamilleEnVrac.getSousfamile().getLiblle());
					  				
					  			}else
					  			{
					  				
					  				
					  				prixMoyenStockMP.setFamille(matierePremier.getCategorieMp().getSubCategorieMp().getNom());
					  				prixMoyenStockMP.setSousfamille(matierePremier.getCategorieMp().getNom());
					  				
					  				
					  			}
					  			
					  			
					  			listPrixMoyenStockMP.add(prixMoyenStockMP); 
		  						 
		  						 
		  						 
		  						 
		  						 
		  						 
		  					 }
		  					 
		  					 
		  					 
		  					 
		  					 
		  					
		  				}
		  				
		  				
		  				listDetailTransferStockMPFabrique=detailTransferMPDAO.ListTransferStockMPEntreDeuxDatesBYMPStatutAchat(dateDebutChooser.getDate(), dateFinChooser.getDate(), mp, ETAT_TRANSFER_STOCK_FABRIQUE,magasin);

		  					// calculer le prix moyen et quantite Fabriquer
		  				
		  			
		  			  	 
		  			  		montantfabriquer=new BigDecimal(0);
		  			  		quantiteTotalfabriquer=new BigDecimal(0);
		  			  		
		  			  	for(int k=0;k<listDetailTransferStockMPFabrique.size();k++)
		  			  	{
		  			  		
		  			  		if(mp.equals(listDetailTransferStockMPFabrique.get(k).getMatierePremier()))
		  			  		{
		  			  			
		  			  			if(listDetailTransferStockMPFabrique.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || listDetailTransferStockMPFabrique.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || listDetailTransferStockMPFabrique.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || listDetailTransferStockMPFabrique.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || listDetailTransferStockMPFabrique.get(k).getMatierePremier().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
		  							{
		  			  				montantfabriquer=montantfabriquer.add(listDetailTransferStockMPFabrique.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPFabrique.get(k).getQuantite()));
		  				    			quantiteTotalfabriquer=quantiteTotalfabriquer.setScale(0, RoundingMode.CEILING).add(listDetailTransferStockMPFabrique.get(k).getQuantite().setScale(0, RoundingMode.CEILING));
		  							}else
		  							{
		  								montantfabriquer=montantfabriquer.add(listDetailTransferStockMPFabrique.get(k).getPrixUnitaire().multiply(listDetailTransferStockMPFabrique.get(k).getQuantite()));
		  				    			quantiteTotalfabriquer=quantiteTotalfabriquer.add(listDetailTransferStockMPFabrique.get(k).getQuantite());
		  							}
		  			  
		  			  			//System.out.println(listDetailTransferStockMPAchatGroupebyMP.get(j).getMatierePremier().getNom() + " : "+listDetailTransferStockMPAchat.get(k).getQuantite());
		  			  			
		  			  		}
		  			  		
		  			  		
		  			  	}
		  			  		if(!montantfabriquer.equals(BigDecimal.ZERO) && !quantiteTotalfabriquer.equals(BigDecimal.ZERO))
		  			  		{
		  			  			/*	    			
		  			  			listDetailTransferStockMPAchatGroupebyMP.get(j).setQuantite(quantiteTotalachat);
		  			  			listDetailTransferStockMPAchatGroupebyMP.get(j).setPrixUnitaire(montantachat.divide(quantiteTotalachat,6,RoundingMode.HALF_UP));
		  			  			*/
		  			  			
		  			  			
		  			  			
		  			  			for(int i=0;i<listPrixMoyenStockMP.size();i++)
		  			  	    	{
		  			  			PrixMoyenStockMP prixMoyenStockMPTmp=listPrixMoyenStockMP.get(i); 
		  			  				
		  			  				if(prixMoyenStockMPTmp.getMp().getId()== mp.getId())
		  				    			{
		  			  					 
		  			  			
		  			  					if(prixMoyenStockMPTmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_BOX) || prixMoyenStockMPTmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_CARTON) || prixMoyenStockMPTmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_TAMPON) || prixMoyenStockMPTmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_STICKERS) || prixMoyenStockMPTmp.getMp().getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.CODE_THERRES_VERRES) )
		  			  					{
		  			  					prixMoyenStockMPTmp.setQuantiteFabriquer (quantiteTotalfabriquer.setScale(0, RoundingMode.CEILING));
		  			  				prixMoyenStockMPTmp.setPrixFabriquer(montantfabriquer.divide(quantiteTotalfabriquer.setScale(0, RoundingMode.CEILING),6,RoundingMode.HALF_UP));
		  			  			prixMoyenStockMPTmp.setMontantFabriquer(prixMoyenStockMPTmp.getQuantiteFabriquer().multiply(prixMoyenStockMPTmp.getPrixFabriquer()));
		  					    				 
		  			  						
		  			  						
		  			  					}else
		  			  					{
		  			  						
		  			  					prixMoyenStockMPTmp.setQuantiteFabriquer(quantiteTotalfabriquer);
		  			  				prixMoyenStockMPTmp.setPrixFabriquer(montantfabriquer.divide(quantiteTotalfabriquer,6,RoundingMode.HALF_UP));
		  			  			prixMoyenStockMPTmp.setMontantFabriquer(prixMoyenStockMPTmp.getQuantiteFabriquer().multiply(prixMoyenStockMPTmp.getPrixFabriquer()));
		  					    				 
		  			  					}
		  			  			
		  			  					
		  				    				
		  				    			}
		  			  				prixMoyenStockMPTmp.setPrixMoyen((prixMoyenStockMPTmp.getMontantInitial().add(prixMoyenStockMPTmp.getMontantAchat()).add(prixMoyenStockMPTmp.getMontantFabriquer())).divide(prixMoyenStockMPTmp.getQuantiteAchat().add(prixMoyenStockMPTmp.getQuantiteInitial()).add(prixMoyenStockMPTmp.getQuantiteFabriquer()), 6, RoundingMode.HALF_UP));

		  			  				
		  			  			listPrixMoyenStockMP.set(i, prixMoyenStockMPTmp);	
		  			  	    	}
		  			  			
		  			  			
		  			  			
		  			  			
		  			  			
		  			  		}
		  			  		
		  			   	
		  				 
		  				
		  				for(int y=0;y<listPrixMoyenStockMP.size();y++)
		  				{
		  					
		  					
		  					if(mp.getId()==listPrixMoyenStockMP.get(y).getMp().getId())
		  					{
		  						if(mp.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_CADEAU))
		  						{
		  							mapPrixMoyenneMatierePremiere.put(mp.getNom(), BigDecimal.ZERO);
		  						}else
		  						{
		  							mapPrixMoyenneMatierePremiere.put(mp.getNom(), listPrixMoyenStockMP.get(y).getPrixMoyen());
		  							
		  						}
		  						
		  				//JOptionPane.showMessageDialog(null, mp.getNom()+" : "+ listPrixMoyenStockMP.get(y).getPrixMoyen());
		  					}
		  					
		  					
		  				}
		  				
		  				
		  				
		  				  
		  			  
		  			  
		  		 
		  			  
		  		
		  		  
	  			
	  		}else
	  		{
	  			JOptionPane.showMessageDialog(null, "La date de dÈbut doit etre supÈrieur au date fin SVP !!!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
	  			return;
	  		}
	  		
	  		}
			}else
			{
				JOptionPane.showMessageDialog(null, "Veuillez selectionner le magasin SVP !!!!","Erreur",JOptionPane.ERROR_MESSAGE);
				return;
			}
		
	}
	




	
}




}
