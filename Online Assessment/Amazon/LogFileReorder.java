import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LogFileReorder {
    public List<String> reorder(int numOfOrder, List<String> orderList) {
        Collections.sort(orderList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String[] str1 = o1.split(" ", 2);
                String[] str2 = o2.split(" ", 2);
                boolean prime1 = Character.isLetter(str1[1].charAt(0));
                boolean prime2 = Character.isLetter(str2[1].charAt(0));
                
                if (prime1 && prime2) {
                    int cmp = str1[1].compareTo(str2[1]);
                    if (cmp != 0) {
                        return cmp;
                    }
                    else {
                        return str1[0].compareTo(str2[0]);
                    }
                }
                if (!prime1 && !prime2) {
                    return 0;
                }
                return (prime1) ? -1: 1;
            }
        });
        
        return orderList;
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("Zid 93 12", "fp kindle book", "IOa echo show", "17g 12 256", "abl kindle book", "125 echo dot second generation");
        LogFileReorder test = new LogFileReorder();
        System.out.println(test.reorder(6, list));
    }
}
