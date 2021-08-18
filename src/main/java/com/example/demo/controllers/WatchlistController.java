package com.example.demo.controllers;

import com.example.demo.exception.DuplicateTitleException;
import com.example.demo.models.WatchlistItem;
import com.example.demo.service.WatchlistService;
import com.example.demo.service.WatchlistServiceImpl;
import org.hibernate.validator.internal.engine.groups.ValidationOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;



@Controller
public class WatchlistController {

    Logger logger =  LoggerFactory.getLogger(WatchlistController.class);

    private WatchlistService watchlistServiceImpl;

    @Autowired
    public WatchlistController(WatchlistService watchlist) {
        super();
        this.watchlistServiceImpl = watchlist;
    }

    @GetMapping("/watchlist")
    public ModelAndView getMovieList() {

        logger.info("GET /watchlist called");

        String viewName = "watchlist";
        Map<String, Object> model = new HashMap<String, Object>();

        model.put("watchlistItems", watchlistServiceImpl.getWatchlistItems());
        model.put("numberOfMovies", watchlistServiceImpl.getWatchlistItemsSize());

        return new ModelAndView(viewName , model);
    }

    @GetMapping("/watchlistItem")
    public ModelAndView showWatchlistItem() {

        logger.info("GET /watchlistItem called");

        String viewName = "watchlistItem";

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("watchlistItem", new WatchlistItem());

        return new ModelAndView(viewName , model);
    }
    @GetMapping("/watchlistItemForm")
    public ModelAndView showWatchlistItemForm(@RequestParam(required = false)Integer id) {

        logger.info("GET /watchlistItemForm called");

        String viewName = "watchlistItemForm";

        Map<String, Object> model = new HashMap<String, Object>();
        WatchlistItem item=watchlistServiceImpl.findWatchlistItemById(id);
        if(item==null)
            model.put("watchlistItem", new WatchlistItem());
        else
            model.put("watchlistItem", item);

        return new ModelAndView(viewName , model);
    }
    @PostMapping("/watchlistItemForm")
    public ModelAndView submitWatchlistItemForm(@Validated(ValidationOrder.class) WatchlistItem watchlistItem, BindingResult bindingResult) {

        logger.info("GET /watchlistItem called");

        if (bindingResult.hasErrors()) {
            return new ModelAndView("watchlistItemForm");
        }

        try {
            watchlistServiceImpl.addOrUpdateWatchlistItem(watchlistItem);
        } catch (DuplicateTitleException e) {
            bindingResult.rejectValue("title", "", "This movie is already on your watchlist");
            return new ModelAndView("watchlistItemForm");
        }

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/watchlist");

        return new ModelAndView(new RedirectView("/watchlist"));
    }
}
