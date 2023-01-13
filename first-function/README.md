# Java Azure Funtion Sample

### Requirements

- install azure-cli
- have a subscription
- Add azure toolkit plugin for IntelliJ
- INSTALL AZURE FUNCTIONS CORE TOOLS (for command-line development). Includes a version of the same runtime that powers Azure Functions runtime that you can run on your local development computer. It also provides commands to create functions, connect to Azure, and deploy function projects
  - install: `brew tap azure/functions`
  - install: `brew install azure-functions-core-tools@4`

### Create base project

`mvn archetype:generate -DarchetypeGroupId=com.microsoft.azure -DarchetypeArtifactId=azure-functions-archetype -DjavaVersion=11`

Navigate inside the created folder and search for `local.settings.json` and `host.json`

### Run function locally

```shell
mvn clean package
mvn azure-functions:run
```

### Tail the logs (currently only works with windows runtime)

`func azure functionapp logstream <APP_NAME>`

### Clean-up resources

`az group delete --name miztli-resource-group`

### Reference

- [create function using azure cli](https://learn.microsoft.com/en-us/azure/azure-functions/create-first-function-cli-java?tabs=bash%2Cazure-cli%2Cbrowser)
- [create function using intellij](https://learn.microsoft.com/en-us/azure/azure-functions/functions-create-maven-intellij)
- [create timer functions](https://learn.microsoft.com/en-us/azure/azure-functions/functions-bindings-timer?tabs=in-process&pivots=programming-language-java)
- [functions-manually-run-non-http](https://learn.microsoft.com/en-us/azure/azure-functions/functions-manually-run-non-http)