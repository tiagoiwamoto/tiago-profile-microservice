package br.com.tiagoiwamoto.tiagoprofilemicroservice.core.domain;

/*
 * Tiago Henrique Iwamoto
 * tiago.iwamoto@gmail.com
 * linkedin.com/in/tiago-iwamoto
 * System specialist
 * 21/10/2021 | 20:02
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "iwt-personal-me")
@AllArgsConstructor
@Data
public class Me {

    @Id
    private String id;
    @NotNull
    @NotBlank
    private String fullName;
    @NotNull
    @NotBlank
    private String aboutMe;
    @NotNull
    @NotBlank
    private String githubUrl;
    @NotNull
    @NotBlank
    private String linkedinUrl;
    private List<Education> educations;
    private List<Especialist> especialists;
    private List<Resume> resumes;
    private List<Work> works;
    private List<Certificate> certificates;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
