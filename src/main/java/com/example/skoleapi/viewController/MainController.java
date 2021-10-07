package com.example.skoleapi.viewController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class MainController {

    @GetMapping("/")
    public void redirectToDoc(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui/");
    }
}
