package util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;
import javax.swing.table.TableModel;

import FactureAchatMP.MoyenStockMP;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import dao.entity.ChargeProduction;
import dao.entity.ClientPF;
import dao.entity.CoutMP;
import dao.entity.DetailCompteClient;
import dao.entity.DetailCompteMagasinier;
import dao.entity.DetailFactureAutresVente;
import dao.entity.DetailFactureAvoirClientPF;
import dao.entity.DetailFactureAvoirPF;
import dao.entity.DetailFacturePF;
import dao.entity.DetailFacturePFParArticle;
import dao.entity.DetailFacturePFParFamille;
import dao.entity.DetailFactureProduction;
import dao.entity.DetailFactureServiceProduction;
import dao.entity.DetailFactureServiceTransport;
import dao.entity.DetailFactureVenteMP;
import dao.entity.DetailHistoriqueVenteVendeur;
import dao.entity.DetailMouvementStock;
import dao.entity.DetailTransferProduitFini;
import dao.entity.DetailTransferStockMP;
import dao.entity.Employe;
import dao.entity.EtatAvoirFinancePF;
import dao.entity.EtatDetailFactureAchat;
import dao.entity.EtatFactureAchatPF;
import dao.entity.EtatFacturePF;
import dao.entity.EtatInitialParSousCtaegorieMP;
import dao.entity.EtatMConsommable;
import dao.entity.EtatMargeArticles;
import dao.entity.EtatPrixMoyen;
import dao.entity.EtatPrixMoyenMP;
import dao.entity.EtatStockMP;
import dao.entity.EtatStockPF;
import dao.entity.EtatValeurSystem;
import dao.entity.EtatVentePFParClientPF;
import dao.entity.EtatVentePFParFamille;
import dao.entity.EtatVenteParArticle;
import dao.entity.EtatVenteParFamilleArticle;
import dao.entity.FacturePF;
import dao.entity.FicheEmploye;
import dao.entity.FicheEmployeGlobale;
import dao.entity.MargeAvantProductionCategorieMP;
import dao.entity.MargeAvantProductionCategoriePF;
import dao.entity.MatierePremier;
import dao.entity.MouvementStockProduitsFini;
import dao.entity.PrixMoyenStockMP;
import dao.entity.PrixMoyenStockPF;
import dao.entity.Production;
import dao.entity.StockMP;
import dao.entity.StockPF;


