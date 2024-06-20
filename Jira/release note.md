<!-- Jira Release Note template for "Jira Automation" -->

**Release Notes for Version: {{version.name}}**

*Released on:* {{version.releaseDate}}

**Issues in this release:**

{% for issue in issues %}
- [{{issue.key}}] {{issue.summary}}
{% endfor %}
