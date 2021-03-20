<h1>Pastore - free key value store in memory</h1>

<h2>Instructions</h2>
<ol>
  <li>Clone current repository <pre><code>git clone git@github.com:paveleroshkinweb/pastore.git</pre></code></li>
  <li>Make sure that all scripts under scripts folder are executable, if they are not executable you can run <pre><code>sudo chmod -R +x scripts</pre></code></li>
  <li>You might need to install additional dependencies(maven and jdk), to do this you can go under scripts folder and run <pre><code>sudo ./install.sh</pre></code></li>
  <li>Run build script with a folder param which shows where .jar file should be located, i.e.<pre><code>sudo ./build /home/pavel/pastore</pre></code>This script will create a pastore.service file under /etc/systemd/system/pastore.servece, also it will create a new group and a user in your system with a name pastore. It will put pastore.properties and secrets.env under new folder /etc/pastore</li>
  <li>To ease the control process you might need to put this code in your .bashrc file
  <pre><code>
  alias start-pastore='sudo systemctl daemon-reload; systemctl start pastore.service; systemctl status pastore.service'
  alias stop-pastore='sudo systemctl stop pastore.service'
  alias status-pastore='sudo systemctl status pastore.service'
  alias restart-pastore='sudo systemctl restart pastore.service'
  </pre></code></li>
  <li>Now you can run pastore with <pre><code>start-pastore</pre></code> and check the status with <pre><code>status-pastore</pre></code>if everything went good you should be able to see a successfull message</li>
  <li>Note: if you want to undone all those things you can run <pre><code>sudo ./unsetup</pre></code>it will remove pastore.service from system.d, pastore user and group, /etc/pastore folder.</li>
</ol>