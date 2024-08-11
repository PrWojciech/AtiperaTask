package com.example.zadanieu.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RepositoryInfo {
    private String name;
    private Owner owner;
    private boolean fork;
    private Branch[] branches;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Owner {
        private String login;
    }
}