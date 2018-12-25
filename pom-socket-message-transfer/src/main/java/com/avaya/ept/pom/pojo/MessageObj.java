package com.avaya.ept.pom.pojo;

import java.io.IOException;
import java.util.HashMap;

import javax.websocket.Session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MessageObj {
    private static final Logger logger = LogManager.getLogger(MessageObj.class);
    private Type type;
    private String method;
    private HashMap<String, String> parameterMap = new HashMap<String, String>();
    
    public MessageObj() {
        
    }
    
    public MessageObj(Type type, String method, HashMap<String, String> parameterMap) {
        MessageObj message = new MessageObj();
        message.setType(type);
        message.setMethod(method);
        message.setParameterMap(parameterMap);
    }
    
    public enum Type {
        REQEUST, EVENT
    }
    
    public String getMethod() {
        return method;
    }
    
    public void setMethod(String method) {
        this.method = method;
    }
    
    public HashMap<String, String> getParameterMap() {
        return parameterMap;
    }
    
    public void setParameterMap(HashMap<String, String> parameterMap) {
        this.parameterMap = parameterMap;
    }
    
    public Type getType() {
        return type;
    }
    
    public void setType(Type type) {
        this.type = type;
    }
    
    public static void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            logger.error("Send message to frontend error");
            logger.error(e);
        }
    }
}
