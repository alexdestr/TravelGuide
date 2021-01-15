package ru.vegd.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

@Entity
@Table(name = "description")
public class Description implements Serializable {

    private static final long serialVersionUID = -1947243339417037389L;

    @OneToOne(targetEntity = City.class)
    @PrimaryKeyJoinColumn(referencedColumnName = "id")
    private City city;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(nullable = false)
    private String description;

    public Description() {
    }

    public Description(Long id, String description) {
        this.id = id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        checkNotNull(description);
        checkState(!description.isEmpty());
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Description that = (Description) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
