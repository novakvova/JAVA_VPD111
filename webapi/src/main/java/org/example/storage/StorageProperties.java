package org.example.storage;

import lombok.Data;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("store")
public class StorageProperties {
    private String location="uploading";
}
