package com.iecisa.taxisaccesscontrol.mvp.presenters;

import android.os.Bundle;

import com.iecisa.taxisaccesscontrol.domain.LoginUsecase;
import com.iecisa.taxisaccesscontrol.model.Repository;
import com.iecisa.taxisaccesscontrol.model.entities.Wrapper;
import com.iecisa.taxisaccesscontrol.model.rest.RestRespository;
import com.iecisa.taxisaccesscontrol.mvp.views.LoginView;
import com.iecisa.taxisaccesscontrol.mvp.views.MVPView;


import rx.Subscription;

/**
 * @author darevalo
 */
public class LoginPresenter implements Presenter {

    private LoginView mLoginView;
    private Repository mRepository;
    private LoginUsecase mLoginUsecase;
    private Subscription mLoginSubscription;

    public LoginPresenter() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if (mLoginSubscription!=null && !mLoginSubscription.isUnsubscribed())
            mLoginSubscription.unsubscribe();
    }

    @Override
    public void attachView(MVPView view) {
        mLoginView = (LoginView) view;
    }

    @Override
    public void attachBundle(Bundle bundle) {

    }

    @Override
    public void initializePresenter() {
    }

    public void attemptLogin(String userName, String password) {
        mRepository = new RestRespository(userName, password);
        mLoginUsecase = new LoginUsecase(mRepository);
        mLoginView.showLoading();
        mLoginSubscription = mLoginUsecase.execute().subscribe(
                // On Next
                (response) ->  { onNext(response); },
                // On Error
                (throwable) -> { onError(throwable); },
                // On Complete
                () -> { onComplete(); }
        );
    }

    private void onNext(Wrapper<String> response) {
        if(response.isSuccessfully()){
            mLoginView.loginOK(mRepository);
        }else{
            mLoginView.showLoginError();
        }
    }

    private void onError(Throwable e) {
        mLoginView.hideLoading();
        mLoginView.showConexionError();
        //TODO: Quitar para saltar instruccion para dejar de saltar login
        mLoginView.loginOK(mRepository);
    }

    private void onComplete() {
        mLoginView.hideLoading();
    }
}
