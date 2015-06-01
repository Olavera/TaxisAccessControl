package com.iecisa.model;

/**
 * @author darevalo
 */
public interface MediaDataSource {

    public void login();

    public void getBlacklist();

    public void getVehicleDetails(String numberPlate);

}
