package com.upclicks.laDiva.data;

import com.google.android.gms.common.data.DataBufferObserver;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.observers.DefaultObserver;

public abstract class CustomRxObserver extends DefaultObserver implements Observer {

    @Override
    public abstract void onNext(@NonNull Object o);

    @Override
    public abstract void onError(@NonNull Throwable e);
}
