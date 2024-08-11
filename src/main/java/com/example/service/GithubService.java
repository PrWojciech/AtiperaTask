package com.example.service;


import com.example.exception.GithubUserNotFoundException;
import com.example.model.Branch;
import com.example.model.RepositoryInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GithubService {

    private final RestTemplate restTemplate;

    public List<RepositoryInfo> getNonForkRepositories(String username) {

        try {

            String url = String.format("https://api.github.com/users/%s/repos", username);
            RepositoryInfo[] repositories = restTemplate.getForObject(url, RepositoryInfo[].class);

            if (repositories == null) {
                throw new GithubUserNotFoundException(HttpStatus.NOT_FOUND, "User not found");
            }

            return Arrays.stream(repositories)
                    .filter(repo -> !repo.isFork())
                    .map(this::populateBranches)
                    .collect(Collectors.toList());

        } catch (HttpClientErrorException.NotFound e) {
            throw new GithubUserNotFoundException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    private RepositoryInfo populateBranches(RepositoryInfo repository) {
        String url = String.format("https://api.github.com/repos/%s/%s/branches",
                repository.getOwner().getLogin(),
                repository.getName());
        Branch[] branches = restTemplate.getForObject(url, Branch[].class);
        repository.setBranches(branches);
        return repository;
    }
}