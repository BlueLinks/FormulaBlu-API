package com.example;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "teams")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String nationality;
    
    @Column(name = "founded_year")
    private Integer foundedYear;
    
    @Column(name = "team_principal")
    private String teamPrincipal;
    
    @Column(name = "headquarters")
    private String headquarters;
    
    @Column(name = "primary_color")
    private String primaryColor;
    
    @Column(name = "secondary_color")
    private String secondaryColor;
    
    private String description;
    
    @Column(name = "website")
    private String website;
}
