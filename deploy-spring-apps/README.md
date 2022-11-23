# Deploy a spring application in azure

## Pre requisites
- azure cli

### Steps

- Login into azure: `az login`

- List account, search for subscription id: `az account list --output table`

- Set subscription (in case you have more than 1): `az account set --subscription <subscription-id>`

- Create a resource group: `az group create --resource-group spring-app-resource-group --tags key1=val1 key2=val2 --location eastus`

- Create an Azure Spring Apps service instance: `az spring create --resource-group spring-app-resource-group --name miztli-springapp-serviceinstance`

- Create an app in your azure service instance: `az spring app create --resource-group spring-app-resource-group --service miztli-springapp-serviceinstance --name api-service --assign-endpoint true --runtime-version Java_17`

- Deploy your app: `az spring app deploy --resource-group spring-app-resource-group --service miztli-springapp-serviceinstance --name api-service --artifact-path containers-practice/api-service/target/api-service-0.0.1-SNAPSHOT.jar`

- Once deployment has completed, you can access the app at `https://<service instance name>-hellospring.azuremicroservices.io/`

- Cleanup resources: `az group delete --name spring-app-resource-group`

