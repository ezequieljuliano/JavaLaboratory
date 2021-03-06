package com.ezequieljuliano.bookmark.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "security_role",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"rol_name"}, name = "uk_rol_name"
                )
        }
)
@SequenceGenerator(sequenceName = "seq_rol_id", name = "role_identifier", allocationSize = 1)
@NoArgsConstructor
@EqualsAndHashCode
public class Role implements Serializable {

    @Id
    @GeneratedValue(generator = "role_identifier", strategy = GenerationType.SEQUENCE)
    @Column(name = "rol_id", nullable = false, updatable = false)
    @Getter
    private Long id;

    @Column(name = "rol_name", length = 30, nullable = false)
    @NotEmpty
    @Size(max = 30)
    @Getter
    private String name;

    @Column(name = "rol_description", length = 60, nullable = false)
    @NotEmpty
    @Size(max = 60)
    @Getter
    @Setter
    private String description;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    @Setter
    private List<User> users;

    @CreationTimestamp
    @Column(name = "rol_created", nullable = false, updatable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Getter
    private Date created;

    @UpdateTimestamp
    @Column(name = "rol_updated", insertable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Getter
    private Date updated;

    @Version
    @Column(name = "rol_version", nullable = false)
    @Getter
    private Long version;

    public void setName(String name) {
        this.name = name.toUpperCase();
    }

    public List<User> getUsers() {
        if (users == null) {
            users = new ArrayList<>();
        }
        return users;
    }

    @Override
    public String toString() {
        return description.concat(" (").concat(id.toString()).concat(")");
    }

}
