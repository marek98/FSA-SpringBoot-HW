package sk.posam.fsa.boot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "city", schema = "public")
@Access(AccessType.FIELD)
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Long id;

    @Column(name = "city")
    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "last_update")
    private Date lastUpdate;

    // Relations
    @OneToMany(mappedBy = "city")
    private List<Address> addresses;

    @Override
    public String toString() {
        return "City(" +
                "name = " + name +
                ", country = " + country +
                ")";
    }
}
