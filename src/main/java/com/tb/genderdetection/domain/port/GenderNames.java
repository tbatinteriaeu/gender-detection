package com.tb.genderdetection.domain.port;

import reactor.core.publisher.Flux;


public interface GenderNames {
    Flux<String> getAvailableNames();
}
