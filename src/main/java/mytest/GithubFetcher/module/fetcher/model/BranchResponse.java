package mytest.GithubFetcher.module.fetcher.model;

public class BranchResponse {
	private String name;
	private Commit commit;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Commit getCommit() {
		return commit;
	}

	public void setCommit(Commit commit) {
		this.commit = commit;
	}
}