host=ec2-54-219-171-24.us-west-1.compute.amazonaws.com
echo $host

identity_file='-i ~/.ssh/web-server.pem -o StrictHostKeyChecking=no'
ssh_host=$identity_file' -l ec2-user '$host

set -x

# Create temp directory
echo "Cleaning and creating temp directory..."
ssh $ssh_host sudo rm -rf /tmp/auto_mobile_tracking_app/
ssh $ssh_host sudo mkdir -p /tmp/auto_mobile_tracking_app/
ssh $ssh_host sudo chown ec2-user /tmp/auto_mobile_tracking_app/
ssh $ssh_host sudo chmod 744 /tmp/auto_mobile_tracking_app/

# Copy all files
echo "Copying files to host..."
scp $identity_file ./setup-app.sh ec2-user@$host:/tmp/auto_mobile_tracking_app/
scp $identity_file ./AutoMobileTracking.service ec2-user@$host:/tmp/auto_mobile_tracking_app/
scp $identity_file ./AutoMobileTracking.sh ec2-user@$host:/tmp/auto_mobile_tracking_app/
#scp $identity_file ./prod_processor.conf ec2-user@$host:/tmp/auto_mobile_tracking_app/
scp $identity_file ./target/AutomobileMaintenanceTracking-0.0.1-SNAPSHOT.jar ec2-user@$host:/tmp/auto_mobile_tracking_app/

# Run setup script
echo "Running setup script..."
ssh $ssh_host sudo chmod +x /tmp/auto_mobile_tracking_app/setup-app.sh
ssh $ssh_host sudo /tmp/auto_mobile_tracking_app/setup-app.sh

echo "AutoMobileTracking deploy complete!"