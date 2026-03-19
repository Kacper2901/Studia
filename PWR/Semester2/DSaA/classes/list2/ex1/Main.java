void main(){
    ArrayList<Integer> arr = new ArrayList<>(5);
    arr.add(1);
    System.out.println(arr.toString());
    arr.add(2);
    System.out.println(arr.toString());
    arr.add(3);
    System.out.println(arr.toString());
    arr.add(4);
    System.out.println(arr.toString());
    arr.add(5);
    System.out.println(arr.toString());
    arr.add(6);
    System.out.println(arr.toString());



    ListIterator<Integer> it = arr.listIterator();
    System.out.println();
    System.out.println(it.next());
    System.out.println(it.next());
    System.out.println(arr.toString());
    it.remove();
    System.out.println(arr.toString());
    System.out.println(it.next());
    it.add(21);
    System.out.println(arr.toString());
    System.out.println(it.next());
    it.add(123);
    System.out.println(arr.toString());
    it.add(123123);
    System.out.println(arr.toString());
}

