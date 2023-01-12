Kubernetes Deployment

Steps followed:

### Create a MySQl DB instance using azure portal

- Create a resource-group 
`az group create --resource-group miztli-resource-group --location eastus`

- Create a SQL DB Server (use azure portal)
  
  - _Azure Database for my sql server_ section
  
    - provide admin credentials
      
      - miztli
      - 3Du@rdo!#

- Add a DB. Go to Settings > Databases > Add
- Download public cert used by the server: `wget --no-check-certificate https://dl.cacerts.digicert.com/DigiCertGlobalRootCA.crt.pem`
- Connect using mysql (or any other client): `mysql -h <server_name>.mysql.database.azure.com -u <user_name> -p --ssl=true --ssl-ca=DigiCertGlobalRootCA.crt.pem`

### Create a vnet and subnet using CLI

`az network vnet create --resource-group miztli-resource-group --name miztli-vnet --address-prefixes 155.55.0.0/16 --subnet-name miztli-subnet-mysql --subnet-prefix 155.55.1.0/24`

### Create a MySQl DB instance using azure CLI

`az mysql flexible-server create --name miztliFlexibleServer --resource-group miztli-resource-group --location eastus --admin-user miztli --admin-password '3Du@rdo!#' --vnet miztli-vnet --subnet miztli-subnet-mysql`

dns: `miztliflexibleserver.private.mysql.database.azure.com`

### Create schema

`az mysql flexible-server db create --resource-group miztli-resource-group --server-name miztliFlexibleServer --database-name employees-db`

### Create container registry

`az acr create --resource-group miztli-resource-group --location eastus --name miztlicontainerregistry --sku Basic`

### Login to container registry

`az acr login --name miztlicontainerregistry`

### Tag image for container registry
`docker tag employee-service:latest miztlicontainerregistry.azurecr.io/employee-service:v1.0`

verify image tag locally: `docker image list`

### Push image to container registry

`docker push miztlicontainerregistry.azurecr.io/employee-service:v1.0`

verify image tag remotely: `az acr repository list --name miztlicontainerregistry --output table`

### Create a subnet for AKS cluster using CLI

`az network vnet subnet create --resource-group miztli-resource-group --vnet-name miztli-vnet --name miztli-subnet-aks --address-prefixes 155.55.2.0/24`

set SUBNET_ID var: `SUBNET_ID=$(az network vnet subnet show --resource-group miztli-resource-group --vnet-name miztli-vnet --name miztli-subnet-aks --query id -o tsv)`

### Create an AKS cluster in the virtual network, with Azure Container Registry (ACR) attached

`az aks create --resource-group miztli-resource-group --name miztli-aks-cluster-demo --network-plugin azure --service-cidr 10.0.0.0/16 --dns-service-ip 10.0.0.10 --docker-bridge-address 172.17.0.1/16 --vnet-subnet-id $SUBNET_ID --attach-acr miztlicontainerregistry --dns-name-prefix miztli-dns-aks-demo --generate-ssh-keys`

### Cleanup resources

- Remove resource group
`az group delete --name miztli-resource-group`

Errors:

```shell
cli.azure.cli.command_modules.acs._roleassignments: The client 'emelgoza@griddynamics.com' with object id '543f593e-42f9-4f1e-a959-98f355c8d83c' does not have authorization to perform action 'Microsoft.Authorization/roleAssignments/write' over scope '/subscriptions/afa1a461-3f97-478d-a062-c8db00c98741/resourceGroups/miztli-resource-group/providers/Microsoft.Network/virtualNetworks/miztli-vnet/subnets/miztli-subnet-aks/providers/Microsoft.Authorization/roleAssignments/d0036641-aaa7-471f-b4b6-60adc944c6a5' or the scope is invalid. If access was recently granted, please refresh your credentials.
cli.azure.cli.command_modules.acs.managed_cluster_decorator: Could not create a role assignment for subnet. Are you an Owner on this subscription?
```

```shell
msrest.exceptions: The client 'emelgoza@griddynamics.com' with object id '543f593e-42f9-4f1e-a959-98f355c8d83c' does not have authorization to perform action 'Microsoft.Authorization/roleAssignments/write' over scope '/subscriptions/afa1a461-3f97-478d-a062-c8db00c98741/resourceGroups/miztli-resource-group/providers/Microsoft.ContainerRegistry/registries/miztlicontainerregistry/providers/Microsoft.Authorization/roleAssignments/bf517fdc-78de-4a75-906d-d2983b724f40' or the scope is invalid. If access was recently granted, please refresh your credentials.
cli.azure.cli.command_modules.acs._roleassignments: The client 'emelgoza@griddynamics.com' with object id '543f593e-42f9-4f1e-a959-98f355c8d83c' does not have authorization to perform action 'Microsoft.Authorization/roleAssignments/write' over scope '/subscriptions/afa1a461-3f97-478d-a062-c8db00c98741/resourceGroups/miztli-resource-group/providers/Microsoft.ContainerRegistry/registries/miztlicontainerregistry/providers/Microsoft.Authorization/roleAssignments/bf517fdc-78de-4a75-906d-d2983b724f40' or the scope is invalid. If access was recently granted, please refresh your credentials.
cli.azure.cli.core.azclierror: Could not create a role assignment for ACR. Are you an Owner on this subscription?
```

### Resources

- [create-azure-database-for-mysql](https://learn.microsoft.com/en-us/training/modules/create-azure-database-for-mysql/)
- [container-registry-get-started-azure-cli](https://learn.microsoft.com/en-us/azure/container-registry/container-registry-get-started-azure-cli)
- [tutorial-deploy-springboot-on-aks-vnet](https://learn.microsoft.com/en-us/azure/mysql/flexible-server/tutorial-deploy-springboot-on-aks-vnet)
