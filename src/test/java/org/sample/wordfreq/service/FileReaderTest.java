package org.sample.wordfreq.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileReaderTest {
    @Autowired
    private FileReader fileReader;

    @Test
    public void test() {
        Assert.assertTrue(fileReader.getFiles().size() == 2);
    }

}
