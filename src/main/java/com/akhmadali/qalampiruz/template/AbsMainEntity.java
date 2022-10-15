package com.akhmadali.qalampiruz.template;

import com.akhmadali.qalampiruz.entity.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;


@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class AbsMainEntity {

    @CreationTimestamp
    private Timestamp createdAt = Timestamp.from(Instant.now());

    @CreationTimestamp
    private Timestamp updatedAt = Timestamp.from(Instant.now());

    @JoinColumn(updatable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    private User updatedBy;


}
