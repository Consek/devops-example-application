variable "hcloud_token" {
  description = "Token for accessing Hetzner Cloud"
}

variable "cloudflare_api_token" {
  description = "Token for accessing Cloudflare"
}

variable "dns_zone" {
  description = "Token for accessing Cloudflare"
  default     = "kacik.it"
}
