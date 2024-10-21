package util;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.jdesktop.swingx.JXTable;

import dao.entity.EtatMargeArticles;
import groovy.model.DefaultTableModel;
import net.sf.jasperreports.engine.export.oasis.CellStyle;

public class  ExporterTableVersExcel {
	public static Workbook workbook = new HSSFWorkbook();
	public static void tabletoexcel(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);
        int rowCount = 0;

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            rowCount++;
            
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>3)
                    row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	else
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        
        rowCount=rowCount+1;
        Row rowTotal = spreadsheet.createRow(rowCount + 2);
        Cell cellTotalText = rowTotal.createCell(3);
        cellTotalText.setCellValue("Total:");
        
        Cell cellTotalInitial = rowTotal.createCell(6);
        cellTotalInitial.setCellFormula("SUM(G2:G"+rowCount+")");
        
        Cell cellTotalAchat = rowTotal.createCell(9);
        cellTotalAchat.setCellFormula("SUM(J2:J"+rowCount+")");
        
        Cell cellTotalSortie = rowTotal.createCell(12);
        cellTotalSortie.setCellFormula("SUM(M2:M"+rowCount+")"); 
        
        Cell cellTotalDechet = rowTotal.createCell(15);
        cellTotalDechet.setCellFormula("SUM(P2:P"+rowCount+")"); 
        
        Cell cellTotalOffre = rowTotal.createCell(18);
        cellTotalOffre.setCellFormula("SUM(S2:S"+rowCount+")"); 
        
        Cell cellTotalService = rowTotal.createCell(21);
        cellTotalService.setCellFormula("SUM(V2:V"+rowCount+")"); 
        
        Cell cellTotalDechetService = rowTotal.createCell(24);
        cellTotalDechetService.setCellFormula("SUM(Y2:Y"+rowCount+")"); 
        
        Cell cellTotalOffreService = rowTotal.createCell(27);
        cellTotalOffreService.setCellFormula("SUM(AB2:AB"+rowCount+")"); 
        
        Cell cellTotalProduction = rowTotal.createCell(30);
        cellTotalProduction.setCellFormula("SUM(AE2:AE"+rowCount+")"); 
        
        Cell cellTotalVente = rowTotal.createCell(33);
        cellTotalVente.setCellFormula("SUM(AH2:AH"+rowCount+")"); 
        
        Cell cellTotalGratuite= rowTotal.createCell(36);
        cellTotalGratuite.setCellFormula("SUM(AK2:AK"+rowCount+")"); 
        
        Cell cellTotalAvoir = rowTotal.createCell(39);
        cellTotalAvoir.setCellFormula("SUM(AN2:AN"+rowCount+")"); 
        
        Cell cellTotalTransfertMPPF = rowTotal.createCell(42);
        cellTotalTransfertMPPF.setCellFormula("SUM(AQ2:AQ"+rowCount+")"); 
        
        Cell cellTotalFinale= rowTotal.createCell(45);
        cellTotalFinale.setCellFormula("SUM(AT2:AT"+rowCount+")"); 
        
        
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	
	public static void tabletoexcelEtatValeurisationStock(JXTable table , String titre , String titrefeuille,BigDecimal StockInitial, BigDecimal StockInitialEmballage,BigDecimal StockInitialSansEmballage,BigDecimal StockAchat,BigDecimal StockAchatEmballage,BigDecimal StockAchatSansEmballage,BigDecimal ProductionEntrer,BigDecimal productionSortie,BigDecimal Prixservice,BigDecimal CA,BigDecimal StockGratuite,BigDecimal StockGratuiteEmballage,BigDecimal StockGratuiteSansEmballage,BigDecimal StockAvoir,BigDecimal StockAvoirEmballage,BigDecimal StockAvoirSansEmballage,BigDecimal StockFinale,BigDecimal StockFinaleEmballage,BigDecimal StockFinaleSansEmballage)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);
        int rowCount = 0;

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            rowCount++;
            
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>3)
                    row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	else
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        
        rowCount=rowCount+1;
        
        
////////////////////////////////////////////////////////////////////////////////////////////  Initial  //////////////////////////////////////////////////////////////////////////////////////////////////////////        
        Row rowTotalInitial = spreadsheet.createRow(rowCount + 2);
        Cell cellTotalTextInitial = rowTotalInitial.createCell(4);
        cellTotalTextInitial.setCellValue("STOCK INITIAL");
        
        Row rowTotalInitialEmballage = spreadsheet.createRow(rowCount + 3);
        Cell cellTotalTextInitialEmballage = rowTotalInitialEmballage.createCell(4);
        cellTotalTextInitialEmballage.setCellValue("STOCK EMBALLAGE");
     
        Row rowTotalInitialSansEmballage = spreadsheet.createRow(rowCount + 4);
        Cell cellTotalTextInitialSansEmballage = rowTotalInitialSansEmballage.createCell(4);
        cellTotalTextInitialSansEmballage.setCellValue("STOCK SANS EMBALLAGE");
        
 ////////////////////////////////////////////////////////////////////////////////////////// Total Initial /////////////////////////////////////////////////////////////
        
        
        Cell cellTotalTextInitialMontant = rowTotalInitial.createCell(6);
        cellTotalTextInitialMontant.setCellValue(Double.valueOf(StockInitial.toString()));
        
       
        Cell cellTotalTextInitialEmballageMontant = rowTotalInitialEmballage.createCell(6);
        cellTotalTextInitialEmballageMontant.setCellValue(Double.valueOf(StockInitialEmballage.toString()));
     
      
        Cell cellTotalTextInitialSansEmballageMontant = rowTotalInitialSansEmballage.createCell(6);
        cellTotalTextInitialSansEmballageMontant.setCellValue(Double.valueOf(StockInitialSansEmballage.toString())); 
        
        
//////////////////////////////////////////////////////////////////////////////////////////// Achat  //////////////////////////////////////////////////////////////////////////////////////////////////////////        

Cell cellTotalTextAchat = rowTotalInitial.createCell(8);
cellTotalTextAchat.setCellValue("STOCK ACHAT");


Cell cellTotalTextAchatEmballage = rowTotalInitialEmballage.createCell(8);
cellTotalTextAchatEmballage.setCellValue("ACHAT EMBALLAGE");


