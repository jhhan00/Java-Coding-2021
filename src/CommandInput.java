import java.util.*;

public class CommandInput {

    public static final String program = "line";
    public static final List<String> rules = Arrays.asList(
//            "-s STRING",
            "-s STRINGS",
//            "-n NUMBER",
            "-n NUMBERS",
            "-e NULL"
    );
    public static final List<String> commands = Arrays.asList(
            "line -e -s iron -n 123",   // true
            "line -s iron -e -n 123",   // true
            "line -s iron -n 123 -e",   // true
            "line -n 123 lion -e",      // false
            "lien -s aaa -n -e",        // false
            "line -n 345 -e -s man pi", // true
            "line -e -n 345 4345 23423 -s man pi"  // true
    );

    public static void main(String[] args) {
        Map<String, String> rl = new HashMap<>();
        List<Boolean> answer = new ArrayList<>();

        for(String s: rules) {
            String[] tmp = s.split(" ");
            rl.put(tmp[0], tmp[1]);
        }

        for(String s: commands) {
            String[] spl = s.split(" ");
            String flag="", type="";
            boolean fail=false;
            for(int i=0; i<spl.length; i++) {
                if(i==0 && !spl[i].equals(program)) {
                    answer.add(false);
                    fail=true;
                    break;
                } else {
                    if(i==0) continue;
                    if(flag.equals("") || spl[i].charAt(0)=='-') { // flag
                        flag = spl[i];
                        type = rl.get(flag);
//                        System.out.println("cm = "+spl[i]+", flag="+flag+", type="+type);
                        if(type==null) {
                            answer.add(false);
                            fail=true;
                            break;
                        } else if(type.equals("NULL")) {
                            if(i != spl.length-1 && spl[i+1].charAt(0)!='-') {
                                answer.add(false);
                                fail=true;
                                break;
                            } else {
                                flag=type="";
                            }
                        }
                    } else {
                        if((type.equals("STRING") || type.equals("STRINGS")) && ('a' > spl[i].charAt(0) || spl[i].charAt(0) > 'z') && ('A' > spl[i].charAt(0) || spl[i].charAt(0) > 'Z')) {
                            answer.add(false);
                            fail=true;
                            break;
                        } else if((type.equals("NUMBER") || type.equals("NUMBERS")) && ('0' > spl[i].charAt(0) || spl[i].charAt(0) > '9')) {
                            answer.add(false);
                            fail=true;
                            break;
                        }

                        if(type.equals("STRING") || type.equals("NUMBER"))
                            flag=type="";
//                        System.out.println("flag="+flag+", type="+type);
                    }
                }
            }
//            System.out.println();
            if(!fail) answer.add(true);
        }

        for(boolean a: answer)
            System.out.println(a);
    }
}