public class JasperUtils {
	
public static 	void imprimerListeEmploye( List<Employe> listEmploye, Map parameters,String nom){

		
		InputStream str =JasperUtils.class.getResourceAsStream("/jasper/listeEmploye.jasper");
		
		try {
			//JasperDesign jasperDesign = JRXmlLoader.load(str);
			//JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
			
			JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEmploye));
			//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\EquipeProducion\\ListeEmploye_"+nom+".pdf");
			JasperViewer.viewReport(JASPER_PRINT, false); 
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

public static 	void imprimerFicheEquipeGen( List<Employe> listEmploye, Map parameters,String nom){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/fichePointageEquipeGen.jasper");
	
	try {
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEmploye));
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static void imprimerDetailOF( List<CoutMP> listCoutMP, Map parameters){

	
	
	
	try {
		InputStream str =JasperUtils.class.getResourceAsStream("/jasper/DetailOF.jasper");
		/*JasperDesign jasperDesign = JRXmlLoader.load(str);
		JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);*/
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listCoutMP));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\BonSortieMatierePremiere\\BonSortieMP_"+nom+".pdf");
	//	JasperViewer.viewReport(JASPER_PRINT);
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static void imprimerBonProduction( List<CoutMP> listCoutMP, Map parameters){

	
	
	
	try {
		InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonProductionCompta.jasper");
		/*JasperDesign jasperDesign = JRXmlLoader.load(str);
		JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);*/
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listCoutMP));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\BonSortieMatierePremiere\\BonSortieMP_"+nom+".pdf");
	//	JasperViewer.viewReport(JASPER_PRINT);
		JasperViewer.viewReport(JASPER_PRINT, false); 
		//JasperPrintManager.printReport(JASPER_PRINT, false);
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public static void imprimerBonProductionPourcentageEnVracConsomme( List<CoutMP> listCoutMP, Map parameters){

	
	
	
	try {
		InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonProductionComptaPourcentageEnVracConsomme.jasper");
		/*JasperDesign jasperDesign = JRXmlLoader.load(str);
		JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);*/
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listCoutMP));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\BonSortieMatierePremiere\\BonSortieMP_"+nom+".pdf");
	//	JasperViewer.viewReport(JASPER_PRINT);
		JasperViewer.viewReport(JASPER_PRINT, false); 
		//JasperPrintManager.printReport(JASPER_PRINT, false);
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public static void imprimerBonProductionPourcentageEnVracConsommeSansDechet( List<CoutMP> listCoutMP, Map parameters){

	
	
	
	try {
		InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonProductionComptaPourcentageEnVracConsommeSansDechet.jasper");
		/*JasperDesign jasperDesign = JRXmlLoader.load(str);
		JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);*/
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listCoutMP));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\BonSortieMatierePremiere\\BonSortieMP_"+nom+".pdf");
	//	JasperViewer.viewReport(JASPER_PRINT);
		JasperViewer.viewReport(JASPER_PRINT, false); 
		//JasperPrintManager.printReport(JASPER_PRINT, false);
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static void imprimerBonProductionParAnnee( List<CoutMP> listCoutMP, Map parameters){

	
	
	
	try {
		InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonProductionComptaParAnnee.jasper");
		/*JasperDesign jasperDesign = JRXmlLoader.load(str);
		JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);*/
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listCoutMP));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\BonSortieMatierePremiere\\BonSortieMP_"+nom+".pdf");
	//	JasperViewer.viewReport(JASPER_PRINT);
		JasperViewer.viewReport(JASPER_PRINT, false); 
		//JasperPrintManager.printReport(JASPER_PRINT, false);
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public static void imprimerBonProductionParAnneeSansDechet( List<CoutMP> listCoutMP, Map parameters){

	
	
	
	try {
		InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonProductionComptaParAnneeSansDechet.jasper");
		/*JasperDesign jasperDesign = JRXmlLoader.load(str);
		JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);*/
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listCoutMP));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\BonSortieMatierePremiere\\BonSortieMP_"+nom+".pdf");
	//	JasperViewer.viewReport(JASPER_PRINT);
		JasperViewer.viewReport(JASPER_PRINT, false); 
		//JasperPrintManager.printReport(JASPER_PRINT, false);
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public static void imprimerBonProductionSansDechet( List<CoutMP> listCoutMP, Map parameters){

	
	
	
	try {
		InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonProductionComptaSansDechet.jasper");
		/*JasperDesign jasperDesign = JRXmlLoader.load(str);
		JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);*/
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listCoutMP));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\BonSortieMatierePremiere\\BonSortieMP_"+nom+".pdf");
	//	JasperViewer.viewReport(JASPER_PRINT);
		JasperViewer.viewReport(JASPER_PRINT, false); 
		//JasperPrintManager.printReport(JASPER_PRINT, false);
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public static 	void imprimerListeMatierePremiere( List<MatierePremier> listMatierePremiere, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/listeMatierePremiere.jasper");
	
	try {
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listMatierePremiere));
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static 	void imprimerCompteMagasinier( List<DetailCompteMagasinier> listDetailCompteMagasinier, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/ConsulterCompteMagasinier.jasper");
	
	try {
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailCompteMagasinier));
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}



public static 	void imprimerFicheEmployeGlobale( List<FicheEmployeGlobale> listFicheEmployeGlobale, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/FicheEmployeGlobale.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listFicheEmployeGlobale));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}

