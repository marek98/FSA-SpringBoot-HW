package sk.posam.fsa.boot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "address", schema = "public")
@Access(AccessType.FIELD)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "address2")
    private String address2;

    @Column(name = "district")
    private String district;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "phone")
    private String phone;

    @Column(name = "last_update")
    private Date lastUpdate;

    // Relations
    @OneToMany(mappedBy = "address")
    private List<Customer> customers;


    @Override
    public String toString() {
        return "Address(" +
                "address = " + address +
                ", address2 = " + address2 +
                ", city = " + city +
                ", postal_code = " + postalCode +
                ", phone = " + phone;
    }
}
