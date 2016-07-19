package Practice_Excel_ApchePOI;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class ApachePOI_ExcelHandler {

	@Test
	public void ExcelHandler(){
		try {
			FileInputStream githubFile = new FileInputStream("GitHubXLSX.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(githubFile);
			XSSFSheet testcasesSheet = workbook.getSheet("Test_Cases");
			XSSFSheet loginSheet = workbook.getSheet("Login");
			
			int testcasesSheetRows = testcasesSheet.getLastRowNum();
			System.out.println("testcasesSheetRows : "+testcasesSheetRows);
			
			int loginSheetRows = loginSheet.getLastRowNum();
			System.out.println("loginSheetRows : "+loginSheetRows);
			
			GitHubTestCasesClass github = new GitHubTestCasesClass();
			Method githubMethods[] = github.getClass().getMethods();
			
			for(int i=1;i<=testcasesSheetRows;i++){
				String testcasesSheet_TestID = String.valueOf(testcasesSheet.getRow(i).getCell(0));
				String testcasesSheet_Mode = String.valueOf(testcasesSheet.getRow(i).getCell(2));
				if(testcasesSheet_Mode.equals("Yes")){
					for(int j=1;j<=loginSheetRows;j++){
						String loginSheet_TestID = String.valueOf(loginSheet.getRow(j).getCell(0));
						if(testcasesSheet_TestID.equals(loginSheet_TestID)){
							String loginSheet_Object = String.valueOf(loginSheet.getRow(j).getCell(2));
							String loginSheet_Action = String.valueOf(loginSheet.getRow(j).getCell(3));
							String loginSheet_Data = String.valueOf(loginSheet.getRow(j).getCell(4));
							for(int k=0;k<githubMethods.length;k++){
								if(loginSheet_Action.equals(githubMethods[k].getName())){
									githubMethods[k].invoke(github, loginSheet_Object,loginSheet_Data);
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
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}	
}