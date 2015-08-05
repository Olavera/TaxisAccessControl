package com.iecisa.taxisaccesscontrol.domain;

import com.iecisa.taxisaccesscontrol.model.Repository;
import com.iecisa.taxisaccesscontrol.model.entities.Vehicle;
import com.iecisa.taxisaccesscontrol.model.entities.Wrapper;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Olavera
 */
public class GetVehicleDetailsUsecase implements Usecase<Wrapper<Vehicle>> {

    private final Repository mRepository;
    private long mId;

    public GetVehicleDetailsUsecase(Repository repository) {
        mRepository = repository;
    }

    public void setNumberPlate(long id) {
        this.mId = id;
    }

    @Override
    public Observable<Wrapper<Vehicle>> execute() {

        return mRepository.getVehicleDetails(this.mId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
