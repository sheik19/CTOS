package Configuration;

import java.util.Properties;

public class DFB_cofiguration {
	
	private static Properties properties;
    private static DFB_cofiguration configLoader;
    public static String dir=System.getProperty("user.dir");
    public static String config;
    @SuppressWarnings("static-access")
	public DFB_cofiguration(String config){
    	this.config=config;
        properties = PropertyUtils.propertyLoader(config);
    }
    public static DFB_cofiguration getInstance(){
    	if(configLoader == null) {
    		configLoader = new DFB_cofiguration(config);
    	}
        return configLoader;
    }

    
   

  

    public String get_DFB_SIT_env(){
        String prop = properties.getProperty("DFB_SIT_env");
        if(prop != null) return prop;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");
    }
    
    public String get_DFB_Dev_env(){
        String prop = properties.getProperty("DFB_Dev_env");
        if(prop != null) return prop;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");
    }
    
    public String get_DFB_Dev_x_api_key(){
        String prop = properties.getProperty("DFB_Dev_x_api_key");
        if(prop != null) return prop;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");
    }
    
    public String get_DFB_SIT_x_api_key(){
        String prop = properties.getProperty("DFB_SIT_x_api_key");
        if(prop != null) return prop;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");
    }
    
    
    public String get_dgf_token_uri(){
        String prop = properties.getProperty("dgf_token_uri");
        if(prop != null) return prop;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");
    }

    public String get_tester_name(){
        String prop = properties.getProperty("dgf_tester");
        if(prop != null) return prop;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");
    }
    
    public String get_pro_name(){
        String prop = properties.getProperty("dgf_pro_name");
        if(prop != null) return prop;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");
    }
    
    public String get_dgf_extentreport(){
        String prop = properties.getProperty("dgf_extentreport");
        if(prop != null) return prop;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");
    }
    
    public String get_go_rest_reports(){
        String prop = properties.getProperty("go_rest_reports");
        if(prop != null) return prop;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");
    }
    
    public String get_go_rest_goRestdatasheet(){
        String prop = properties.getProperty("go_rest_goRestdatasheet");
        if(prop != null) return prop;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");
    }
    
    public String get_go_rest_tester(){
        String prop = properties.getProperty("go_rest_tester");
        if(prop != null) return prop;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");
    }
    
    public String get_go_rest_pro_name(){
        String prop = properties.getProperty("go_rest_pro_name");
        if(prop != null) return prop;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");
    }
    
    public String get_go_rest_baseURI(){
        String prop = properties.getProperty("go_rest_baseURI");
        if(prop != null) return prop;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");
    }
    
    public String get_go_rest_tokens(){
        String prop = properties.getProperty("go_rest_tokens");
        if(prop != null) return prop;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");
    }
    
    public String get_go_rest_get_endpoint(){
        String prop = properties.getProperty("go_rest_get_endpoint");
        if(prop != null) return prop;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");
    }
    
    public String get_go_rest_post_endpoint(){
        String prop = properties.getProperty("go_rest_post_endpoint");
        if(prop != null) return prop;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");
    }
    
    public String get_go_rest_reportname(){
        String prop = properties.getProperty("go_rest_reportname");
        if(prop != null) return prop;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");
    }
    
    public String get_go_rest_DocumentTitle(){
        String prop = properties.getProperty("go_rest_DocumentTitle");
        if(prop != null) return prop;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");
    }
    
    
    
    
    
    
    
}
