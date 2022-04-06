package by.shcharbunou.dal.entity;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public abstract class BaseEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Deprecated
    public BaseEntity() {}
}
