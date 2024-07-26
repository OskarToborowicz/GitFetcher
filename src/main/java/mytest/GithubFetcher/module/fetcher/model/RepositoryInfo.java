package mytest.GithubFetcher.module.fetcher.model;

import java.util.List;

public class RepositoryInfo {
	private final String name;
	private final String ownerLogin;
	private final List<Branch> branches;

	public RepositoryInfo(String name, String ownerLogin, List<Branch> branches) {
		this.name = name;
		this.ownerLogin = ownerLogin;
		this.branches = branches;
	}

	public String getName() {
		return name;
	}

	public String getOwnerLogin() {
		return ownerLogin;
	}

	public List<Branch> getBranches() {
		return branches;
	}
}