# Java Azure Function Sample

### Requirements

- install azure-cli
- have a subscription
- Add azure toolkit plugin for IntelliJ
- INSTALL AZURE FUNCTIONS CORE TOOLS (for command-line development). Includes a version of the same runtime that powers Azure Functions runtime that you can run on your local development computer. It also provides commands to create functions, connect to Azure, and deploy function projects
  - install: `brew tap azure/functions`
  - install: `brew install azure-functions-core-tools@4`

### Definitions
- Execution context: 
  - Contains helper methods to communicate with the functions runtime.
- Logger:
  - Use getLogger, defined in ExecutionContext, to write logs from function code.
- Configure a storage account:
  - Per azure function app to improve performance in production.
  - In the same region as function app.
  
### Create base project

Use archetype to generate a java function project
`mvn archetype:generate -DarchetypeGroupId=com.microsoft.azure -DarchetypeArtifactId=azure-functions-archetype -DjavaVersion=11`

Navigate inside the created folder and search for `local.settings.json` and `host.json`

### Run function locally
- A `local.settings.json` file is needed
- `JAVA_HOME` env variable must be set correctly to point to the JDK directory that is used during code compiling using maven.
- [host.json](https://learn.microsoft.com/en-us/azure/azure-functions/functions-host-json) file contains configurations that affect all functions in a function app instance.
  - Override [host.json values](https://learn.microsoft.com/en-us/azure/azure-functions/functions-host-json#override-hostjson-values)
  - Used to configure advanced behavior of function triggers and the Azure Functions runtime

```shell
mvn clean package
mvn azure-functions:run
```

### Deploy function
```shell
mvn azure-functions:deploy
```

### Update configs using CLI
```shell
az functionapp config appsettings set --settings "MIZTLI_TEST=foo" --name <function-name-change-me> --resource-group <rg-name-change-me>
```

### Tail the logs (currently only works with windows runtime)

`func azure functionapp logstream <APP_NAME>`
`az webapp log tail --name <webappname> --resource-group <myResourceGroup>`

Download log:
`az webapp log download --resource-group <resourcegroupname> --name <functionappname>`

### Function App -> Key Vault

- Function App --- 1:1 ---> Managed Identity ---1:*---> Roles ---1:*---> Data actions

### Clean-up resources

`az group delete --name miztli-resource-group`

### Reference

- [create function using azure cli](https://learn.microsoft.com/en-us/azure/azure-functions/create-first-function-cli-java?tabs=bash%2Cazure-cli%2Cbrowser)
- [create function using intellij](https://learn.microsoft.com/en-us/azure/azure-functions/functions-create-maven-intellij)
- [create timer functions](https://learn.microsoft.com/en-us/azure/azure-functions/functions-bindings-timer?tabs=in-process&pivots=programming-language-java)
- [functions-manually-run-non-http](https://learn.microsoft.com/en-us/azure/azure-functions/functions-manually-run-non-http)