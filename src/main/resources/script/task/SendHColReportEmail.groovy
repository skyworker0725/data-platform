package script.task

import tv.vego.dp.service.util.ReportGetter
import tv.vego.dp.util.FreeMarkerUtil
import tv.vego.dp.util.ReportVarUtil
import tv.vego.dp.util.SendEmailUtil

//任务参数
def taskParams = getBinding().getVariables()
taskParams.each {key, value ->
    taskParams.put(key, ReportVarUtil.getEvalValue(value))
}

//模板参数
def ftlData = new HashMap<String, Object>()

//报表元信息
def reportId = taskParams.get("report_id").toString()
def reportDefine = ReportGetter.getMeta(reportId)
ftlData.put("reportDefine", reportDefine)

//报表数据
def reportData = ReportGetter.getData(reportId, taskParams, null, null)
ftlData.put("reportData", reportData)

//获取邮件内容
def mailContent = FreeMarkerUtil.generateResult("template", "normal_html_h_table.ftl", ftlData)

//发送邮件
SendEmailUtil.sendMail(reportDefine.getReportName(), mailContent, taskParams.get("recipients").toString().split(","))