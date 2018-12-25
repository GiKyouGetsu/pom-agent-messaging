package com.avaya.ept.server;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.avaya.ept.pom.pojo.MessageObj;
import com.avaya.ept.pom.pojo.MessageObj.Type;
import com.avaya.ept.pom.worker.ReqCommandHandler;
import com.avaya.ept.util.ConvertUtil;

@ServerEndpoint(value = "/socketServer")
@Component
public class SocketServer {
    
    private static final Logger logger = LogManager.getLogger(SocketServer.class);
    private static final ReqCommandHandler reqCommandHandler = new ReqCommandHandler();
    
    private Session session;

    /**
     * connect the websocket
     * @param session
     * @param userid
     * @param password
     */
    @OnOpen
    public void open(Session session){
        logger.info("session connected");
        logger.info("On messgage session id: " + session.getId());
//        LOGGER.info("Agent id = " + agent + " connected the socket");
        this.session = session;
        
//        sessionPool.put(agent, session);
    }

    /**
     * Received the message
     * @param message
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        MessageObj msgobj = null;
        try {
            msgobj = ConvertUtil.parseJson(message);
            if (msgobj.getType().equals(Type.REQEUST)) {
                logger.info("POM REQUEST METHOD::" + msgobj.getMethod());
                switch (msgobj.getMethod()) {
                case "AGTLogon":
                    reqCommandHandler.handleAGTLogon(msgobj, session);
                    break;
                case "AGTLogoff":
                    reqCommandHandler.handleAGTLogout(msgobj, session);
                    break;
                case "AGTGetErrorString":
                    reqCommandHandler.handleAGTGetErrorString(msgobj, session);
                    break;
                case "AGTStateChange":
                    reqCommandHandler.handleAGTStateChange(msgobj, session);
                    break;
                case "AGTHoldCall":
                    reqCommandHandler.handleAGTHoldCall(msgobj, session);
                    break;
                case "AGTUnHoldCall":
                    reqCommandHandler.handleAGTUnHoldCall(msgobj, session);
                    break;
                case "AGTReleaseLine":
                    reqCommandHandler.handleAGTReleaseLine(msgobj, session);
                    break;
                case "AGTGetCompCodes":
                    reqCommandHandler.handleAGTGetCompCodes(msgobj, session);
                    break;
                case "AGTWrapupContact": 
                    reqCommandHandler.handleAGTWrapupContact(msgobj, session);
                    break;
                case "AGTExtendWrapup":
                    reqCommandHandler.handleAGTExtendWrapup(msgobj, session);
                    break;
                case "AGTGetConsultTypes":
                    reqCommandHandler.handleAGTGetConsultTypes(msgobj, session);
                    break;
                case "AGTGetConsultDestsForType":
                    reqCommandHandler.handleAGTGetConsultDestsForType(msgobj, session);
                    break;
                case "AGTConsultCall":
                    reqCommandHandler.handleAGTConsultCall(msgobj, session);
                    break;
                case "AGTCompleteTransfer":
                    reqCommandHandler.handleAGTCompleteTransfer(msgobj, session);
                    break;
                case "AGTCancelConsult":
                    reqCommandHandler.handleAGTCancelConsult(msgobj, session);
                    break;
                case "AGTStartConf":
                    reqCommandHandler.handleAGTStartConf(msgobj, session);
                    break;
                case "AGTEndConf":
                    reqCommandHandler.handleAGTEndConf(msgobj, session);
                    break;
                case "AGTConfChangeOwnership":
                    reqCommandHandler.handleAGTConfChangeOwnership(msgobj, session);
                    break;
                case "AGTRedial":
                    reqCommandHandler.handleAGTRedial(msgobj, session);
                    break;
                case "AGTSendDTMF":
                    reqCommandHandler.handleAGTSendDTMF(msgobj, session);
                    break;
                case "AGTGetCallbackTypes":
                    reqCommandHandler.handleAGTGetCallbackTypes(msgobj, session);
                    break;
                case "AGTGetCallbackTypesForTypes":
                    reqCommandHandler.handleAGTGetCallbackTypesForTypes(msgobj, session);
                    break;
                case "AGTCreateCallback":
                    reqCommandHandler.handleAGTCreateCallback(msgobj, session);
                    break;
                case "AGTGetErrorInfo":
                    reqCommandHandler.handleAGTGetErrorInfo(msgobj, session);
                    break;
                case "AGTPreviewDial":
                    reqCommandHandler.handleAGTPreviewDial(msgobj, session);
                    break;
                case "AGTPreviewCancel":
                    reqCommandHandler.handleAGTPreviewCancel(msgobj, session);
                    break;
                case "AGTGetCustomerDetails":
                    reqCommandHandler.handleAGTGetCustomerDetails(msgobj, session);
                    break;
                case "AGTSetCustomerDetail":
                    reqCommandHandler.handleAGTSetCustomerDetail(msgobj, session);
                    break;
                case "AGTBlendToInbound":
                    reqCommandHandler.handleAGTBlendToInbound(msgobj, session);
                    break;
                case "AGTBlendToOutbound":
                    reqCommandHandler.handleAGTBlendToOutbound(msgobj, session);
                    break;
                case "AGTNailupAgent":
                    reqCommandHandler.handleAGTNailupAgent(msgobj, session);
                    break;
                case "AGTReadyForNailup":
                    reqCommandHandler.handleAGTReadyForNailup(msgobj, session);
                    break;
                case "AGTLostNailing":
                    reqCommandHandler.handleAGTLostNailing(msgobj, session);
                    break;
                case "AGTPendingLogout":
                    reqCommandHandler.handleAGTPendingLogout(msgobj, session);
                    break;
                case "AGTAddAgentNote":
                    reqCommandHandler.handleAGTAddAgentNote(msgobj, session);
                    break;
                case "AGTRefreshAgentNotes":
                    reqCommandHandler.handleAGTRefreshAgentNotes(msgobj, session);
                    break;
                case "AGTGetTimezones":
                    reqCommandHandler.handleAGTGetTimezones(msgobj, session);
                    break;
                case "AGTAvailableForNailup":
                    reqCommandHandler.handleAGTAvailableForNailup(msgobj, session);
                    break;
                case "AGTAgentDisconnected":
                    reqCommandHandler.handleAGTAgentDisconnected(msgobj, session);
                    break;
                case "AGTAddToDNC":
                    reqCommandHandler.handleAGTAddToDNC(msgobj, session);
                    break;
                case "AGTIsInDNC":
                    reqCommandHandler.handleAGTIsInDNC(msgobj, session);
                    break;
                case "AGTGetZoneList":
                    reqCommandHandler.handleAGTGetZoneList(msgobj, session);
                    break;
                case "AGTGetContactAttributes":
                    reqCommandHandler.handleAGTGetContactAttributes(msgobj, session);
                    break;
                default:
                    logger.info("Unknown Command: " + msgobj.getMethod());
                    break;
                }
            }
        } catch (Exception e) {
            logger.info("On message error");
            logger.info(e);
        }
        
        logger.info("On messgage session id: " + session.getId());
    }

    /**
     * Closed the socket connect
     */
    @OnClose
    public void onClose(){
        logger.info("Session id = [" + this.session.getId() + "] socket closed" );
        reqCommandHandler.handleException(session);
    }

    /**
     * Error with socket connected
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        reqCommandHandler.handleException(session);
        logger.error("Session id = [" + this.session.getId() + "] socket onError");
        logger.error("Error: ", error);
    }
}
