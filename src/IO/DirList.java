package IO;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by VFilin on 12.05.2016.
 */
class DirFilter implements FilenameFilter {
    private Pattern pattern;

    public DirFilter (String regex) {
        pattern = Pattern.compile(regex);
    }

    @Override
    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }
}

public class DirList {
    public static void main (String[] args) {
        File path = new File (".");
        String[] list = path.list(new DirFilter(".+"));
        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);

        File currentFile;
        long size = 0;
        for (String dirItem : list) {
            currentFile = new File(dirItem);
            System.out.print (dirItem);
            if (currentFile.isFile()) {
                size = size + currentFile.length();
                System.out.print(" - " + currentFile.length() + " bytes");
            }
            System.out.println();
        }

        System.out.println("Total size is " + size + " bytes");
    }
}
