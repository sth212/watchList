package com.example.demo.controllers;

import com.example.demo.models.Bank;
import com.example.demo.models.BankResult;
import com.example.demo.models.CustomPageImpl;
import com.example.demo.service.ExcelExporterService;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class BanksController {

    @GetMapping("/banklist")
    public ModelAndView getBanklist(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(0);
        int pageSize = size.orElse(2);
        Map<String, Object> model = new HashMap<String, Object>();
        BankResult bankResult = getBankResult(currentPage, pageSize);
        model.put("bankResult", bankResult);
        int totalPages = bankResult.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.put("pageNumbers", pageNumbers);
        }
        model.put("PageNumber", page.orElse(1)-1);
        String viewName = "bankList";
        return new ModelAndView(viewName , model);
    }

    private BankResult getBankResult(int currentPage, int pageSize) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<BankResult> response;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8082/api/v1/banks")
                .queryParam("page", currentPage)
                .queryParam("size", pageSize);
        response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<BankResult>(){}
        );

        BankResult  bankResult = response.getBody();
        return bankResult;
    }

    @PostMapping("/banklist")
    public ModelAndView getBanklist(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,BankResult searchCriteria) {
        int currentPage = page.orElse(0);
        int pageSize = size.orElse(2);
        Map<String, Object> model = new HashMap<String, Object>();
        String viewName = "bankList";

        BankResult bankResult = getBankResult(searchCriteria.getSearchName(), currentPage, pageSize);
        model.put("bankResult", bankResult);
        int totalPages = bankResult.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.put("pageNumbers", pageNumbers);
        }
        model.put("PageNumber", page.orElse(1)-1);
        return new ModelAndView(viewName , model);
    }

    private BankResult getBankResult(String name, int currentPage, int pageSize) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<BankResult> response;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8082/api/v1/banks")
                .queryParam("page", currentPage)
                .queryParam("size", pageSize)
                .queryParam("name", name);
        response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<BankResult>(){}
        );

        BankResult  bankResult = response.getBody();
        return bankResult;
    }


@GetMapping("/banklist/export/excel")
    public String exportToExcel(HttpServletResponse response, @RequestParam("name") String name) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Bank_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        ExcelExporterService excelExporter = new ExcelExporterService();

//        if(name.isPresent())
         excelExporter.export(response,getBankResult(name,0,2).getBankList());
//        else
//            excelExporter.export(response,getBankResult(0,2).getBankList());
    return "redirect:/banklist";
    }

}
