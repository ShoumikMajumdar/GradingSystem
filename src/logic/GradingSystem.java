package logic;

import db.*;


public class GradingSystem {

    public static final CourseReader courseRd = new CourseReader();

    public static final SectionReader sectionRd = new SectionReader();

    public static final StudentReader studentRd = new StudentReader();

    public static final TemplateReader templateRd = new TemplateReader();

    public static final ComponentReader componentRd = new ComponentReader();

    public static final ScoresReader scoreRd = new ScoresReader();

    public GradingSystem() {
    }

}