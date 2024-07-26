package mytest.GithubFetcher.advice.exception;


import com.fasterxml.jackson.annotation.JsonProperty;


public class ErrorDTO {
	@JsonProperty("status")
    private int status;
    @JsonProperty("message")
    private String message;
	public ErrorDTO(int status, String message) {
		this.status = status;
		this.message = message;
	}
    
}
