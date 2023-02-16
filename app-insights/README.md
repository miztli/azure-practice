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
    message = iff(message != '', message, iff(innermostMessage != '', innermostMessage, customDimensions.['prop__{OriginalFormat}'])),
    logLevel = customDimensions.['LogLevel']
```

last minute traces
```shell
traces
| where timestamp < ago(20m) and operation_Name == 'RunMigration'
| order by timestamp asc
| project
    timestamp,
    message,
    logLevel = customDimensions.['LogLevel']
```

range traces
```shell
traces
| where timestamp between (datetime(2023-02-15 15:35:00)..datetime(2023-02-15 16:40:00)) and operation_Name == 'RunMigration'
| order by timestamp desc
| project
    timestamp,
    message,
    logLevel = customDimensions.['LogLevel']
```