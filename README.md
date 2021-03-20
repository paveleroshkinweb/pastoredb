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
  <li>Now you can run pastore with <pre><code>start-pastore</pre></code>if everything went well you should be able to see a successfull message</li>
  <li>Note: if you want to undone all those things you can run <pre><code>sudo ./unsetup</pre></code>it will remove pastore.service from systemd, pastore user and group, /etc/pastore folder, but it will not remove a jar file under the folder which you used in build command</li>
  <li>if you want to move the jar file in another folder you need to
    <ul>
      <li>stop pastore service via<pre><code>stop-pastore</pre></code>
      <li>move the jar file in whatever folder you want</li>
      <li>change WorkingDirectory property in /etc/systemd/system/pastore.service on a new folder</li>
      <li>start pastore service via<pre><code>start-pastore</pre></code></li>
    </ul>
  </li>
  <li>if you want to change the code base you can do it but then you have to rebuild a project
    <ul>
      <li>stop pastore service via<pre><code>stop-pastore</pre></code></li>
      <li>run rebuild script with a folder where pastore-1.0.jar located<pre><code>sudo ./rebuild /path/to/jar</pre></code></li>
      <li>start pastore service via<pre><code>start-pastore</pre></code></li>
    </ul>
    Note: this command will remove previous pastore-1.0.jar, also this command makes pastore user as folder owner.Folder which you use should contain only pastore build.
  </li>
  <li>if you need to change the config or secrets
    <ul>
      <li>go to /etc/pastore/</li>
      <li>change whatever you need</li>
      <li>restart pastore service via <pre><code>restart-pastore</pre></code></li>
    </ul>
  </li>
</ol>