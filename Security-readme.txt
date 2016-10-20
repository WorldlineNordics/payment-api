# generate a 1024-bit RSA private key
$ openssl genrsa -out private_key.pem 1024

# convert private Key to PKCS#8 format (so Java can read it)
$ openssl pkcs8 -topk8 -inform PEM -outform DER -in private_key.pem \
    -out private_key.der -nocrypt

# output public key portion in DER format (so Java can read it)
$ openssl rsa -in private_key.pem -pubout -outform DER -out public_key.der


   1. openssl DER output options (particularly the PKCS#8 encoding)
   2. which type of KeySpec Java needed to use (strangely enough, the public key needs the "X509" keyspec, even though you would normally handle X.509 certificates with the openssl x509 command, not the openssl rsa command. Real intuitive.)

# To create PaymentPageAPI javadoc
C:\Workspace\PaymentPage\Startkit DigitalRiver\Source files>javadoc -public -d PaymentPageAPI\doc -sourcepath PaymentPag
eAPI\src -subpackages com
