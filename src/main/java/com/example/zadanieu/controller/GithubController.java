package com.example.zadanieu.controller;


import com.example.zadanieu.model.RepositoryInfo;
import com.example.zadanieu.servis.GithubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/github")
@RequiredArgsConstructor
public class GithubController {

    private final GithubService githubService;

    @GetMapping("/repositories/{username}")
    public ResponseEntity<List<RepositoryInfo>> getRepositories(@PathVariable String username,
                                                                @RequestHeader(value = HttpHeaders.ACCEPT, defaultValue = "application/json") String acceptHeader) {

        if (acceptHeader.contains(org.springframework.http.MediaType.APPLICATION_JSON_VALUE)) {
            List<RepositoryInfo> repositories = githubService.getNonForkRepositories(username);
            return ResponseEntity.ok(repositories);
        }

        return ResponseEntity.badRequest().build();
    }
}