import java.util.ArrayList;
import java.util.List;

public class Friuts {
    public static void main(String[] args) {
        List<List<String>> codeList = new ArrayList<List<String>>();
        List<String> code = new ArrayList<String>();
        code.add("apple");
        code.add("apple");
        codeList.add(code);
        code = new ArrayList<String>();
        code.add("anything");
        code.add("banana");
        code.add("orange");
        codeList.add(code);
    
        List<String> shoppingCart = new ArrayList<String>();
        shoppingCart.add("orange");
        shoppingCart.add("apple");
        shoppingCart.add("apple");
        shoppingCart.add("orange");
        shoppingCart.add("banana");
        shoppingCart.add("orange");
    
        Friuts ft = new Friuts();
        System.out.println(ft.check(codeList, shoppingCart));
    
    }
    
    public int check(List<List<String>> codeList, List<String> scList) {
        // covert codeList to arraylist
        List<String> xmList = new ArrayList<String>();
        for (List<String> eh : codeList) {
            for (String st : eh) {
                xmList.add(st);
            }
        }
        System.out.println(xmList);
        System.out.println(scList);
        if (xmList.size() > scList.size()) return 0;
        int j = 0;
        for (int i = 0; i <= scList.size() - xmList.size(); i++) {
            while (j < xmList.size() && (xmList.get(j).equals(scList.get(i+j)) ||
                                             xmList.get(j).equals("anything"))) {
                j++;
            }
            if (j == xmList.size()) {
                return 1;
            }
            j = 0;
        }
    
        return 0;
    }
}