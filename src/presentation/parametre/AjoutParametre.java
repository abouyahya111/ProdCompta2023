package presentation.parametre;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
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
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import main1.AuthentificationView;
import main1.ProdLauncher;

import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.Utils;
import dao.daoImplManager.EmployeBloqueDAOImpl;
import dao.daoImplManager.EmployeDAOImpl;
import dao.daoImplManager.EquipeDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoManager.EmployeDAO;
import dao.daoManager.EquipeDAO;
import dao.daoManager.ParametreDAO;
import dao.entity.Employe;
import dao.entity.Equipe;
import dao.entity.Parametre;


public class AjoutParametre extends JLayeredPane implements Constantes {
	public JLayeredPane contentPane;	
	
	private DefaultTableModel modele;
	private JXTable table;
	
	private ImageIcon imgModifier;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	
	private JButton btnSupprimer;
	private JButton btnModifier;
	private JButton btnInitialiser;
	private JButton btnAjouter;
	private JButton btnRechercher;
	private JTextField txtCode;
	private JTextField txtLibelle;
	private int valeur;
	
	private List <Parametre> listeParametres = new ArrayList<Parametre>();
	private Map< Integer, Parametre> mapParametre = new HashMap<>();

	private JLayeredPane layeredPane_1;
	private JTextField  txtCodeModif = new JTextField();
	
	private JTextField txtLibelleModif= new JTextField();
	private JLabel lblCatModif;
	private JLabel lblNomModif;
	private JLabel lblCodeModif;
	private JButton btnValiderModif;
	private JButton initialiserModif;
	
	private JTextField txtValeur;
	private JTextField  txtValeurModif = new JTextField();
	
