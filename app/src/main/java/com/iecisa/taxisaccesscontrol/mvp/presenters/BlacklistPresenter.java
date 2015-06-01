package com.iecisa.taxisaccesscontrol.mvp.presenters;

import com.iecisa.common.BusProvider;
import com.iecisa.domain.GetBlacklistUsecase;
import com.iecisa.domain.GetVehicleDetailsUsecase;
import com.iecisa.model.entities.BlacklistWrapper;
import com.iecisa.model.entities.ErrorResponse;
import com.iecisa.model.entities.VehicleDetailsWrapper;
import com.iecisa.model.rest.RestDataSource;
import com.iecisa.taxisaccesscontrol.mvp.views.BlacklistView;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;

/**
 * @author darevalo
 */
public class BlacklistPresenter implements Presenter {

    private final BlacklistView mBlacklistView;
    private GetBlacklistUsecase mGetBlacklistUsecase;
    private GetVehicleDetailsUsecase mGetVehicleDetailsUsecase;
    private RestDataSource mRestDataSource;

    public BlacklistPresenter(BlacklistView blacklistView, RestDataSource restDataSource) {
        this.mBlacklistView = blacklistView;
        this.mRestDataSource = restDataSource;
    }

    @Override
    public void start() {
        BusProvider.getUIBusInstance().register(this);
        mGetBlacklistUsecase = new GetBlacklistUsecase(mRestDataSource);
        mGetVehicleDetailsUsecase = new GetVehicleDetailsUsecase(mRestDataSource);
        refreshBlacklist();
    }

    @Override
    public void stop() {
        BusProvider.getUIBusInstance().unregister(this);
        if(mGetBlacklistUsecase!=null){
            mGetBlacklistUsecase.unregisterBus();
        }
        if(mGetVehicleDetailsUsecase!=null){
            mGetVehicleDetailsUsecase.unregisterBus();
        }
    }

    public void refreshBlacklist() {
        mBlacklistView.showLoading();
        mGetBlacklistUsecase.execute();
    }

    @Subscribe
    public void onBlacklistReceived(BlacklistWrapper response) {
        mBlacklistView.hideLoading();
        if(response.isSuccessful()) {
            mBlacklistView.showNewList((ArrayList) response.getResults());
        }else{
            mBlacklistView.showError();
        }
    }

    public void getVehicleDetails(String numberPlate){
        mBlacklistView.showLoading();
        mGetVehicleDetailsUsecase.getVehicleDetails(numberPlate);
    }

    @Subscribe
    public void onVehicleDetailsReceived(VehicleDetailsWrapper response) {
        mBlacklistView.hideLoading();
        if(response.isSuccessful()) {
            mBlacklistView.showVehicleDetails(response.getVehicleDetails());
        }else{
            mBlacklistView.showError();
        }
    }

    @Subscribe
    public void onError(ErrorResponse response) {
        mBlacklistView.hideLoading();
        mBlacklistView.showConexionError();
    }
}
