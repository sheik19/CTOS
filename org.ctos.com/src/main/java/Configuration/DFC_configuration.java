package Configuration;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Properties;

public class DFC_configuration {
	
	private static Properties properties;
    public static DFC_configuration configLoader;
    public static String dir=System.getProperty("user.dir");
    public static String config;
    
	public DFC_configuration(String config){
    	this.config=config;
        properties = PropertyUtils.propertyLoader(config);
    
    }
    public static DFC_configuration getInstance(){
    	if(configLoader == null) {
    		configLoader = new DFC_configuration(config);
    	}
        return configLoader;
    }
    
    public String get_DFC_SIT_env_upload_url(){
        String prop = properties.getProperty("DFC_SIT_env_upload");
        if(prop != null) return prop;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");
    }
    
    public String get_DFC_SIT_env_retrieve_url(){
        String prop = properties.getProperty("DFC_SIT_env_retrieve");
      
        if(prop != null) return prop;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");
    }
    
    public String get_DFC_SIT_x_api_key(){
        String prop = properties.getProperty("DFC_SIT_x_api_key");
        if(prop != null) return prop;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");
    }
    
    public String get_DFC_report_path(){
        String prop = properties.getProperty("DFC_reports");
        if(prop != null) return prop;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");
    }
    
    public String get_DFC_reportname(){
        String prop = properties.getProperty("reportname");
        if(prop != null) return prop;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");
    }
    
    public String get_DocumentTitle(){
        String prop = properties.getProperty("DocumentTitle");
        if(prop != null) return prop;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");
    }
    
    public String get_tester(){
        String prop = properties.getProperty("tester");
        if(prop != null) return prop;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");
    }
    
    public String get_pro_name(){
        String prop = properties.getProperty("pro_name");
        if(prop != null) return prop;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");
    }
    public String get_dataset(){
        String prop = properties.getProperty("dataset");
        if(prop != null) return prop;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");
    }
    
    public String update_dataset(String ref_number) throws FileNotFoundException{
       // String prop = properties.getProperty("dataset");
    	FileOutputStream out = new FileOutputStream(config);
    	properties.setProperty("ref_number", ref_number);
        if(ref_number != null) return ref_number;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");
    }
    
   
    }
