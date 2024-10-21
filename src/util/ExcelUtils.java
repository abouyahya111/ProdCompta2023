package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.text.DefaultEditorKit;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.util.StringUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import dao.daoImplManager.ClientDAOImpl;
import dao.daoImplManager.DetailFactureServiceProductionDAOImpl;
import dao.daoImplManager.FactureServiceProductionDAOImpl;
import dao.daoManager.ClientDAO;
import dao.daoManager.DetailFactureServiceProductionDAO;
import dao.daoManager.FactureServiceProductionDAO;
import dao.entity.Client;
import dao.entity.Depot;
import dao.entity.DetailFactureServiceProduction;
import dao.entity.FactureServiceProduction;
import dao.entity.Magasin;




public class ExcelUtils {


	private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell CellArticle;
    private static XSSFCell CellMontantGros;
    private static XSSFCell CellMontantDetail;
    private static XSSFCell CellQuantiteGros;
    private static XSSFCell CellQuantiteDetail;
    private static XSSFRow Row;
	
	private static  ClientDAO clientDAO;
	private static FactureServiceProductionDAO factureserviceproductionDAO;
	private static DetailFactureServiceProductionDAO detailfactureserviceproductionDAO;

	
	
    public static void setExcelFile(File Path , Magasin magasin ) throws Exception  {              

   try {
	   
	   clientDAO=new ClientDAOImpl();
	   factureserviceproductionDAO=new FactureServiceProductionDAOImpl();
    	detailfactureserviceproductionDAO=new DetailFactureServiceProductionDAOImpl();
		
       FileInputStream ExcelFile = new FileInputStream(Path);
    
   	
	
       
      
      
      
       
       ExcelWBook = new XSSFWorkbook(ExcelFile);
			
			
				ExcelWSheet = ExcelWBook.getSheet("service");
				 
			
				
///////////////////////////////////////////////////////////////// Comparer les Articles et verifier les cellules si il sont vide //////////////////////////////////////////////////////////////////
				
				   Iterator<org.apache.poi.ss.usermodel.Row> rowIt = ExcelWSheet.iterator();
				   Client client=clientDAO.findClientByCodeClient("TAN_C1339");

				   for(int k=43;k<208;k++)
				   {
				   
					///////////////////////////////////
						
						FactureServiceProduction factureServiceProduction=new FactureServiceProduction();
						
						Date date1=ExcelWSheet.getRow(k).getCell(0).getDateCellValue();  
						
						factureServiceProduction.setDateFacture(date1);
						factureServiceProduction.setNumFacture(ExcelWSheet.getRow(k).getCell(1).toString());
						factureServiceProduction.setClient(client);
						factureServiceProduction.setEtat(Constantes.ETAT_REGLE);
						factureServiceProduction.setDepot(magasin.getDepot());
						//factureServiceProduction.setMagasin(magasin);
						factureServiceProduction.setMontantHT(new BigDecimal(ExcelWSheet.getRow(k).getCell(6).getRawValue()));
						factureServiceProduction.setMontantTVA(BigDecimal.ZERO);
						factureServiceProduction.setMontantTTC(new BigDecimal(ExcelWSheet.getRow(k).getCell(6).getRawValue()));
						factureServiceProduction.setType(Constantes.TRANSFERE_BL_FACTURE);
						factureserviceproductionDAO.add(factureServiceProduction);
							
						DetailFactureServiceProduction detailfactureserviceproduction=new DetailFactureServiceProduction();
						
						detailfactureserviceproduction.setArticle(ExcelWSheet.getRow(k).getCell(3).toString());
						detailfactureserviceproduction.setFactureService(factureServiceProduction);
						detailfactureserviceproduction.setMontantHT(new BigDecimal(ExcelWSheet.getRow(k).getCell(6).getRawValue()));
						detailfactureserviceproduction.setPrix(new BigDecimal(ExcelWSheet.getRow(k).getCell(5).getRawValue()));
						detailfactureserviceproduction.setQuantite(new BigDecimal(ExcelWSheet.getRow(k).getCell(4).getRawValue()));
						detailfactureserviceproductionDAO.add(detailfactureserviceproduction);
						
						//////////////////////////////////
						
				   }
				   
				   JOptionPane.showMessageDialog(null, "Insertion Valider !!!");
				   
	      
   } catch (Exception e) {
       throw (e);
   }

}

  
    
    
public static void setCellData(String Result, int RowNum, int ColNum, File Path) throws Exception {

		try {
			/*
			 * Row = ExcelWSheet.createRow(RowNum - 1); Cell = Row.createCell(ColNum - 1);
			 * Cell.setCellValue(Result); FileOutputStream fileOut = new
			 * FileOutputStream(Path); ExcelWBook.write(fileOut); fileOut.flush();
			 * fileOut.close();
			 */} catch (Exception e) {

       throw (e);

   }

}

public static boolean isNumeric(final String str) {

    if (str == null || str.length() == 0) {
        return false;
    }

    try {

       new BigDecimal(str);
        return true;

    } catch (NumberFormatException e) {
        return false;
    }

}






}
