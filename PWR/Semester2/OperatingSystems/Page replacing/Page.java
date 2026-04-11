public class Page{
    int pageId;
    int bit;
    int stepsToNextRequest;

    Page(int pageId){
        this.pageId = pageId;
        this.bit = 0;
        stepsToNextRequest = Main.PAGE_REQUESTS_CAPACITY + 100;
    }
}
