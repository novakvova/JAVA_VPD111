package org.example.services;

import com.github.javafaker.Faker;
import org.example.constants.Roles;
import org.example.entities.*;
import org.example.respositories.*;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public DatabaseSeeder(CategoryRepository categoryRepository,
                          PostRepository postRepository,
                          PostTagRepository postTagRepository,
                          TagRepository tagRepository,

                          RoleRepository roleRepository,
                          UserRepository userRepository,
                          UserRoleRepository userRoleRepository,
                          PasswordEncoder passwordEncoder) {
        this.categoryRepository = categoryRepository;
        this.postRepository = postRepository;
        this.postTagRepository = postTagRepository;
        this.tagRepository = tagRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        faker = new Faker(new Locale("uk"));
    }

    public void SeedAllTables() {
        seedCategories(10);
        seedTags(10);
        generatePosts(20);
        generatePostTags(5);

        seedRole();
        seedUser();
    }

    private void seedCategories(int n) {
        if (categoryRepository.count() < n) {
            for (int i = 0; i < n; i++) {
                CategoryEntity category = new CategoryEntity();
                category.setName(faker.commerce().department());
                category.setUrlSlug(UrlSlugGenerator.generateUrlSlug(category.getName()));
                category.setDescription(faker.lorem().word());
                categoryRepository.save(category);
            }
        }
    }

    private void seedTags(int n) {
        if (tagRepository.count() < n) {
            for (int i = 0; i < n; i++) {
                TagEntity tag = new TagEntity();
                tag.setName(faker.lorem().word());
                tag.setUrlSlug(UrlSlugGenerator.generateUrlSlug(tag.getName()));
                tag.setDescription(faker.lorem().word());
                tagRepository.save(tag);
            }
        }
    }

    public void generatePosts(int count) {
        if (postRepository.count() < count) {
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
    }

    public void generatePostTags(int count) {
        if (postTagRepository.count() < count) {
            var posts = postRepository.findAll();
            var tags = tagRepository.findAll();
            for (int i = 0; i < count; i++) {
                PostTagEntity postTag = new PostTagEntity();
                postTag.setTag(tags.get(faker.random().nextInt(tags.size())));
                postTag.setPost(posts.get(faker.random().nextInt(posts.size())));
                postTagRepository.save(postTag);
            }
        }
    }

    private void seedRole() {
        if(roleRepository.count() == 0) {
            RoleEntity admin = RoleEntity
                    .builder()
                    .name(Roles.Admin)
                    .build();
            roleRepository.save(admin);
            RoleEntity user = RoleEntity
                    .builder()
                    .name(Roles.User)
                    .build();
            roleRepository.save(user);
        }
    }

    private void seedUser() {
        if(userRepository.count() == 0) {
            var user = UserEntity
                    .builder()
                    .email("admin@gmail.com")
                    .firstName("Микола")
                    .lastName("Підкаблучник")
                    .phone("+380 97 67 56 464")
                    .password(passwordEncoder.encode("123456"))
                    .build();
            userRepository.save(user);
            var role = roleRepository.findByName(Roles.Admin);
            var ur = UserRoleEntity
                    .builder()
                    .role(role)
                    .user(user)
                    .build();
            userRoleRepository.save(ur);
        }
    }
}


