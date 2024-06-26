# Implement CI/CD

Continuous Integration/Continuous Deployment for software development.The main idea is to enhance the developer's coding experience and streamline the software development process. 

**Typical Work Flow:**  
```shell
+--------------------------------------+
|          Code Repository (Git)       |
+--------------------------------------+
                  ↓↓
             Commit/Push
                  ↓↓
            Webhook Trigger
                  ↓↓
+--------------------------------------+
|         CI Server (Jenkins)          |
+--------------------------------------+
                  ↓↓
              Build/Test (pipeline)
                  ↓↓
            Archive Artifact
                  ↓↓
+--------------------------------------+
|        Artifact Repository (Git)     |
+--------------------------------------+
                  ↓↓
          Trigger CD process
                  ↓↓
                Deploy
                  ↓↓
+--------------------------------------+
|       Production Environment         |
+--------------------------------------+
```

## Integrate with

- **Bitbucket Repository**
  - Store the source code and artifacts.
  - **webhooks:** Trigger by commit and push notification or trigger other task as CI/CD. ([Bitbucket setup detail](https://github.com/D50000/Implement-CI-CD/blob/main/hook/bitbucket_hook.md))
- **Git Hook:** _Major benefits of using Git hooks include encouraging a commit policy, automating development workflow, and implementing continuous integration._
  - **client-side: local hooks**, which are prompted by events on the local repository, such as when a developer commits or merges code.
  - **server-side: remote hooks**, which are run on the network hosting the repository, and they are prompted by events such as receiving pushes.
- **Jenkis**
  - Standardization and consistency in CI/CD progress.
  - Monitor and archive the execute status.
- **Jira**
  - Management the task progress and status.
  - Publish release note after project merge. (Plugin: *Jira Automation* or *ScriptRunner*)


### Reference

- [Learn Git](https://www.atlassian.com/git/glossary#commands)
- [Get Started with Git Hooks](https://medium.com/@f3igao/get-started-with-git-hooks-5a489725c639)
- [Do not do pre-commit at client-side](https://www.youtube.com/watch?v=RAelLqnnOp0)
- [Workflow strategies](https://confluence.atlassian.com/bitbucketserver/workflow-strategies-776639944.html)
- [The essence of branch-based workflows](https://www.atlassian.com/blog/git/the-essence-of-branch-based-workflows)
- [5 ways to make the most of Jira Software and Bitbucket](https://www.atlassian.com/blog/software-teams/5-jira-software-bitbucket-cloud-integration-tips)
- [Jenkins Pipeline](https://www.jenkins.io/doc/book/pipeline/)
- [Jira](https://www.atlassian.com/software/jira?&aceid=&adposition=&adgroup=141043469439&campaign=18445833028&creative=697046809358&device=c&keyword=jira%20tutorial&matchtype=e&network=g&placement=&ds_kids=p73335842418&ds_e=GOOGLE&ds_eid=700000001558501&ds_e1=GOOGLE&gad_source=1&gclid=CjwKCAjwps-zBhAiEiwALwsVYRs4TZ7L9AsYQBEVcCY2Nt2lcVUw0supIrtWWqRrHHAdi57x3EVpEhoCjVoQAvD_BwE&gclsrc=aw.ds)
- [Jira overview](https://www.youtube.com/watch?v=fiWaMGCMyk8)
