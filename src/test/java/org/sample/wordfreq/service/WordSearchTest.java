package org.sample.wordfreq.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.wordfreq.model.SearchResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WordSearchTest {

    @Value("${file.search.location}")
    private String testFile1;

    @Test
    public void test() throws ExecutionException, InterruptedException, URISyntaxException {
        String word = "she";
        WordSearch searcher = new WordSearch(word, Paths.get(getClass().getClassLoader().getResource(testFile1).toURI()));
        ExecutorService service = Executors.newSingleThreadExecutor();
        Future<SearchResult> future = service.submit(searcher);
        SearchResult searchResult = future.get();
        Assert.assertTrue(searchResult.getCount() == 6);
//        Assert.assertTrue(searchResult.getFoundInFileName().equals("./file1.txt"));
    }
}
