package com.iecisa.taxisaccesscontrol.mvp.presenters;

import android.os.Bundle;
import android.view.View;

import com.iecisa.taxisaccesscontrol.model.entities.Wrapper;
import com.iecisa.taxisaccesscontrol.mvp.views.MVPView;
import com.iecisa.taxisaccesscontrol.domain.GetBlacklistUsecase;
import com.iecisa.taxisaccesscontrol.model.Repository;
import com.iecisa.taxisaccesscontrol.model.entities.Vehicle;
import com.iecisa.taxisaccesscontrol.mvp.views.BlacklistView;

import java.util.List;

import rx.Subscription;

/**
 * @author darevalo
 */
public class BlacklistPresenter implements Presenter {

    private BlacklistView mBlacklistView;
    private final GetBlacklistUsecase mGetBlacklistUsecase;
    private Subscription mBlacklistSubscription;

    public BlacklistPresenter(Repository repository) {
        this.mGetBlacklistUsecase = new GetBlacklistUsecase(repository);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if (mBlacklistSubscription!=null && !mBlacklistSubscription.isUnsubscribed())
            mBlacklistSubscription.unsubscribe();
    }

    @Override
    public void attachView(MVPView view) {
        mBlacklistView = (BlacklistView) view;
    }

    @Override
    public void attachBundle(Bundle bundle) {

    }

    @Override
     public void initializePresenter() {
        mBlacklistView.showLoading();
        mBlacklistSubscription = mGetBlacklistUsecase.execute().subscribe(
                // On Next
                (response) ->  { onNext(response); },
                // On Error
                (throwable) -> { onError(throwable); },
                // On Complete
                () -> { onComplete(); }
        );
    }

    private void onNext(Wrapper<List<Vehicle>> response) {
        if(response.isSuccessfully()){
            mBlacklistView.showNewList(response.getResult());
        }else{
            mBlacklistView.showError();
        }
    }

    private void onError(Throwable e) {
        mBlacklistView.hideLoading();
        mBlacklistView.showConexionError();
    }

    private void onComplete() {
        mBlacklistView.hideLoading();
    }

}
