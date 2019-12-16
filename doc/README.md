# How to use

First run the main function in the `MySQLTableCreation.java`. This
will clear the database and create the tables the program need. Then
run the main function in `App.java` this will launch the GUI.

# Abstract 

In this assignment, our team implemented a simple grading system. This
grading system allows the user to create courses and grading template,
which describes how the students get their final grade. 

In this program, the user could independently create courses and
template, which means a template could be adopt by multiple
courses. The template could also exist without a course and be adopt
by a course created in the future. We also provided some built in
statistical tools which helps the user to quickly calculate the
averate, mean and difference of assignments, exams, homeworks or
whatever. The most powerful feature of the program is the user could
customize the template as much as possible. It feels like MS EXcel but
the template you created is persisten and reusealbe in different
courses.

# Scope

# Class Design

In this program, we split classes into three categories. The frist
category consists of the classes correspond to the logic of the
program. These are the classes which represent data and how they are
processed by the program. The second category consists of the classes
correspond to the user interface. They define how the user interfaces
look like and how they respond to the user input. The final category
consists of the classes used by the database.

## Logic

### Component

This is the most important class which actually consists of the
grading template. We treat a grading template as a tree-like data
structure. For each node in the tree it may or may not have children
nodes. If it has children nodes then it is a "category". If not, then
it is an actual homework/assignment/exam/project or whatever the
designer like.

Component itselt has no category, the user assign a descriptive name
to it to tell what it is. A component defines the max points a student
could get in it and how much percent it contributes to its parent
node. Finally, all the children component of the root component
defines how the final grade will be calculated.

By using this simple component, the user could build a complicated
grading template.

### Course Section Student Template and Cells

These classes are simple data classes which holds data defining an
object and they are passing around the program. The `Cell` class is
the base of three cell-like classes, namely `Grade`, `Bonus` and
`Comment`. They share the similar properties that they must exist with
a particular combination of course, student and component.

In these classes, we defined a lot of static functions which are
responsible for object creation and commnunication with the
database. The reason we choose this design is that since this is a
grading system, the most objects in the logic part are data. Since
data is stored in the database, it is naturally to query the database
when the application need something to show to the user. Although
frequently loading from or storing to the database is expensive,
considering the scale of the assignment and the simplicity of this
model, we still adpat this method. If the scale of the program rises,
we may develope a buffer in memory to hold the data queried from the
database.

This way we could focus on the processing of the data instead of
maintaining the life cycle of the objects. In addtion, there is little
operation these objects could do. So a heavy designed object model is
some what too expensive.

## User Interface

### Panels

The program adapts a single frame design. There will be only one main
frame in the life cycle of the program. We update/switch its content
by filling it with different panels. Thus there are some panel classes
defining what to show and the function provided to the user.

### UIConsts

In this assignment there are some hard-coded variables defining
attributes of the user interface such as the width and height of the
frame. We define these variables in the `UIConsts` class which
provides global access to these variables. Thus we could access and
use these variables everywhere easily in the program.

### UIController

This class defines some methods which might be used by the panel
classes. These methods makes switching from different panels much more
easier.

### Components

We extend some components from Swing to help us do our work much more
easier. An important part of the extension is the `GTable` which
almost contain all the main features of our program. Other components
defined some reusable callback functions which are extensively used in
the `GTable` component.

## Database

### BaseDBReader 
This is the Base class which is responsible for initializing the connection to local database and stores the credentials for login information. 

### MySQLTableCreation 
This is the class which initialize the all the empty tables 

### Child class of BaseDBReader 
All the child class of BaseDBReader will provide a set of API that returns the information associated with each entity in this project such as a course, an assignment, a particular student etc. 
- CourseReader 
- StudentReader 
- ComponentReader etc.. 
All API definition can be found in `doc/API.java`
