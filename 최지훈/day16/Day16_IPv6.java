import java.util.*;
import java.io.*;

public class Main {
    private static String[] ip;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ip = br.readLine().split(":", -1);
        int numberCnt = 0; // 생략되지 않은 그룹 수
        for(int i=0; i<ip.length; i++) if(!ip[i].isEmpty()) numberCnt++;

        StringBuilder ipv6 = new StringBuilder();
        boolean check = false;
        int cnt = 0;
        for(int i=0; i<ip.length; i++) {
            if(ip[i].isEmpty()) {
                if(!check){
                    check = true;
                    for(int j=0; j<8-numberCnt; j++) {
                        ipv6.append("0000");
                        cnt++;
                        if(cnt < 8) ipv6.append(":");
                    }
                }
                // 그 다음 빈 ip는 무시
            } else {
                if(ip[i].length() < 4) {
                    for(int j=0; j<4-ip[i].length(); j++) ipv6.append("0");
                }
                ipv6.append(ip[i]);
                cnt++;
                if(cnt < 8) ipv6.append(":");
            }
        }

        System.out.print(ipv6);
    }
}