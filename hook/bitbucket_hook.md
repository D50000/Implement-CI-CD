# Bitbucket Hook

1. Using the Java plugin framework
   [Pre-Receive with configuration](https://docs.atlassian.com/bitbucket-server/docs/4.7.1/how-tos/repo-hook-examples/pre-receive-branch-config.html)
   [Stash Plugin Tutorial: Beer O'Clock Repository Hook](https://blog.developer.atlassian.com/beer-o-clock-stash-plugin-tutorial/)
2. [Creating the hook](https://marketplace.atlassian.com/apps/1211631/external-hooks-by-reconquest?hosting=server&tab=overview)

## Primary Approaches
versions below 8.0:
Created directly on the file system

version 8.0 and above:  
No longer support creating hook scripts. Just use [REST API](https://developer.atlassian.com/server/bitbucket/rest/v810/api-group-system-maintenance/#api-api-latest-hook-scripts-post).
