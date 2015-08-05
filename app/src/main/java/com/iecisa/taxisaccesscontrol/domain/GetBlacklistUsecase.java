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
public class GetBlacklistUsecase implements Usecase<Wrapper<List<Vehicle>>>{

    private final Repository mRepository;

    public GetBlacklistUsecase(Repository repository) {
        mRepository = repository;
    }

    @Override
    public Observable<Wrapper<List<Vehicle>>> execute() {

        return mRepository.getBlacklist()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
