This is where we store magic configurations for terraform, ansible, kubernetes,
jenkins, etc.

# Spinning up a dev instance

## First time setup

* You'll need an [SSH key](https://www.vultr.com/docs/how-do-i-generate-ssh-keys/)
in your Vultr account. Add one. You should do this locally so you can SSH to
instances.
* Get an API key from Vultr while you're at it.
* Ensure you have the
[Vultr Terraform provider](https://github.com/squat/terraform-provider-vultr)
installed.

## To spin up an instance

Currently, we only support the `main.tf` in the `provisioning/provider/vultr`
directory. Head there and try the below.


    # Get started (only run once)
    terraform init

    # See the cool plan!
    terraform plan -var="token=API_KEY" -var='ssh_keys=["KEY_NAME"]' -var="hosts=1" -var='hostname_format="kube%d"'
    ...

    # Make that plan a reality!
    terraform apply -var="token=API_KEY" -var='ssh_keys=["KEY_NAME"]' -var="hosts=1" -var='hostname_format="kube%d"'
    ...

   # And then tear it down if you want
   terraform destroy -var="token=API_KEY" -var='ssh_keys=["KEY_NAME"]' -var="hosts=1" -var='hostname_format="kube%d"'
