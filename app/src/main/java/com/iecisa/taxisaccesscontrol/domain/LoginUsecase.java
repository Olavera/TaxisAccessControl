package com.iecisa.taxisaccesscontrol.domain;

import com.iecisa.taxisaccesscontrol.model.Repository;
import com.iecisa.taxisaccesscontrol.model.entities.Wrapper;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import rx.Observable;

/**
 * @author Olavera
 */
public class LoginUsecase implements Usecase<Wrapper<String>> {

    private final Repository mRepository;
    private String mUserName;
    private String mPassword;

    public LoginUsecase(Repository repository) {
        mRepository = repository;
    }

    public void setUserPass(String userName, String password) {
        this.mUserName = userName;
        this.mPassword = password;
    }

    @Override
    public Observable<Wrapper<String>> execute() {

        return mRepository.login()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
