package com.iecisa.domain;

import com.iecisa.common.BusProvider;
import com.iecisa.model.MediaDataSource;
import com.iecisa.model.entities.BlacklistWrapper;
import com.iecisa.model.entities.ErrorResponse;
import com.iecisa.model.entities.VehicleDetailsWrapper;
import com.squareup.otto.Subscribe;

/**
 * @author darevalo
 */
public class GetBlacklistUsecase implements Usecase{

    private final MediaDataSource mMediaDataSource;

    public GetBlacklistUsecase(MediaDataSource mediaDataSource) {
        mMediaDataSource    = mediaDataSource;
        BusProvider.getRestBusInstance().register(this);
    }

    @Override
    public void execute() {
        getBlacklist();
    }

    @Override
    public void unregisterBus() {
        BusProvider.getRestBusInstance().unregister(this);
    }

    private void getBlacklist() {
        mMediaDataSource.getBlacklist();
    }

    @Subscribe
    public void onBlacklistReceived(BlacklistWrapper response) {
        BusProvider.getUIBusInstance().post(response);
    }

    @Subscribe
    public void onError(ErrorResponse error) {
        BusProvider.getUIBusInstance().post(error);
    }
}
