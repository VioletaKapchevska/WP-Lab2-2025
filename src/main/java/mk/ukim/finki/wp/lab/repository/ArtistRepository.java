package mk.ukim.finki.wp.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ArtistRepository {

    static List<Artist> artists = new ArrayList<Artist>(5);

    @PostConstruct
    public void init(){
        artists.add(new Artist(1L, "John", "Doe", "Bio1"));
        artists.add(new Artist(2L, "Jane", "Smith", "Bio2"));
        artists.add(new Artist(3L, "Emily", "Johnson", "Bio3"));
        artists.add(new Artist(4L, "Michael", "Brown", "Bio4"));
        artists.add(new Artist(5L, "Jessica", "Davis", "Bio5"));
    }
    public List<Artist> findAll(){
        return artists;
    }
    public Optional<Artist> findById(Long id){
        return artists.stream().filter(artist -> artist.getId().equals(id)).findFirst();
    }
}
