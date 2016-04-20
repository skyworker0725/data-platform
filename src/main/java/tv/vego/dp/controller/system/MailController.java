package tv.vego.dp.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tv.vego.dp.service.util.MailSenderService;
import tv.vego.dp.vo.BaseResultInfo;

/**
 * 邮件发送
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2016/3/11 10:25
 */
@Controller
@RequestMapping(value = "/sys/mail")
public class MailController
{
    @RequestMapping(value = "/send")
    @ResponseBody
    public BaseResultInfo sendEmail(@RequestParam String subject, @RequestParam String content, @RequestParam String to)
    {
        return MailSenderService.sendMail(subject, content, to.split(","));
    }
}
