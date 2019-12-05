package Student;

public class Student {

    public static class Builder {
        private String student_name;
        private int student_id;

        public Builder setName(String name) {
            this.student_name = name;
            return this;
        }

        public Builder setId(int id) {
            this.student_id = id;
            return this;
        }

        public Student build() {
            return new Student(student_name, student_id);
        }

    }

    private String student_name;
    private int student_id;

    public Student(String name, int id) {
        this.student_name = name;
        this.student_id = id;
    }

    public String getName() {
        return student_name;
    }



    public void setName(String name) {
        this.student_name = name;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int id) {
        this.student_id = id;
    }

    @Override
    public String toString() {
        return String.format("\n" + " Student_name: " + this.getName()
                + "\n" + " Student_id: " + this.getStudent_id());
    }
}


