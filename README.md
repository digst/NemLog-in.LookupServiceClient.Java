# NemLog-in.LookupServiceClient.Java

An OIO IDWS REST client sample for NemLog-in Lookup Services.

## Introduction

This Java code shows how to invoke NemLog-in Lookup Services using the OIO IDWS authorization model.

The services are described in section 2 of [SS].
Please refer to [SS] for detailed documentation of available services.

Note especially, that services described in section 3 of [SS] (UUID match services)
use a different authentication and authorization model and that this sample is not relevant
if you need to use the UUID match services.

## Solution

The solution consists of a single Java Spring Boot application that executes a REST invocation of PID-CPR service in ```Application.run()```


## User guide

* Run application with ```mvn spring-boot:run```

The client will:

1. Request a SAML token at the NemLog-in STS
2. Exchange the SAML token for a JWT token (only for REST)
3. Invoke the PID-CPR methods in the NemLog-in pre-production environment (domain ```devtest4-nemlog-in.dk```)


## Certificates

<dl>

<dt>trust.jks</dt>
<dd>
Trust-store containing the signing certificate for the NemLog-in pre-production STS.
Used for verifying authenticity of the SAML tokens issued by the STS.
<br>
Note that SOAP and REST clients must update this certificate in their environment when it is updated in NemLog-in.
<br>
Subject: <br>

```CN=NemLog-in IdP - Test, SERIALNUMBER=UI:DK-O:G:a040fe26-ce78-4328-ac0e-29eeb12f46df, O=Digitaliseringsstyrelsen, organizationIdentifier=NTRDK-34051178, C=DK```
</dd>

<dt>lookupservice-testwsc-test.pfx</dt>
<dd>File containing certificate and private key used by clients (WSC) to sign the STS request.
NOTE: It is very important, that you do not reuse this certificate for your own WSC! Follow the guide 
[UG] when you establish your own WSC. <br>

Subject: <br>
```CN=NemLog-in LookupServices.TestWSC - Test, SERIALNUMBER=UI:DK-O:G:9f716cec-2420-4aff-b455-4972897b6249, O=Digitaliseringsstyrelsen, organizationIdentifier=NTRDK-34051178, C=DK```
</dd>

</dl>


## References

<dl>
<dt>[SS]</dt>
<dd>NemLog-in Supporting Services documentation, available at 
https://tu.nemlog-in.dk/oprettelse-og-administration-af-tjenester/log-in/dokumentation.og.guides/. 
</dd>

<dt>[UG]</dt>
<dd>Guide til anvendelse af Opslagstjenester, available at 
https://tu.nemlog-in.dk/oprettelse-og-administration-af-tjenester/log-in/dokumentation.og.guides/. 
</dd>

<dt>[Inttest]</dt>
<dd>
Certificates for Integrationtest can be obtained at 

https://www.nemlog-in.dk/metadata/
</dd>

<dt>[Prod]</dt>
<dd>
Certificates for Production can be obtained at

https://www.nemlog-in.dk/metadata/
</dd>


</dl>
