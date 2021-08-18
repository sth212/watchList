package com.example.demo.controllers;

import com.example.demo.exception.DuplicateTitleException;
import com.example.demo.models.BankResult;
import com.example.demo.models.LoginViewModel;
import com.example.demo.models.WatchlistItem;
import org.hibernate.validator.internal.engine.groups.ValidationOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.namespace.QName;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
public class LoginController {
    Logger logger =  LoggerFactory.getLogger(LoginController.class);
    @PostMapping("/login")
    public ModelAndView submitWatchlistItemForm(@Validated(ValidationOrder.class) LoginViewModel loginViewModel, BindingResult bindingResult) {

        logger.info("GET /watchlistItem called");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> response;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8083/api/v1/users")
                .queryParam("name", loginViewModel.getUserName());
        response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Boolean>() {
                }
        );

        Boolean Result = response.getBody();

        if (Result) {
            RedirectView redirectView = new RedirectView();
            return new ModelAndView( new RedirectView("/watchlist"));
        }
        else
        {
            Map<String, Object> model = new HashMap<String, Object>();
            String msg = "User Name or password not correct";
           model.put("errorMessage ", msg);


            model.put("loginViewModel", new LoginViewModel());
            return new ModelAndView(new RedirectView(""),model );
        }
//        return new ModelAndView(new RedirectView("/login"));
    }
    @GetMapping("")
    public ModelAndView showWatchlistItemForm() {

        logger.info("GET /watchlistItemForm called");
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("loginViewModel", new LoginViewModel());
        model.put("errorMessage", "");
        String viewName = "login";
        return new ModelAndView(viewName,model );
    }

}
