<html>
<head>
    <title>Error Page</title>
</head>
<body>

<br>
<br>
<br>
<center>
    <h1> ERROR ALARM AHTUG!!! <p></h1>
    <#if createErrorForFtl??>
    Error message: <br>
    ${createErrorForFtl.message}
    <br>
    <br>
    <br>
    <br>
    Error from URL: <br>
    ${createErrorForFtl.url}
    <br>
    <br>
    <br>
    <br>
    Stack trace for this error: <br>
    ${createErrorForFtl.stackTrace}
</#if>

</center>
<br>
<center>
    <h2>
        <a href="/carrent">Go to index page</a>
    </h2>
</center>

</body>
</html>