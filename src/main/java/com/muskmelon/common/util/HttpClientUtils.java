package com.muskmelon.common.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author muskmelon
 * @description
 * @date 2020-3-28 20:04
 * @since 1.0
 */
public class HttpClientUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    private static final CloseableHttpClient HTTP_CLIENT;

    /**
     * 默认编码
     */
    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * 连接超时时间(ms)
     */
    private static final Integer CONNECT_TIMEOUT = 5000;

    /**
     * 响应超时时间(ms)
     */
    private static final Integer SOCKET_TIMEOUT = 5000;

    /**
     * 从连接池获取连接的超时时间(ms)
     */
    private static final Integer CONNECT_REQUEST_TIMEOUT = 5000;

    private static final Integer DEFAULT_MAX_CONNECTION = 5;

    private static final Integer MAX_CONNECTION = 10;

    static {
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(SOCKET_TIMEOUT)
                .setConnectTimeout(CONNECT_TIMEOUT)
                .setConnectionRequestTimeout(CONNECT_REQUEST_TIMEOUT)
                .build();

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(MAX_CONNECTION);
        connectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_CONNECTION);

        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setConnectionManager(connectionManager);

        HTTP_CLIENT = httpClientBuilder.setDefaultRequestConfig(requestConfig).build();
    }

    public static String doGet(String url, Map<String, String> params) {
        return doGet(url, params, DEFAULT_CHARSET);
    }

    public static String doPost(String url, Map<String, String> params) {
        return doPost(url, params, DEFAULT_CHARSET);
    }

    public static String doGetSSL(String url, Map<String, String> params) {
        return doGetSSL(url, params, DEFAULT_CHARSET);
    }

    /**
     * Http Get请求
     *
     * @param url     请求地址
     * @param params  请求参数
     * @param charset 编码格式
     * @return 请求结果
     */
    public static String doGet(String url, Map<String, String> params, String charset) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {
            if (MapUtils.isNotEmpty(params)) {
                url = convertGetUrl(url, params, charset);
            }
            HttpGet httpGet = new HttpGet(url);
            try (CloseableHttpResponse response = HTTP_CLIENT.execute(httpGet)) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (HttpStatus.SC_OK != statusCode) {
                    httpGet.abort();
                    throw new RuntimeException("HttpClientUtils httpGet Error Status Code :" + statusCode);
                }
                return convertResponse(response, charset);
            } catch (Exception e) {
                logger.error("doGet execute error, url: {}", url, e);
            }
        } catch (Exception e) {
            logger.error("doGet Error,url: {}", url, e);
        }
        return null;
    }

    /**
     * Http post请求
     *
     * @param url     请求url地址
     * @param params  请求参数
     * @param charset 编码格式
     * @return 请求结果
     */
    public static String doPost(String url, Map<String, String> params, String charset) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {
            List<NameValuePair> pairs = null;
            if (MapUtils.isNotEmpty(params)) {
                pairs = convertParam(params);
            }
            HttpPost httpPost = new HttpPost(url);
            if (CollectionUtils.isNotEmpty(pairs)) {
                httpPost.setEntity(new UrlEncodedFormEntity(pairs, charset));
            }
            try (CloseableHttpResponse response = HTTP_CLIENT.execute(httpPost)) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (HttpStatus.SC_OK != statusCode) {
                    httpPost.abort();
                    throw new RuntimeException("HttpClientUtils Error status code : " + statusCode);
                }
                return convertResponse(response, charset);
            } catch (Exception e) {
                logger.error("doPost execute error, url: {}", url, e);
            }
        } catch (Exception e) {
            logger.error("doPost error, url: {}", url, e);
        }
        return null;
    }

    /**
     * Https GET 请求
     *
     * @param url     请求地址
     * @param params  请求参数
     * @param charset 编码格式
     * @return 请求结果
     */
    public static String doGetSSL(String url, Map<String, String> params, String charset) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        try {
            if (MapUtils.isNotEmpty(params)) {
                url = convertGetUrl(url, params, charset);
            }
            HttpGet httpGet = new HttpGet(url);
            CloseableHttpClient httpClient = HttpClientUtils.createSSLClient();
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                int statusCode = response.getStatusLine().getStatusCode();
                if (HttpStatus.SC_OK != statusCode) {
                    httpGet.abort();
                    throw new RuntimeException("HttpClientUtils httpGet Error status code : " + statusCode);
                }
                return convertResponse(response, charset);
            } catch (Exception e) {
                logger.error("doGetSSL execute error, url: {}", url, e);
            }
        } catch (Exception e) {
            logger.error("doGetSSL error,url: {}", url, e);
        }
        return null;
    }

    /**
     * 创建了忽略证书的的CloseableHttpClient对象
     *
     * @return CloseableHttpClient
     */
    public static CloseableHttpClient createSSLClient() {
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (x509Certificates, s) -> {
                // 信任所有
                return true;
            }).build();
            SSLConnectionSocketFactory factory = new SSLConnectionSocketFactory(sslContext);
            return HttpClients.custom().setSSLSocketFactory(factory).build();
        } catch (NoSuchAlgorithmException e) {
            logger.error("NoSuchAlgorithmException", e);
        } catch (KeyManagementException e) {
            logger.error("KeyManagementException", e);
        } catch (KeyStoreException e) {
            logger.error("KeyStoreException", e);
        }
        return HttpClients.createDefault();
    }

    /**
     * 有参数时处理get请求url
     *
     * @param params
     * @return
     */
    private static String convertGetUrl(String url, Map<String, String> params, String charset) throws Exception {
        List<NameValuePair> pairs = convertParam(params);
        url += "?" + EntityUtils.toString(new UrlEncodedFormEntity(pairs, charset));
        return url;
    }

    /**
     * 处理参数
     *
     * @param params
     * @return
     */
    private static List<NameValuePair> convertParam(Map<String, String> params) {
        List<NameValuePair> pairs = new ArrayList<>(params.size());
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String value = entry.getValue();
            if (null != value) {
                pairs.add(new BasicNameValuePair(entry.getKey(), value));
            }
        }
        return pairs;
    }

    /**
     * 处理请求返回值
     *
     * @param response 请求返回response
     * @param charset  编码格式
     * @return
     * @throws Exception
     */
    private static String convertResponse(CloseableHttpResponse response, String charset) throws Exception {
        HttpEntity entity = response.getEntity();
        String result = null;
        if (null != entity) {
            result = EntityUtils.toString(entity, charset);
        }
        EntityUtils.consume(entity);
        return result;
    }

}
