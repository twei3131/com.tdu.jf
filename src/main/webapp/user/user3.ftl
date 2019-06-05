<head>
    <meta charset="UTF-8">
    <title>用户列表</title>
</head>
<body>
    <#if user?? && (user?size > 0)>
        <table>
            <thead>
                <tr>
                    <td>用户编号</td>
                    <td>昵称</td>
                </tr>
            </thead>
            <tbody>
                <#list user as list>
                    <tr>
                        <td>${list.id!'暂无'}</td>
                        <td>${list.nick!'暂无'}</td>
                    </tr>
                </#list>
            </tbody>
        </table>
        <#else>
            <center><h1>暂无</h1></center>
    </#if>
</body>
</html>