# gojek-parking-lot-assignment
Gojek parking lot (code pairing or machine coding round) assignment

# Getting started (Run the application)
Go to root of project and run the following command

/bin/sh ./setup.sh

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