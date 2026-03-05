
class Main{
    public static void main(String[] args){
        RandomGeneratorFromSets random = new RandomGeneratorFromSets();
        for(int i = 0; i< 30; i++){
            int randomNumber = random.nextInt();
            System.out.print(randomNumber + ", ");
        }
    }
}