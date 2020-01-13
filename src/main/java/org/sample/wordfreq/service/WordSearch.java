package org.sample.wordfreq.service;

import org.sample.wordfreq.model.SearchResult;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Case insensitive word search given a word and a text based file
 */
public class WordSearch implements Callable<SearchResult> {

    private String word;
    private Path file;

    private WordSearch() {
        //Lock in the default constructor to ensure that this class always has the needed parameters
    }

    public WordSearch(String word, Path file) {
        this.word = word;
        this.file = file;
    }

    @Override
    public SearchResult call() throws Exception {
        Path path = Paths.get(file.toUri());
        SearchResult searchResult = new SearchResult();
        try(Stream<String> lines = Files.lines(path)) {
            searchResult.setCount(
                    lines.map(line -> count(word, line)).collect(Collectors.toList())
                            .stream().reduce(0, Integer::sum));
            searchResult.setFoundInFileName(file.toAbsolutePath().toString());
        }
        return searchResult;
    }

    private int count(String word, String line) {
        Pattern pattern = Pattern.compile("\\b"+word+"\\b", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(line);
        int count = 0;
        while (matcher.find())
            count++;
        return count;
    }
}
