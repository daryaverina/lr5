package subd.lr5.models;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "work", schema = "public", catalog = "testBD")
@Getter
@Setter
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "salary_date")
    private Date salary_date;

    @Column(name = "hours_amount")
    private int hours_amount ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_id")
    private Destination destination;

    public Work() {}

    public Work(Date salary_date, int hours_amount, Destination destination) {
        this.salary_date = salary_date;
        this.hours_amount = hours_amount;
        this.destination = destination;
    }

    /*public void nullificationDestination(){
        this.destination=null;
    }*/

    @Override
    public String toString() {
        return "Work {" +
                "id=" + id +
                ", salary_date='" + salary_date + '\'' +
                ", hours_amount='" + hours_amount + '\'' +
                ", destination_id='" + destination.getId() + '\'' + + '\'' +
                '}' + "\n";
    }
}
