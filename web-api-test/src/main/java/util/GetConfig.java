package util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

public class GetConfig {
    private static Logger log = LogManager.getLogger(GetConfig.class);
    public String getPara(String filename, String para) {

        Properties prop= new Properties();
        try {

            InputStream is = this.getClass().getResourceAsStream("/" + filename);
            prop.load(is);
            if(is!= null){
                is.close();
            }
        }
        catch(Exception e) {
            log.info(e+" configfile " + filename + " not found");
        }
        return prop.getProperty(para);
    }

    public static String getFile(String filename) {

        String file = GetConfig.class.getClassLoader().getResource(filename).getFile();
        if(file == null){
            log.info("expect file is not found");
        }

        return file;
    }


}
