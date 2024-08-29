public class Deadline extends Task{
    private String by;

    public Deadline(String name, String by, boolean isDone){
        super(name, isDone);
        this.by = by;
    }

     public String getBy(){
        return this.by;
     }


    public String toString(){
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
