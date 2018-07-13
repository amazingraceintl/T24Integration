package com.jethro.T24Integration;

        import com.jbase.jremote.DefaultJConnectionFactory;
        import com.jbase.jremote.JConnection;
        import com.jbase.jremote.JRemoteException;

public class T24Connection {
    private static JConnection connection;
    public static JConnection tafc_connection(){
        if (connection!=null)
        {
            return connection;
        } else
        {
            try{
                DefaultJConnectionFactory jbaseConn = new DefaultJConnectionFactory();
                jbaseConn.setHost("127.0.0.1");
                jbaseConn.setPort(20100);
                System.out.println("Connecting to T24...");
                connection = jbaseConn.getConnection();
                System.out.println("Connection to T24 Established");
            }catch (JRemoteException ex)
            {System.out.println(ex.getLocalizedMessage());}
            return connection;
        }
    }

}