public static 	void imprimerCoutProduction( List<ChargeProduction> listchargeproduction, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/CoutProduction.jasper");
	
	try {
		//JasperDesign jasperDesign = JRXmlLoader.load(str);
		//JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listchargeproduction));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\BonSortieMatierePremiere\\BonSortieMP_"+nom+".pdf");
	//	JasperViewer.viewReport(JASPER_PRINT);
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public static 	void imprimerFicheEquipeGen2( List<Employe> listEmploye, Map parameters,String nom){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/fichePointageEquipeGen2.jasper");
	
	try {
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEmploye));
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public static 	void imprimerDetailOrdreFabrication( List<CoutMP> listCoutMP, Map parameters,String nom){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/detailOrdreFabrication1.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listCoutMP));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\OrdreFabrication\\DetailOrdreFabrication"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}



public static 	void imprimerBonSortieMatierePremiere( List<CoutMP> listCoutMP, Map parameters,String nom){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonSortieMatierePremiere.jasper");
	
	try {
		//JasperDesign jasperDesign = JRXmlLoader.load(str);
		//JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listCoutMP));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\BonSortieMatierePremiere\\BonSortieMP_"+nom+".pdf");
	//	JasperViewer.viewReport(JASPER_PRINT);
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static 	void imprimerValiderBonSortieMatierePremiere( List<CoutMP> listCoutMP, Map parameters,String nom){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/ValiderBonSortieMatierePremiere.jasper");
	
	try {
		
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listCoutMP));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		
		e.printStackTrace();
	}
}

public static 	void imprimerBonSortieMPChargeSupp( List<CoutMP> listCoutMP, Map parameters,String nom){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonSortieMPChargeSupp.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listCoutMP));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\BonSortieChargeSupp\\BonSortieMPChargeSupp_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static 	void imprimerFicheCalculeMatierePremiere( List<CoutMP> listCoutMP, Map parameters,String nom){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/FicheCalculeMPResteDechet.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listCoutMP));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheDechetMatierePremiere\\FicheCalculeMP_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}

public static 	void imprimerFicheEmploye( List<FicheEmploye> listFicheEmploye, Map parameters,String nom){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/FicheEmploye.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listFicheEmploye));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}

public static 	void imprimerFacturePF( List<DetailFacturePF> listDetailFacturePF, Map parameters , boolean apercu) throws PrintException, IOException{

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/FactureVente.jasper");
	
	try {
		
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFacturePF));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		if(apercu==true)
		{
			JasperViewer.viewReport(JASPER_PRINT, false); 
		}
		
	 JasperPrintManager.printReport(JASPER_PRINT, false);
		
		
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerBonDeLivraison( List<DetailFacturePF> listDetailFacturePF, Map parameters , boolean apercu) throws PrintException, IOException{

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonDeLivraison.jasper");
	
	try {
		
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFacturePF));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		if(apercu==true)
		{
			JasperViewer.viewReport(JASPER_PRINT, false); 
		}
		
	 JasperPrintManager.printReport(JASPER_PRINT, false);
		
		
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}

public static 	void imprimerBonDeLivraisonHistorique( List<DetailHistoriqueVenteVendeur> listDetailFacturePF, Map parameters , boolean apercu) throws PrintException, IOException{

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonDeLivraisonHistorique.jasper");
	
	try {
		
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFacturePF));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		if(apercu==true)
		{
			JasperViewer.viewReport(JASPER_PRINT, false); 
		}
		
	 JasperPrintManager.printReport(JASPER_PRINT, false);
		
		
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}

