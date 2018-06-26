package com.liqz.test;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class ReturnMapPut 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        Map map = new HashMap();
        Object put1 = map.put("a", 1);
        Object put2 = map.put("b", 2);
        Object put3 = map.put("b", 3);
        System.out.println(put1);
        System.out.println(put2);
        System.out.println(put3);
        int a = 2;
        System.out.println("a:" + (a = a << 8));
    }
    public interface name {
		void  a();
	}
}
