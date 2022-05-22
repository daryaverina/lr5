package subd.lr5.models;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Table(name = "project", schema = "public", catalog = "testBD")
@Getter
@Setter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "project_name")
    private String project_name;

    @Column(name = "is_develop ")
    private Boolean is_develop ;

    @OneToMany(mappedBy = "project")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private List<Destination> destinations;

    public Project() {}

    public Project(String project_name,  Boolean is_develop) {
        this.project_name = project_name;
        this.is_develop = is_develop;
    }

    @Override
    public String toString() {
        return "Project {" +
                "id=" + id +
                ", project_name='" + project_name + '\'' +
                ", is_develop='" + is_develop + '\'' +
                '}' + "\n";
    }
}
