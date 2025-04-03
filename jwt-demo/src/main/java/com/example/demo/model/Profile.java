package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String login;

    @Column
    private String shortDescription;

    @JoinColumn
    @ManyToOne
    private Town homeTown;

    @ManyToMany
    @JoinTable(
            name = "profile_known_languages",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id")
    )
    private Set<Language> knownLanguages;

    @Enumerated(EnumType.STRING)
    @Column
    private MaritalStatus maritalStatus;

    @ManyToMany
    @JoinTable(
            name = "profile_family",
            joinColumns = @JoinColumn(name = "first_profile_id"),
            inverseJoinColumns = @JoinColumn(name = "second_profile_id")
    )
    private Set<Profile> family;

    @Column
    private boolean frozen;

    @OneToMany(mappedBy = "profile")
    private List<News> news;

    @OneToOne(mappedBy = "profile")
    private AccountId accountId;
}
