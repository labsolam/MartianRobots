# Martian Robots

A small Java command line app to process Martian robot instructions.


## Requirements

- JDK 25

## Build

### Linux/Mac

```sh
./mvnw package
```
### Windows
```cmd
mvnw.cmd
```

Builds `target/martian-robots.jar`

## Run

### Whole file processing

The app is designed to accept input from stdin and output to stdout. 

It's best used when passing a whole file contents into it e.g.

```sh
java -jar target/martian-robots.jar < examples/sample.txt
```

### Manual input

You can run `java -jar target/martian-robots.jar` and type the input. Each result appears as soon as its robot's instruction line is entered.

### Examples

`examples/` holds `sample.txt` (the brief's sample), `edge-cases.txt`, and `limits.txt` (a 50 50 grid and 99-character instructions), each with a `.expected.txt` output to validate against.

## Test

```sh
./mvnw test
```

## Assumptions

- Input is always valid
  - No blank lines
  - Correct input order - Mars grid coordinates, robot position, instructions, robot position, instructions etc
  - Robot positions are within Mars boundaries
  - Mars coordinates are within their bounds
  - All instructions exist
  - Instructions are case sensitive (could have lowercase instructions later)
  - Instructions are under 100 characters
- Scent is keyed by position and direction: a robot is only saved if a previous robot fell off the same point facing the same way
- Robots can occupy the same position
- Use JDK 25 default charset (UTF-8)

## Design decisions

- Use maven wrapper for reproducible builds
- Strategy pattern for commands
  - More commands can be added later by implementing the Command interface and adding to the MissionRunner
- Dependency injection to support testability (this also allows multiple missions to have different commands if for example if there are different robot versions)
- Scent is the only mutable state as Mars is shared
- Spotless for code formatting

## With more time

- Input validation. You can be very defensive with validation.
  - Validate Mars coordinates
  - Validate Robot coordinates and orientation
  - How to handle blank lines? That could mean no instructions
  - Skip instructions if any instructions are unknown
  - Validate instructions pairs (1:1 robot - instructions)
- Persisting scents 
