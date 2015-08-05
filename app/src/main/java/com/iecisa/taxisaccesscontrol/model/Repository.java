package com.iecisa.taxisaccesscontrol.model;

import com.iecisa.taxisaccesscontrol.model.entities.Vehicle;
import com.iecisa.taxisaccesscontrol.model.entities.Wrapper;

import java.util.List;

import rx.Observable;

/**
 * @author Olavera
 */
public interface Repository {

    public Observable<Wrapper<String>> login();

    public Observable<Wrapper<List<Vehicle>>> getBlacklist();

    public Observable<Wrapper<Vehicle>> getVehicleDetails(long id);

}
