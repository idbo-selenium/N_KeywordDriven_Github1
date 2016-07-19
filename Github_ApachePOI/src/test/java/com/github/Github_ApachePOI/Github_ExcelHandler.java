package com.github.Github_ApachePOI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.junit.Test;

public class Github_ExcelHandler {

	public static Logger logger = Logger.getLogger(Github_ExcelHandler.class);
	@Test
	public void Github_Handler()throws Exception{
		
		try {
			PropertyConfigurator.configure("log4j.properties");
			logger.info("sdgtfrhyjukl");
			FileInputStream file = new FileInputStream("GitHub.xls");
			HSSFWorkbook workbook = new HSSFWorkbook(file);
			HSSFSheet validation_sheet = workbook.getSheet("Sheet_Validation");
			int validation_sheet_rows = validation_sheet.getLastRowNum();
			System.out.println("Validation Sheet rows : "+validation_sheet_rows);
			HSSFSheet login_sheet = workbook.getSheet("GitHub_Login");
			int login_sheet_rows = login_sheet.getLastRowNum();
			System.out.println("Login Sheet rows : "+login_sheet_rows);	
			
			GitHub_Login github = new GitHub_Login();
			Method githubActions[] =github.getClass().getMethods();
			
			for(int i=1;i<=validation_sheet_rows;i++){
				String validation_sheet_TestID = validation_sheet.getRow(i).getCell(0).getStringCellValue();
				String validation_sheet_TestMode = validation_sheet.getRow(i).getCell(2).getStringCellValue();
				if(validation_sheet_TestMode.equalsIgnoreCase("Yes")){
					for(int j=1;j<=login_sheet_rows;j++){
						String login_sheet_TestID = login_sheet.getRow(j).getCell(0).getStringCellValue();
						if(validation_sheet_TestID.equals(login_sheet_TestID)){
							String login_sheet_Action = String.valueOf(login_sheet.getRow(j).getCell(3));
							String login_sheet_Object = String.valueOf(login_sheet.getRow(j).getCell(2));
							String login_sheet_Data = String.valueOf(login_sheet.getRow(j).getCell(4));
							for(int k=0;k<githubActions.length;k++){
								if(githubActions[k].getName().equals(login_sheet_Action)){
									githubActions[k].invoke(github, login_sheet_Object,login_sheet_Data);									
									if(login_sheet_Action.contains("Validate")){
										Cell cell = login_sheet.getRow(j).createCell(5);
										cell.setCellType(Cell.CELL_TYPE_STRING);
										cell.setCellValue("Success");		
										FileOutputStream fileOut = new FileOutputStream("GitHub.xls");
										workbook.write(fileOut);
										fileOut.close();
									}
								}
							}							
						}
					}
				} 
			}				
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}