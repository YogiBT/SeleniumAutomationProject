package utils;

import all.utils.ExcelUtils;
import org.testng.annotations.DataProvider;

import java.util.List;

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
}
