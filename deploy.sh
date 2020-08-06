#!/bin/bash
cd /home/ubuntu/code_repository/drum
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
mv /home/ubuntu/code_repository/drum/target/drum-0.0.1-SNAPSHOT.jar /home/ubuntu/services/drum-0.0.1-SNAPSHOT.jar
mv /home/ubuntu/code_repository/drum/target/drum-0.0.1-SNAPSHOT.jar.original /home/ubuntu/services/drum-0.0.1-SNAPSHOT.jar.original
echo "部署完成"
cd /root/services

echo "####################################"
echo "尝试终止服务进程"
jar_pid=`ps -ef | grep "drum-0.0.1-SNAPSHOT.jar" | grep -v "grep"|awk '{print $2}'`
if [  -n $jar_pid ]
then
   kill -9 $jar_pid
fi

echo "####################################"
echo "启动服务中....."
nohup java -jar -Dspring.profiles.active=prod drum-0.0.1-SNAPSHOT.jar > /home/ubuntu/services/logs/littlebee/service-drum.log &
echo "#############输出启动日志####################"
tail -f /home/ubuntu/services/logs/drum/service-drum.log