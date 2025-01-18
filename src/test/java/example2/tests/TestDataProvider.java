package example2.tests;

import org.testng.annotations.DataProvider;
import all.utils.ExcelUtils;

import java.util.List;

public class TestDataProvider {

    @DataProvider(name = "excelData")
    public Object[][] getData() {
        List<List<String>> excelData = ExcelUtils.readExcelFile("./Data.xlsx");
        Object[][] data = new Object[excelData.size()][];
        for (int i = 0; i < excelData.size(); i++) {
            data[i] = excelData.get(i).toArray(new Object[0]);
        }
        return data;
    }
}
