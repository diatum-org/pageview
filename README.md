# PageView

Without a view count in Ghost, we didn't know if our blog posts were actually reaching anyone. Because of our mission in privacy, we didn't feel comfortable using 3rd party analytics, so we came up with our own quick solution.

Since we are using an AWS EC2 instance to run our blog, we have it run an additional app in tomcat, and record the page views. To update the server, copy over the java app (pageview.war) and the sql delta script (pageview.sql), and run the script (scripts/setup.sh) on the target server.

Finally, in each page, we then add the following code the the Post footer of the Code Injection section, and a view counter will appear at the bottom of the post:


```js
<script>
  const Http = new XMLHttpRequest();
  const url='https://< blog hostname >:8443/page/view?id=< page ID >';
  Http.open("PUT", url);
  Http.send();
  Http.onreadystatechange = (e) => {
    document.getElementById("views").innerHTML=Http.responseText;
  }
</script>
<div style="text-align:center;font-size:12px">
  <span id="views"></span>
  <span>views</span>
</div>
