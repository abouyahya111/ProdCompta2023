package matierePremiere;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import main1.ProdLauncher;

import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import dao.daoImplManager.CategorieMpDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoManager.CategorieMpDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.entity.CategorieMp;
import dao.entity.MatierePremier;


public class ChercherMatierePremiere extends JLayeredPane {
	public JLayeredPane contentPane;	

	private ImageIcon imgModifier;
	private ImageIcon imgInit;
	private ImageIcon imgChercher;
	
	private JButton btnInitialiser;
	private JButton btnModifier;
	
	private  MatierePremiereDAO matierePremiereDAO;
	private CategorieMpDAO categorieMpDAO;
	
	
	private JTextField txtCode;
	private JTextField txtNomEmploye;
	private JTextField codeMatierePremiere;
	
	private Map< String, MatierePremier> mapMatierePremiere = new HashMap<>();
	private Map< String, String> mapCode= new HashMap<>();
	private Map< String, String> mapLibelle = new HashMap<>();
	
	private Map< String, CategorieMp> mapCategorieMp = new HashMap<>();
	
	
	private JComboBox comboMatierePremiere = new JComboBox();
	private JComboBox comboCategorie = new JComboBox();
	
	
	private List<CategorieMp> listCategorieMp = new ArrayList<CategorieMp>();
	private CategorieMp categorieMp=new CategorieMp();
	
