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
import mytest.GithubFetcher.module.fetcher.model.RepoListResponse;
import mytest.GithubFetcher.module.fetcher.model.RepositoryInfo;

@Service
public class FetcherService {
	
	@Autowired
	private WebClientService webClientService;

	public List<RepositoryInfo> getRepositoryInfoByUsername(String username) {
		String url = GITHUB_API_URL.replace(USERNAME, username);
		List<RepoListResponse> entityList = fetchRepositoriesFromGithub(url);
		List<RepoListResponse> nonforkRepositories = filterNonforkRepositories(entityList);
		return fetchBranchesAndCreateRepositoryInfo(nonforkRepositories);
	}

	private List<RepositoryInfo> fetchBranchesAndCreateRepositoryInfo(List<RepoListResponse> nonforkRepositories) {
		List<RepositoryInfo> repoInfoList = new ArrayList<RepositoryInfo>();
		nonforkRepositories.forEach(response -> {
			repoInfoList.add(new RepositoryInfo(response.getName(), response.getOwner().getLogin(),
					fetchAndMapBranches(response)));
		});
		return repoInfoList;
	}

	private List<Branch> fetchAndMapBranches(RepoListResponse response) {
		return webClientService.fetchBranchesFromGithub(response.getBranches_url().replace(BRANCH, Strings.EMPTY))
				.stream()
				.map(branchResponse -> new Branch(branchResponse.getName(), branchResponse.getCommit().getSha()))
				.collect(Collectors.toList());
	}

	private List<RepoListResponse> filterNonforkRepositories(List<RepoListResponse> entityList) {
		return entityList.stream().filter(response -> !response.isFork()).collect(Collectors.toList());
	}

	private List<RepoListResponse> fetchRepositoriesFromGithub(String uri) {
		return webClientService.fetchRepositoriesFromGithub(uri);
	}
}