public static 	void imprimerFacturePFSansTVA( List<DetailFacturePF> listDetailFacturePF, Map parameters , boolean apercu) throws PrintException, IOException{

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/FactureVenteSansTVA.jasper");
	
	try {
		
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFacturePF));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		if(apercu==true)
		{
			JasperViewer.viewReport(JASPER_PRINT, false); 
		}
		
	 JasperPrintManager.printReport(JASPER_PRINT, false);
		
		
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerFactureAutresVente( List<DetailFactureAutresVente> listDetailFactureAutresvente, Map parameters , boolean apercu) throws PrintException, IOException{

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/FactureAutresVente.jasper");
	
	try {
		
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFactureAutresvente));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		if(apercu==true)
		{
			JasperViewer.viewReport(JASPER_PRINT, false); 
		}
		
	 JasperPrintManager.printReport(JASPER_PRINT, false);
		
		
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerFactureAvoirClientPF( List<DetailFactureAvoirClientPF> listDetailFactureAvoirClientPF, Map parameters , boolean apercu) throws PrintException, IOException{

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/FactureAvoirClientPF.jasper");
	
	try {
		
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFactureAvoirClientPF));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		if(apercu==true)
		{
			JasperViewer.viewReport(JASPER_PRINT, false); 
		}
		
	 JasperPrintManager.printReport(JASPER_PRINT, false);
		
		
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerFactureSrviceTransport( List<DetailFactureServiceTransport> listDetailFactureServiceTransport, Map parameters , boolean apercu) throws PrintException, IOException{

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/FactureServiceTransport.jasper");
	
	try {
		
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFactureServiceTransport));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		if(apercu==true)
		{
			JasperViewer.viewReport(JASPER_PRINT, false); 
		}
		
	 JasperPrintManager.printReport(JASPER_PRINT, false);
		
		
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerHistoriqueVenteVendeur( List<DetailHistoriqueVenteVendeur> listDetailFacturePF, Map parameters , boolean apercu) throws PrintException, IOException{

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/HistoriqueVenteVendeurPF.jasper");
	
	try {
		
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFacturePF));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		if(apercu==true)
		{
			JasperViewer.viewReport(JASPER_PRINT, false); 
		}
		
	// JasperPrintManager.printReport(JASPER_PRINT, false);
		
		
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerHistoriqueVenteVendeurSansTVA( List<DetailHistoriqueVenteVendeur> listDetailFacturePF, Map parameters , boolean apercu) throws PrintException, IOException{

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/HistoriqueVenteVendeurPFSansTVA.jasper");
	
	try {
		
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFacturePF));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		if(apercu==true)
		{
			JasperViewer.viewReport(JASPER_PRINT, false); 
		}
		
	// JasperPrintManager.printReport(JASPER_PRINT, false);
		
		
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void ExporterFacturePFToExcel( List<DetailFacturePF> listDetailFacturePF, Map parameters  ) throws PrintException, IOException{

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/FactureVente.jasper");
	
	try {
		
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFacturePF));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
	
		
	// JasperPrintManager.printReport(JASPER_PRINT, false);
	 
	/////////////////////////////////////////////// Exporter Jasper To Excel ///////////////////////////////////////////////////////// 
		//JasperViewer.viewReport(JASPER_PRINT, true);
	 
		
		
	 try{
		 
		//To generate and open the XLSheet

		 JRXlsxExporter exporterXLS = new JRXlsxExporter();
		 exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, JASPER_PRINT);
		 exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, "FactureVentePF.xlsx");
		 
		 exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
		
		 exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
		 exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
		 exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.FALSE);	
		 
		 exporterXLS.exportReport();
		 
		Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "FactureVentePF.xlsx");
		
		}
		catch(Exception exception)
		{
		exception.printStackTrace();
		}
	 
	 
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
	 
	 
		
		
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}



public static 	void ExporterFacturePFToExcelFactureAutresVente( List<DetailFactureAutresVente> listDetailFactureAutresVente, Map parameters  ) throws PrintException, IOException{

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/FactureAutresVente.jasper");
	
	try {
		
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFactureAutresVente));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
	
		
	// JasperPrintManager.printReport(JASPER_PRINT, false);
	 
	/////////////////////////////////////////////// Exporter Jasper To Excel ///////////////////////////////////////////////////////// 
		//JasperViewer.viewReport(JASPER_PRINT, true);
	 
		
		
	 try{
		 
		//To generate and open the XLSheet

		 JRXlsxExporter exporterXLS = new JRXlsxExporter();
		 exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, JASPER_PRINT);
		 exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, "FactureVenteAutresVente.xlsx");
		 
		 exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
		
		 exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
		 exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
		 exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.FALSE);	
		 
		 exporterXLS.exportReport();
		 
		Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "FactureVenteAutresVente.xlsx");
		
		}
		catch(Exception exception)
		{
		exception.printStackTrace();
		}
	 
	 
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
	 
	 
		
		
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}




public static 	void imprimerFactureService( List<DetailFactureServiceProduction> listDetailFactureServiceProduction, Map parameters , boolean apercu){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/FactureServiceMP.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFactureServiceProduction));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		
		
		if(apercu==true)
		{
			JasperViewer.viewReport(JASPER_PRINT, false); 
		}
		
	 JasperPrintManager.printReport(JASPER_PRINT, false);
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerFactureServiceSansTVA( List<DetailFactureServiceProduction> listDetailFactureServiceProduction, Map parameters, boolean apercu){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/FactureServiceMPSansTVA.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFactureServiceProduction));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		if(apercu==true)
		{
			JasperViewer.viewReport(JASPER_PRINT, false); 
		}
		
	 JasperPrintManager.printReport(JASPER_PRINT, false);
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerFactureServiceToExcel( List<DetailFactureServiceProduction> listDetailFactureServiceProduction, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/FactureServiceMP.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFactureServiceProduction));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		//JasperViewer.viewReport(JASPER_PRINT, false); 
		 try{
			 
				//To generate and open the XLSheet

				 JRXlsxExporter exporterXLS = new JRXlsxExporter();
				 exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, JASPER_PRINT);
				 exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, "FactureService.xlsx");
				 
				 exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
				
				 exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
				 exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
				 exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.FALSE);	
				 
				 exporterXLS.exportReport();
				 
				Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "FactureService.xlsx");
				
				}
				catch(Exception exception)
				{
				exception.printStackTrace();
				}
		
		
		
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}




