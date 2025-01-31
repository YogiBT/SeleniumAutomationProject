package utils;

import all.utils.ExcelUtils;
import org.testng.annotations.DataProvider;

import java.util.List;
import java.util.stream.Collectors;

public class readFromExcel {
    @DataProvider(name = "excelData")
    public Object[][] getData() {
        List<List<String>> excelData = ExcelUtils.readExcelFile("./ContactUsData.xlsx");
        Object[][] data = new Object[excelData.size()][];
        for (int i = 0; i < excelData.size(); i++) {
            data[i] = excelData.get(i).toArray(new Object[0]);
        }
        return data;
    }

    public String getFirstEmail() {
        List<List<String>> excelData = ExcelUtils.readExcelFile("./ContactUsData.xlsx");
        if (excelData.size() > 1) {
            int emailColumnIndex = excelData.get(0).indexOf("email");
            if (emailColumnIndex != -1) {
                return excelData.get(1).get(emailColumnIndex); // Get the first value after the header
            }
        }
        throw new IndexOutOfBoundsException("Email column or data not found in the Excel file.");
    }

    public String getFirstPassword() {
        List<List<String>> excelData = ExcelUtils.readExcelFile("./ContactUsData.xlsx");
        if (excelData.size() > 1) {
            int passwordColumnIndex = excelData.get(0).indexOf("password");
            if (passwordColumnIndex != -1) {
                return excelData.get(1).get(passwordColumnIndex); // Get the first value after the header
            }
        }
        throw new IndexOutOfBoundsException("Password column or data not found in the Excel file.");
    }
}
