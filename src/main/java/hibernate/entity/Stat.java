package hibernate.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.Objects;

@Entity
@Table(name = "stat", schema = "todolist", catalog = "postgres")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Stat { // there is only 1 record in this table which is updated (but never deleted)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "completed_total", updatable = false)
    private Long completedTotal; // the value is set in the trigger in the database

    @Column(name = "uncompleted_total", updatable = false)
    private Long uncompletedTotal; // the value is set in the trigger in the database

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id", referencedColumnName = "id") // which fields to bind (foreign key)
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stat stat = (Stat) o;
        return id.equals(stat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}