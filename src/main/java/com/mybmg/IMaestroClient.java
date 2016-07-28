package com.mybmg;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

/**
 *
 */
public class IMaestroClient {

    public static void mainX(String[] args) {
        final CloseableHttpAsyncClient httpAsyncClient = HttpAsyncClientBuilder.create()
                                                                               .build();
        httpAsyncClient.start();

        final HttpGet httpGetRequest = new HttpGet("http://10.6.238.176:9401/bmgsongsrestapi/bmgsongs/data/song?page=1&per_page=50");
        httpAsyncClient.execute(httpGetRequest, new FutureCallback<HttpResponse>() {

            @Override
            public void completed(final HttpResponse response) {
                final HttpEntity entity = response.getEntity();

                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                try {
                    entity.writeTo(baos);
                    byte[] bytes = baos.toByteArray();
                    System.err.println("Plain response: " + new String(bytes));

                    ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                    GZIPInputStream gis = new GZIPInputStream(bis);
                    BufferedReader br = new BufferedReader(new InputStreamReader(gis, "ISO-8859-1"));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while((line = br.readLine()) != null) {
                        sb.append(line);
                    }

                    System.err.println("Manually gunzipped: " + sb);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(final Exception e) {
                System.err.println("Failed: " + e.getMessage());
            }

            @Override
            public void cancelled() {
                System.err.println("Canceled.");
            }
        });
    }

}
