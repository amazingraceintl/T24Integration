package com.jethro.T24Integration;

import com.jbase.jremote.JDynArray;
import com.jbase.jremote.JRemoteException;
import com.jbase.jremote.JSubroutineParameters;

public class Doit {

    public String Doit2(String a, String b, int c, String d ){
        String res_msg="";
        JSubroutineParameters routine = new JSubroutineParameters();
        routine.add(new JDynArray(res_msg));
        routine.add(new JDynArray(a));
        routine.add(new JDynArray(b));
        routine.add(new JDynArray(Integer.toString(c)));
        routine.add(new JDynArray(d));

        try {
            JSubroutineParameters FTransfer = T24Connection.tafc_connection().call("JSL.FT.OFS", routine);
            String returnValue = FTransfer.toString(); //Converting Return Value to String
            System.out.println(returnValue);


            //Testing conditions for Return Value
            if (Character.toString(returnValue.charAt(18)).equals("1")) {
                System.out.println("Success !!!"); } else
            {System.out.println("Transaction Failed");
                System.out.println(); }
        } catch (JRemoteException ex){ex.printStackTrace(); }

        return res_msg;
    }

}
