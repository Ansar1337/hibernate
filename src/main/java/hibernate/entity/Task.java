package hibernate.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;

import java.util.Date;
import java.util.Objects;

/*
User tasks
 */

@Entity
@Table(name = "task", schema = "todolist", catalog = "postgres")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Task {

    // indicate that the field is filled in the database
    // needed when we add a new object, and it returns with a new id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String title;

    @Type(type = "org.hibernate.type.NumericBooleanType") // to automatically convert a number to true/false
    private Boolean completed; // 1 = true, 0 = false

    @Column(name = "task_date") // in the database, the field is called task_date, you cannot use the name date
    private Date taskDate;

    // a task can only have one priority (on the other hand, the same priority can be used in multiple tasks)
    @ManyToOne
    @JoinColumn(name = "priority_id", referencedColumnName = "id") // which fields to bind (foreign key)
    private Priority priority;

    // a task can only have one category (on the other hand, the same category can be used in multiple issues)
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id") // по каким полям связывать (foreign key)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id") // which fields to bind (foreign key)
    private User user; // indicate for which user the task is

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id.equals(task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return title;
    }
}