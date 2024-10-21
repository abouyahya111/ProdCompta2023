package FactureAchat;

import groovy.lang.Sequence;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import main1.AuthentificationView;
import main1.ProdLauncher;

import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.JXTitledSeparator;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import org.jdesktop.swingx.decorator.HighlighterFactory;

import util.Constantes;
import util.JasperUtils;
import util.Utils;
import dao.daoImplManager.ArticlesDAOImpl;
import dao.daoImplManager.ChargeProductionDAOImpl;
import dao.daoImplManager.ChargesDAOImpl;
import dao.daoImplManager.DepotDAOImpl;
import dao.daoImplManager.DetailCompteClientDAOImpl;
import dao.daoImplManager.DetailFactureAvoirPFDAOImpl;
import dao.daoImplManager.DetailTransferProduitFiniDAOImpl;
import dao.daoImplManager.FactureAvoirPFDAOImpl;
import dao.daoImplManager.FamilleArticlesPFDAOImpl;
import dao.daoImplManager.FournisseurDAOImpl;
import dao.daoImplManager.ListTvaDAOImpl;
import dao.daoImplManager.ParametreDAOImpl;
import dao.daoImplManager.SousFamilleArticlesPFDAOImpl;
import dao.daoImplManager.StockPFDAOImpl;
import dao.daoImplManager.TransferStockPFDAOImpl;
import dao.daoImplManager.TypeVenteDAOImpl;
import dao.daoManager.ArticlesDAO;
import dao.daoManager.ChargeFixeDAO;
import dao.daoManager.ChargeProductionDAO;
import dao.daoManager.ChargesDAO;
import dao.daoManager.ClientDAO;
import dao.daoManager.ClientPFDAO;
import dao.daoManager.CompteClientDAO;
import dao.daoManager.CompteurProductionDAO;
import dao.daoManager.DepotDAO;
import dao.daoManager.DetailCompteClientDAO;
import dao.daoManager.DetailCoutProductionDAO;
import dao.daoManager.DetailFactureAchatDAO;
import dao.daoManager.DetailFactureAvoirPFDAO;
import dao.daoManager.DetailFacturePFDAO;
import dao.daoManager.DetailTransferProduitFiniDAO;
import dao.daoManager.FactureAchatDAO;
import dao.daoManager.FactureAvoirPFDAO;
import dao.daoManager.FacturePFDAO;
import dao.daoManager.FamilleArticlesPFDAO;
import dao.daoManager.FournisseurDAO;
import dao.daoManager.ListTvaDAO;
import dao.daoManager.MatierePremiereDAO;
import dao.daoManager.ParametreDAO;
import dao.daoManager.ProductionDAO;
import dao.daoManager.SousFamilleArticlesPFDAO;
import dao.daoManager.StockMPDAO;
import dao.daoManager.StockPFDAO;
import dao.daoManager.TransferStockPFDAO;
import dao.daoManager.TypeVenteDAO;
import dao.entity.Articles;
import dao.entity.ChargeProduction;
import dao.entity.Charges;
import dao.entity.ChargeFixe;
import dao.entity.Client;
import dao.entity.ClientPF;
import dao.entity.CompteurProduction;
import dao.entity.CoutMP;
import dao.entity.Depot;
import dao.entity.DetailChargeFixe;
import dao.entity.DetailChargeVariable;
import dao.entity.DetailCompteClient;
import dao.entity.DetailCoutProduction;
import dao.entity.DetailFactureAchat;
import dao.entity.DetailFactureAvoirPF;
import dao.entity.DetailFacturePF;
import dao.entity.DetailFraisDepot;
import dao.entity.DetailResponsableProd;
import dao.entity.DetailTransferProduitFini;
import dao.entity.Employe;
import dao.entity.FactureAchat;
import dao.entity.FactureAvoirPF;
import dao.entity.FacturePF;
import dao.entity.FamilleArticlePF;
import dao.entity.Fournisseur;
import dao.entity.FraisDepot;
import dao.entity.ListTva;
import dao.entity.Magasin;
import dao.entity.MatierePremier;
import dao.entity.Parametre;
import dao.entity.Production;
import dao.entity.SousFamilleArticlePF;
import dao.entity.StockMP;
import dao.entity.StockPF;
import dao.entity.TransferStockPF;
import dao.entity.TypeVente;
import dao.entity.Utilisateur;

import javax.swing.JFormattedTextField;

import com.toedter.calendar.JDateChooser;

import java.util.Locale;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.GridBagLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.JRadioButton;

import java.awt.Component;

import javax.swing.JToggleButton;

