class SantaClaus{
    Gift[] bag;
    private int giftsQuantity;

    SantaClaus(){
        bag = new Gift[100];
        giftsQuantity = 0;
    }

    void addToBag(Gift... gifts){
        for(Gift g: gifts){
            bag[giftsQuantity] = g;
            giftsQuantity++;
        }

    }

    void giveGifts(){
        for(int i = 0; i < giftsQuantity; i++){
            bag[i].unwrap();
        }
    }

    int getGiftsQuantity(){
        return giftsQuantity;
    }
}