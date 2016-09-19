/**
* This script reads the xml file with connections configuration
* allows to retrieve the connection with specified values and
* create connection string as well
* https://community.smartbear.com/t5/SoapUI-NG/Parsing-XML-file-with-Groovy-script/m-p/127139#M29312
* NOTE: If using in soapui, replace "println" with "log.info"
**/

def envs = ['test', 'sandbox']
def connNames = ['wba', 'vb', 'cnx', 'rb', 'subinfo']
def xml = '''<?xml version="1.0" encoding="UTF-8"?>
<connStrings>
   <env name="test">
      <conn>
         <connName>wba</connName>
         <host>host01</host>
         <port>1500</port>
         <user>wbauser</user>
         <password>wbapassword</password>
         <sid>test</sid>
      </conn>
      <conn>
         <connName>vb</connName>
         <host>host02</host>
         <port>1500</port>
         <user>vbuser</user>
         <password>vbpassword</password>
         <sid>test</sid>
      </conn>
      <conn>
         <connName>cnx</connName>
         <host>host03</host>
         <port>1500</port>
         <user>cnxuser</user>
         <password>cnxpassword</password>
         <sid>test</sid>
      </conn>
      <conn>
         <connName>rb</connName>
         <host>host04</host>
         <port>1500</port>
         <user>rbuser</user>
         <password>rbpassword</password>
         <sid>test</sid>
      </conn>
   </env>
   <env name="sandbox">
      <conn>
         <connName>wba</connName>
         <host>host01</host>
         <port>1500</port>
         <user>wbauser</user>
         <password>wbapassword</password>
         <sid>sandbox</sid>
      </conn>
      <conn>
         <connName>vb</connName>
         <host>host02</host>
         <port>1500</port>
         <user>vbuser</user>
         <password>vbpassword</password>
         <sid>sandbox</sid>
      </conn>
      <conn>
         <connName>cnx</connName>
         <host>host03</host>
         <port>1500</port>
         <user>cnxuser</user>
         <password>cnxpassword</password>
         <sid>sandbox</sid>
      </conn>
      <conn>
         <connName>subinfo</connName>
         <host>host03</host>
         <port>1500</port>
         <user>subinfouser</user>
         <password>subinfopassword</password>
         <sid>sandbox</sid>
      </conn>
   </env>
</connStrings>'''
def connStrings = new XmlSlurper().parseText(xml)

//Get details of sandbox environment
for (String env: envs) {
   for(String connName: connNames) {
      def connetionDetails = getConnectionDetails(connStrings, env, connName)
      if (connetionDetails) {
         showConnectionDetails(connetionDetails, env, connName)
         def connStr = getConnectionString(connectionDetails)
         println "Connection String is : ${connStr}"
      }
   }
}

def getConnectionDetails(def connectionStrings, String environmentName, String connectionName) {
   def envronment = connectionStrings.'**'.find{it.@name == environmentName}
   envronment.'**'.find{ it.name() == 'connName' && it == connectionName}?.parent()
}

def showConnectionDetails(def connexionDetails, String environmentName, String connectionName){
  println "Environment : ${environmentName}, Connection name : ${connectionName}"
  println "Details followed: "
  println "Host : ${connexionDetails.host}"
  println "Port : ${connexionDetails.port}"
  println "User : ${connexionDetails.user}"
  println "Password: ${connexionDetails.password}"
  println "Sid  : ${connexionDetails.sid}"
}

def getConnectionString(def connexionDetails, def prefix='jdbc:oracle:thin:') {
   "${prefix}${connexionDetails.user}/${connexionDetails.password}@${connexionDetails.host}:${connexionDetails.port}:${connexionDetails.sid}" as String
}
