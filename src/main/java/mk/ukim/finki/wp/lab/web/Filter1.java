package mk.ukim.finki.wp.lab.web;

/*import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mk.ukim.finki.wp.lab.model.Song;

import java.io.IOException;

@WebFilter({"/details", "/artist"})
public class Filter1 implements Filter {
    @Override
    public void doFilter(jakarta.servlet.ServletRequest request, jakarta.servlet.ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();//ja zimame sesijata
        Song song = (Song) session.getAttribute("song");
        if (song == null || song.getPerformers() == null) {//ako ne e selektirana pesna
            resp.sendRedirect("/listSongs");
        } else {
            chain.doFilter(request, response);
        }
    }
}*/
