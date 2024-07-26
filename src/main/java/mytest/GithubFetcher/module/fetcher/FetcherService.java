package mytest.GithubFetcher.module.fetcher;

import static mytest.GithubFetcher.module.fetcher.FetcherConstants.BRANCH;
import static mytest.GithubFetcher.module.fetcher.FetcherConstants.GITHUB_API_URL;
import static mytest.GithubFetcher.module.fetcher.FetcherConstants.USERNAME;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mytest.GithubFetcher.module.fetcher.model.Branch;
import mytest.GithubFetcher.module.fetcher.model.RepoResponse;
import mytest.GithubFetcher.module.fetcher.model.RepositoryInfo;

@Service
public class FetcherService {

	@Autowired
	private WebClientService webClientService;

	public List<RepositoryInfo> getRepositoryInfoByUsername(String username) {
		String uri = GITHUB_API_URL.replace(USERNAME, username);
		List<RepoResponse> fetchedRepositoriesForUser = fetchRepositoriesFromGithub(uri);
		List<RepoResponse> nonforkRepositories = filterNonforkRepositories(fetchedRepositoriesForUser);
		return fetchBranchesAndCreateRepositoryInfo(nonforkRepositories);
	}

	private List<RepositoryInfo> fetchBranchesAndCreateRepositoryInfo(List<RepoResponse> nonforkRepositories) {
		return nonforkRepositories.stream().map(repository -> new RepositoryInfo(repository.getName(),
				repository.getOwner().getLogin(), fetchAndMapBranches(repository))).collect(Collectors.toList());
	}

	private List<Branch> fetchAndMapBranches(RepoResponse response) {
		return webClientService.fetchBranchesFromGithub(removeBranchStringFromUrlAndReturn(response)).stream()
				.map(branchResponse -> new Branch(branchResponse.getName(), branchResponse.getCommit().getSha()))
				.collect(Collectors.toList());
	}

	private String removeBranchStringFromUrlAndReturn(RepoResponse response) {
		return response.getBranches_url().replace(BRANCH, Strings.EMPTY);
	}

	private List<RepoResponse> filterNonforkRepositories(List<RepoResponse> fetchedRepositoriesForUser) {
		return fetchedRepositoriesForUser.stream().filter(repository -> !repository.isFork())
				.collect(Collectors.toList());
	}

	private List<RepoResponse> fetchRepositoriesFromGithub(String uri) {
		return webClientService.fetchRepositoriesFromGithub(uri);
	}
}