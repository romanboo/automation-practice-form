package tests;

public class Homework_5 {

    public static void main(String args[]) {
        int a = 10;
        int b = 20;
        int c = 25;
        int d = 25;
        System.out.println("a + b = " + (a + b));
        System.out.println("a - b = " + (a - b));
        System.out.println("a * b = " + (a * b));
        System.out.println("b / a = " + (b / a));
        System.out.println("b % a = " + (b % a));
        System.out.println("c % a = " + (c % a));
        System.out.println("a++   = " +  (a++));
        System.out.println("b--   = " +  (a--));
        System.out.println("d++   = " +  (d++));
        System.out.println("++d   = " +  (++d));
        System.out.println("a == b = " + (a == b) );
        System.out.println("a != b = " + (a != b) );
        System.out.println("a > b = " + (a > b) );
        System.out.println("a < b = " + (a < b) );
        System.out.println("b >= a = " + (b >= a) );
        System.out.println("b <= a = " + (b <= a) );






        a = 60;	/* 60 = 0011 1100 */
        b = 13;	/* 13 = 0000 1101 */

        c = a & b;       /* 12 = 0000 1100 */
        System.out.println("a & b = " + c );

        c = a | b;       /* 61 = 0011 1101 */
        System.out.println("a | b = " + c );

        c = a ^ b;       /* 49 = 0011 0001 */
        System.out.println("a ^ b = " + c );

        c = ~a;          /*-61 = 1100 0011 */
        // a = 60
        // 60 -> 0011 1100
        // -60 -> НЕ 60
        // 1100 0011

        // a = 61
        // 61 -> 0011 1101
        // 1100 0010
        System.out.println("~a = " + c );

        c = a << 2;     /* 240 = 1111 0000 */
        System.out.println("a << 2 = " + c );

        c = a >> 2;     /* 215 = 1111 */
        System.out.println("a >> 2  = " + c );

        c = a >>> 2;     /* 215 = 0000 1111 */
        System.out.println("a >>> 2 = " + c );





        boolean e = true;
        boolean f = false;

        System.out.println("e && f = " + (e&&f));

        System.out.println("e || f = " + (e||f) );

        System.out.println("!(e && f) = " + !(e && f));





        a = 10;
        b = 20;
        c = 0;

        c = a + b;
        System.out.println("c = a + b = " + c );

        c += a ;
        System.out.println("c += a  = " + c );

        c -= a ;
        System.out.println("c -= a = " + c );

        c *= a ;
        System.out.println("c *= a = " + c );

        a = 10;
        c = 15;
        c /= a ;
        System.out.println("c /= a = " + c );

        a = 10;
        c = 15;
        c %= a ;
        System.out.println("c %= a  = " + c );

        c <<= 2 ;
        System.out.println("c <<= 2 = " + c );

        c >>= 2 ;
        System.out.println("c >>= 2 = " + c );

        c >>= 2 ;
        System.out.println("c >>= a = " + c );

        c &= a ;
        System.out.println("c &= 2  = " + c );

        c ^= a ;
        System.out.println("c ^= a   = " + c );

        c |= a ;
        System.out.println("c |= a   = " + c );








        a = 10;
        b = (a == 1) ? 20 : 30;
        System.out.println( "Значение b: " +  b );

        b = (a == 10) ? 20 : 30;
        System.out.println( "Значение b: " + b );






        String name = "Олег";
        // Следующее вернётся верно, поскольку тип String
        boolean result = name instanceof String;
        System.out.println( result );





        a = 1;
        double g = 1.3;
        System.out.println(a + g);



        int count = 0;
        for (int i = 0; i <= 10000000; i++) {
            count += 1000000000;
        }
        System.out.println(count);


        int maxInt = Integer.MAX_VALUE;
        System.out.println(maxInt + 1);


        int minInt = Integer.MIN_VALUE;
        System.out.println(minInt - 1);


    }





} 