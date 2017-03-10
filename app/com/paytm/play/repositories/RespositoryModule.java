package com.paytm.play.repositories;

import com.google.inject.AbstractModule;

public class RespositoryModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CassandraRepository.class).asEagerSingleton();
    }

}
