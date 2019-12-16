# Requirement

- Course based grading system which means user could add or remove courses
- Flexibility to add or remove new assignment/exam/quiz...
- Flexibility to define how to calculate the final grade

# Design

## Course

- Batches
- Data structrue
- How to calculate

## Batch

- List of students
- Each student has a correspoding data structrue




## DataBase Design 

### BaseDBReader 
This is the Base class which is responsible for initializing the connection to local database and stores the credentials for login information. 

### MySQLTableCreation 
This is the class which initialize the all the empty tables 

### Child class of BaseDBReader 
- CourseReader 
- StudentReader 
- ComponentReader 
etc.. 
All API definition can be found in doc


