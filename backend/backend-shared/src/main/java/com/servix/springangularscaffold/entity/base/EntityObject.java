package com.servix.springangularscaffold.entity.base;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class EntityObject implements Comparable<EntityObject>, Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @CreatedDate
    @Column(name = "created_on", nullable = false)
    private LocalDateTime createdOn;

    @LastModifiedDate
    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    @Override
    public int compareTo(EntityObject o) {
        return Objects.compare(this, o, Comparator.comparingLong(this::getIdForComparison));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntityObject that = (EntityObject) o;
        if (id == null) return this == that;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        if (id == null) {
            return super.hashCode();
        }
        return Objects.hashCode(id);
    }

    @Override
    @Transient
    public boolean isNew() {
        return id == null;
    }

    private long getIdForComparison(EntityObject value) {
        return Optional
                .ofNullable(value)
                .map(EntityObject::getId)
                .orElse(-1L);
    }
}
