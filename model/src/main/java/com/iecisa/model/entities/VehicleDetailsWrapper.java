package com.iecisa.model.entities;

/**
 * @author darevalo
 */
public class VehicleDetailsWrapper extends Response{

    private VehicleDetails vehicleDetails;

    public VehicleDetailsWrapper() {
    }

    public VehicleDetails getVehicleDetails() {
        return vehicleDetails;
    }

    public void setVehicleDetails(VehicleDetails vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }
}
