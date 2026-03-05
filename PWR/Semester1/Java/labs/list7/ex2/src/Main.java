
void main() {
    RandomNumberFromSets rand = new RandomNumberFromSets();
    for(int i = 0; i < 100; i++){
        System.out.print(rand.nextDouble() + ", ");
    }
    System.out.print("\n\n");

    for(int i = 0; i < 100; i++){
        System.out.print(rand.nextInt() + ", ");
    }
    System.out.print("\n\n");

    for(int i = 0; i < 100; i++){
        System.out.print(rand.nextLong() + ", ");
    }
    System.out.print("\n\n");

    for(int i = 0; i < 100; i++){
        System.out.print(rand.nextFloat() + ", ");
    }
    System.out.print("\n\n");

}
