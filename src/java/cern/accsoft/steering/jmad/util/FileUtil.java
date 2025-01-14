// @formatter:off
 /*******************************************************************************
 *
 * This file is part of JMad.
 * 
 * Copyright (c) 2008-2011, CERN. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 ******************************************************************************/
// @formatter:on

/**
 * 
 */
package cern.accsoft.steering.jmad.util;

import static java.util.stream.Collectors.toSet;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cern.accsoft.steering.jmad.util.io.TextFileParser;
import cern.accsoft.steering.jmad.util.io.TextFileParserException;
import cern.accsoft.steering.jmad.util.io.impl.TextFileParserImpl;

/**
 * This class contains some useful static methods for handling files
 * 
 * @author Kajetan Fuchsberger (kajetan.fuchsberger at cern.ch)
 */
public final class FileUtil {

    /** The logger for the class */
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    /**
     * private constructor to prevent instantiation
     */
    private FileUtil() {
        /* only static methods */
    }

    /**
     * creates the given dir and enables access for all users if wanted.
     * 
     * @param dir the dir to create
     * @param globalAccess if true, give rights to all users, if false permissions are not changed
     */
    public static void createDir(File dir, boolean globalAccess) {
        if ((!dir.exists()) && (!dir.mkdirs())) {
            LOGGER.warn("failed to create directory '" + dir.getAbsolutePath() + "'.");
        }

        if (globalAccess) {
            dir.setReadable(true, false);
            if (!dir.setWritable(true, false)) {
                LOGGER.warn("failed to allow write for all users on directory '" + dir.getAbsolutePath() + "'.");
            }
        }
    }

    /**
     * recursively deletes the dir and all its content
     * 
     * @param dir the dir to delete
     * @return true if successful, false otherwise.
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    /**
     * Copy a file in the File system. Uses {@link FileReader} and {@link FileWriter} to copy the files content.
     * 
     * @param source the source file
     * @param destination the destination file to save the content to
     * @throws IOException
     */
    public static void copyFile(File source, File destination) throws IOException {
        FileReader inFile = new FileReader(source);
        FileWriter outFile = new FileWriter(destination);
        int content;

        while ((content = inFile.read()) != -1) {
            outFile.write(content);
        }

        inFile.close();
        outFile.close();
    }

    /**
     * Returns the last N lines of the given file. If the file has less lines than the requested ones, it will return
     * all the lines of the file. Thus, the returned list can also be empty.
     * 
     * @param file the file from which to retrieve the last lines
     * @param requestedNumberOfLines the number of lines to retrieve
     * @return the last lines, as requested.
     */
    public static List<String> tail(File file, int requestedNumberOfLines) {
        TextFileParser parser = new TextFileParserImpl();
        List<String> lines;
        try {
            lines = parser.parse(file);
        } catch (TextFileParserException e) {
            throw new RuntimeException("Error while loading file", e);
        }

        int numberOfLines = lines.size();
        if (numberOfLines > requestedNumberOfLines) {
            return new ArrayList<String>(lines.subList(numberOfLines - requestedNumberOfLines, numberOfLines));
        } else {
            return new ArrayList<String>(lines);
        }
    }

    public static Set<File> searchInFor(Path root, Predicate<? super Path> filterPredicate) {
        try (Stream<Path> stream = Files.walk(root)) {
            // @formatter:off
            return stream
                    .filter(Files::isRegularFile)
                    .filter(filterPredicate)
                    .map(Path::toFile)
                    .collect(toSet());
            // @formatter:on
        } catch (IOException e) {
            throw new RuntimeException("Directory " + root + " cannot be searched.", e);
        }
    }
}
