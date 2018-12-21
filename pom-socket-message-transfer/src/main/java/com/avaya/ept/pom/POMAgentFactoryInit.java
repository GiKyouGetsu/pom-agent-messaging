package com.avaya.ept.pom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.avaya.ept.pom.cert.SSLContextFactory;
import com.avaya.sdk.PAMSocketInfo;
import com.avaya.sdk.POMAgentFactory;

@Component
public class POMAgentFactoryInit implements CommandLineRunner {
	
	private static final Logger logger = LogManager.getLogger(POMAgentFactoryInit.class);
	
//	private static final Logger log = LoggerFactory.getLogger(POMAgentFactoryInit.class);

	
	@Value("${pom.cert.path}")
	private String pomCertPath;
	
	@Value("${pom.cert.path}")
	private String pomCertPassword;
	
	@Value("${pom.server.address}")
	private String pomServerAddress;
	
	@Value("${pom.server.port}")
	private int pomServerPort;

	@Override
	public void run(String... arg0) throws Exception {
		PAMSocketInfo pamSktInfo = new PAMSocketInfo();
		pamSktInfo.ipAddress = pomServerAddress;
		pamSktInfo.port = pomServerPort;
		
		PAMSocketInfo [] pamSktInfoArry = new PAMSocketInfo[1];
		pamSktInfoArry[0] = pamSktInfo;
		
		logger.info("POM factory info: [ " + "Pom server= " + pomServerAddress + "; POM server port= " + pomServerPort + "; POM cert path= " + pomCertPath + "; POM CERT password= " + pomCertPassword + " ]");
		logger.info("Init POMAgentFactory");
		POMAgentFactory.init(pamSktInfoArry, ILoggerImpl.getTracer(), SSLContextFactory.getSSLContext(pomCertPath, pomCertPassword));
		
		
	}

}
