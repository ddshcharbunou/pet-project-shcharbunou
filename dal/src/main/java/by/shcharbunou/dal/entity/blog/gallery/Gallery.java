package by.shcharbunou.dal.entity.blog.gallery;

import by.shcharbunou.dal.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor

@Entity
@Table(name = "gallery")
@AttributeOverride(name = "id", column = @Column(name = "gallery_id"))
public class Gallery extends BaseEntity {
    @Column(name = "year", nullable = false)
    private int year;

    @OneToMany(mappedBy = "gallery", cascade = CascadeType.ALL)
    private Set<Image> images = new HashSet<>();

    public void connectImage(Image image) {
        this.images.add(image);
        image.setGallery(this);
    }

    @Deprecated
    public Gallery() {}
}
