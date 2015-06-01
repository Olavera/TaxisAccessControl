package com.iecisa.taxisaccesscontrol.mvp.presenters;

import com.iecisa.common.BusProvider;
import com.iecisa.domain.LoginUsecase;
import com.iecisa.model.MediaDataSource;
import com.iecisa.model.entities.ErrorResponse;
import com.iecisa.model.entities.LoginResponse;
import com.iecisa.model.rest.RestDataSource;
import com.iecisa.taxisaccesscontrol.mvp.views.LoginView;
import com.squareup.otto.Subscribe;

/**
 * @author darevalo
 */
public class LoginPresenter implements Presenter {

    private final LoginView mLoginView;
    private LoginUsecase mLoginUsecase;
    private RestDataSource mRestDataSource;

    public LoginPresenter(LoginView loginView) {
        this.mLoginView = loginView;
    }

    @Override
    public void start() {
        BusProvider.getUIBusInstance().register(this);
    }

    @Override
    public void stop() {
        BusProvider.getUIBusInstance().unregister(this);
        if(mLoginUsecase!=null){
            mLoginUsecase.unregisterBus();
        }
    }

    public void attemptLogin(String userName, String password) {
        mRestDataSource = new RestDataSource(userName, password);
        this.mLoginUsecase = new LoginUsecase(mRestDataSource);
        mLoginView.showLoading();
        mLoginUsecase.execute();
    }

    @Subscribe
    public void onAttempedLogin(LoginResponse response) {
        mLoginView.hideLoading();
        if(response.isSuccessful()) {
            mLoginView.loginOK(mRestDataSource);
        }else{
            mLoginView.showLoginError();
        }
    }

    @Subscribe
    public void onError(ErrorResponse response) {
        mLoginView.hideLoading();
        mLoginView.showConexionError();
    }
}
