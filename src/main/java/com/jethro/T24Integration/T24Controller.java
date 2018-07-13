package com.jethro.T24Integration;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.jbase.jremote.JRemoteException;
import com.jbase.jremote.JDynArray;
import com.jbase.jremote.JRemoteException;
import com.jbase.jremote.JSubroutineParameters;

@RestController
public class T24Controller {


    @RequestMapping("/fund_transfer")
    public String FTransfer (@RequestParam("dr_acct") String dr_acct, @RequestParam("cr_acct") String cr_acct, @RequestParam("amt") int amt,@RequestParam("currency") String currency)
            throws IOException, JRemoteException{
String res_msg="";
        JSubroutineParameters routine = new JSubroutineParameters();
        routine.add(new JDynArray(res_msg));
        routine.add(new JDynArray(dr_acct));
        routine.add(new JDynArray(cr_acct));
        routine.add(new JDynArray(currency));
        routine.add(new JDynArray(Integer.toString(amt)));
        String returnValue="";

        try {
            JSubroutineParameters FTransfer = T24Connection.tafc_connection().call("JSL.FT.OFS", routine);
             returnValue = FTransfer.toString(); //Converting Return Value to String
            //System.out.println(returnValue);
            //Testing conditions for Return Value
            if (Character.toString(returnValue.charAt(18)).equals("1")) {
                String reg=returnValue;

                System.out.println("Success !!!");
               // System.out.println("bunmi      ="+returnValue);
            } else
                    {System.out.println("Transaction Failed");
             //   System.out.println("bunmi      ="+returnValue);
                    }
        } catch (JRemoteException ex){ex.printStackTrace(); }

        return returnValue;

    }

    @RequestMapping("/acct_statement")
    public  String Statement(@RequestParam("acct_no") String acct_no, @RequestParam("date") String date)
            throws IOException,  JRemoteException{

        String res_msg="";
        JSubroutineParameters routine = new JSubroutineParameters();
        routine.add(new JDynArray(res_msg));
        routine.add(new JDynArray(acct_no));
        routine.add(new JDynArray(date));
        String returnValue="";
        try {
            JSubroutineParameters AcctStatement = T24Connection.tafc_connection().call("JSL.ACCT.STMT", routine);
             returnValue = AcctStatement.toString(); //Converting Return Value to String
            System.out.println(returnValue);


            //Testing conditions for Return Value
            if (Character.toString(returnValue.charAt(18)).equals("1")) {
                System.out.println("Success !!!");
            } else {
                System.out.println("Transaction Failed");
                System.out.println();
            }
        } catch (JRemoteException ex){

            ex.printStackTrace();
        }



        return  returnValue;
    }


    @RequestMapping("/aa_loans")
    public  String Statement(@RequestParam("arrng") String arrng, @RequestParam("actvty")
            String actvty,@RequestParam("date") String date,@RequestParam("cus")
            String cus,@RequestParam("product") String product,@RequestParam("currency") String currency)
            throws IOException, JRemoteException{

        String res_msg="";
        JSubroutineParameters routine = new JSubroutineParameters();
        routine.add(new JDynArray(res_msg));
        routine.add(new JDynArray(arrng));
        routine.add(new JDynArray(actvty));
        routine.add(new JDynArray(date));
        routine.add(new JDynArray(cus));
        routine.add(new JDynArray(product));
        routine.add(new JDynArray(currency));
        String returnValue="";
        try {
            JSubroutineParameters AALoans = T24Connection.tafc_connection().call("JSL.AALOANS", routine);
             returnValue = AALoans.toString(); //Converting Return Value to String
            System.out.println(returnValue);


            //Testing conditions for Return Value
            if (Character.toString(returnValue.charAt(18)).equals("1")) {
                System.out.println("Success !!!");
            } else {
                System.out.println("Transaction Failed");
                System.out.println();
            }
        } catch (JRemoteException ex){

            ex.printStackTrace();
        }



        return  returnValue;
    }
}
