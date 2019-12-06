package Course;

public class CourseDB {

    public static class Builder{
        private int course_id;
        private String course_name;

        private int template_id;

        public Builder setName(String name){
            this.course_name = name;
            return this;
        }

        public Builder setCourseId(int course_id){
            this.course_id = course_id;
            return this;
        }

        public Builder setTemplateId(int template_id){
            this.template_id = template_id;
            return this;
        }

        public CourseDB build(){
            return new CourseDB(course_id,course_name, template_id );
        }

    }


    private String course_name;
    private int course_id;
    private int template_id;

    public CourseDB(int course_id, String course_name, int template_id){
        this.course_id = course_id;
        this.course_name = course_name;
        this.template_id = template_id;

    }

    public String getCourseName(){return course_name; }

    public void setCourseName(String course_name ){this.course_name = course_name; }

    public int getCourseId(){return course_id;}

    public void setCourseId( int course_id ){this.course_id = course_id;}

    public int getTemplateId(){return template_id;}

    public void setTemplateId(int template_id){this.template_id = template_id;}



    @Override
    public String toString() {
        return String.format(  "\n" + " Course ID: " + this.getCourseId()
                + "\n" + " Course_Name: " + this.getCourseName()
                + "\n" + " Template Id: " + this.getTemplateId()
                + "\n");
    }





}
