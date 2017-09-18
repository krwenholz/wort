// Provider variables with -var=vultr_key=FOO -var=ssh_key_name=BAR...
variable "vultr_key" {}
variable "ssh_key_name" {}

provider "vultr" {
  api_key = "${var.vultr_key}"
}

// Find the ID of the Miami region (cheap instances here at time of writing).
data "vultr_region" "miami" {
  filter {
    name   = "name"
    values = ["Miami"]
  }
}

// Find the ID for CoreOS Container Linux.
data "vultr_os" "container_linux" {
  filter {
    name   = "family"
    values = ["coreos"]
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
// TODO(wenholz): this should be parameterized in a script (Jinja template?)
data "vultr_ssh_key" "Mac" {
  filter {
    name   = "name"
    values = ["${var.ssh_key_name}"]
  }
}

// Create a new firewall group.
resource "vultr_firewall_group" "dev" {
  description = "dev group"
}

// Add a firewall rule to the group allowing SSH access.
resource "vultr_firewall_rule" "ssh" {
  firewall_group_id = "${vultr_firewall_group.dev.id}"
  cidr_block        = "0.0.0.0/0"
  protocol          = "tcp"
  from_port         = 22
  to_port           = 22
}

// Create a Vultr virtual machine.
resource "vultr_instance" "dev" {
  name              = "dev"
  region_id         = "${data.vultr_region.miami.id}"
  plan_id           = "${data.vultr_plan.starter.id}"
  os_id             = "${data.vultr_os.container_linux.id}"
  ssh_key_ids       = ["${data.vultr_ssh_key.Mac.id}"]
  hostname          = "dev0"
  tag               = "container-linux"
  firewall_group_id = "${vultr_firewall_group.dev.id}"
}
