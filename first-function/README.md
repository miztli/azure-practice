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

### Reference

- https://learn.microsoft.com/en-us/azure/azure-functions/create-first-function-cli-java?tabs=bash%2Cazure-cli%2Cbrowser