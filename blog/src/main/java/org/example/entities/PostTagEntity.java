package org.example.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="tbl_post_tags")
@IdClass(PostTagPK.class)
public class PostTagEntity {
    @Id
    @ManyToOne
    @JoinColumn(name="post_id", nullable = false)
    private PostEntity post;

    @Id
    @ManyToOne
    @JoinColumn(name="tag_id", nullable = false)
    private TagEntity tag;
}
