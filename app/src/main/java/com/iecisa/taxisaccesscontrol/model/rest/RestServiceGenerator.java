package com.iecisa.taxisaccesscontrol.model.rest;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;

/**
 * @author Olavera
 */
public class RestServiceGenerator {

    private RestServiceGenerator() {
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl,
                                      int connectTimeout, int readTimeout) {

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(baseUrl)
                .setClient(getClient(connectTimeout, readTimeout))
                .setLogLevel(RestAdapter.LogLevel.HEADERS_AND_ARGS)
                .build();

        return adapter.create(serviceClass);
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl,
                                      int connectTimeout, int readTimeout,
                                      String username, String password) {

        RequestInterceptor requestInterceptor = null;

        if (!username.isEmpty() && !password.isEmpty()) {
            // concatenate username and password with colon for authentication
            final String credentials = username + ":" + password;

            requestInterceptor =
                    (request) -> {
                        String string = "Basic " + credentials;
                        request.addHeader("Accept", "application/json");
                        request.addHeader("Authorization", string);
                    };
        }

        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(baseUrl)
                .setClient(getClient(connectTimeout, readTimeout))
                .setLogLevel(RestAdapter.LogLevel.HEADERS_AND_ARGS)
                .setRequestInterceptor(requestInterceptor)
                .build();

        return adapter.create(serviceClass);
    }

    private static Client getClient(int connectTimeout, int readTimeout){

        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(connectTimeout, TimeUnit.SECONDS);
        client.setReadTimeout(readTimeout, TimeUnit.SECONDS);

        return new OkClient(client);
    }

}