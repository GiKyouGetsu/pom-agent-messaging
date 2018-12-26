package com.avaya.ept.server;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.avaya.ept.pom.pojo.APICommand;
import com.avaya.ept.pom.pojo.MessageObj;
import com.avaya.ept.pom.pojo.MessageObj.Type;
import com.avaya.ept.pom.worker.ReqCommandHandler;
import com.avaya.ept.util.ConvertUtil;

@ServerEndpoint(value = "/socketServer")
@Component
public class SocketServer {
    
    private static final Logger logger = LogManager.getLogger(SocketServer.class);
    private static final ReqCommandHandler reqCommandHandler = new ReqCommandHandler();
    
//    private Session session;

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
//        this.session = session;
        
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
                logger.info("POM REQUEST Command:" + msgobj.getMethod());
                switch (msgobj.getMethod()) {
                case APICommand.AGTLogon:
                    reqCommandHandler.handleAGTLogon(msgobj, session);
                    break;
                case APICommand.AGTLogoff:
                    reqCommandHandler.handleAGTLogout(msgobj, session);
                    break;
                case APICommand.AGTGetErrorString:
                    reqCommandHandler.handleAGTGetErrorString(msgobj, session);
                    break;
                case APICommand.AGTStateChange:
                    reqCommandHandler.handleAGTStateChange(msgobj, session);
                    break;
                case APICommand.AGTHoldCall:
                    reqCommandHandler.handleAGTHoldCall(msgobj, session);
                    break;
                case APICommand.AGTUnHoldCall:
                    reqCommandHandler.handleAGTUnHoldCall(msgobj, session);
                    break;
                case APICommand.AGTReleaseLine:
                    reqCommandHandler.handleAGTReleaseLine(msgobj, session);
                    break;
                case APICommand.AGTGetCompCodes:
                    reqCommandHandler.handleAGTGetCompCodes(msgobj, session);
                    break;
                case APICommand.AGTWrapupContact: 
                    reqCommandHandler.handleAGTWrapupContact(msgobj, session);
                    break;
                case APICommand.AGTExtendWrapup:
                    reqCommandHandler.handleAGTExtendWrapup(msgobj, session);
                    break;
                case APICommand.AGTGetConsultTypes:
                    reqCommandHandler.handleAGTGetConsultTypes(msgobj, session);
                    break;
                case APICommand.AGTGetConsultDestsForType:
                    reqCommandHandler.handleAGTGetConsultDestsForType(msgobj, session);
                    break;
                case APICommand.AGTConsultCall:
                    reqCommandHandler.handleAGTConsultCall(msgobj, session);
                    break;
                case APICommand.AGTCompleteTransfer:
                    reqCommandHandler.handleAGTCompleteTransfer(msgobj, session);
                    break;
                case APICommand.AGTCancelConsult:
                    reqCommandHandler.handleAGTCancelConsult(msgobj, session);
                    break;
                case APICommand.AGTStartConf:
                    reqCommandHandler.handleAGTStartConf(msgobj, session);
                    break;
                case APICommand.AGTEndConf:
                    reqCommandHandler.handleAGTEndConf(msgobj, session);
                    break;
                case APICommand.AGTConfChangeOwnership:
                    reqCommandHandler.handleAGTConfChangeOwnership(msgobj, session);
                    break;
                case APICommand.AGTRedial:
                    reqCommandHandler.handleAGTRedial(msgobj, session);
                    break;
                case APICommand.AGTSendDTMF:
                    reqCommandHandler.handleAGTSendDTMF(msgobj, session);
                    break;
                case "AGTGetCallbackTypes":
                    reqCommandHandler.handleAGTGetCallbackTypes(msgobj, session);
                    break;
                case APICommand.AGTGetCallbackTypesForTypes:
                    reqCommandHandler.handleAGTGetCallbackDestsForType(msgobj, session);
                    break;
                case APICommand.AGTCreateCallback:
                    reqCommandHandler.handleAGTCreateCallback(msgobj, session);
                    break;
                case APICommand.AGTGetErrorInfo:
                    reqCommandHandler.handleAGTGetErrorInfo(msgobj, session);
                    break;
                case APICommand.AGTPreviewDial:
                    reqCommandHandler.handleAGTPreviewDial(msgobj, session);
                    break;
                case APICommand.AGTPreviewCancel:
                    reqCommandHandler.handleAGTPreviewCancel(msgobj, session);
                    break;
                case APICommand.AGTGetCustomerDetails:
                    reqCommandHandler.handleAGTGetCustomerDetails(msgobj, session);
                    break;
                case APICommand.AGTSetCustomerDetail:
                    reqCommandHandler.handleAGTSetCustomerDetail(msgobj, session);
                    break;
                case APICommand.AGTBlendToInbound:
                    reqCommandHandler.handleAGTBlendToInbound(msgobj, session);
                    break;
                case APICommand.AGTBlendToOutbound:
                    reqCommandHandler.handleAGTBlendToOutbound(msgobj, session);
                    break;
                case APICommand.AGTNailupAgent:
                    reqCommandHandler.handleAGTNailupAgent(msgobj, session);
                    break;
                case APICommand.AGTReadyForNailup:
                    reqCommandHandler.handleAGTReadyForNailup(msgobj, session);
                    break;
                case APICommand.AGTLostNailing:
                    reqCommandHandler.handleAGTLostNailing(msgobj, session);
                    break;
                case APICommand.AGTPendingLogout:
                    reqCommandHandler.handleAGTPendingLogout(msgobj, session);
                    break;
                case APICommand.AGTAddAgentNote:
                    reqCommandHandler.handleAGTAddAgentNote(msgobj, session);
                    break;
                case APICommand.AGTRefreshAgentNotes:
                    reqCommandHandler.handleAGTRefreshAgentNotes(msgobj, session);
                    break;
                case APICommand.AGTGetTimezones:
                    reqCommandHandler.handleAGTGetTimezones(msgobj, session);
                    break;
                case APICommand.AGTAvailableForNailup:
                    reqCommandHandler.handleAGTAvailableForNailup(msgobj, session);
                    break;
                case APICommand.AGTAgentDisconnected:
                    reqCommandHandler.handleAGTAgentDisconnected(msgobj, session);
                    break;
                case APICommand.AGTAddToDNC:
                    reqCommandHandler.handleAGTAddToDNC(msgobj, session);
                    break;
                case APICommand.AGTIsInDNC:
                    reqCommandHandler.handleAGTIsInDNC(msgobj, session);
                    break;
                case APICommand.AGTGetZoneList:
                    reqCommandHandler.handleAGTGetZoneList(msgobj, session);
                    break;
                case APICommand.AGTGetContactAttributes:
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
    public void onClose(Session session, CloseReason reaon){
        logger.info("Session id = [" + session.getId() + "] socket closed" );
        logger.info("Session id = [" + session.getId() + "] CloseReason= " + reaon.getReasonPhrase() );
        
        reqCommandHandler.handleException(session);
    }

    /**
     * Error with socket connected 
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
//        reqCommandHandler.handleException(session);
        logger.error("Session id = [" + session.getId() + "] socket onError");
        logger.error("Error: ", error);
    }
}

