package com.avaya.ept.pom.pojo;

public class Constant {

	public static String REQUEST = "REQUEST";
	public static String EVENT = "EVENT";
	public static String METHOD = "METHOD";
	public static String PARAMETERS = "PARAMETERS";
	
	//EVENT field
	public static String EVENT_AGENT_CONNECTED = "Agent_Connected";
	public static String EVENT_AGENT_STATE = "Agent_State";
	public static String EVENT_CALL_UCID = "Call_UCID";
	public static String EVENT_CALL_CALLING = "Call_Calling";
	public static String EVENT_CALL_CALLED = "Call_Called";
	public static String EVENT_CALL_UUI = "UUI";
	public static String EVENT_CALL_ID = "CallID";
	
	//EVENT name
	public static String EVENT_ON_LOGIN = "OnLogin";
	public static String EVENT_ON_NOT_READY = "OnNotReady";
	public static String EVENT_ON_READY = "OnReady";
	public static String EVENT_ON_BUSY = "OnBusy";
	public static String EVENT_ON_UNKNOWN = "OnUnKnown";
	public static String EVENT_ON_LOGOUT = "OnLogout";
	public static String EVENT_ON_WORK_READY="OnWorkReady";
	public static String EVENT_ON_WORK_NOT_READY="OnWorkNotReady";
	
	
	public static String EVENT_ONERROR = "OnError";
	
	public static String EVENT_ON_LOGIN_ERROR = "OnLoginError";
	
	public static String EVENT_ON_LOGINOUT_ERROR = "OnLoginOutError";
	
	public static String EVENT_ON_SETREADY_ERROR = "OnReadyError";
	
	public static String EVENT_ON_SETNOTREADY_ERROR = "OnNotReadyError";
	
	public static String EVENT_ON_ANSWER_ERROR = "OnAnswerError";
	
	public static String EVENT_ON_HUNGUP_ERROR = "OnHungUpError";
	
	public static String EVENT_ON_HOLDE_ERROR = "OnHoldError";
	
	public static String EVENT_ON_UNHOLDE_ERROR = "OnUnHoldError";
	
	public static String EVENT_ON_MAKECALL_ERROR = "OnMakeCallError";
	
	public static String EVENT_ON_CONFERENCEJOIN_ERROR = "OnConferenceJoinError";
	
	public static String EVENT_ON_ENDCONFERENCE_ERROR = "OnEndConferenceError";
	
	public static String EVENT_ON_TRANSFER_ERROR = "OnTransferError";
	
	public static String EVENT_ON_TRANSFERBYCONSULT_ERROR = "OnTransferByConsultError";
	
	public static String EVENT_ON_CONSULTATIONCALL_ERROR = "OnConsultationCallError";
	
	public static String EVENT_ON_CONSULTATIONRECONECT_ERROR = "OnConsultationReconnectError";
	
	public static String EVENT_ON_SENDDTMF_ERROR = "OnSendDtmfError";
}