	private ParametreDAO parametreDAO;
	private EquipeDAO equipeDAO;
	private EmployeDAO employeDAO;
	private Parametre parametre=new Parametre();
	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjoutParametre() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 565);
        try{
        	
        	parametreDAO=new ParametreDAOImpl();
        	equipeDAO=new EquipeDAOImpl();
        	employeDAO=new EmployeDAOImpl();
        	util.Utils.copycoller(txtValeurModif);
        	util.Utils.copycoller(txtCodeModif);
        	util.Utils.copycoller(txtLibelleModif);
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
		 	
	
        try{
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
             imgSupprimer = new ImageIcon(this.getClass().getResource("/img/supp.png"));
          } catch (Exception exp){exp.printStackTrace();}
	
		table = new JXTable();
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
		
 
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		scrollPane.setBounds(0, 235, 782, 227);
		
		this.add(scrollPane);
				  		 
				  		 JXLabel lblListDes = new JXLabel();
				  		 lblListDes.setForeground(new Color(255, 69, 0));
				  		 lblListDes.setBorder(new MatteBorder(0, 0, 1, 0, (Color) Color.WHITE));
				  		 lblListDes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
				  		 lblListDes.setText("List des Param\u00E8tres");
				  		 lblListDes.setBounds(0, 207, 782, 24);
				  		 add(lblListDes);
				  		layeredPane_1 = new JLayeredPane();
				  		  btnModifier = new JButton("  Modifier");
				  		  btnModifier.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  
								int row=0;
								   if(table.getSelectedRow()==-1)
									     JOptionPane.showMessageDialog(null, "Il faut s�lectionner un ligne!", "Attention", JOptionPane.INFORMATION_MESSAGE);
								   else
								   {  
									    
									   layeredPane_1.setVisible(true);
									   row = table.getSelectedRow();
									   parametre=mapParametre.get(row);
									   
									   txtCodeModif.setText(parametre.getCode());
									   txtLibelleModif.setText(parametre.getLibelle());
									   txtValeurModif.setText(parametre.getValeur()+"");
				                 
								   }
							
				  		  	}
				  		  });
				  		btnModifier.setIcon(imgModifier);
				  		 btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnModifier.setBounds(792, 272, 112, 24);
				  		 add(btnModifier);
				  		 
				  		  btnSupprimer = new JButton("D\u00E9sactiver");
				  		  btnSupprimer.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {

								
								 try{/*
									   int row=0;
									   if(table.getSelectedRow()==-1)
										     JOptionPane.showMessageDialog(null, "Il faut s�lectionner une Mati�re Premi�re!", "Attention", JOptionPane.INFORMATION_MESSAGE);
									   else
									   {
										   
										   int reponse = JOptionPane.showConfirmDialog(null, "Vous voulez vraiment d�sactiver cette Mati�re Premi�re?", 
												"Satisfaction", JOptionPane.YES_NO_OPTION);
										 
										if(reponse == JOptionPane.YES_OPTION ){
										   
										row = table.getSelectedRow();
									  
									   //stx=con.createStatement();
										int  id=Integer.parseInt(table.getValueAt(row, 0).toString());
									     MatierePremier mp = dao.findById(id);
									     mp.setDeleted(true);
									   	 dao.edit(mp);
									     afficher_table();  
				                        table.setRowSorter(null);
									     modele.removeRow(row);
											}
									   }
						                */}catch (Exception e1){
						                	}
										
							
				  		  	}
				  		  });
				  		btnSupprimer.setIcon(imgSupprimer);
				  		 btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnSupprimer.setBounds(792, 306, 112, 23);
				  		 add(btnSupprimer);
				  		 
				  		 JLayeredPane layeredPane = new JLayeredPane();
				  		 layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		 layeredPane.setBounds(10, 11, 630, 192);
				  		 add(layeredPane);
				  		 
				  		 txtCode = new JTextField();
				  		 util.Utils.copycoller(txtCode);
				  		 txtCode.setForeground(Color.BLUE);
				  		 txtCode.setBackground(Color.WHITE);
				  		 txtCode.setEditable(false);
				  		 txtCode.setColumns(10);
				  		 txtCode.setBounds(165, 13, 191, 26);
				  		 layeredPane.add(txtCode);
				  		 
				  		 txtLibelle = new JTextField();
				  		 util.Utils.copycoller(txtLibelle);
				  		 txtLibelle.setColumns(10);
				  		 txtLibelle.setBounds(165, 44, 191, 26);
				  		 layeredPane.add(txtLibelle);
				  		 
				  		 JLabel lblValeur = new JLabel("Valeur:");
				  		 lblValeur.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		 lblValeur.setBounds(54, 73, 102, 26);
				  		 layeredPane.add(lblValeur);
				  		 
				  		 JLabel lblLibelle = new JLabel("Libelle :");
				  		 lblLibelle.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		 lblLibelle.setBounds(54, 42, 130, 26);
				  		 layeredPane.add(lblLibelle);
				  		 
				  		 JLabel lblCode = new JLabel("Code :");
				  		 lblCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		 lblCode.setBounds(54, 13, 114, 26);
				  		 layeredPane.add(lblCode);
				  		 
				  		  
				  		  btnAjouter = new JButton("Ajouter");
				  		  btnAjouter.setBounds(120, 142, 107, 24);
				  		  layeredPane.add(btnAjouter);
				  		  btnAjouter.setIcon(imgAjouter);
				  		  btnAjouter.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {
				  		   
				  		   		if(txtLibelle.getText().equals(""))
				  		   			JOptionPane.showMessageDialog(null, "Il faut saisir le libelle!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		   		else if (txtValeur.equals(""))
				  		   			JOptionPane.showMessageDialog(null, "Il faut saisir la Valeur!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		   		else {
				  		   		
				  		   		Parametre parametre = new Parametre();
				  		   		parametre.setCode(txtCode.getText());
				  		   		parametre.setLibelle(txtLibelle.getText());
				  		   		parametre.setValeur(new BigDecimal(txtValeur.getText()));
				  		   		
				  		   		parametreDAO.add(parametre);
				  		   		Utils.incrementerValeurSequenceur(Constantes.PARAMETRE_LIBELLE);
				  		   		listeParametres.add(parametre);
				  		   		intialiser();
				  		   		String code =Utils.genererCode(Constantes.PARAMETRE_LIBELLE);
				  		   		txtCode.setText(code);
								afficher_table(listeParametres);
				  		   		}
				  		   	}
				  		   });
				  		  btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  
				  		   btnInitialiser = new JButton("Initialiser");
				  		   btnInitialiser.setBounds(270, 143, 102, 23);
				  		   layeredPane.add(btnInitialiser);
				  		   btnInitialiser.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {
				  		   	intialiser();
				  		   		
				  		   	}
				  		   });
				  		   btnInitialiser.setIcon(imgInit);
				  		   btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   
				  		   txtValeur = new JTextField();
				  		 util.Utils.copycoller(txtValeur);
				  		   txtValeur.setColumns(10);
				  		   txtValeur.setBounds(165, 73, 191, 26);
				  		   layeredPane.add(txtValeur);
				  		   
				  		   
				  		   layeredPane_1.setBackground(new Color(135, 206, 235));
				  		   layeredPane_1.setVisible(false);
				  		   layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(135, 206, 250), new Color(135, 206, 250)));
				  		   layeredPane_1.setBounds(654, 11, 495, 192);
				  		   add(layeredPane_1);
				  		   
				  		  
				  		   txtCodeModif.setColumns(10);
				  		   txtCodeModif.setBounds(165, 13, 191, 26);
				  		   layeredPane_1.add(txtCodeModif);
				  		   
				  		   txtLibelleModif.setColumns(10);
				  		   txtLibelleModif.setBounds(165, 44, 191, 26);
				  		   layeredPane_1.add(txtLibelleModif);
				  		   
				  		   lblCatModif = new JLabel("Valeur :");
				  		   lblCatModif.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   lblCatModif.setBounds(54, 73, 102, 26);
				  		   layeredPane_1.add(lblCatModif);
				  		   
				  		   lblNomModif = new JLabel("Libelle:");
				  		   lblNomModif.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   lblNomModif.setBounds(54, 42, 130, 26);
				  		   layeredPane_1.add(lblNomModif);
				  		   
				  		   lblCodeModif = new JLabel("Code:");
				  		   lblCodeModif.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   lblCodeModif.setBounds(54, 13, 114, 26);
				  		   layeredPane_1.add(lblCodeModif);
				  		   
				  		   btnValiderModif = new JButton("Valider Modification");
				  		   btnValiderModif.addActionListener(new ActionListener() {
				  		   	public void actionPerformed(ActionEvent e) {
				  		   		
				  		   	
				  		   	parametre.setLibelle(txtLibelleModif.getText());
				  		   	parametre.setValeur(new BigDecimal(txtValeurModif.getText()));
				  		   	parametreDAO.edit(parametre);
				  		  
				  		   	MAJRemiseParametre(parametre);
				  		   	
				  		   	
						   	JOptionPane.showMessageDialog(null, "les param�tre ont �t� modifi�e avec succ�s!", "Succ�s", JOptionPane.INFORMATION_MESSAGE);
						   	layeredPane_1.setVisible(false);
						   	listeParametres = new ArrayList<Parametre>();
						   listeParametres=parametreDAO.findAll();
						   	afficher_table(listeParametres); 
						     
				  		   		
				  		   	}
				  		   });
				  		   btnValiderModif.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   btnValiderModif.setBounds(141, 142, 130, 24);
				  		   layeredPane_1.add(btnValiderModif);
				  		   
				  		   initialiserModif = new JButton("Initialiser");
				  		  
				  		   initialiserModif.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		   initialiserModif.setBounds(292, 143, 102, 23);
				  		   layeredPane_1.add(initialiserModif);
				  		   
				  		   	
				  		   	String code =Utils.genererCode(Constantes.PARAMETRE_LIBELLE);
				  		  txtCode.setText(code);
				  		  txtValeurModif.setText(valeur+"");
				  		   txtValeurModif.setColumns(10);
				  		   txtValeurModif.setBounds(165, 73, 191, 26);
				  		   layeredPane_1.add(txtValeurModif);
				  		 listeParametres = new ArrayList<Parametre>();
				  		 
				  		
				  		 listeParametres=parametreDAO.findAll();
				  		afficher_table(listeParametres);
	}
	
	void afficher_table(List <Parametre> listeParametres)
	{
		modele =new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"id","Code Param�tre","Libelle Param�tre", "Valeur"
				}
			) {
				boolean[] columnEditables = new boolean[] {
						false,false,false, false
				};
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			};
		table.setModel(modele);
		  int i=0;
			while(i<listeParametres.size())
			{	
				Parametre parametre=listeParametres.get(i);
				Object []ligne={parametre.getId(),parametre.getCode(),parametre.getLibelle(),parametre.getValeur()};
				mapParametre.put(i, parametre);

				modele.addRow(ligne);
				i++;
			}
}
	void intialiser()
	{
		txtLibelle.setText("");
		txtValeur.setText("");
		
	}
	
	void MAJRemiseParametre(Parametre parametre){
		
		if(parametre.getCode().equals(PARAMETRE_CODE_REMISE_EQUIPE_PRODUCTION)){
			List<Equipe> listeEquipe =equipeDAO.findListeEquipeByType(TYPE_EQUIPE_CODE_PRPDUCTION,AuthentificationView.utilisateur.getCodeDepot()) ;
			 for(int i=0;i<listeEquipe.size();i++){
				 Equipe equipe =listeEquipe.get(i);
				 equipe.setRemise(parametre.getValeur());
				 equipeDAO.edit(equipe);
			 }
		}

		if(parametre.getCode().equals(PARAMETRE_CODE_REMISE_EQUIPE_EMBALAGE)){
			List<Equipe> listeEquipe =equipeDAO.findListeEquipeByType(TYPE_EQUIPE_CODE_GENERIQUE,AuthentificationView.utilisateur.getCodeDepot()) ;
			 for(int i=0;i<listeEquipe.size();i++){
				 Equipe equipe =listeEquipe.get(i);
				 equipe.setRemise(parametre.getValeur());
				 equipeDAO.edit(equipe);
			 }
		}
	 	if(parametre.getCode().equals(PARAMETRE_CODE_COUT_HORAIRE_CNSS)){
	 		List<Employe> listeEmploye = employeDAO.findAll();
		   		BigDecimal coutHoriareCNSS=parametre.getValeur();
		   		Parametre parametre0 =parametreDAO.findByCode(PARAMETRE_CODE_COUT_HORAIRE);
		   		Parametre parametre1 =parametreDAO.findByCode(PARAMETRE_CODE_TAUX_CNSS_226);
		   		Parametre parametre2 =parametreDAO.findByCode(PARAMETRE_CODE_TAUX_CNSS_448);
		   		BigDecimal taux226=parametre1.getValeur().divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP)    ;
		   		BigDecimal taux448=parametre2.getValeur().divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP) ;
		   		BigDecimal cout226=coutHoriareCNSS.multiply(taux226)    ;
		   		BigDecimal cout448=coutHoriareCNSS.multiply(taux448)   ;
		   		BigDecimal coutHoraire =coutHoriareCNSS.subtract(cout226.add(cout448));
		   		parametre0.setValeur(coutHoraire);
		   		parametreDAO.edit(parametre0);
		   		
		   		for(int i=0;i<listeEmploye.size();i++){
		   			Employe employe =listeEmploye.get(i);
		   			if(employe.isSalarie()){
		   				employe.setCoutHoraire(BigDecimal.ZERO);
		   			}else
		   			employe.setCoutHoraire(coutHoraire);
		   			employeDAO.edit(employe);
				 }
		   		
		   	}
		
		
	}
	

	
}