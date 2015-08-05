package com.iecisa.taxisaccesscontrol.mvp.views;

import com.iecisa.taxisaccesscontrol.model.Repository;


/**
 * @author darevalo
 */
public interface LoginView extends MVPView {

    public void showLoading ();

    public void hideLoading ();

    public void showLoginError();

    public void showConexionError();

    public void loginOK(Repository repository);

}
