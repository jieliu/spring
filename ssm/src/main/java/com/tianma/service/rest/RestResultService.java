package com.tianma.service.rest;

import com.tianma.model.exception.Error;
import org.apache.http.Header;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by zuowenxia on 2017/4/29.
 */
public class RestResultService {

    private static final Logger logger = LoggerFactory.getLogger(RestResultService.class);

    private static final String resultUrl = "http://localhost:7777/api/results/";

    private static final String user = "admin";

    private static final String password = "admin";


    public Error getResult() throws IOException {

        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope("AuthScope.ANY", 7777),
                new UsernamePasswordCredentials(user, password));
        CloseableHttpClient httpclient = HttpClients.createDefault();

        try {
            HttpPost httppost = new HttpPost(resultUrl);

            String cookie = "sessionId=CA453C9291591A555CA09975177EB852";
            Header header = new BasicHeader("Cookie",cookie);
            httppost.setHeader(header);

            System.out.println("Executing request: " + httppost.getRequestLine());

            CloseableHttpResponse response = null;
            try {
                response = httpclient.execute(httppost);

                int statusCode = response.getStatusLine().getStatusCode();
                if(statusCode >= 200 && statusCode < 300) {
                    return new Error(0, EntityUtils.toString(response.getEntity()));
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(response != null) {
                    response.close();
                }
            }
        } finally {
            httpclient.close();
        }
        return new Error(-1, "eception");

    }
}
