package com.virtualthread.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Slf4j
@SpringBootApplication
public class VirtualThreadDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(VirtualThreadDemoApplication.class, args);
	}

	@Bean
	TomcatProtocolHandlerCustomizer<?> protocolHandlerCustomizer(){
		return protocolHandler -> {
			log.info("Configuring" + protocolHandler + "to use VirtualThreadPerTaskExecutor");
			protocolHandler.setExecutor(
					Executors.newVirtualThreadPerTaskExecutor()
			);
		};
	}
}
