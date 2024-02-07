# Git Hook

## [Conceptual overview](https://www.atlassian.com/git/tutorials/git-hooks)

- **Client-Side: scripts paths:** `project/.git/hooks`
  **_applypatch-msg.sample_**  
  **_pre-push.sample_**  
  **_commit-msg.sample_**  
  **_pre-rebase.sample_**  
  **_post-update.sample_**  
  **_prepare-commit-msg.sample_**  
  **_pre-applypatch.sample_**  
  **_update.sample_**  
  **_pre-commit.sample_**

- **[Server-Side (Bitbucket Setting)](https://confluence.atlassian.com/bitbucketserverkb/how-to-create-a-simple-hook-in-bitbucket-data-center-and-server-779171711.html)**  
  **_pre-receive_**  
  Before branch push, but do not fire when a pull-request is merged.
  **_post-receive_**  
  After branch pushed. ex: notify a build server.