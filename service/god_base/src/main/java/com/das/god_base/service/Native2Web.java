package com.das.god_base.service;

import java.util.List;

public class Native2Web {
    private List<String> value;

    public Native2Web(List<String> value) {
        this.value = value;
    }

    public List<String> getValue() {
        return value;
    }

    public void setValue(List<String> value) {
        this.value = value;
    }
}
