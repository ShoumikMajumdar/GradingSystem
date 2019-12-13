package logic;

import Template.TemplateDB;

import db.ScoresReader;
import db.StudentReader;

import Course.CourseDB;


import java.util.*;

public class Course {

    private static int nextID;

    public final int id;

    public final String name;

    public HashMap<Integer, Section> sections;

    public int tid; // template id

    public static Course build(int id, String name) {
        Course c = new Course(id, name);
        return c;
    }

    public static Course build(int id, String name, int tid) {
        Course c = new Course(id, name, tid);
        return c;
    }

    public static Course create(String name) {
        Course c = null;
        if (GradingSystem.courseRd.createCourse(nextID, name)) {
            c = Course.build(nextID, name);
            ++nextID;
        }
        return c;
    }

    public static void delete(int cid) {
        // From CourseID get a list of section IDs
        //Delete all Sections using Course.deleteSection.
        

        ArrayList<Integer> SectionList = new ArrayList<>(GradingSystem.sectionRd.querySections(cid));
        for (int section:SectionList) {
            deleteSection(cid,section);
        }
        GradingSystem.courseRd.deleteCourse(cid);
    }

    protected Course(int id, String name, int tid) {
        this.id = id;
        this.name = name;
        this.tid = tid;
        sections = new HashMap<Integer, Section>();
    }

    protected Course(int id, String name) {
        this(id, name, -1);
    }

    public static void addNewSection(int cid) {
        Section s = Section.create();
        GradingSystem.sectionRd.addNewSection(cid, s.id);
    }

    public static void deleteSection(int cid, int sid) {
        //Use Section ID to fetch a list of student IDs
        // Use helper function to fetch list of component ID
        //Use student IDs, Component IDs and Course ID to delete scores using ScoresReader.deleteScores

        ArrayList<Integer> ListStudentID = new ArrayList<>(GradingSystem.studentRd.queryStudents(sid));
        ArrayList<Integer> ListcomponentId = new ArrayList<>(getAllLeafComponentID(cid));

        for (int i=0;i<ListStudentID.size();i++){
            for (int j=0;j<ListcomponentId.size();j++){
                GradingSystem.scoreRd.deleteScore(i,cid,j);
            }
        }
        GradingSystem.sectionRd.deleteSection(sid);
    }

    public static void adaptTemplate(int cid, int tid) {
        TemplateDB template = GradingSystem.templateRd.queryTemplate(tid);
        GradingSystem.templateRd.adaptTemplate(cid, tid, template.getTemplateName(), template.getTemplateId());
    }

    public static ArrayList<Integer> getAllLeafComponentID(int cid) {
        CourseDB cdb = GradingSystem.templateRd.queryCourse(cid);
        if (cdb == null) {
            return null;
        }
        TemplateDB tdb = GradingSystem.templateRd.queryTemplate(cdb.getTemplateId());
        return Component.getAllLeafChildrenID(tdb.getRootId());
    }
}