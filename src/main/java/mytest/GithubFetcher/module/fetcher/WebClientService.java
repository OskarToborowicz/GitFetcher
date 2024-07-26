package mytest.GithubFetcher.module.fetcher;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import mytest.GithubFetcher.advice.exception.UserNotFoundException;
import mytest.GithubFetcher.module.fetcher.model.BranchResponse;
import mytest.GithubFetcher.module.fetcher.model.RepoListResponse;

@Service
class WebClientService {
	
	private WebClient webClient;

	WebClientService() {
		this.webClient = WebClient.create();
	}

	List<BranchResponse> fetchBranchesFromGithub(String uri) {
		return webClient.get().uri(UriComponentsBuilder.fromUriString(uri).build().toUri()).retrieve()
				.toEntityList(BranchResponse.class).flux().blockLast().getBody();
	}

	List<RepoListResponse> fetchRepositoriesFromGithub(String uri) throws UserNotFoundException {
		return webClient.get().uri(UriComponentsBuilder.fromUriString(uri).build().toUri()).retrieve()
				.onStatus(httpStatus -> httpStatus == HttpStatus.NOT_FOUND, clientResponse -> {
					return clientResponse.createException().map(it -> new UserNotFoundException(it.getStatusText()));
				}).toEntityList(RepoListResponse.class).flux().blockLast().getBody();
	}
}
