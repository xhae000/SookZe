import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Yogiyo {
    /**
     *  1. 주어지는 URL 에서 최상위 도메인을 리턴하는 프로그램을 작성하세요.
     */
    static String getTLD(String url){
        Queue<String> queue = new LinkedList<>(Arrays.asList(url.split("\\.")));
        queue.poll();
        queue.poll();

        return queue.poll().split("/")[0];
    }

    /**
     *  2. 1 부터 N 사이의 숫자를 프린트하는 프로그램을 작성하는데 3의
     *  배수이면 "RGP"를, 5의 배수이면 "Korea"를, 둘 모두의
     *  배수이면 "RGPKorea" 를 프린트 하세요
     */
    static void printNumbers(int N){
        for(int i=1 ; i<=N ; i++){
            if(i%15 == 0) System.out.println("RGPKorea");
            else if(i%5 == 0) System.out.println("Korea");
            else if(i%3 == 0) System.out.println("RGP");
            else System.out.println(i);
        }
    }

    /**
     * 3. 달팽이 출력
     */
    static void dalpeng(int length){
        int square = length*length;
        int[][] matrix = new int[length][length];
        int[][] direction = {
                {0, 1},   // east
                {1, 0},   // south
                {0, -1},  // west
                {-1, 0}   // north
        };

        int directionArg = 0; // default direction : east
        int x = 0, y = 0; // start point : (0, 0)

        for(int i=1 ; i<=square ; i++){
            matrix[x][y] = i;

            int dx = direction[directionArg][0];
            int dy = direction[directionArg][1];

            // 범위를 초과했거나, 이미 방문했다면 방향 전환
            if(x+dx >= length || x+dx < 0 || y+dy >= length || y+dy < 0 ||
                    matrix[x+dx][y+dy] > 0) {
                directionArg = (directionArg + 1) % 4;
                dx = direction[directionArg][0];
                dy = direction[directionArg][1];
            }

            x += dx;
            y += dy;
        }

        for(int i=0 ; i<length ; i++){
            for(int j=0 ; j<length ; j++)
                System.out.printf("%d\t", matrix[i][j]);
            System.out.println();
        }
    }

    /**
     * 4. 입력한 문자열이 대문자는 소문자로 소문자는 대문자로 바뀌어 출력되도록 하는
     * 프로그램을 작성하세요
     */
    static void strChange(String str){
        for (char c : str.toCharArray()){
            if(c>='A' && c<='Z')  // 대문자
                System.out.print((char) ((int) c + ('a' - 'A')));
            else if(c>='a' && c<='z') // 소문자
                System.out.print((char) ((int) c - ('a' - 'A')));
            else // 알파벳 아님
                System.out.print(c);
        }
        System.out.println();
    }

    /**
     * 5. 입력한 단어가 Palindrome 인지 아닌지를 검사하는 프로그램을 작성하세요.
     */
    static void checkPalindrome(String str){
        int len = str.length();

        int pushStart = 0;
        int pushEnd = len/2-1;
        int popStart = len%2 == 0 ? pushEnd + 1 : pushEnd + 2;
        int popEnd = len-1;

        Stack<Character> stk = new Stack<>();

        for(int i=pushStart ; i<=pushEnd ; i++){
            stk.push(str.charAt(i));
        }
        for(int i=popStart ; i<=popEnd ; i++){
            if(str.charAt(i) == stk.peek()) stk.pop();
            else break;
        }

        if(stk.size() == 0) System.out.println("Palindrome");
        else System.out.println("Not Palindrome");
    }

    // test
    public static void main(String[] args) {
        // # 1
        String arg1 = "http://www.bdtong.co.kr/something";
        System.out.println(getTLD(arg1)); //ans1

        // # 2
        int arg2 = 16;
        printNumbers(arg2); // ans2

        // #3
        int arg3 = 10;
        dalpeng(arg3); // ans3

        // #4
        String arg4 = "2018RgpKorea!";
        strChange(arg4); // ans4

        // #5
        String arg5 = "madam";
        checkPalindrome(arg5); // ans5
    }
}
