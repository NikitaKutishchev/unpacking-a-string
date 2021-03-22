package com.company;


public class Main
{
    public static void main(String[] args){

        String packedString = "2[x2[z]y]";

        System.out.println(getUnpackedString(packedString));
    }


    public static String getUnpackedString(String packedString){

        StringBuilder unpackedString = new StringBuilder();

        int multiplier = 0, degree = 0;     //multiplier of a string and digit of a number
        int beg = 0, end = 0;               //start and end of substring in []
        boolean flag = true;                //flag
        int j = 0;


        for (int i = 0; i < packedString.length(); ++i) {

            //read the multiplier and save it to a variable
            if (Character.isDigit(packedString.charAt(i))) {

                j = i;
                while (packedString.charAt(j) != '[' && flag) {
                    ++degree;
                    ++j;
                }
                flag = false;


                if (((int)packedString.charAt(i) - 48) == 0) {
                    --degree;
                    multiplier *= Math.pow(10, degree);
                }
                else {
                    --degree;
                    multiplier += ((int) packedString.charAt(i) - 48) * Math.pow(10, degree );
                }

                //select a substring and repeat it
                 if (packedString.charAt(i + 1) == '['){

                    beg = ++i;
                    end = beg;

                    while(packedString.charAt(i) != ']') {
                        if (Character.isDigit(packedString.charAt(i))) {

                            //call recursion for inner substrings in []
                            packedString = getUnpackedString(packedString.substring(beg));

                        }
                        else
                           i = ++end;
                    }

                     //add the unpacked substring
                    unpackedString.append(
                            packedString
                            .substring(beg, end)
                            .repeat(multiplier)
                            .replace("[","")
                    );


                    multiplier = 0; degree = 0;
                    flag = true;
                    j = 0;


                }
            }

            //add the symbol
            else unpackedString.append(packedString.charAt(i));

        }


        return unpackedString.toString();
    }

}




