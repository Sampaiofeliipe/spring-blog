package com.anhembiblog.anhembiblog.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = Post.TABLE_NAME )
public class Post {

    public static final String TABLE_NAME = "post";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Size(max = 255)
    private String title;

    @NotBlank
    @Size(max = 500)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    private LocalDateTime createat = LocalDateTime.now();
}