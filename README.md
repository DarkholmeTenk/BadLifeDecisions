# [BadLifeDecisions][repo]

Graphene Condoms' solution for comp34120 Exercise 2.

## Building

```sh
$ ./gradlew build
```

## Branches

master: current stable build is guaranteed to have passed all tests and be in a working state.  
dev: current development build all branches should be merged to the dev build until the dev build is stable and then merged into master only once it passes all tests and is stable to the best of our knowledge.

## Running

The platform and GUI must be running before any leaders can be run:

```sh
$ ./scripts/runPlatform.sh
```

With the platform running, you can run the leader.

```sh
$ ./scripts/runLeader.sh
```

[repo]: https://github.com/DarkholmeTenk/BadLifeDecisions
