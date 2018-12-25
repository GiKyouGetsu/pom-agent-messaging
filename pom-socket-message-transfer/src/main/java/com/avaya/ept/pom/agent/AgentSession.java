package com.avaya.ept.pom.agent;

import javax.websocket.Session;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.avaya.ept.pom.worker.SDKWorker;

public class AgentSession {
    
    private static final Logger logger = LogManager.getLogger(AgentSession.class);
    
    private String agentID;
    private String station;
    
    private String loginStatus;
    private String socketSessionId;
    private Session socketSession;
    private SDKWorker agentWorker;
    
    public String getAgentID() {
        return agentID;
    }
    
    public void setAgentID(String agentID) {
        this.agentID = agentID;
    }
    
    public String getStation() {
        return station;
    }
    
    public void setStation(String station) {
        this.station = station;
    }
    
    public String getLoginStatus() {
        return loginStatus;
    }
    
    public void setLoginStatus(String loginStatus) {
        this.loginStatus = loginStatus;
    }
    
    public String getSocketSessionId() {
        return socketSessionId;
    }
    
    public void setSocketSessionId(String socketSessionId) {
        this.socketSessionId = socketSessionId;
    }
    
    public Session getSocketSession() {
        return socketSession;
    }
    
    public void setSocketSession(Session socketSession) {
        this.socketSession = socketSession;
    }
    
    public SDKWorker getAgentWorker() {
        return agentWorker;
    }

    public void setAgentWorker(SDKWorker agentWorker) {
        this.agentWorker = agentWorker;
    }

    public void sendText(String message) {
        logger.info("sendText message is:" + message);
        socketSession.getAsyncRemote().sendText(message);
    }
}
