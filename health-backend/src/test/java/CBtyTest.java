import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @description:
 * @author: yd93
 * @createTime: 2019-10-20 19:32:25
 **/
public class CBtyTest {
    @Test
    public void test(){
        //测试加密的密码
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode("test");
        System.out.println(encode);
        //$2a$10$u/BcsUUqZNWUxdmDhbnoeeobJy6IBsL1Gn/S0dMxI2RbSgnMKJ.4a 1234
        //$2a$10$3xW2nBjwBM3rx1LoYprVsemNri5bvxeOd/QfmO7UDFQhW2HRHLi.C 1234
        //$2a$10$zYJRscVUgHX1wqwu90WereuTmIg6h/JGirGG4SWBsZ60wVPCgtF8W 1234
        boolean matches = encoder.matches("1234", "$2a$10$zYJRscVUgHX1wqwu90WereuTmIg6h/JGirGG4SWBsZ60wVPCgtF8W");
        System.out.println(matches);
    }
}
