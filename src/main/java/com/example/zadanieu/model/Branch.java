package com.example.zadanieu.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Branch {
    private String name;
    private Commit commit;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Commit {
        private String sha;
    }
}