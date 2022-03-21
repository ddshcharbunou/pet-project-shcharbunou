package by.shcharbunou.dal.entity.user.office.material;

import by.shcharbunou.dal.entity.BaseEntity;
import by.shcharbunou.dal.entity.blog.gallery.Image;
import by.shcharbunou.dal.entity.enums.material.MaterialType;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor

@Entity
@Table(name = "material")
@AttributeOverride(name = "id", column = @Column(name = "material_id"))
public class Material extends BaseEntity {
    @Column(name = "material_designation", nullable = false)
    private String designation;

    @Column(name = "material_link", nullable = false)
    private String link;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private Image image;

    @Column(name = "material_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private MaterialType type;

    @Deprecated
    public Material() {}
}
