package cn.hu;

import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.hu.common.util.MD5Utils;

public class ApplicationTest {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${jasypt.encryptor.password}")
    private String encryptorPassword;

    @Value("${spring.datasource.druid.username}")
    private String datasourceUsername;

    @Value("${spring.datasource.druid.password}")
    private String datasourcePassword;

    public static void main(String[] args) {
    	String str = MD5Utils.encrypt("admin".toLowerCase(), "admin");
    	System.out.println(str);
    }
}