Cell cellTotalTextAchatSansEmballage = rowTotalInitialSansEmballage.createCell(8);
cellTotalTextAchatSansEmballage.setCellValue("ACHAT SANS EMBALLAGE");

////////////////////////////////////////////////////////////////////////////////////////// Total Achat /////////////////////////////////////////////////////////////


Cell cellTotalTextAchatMontant = rowTotalInitial.createCell(9);
cellTotalTextAchatMontant.setCellValue(Double.valueOf(StockAchat.toString()));


Cell cellTotalTextAchatEmballageMontant = rowTotalInitialEmballage.createCell(9);
cellTotalTextAchatEmballageMontant.setCellValue(Double.valueOf(StockAchatEmballage.toString()));


Cell cellTotalTextAchatSansEmballageMontant = rowTotalInitialSansEmballage.createCell(9);
cellTotalTextAchatSansEmballageMontant.setCellValue(Double.valueOf(StockAchatSansEmballage.toString()));




       
//////////////////////////////////////////////////////////////////////////////////////////// Production  //////////////////////////////////////////////////////////////////////////////////////////////////////////        

Cell cellTotalTextProduction = rowTotalInitial.createCell(19);
cellTotalTextProduction.setCellValue("PRODUCTION ENTRER");


Cell cellTotalTextProductionSortie = rowTotalInitialEmballage.createCell(19);
cellTotalTextProductionSortie.setCellValue("PRODUCTION SORTIE");


Cell cellTotalTextPrixService = rowTotalInitialSansEmballage.createCell(19);
cellTotalTextPrixService.setCellValue("PRIX SERVICE");

//////////////////////////////////////////////////////////////////////////////////////////Total Production /////////////////////////////////////////////////////////////


Cell cellTotalTextProductionMontant = rowTotalInitial.createCell(20);
cellTotalTextProductionMontant.setCellValue(Double.valueOf(ProductionEntrer.toString()));


Cell cellTotalTextProductionSortieMontant = rowTotalInitialEmballage.createCell(20);
cellTotalTextProductionSortieMontant.setCellValue(Double.valueOf(productionSortie.toString()));


Cell cellTotalTextPrixServiceMontant = rowTotalInitialSansEmballage.createCell(20);
cellTotalTextPrixServiceMontant.setCellValue(Double.valueOf(Prixservice.toString()));


       
//////////////////////////////////////////////////////////////////////////////////////////// Chiffre d'Affaire  //////////////////////////////////////////////////////////////////////////////////////////////////////////        

Cell cellTotalTextCA= rowTotalInitial.createCell(28);
cellTotalTextCA.setCellValue("CA");      

//////////////////////////////////////////////////////////////////////////////////////////Total Chiffre d'Affaire /////////////////////////////////////////////////////////////


Cell cellTotalTextCAMontant= rowTotalInitial.createCell(29);
cellTotalTextCAMontant.setCellValue(Double.valueOf(CA.toString()));

//////////////////////////////////////////////////////////////////////////////////////////// Gratuite  //////////////////////////////////////////////////////////////////////////////////////////////////////////        

Cell cellTotalTextGratuite = rowTotalInitial.createCell(31);
cellTotalTextGratuite.setCellValue("GRATUITE");


Cell cellTotalTextGratuiteEmballage= rowTotalInitialEmballage.createCell(31);
cellTotalTextGratuiteEmballage.setCellValue("GRATUITE EMBALLAGE");


Cell cellTotalTextGratuiteSansEmballage = rowTotalInitialSansEmballage.createCell(31);
cellTotalTextGratuiteSansEmballage.setCellValue("GRATUITE SANS EMBALLAGE");

//////////////////////////////////////////////////////////////////////////////////////////Total Gratuite /////////////////////////////////////////////////////////////


Cell cellTotalTextGratuiteMontant = rowTotalInitial.createCell(32);
cellTotalTextGratuiteMontant.setCellValue(Double.valueOf( StockGratuite.toString()));


Cell cellTotalTextGratuiteEmballageMontant= rowTotalInitialEmballage.createCell(32);
cellTotalTextGratuiteEmballageMontant.setCellValue(Double.valueOf(StockGratuiteEmballage.toString()));


Cell cellTotalTextGratuiteSansEmballageMontant = rowTotalInitialSansEmballage.createCell(32);
cellTotalTextGratuiteSansEmballageMontant.setCellValue(Double.valueOf(StockGratuiteSansEmballage.toString()));


//////////////////////////////////////////////////////////////////////////////////////////// Avoir  //////////////////////////////////////////////////////////////////////////////////////////////////////////        

Cell cellTotalTextAvoir = rowTotalInitial.createCell(34);
cellTotalTextAvoir.setCellValue("AVOIR");


Cell cellTotalTextAvoirEmballage= rowTotalInitialEmballage.createCell(34);
cellTotalTextAvoirEmballage.setCellValue("AVOIR EMBALLAGE");


Cell cellTotalTextAvoirSansEmballage = rowTotalInitialSansEmballage.createCell(34);
cellTotalTextAvoirSansEmballage.setCellValue("AVOIR SANS EMBALLAGE");

//////////////////////////////////////////////////////////////////////////////////////////Total Avoir /////////////////////////////////////////////////////////////


Cell cellTotalTextAvoirMontant = rowTotalInitial.createCell(35);
cellTotalTextAvoirMontant.setCellValue(Double.valueOf(StockAvoir.toString()));


Cell cellTotalTextAvoirEmballageMontant= rowTotalInitialEmballage.createCell(35);
cellTotalTextAvoirEmballageMontant.setCellValue(Double.valueOf(StockAvoirEmballage.toString()));


Cell cellTotalTextAvoirSansEmballageMontant = rowTotalInitialSansEmballage.createCell(35);
cellTotalTextAvoirSansEmballageMontant.setCellValue(Double.valueOf(StockAvoirSansEmballage.toString()));

//////////////////////////////////////////////////////////////////////////////////////////// Finale  //////////////////////////////////////////////////////////////////////////////////////////////////////////        

Cell cellTotalTextFinale= rowTotalInitial.createCell(41);
cellTotalTextFinale.setCellValue("STOCK FINAL");


Cell cellTotalTextFinaleEmballage= rowTotalInitialEmballage.createCell(41);
cellTotalTextFinaleEmballage.setCellValue("STOCK EMBALLAGE");


