import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

    }

    public static void writeToBinary(String f, ArrayList<HashMap<String, Object>> l) throws IOException{
        var fos = new FileOutputStream(f);   //Creating the stream to read the file f
        var dos = new DataOutputStream(new BufferedOutputStream(fos));
        Object[] headers = l.get(0).keySet().toArray(); //Get the headers from the file
        for (int i = 0; i < headers.length; i++) {
            dos.writeUTF(headers[i].toString());  //Write the files to binary
        }
        for (int i = 0; i < l.size(); i++){
            dos.writeDouble(Double.valueOf(l.get(i).get(headers[0]).toString()));
            dos.writeDouble(Double.valueOf(l.get(i).get(headers[2]).toString()));
            dos.writeInt(Integer.parseInt(l.get(i).get(headers[1]).toString()));

            //Write each row of the file to binary, order switched to match the switched order created by keySet()
        }
        dos.close();
    }

    public static ArrayList<HashMap<String, Object>> readFromBinary(String f) throws IOException{
        ArrayList<HashMap<String, Object>> list = new ArrayList<>();
        var fis = new FileInputStream(f);
        var dis = new DataInputStream(new BufferedInputStream(fis)); //Create stream to read the binary
        String[] headers = new String[3];
        headers[0] = dis.readUTF();
        headers[2] = dis.readUTF();
        headers[1] = dis.readUTF();  //Read the headers in from binary, put them in array out of order to fix wrong order caused by keySet()
        try{
            while(true) {
                HashMap<String,Object> map = new HashMap<String,Object>();
                map.put(headers[0], dis.readDouble());
                map.put(headers[1], dis.readDouble());
                map.put(headers[2], dis.readInt());
                list.add(map);
                //Add each row back into a map, with their respective headers
            }
        }catch(EOFException e){
            //Catches error when we hit end of file, we don't do anything we just want to stop here
        }
        return list;
    }

    public static void serializableWrite( ArrayList<HashMap<String, Object>> list) throws IOException {
        FileOutputStream f = new FileOutputStream("output2.csv");
        ObjectOutput s = new ObjectOutputStream(f);
        s.writeObject(list);  //Use serializable method to write entire file at once
        s.flush();
    }

    public static  ArrayList<HashMap<String, Object>>  serializableRead( String blah) throws IOException, ClassNotFoundException {
        FileInputStream f = new FileInputStream(blah);
        ObjectInput s = new ObjectInputStream(f);
        ArrayList<HashMap<String, Object>> list = (ArrayList<HashMap<String, Object>>) s.readObject();
        //read back the entire binary file and cast the container back to its original type
        s.close();
        return list;
    }
}
