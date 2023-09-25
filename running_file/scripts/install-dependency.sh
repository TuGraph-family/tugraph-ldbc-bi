#!/usr/bin/env bash

set -eu
set -o pipefail


pip3 install -r ./lib/requirements.txt
echo 'python lib install success'

yum install -y zip
yum install -y git
yum install -y java-1.8.0-openjdk-devel.x86_64
# install maven
rm -rf apache-maven*
wget https://geaflow-ldbc.oss-cn-hangzhou-internal.aliyuncs.com/apache-maven-3.9.4-bin.zip
unzip apache-maven-3.9.4-bin.zip
rm apache-maven-3.9.4/conf/settings.xml
cp ./lib/maven-settings.xml apache-maven-3.9.4/conf/settings.xml
export M2_HOME=`pwd`/apache-maven-3.9.4
export M2=$M2_HOME/bin
export PATH=$M2:$PATH
echo 'Maven install success'

# build tugraph driver
rm -rf tugraph-ldbc-bi
rm -rf ../lib/*
git clone https://github.com/TuGraph-family/tugraph-ldbc-bi
cd tugraph-ldbc-bi
mvn clean install
cp target/tugraph-*.jar ../lib
echo 'TuGraph client build success'

