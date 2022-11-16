# Practices for Microsoft Certified: Azure Fundamentals

[Microsoft Azure Fundamentals Certification](https://learn.microsoft.com/en-us/certifications/azure-fundamentals/)

[Content for study](https://learn.microsoft.com/en-us/users/sandramarin/collections/n6ga8m0jkgrwk)

[Skills measured](https://query.prod.cms.rt.microsoft.com/cms/api/am/binary/RE3VwUY)

### Azure CLI installation

Install with command:

`brew install azure-cli`

Verify installation:

`az --version`

Check azure version:

`az version`

Run with interactive mode:

`az interactive`

### Practice 1 - [Create a VM using Azure Portal](https://learn.microsoft.com/en-us/training/modules/describe-core-architectural-components-of-azure/7-exercise-create-azure-resource)

Steps followed:
- Create azure trial free account
- Give permissions to the sandbox to create Azure resources

### Practice 2 - [Create a vm using command line](https://learn.microsoft.com/en-us/training/modules/describe-azure-compute-networking-services/3-exercise-create-azure-virtual-machine)

Steps followed:

- create a vm
```shell
az vm create --resource-group learn-647a1570-c23b-48e6-90af-9798db20696d --name my-vm --image UbuntuLTS --admin-username azureuser --generate-ssh-keys
```

- configure nginx in vm
```shell
az vm extension set --resource-group learn-647a1570-c23b-48e6-90af-9798db20696d --vm-name my-vm --name customScript --publisher Microsoft.Azure.Extensions --version 2.1 --settings '{"fileUris":["https://raw.githubusercontent.com/MicrosoftDocs/mslearn-welcome-to-azure/master/configure-nginx.sh"]}' --protected-settings '{"commandToExecute": "./configure-nginx.sh"}'
```

### Practice 3 - [Configure network access](https://learn.microsoft.com/en-us/training/modules/describe-azure-compute-networking-services/9-exercise-configure-network-access)

Steps followed:

- List IP addresses:
```shell
az vm list-ip-addresses --resource-group learn-b1fa632b-97d5-4b20-90ea-3b30de56d286 --name my-vm --query "[].virtualMachine.network.publicIpAddresses[*].ipAddress" --output tsv
```

- Assign IP to variable (done in azure cloud shell)
```shell
IPADDRESS="$(az vm list-ip-addresses --resource-group learn-b1fa632b-97d5-4b20-90ea-3b30de56d286 --name my-vm --query "[].virtualMachine.network.publicIpAddresses[*].ipAddress" --output tsv)"
```

- curl to download homepage
```shell
curl --connect-timeout 5 http://$IPADDRESS
```

- list network security rules associated to VM
```shell
az network nsg list --resource-group learn-b1fa632b-97d5-4b20-90ea-3b30de56d286 --query '[].name' --output tsv
```

- list the rules associated with the network security group
```shell
az network nsg rule list --resource-group learn-b1fa632b-97d5-4b20-90ea-3b30de56d286 --nsg-name my-vmNSG
```

- For a better readability, format the previous output
```shell
az network nsg rule list --resource-group learn-b1fa632b-97d5-4b20-90ea-3b30de56d286 --nsg-name my-vmNSG --query '[].{Name:name, Priority:priority, Port:destinationPortRange, Access:access}' --output table
```
 
- Currently, we see only SSH port 22 is allowed for inbound connections, let's enable port 80 for http connections
```shell
az network nsg rule create --resource-group learn-b1fa632b-97d5-4b20-90ea-3b30de56d286 --nsg-name my-vmNSG --name allow-http --protocol tcp --priority 100 --destination-port-range 80 --access Allow
```
Run the previous command for validating the creation of another rule for http port


### Practice 4 - [Create a storage account](https://learn.microsoft.com/en-us/training/modules/describe-azure-storage-services/5-exercise-create-storage-blob)

### Practice 5 - [Configure a resource lock](https://learn.microsoft.com/en-us/training/modules/describe-features-tools-azure-for-governance-compliance/5-exercise-configure-resource-lock)