package bg.softuni.movieapp.web;

import bg.softuni.movieapp.model.entity.Actor;
import bg.softuni.movieapp.services.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ActorController {

    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @GetMapping("/actors")
    public ModelAndView getActors(
            @RequestParam(name = "search", required = false) String searchName,
            @RequestParam(name = "sort", defaultValue = "name") String sort,
            @RequestParam(name = "order", defaultValue = "asc") String order,
            Pageable pageable) {

        ModelAndView view = new ModelAndView("actors");

        Page<Actor> actors = actorService.getActorsBySearchAndSort(searchName, sort, order, pageable);
        view.addObject("actors", actors);

        return view;
    }
}
