package org.sample.wordfreq.controller;

import org.sample.wordfreq.model.WordFrequencyOutput;
import org.sample.wordfreq.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for performing word Search
 */
@RestController
public class WordFrequencyController {
    @Autowired
    private SearchService searchService;

    @GetMapping("/search")
    public WordFrequencyOutput search(@RequestParam(value = "word") String word) {
        return searchService.search(word);
    }
}
