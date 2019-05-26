# Producer-Consumer model implemented by Java

## Usage
``` bash
javac Main.java
java Main
```

## Overview
A "Producer" will insert a pair of words into a "Container". While a "Consumer" will retrieve a pair from the "Container" and calculate a "Word Ladder" between them.

Both "Producer" and "Consumer" shares an singeton object "Container", where I have encapsulated the insertion and retrieval logic (It will acts as a stack when there are too many requests (above a threshold) in the container, otherwise it will acts as a queue.) with a LinkedBlockingDeque.