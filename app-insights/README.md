### App Insights

## Queries

List last 15 min exceptions
```shell
exceptions
| where timestamp > ago(15m)
| order by timestamp desc 
```

Last minutes logs
```shell
union traces
| union exceptions
| where timestamp > ago(15m)
| order by timestamp desc
| project
    timestamp,
    message,
    logLevel = customDimensions.['LogLevel']
```

last minute traces
```shell
traces
| where timestamp > ago(20m) and operation_Name == 'RunMigration'
| order by timestamp asc
| project
    timestamp,
    message,
    logLevel = customDimensions.['LogLevel']
```

period of time

```shell
union traces
| union exceptions
| where timestamp > ago(20m)
| where message contains "Executed"
| where operation_Name == 'RunMigration'
| order by timestamp asc
```

range traces
```shell
traces
| where timestamp 
 and operation_Name == 'RunMigration'
| order by timestamp desc
| project
    timestamp,
    message,
    logLevel = customDimensions.['LogLevel']
```