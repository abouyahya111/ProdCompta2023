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
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import Production.MatierePremiere;
import util.Constantes;
import util.ConverterNumberToWords;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.ClientPFDAOImpl;
import dao.daoImplManager.CompteClientDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailCompteClientDAOImpl;
import dao.daoImplManager.FournisseurDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.ClientPFDAO;
import dao.daoManager.CompteClientDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailCompteClientDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.FournisseurDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.SubCategorieMPDAO;
import dao.entity.CategorieMp;
import dao.entity.Client;
import dao.entity.ClientPF;
import dao.entity.CompteClient;
import dao.entity.Depot;
import dao.entity.DetailCompteClient;
import dao.entity.DetailEstimation;
import dao.entity.FacturePF;
import dao.entity.FamilleArticlePF;
import dao.entity.Fournisseur;
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
import javax.swing.table.TableModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.toedter.calendar.JDateChooser;

import java.util.Locale;


public class ConsulterCompte extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	
	private DefaultTableModel	 modeleDetailCompte;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private ImageIcon imgSupprimer;
	private ImageIcon imgChercher;
	private ImageIcon imgImprimer;
	
	private  CompteClientDAO compteClientdao;
	
	private ClientDAO fournisseurdao;
	private ClientPFDAO clientpfdao;
	private DepotDAO depotDAO;
	
	
	private Map< String, ClientPF> mapClientPF= new HashMap<>();
	private Map< String, Client> mapFournisseur= new HashMap<>();

	private List<CompteClient> listCompte =new ArrayList<CompteClient>();
	private List<Client> listFournisseur =new ArrayList<Client>();
	private List<ClientPF> listClientPF =new ArrayList<ClientPF>();
	private List<DetailCompteClient> listDetailCompte =new ArrayList<DetailCompteClient>();
	private DetailCompteClientDAO detailCompteClientdao;
	
	
	private JScrollPane scrollPane;
	private JTable table;
	private Utilisateur utilisateur;
	private JTextField txtsolde;
	private JTextField txttva;
	private String client="";
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ConsulterCompte() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

        setBounds(0, 0, 1284, 882);
       
        try{
        	   utilisateur=AuthentificationView.utilisateur;
        	compteClientdao=new CompteClientDAOImpl();
        	fournisseurdao=new ClientDAOImpl();
        	clientpfdao=new ClientPFDAOImpl();
        	detailCompteClientdao=new DetailCompteClientDAOImpl();
        	depotDAO=new DepotDAOImpl();
        	
      

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp.png"));
            imgChercher=new ImageIcon(this.getClass().getResource("/img/chercher.png"));
            imgImprimer=new ImageIcon(this.getClass().getResource("/img/imprimer.png"));
          } catch (Exception exp){exp.printStackTrace();}
        
        
        
	    
	      	
	     final Utilisateur utilCreation=AuthentificationView.utilisateur;
				  		   		 incrementercode();
				  		   			
				  		   		
				  		   			
				  		   			scrollPane = new JScrollPane((Component) null);
				  		   			scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			scrollPane.setBounds(10, 217, 1253, 302);
				  		   			add(scrollPane);
				  		   			
				  		   			table = new JTable();
				  		   			table.addMouseListener(new MouseAdapter() {
				  		   				@Override
				  		   				public void mouseClicked(MouseEvent arg0) {
				  		   					
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   			scrollPane.setViewportView(table);
				  		   			table.setModel(new DefaultTableModel(
				  		   				new Object[][] {
				  		   				},
				  		   				new String[] {
				  		   					"Designation", "Montant Debit", "Montant Cr\u00E9dit", "Compte ", "Num Facture"
				  		   				}
				  		   			));
				  		   			table.getColumnModel().getColumn(0).setPreferredWidth(209);
				  		   			table.getColumnModel().getColumn(1).setPreferredWidth(120);
				  		   			table.getColumnModel().getColumn(2).setPreferredWidth(123);
				  		   			table.getColumnModel().getColumn(3).setPreferredWidth(120);
				  		   			table.getColumnModel().getColumn(4).setPreferredWidth(121);
				  		   			table.setFillsViewportHeight(true);
				  		   			
				  		   			JLayeredPane layeredPane = new JLayeredPane();
				  		   			layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			layeredPane.setBounds(10, 120, 1252, 51);
				  		   			add(layeredPane);
				  		   			
				  		   			JLabel label = new JLabel("Client :");
				  		   			label.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			label.setBounds(10, 11, 56, 24);
				  		   			layeredPane.add(label);
				  		   			
				  		   			JComboBox comboclient = new JComboBox();
				  		   			comboclient.setSelectedIndex(-1);
				  		   			comboclient.setBounds(57, 11, 183, 24);
				  		   			layeredPane.add(comboclient);
				  		   		  AutoCompleteDecorator.decorate(comboclient);
				  		   			Depot depot=depotDAO.findByCode(utilisateur.getCodeDepot());
				  		   			listClientPF=clientpfdao.findListClientByCodeDepot(depot.getCode());
				  		   			int i=0;
				  		   			while(i<listClientPF.size())
				  		   			{
				  		   				ClientPF clientpf=listClientPF.get(i);
				  		   				comboclient.addItem(clientpf.getNom());
				  		   				mapClientPF.put(clientpf.getNom(), clientpf);
				  		   				
				  		   				i++;
				  		   			}
				  		   			
				  		   			JLabel label_1 = new JLabel("Fournisseur :");
				  		   			label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			label_1.setBounds(250, 11, 79, 24);
				  		   			layeredPane.add(label_1);
				  		   			
				  		   			JComboBox combofournisseur = new JComboBox();
				  		   			combofournisseur.setSelectedIndex(-1);
				  		   			combofournisseur.setBounds(328, 12, 165, 24);
				  		   			layeredPane.add(combofournisseur);
				  		   		 AutoCompleteDecorator.decorate(combofournisseur);
				  		   			String codeClient ="";
				  		   			if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_AGADIR))
				  		   			{
				  		   				Magasin magasin=depotDAO.magasinByCode(MAGASIN_PF_AHL_BRAHIM);
				  		   			codeClient=magasin.getCodeMachine();
				  		   		listFournisseur.add(fournisseurdao.findClientByCodeClient(codeClient)) ;
				  		   				
				  		   			}else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_TANTAN))
				  		   			{
				  		   				
				  		   			Magasin magasin=depotDAO.magasinByCode(Constantes.CODE_MAGASIN_ELNASS_TEA_PACKING_PF);
				  		   			codeClient=magasin.getCodeMachine();
				  		   			
				  		   		listFournisseur.add(fournisseurdao.findClientByCodeClient(codeClient)) ;
				  		   			}else if(utilisateur.getCodeDepot().equals(Constantes.CODE_DEPOT_SIEGE))
				  		   			{
				  		   				
				  		   				
				  		   			listFournisseur=fournisseurdao.findAll() ;
				  		   			
				  		   			}
				  		   			
				  		   			
				  		   		
			  		   			int j=0;
			  		   			while(j<listFournisseur.size())
			  		   			{
			  		   				Client fournisseur=listFournisseur.get(j);
			  		   				combofournisseur.addItem(fournisseur.getNom());
			  		   				mapFournisseur.put(fournisseur.getNom(), fournisseur);
			  		   				
			  		   				j++;
			  		   			}
				  		   			
				  		   			JButton button = new JButton("Consulter");
				  		   			button.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent arg0) {
				  		   					BigDecimal MontantTVA=BigDecimal.ZERO;
				  		   				BigDecimal solde=BigDecimal.ZERO;
				  		   					if(comboclient.getSelectedIndex()==-1)
				  		   					{
				  		   						JOptionPane.showMessageDialog(null, "Veuillez selectionner le client SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
				  		   						return;
				  		   						
				  		   					}else if(combofournisseur.getSelectedIndex()==-1)
				  		   					{
				  		   					JOptionPane.showMessageDialog(null, "Veuillez selectionner le Fournisseur SVP ","Erreur",JOptionPane.ERROR_MESSAGE);
			  		   						return;
				  		   					}else
				  		   					{
				  		   						
				  		   					ClientPF clientpf=mapClientPF.get(comboclient.getSelectedItem());
				  		   					Client fournisseur=mapFournisseur.get(combofournisseur.getSelectedItem());
				  		   				client=clientpf.getNom();
				  		   				listDetailCompte=detailCompteClientdao.findByFournisseurEtClient(clientpf.getCompteClient(), fournisseur);
				  		   				if(listDetailCompte.size()!=0)
				  		   				{
				  		   					for(int i=0;i<listDetailCompte.size();i++)
				  		   					{
				  		   						
				  		   					solde=solde.add(listDetailCompte.get(i).getMontantDebit().subtract(listDetailCompte.get(i).getMontantCredit()));
				  		   					}
				  		   					
				  		   					txtsolde.setText(solde+"");
				  		   					
				  		   					////////***** calculer TVA Solde = Solde - (solde/1.2)   ************///////////
				  		   					
				  		   				MontantTVA= solde.subtract(solde.divide(new BigDecimal(1.2), 6, RoundingMode.HALF_UP)) ;
				  		   				txttva.setText(MontantTVA+"");
				  		   					afficher_tableDetailCompte(listDetailCompte);
				  		   					
				  		   				}else
				  		   				{
				  		   					
				  		   				txtsolde.setText("");
				  		   				txttva.setText("");
				  		   				listDetailCompte.clear();
				  		   				afficher_tableDetailCompte(listDetailCompte);
				  		   				
				  		   				}
				  		   						
				  		   						
				  		   						
				  		   					}
				  		   					
				  		   					
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   			button.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			button.setBounds(562, 13, 107, 24);
				  		   		    button.setIcon(imgChercher);
				  		   			layeredPane.add(button);
				  		   			
				  		   			JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		   			GridBagLayout gridBagLayout = (GridBagLayout) titledSeparator.getLayout();
				  		   			gridBagLayout.rowWeights = new double[]{0.0};
				  		   			gridBagLayout.rowHeights = new int[]{0};
				  		   			gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
				  		   			gridBagLayout.columnWidths = new int[]{0, 0, 0};
				  		   			titledSeparator.setTitle("Compte par :");
				  		   			titledSeparator.setBounds(10, 87, 1252, 30);
				  		   			add(titledSeparator);
				  		   			
				  		   			JLabel lblConsulterLesComptes = new JLabel("           Consulter les Comptes");
				  		   			lblConsulterLesComptes.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 35));
				  		   			lblConsulterLesComptes.setBackground(Color.WHITE);
				  		   			lblConsulterLesComptes.setBounds(215, 26, 791, 35);
				  		   			add(lblConsulterLesComptes);
				  		   			
				  		   			JXTitledSeparator titledSeparator_1 = new JXTitledSeparator();
				  		   			GridBagLayout gridBagLayout_1 = (GridBagLayout) titledSeparator_1.getLayout();
				  		   			gridBagLayout_1.rowWeights = new double[]{0.0};
				  		   			gridBagLayout_1.rowHeights = new int[]{0};
				  		   			gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
				  		   			gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
				  		   			titledSeparator_1.setTitle("Informations Detail Compte  :");
				  		   			titledSeparator_1.setBounds(10, 182, 1252, 30);
				  		   			add(titledSeparator_1);
				  		   			
				  		   			JLabel lblSolde = new JLabel("Solde  :");
				  		   			lblSolde.setFont(new Font("Tahoma", Font.BOLD, 11));
				  		   			lblSolde.setBounds(914, 541, 94, 24);
				  		   			add(lblSolde);
				  		   			
				  		   			txtsolde = new JTextField();
				  		   			txtsolde.setEditable(false);
				  		   			txtsolde.setColumns(10);
				  		   			txtsolde.setBounds(1018, 539, 162, 26);
				  		   			add(txtsolde);
				  		   			
				  		   			JLabel lblDontTvaEst = new JLabel("Dont TVA est  :");
				  		   			lblDontTvaEst.setFont(new Font("Tahoma", Font.BOLD, 11));
				  		   			lblDontTvaEst.setBounds(914, 577, 94, 24);
				  		   			add(lblDontTvaEst);
				  		   			
				  		   			txttva = new JTextField();
				  		   			txttva.setEditable(false);
				  		   			txttva.setColumns(10);
				  		   			txttva.setBounds(1018, 575, 162, 26);
				  		   			add(txttva);
				  		   			
				  		   			JButton button_1 = new JButton("Imprimer");
				  		   			button_1.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent arg0) {
				  		   					

				  		  				

				
				 if(listDetailCompte.size()!=0)
				 {
					
						
			  
			  		  Client fournisseur=mapFournisseur.get(combofournisseur.getSelectedItem());
			  		 
						
			  		    
						Map parameters = new HashMap();
						parameters.put("solde", txtsolde.getText());
						parameters.put("tva", txttva.getText());
						parameters.put("fournisseur", fournisseur.getNom());						
						
						
					if(client!="")
					{
						parameters.put("client", client);
					}else
					{
						parameters.put("client", comboclient.getSelectedItem());	
					}
						
					
						
						JasperUtils.imprimerEtatCompte(table.getModel(),parameters);
						
				 }else
				 {
					 JOptionPane.showMessageDialog(null, "Il n'existe auccun compte ", "Erreur", JOptionPane.ERROR_MESSAGE); 
				 }
					
					
			//	JOptionPane.showMessageDialog(null, "PDF exporté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
	  		  	
				
			
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   					
				  		   				}
				  		   			});
				  		   			button_1.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   			button_1.setBounds(503, 557, 112, 32);
				  		   		button_1.setIcon(imgImprimer);
				  		   			add(button_1);
				  		   		
				  		   			
	}
	
	
	

	
	
	void incrementercode()
	{
	}
	
	void afficher_tableDetailCompte(List<DetailCompteClient> listDetailCompte)
	{
		
		

		modeleDetailCompte =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Designation", "Montant Debit", "Montant Credit", "Compte", "Num Facture"
	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false,false,false,false
	  		     	};
	  		     	public boolean isCellEditable(int row, int column) {
	  		     		return columnEditables[column];
	  		     	}
	  		     };
	  		   table.setModel(modeleDetailCompte);
	  		  // table.getColumnModel().getColumn(2).setPreferredWidth(160);
	  		   //table.getColumnModel().getColumn(3).setPreferredWidth(60);
	        //q table.getColumnModel().getColumn(4).setPreferredWidth(60);
	        
		  int i=0;
		  String NumFacture="";
			while(i<listDetailCompte.size())
			{	
				
				
				if(listDetailCompte.get(i).getFacturepf()!=null)
				{
					NumFacture=listDetailCompte.get(i).getFacturepf().getNumFacture();
					
				}else if(listDetailCompte.get(i).getFactureAvoirClientpf()!=null)
				{
					NumFacture=listDetailCompte.get(i).getFactureAvoirClientpf().getNumFacture();
				}
				
				
					Object []ligne={listDetailCompte.get(i).getDesignation() ,listDetailCompte.get(i).getMontantDebit(),listDetailCompte.get(i).getMontantCredit(),listDetailCompte.get(i).getCompteClient().getCode(),NumFacture };

					modeleDetailCompte.addRow(ligne);
				
					
				
				i++;
			}
	}
}
