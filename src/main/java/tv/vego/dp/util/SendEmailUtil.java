package tv.vego.dp.util;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tv.vego.dp.service.util.MailSenderService;
import tv.vego.dp.vo.MailSendResultInfo;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 邮件发送工具
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2016/1/13 14:10
 */
public class SendEmailUtil
{
    private static final Logger LOGGER = LoggerFactory.getLogger(SendEmailUtil.class);

    private static final BlockingDeque<MailInfo> EMAILS = new LinkedBlockingDeque<>();

    private static final int EMAIL_SEND_RETRY_TIMES = 20;

    private static Thread EMAIL_SEND_THREAD = null;

    /**
     * 发送邮件
     *
     * @param subject   主题
     * @param context   内容
     * @param receivers 收件人
     */
    public static void sendMail(String subject, String context, String[] receivers) throws InterruptedException
    {
        EMAILS.put(new MailInfo(subject, context, receivers));

        checkSendThreadStatus();
    }

    private static void checkSendThreadStatus()
    {
        if (null == EMAIL_SEND_THREAD || !EMAIL_SEND_THREAD.isAlive())
        {
            EMAIL_SEND_THREAD = new Thread(new Runnable()
            {
                @SuppressWarnings("InfiniteLoopStatement")
                @Override
                public void run()
                {
                    while (true)
                    {
                        boolean sendResult;
                        MailInfo mailInfo = null;
                        try
                        {
                            mailInfo = EMAILS.take();
                            mailInfo.setSendTimes(mailInfo.getSendTimes() + 1);

                            sendResult = sendEmail(mailInfo);
                        }
                        catch (Exception e)
                        {
                            sendResult = false;
                            LOGGER.error("发送邮件出错，10秒钟后重试…", e);
                        }

                        if (!sendResult)
                        {
                            sendMailFailedAndRetry(mailInfo);
                        }
                    }
                }

                private void sendMailFailedAndRetry(MailInfo mailInfo)
                {
                    try
                    {
                        if (null != mailInfo)
                        {
                            LOGGER.error("第{}次发送邮件出错，邮件主题：{}，邮件接收者：{}"
                                    , mailInfo.getSendTimes(), mailInfo.getSubject(), mailInfo.getReceivers());

                            //若发送邮件错误，将邮件重新放入队列等待下次发送
                            //若邮件发送次数超过重试次数，放弃此邮件发送
                            if (mailInfo.getSendTimes() < EMAIL_SEND_RETRY_TIMES)
                            {
                                EMAILS.put(mailInfo);
                            }
                        }

                        Thread.sleep(10000);
                    }
                    catch (InterruptedException e)
                    {
                        LOGGER.error("重试邮件队列出错", e);
                    }
                }

                private boolean sendEmail(MailInfo mailInfo)
                {
                    LOGGER.info("开始发送邮件【{}】至【{}】", mailInfo.subject, mailInfo.getReceivers());

                    MailSendResultInfo resultInfo = MailSenderService.sendMail(mailInfo.getSubject(), mailInfo.getContent(), mailInfo.getReceivers());

                    //全部发送成功
                    if (ParamConstant.RESULT_SUCCESS_CODE == resultInfo.getResultCode())
                    {
                        if (ArrayUtils.isEmpty(resultInfo.getValidUnSentAddrs()))
                        {
                            LOGGER.info("完成发送邮件【{}】至【{}】", mailInfo.subject, mailInfo.getReceivers());

                            return true;
                        }
                        else
                        {
                            LOGGER.info("完成发送邮件【{}】至【{}】，继续发送邮件至【{}】"
                                    , mailInfo.subject, resultInfo.getValidSentAddrs(), resultInfo.getValidUnSentAddrs());

                            mailInfo.setReceivers(resultInfo.getValidUnSentAddrs());

                            return false;
                        }
                    }
                    else
                    {
                        LOGGER.info("发送邮件【{}】至【{}】失败，失败信息为【{}】", mailInfo.subject, mailInfo.getReceivers(), resultInfo.getResultMessage());

                        return false;
                    }
                }
            });

            EMAIL_SEND_THREAD.start();
        }
    }

    private static class MailInfo
    {
        private String subject;

        private String content;

        private String[] receivers;

        private int sendTimes;

        public MailInfo(String subject, String content, String[] receivers)
        {
            this.subject = subject;
            this.content = content;
            this.receivers = receivers;
        }

        public String getSubject()
        {
            return subject;
        }

        public void setSubject(String subject)
        {
            this.subject = subject;
        }

        public String getContent()
        {
            return content;
        }

        public void setContent(String content)
        {
            this.content = content;
        }

        public String[] getReceivers()
        {
            return receivers;
        }

        public void setReceivers(String[] receivers)
        {
            this.receivers = receivers;
        }

        public int getSendTimes()
        {
            return sendTimes;
        }

        public void setSendTimes(int sendTimes)
        {
            this.sendTimes = sendTimes;
        }
    }
}
