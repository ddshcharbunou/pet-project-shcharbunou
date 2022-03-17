package by.shcharbunou.dal.entity.blog.gallery;

import by.shcharbunou.dal.entity.BaseEntity;

import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
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
