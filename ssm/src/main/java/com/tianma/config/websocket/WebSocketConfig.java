package com.tianma.config.websocket;

import com.tianma.config.websocket.EchoWebSocketHandler;
import com.tianma.config.websocket.SockTaskRunner;
import com.tianma.config.websocket.SocketSessionInfo;
import com.tianma.service.websocket.EchoService;
import com.tianma.service.websocket.EchoServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.ExecutorSubscribableChannel;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(echoWebSocketHandler(), "/echo")
				.setAllowedOrigins("*")
				.withSockJS()
				.setHeartbeatTime(30000)
				.setSessionCookieNeeded(true)
				.setTaskScheduler(taskScheduler());
	}

	@Bean
	public WebSocketHandler echoWebSocketHandler() {
		return new EchoWebSocketHandler(echoService());
	}

	@Bean
	public EchoService echoService() {
		return new EchoServiceImpl("Echo Server： \"%s\"?");
	}

	@Bean
	public ConcurrentTaskScheduler taskScheduler() {
		ConcurrentTaskScheduler task = new ConcurrentTaskScheduler();
		task.scheduleAtFixedRate(new SockTaskRunner(), 1000*60*SocketSessionInfo.SESSION_INVALID);
		return task;
	}
}
