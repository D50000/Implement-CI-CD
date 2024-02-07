# Bitbucket Hook

1. Using the Java plugin framework
   [Pre-Receive with configuration](https://docs.atlassian.com/bitbucket-server/docs/4.7.1/how-tos/repo-hook-examples/pre-receive-branch-config.html)
   [Stash Plugin Tutorial: Beer O'Clock Repository Hook](https://blog.developer.atlassian.com/beer-o-clock-stash-plugin-tutorial/)
2. [Creating the hook](https://marketplace.atlassian.com/apps/1211631/external-hooks-by-reconquest?hosting=server&tab=overview)

## Primary Approaches

versions below 8.0:  
**Created directly on the file system**

1. Navigate to a repository in a bitbucket instance:  
   [Identifying a repository's ID in Bitbucket Server](https://confluence.atlassian.com/bitbucketserverkb/identifying-a-repository-s-id-in-bitbucket-server-779171333.html)
2. Navigate to the directory of pre-receive or post-receive hooks:Inside the hooks directory of a repository (on the server where Bitbucket is running)  you will find  pre-receive, post-receive.
1. Create a file in the pre or post-receive directory
   Troubleshooting

version 8.0 and above:  
No longer support creating hook scripts. Just use [**REST API**](https://developer.atlassian.com/server/bitbucket/rest/v810/api-group-system-maintenance/#api-api-latest-hook-scripts-post).