public static 	void imprimerFactureServiceToExcelSansTVA( List<DetailFactureServiceProduction> listDetailFactureServiceProduction, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/FactureServiceMPSansTVA.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFactureServiceProduction));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		//JasperViewer.viewReport(JASPER_PRINT, false); 
		 try{
			 
				//To generate and open the XLSheet

				 JRXlsxExporter exporterXLS = new JRXlsxExporter();
				 exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, JASPER_PRINT);
				 exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, "FactureService.xlsx");
				 
				 exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
				
				 exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
				 exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
				 exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.FALSE);	
				 
				 exporterXLS.exportReport();
				 
				Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "FactureService.xlsx");
				
				}
				catch(Exception exception)
				{
				exception.printStackTrace();
				}
		
		
		
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}




public static 	void imprimerFactureVenteMP( List<DetailFactureVenteMP> listDetailFactureVenteMP, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/FactureVenteMP.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFactureVenteMP));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerListeClientPF( List<ClientPF> listClientPF, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/ListeClientPF.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listClientPF));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerEtatFacturePF( List<FacturePF> listFacturePF, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatFactureVente.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listFacturePF));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerEtatFacturePFAvecModeRegelement( List<EtatFacturePF> listFacturePF, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatFactureVenteAvecModeReglement.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listFacturePF));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}

public static 	void imprimerEtatChiffreAffaireFacture( List<DetailFacturePF> listDetailFacturePF, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatChiffreAffaireDesArticlesFacture.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFacturePF));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}

public static 	void imprimerEtatFacturesAutresVenteTypeFacture (List<DetailFactureAutresVente> listDetailFacturePF, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatFacturesAutresVenteTypeFacture.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFacturePF));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}

public static 	void imprimerEtatFacturesAutresVenteTypeBL (List<DetailFactureAutresVente> listDetailFacturePF, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatFacturesAutresVenteTypeBL.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFacturePF));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}

public static 	void imprimerEtatFacturesAutresVente (List<DetailFactureAutresVente> listDetailFacturePF, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatFacturesAutresVente.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFacturePF));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerEtatFactureArticle( List<DetailFacturePF> listDetailFacturePF, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatFactureArticle.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFacturePF));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerEtatFactureService( TableModel model, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatFactureService.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRTableModelDataSource(model));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}

public static 	void imprimerEtatFactureServiceAvecSousTautaux( TableModel model, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatFactureServiceDetailAvecSousTautaux.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRTableModelDataSource(model));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}



public static 	void imprimerEtatPrixMoyenArticle( List<EtatPrixMoyen> listEtatprixMoyen, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatPrixMoyenArticle.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatprixMoyen));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerEtatMoyenValeurisationProduction( List<EtatPrixMoyen> listEtatprixMoyen, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatMoyenValeurisationProduction.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatprixMoyen));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerEtatPrixMoyenValeurisationProductionParSousFamille( List<EtatPrixMoyenMP> listEtatprixMoyenMP, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatPrixMoyenValeurisationProductionParSousFamille.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatprixMoyenMP));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerEtatPrixMoyenMP( List<EtatPrixMoyenMP> listEtatprixMoyen, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatPrixMoyenMP.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatprixMoyen));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerMoyenStockMP( List<PrixMoyenStockMP> listMoyenStockMP, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/MoyenStockMP.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listMoyenStockMP));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}
public static 	void imprimerMoyenStockMPParSousFamille( List<PrixMoyenStockMP> listMoyenStockMP, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/MoyenStockMPParSousFamille.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listMoyenStockMP));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}

public static 	void imprimerMoyenStockPF( List<PrixMoyenStockPF> listMoyenStockPF, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/MoyenStockPF.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listMoyenStockPF));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerEtatFactureAchatArticle( List<EtatDetailFactureAchat> listEtatDetailFactureAchatArticle, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatFactureAchatArticle.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatDetailFactureAchatArticle));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}

