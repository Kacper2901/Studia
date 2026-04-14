import dsaa.lab05.Document;

void main(){
    Document doc = new Document("Doc", new Scanner(System.in));

    int[] weights = {20, 7, 10, 3, 8, 4, 1};
    doc.selectSort(weights);
}
