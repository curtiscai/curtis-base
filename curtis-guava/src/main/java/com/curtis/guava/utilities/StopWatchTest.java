package com.curtis.guava.utilities;

import com.google.common.base.Stopwatch;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author curtis.cai
 * @desc TODO
 * @date 2021-09-15
 * @email curtis.cai@outlook.com
 * @reference
 */
public class StopWatchTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(StopWatchTest.class);

    @Test
    public void testStopWatch(){
        LOGGER.info("start process");
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // long elapsed1 = stopwatch.stop().elapsed(TimeUnit.SECONDS);
        Duration elapsed2 = stopwatch.stop().elapsed();
        LOGGER.info("end process, elapsed {}",elapsed2);
    }
}
