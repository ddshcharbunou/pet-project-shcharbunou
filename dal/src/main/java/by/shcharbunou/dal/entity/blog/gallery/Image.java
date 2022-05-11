package by.shcharbunou.dal.entity.blog.gallery;

import by.shcharbunou.dal.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@AllArgsConstructor

@Entity
@Table(name = "image")
@AttributeOverride(name = "id", column = @Column(name = "image_id"))
public class Image extends BaseEntity {
    @Column(name = "image_designation", nullable = false)
    private String designation;

    @ManyToOne
    @JoinColumn(name = "gallery_id")
    private Gallery gallery;

    @Deprecated
    public Image() {}
}
