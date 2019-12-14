package dbMain;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import Component.ComponentDB;
import Course.CourseDB;
import Student.StudentDB;
import Template.TemplateDB;
import db.*;

public class Main {
    public static void main(String[] args) {
        // Test for Course table API

        // API 1 : Create course [checked]
        CourseReader coursereader = new CourseReader();
        coursereader.createCourse(0,"CS591");
        coursereader.createCourse(1,"CS530");
        coursereader.createCourse(2,"CS651");

        // API 2 : Delete a course [checked]
        coursereader.deleteCourse(0);

        // API 3 : get all courses id 【checked】
        ArrayList<Integer> courses_id = coursereader.queryCourses();
        System.out.println("All courses id : \n");
        for (int i = 0; i < courses_id.size(); i++) {
            System.out.println(courses_id.get(i));
        }

        // Test for Section Table API
        SectionReader sectionreader = new SectionReader();

        // API 1 : Create a new section [checked ]
        sectionreader.createSection(1);
        sectionreader.createSection(2);

        // API 2 :  add a new section associated with course id [checked ]

        // test 1 : overwrite
        sectionreader.addNewSection(1,1);
        sectionreader.addNewSection(1,2);
        sectionreader.addNewSection(2,3);


        // API 3 : delete a section from a course [checked]
        sectionreader.deleteSection(1);

        // API 4 : query all sections [checked]
        ArrayList<Integer> sections_id = sectionreader.querySections(1);
        System.out.println("All sections id for course 1: \n  " );
        for (int i = 0; i < sections_id.size(); i++) {
            System.out.println(sections_id.get(i));
        }


        // Test for Student table API
        StudentReader studentreader = new StudentReader();

        // API 1 : Create new student [checked]
        studentreader.createStudent(1,"John");
        studentreader.createStudent(2,"May");
        studentreader.createStudent(3,"Jay");


        // API 2 : add a new Student [checked]
        studentreader.addNewStudent(1,1, "John");
        studentreader.addNewStudent(2,1, "May");
        studentreader.addNewStudent(3,1, "Jay");

        // API 3 : delete Student [ checked ]
        studentreader.deleteStudent(1,1);

        // API 4 : query students [checked]
        ArrayList<Integer> student_id = studentreader.queryStudents(1);
        System.out.println("All students id for section 1 : \n  " );
        for (int i = 0; i < student_id.size(); i++) {
            System.out.println(student_id.get(i));
        }

        // API 5 : query a certain student [checked]
        StudentDB student = studentreader.queryStudent(2);
        System.out.println(student.toString());


        // Test : Template table API tests
        TemplateReader templatereader = new TemplateReader();
        // API 1 : create Template [ checked ]
        templatereader.createTemplate(1,"template 1",1);
        templatereader.createTemplate(2,"template 2",1);
        templatereader.createTemplate(3,"template 3",1);

        templatereader.createTemplate(4,"template 4",2);
        // API 2 : adapt Template, associate course id for a template id [checked]

        templatereader.adaptTemplate(1,1,"template 1",1);
        templatereader.adaptTemplate(2,2,"template 2",1);
        templatereader.adaptTemplate(3,3,"template 3",1);
        templatereader.adaptTemplate(4,3,"template 3",1);


        // API 3 : delete template 【checked】
        templatereader.deleteTemplate(1);

        // API 4 : delete all component [checked]
        templatereader.deleteAllComponent(2);

        // API 5 : query courses using a template id [checked]
        ArrayList<Integer> courses = templatereader.queryCoursesUsingTemplate(3);
        System.out.println("All course id using template 1 : \n  " );
        for (int i = 0; i < courses.size(); i++) {
            System.out.println(courses.get(i));
        }

        // API 6 : query a specific course [checked]
        CourseDB course = templatereader.queryCourse(2);
        System.out.println(course.toString());



        // API 7 : query Template Test
        System.out.println("Query Template 1 : \n  " );
        TemplateDB template = templatereader.queryTemplate(2);
        System.out.println(template.toString());

        //API 8 : Query all template ids

        System.out.println("ALL templetate ids");
        ArrayList<Integer> templates_id = templatereader.queryTemplates();
        for (int i = 0; i < templates_id.size(); i++) {
            System.out.println(templates_id.get(i));
        }




        // Test : Component Table API tests

        // API 1 : create a new component
        ComponentReader componentreader = new ComponentReader();
        componentreader.createComponent(1,"assignment 1",30.0, 15);
        componentreader.createComponent(2,"assignment 2",40.0, 25);
        componentreader.createComponent(3,"assignment 3",50.0, 30);
        componentreader.createComponent(4,"assignment 4",50.0, 30);

        // API 2 : add a child mapping [checked]
        componentreader.addChild(1,2);
        componentreader.addChild(2,3);
        componentreader.addChild(2,4);

        // API 3 : delete a child mapping [checked]

        componentreader.deleteChild(1,2);

        // API 4 : queryChilren [checked]
        ArrayList<Integer> children = componentreader.queryChildren(2);
        System.out.println("All children of  2 : \n  " );
        for (int i = 0; i < children.size(); i++) {
            System.out.println(children.get(i));
        }

        // API 5 : query component Test
        System.out.println("Query Component 3 : \n  " );
        ComponentDB component = componentreader.queryComponent(3);
        System.out.println(component.toString());





        // API tests for Scores table
        // API 1 : add a score for a student given a course and component id
        ScoresReader scorereader = new ScoresReader();
        scorereader.addScore(1,1,1,25);
        scorereader.addScore(1,2,1,35);
        scorereader.addScore(1,3,1,45);
        scorereader.addScore(1,2,2,35);
        scorereader.addScore(1,2,2,35);
        scorereader.addScore(1,2,3,35);

        // API 2 :  delete a score [checked]
        scorereader.deleteScore(1,1,1);

        // API 3 : delete a component
        scorereader.deleteComponent(2);

        // API 4: get a score

        double score = scorereader.getScore(1,2,1);
        System.out.println(score);



        // API tests for Bonus table
        //
        // API 1 : add a score for a student given a course and component id
        BonusReader bonusreader = new BonusReader();
        bonusreader.addBonus(1,1,1,25);
        bonusreader.addBonus(1,2,1,35);
        bonusreader.addBonus(1,3,1,45);
        bonusreader.addBonus(1,2,2,35);
        bonusreader.addBonus(1,2,2,35);
        bonusreader.addBonus(1,2,3,35);

        // API 2 :  delete a score [checked]
        bonusreader.deleteScore(1,1,1);

        // API 3 : delete a component
        scorereader.deleteComponent(2);

        // API 4: get a score

        double bonus = bonusreader.getBonus(1,2,1);
        System.out.println(bonus);




        // API tests for comments table


        // API 1 : add a score for a student given a course and component id
        CommentReader commentreader = new CommentReader();
        commentreader.addComment(1,1,1,"c1");
        commentreader.addComment(1,2,1,"c2");
        commentreader.addComment(1,3,1,"c3");
        commentreader.addComment(1,2,2,"c4");
        commentreader.addComment(1,2,2,"c5");
        commentreader.addComment(1,2,3,"c6");

        // API 2 :  delete a score [checked]
        commentreader.deleteComment(1,1,1);

        // API 3 : delete a component
        commentreader.deleteComponent(2);

        // API 4: get a score

        String comment = commentreader.getComment(1,2,1);
        System.out.println(comment);


        // INFO TABLE TEST
        InfoReader inforeader = new InfoReader();
        inforeader.setData("key1", 1);
        inforeader.setData("key1",2);
        inforeader.setData("key2", 3);

        // get
        int res1 = inforeader.getData("key1");
        int res2 = inforeader.getData("key2");

        System.out.println(res1);
        System.out.println(res2);


        // curve table test
        CurveReader curvereader = new CurveReader();
        curvereader.setCurve(1,30);
        curvereader.setCurve(2,35);
        curvereader.setCurve(3,45);


        int curve_1 = curvereader.getCurve(1);
        int curve_2 = curvereader.getCurve(2);

        System.out.println(curve_1);
        System.out.println(curve_2);























































    }
}