public static 	void imprimerEtatFactureAvoir( List<DetailFactureAvoirPF> listDetailFactureAvoirPF, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatFactureAvoirPF.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFactureAvoirPF));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}

public static 	void imprimerEtatFactureAvoirClientPF( List<DetailFactureAvoirClientPF> listDetailFactureAvoirPF, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatFactureAvoirClientPF.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFactureAvoirPF));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}

public static 	void imprimerEtatFactureAchatPF( List<EtatFactureAchatPF> listEtatFactureAchatPF, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatFactureAchatPF.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatFactureAchatPF));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerEtatChiffreAffaireAvecTimbre( TableModel model, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatChiffreAffaireAvecTimbre.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRTableModelDataSource(model));
		
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}




public static 	void imprimerEtatVentePF( TableModel model, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatVentePF.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRTableModelDataSource(model));
		
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}

public static 	void imprimerEtatFactureAchatMP( TableModel model, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatAchatMP.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRTableModelDataSource(model));
		
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}



public static 	void imprimerEtatChiffreAffaireBL( List<DetailFacturePF> listDetailFacturePF, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatChiffreAffaireDesArticlesBL.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFacturePF));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerEtatChiffreAffaireParFamille( List<DetailFacturePFParFamille> listDetailFactureParFamille, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatChiffreAffaireParFamille.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFactureParFamille));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}

public static 	void imprimerEtatChiffreAffaireParArticle( List<DetailFacturePFParArticle> listDetailFactureParArticle, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatChiffreAffaireParArticle.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFactureParArticle));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerEtatDetailFacturePFParArticle( List<DetailFacturePF> listDetailFacturePF, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatFactureVenteParArticle.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFacturePF));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerMouvementStockProduitFini( List<MouvementStockProduitsFini> listMouvementStockPF, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/MouvementStockPF.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listMouvementStockPF));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}



public static 	void imprimerMouvementStock( List<DetailMouvementStock> listMouvementStock, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/MouvementStockGlobale.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listMouvementStock));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}



public static 	void imprimerEtatStock( List<EtatStockMP> listEtatStock, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatStockMP.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatStock));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerEtatQuantiteMPTransforme( List<EtatStockMP> listEtatStock, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatQuantiteMPTransformerParMP.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatStock));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerEtatStockInitialMP( List<EtatStockMP> listEtatStockInitialMP, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatStockInitialMP.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatStockInitialMP));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}



public static 	void imprimerEtatStockPF( List<EtatStockPF> listEtatStockPF, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatStockPF.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatStockPF));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerEtatStockInitialPF( List<EtatStockPF> listEtatStockInitialPF, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatStockInitialPF.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatStockInitialPF));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerEtatMargeParArticle( List<EtatMargeArticles> listEtatMargeParArticles, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatMargeParArticles.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatMargeParArticles));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerEtatMargeGlobal( List<EtatMargeArticles> listEtatMargeGlobal, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatMargeGlobal.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatMargeGlobal));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerEtatValeurSystem( List<EtatValeurSystem> listEtatValeurSystem, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatValeurSystem.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatValeurSystem));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}



public static 	void imprimerMargeAvantProduction( List<MargeAvantProductionCategorieMP> listMargeAvantProduction, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/MargeAvantProduction.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listMargeAvantProduction));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}






public static 	void imprimerEtatEstimationStockPF( List<EtatStockPF> listEtatStockPF, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatEstimationStockPF.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatStockPF));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}

public static 	void imprimerEtatAvoirFinancePF( List<EtatAvoirFinancePF> listEtatAvoirFinancePF, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatAvoirFinance.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatAvoirFinancePF));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerEtatMConsommablePF( List<EtatMConsommable> listEtatMConsommablePF, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatMConsommablePF.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatMConsommablePF));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}



public static 	void imprimerEtatVenteArticle( List<EtatVenteParArticle> listEtatVenteArticle, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatVenteArticle.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatVenteArticle));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}



public static 	void imprimerEtatVenteFamilleArticle( List<EtatVenteParFamilleArticle> listEtatVenteFamilleArticle, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatVenteFamilleArticle.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatVenteFamilleArticle));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}

