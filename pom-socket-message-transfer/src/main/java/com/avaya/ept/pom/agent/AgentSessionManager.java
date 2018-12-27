package com.avaya.ept.pom.agent;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.StringUtils;

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
    
    public void removeAgentSessionBySocketsessionId(String socketSessionId) {
        logger.info("begin to remove session");
        AgentSession agentSession = getAgentSessionBySocketSessionId(socketSessionId);
        if (agentSession != null) {
            if (agentSessionMap.containsKey(agentSession.getSocketSessionId())) {
                agentSessionMap.remove(agentSession.getSocketSessionId());
                logger.info("removeAgentSessionBySocketsessionId():: Successful => Agent ID= [" + agentSession.getAgentID() + "]");
            }
        }
    }
    
    public void removeAgentSession(AgentSession agentSession) {
        if (agentSession != null) {
            if (agentSessionMap.containsKey(agentSession.getSocketSessionId())) {
                agentSessionMap.remove(agentSession.getSocketSessionId());
                logger.info("removeAgentSession():: Successful => Agent ID= [" + agentSession.getAgentID() + "]");
            }
        }
    }
    
    public AgentSession getAgentSessionBySocketSessionId(String socketSessionId) {
        
        return agentSessionMap.get(socketSessionId);
    }
    
    public ConcurrentHashMap<String, AgentSession> getagentSessionMap() {
        return agentSessionMap;
    }
    
    
    /**
     * @param agentId
     * @return true is mean agent is already logon or agent id is null or empty
     *         false is mean agent is not logon
     */
    public boolean agentIsLogon(String agentId) {
        
        if (StringUtils.isEmpty(agentId)) {
            return true;
        }
        
        Iterator<Entry<String, AgentSession>> iter = agentSessionMap.entrySet().iterator();
        while (iter.hasNext()) {
            Entry<String, AgentSession> entry = (Entry<String, AgentSession>) iter.next();
            AgentSession agentSession = entry.getValue();
            if (agentSession.getAgentID().equals(agentId)) {
                return true;
            }
        }
        return false;
    }
}
