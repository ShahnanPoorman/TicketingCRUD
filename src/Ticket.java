import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket {
    private static int counter = 1;
    private final int id;
    private String name;
    private final LocalDateTime createdAt;
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy" +
            "-MM-dd HH:mm");
    private String content;



    public Ticket(String name, String content){
        this.id = counter++;

        if (name == null) throw new IllegalArgumentException("Name cannot be null");
        name = name.trim();

        if(name.length() > 2 && name.length() < 50){
            this.name = name;
        }
        else {

            this.name = "Ticket #" + id;
        }

        this.createdAt = LocalDateTime.now();

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
        if (name == null) throw new IllegalArgumentException("Name cannot be null");
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

    @Override
    public String toString(){
        return String.format("\n      id: %s\ncreation: %s\n    name: %s \n content: %s\n", id,
                createdAt.format(dateTimeFormatter),
                name,
                content);
    }



}
