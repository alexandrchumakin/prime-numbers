# Prime number generator
This simple app generates a list of prime numbers up to provided max integer.

## Local setup
- Install Java 17+ version (follow [this](https://docs.oracle.com/en/java/javase/17/install/) instructions)
- Maven 3.8 (reach out to [this link](https://maven.apache.org/install.html))
- Run `mvn compile exec:java` (will run with default max primer number 100)
- Run `mvn compile exec:java -Dexec.args=1000000` (to run with desired max number)

## Technical decision
The final implementation in [PrimeStreamCalc](src/main/java/org/achumakin/PrimeStreamCalc.java) only uses built-in
functionality from IntStream that implements working with `CompletableFuture` under the hood. 

More class example of multi-threading is [PrimePoolCalc](src/main/java/org/achumakin/PrimePoolCalc.java) where thread
pool is managing threads and results are populated to an instance of `ConcurrentLinkedQueue`. Time execution and usage 
of hardware is exactly the same as with mainly used class.
