package com.iecisa.domain;

import com.iecisa.common.BusProvider;
import com.iecisa.model.MediaDataSource;
import com.iecisa.model.entities.BlacklistWrapper;
import com.iecisa.model.entities.ErrorResponse;
import com.iecisa.model.entities.VehicleDetails;
import com.iecisa.model.entities.VehicleDetailsWrapper;
import com.squareup.otto.Subscribe;

/**
 * @author darevalo
 */
public class GetVehicleDetailsUsecase  implements Usecase {

    private final MediaDataSource mMediaDataSource;

    public GetVehicleDetailsUsecase(MediaDataSource mediaDataSource) {
        mMediaDataSource    = mediaDataSource;
        BusProvider.getRestBusInstance().register(this);
    }

    @Override
    public void execute() {
    }

    @Override
    public void unregisterBus() {
        BusProvider.getRestBusInstance().unregister(this);
    }

    public void getVehicleDetails(String numberPlate) {
        mMediaDataSource.getVehicleDetails(numberPlate);
    }

    @Subscribe
    public void onVehicleDetailsReceived(VehicleDetailsWrapper response) {
        BusProvider.getUIBusInstance().post(response);
    }

    @Subscribe
    public void onError(ErrorResponse error) {
        BusProvider.getUIBusInstance().post(error);
    }
}

