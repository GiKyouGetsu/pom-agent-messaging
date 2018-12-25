package com.avaya.ept.pom.cert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class SSLContextFactory {

    private static final Logger logger  = LogManager.getLogger(SSLContextFactory.class);
    
    public static SSLContext getIgnoreSSLContext() {
        logger.info("Skip pom server cert");
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL");
            TrustManager[] tm = { new MyX509TrustManager() };
            sslContext.init(null, tm, new java.security.SecureRandom());
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            logger.error(e);
        }
        return sslContext;
    }
    
    public static SSLContext getSSLContext(String truststore, String password)
    {
        FileInputStream stream = null;
        try
        {
            char[] passphrase = password.toCharArray();
            KeyStore ks = KeyStore.getInstance("JKS");
            stream = new FileInputStream(truststore);
            ks.load(stream, passphrase);
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(ks, passphrase);
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(ks);
            SSLContext sslContext = SSLContext.getInstance("TLSv1.2");
            sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
            stream.close();
            return sslContext;
            
        } catch (FileNotFoundException e) {
            logger.error(e);
            logger.error("Truststore file is not found will skip trust store");
            return getIgnoreSSLContext();
        } catch(Exception e) {
            logger.error(e);
            logger.error("Unexception happened will skip trust store");
            return getIgnoreSSLContext();
        } finally {
            try
            {
                if (stream != null)
                    stream.close();
            } catch (IOException e)
            {
                logger.error(e);
            }
        }
    }
}
