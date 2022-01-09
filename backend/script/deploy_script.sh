#!/bin/bash

NOHUP_LOCATION=/home/ec2-user/fieldtrip/backend/script
JAR_PATH=/home/ec2-user/fieldtrip/backend/build/libs
JAR_NAME=golfie-v1.0.0.jar
ACTIVE_PROFILE=dev

echo "> Let's get started."

CURRENT_PID=$(pgrep -fl golfie | grep java | awk '{print $1}')

if [ -z "$CURRENT_PID" ]; then
    echo "> 현재 구동 중인 애플리케이션이 없습니다."
else
    echo "> 이미 구동 중인 애플리케이션(PID: $CURRENT_PID)을 종료합니다."
    kill -9 $CURRENT_PID
    sleep 3
fi

rm $JAR_PATH/*plain*.jar

echo "> 서버를 실행합니다."
nohup java -jar -Dspring.profiles.active=$ACTIVE_PROFILE $JAR_PATH/$JAR_NAME > $NOHUP_LOCATION/nohup.out 2>&1 &

echo "> Completed."