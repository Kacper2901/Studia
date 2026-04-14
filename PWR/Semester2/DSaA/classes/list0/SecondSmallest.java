public class SecondSmallest<T extends Comparable<T>> {

    public T getSecondSmallest(T[] values) throws NoAnswerException {
        if (values.length < 2)
            throw new NoAnswerException();

        T smallestValue = values[0];
        T secondSmallestValue = null;

        for (T currValue : values) {
            if (currValue.compareTo(smallestValue) < 0) {
                secondSmallestValue = smallestValue;
                smallestValue = currValue;
            } else if (currValue.compareTo(smallestValue) > 0) {
                if (secondSmallestValue == null || currValue.compareTo(secondSmallestValue) < 0) {
                    secondSmallestValue = currValue;
                }
            }
        }
        if (secondSmallestValue == null) throw new NoAnswerException();
        return secondSmallestValue;
    }


}