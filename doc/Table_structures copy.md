## Course Table : 

Columns : course_id, course_name 

| course_id | course_name |
| --------- | :---------- |
|           |             |

Supported API :

```java
/**
 * create a new course
 * @param id   global unique id for courses
 * @param name descriptive name of the course
 */
void createCourse(int id, String name);

/**
 * delete a course
 * @param id global unique id for courses
 */
void deleteCourse(int id);

/**
 * query all courses
 * @return id of courses
 */
List<Integer> queryCourses();

```



## Section Table 

Columns :  course_id, section_id 

| course_id | section_id |
| --------- | ---------- |
|           |            |

Supported API :

```java

/**
 * create a new section object
 * @param id section id
 * for section that does not have a course , assign -1 
 */
void createSection(int section_id);

/**
 * add a new section to a course
 * @param cid course id
 * @param sid section id
 */
void addNewSection(int course_id, int section_id);

/**
 * delete a section from a course
 * @param cid course id
 * @param sid section id
 */
void deleteSection(int section_id);



/**
 * query all sections of a course
 * @param id course id
 * @return id of sections
 */
List<Integer> querySections(int course_id);


// optional for now 
/**
 * query a certain section
 * @param id section id
 * @return data of section (maybe db-side structure?)
 */
Section querySection(int id);
```



## Student Table 

Columns : student_id, student_name, section_id 

| Student_id | Student_name | Section_id |
| ---------- | ------------ | ---------- |
|            |              |            |

Supported API : 

```java
/**
 * create a new student
 * @param id   student id
 * @param name student name
 * For a new student that are not enrolled in any course yet 
 * assign section_id to be -1 
 */
void createStudent(int student_id, String student_name);

/**
 * add a new student to a section
 * @param scid section id
 * @param stid student id
 * @param sname student name 
 */
void addNewStudent(int student_id, int section_id, String sname);


/**
 * delete a student from a section
 * @param scid section id
 * @param stid student id
 */
void deleteStudent(int student_id, int section_id);



/**
 * query all students of a section
 * @param id section id
 * @return id of students
 */
List<Integer> queryStudents(int section_id );

/**
 * query a certain student
 * @param id student id
 * @return data of student (maybe db-side structure?)
 */
Student queryStudent(int student_id);
```

## Template Table 

Columns : course_id, template_id, template_name, root_id 

| course_id | template_id | template_name | root_id |
| --------- | ----------- | ------------- | ------- |
|           |             |               |         |

Supported API :

```java
/**
 * cereate a new template
 * @param tid template id
 * @param name template name
 * @param rid root component id
 * course id will be initialized to be -1 
 */
void createTemplate(int tid, String name, int rid);

/**
 * adapt a grading template for a course
 * @param cid course id
 * @param tid template id
 * For a template that has not beed assigned to a course and root_component id 
 * assign -1 to those two fields 
 * repeatedly call for different unqiue course id
 */
void adaptTemplate(int cid, int tid, String name, int rid);


/**
 * delete an existing template
 * @param id template id
 */
void deleteTemplate(int id);


/**
 * @param id component_id
 * delete all asscociate information about students using that template
 */

void delete_all_component (int component_id);

/**
 * query courses using a certain template
 * @param tid
 * @return id of courses which is using this template
 */
ArrayList<Integer> queryCoursesUsingTemplate(int tid);

/**
 * query a certain course
 * @param id curse id
 * @return data of course (maybe db-side structure?)
 */
Course queryCourse(int course_id);
```



## Component 

Columns: component_id, component_name, percent, points 

| Component_id | Component_name | percent | points | Parent |
| ------------ | -------------- | ------- | ------ | ------ |
|              |                |         |        |        |



Supported API: 

```java
/**
 * create a new component object
 * @param id component id
 * @param name component name
 * @param percent percentage the component takes in the same level
 * @param points points this component has
 */
void createComponent(int id, String name, double percent, double points);

/**
 * add a component as a child of another component
 * @param pid parent component id
 * @param cid child component id
 * for a new child that does not have name, percent, points , assign -1 for those 3 fields 
 */
void addChild(int pid, int cid);

/**
 * delete a child component from a parent component
 * which also means the child component should be
 * delete from the database
 * @param pid parent component id
 * @param cid child component id
 */
void deleteChild(int pid, int cid);


void deleteComponent(int component_id);

/**
 * return all componenets that are children of given component id
 * @param pid parent component id
 */

ArrayList<Integer> queryChildren(int componentid)
```





## Scores Table

Columns : course_id, student_id, component_id, points 

| course_id | Student_id | Component_id | Points |
| --------- | ---------- | ------------ | ------ |
|           |            |              |        |



```java
// API 

/** 
* get a score
*/

double getScore(int course_id, int student_id, int component_id);


/**
 * add a score for astudent given course id and component id 
 * @param course_id 
 * @param student_id 
 * @param component_id 
 * @param points 
 */

void add_score(int course_id, int student_id, int component_id, double points);
  
/**
 * delete a score for a student 
 * @param student_id 
 * @param course_id 
 * @param component_id
 */
void delete_score(int student_id, int course_id, int component_id);

/**
 * delete a component [helper function ]
 * @param component_id
 */

void delete_component(int component_id );
  


```



## Bonus Table 

Columns : course_id,  student_id, component_id, points 

| Course_id | Student_id | Component_id | Bonus |
| --------- | ---------- | ------------ | ----- |
|           |            |              |       |

```java
// API 
/**
 * add a bonus score for a student given course id and component id 
 * @param course_id 
 * @param student_id 
 * @param component_id 
 * @param points 
 */

void add_bonus(int course_id, int student_id, int component_id, double points);
  
/**
 * delete a bonus score for a student 
 * @param student_id 
 * @param course_id 
 * @param component_id
 */
void delete_bonus(int student_id, int course_id, int component_id);

/**
 * delete a component [helper function ]
 * @param component_id
 */

void delete_component(int component_id );
```

## Comment Table 

Columns : course id , student id, component id, comment 

| course id | student id | component id | comment |
| --------- | ---------- | ------------ | ------- |
|           |            |              |         |

```java
// API 
/**
 * add a bonus score for a student given course id and component id 
 * @param course_id 
 * @param student_id 
 * @param component_id 
 * @param points 
 */

void add_comment(int course_id, int student_id, int component_id, double points);
  
/**
 * delete a bonus score for a student 
 * @param student_id 
 * @param course_id 
 * @param component_id
 */
void delete_comment(int student_id, int course_id, int component_id);

/**
 * delete a component [helper function ]
 * @param component_id
 */

void delete_component(int component_id );
```

