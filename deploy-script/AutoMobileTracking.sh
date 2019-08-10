#!/bin/sh
SERVICE_NAME=AutoMobileTracking
PID_PATH_NAME=/tmp/AutoMobileTracking-pid
export JAMES_OPTS="-Denv=prod_processor"

cd /var/ecaphi/spring-app
echo $PWD

case $1 in
    start)
        echo "Starting $SERVICE_NAME ..."
        if [ ! -f $PID_PATH_NAME ]; then
            nohup java -jar /var/ecaphi/spring-app/AutomobileMaintenanceTracking-0.0.1-SNAPSHOT >> /var/ecaphi/spring-app/auto_mobile_tracking.out 2>&1&
                        echo $! > $PID_PATH_NAME
            echo "$SERVICE_NAME started ..."
        else
            echo "$SERVICE_NAME is already running ..."
        fi
    ;;
    stop)
        if [ -f $PID_PATH_NAME ]; then
            PID=$(cat $PID_PATH_NAME);
            echo "$SERVICE_NAME stoping ..."
            kill $PID;
            echo "$SERVICE_NAME stopped ..."
            rm $PID_PATH_NAME
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;
    restart)
        if [ -f $PID_PATH_NAME ]; then
            PID=$(cat $PID_PATH_NAME);
            echo "$SERVICE_NAME stopping ...";
            kill $PID;
            echo "$SERVICE_NAME stopped ...";
            rm $PID_PATH_NAME
            echo "$SERVICE_NAME starting ..."
            nohup /var/carta/carta_processor/JAMES-1.0-SNAPSHOT/bin/JAMES >> /var/carta/carta_processor.out 2>&1&
                        echo $! > $PID_PATH_NAME
            echo "$SERVICE_NAME started ..."
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;
esac