public class ConsulterReportRemise extends JLayeredPane implements Constantes {
	public ConsulterReportRemise() {
	}
	/*
	 * 
	 * 
	 * 
	 * public JLayeredPane contentPane;
	 * 
	 * private DefaultTableModel modeleArticle; private DefaultTableModel
	 * modelefacture;
	 * 
	 * private JXTable tableArticle = new JXTable(); private JXTable table = new
	 * JXTable(); private List<Fournisseur> listFournisseur =new
	 * ArrayList<Fournisseur>(); private List <Object[]> listeObject=new
	 * ArrayList<Object[]>(); private List<DetailFactureAvoirPF>
	 * listDetailFactureAvoirPF =new ArrayList<DetailFactureAvoirPF>(); private
	 * List<DetailFactureAvoirPF> listDetailFactureAvoirPFTMP =new
	 * ArrayList<DetailFactureAvoirPF>(); private List<Articles> listArticle =new
	 * ArrayList<Articles>(); private List<Depot> listDepot =new ArrayList<Depot>();
	 * private List<Depot> listparDepot =new ArrayList<Depot>(); private
	 * List<Magasin> listMagasin =new ArrayList<Magasin>(); private List<StockPF>
	 * listStockPF =new ArrayList<StockPF>(); private List<Articles>
	 * listArticleStockPF =new ArrayList<Articles>(); private List<TypeVente>
	 * listTypeVente =new ArrayList<TypeVente>(); private DetailCompteClientDAO
	 * detailCompteClientdao; private List<FactureAvoirPF> listFactureAvoirPF =new
	 * ArrayList<FactureAvoirPF>(); private List<FactureAvoirPF>
	 * listFactureAvoirPFTmp =new ArrayList<FactureAvoirPF>(); private
	 * List<ClientPF> listClientPFParCode =new ArrayList<ClientPF>(); private
	 * List<FamilleArticlePF> listFamilleArticle =new ArrayList<FamilleArticlePF>();
	 * private List<SousFamilleArticlePF> listSousFamilleArticle =new
	 * ArrayList<SousFamilleArticlePF>(); private List<ListTva> listTva =new
	 * ArrayList<ListTva>(); private Map< String, ListTva> mapTVA= new HashMap<>();
	 * private Map< BigDecimal, String> mapTVAparTaux= new HashMap<>();
	 * 
	 * TransferStockPF transferStock ; private List<DetailTransferProduitFini>
	 * listDetailTransferProduitFini= new ArrayList<DetailTransferProduitFini>();
	 * private TransferStockPFDAO transferStockPFDAO; private
	 * DetailTransferProduitFiniDAO detailTransferStockPFdao;
	 * 
	 * private Map< String, Articles> mapArticle = new HashMap<>(); private Map<
	 * String, Articles> mapCodeArticle = new HashMap<>(); private Map< String,
	 * Depot> mapDepot= new HashMap<>(); private Map< String, Depot> mapparDepot=
	 * new HashMap<>(); private Map< String, Magasin> mapMagasin= new HashMap<>();
	 * private Map< String, TypeVente> mapTypeVente= new HashMap<>(); private Map<
	 * String, Fournisseur> mapFournisseur= new HashMap<>(); private Map< String,
	 * FamilleArticlePF> mapfamille= new HashMap<>(); private Map< String,
	 * SousFamilleArticlePF> mapsousfamille= new HashMap<>(); private ImageIcon
	 * imgModifierr; private ImageIcon imgSupprimer; private ImageIcon imgAjouter;
	 * private ImageIcon imgInit; private ImageIcon imgValider; private ImageIcon
	 * imgChercher; private ListTvaDAO listtvaDAO ; private JButton btninitialiser =
	 * new JButton(); private JButton btnChercherOF; private JButton btnImprimer;
	 * private JButton btnInitialiser; private JButton btnAjouter; private JButton
	 * btnRechercher; private Utilisateur utilisateur; JComboBox combosousfamille;
	 * private JComboBox comboArticle; private ChargesDAO chargedao=new
	 * ChargesDAOImpl(); private ChargeProductionDAO chargeproductiondao; private
	 * ArticlesDAO articleDAO; private FactureAvoirPFDAO factureAvoirpfdao; private
	 * FactureAvoirPF factureAvoirPF; private DetailFactureAvoirPFDAO
	 * detailFactureAvoirPFdao; private FournisseurDAO fournisseurdao; private
	 * JComboBox combofamille ; private JTextField txtcodearticle; ChargeProduction
	 * chargeproduction; private JTextField txtquantitepacket; private JTextField
	 * txtnumfacture; private JTextField txtlibelle=new JTextField(); JComboBox
	 * combochargefixe = new JComboBox(); JComboBox combodepot = new JComboBox();
	 * private DepotDAO depotdao; private ParametreDAO parametredao; private
	 * TypeVenteDAO typeventedao; JComboBox combomagasin = new JComboBox(); private
	 * JDateChooser dateChooser = new JDateChooser(); private JDateChooser
	 * dateChooserfacture; private ChargeFixe chargefixe=new ChargeFixe(); private
	 * ChargeProduction chargeProductionTmp=new ChargeProduction(); private
	 * JTextField txtPrix; private JButton btnModifier ; private JButton
	 * btnSupprimer = new JButton(); private JComboBox comboFournisseur = new
	 * JComboBox(); private JRadioButton rdbtnDateFacture; private JDateChooser
	 * pardateChooser; private JComboBox comboparmagasin; private JComboBox
	 * comboparfournisseur = new JComboBox(); private StockPFDAO stockpfDAO; private
	 * FamilleArticlesPFDAO familleArticleDAO; private SousFamilleArticlesPFDAO
	 * sousFamilleArticleDAO; private JTextField txtquantite; JRadioButton
	 * radiosanstva = new JRadioButton("Sans TVA"); JComboBox combotva = new
	 * JComboBox(); JRadioButton radioavectva = new JRadioButton("Avec TVA");
	 * 
	 * 
	 * 
	 * public ConsulterReportRemise() { setOpaque(true); setBackground(new
	 * Color(248, 248, 255)); setForeground(new Color(248, 248, 255));
	 * 
	 * final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	 * setBounds(0, 0, 1478, 1062);
	 * 
	 * 
	 * try{
	 * 
	 * 
	 * 
	 * imgAjouter = new ImageIcon(this.getClass().getResource("/img/ajout.png"));
	 * imgModifierr= new
	 * ImageIcon(this.getClass().getResource("/img/modifier.png")); imgSupprimer=
	 * new ImageIcon(this.getClass().getResource("/img/supp1.png")); imgInit= new
	 * ImageIcon(this.getClass().getResource("/img/init.png")); imgValider= new
	 * ImageIcon(this.getClass().getResource("/img/ajout.png")); imgChercher=new
	 * ImageIcon(this.getClass().getResource("/img/chercher.png"));
	 * 
	 * 
	 * 
	 * 
	 * utilisateur=AuthentificationView.utilisateur; detailTransferStockPFdao=new
	 * DetailTransferProduitFiniDAOImpl(); transferStockPFDAO=new
	 * TransferStockPFDAOImpl();
	 * 
	 * depotdao=new DepotDAOImpl(); typeventedao=new TypeVenteDAOImpl();
	 * factureAvoirpfdao=new FactureAvoirPFDAOImpl(); detailFactureAvoirPFdao=new
	 * DetailFactureAvoirPFDAOImpl();
	 * 
	 * articleDAO=new ArticlesDAOImpl(); stockpfDAO=new StockPFDAOImpl();
	 * parametredao=new ParametreDAOImpl(); detailCompteClientdao=new
	 * DetailCompteClientDAOImpl(); listtvaDAO=new ListTvaDAOImpl();
	 * fournisseurdao=new FournisseurDAOImpl(); familleArticleDAO=new
	 * FamilleArticlesPFDAOImpl(); sousFamilleArticleDAO=new
	 * SousFamilleArticlesPFDAOImpl();
	 * listFamilleArticle=familleArticleDAO.findAll();
	 * 
	 * } catch (Exception exp){exp.printStackTrace();}
	 * tableArticle.setSortable(false); tableArticle.addMouseListener(new
	 * MouseAdapter() {
	 * 
	 * @Override public void mouseClicked(MouseEvent arg0) {
	 * 
	 * 
	 * 
	 * 
	 * boolean trouve=false;
	 * combofamille.setSelectedItem(tableArticle.getValueAt(tableArticle.
	 * getSelectedRow(), 0));
	 * combosousfamille.setSelectedItem(tableArticle.getValueAt(tableArticle.
	 * getSelectedRow(), 1));
	 * comboArticle.setSelectedItem(tableArticle.getValueAt(tableArticle.
	 * getSelectedRow(), 2));
	 * 
	 * 
	 * Articles
	 * article=mapArticle.get(tableArticle.getValueAt(tableArticle.getSelectedRow(),
	 * 2).toString()); if(article.getCentreCout2()!=null) {
	 * if(article.getCentreCout2().equals(Constantes.ARTICLE_PACKET)) { trouve=true;
	 * }
	 * 
	 * }
	 * 
	 * if(trouve==true) { txtPrix.setText(new
	 * BigDecimal(tableArticle.getValueAt(tableArticle.getSelectedRow(),
	 * 3).toString()).multiply(article.getConditionnement())+"");
	 * 
	 * if(new BigDecimal(tableArticle.getValueAt(tableArticle.getSelectedRow(),
	 * 4).toString()).compareTo(article.getConditionnement())<0) {
	 * txtquantite.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(),
	 * 4).toString()); }else { txtquantitepacket.setText(new
	 * BigDecimal(tableArticle.getValueAt(tableArticle.getSelectedRow(),
	 * 4).toString()).divide(article.getConditionnement(),6,RoundingMode.HALF_UP)+""
	 * );
	 * 
	 * String[] part=txtquantitepacket.getText().split("\\.");
	 * 
	 * txtquantitepacket.setText(part[0]); txtquantite.setText(new
	 * BigDecimal(tableArticle.getValueAt(tableArticle.getSelectedRow(),
	 * 4).toString()).subtract(new
	 * BigDecimal(txtquantitepacket.getText()).multiply(article.getConditionnement()
	 * ))+""); } }else {
	 * txtquantite.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(),
	 * 4).toString());
	 * txtPrix.setText(tableArticle.getValueAt(tableArticle.getSelectedRow(),
	 * 3).toString()); }
	 * 
	 * DetailFactureAvoirPF
	 * detailfactureavoir=listDetailFactureAvoirPF.get(tableArticle.getSelectedRow()
	 * );
	 * if(detailfactureavoir.getTva().setScale(2).equals(BigDecimal.ZERO.setScale(2)
	 * )) { radiosanstva.setSelected(true); combotva.setVisible(false);
	 * combotva.setSelectedIndex(-1);
	 * 
	 * }else { radioavectva.setSelected(true); combotva.setVisible(true);
	 * 
	 * combotva.setSelectedItem(mapTVAparTaux.get(detailfactureavoir.getTva().
	 * setScale(2).divide(new BigDecimal(100))));
	 * 
	 * }
	 * 
	 * 
	 * btnAjouter.setEnabled(false);
	 * 
	 * 
	 * } });
	 * 
	 * tableArticle.setModel(new DefaultTableModel( new Object[][] { }, new String[]
	 * { "Famille","Sous Famille","Article", "Prix Unitaire",
	 * "Quantite","Reduction", "Montant HT", "Montant TVA", "Montant TTC" } ));
	 * 
	 * tableArticle.setShowVerticalLines(false);
	 * tableArticle.setSelectionBackground(new Color(51, 204, 255));
	 * tableArticle.setRowHeightEnabled(true); tableArticle.setBackground(new
	 * Color(255, 255, 255));
	 * tableArticle.setHighlighters(HighlighterFactory.createSimpleStriping());
	 * tableArticle.setColumnControlVisible(true);
	 * tableArticle.setForeground(Color.BLACK); tableArticle.setGridColor(new
	 * Color(0, 0, 255)); tableArticle.setAutoCreateRowSorter(true);
	 * tableArticle.setBounds(2, 27, 411, 198); tableArticle.setRowHeight(20);
	 * 
	 * JScrollPane scrollPane = new JScrollPane(tableArticle);
	 * scrollPane.setBounds(10, 676, 1375, 264); add(scrollPane);
	 * scrollPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	 * 
	 * 
	 * JXTitledSeparator titledSeparator = new JXTitledSeparator();
	 * titledSeparator.setTitle("Liste Des Articles"); titledSeparator.setBounds(10,
	 * 648, 1458, 30); add(titledSeparator);
	 * 
	 * JLayeredPane layeredPane = new JLayeredPane(); layeredPane.setBorder(new
	 * EtchedBorder(EtchedBorder.LOWERED, null, null)); layeredPane.setBounds(10,
	 * 523, 1458, 93); add(layeredPane);
	 * 
	 * JLabel lblCodeArticle = new JLabel("Code Article :");
	 * lblCodeArticle.setBounds(516, 12, 74, 26); layeredPane.add(lblCodeArticle);
	 * lblCodeArticle.setFont(new Font("Tahoma", Font.PLAIN, 11));
	 * 
	 * JLabel lbllibelle = new JLabel("Libelle :"); lbllibelle.setBounds(691, 12,
	 * 45, 26); layeredPane.add(lbllibelle); lbllibelle.setFont(new Font("Tahoma",
	 * Font.PLAIN, 11));
	 * 
	 * txtcodearticle = new JTextField();
	 * 
	 * util.Utils.copycoller(txtcodearticle); txtcodearticle.addKeyListener(new
	 * KeyAdapter() {
	 * 
	 * @Override public void keyPressed(KeyEvent e) { if(e.getKeyCode()==e.VK_ENTER)
	 * {
	 * 
	 * 
	 * if(!txtcodearticle.getText().equals("")) {
	 * 
	 * Articles article=mapCodeArticle.get(txtcodearticle.getText());
	 * 
	 * if(article!=null) { comboArticle.setSelectedItem(article.getLiblle());
	 * 
	 * }else { JOptionPane.showMessageDialog(null, "Code Article Introuvable !!!!",
	 * "Erreur", JOptionPane.ERROR_MESSAGE);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * }else { JOptionPane.showMessageDialog(null,
	 * "Veuillez  entrer code Article SVP", "Erreur", JOptionPane.ERROR_MESSAGE);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * } });
	 * 
	 * 
	 * 
	 * txtcodearticle.setColumns(10); txtcodearticle.setBounds(588, 12, 93, 26);
	 * layeredPane.add(txtcodearticle);
	 * 
	 * 
	 * comboArticle = new JComboBox(); comboArticle.addActionListener(new
	 * ActionListener() { public void actionPerformed(ActionEvent arg0) {
	 * 
	 * 
	 * if(comboArticle.getSelectedIndex()!=-1) {
	 * if(!comboArticle.getSelectedItem().equals("")) { Articles
	 * article=mapArticle.get(comboArticle.getSelectedItem());
	 * txtcodearticle.setText(article.getCodeArticle());
	 * 
	 * 
	 * }else { txtcodearticle.setText("");
	 * 
	 * }
	 * 
	 * }
	 * 
	 * } }); comboArticle.setBounds(736, 12, 218, 27);
	 * layeredPane.add(comboArticle); AutoCompleteDecorator.decorate(comboArticle);
	 * 
	 * 
	 * JLabel lblQuantit = new JLabel("Quantite Packet :");
	 * lblQuantit.setBounds(170, 55, 93, 26); layeredPane.add(lblQuantit);
	 * 
	 * txtquantitepacket = new JTextField();
	 * util.Utils.copycoller(txtquantitepacket);
	 * txtquantitepacket.addKeyListener(new KeyAdapter() {
	 * 
	 * @Override public void keyPressed(KeyEvent e) {
	 * 
	 * 
	 * 
	 * 
	 * 
	 * } }); txtquantitepacket.setColumns(10); txtquantitepacket.setBounds(259, 54,
	 * 101, 26); layeredPane.add(txtquantitepacket);
	 * 
	 * JLabel lblPrix = new JLabel("Prix  :"); lblPrix.setBounds(370, 54, 45, 26);
	 * layeredPane.add(lblPrix);
	 * 
	 * txtPrix = new JTextField(); txtPrix.setColumns(10); txtPrix.setBounds(406,
	 * 55, 87, 26); layeredPane.add(txtPrix);
	 * 
	 * 
	 * JButton buttonvalider = new JButton("Valider ");
	 * buttonvalider.addActionListener(new ActionListener() { public void
	 * actionPerformed(ActionEvent e) { BigDecimal solde=BigDecimal.ZERO;
	 * if(txtnumfacture.getText().equals("")) { JOptionPane.showMessageDialog(null,
	 * "Erreur Num Facture !!!!!","Erreur",JOptionPane.ERROR_MESSAGE); return; }else
	 * if(dateChooserfacture.getDate()==null) {
	 * 
	 * JOptionPane.showMessageDialog(null,
	 * "Veuillez choisir la date SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
	 * return; }else if(combodepot.getSelectedIndex()==-1) {
	 * JOptionPane.showMessageDialog(null,
	 * "Veuillez choisir le depot SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
	 * return; }else if(combomagasin.getSelectedIndex()==-1 ||
	 * combomagasin.getSelectedItem().equals("")) {
	 * JOptionPane.showMessageDialog(null,
	 * "Veuillez choisir le magasin SVP !!!!!","Erreur",JOptionPane.ERROR_MESSAGE);
	 * return;
	 * 
	 * 
	 * }else if(comboFournisseur.getSelectedIndex()==-1) {
	 * 
	 * JOptionPane.showMessageDialog(null,
	 * "Veuillez choisir le Fournisseur SVP !!!!!","Erreur",JOptionPane.
	 * ERROR_MESSAGE); return; }
	 * 
	 * 
	 * else if(listDetailFactureAvoirPF.size()==0) {
	 * JOptionPane.showMessageDialog(null,
	 * "Veuillez entrer les articles à facturé SVP !!!!!","Erreur",JOptionPane.
	 * ERROR_MESSAGE); return; }else {
	 * 
	 * Depot depot=mapDepot.get(combodepot.getSelectedItem()); Magasin magasin
	 * =mapMagasin.get(combomagasin.getSelectedItem()); Fournisseur
	 * fournisseur=mapFournisseur.get(comboFournisseur.getSelectedItem()); int
	 * reponse = JOptionPane.showConfirmDialog(null,
	 * "Vous voulez vraiment Modifier La facture  ?", "Satisfaction",
	 * JOptionPane.YES_NO_OPTION);
	 * 
	 * if(reponse == JOptionPane.YES_OPTION )
	 * 
	 * 
	 * { FactureAvoirPF
	 * factureAvoirpfTmp=listFactureAvoirPF.get(table.getSelectedRow());
	 * factureAvoirpfTmp.setNumFacture(txtnumfacture.getText());
	 * factureAvoirpfTmp.setFournisseur(fournisseur);
	 * factureAvoirpfTmp.setDepot(depot); factureAvoirpfTmp.setMagasin(magasin);
	 * factureAvoirpfTmp.setDateFacture(dateChooserfacture.getDate());
	 * factureAvoirpfTmp.setEtat(Constantes.ETAT_NON_REGLE);
	 * factureAvoirpfTmp.setType(Constantes.TYPE_BON_LIVRAISON);
	 * factureAvoirpfTmp.setMontantHT(new
	 * BigDecimal(txttotalmontantHT.getText()).setScale(6, RoundingMode.HALF_UP));
	 * factureAvoirpfTmp.setMontantTVA(new
	 * BigDecimal(txttotalmontantTVA.getText()).setScale(6, RoundingMode.HALF_UP));
	 * factureAvoirpfTmp.setMontantTTC((new
	 * BigDecimal(txttotalmontantTTC.getText())).setScale(6, RoundingMode.HALF_UP));
	 * 
	 * factureAvoirpfTmp.setDateModifier(new Date());
	 * factureAvoirpfTmp.setModifierPar(utilisateur);
	 * factureAvoirpfdao.edit(factureAvoirpfTmp);
	 * 
	 * 
	 * 
	 * int i=0; while(i<listStockPF.size()) {
	 * 
	 * StockPF stockPF=stockpfDAO.findById(listStockPF.get(i).getId());
	 * if(stockPF!=null) { stockPF.setStock(listStockPF.get(i).getStock());
	 * stockpfDAO.edit(stockPF); }else { stockpfDAO.add(listStockPF.get(i)); }
	 * 
	 * i++; }
	 * 
	 * for(int j=0;j<listDetailTransferProduitFini.size();j++) {
	 * DetailTransferProduitFini
	 * detailtransferpf=listDetailTransferProduitFini.get(j);
	 * detailtransferpf.setDateTransfer(dateChooserfacture.getDate());
	 * detailTransferStockPFdao.edit(detailtransferpf); }
	 * 
	 * // update transfer stock produit fini
	 * transferStock.setModifierPar(utilisateur); transferStock.setDateModifier(new
	 * Date()); transferStock.setDateTransfer(dateChooserfacture.getDate()); //
	 * transferStock.setListDetailTransferProduitFini(listDetailTransferProduitFini)
	 * ; transferStockPFDAO.edit(transferStock);
	 * 
	 * //// Reglement de Stock Date dateDebut; try {
	 * detailTransferStockPFdao.ViderSession(); dateDebut = new
	 * SimpleDateFormat("yyyy-MM-dd").parse("2018"+"-01-01");
	 * listeObject=detailTransferStockPFdao.listeStockFinalePF(dateDebut, new
	 * Date(), magasin);
	 * 
	 * 
	 * for(int t=0;t<listeObject.size();t++) { Object[] object=listeObject.get(t);
	 * 
	 * 
	 * StockPF stockPF=stockpfDAO.findStockByMagasinPFBySousFamille((int) object[0],
	 * magasin.getId(), (int)object[1]); stockPF.setStock((BigDecimal)object[2]);
	 * stockpfDAO.edit(stockPF);
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * } catch (ParseException e1) { // TODO Auto-generated catch block
	 * e1.printStackTrace(); }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * JOptionPane.showMessageDialog(null,
	 * "Facture Modifier avec succée","Satisfaction",JOptionPane.INFORMATION_MESSAGE
	 * ); // initialiserFacture(); initialiser();
	 * 
	 * 
	 * listDetailFactureAvoirPF.clear(); InitialiseTableau();
	 * InitialiseTableauFacture(); chargerListFacture();
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * }}); buttonvalider.setIcon(imgValider); buttonvalider.setFont(new
	 * Font("Tahoma", Font.PLAIN, 11)); buttonvalider.setBounds(619, 952, 112, 24);
	 * add(buttonvalider);
	 * 
	 * JXTitledSeparator titledSeparator_1 = new JXTitledSeparator(); GridBagLayout
	 * gridBagLayout = (GridBagLayout) titledSeparator_1.getLayout();
	 * gridBagLayout.rowWeights = new double[]{0.0}; gridBagLayout.rowHeights = new
	 * int[]{0}; gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0};
	 * gridBagLayout.columnWidths = new int[]{0, 0, 0};
	 * titledSeparator_1.setTitle("Informations Articles");
	 * titledSeparator_1.setBounds(10, 493, 1458, 30); add(titledSeparator_1);
	 * 
	 * JLayeredPane layeredPane_1 = new JLayeredPane(); layeredPane_1.setBorder(new
	 * EtchedBorder(EtchedBorder.LOWERED, null, null)); layeredPane_1.setBounds(10,
	 * 408, 1458, 51); add(layeredPane_1);
	 * 
	 * JLabel label = new JLabel("Code  :"); label.setBounds(10, 13, 89, 24);
	 * layeredPane_1.add(label);
	 * 
	 * txtnumfacture = new JTextField(); txtnumfacture.setEditable(false);
	 * txtnumfacture.setColumns(10); txtnumfacture.setBounds(57, 12, 129, 26);
	 * layeredPane_1.add(txtnumfacture);
	 * 
	 * JLabel label_1 = new JLabel("Date  :"); label_1.setBounds(196, 13, 62, 24);
	 * layeredPane_1.add(label_1);
	 * 
	 * dateChooserfacture = new JDateChooser();
	 * dateChooserfacture.addPropertyChangeListener(new PropertyChangeListener() {
	 * public void propertyChange(PropertyChangeEvent arg0) {} });
	 * dateChooserfacture.setLocale(Locale.FRANCE);
	 * dateChooserfacture.setDateFormatString("dd/MM/yyyy");
	 * dateChooserfacture.setBounds(236, 11, 139, 26);
	 * layeredPane_1.add(dateChooserfacture);
	 * 
	 * 
	 * JLabel label_3 = new JLabel("Depot :"); label_3.setBounds(385, 13, 56, 24);
	 * layeredPane_1.add(label_3); label_3.setFont(new Font("Tahoma", Font.PLAIN,
	 * 12));
	 * 
	 * combodepot = new JComboBox(); combodepot.setBounds(433, 13, 144, 24);
	 * layeredPane_1.add(combodepot); combodepot.addItemListener(new ItemListener()
	 * { public void itemStateChanged(ItemEvent e) {
	 * 
	 * 
	 * 
	 * if(e.getStateChange() == ItemEvent.SELECTED) { int i=0;
	 * 
	 * if(!combodepot.getSelectedItem().equals("")) { Depot
	 * depot=mapDepot.get(combodepot.getSelectedItem()); if(depot!=null) {
	 * listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(),Constantes.
	 * MAGASIN_CODE_TYPE_PF); if(listMagasin.size()!=0) {
	 * combomagasin.removeAllItems(); combomagasin.addItem("");
	 * while(i<listMagasin.size()) { Magasin magasin=listMagasin.get(i);
	 * combomagasin.addItem(magasin.getLibelle());
	 * mapMagasin.put(magasin.getLibelle(), magasin); i++; }
	 * if(table.getSelectedRow()!=-1) {if(listFactureAvoirPF.size()!=0) {
	 * factureAvoirPF=listFactureAvoirPF.get(table.getSelectedRow()) ;
	 * 
	 * combomagasin.setSelectedItem(factureAvoirPF.getMagasin().getLibelle()); }
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * }else { combomagasin.removeAllItems();
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * }else { combomagasin.removeAllItems();
	 * 
	 * }
	 * 
	 * 
	 * }
	 * 
	 * 
	 * }
	 * 
	 * } });
	 * 
	 * if(utilisateur.getLogin().equals("admin")) { listDepot=depotdao.findAll();
	 * int k=0; combodepot.addItem(""); while (k<listDepot.size()) { Depot
	 * depot=listDepot.get(k); Magasin
	 * magasin=depotdao.MagasinByTypeMagasinStkAttente(MAGASIN_CODE_TYPE_MP_ATT);
	 * if(magasin!=null) { if(depot.getId()!=magasin.getDepot().getId()) {
	 * combodepot.addItem(depot.getLibelle());
	 * 
	 * mapDepot.put(depot.getLibelle(), depot);
	 * 
	 * 
	 * 
	 * } }else { combodepot.addItem(depot.getLibelle());
	 * 
	 * mapDepot.put(depot.getLibelle(), depot);
	 * 
	 * 
	 * } k++;
	 * 
	 * }
	 * 
	 * }else { Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
	 * if(depot!=null) { combodepot.addItem(depot.getLibelle());
	 * 
	 * mapDepot.put(depot.getLibelle(), depot); } }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * combodepot.setSelectedIndex(-1);
	 * 
	 * 
	 * JLabel label_4 = new JLabel("Magasin :"); label_4.setBounds(587, 13, 56, 24);
	 * layeredPane_1.add(label_4); label_4.setFont(new Font("Tahoma", Font.PLAIN,
	 * 12)); combomagasin = new JComboBox(); combomagasin.addItemListener(new
	 * ItemListener() { public void itemStateChanged(ItemEvent e) {
	 * 
	 * 
	 * } }); combomagasin.setBounds(641, 14, 183, 24);
	 * layeredPane_1.add(combomagasin); combomagasin.setSelectedIndex(-1);
	 * 
	 * JLabel label_6 = new JLabel("Fournisseur :"); label_6.setFont(new
	 * Font("Tahoma", Font.PLAIN, 12)); label_6.setBounds(859, 13, 79, 24);
	 * layeredPane_1.add(label_6);
	 * 
	 * comboFournisseur = new JComboBox(); comboFournisseur.setSelectedIndex(-1);
	 * comboFournisseur.setBounds(937, 14, 149, 24);
	 * layeredPane_1.add(comboFournisseur);
	 * 
	 * JXTitledSeparator titledSeparator_2 = new JXTitledSeparator(); GridBagLayout
	 * gridBagLayout_1 = (GridBagLayout) titledSeparator_2.getLayout();
	 * gridBagLayout_1.rowWeights = new double[]{0.0}; gridBagLayout_1.rowHeights =
	 * new int[]{0}; gridBagLayout_1.columnWeights = new double[]{0.0, 0.0, 0.0};
	 * gridBagLayout_1.columnWidths = new int[]{0, 0, 0};
	 * titledSeparator_2.setTitle("Information Facture");
	 * titledSeparator_2.setBounds(10, 375, 1311, 30); add(titledSeparator_2);
	 * 
	 * 
	 * 
	 * btnAjouter = new JButton("Ajouter"); btnAjouter.setBounds(498, 627, 107, 24);
	 * add(btnAjouter); btnAjouter.addActionListener(new ActionListener() { public
	 * void actionPerformed(ActionEvent arg0) {
	 * 
	 * try {
	 * 
	 * 
	 * boolean trouve=false; //BigDecimal prix=BigDecimal.ZERO; BigDecimal
	 * Quantite=BigDecimal.ZERO; BigDecimal QuantiteTotal=BigDecimal.ZERO;
	 * BigDecimal QuantiteUnit=BigDecimal.ZERO; BigDecimal
	 * QuantitePaquet=BigDecimal.ZERO; BigDecimal montant=BigDecimal.ZERO;
	 * BigDecimal marge=BigDecimal.ZERO; BigDecimal TVA=BigDecimal.ZERO; boolean
	 * articleboisson=false;
	 * 
	 * if(comboArticle.getSelectedItem().equals("")) {
	 * JOptionPane.showMessageDialog(null,
	 * "Veuillez Selectionner l'article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
	 * return; }else if(txtcodearticle.getText().equals("")) {
	 * JOptionPane.showMessageDialog(null,
	 * "Veuillez saisir code article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
	 * return; }else if(txtquantitepacket.getText().equals("")) {
	 * JOptionPane.showMessageDialog(null,
	 * "Veuillez saisir quantite article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
	 * return; } else if( (new
	 * BigDecimal(txtquantitepacket.getText()).compareTo(BigDecimal.ZERO)==0 )||
	 * (new BigDecimal(txtquantitepacket.getText()).compareTo(BigDecimal.ZERO)<0)) {
	 * JOptionPane.showMessageDialog(null,
	 * "la quantite d'article doit etre superieur à 0 SVP","Erreur",JOptionPane.
	 * ERROR_MESSAGE); return;
	 * 
	 * }else if(txtPrix.getText().equals("")) { JOptionPane.showMessageDialog(null,
	 * "Erreur de prix","Erreur",JOptionPane.ERROR_MESSAGE); return;
	 * 
	 * }else {
	 * 
	 * Articles article=mapArticle.get(comboArticle.getSelectedItem()); Magasin
	 * magasin=mapMagasin.get(combomagasin.getSelectedItem()); SousFamilleArticlePF
	 * sousfamille=mapsousfamille.get(combosousfamille.getSelectedItem());
	 * if(radioavectva.isSelected()==true) { if(combotva.getSelectedIndex()!=-1) {
	 * ListTva listtva=mapTVA.get(combotva.getSelectedItem());
	 * TVA=listtva.getTaux(); }
	 * 
	 * }
	 * 
	 * 
	 * if(!txtquantitepacket.getText().equals("")) { QuantitePaquet=new
	 * BigDecimal(txtquantitepacket.getText()).multiply(article.getConditionnement()
	 * ) ; } if(!txtquantite.getText().equals("")) { QuantiteUnit=new
	 * BigDecimal(txtquantite.getText()) ; }
	 * QuantiteTotal=QuantitePaquet.add(QuantiteUnit);
	 * 
	 * 
	 * 
	 * 
	 * if(article.getCentreCout1()!=null) { BigDecimal quantite =
	 * (QuantitePaquet.add(QuantiteUnit)).setScale(3); BigDecimal grammage =
	 * quantite.multiply(new BigDecimal(1000));
	 * 
	 * 
	 * if(article.getCentreCout1().equals(BigDecimal.ONE)) {
	 * if(grammage.compareTo(BigDecimal.ZERO)!=0) {
	 * JOptionPane.showMessageDialog(null,
	 * "Grammage  "+article.getLiblle()+" incompatible !!!!!","Attention",
	 * JOptionPane.ERROR_MESSAGE); return; }
	 * 
	 * }else { BigDecimal result=grammage.divide(article.getCentreCout1(), 6,
	 * RoundingMode.FLOOR);
	 * 
	 * BigDecimal rest=result.subtract(result.setScale(0,
	 * RoundingMode.FLOOR)).movePointRight(result.scale());
	 * 
	 * if(rest.compareTo(BigDecimal.ZERO)!=0) { JOptionPane.showMessageDialog(null,
	 * "Grammage d'"+article.getLiblle()+" incompatible !!!!!","Attention",
	 * JOptionPane.ERROR_MESSAGE); return; }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * for(int i=0;i<listDetailFactureAvoirPF.size();i++) { DetailFactureAvoirPF
	 * detailFactureAvoirPF =listDetailFactureAvoirPF.get(i);
	 * if(detailFactureAvoirPF.getArticle().getLiblle().equals(article.getLiblle())
	 * && detailFactureAvoirPF.getSousFamille().getLiblle().equals(sousfamille.
	 * getLiblle())) { trouve=true; } }
	 * 
	 * if(trouve==false) { boolean existe=false;
	 * 
	 * if(article.getCentreCout2()!=null) {
	 * if(article.getCentreCout2().equals(Constantes.ARTICLE_PACKET)) { existe=true;
	 * }
	 * 
	 * }
	 * 
	 * 
	 * 
	 * StockPF stockpf=stockpfDAO.findStockByMagasinPFBySousFamille(article.getId(),
	 * magasin.getId(),sousfamille.getId());
	 * 
	 * 
	 * 
	 * DetailFactureAvoirPF detailFacture=new DetailFactureAvoirPF();
	 * if(!txtreduction.getText().equals("")) { detailFacture.setReduction(new
	 * BigDecimal(txtreduction.getText())); }else {
	 * detailFacture.setReduction(BigDecimal.ZERO); }
	 * 
	 * detailFacture.setArticle(article); detailFacture.setQuantite(QuantiteTotal);
	 * 
	 * if(existe==true) { detailFacture.setPrixUnitaire(new
	 * BigDecimal(txtPrix.getText()).divide(article.getConditionnement() , 6,
	 * RoundingMode.DOWN)); montant=QuantiteTotal.multiply(new
	 * BigDecimal(txtPrix.getText()).divide(article.getConditionnement() , 6,
	 * RoundingMode.DOWN)); }else { detailFacture.setPrixUnitaire(new
	 * BigDecimal(txtPrix.getText()).setScale(6));
	 * montant=QuantiteTotal.multiply(new
	 * BigDecimal(txtPrix.getText()).setScale(6)); }
	 * if(article.getCodeArticle().equals("1500") ||
	 * article.getCodeArticle().equals("1503") ||
	 * article.getCodeArticle().equals("1504") ) {
	 * 
	 * detailFacture.setMontantHT(montant);
	 * detailFacture.setMontantTVA(((montant).multiply(BigDecimal.ZERO)).setScale(6,
	 * RoundingMode.HALF_UP)); detailFacture.setTva(BigDecimal.ZERO);
	 * detailFacture.setSousFamille(sousfamille);
	 * if(!txtreduction.getText().equals("")) {
	 * detailFacture.setMontantTTC((((montant).add((montant).multiply(BigDecimal.
	 * ZERO))).setScale(6,
	 * RoundingMode.HALF_UP)).subtract((((montant).add((montant).multiply(BigDecimal
	 * .ZERO))).setScale(6, RoundingMode.HALF_UP)).multiply((new
	 * BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6,
	 * RoundingMode.HALF_UP)))); }else {
	 * detailFacture.setMontantTTC(((montant).add((montant).multiply(BigDecimal.ZERO
	 * ))).setScale(6, RoundingMode.HALF_UP)); }
	 * 
	 * }else { detailFacture.setMontantHT(montant);
	 * detailFacture.setMontantTVA(((montant).multiply(TVA)).setScale(6,
	 * RoundingMode.HALF_UP)); detailFacture.setTva(TVA.multiply(new
	 * BigDecimal(100))); detailFacture.setSousFamille(sousfamille);
	 * if(!txtreduction.getText().equals("")) {
	 * detailFacture.setMontantTTC((((montant).add((montant).multiply(TVA))).
	 * setScale(6,
	 * RoundingMode.HALF_UP)).subtract((((montant).add((montant).multiply(TVA))).
	 * setScale(6, RoundingMode.HALF_UP)).multiply((new
	 * BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6,
	 * RoundingMode.HALF_UP)))); }else {
	 * detailFacture.setMontantTTC(((montant).add((montant).multiply(TVA))).setScale
	 * (6, RoundingMode.HALF_UP)); } }
	 * detailFacture.setFactureAvoirPf(factureAvoirPF); //
	 * detailFacture.setDateCreation(new Date());
	 * 
	 * detailFacture.setUtilisateur(utilisateur);
	 * detailFactureAvoirPFdao.add(detailFacture);
	 * listDetailFactureAvoirPF.add(detailFacture);
	 * 
	 * if(stockpf!=null) {
	 * 
	 * 
	 * 
	 * if(stockpf.getStock().compareTo(QuantiteTotal)>=0) {
	 * Quantite=stockpf.getStock().subtract(QuantiteTotal);
	 * if(Quantite.compareTo(BigDecimal.ZERO)<=0) { Quantite=BigDecimal.ZERO; //
	 * prix=stockpf.getPrixUnitaire();
	 * 
	 * }else { //
	 * prix=((stockpf.getPrixUnitaire().multiply(Quantite.add(quantite))).subtract(
	 * quantite.multiply(new BigDecimal(txtPrix.getText())))).divide(Quantite,
	 * RoundingMode.HALF_UP);
	 * 
	 * }
	 * 
	 * stockpf.setStock(Quantite); // stockpf.setPrixUnitaire(prix);
	 * listStockPF.add(stockpf);
	 * 
	 * 
	 * }else { JOptionPane.showMessageDialog(null,
	 * "la Quantité d'article est supérieur de celle de stock !!!!!!! ","Erreur",
	 * JOptionPane.ERROR_MESSAGE); return; }
	 * 
	 * }else { JOptionPane.showMessageDialog(null,
	 * "Article introuvable dans le stock !!!!!!! ","Erreur",JOptionPane.
	 * ERROR_MESSAGE); return;
	 * 
	 * }
	 * 
	 * 
	 * 
	 * afficher_tableDetailFactureAchat(listDetailFactureAvoirPF); int i=0;
	 * BigDecimal montanttotal=BigDecimal.ZERO; BigDecimal
	 * sommequantite=BigDecimal.ZERO; BigDecimal montanttotalHT=BigDecimal.ZERO;
	 * BigDecimal montanttotalTVA=BigDecimal.ZERO;
	 * while(i<listDetailFactureAvoirPF.size()) { articleboisson=false;
	 * 
	 * if(listDetailFactureAvoirPF.get(i).getArticle().getCentreCout2()!=null) {
	 * if(listDetailFactureAvoirPF.get(i).getArticle().getCentreCout2().equals(
	 * Constantes.ARTICLE_PACKET)) { articleboisson=true; }
	 * 
	 * }
	 * 
	 * 
	 * if(articleboisson==true) { DetailFactureAvoirPF
	 * detailFactureAvoirPF=listDetailFactureAvoirPF.get(i); montanttotal=
	 * montanttotal.add(detailFactureAvoirPF.getMontantTTC().divide(
	 * detailFactureAvoirPF.getArticle().getConditionnement(),RoundingMode.HALF_UP))
	 * ; sommequantite= sommequantite.add(detailFactureAvoirPF.getQuantite().divide(
	 * detailFactureAvoirPF.getArticle().getConditionnement(),RoundingMode.HALF_UP))
	 * ;
	 * montanttotalHT=montanttotalHT.add(detailFactureAvoirPF.getMontantHT().divide(
	 * detailFactureAvoirPF.getArticle().getConditionnement(),RoundingMode.HALF_UP))
	 * ; montanttotalTVA=montanttotalTVA.add(detailFactureAvoirPF.getMontantTVA().
	 * divide(detailFactureAvoirPF.getArticle().getConditionnement(),RoundingMode.
	 * HALF_UP));
	 * 
	 * montanttotal= montanttotal.add(detailFactureAvoirPF.getMontantTTC());
	 * sommequantite= sommequantite.add(detailFactureAvoirPF.getQuantite());
	 * montanttotalHT=montanttotalHT.add(detailFactureAvoirPF.getMontantHT());
	 * montanttotalTVA=montanttotalTVA.add(detailFactureAvoirPF.getMontantTVA());
	 * 
	 * 
	 * }else {
	 * 
	 * DetailFactureAvoirPF detailFactureAvoirPF=listDetailFactureAvoirPF.get(i);
	 * montanttotal= montanttotal.add(detailFactureAvoirPF.getMontantTTC());
	 * sommequantite= sommequantite.add(detailFactureAvoirPF.getQuantite());
	 * montanttotalHT=montanttotalHT.add(detailFactureAvoirPF.getMontantHT());
	 * montanttotalTVA=montanttotalTVA.add(detailFactureAvoirPF.getMontantTVA()); }
	 * i++; } txttotalmontantTTC.setText(montanttotal+"");
	 * txttotalquantite.setText(sommequantite+"");
	 * txttotalmontantHT.setText(montanttotalHT+"");
	 * txttotalmontantTVA.setText(montanttotalTVA+"");
	 * 
	 * 
	 * /// ajout transfer stock PF (Mouvement Stock PF) DetailTransferProduitFini
	 * detailTransferStockPF=new DetailTransferProduitFini();
	 * detailTransferStockPF.setArticle(article);
	 * detailTransferStockPF.setSousFamille(sousfamille);
	 * detailTransferStockPF.setDateTransfer(dateChooserfacture.getDate());
	 * detailTransferStockPF.setMagasinDestination(magasin);
	 * detailTransferStockPF.setQuantite(QuantiteTotal);
	 * 
	 * if(existe==true) { detailTransferStockPF.setPrixUnitaire(new
	 * BigDecimal(txtPrix.getText()).divide(article.getConditionnement() , 6,
	 * RoundingMode.DOWN));
	 * 
	 * }else { detailTransferStockPF.setPrixUnitaire(new
	 * BigDecimal(txtPrix.getText())); }
	 * 
	 * detailTransferStockPF.setTransferStockPF(transferStock);
	 * detailTransferStockPF.setTypeTransfer(ETAT_TRANSFER_STOCK_AVOIR);
	 * detailTransferStockPFdao.add(detailTransferStockPF);
	 * listDetailTransferProduitFini.add(detailTransferStockPF);
	 * 
	 * 
	 * initialiser();
	 * 
	 * 
	 * 
	 * 
	 * }else { JOptionPane.showMessageDialog(null,
	 * "Article déja facturé Veuillez le Modifier SVP !!!!!","Erreur",JOptionPane.
	 * ERROR_MESSAGE); return; }
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * } catch (NumberFormatException e) { JOptionPane.showMessageDialog(null,
	 * "La Quantité , Le Prix et la Remise doit etre en chiffre SVP !!!!","Erreur",
	 * JOptionPane.ERROR_MESSAGE); }
	 * 
	 * }
	 * 
	 * 
	 * }); btnAjouter.setIcon(imgAjouter);
	 * 
	 * btnAjouter.setFont(new Font("Tahoma", Font.PLAIN, 11));
	 * 
	 * 
	 * 
	 * btnModifier = new JButton(); btnModifier.addActionListener(new
	 * ActionListener() { public void actionPerformed(ActionEvent arg0) {
	 * 
	 * try {
	 * 
	 * boolean articleboisson=false; BigDecimal nouveauprix=BigDecimal.ZERO;
	 * BigDecimal coutAchat=BigDecimal.ZERO; BigDecimal coutStock=BigDecimal.ZERO;
	 * BigDecimal QuantiteTotal=BigDecimal.ZERO; //BigDecimal
	 * oldPrix=BigDecimal.ZERO; BigDecimal oldQuantite=BigDecimal.ZERO; BigDecimal
	 * montant=BigDecimal.ZERO; BigDecimal stocktmp=BigDecimal.ZERO; BigDecimal
	 * marge=BigDecimal.ZERO; BigDecimal TVA=BigDecimal.ZERO; BigDecimal
	 * QuantiteUnit=BigDecimal.ZERO; BigDecimal QuantitePaquet=BigDecimal.ZERO;
	 * 
	 * if(tableArticle.getSelectedRow()!=-1) { boolean trouve=false;
	 * 
	 * if(comboArticle.getSelectedItem().equals("") ||
	 * comboArticle.getSelectedIndex()==-1) { JOptionPane.showMessageDialog(null,
	 * "Veuillez Selectionner l'article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
	 * return; }else if(txtcodearticle.getText().equals("")) {
	 * JOptionPane.showMessageDialog(null,
	 * "Veuillez saisir code article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
	 * return; }else if(txtquantitepacket.getText().equals("")) {
	 * JOptionPane.showMessageDialog(null,
	 * "Veuillez saisir quantite article SVP","Erreur",JOptionPane.ERROR_MESSAGE);
	 * return; } else if( (new
	 * BigDecimal(txtquantitepacket.getText()).compareTo(BigDecimal.ZERO)==0 )||
	 * (new BigDecimal(txtquantitepacket.getText()).compareTo(BigDecimal.ZERO)<0)) {
	 * JOptionPane.showMessageDialog(null,
	 * "la quantite d'article doit etre superieur à 0 SVP","Erreur",JOptionPane.
	 * ERROR_MESSAGE); return;
	 * 
	 * }else if(txtPrix.getText().equals("")) { JOptionPane.showMessageDialog(null,
	 * "Erreur de prix","Erreur",JOptionPane.ERROR_MESSAGE); return;
	 * 
	 * }else {
	 * 
	 * Articles article=mapArticle.get(comboArticle.getSelectedItem()); Magasin
	 * magasin=mapMagasin.get(combomagasin.getSelectedItem()); SousFamilleArticlePF
	 * sousfamille=mapsousfamille.get(combosousfamille.getSelectedItem());
	 * 
	 * if(radioavectva.isSelected()==true) { if(combotva.getSelectedIndex()!=-1) {
	 * ListTva listtva=mapTVA.get(combotva.getSelectedItem());
	 * TVA=listtva.getTaux(); }
	 * 
	 * }
	 * 
	 * if(!txtquantitepacket.getText().equals("")) { QuantitePaquet=new
	 * BigDecimal(txtquantitepacket.getText()).multiply(article.getConditionnement()
	 * ) ; } if(!txtquantite.getText().equals("")) { QuantiteUnit=new
	 * BigDecimal(txtquantite.getText()) ; }
	 * QuantiteTotal=QuantitePaquet.add(QuantiteUnit);
	 * 
	 * if(article.getCentreCout1()!=null) { BigDecimal quantite =
	 * (QuantitePaquet.add(QuantiteUnit)).setScale(3); BigDecimal grammage =
	 * quantite.multiply(new BigDecimal(1000));
	 * 
	 * 
	 * if(article.getCentreCout1().equals(BigDecimal.ONE)) {
	 * if(grammage.compareTo(BigDecimal.ZERO)!=0) {
	 * JOptionPane.showMessageDialog(null,
	 * "Grammage  "+article.getLiblle()+" incompatible !!!!!","Attention",
	 * JOptionPane.ERROR_MESSAGE); return; }
	 * 
	 * }else { BigDecimal result=grammage.divide(article.getCentreCout1(), 6,
	 * RoundingMode.FLOOR);
	 * 
	 * BigDecimal rest=result.subtract(result.setScale(0,
	 * RoundingMode.FLOOR)).movePointRight(result.scale());
	 * 
	 * if(rest.compareTo(BigDecimal.ZERO)!=0) { JOptionPane.showMessageDialog(null,
	 * "Grammage d'"+article.getLiblle()+" incompatible !!!!!","Attention",
	 * JOptionPane.ERROR_MESSAGE); return; }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * for(int i=0;i<listDetailFactureAvoirPF.size();i++) {
	 * if(i!=tableArticle.getSelectedRow()) {
	 * 
	 * DetailFactureAvoirPF detailFactureAvoirpf =listDetailFactureAvoirPF.get(i);
	 * if(detailFactureAvoirpf.getArticle().getLiblle().equals(article.getLiblle())
	 * && detailFactureAvoirpf.getSousFamille().getLiblle().equals(sousfamille.
	 * getLiblle())) { trouve=true; }
	 * 
	 * 
	 * }
	 * 
	 * 
	 * }
	 * 
	 * if(trouve==false) { boolean existe=false;
	 * 
	 * StockPF stockpftmp
	 * =stockpfDAO.findStockByMagasinPFBySousFamille(listDetailFactureAvoirPF.get(
	 * tableArticle.getSelectedRow()).getArticle().getId(),
	 * listFactureAvoirPF.get(table.getSelectedRow()).getMagasin().getId(),
	 * listDetailFactureAvoirPF.get(tableArticle.getSelectedRow()).getSousFamille().
	 * getId()) ;
	 * 
	 * // coutAchat=listDetailFactureAvoirPF.get(tableArticle.getSelectedRow()).
	 * getQuantite().multiply(listDetailFactureAvoirPF.get(tableArticle.
	 * getSelectedRow()).getPrixUnitaire()); //
	 * coutStock=stockpftmp.getStock().multiply(stockpftmp.getPrixUnitaire());
	 * QuantiteTotal=listDetailFactureAvoirPF.get(tableArticle.getSelectedRow()).
	 * getQuantite().add(stockpftmp.getStock());
	 * 
	 * // nouveauprix=(coutAchat.add(coutStock)).divide(QuantiteTotal, 6,
	 * RoundingMode.HALF_UP); stockpftmp.setStock(QuantiteTotal); //
	 * stockpftmp.setPrixUnitaire(nouveauprix); stockpfDAO.edit(stockpftmp);
	 * 
	 * 
	 * StockPF stockpf=stockpfDAO.findStockByMagasinPFBySousFamille(article.getId(),
	 * magasin.getId(),sousfamille.getId());
	 * 
	 * 
	 * if(article.getCentreCout2()!=null) {
	 * if(article.getCentreCout2().equals(Constantes.ARTICLE_PACKET)) { existe=true;
	 * }
	 * 
	 * }
	 * 
	 * 
	 * 
	 * DetailFactureAvoirPF detailFacture=
	 * listDetailFactureAvoirPF.get(tableArticle.getSelectedRow());
	 * DetailTransferProduitFini
	 * detailTransferStockPF=listDetailTransferProduitFini.get(tableArticle.
	 * getSelectedRow());
	 * 
	 * if(!txtreduction.getText().equals("")) { detailFacture.setReduction(new
	 * BigDecimal(txtreduction.getText())); }else {
	 * detailFacture.setReduction(BigDecimal.ZERO); }
	 * 
	 * detailFacture.setArticle(article);
	 * 
	 * detailFacture.setQuantite(QuantiteTotal);
	 * 
	 * 
	 * 
	 * if(existe==true) { detailFacture.setPrixUnitaire(new
	 * BigDecimal(txtPrix.getText()).divide(article.getConditionnement() , 6,
	 * RoundingMode.DOWN)); montant=QuantiteTotal.multiply(new
	 * BigDecimal(txtPrix.getText()).divide(article.getConditionnement() , 6,
	 * RoundingMode.DOWN));
	 * 
	 * }else { detailFacture.setPrixUnitaire(new BigDecimal(txtPrix.getText()));
	 * montant=QuantiteTotal.multiply(new BigDecimal(txtPrix.getText())); }
	 * 
	 * if(article.getCodeArticle().equals("1500") ||
	 * article.getCodeArticle().equals("1503") ||
	 * article.getCodeArticle().equals("1504") ) {
	 * 
	 * detailFacture.setMontantHT(montant);
	 * detailFacture.setMontantTVA(((montant).multiply(BigDecimal.ZERO)).setScale(6,
	 * RoundingMode.HALF_UP)); detailFacture.setTva(BigDecimal.ZERO);
	 * detailFacture.setSousFamille(sousfamille);
	 * if(!txtreduction.getText().equals("")) {
	 * detailFacture.setMontantTTC((((montant).add((montant).multiply(BigDecimal.
	 * ZERO))).setScale(6,
	 * RoundingMode.HALF_UP)).subtract((((montant).add((montant).multiply(BigDecimal
	 * .ZERO))).setScale(6, RoundingMode.HALF_UP)).multiply((new
	 * BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6,
	 * RoundingMode.HALF_UP)))); }else {
	 * detailFacture.setMontantTTC(((montant).add((montant).multiply(BigDecimal.ZERO
	 * ))).setScale(6, RoundingMode.HALF_UP)); }
	 * 
	 * }else { detailFacture.setMontantHT(montant);
	 * detailFacture.setMontantTVA(((montant).multiply(TVA)).setScale(6,
	 * RoundingMode.HALF_UP)); detailFacture.setTva(TVA.multiply(new
	 * BigDecimal(100))); detailFacture.setSousFamille(sousfamille);
	 * 
	 * if(!txtreduction.getText().equals("")) {
	 * detailFacture.setMontantTTC((((montant).add((montant).multiply(TVA))).
	 * setScale(6,
	 * RoundingMode.HALF_UP)).subtract((((montant).add((montant).multiply(TVA))).
	 * setScale(6, RoundingMode.HALF_UP)).multiply((new
	 * BigDecimal(txtreduction.getText())).divide(new BigDecimal(100), 6,
	 * RoundingMode.HALF_UP)))); }else {
	 * detailFacture.setMontantTTC(((montant).add((montant).multiply(TVA))).setScale
	 * (6, RoundingMode.HALF_UP)); } }
	 * detailFacture.setFactureAvoirPf(factureAvoirPF); //
	 * detailFacture.setDateCreation(new Date());
	 * 
	 * detailFacture.setUtilisateur(utilisateur);
	 * 
	 * 
	 * // Modifier Transfer Stock PF detailTransferStockPF.setArticle(article);
	 * detailTransferStockPF.setSousFamille(sousfamille);
	 * detailTransferStockPF.setDateTransfer(dateChooserfacture.getDate());
	 * detailTransferStockPF.setMagasinDestination(magasin);
	 * detailTransferStockPF.setQuantite(QuantiteTotal); if(existe==true) {
	 * detailTransferStockPF.setPrixUnitaire(new
	 * BigDecimal(txtPrix.getText()).divide(article.getConditionnement() , 6,
	 * RoundingMode.DOWN));
	 * 
	 * }else { detailTransferStockPF.setPrixUnitaire(new
	 * BigDecimal(txtPrix.getText())); }
	 * 
	 * detailTransferStockPF.setTypeTransfer(ETAT_TRANSFER_STOCK_AVOIR);
	 * detailTransferStockPFdao.edit(detailTransferStockPF);
	 * listDetailTransferProduitFini.set(tableArticle.getSelectedRow(),
	 * detailTransferStockPF);
	 * 
	 * 
	 * if(stockpf!=null) { BigDecimal quantite=BigDecimal.ZERO;
	 * 
	 * if(stockpf.getStock().compareTo(QuantiteTotal)>=0) {
	 * oldQuantite=stockpf.getStock().subtract(QuantiteTotal);
	 * if(oldQuantite.compareTo(BigDecimal.ZERO)<=0) { oldQuantite=BigDecimal.ZERO;
	 * // oldPrix=stockpf.getPrixUnitaire();
	 * 
	 * }else { //
	 * oldPrix=((stockpf.getPrixUnitaire().multiply(oldQuantite.add(quantite))).
	 * subtract(quantite.multiply(new
	 * BigDecimal(txtPrix.getText())))).divide(oldQuantite, RoundingMode.HALF_UP);
	 * 
	 * }
	 * 
	 * stockpf.setStock(oldQuantite); //stockpf.setPrixUnitaire(oldPrix);
	 * listStockPF.add(stockpf);
	 * 
	 * 
	 * }else { JOptionPane.showMessageDialog(null,
	 * "la Quantité d'article est supérieur de celle de stock !!!!!!! ","Erreur",
	 * JOptionPane.ERROR_MESSAGE); return; }
	 * 
	 * 
	 * listStockPF.set(tableArticle.getSelectedRow(), stockpf); } else {
	 * 
	 * 
	 * JOptionPane.showMessageDialog(null,
	 * "Article introuvable dans le stock !!!!!!! ","Erreur",JOptionPane.
	 * ERROR_MESSAGE); return;
	 * 
	 * }
	 * 
	 * detailFactureAvoirPFdao.edit(detailFacture);
	 * listDetailFactureAvoirPF.set(tableArticle.getSelectedRow(), detailFacture);
	 * afficher_tableDetailFactureAchat(listDetailFactureAvoirPF); int i=0;
	 * BigDecimal montanttotal=BigDecimal.ZERO; BigDecimal
	 * sommequantite=BigDecimal.ZERO; BigDecimal montanttotalHT=BigDecimal.ZERO;
	 * BigDecimal montanttotalTVA=BigDecimal.ZERO;
	 * while(i<listDetailFactureAvoirPF.size()) {
	 * 
	 * articleboisson=false;
	 * 
	 * if(listDetailFactureAvoirPF.get(i).getArticle().getCentreCout2()!=null) {
	 * if(listDetailFactureAvoirPF.get(i).getArticle().getCentreCout2().equals(
	 * Constantes.ARTICLE_PACKET)) { articleboisson=true; }
	 * 
	 * }
	 * 
	 * 
	 * if(articleboisson==true) { DetailFactureAvoirPF
	 * detailFactureAvoirPF=listDetailFactureAvoirPF.get(i); montanttotal=
	 * montanttotal.add(detailFactureAvoirPF.getMontantTTC()); sommequantite=
	 * sommequantite.add(detailFactureAvoirPF.getQuantite());
	 * montanttotalHT=montanttotalHT.add(detailFactureAvoirPF.getMontantHT());
	 * montanttotalTVA=montanttotalTVA.add(detailFactureAvoirPF.getMontantTVA());
	 * }else {
	 * 
	 * DetailFactureAvoirPF detailFactureAvoirPF=listDetailFactureAvoirPF.get(i);
	 * montanttotal= montanttotal.add(detailFactureAvoirPF.getMontantTTC());
	 * sommequantite= sommequantite.add(detailFactureAvoirPF.getQuantite());
	 * montanttotalHT=montanttotalHT.add(detailFactureAvoirPF.getMontantHT());
	 * montanttotalTVA=montanttotalTVA.add(detailFactureAvoirPF.getMontantTVA()); }
	 * i++; } txttotalmontantTTC.setText(montanttotal+"");
	 * txttotalquantite.setText(sommequantite+"");
	 * txttotalmontantHT.setText(montanttotalHT+"");
	 * txttotalmontantTVA.setText(montanttotalTVA+""); initialiser();
	 * 
	 * 
	 * 
	 * 
	 * }else { JOptionPane.showMessageDialog(null,
	 * "Article déja facturé Veuillez le Modifier SVP !!!!!","Erreur",JOptionPane.
	 * ERROR_MESSAGE); return; }
	 * 
	 * 
	 * 
	 * } }
	 * 
	 * 
	 * 
	 * } catch (NumberFormatException e) { JOptionPane.showMessageDialog(null,
	 * "La Quantite , le Prix et la Remise doit etre en chiffre SVP !!!!","Erreur",
	 * JOptionPane.ERROR_MESSAGE); }
	 * 
	 * }
	 * 
	 * 
	 * }); btnModifier.setFont(new Font("Tahoma", Font.PLAIN, 11));
	 * 
	 * 
	 * btnSupprimer = new JButton(); btnSupprimer.addActionListener(new
	 * ActionListener() { public void actionPerformed(ActionEvent arg0) {
	 * 
	 * BigDecimal oldPrix=BigDecimal.ZERO; BigDecimal oldQuantite=BigDecimal.ZERO;
	 * BigDecimal nouveauprix=BigDecimal.ZERO; BigDecimal coutAchat=BigDecimal.ZERO;
	 * BigDecimal coutStock=BigDecimal.ZERO; BigDecimal
	 * QuantiteTotal=BigDecimal.ZERO; boolean articleboisson=false;
	 * if(tableArticle.getSelectedRow()!=-1) {
	 * 
	 * int reponse = JOptionPane.showConfirmDialog(null,
	 * "Vous voulez vraiment supprimer l'article dans la facture  ?",
	 * "Satisfaction", JOptionPane.YES_NO_OPTION);
	 * 
	 * if(reponse == JOptionPane.YES_OPTION )
	 * 
	 * {
	 * 
	 * StockPF stockpftmp =listStockPF.get(tableArticle.getSelectedRow());
	 * coutAchat=listDetailFactureAvoirPF.get(tableArticle.getSelectedRow()).
	 * getQuantite().multiply(listDetailFactureAvoirPF.get(tableArticle.
	 * getSelectedRow()).getPrixUnitaire());
	 * coutStock=stockpftmp.getStock().multiply(stockpftmp.getPrixUnitaire());
	 * QuantiteTotal=listDetailFactureAvoirPF.get(tableArticle.getSelectedRow()).
	 * getQuantite().add(stockpftmp.getStock());
	 * 
	 * nouveauprix=(coutAchat.add(coutStock)).divide(QuantiteTotal, 6,
	 * RoundingMode.HALF_UP); stockpftmp.setStock(QuantiteTotal);
	 * stockpftmp.setPrixUnitaire(nouveauprix); stockpfDAO.edit(stockpftmp);
	 * 
	 * 
	 * DetailFactureAvoirPF
	 * detailfactureavoirpf=listDetailFactureAvoirPF.get(tableArticle.getSelectedRow
	 * ()); detailFactureAvoirPFdao.delete(detailfactureavoirpf.getId());
	 * listDetailFactureAvoirPF.remove(tableArticle.getSelectedRow());
	 * listStockPF.set(tableArticle.getSelectedRow(),stockpftmp);
	 * detailTransferStockPFdao.delete(listDetailTransferProduitFini.get(
	 * tableArticle.getSelectedRow()).getId());
	 * listDetailTransferProduitFini.remove(tableArticle.getSelectedRow());
	 * 
	 * afficher_tableDetailFactureAchat(listDetailFactureAvoirPF); int i=0;
	 * BigDecimal montanttotal=BigDecimal.ZERO; BigDecimal
	 * sommequantite=BigDecimal.ZERO; BigDecimal montanttotalHT=BigDecimal.ZERO;
	 * BigDecimal montanttotalTVA=BigDecimal.ZERO;
	 * while(i<listDetailFactureAvoirPF.size()) { articleboisson=false;
	 * 
	 * if(listDetailFactureAvoirPF.get(i).getArticle().getCentreCout2()!=null) {
	 * if(listDetailFactureAvoirPF.get(i).getArticle().getCentreCout2().equals(
	 * Constantes.ARTICLE_PACKET)) { articleboisson=true; }
	 * 
	 * }
	 * 
	 * 
	 * if(articleboisson==true) { DetailFactureAvoirPF
	 * detailFactureAvoirpf=listDetailFactureAvoirPF.get(i); montanttotal=
	 * montanttotal.add(detailFactureAvoirpf.getMontantTTC()); sommequantite=
	 * sommequantite.add(detailFactureAvoirpf.getQuantite());
	 * montanttotalHT=montanttotalHT.add(detailFactureAvoirpf.getMontantHT());
	 * montanttotalTVA=montanttotalTVA.add(detailFactureAvoirpf.getMontantTVA());
	 * }else {
	 * 
	 * DetailFactureAvoirPF detailFactureAvoirpf=listDetailFactureAvoirPF.get(i);
	 * montanttotal= montanttotal.add(detailFactureAvoirpf.getMontantTTC());
	 * sommequantite= sommequantite.add(detailFactureAvoirpf.getQuantite());
	 * montanttotalHT=montanttotalHT.add(detailFactureAvoirpf.getMontantHT());
	 * montanttotalTVA=montanttotalTVA.add(detailFactureAvoirpf.getMontantTVA()); }
	 * i++; } txttotalmontantTTC.setText(montanttotal+"");
	 * txttotalquantite.setText(sommequantite+"");
	 * txttotalmontantHT.setText(montanttotalHT+"");
	 * txttotalmontantTVA.setText(montanttotalTVA+"");
	 * JOptionPane.showMessageDialog(null,
	 * "Article supprimer avec succée ","Satisfaction",JOptionPane.
	 * INFORMATION_MESSAGE); initialiser(); }
	 * 
	 * }
	 * 
	 * } }); btnSupprimer.setFont(new Font("Tahoma", Font.PLAIN, 11));
	 * btnSupprimer.setBounds(1395, 735, 73, 24);
	 * btnSupprimer.setIcon(imgSupprimer); add(btnSupprimer);
	 * 
	 * btnModifier.setIcon(imgModifierr); btnModifier.setBounds(1395, 705, 73, 24);
	 * add(btnModifier);
	 * 
	 * JLabel lblConslterLesFactures = new
	 * JLabel("                        Consulter les Reports Remise par :");
	 * lblConslterLesFactures.setBackground(Color.WHITE);
	 * lblConslterLesFactures.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC,
	 * 35)); lblConslterLesFactures.setBounds(151, 11, 1234, 35);
	 * add(lblConslterLesFactures);
	 * 
	 * JScrollPane scrollPane_1 = new JScrollPane((Component) null);
	 * scrollPane_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	 * scrollPane_1.setBounds(10, 154, 1458, 218); add(scrollPane_1);
	 * table.setSortable(false); table.addMouseListener(new MouseAdapter() {
	 * 
	 * @Override public void mouseClicked(MouseEvent arg0) {
	 * if(table.getSelectedRow()!=-1) { boolean articleboisson=false;
	 * if(listFactureAvoirPF.size()!=0) { listStockPF.clear();
	 * factureAvoirPF=listFactureAvoirPF.get(table.getSelectedRow()) ;
	 * 
	 * transferStock=transferStockPFDAO.findByCodeTransfert(factureAvoirPF.
	 * getCodeTransfer());
	 * listDetailTransferProduitFini=detailTransferStockPFdao.findByTransferStockPF(
	 * transferStock.getId());
	 * 
	 * txtnumfacture.setText(factureAvoirPF.getNumFacture());
	 * dateChooserfacture.setDate(factureAvoirPF.getDateFacture());
	 * 
	 * combodepot.setSelectedItem(factureAvoirPF.getDepot().getLibelle());
	 * 
	 * 
	 * //combomagasin.setSelectedItem(facturePF.getMagasin().getLibelle());
	 * comboFournisseur.setSelectedItem(factureAvoirPF.getFournisseur().getNom());
	 * listDetailFactureAvoirPF=detailFactureAvoirPFdao.
	 * listeDetailFactureAvoirByFacture(factureAvoirPF.getId());
	 * 
	 * int i=0; BigDecimal montanttotal=BigDecimal.ZERO; BigDecimal
	 * sommequantite=BigDecimal.ZERO; BigDecimal montanttotalHT=BigDecimal.ZERO;
	 * BigDecimal montanttotalTVA=BigDecimal.ZERO;
	 * while(i<listDetailFactureAvoirPF.size()) { StockPF
	 * stockpf=stockpfDAO.findStockByMagasinPFBySousFamille(listDetailFactureAvoirPF
	 * .get(i).getArticle().getId(),factureAvoirPF.getMagasin().getId(),
	 * listDetailFactureAvoirPF.get(i).getSousFamille().getId());
	 * listStockPF.add(stockpf);
	 * 
	 * if(listDetailFactureAvoirPF.get(i).getArticle().getCentreCout2()!=null) {
	 * if(listDetailFactureAvoirPF.get(i).getArticle().getCentreCout2().equals(
	 * Constantes.ARTICLE_PACKET)) { articleboisson=true; }
	 * 
	 * }
	 * 
	 * 
	 * if(articleboisson==true) { DetailFactureAvoirPF
	 * detailFactureAvoirpf=listDetailFactureAvoirPF.get(i); montanttotal=
	 * montanttotal.add(detailFactureAvoirpf.getMontantTTC().divide(
	 * detailFactureAvoirpf.getArticle().getConditionnement(),RoundingMode.HALF_UP))
	 * ; sommequantite= sommequantite.add(detailFactureAvoirpf.getQuantite().divide(
	 * detailFactureAvoirpf.getArticle().getConditionnement(),RoundingMode.HALF_UP))
	 * ;
	 * montanttotalHT=montanttotalHT.add(detailFactureAvoirpf.getMontantHT().divide(
	 * detailFactureAvoirpf.getArticle().getConditionnement(),RoundingMode.HALF_UP))
	 * ; montanttotalTVA=montanttotalTVA.add(detailFactureAvoirpf.getMontantTVA().
	 * divide(detailFactureAvoirpf.getArticle().getConditionnement(),RoundingMode.
	 * HALF_UP)); }else { DetailFactureAvoirPF
	 * detailFactureAvoirpf=listDetailFactureAvoirPF.get(i); montanttotal=
	 * montanttotal.add(detailFactureAvoirpf.getMontantTTC()); sommequantite=
	 * sommequantite.add(detailFactureAvoirpf.getQuantite());
	 * montanttotalHT=montanttotalHT.add(detailFactureAvoirpf.getMontantHT());
	 * montanttotalTVA=montanttotalTVA.add(detailFactureAvoirpf.getMontantTVA());
	 * 
	 * }
	 * 
	 * i++; } txttotalmontantTTC.setText(montanttotal+"");
	 * txttotalquantite.setText(sommequantite+"");
	 * txttotalmontantHT.setText(montanttotalHT+"");
	 * txttotalmontantTVA.setText(montanttotalTVA+"");
	 * 
	 * 
	 * 
	 * afficher_tableDetailFactureAchat(listDetailFactureAvoirPF); initialiser();
	 * 
	 * }
	 * 
	 * }
	 * 
	 * } });
	 * 
	 * 
	 * scrollPane_1.setViewportView(table); table.setColumnControlVisible(true);
	 * table.setColumnSelectionAllowed(true); table.setModel(new DefaultTableModel(
	 * new Object[][] { }, new String[] { "Num Facture", "Date Facture", "Etat",
	 * "Fournisseur", "Depot", "Magasin", "Montant TTC" } ));
	 * table.getColumnModel().getColumn(0).setPreferredWidth(121);
	 * table.getColumnModel().getColumn(1).setPreferredWidth(106);
	 * table.getColumnModel().getColumn(2).setPreferredWidth(111);
	 * table.getColumnModel().getColumn(3).setPreferredWidth(110);
	 * table.getColumnModel().getColumn(4).setPreferredWidth(114);
	 * table.getColumnModel().getColumn(5).setPreferredWidth(136);
	 * table.setShowVerticalLines(false); table.setSelectionBackground(new Color(51,
	 * 204, 255)); table.setRowHeightEnabled(true); table.setRowHeight(20);
	 * table.setGridColor(Color.BLUE); table.setForeground(Color.BLACK);
	 * table.setBackground(Color.WHITE); table.setAutoCreateRowSorter(true); //Group
	 * the radio buttons. ButtonGroup group = new ButtonGroup();
	 * 
	 * JButton btnAfficher = new JButton("Consulter");
	 * btnAfficher.addActionListener(new ActionListener() { public void
	 * actionPerformed(ActionEvent arg0) {
	 * 
	 * detailFactureAvoirPFdao.ViderSession();
	 * if(comboparmagasin.getSelectedItem().equals("")) {
	 * JOptionPane.showMessageDialog(null,
	 * "Veuillez choisir le depot SVP ","Erreur",JOptionPane.ERROR_MESSAGE); return;
	 * } if(comboparfournisseur.getSelectedItem().equals("")
	 * 
	 * && pardateChooser.getDate()==null &&
	 * comboparmagasin.getSelectedItem().equals("")) {
	 * 
	 * initialiser(); initialiserFacture(); InitialiseTableau();
	 * InitialiseTableauFacture();
	 * 
	 * }else {
	 * 
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * } }); btnAfficher.setFont(new Font("Tahoma", Font.PLAIN, 11));
	 * btnAfficher.setBounds(581, 119, 107, 24); btnAfficher.setIcon(imgChercher);
	 * add(btnAfficher);
	 * 
	 * JLayeredPane layeredPane_2 = new JLayeredPane(); layeredPane_2.setBorder(new
	 * EtchedBorder(EtchedBorder.LOWERED, null, null)); layeredPane_2.setBounds(10,
	 * 57, 1458, 51); add(layeredPane_2);
	 * 
	 * JLabel lblDateFacture = new JLabel("Ann\u00E9e Report:");
	 * lblDateFacture.setBounds(212, 12, 97, 24); layeredPane_2.add(lblDateFacture);
	 * lblDateFacture.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 12));
	 * 
	 * pardateChooser = new JDateChooser(); pardateChooser.setBounds(319, 11, 151,
	 * 26); layeredPane_2.add(pardateChooser);
	 * pardateChooser.addPropertyChangeListener(new PropertyChangeListener() {
	 * public void propertyChange(PropertyChangeEvent arg0) {} });
	 * pardateChooser.addKeyListener(new KeyAdapter() {
	 * 
	 * @Override public void keyPressed(KeyEvent e) {
	 * 
	 * 
	 * 
	 * } }); pardateChooser.setLocale(Locale.FRANCE);
	 * pardateChooser.setDateFormatString("yyyy");
	 * 
	 * comboparmagasin = new JComboBox(); comboparmagasin.setBounds(542, 13, 200,
	 * 24); layeredPane_2.add(comboparmagasin);
	 * comboparmagasin.addActionListener(new ActionListener() { public void
	 * actionPerformed(ActionEvent arg0) {} });
	 * comboparmagasin.setSelectedIndex(-1);
	 * 
	 * JLabel lblDepot = new JLabel("Magasin  :"); lblDepot.setBounds(480, 12, 97,
	 * 24); layeredPane_2.add(lblDepot); lblDepot.setFont(new Font("Verdana",
	 * Font.BOLD | Font.ITALIC, 12));
	 * 
	 * 
	 * 
	 * JLabel label_9 = new JLabel("Famille Article :"); label_9.setFont(new
	 * Font("Tahoma", Font.PLAIN, 12)); label_9.setBounds(0, 13, 87, 24);
	 * layeredPane.add(label_9);
	 * 
	 * combofamille = new JComboBox(); combofamille.addItemListener(new
	 * ItemListener() { public void itemStateChanged(ItemEvent e) {
	 * 
	 * 
	 * 
	 * if(e.getStateChange() == ItemEvent.SELECTED) {
	 * combosousfamille.removeAllItems(); combosousfamille.addItem("");
	 * FamilleArticlePF famille=mapfamille.get(combofamille.getSelectedItem());
	 * if(famille!=null) {
	 * 
	 * listSousFamilleArticle=sousFamilleArticleDAO.
	 * listeSousFamillePFByFamilleArticlePF(famille.getId()); for(int
	 * i=0;i<listSousFamilleArticle.size();i++) { SousFamilleArticlePF
	 * sousfamille=listSousFamilleArticle.get(i);
	 * combosousfamille.addItem(sousfamille.getLiblle());
	 * mapsousfamille.put(sousfamille.getLiblle(), sousfamille);
	 * 
	 * }
	 * 
	 * }
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * } }); combofamille.setSelectedIndex(-1); combofamille.setBounds(82, 14, 167,
	 * 24); layeredPane.add(combofamille);
	 * 
	 * JLabel label_10 = new JLabel("Sous Famille :"); label_10.setFont(new
	 * Font("Tahoma", Font.PLAIN, 12)); label_10.setBounds(259, 13, 87, 24);
	 * layeredPane.add(label_10);
	 * 
	 * combosousfamille = new JComboBox(); combosousfamille.addItemListener(new
	 * ItemListener() { public void itemStateChanged(ItemEvent e) {
	 * 
	 * 
	 * if(e.getStateChange() == ItemEvent.SELECTED) {
	 * 
	 * SousFamilleArticlePF
	 * sousfamille=mapsousfamille.get(combosousfamille.getSelectedItem()); Magasin
	 * magasin=mapMagasin.get(combomagasin.getSelectedItem()); if(magasin!=null) {
	 * 
	 * if(sousfamille!=null) { comboArticle.removeAllItems();
	 * comboArticle.addItem("");
	 * listArticleStockPF=stockpfDAO.findStockArticleByMagasinPFBySousFamille(
	 * magasin.getId(), sousfamille.getId()); for(int
	 * i=0;i<listArticleStockPF.size();i++) { Articles article=
	 * listArticleStockPF.get(i).getArticles();
	 * comboArticle.addItem(article.getLiblle());
	 * mapArticle.put(article.getLiblle(), article);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * }
	 * 
	 * 
	 * }
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * } }); combosousfamille.setSelectedIndex(-1); combosousfamille.setBounds(341,
	 * 14, 167, 24); layeredPane.add(combosousfamille);
	 * 
	 * int p=0; combofamille.addItem(""); while(p<listFamilleArticle.size()) {
	 * 
	 * FamilleArticlePF famillearticle=listFamilleArticle.get(p);
	 * combofamille.addItem(famillearticle.getLiblle());
	 * mapfamille.put(famillearticle.getLiblle(), famillearticle); p++; }
	 * 
	 * 
	 * Depot Depot=depotdao.findByCode(utilisateur.getCodeDepot());
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * btninitialiser = new JButton("Initialiser");
	 * btninitialiser.addActionListener(new ActionListener() { public void
	 * actionPerformed(ActionEvent arg0) {
	 * 
	 * initialiser(); } }); btninitialiser.setFont(new Font("Tahoma", Font.PLAIN,
	 * 11)); btninitialiser.setBounds(625, 627, 106, 23); add(btninitialiser);
	 * 
	 * JButton button = new JButton("Initialiser"); button.addActionListener(new
	 * ActionListener() { public void actionPerformed(ActionEvent arg0) {
	 * 
	 * pardateChooser.setCalendar(null); comboparmagasin.setSelectedItem("");
	 * 
	 * 
	 * } }); button.setFont(new Font("Tahoma", Font.PLAIN, 11));
	 * button.setBounds(711, 120, 106, 23); add(button);
	 * 
	 * if(utilisateur.getLogin().equals("admin")) { listDepot=depotdao.findAll();
	 * int k=0; comboparmagasin.addItem(""); while (k<listDepot.size()) { Depot
	 * depot=listDepot.get(k); Magasin
	 * magasin=depotdao.MagasinByTypeMagasinStkAttente(MAGASIN_CODE_TYPE_MP_ATT);
	 * if(magasin!=null) { if(depot.getId()!=magasin.getDepot().getId()) {
	 * comboparmagasin.addItem(depot.getLibelle());
	 * 
	 * mapparDepot.put(depot.getLibelle(), depot);
	 * 
	 * 
	 * 
	 * } }else { comboparmagasin.addItem(depot.getLibelle());
	 * 
	 * mapparDepot.put(depot.getLibelle(), depot);
	 * 
	 * } k++;
	 * 
	 * }
	 * 
	 * }else { int i=0; Depot depot=depotdao.findByCode(utilisateur.getCodeDepot());
	 * if(depot!=null) {
	 * listMagasin=depotdao.listeMagasinByTypeMagasinDepot(depot.getId(),Constantes.
	 * MAGASIN_CODE_TYPE_PF); if(listMagasin.size()!=0) {
	 * comboparmagasin.removeAllItems(); comboparmagasin.addItem("");
	 * while(i<listMagasin.size()) { Magasin magasin=listMagasin.get(i);
	 * comboparmagasin.addItem(magasin.getLibelle());
	 * mapMagasin.put(magasin.getLibelle(), magasin); i++; } }else {
	 * comboparmagasin.removeAllItems();
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * }else { comboparmagasin.removeAllItems();
	 * 
	 * }
	 * 
	 * } }
	 * 
	 * 
	 * comboArticle.addItem("");
	 * 
	 * JLabel label_13 = new JLabel("Quantite :"); label_13.setBounds(10, 55, 72,
	 * 26); layeredPane.add(label_13);
	 * 
	 * txtquantite = new JTextField(); txtquantite.setColumns(10);
	 * txtquantite.setBounds(68, 55, 93, 26); layeredPane.add(txtquantite);
	 * listArticleStockPF=articleDAO.findAll(); for(int
	 * j=0;j<listArticleStockPF.size();j++) { Articles article=
	 * listArticleStockPF.get(j); comboArticle.addItem(article.getLiblle());
	 * mapArticle.put(article.getLiblle(), article);
	 * mapCodeArticle.put(article.getCodeArticle(), article); }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * void initialiserFacture() { txtnumfacture.setText("");
	 * dateChooserfacture.setCalendar(null); combodepot.setSelectedIndex(-1);
	 * combomagasin.removeAllItems(); combomagasin.setSelectedIndex(-1);
	 * comboparfournisseur.setSelectedItem("");
	 * 
	 * }
	 * 
	 * void initialiser() { txtcodearticle.setText("");
	 * comboArticle.setSelectedIndex(-1); txtPrix.setText("");
	 * txtquantitepacket.setText(""); btnAjouter.setEnabled(true);
	 * 
	 * combofamille.setSelectedItem(""); combosousfamille.removeAllItems();
	 * 
	 * 
	 * 
	 * }
	 * 
	 * void initialiserInfofactureEt() { txttotalmontantTTC.setText("");
	 * txttotalquantite.setText(""); txttotalmontantHT.setText("");
	 * txttotalmontantTVA.setText(""); comboFournisseur.setSelectedIndex(-1);
	 * txtnumfacture.setText(""); combodepot.setSelectedItem("");
	 * combomagasin.setSelectedIndex(-1);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * void InitialiseTableau() { modeleArticle =new DefaultTableModel( new
	 * Object[][] { }, new String[] { "Famille","Sous Famille","Article",
	 * "Prix Unitaire", "Quantite","Reduction", "Montant HT", "Montant TVA",
	 * "Montant TTC" } ) { boolean[] columnEditables = new boolean[] {
	 * false,false,false,false,false,false }; public boolean isCellEditable(int row,
	 * int column) { return columnEditables[column]; } };
	 * tableArticle.setModel(modeleArticle);
	 * tableArticle.getColumnModel().getColumn(0).setPreferredWidth(198);
	 * tableArticle.getColumnModel().getColumn(1).setPreferredWidth(87);
	 * tableArticle.getColumnModel().getColumn(2).setPreferredWidth(94);
	 * 
	 * initialiserInfofactureEt();
	 * 
	 * 
	 * }
	 * 
	 * 
	 * void InitialiseTableauFacture() { modelefacture =new DefaultTableModel( new
	 * Object[][] { }, new String[] { "Num Facture", "Date Facture", "Etat",
	 * "Fournisseur", "Depot", "Magasin", "Montant TTC" } ) { boolean[]
	 * columnEditables = new boolean[] { false,false,false,false,false,false,false
	 * }; public boolean isCellEditable(int row, int column) { return
	 * columnEditables[column]; } }; table.setModel(modelefacture);
	 * table.getColumnModel().getColumn(0).setPreferredWidth(121);
	 * table.getColumnModel().getColumn(1).setPreferredWidth(106);
	 * table.getColumnModel().getColumn(2).setPreferredWidth(111);
	 * table.getColumnModel().getColumn(3).setPreferredWidth(110);
	 * table.getColumnModel().getColumn(4).setPreferredWidth(114);
	 * table.getColumnModel().getColumn(5).setPreferredWidth(136);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * 
	 * void afficher_tableDetailFactureAchat(List<DetailFactureAvoirPF>
	 * listDetailFacture) { modeleArticle =new DefaultTableModel( new Object[][] {
	 * }, new String[] { "Famille","Sous Famille","Article", "Prix Unitaire",
	 * "Quantite","Reduction", "Montant HT", "Montant TVA", "Montant TTC" } ) {
	 * boolean[] columnEditables = new boolean[] {
	 * false,false,false,false,false,false,false,false,false }; public boolean
	 * isCellEditable(int row, int column) { return columnEditables[column]; } };
	 * tableArticle.setModel(modeleArticle); int i=0;
	 * 
	 * while(i<listDetailFacture.size()) { boolean existe=false;
	 * DetailFactureAvoirPF detailfactureAvoirPF=listDetailFacture.get(i); Articles
	 * article=detailfactureAvoirPF.getArticle();
	 * 
	 * if(article.getCentreCout2()!=null) {
	 * if(article.getCentreCout2().equals(Constantes.ARTICLE_PACKET)) { existe=true;
	 * } }
	 * 
	 * if(existe==true) { Object
	 * []ligne={detailfactureAvoirPF.getSousFamille().getFamileArticlePF().getLiblle
	 * (),detailfactureAvoirPF.getSousFamille().getLiblle(),detailfactureAvoirPF.
	 * getArticle().getLiblle(),detailfactureAvoirPF.getPrixUnitaire(),
	 * detailfactureAvoirPF.getQuantite().divide(article.getConditionnement(),
	 * RoundingMode.HALF_UP),detailfactureAvoirPF.getReduction().divide(article.
	 * getConditionnement(),RoundingMode.HALF_UP),
	 * detailfactureAvoirPF.getMontantHT().divide(article.getConditionnement(),
	 * RoundingMode.HALF_UP),detailfactureAvoirPF.getMontantTVA().divide(article.
	 * getConditionnement(),RoundingMode.HALF_UP),
	 * detailfactureAvoirPF.getMontantTTC().divide(article.getConditionnement(),
	 * RoundingMode.HALF_UP)};
	 * 
	 * modeleArticle.addRow(ligne); }else { Object
	 * []ligne={detailfactureAvoirPF.getSousFamille().getFamileArticlePF().getLiblle
	 * (),detailfactureAvoirPF.getSousFamille().getLiblle(),detailfactureAvoirPF.
	 * getArticle().getLiblle(),detailfactureAvoirPF.getPrixUnitaire(),
	 * detailfactureAvoirPF.getQuantite(),detailfactureAvoirPF.getReduction(),
	 * detailfactureAvoirPF.getMontantHT(),detailfactureAvoirPF.getMontantTVA(),
	 * detailfactureAvoirPF.getMontantTTC()};
	 * 
	 * modeleArticle.addRow(ligne); }
	 * 
	 * i++; } }
	 * 
	 * 
	 * void afficher_tableFactureAchat(List<FactureAvoirPF> listFacture) {
	 * modelefacture =new DefaultTableModel( new Object[][] { }, new String[] {
	 * "Num Facture", "Date Facture", "Etat", "Fournisseur", "Depot", "Magasin",
	 * "Montant TTC" } ) { boolean[] columnEditables = new boolean[] {
	 * false,false,false,false,false,false,false }; public boolean
	 * isCellEditable(int row, int column) { return columnEditables[column]; } };
	 * table.setModel(modelefacture); int i=0;
	 * 
	 * while(i<listFacture.size()) { FactureAvoirPF
	 * factureAvoirPF=listFacture.get(i);
	 * 
	 * Date datefacture=factureAvoirPF.getDateFacture();
	 * 
	 * Object
	 * []ligne={factureAvoirPF.getNumFacture(),datefacture,factureAvoirPF.getEtat(),
	 * factureAvoirPF.getFournisseur().getNom(),factureAvoirPF.getDepot().getLibelle
	 * (),factureAvoirPF.getMagasin().getLibelle(),factureAvoirPF.getMontantTTC()};
	 * 
	 * modelefacture.addRow(ligne); i++; } }
	 * 
	 * 
	 * 
	 */}
