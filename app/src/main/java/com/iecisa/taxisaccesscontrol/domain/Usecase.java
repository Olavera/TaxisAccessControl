package com.iecisa.taxisaccesscontrol.domain;

import rx.Observable;

/**
 * @author Olavera
 */
public interface Usecase<T> {

    Observable<T> execute();
}