	private List<MatierePremier> listMatierePremiere = new ArrayList<MatierePremier>();
	private MatierePremier matierePremier=new MatierePremier();
	

	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public ChercherMatierePremiere() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 565);
        try{
        	
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	categorieMpDAO=new CategorieMpDAOImpl();

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion √† la base de donn√©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
	
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgChercher= new ImageIcon(this.getClass().getResource("/img/chercher.png"));
            imgModifier= new ImageIcon(this.getClass().getResource("/img/modifier.png"));
          } catch (Exception exp){exp.printStackTrace();}
        
        comboMatierePremiere.addItem("");
        comboCategorie.addItem("");
        
        listMatierePremiere = matierePremiereDAO.findAll();	
	     	
	      int i=0;
	      	while(i<listMatierePremiere.size())
	      		{	
	      			MatierePremier matierePremiere=listMatierePremiere.get(i);
	      			mapMatierePremiere.put(matierePremiere.getNom(), matierePremiere);
	      			mapLibelle.put(matierePremiere.getNom(), matierePremiere.getCode());
		      		mapCode.put(matierePremiere.getCode(),matierePremiere.getNom());
	      			
	      			comboMatierePremiere.addItem(matierePremiere.getNom());
	      			i++;
	      		}
	      	
	      	 listCategorieMp = categorieMpDAO.findAll();	
		     	
		       i=0;
		      	while(i<listCategorieMp.size())
		      		{	
		      		CategorieMp categorieMp=listCategorieMp.get(i);
		      			mapCategorieMp.put(categorieMp.getNom(), categorieMp);
		      			
		      			comboCategorie.addItem(categorieMp.getNom());
		      			i++;
		      		}
		      	
	      	
	      
        
				  		  btnModifier = new JButton("Modifier ");
				  		  btnModifier.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  		
				  		  		if(matierePremier.getId()<1){
				  		  			
				  		  		JOptionPane.showMessageDialog(null, "Il faut chercher la matiËre premiËre ‡ modifier!", "Attention", JOptionPane.INFORMATION_MESSAGE);
				  		  		}else {
				  		  		
				  		  			matierePremier.setNom(txtNomEmploye.getText());
				  		  			matierePremier.setCategorieMp(mapCategorieMp.get(comboCategorie.getSelectedItem()));
				  		  			matierePremiereDAO.edit(matierePremier);
				  		  		
				  		  		JOptionPane.showMessageDialog(null, "la matiËre premiËre a ÈtÈ modifiÈe avec succËs!", "SuccËs", JOptionPane.INFORMATION_MESSAGE);
				  		  		
				  		  		}
				  		  	}
				  		  });
				  		btnModifier.setIcon(imgModifier);
				  		 btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnModifier.setBounds(269, 287, 126, 26);
				  		 add(btnModifier);
				  		 
				  		  btnInitialiser = new JButton("Initialiser");
				  		  btnInitialiser.addActionListener(new ActionListener() {
				  		  	public void actionPerformed(ActionEvent e) {
				  		  	intialiser ();
				  		  	}
				  		  });
				  		btnInitialiser.setIcon(imgInit);
				  		 btnInitialiser.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		 btnInitialiser.setBounds(418, 287, 112, 26);
				  		 add(btnInitialiser);
				  		 
				  		 JLayeredPane layeredPane = new JLayeredPane();
				  		   
				  		   JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		   titledSeparator.setBounds(9, 92, 902, 24);
				  		   add(titledSeparator);
				  		   titledSeparator.setTitle("Informations  Mati\u00E8re Premi\u00E8re");
				  		   
				  		  
				  		   layeredPane.setBorder(new MatteBorder(0, 1, 1, 1, (Color) Color.LIGHT_GRAY));
				  		   layeredPane.setBounds(8, 105, 903, 171);
				  		   add(layeredPane);
				  		   
				  		   JLabel lblNom = new JLabel("Nom :");
				  		   lblNom.setBounds(29, 70, 130, 26);
				  		   layeredPane.add(lblNom);
				  		   lblNom.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   
				  		   txtNomEmploye = new JTextField();
				  		 util.Utils.copycoller(txtNomEmploye);
				  		   txtNomEmploye.setBounds(122, 71, 191, 26);
				  		   layeredPane.add(txtNomEmploye);
				  		   txtNomEmploye.setColumns(10);
				  		   txtCode = new JTextField();
				  		 util.Utils.copycoller(txtCode);
				  		   txtCode.setBackground(Color.WHITE);
				  		   txtCode.setDisabledTextColor(Color.BLACK);
				  		   txtCode.setEditable(false);
				  		   txtCode.setBounds(122, 34, 191, 26);
				  		   layeredPane.add(txtCode);
				  		   txtCode.setColumns(10);
				  		   
				  		   JLabel lblCodeLigne = new JLabel("Code :");
				  		   lblCodeLigne.setBounds(29, 33, 114, 26);
				  		   layeredPane.add(lblCodeLigne);
				  		   lblCodeLigne.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		  
				  		   			JLabel lblCatgorie = new JLabel("Cat\u00E9gorie :");
				  		   			lblCatgorie.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			lblCatgorie.setBounds(29, 108, 114, 26);
				  		   			layeredPane.add(lblCatgorie);
				  		   			
				  		   			 
				  		   			comboCategorie.setBounds(122, 108, 191, 26);
				  		   			layeredPane.add(comboCategorie);
				  		   			
				  		   			JLayeredPane layeredPane_1 = new JLayeredPane();
				  		   			layeredPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		   			layeredPane_1.setBounds(10, 11, 901, 70);
				  		   			add(layeredPane_1);
				  		   			
				  		   			JButton btnChercherEmploye = new JButton("Chercher");
				  		   			btnChercherEmploye.setBounds(775, 15, 116, 26);
				  		   			layeredPane_1.add(btnChercherEmploye);
				  		   			btnChercherEmploye.setIcon(imgChercher);
				  		   			
				  		   		
					  		      comboMatierePremiere.addItemListener(new ItemListener() {
					  		     	 	public void itemStateChanged(ItemEvent e) {
					  		     	 	
					  		     	 	 if(e.getStateChange() == ItemEvent.SELECTED) {
					  		     	
						  		     	   	codeMatierePremiere.setText(mapLibelle.get(comboMatierePremiere.getSelectedItem()));
					  	                   
					  		     	 	 	}
					  		     	 	}
					  		     	 });
							
							
						        	AutoCompleteDecorator.decorate(comboMatierePremiere);
				  		   			comboMatierePremiere.setBounds(471, 15, 263, 26);
				  		   			layeredPane_1.add(comboMatierePremiere);
				  		   			
				  		   			codeMatierePremiere = new JTextField();
				  		   		 util.Utils.copycoller(codeMatierePremiere);
				  		   			codeMatierePremiere.setBounds(124, 15, 191, 26);
				  		   			layeredPane_1.add(codeMatierePremiere);
				  		   			codeMatierePremiere.setColumns(10);
				  		   			
				  		   			JLabel lblLibelle = new JLabel("Nom :");
				  		   			lblLibelle.setBounds(419, 14, 130, 26);
				  		   			layeredPane_1.add(lblLibelle);
				  		   			lblLibelle.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			
				  		   			JLabel lblCode = new JLabel("Code :");
				  		   			lblCode.setBounds(60, 14, 114, 26);
				  		   			layeredPane_1.add(lblCode);
				  		   			lblCode.setFont(new Font("Tahoma", Font.PLAIN, 12));
				  		   			
					  		    codeMatierePremiere.addKeyListener(new KeyAdapter() {
								  	@Override
								  	public void keyReleased(KeyEvent e)
								  	{
								  		if (e.getKeyCode() == e.VK_ENTER)
								  		{
								  			
								  			comboMatierePremiere.setSelectedItem(mapCode.get(codeMatierePremiere.getText().toUpperCase()));
								  		}}});
				  		   			btnChercherEmploye.addActionListener(new ActionListener() {
				  		   				public void actionPerformed(ActionEvent e) {
				  		   					
				  		   				if(codeMatierePremiere.getText().equals("") && comboMatierePremiere.getSelectedItem().equals(""))
		  		     			JOptionPane.showMessageDialog(null, "Il faut remplir au critËre de recherche!", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		     		
		  		     		 else {
		  		     			matierePremier = matierePremiereDAO.findByCode(codeMatierePremiere.getText());
		  		     			  
		  		     			  if(codeMatierePremiere!=null){
		  		     			 
		  		     			txtCode.setText(matierePremier.getCode());
		  		     			txtNomEmploye.setText(matierePremier.getNom());
		  		     			comboCategorie.setSelectedItem(matierePremier.getCategorieMp().getNom());
		  		     			  }else {
		  		     				JOptionPane.showMessageDialog(null, "Il n'existe aucun rÈsultat pour ces critËres de recherche. Merci de vÈrifier votre critËre !", "Attention", JOptionPane.INFORMATION_MESSAGE);
		  		     			  }
		  		     		 }
				  		   					
				  		   				}
				  		   			});
				  		
				  		 
	}
	
	
	

	
void intialiser (){
		
		txtCode.setText("");
		txtNomEmploye.setText("");
		comboCategorie.setSelectedItem("");
		
	}
}
