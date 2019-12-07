package Template;

public class TemplateDB {
    public static class Builder{
        private int template_id;
        private String template_name;
        private int root_id;

        public Builder setId(int template_id){
            this.template_id = template_id;
            return this;
        }

        public Builder setTemplateName(String template_name){
            this.template_name = template_name;
            return this;
        }

        public Builder setRootId(int root_id){
            this.root_id = root_id;
            return this;
        }

        public TemplateDB build() {return new TemplateDB(template_id,template_name,root_id); }


    }

    private int template_id;
    private String template_name;
    private int root_id;

    public TemplateDB(int template_id, String template_name, int root_id){
        this.template_id = template_id;
        this.template_name = template_name;
        this.root_id = root_id;

    }
    public String getTemplateName(){return template_name; }

    public void setTemplateName(String template_name ){this.template_name = template_name; }

    public int getRootId(){return root_id;}

    public void setRootId( int root_id ){this.root_id = root_id;}

    public int getTemplateId(){return template_id;}

    public void setTemplateId(int template_id){this.template_id = template_id;}



    @Override
    public String toString() {
        return String.format(  "\n" + " Template Id: " + this.getTemplateId()
                + "\n" + " Template Name: " + this.getTemplateName()
                + "\n" + " Root Id: " + this.getRootId()
                + "\n");
    }








}
