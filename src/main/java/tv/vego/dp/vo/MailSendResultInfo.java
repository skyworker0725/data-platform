package tv.vego.dp.vo;

/**
 * 邮件发送结果
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2016/3/11 11:36
 */
public class MailSendResultInfo extends BaseResultInfo
{
    private String[] validSentAddrs;

    private String[] validUnSentAddrs;

    private String[] invalidAddrs;

    public String[] getValidSentAddrs()
    {
        return validSentAddrs;
    }

    public void setValidSentAddrs(String[] validSentAddrs)
    {
        this.validSentAddrs = validSentAddrs;
    }

    public String[] getValidUnSentAddrs()
    {
        return validUnSentAddrs;
    }

    public void setValidUnSentAddrs(String[] validUnSentAddrs)
    {
        this.validUnSentAddrs = validUnSentAddrs;
    }

    public String[] getInvalidAddrs()
    {
        return invalidAddrs;
    }

    public void setInvalidAddrs(String[] invalidAddrs)
    {
        this.invalidAddrs = invalidAddrs;
    }
}
