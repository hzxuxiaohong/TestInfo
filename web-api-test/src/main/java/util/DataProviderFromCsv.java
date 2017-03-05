package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;




import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import au.com.bytecode.opencsv.CSVReader;



public class DataProviderFromCsv {
    // 定义csv文件的分割符
    public static char csvSeprator = ',';
    // 定义@DataProvider的name
    public static DataProviderFromCsv dataProviderFromCsv = new DataProviderFromCsv();

    @DataProvider(name = "ProviderCsvMethod")
    public static Object[][] getCsvCMData(Method method, ITestContext context)
            throws IOException {
        // 获取当前文件编译后的绝对路径

        String retPath = dataProviderFromCsv.getPath();

        // 获取调用方法的方法名
        String methodName = method.getName();

        // 获取调用方法的类名
        String className = null;
        String[] packageName = method.getDeclaringClass().getName().toString()
                .split("\\.");
        className = packageName[packageName.length - 1];
        // 指定当前类当前方法对应的csv文件
        //String csvPath = retPath + className + "." + methodName + ".xls";
        String csvPath = retPath + className + "." + methodName + ".csv";
        return dataProviderFromCsv.readCsvData(csvPath);

    }
    @DataProvider(name = "ProviderCsvClass")
    public static Object[][] getCsvCData(Method method, ITestContext context)
            throws IOException{
        // 获取当前文件编译后的绝对路径
        String retPath = dataProviderFromCsv.getPath();
        // 获取调用方法的类名
        String className = null;
        String[] packageName = method.getDeclaringClass().getName().toString()
                .split("\\.");
        className = packageName[packageName.length - 1];
        // 指定当前类当前方法对应的csv文件
        String csvPath = retPath + className + ".csv";
        //return dataProviderFromCsv.readCsvData(csvPath);
        return dataProviderFromCsv.readCsvData(csvPath);

    }
    public String getPath() {
        String absolutePath = null;
        try {
            absolutePath = this.getClass().getResource("/inputdata/").getPath();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return absolutePath;
    }
    public Object[][] readCsvData(String csvPath) {
        // 从CSV文件中读取数据
        ArrayList<Object[]> csvList = null;
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(csvPath), "UTF-8"), csvSeprator);

            reader.readNext();

            // csv中每行的数据都是一个string数组
            String[] csvRow = null;
            csvList = new ArrayList<Object[]>();
            // 将读取的数据，按行存入到csvList中
            while ((csvRow = reader.readNext()) != null) {
            	if (csvRow[2].equals("1"))
                csvList.add(csvRow);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(csvList!=null){
            // 定义返回值
            Object[][] results = new Object[csvList.size()][];
            // 设置二维数组每行的值，每行是个object对象
            for (int i = 0; i < csvList.size(); i++) {
                results[i] = csvList.get(i);
            }
            return results;
        }else{
            return  null;
        }

	}
}
