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

The GUI must be running before any leaders can be run:

```sh
$ ./scripts/runGUI.sh
```

With the GUI running, you can run a leader. For Example:

```sh
$ ./scripts/runLeader.sh bld.SimpleLeader
```

[repo]: https://github.com/DarkholmeTenk/BadLifeDecisions
