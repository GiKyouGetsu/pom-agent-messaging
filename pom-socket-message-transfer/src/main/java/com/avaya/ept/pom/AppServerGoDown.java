package com.avaya.ept.pom;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;

import com.avaya.ept.pom.worker.ReqCommandHandler;
import com.avaya.sdk.POMAgentFactory;

@Component
public class AppServerGoDown implements DisposableBean, ExitCodeGenerator {
    
    private static final Logger logger = LogManager.getLogger(AppServerGoDown.class);
    private static final ReqCommandHandler reqCommandHandler = new ReqCommandHandler();
    @Override
    public int getExitCode() {
        logger.info("Exit code is: " + " -1");
        return -1;
    }
    
    @Override
    public void destroy() throws Exception {
        logger.info("Server is down");
        reqCommandHandler.logffAllAgent();
        POMAgentFactory.deinit();
    }
}
