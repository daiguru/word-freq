package org.sample.wordfreq.service;

import org.sample.wordfreq.model.SearchResult;
import org.sample.wordfreq.model.WordFrequencyOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Service that reads the provided seed file and outputs the relevant {@link org.sample.wordfreq.model.WordFrequencyOutput}.
 */
@Component
public class SearchService {

    /**
     *
     */
    @Value("${word.search.thread.pool:5}")
    private int threadCount;

    @Autowired
    private FileReader fileReader;

    public WordFrequencyOutput search(String word) {
        final ExecutorService executorPool = Executors.newFixedThreadPool(threadCount);
        final ExecutorCompletionService<SearchResult> completionService = new ExecutorCompletionService<>(executorPool);

        List<Path> fileList = fileReader.getFiles();

        //Executes the word search per file leveraging on concurrency
        for (Path file: fileList) {
            completionService.submit(new WordSearch(word, file));
        }

        List<SearchResult> searchResults = new ArrayList<>();
        Integer finalCount = 0;

        //Collects the output of the word search
        for (int i=0; i < fileList.size(); i++) {
            try {
                final Future<SearchResult> future = completionService.take();
                SearchResult searchResult = future.get();
                finalCount += searchResult.getCount();
                searchResults.add(searchResult);
            } catch (InterruptedException|ExecutionException e) {
                e.printStackTrace();
            }
        }

        WordFrequencyOutput output = new WordFrequencyOutput();
        output.setFinalCount(finalCount);
        output.setSearchResultList(searchResults);
        return output;
    }

}
