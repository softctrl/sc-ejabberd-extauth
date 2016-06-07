import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
/*
The MIT License (MIT)

Copyright (c) 2015 Carlos Timoshenko Rodrigues Lopes
http://www.0x09.com.br

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

/**
* Just a single class to use as extauth script for Ejabberd.
* 
* @author carlostimoshenkorodrigueslopes@gmail.com
*/
public class SCMain {
    
    private static final String AUTH = "auth";
    private static final String IS_USER = "isuser";
    private static final String SET_PASS = "setpass";
    
    private static final String SPLIT = ":";
    
    private static final String TRUE = "1";
    private static final String FALSE = "0";
    
    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    private static final OutputStreamWriter WRITER = new OutputStreamWriter(System.out);

    /**
     * @return
     * @throws IOException 
     * 
     */
    public synchronized static final String[] fromEjabberd() throws IOException {
        return READER.readLine().split(SPLIT);
    }

    /**
     * 
     * @param result
     * @throws IOException 
     */
    public synchronized static final void toEjabberd(boolean result) throws IOException {
        WRITER.write((result ? TRUE : FALSE));
        WRITER.flush();
    }

    /**
     * 
     * @param username
     * @param server
     * @param password
     * @return
     */
    public synchronized static final boolean auth(final String username, final String server, final String password) {
        System.out.println(String.format("auth()= username:%s server:%s password:%s", username, server, password));
        // TODO: you need to implement here.
        return true;
    }

    /**
     * 
     * @param username
     * @param server
     * @return
     */
    public synchronized static final boolean isUser(final String username, final String server) {
        System.out.println(String.format("isUser()= username:%s server:%s", username, server));
        // TODO: you need to implement here.
        return true;
    }

    /**
     * 
     * @param username
     * @param server
     * @param password
     * @return
     */
    public synchronized static final boolean setPass(final String username, final String server,
            final String password) {
        System.out.println(String.format("setPass()= username:%s server:%s password:%s", username, server, password));
        // TODO: you need to implement here.
        return true;
    }

    public static void run(String[] args) throws IOException {

        boolean result = false;
        String[] command;
        while (true) {
            result = false;
            command = fromEjabberd();
            switch (command[0]) {
            case AUTH:
                result = auth(command[1], command[2], command[3]);
                break;
            case IS_USER:
                result = isUser(command[1], command[2]);
                break;
            case SET_PASS:
                result = setPass(command[1], command[2], command[3]);
                break;
            default:
                break;
            }
            toEjabberd(result);
        }

    }

    /**
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        try {
            run(args);
        } finally {
            WRITER.close();
            READER.close();            
        }

    }

}
