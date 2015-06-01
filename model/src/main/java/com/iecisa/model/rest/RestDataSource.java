package com.iecisa.model.rest;

import com.iecisa.common.BusProvider;
import com.iecisa.common.ConexionConstants;
import com.iecisa.common.RestServiceGenerator;
import com.iecisa.model.MediaDataSource;
import com.iecisa.model.entities.BlacklistWrapper;
import com.iecisa.model.entities.ErrorResponse;
import com.iecisa.model.entities.LoginResponse;
import com.iecisa.model.entities.Vehicle;
import com.iecisa.model.entities.VehicleDetailsWrapper;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * @author darevalo
 */
public class RestDataSource implements MediaDataSource {

    private final String userName;
    private final String password;
    private final RestServiceAPI restAPI;

    public RestDataSource(String userName, String password) {

        /*RestAdapter movieAPIRest = new RestAdapter.Builder()
                .setEndpoint(ConexionConstants.ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.HEADERS_AND_ARGS)
                .build();

        restAPI = movieAPIRest.create(RestServiceAPI.class);*/
        this.userName = userName;
        this.password = password;
        restAPI = RestServiceGenerator.createService(RestServiceAPI.class, ConexionConstants.ENDPOINT, userName, password);
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public void login() {
        //restAPI.login(retrofitCallback);
        LoginResponse response = new LoginResponse();
        response.setCode(0);
        BusProvider.getRestBusInstance().post(response);
    }

    @Override
    public void getBlacklist() {
        //restAPI.getBlacklist(retrofitCallback);
        BlacklistWrapper response = new BlacklistWrapper();
        ArrayList<Vehicle> list = new ArrayList<>();
        list.add(new Vehicle("6545HPF", "BMW", "320tdi", "Verde"));
        list.add(new Vehicle("M3861XP", "Mercedes", "220cc", "Negro"));
        list.add(new Vehicle("2187HJP", "Renault", "Megane", "Amarillo"));
        list.add(new Vehicle("9145DDF", "Seat", "Ibiza", "Blanco"));
        list.add(new Vehicle("2432BCB", "Toyota", "Yaris", "Rojo"));
        response.setCode(0);
        response.setResults(list);
        BusProvider.getRestBusInstance().post(response);
    }

    @Override
    public void getVehicleDetails(String numberPlate) {
        restAPI.getVehicleDetails(numberPlate, retrofitCallback);
    }

    public Callback retrofitCallback = new Callback() {
        @Override
        public void success(Object o, Response response) {

            if (o instanceof LoginResponse) {
                LoginResponse resp = (LoginResponse) o;
                BusProvider.getRestBusInstance().post(resp);
            } else if (o instanceof BlacklistWrapper) {
                BlacklistWrapper resp = (BlacklistWrapper) o;
                BusProvider.getRestBusInstance().post(resp);
            } else if (o instanceof VehicleDetailsWrapper) {
                VehicleDetailsWrapper resp = (VehicleDetailsWrapper) o;
                BusProvider.getRestBusInstance().post(resp);
            }
        }

        @Override
        public void failure(RetrofitError error) {
            ErrorResponse resp = new ErrorResponse();
            resp.setMessage(error.getMessage());
            BusProvider.getRestBusInstance().post(resp);
        }
    };
}
