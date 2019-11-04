package com.ubs.sample;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringAccumulator {
    public static void main(String[] args) {
        StringAccumulator add = new StringAccumulator();
        String str = "//$!\n1003$!1$!2";
//        String str = "//****";
        try {
            System.out.println(add.processString(str));
        } catch (NegativeInputException e) {
            System.out.println(e.getMessage());
        }
    }

    public int processString(String numbers) throws NegativeInputException {
        String numberString = removeAllTabsAndNewLines(numbers);
        String customDelimeter = getCustomDelimeter(numberString);
        String delimeter = ",";
        if (!customDelimeter.isEmpty()) {
            delimeter = customDelimeter;
            numberString = extractNumberString(numberString);
        }
        List<Integer> numbersList = getAllNumbers(numberString, delimeter);
        if (numbersList.size() == 0) {
            return 0;
        } else {
            List<Integer> negativeNumbers = numbersList.stream().filter((it) -> it < 0).collect(Collectors.toList());
            if (negativeNumbers.size() > 0) {
                String negNumbers = negativeNumbers.stream().map((it) -> it.toString()).collect(Collectors.joining(","));
                throw new NegativeInputException("Cant contain negative integets : " + negNumbers);
            }
            int sum = numbersList.stream().reduce((total, number) -> number + total).get();
            return sum;
        }
    }

    public List<Integer>  getAllNumbers(String numberString, String delimeter) {
        return Arrays.asList(numberString.split(wrapDelimeter(delimeter))).stream().filter((it) -> StringUtils.isNotEmpty(it)).map((it) -> Integer.parseInt(it.trim())).filter((it) -> it <= 1000).collect(Collectors.toList());

    }

    public String getCustomDelimeter(String s) {
        String delimeter = "";
        if (s.length() > 2) {
            if (s.substring(0, 2).equals("//")) {
                for (int i = 2; i < s.length(); i++) {
                    String val = s.charAt(i) + "";
                    if (!StringUtils.isNumeric(val)) {
                        delimeter = delimeter + val;
                    } else {
                        return delimeter;
                    }
                }
            }
        }
        return delimeter;
    }

    public String wrapDelimeter(String delimeter) {
        if (delimeter.split("\\|").length > 1) {
            return "["+delimeter.replaceAll("|","")+"]";
        } else {
            return Pattern.quote(delimeter);
        }
    }

    public String extractNumberString(String str) {
        int l = str.length();
        for (int i = 0; i < l; i++) {
            String num = str.charAt(0) + "";
            if (!StringUtils.isNumeric(num)) {
                str = str.substring(1);
            } else {
                return str;
            }
        }
        return str;
    }

    public String removeAllTabsAndNewLines(String str) {
        return str.replaceAll("\n", "").replaceAll("\t", "");
    }
}
