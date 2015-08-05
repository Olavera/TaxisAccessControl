package com.iecisa.taxisaccesscontrol.mvp.views;


import com.iecisa.taxisaccesscontrol.model.entities.Vehicle;

import java.util.List;

/**
 * @author darevalo
 */
public interface BlacklistView extends MVPView {

    public void showLoading ();

    public void hideLoading ();

    public void showConexionError();

    public void showNewList(List<Vehicle> vehicles);

    public void showError();

}
