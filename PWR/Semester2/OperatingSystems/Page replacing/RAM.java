import java.util.ArrayList;

public class RAM {
    ArrayList<Page> frames;

    RAM() {
        frames = new ArrayList<>(Main.RAM_SIZE);
        for(int i = 0; i < Main.RAM_SIZE; i++){
            frames.add(new Page(-1));
        }
    }

    void resetFrames(){
        for(int i = 0; i < Main.RAM_SIZE; i++){
            frames.get(i).pageId = -1;
        }

    }

    int isPageInFrames(Page page){
        for (int i = 0; i < frames.size(); i++){
            if(frames.get(i).pageId == page.pageId) return i;
        }
        return -1;
    }

}
