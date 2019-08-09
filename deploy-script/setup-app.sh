#!/usr/bin/env bash
set -x

# Install unzip and OpenJDK 8
apt update -q
apt install unzip openjdk-8-jdk -yqq

## Create directories
#mkdir -p /tmp/auto_mobile_tracking_app/carta_processor_/
mkdir -p /var/ecaphi/spring-app

## Update systemd service
echo "Updating systemd service..."
cp /tmp/auto_mobile_tracking_app/AutoMobileTracking.service /etc/systemd/system/
systemctl daemon-reload
systemctl enable AutoMobileTracking

cp /tmp/auto_mobile_tracking_app/AutoMobileTracking.sh /usr/local/bin/
chmod 550 /usr/local/bin/AutoMobileTracking.sh
#cp /tmp/auto_mobile_tracking_app/prod_processor.conf /var/carta/
#unzip /tmp/auto_mobile_tracking_app/JAMES-1.0-SNAPSHOT.zip -d /tmp/auto_mobile_tracking_app/carta_processor_/

# Stop AutoMobileTracking if it's already running
if (systemctl -q is-active AutoMobileTracking.service) then
	echo "Stopping AutoMobileTracking"
	systemctl stop AutoMobileTracking
fi

## Back up existing CartaProcessor directory, deploy new version
#rm -rf /tmp/auto_mobile_tracking_app/carta_processor_bak/*
#mv /var/carta/carta_processor /tmp/auto_mobile_tracking_app/carta_processor_bak
mv /tmp/auto_mobile_tracking_app/AutomobileMaintenanceTracking-0.0.1-SNAPSHOT /var/ecaphi/spring-app/

# Start AutoMobileTracking
echo "Starting AutoMobileTracking service...."
systemctl start AutoMobileTracking

# Check if AutoMobileTracking is running
if (systemctl -q is-active AutoMobileTracking.service) then
	echo "AutoMobileTracking is running!"
else
	echo "AutoMobileTracking is not running! Deploy failed..."
fi
