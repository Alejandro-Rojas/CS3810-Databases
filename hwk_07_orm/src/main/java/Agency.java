import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="Agencies")
public class Agency {

    @Id
    private int id;
    private String name;
    private int ouid;

    @OneToMany(mappedBy = "ouid")
    private List<Agent> agents;

    public Agency(int i, String agencies, EntityManager em) {
        this();
        em.getTransaction().begin();
        id = i;
        name = agencies;
        agents = new LinkedList<>();
        em.persist(this);

    }

    public Agency() {

    }

    @Override
    public String toString() {
        return "Agency{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ouid=" + ouid +
                ", agents=" + agents +
                '}';
    }

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
        this.ouid = ouid;
    }

    public List<Agent> getAgents() {
        return agents;
    }

    public void setAgents(List<Agent> agents, EntityManager em) {
        em.getTransaction().begin();
        for (Agent agent : agents){
            agent.setOuid(ouid);
            em.persist(agent);
        }
        em.getTransaction().commit();

        this.agents = agents;
    }
}