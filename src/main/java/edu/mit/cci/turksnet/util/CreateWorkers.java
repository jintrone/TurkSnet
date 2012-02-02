package edu.mit.cci.turksnet.util;

/**
 * User: jintrone
 * Date: 2/1/12
 * Time: 2:14 PM
 */
public class CreateWorkers {

    public static void main(String[] args) {

        String gen = "INSERT into worker values (%d,NULL,\"\\\"\\\"\",\"{\\\"foo\\\":\\\"bar\\\"}\",\"1;2;3\",\"st%d\",1,NULL,NULL);";
        String siege = "http://cognosis.mit.edu:8084/turksnet/experiments/2/ping?workerId=%d";

        for (int i = 1;i<=201;i++) {

            System.out.println(String.format(gen,50+i,i));
        }

         for (int i = 1;i<=201;i++) {

            System.out.println(String.format(siege,50+i,i));
        }


    }
}
