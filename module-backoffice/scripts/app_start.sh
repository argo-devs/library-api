#!/bin/bash

######################
## 스크립트 추후 수정할 예정
######################

BUILD_JAR=$(ls /home/ec2-user/library/build/libs/*.jar)
JAR_NAME=$(basename $BUILD_JAR)
echo "> Build 파일명: $JAR_NAME" >> /home/ec2-user/library/deploy.log

echo "> Build 파일 복사" >> /home/ec2-user/library/deploy.log
DEPLOY_PATH=/home/ec2-user/library/
cp $BUILD_JAR $DEPLOY_PATH

echo "> 현재 실행중인 애플리케이션 PID 확인" >> /home/ec2-user/library/deploy.log
CURRENT_PID=$(pgrep -f $JAR_NAME)

if [ -z $CURRENT_PID ]
then
  echo "> 현재 구동중인 애플리케이션이 없으므로 종료하지 않습니다." >> /home/ec2-user/library/deploy.log
else
  echo "> kill -15 $CURRENT_PID" >> /home/ec2-user/library/deploy.log
  kill -15 $CURRENT_PID
  sleep 20
fi

DEPLOY_JAR=$DEPLOY_PATH$JAR_NAME
echo "> DEPLOY_JAR 배포"    >> /home/ec2-user/library/deploy.log
nohup java -jar $DEPLOY_JAR >> /home/ec2-user/deploy.log 2>/home/ec2-user/library/deploy_err.log &
