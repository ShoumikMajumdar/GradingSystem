# Overview
  High level overview of the document and what I should expect to get out of reading this document

# Scope
  The user/client is looking for a grading system that can make it easier for her to organize the grades of the students enrolled in her courses at the end of the semester. She currently uses Microsoft Excel which is very flexible. However Microsoft Excel does not provide her the reusability and functionalities she is looking for. We are creating a software that provides her with the flexibilty of Microsoft Excel and provide reusability at the same time.

# Functionality
  We have categorized our functionalities based on priority. Our categories are as follows:
  
  1) Non Negotiables:
      - A template once created that can be used in the future again.
      - Ability to support multiple courses and multiple batches within each course.
      - Different grading templates for different courses, but the same template for all batches within the same course.
      - Ability to add and remove students.
      - Ability to add and remove components.
      - Flexibility on how you want to enter the grades.
      - Flexibility to change the rubrics.
      
   2) Good to Have
      - Login Function.
      - In built statistic for all the student in the course and individual batches.
      - Allow curving the grades for the entire class with a click of a button.
      - Add comments and notes without having to add unnecessary columns.
      - Keep a tab of bonus points every student earns.
      - Ability to see grades for a specific component.
      
   3) Aditional Features
      - Import and Export to and from excel.
      - Search Bar to search for a specific component or student.
      - Name and details of the grader for the specific Component.
      - Email a student directly regarding specific grades.

# Goals
  Our goal was to make sure our software has all of the Non Negotiable features that we categorized. In addition we hoped to include most of the features from the Good to Have section.
  
  We achieved our goal of making sure our software has all the Non Negotiables. In addition to that, our software has a Login Function, Statistics function and Bonus Point function.
  
  
# Object Diagrams
UML diagram

# Object Justification

## UI
This package contatins all the necessary classes for the User Interface.

### Component
  This is the package that has the classes for our header components. They are responsible for the pop up features and the basic building blocks of our Tempalte header/table. The classes in this package are:
    - GButton
    - GTextField
    - GLabel
    - GTable
    - GRowPopup (Initialize pop up on rows)
    - GCellPopup (Initialize pop up on cells)
    - GHeadPopup (Initialize pop up on Headers)

### Panel
  The Panel Package hold the various classes for the UI panels. They are controlled by the UIController class that acts as a controller while swtiching between all the panels. The classes in this package are:
    - UIController (Controller that helps switch between Panels)
    - MainPanel
    - CoursePanel
    - TemplatePanel
    - TabelPanel
    - SectionList
    - CreateCoursePanel
    - CreateTemplatePanel
    - SelectTemplatePanel
    - LoginPanel
    
    
 ## Logic
  This package contains the classes necessary for logic. This is where we define the classes for our students, courses and templates to name a few. They also act as an interface between the UI and the Database. None of the UI components communicate with the Database directly. They only communicate with the database through the various logic classes. For instance, the CourseReader class (db Package) is responsible for inserting and deleting from the course table in the database. The UI panels can access the CourseReader only through the Course Class. The remaining logic classes also act as an interface between the UserInterface and Database in a similar way. The various classes in this package are: 
    - Course
    - Component
    - Template
    - Cell
    - Comment
    - Student
    - Section
    - Grade
    - Bonus
    - GradingSystem
    
## DataBase Design 
### BaseDBReader 
This is the Base class which is responsible for initializing the connection to local database and stores the credentials for login information. 

### MySQLTableCreation 
This is the class which initialize the all the empty tables 

### Child class of BaseDBReader 
- CourseReader 
- StudentReader 
- ComponentReader etc.. 
All API definition can be found in doc/API.java


