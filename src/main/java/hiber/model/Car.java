package hiber.model;

import javax.persistence.*;

@Entity
@Table(name="cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "model")
    private String model;

    @Column(name = "series")
    private int series;

//    @OneToOne(optional = false, mappedBy = "car")
//    private User user;

    public Car() {}

    public Car(String model, int series){
        this.model = model;
        this.series = series;
    }

    @Override
    public String toString() {
        return "model-" + model +
                ", series-" + series;
    }

    public User getUser() {

    }
}
