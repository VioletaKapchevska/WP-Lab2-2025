package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Album {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;
     String name;
     String genre;
     String releaseYear;
     @OneToMany(mappedBy = "album")
     List<Song> songs;
     public Album(){}

    public Album(Long id, String name, String genre, String releaseYear) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }
}
