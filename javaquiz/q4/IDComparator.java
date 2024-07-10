import java.util.Comparator;

public class IDComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        int idFirst = Integer.parseInt(o1.split("\t")[0].trim());
        int idSecond = Integer.parseInt(o2.split("\t")[0].trim());
        return Integer.compare(idFirst,idSecond);
    }
}
