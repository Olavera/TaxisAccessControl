package com.iecisa.taxisaccesscontrol.mvp.views;

import android.graphics.Movie;

import com.iecisa.model.rest.RestDataSource;

import java.util.List;

/**
 * @author darevalo
 */
public interface LoginView extends MVPView {

    public void showLoading ();

    public void hideLoading ();

    public void showLoginError();

    public void showConexionError();

    public void loginOK(RestDataSource restDataSource);

}
