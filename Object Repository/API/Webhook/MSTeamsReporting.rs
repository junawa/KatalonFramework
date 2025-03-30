<?xml version="1.0" encoding="UTF-8"?>
<WebServiceRequestEntity>
   <description></description>
   <name>MSTeamsReporting</name>
   <tag></tag>
   <elementGuidId>2cefca82-f06e-4694-b8bf-0e18b6b34ff6</elementGuidId>
   <selectorMethod>BASIC</selectorMethod>
   <smartLocatorEnabled>false</smartLocatorEnabled>
   <useRalativeImagePath>false</useRalativeImagePath>
   <autoUpdateContent>false</autoUpdateContent>
   <connectionTimeout>0</connectionTimeout>
   <followRedirects>false</followRedirects>
   <httpBody></httpBody>
   <httpBodyContent>{
  &quot;text&quot;: &quot;{\n    \&quot;type\&quot;: \&quot;message\&quot;,\n    \&quot;attachments\&quot;: [\n        {\n            \&quot;contentType\&quot;: \&quot;application/vnd.microsoft.card.adaptive\&quot;,\n            \&quot;content\&quot;: {\n                \&quot;type\&quot;: \&quot;AdaptiveCard\&quot;,\n                \&quot;body\&quot;: [\n                    {\n                    \&quot;type\&quot;: \&quot;FactSet\&quot;,\n                    \&quot;spacing\&quot;: \&quot;large\&quot;,\n                    \&quot;facts\&quot;: [\n                        {\n                            \&quot;title\&quot;: \&quot;Test Suite\&quot;,\n                            \&quot;value\&quot;: \&quot;${testSuite}\&quot;\n                        },\n                        {\n                            \&quot;title\&quot;: \&quot;Environment\&quot;,\n                            \&quot;value\&quot;: \&quot;${env}\&quot;\n                        },\n                        {\n                            \&quot;title\&quot;: \&quot;Platform\&quot;,\n                            \&quot;value\&quot;: \&quot;${platform}\&quot;\n                        },\n                        {\n                            \&quot;title\&quot;: \&quot;Time Start\&quot;,\n                            \&quot;value\&quot;: \&quot;${timeStart}\&quot;\n                        },\n                        {\n                            \&quot;title\&quot;: \&quot;Time End\&quot;,\n                            \&quot;value\&quot;: \&quot;${timeEnd}\&quot;\n                        },\n                        {\n                            \&quot;title\&quot;: \&quot;Total Duration\&quot;,\n                            \&quot;value\&quot;: \&quot;${totalDuration}\&quot;\n                        },\n                        {\n                            \&quot;title\&quot;: \&quot;PASSED\&quot;,\n                            \&quot;value\&quot;: \&quot;${countPassed}\&quot;\n                        },\n                        {\n                            \&quot;title\&quot;: \&quot;FAILED\&quot;,\n                            \&quot;value\&quot;: \&quot;${countFailed}\&quot;\n                        },\n                        {\n                            \&quot;title\&quot;: \&quot;ERROR\&quot;,\n                            \&quot;value\&quot;: \&quot;${countError}\&quot;\n                        },\n                        {\n                            \&quot;title\&quot;: \&quot;SKIPPED\&quot;,\n                            \&quot;value\&quot;: \&quot;${countSkipped}\&quot;\n                        },\n                        {\n                            \&quot;title\&quot;: \&quot;Total Count\&quot;,\n                            \&quot;value\&quot;: \&quot;${countTotal}\&quot;\n                        }\n                        ]\n                        }\n                ],\n                \&quot;$schema\&quot;: \&quot;http://adaptivecards.io/schemas/adaptive-card.json\&quot;,\n                \&quot;version\&quot;: \&quot;1.0\&quot;\n            }\n        }\n    ]\n}&quot;,
  &quot;contentType&quot;: &quot;application/json&quot;,
  &quot;charset&quot;: &quot;UTF-8&quot;
}</httpBodyContent>
   <httpBodyType>text</httpBodyType>
   <httpHeaderProperties>
      <isSelected>true</isSelected>
      <matchCondition>equals</matchCondition>
      <name>Content-Type</name>
      <type>Main</type>
      <value>application/json</value>
      <webElementGuid>8fe0072e-8c06-4854-9d47-164293e82a4e</webElementGuid>
   </httpHeaderProperties>
   <katalonVersion>9.6.0</katalonVersion>
   <maxResponseSize>0</maxResponseSize>
   <migratedVersion>5.4.1</migratedVersion>
   <path></path>
   <restRequestMethod>POST</restRequestMethod>
   <restUrl>https://securitybankcorporation.webhook.office.com/webhookb2/a05ea833-5d0b-4160-b0a9-010d2d4997a6@eb18a341-8b39-42f4-a368-bcb385e9422f/IncomingWebhook/b441b74bdffd4ce795cc0d9e782dd99c/963736aa-75fd-44f6-a3b0-bf1b1d47980c/V2BoSFU1ohoysPGWw3004r5_90h3S1YOiJZ_qaI7Haa8I1</restUrl>
   <serviceType>RESTful</serviceType>
   <soapBody></soapBody>
   <soapHeader></soapHeader>
   <soapRequestMethod></soapRequestMethod>
   <soapServiceEndpoint></soapServiceEndpoint>
   <soapServiceFunction></soapServiceFunction>
   <socketTimeout>0</socketTimeout>
   <useServiceInfoFromWsdl>true</useServiceInfoFromWsdl>
   <variables>
      <defaultValue>GlobalVariable.ENV</defaultValue>
      <description></description>
      <id>a727bd88-1c73-4e25-8ba6-a7b4beba9185</id>
      <masked>false</masked>
      <name>env</name>
   </variables>
   <variables>
      <defaultValue>GlobalVariable.DEFAULT_PLATFORM</defaultValue>
      <description></description>
      <id>9ac1a7de-04bb-4857-adde-8cb0ee95cfbf</id>
      <masked>false</masked>
      <name>platform</name>
   </variables>
   <verificationScript>import static org.assertj.core.api.Assertions.*

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webservice.verification.WSResponseManager

import groovy.json.JsonSlurper
import internal.GlobalVariable as GlobalVariable

RequestObject request = WSResponseManager.getInstance().getCurrentRequest()

ResponseObject response = WSResponseManager.getInstance().getCurrentResponse()</verificationScript>
   <wsdlAddress></wsdlAddress>
</WebServiceRequestEntity>
