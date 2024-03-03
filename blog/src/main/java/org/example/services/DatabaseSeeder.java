package org.example.services;

import com.github.javafaker.Faker;
import org.example.entities.CategoryEntity;
import org.example.entities.PostEntity;
import org.example.entities.PostTagEntity;
import org.example.entities.TagEntity;
import org.example.respositories.CategoryRepository;
import org.example.respositories.PostRepository;
import org.example.respositories.PostTagRepository;
import org.example.respositories.TagRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;

@Service
public class DatabaseSeeder {
    private final Faker faker;
    private final CategoryRepository categoryRepository;
    private final PostRepository postRepository;
    private final PostTagRepository postTagRepository;
    private final TagRepository tagRepository;

    public DatabaseSeeder(CategoryRepository categoryRepository,
                          PostRepository postRepository,
                          PostTagRepository postTagRepository,
                          TagRepository tagRepository) {
        this.categoryRepository = categoryRepository;
        this.postRepository = postRepository;
        this.postTagRepository = postTagRepository;
        this.tagRepository = tagRepository;

        faker = new Faker(new Locale("uk"));
    }

    public void SeedAllTables() {
        seedCategories(10);
        seedTags(10);
        generatePosts(20);
        generatePostTags(1);
    }
    private void seedCategories(int n) {
        if(categoryRepository.count()<10) {
            for (int i = 0; i<n;i++) {
                CategoryEntity category = new CategoryEntity();
                category.setName(faker.commerce().department());
                category.setUrlSlug(UrlSlugGenerator.generateUrlSlug(category.getName()));
                category.setDescription(faker.lorem().word());
                categoryRepository.save(category);
            }
        }
    }

    private void seedTags(int n) {
        if(tagRepository.count()<10) {
            for (int i = 0; i<n;i++) {
                TagEntity tag = new TagEntity();
                tag.setName(faker.lorem().word());
                tag.setUrlSlug(UrlSlugGenerator.generateUrlSlug(tag.getName()));
                tag.setDescription(faker.lorem().word());
                tagRepository.save(tag);
            }
        }
    }
    public void generatePosts(int count) {
        var categories = categoryRepository.findAll();
        for (int i = 0; i < count; i++) {
            PostEntity post = new PostEntity();
            post.setTitle(faker.lorem().characters(10, 30));
            post.setShortDescription(faker.lorem().characters(20, 50));
            post.setDescription(faker.lorem().characters(100, 150));
            post.setMeta(faker.lorem().characters(20, 30));
            post.setUrlSlug(faker.lorem().characters(5, 10));
            post.setPublished(faker.random().nextBoolean());
            post.setPostedOn(LocalDateTime.now());
            post.setCategory(categories.get(faker.random().nextInt(categories.size())));
            postRepository.save(post);
        }
    }

    public void generatePostTags(int count) {
        var posts = postRepository.findAll();
        var tags = tagRepository.findAll();
        for (int i = 0; i < count; i++) {
            PostTagEntity postTag = new PostTagEntity();
            var post = posts.get(faker.random().nextInt(posts.size()));
            var newPost = new PostEntity();
            newPost.setId(post.getId());
            postTag.setPost(newPost);

            var tag = tags.get(faker.random().nextInt(tags.size()));
            var newTag = new TagEntity();
            newTag.setId(tag.getId());
            postTag.setTag(newTag);
            postTagRepository.save(postTag);
        }
    }
}


