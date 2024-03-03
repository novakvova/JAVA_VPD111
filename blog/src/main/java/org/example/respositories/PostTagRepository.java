package org.example.respositories;

import org.example.entities.PostTagEntity;
import org.example.entities.PostTagPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostTagRepository extends JpaRepository<PostTagEntity, PostTagPK> {
}