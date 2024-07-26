package mytest.GithubFetcher.module.fetcher.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import mytest.GithubFetcher.module.fetcher.FetcherService;
import mytest.GithubFetcher.module.fetcher.model.RepositoryInfo;

@RestController
public class FetcherController {
	@Autowired
	private FetcherService fetcherService;

	@GetMapping("get-repositories-by-username/{username}")
	public List<RepositoryInfo> getRepostioriesByUsername(@RequestHeader(HttpHeaders.ACCEPT) String acceptHeader,
			@PathVariable String username) {
		return fetcherService.getRepositoryInfoByUsername(username);
	};
}