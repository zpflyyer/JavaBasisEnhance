package com.spring.ehancement;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import java.util.Properties;

public class JavaMailTest {

    public static void main(String[] args) throws Exception {
        // 准备连接服务器的会话信息
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imap");
        props.setProperty("mail.imap.host", "imap.139.com");

        // 1.创建Session实例对象
        Session session = Session.getDefaultInstance(props);

        // 2.创建IMAP协议的Store对象
        Store store = session.getStore("imap");

        // 3.连接邮件服务器
        store.connect("pengfei-zhao@139.com", "@YDmy$2020");

        // 4.获得收件箱
        Folder folder = store.getFolder("INBOX");

        // 5.以读写模式打开收件箱
        folder.open(Folder.READ_WRITE);

        // 6.获得收件箱的邮件列表
        Message[] messages = folder.getMessages();

        // 解析邮件
        for (Message message : messages) {
            if (message.getSubject() != null && message.getSubject().contains("密码") && message.getContent() != null) {
                System.out.println(message.getSubject());
                System.out.println(message.getContent().toString());
                break;
            }
        }

        System.out.println("finish");

        // 关闭资源
        folder.close(false);
        store.close();
    }
}