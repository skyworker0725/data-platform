package tv.vego.dp.service.util;

import com.sun.mail.smtp.SMTPSendFailedException;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import tv.vego.dp.vo.BaseResultInfo;
import tv.vego.dp.vo.MailSendResultInfo;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

/**
 * JAVA邮件发送
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2016/3/11 10:01
 */
@Component
public class MailSenderService implements InitializingBean
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MailSenderService.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value("${mail.send.from}")
    private String mailFrom;

    private static JavaMailSender _mailSender;

    private static String _mailFrom;

    @SuppressWarnings("unchecked")
    public static MailSendResultInfo sendMail(String subject, String content, String[] receivers)
    {
        LOGGER.info("开始发送邮件【{}】至【{}】。", subject, receivers);

        MailSendResultInfo resultInfo = new MailSendResultInfo();
        try
        {
            send(subject, content, receivers);

            LOGGER.info("完成发送邮件【{}】至【{}】。", subject, receivers);
        }
        catch (MailSendException e)
        {
            LOGGER.error("发送邮件异常", e);

            resultInfo.setResultCode(BaseResultInfo.FAIL);
            resultInfo.setResultMessage(e.getMessage());

            Map<Object, Exception> failedMessages = e.getFailedMessages();
            if (MapUtils.isNotEmpty(failedMessages) && CollectionUtils.isNotEmpty(failedMessages.values()))
            {
                for (Exception fe : failedMessages.values())
                {
                    if (fe instanceof SMTPSendFailedException)
                    {
                        resultInfo.setResultCode(BaseResultInfo.SUCCESS);
                        resultInfo.setResultMessage(fe.getMessage());

                        resultInfo.setValidSentAddrs(getMailAddresses(((SMTPSendFailedException) fe).getValidSentAddresses()));
                        resultInfo.setValidUnSentAddrs(getMailAddresses(((SMTPSendFailedException) fe).getValidUnsentAddresses()));
                        resultInfo.setInvalidAddrs(getMailAddresses(((SMTPSendFailedException) fe).getInvalidAddresses()));

                        break;
                    }
                }
            }
        }
        catch (Exception e)
        {
            LOGGER.error("发送邮件失败", e);

            resultInfo.setResultCode(BaseResultInfo.FAIL);
            resultInfo.setResultMessage(e.getMessage());
        }

        return resultInfo;
    }

    private static void send(String subject, String content, String[] receivers) throws MessagingException
    {
        MimeMessage mime = _mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mime, true, "utf-8");
        helper.setValidateAddresses(true);
        helper.setFrom(MailSenderService._mailFrom);// 发件人
        helper.setTo(receivers);// 收件人
        helper.setSubject(subject);// 邮件主题
        helper.setText(content, true);// true表示设定html格式

        _mailSender.send(mime);
    }

    private static String[] getMailAddresses(Address[] addresses)
    {
        String[] mailAddresses = new String[addresses.length];
        for (int i = 0; i < addresses.length; i++)
        {
            mailAddresses[i] = addresses[i].toString();
        }

        return mailAddresses;
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {
        MailSenderService._mailSender = this.mailSender;
        MailSenderService._mailFrom = this.mailFrom;
    }
}
