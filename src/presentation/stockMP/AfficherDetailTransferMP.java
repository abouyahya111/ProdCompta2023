package presentation.stockMP;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
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
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import dao.daoImplManager.TransferStockMPDAOImpl;
import dao.daoManager.DepotDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.TransferStockMPDAO;
import dao.entity.Depot;
import dao.entity.DetailTransferStockMP;
import dao.entity.Magasin;
import dao.entity.StockMP;
import dao.entity.TransferStockMP;
import dao.entity.Utilisateur;


public class AfficherDetailTransferMP extends JLayeredPane implements Constantes{
	public JLayeredPane contentPane;	

	private DefaultTableModel	 modeleMP;

	private JXTable table;
	private ImageIcon imgModifier;
	private ImageIcon imgSupprimer;
	private ImageIcon imgAjouter;
	private ImageIcon imgInit;
	private JButton btnRechercher;
	
	private TransferStockMPDAO transferStockMPDAO;
	private Utilisateur utilisateur;
	private JTextField txtCodeTransfer;
	private JTextField txtDateTransfer;
	private JTextField txtStatut;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public AfficherDetailTransferMP() {
		setOpaque(true);
		setBackground(new Color(248, 248, 255));
		setForeground(new Color(248, 248, 255));

		final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(0, 0, 1284, 565);
        try{
        	
        	
        	transferStockMPDAO=new TransferStockMPDAOImpl();
        	utilisateur= AuthentificationView.utilisateur;

       }catch(Exception exp){exp.printStackTrace();		
       JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données", "Erreur", JOptionPane.ERROR_MESSAGE);
}
		
        //comboDepot.addItem("");
        txtCodeTransfer = new JTextField();
        util.Utils.copycoller(txtCodeTransfer);
        txtDateTransfer = new JTextField();
        util.Utils.copycoller(txtDateTransfer);
        txtStatut = new JTextField();
        util.Utils.copycoller(txtStatut);
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
				  		     	scrollPane.setBounds(9, 130, 782, 396);
				  		     	add(scrollPane);
				  		     	scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	
				  		     	
				  		     	JXTitledSeparator titledSeparator = new JXTitledSeparator();
				  		     	titledSeparator.setTitle("Liste Mati\u00E8res Premi\u00E8res ");
				  		     	titledSeparator.setBounds(9, 101, 782, 30);
				  		     	add(titledSeparator);
				  		     	
				  		     	JLayeredPane layeredPane = new JLayeredPane();
				  		     	layeredPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
				  		     	layeredPane.setBounds(10, 0, 781, 90);
				  		     	add(layeredPane);
				  		     	
				  		     	JLabel lblMachine = new JLabel("Code Transfer :");
				  		     	lblMachine.setBounds(10, 11, 96, 24);
				  		     	layeredPane.add(lblMachine);
				  		     	lblMachine.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JButton btnAfficherStock = new JButton("Afficher Stock");
		btnAfficherStock.setBounds(639, 12, 113, 23);
		layeredPane.add(btnAfficherStock);
		btnAfficherStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			if(txtCodeTransfer.getText().equals(""))	{
				JOptionPane.showMessageDialog(null, "Il Saisir le code Transfer", "Erreur", JOptionPane.ERROR_MESSAGE);
			} else {
				DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	  		  
				TransferStockMP transferStockMP=transferStockMPDAO.findTransferByCode(txtCodeTransfer.getText());
				String dateTransfer=dateFormat.format(transferStockMP.getDateTransfer());
				txtDateTransfer.setText(dateTransfer);
				txtStatut.setText(transferStockMP.getStatut());
				List<DetailTransferStockMP> listDetailTransferStockMP =transferStockMP.getListDetailTransferMP(); 
				
				afficher_tableMP(listDetailTransferStockMP);
			}
		  }
		});
		btnAfficherStock.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		
		txtCodeTransfer.setBounds(90, 13, 207, 24);
		layeredPane.add(txtCodeTransfer);
		txtCodeTransfer.setColumns(10);
		
		JLabel lblDateTransfer = new JLabel("Date Transfer :");
		lblDateTransfer.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblDateTransfer.setBounds(10, 53, 96, 24);
		layeredPane.add(lblDateTransfer);
		
		
		txtDateTransfer.setBackground(Color.WHITE);
		txtDateTransfer.setEditable(false);
		txtDateTransfer.setColumns(10);
		txtDateTransfer.setBounds(90, 55, 207, 24);
		layeredPane.add(txtDateTransfer);
		
		JLabel lblSatutTransfert = new JLabel("Type Transfert:");
		lblSatutTransfert.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblSatutTransfert.setBounds(307, 54, 96, 24);
		layeredPane.add(lblSatutTransfert);
		
		
		txtStatut.setEditable(false);
		txtStatut.setColumns(10);
		txtStatut.setBackground(Color.WHITE);
		txtStatut.setBounds(395, 55, 207, 24);
		layeredPane.add(txtStatut);
	
				  		     
				  		 
	}
	
void afficher_tableMP(List<DetailTransferStockMP> listDetailTransferStockMP)
	{
	intialiserTableau();
		  int i=0;
			while(i<listDetailTransferStockMP.size())
			{	
				
				DetailTransferStockMP detailTransferStockMP=listDetailTransferStockMP.get(i);
				
				
				Object []ligne={detailTransferStockMP.getMatierePremier().getCode(),detailTransferStockMP.getMatierePremier().getNom(),detailTransferStockMP.getQuantite()};

				modeleMP.addRow(ligne);
				i++;
			}
	}
	
void intialiserTableau(){
	
	 modeleMP =new DefaultTableModel(
		     	new Object[][] {
		     	},
		     	new String[] {
		     			"Code Mati�re Premi�re","Mati�re Premi�re", "Quantit�"
		     	}
		     ) {
		     	boolean[] columnEditables = new boolean[] {
		     			false,false,false
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
