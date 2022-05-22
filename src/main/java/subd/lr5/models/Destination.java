package subd.lr5.models;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "destination", schema = "public", catalog = "testBD")
@Getter
@Setter
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "destination_date")
    private Date destination_date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_id")
    private Personal personal;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "destination")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private List<Work> works;

    public Destination() {}

    public Destination(Date destination_date, Personal personal, Project project) {
        this.destination_date = destination_date;
        this.personal = personal;
        this.project = project;
    }

    @Override
    public String toString() {
        return "Personal {" +
                "id=" + id +
                ", destination_date='" + destination_date + '\'' +
                ", personal_id='" + personal.getId() + '\'' +
                ", project_id='" + project.getId() + '\'' +
                '}' + "\n";
    }
}
