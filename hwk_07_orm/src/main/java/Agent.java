import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Agents")
public class Agent {

    @Id
    private int id;

    private String name;

    private int ouid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOuid() {
        return ouid;
    }

    public void setOuid(int ouid) {
    }
}