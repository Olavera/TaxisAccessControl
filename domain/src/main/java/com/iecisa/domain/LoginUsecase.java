package com.iecisa.domain;

import com.iecisa.common.BusProvider;
import com.iecisa.model.MediaDataSource;
import com.iecisa.model.entities.ErrorResponse;
import com.iecisa.model.entities.LoginResponse;
import com.squareup.otto.Subscribe;

/**
 * @author darevalo
 */
public class LoginUsecase implements Usecase {

    private final MediaDataSource mMediaDataSource;

    public LoginUsecase(MediaDataSource mediaDataSource) {
        mMediaDataSource    = mediaDataSource;
        BusProvider.getRestBusInstance().register(this);
    }

    @Override
    public void execute() {
        requestLogin();
    }

    @Override
    public void unregisterBus() {
        BusProvider.getRestBusInstance().unregister(this);
    }

    private void requestLogin() {
        mMediaDataSource.login();
    }

    @Subscribe
    public void onAttempedLogin(LoginResponse response) {
        BusProvider.getUIBusInstance().post(response);
    }

    @Subscribe
    public void onError(ErrorResponse error) {
        BusProvider.getUIBusInstance().post(error);
    }
}
