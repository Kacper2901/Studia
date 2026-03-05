import java.nio.file.Path;

public class FileDistance {
    Path filePath;
    double distanceFromInputText;
    Languages language;

    FileDistance(Path filePath, Languages language){
        this.filePath = filePath;
        this.distanceFromInputText = 0;
        this.language = language;
    }

    public void setDistanceFromInputText(double distanceFromInputText) {
        this.distanceFromInputText = distanceFromInputText;
    }
}
