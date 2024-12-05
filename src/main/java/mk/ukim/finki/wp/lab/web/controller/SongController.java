package mk.ukim.finki.wp.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.wp.lab.model.Album;
import mk.ukim.finki.wp.lab.model.Artist;
import mk.ukim.finki.wp.lab.model.Song;
import mk.ukim.finki.wp.lab.service.AlbumService;
import mk.ukim.finki.wp.lab.service.ArtistService;
import mk.ukim.finki.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;
    private final AlbumService albumService;
    private final ArtistService artistService;

    public SongController(SongService songService, AlbumService albumService, ArtistService artistService) {
        this.songService = songService;
        this.albumService = albumService;
        this.artistService = artistService;
    }
    @GetMapping()
    public String getSongsPage(@RequestParam(required = false) String error, Model model){
     //da gi vrakja site pesni so albumite

        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("songs",songService.listSongs());
        return "listSongs";

    }
    //ja vrakja stranata za dodavanje na nova pesna

    @GetMapping("/add-form")
    public String getAddSongPage(Model model){
        model.addAttribute("albums",albumService.findAll());//albumite sto ni trebaat
        return "add-song";
    }

    //delete a song
    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable(name = "id") String id){
        System.out.println("id: "+id);
          this.songService.deleteSongById(Long.parseLong(id));//ja brisheme pesnata
          return "redirect:/songs";
    }


    //add new Song
    @PostMapping("/add")
    public String saveSong(@RequestParam(name="songName") String title,
                           @RequestParam(name="genre") String genre,
                           @RequestParam(name="rYear") String releaseYear,
                           @RequestParam(name="albumId") Long albumId){
       Album album = albumService.findById(albumId);
       Integer year = Integer.parseInt(releaseYear);
       this.songService.addNewSong(title,genre,year,album);
       return "redirect:/songs";
    }

   @GetMapping("/edit-form/{id}")
   public String getEditSongForm(@PathVariable String id, Model model){
       Song song = this.songService.findByTrackId(Long.parseLong(id));
       List<Artist> artists = this.artistService.listArtists();
       List<Album> albums = this.albumService.findAll();
       model.addAttribute("song", song);
       model.addAttribute("artists", artists);
       model.addAttribute("albums", albums);
       return "add-song";
   }

    @GetMapping("/edit/{songId}")
    public String editSong(@PathVariable Long songId,
                           @RequestParam(required = false) String title,
                           @RequestParam(required = false) Long trackId,
                           @RequestParam(required = false) String genre,
                           @RequestParam(required = false) Integer releaseYear,
                           @RequestParam(required = false) Long albumId){
       Album album = albumService.findById(albumId);
       Song song = songService.findByTrackId(trackId);
       List<Artist> artistsList = song.getPerformers();
       // String trackId, String title, String genre, int releaseYear, List<Artist> performers,Album album
        songService.editSong(trackId,title,genre,releaseYear,artistsList,album);
        return "redirect:/songs";
    }

   @PostMapping
    public String songsToArtist(@RequestParam(required = false) Long trackId, HttpServletRequest request){
       if (trackId == null) {
           return "redirect:/songs";
       }
       try {
           Song song = songService.findByTrackId(trackId);
           request.getSession().setAttribute("song", song);
           System.out.println("redirecting!");
           return "redirect:/artist";

       } catch (Exception ex) {
           return "redirect:/songs";
       }
   }



}
