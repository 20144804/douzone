package chatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;

import org.json.JSONObject;

import com.oracle.wls.shaded.org.apache.xalan.xsltc.compiler.Pattern;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
// WebSocket의 호스트 주소 설정
@ServerEndpoint("/websocket")
public class Websocket {
	  // 접속 된 클라이언트 WebSocket session 관리 리스트
//	  private static List<Session> sessionUsers = Collections.synchronizedList(new ArrayList<>());
	  private static HashMap<Session,String> sessionUsers = new HashMap<Session,String>();
	  
	  
	  
	  // 메시지에서 유저 명을 취득하기 위한 정규식 표현
	  // WebSocket으로 브라우저가 접속하면 요청되는 함수);
  @OnOpen
  //사용자 세션 정보
  public void handleOpen(Session userSession) {
    // 콘솔에 접속 로그를 출력한다.
	
    System.out.println("client is now connected 1...");
    sessionUsers.put(userSession,"");
  }
  // WebSocket으로 메시지가 오면 요청되는 함수
  @OnMessage
  //Session userSession 브라우저에서 보낸사람
  public void handleMessage(String message, Session userSession) throws IOException {
	  //message 구조 : {userName:홍길동, op:"특정기능(귓속말등)" ,message:안녕}
	  JSONObject jsonObject =new JSONObject(message);
	  String roomName =jsonObject.getString("roomName");
	  String roomAdd = jsonObject.getString("roomAdd");
	  System.out.println("roomName:"+roomName);
	  System.out.println("roomAdd:"+roomAdd);
	
	  
	  if (roomAdd.equals("1")) {
	
		  sessionUsers.forEach((session,value) -> {
		      if (session == userSession) {
		    	
		    		  sessionUsers.replace(userSession, roomName);
		    		  System.out.print(roomName);
		      }
		    });
		  return;
	  } else {
	
	  }
	  
	  
	  
	  String sendMessage =jsonObject.getString("userName")+" : " + jsonObject.getString("message");
	  // 메시지 내용을 콘솔에 출력한다.
		/*
		 * System.out.println(message);
		 *  // 초기 유저 명 String name = "anonymous"; // 메시지로 유저
		 * 명을 추출한다. 
		 * Matcher matcher = pattern.matcher(message);
		 *  // 메시지 예: {{유저명}}메시지 if
		 * (matcher.find()) { name = matcher.group(); } // 클로져를 위해 변수의 상수화 final String
		 * msg = message.replaceAll(pattern.pattern(), ""); final String username =
		 * name.replaceFirst("^\\{\\{", "").replaceFirst("\\}\\}$", ""); // session관리
		 * 리스트에서 Session을 취득한다.
		 */	  
	  
	  sessionUsers.forEach((session,value) -> {
	      // 리스트에 있는 세션과 메시지를 보낸 세션이 같으면 메시지 송신할 필요없다.
	      if (session == userSession) {
	        return;
	      }
	      try {
	    	  
	        // 리스트에 있는 모든 세션(메시지 보낸 유저 제외)에 메시지를 보낸다. (형식: 유저명 => 메시지)
	    	if(value.equals(roomName))
	    		session.getBasicRemote().sendText(sendMessage);
	      } catch (IOException e) {
	        // 에러가 발생하면 콘솔에 표시한다.
	        e.printStackTrace();
	      }
	    });
	  }
  
  // WebSocket과 브라우저가 접속이 끊기면 요청되는 함수
  @OnClose
  public void handleClose(Session userSession) {
	    // session 리스트로 접속 끊은 세션을 제거한다.
	    sessionUsers.remove(userSession);
	    // 콘솔에 접속 끊김 로그를 출력한다.
	    System.out.println("client is now disconnected...");
	  }
  // WebSocket과 브라우저 간에 통신 에러가 발생하면 요청되는 함수.
  @OnError
  public void handleError(Throwable t) {
    // 콘솔에 에러를 표시한다.
    t.printStackTrace();
  }
}