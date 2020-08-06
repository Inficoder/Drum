#!/bin/bash
cd /root/service_code_rep/bumblebee_backend
echo "####################################"
echo "开始拉取最新代码"
git pull
echo "代码更新完成"

echo "####################################"
echo "开始编译打包"
mvn clean install
echo "编译打包完成"

echo "####################################"
echo "开始部署jar包"
mv /root/service_code_rep/bumblebee_backend/target/littlebee-0.0.1-SNAPSHOT.jar /root/services/littlebee-0.0.1-SNAPSHOT.jar
mv /root/service_code_rep/bumblebee_backend/target/littlebee-0.0.1-SNAPSHOT.jar.original /root/services/littlebee-0.0.1-SNAPSHOT.jar.original
echo "部署完成"
cd /root/services

echo "####################################"
echo "尝试终止服务进程"
jar_pid=`ps -ef | grep "littlebee-0.0.1-SNAPSHOT.jar" | grep -v "grep"|awk '{print $2}'`
if [  -n $jar_pid ]
then
   kill -9 $jar_pid
fi

echo "####################################"
echo "启动服务中....."
nohup java -jar -Dspring.profiles.active=prod littlebee-0.0.1-SNAPSHOT.jar > /root/services/logs/littlebee/service-littlebee.log &
echo "#############输出启动日志####################"
tail -f /root/services/logs/littlebee/service-littlebee.log