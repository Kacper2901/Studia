import java.util.ArrayList;
import java.util.Random;

public class Simulation {
    RAM ram;
    ArrayList<Page> pageRequests;
    Random rand;



    Simulation(){
        ram = new RAM();
        pageRequests = new ArrayList<>(Main.PAGE_REQUESTS_CAPACITY);
        rand = new Random();
    }

     int randomNumberFromInterval(int l, int r){
        return (int)(rand.nextDouble()*(r + 1 -l)) + l;
    }

    void setPageRequests(){
        int firstPageId = 0;
        for(int i = 0; i < Main.PAGE_REQUESTS_CAPACITY; i++){
            if(i % 50 == 0){
                firstPageId = randomNumberFromInterval(Main.LOCALITY_DELTA, Main.DISK_SIZE - 1 - Main.LOCALITY_DELTA);
                addPageRequest(firstPageId);
                continue;
            }

            int closePageId = randomNumberFromInterval(firstPageId - Main.LOCALITY_DELTA, firstPageId + Main.LOCALITY_DELTA);
            addPageRequest(closePageId);
        }
    }

    void addPageRequest(int id){
        pageRequests.add(new Page(id));
    }

    void resetSimulation(){
        ram.resetFrames();
        pageRequests = new ArrayList<>(Main.PAGE_REQUESTS_CAPACITY);
        setPageRequests();
    }

    int findLongestStepIdx(int currIdx){
        int max = -1;
        int id = -1;
        for(int i = 0; i < ram.frames.size(); i ++){
            if(ram.frames.get(i).stepsToNextRequest > max){
                id = i;
                max = ram.frames.get(i).stepsToNextRequest;
            }
        }
        return id;
    }

    void setFutureSteps(int currRequestIdx){
        for(int i = 0; i < Main.PAGE_REQUESTS_CAPACITY; i++){
            Page currPageRequest = pageRequests.get(i);
            for(int j = currRequestIdx + 1; j < Main.PAGE_REQUESTS_CAPACITY; j++){
                if(currPageRequest.pageId == pageRequests.get(j).pageId){
                    currPageRequest.stepsToNextRequest = j - i;
                    break;
                }
                currPageRequest.stepsToNextRequest = 100000000;
            }
        }
    }



    int FIFO(){
        int pageFaults = 0;
        int currFrameId = -1;
        for(int i = 0; i < Main.PAGE_REQUESTS_CAPACITY; i++){
            Page pageRequest = pageRequests.get(i);
            int pageRequestIdxInFrames = ram.isPageInFrames(pageRequest);
            if(pageRequestIdxInFrames == -1){
                currFrameId = (currFrameId + 1) % Main.RAM_SIZE;
                pageFaults++;
                ram.frames.set(currFrameId,pageRequest);
            }
        }

        return pageFaults;
    }

    int ALRU(){
        int pageFaults = 0;
        int currFrameId = -1;
        for(int i = 0; i < Main.PAGE_REQUESTS_CAPACITY; i++){
            Page pageRequest = pageRequests.get(i);
            int pageRequestIdxInFrames = ram.isPageInFrames(pageRequest);
            if(pageRequestIdxInFrames == -1){
                currFrameId = (currFrameId + 1) % Main.RAM_SIZE;
                pageFaults++;
                Page pageToReplace = ram.frames.get(currFrameId);
                while (pageToReplace.bit != 0){
                    pageToReplace.bit--;
                    currFrameId = (currFrameId + 1) % Main.RAM_SIZE;
                    pageToReplace = ram.frames.get(currFrameId);
                }
                ram.frames.set(currFrameId,pageRequest);
            }
            else{
                ram.frames.get(pageRequestIdxInFrames).bit = 1;
            }
        }

        return pageFaults;
    }

    int LRU(){
        int pageFaults = 0;
        int freeFramesQuantity = Main.RAM_SIZE;
        ram.frames.clear();
        for(int i = 0; i < Main.PAGE_REQUESTS_CAPACITY; i++){
            Page pageRequest = pageRequests.get(i);
            int pageRequestIdxInFrames = ram.isPageInFrames(pageRequest);
            if(pageRequestIdxInFrames == -1){
                if(freeFramesQuantity == 0) ram.frames.remove(0);
                else freeFramesQuantity --;
                ram.frames.add(pageRequest);
                pageFaults++;
            }
            else{
                ram.frames.remove(pageRequestIdxInFrames);
                ram.frames.add(pageRequest);
            }
        }

        return pageFaults;
    }

    int OPT(){
        int pageFaults = 0;
        ram.frames.clear();

        setFutureSteps(-1);

        for(int i = 0; i < Main.PAGE_REQUESTS_CAPACITY; i++){
            Page pageToAdd = pageRequests.get(i);
            int isPageInFrames = ram.isPageInFrames(pageToAdd);
            if(isPageInFrames == -1){
                if(ram.frames.size() == Main.RAM_SIZE) {
                    int idxToReplace = findLongestStepIdx(i);
                    ram.frames.set(idxToReplace, pageToAdd);
                }
                else {
                    ram.frames.add(pageToAdd);
                }

                pageFaults++;
            }
            setFutureSteps(i);
        }
        return pageFaults;
    }

    int RAND(){
        int pageFaults = 0;
        ram.frames.clear();

        for(int i = 0; i < Main.PAGE_REQUESTS_CAPACITY; i++){
            Page pageToAdd = pageRequests.get(i);
            int isPageInFrames = ram.isPageInFrames(pageToAdd);
            if(isPageInFrames == -1){
                if(ram.frames.size() == Main.RAM_SIZE) {
                    int idxToReplace = randomNumberFromInterval(0, Main.RAM_SIZE - 1);
                    ram.frames.set(idxToReplace, pageToAdd);
                }
                else {
                    ram.frames.add(pageToAdd);
                }

                pageFaults++;
            }
        }
        return pageFaults;
    }
}
