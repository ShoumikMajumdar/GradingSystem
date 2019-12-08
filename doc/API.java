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
 * add a new section to a course
 * @param cid course id
 * @param sid section id
 */
void addNewSection(int cid, int sid);

/**
 * delete a section from a course
 * @param cid course id
 * @param sid section id
 */
void deleteSection(int cid, int sid);

/**
 * adapt a grading template for a course
 * @param cid course id
 * @param tid template id
 */
void adaptTemplate(int cid, int tid);

/**
 * create a new section object
 * @param id section id
 */
void createSection(int id);

/**
 * add a new student to a section
 * @param scid section id
 * @param stid student id
 */
void addNewStudent(int scid, int stid);

/**
 * delete a student from a section
 * @param scid section id
 * @param stid student id
 */
void deleteStudent(int scid, int stid);

/**
 * create a new student
 * @param id   student id
 * @param name student name
 */
void createStudent(int id, String name);

/**
 * cereate a new template
 * @param tid template id
 * @param name template name
 * @param rid root component id
 */
void createTemplate(int tid, String name, int rid);

/**
 * delete an existing template
 * @param id template id
 */
void deleteTemplate(int id);

/**
 * create a new component object
 * @param id component id
 * @param name component name
 * @param percent percentage the component takes in the same level
 * @param points points this component has
 */
void createComponent(int id, String name, double percent, int points);

/**
 * add a component as a child of another component
 * @param pid parent component id
 * @param cid child component id
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

/**
 * query courses using a certain template
 * @param tid
 * @return id of courses which is using this template
 */
List<Integer> queryCoursesUsingTemplate(int tid);

/**
 * query all courses
 * @return id of courses
 */
List<Integer> queryCourses();

/**
 * query a certain course
 * @param id curse id
 * @return data of course (maybe db-side structure?)
 */
Course queryCourse(int id);

/**
 * query all sections of a course
 * @param id course id
 * @return id of sections
 */
List<Integer> querySections(int id);

/**
 * query a certain section
 * @param id section id
 * @return data of section (maybe db-side structure?)
 */
Section querySection(int id);

/**
 * query all students of a section
 * @param id section id
 * @return id of students
 */
List<Integer> queryStudents(int id);

/**
 * query a certain student
 * @param id student id
 * @return data of student (maybe db-side structure?)
 */
Student queryStudent(int id);

/**
 * query all templates
 * @return List of id of templates
 */
List<Integer> queryTemplates();

/**
 * query a template
 * @param id template id
 * @return data of template (id, name, root component id)
 */
Template queryTemplate(int tid);

/**
 * query a component
 * @param id component id
 * @return data of component (id, percent, name, points)
 */
Component queryComponent(int id);