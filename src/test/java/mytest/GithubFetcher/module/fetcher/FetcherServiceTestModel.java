package mytest.GithubFetcher.module.fetcher;

import java.util.List;

import mytest.GithubFetcher.module.fetcher.model.BranchResponse;
import mytest.GithubFetcher.module.fetcher.model.Commit;
import mytest.GithubFetcher.module.fetcher.model.Owner;
import mytest.GithubFetcher.module.fetcher.model.RepoListResponse;

class FetcherServiceTestModel {

	static final List<RepoListResponse> getRepoList() {
		Owner owner1 = new Owner();
		owner1.setLogin("ownerlogin");
		RepoListResponse repo1 = new RepoListResponse();
		repo1.setBranches_url("branchesURL");
		repo1.setFork(false);
		repo1.setName("name");
		repo1.setOwner(owner1);
		return List.of(repo1);
	}

	static final List<BranchResponse> getBranchResponseList() {
		BranchResponse branch1 = new BranchResponse();
		Commit testCommit = new Commit();
		testCommit.setSha("sha1");
		branch1.setCommit(testCommit);
		branch1.setName("branch1name");
		return List.of(branch1);
	}

}
