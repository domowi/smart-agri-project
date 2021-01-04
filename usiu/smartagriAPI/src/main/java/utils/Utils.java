package utils;

import java.util.List;

public class Utils {

    public static<T> void reverseList(List<T> list)
    {
        // base case: list is empty or only one element is left
        if (list == null || list.size() <= 1)
            return;

        // remove first element
        T value = list.remove(0);

        // recur for remaining items
        reverseList(list);

        // insert the top element back after recusing for remaining items
        list.add(value);
    }

}