Cell cellTotalTextFinaleSansEmballage = rowTotalInitialSansEmballage.createCell(41);
cellTotalTextFinaleSansEmballage.setCellValue("STOCK SANS EMBALLAGE");

//////////////////////////////////////////////////////////////////////////////////////////Total Finale /////////////////////////////////////////////////////////////


Cell cellTotalTextFinaleMontant= rowTotalInitial.createCell(42);
cellTotalTextFinaleMontant.setCellValue(Double.valueOf(StockFinale.toString()));


Cell cellTotalTextFinaleEmballageMontant= rowTotalInitialEmballage.createCell(42);
cellTotalTextFinaleEmballageMontant.setCellValue(Double.valueOf(StockFinaleEmballage.toString()));


Cell cellTotalTextFinaleSansEmballageMontant = rowTotalInitialSansEmballage.createCell(42);
cellTotalTextFinaleSansEmballageMontant.setCellValue(Double.valueOf(StockFinaleSansEmballage.toString()));


       
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	
	
	public static void tabletoexcelEtatMargeStockParArticle(JXTable table , String titre , String titrefeuille,BigDecimal initial,BigDecimal achat,BigDecimal service,BigDecimal vente,BigDecimal gratuite,BigDecimal avoir,BigDecimal transfertMPPF,BigDecimal finale,BigDecimal achatrevendu,BigDecimal marge,BigDecimal percentage)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);
        int rowCount = 0;

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            rowCount++;
            
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>3)
                    row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	else
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        
        rowCount=rowCount+1;
        
        
////////////////////////////////////////////////////////////////////////////////////////////  Initial  //////////////////////////////////////////////////////////////////////////////////////////////////////////        
        Row rowTotalInitial = spreadsheet.createRow(rowCount + 2);
        Cell cellTotalTextInitial = rowTotalInitial.createCell(3);
        cellTotalTextInitial.setCellValue("TOTAL");
             
 ////////////////////////////////////////////////////////////////////////////////////////// Total Initial /////////////////////////////////////////////////////////////
        
        
        Cell cellTotalTextInitialMontant = rowTotalInitial.createCell(4);
        cellTotalTextInitialMontant.setCellValue(Double.valueOf(initial.toString()));
        
                         

////////////////////////////////////////////////////////////////////////////////////////// Total Achat /////////////////////////////////////////////////////////////


Cell cellTotalTextAchatMontant = rowTotalInitial.createCell(5);
cellTotalTextAchatMontant.setCellValue(Double.valueOf(achat.toString()));



//////////////////////////////////////////////////////////////////////////////////////////Total Production /////////////////////////////////////////////////////////////


Cell cellTotalTextProductionMontant = rowTotalInitial.createCell(6);
cellTotalTextProductionMontant.setCellValue(Double.valueOf(service.toString()));


//////////////////////////////////////////////////////////////////////////////////////////Total Chiffre d'Affaire /////////////////////////////////////////////////////////////


Cell cellTotalTextCAMontant= rowTotalInitial.createCell(7);
cellTotalTextCAMontant.setCellValue(Double.valueOf(vente.toString()));


//////////////////////////////////////////////////////////////////////////////////////////Total Gratuite /////////////////////////////////////////////////////////////


Cell cellTotalTextGratuiteMontant = rowTotalInitial.createCell(8);
cellTotalTextGratuiteMontant.setCellValue(Double.valueOf( gratuite.toString()));


//////////////////////////////////////////////////////////////////////////////////////////Total Avoir /////////////////////////////////////////////////////////////


Cell cellTotalTextAvoirMontant = rowTotalInitial.createCell(9);
cellTotalTextAvoirMontant.setCellValue(Double.valueOf(avoir.toString()));

//////////////////////////////////////////////////////////////////////////////////////////Total TransfertMPPF /////////////////////////////////////////////////////////////


Cell cellTotalTextTransfertMpPFMontant = rowTotalInitial.createCell(10);
cellTotalTextTransfertMpPFMontant.setCellValue(Double.valueOf(transfertMPPF.toString()));



//////////////////////////////////////////////////////////////////////////////////////////Total Finale /////////////////////////////////////////////////////////////


Cell cellTotalTextFinaleMontant= rowTotalInitial.createCell(11);
cellTotalTextFinaleMontant.setCellValue(Double.valueOf(finale.toString()));
//////////////////////////////////////////////////////////////////////////////////////////Achat Revendu /////////////////////////////////////////////////////////////


Cell cellTotalTextAchatRevenduMontant= rowTotalInitial.createCell(12);
cellTotalTextAchatRevenduMontant.setCellValue(Double.valueOf(achatrevendu.toString()));

////////////////////////////////////////////////////////////////////////////////////////// marge /////////////////////////////////////////////////////////////


Cell cellTotalTextMargeMontant= rowTotalInitial.createCell(13);
cellTotalTextMargeMontant.setCellValue(Double.valueOf(marge.toString()));

////////////////////////////////////////////////////////////////////////////////////////// percentage /////////////////////////////////////////////////////////////


