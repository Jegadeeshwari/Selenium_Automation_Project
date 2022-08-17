package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

  private static final Properties properties;
 


  static {
	  properties = loadProps("demoblaze.properties");
  }

  private static Properties loadProps(String path){
      Properties props = new Properties();
      InputStream inputStream = null;
      try {
          inputStream = ClassLoader.getSystemResourceAsStream(path);
          if (inputStream != null){
              props.load(inputStream);
              return props;
          }
      } catch (IOException e) {
          e.printStackTrace();
      }
      finally {
          try {
              if (inputStream != null) {
                  inputStream.close();
              }
          } catch (IOException e) {
              e.printStackTrace();
          }
      }
      return null;
  }

  public static String getProperty(String key){
      return properties.getProperty(key);
  }
}
