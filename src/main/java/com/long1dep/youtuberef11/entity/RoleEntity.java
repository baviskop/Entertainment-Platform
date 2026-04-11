package com.long1dep.youtuberef11.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UuidGenerator;

import java.io.Serializable;
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "roles")
public class RoleEntity extends AbstractAuditingEntity<String> implements Serializable {
    @Id
    @UuidGenerator
    String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 20)
    ERole name;
}