Cell cellTotalTextpercentageMontant= rowTotalInitial.createCell(14);
cellTotalTextpercentageMontant.setCellValue(Double.valueOf(percentage.toString()));

       
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}	
	
	
	
	
	
	
	
	public static void tabletoexcelEtatMargeParArticle(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);
        int rowCount = 0;

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            rowCount++;
            
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>3)
                    row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	else
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        
        rowCount=rowCount+1;
        Row rowTotal = spreadsheet.createRow(rowCount + 3);
        Cell cellTotalText = rowTotal.createCell(3);
        cellTotalText.setCellValue("Total:");
        
        Cell cellTotalInitial = rowTotal.createCell(4);
        cellTotalInitial.setCellFormula("SUM(E2:E"+rowCount+")");
        
        Cell cellTotalProduction = rowTotal.createCell(5);
        cellTotalProduction.setCellFormula("SUM(F2:F"+rowCount+")");
        
        Cell cellTotalAchat = rowTotal.createCell(6);
        cellTotalAchat.setCellFormula("SUM(G2:G"+rowCount+")");
        
        Cell cellTotalRRR2020 = rowTotal.createCell(7);
        cellTotalRRR2020.setCellFormula("SUM(H2:H"+rowCount+")"); 
        
        Cell cellTotalStockFinal = rowTotal.createCell(8);
        cellTotalStockFinal.setCellFormula("SUM(I2:I"+rowCount+")"); 
        
        Cell cellTotalAchatRevendu = rowTotal.createCell(9);
        cellTotalAchatRevendu.setCellFormula("SUM(J2:J"+rowCount+")"); 
        
        Cell cellTotalChiffreAffaire = rowTotal.createCell(10);
        cellTotalChiffreAffaire.setCellFormula("SUM(K2:K"+rowCount+")"); 
        
        Cell cellTotalMarge = rowTotal.createCell(11);
        cellTotalMarge.setCellFormula("SUM(L2:L"+rowCount+")"); 
        
      
        
        
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	
	
	public static void tabletoexcelEtatChiffreAffaireClient(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);
        int rowCount = 0;

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            rowCount++;
            
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>1)
                    row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString().replaceAll(" ", "").replaceAll(",", ".")));
                	else
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        
         
        
      
        
        
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	
	
	public static void tabletoexcelEtatMargeGlobal(List<EtatMargeArticles> listEtatMargeParArticles , String titre , String titrefeuille)
	{
		
if(listEtatMargeParArticles.size()==0)
{
	JOptionPane.showMessageDialog(null, "la list est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);
        int rowCount = 0;

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        
    
      
     
       
       
        int ligne = 0;
       
       
      
       
        
        for (int i = 0; i < listEtatMargeParArticles.size(); i++) {
           
            rowCount++;
            ligne=ligne+1;
            row = spreadsheet.createRow(ligne);
                     row.createCell(0).setCellValue("SI");
                    row.createCell(1).setCellValue(Double.valueOf(listEtatMargeParArticles.get(i).getTotalInitial().toString()));
                    
                    ligne=ligne+1;
                    row = spreadsheet.createRow(ligne);
                    row.createCell(0).setCellValue("PROD");
                    row.createCell(1).setCellValue(Double.valueOf(listEtatMargeParArticles.get(i).getTotalProduction().toString()));
                    
                    ligne=ligne+1;
                    row = spreadsheet.createRow(ligne);
                    row.createCell(0).setCellValue("ACHAT");
                    row.createCell(1).setCellValue(Double.valueOf(listEtatMargeParArticles.get(i).getTotalAchat().toString()));
                    
                    ligne=ligne+1;
                    row = spreadsheet.createRow(ligne);
                    row.createCell(0).setCellValue("RRR");
                    row.createCell(1).setCellValue(Double.valueOf(listEtatMargeParArticles.get(i).getTotalRRR2020().toString()));
                    
                    ligne=ligne+1;
                    row = spreadsheet.createRow(ligne);
                    row.createCell(0).setCellValue("RRR CREDIT");
                    row.createCell(1).setCellValue(Double.valueOf(listEtatMargeParArticles.get(i).getTotalRRRCredit().toString()));
                    
                    ligne=ligne+1;
                    row = spreadsheet.createRow(ligne);
                    row.createCell(0).setCellValue("SF");
                    row.createCell(1).setCellValue(Double.valueOf(listEtatMargeParArticles.get(i).getTotalFinale().toString()));
                    
                    ligne=ligne+1;
                    row = spreadsheet.createRow(ligne);
                    row.createCell(0).setCellValue("ACHAT REVENDU");
                    row.createCell(1).setCellValue(Double.valueOf(listEtatMargeParArticles.get(i).getTotalAchatRevendu().toString()));
                    
                    ligne=ligne+1;
                    row = spreadsheet.createRow(ligne);
                    row.createCell(0).setCellValue("CA");
                    row.createCell(1).setCellValue(Double.valueOf(listEtatMargeParArticles.get(i).getTotalVente().toString()));
                    
                    ligne=ligne+1;
                    row = spreadsheet.createRow(ligne);
                    row.createCell(0).setCellValue("MARGE");
                    row.createCell(1).setCellValue(Double.valueOf(listEtatMargeParArticles.get(i).getTotalMarge().toString()));
                    
                    ligne=ligne+1;
                    row = spreadsheet.createRow(ligne);
                    row.createCell(0).setCellValue("%");
                    row.createCell(1).setCellValue(Double.valueOf(listEtatMargeParArticles.get(i).getTotalMargePourcentage().setScale(2, RoundingMode.HALF_UP).toString()));
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        
        
        
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	
	
	
	
	public static void tabletoexcelEtatFactureArticle(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);
        int rowCount = 0;
    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            rowCount++;
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>5)
                    row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	else
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        
        
        
        rowCount=rowCount+1;
        Row rowTotal = spreadsheet.createRow(rowCount + 2);
        Cell cellTotalText = rowTotal.createCell(5);
        cellTotalText.setCellValue("Total:");
        
        Cell cellTotalInitial = rowTotal.createCell(6);
        cellTotalInitial.setCellFormula("SUM(G2:G"+rowCount+")");
       
        Cell cellTotalProduction = rowTotal.createCell(7);
        cellTotalProduction.setCellFormula("SUM(I2:I"+rowCount+")/SUM(G2:G"+rowCount+")");
        
        Cell cellTotalAchat = rowTotal.createCell(8);
        cellTotalAchat.setCellFormula("SUM(I2:I"+rowCount+")");
        
        Cell cellTotalRRR2020 = rowTotal.createCell(9);
        cellTotalRRR2020.setCellFormula("SUM(J2:J"+rowCount+")"); 
        
        Cell cellTotalStockFinal = rowTotal.createCell(10);
        cellTotalStockFinal.setCellFormula("SUM(K2:K"+rowCount+")"); 
        
        
        
        
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	public static void tabletoexcelEtatFactureClientAvecOuSansICE(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);
        int rowCount = 0;
    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            rowCount++;
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>4)
                    row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	else
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        
        
        
        rowCount=rowCount+1;
        Row rowTotal = spreadsheet.createRow(rowCount + 2);
        Cell cellTotalText = rowTotal.createCell(4);
        cellTotalText.setCellValue("Total:");


        Cell cellTotalAchat = rowTotal.createCell(5);
        cellTotalAchat.setCellFormula("SUM(F2:F"+rowCount+")");
        
        Cell cellTotalRRR2020 = rowTotal.createCell(6);
        cellTotalRRR2020.setCellFormula("SUM(G2:G"+rowCount+")"); 
        
        Cell cellTotalStockFinal = rowTotal.createCell(7);
        cellTotalStockFinal.setCellFormula("SUM(H2:H"+rowCount+")"); 
        
        
        
        
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	
	
	
	public static void tabletoexcelEtatPrixMoyenAchatArticle(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);
        int rowCount = 0;
    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            rowCount++;
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>2)
                    row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	else
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        
        
        
        rowCount=rowCount+1;
        Row rowTotal = spreadsheet.createRow(rowCount + 2);
        Cell cellTotalText = rowTotal.createCell(2);
        cellTotalText.setCellValue("Total:");
        
        Cell cellTotalInitial = rowTotal.createCell(3);
        cellTotalInitial.setCellFormula("SUM(D2:D"+rowCount+")");
       
        Cell cellTotalProduction = rowTotal.createCell(4);
        cellTotalProduction.setCellFormula("SUM(F2:F"+rowCount+")/SUM(D2:D"+rowCount+")");
        
        Cell cellTotalAchat = rowTotal.createCell(5);
        cellTotalAchat.setCellFormula("SUM(F2:F"+rowCount+")");
        
        Cell cellTotalRRR2020 = rowTotal.createCell(6);
        cellTotalRRR2020.setCellFormula("SUM(G2:G"+rowCount+")"); 
        
        Cell cellTotalStockFinal = rowTotal.createCell(7);
        cellTotalStockFinal.setCellFormula("SUM(H2:H"+rowCount+")"); 
        
        
        
        
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	public static void tabletoexcelEtatMoyenStockMP(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);
        int rowCount = 0;
    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            rowCount++;
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>5)
                    row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	else
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        
        
  rowCount=rowCount+1;
        
        Row rowTotal = spreadsheet.createRow(rowCount + 2);
        Cell cellTotalText = rowTotal.createCell(5);
        cellTotalText.setCellValue("Total:");
        
        Cell cellTotalInitial = rowTotal.createCell(6);
        cellTotalInitial.setCellFormula("SUM(G2:G"+rowCount+")");
        
        Cell cellPrixMoyenInitial = rowTotal.createCell(7);
        cellPrixMoyenInitial.setCellFormula("SUM(I2:I"+rowCount+")/SUM(G2:G"+rowCount+")");
        
        Cell cellTotalHTInitial = rowTotal.createCell(8);
        cellTotalHTInitial.setCellFormula("SUM(I2:I"+rowCount+")");
        
        
        Cell cellQuantiteAchat = rowTotal.createCell(9);
        cellQuantiteAchat.setCellFormula("SUM(J2:J"+rowCount+")");
        
        Cell cellPrixMoyenAchat = rowTotal.createCell(10);
        cellPrixMoyenAchat.setCellFormula("SUM(L2:L"+rowCount+")/SUM(J2:J"+rowCount+")");
        
        Cell cellTotalHTAchat = rowTotal.createCell(11);
        cellTotalHTAchat.setCellFormula("SUM(L2:L"+rowCount+")");
       
        Cell cellQuantiteFinale = rowTotal.createCell(12);
        cellQuantiteFinale.setCellFormula("SUM(M2:M"+rowCount+")");
        
        Cell cellPrixMoyenFinale = rowTotal.createCell(13);
        cellPrixMoyenFinale.setCellFormula("SUM(O2:O"+rowCount+")/SUM(M2:M"+rowCount+")");
        
        Cell cellTotalHTFinale = rowTotal.createCell(14);
        cellTotalHTFinale.setCellFormula("SUM(O2:O"+rowCount+")");
       
        
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	public static void tabletoexcelEtatMoyenStockMPParSousFamille(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);
        int rowCount = 0;
    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            rowCount++;
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>3)
                    row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	else
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        
        
  rowCount=rowCount+1;
        
        Row rowTotal = spreadsheet.createRow(rowCount + 2);
        Cell cellTotalText = rowTotal.createCell(3);
        cellTotalText.setCellValue("Total:");
        
        Cell cellTotalInitial = rowTotal.createCell(4);
        cellTotalInitial.setCellFormula("SUM(E2:E"+rowCount+")");
        
        Cell cellPrixMoyenInitial = rowTotal.createCell(5);
        cellPrixMoyenInitial.setCellFormula("SUM(G2:G"+rowCount+")/SUM(E2:E"+rowCount+")");
        
        Cell cellTotalHTInitial = rowTotal.createCell(6);
        cellTotalHTInitial.setCellFormula("SUM(G2:G"+rowCount+")");
        
        
        Cell cellQuantiteAchat = rowTotal.createCell(7);
        cellQuantiteAchat.setCellFormula("SUM(H2:H"+rowCount+")");
        
        Cell cellPrixMoyenAchat = rowTotal.createCell(8);
        cellPrixMoyenAchat.setCellFormula("SUM(J2:J"+rowCount+")/SUM(H2:H"+rowCount+")");
        
        Cell cellTotalHTAchat = rowTotal.createCell(9);
        cellTotalHTAchat.setCellFormula("SUM(J2:J"+rowCount+")");
       
        Cell cellQuantiteFinale = rowTotal.createCell(10);
        cellQuantiteFinale.setCellFormula("SUM(K2:K"+rowCount+")");
        
        Cell cellPrixMoyenFinale = rowTotal.createCell(11);
        cellPrixMoyenFinale.setCellFormula("SUM(M2:M"+rowCount+")/SUM(K2:K"+rowCount+")");
        
        Cell cellTotalHTFinale = rowTotal.createCell(12);
        cellTotalHTFinale.setCellFormula("SUM(M2:M"+rowCount+")");
       
        
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	public static void tabletoexcelEtatMoyenVenteArticle(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);
        int rowCount = 0;
    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            rowCount++;
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>2)
                    row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	else
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        
        
        
        
        rowCount=rowCount+1;
        
        Row rowTotal = spreadsheet.createRow(rowCount + 2);
        Cell cellTotalText = rowTotal.createCell(2);
        cellTotalText.setCellValue("Total:");
        
        Cell cellTotalQuantite = rowTotal.createCell(3);
        cellTotalQuantite.setCellFormula("SUM(D2:D"+rowCount+")");
        
        Cell cellPrixMoyen = rowTotal.createCell(4);
        cellPrixMoyen.setCellFormula("SUM(F2:F"+rowCount+")/SUM(D2:D"+rowCount+")");
        
        Cell cellTotalHT = rowTotal.createCell(5);
        cellTotalHT.setCellFormula("SUM(F2:F"+rowCount+")");
        
        Cell cellTotalTVA = rowTotal.createCell(6);
        cellTotalTVA.setCellFormula("SUM(G2:G"+rowCount+")");
        
        Cell cellTotalTTC = rowTotal.createCell(7);
        cellTotalTTC.setCellFormula("SUM(H2:H"+rowCount+")");
        
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	
	public static void tabletoexcelEtatMoyenAvoirArticle(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);
        int rowCount = 0;
    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            rowCount++;
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>2)
                    row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	else
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        
        
        
        
        rowCount=rowCount+1;
        
        Row rowTotal = spreadsheet.createRow(rowCount + 2);
        Cell cellTotalText = rowTotal.createCell(2);
        cellTotalText.setCellValue("Total:");
        
        Cell cellTotalQuantite = rowTotal.createCell(3);
        cellTotalQuantite.setCellFormula("SUM(D2:D"+rowCount+")");
        
        Cell cellPrixMoyen = rowTotal.createCell(4);
        cellPrixMoyen.setCellFormula("SUM(F2:F"+rowCount+")/SUM(D2:D"+rowCount+")");
        
        Cell cellTotalHT = rowTotal.createCell(5);
        cellTotalHT.setCellFormula("SUM(F2:F"+rowCount+")");
        
        Cell cellTotalTVA = rowTotal.createCell(6);
        cellTotalTVA.setCellFormula("SUM(G2:G"+rowCount+")");
        
        Cell cellTotalTTC = rowTotal.createCell(7);
        cellTotalTTC.setCellFormula("SUM(H2:H"+rowCount+")");
        
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	public static void tabletoexcelEtatMoyenValeurisationProduction(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>3)
                    row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	else
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	
	
	
	public static void tabletoexcelEtatVenteParSousFamilleArticle(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>1)
                    row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	else
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	
	public static void tabletoexcelEtatFactureParModeReglement(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }
        
    	Date datefacture=null;
    	Cell cell = null;
    	
    	org.apache.poi.ss.usermodel.CellStyle cellStyle = workbook.createCellStyle();
    	CreationHelper createHelper = workbook.getCreationHelper();
    	cellStyle.setDataFormat(
    	    createHelper.createDataFormat().getFormat("m/d/yy"));
    	
    	

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	cell=row.createCell(j);
                	
                	if(j>3 && j<7)
                	{
                		cell.setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	}
                   
                	else if(j==0) {
                		
                		try {
                       	 datefacture=new SimpleDateFormat("dd/MM/yyyy").parse(table.getValueAt(i, j).toString());

   					} catch (ParseException e) {
   						// TODO Auto-generated catch block
   						e.printStackTrace();
   					}
                		
                		
                		
                		cell.setCellValue(datefacture);
                		cell.setCellStyle(cellStyle);
                	}else
                	{
                		cell.setCellValue(table.getValueAt(i, j).toString());
                	}
                		
   
                } else {
                	cell.setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	
	
	public static void tabletoexcelEtatFactureAutresVentesTypeFacture(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }
        
    	Date datefacture=null;
    	Cell cell = null;
    	
    	org.apache.poi.ss.usermodel.CellStyle cellStyle = workbook.createCellStyle();
    	CreationHelper createHelper = workbook.getCreationHelper();
    	cellStyle.setDataFormat(
    	    createHelper.createDataFormat().getFormat("m/d/yy"));
    	
    	

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	cell=row.createCell(j);
                	
                	if(j>4 )
                	{
                		cell.setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	}
                   
                	else if(j==0) {
                		
                		try {
                       	 datefacture=new SimpleDateFormat("dd/MM/yyyy").parse(table.getValueAt(i, j).toString());

   					} catch (ParseException e) {
   						// TODO Auto-generated catch block
   						e.printStackTrace();
   					}
                		
                		
                		
                		cell.setCellValue(datefacture);
                		cell.setCellStyle(cellStyle);
                	}else
                	{
                		cell.setCellValue(table.getValueAt(i, j).toString());
                	}
                		
   
                } else {
                	cell.setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	public static void tabletoexcelEtatFactureAutresVentes(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }
        
    	Date datefacture=null;
    	Cell cell = null;
    	
    	org.apache.poi.ss.usermodel.CellStyle cellStyle = workbook.createCellStyle();
    	CreationHelper createHelper = workbook.getCreationHelper();
    	cellStyle.setDataFormat(
    	    createHelper.createDataFormat().getFormat("m/d/yy"));
    	
    	

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	cell=row.createCell(j);
                	
                	if(j>5 )
                	{
                		cell.setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	}
                   
                	else if(j==0) {
                		
                		try {
                       	 datefacture=new SimpleDateFormat("dd/MM/yyyy").parse(table.getValueAt(i, j).toString());

   					} catch (ParseException e) {
   						// TODO Auto-generated catch block
   						e.printStackTrace();
   					}
                		
                		
                		
                		cell.setCellValue(datefacture);
                		cell.setCellStyle(cellStyle);
                	}else
                	{
                		cell.setCellValue(table.getValueAt(i, j).toString());
                	}
                		
   
                } else {
                	cell.setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	
	
	
	public static void tabletoexcelEtatChiffreAffaireAvectimbre(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }
        
    	Date datefacture=null;
    	Cell cell = null;
    	
    	org.apache.poi.ss.usermodel.CellStyle cellStyle = workbook.createCellStyle();
    	
    	
    	

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	cell=row.createCell(j);
                	
                	if(j>0)
                	{
                		cell.setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	
                	}else
                	{
                		cell.setCellValue(table.getValueAt(i, j).toString());
                	}
                		
   
                } else {
                	cell.setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	public static void tabletoexcelEtatFacturePFParArticle(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }
        
    	Date datefacture=null;
    	Cell cell = null;
    	
    	org.apache.poi.ss.usermodel.CellStyle cellStyle = workbook.createCellStyle();
    	CreationHelper createHelper = workbook.getCreationHelper();
    	cellStyle.setDataFormat(
    	    createHelper.createDataFormat().getFormat("m/d/yy"));
    	
    	

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	cell=row.createCell(j);
                	
                	if(j>2 && j<6)
                	{
                		cell.setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	}
                   
                	else if(j==0) {
                		
                		try {
                       	 datefacture=new SimpleDateFormat("dd/MM/yyyy").parse(table.getValueAt(i, j).toString());

   					} catch (ParseException e) {
   						// TODO Auto-generated catch block
   						e.printStackTrace();
   					}
                		
                		
                		
                		cell.setCellValue(datefacture);
                		cell.setCellStyle(cellStyle);
                	}else
                	{
                		cell.setCellValue(table.getValueAt(i, j).toString());
                	}
                		
   
                } else {
                	cell.setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	
	public static void tabletoexcelEtatVenteParArticle(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>0)
                    row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	else
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	public static void tabletoexcelEtatInitialMPParSousFamilleArticle(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>3)
                    row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	else
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	public static void tabletoexcelEtatMPConsommerParOF(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>7)
                    row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	else
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	public static void tabletoexcelEtatBoxEtCartonConsommerParPF(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j==3 || j==5 ||j==6 || j==7 || j==9 || j==10 || j==11)
                    row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	else
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	
	public static void tabletoexcelEtatAchatParSousFamilleArticle(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>1)
                    row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	else
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	
	
	public static void tabletoexcelEtatMoyenStockPF(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                if(j<=3 )	
                	 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
                else 
                	
               		 row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                } 
                
                
                
                else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	
	
	
	public static void tabletoexcelEtatVentePF(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>8)
                    row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	else
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	public static void tabletoexcelHistoriqueDeTransfertFactureEnBL(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>6)
                	{
                		 row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	}
                   
                	else
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	public static void tabletoexcelHistoriqueDeTransfertFactureAvoirClientPFEnBL(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }
        
      	Date datefacture=null;
    	Cell cell = null;
    	
    	org.apache.poi.ss.usermodel.CellStyle cellStyle = workbook.createCellStyle();
    	CreationHelper createHelper = workbook.getCreationHelper();
    	cellStyle.setDataFormat(
    	    createHelper.createDataFormat().getFormat("m/d/yy"));
    	

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	cell=row.createCell(j);
                	if(j>6)
                	{
                		cell.setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	}else if(j==0)
                	{
                		try {
                          	 datefacture=new SimpleDateFormat("dd/MM/yyyy").parse(table.getValueAt(i, j).toString());

      					} catch (ParseException e) {
      						// TODO Auto-generated catch block
      						e.printStackTrace();
      					}
                   		
                   		
                   		
                   		cell.setCellValue(datefacture);
                   		cell.setCellStyle(cellStyle);
                		
                	}else {
                		cell.setCellValue(table.getValueAt(i, j).toString());
                	}
                		
   
                } else {
                	cell.setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	
	
	
	
	public static void tabletoexcelEtatAchatMP(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>9)
                    row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	else
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	
	
	
	
	
	public static void tabletoexcelMP(JXTable table , String titre , String titrefeuille )
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>3)
                    row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	else
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	public static void tabletoexcelEtatQuantiteTransforme(JXTable table , String titre , String titrefeuille )
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>2)
                    row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	else
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	
	public static void tabletoexcelEtatValeurStock(JXTable table , String titre , String titrefeuille , String stockinitial,String stockinitialEmballage,String stockinitialSansEmballage,String stockAchat,String stockAchatEmballage,String stockAchatSansEmballage,String stockProdEntrer,String stockProdSortie,String stockCA,String stockGratuite, String stockAvoir,String stockAvoirEmballage,String stockAvoirSansEmballage, String stockFinale,String stockFinaleEmballage,String stockFinaleSansEmballage)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>2)
                    row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	else
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        
 
        
/////////////////////////////////////////////////////        Stock    /////////////////////////////////////////////////////////////////////  
        
        row = spreadsheet.createRow(table.getRowCount() + 4); 
        row.createCell(4).setCellValue("STOCK INITIAL");
        row.createCell(5).setCellValue(Double.valueOf(stockinitial));
        row.createCell(7).setCellValue("STOCK Achat");
        row.createCell(8).setCellValue(Double.valueOf(stockAchat));
        row.createCell(10).setCellValue("Production Entrer");
        row.createCell(11).setCellValue(Double.valueOf(stockProdEntrer));
        row.createCell(13).setCellValue("Production sortie");
        row.createCell(14).setCellValue(Double.valueOf(stockProdSortie));
        row.createCell(16).setCellValue("CA");
        row.createCell(17).setCellValue(Double.valueOf(stockCA));
        row.createCell(19).setCellValue("AV GRAT");
        row.createCell(20).setCellValue(Double.valueOf(stockGratuite));
        row.createCell(22).setCellValue("AV");
        row.createCell(23).setCellValue(Double.valueOf(stockAvoir));
        row.createCell(25).setCellValue("STOCK FINAL");
        row.createCell(26).setCellValue(Double.valueOf(stockFinale));
