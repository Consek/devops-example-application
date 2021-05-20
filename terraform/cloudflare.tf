data "cloudflare_zones" "main" {
  filter {
    name = var.dns_zone
  }
}

resource "cloudflare_record" "main" {
  for_each = {
    "jenkins.katujemy"     = hcloud_server.jenkins.ipv4_address
    "staging.katujemy"     = hcloud_server.envs["staging"].ipv4_address
    "katujemy"             = hcloud_server.envs["production"].ipv4_address
    "api.staging.katujemy" = hcloud_server.envs["staging"].ipv4_address
    "api.katujemy"         = hcloud_server.envs["production"].ipv4_address
  }

  zone_id = data.cloudflare_zones.main.zones[0].id
  name    = each.key
  value   = each.value
  type    = "A"
  ttl     = 1
}
