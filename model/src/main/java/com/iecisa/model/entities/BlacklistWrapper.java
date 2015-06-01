package com.iecisa.model.entities;

import java.util.List;

/**
 * @author darevalo
 */
public class BlacklistWrapper extends Response {

    private List<Vehicle> results;

    public BlacklistWrapper() {
    }

    public List<Vehicle> getResults() {
        return results;
    }

    public void setResults(List<Vehicle> results) {
        this.results = results;
    }
}
