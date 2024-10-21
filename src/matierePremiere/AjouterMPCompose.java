package matierePremiere;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;

import main1.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import dao.daoImplManager.ArticlesMPDAOImpl;
import dao.daoImplManager.MatierePremierDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ArticlesMPDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.entity.Articles;
import dao.entity.ArticlesMP;
import dao.entity.DetailEstimation;
import dao.entity.DetailEstimationMP;
import dao.entity.MatierePremier;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class AjouterMPCompose extends JLayeredPane {
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;

	private ImageIcon imgInit;
	private ImageIcon imgAjouter;
	private JButton btnIntialiserOF;

	
	private Map< String, String> mapQuantiteMP = new HashMap<>();
	private Map< String, String> mapPrixMP = new HashMap<>();
	private Map< Integer, MatierePremier> mapMatierePremier = new HashMap<>();
	private Map< String, MatierePremier> mapMatierePremierTmp = new HashMap<>();
	

	
	private JLabel lblDpotDestination;
	

	private MatierePremiereDAO matierePremiereDAO;
	private ArticlesMPDAO articlesMPDAO;
	ArticlesMP articlesMP =new ArticlesMP ();
	private JTextField txtCode;
	private JTextField txtLibelle;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AjouterMPCompose() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 565);
        try{
        	
        	matierePremiereDAO=new MatierePremierDAOImpl();
        	articlesMPDAO=new ArticlesMPDAOImpl();
       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion Ã  la base de donnÃ©es", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
		 	
	
        try{
            imgInit= new ImageIcon(this.getClass().getResource("/img/init.png"));
            imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
          } catch (Exception exp){exp.printStackTrace();}
				  		     btnIntialiserOF = new JButton("Intialiser");
				  		     btnIntialiserOF.setBounds(421, 515, 112, 23);
				  		     add(btnIntialiserOF);
				  		     btnIntialiserOF.addActionListener(new ActionListener() {
				  		     	public void actionPerformed(ActionEvent e) {
				  		     	intialiser();
				  		     		
				  		     	}
				  		     });
				  		     btnIntialiserOF.setIcon(imgInit);
				  		     btnIntialiserOF.setFont(new Font("Tahoma", Font.PLAIN, 11));
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
				  		   DefaultCellEditor ce = (DefaultCellEditor) table.getDefaultEditor(Object.class);
				  	        JTextComponent textField = (JTextComponent) ce.getComponent();
				  	        util.Utils.copycollercell(textField);
				  		     	JScrollPane scrollPane = new JScrollPane(table);
				  		     	scrollPane.setBounds(9, 167, 782, 337);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(9, 135, 782, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(9, 13, 781, 111);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblarticle = new JLabel("Code Article");
				  		     	lblarticle.setBounds(10, 35, 144, 24);
				  		     	layeredPane.add(lblarticle);
				  		     	lblarticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
				  		  
				  		  lblDpotDestination = new JLabel("Libelle Article");
				  		  lblDpotDestination.setBounds(339, 34, 108, 26);
				  		  layeredPane.add(lblDpotDestination);
				  		  
				  		  txtCode = new JTextField();
				  		 util.Utils.copycoller(txtCode);
				  		  txtCode.addKeyListener(new KeyAdapter() {
				  		  	@Override
				  		  	public void keyPressed(KeyEvent arg0) {
				  		  		
				  		  		if(!txtCode.getText().equals(""))
				  		  		{
				  		  			
				  		  			
				  		  		}
				  		  		
				  		  		
				  		  		
				  		  		
				  		  		
				  		  		
				  		  		
				  		  		
				  		  		
				  		  		
				  		  		
				  		  		
				  		  	}
				  		  });
				  		  txtCode.setBounds(98, 35, 144, 24);
				  		  layeredPane.add(txtCode);
				  		  txtCode.setColumns(10);
				  		  
				  		  txtLibelle = new JTextField();
				  		 util.Utils.copycoller(txtLibelle);
				  		  txtLibelle.setColumns(10);
				  		  txtLibelle.setBounds(436, 35, 222, 24);
				  		  layeredPane.add(txtLibelle);
		
		JButton btnValiderTransfer = new JButton("Ajouter Article");
		btnValiderTransfer.setIcon(imgAjouter);
		btnValiderTransfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(!remplirMapQuantiteEstimation())	{
				JOptionPane.showMessageDialog(null, "Il faut remplir la quantité", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else {
				if(txtCode.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Il faut saisir le code", "Erreur", JOptionPane.ERROR_MESSAGE);
					
				}else if(txtLibelle.getText().equals("")){
					JOptionPane.showMessageDialog(null, "Il faut saisir le libelle", "Erreur", JOptionPane.ERROR_MESSAGE);
					
				
					
				} else {
					
					articlesMP =new ArticlesMP();
					articlesMP.setCodeArticle(txtCode.getText());
					articlesMP.setLiblle(txtLibelle.getText());
					articlesMP.setDetailEstimationMP(remplirDetailEstimationMP());
					articlesMPDAO.add(articlesMP);
					
					JOptionPane.showMessageDialog(null, "Article ajouté avec succès", "Succès", JOptionPane.INFORMATION_MESSAGE);
					
				}
				
				
				
				
			}
		  }
		});
		btnValiderTransfer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnValiderTransfer.setBounds(253, 515, 158, 23);
		add(btnValiderTransfer);
		List<MatierePremier> listMatierePremier=matierePremiereDAO.findAll();
		afficher_tableMP(listMatierePremier);
				  		     
				  		 
	}
	
	
	void intialiser()
	{
		txtCode.setText("");
		txtLibelle.setText("");
		txtCode.requestFocus();
		
	}
	
	void afficher_tableMP(List<MatierePremier> listMatierePremier)
	{

		

		modeleMP =new DefaultTableModel(
	  		     	new Object[][] {
	  		     	},
	  		     	new String[] {
	  		     			"Code","Nom MP", "Quantité Estimé"
	  		     	}
	  		     ) {
	  		     	boolean[] columnEditables = new boolean[] {
	  		     			false,false,true
	  		     	};
	  		     	public boolean isCellEditable(int row, int column) {
	  		     		return columnEditables[column];
	  		     	}
	  		     };
	  		 table.setModel(modeleMP); 
	  		 table.getColumnModel().getColumn(0).setPreferredWidth(10);
	         table.getColumnModel().getColumn(1).setPreferredWidth(260);
	         table.getColumnModel().getColumn(2).setPreferredWidth(160);
	        //table.getColumnModel().getColumn(3).setPreferredWidth(160);
	        //table.getColumnModel().getColumn(4).setPreferredWidth(60);
	        
		  int i=0;
			while(i<listMatierePremier.size())
			{	
				
				MatierePremier matierePremier=listMatierePremier.get(i);
				mapMatierePremierTmp.put(matierePremier.getCode(), matierePremier);
				Object []ligne={matierePremier.getCode(),matierePremier.getNom(),""};

				modeleMP.addRow(ligne);
				i++;
			}
	}
	