public static 	void imprimerEtatInitalFamilleArticle( List<EtatVenteParFamilleArticle> listEtatVenteFamilleArticle, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatInitialFamilleArticle.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatVenteFamilleArticle));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerEtatPrixMoyenStockInitialparSousCategorie( List<EtatInitialParSousCtaegorieMP> listEtatPrixMoyenStockInitialparSousCategorie, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatPrixMoyenStockMPParSousCategorie.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatPrixMoyenStockInitialparSousCategorie));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}

public static 	void imprimerEtatServiceFamilleArticle( List<EtatVenteParFamilleArticle> listEtatServiceFamilleArticle, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatServiceFamilleArticle.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatServiceFamilleArticle));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerEtatAchatFamilleArticle( List<EtatVenteParFamilleArticle> listEtatAchatFamilleArticle, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatAchatFamilleArticle.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatAchatFamilleArticle));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerEtatVenteParFamille( List<EtatVentePFParFamille> listEtatVenteParFamille, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatVenteParFamille.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatVenteParFamille));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}


public static 	void imprimerEtatVenteParClientPF( List<EtatVentePFParClientPF> listEtatVenteParClientPF, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatVenteParClientPF.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listEtatVenteParClientPF));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}





public static 	void imprimerEtatCompte( TableModel model, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/EtatCompte.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRTableModelDataSource(model));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}





public static 	void imprimerBulletinPaieEmploye( List<FicheEmploye> listFicheEmploye, Map parameters,String nom){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BulltinPaieEmploye.jasper");
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listFicheEmploye));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\BulletinPaieEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}

public static 	void imprimerBonSortieMPDeplace( List<DetailTransferStockMP> listDetailTransferStockMP, Map parameters,String nom){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonSortieMPDeplace.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailTransferStockMP));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static 	void imprimerBonTransfereMP( List<DetailTransferStockMP> listDetailTransferStockMP, Map parameters,String nom){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonTransfereMP.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailTransferStockMP));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static 	void imprimerBonEntrerMP( List<DetailTransferStockMP> listDetailTransferStockMP, Map parameters,String nom){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonEntrerMP.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailTransferStockMP));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static 	void imprimerBonTransfereMPProduitFini( List<DetailTransferStockMP> listDetailTransferStockMP, Map parameters,String nom){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonTransferMPProduitFini.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailTransferStockMP));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public static 	void imprimerBonTransferePFProduitFini( List<DetailTransferProduitFini> listDetailTransferStockPF, Map parameters,String nom){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonTransferPFProduitFini.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailTransferStockPF));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static 	void imprimerBonSortiePF( List<DetailTransferProduitFini> listDetailTransferStockPF, Map parameters,String nom){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/BonSortiePF.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailTransferStockPF));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

/**
 * 
 * @param listDetailTransferStockMP
 * @param parameters
 * @param nom
 * Méthode permet d'imprimer la situation de stock d'un magasin donné.
 */
public static 	void imprimerSitutationStock( List<StockMP> listStockMP, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/situtationStockMP.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listStockMP));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static 	void imprimerSitutationStockPF( List<StockPF> listStockPF, Map parameters){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/situtationStockPF.jasper");
	
	
	try {
	
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listStockPF));
	
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}