/////////////////////////////////////////////////////        Stock Emballage    /////////////////////////////////////////////////////////////////////  
       
        
        row = spreadsheet.createRow(table.getRowCount() + 5); 
        row.createCell(4).setCellValue("STOCK Emballage");
        row.createCell(5).setCellValue(Double.valueOf(stockinitialEmballage));
        row.createCell(7).setCellValue("Achat Emballage");
        row.createCell(8).setCellValue(Double.valueOf(stockAchatEmballage));
        row.createCell(22).setCellValue("AV EMB");
        row.createCell(23).setCellValue(Double.valueOf(stockAvoirEmballage));
        row.createCell(25).setCellValue("STOCK EMB");
        row.createCell(26).setCellValue(Double.valueOf(stockFinaleEmballage));
        
/////////////////////////////////////////////////////        Stock Sans Emballage    /////////////////////////////////////////////////////////////////////  
  
        
        row = spreadsheet.createRow(table.getRowCount() + 6); 
        row.createCell(4).setCellValue("STOCK Sans Emballage");
        row.createCell(5).setCellValue(Double.valueOf(stockinitialSansEmballage));
        row.createCell(7).setCellValue("Achat Sans Emballage");
        row.createCell(8).setCellValue(Double.valueOf(stockAchatSansEmballage));
        row.createCell(22).setCellValue("AV SANS EMB");
        row.createCell(23).setCellValue(Double.valueOf(stockAvoirSansEmballage));
        row.createCell(25).setCellValue("STOCK SANS EMB");
        row.createCell(26).setCellValue(Double.valueOf(stockFinaleSansEmballage));
        
        
    
  
       
      
      
     
        
        
        
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	
	public static void tabletoexcelFactureService(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>5)
                    row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	else
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	public static void tabletoexcelFactureServiceParOF(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>6)
                    row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	else
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	public static void tabletoexcelFactureAchatPF(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>5)
                    row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	else
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	
	public static void tabletoexcelEtatVenteParFamille(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>0)
                    row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	else
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	public static void tabletoexcelEtatVenteParFamilleParFacture(JXTable table , String titre , String titrefeuille)
	{
		
if(table.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < table.getColumns().size(); j++) {
            row.createCell(j).setCellValue(table.getColumnName(j).toString());
   

        }

        for (int i = 0; i < table.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < table.getColumns().size(); j++) {
                if (table.getValueAt(i, j) != null) {
                	
                	
                	if(j>2)
                    row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
                	else
                		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	public static void tabletoexcelStatistiqueEnVracUtiliserLorsDeLaProduction(JXTable table , String titre , String titrefeuille)
	{
		
	if(table.getRowCount()==0)
	{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
	}
		

	    int number = workbook.getNumberOfSheets();
	    if (number == 1) {
	        workbook.removeSheetAt(0);
	    }
	    Sheet spreadsheet = workbook.createSheet(titrefeuille);

	    org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

	/*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
	    ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
	    ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
	    ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

	    Font font1 = (Font) workbook.createFont();
	    ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
	    ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
	    ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
	  
	    for (int j = 0; j < table.getColumns().size(); j++) {
	        row.createCell(j).setCellValue(table.getColumnName(j).toString());


	    }

	    for (int i = 0; i < table.getRowCount(); i++) {
	        row = spreadsheet.createRow(i + 1);
	        for (int j = 0; j < table.getColumns().size(); j++) {
	            if (table.getValueAt(i, j) != null) {
	            	
	            	/*
	            	if(j>0)
	            		if(!table.getValueAt(i, j).toString().equals("-"))
	            		{
	            			 row.createCell(j).setCellValue(Double.valueOf(table.getValueAt(i, j).toString()));
	            		}else
	            		{
	            			row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
	            		}
	               
	            	else*/
	            	if(j>1)
	            	{
	            		if(!table.getValueAt(i, j).toString().equals("-"))
	            		{
		            		HSSFCell dataCell = (HSSFCell) row.createCell(j);
		            		dataCell.setCellValue(Double.valueOf(table.getValueAt(i, j).toString().replaceAll(" ", "").replaceAll(",", ".")));
		            		dataCell.getCellStyle().setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
	            		}else
	            		{
	            			 row.createCell(j).setCellValue(table.getValueAt(i, j).toString().replaceAll(" ", ""));
	            		}
	            	}else
	            	{
	            		 row.createCell(j).setCellValue(table.getValueAt(i, j).toString());
	            	}
	            	
	            	
	            	
	            		

	            } else {
	                row.createCell(j).setCellValue("");
	            }
	        }
	    }
	    int rowNum = 0;
	    for (short i = spreadsheet.getRow(0).getFirstCellNum(),
	            end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
	        CellRangeAddress ca
	                = new CellRangeAddress(0, rowNum,
	                        spreadsheet.getRow(0).getFirstCellNum(),
	                        spreadsheet.getRow(0).getLastCellNum());

	        rowNum++;

	    }
	    for (short i = spreadsheet.getRow(0).getFirstCellNum(),
	            end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
	        spreadsheet.autoSizeColumn(i);
	    }
	    try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
	    	
	        workbook.write(fileOut);
	        fileOut.flush();
	        fileOut.close();
	        Desktop.getDesktop().open(new File(titre+".xls"));

	    } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	
	
	
	
	
	public static void tableTonnageproductionToExcel(javax.swing.table.DefaultTableModel modeleMP , String titre , String titrefeuille)
	{
		
if(modeleMP.getRowCount()==0)
{
	JOptionPane.showMessageDialog(null, "la table est vide","Attention",JOptionPane.ERROR_MESSAGE);
	return;
}
		

        int number = workbook.getNumberOfSheets();
        if (number == 1) {
            workbook.removeSheetAt(0);
        }
        org.apache.poi.ss.usermodel.Sheet spreadsheet = workbook.createSheet(titrefeuille);

        org.apache.poi.ss.usermodel.Row row = spreadsheet.createRow(0);

    /*    org.apache.poi.ss.usermodel.Font font =  workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font).setFontHeightInPoints((short) 13);
        ((org.apache.poi.ss.usermodel.Font) font).setBold(true);
        ((org.apache.poi.ss.usermodel.Font) font).setColor((short) HSSFColor.GREEN.index);

        Font font1 = (Font) workbook.createFont();
        ((org.apache.poi.ss.usermodel.Font) font1).setFontHeightInPoints((short) 10);
        ((org.apache.poi.ss.usermodel.Font) font1).setBold(false);
        ((org.apache.poi.ss.usermodel.Font) font1).setColor((short) HSSFColor.BLACK.index);*/
      
        for (int j = 0; j < modeleMP.getColumnCount(); j++) {
            row.createCell(j).setCellValue(modeleMP.getColumnName(j).toString());
   

        }

        for (int i = 0; i < modeleMP.getRowCount(); i++) {
            row = spreadsheet.createRow(i + 1);
            for (int j = 0; j < modeleMP.getColumnCount(); j++) {
                if (modeleMP.getValueAt(i, j) != null) {
                	
                	
                	 
                    row.createCell(j).setCellValue(Double.valueOf(modeleMP.getValueAt(i, j).toString()));
                 
   
                } else {
                    row.createCell(j).setCellValue("");
                }
            }
        }
        int rowNum = 0;
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end; i++) {
            CellRangeAddress ca
                    = new CellRangeAddress(0, rowNum,
                            spreadsheet.getRow(0).getFirstCellNum(),
                            spreadsheet.getRow(0).getLastCellNum());
  
            rowNum++;

        }
        for (short i = spreadsheet.getRow(0).getFirstCellNum(),
                end = spreadsheet.getRow(0).getLastCellNum(); i < end - 1; i++) {
            spreadsheet.autoSizeColumn(i);
        }
        try (FileOutputStream fileOut = new FileOutputStream(titre+".xls")) {
        	
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Desktop.getDesktop().open(new File(titre+".xls"));

        } catch (Exception e) {
			// TODO: handle exception
		}
			
		
	}
	

}
