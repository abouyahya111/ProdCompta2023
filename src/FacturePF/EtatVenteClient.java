package FacturePF;

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
import org.eclipse.swt.internal.cde.DtActionArg;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import Production.MatierePremiere;
import util.Constantes;
import util.ConverterNumberToWords;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.ClientPFDAOImpl;
import dao.daoImplManager.DetailFacturePFDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.ClientPFDAO;
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
import dao.entity.EtatVentePFParClientPF;
import dao.entity.EtatVentePFParFamille;
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
import javax.swing.JCheckBox;


public class EtatVenteClient extends JLayeredPane implements Constantes{
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
	private List<ClientPF> listClientPF =new ArrayList<ClientPF>();
	private Map< String, ClientPF> mapClientPF= new HashMap<>();
	
	
	private Map< String, Depot> mapDepot= new HashMap<>();
	private Map< String, Magasin> mapMagasin= new HashMap<>();
	private Map< String, FamilleArticlePF> mapFamillePF= new HashMap<>();
	private List<EtatVentePFParClientPF> listVentePFParClientPF =new ArrayList<EtatVentePFParClientPF>();
	private List <Object[]> listeObject=new ArrayList<Object[]>();
	private JScrollPane scrollPane;
	private JTable table;
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
	private ClientPFDAO clientpfdao;
	 JComboBox comboClientpf = new JComboBox();


	
	
	
	
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public EtatVenteClient() {
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
            depotdao=ProdLauncher.depotDAO;
            utilisateur=AuthentificationView.utilisateur;
                          
            clientpfdao=new ClientPFDAOImpl();
            listClientPF=clientpfdao.findAll();
            
            
          } catch (Exception exp){exp.printStackTrace();}
        
        
        
	    
	      	
