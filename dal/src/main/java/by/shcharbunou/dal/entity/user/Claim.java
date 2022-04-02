package by.shcharbunou.dal.entity.user;

import by.shcharbunou.dal.entity.BaseEntity;
import lombok.*;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "claim")
@AttributeOverride(name = "id", column = @Column(name = "claim_id"))
public class Claim extends BaseEntity {
    @Column(name = "claim_user_id")
    private UUID userID;

    @Column(name = "claim_group_id")
    private UUID groupID;
}
