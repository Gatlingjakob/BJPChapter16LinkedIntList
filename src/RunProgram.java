import java.util.Arrays;

/**
 * Created by Jakob on 08-09-2016.
 */
public class RunProgram {

    public static void main(String[] args) {


        LinkedIntList list = new LinkedIntList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);



        list.reverse();
        System.out.println(list);



    }


}
