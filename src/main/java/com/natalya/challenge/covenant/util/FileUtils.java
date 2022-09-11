package com.natalya.challenge.covenant.util;

import java.io.File;
import java.nio.file.FileSystemException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class FileUtils {

    /**
     *
     * @param fileToRename existing file that should be renamed
     * @param name new file name
     * @return renamed file
     * @throws FileSystemException
     */
    public static File renameFile(@NonNull File fileToRename, @NonNull String name) throws FileSystemException {
        String newPath = fileToRename.getAbsolutePath().replace(fileToRename.getName(), name);
        File renamedFile = new File(newPath);
        boolean isRenamed = fileToRename.renameTo(renamedFile);
        if (!isRenamed) {
            throw new FileSystemException(newPath);
        }
        return renamedFile;
    }

    /**
     * collects files in directory and returns last modified/saved file
     * @param sortDir directory path
     * @return last modified/saved file. If directory is empty return null
     */
    public static File getLastModifiedFile(String sortDir) {
        File dir = new File(sortDir);
        if (dir.isDirectory()) {
            Optional<File> opFile = Arrays.stream(Objects.requireNonNull(dir.listFiles(File::isFile)))
                    .max(Comparator.comparingLong(File::lastModified));

            if (opFile.isPresent()) {
                return opFile.get();
            }
        }
        return null;
    }
}
