public class Todo {
    protected boolean isDone;
    protected String name;

    public Todo( String name){
        this.name = name;
        this.isDone = false;
    }

    public String getStatus(){
        return (isDone ? "X" : "");
    }

    public String showStatus(){
       return "[" + getStatus() + "] " + getName();
    }

    public String getName(){
        return this.name;
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public void markAsUndone(){
        this.isDone = false;
    }


}
