package lombokAction;

import lombok.Cleanup;

import java.io.*;

/**
 * @Author : gavin
 * @Date : 2019-11-1915:38
 * @DESC : lombok注解@Cleanup使用案例
 *
 * @Cleanup注解能够自动调用流资源的close()方法,极大简化代码
 */
public class CleanupExample {

    public static void main(String[] args) throws IOException {

        @Cleanup InputStream in = new FileInputStream(args[0]);
        @Cleanup OutputStream out = new FileOutputStream(args[1]);

        byte[] b = new byte[1000];

        while(true){
            int r = in .read(b);
            if(r == -1) break;
            out.write(b,0,r);
        }

    }
}
