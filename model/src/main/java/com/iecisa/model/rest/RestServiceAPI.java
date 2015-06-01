package com.iecisa.model.rest;

import com.iecisa.common.ConexionConstants;
import com.iecisa.model.entities.BlacklistWrapper;
import com.iecisa.model.entities.LoginResponse;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * @author darevalo
 */
public interface RestServiceAPI {

    @POST(ConexionConstants.LOGIN)
    public void login(Callback<LoginResponse> callback);

    @GET(ConexionConstants.GET_BLACKLIST)
    void getBlacklist (Callback<BlacklistWrapper> callback);

    @GET(ConexionConstants.GET_VEHICLE_DETAILS)
    void getVehicleDetails (@Path("id") String id, Callback<BlacklistWrapper> callback);

    /*@GET("/movie/{id}")
    void getMovieDetail (
            @Query("api_key") String apiKey,
            @Path("id") String id,
            Callback<MovieDetail> callback
    );

    @GET("/movie/{id}/reviews")
    void getReviews (
            @Query("api_key") String apiKey,
            @Path("id") String id,
            Callback<ReviewsWrapper> response
    );*/
}
