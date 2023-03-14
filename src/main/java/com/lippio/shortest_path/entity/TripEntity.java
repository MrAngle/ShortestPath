package com.lippio.shortest_path.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;


@Table(name = "trip")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TripEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "countries", nullable = false)
    private String countries;

    @Column(name = "name")
    private String name;

    @Column(name = "creation_at", nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Override
    public boolean equals(final Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        final TripEntity that = (TripEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "TripEntity{" +
               "id=" + id +
               ", countries='" + countries + '\'' +
               ", name='" + name + '\'' +
               ", createdAt=" + createdAt +
               '}';
    }
}

