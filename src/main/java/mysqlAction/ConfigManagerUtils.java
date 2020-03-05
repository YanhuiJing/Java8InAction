package mysqlAction;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author gavin
 * @createDate 2020/3/5
 */
public class ConfigManagerUtils {
    
    private static Properties properties = new Properties();
    
    static {

        try {

        InputStream resourceAsStream =
                ConfigManagerUtils.class.getClassLoader().getResourceAsStream("my.properties");

        properties.load(resourceAsStream);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static String getProperty(String key){

        return properties.getProperty(key);

    }


    public static Integer getInteger(String key){

        String value = properties.getProperty(key);

        try{
            return Integer.valueOf(value);
        }catch (Exception e){
            e.printStackTrace();
        }

        return 0;

    }

    public static Long getLong(String key){

        String value = properties.getProperty(key);

        try{
            return Long.valueOf(value);
        }catch (Exception e){
            e.printStackTrace();
        }

        return 0L;

    }

    public static Boolean getBoolean(String key){

        String value = properties.getProperty(key);

        try{
            return Boolean.valueOf(value);
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
}



