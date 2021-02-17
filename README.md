# gojek-parking-lot-assignment
Gojek parking lot (code pairing or machine coding round) assignment

# Getting started (Run the application)
Go to root of project and run the following commands

## input from console
/bin/sh ./setup.sh & /bin/sh ./parking_lot.sh

# Commands supported
1. create_parking_lot <Size of parking spot>
2. park <Car registration Number> <Car's color>
3. leave <Slot id>
4. status
5. registration_numbers_for_cars_with_colour <Car's color>


# Sample Input & Output for interactive mode
input:

create_parking_lot 6
park KA-01-HH-1234 White
park KA-01-HH-9999 White
park KA-01-BB-0001 Black
park KA-01-HH-7777 Red
park KA-01-HH-2701 Blue
park KA-01-HH-3141 Black
leave 4
status
park KA-01-P-333 White
park DL-12-AA-9999 White
registration_numbers_for_cars_with_colour White

expected output:
Created a parking lot with 6 slots
Allocated slot number: 1
Allocated slot number: 2
Allocated slot number: 3
Allocated slot number: 4
Allocated slot number: 5
Allocated slot number: 6
Slot number 4 is free
Slot No.        Registration No     Colour
1               KA-01-HH-1234       White
2               KA-01-HH-9999       White
3               KA-01-BB-0001       Black
5               KA-01-HH-2701       Blue
6               KA-01-HH-3141       Black
Allocated slot number: 4
Sorry, parking lot is full
KA-01-HH-1234, KA-01-HH-9999, KA-01-P-333


# Things I tried to follow :
1. Tried to create all the required domain entities/models as per problem statement

2. Tried not to break encapsulation by avoiding getters & setters (as much as possible)

3. Tried to have immutable state with value objects (as much as possible) so as to avoid concurrency issues (Thread
   safety)

4. Tried to have readable methods & variables naming so as to clear the intention
   (4 rules of simple design).

5. Tried to have small & logical commits

6. Tried to avoid code duplication by refactoring/reusing duplicate code (DRY)
   but still code duplication can be improved if given more time

7. Didn't make interfaces as per YAGNI principles because for now I don't feel the need for the same (Yes, I am aware of
   this principle also - "Program to interfaces rather than concrete implementation")
