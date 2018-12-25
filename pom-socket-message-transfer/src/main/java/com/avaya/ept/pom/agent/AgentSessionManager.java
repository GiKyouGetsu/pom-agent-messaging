package com.avaya.ept.pom.agent;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AgentSessionManager {
    
    private static final Logger logger = LogManager.getLogger(AgentSessionManager.class);
    
    private ConcurrentHashMap<String, AgentSession> agentSessionMap;
    private static AgentSessionManager agentSessionManager = new AgentSessionManager();
    
    private AgentSessionManager() {
    }
    
    public static AgentSessionManager getInstance() {
        return agentSessionManager;
    }
    
    public void addAgentSession(AgentSession agentSession) {
        if (agentSessionMap == null) {
            agentSessionMap = new ConcurrentHashMap<>();
        }
        
        if (agentSessionMap.containsKey(agentSession.getSocketSessionId())) {
            agentSessionMap.remove(agentSession.getSocketSessionId());
        }
        agentSessionMap.put(agentSession.getSocketSessionId(), agentSession);
        
    }
    
    public void removeAgentSession(String socketSessionId) {
        logger.info("begin to remove session");
        AgentSession agentSession = getAgentSessionBySocketSessionId(socketSessionId);
        if (agentSession != null) {
            if (agentSessionMap.containsKey(agentSession.getSocketSessionId())) {
                logger.info("get remove session");
                agentSessionMap.remove(agentSession.getSocketSessionId());
            }
        }
    }
    
    public AgentSession getAgentSessionBySocketSessionId(String socketSessionId) {
        
        return agentSessionMap.get(socketSessionId);
    }
    
    public ConcurrentHashMap<String, AgentSession> getagentSessionMap() {
        return agentSessionMap;
    }
}