boolean remplirMapQuantiteEstimation(){
	boolean trouve=false;
	int i=0;
			
	for(int j=0;j<table.getRowCount();j++){
		
		if(!table.getValueAt(j, 2).toString().equals("") ){
			mapQuantiteMP.put(table.getValueAt(j, 0).toString(), table.getValueAt(j, 2).toString());
			mapMatierePremier.put(i, mapMatierePremierTmp.get(table.getValueAt(j, 0).toString()));
			i++;
			trouve=true;
		}	
	}
	return trouve;
}

List<DetailEstimationMP> remplirDetailEstimationMP(){
	BigDecimal quantite=BigDecimal.ZERO;

	List<DetailEstimationMP> listDetailEstimationMP= new ArrayList<DetailEstimationMP>();
	
	for(int i=0;i<mapMatierePremier.size();i++){
		
		DetailEstimationMP detailEstimationMP=new DetailEstimationMP();
	
		MatierePremier matierePremier =mapMatierePremier.get(i);
		quantite=new BigDecimal(mapQuantiteMP.get(matierePremier.getCode()));
		
//		if(matierePremier.getCategorieMp().getSubCategorieMp().getCode().equals(Constantes.SOUS_CATEGORIE_MATIERE_PREMIERE_THE))
//			detailEstimationMP.setPriorite(1);
//		else 
		detailEstimationMP.setPriorite(1);
		
		detailEstimationMP.setQuantite(quantite);
		detailEstimationMP.setMatierePremier(matierePremier);
		detailEstimationMP.setArticlesMP(articlesMP);
		
		listDetailEstimationMP.add(detailEstimationMP);
	}
	
	
	return listDetailEstimationMP;
	
}
}
