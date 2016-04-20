<style type="text/css">
    body {
        font: normal 11px auto "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
        color: #4f6b72;
        background: #E6EAE9;
    }

    a {
        color: #c75f3e;
    }

    caption {
        padding: 0 0 5px 0;
        width: 700px;
        font: italic 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
        text-align: right;
    }

    th {
        font: bold 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
        color: #4f6b72;
        border-right: 1px solid #C1DAD7;
        border-bottom: 1px solid #C1DAD7;
        border-top: 1px solid #C1DAD7;
        letter-spacing: 2px;
        text-transform: uppercase;
        text-align: left;
        padding: 6px 6px 6px 12px;
        background: #CAE8EA;
    }

    th.nobg {
        border-top: 0;
        border-left: 0;
        border-right: 1px solid #C1DAD7;
        background: none;
    }

    td {
        border-right: 1px solid #C1DAD7;
        border-bottom: 1px solid #C1DAD7;
        background: #fff;
        font-size: 11px;
        padding: 6px 6px 6px 12px;
        color: #4f6b72;
    }

    td.alt {
        background: #F5FAFA;
        color: #797268;
    }

    th.spec {
        border-left: 1px solid #C1DAD7;
        border-top: 0;
        background: #fff;
        font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
    }

    th.specalt {
        border-left: 1px solid #C1DAD7;
        border-top: 0;
        background: #f5fafa;
        font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
        color: #797268;
    }

    html > body td {
        font-size: 11px;
    }
</style>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>${reportDefine.reportName!'报表名称未设置！！！'}</title>
</head>
<body>
<h2>${reportDefine.reportName!'报表名称未设置！！！'}</h2>
<table id="reportTable" cellspacing="0">
    <tr>
    <#--<#if reportDefine.columns??>-->
        <#list reportDefine.columns as column>
            <th>${column.fieldLabel!'列名称未设置！！！'}</th>
        </#list>
    <#--</#if>-->
    </tr>
<#list reportData as reportRecord>
    <#if reportRecord??>
        <#if reportRecord_index % 2 ==0>
            <tr>
                <#list reportDefine.columns as column>
                    <td>${reportRecord[column.fieldName]!("")}</td>
                </#list>
            </tr>
        <#else>
            <tr>
                <#list reportDefine.columns as column>
                    <td class="alt">${reportRecord[column.fieldName]!("")}</td>
                </#list>
            </tr>
        </#if>
    </#if>
</#list>
</table>
<div>${reportDefine.reportDesc!''}</div>
<h3 style="color: crimson">此内容由系统自动生成，请勿在邮件中回复。</h3>
</body>
</html>