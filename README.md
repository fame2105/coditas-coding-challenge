# coditas-coding-challenge
The aim is to consume all the APIs and create a single unified API.

Following clients have been implemented: 
GithubClient
GitlabClient
In order to implement a new client in the future, make a new client and leverage it in the Aggregator Service, and add/remove/change new properties in 
IntegratedApiBean accordingly.

Exposed endpoint: /user/{userName}/projects
this endpoint will look for publicly exposed projects on Github and Gitlab for the userName parameter passed in the path.

Following filters are available for this API:
language : projects can be filtered based on the language in which they have been written, but only for GITHUB, Gitlab doesn't provides language parameter in its response, to be able to work with it.
owned : projects that have been originally created by the user identified by userName, i.e they haven't been forked but created from scratch by that user, but only for GITHUB, Gitlab provides this option where we can filter the projects returned on the basis of who own it, but Gitlab identifies the forked repositories also as the userName's projects, i.e rendering owned propery in their API somewhat useless. 
projectName: if we looking for a project by a certain name then, we can pass this parameter to get a list of projects that match with this name for the passed in userName for Github and Gitlab. 

Usage:

/user/{userName}/projects?language=Java
/user/{userName}/projects?owned=true
/user/{userName}/projects?owned=false
/user/{userName}/projects?projectName=coditas-coding-challenge

we can also use these parameters simultaneously to filter based on all or combination of these parameters
ex:
/user/{userName}/projects?language=Java&owned=true&projectName=coditas-coding-challenge



