
void main() {
    RandomGeneratorFromComplexSets rand = new RandomGeneratorFromComplexSets();
    rand.setComplexSets(new TPair(1,5), new TPair(100,105), new TPair(200,205), new TPair(300,305));

    for(int i = 0; i<100; i++){
        System.out.print(rand.nextFloat() + ", ");
    }
    System.out.print("\n\n");

    for(int i = 0; i<100; i++){
        System.out.print(rand.nextDouble() + ", ");
    }
    System.out.print("\n\n");

    for(int i = 0; i<100; i++){
        System.out.print(rand.nextLong() + ", ");
    }
    System.out.print("\n\n");

    for(int i = 0; i<100; i++){
        System.out.print(rand.nextInt() + ", ");
    }
    System.out.print("\n\n");
}
