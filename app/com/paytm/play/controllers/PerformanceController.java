package com.paytm.play.controllers;

import com.paytm.play.repositories.CassandraRepository;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class PerformanceController extends Controller {

    private final CassandraRepository cass;
    private final WSClient ws;

    @Inject
    public PerformanceController(CassandraRepository cass, WSClient ws) {
        this.cass = cass;
        this.ws = ws;
    }

    public CompletionStage<Result> index() {
        String url = "https://thunderbolt-dev.paytm.com/thunderbolt/v1/bankServices";
        CompletionStage<WSResponse> wsResponsePromise = ws.url(url).get();

        CompletionStage<String> cassPromise = wsResponsePromise.thenApplyAsync(response -> {
            return cass.saveInCassandra(response.getBody().toString());
        });

        CompletionStage<Result> resultPromise = cassPromise.thenApplyAsync(key -> {
            return ok(key);
        });

        return resultPromise;
    }
}
