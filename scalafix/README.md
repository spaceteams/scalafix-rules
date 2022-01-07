# Development
To develop rules while testing:
```
sbt ~tests/test
# edit files in rules/src/main/scala/fix/
```

Testing against cross-published versions: `sbt +test`

To publish locally use `sbt publishLocal`.
When published locally you can use it the same as though it is published on Maven (see [../README.md](../README.md)).
