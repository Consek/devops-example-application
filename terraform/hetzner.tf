resource "hcloud_network" "network" {
  name     = "network"
  ip_range = "10.0.0.0/16"
}

resource "hcloud_network_subnet" "network-subnet" {
  type         = "cloud"
  network_id   = hcloud_network.network.id
  network_zone = "eu-central"
  ip_range     = "10.0.1.0/24"
}

resource "tls_private_key" "ssh_key" {
  algorithm = "RSA"
  rsa_bits  = "2048"
}

resource "local_file" "ssh_key" {
  content         = tls_private_key.ssh_key.private_key_pem
  filename        = "${path.module}/hetzner_ssh_key"
  file_permission = 0400
}

resource "hcloud_ssh_key" "ssh_key" {
  name       = "DevOps Application Example"
  public_key = tls_private_key.ssh_key.public_key_openssh
}

resource "hcloud_server" "jenkins" {
  name        = "jenkins"
  server_type = "cx21"
  image       = "ubuntu-20.04"
  ssh_keys    = [hcloud_ssh_key.ssh_key.id]

  network {
    network_id = hcloud_network.network.id
  }

  labels = {
    "App" = "Jenkins"
  }

  # **Note**: the depends_on is important when directly attaching the
  # server to a network. Otherwise Terraform will attempt to create
  # server and sub-network in parallel. This may result in the server
  # creation failing randomly.
  depends_on = [
    hcloud_network_subnet.network-subnet
  ]
}

resource "hcloud_server" "envs" {
  for_each = toset(["staging", "production"])

  name        = each.key
  server_type = "cx21"
  image       = "ubuntu-20.04"
  ssh_keys    = [hcloud_ssh_key.ssh_key.id]

  network {
    network_id = hcloud_network.network.id
  }

  labels = {
    "App" = each.key
  }

  # **Note**: the depends_on is important when directly attaching the
  # server to a network. Otherwise Terraform will attempt to create
  # server and sub-network in parallel. This may result in the server
  # creation failing randomly.
  depends_on = [
    hcloud_network_subnet.network-subnet
  ]
}
