///////////////////////////////////////////////////////////////////////////
// Variables
///////////////////////////////////////////////////////////////////////////

// Provider variables with -var=token=FOO -var=ssh_key_name=BAR...
variable "token" {}
variable "ssh_keys" { type = "list" }
variable "hosts" { default = 0 }
variable "hostname_format" { type = "string" }
variable "region" {
  type    = "string"
  default = "Miami"
}

///////////////////////////////////////////////////////////////////////////
// Provisioning
///////////////////////////////////////////////////////////////////////////

provider "vultr" {
  api_key = "${var.token}"
}

data "vultr_region" "miami" {
  filter {
    name   = "name"
    values = ["${var.region}"]
  }
}

// Find the ID for CoreOS Container Linux.
data "vultr_os" "container_linux" {
  filter {
    name   = "name"
    values = ["Ubuntu 17.04 x64"]
  }
}

// Find the ID for a mini-starter plan.
data "vultr_plan" "starter" {
  filter {
    name   = "price_per_month"
    values = ["5.00"]
  }

  filter {
    name   = "ram"
    values = ["1024"]
  }
}

// Find the ID of an existing SSH key.
data "vultr_ssh_key" "ids" {
  filter {
    name   = "name"
    values = "${var.ssh_keys}"
  }
}

// Create a new firewall group.
resource "vultr_firewall_group" "the_wall" {
  description = "dev group"
}

// Add a firewall rule to the group allowing SSH access.
resource "vultr_firewall_rule" "ssh" {
  firewall_group_id = "${vultr_firewall_group.the_wall.id}"
  cidr_block        = "0.0.0.0/0"
  protocol          = "tcp"
  from_port         = 22
  to_port           = 22
}

// Create a Vultr virtual machine.
resource "vultr_instance" "host" {
  name              = "${format(var.hostname_format, count.index + 1)}"
  hostname          = "${format(var.hostname_format, count.index + 1)}"
  region_id         = "${data.vultr_region.miami.id}"
  plan_id           = "${data.vultr_plan.starter.id}"
  os_id             = "${data.vultr_os.container_linux.id}"
  ssh_key_ids       = ["${data.vultr_ssh_key.ids.*.id}"]
  tag               = "container-linux"
  firewall_group_id = "${vultr_firewall_group.the_wall.id}"

  count = "${var.hosts}"

  provisioner "remote-exec" {
    inline = [
      "until [ -f /var/lib/cloud/instance/boot-finished ]; do sleep 1; done",
      "apt-get update",
      "apt-get install -yq nfs-common",
    ]
  }
}

///////////////////////////////////////////////////////////////////////////
// Outputs
///////////////////////////////////////////////////////////////////////////

output "hostnames" {
  value = ["${vultr_instance.host.*.name}"]
}

output "public_ips" {
  value = ["${vultr_instance.host.*.ipv4_address}"]
}

output "private_ips" {
  value = ["${vultr_instance.host.*.ipv4_address_private}"]
}

output "private_network_interface" {
  value = "eth1"
}
