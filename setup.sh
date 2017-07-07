# Install the AWS CLI and run this script
# ./setup.sh GITHUB_USER GITHUB_TOKEN GITHUB_REPO BRANCH_NAME
aws cloudformation update-stack --stack-name=bobdylan --template-body "$(cat pipeline-definition.yaml)" --region=us-east-1 --capabilities=CAPABILITY_IAM --parameters ParameterKey=GitHubUser,ParameterValue=$1 ParameterKey=GitHubToken,ParameterValue=$2 ParameterKey=Repo,ParameterValue=$3 ParameterKey=Branch,ParameterValue=$4