/*imprimer facture production */
public static 	void imprimerFacutreProduction( List<DetailFactureProduction> listDetailFactureProduction, Map parameters,String nom){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/factureProduction.jasper");
	
	try {
		//JasperDesign jasperDesign = JRXmlLoader.load(str);
		//JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFactureProduction));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\BonSortieMatierePremiere\\BonSortieMP_"+nom+".pdf");
	//	JasperViewer.viewReport(JASPER_PRINT);
		JasperViewer.viewReport(JASPER_PRINT, false); 
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}



/*####################################################################################*/

public static 	void imprimerInterne( ){

	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/test.jasper");
	CoutMP CoutMP =new CoutMP();
	CoutMP.setPrixUnitaire(new BigDecimal(77777) );
	
	List<CoutMP> listCoutMP=new ArrayList<CoutMP>();
	listCoutMP.add(CoutMP);
	Map parameters = new HashMap();
	parameters.put("date", "TEST");
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listCoutMP));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\BonSortieMPDéplacé\\BonSortieMPDeplace_"+nom+".pdf");
		//JasperViewer.viewReport(JASPER_PRINT);
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static 	void imprimerExterne(List<CoutMP> listCoutMP, Map parameters ){

	
	InputStream str =JasperUtils.class.getResourceAsStream("C:/Edition/jasper/test.jasper");
	
	
	
	try {
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		JasperReport jasperReport = (JasperReport)JRLoader.loadObject("C:/Edition/jasper/BonSortieMatierePremiere.jasper");
		
		
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(jasperReport,parameters,new JRBeanCollectionDataSource(listCoutMP));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\BonSortieMPDéplacé\\BonSortieMPDeplace_"+nom+".pdf");
		//JasperViewer.viewReport(JASPER_PRINT);
		JasperViewer.viewReport(JASPER_PRINT, false); 

	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public static 	void imprimerTestSubReport( List<Employe> listEmploye, Map parameters,String nom){
	
	
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/report1.jrxml");
	InputStream strSubReport =JasperUtils.class.getResourceAsStream("/jasper/address_report_template.jrxml");
	
	try {
		
		String sqlPrint1="SELECT  name , english , maths , history , science from student where name='Thomas'";
        JasperDesign jasperDesign;
        jasperDesign = JRXmlLoader.load("C:/Program Files/TMS/reports/Master.jrxml");
        JasperDesign jasperDesignSub1 = JRXmlLoader.load("C:/Program Files/TMS/reports/Master_subreport1.jrxml");
        JasperDesign jasperDesignSub2 = JRXmlLoader.load("C:/Program Files/TMS/reports/Master_subreport2.jrxml");
       
        JasperReport jasperReport= JasperCompileManager.compileReport(jasperDesign);
        JasperReport jasperReportSub1 = JasperCompileManager.compileReport(jasperDesignSub1);
        JasperReport jasperReportSub2 = JasperCompileManager.compileReport(jasperDesignSub2);
        Map perameters=new HashMap();
      /* JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,perameters,conn);
        JasperViewer.viewReport(jasperPrint,false);
        } catch (JRException ex) {
        Logger.getLogger(Subr.class.getName()).log(Level.SEVERE, null, ex);
        }*/
		
		
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
		JasperDesign jasperDesignSub = JRXmlLoader.load(strSubReport);
		JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		JasperReport JASPER_REP_SUB = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(JASPER_REP,parameters,new JRBeanCollectionDataSource(listEmploye));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\EquipeProducion\\ListeEmploye_"+nom+".pdf");
		JasperViewer.viewReport(JASPER_PRINT);
	} catch (JRException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}


public static 	void ExporterFactureAvoirClientToExcel( List<DetailFactureAvoirClientPF> listDetailFactureAvoirClientPF, Map parameters  ) throws PrintException, IOException{

	 
	InputStream str =JasperUtils.class.getResourceAsStream("/jasper/FactureAvoirClientPF.jasper");
	
	try {
		
	//	JasperDesign jasperDesign = JRXmlLoader.load(str);
	//	JasperReport JASPER_REP = JasperCompileManager.compileReport(jasperDesign);
		
		JasperPrint JASPER_PRINT =JasperFillManager.fillReport(str,parameters,new JRBeanCollectionDataSource(listDetailFactureAvoirClientPF));
		//JasperExportManager.exportReportToPdfFile(JASPER_PRINT, "D:\\Edition\\FicheEmploye\\FicheEmploye_"+nom+".pdf");
	
		
	// JasperPrintManager.printReport(JASPER_PRINT, false);
	 
	/////////////////////////////////////////////// Exporter Jasper To Excel ///////////////////////////////////////////////////////// 
		//JasperViewer.viewReport(JASPER_PRINT, true);
	 
		
		
	 try{
		 
		//To generate and open the XLSheet

		 JRXlsxExporter exporterXLS = new JRXlsxExporter();
		 exporterXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT, JASPER_PRINT);
		 exporterXLS.setParameter(JRXlsExporterParameter.OUTPUT_FILE_NAME, "FactureAvoirClientPF.xlsx");
		 
		 exporterXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
		
		 exporterXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
		 exporterXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
		 exporterXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.FALSE);	
		 
		 exporterXLS.exportReport();
		 
		Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + "FactureAvoirClientPF.xlsx");
		
		}
		catch(Exception exception)
		{
		exception.printStackTrace();
		}
	 
	 
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
	 
	 
		
		
	} catch (JRException e) {
		// TODO Auto-generated catch blockal
		e.printStackTrace();
	}
}

}
