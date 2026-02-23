
public class Ticket {
    private static int counter = 1;
    private final int id;
    private String name;
    private String content;



    public Ticket(String name, String content){
        this.id = counter++;
        name = name.trim();

        if(name.length() > 2 && name.length() < 50){
            this.name = name;
        }
        else {

            this.name = "Ticket " + id;
        }

        if(content.length() > 25 && content.length() < 150) {
            this.content = content;
        } else {
            throw new IllegalArgumentException("Content cannot be shorter than 25 characters or " +
                    "longer than 150!");
        }
    }

    public int getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public String getContent(){
        return content;
    }

    public void setName(String name){
        name = name.trim();
        if(name.length() > 2 && name.length() < 50){
            this.name = name;
        }
        else {

            this.name = "Ticket #" + id;
        }
    }

    public void setContent(String content){
        if(content.length() > 25 && content.length() < 150) {
            this.content = content;
        } else {
            throw new IllegalArgumentException("Content cannot be shorter than 25 characters or " +
                    "longer than 150!");
        }
    }

    public String toString(){
        return String.format("\n     id: %s \n   name: %s \ncontent: %s\n", id, name, content);
    }



}
