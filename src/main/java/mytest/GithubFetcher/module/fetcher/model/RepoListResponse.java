package mytest.GithubFetcher.module.fetcher.model;

public class RepoListResponse {
	private String name;
	private String branches_url;
	private Boolean fork;
	private Owner owner;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBranches_url() {
		return branches_url;
	}

	public void setBranches_url(String branches_url) {
		this.branches_url = branches_url;
	}

	public Boolean isFork() {
		return fork;
	}

	public void setFork(Boolean fork) {
		this.fork = fork;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
}
