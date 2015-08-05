package com.iecisa.taxisaccesscontrol.model.rest;


import rx.Observable;

import com.iecisa.taxisaccesscontrol.model.Repository;
import com.iecisa.taxisaccesscontrol.model.entities.Vehicle;
import com.iecisa.taxisaccesscontrol.model.entities.Wrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Olavera
 */
public class RestRespository  implements Repository {

    private final RestApi mRestApi;


    public RestRespository(String userName, String password) {
        mRestApi = RestServiceGenerator.createService(RestApi.class, RestApi.ENDPOINT,
                5, 20, userName, password);
    }

    @Override
    public Observable<Wrapper<String>> login() {
        return mRestApi.login();
    }

    @Override
    public Observable<Wrapper<List<Vehicle>>> getBlacklist() {
        //return mRestApi.getBlacklist();
        List<Vehicle> list = new ArrayList<>();
        list.add(new Vehicle(1, "5225DFG", 1, "12-01-2015"));
        list.add(new Vehicle(2, "2552GDS", 1, "15-01-2015"));
        list.add(new Vehicle(3, "3344JTF", 1, "22-01-2015"));
        list.add(new Vehicle(4, "8424BFP", 1, "07-02-2015"));
        list.add(new Vehicle(5, "7443CTF", 1, "09-02-2015"));
        list.add(new Vehicle(6, "2885DJK", 1, "01-03-2015"));
        list.add(new Vehicle(7, "4564CJK", 1, "12-03-2015"));
        list.add(new Vehicle(8, "2529FGP", 1, "29-03-2015"));
        list.add(new Vehicle(9, "9409JKL", 1, "06-04-2015"));
        list.add(new Vehicle(10, "5445CLJ", 1, "07-04-2015"));
        return Observable.just(new Wrapper<List<Vehicle>>(0, "", list));
    }

    @Override
    public Observable<Wrapper<Vehicle>> getVehicleDetails(long id) {
        return mRestApi.getVehicleDetails(id);
    }

}
