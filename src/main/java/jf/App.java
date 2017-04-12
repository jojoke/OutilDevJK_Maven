package jf;

import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;


/**
 * Hello world!
 *
 */

public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Salut la compagnie" );
        System.out.println(max(4,5));
        int i,x,nb,monmax = 0 ;
        try{
        	CSVReader reader = new CSVReader(new FileReader("data.csv")) ;
        	 CSVWriter writer = new CSVWriter(new FileWriter("data-filtered.csv"), ';', (char)0);
            List<String[]> myEntries = reader.readAll() ;
            
            for(i = 0; i<myEntries.size(); i++)
            {
            	nb = Integer.parseInt(myEntries.get(i)[0]);
            	List<String> list = Arrays.asList(myEntries.get(i));
            	Vector<String>out=new Vector<String>();
                Predicate predicat = new Predicate(){
                    public boolean evaluate(Object x)
                    {
                        try
                        {
                            return Integer.parseInt((String) x) < 50;
                        }
                        catch (java.lang.NumberFormatException n)
                        {
                            return false;
                        }
                    }

				
                };
                
                CollectionUtils.select(list,  predicat, out);
                
            	System.out.println("OUT:"+out);
                for (int j=0; j < out.size(); j++) {
                    String[] tmp=out.toArray(new String[0]);
                    System.out.println("TMP:"+tmp[j]);
                    if(j==out.size()-1)
                    {
                        writer.writeNext(tmp);
                    }

                    String y = out.get(j).toString();
                    x= Integer.parseInt(y);
                    monmax=max(x,monmax);
                }
            }
            System.out.println(monmax);
        }catch(FileNotFoundException e)
        {
        	e.printStackTrace();
        }
        catch(IOException e)
        {
        	e.printStackTrace();
        }
        System.out.println(monmax);
        
        
    }
    
    public static int max (int a, int b)
    {
    	return a>b ?a :b;// Le stagiaire est passe par la
    }
}


