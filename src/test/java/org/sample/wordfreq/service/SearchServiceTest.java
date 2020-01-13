package org.sample.wordfreq.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.wordfreq.model.WordFrequencyOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchServiceTest {
    @Autowired
    private SearchService searchService;

    @Test
    public void test() {
        WordFrequencyOutput output = searchService.search("she");
        output.getFinalCount();
    }
}
