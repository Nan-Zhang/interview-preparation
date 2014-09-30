/*******************************************************************************
 * Copyright (c) 2014 Nan Zhang.
 * 
 *        Filename:   ExcellColConversion.java
 *         Created:   Sep 27, 2014
 *          Author:   Nan Zhang
 *    Organization:   https://github.com/Nan-Zhang
 *            Note:   A certain spreadsheet program labels the columns of a spreadsheet using letters.
 *                    Column 0 is labeled as "A", column 1 as "B", ..., column 25 as "Z".
 *                    When the number of columns is greater than 25, another letter is used.
 *                    For example, column 26 is "AA", column 27 is "AB" and column 51 is "AZ".
 *                    It follows that column 52 would be "BA" and so on.
 *                    Similarly, when column "ZZ" is reached, the next column would be "AAA", then "AAB" and so on.
 *                    Given the number, output the character sequence.
 *                    Ref: https://github.com/Linzertorte/POJ/blob/master/2273AnExcellentProblem.cpp
 * All rights reserved.
 ******************************************************************************/
package bloomberg;

import java.util.ArrayList;

public class ExcellColConversion {
    public static String convert(int input) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        do {
            list.add(input % 26);
            input = input / 26;
        } while (input > 0);
        int borrow = 0;
        for (int i = 1; i < list.size(); i++) {
            if ((list.get(i) + borrow) <= 0) {
                list.set(i, (list.get(i) - 1 + borrow) % 26 + 26);
                borrow = -1;
            } else {
                list.set(i, list.get(i) - 1 + borrow);
                borrow = 0;
            }
        }
        if (list.size() > 1 && list.get(list.size() - 1) == 25) {//original highest pos start from B
            list.remove(list.size() - 1);
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            ans.insert(0, (char) ('A' + list.get(i)));
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 27 * 26 * 27; i++) {
            System.out.println(convert(i));
        }
    }
}
