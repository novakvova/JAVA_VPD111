package org.example.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class PostTagPK implements Serializable {
    private PostEntity post;
    private TagEntity tag;
}
