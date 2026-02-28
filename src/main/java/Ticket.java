import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name="TICKET", schema="TICKET_MANAGER")
public class Ticket {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String name;

    @Column(nullable = false, updatable = false)
    private final LocalDateTime createdAt = LocalDateTime.now();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy" +
            "-MM-dd HH:mm");
    private String content;
    private static final int MIN_CONTENT_LENGTH = 25;
    private static final int MAX_CONTENT_LENGTH = 150;


    public Ticket(
    ){}

    public Ticket(String name, String content){

        if (name == null) throw new IllegalArgumentException("Name cannot be null");
        name = name.trim();

        if(name.length() > 2 && name.length() < 50){
            this.name = name;
        }
        else {

            this.name = "Ticket #" + id;
        }

        if(content.length() > 25 && content.length() < 150) {
            this.content = content;
        } else {
            throw new IllegalArgumentException(String.format("Content cannot be shorter than %s " +
                    "characters or " +
                    "longer than %s!", MIN_CONTENT_LENGTH, MAX_CONTENT_LENGTH));
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
        if(content.length() >= MIN_CONTENT_LENGTH && content.length() <= MAX_CONTENT_LENGTH) {
            this.content = content;
        } else {
            throw new IllegalArgumentException("Content cannot be shorter than 25 characters or " +
                    "longer than 150!");
        }
    }

    @Override
    public String toString(){
        return String.format("\n      id: %s\ncreation: %s\n    name: %s \n content: %s\n", id,
                createdAt.format(DATE_FORMATTER),
                name,
                content);
    }



}
