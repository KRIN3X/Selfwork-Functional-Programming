# Java Functional Programming

A simple Java project with all examples from the "Java Functional Programming" PDF.

## Project Structure

```
src/
├── App.java                         # All examples in one file
├── Gender.java                      # Enum: MALE, FEMALE
├── Person.java                      # Person class
├── Color.java                       # Enum: RED, GREEN
├── Apple.java                       # Apple class with color and weight
├── ApplePredicateInterface.java     # Custom predicate interface
├── AppleGreenColorPredicate.java    # Filter: green apples
├── AppleRedColorPredicate.java      # Filter: red apples
├── AppleWeightPredicate.java        # Filter: heavy apples (> 150g)
├── AppleLightPredicate.java         # Filter: light apples (<= 150g)
├── AppleRedHeavyPredicate.java      # Filter: red AND heavy
└── AppleRedLightPredicate.java      # Filter: red AND light
```

## Examples Included

1. **Imperative vs Functional** - Compare two approaches to filtering
2. **Stream Operations** - Filter, map, reduce operations
3. **Apple Filtering Step 1** - Basic filtering with hardcoded logic
4. **Apple Filtering Step 2** - Parameterized filtering by color
5. **Apple Filtering Step 3** - Using predicates
6. **Apple Filtering Step 4** - Using lambda expressions
7. **Java Predicate Interface** - Using built-in Predicate<T>

## How to Run

### Compile:
```bash
cd src
javac *.java
```

### Run all examples:
```bash
java App
```

## Requirements

- Java 8+ (for lambda expressions and streams)
- No external dependencies