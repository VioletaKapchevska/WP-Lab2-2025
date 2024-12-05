package mk.ukim.finki.wp.lab.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
public class Song {
    Long trackId;
    String title;
    String genre;
    int releaseYear;
    //private Long id;
    public List<Artist> performers;
    Album album;

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
