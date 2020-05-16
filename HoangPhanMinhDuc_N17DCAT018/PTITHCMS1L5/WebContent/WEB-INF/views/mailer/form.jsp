<%@ page pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <base href="/Lab5/"/>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Mail</title>
</head>
<body>
  ${message}
  <form action ="mailer/send.htm" method="POST">
  	<p> <input name="from" placeholder="From" > </p>
  	<p> <input name="to" placeholder="To" > </p>
  	<p> <input name="subject" placeholder="Subject" > </p>
  	<p> <textarea name="body" placeholder="Body" rows="3" cols="30"></textarea> </p>
  	<button>Send</button>
  </form>
</body>
</html>