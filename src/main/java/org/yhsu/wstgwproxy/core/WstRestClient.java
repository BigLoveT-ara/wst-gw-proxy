package org.yhsu.wstgwproxy.core;

import com.iflytek.wst.gateway.sdk.client.ApacheHttpClient;
import com.iflytek.wst.gateway.sdk.enums.Scheme;
import com.iflytek.wst.gateway.sdk.model.HttpClientBuilderParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.net.ssl.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

@Component
public class WstRestClient extends ApacheHttpClient {

    @Value("${wst.gateway.app-key}")
    private String appKey;

    @Value("${wst.gateway.app-secret}")
    private String appSecret;

    @Value("${wst.gateway.host}")
    private String host;

    @Value("${wst.gateway.context-path}")
    private String contextPath;

    public static final Scheme scheme = Scheme.HTTP;

    @Value("${http.client.connect-timeout}")
    private int connectTimeOut;

    @Value("${http.client.read-timeout}")
    private int readTimeOut;

    @PostConstruct
    public void init() {
        // HTTP Client init
        HttpClientBuilderParams httpClientBuilderParams = new HttpClientBuilderParams();
        httpClientBuilderParams.setAppKey(appKey);
        httpClientBuilderParams.setAppSecret(appSecret);
        httpClientBuilderParams.setScheme(scheme);
        httpClientBuilderParams.setHost(host);
        httpClientBuilderParams.setContextPath(contextPath);

        // HTTPS客户端需要单独设置，禁用证书校验
        if (scheme == Scheme.HTTPS) {
            //HTTPS Client init
            /**
             * HTTPS request use DO_NOT_VERIFY mode only for demo
             * Suggest verify for security
             */
            X509TrustManager xtm = new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    X509Certificate[] x509Certificates = new X509Certificate[0];
                    return x509Certificates;
                }
            };

            SSLContext sslContext = null;
            try {
                sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, new TrustManager[]{xtm}, new SecureRandom());

            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            } catch (KeyManagementException e) {
                throw new RuntimeException(e);
            }
            HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };

            httpClientBuilderParams.setSslSocketFactory(sslContext.getSocketFactory());
            httpClientBuilderParams.setX509TrustManager(xtm);
            httpClientBuilderParams.setHostnameVerifier(DO_NOT_VERIFY);
            //超时时间设置
            httpClientBuilderParams.setConnectionTimeout(connectTimeOut);
            httpClientBuilderParams.setReadTimeout(readTimeOut);
        }

        super.init(httpClientBuilderParams);
    }
}
