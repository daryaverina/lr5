package subd.lr5.models;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@Table(name = "position", schema = "public", catalog = "testBD")
@Getter
@Setter
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "position_name")
    private String position_name;

    @Column(name = "cost_hour")
    private Double cost_hour ;

    @OneToMany(mappedBy = "position")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private List<Personal> personals;

    public Position() {}

    public Position(String position_name, Double cost_hour) {
        this.position_name = position_name;
        this.cost_hour = cost_hour;
    }

    @Override
    public String toString() {
        return "Position {" +
                "id=" + id +
                ", position_name='" + position_name + '\'' +
                ", cost_hour='" + cost_hour + '\'' +
                '}' + "\n";
    }
}
