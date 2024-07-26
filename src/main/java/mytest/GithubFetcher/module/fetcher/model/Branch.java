package mytest.GithubFetcher.module.fetcher.model;

public class Branch {
	private final String name;
	private final String lastCommitSha;

	public Branch(String name, String lastCommitSha) {
		this.name = name;
		this.lastCommitSha = lastCommitSha;
	}

	public String getName() {
		return name;
	}

	public String getLastCommitSha() {
		return lastCommitSha;
	}

}