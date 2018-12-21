package com.avaya.ept.server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@ServerEndpoint(value = "/socketServer/{agent}/{password}")
@Component
public class SocketServer {

	private static final Logger LOGGER = LogManager.getLogger(SocketServer.class);
	private Session session;
	private static Map<String,Session> sessionPool = new ConcurrentHashMap<String,Session>();
	private static Map<String,String> sessionIds = new HashMap<String,String>();

	/**
	 * connect the websocket
	 * @param session
	 * @param userid
	 * @param password
	 */
	@OnOpen
	public void open(Session session,@PathParam(value="agent") String agent, @PathParam (value = "password") String password){
		LOGGER.info("Agent id = " + agent + " connected the socket");
		this.session = session;
		sessionPool.put(agent, session);
	}

	/**
	 * 收到信息时触发
	 * @param message
	 */
	@OnMessage
	public void onMessage(String message){
		sendMessage(session.getId() +"<--"+message,"niezhiliang9595");
		System.out.println("发送人:"+sessionIds.get(session.getId())+"内容:"+message);
	}

	/**
	 * 连接关闭触发
	 */
	@OnClose
	public void onClose(){
		sessionPool.remove(sessionIds.get(session.getId()));
		sessionIds.remove(session.getId());
	}

	/**
	 * 发生错误时触发
	 * @param session
	 * @param error
	 */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

	/**
	 *信息发送的方法
	 * @param message
	 * @param userId
	 */
	public static void sendMessage(String message,String userId){
		Session s = sessionPool.get(userId);
		if(s!=null){
			try {
				s.getBasicRemote().sendText(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取当前连接数
	 * @return
	 */
	public static int getOnlineNum(){
		if(sessionIds.values().contains("niezhiliang9595")) {

			return sessionPool.size()-1;
		}
		return sessionPool.size();
	}

	/**
	 * 获取在线用户名以逗号隔开
	 * @return
	 */
	public static String getOnlineUsers(){
		StringBuffer users = new StringBuffer();
	    for (String key : sessionIds.keySet()) {//niezhiliang9595是服务端自己的连接，不能算在线人数
	    	if (!"niezhiliang9595".equals(sessionIds.get(key)))
			{
				users.append(sessionIds.get(key)+",");
			}
		}
	    return users.toString();
	}
}
