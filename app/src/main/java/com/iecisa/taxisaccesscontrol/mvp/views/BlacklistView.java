package com.iecisa.taxisaccesscontrol.mvp.views;

import com.iecisa.model.entities.Vehicle;
import com.iecisa.model.entities.VehicleDetails;

import java.util.ArrayList;

/**
 * @author darevalo
 */
public interface BlacklistView extends MVPView {

    public void showLoading ();

    public void hideLoading ();

    public void showConexionError();

    public void showNewList(ArrayList<Vehicle> list);

    public void showError();

    public void showVehicleDetails(VehicleDetails vehicleDetails);

}
