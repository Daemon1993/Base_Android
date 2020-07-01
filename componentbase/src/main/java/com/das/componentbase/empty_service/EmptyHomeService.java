package com.das.componentbase.empty_service;

import com.das.componentbase.service.IHomeService;

public class EmptyHomeService implements IHomeService {
    @Override
    public boolean isHomeOk() {
        return false;
    }
}
