package cern.accsoft.steering.jmad.kernel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.After;
import org.junit.Test;

import cern.accsoft.steering.jmad.JMadException;
import cern.accsoft.steering.jmad.bin.MadxBinImpl;
import cern.accsoft.steering.jmad.util.JMadPreferences;
import cern.accsoft.steering.jmad.util.JMadPreferencesImpl;
import cern.accsoft.steering.jmad.util.TempFileUtilImpl;

public class JMadKernelTest extends cern.accsoft.steering.jmad.util.LoggedTestCase {

    File file = new File("madx-test.out");
    JMadKernelImpl kernel = new JMadKernelImpl();
    {
        JMadPreferences prefs = new JMadPreferencesImpl();
        TempFileUtilImpl fileUtil = new TempFileUtilImpl();
        fileUtil.setPreferences(prefs);
        fileUtil.init();

        MadxBinImpl madxBin = new MadxBinImpl();
        madxBin.setFileUtil(fileUtil);
        madxBin.init();

        kernel.setFileUtil(fileUtil);
        kernel.setMadxBin(madxBin);
        kernel.setPreferences(prefs);
    }

    @After
    public void deleteTestFile() throws JMadException {
        if (!file.delete()) {
            ; // ignore, may not exist.
        }

        if (kernel.isMadxRunning()) {
            kernel.stop();
        }
    }

    // @Ignore("Takes to long")
    @Test
    public void testEmptyCommands() throws JMadException {
        kernel.start();
        int exitValue = kernel.stop();

        assertEquals("Call of MadX with empty file should return correctly.", 0, exitValue);
    }

    // @Ignore("Takes to long")
    @Test
    public void testWaitUntilReady() throws JMadException {
        kernel.start();
        kernel.writeCommand("System \"echo > " + file.getAbsolutePath() + "\";");
        kernel.waitUntilReady();
        assertTrue("madx should have created a file.", file.exists());
    }

    // @Ignore("Takes to long")
    @Test
    public void testWaitUntilReadyTimeout() throws JMadException {
        kernel.start();
        kernel.setTimeout((long) 0);
        kernel.writeCommand("System \"echo > " + file.getAbsolutePath() + "\";");
        boolean timedout = kernel.waitUntilReady();
        assertTrue("madx should not have been that fast in creating the file.", timedout);
    }
}
