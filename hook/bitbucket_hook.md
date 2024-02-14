# Bitbucket Hook

1. Using the Java plugin framework
   [Pre-Receive with configuration](https://docs.atlassian.com/bitbucket-server/docs/4.7.1/how-tos/repo-hook-examples/pre-receive-branch-config.html)
   [Stash Plugin Tutorial: Beer O'Clock Repository Hook](https://blog.developer.atlassian.com/beer-o-clock-stash-plugin-tutorial/)
2. [Creating the hook](https://marketplace.atlassian.com/apps/1211631/external-hooks-by-reconquest?hosting=server&tab=overview)

## Primary Approaches

### Versions below 8.0:  
**Created directly on the file system**

1. Navigate to a repository in a bitbucket instance:  
   [Identifying a repository's ID in Bitbucket Server](https://confluence.atlassian.com/bitbucketserverkb/identifying-a-repository-s-id-in-bitbucket-server-779171333.html)  
   Bitbucket Server hooks are enabled and configured on a per repository basis so you will need to create your hook in each repositories hooks directory.
2. Navigate to the directory of pre-receive or post-receive hooks:  
   Inside the hooks directory of a repository (on the server where Bitbucket is running) you will find pre-receive, post-receive.
3. Create a file in the pre or post-receive directory:  
   If you have multiple hooks and you want them to happen in a specific order number them accordingly: 21_pre_receive, 22_pre_receive, 23_pre_receive and so on. (ps: This number should always be something higher than **20** because the script that we ship needs to be executed first.)
4. Troubleshooting
   - If a hook is not functioning one thing you can check is the application.xml file located in BITBUCKET_HOME/logs/support (this file is generated when you create a Support Zip)
   - Make sure that the isExecutable element is true.
   - If you are executing a command that the user running Stash/Bitbucket server doesn't have permission to, that will fail.

### Version 8.0 and above:  
No longer support creating hook scripts. Just use [**REST API**](https://developer.atlassian.com/server/bitbucket/rest/v810/api-group-system-maintenance/#api-api-latest-hook-scripts-post).
