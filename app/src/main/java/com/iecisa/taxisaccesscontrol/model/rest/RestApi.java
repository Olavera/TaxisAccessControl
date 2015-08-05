package com.iecisa.taxisaccesscontrol.model.rest;

import rx.Observable;

import com.iecisa.taxisaccesscontrol.model.entities.Vehicle;
import com.iecisa.taxisaccesscontrol.model.entities.Wrapper;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * @author Olavera
 */
public interface RestApi {

    public static final String ENDPOINT = "http://192.168.100.13:8090/CTM-LPR-Services/rest";
    public static final String LOGIN = "/login";
    public static final String GET_BLACKLIST = "/listaNegra/list/criteria";
    public static final String GET_VEHICLE_DETAILS = "/vehicle/{id}/details";


    @POST(LOGIN)
    public Observable<Wrapper<String>> login();

    @GET(GET_BLACKLIST)
    Observable<Wrapper<List<Vehicle>>> getBlacklist ();

    @GET(GET_VEHICLE_DETAILS)
    Observable<Wrapper<Vehicle>> getVehicleDetails (@Path("id") long id);

}