	     final Utilisateur utilCreation=AuthentificationView.utilisateur;
				  		   		
				  		   			
				  		   		
				  		   			
				  		   			scrollPane = new JScrollPane((Component) null);
				  		   			scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			scrollPane.setBounds(36, 269, 1152, 378);
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
				  		   					"Date Facture", "Num Facture","Client", "Montant HT", "Montant TVA","Montant TTC"
				  		   				}
				  		   			));
				  		   			table.getColumnModel().getColumn(0).setPreferredWidth(221);
				  		   			table.getColumnModel().getColumn(1).setPreferredWidth(120);
				  		   			table.getColumnModel().getColumn(2).setPreferredWidth(123);
				  		   		table.getColumnModel().getColumn(3).setPreferredWidth(123);
				  		   	table.getColumnModel().getColumn(4).setPreferredWidth(123);
				  		  table.getColumnModel().getColumn(5).setPreferredWidth(123);
				  		   			table.setFillsViewportHeight(true);
				  		   			
				  		   			btnImprimer = new JButton("Imprimer");
				  		   			btnImprimer.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent arg0) {
				  		   					
				  		   					if(listVentePFParClientPF.size()!=0)
				  		   					{
				  		   						
				  		   				
				  		   				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
							  		  	String datedebut=dateFormat.format(dateChooserdebut.getDate());
							  		 	String dateFin=dateFormat.format(dateChooserfin.getDate());
							  		  
										
							  		    
										Map parameters = new HashMap();
										parameters.put("datedebut", datedebut);
										parameters.put("dateFin", dateFin);
										
										JasperUtils.imprimerEtatVenteParClientPF(listVentePFParClientPF, parameters);
										
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
				  		   			
				  		   			lblEtatDeVente = new JLabel("             Etat de Vente Par Client");
				  		   			lblEtatDeVente.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
				  		   			lblEtatDeVente.setBackground(Color.WHITE);
				  		   			lblEtatDeVente.setBounds(143, 40, 926, 35);
				  		   			add(lblEtatDeVente);
				  		   			
				  		   			JLayeredPane layeredPane = new JLayeredPane();
				  		   			layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			layeredPane.setBounds(10, 111, 1180, 92);
				  		   			add(layeredPane);
				  		   			
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
					  		 		     				
					  		 		     			mapClientPF.clear();
					  			     				listClientPF=clientpfdao.findListClientByCodeDepot(depot.getCode());
					  			     				
					  			     				if(listClientPF.size()!=0)
					  			     				{
					  			     					comboClientpf.removeAllItems();
					  			     					comboClientpf.addItem("");
					  			     					for(int j=0;j<listClientPF.size();j++)
					  			     					{
					  			     						ClientPF clientpf=listClientPF.get(j);
					  			     						
					  			     						comboClientpf.addItem(clientpf.getNom());
					  			     						mapClientPF.put(clientpf.getNom(), clientpf);
					  			     						
					  			     						
					  			     					}
					  			     				}else
					  			     				{
					  			     					comboClientpf.removeAllItems();
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
				  		   			combodepot.setBounds(60, 27, 143, 24);
				  		   			layeredPane.add(combodepot);
				  		   			
				  		   			label_1 = new JLabel("Magasin :");
				  		   			label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			label_1.setBounds(213, 27, 56, 24);
				  		   			layeredPane.add(label_1);
				  		   			
				  		   			combomagasin = new JComboBox();
				  		   			combomagasin.setSelectedIndex(-1);
				  		   			combomagasin.setBounds(279, 28, 154, 24);
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
				  				  
				  				  button_1 = new JButton();
				  				  button_1.setBounds(1133, 20, 31, 31);
				  				  layeredPane.add(button_1);
				  				  button_1.addActionListener(new ActionListener() {
				  				  	public void actionPerformed(ActionEvent e) {
				  				  		Magasin magasin=mapMagasin.get(combomagasin.getSelectedItem());
				  				  		
				  				  		if(magasin!=null)
				  				  		{
				  				  			
				  				  	
					  		   				String dateDebut=((JTextField)dateChooserdebut.getDateEditor().getUiComponent()).getText();
					  						String dateFin=((JTextField)dateChooserfin.getDateEditor().getUiComponent()).getText();
					  					if(dateDebut.equals(""))	{
					  						JOptionPane.showMessageDialog(null, "Il faut choisir Date Début", "Erreur", JOptionPane.ERROR_MESSAGE);
					  						return;
					  					} else if(dateFin.equals("")){
					  						JOptionPane.showMessageDialog(null, "Il faut choisir Date Fin", "Erreur", JOptionPane.ERROR_MESSAGE);
					  						return ;
					  					
					  						}else {

					  						
					  							if(comboClientpf.getSelectedIndex()==-1)
					  							{
					  								
					  								JOptionPane.showMessageDialog(null, "Il faut selectionner le client SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
							  						return ;
					  								
					  							}else
					  							{
					  								
					  								ClientPF clientPF=mapClientPF.get(comboClientpf.getSelectedItem());
					  								
					  								listVentePFParClientPF.clear();


					  								
					  								listeObject=detailfacturedao.listeEtatVentePFParClientPF(dateChooserdebut.getDate(), dateChooserfin.getDate(), magasin ,clientPF, Constantes.ETAT_REGLE);
					  								
					  									
					  									for(int i=0;i<listeObject.size() ; i++)
						  								{
						  									
						  									 Object[] object=listeObject.get(i);
						  									 
						  									@SuppressWarnings("deprecation")
															Date datefacture=(Date) object[0] ;
						  										ClientPF clientPFTmp=mapClientPF.get(object[2]);
						  										EtatVentePFParClientPF etatVentePFParClientPF=new EtatVentePFParClientPF();
						  										etatVentePFParClientPF.setClientPF(clientPFTmp);
						  										etatVentePFParClientPF.setDateFacture(datefacture);
						  										etatVentePFParClientPF.setNumFacture (String.valueOf(object[1]));
						  										etatVentePFParClientPF.setMontantHT(new BigDecimal(object[3].toString()));
						  										etatVentePFParClientPF.setMontantTVA(new BigDecimal(object[4].toString()));
						  										etatVentePFParClientPF.setMontantTTC(new BigDecimal(object[5].toString()));
						  										listVentePFParClientPF.add(etatVentePFParClientPF);
						  								}
					  									
					  								
					  								
					  								
					  								afficher_tableEtatVenteClient(listVentePFParClientPF);
					  								
					  							
					  								
					  								
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
				  				  
				  				  JLabel lblClient = new JLabel("Client :");
				  				  lblClient.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  				  lblClient.setBounds(443, 27, 56, 24);
				  				  layeredPane.add(lblClient);
				  				  
				  				   comboClientpf = new JComboBox();
				  				  comboClientpf.setSelectedIndex(-1);
				  				  comboClientpf.setBounds(492, 28, 194, 24);
				  				  layeredPane.add(comboClientpf);
				  				 AutoCompleteDecorator.decorate(comboClientpf);
				  				  dateChooserdebut = new JDateChooser();
				  				  dateChooserdebut.setBounds(767, 27, 139, 26);
				  				  layeredPane.add(dateChooserdebut);
				  				  dateChooserdebut.setLocale(Locale.FRANCE);
				  				  dateChooserdebut.setDateFormatString("dd/MM/yyyy");
				  				  
				  				  lblDateDebut = new JLabel("Date Debut  :");
				  				  lblDateDebut.setBounds(696, 28, 72, 24);
				  				  layeredPane.add(lblDateDebut);
				  				  
				  				  dateChooserfin = new JDateChooser();
				  				  dateChooserfin.setBounds(973, 23, 150, 26);
				  				  layeredPane.add(dateChooserfin);
				  				  dateChooserfin.setLocale(Locale.FRANCE);
				  				  dateChooserfin.setDateFormatString("dd/MM/yyyy");
				  				  
				  				  lblDateFin = new JLabel("Date Fin  :");
				  				  lblDateFin.setBounds(916, 27, 62, 24);
				  				  layeredPane.add(lblDateFin);
				  				
				  		   			
				  		   		
				  		   			
	}
	
	

	


	
	
	
	
	
	
	void afficher_tableEtatVenteClient(List<EtatVentePFParClientPF> listEtatVenteParClientPF)
	{
		
		

		modeleCompte =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Date Facture", "Num Facture","Client", "Montant HT", "Montant TVA","Montant TTC"
	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false,false,false,false,false
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
			while(i<listEtatVenteParClientPF.size())
			{	
				
				
				Object []ligne={listEtatVenteParClientPF.get(i).getDateFacture() ,listEtatVenteParClientPF.get(i).getNumFacture() , listEtatVenteParClientPF.get(i).getClientPF().getNom() ,listEtatVenteParClientPF.get(i).getMontantHT(),listEtatVenteParClientPF.get(i).getMontantTVA(),listEtatVenteParClientPF.get(i).getMontantTTC()};

				modeleCompte.addRow(ligne);
				i++;
			}
	}
}
