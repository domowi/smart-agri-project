package net.codejava.excel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ListTest {

    public static void main(String[] args){

        List<Double> minMaxValList = Arrays.asList(9500.0, 6300.0, 5400.0, 11250.0, 5000.0, 9000.0, 10800.0);
        System.out.print(" :>>>>>> minMaxValList 1111111 .......: " + minMaxValList.toString() + " ::: \n");

        List<Double> sortedList = new ArrayList<>();

        // sort the list and extract the minimum and maximum values
        sortedList = minMaxValList.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
        System.out.print(" :>>>>>> minMaxValList 2222222222 .......: " + sortedList.toString() + " ::: \n");

        Double maxVal = Double.valueOf(sortedList.get(sortedList.size() - 1));
        Double minVal = Double.valueOf(sortedList.get(0));
        System.out.println("minVal = " + minVal + " :: maxVal = " + maxVal + "\n ");


    }

}
