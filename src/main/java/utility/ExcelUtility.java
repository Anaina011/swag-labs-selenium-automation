package utility;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public static Map<String, String> getUserAddressFromExcelSheet(String sheetName) {
		String projectPath = System.getProperty("user.dir");
		System.out.println(projectPath);
		String completePath = projectPath+"\\src\\main\\java\\Test Data\\User Address.xlsx";
		System.out.println(completePath);
		Map<String, String> excelMap = new HashMap();
		
		try {
			XSSFWorkbook workbook = new XSSFWorkbook(completePath);
			XSSFSheet sheet = workbook.getSheet(sheetName);
			
			for(int i=0; i <= sheet.getLastRowNum(); i++) {
				XSSFRow row = sheet.getRow(i);
				String key = row.getCell(0).toString();
				String value = row.getCell(1).toString().replace(".0", "");
				System.out.println(key + ":" + value);
				excelMap.put(key, value);
			}
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return excelMap;
	}
	
	
	public static void main(String[] a) {
		getUserAddressFromExcelSheet("User Address1");
	} 
	
}
