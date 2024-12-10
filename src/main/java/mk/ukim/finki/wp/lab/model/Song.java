package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long trackId;

    String title;

    String genre;

    int releaseYear;

    @ManyToMany
    public List<Artist> performers;

    @ManyToOne
    Album album;

    public Song(){}

    public Song(Long trackId, String title, String genre, int releaseYear, List<Artist> performers,Album album) {
        this.trackId = trackId;
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = new ArrayList<>(performers);

        this.trackId = trackId;
        this.album=album;
    }
    public Song( String title, String genre, int releaseYear, Album album) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.performers = new ArrayList<>(); // Иницијализираме празна листа за изведувачи
        Random random = new Random();
        this.trackId=random.nextLong(10000); // Генерирање случаен ID
        this.album=album;
    }
}
