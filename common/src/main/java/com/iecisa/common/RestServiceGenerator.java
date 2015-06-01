package com.iecisa.common;

import com.squareup.okhttp.OkHttpClient;

import java.net.HttpURLConnection;
import java.util.concurrent.TimeUnit;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.client.Request;
import retrofit.client.UrlConnectionClient;

/**
 * @author darevalo
 */
public class RestServiceGenerator {

    private RestServiceGenerator() {
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl) {

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(baseUrl)
                .setClient(getClient())
                .setLogLevel(RestAdapter.LogLevel.HEADERS_AND_ARGS)
                .build();

        return adapter.create(serviceClass);
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl, String username, String password) {

        RequestInterceptor requestInterceptor = null;

        if (!username.isEmpty() && !password.isEmpty()) {
            // concatenate username and password with colon for authentication
            final String credentials = username + ":" + password;

            requestInterceptor = new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    String string = "Basic " + credentials;
                    request.addHeader("Accept", "application/json");
                    request.addHeader("Authorization", string);
                }
            };
        }

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(baseUrl)
                .setClient(getClient())
                .setLogLevel(RestAdapter.LogLevel.HEADERS_AND_ARGS)
                .setRequestInterceptor(requestInterceptor)
                .build();

        return adapter.create(serviceClass);
    }

    private static Client getClient(){

        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(5, TimeUnit.SECONDS);
        client.setReadTimeout(20, TimeUnit.SECONDS);

        return new OkClient(client);
    }

}
