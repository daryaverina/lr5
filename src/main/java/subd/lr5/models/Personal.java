package subd.lr5.models;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "personal", schema = "public", catalog = "testBD")
@Getter
@Setter
public class Personal{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "full_name")
    private String full_name;

    @Column(name = "nadbavka")
    private Double nadbavka;

    @Column(name = "work_experience")
    private Integer work_experience;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "mail_address")
    private String mail_address;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    private Position position;

    @OneToMany(mappedBy = "personal")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private List<Destination> destinations;

    public Personal() {}

    public Personal(String full_name, Double nadbavka, Integer work_experience, String phone_number, String mail_address, Position position) {
        this.full_name=full_name;
        this.nadbavka = nadbavka;
        this.work_experience = work_experience;
        this.phone_number = phone_number;
        this.mail_address = mail_address;
        this.position = position;
    }

    @Override
    public String toString() {
        return "Personal {" +
                "id=" + id +
                ", full_name='" + full_name + '\'' +
                ", nadbavka='" + nadbavka + '\'' +
                ", work_experience='" + work_experience + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", mail_address='" + mail_address +
                ", position_id='" + position.getId() + '\'' +
                '}' + "\n";
    }
}
