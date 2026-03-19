void main(){
    OneWayLinkedListWithHead<Integer> arr = new OneWayLinkedListWithHead<>();
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

    Iterator<Integer> it = arr.iterator();
    System.out.println();

    System.out.println(it.next());
    System.out.println(it.next());

    System.out.println(arr.toString());
    it.remove();
    System.out.println(arr.toString());

    System.out.println(it.next());
    it.remove();
    System.out.println(arr.toString());

    System.out.println(it.next());
    System.out.println(it.next());

    it.remove();
    System.out.println(arr.toString());
}