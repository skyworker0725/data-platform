package tv.vego.dp.vo;

import java.io.Serializable;

/**
 * 结果信息
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2016/1/26 9:33
 */
public class BaseResultInfo implements Serializable
{
    public static final int SUCCESS = 0;

    public static final int FAIL = 1;

    private int resultCode;

    private String resultMessage;

    public BaseResultInfo()
    {
    }

    public BaseResultInfo(int resultCode, String resultMessage)
    {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
    }

    public static BaseResultInfo getFailResult(String resultMessage)
    {
        return new BaseResultInfo(FAIL, resultMessage);
    }

    public static BaseResultInfo getSuccessResult(String resultMessage)
    {
        return new BaseResultInfo(SUCCESS, resultMessage);
    }

    public boolean isSuccess()
    {
        return resultCode == SUCCESS;
    }

    public int getResultCode()
    {
        return resultCode;
    }

    public void setResultCode(int resultCode)
    {
        this.resultCode = resultCode;
    }

    public String getResultMessage()
    {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage)
    {
        this.resultMessage = resultMessage;
    }
}
