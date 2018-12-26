package com.avaya.ept.util;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.avaya.ept.pom.pojo.Constant;
import com.avaya.ept.pom.pojo.MessageObj;
import com.avaya.ept.pom.pojo.MessageObj.Type;

public class ConvertUtil {
    
    private final static Logger logger = LogManager.getLogger(ConvertUtil.class);
    
    public static MessageObj parseJson(String message) {
        JSONObject json = new JSONObject(message);
        
        JSONObject requestMessage = json.getJSONObject(Constant.REQUEST);
        // System.out.println("*** REQUEST" + ": " + requestMessage);
        // logger.info("*** REQUEST" + ": " + requestMessage);
        MessageObj msgObj = new MessageObj();
        
        if (requestMessage != null) {
            msgObj.setType(Type.REQEUST);
            
            String methodName = requestMessage.getString("METHOD");
            msgObj.setMethod(methodName);
            logger.info("*** REQUEST methodName" + ": " + methodName);
            JSONArray requestParams = requestMessage.getJSONArray("Parameters");
            
            HashMap<String, String> paraMap = new HashMap<String, String>();
            String paramerName;
            String paramerValue;
            int len = requestParams.length();
            for (int i = 0; i < len; i++) {
                JSONObject action = (JSONObject) requestParams.get(i);
                paramerName = (String) action.get("name");
                logger.info("*** REQUEST paramerName" + ": " + paramerName);
                paramerValue = (String) action.get("value");
                if (paramerName.equals("agentPassword")) {
                    logger.info("*** REQUEST paramerValue" + ": " + "******");
                } else {
                    logger.info("*** REQUEST paramerValue" + ": " + paramerValue);
                }
                paraMap.put(paramerName, paramerValue);
            }
            msgObj.setParameterMap(paraMap);
            
        }
        
        return msgObj;
        
    }
    
    public static String parseString(MessageObj message) {
        
        JSONObject json = new JSONObject();
        
        JSONObject eventMessage = new JSONObject();
        eventMessage.put("METHOD", message.getMethod());
        
        JSONArray eventParams = new JSONArray();
        eventParams.put(message.getParameterMap());
        eventMessage.put("Parameters", eventParams);
        json.put(Constant.EVENT, eventMessage);
        
        return json.toString();
    }
    
}
