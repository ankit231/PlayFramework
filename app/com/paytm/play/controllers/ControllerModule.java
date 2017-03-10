package com.paytm.play.controllers;

import com.google.inject.AbstractModule;

public class ControllerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PerformanceController.class).asEagerSingleton();
    }

}
