
## GitFetcher 
GitFetcher is a program to fetch non-fork github repositories by providing username.
Program is written in java 21 and Spring Boot 3 framework.

## Usage

Open the project in your IDE as a Maven project and then run the GithubFetcherApplication class as the main class.
The API is located in FetcherController class.  

Example usage:

curl -H "Accept: application/json" localhost:8080/get-repositories-by-username/OskarToborowicz

Gives output:
```
[
    {
        "name": "calculator",
        "ownerLogin": "OskarToborowicz",
        "branches": [
            {
                "name": "main",
                "lastCommitSha": "622859b5f3552832d9477b13624b8014c2dc0963"
            }
        ]
    },
    {
        "name": "GitFetcher",
        "ownerLogin": "OskarToborowicz",
        "branches": [
            {
                "name": "main",
                "lastCommitSha": "24381ad655922a60a7a7acc42c0d8db418bd3cec"
            },
            {
                "name": "master",
                "lastCommitSha": "0089598bc1797771cc78f315531fc9230a8202de"
            }
        ]
    },
    {
        "name": "todolist",
        "ownerLogin": "OskarToborowicz",
        "branches": [
            {
                "name": "main",
                "lastCommitSha": "0d22411f3682f44431446f8a53e6f9eaac8783bb"
            },
            {
                "name": "test",
                "lastCommitSha": "50a6f5aa96908c6379df541d2e170051e5b718e5"
            }
        ]
    }
]
```
