package mytest.GithubFetcher.module.fetcher;

import static mytest.GithubFetcher.module.fetcher.FetcherServiceTestModel.getBranchResponseList;
import static mytest.GithubFetcher.module.fetcher.FetcherServiceTestModel.getRepoList;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import mytest.GithubFetcher.advice.exception.UserNotFoundException;
import mytest.GithubFetcher.module.fetcher.model.RepositoryInfo;

@SpringBootTest
class FetcherServiceTest {

	@Autowired
	private FetcherService fetcherService;

	@MockBean
	private WebClientService webClientService;

	@Test
	void shouldReturnRepositoryInfoListGivenUsername() {

		// Given
		final String username = "username";
		when(webClientService.fetchBranchesFromGithub(anyString())).thenReturn(getBranchResponseList());
		when(webClientService.fetchRepositoriesFromGithub(anyString())).thenReturn(getRepoList());

		// When
		List<RepositoryInfo> result = fetcherService.getRepositoryInfoByUsername(username);

		// Then
		assertEquals(1, result.size());
		assertEquals("name", result.get(0).getName());
		assertEquals("ownerlogin", result.get(0).getOwnerLogin());
		assertEquals(1, result.get(0).getBranches().size());
		assertEquals("branch1name", result.get(0).getBranches().get(0).getName());
		assertEquals("sha1", result.get(0).getBranches().get(0).getLastCommitSha());
	}
	
	@Test
	void shouldReturnExceptionGivenWrongUsername() {

		// Given
		final String wrongUsername = "wrongUsername";
		when(webClientService.fetchBranchesFromGithub(anyString())).thenReturn(getBranchResponseList());
		when(webClientService.fetchRepositoriesFromGithub(anyString())).thenThrow(new UserNotFoundException("Not found."));
	
		// When and Then
		assertThrows(UserNotFoundException.class, ( ) -> fetcherService.getRepositoryInfoByUsername(wrongUsername));
	}

}