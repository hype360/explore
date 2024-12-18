package com.hype360kh.explore.model.base;

import io.hypersistence.tsid.TSID;

import java.util.function.Supplier;

public class CustomTsidSupplier implements Supplier<TSID.Factory> {

    @Override
    public TSID.Factory get() {
        return TSID.Factory.builder()
                .withNodeBits(10)
                .build();
    }
}