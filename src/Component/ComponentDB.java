package Component;

public class ComponentDB {

    public static class Builder{
        private int component_id;
        private double percent;
        private String component_name;
        private double points;


        public Builder setComponentId(int component_id){
            this.component_id = component_id;
            return this;
        }

        public Builder setComponentNmae(String component_name){
            this.component_name = component_name;
            return this;
        }
        public Builder setPercent(double percent){
            this.percent = percent;
            return this;
        }
        public Builder setPoints(double points){
            this.points = points;
            return this;
        }

        public ComponentDB build(){return new ComponentDB(component_id,component_name,percent,points);}
    }

    private int component_id;
    private String component_name;
    private double percent;
    private double points;

    public ComponentDB(int component_id, String component_name, double percent, double points){
        this.component_id = component_id;
        this.component_name = component_name;
        this.percent = percent;
        this.points = points;
    }

    public int getComponentId(){return component_id; }

    public void setComponentId(String course_name ){this.component_id = component_id; }

    public String getComponentName(){return component_name;}

    public void setComponentName( String component_name ){this.component_name = component_name;}

    public double getPercent(){return percent;}

    public void setPercent(double percent){this.percent = percent;}

    public double getPoints(){return points;}

    public void setPoints(double points){this.points = points;}



    @Override
    public String toString() {
        return String.format(  "\n" + " Component ID: " + this.getComponentId()
                + "\n" + " Component Name: " + this.getComponentName()
                + "\n" + " Percent : " + this.getPercent()
                + "\n" + " Points : " + this.getPoints());
    }



}
