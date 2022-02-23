package by.shcharbunou.dal.entity.group;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@AllArgsConstructor

@Entity
@Table(name = "group")
public class Group {
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "group_id", nullable = false)
    private UUID id;

    @Deprecated
    public Group() {}
}
