package com.yingwu.digital.service;

import com.yingwu.digital.base.DigitalException;

/**
 * @author Created by: zhangbingbing
 * @date 2018/9/25
 **/
public interface MailService {
    /**
     * 发送普通邮件
     * @param to 接收人
     * @param subject 邮件主题
     * @param content 邮件内容
     * @throws DigitalException
     */
    public void sendSimpleMail (String to, String subject, String content);

    /**
     * 发送HTML邮件
     * @param to 接收人
     * @param subject 邮件主题
     * @param content 邮件内容
     * @throws DigitalException
     */
    public void sendHtmlMail (String to, String subject, String content);

    /**
     * 发送带附件邮件
     * @param to 接收人
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param filePath 附件地址
     * @throws DigitalException
     */

    public void sendAttachmentsMail (String to, String subject, String content,String filePath);


}
