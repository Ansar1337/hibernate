package hibernate.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import java.util.Objects;

/*
All user activity (account activation, other actions as needed)
*/

@Entity
@Table(name = "activity", schema = "todolist", catalog = "postgres")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Activity { // the name of the table will be automatically taken from the name of the class with a small letter: activity

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Type(type = "org.hibernate.type.NumericBooleanType") // to automatically convert a number to true/false
    private boolean activated; // becomes true only after confirmation of activation by the user (logically it cannot become false back)

    @Column(updatable = false)
    private String uuid; // created only once by a trigger in the database

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return id.equals(activity.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}