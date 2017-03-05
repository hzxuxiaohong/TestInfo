package util;

import java.io.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hzliujingyuan on 2016/7/27.
 */
public class ReadDataFromFile {
    public static String readFile(String path) {

        String data="";
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
            String line;
            while ((line= br.readLine()) != null) {
                data=data+line;
            }
            br.close();
        } catch (FileNotFoundException e){

        }catch (IOException e){

        }
        return data;
    }

    public static List<String[]> readFileIntoData(String path) {

        List<String[]> data=new ArrayList<String[]>();
        String[] lineData=new String[]{};
        try {
            BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(path),"UTF-8"));
            String line;
            while ((line= br.readLine()) != null) {
                lineData=line.split(",");
                data.add(lineData);
            }

            br.close();
        } catch (FileNotFoundException e){

        }catch (IOException e){

        }
        return data;
    }
}
