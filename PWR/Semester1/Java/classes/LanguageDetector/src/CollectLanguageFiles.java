import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;

public class CollectLanguageFiles {

    public static ArrayList<FileDistance> findLangFiles(Path dir, Languages lang) throws IOException {
        ArrayList<FileDistance> result = new ArrayList<>();
        String langPrefix = getLangPrefix(lang);
        try (DirectoryStream<Path> filesPaths = Files.newDirectoryStream(dir, langPrefix + "_*")) {
            for (Path path : filesPaths) {
                if (Files.isRegularFile(path)) {
                    result.add(new FileDistance(path,lang));
                }
            }
        }
        return result;
    }

    private static String getLangPrefix(Languages lang){
        switch (lang){
            case POLISH:
                return "PL";
            case ENGLISH:
                return "EN";
            case SPANISH:
                return "ES";
            default:
                throw new IllegalArgumentException("This language is not available");
        }
    }
}
